<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hcl.ecommerce.Dao.UserDao" %>
<%@ page import="com.hcl.ecommerce.Dao.OrderDao" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Or" %>
<%@ page import="com.hcl.ecommerce.Model.*" %>
<%
  User auth = (User) request.getSession().getAttribute("auth");
  if(auth != null){
    request.setAttribute("auth", auth);
  }

  User admin = (User) request.getSession().getAttribute("admin");
  if(admin!= null){
    request.setAttribute("admin", admin);
  }

  DecimalFormat format = new DecimalFormat("$#0.00"); //used for formatting
  ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
  List<Cart> cartProduct = null;
  if(cart_list != null){
    ProductDao productDao = new ProductDao(DbCon.getConnection());
    cartProduct = productDao.ProductCart(cart_list);
    double subTotal = productDao.subTotalPrice(cart_list);
    request.setAttribute("cart_list", cart_list);
    request.setAttribute("subTotal", format.format(subTotal));
  }

  DecimalFormat format1 = new DecimalFormat("$#0.00"); //used for formatting
  if(cart_list != null){
    ProductDao productDao = new ProductDao(DbCon.getConnection());
    cartProduct = productDao.ProductCart(cart_list);
    double total = productDao.totalPrice(cart_list);
    request.setAttribute("cart_list", cart_list);
    request.setAttribute("total", format.format(total));
  }

  DecimalFormat format2 = new DecimalFormat("$#0.00"); //used for formatting
  if(cart_list != null){
    ProductDao productDao = new ProductDao(DbCon.getConnection());
    cartProduct = productDao.ProductCart(cart_list);
    double taxTotal = productDao.totalTax(cart_list);
    request.setAttribute("cart_list", cart_list);
    request.setAttribute("taxTotal", format.format(taxTotal));
  }

  UserDao ud = new UserDao(DbCon.getConnection());
  List<User> users = ud.getAllUsers();

  User user= new User();

  List<Order> orders  = new OrderDao(DbCon.getConnection()).clientOrders(auth.getId());


%>
<!DOCTYPE HTML>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="Type some info">
  <meta name="author" content="Type name">

  <title>Evolve</title>

  <!-- Bootstrap css -->
  <link href="css/bootstrap.css?v=2.0" rel="stylesheet" type="text/css" />

  <!-- Custom css -->
  <link href="css/ui.css?v=2.0" rel="stylesheet" type="text/css" />
  <link href="css/responsive.css?v=2.0" rel="stylesheet" type="text/css" />

  <!-- Font awesome 5 -->
  <link href="fonts/fontawesome/css/all.min.css" type="text/css" rel="stylesheet">

</head>
<body class="gradient-custom">
<%@include file="includes/navbar.jsp"%>


<!-- ============== SECTION CONTENT ============== -->
<section class="padding-y bg-light">
<div class="container">

<div class="row">
  <aside class="col-lg-3 col-xl-3">
    <!--  COMPONENT MENU LIST  -->
    <nav class="nav flex-lg-column nav-pills mb-4">
      <a class="nav-link active" href="#">Account main</a>
      <a class="nav-link" href="log-out">Log out</a>
    </nav>
    <!--   COMPONENT MENU LIST END .//   -->
  </aside>
  <main class="col-lg-9  col-xl-9">
    <article class="card">
    <div class="content-body">

    <figure class="itemside align-items-center">
      <div class="aside">
        <span class="bg-gray icon-md rounded-circle">
          <img src="product-image/(1).png" class="icon-md rounded-circle">
        </span>
      </div>
      <figcaption class="info">
        <h6 class="title"><%=auth.getFirstname()%> <%=auth.getLastname()%></h6>
        <p>Email: <%=auth.getEmail()%>
          <a href="editU-form?id=<%=auth.getId()%>" class="px-2"><i class="fa fa-pen"></i></a>
        </p>
      </figcaption>
    </figure>

    <hr>

    <div class="row g-2 mb-3"> 
      <div class="col-md-6">
        <article class="box bg-light">
          <b class="mx-2 text-muted"><i class="fa fa-map-marker-alt"></i></b>
          <%=auth.getAddress()%>, <%=auth.getCity()%>, <%=auth.getState()%> <!--<=auth.getAddress()> -->
        </article>
      </div> <!-- col.// -->
    </div> <!-- row.// -->

    <a href="editU-form?id=<%=auth.getId()%>" class="btn btn-light"> <i class="me-2 fa fa-plus"></i> Add new address </a>

    <hr class="my-4">

    <h5 class="card-title"> Your orders </h5> 
    <!--  ======== item order ======== -->
      <% if(orders != null){%>
        <%for (Order order:orders){%>
    <article class="card border-primary mb-4">
    <div class="card-body">
      <header class="d-lg-flex">
        <div class="flex-grow-1">
          <h6 class="mb-0"><i class="dot"></i>
            <span class="text-success"> <%=order.getOrderID()%> Shipped</span>
          </h6>
          <span class="text-muted"><%=order.getOrderDate()%> </span>
        </div>
        <div>
          <a href="remove-order?id=<%=order.getOrderID()%>" class="btn btn-sm btn-outline-danger">Cancel order</a>
<%--          <a href="#" class="btn btn-sm btn-primary">Track order</a> --%>
        </div>
      </header>
      <hr>
      <div class="row">
        <div class="col-lg-4">
          <p class="mb-0 text-muted">Contact</p>
          <p class="m-0">
            <%=auth.getFirstname()%><br>  Phone: <%=auth.getPhonenumber()%> <br> <%=auth.getEmail()%> </p>
        </div> <!-- col.// -->
        <div class="col-lg-4 border-start">
          <p class="mb-0 text-muted">Shipping address</p>
          <p class="m-0"><%=auth.getCountry()%> <br>
            <%=auth.getAddress()%>, <%=auth.getCity()%>, <%=auth.getState()%> </p>
        </div> <!-- col.// -->
        <div class="col-lg-4 border-start">
          <p class="mb-0 text-muted">Payment</p>
          <p class="m-0">
            <span class="text-success"> Visa  **** 4216   </span> <br> 
<%--            Shipping fee:  $56 <br> --%>
<%--            Total Paid:--%>
          </p>
        </div> <!-- col.// -->
      </div> <!-- row.// -->
      <hr>
      <ul class="row">
        <li class="col-xl-4  col-lg-6">
          <figure class="itemside mb-3">
            <div class="aside">
              <img width="72" height="72" class="img-sm rounded" src="product-image/<%= order.getImage()%>">
            </div>
            <figcaption class="info">
              <p class="title"><%=order.getName()%></p>
              <strong><%=order.getQuantity()%>x= <%=order.getPrice()%> </strong>
            </figcaption>
          </figure> 
        </li>
      </ul>
    </div> <!-- card-body .// -->
    </article>
  <%}%>
  <%}%>

    </div> <!-- card-body .// -->
    </article> <!-- card .// --> 
  </main>
</div> <!-- row.// -->

<br><br>


</div> <!-- container .//  -->
</section>



<!-- Bootstrap js -->
<script src="js/bootstrap.bundle.min.js"></script>

<!-- Custom js -->
<script src="js/script.js?v=2.0"></script>

</body>
</html>