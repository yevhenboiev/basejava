package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static Resume fillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContacts(ContactsType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.setContacts(ContactsType.SKYPE, "grigory.kislin");
        resume.setContacts(ContactsType.MAIL, "gkislin@yandex.ru");
        resume.setContacts(ContactsType.LINKEDIN, "Профиль LinkedIn");
        resume.setContacts(ContactsType.GIT_HUB, "Профиль GitHub");
        resume.setContacts(ContactsType.STACKOVERFLOW, "Профиль StackOverflow");
        resume.setContacts(ContactsType.HOME_PAGE, "Домашняя страница");

        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям"));
        resume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achievement = new ArrayList<>();
        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"" +
                ", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                "Более 1000 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                "интеграция CIFS/SMB java сервера");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и " +
                "информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        ListSection achievementSection = new ListSection(achievement);
        resume.setSection(SectionType.ACHIEVEMENT, achievementSection);

        List<String> qualification = new ArrayList<>();
        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualification.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualification.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualification.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualification.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), " +
                "Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualification.add("Python: Django.");
        qualification.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualification.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualification.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, " +
                "CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualification.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualification.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualification.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualification.add("Родной русский, английский \"upper intermediate\"");
        ListSection qualificationSection = new ListSection(qualification);
        resume.setSection(SectionType.QUALIFICATION, qualificationSection);

        List<Organization> experienceOrganization = new ArrayList<>();
        Organization.Position positionWrike = new Organization.Position(LocalDate.parse("2010-10-01"), LocalDate.parse("2011-10-01"),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization.Position positionWrike1 = new Organization.Position(LocalDate.parse("2010-10-01"), LocalDate.parse("2011-10-01"), "Младший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization wrike = new Organization("Wrike", "url", positionWrike, positionWrike1);
        experienceOrganization.add(wrike);
        OrganizationSection experience = new OrganizationSection(experienceOrganization);
        resume.setSection(SectionType.EXPERIENCE, experience);

        List<Organization> educationOrganization = new ArrayList<>();
        Organization.Position positionCoursera = new Organization.Position(LocalDate.parse("2020-10-01"),
                LocalDate.parse("2020-11-01"),
                " \"Functional Programming Principles in Scala\" by Martin Odersky", null);
        Organization coursera = new Organization("Coursera", null, positionCoursera);
        educationOrganization.add(coursera);
        OrganizationSection education = new OrganizationSection(educationOrganization);
        resume.setSection(SectionType.EDUCATION, education);

        return resume;
    }
}

