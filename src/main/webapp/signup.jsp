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

<form action="signup-user" method="post">
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration Form</h3>
                        <form>

                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <div class="form-outline">
                                        <input type="text" id="firstName" class="form-control form-control-lg" name="firstname" />
                                        <label class="form-label" for="firstName">First Name</label>
                                    </div>

                                </div>
                                <div class="col-md-6 mb-4">

                                    <div class="form-outline">
                                        <input type="text" id="lastName" class="form-control form-control-lg" name="lastname" />
                                        <label class="form-label" for="lastName">Last Name</label>
                                    </div>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-7 mb-4 d-flex align-items-center">

                                    <div class="form-outline datepicker w-100">
                                        <input type="text" class="form-control form-control-lg" name="address" />
                                        <label class="form-label">Address</label>
                                    </div>

                                </div>
                                <div class="col-md-5 mb-4">

                                    <div class="form-check form-check-inline"></div>
                                    <input type="text" class="form-control form-control-lg" name="city"/>
                                    <label class="form-label">City</label>
                                </div>

                                <div class="col-md-2 mb-4">

                                    <div class="form-check form-check-inline"></div>
                                    <input type="text" class="form-control form-control-lg" name="zip"/>
                                    <label class="form-label">Zip Code</label>
                                </div>

                                <div class="col-md-5 mb-4">

                                    <div class="form-check form-check-inline"></div>
                                    <input type="text" class="form-control form-control-lg" name="state"/>
                                    <label class="form-label">State</label>
                                </div>
                                <div class="col-md-5 mb-4">

                                    <div class="form-check form-check-inline"></div>
                                    <input type="text" class="form-control form-control-lg" name="country"/>
                                    <label class="form-label">Country</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4 pb-2">

                                    <div class="form-outline">
                                        <input type="email" id="emailAddress" class="form-control form-control-lg" name="email" />
                                        <label class="form-label" for="emailAddress">Email</label>
                                    </div>
                                    <div class="form-outline">
                                        <input type="password" id="password" class="form-control form-control-lg" name="password" />
                                        <label class="form-label" for="password">Password</label>
                                    </div>

                                </div>
                                <div class="col-md-6 mb-4 pb-2">

                                    <div class="form-outline">
                                        <input type="tel" id="phoneNumber" class="form-control form-control-lg" name="phonenumber"/>
                                        <label class="form-label" for="phoneNumber">Phone Number</label>
                                    </div>

                                </div>
                            </div>

                            <fieldset class="form-group">
                                <input type="hidden" value="Client" class="form-control"
                                       name="usertype" required="required">
                            </fieldset>


                            <div class="mt-4 pt-2">
                                <input class="btn btn-primary btn-lg" type="submit" value="Submit" />
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</form>
</body>
</html>