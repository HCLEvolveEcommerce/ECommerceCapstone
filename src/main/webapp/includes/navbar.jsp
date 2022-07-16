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
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: black">
        <div>
            <a href="index.jsp" class="navbar-brand px-4">  Evolve ECommerce  </a>
        </div>

        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="ShoppingCart.jsp">Cart <span class="badge badge-dark px-2">${cart_list.size()}</span></a></li>
            <%if (auth != null) {%>
                <%if (admin != null) {%>
                     <li class="nav-item"><a class="nav-link" href="UserList.jsp">User List </a></li>
                <%}%>
            <li class="nav-item"><a class="nav-link" href="AccountPage.jsp">Account</a></li>
            <li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
            <%
            } else {
            %>
            <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
            <li class="nav-item"><a class="nav-link" href="signup.jsp">Signup</a></li>
            <%
                }
            %>
        </ul>
    </nav>
</header>

</body>
</html>
