
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <c:if test="${user != null}">
      <form action="update-user" method="get">
        </c:if>

        <caption>
            <h2>
              <c:if test="${user != null}">
                Update Info
              </c:if>
            </h2>
          </caption>

          <c:if test="${user != null}">
            <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
          </c:if>

          <fieldset class="form-group">
            <label>First Name</label> <input type="text"
                                            value="<c:out value='${user.firstname}' />" class="form-control"
                                            name="firstname" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Last Name</label> <input type="text" minlength="5"
                                           value="<c:out value='${user.lastname}' />" class="form-control"
                                           name="lastname" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Email</label> <input type="email"
                                             value="<c:out value='${user.email}' />" class="form-control"
                                             name="email" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Password</label> <input type="password"
                                             value="<c:out value='${user.password}' />" class="form-control"
                                             name="password" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Street Address</label> <input type="text"
                                             value="<c:out value='${user.address}' />" class="form-control"
                                             name="address" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>City</label> <input type="text"
                                             value="<c:out value='${user.city}' />" class="form-control"
                                             name="city" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Zip Code</label> <input type="number"
                                             value="<c:out value='${user.zip}' />" class="form-control"
                                             name="zip" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>State </label> <input type="text"
                                             value="<c:out value='${user.state}' />" class="form-control"
                                             name="state" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Country</label> <input type="text" minlength="5"
                                           value="<c:out value='${user.country}' />" class="form-control"
                                           name="country" required="required">
          </fieldset>

          <fieldset class="form-group">
            <label>Phone Number</label> <input type="text" minlength="5"
                                           value="<c:out value='${user.phonenumber}' />" class="form-control"
                                           name="phonenumber" required="required">
          </fieldset>
          <fieldset class="form-group">
            <input type="text" minlength="5"
                                           value="Client"
                                           name="usertype" required="required" aria-disabled="true" aria-hidden="true" class="btn btn-secondary disabled">
          </fieldset>

          <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
  </div>
</div>
<%@include file="includes/BootScript.jsp" %>
</body>
</html>
