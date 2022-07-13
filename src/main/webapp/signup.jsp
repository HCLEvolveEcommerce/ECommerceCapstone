<%@ page import="com.hcl.ecommerce.Model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
        request.setAttribute("cart_list", cart_list );
    }
    User admin = (User) request.getSession().getAttribute("admin");
    if(admin!= null){
        request.setAttribute("admin", admin);
    }
%>
<html>
<head>
    <title>Your Orders</title>
    <%@include file="includes/header.jsp"%>  <!--BoostStrap CSS CDN -->
</head>
<%@include file="includes/navbar.jsp"%>
<body style="background-color: #90d5ec;
background-image: linear-gradient(315deg, #90d5ec 0%, #fc575e 74%);">
    <div class="container col-md-5">
    <div class="card">
        <div class="card-body">
                <form action="signup-user" method="post">
                    <caption>
                        <h2>
                                Sign Up
                        </h2>
                    </caption>
                        <input type="hidden" name="id" />

                    <fieldset class="form-group">
                        <label>User Name</label> <input type="text"
                                                        class="form-control"
                                                        name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Email</label> <input type="email"
                                                         class="form-control"
                                                         name="email" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label> <input type="text" minlength="5"
                                                       class="form-control"
                                                       name="password" required="required">
                    </fieldset>

                        <fieldset class="form-group">
                            <input type="hidden" value="Client" class="form-control"
                                                           name="usertype" required="required">
                        </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>

</body>
</html>