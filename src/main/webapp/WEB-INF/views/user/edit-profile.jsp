<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
</head>
<body>
<%@ include file="../commons/header.jsp"%>
<section class="login-page">
    <h2>Edytuj dane</h2>
    <form:form method="post" modelAttribute="user">
        <form:errors path="firstName"/>
        <div class="form-group">
            <form:input type="text" path="firstName" placeholder="Imię" />
        </div>
        <form:errors path="lastName"/>
        <div class="form-group">
            <form:input type="text" path="lastName" placeholder="Nazwisko" />
        </div>
        <form:errors path="email"/>
        <div class="form-group">
            <form:input type="email" path="email" placeholder="Email" />
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zapisz</button>
        </div>
    </form:form>
</section>
<%@ include file="../commons/footer.jsp"%>
</body>
</html>
