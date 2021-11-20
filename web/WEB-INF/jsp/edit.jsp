<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
<div>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h3><label for="fullName" id="fullName1">Full Name</label></h3>
        <input type="text" id="fullName"
               name="fullName" size=50 pattern="[А-Яа-яa-zA-Z0-9\s]{2,}"
               placeholder="Send you Full Name"
               value="${resume.fullName}" required>
        <p>Contacts</p>
        <dl>
        <c:forEach var="type" items="<%=ContactsType.values()%>">
            <dd><label for="contactType">
                <input type="text" id="contactType"
                       name="${type.name()}" size=30
                       placeholder="${type.title}"
                       value="${resume.getContacts(type)}"></label></dd>
            <br/>
        </c:forEach>
        </dl>
        <p>Section</p>
        <dl>
            <c:forEach var="type" items="<%=SectionType.values()%>">
                <c:choose>
                    <c:when test="${type == SectionType.OBJECTIVE || type == SectionType.PERSONAL}">
                        <dt><label for="${type}">${type.title}</label></dt>
                        <dd><input type="text" id="${type}" name="${type.name()}" size="150"
                                   value="${resume.getSection(type)}"></dd>
                        <br/>
                    </c:when>
                    <c:when test="${type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATION}">
                        <dt><label for="${type}">${type.title}</label></dt>
                        <dd><textarea id="${type}"
                                      name="${type.name()}" cols="150"
                                      rows="8">${HtmlHelper.toHtmlSectionType(type, resume.getSection(type), true)}</textarea>
                        </dd>
                        <br/>
                    </c:when>
                </c:choose>
            </c:forEach>
        </dl>
        <button type="submit">Save</button>
        <button type="reset">Cancel</button>
    </form>
</div>
</body>
</html>