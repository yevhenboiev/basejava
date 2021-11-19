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

<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactsType, java.lang.String>"/>
            <%=HtmlHelper.toHtmlContactType(contactEntry.getKey(), contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <hr>

    <p>
        <c:forEach var="sectionEntry" items="${resume.section}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType
                         , ru.javawebinar.basejava.model.AbstractSection>"/>
            <c:if test="<%=sectionEntry.getValue() != null%>">
                <%=HtmlHelper.toHtmlSectionType(sectionEntry.getKey(), sectionEntry.getValue())%><br/>
            </c:if>
    </c:forEach>
    </p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
