<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />"/>
</head>
<body>
<%@ include file="../commons/header.jsp"%>

<section class="login-page">
    <h2>Profil</h2>
    <div>
        <div class="form-group">
            <h3>Imię: ${user.firstName}</h3>
        </div>
        <div class="form-group">
            <h3>Nazwisko: ${user.lastName}</h3>
        </div>
        <div class="form-group">
           <h3>Email: ${user.email}</h3>
        </div>
        <div class="form-group form-group--buttons">
            <a href="<c:url value="/user/profile/edit"/>" class="btn btn--without-border">Edytuj dane</a>
            <a class="btn" href="<c:url value="/user/password/change"/>">Zmień hasło</a>
        </div>
    </div>
</section>
<%@ include file="../commons/footer.jsp"%>
</body>
</html>
