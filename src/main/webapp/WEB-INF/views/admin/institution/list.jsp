<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>charity-donation admin</title>

  <!-- Custom fonts for this template-->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
  <!-- Custom styles for this template-->
  <link href="<c:url value="/resources/css/sb-admin-2.min.css"/>" rel="stylesheet">

</head>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

  <%@ include file="../commons/sidebar.jsp"%>

  <!-- Content Wrapper -->
  <div id="content-wrapper" class="d-flex flex-column">

    <!-- Main Content -->
    <div id="content">

      <%@ include file="../commons/topbar.jsp"%>

      <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          <a href="<c:url value="/admin/institution/add"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-duotone fa-plus text-white-50"></i> Dodaj fundację</a>
        </div>

        <div class="card shadow mb-4">
          <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Lista fundacji</h6>
          </div>
          <div class="card-body">
            <ul class="list-group list-group-flush">
              <li class="list-group-item">
                <div class="row">
                  <div class="col-1">
                    <strong>id</strong>
                  </div>
                  <div class="col-4">
                    <strong>Nazwa</strong>
                  </div>
                  <div class="col-5">
                    <strong>Opis</strong>
                  </div>
                  <div class="col-2">
                    <strong>Akcja</strong>
                  </div>
                </div>
              </li>
              <c:forEach items="${institutions}" var="institution">
                <li class="list-group-item">
                  <div class="row">
                    <div class="col-1">
                        ${institution.id}
                    </div>
                    <div class="col-4">
                        ${institution.name}
                    </div>
                    <div class="col-5">
                        ${institution.description}
                    </div>
                    <div class="col-2">
                      <a class="delete" href="<c:url value="/admin/institution/${institution.id}/delete"/>" data-toggle="modal" data-target="#staticBackdrop">Usuń </a>
                      <a href="<c:url value="/admin/institution/${institution.id}/edit"/>">Edytuj </a>
                    </div>
                  </div>
                </li>
              </c:forEach>
            </ul>
          </div>
        </div>

      </div>
      <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->

    <%@ include file="../commons/footer.jsp"%>

  </div>
  <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>

<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Jesteś pewien?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Czy na pewno chcesz usunąć fundację?
      </div>
      <div id="modalFooter" class="modal-footer">
        <a href="" type="button" class="confirm-delete btn btn-primary">Tak</a>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Nie</button>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value="/resources/js/sb-admin-2.min.js"/>"></script>

<%-- Custom script for bootstrap modal service --%>
<script src="<c:url value="/resources/js/confirm-delete.js"/>"></script>

</body>
</html>