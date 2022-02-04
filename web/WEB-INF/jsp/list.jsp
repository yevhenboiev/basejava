<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resumes</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@600&family=Roboto:wght@400;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="css/list.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="main-container">
    <div class="create-resume">
        <img src="img/add_resume.png">
        <a href="resume?uuid=${resume.uuid}&action=save">
            Добавить <br>
            резюме
        </a>
    </div>
    <div class="table-resumes">
        <table>
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">E-mail, phone</th>
                <th scope="col">Edit</th>
                <th scope="col">Remove</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${resumes}" var="resume">
                <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume"/>
                <tr>
                    <td><a href="resume?uuid=${resume.uuid}&action=view" style="color:#4a586e; text-decoration:none">${resume.fullName}</a></td>
                    <td><%=resume.getContacts(ContactsType.MAIL) == null ? "" : resume.getContacts(ContactsType.MAIL)%><br>
                        <%=resume.getContacts(ContactsType.PHONE_NUMBER) == null ? "" : resume.getContacts(ContactsType.PHONE_NUMBER)%>
                    </td>
                    <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png"></a></td>
                    <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/remove.png"></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>