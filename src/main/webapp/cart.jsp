<%@page import="java.text.DecimalFormat" %>
<%@ page import="com.hcl.ecommerce.Model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hcl.ecommerce.Model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hcl.ecommerce.Dao.ProductDao" %>
<%@ page import="com.hcl.ecommerce.Model.DbCon" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User auth = (User) request.getSession().getAttribute("auth");
  if(auth != null){
    request.setAttribute("auth", auth);
  }
  DecimalFormat format = new DecimalFormat("$#0.00"); //used for formatting
  ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
  List<Cart> cartProduct = null;
  if(cart_list != null){
    ProductDao productDao = new ProductDao(DbCon.getConnection());
    cartProduct = productDao.ProductCart(cart_list);
    double total = productDao.totalPrice(cart_list);
    request.setAttribute("cart_list", cart_list);
    request.setAttribute("total", format.format(total));
  }

%>
<html>
<head>
  <title>Your Cart</title>
  <%@include file="includes/header.jsp"%>  <!--BoostStrap CSS CDN -->
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<style>
  body {
    background: #eecda3;
    background: -webkit-linear-gradient(to right, #eecda3, #ef629f);
    background: linear-gradient(to right, #eecda3, #ef629f);
    min-height: 100vh;
  }
</style>

<div class="px-4 px-lg-0">
  <!-- For demo purpose -->
  <div class="container text-white py-5 text-center">
    <%if (cart_list == null){%>
    <h1 class="display-4">Shopping Cart Is Empty</h1>
    <p class="lead mb-0">Try Looking Around</p>
    <%}%>
    <%if (cart_list != null){%>
    <h1 class="display-4">Your Shopping Cart</h1>
    <p class="lead mb-0">Oh Wow, You've Been Busy</p>
    <%}%>
  </div>
  <!-- End -->

  <div class="pb-5">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

          <!-- Shopping cart table -->
          <div class="table-responsive">
            <table class="table">
              <thead>
              <tr>
                <th scope="col" class="border-0 bg-light">
                  <div class="p-2 px-3 text-uppercase">Product</div>
                </th>
                <th scope="col" class="border-0 bg-light">
                  <div class="py-2 text-uppercase">Price</div>
                </th>
                <th scope="col" class="border-0 bg-light">
                  <div class="py-2 text-uppercase">Quantity</div>
                </th>
                <th scope="col" class="border-0 bg-light">
                  <div class="py-2 text-uppercase">Remove</div>
                </th>
              </tr>
              </thead>
              <tbody>
              <%if (cart_list != null){%>
              <%for(Cart c:cartProduct){%>
              <tr>
                <th scope="row" class="border-0">
                  <div class="p-2">
                    <img src="product-image/<%= c.getImage()%>" alt="" width="70" class="img-fluid rounded shadow-sm">
                    <div class="ml-3 d-inline-block align-middle">
                      <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block align-middle"><%=c.getName()%></a></h5><span class="text-muted font-weight-normal font-italic d-block"><%=c.getCategory()%></span>
                    </div>
                  </div>
                </th>
                <td class="border-0 align-middle"><strong>$<%=c.getPrice()%></strong></td>
                <td class="border-0 align-middle"><a href="quantity-control" class="btn btn-outline-success btn-small" >+</a><strong> <%=c.getQuantity()%> </strong> <a href="quantity-control" class="btn btn-outline-danger btn-small" >  -</a>
                </td>
                <td class="border-0 align-middle"><a href="#" class="text-dark"><i class="fa fa-trash"></i></a></td>
              </tr>
              <%}%>
              <%}%>
              </tbody>
            </table>
          </div>
          <!-- End -->
        </div>
      </div>

      <div class="row py-5 p-4 bg-white rounded shadow-sm">
        <div class="col-lg-6">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Coupon code</div>
          <div class="p-4">
            <p class="font-italic mb-4">If you have a coupon code, please enter it in the box below</p>
            <div class="input-group mb-4 border rounded-pill p-2">
              <input type="text" placeholder="Apply coupon" aria-describedby="button-addon3" class="form-control border-0">
              <div class="input-group-append border-0">
                <button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill"><i class="fa fa-gift mr-2"></i>Apply coupon</button>
              </div>
            </div>
          </div>
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Instructions for seller</div>
          <div class="p-4">
            <p class="font-italic mb-4">If you have some information for the seller you can leave them in the box below</p>
            <textarea name="" cols="30" rows="2" class="form-control"></textarea>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Order summary </div>
          <div class="p-4">
            <p class="font-italic mb-4">Shipping and additional costs are calculated based on values you have entered.</p>
            <ul class="list-unstyled mb-4">
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Order Subtotal </strong><strong>$390.00</strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Shipping and handling</strong><strong>$10.00</strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tax</strong><strong>$0.00</strong></li>
              <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                <h5 class="font-weight-bold">${(total)}</h5>
              </li>
            </ul><a href="#" class="btn btn-dark rounded-pill py-2 btn-block">Procceed to checkout</a>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>

<%@include file="includes/BootScript.jsp"%> <!--BoostStrap JS CDN -->
</body>
</html>

