<%@ page import="com.hcl.ecommerce.Model.DbCon" %>
<%@ page import="com.hcl.ecommerce.Model.User" %>
<%@ page import="com.hcl.ecommerce.Dao.ProductDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hcl.ecommerce.Model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth", auth);
    }

    ProductDao pd = new ProductDao(DbCon.getConnection());
    List<Product> products = pd.getAllProducts();
%>
<html>
<head>
    <title>Welcome to ECommerce</title>
    <%@include file="includes/header.jsp"%>  <!--BoostStrap CSS CDN -->
</head>
<body class="gradient-custom">
<%@include file="includes/navbar.jsp"%>
<%--<style>--%>
<%--    .gradient-custom {--%>
<%--        /* fallback for old browsers  */--%>
<%--        background: #6a11cb;--%>

<%--        /* Chrome 10-25, Safari 5.1-6 */--%>
<%--        background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));--%>

<%--        /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */--%>
<%--        background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1))--%>
<%--    }--%>

<%--</style>--%>

<%--<!-- Product Cards -->--%>
<%--<div class="container">--%>
<%--    <div class="card-header my-3"> All Products</div>--%>
<%--    <div class="row">--%>

<%--        <%if (!products.isEmpty()){--%>
<%--                for(Product p:products){%>--%>
<%--                    <div class="col-md-3 my-5">--%>
<%--            <div class="card w-100" style="width: 18rem;">--%>
<%--                <img class="card-img-top" src="product-image/<%= p.getImage()%>" alt="Card image cap">--%>
<%--                <div class="card-body">--%>
<%--                    <h5 class="card-title"><%= p.getName()%></h5>--%>
<%--                    <h6 class="price">$<%=p.getPrice()%></h6>--%>
<%--                    <h6 class="price"><%=p.getCategory()%></h6>--%>
<%--                    <div class="mt-3 d-flex justify-content-between">--%>
<%--                        <a href="#" class="btn btn-primary">Add To Cart</a>--%>
<%--                        <a href="#" class="btn btn-primary">Buy Now</a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--                    </div>--%>
<%--                <%}--%>
<%--            }--%>
<%--        %>--%>

<%--        </div>--%>
<%--    </div>--%>


<section style=" background-color: #20bf55; background-image:linear-gradient(315deg, #20bf55 0%, #01baef 74%);">
    <div class="text-center container py-5">
        <h4 class="mt-4 mb-5"><strong>Products</strong></h4>

        <div class="row">
                <%if (!products.isEmpty()){
                for(Product p:products){%>
            <div class="col-lg-4 col-md-12 mb-4">
                <div class="card">
                    <div class="bg-image hover-zoom ripple ripple-surface ripple-surface-light"
                         data-mdb-ripple-color="light">
                        <img src="product-image/<%= p.getImage()%>"
                             class="w-100" alt="Product Images"/>
                        <a href="#!">
                            <div class="hover-overlay">
                                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                            </div>
                        </a>
                    </div>
                    <div class="card-body">
                        <a href="" class="text-reset">
                            <h5 class="card-title mb-3"><%= p.getName()%>
                            </h5>
                        </a>
                        <a href="" class="text-reset">
                            <p><%=p.getCategory()%>
                            </p>
                        </a>
                        <h6 class="mb-3">$<%=p.getPrice()%>
                        </h6>
                        <button class="btn btn-primary btn-sm" type="button">Add To Cart</button>
                        <button class="btn btn-primary btn-sm" type="button">Buy Now</button>
                    </div>
                </div>
            </div>

                <%}
            }
        %>

<%@include file="includes/footer.jsp"%> <!--BoostStrap JS CDN -->
</body>
</html>
