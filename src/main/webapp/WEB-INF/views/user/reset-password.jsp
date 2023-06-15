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
  <h2>Podaj email, na który ma zostać wysłany link resetujący hasło</h2>
  <h3>${errorMessage}</h3>
  <h3>${successMessage}</h3>
  <form method="post" action="/reset-password">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="form-group">
      <input type="email" name="email" placeholder="Email" />
    </div>
    <div class="form-group form-group--buttons">
      <a href="/register" class="btn btn--without-border">Załóż konto</a>
      <button class="btn" type="submit">Wyślij link</button>
    </div>
  </form>
</section>
<%@ include file="../commons/footer.jsp"%>
</body>
</html>
