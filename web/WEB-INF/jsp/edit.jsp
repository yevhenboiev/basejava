<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlHelper" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@600&family=Roboto:wght@400;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="css/edit.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="main-container">
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <!--Full Name Section-->
        <div class="full_name">
            <input type="hidden" name="uuid" value="${resume.uuid}">
            <h3><label for="fullName" id="fullName1">Full Name</label></h3>
            <input type="text" id="fullName" name="fullName" size=50 pattern="[А-Яа-яa-zA-Z\s]{2,}"
                   placeholder="Send you Full Name"
                   value="${resume.fullName}" required>
        </div>

        <div class="contact_section">
            <h3>Contacts</h3>
            <c:forEach var="type" items="<%=ContactsType.values()%>">
                <input type="text" id="contactType" name="${type.name()}" size=30 placeholder="${type.title}"
                       value="${resume.getContacts(type)}">
            </c:forEach>
        </div>

        <!--Section Type-->
        <div class="section_type">
            <h3>Section</h3><c:forEach var="type" items="<%=SectionType.values()%>"><c:choose><c:when
                test="${type != SectionType.EDUCATION && type != SectionType.EXPERIENCE}">
            <!--PERSONAL OBJECTIVE ACHIEVEMENT QUALIFICATION-->
            <div class="personal">
                <h4>${type.title}</h4>
                <textarea
                        name="${type.name()}">${HtmlHelper.toHtmlSectionType(type, resume.getSection(type), true)}</textarea>
            </div>
        </c:when><c:when test="${type == SectionType.EDUCATION || type == SectionType.EXPERIENCE}">
            <!--EDUCATION and EXPERIENCE-->
            <div class="education">
                <h4>${type.title}</h4>
                <c:set var="type.name()" value="${type.name()}"/>
                <c:set var="orgCounter" value="${0}"/>
                <c:forEach var="organization" items="${resume.getSection(type).organizations}">
                    <input type="text" name="${type.name()}orgName" size="150"
                           placeholder="Name" value="${organization.homePage.name}">
                    <input type="text" name="${type.name()}link" size="150"
                           placeholder="Link" value="${organization.homePage.url}">
                    <c:forEach var="position" items="${organization.positions}">
                        <div class="date_section">
                            <input placeholder="Start date: MM YYYY" class="textbox-n" type="text"
                                   onfocus="(this.type='date')"
                                   onblur="(this.value == '' ? this.type='text' : this.type='date')"
                                   name="${type.name()}${orgCounter}startDate"
                                   value="${position.startDate}">
                            <input placeholder="End date: MM YYYY" class="textbox-n" type="text"
                                   onfocus="(this.type='date')"
                                   onblur="(this.value == '' ? this.type='text' : this.type='date')"
                                   name="${type.name()}${orgCounter}endDate" value="${position.endDate}">
                        </div>
                        <input type="text" name="${type.name()}${orgCounter}position" cols="150"
                               placeholder="Title" value="${position.title}">
                        <c:if test="${type != SectionType.EDUCATION}">
                                        <textarea placeholder="Description"
                                                  name="${type.name()}${orgCounter}description">${position.description}</textarea>
                        </c:if>
                    </c:forEach>
                    <button class="add_new_organization" type="button">Добавить позицию</button>
                    <div class="date_section">
                        <input placeholder="Start date: MM YYYY" class="textbox-n" type="text"
                               onfocus="(this.type='date')"
                               onblur="(this.value == '' ? this.type='text' : this.type='date')"
                               name="${type.name()}${orgCounter}startDate">
                        <input placeholder="End date: MM YYYY" class="textbox-n" type="text"
                               onfocus="(this.type='date')"
                               onblur="(this.value == '' ? this.type='text' : this.type='date')"
                               name="${type.name()}${orgCounter}endDate">
                    </div>
                    <input type="text" name="${type.name()}${orgCounter}position" cols="150"
                           placeholder="Title">
                    <c:if test="${type != SectionType.EDUCATION}">
                                <textarea placeholder="Description"
                                          name="${type.name()}${orgCounter}description"></textarea>
                    </c:if>
                    <c:set var="orgCounter" value="${orgCounter + 1}"/>
                </c:forEach>
                <button class="add_new_organization" type="button">Добавить</button>
                <input type="text" name="${type.name()}orgName" size="150" placeholder="Name">
                <input type="text" name="${type.name()}link" size="150" placeholder="Link">
                <button class="add_new_organization" type="button">Добавить позицию</button>
                <div class="date_section">
                    <input placeholder="Start date: MM YYYY" class="textbox-n" type="text"
                           onfocus="(this.type='date')"
                           onblur="(this.value == '' ? this.type='text' : this.type='date')"
                           name="${type.name()}${orgCounter}startDate">
                    <input placeholder="End date: MM YYYY" class="textbox-n" type="text"
                           onfocus="(this.type='date')"
                           onblur="(this.value == '' ? this.type='text' : this.type='date')"
                           name="${type.name()}${orgCounter}endDate">
                </div>
                <input type="text" name="${type.name()}${orgCounter}position" cols="150"
                       placeholder="Title">
                <c:if test="${type != SectionType.EDUCATION}">
                                <textarea placeholder="Description"
                                          name="${type.name()}${orgCounter}description"></textarea>
                </c:if>
            </div>
        </c:when>
        </c:choose>
        </c:forEach>
        </div>
        <div class="button">
            <button class="button_cancel" type="reset">Cancel</button>
            <button class="button_save" type="submit">Save</button>
        </div>
    </form>
</div>
</body>
</html>