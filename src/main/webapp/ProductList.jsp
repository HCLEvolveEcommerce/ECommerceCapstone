<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dsike
  Date: 7/10/2022
  Time: 10:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hcl.ecommerce.Model.User" %>
<%@ page import="com.hcl.ecommerce.Dao.UserDao" %>
<%@ page import="com.hcl.ecommerce.Model.DbCon" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hcl.ecommerce.Dao.ProductDao" %>
<%@ page import="com.hcl.ecommerce.Model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }
    User admin = (User) request.getSession().getAttribute("admin");
    if(admin!= null){
        request.setAttribute("admin", admin);
    }

    UserDao ud = new UserDao(DbCon.getConnection());
    List<User> users = ud.getAllUsers();

    ProductDao pd = new ProductDao(DbCon.getConnection());
    List<Product> products = pd.getAllProducts();
%>
<html>
<head>
    <title>Evolve</title>
    <%@include file="includes/header.jsp" %>  <!--BoostStrap CSS CDN -->
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <div class="container">
        <h3 class="text-center">List of Products</h3>
        <hr>
        <div class="container text-center">
            <a href="InsertProductForm.jsp" class="btn btn-success" >Add New Product</a>
            <a href="UserList.jsp" class="btn btn-success" >User Table</a>
        </div>
        <br>
        <table class="table table-bordered" style="border-width: thick ">
            <thead style="border-width: thick;">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Image Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <%if (!products.isEmpty()){
                for(Product p:products){%> <!-- should work -->
            <tbody>
            <tr>
                <td><%=p.getId()%></td>
                <td><%=p.getName()%></td>
                <td><%=p.getCategory()%></td>
                <td>$<%=p.getPrice()%></td>
                <td><%=p.getImage()%></td>
                <td><a href="editP-form?id=<%=p.getId()%>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete-product?id=<%=p.getId()%>">Delete</a></td> <!--should work -->
            </tr>
            </tbody>
            <%}
            }%>

        </table>

    </div>
    <%@include file="includes/BootScript.jsp" %>
</body>
</html>
