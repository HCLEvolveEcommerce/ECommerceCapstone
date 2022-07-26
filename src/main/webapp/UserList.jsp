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
    User admin = (User) request.getSession().getAttribute("admin");
    if(admin!= null){
        request.setAttribute("admin", admin);
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
        <br>
        <br>
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-center">
            <a href="InsertUserForm.jsp" class="btn btn-success" id="adminButton">Add
                New User</a>
            <a href="ProductList.jsp" class="btn btn-success" id="adminButton2">Products Table</a>
            <a href="OrderList.jsp" class="btn btn-success" id="adminButton3">Order Table</a>
        </div>
        <br>

        <!--Search User -->
        <nav class="navbar bg-light text-center">
            <form action="select-user" class="container-fluid" method="get"> <!-- pass whatever value you wanna search by then request.getparamater it (note for future self -->
                <div class="input-group">
                    <span class="input-group-text" id="basic-addon1">@</span>
                    <input type="text" name="id" class="form-control" placeholder="email or id" value="search" aria-label="Username" aria-describedby="basic-addon1">
                </div>
            </form>
        </nav>

        <table class="table table-bordered" id="table" style="border-width: thick ">
            <thead style="border-width: thick;">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>User Type</th>
                <th>Address</th>
                <th>City</th>
                <th>Zip</th>
                <th>State</th>
                <th>Country</th>
                <th>Phone Number</th>
                <th>Actions</th>
            </tr>
            </thead>
            <%if (!users.isEmpty()){
                for(User u:users){%>
            <tbody>
            <tr>
                <td><%=u.getId()%></td>
                <td><%=u.getFirstname()%></td>
                <td><%=u.getLastname()%></td>
                <td><%=u.getEmail()%></td>
                <td><%=u.getPassword()%></td>
                <td><%=u.getUsertype()%></td>
                <td><%=u.getAddress()%></td>
                <td><%=u.getCity()%></td>
                <td><%=u.getZip()%></td>
                <td><%=u.getState()%></td>
                <td><%=u.getCountry()%></td>
                <td><%=u.getPhonenumber()%></td>
                    &nbsp;&nbsp;&nbsp;&nbsp;<td> <a href="delete-user?id=<%=u.getId()%>">Delete</a></td>
            </tr>
            </tbody>
            <%}
            }%>

        </table>

    </div>
    <%@include file="includes/BootScript.jsp" %>
</body>
</html>
