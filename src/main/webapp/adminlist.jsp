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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    UserDao ud = new UserDao(DbCon.getConnection());
    List<User> users = ud.getAllUsers();
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
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="insertform.jsp" class="btn btn-success">Add
                New User</a>
        </div>
        <br>
        <table class="table table-bordered" style="border-width: thick ">
            <thead style="border-width: thick;">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>User Type</th>
                <th>Actions</th>
            </tr>
            </thead>
            <%if (!users.isEmpty()){
                for(User u:users){%>
            <tbody>
            <tr>
                <td><%=u.getId()%></td>
                <td><%=u.getName()%></td>
                <td><%=u.getEmail()%></td>
                <td><%=u.getPassword()%></td>
                <td><%=u.getUsertype()%></td>
                <td><a href="edit-form?id=<%=u.getId()%>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete-user?id=<%=u.getId()%>">Delete</a></td>
            </tr>
            </tbody>
            <%}
            }%>

        </table>

    </div>
    <%@include file="includes/BootScript.jsp" %>
</body>
</html>
