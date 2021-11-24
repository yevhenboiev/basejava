package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "save":
                resume = new Resume();
                storage.save(resume);
                break;
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "WEB-INF/jsp/view.jsp" : "WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume = storage.get(uuid);
        saveContact(request, resume);
        saveSection(request, resume);
        if (fullName.equals("")) {
            request.setAttribute("resume", resume);
            request.getRequestDispatcher("WEB-INF/jsp/edit.jsp").forward(request, response);
        }
        resume.setFullName(fullName);
        storage.update(resume);
        response.sendRedirect("resume");
    }

    private void saveContact(HttpServletRequest request, Resume resume) {
        for (ContactsType type : ContactsType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.setContacts(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }
    }

    private void saveSection(HttpServletRequest request, Resume resume) {
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name()) == null ?
                    request.getParameter(type.name() + "orgName") : request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.setSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        resume.setSection(type, new ListSection(Arrays.asList(value.split("\n"))));
                        break;
                    case EXPERIENCE:
                        OrganizationSection organizationSection = setOrganizationSection(request, type);
                        resume.setSection(type, organizationSection);
                        break;
                }
            } else {
                resume.getSection().remove(type);
            }
        }
    }

    private OrganizationSection setOrganizationSection(HttpServletRequest request, SectionType type) {
        List<Organization> organizationList = new ArrayList<>();
        String[] nameOrganization = request.getParameterValues(type.name() + "orgName");
        for (int i = 0; i < nameOrganization.length; i++) {
            if (isPresent(nameOrganization[i])) {
                List<Organization.Position> positionList = setPosition(
                        request.getParameterValues(type.name() + i + "startDate")
                        , request.getParameterValues(type.name() + i + "endDate")
                        , request.getParameterValues(type.name() + i + "position")
                        , request.getParameterValues(type.name() + i + "description"));
                organizationList.add(new Organization(new Link(nameOrganization[i], null), positionList));
            }
        }
        return organizationList.size() == 0 ? null : new OrganizationSection(organizationList);
    }

    private static List<Organization.Position> setPosition(String[] startDate, String[] endDate, String[] title, String[] description) {
        List<Organization.Position> positionList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            if (isPresent(startDate[i]) && isPresent(title[i])) {
                positionList.add(new Organization.Position(checkDate(startDate[i]), checkDate(endDate[i]), title[i], description[i]));
            }
        }
        return positionList.isEmpty() ? null : positionList;
    }

    private static LocalDate checkDate(String line) {
        return line.isEmpty() ? null : LocalDate.parse(line);
    }

    private static boolean isPresent(String line) {
        return line != null && !line.isEmpty();
    }
}
