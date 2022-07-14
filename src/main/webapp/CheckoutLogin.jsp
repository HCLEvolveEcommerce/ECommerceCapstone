
<%@ page import="com.hcl.ecommerce.Model.User" %>
<%@ page import="com.hcl.ecommerce.Dao.UserDao" %>
<%@ page import="com.hcl.ecommerce.Model.DbCon" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        response.sendRedirect("index.jsp");
    }

    User admin = (User) request.getSession().getAttribute("admin");
    if(admin!= null){
        request.setAttribute("admin", admin);
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
        request.setAttribute("cart_list", cart_list );
    }

%>
<html>
<head>
    <title>Client Login</title>
    <%@include file="includes/header.jsp"%>  <!--BoostStrap CSS CDN -->
</head>
<body>

<style>
    .gradient-custom {
        height: 100%;
        /* fallback for old browsers */
        background: #6a11cb;

        /* Chrome 10-25, Safari 5.1-6 */
        background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));

        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1))
    }
</style>
<%@include file="includes/navbar.jsp"%>
<!-- Login Form -->
<form action="user-order" method="get" class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                            <style>#invalidtext{color: crimson;}</style>
                            <p class="btn btn-outline-dark" id="invalidtext"><strong>${invalid}</strong></p>
                            <p class="text-white-50 mb-5">Please enter your login and password!</p> <!-- success account created please login -->
                            <div class="form-outline form-white mb-4">
                                <input type="email"
                                       id="typeEmailX" class="form-control form-control-lg" placeholder="Enter Email" name="login-email" required/>
                                <label class="form-label" for="typeEmailX">Email</label>
                            </div>

                            <div class="form-outline form-white mb-4">
                                <input type="password" id="typePasswordX" class="form-control form-control-lg" name="login-password" required/>
                                <label class="form-label" for="typePasswordX">Password</label>
                            </div>
                            <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="">Forgot password?</a></p> <!-- Develop this -->

                            <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>

                        </div>

                        <div>
                            <p class="mb-0">Don't have an account? <a href="signup.jsp" class="text-white-50 fw-bold">Sign Up</a>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>


<%@include file="includes/BootScript.jsp"%> <!--BoostStrap JS CDN -->
</body>
</html>
