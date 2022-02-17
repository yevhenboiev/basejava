<%@ page import="ru.javawebinar.basejava.util.HtmlHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Resume ${resume.fullName}</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;600&family=Roboto:wght@400;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="css/view.css">
    <link rel="stylesheet" href="css/header.css">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="main-container">
    <h2>${resume.fullName}&nbsp;</h2>
    <div class="contact-section">
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry" type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactsType, java.lang.String>"/>
            <span><%=HtmlHelper.toHtmlContactType(contactEntry.getKey(), contactEntry.getValue())%></span><br/>
        </c:forEach>
    </div>
    <div class="section-type">
        <c:forEach var="sectionEntry" items="${resume.section}">
            <jsp:useBean id="sectionEntry" type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType
                                            , ru.javawebinar.basejava.model.AbstractSection>"/>
            <c:if test="<%=sectionEntry.getValue() != null%>">
            <div class="section">
                <h3>${sectionEntry.key.title}</h3>
                <%=HtmlHelper.toHtmlSectionType(sectionEntry.getKey(), sectionEntry.getValue(), false)%>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
</body>
</html>
