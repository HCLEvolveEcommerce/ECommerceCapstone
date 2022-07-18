
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
<%@ page import="com.hcl.ecommerce.Model.Product" %>
<%@ page import="com.hcl.ecommerce.Dao.ProductDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    UserDao ud = new UserDao(DbCon.getConnection());
    List<User> users = ud.getAllUsers();

    User admin = (User) request.getSession().getAttribute("admin");
    if(admin!= null){
        request.setAttribute("admin", admin);
    }

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
            <form action="insert-product" method="post">
                <caption>
                    <h2>
                        Add New Product
                    </h2>
                </caption>
                <input type="hidden" name="id" />

                <fieldset class="form-group">
                    <label>Product Name</label> <input type="text"
                                                    class="form-control"
                                                    name="name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Category</label> <input type="text"
                                                     class="form-control"
                                                     name="category" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Price</label> <input type="text"
                                                   class="form-control"
                                                   name="price" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Image Name</label> <input type="file"
                                                class="form-control"
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
