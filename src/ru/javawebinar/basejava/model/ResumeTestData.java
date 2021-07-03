package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "Григорий Кислин");
        String phone_number = "+7(921) 855-0482";
        r1.setContacts(ContactsType.PHONE_NUMBER, phone_number);
        String skype = "grigory.kislin";
        r1.setContacts(ContactsType.SKYPE, skype);
        String mail = "gkislin@yandex.ru";
        r1.setContacts(ContactsType.MAIL, mail);
        String linkedIn = "Профиль LinkedIn";
        r1.setContacts(ContactsType.LINKEDIN, linkedIn);
        String gitHub = "Профиль GitHub";
        r1.setContacts(ContactsType.GIT_HUB, gitHub);
        String stackoverflow = "Профиль StackOverflow";
        r1.setContacts(ContactsType.STACKOVERFLOW, stackoverflow);
        String homePage = "Домашняя страница";
        r1.setContacts(ContactsType.HOME_PAGE, homePage);

        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения " +
                "по Java Web и Enterprise технологиям");
        r1.setSection(SectionType.OBJECTIVE, objective);

        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры.");
        r1.setSection(SectionType.PERSONAL, personal);

        List<String> achievement = new ArrayList<>();
        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"" +
                ", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                "Более 1000 выпускников.");
        achievement.add("\nРеализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("\nНалаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                "интеграция CIFS/SMB java сервера");
        achievement.add("\nРеализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievement.add("\nСоздание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и " +
                "информации о состоянии через систему мониторинга Nagios. " +
                "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.add("\nРеализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        ListSection achievementSection = new ListSection(achievement);
        r1.setSection(SectionType.ACHIEVEMENT, achievementSection);

        List<String> qualification = new ArrayList<>();
        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.add("\nVersion control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.add("\nDB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualification.add("\nMySQL, SQLite, MS SQL, HSQLDB");
        qualification.add("\nLanguages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualification.add("\nXML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualification.add("\nJava Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring " +
                "(MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), " +
                "Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualification.add("\nPython: Django.");
        qualification.add("\nJavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualification.add("\nScala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualification.add("\nТехнологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, " +
                "CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualification.add("\nИнструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualification.add("\nадминистрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualification.add("\nОтличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualification.add("\nРодной русский, английский \"upper intermediate\"");
        ListSection qualificationSection = new ListSection(qualification);
        r1.setSection(SectionType.QUALIFICATION, qualificationSection);

        System.out.println(r1.getFullName());
        System.out.println(SectionType.OBJECTIVE.getTitle());
        System.out.println(r1.getSection(SectionType.OBJECTIVE));
        System.out.println(SectionType.PERSONAL.getTitle());
        System.out.println(r1.getSection(SectionType.PERSONAL));
        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        System.out.println(r1.getSection(SectionType.ACHIEVEMENT));
        System.out.println(SectionType.QUALIFICATION.getTitle());
        System.out.println(r1.getSection(SectionType.QUALIFICATION));


    }
}
