<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <sec:authorize access="isAnonymous()">
                <li>
                    <a href="<c:url value="/login"/>" class="btn btn--small btn--without-border">
                        Zaloguj
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/register"/>" class="btn btn--small btn--highlighted">
                        Załóż konto
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li class="logged-user">
                    Witaj ${userName}
                    <ul class="dropdown">
                        <li><a href="<c:url value="/user/profile"/>">Profil</a></li>
                        <li><a href="#">Moje zbiórki</a></li>
                        <sec:authorize access="hasRole('ADMIN')">
                            <li><a href="<c:url value="/admin"/>">Panel administracyjny</a></li>
                        </sec:authorize>
                        <li><a href="<c:url value="/logout"/>">Wyloguj</a></li>
                    </ul>
                </li>
            </sec:authorize>
        </ul>

        <ul>
            <sec:authorize access="isAnonymous()">
                <li><a href="<c:url value="/"/>" class="btn btn--without-border active">Start</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a href="<c:url value="/user"/>" class="btn btn--without-border active">Start</a></li>
            </sec:authorize>
            <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#" class="btn btn--without-border">O nas</a></li>
            <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zacznij pomagać!<br/>
                Oddaj niechciane rzeczy w zaufane ręce
            </h1>
        </div>
    </div>
</header>
