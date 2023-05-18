<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post">
  <fieldset>
    <div class="form-group">
      <label for="firstName">Imię</label>
      <input name="firstName" type="text" id="firstName"
             class="form-control" placeholder="Imie" value="${admin.firstName}"/>
    </div>
    <div class="form-group">
      <label for="lastName">Imię</label>
      <input name="lastName" type="text" id="lastName"
             class="form-control" placeholder="Nazwisko" value="${admin.lastName}"/>
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input name="email" type="email" id="email" class="form-control" placeholder="Adres email" value="${admin.email}"/>
    </div>
    <button type="submit" class="btn btn-primary">Zapisz</button>
  </fieldset>
</form>
