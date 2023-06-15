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
    <h2>Wpisz nowe hasło</h2>
    <form method="post">
        <div class="form-group">
            <input type="password" name="newPassword" placeholder="Nowe hasło"/>
        </div>
        <div class="form-group">
            <input type="password" name="rePassword" placeholder="Potwierdź hasło"/>
        </div>
        <h3>${errorMessage}</h3>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zatwierdź</button>
        </div>
    </form>
</section>
<%@ include file="../commons/footer.jsp"%>
</body>
</html>
