<%--
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
            <a href="index.jsp" class="navbar-brand"> Evolve ECommerce  </a>
        </div>

        <ul class="navbar-nav ml-auto">
            <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="cart.jsp">Your Cart </a></li>
            <%
                if (auth != null) {
            %>
            <li class="nav-item"><a class="nav-link" href="adminlist.jsp">User List </a></li>
            <li class="nav-item"><a class="nav-link" href="orders.jsp">Your Orders</a></li>
            <li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
            <%
            } else {
            %>
            <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
            <%
                }
            %>
        </ul>
    </nav>
</header>

</body>
</html>
