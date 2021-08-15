package sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.storage.SqlStorage;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ExceptionConvert extends RuntimeException {
    private static final Logger LOG = Logger.getLogger(SqlStorage.class.getName());

    public static StorageException convert(SQLException e) {
        LOG.info(e.getSQLState());
        if (e.getSQLState().equals("23505")) {
            return new ExistStorageException(null);
        } else if(e.getSQLState().equals("22023")) {
            return new NotExistStorageException(null);
        }
        return new StorageException(e);
    }
}
