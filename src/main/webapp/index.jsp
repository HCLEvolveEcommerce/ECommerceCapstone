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

    User admin = (User) request.getSession().getAttribute("admin");
    if(admin!= null){
        request.setAttribute("admin", admin);
    }

    ProductDao pd = new ProductDao(DbCon.getConnection());
    List<Product> products = pd.getAllProducts();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
        request.setAttribute("cart_list", cart_list );
    }
%>
<html>
<head>
    <title>Evolve</title>
    <%@include file="includes/header.jsp"%>  <!--BoostStrap CSS CDN -->
</head>
<body class="gradient-custom">
<%@include file="includes/navbar.jsp"%>
<section class="section-intro bg-light padding-y-lg" style="background-image:url(product-image/mountain.png)">

    <div class="container">

        <article class="my-5 padding-y-lg text-center">
            <h1 class="display-4 text-black"> Luxury Products<br> At Your Fingertips  </h1>
            <p class="lead text-black">Trendy Products, Quality Material, Excellent Service</p>
            <a href="#" class="btn btn-outline-dark"> Learn more </a>
        </article>

    </div> <!-- container end.// -->


</section>



<section class="bg-light padding-y">
    <div class="container">

        <header class="section-heading mt-2 mb-5">
            <h3 class="section-title">Why choose us</h3>
        </header>

        <div class="row mb-4">
            <div class="col-lg-4 col-md-6">
                <figure class="itemside align-items-center mb-4">
                    <div class="aside">
					<span class="rounded-circle shadow-sm text-dark icon-lg bg-white">
						<i class="fa fa-money-bill"></i>
					</span>
                    </div>
                    <figcaption class="info">
                        <h6 class="title">Reasonable prices</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit sed do eiusmor </p>
                    </figcaption>
                </figure> <!-- itemside // -->
            </div><!-- col // -->
            <div class="col-lg-4 col-md-6">
                <figure class="itemside align-items-center  mb-4">
                    <div class="aside">
					<span class="rounded-circle shadow-sm text-dark icon-lg bg-white">
						<i class="fa fa-star"></i>
					</span>
                    </div>
                    <figcaption class="info">
                        <h6 class="title">Best quality</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit sed do eiusmor </p>
                    </figcaption>
                </figure> <!-- itemside // -->
            </div><!-- col // -->
            <div class="col-lg-4 col-md-6">
                <figure class="itemside align-items-center  mb-4">
                    <div class="aside">
					<span class="rounded-circle shadow-sm text-darkicon-lg bg-white">
						<i class="fa fa-plane"></i>
					</span>
                    </div>
                    <figcaption class="info">
                        <h6 class="title">Worldwide shipping</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit sed do eiusmor </p>
                    </figcaption>
                </figure> <!-- itemside // -->
            </div> <!-- col // -->
            <div class="col-lg-4 col-md-6">
                <figure class="itemside align-items-center  mb-4">
                    <div class="aside">
					<span class="rounded-circle shadow-sm text-dark icon-lg bg-white">
						<i class="fa fa-users"></i>
					</span>
                    </div>
                    <figcaption class="info">
                        <h6 class="title">Customer satisfaction</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit sed do eiusmor </p>
                    </figcaption>
                </figure> <!-- itemside // -->
            </div><!-- col // -->
            <div class="col-lg-4 col-md-6">
                <figure class="itemside align-items-center mb-4">
                    <div class="aside">
					<span class="rounded-circle shadow-sm text-dark icon-lg bg-white">
						<i class="fa fa-thumbs-up"></i>
					</span>
                    </div>
                    <figcaption class="info">
                        <h6 class="title">Happy customers</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit sed do eiusmor </p>
                    </figcaption>
                </figure> <!-- itemside // -->
            </div><!-- col // -->
            <div class="col-lg-4 col-md-6">
                <figure class="itemside align-items-center mb-4">
                    <div class="aside">
					<span class="rounded-circle shadow-sm text-dark icon-lg bg-white">
						<i class="fa fa-box"></i>
					</span>
                    </div>
                    <figcaption class="info">
                        <h6 class="title">Thousand items</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit sed do eiusmor </p>
                    </figcaption>
                </figure> <!-- itemside // -->
            </div> <!-- col // -->
        </div>
    </div> <!-- container end.// -->
</section>





<section class="padding-y">
    <div class="container">

        <header class="section-heading">
            <h4 class="section-title">Featured Products</h4>
        </header>

        <div class="row">
            <%if (!products.isEmpty()){
                for(Product p:products){%>
            <div class="col-lg-6 col-md-1 col-sm-6">
                <figure class="card card-product-grid">
                    <div class="img-wrap">
                        <img src="product-image/<%= p.getImage()%>">
                    </div>
                    <figcaption class="info-wrap border-bottom mx-auto">
                        <div class="price-wrap">
                            <span class="price">$<%=p.getPrice()%></span>
                        </div> <!-- price-wrap.// -->
                        <p class="title mb-2"><%= p.getName()%></p>

                        <a href="add-cart?id=<%=p.getId()%>" class="btn btn-outline-primary">Add to cart</a>
                        <a href="user-order?quantity=1&id=<%=p.getId()%>" class="btn btn-success">Buy Now</a>
                        <a href="#" class="btn btn-light btn-icon"> <i class="fa fa-heart"></i> </a>
                    </figcaption>
                </figure>
            </div>
            <%}
            }
            %>
        </div>
</section>






<%@include file="includes/BootScript.jsp"%> <!--BoostStrap JS CDN -->
</body>
</html>
