package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import sql.SqlHelper;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Not found Postgres Driver", e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        LOG.info("UPDATE " + r);
        sqlHelper.transactionExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=? WHERE uuid=?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            deleteContacts(r, conn);
            insertContacts(r, conn);
            deleteSections(r, conn);
            insertSections(r, conn);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        LOG.info("SAVE " + r);
        sqlHelper.transactionExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            insertContacts(r, conn);
            insertSections(r, conn);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("GET " + uuid);
        return sqlHelper.transactionExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "   SELECT * FROM resume r " +
                    "LEFT JOIN contact c " +
                    "       ON r.uuid = c.resume_uuid " +
                    "    WHERE r.uuid = ? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                Resume r = new Resume(uuid, rs.getString("full_name"));
                do {
                    addContact(rs, r);
                } while (rs.next());
                addSections(conn, r);
                return r;
            }
        });
    }

    @Override
    public void delete(String uuid) {
        LOG.info("DELETE " + uuid);
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("" +
                    "   SELECT * FROM resume" +
                    " ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    Resume resume = resumes.get(uuid);
                    if (resume == null) {
                        resume = new Resume(uuid, rs.getString("full_name"));
                        resumes.put(uuid, resume);
                    }
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, resumes.get(rs.getString("resume_uuid")));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resumes.get(rs.getString("resume_uuid")));
                }
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void deleteContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact c WHERE resume_uuid = ?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }

    private void insertContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactsType, String> entry : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String type = rs.getString("type");
        if (type != null) {
            r.setContacts(ContactsType.valueOf(type), rs.getString("value"));
        }
    }

    private void insertSections(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES(?, ?, ?)")) {
            for (Map.Entry<SectionType, AbstractSection> entry : r.getSection().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, entry.getKey().name());
                SectionType type = entry.getKey();
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        ps.setString(3, ((TextSection) entry.getValue()).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        List<String> list = ((ListSection) entry.getValue()).getContentList();
                        ps.setString(3, String.join("\n", list));
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteSections(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section c WHERE resume_uuid = ?")) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }

    private void addSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE resume_uuid = ?")) {
            ps.setString(1, r.getUuid());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                addSection(rs, r);
            }
        }
    }

    private void addSection(ResultSet rs, Resume r) throws SQLException {
        String type = rs.getString("type");
        String text = rs.getString("value");
        switch (SectionType.valueOf(type)) {
            case OBJECTIVE:
            case PERSONAL:
                r.setSection(SectionType.valueOf(type), new TextSection(text));
                break;
            case ACHIEVEMENT:
            case QUALIFICATION:
                r.setSection(SectionType.valueOf(type), new ListSection(Arrays.asList(text.split("\n"))));
                break;
        }
    }
}