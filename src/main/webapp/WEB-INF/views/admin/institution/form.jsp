<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post">
  <fieldset>
    <div class="form-group">
      <label for="institutionName">Nazwa</label>
      <input name="name" type="text" id="institutionName"
             class="form-control" placeholder="Nazwa fundacji" value="${institution.name}">
    </div>
    <div class="form-group">
      <label for="institutionDescription">Opis</label>
      <textarea name="description" type="text" id="institutionDescription"
                class="form-control" placeholder="Opis fundacji" value="${institiution.description}">
      </textarea>
    </div>
    <button type="submit" class="btn btn-primary">Zapisz</button>
  </fieldset>
</form>
