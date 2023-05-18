<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

  <!-- Sidebar - Brand -->
  <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<c:url value="/admin"/>">
    <div class="sidebar-brand-text mx-3">charity donation</div>
  </a>

  <!-- Divider -->
  <hr class="sidebar-divider my-0">

  <!-- Nav Item - Institutions -->
  <li class="nav-item">
    <a class="nav-link" href="<c:url value="/admin/institution/list"/>">
      <i class="fas fa-university"></i>
      <span>Fundacje</span></a>
  </li>

  <!-- Divider -->
  <hr class="sidebar-divider">

  <!-- Nav Item - Users -->
  <li class="nav-item">
    <a class="nav-link" href="<c:url value="/admin/user/list"/>">
      <i class="fas fa-user"></i>
      <span>Użytkownicy</span></a>
  </li>

  <!-- Divider -->
  <hr class="sidebar-divider">

  <!-- Nav Item - Admins -->
  <li class="nav-item">
    <a class="nav-link" href="<c:url value="/admin/administrator/list"/>">
      <i class="fas fa-user-tie"></i>
      <span>Administratorzy</span></a>
  </li>

  <!-- Divider -->
  <hr class="sidebar-divider">


  <!-- Sidebar Toggler (Sidebar) -->
  <div class="text-center d-none d-md-inline">
    <button class="rounded-circle border-0" id="sidebarToggle"></button>
  </div>

</ul>
<!-- End of Sidebar -->
