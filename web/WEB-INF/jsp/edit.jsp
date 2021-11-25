<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlHelper" %>
<%@ page import="java.time.LocalDate" %>
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
               name="fullName" size=50 pattern="[А-Яа-яa-zA-Z\s]{2,}"
               placeholder="Send you Full Name"
               value="${resume.fullName}" required>
        <p>
        <h3>Contacts</h3></p>

        <c:forEach var="type" items="<%=ContactsType.values()%>">
            <dl>
                <dd><label for="contactType">
                    <input type="text" id="contactType"
                           name="${type.name()}" size=30
                           placeholder="${type.title}"
                           value="${resume.getContacts(type)}"></label></dd>

            </dl>
        </c:forEach>
        <h3>Section</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:choose>
                <c:when test="${type == SectionType.OBJECTIVE || type == SectionType.PERSONAL}">
                    <h3>${type.title}</h3>
                    <p><label><input type="text" name="${type.name()}" size="150"
                                     value="${resume.getSection(type)}">
                    </label></p>
                    <br/>
                </c:when>
                <c:when test="${type == SectionType.ACHIEVEMENT || type == SectionType.QUALIFICATION}">
                    <h3>${type.title}</h3>
                    <p><textarea name="${type.name()}" cols="150" rows="8">${HtmlHelper.toHtmlSectionType(type, resume.getSection(type), true)}</textarea></p>
                </c:when>
                <c:when test="${type == SectionType.EDUCATION || type == SectionType.EXPERIENCE}">
                    <h3>${type.title}</h3>
                    <c:set var="type.name()" value="${type.name()}"/>
                    <c:set var="orgCounter" value="${0}"/>
                    <c:forEach var="organization" items="${resume.getSection(type).organizations}">
                        <p><label>Name of an organization:<input type="text"
                                                                 name="${type.name()}orgName" size="150"
                                                                 value="${organization.homePage.name}"></label></p>
                        <c:forEach var="position" items="${organization.positions}">
                            <p><label>Start date:
                                <input type="date" name="${type.name()}${orgCounter}startDate"
                                       value="${position.startDate}"></label></p>
                                <p><label>End date:
                                    <input type="date" name="${type.name()}${orgCounter}endDate"
                                           value="${position.endDate}"></label></p>
                            <p><label>Name of an Position: <textarea name="${type.name()}${orgCounter}position"
                                                                     cols="150"
                                                                     rows="1">${position.title}</textarea></label></p>
                            <c:if test="${type != SectionType.EDUCATION}">
                                <p><label>Description of an Position:
                                    <textarea name="${type.name()}${orgCounter}description" cols="150"
                                              rows="8">${position.description}</textarea></label></p>
                            </c:if>
                        </c:forEach>
                        <h3>Add a new Position:</h3>
                        <p><label>Start date</label></p>
                            <input type="date" name="${type.name()}${orgCounter}startDate">
                        <p><label>End date</label></p>
                                <input type="date" name="${type.name()}${orgCounter}endDate">
                        <p><label>Name of an Position:
                            <textarea name="${type.name()}${orgCounter}position" cols="150" rows="1"></textarea>
                        </label></p>
                        <c:if test="${type != SectionType.EDUCATION}">
                            <p><label> Description of an Position:
                                <textarea name="${type.name()}${orgCounter}description" cols="150" rows="8"></textarea>
                            </label></p>
                        </c:if>
                        <c:set var="orgCounter" value="${orgCounter + 1}"/>
                    </c:forEach>
                    <h3>Add a new Organization</h3>
                    <p><label>Name of an organization:<input type="text" name="${type.name()}orgName" size="150">
                    </label></p>
                    <p><label>Start date:
                        <input type="date" name="${type.name()}${orgCounter}startDate"></label>
                        <label>End date:
                            <input type="date" name="${type.name()}${orgCounter}endDate"></label></p>
                    <p><label>Name of an Position: <textarea name="${type.name()}${orgCounter}position" cols="150"
                                                             rows="1"></textarea></label></p>
                    <c:if test="${type != SectionType.EDUCATION}">
                        <p><label>Description of an Position:
                            <textarea name="${type.name()}${orgCounter}description" cols="150"
                                      rows="8"></textarea></label></p>
                    </c:if>
                </c:when>
            </c:choose>
        </c:forEach>
        <button type="submit">Save</button>
        <button type="reset">Cancel</button>
    </form>
</div>
</body>
</html>