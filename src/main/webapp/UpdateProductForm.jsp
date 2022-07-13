<%--
  Created by IntelliJ IDEA.
  User: dsike
  Date: 7/12/2022
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hcl.ecommerce.Model.User" %>
<%@ page import="com.hcl.ecommerce.Dao.UserDao" %>
<%@ page import="com.hcl.ecommerce.Model.DbCon" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hcl.ecommerce.Model.Product" %>
<%@ page import="com.hcl.ecommerce.Dao.ProductDao" %>
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
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">
      <c:if test="${product != null}">
      <form action="update-product" method="get">
        </c:if>

        <caption>
          <h2>
            <c:if test="${product != null}">
              Edit Product
            </c:if>
          </h2>
        </caption>

        <c:if test="${product != null}">
          <input type="hidden" name="id" value="<c:out value='${product.id}' />" />
        </c:if>

        <fieldset class="form-group">
          <label>Product Name</label> <input type="text"
                                          value="<c:out value='${product.name}' />" class="form-control"
                                          name="name" required="required">
        </fieldset>

        <fieldset class="form-group">
          <label>Category</label> <input type="text"
                                           value="<c:out value='${product.category}' />" class="form-control"
                                           name="category" required="required">
        </fieldset>

        <fieldset class="form-group">
          <label>Price</label> <input type="text"
                                         value="<c:out value='${product.price}' />" class="form-control"
                                         name="price" required="required">
        </fieldset>

        <fieldset class="form-group">
          <label>Image Name</label> <input type="text" minlength="5"
                                         value="<c:out value='${product.image}' />" class="form-control"
                                         name="image" required="required">
        </fieldset>

        <button type="submit" class="btn btn-success">Save</button>
      </form>
    </div>
  </div>
</div>
<%@include file="includes/BootScript.jsp" %>
</body>
</html>
