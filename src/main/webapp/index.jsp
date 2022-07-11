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
    <title>Evolve</title>
    <%@include file="includes/header.jsp"%>  <!--BoostStrap CSS CDN -->
</head>
<body class="gradient-custom">
<%@include file="includes/navbar.jsp"%>

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

<%@include file="includes/BootScript.jsp"%> <!--BoostStrap JS CDN -->
</body>
</html>
