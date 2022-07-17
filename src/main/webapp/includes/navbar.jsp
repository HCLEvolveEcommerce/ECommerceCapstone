<%@ page import="java.util.Objects" %>
<%@ page import="com.hcl.ecommerce.Model.Product" %>
<%@ page import="com.hcl.ecommerce.Dao.ProductDao" %>
<%@ page import="com.hcl.ecommerce.Model.Cart" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: dsike
  Date: 7/9/2022
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap css -->
    <link href="css/bootstrap.css?v=2.0" rel="stylesheet" type="text/css" />

    <!-- Custom css -->
    <link href="css/ui.css?v=2.0" rel="stylesheet" type="text/css" />
    <link href="css/responsive.css?v=2.0" rel="stylesheet" type="text/css" />

    <!-- Font awesome 5 -->
    <link href="fonts/fontawesome/css/all.min.css" type="text/css" rel="stylesheet">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark"
         style="background-color: black">
        <a href="index.jsp" class="navbar-brand">
            <img height="70" width="70" src="product-image/Evolve%20(1).png">
            Evolve Ecommerce
        </a>

        <ul class="navbar-nav ms-lg-auto">
            <a class="nav-link" href="index.jsp"><i class="fas fa-home"></i><span class="ms-1 d-none d-sm-inline-block">Home</span></a>
            <%--            <a class="nav-link" href="stripe-checkout"><i class="fas fa-home"></i><span class="ms-1 d-none d-sm-inline-block">Stripe</span></a>--%>
            <a class="nav-link" href="ShoppingCart.jsp"><i class="fa fa-shopping-cart"></i><span class="ms-1 d-none d-sm-inline-block">Cart </span><span class="badge badge px-2">${cart_list.size()}</span></a>
            <%if (auth != null) {%>
            <%if (admin != null) {%>
            <a class="nav-link" href="UserList.jsp"><i class="fa fa-user"></i><span class="ms-1 d-none d-sm-inline-block">User List </span></a>
            <%}%>
            <a class="nav-link" href="AccountPage.jsp"><i class="fas fa-user-circle"></i><span class="ms-1 d-none d-sm-inline-block">Account </span></a>
            <a class="nav-link" href="log-out"><i class="fas fa-user-alt-slash"></i><span class="ms-1 d-none d-sm-inline-block">Logout </span></a>
            <%
            } else {
            %>
            <a class="nav-link" href="login.jsp"><i class="fas fa-user-check"></i><span class="ms-1 d-none d-sm-inline-block">Sign in  </span></a>
            <a class="nav-link" href="signup.jsp"><i class="fas fa-user-plus"></i><span class="ms-1 d-none d-sm-inline-block">Signup </span></a>
            <%
                }
            %>
        </ul>
    </nav>
</header>

</body>
</html>

