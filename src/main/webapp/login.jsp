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
    <%@include file="includes/header.jsp"%>
    <script src="https://ok1static.oktacdn.com/assets/js/sdk/okta-signin-widget/2.16.0/js/okta-sign-in.min.js" type="text/javascript"></script>
    <link href="https://ok1static.oktacdn.com/assets/js/sdk/okta-signin-widget/2.16.0/css/okta-sign-in.min.css" type="text/css" rel="stylesheet"/>
    <link href="https://ok1static.oktacdn.com/assets/js/sdk/okta-signin-widget/2.16.0/css/okta-theme.css" type="text/css" rel="stylesheet"/><!--BoostStrap CSS CDN -->
</head>
<%@include file="includes/navbar.jsp"%>
<body style="background-repeat: no-repeat; background-image:url(product-image/mountain.png)">

<%--<form action="user-login" method="post" class="vh-100 gradient-custom">--%>
<%--    <div class="container py-5 h-100">--%>
<%--        <div class="row d-flex justify-content-center align-items-center h-100">--%>
<%--            <div class="col-12 col-md-8 col-lg-6 col-xl-5">--%>
<%--                <div class="card bg-dark text-white" style="border-radius: 1rem;">--%>
<%--                    <div class="card-body p-5 text-center">--%>
<%--                        <div class="mb-md-5 mt-md-4 pb-5">--%>
<%--                            <h2 class="fw-bold mb-2 text-uppercase" style="color: white">Login</h2>--%>
<%--                            <style>#invalidtext{color: crimson;}</style>--%>
<%--                            <p class="btn btn-outline-dark" id="invalidtext"><strong>${invalid}</strong></p>--%>
<%--                            <p class="text-white-50 mb-5">Please enter your login and password!</p> <!-- success account created please login -->--%>
<%--                            <div class="form-outline form-white mb-4">--%>
<%--                                <input type="email"--%>
<%--                                       id="typeEmailX" class="form-control form-control-lg" placeholder="Enter Email" name="login-email" required/>--%>
<%--                                <label class="form-label" for="typeEmailX">Email</label>--%>
<%--                            </div>--%>

<%--                            <div class="form-outline form-white mb-4">--%>
<%--                                <input type="password" id="typePasswordX" class="form-control form-control-lg" name="login-password" required/>--%>
<%--                                <label class="form-label" for="typePasswordX">Password</label>--%>
<%--                            </div>--%>
<%--                            <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p> <!-- Develop this -->--%>

<%--                            <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>--%>

<%--                        </div>--%>

<%--                        <div>--%>
<%--                            <p class="mb-0">Don't have an account? <a href="signup.jsp" class="text-white-50 fw-bold">Sign Up</a>--%>
<%--                            </p>--%>
<%--                        </div>--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</form>--%>

<!-- where the sign-in form will be displayed -->
<div id="okta-login-container"></div>

<script type="text/javascript">
    var oktaSignIn = new OktaSignIn({
        baseUrl: "http://localhost:8080/ECommerceCapstone_war/",
        clientId: "0oa5vckhoeXudIB555d7",
        authParams: {
            issuer: "default",
            responseType: ['token', 'id_token'],
            display: 'page'
        }
    });

    if (oktaSignIn.token.hasTokensInUrl()) {
        oktaSignIn.token.parseTokensFromUrl(
            // If we get here, the user just logged in.
            function success(res) {
                var accessToken = res[0];
                var idToken = res[1];

                oktaSignIn.tokenManager.add('accessToken', accessToken);
                oktaSignIn.tokenManager.add('idToken', idToken);

                window.location.hash='';
                document.getElementById("messageBox").innerHTML = "Hello, " + idToken.claims.email + "! You just logged in! :)";
            },
            function error(err) {
                console.error(err);
            }
        );
    } else {
        oktaSignIn.session.get(function (res) {
            // If we get here, the user is already signed in.
            if (res.status === 'ACTIVE') {
                document.getElementById("messageBox").innerHTML = "Hello, " + res.login + "! You are *still* logged in! :)";
                return;
            }
            oktaSignIn.renderEl(
                { el: '#okta-login-container' },
                function success(res) {},
                function error(err) {
                    console.error(err);
                }
            );
        });
    }
</script>
<%@include file="includes/BootScript.jsp"%> <!--BoostStrap JS CDN -->
</body>
</html>
