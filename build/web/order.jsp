<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <title>FoodMart</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-villa-agency.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    <style>
    .item {
      text-align: center; /* Center image and content */
    }

    .item img {
      max-width: 100%;  /* Responsive scaling */
      height: auto;     /* Maintain aspect ratio */
      margin: 0 auto;   /* Center horizontally */
      display: block;   /* Make margin auto work */
    }
    .item {
      padding: 20px;
    }

    .item img {
      width: 80%;
      max-width: 300px;
    }
    </style>
  </head>

<body>

  <!-- ***** Preloader Start ***** -->
  <div id="js-preloader" class="js-preloader">
    <div class="preloader-inner">
      <span class="dot"></span>
      <div class="dots">
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>
  </div>
  <!-- ***** Preloader End ***** -->
 

  <!-- ***** Header Area Start ***** -->
  <header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="index.jsp" class="logo">
                        <h1>FoodMart</h1>
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                      <li><a href="index.jsp">Home</a></li>
                     <li><a href="CouponManager.jsp">Coupon Manager</a></li>
                      <li><a href="products" class="active">Order</a></li>
                      <li><a href="campaign.jsp">Campaign</a></li>
                      <li><a href="login.jsp">Login</a></li>
                      <li><a href="#"><i class="fa fa-calendar"></i> Get your coupon</a></li>
                      <li><a href="cart.jsp"><i class="fa fa-shopping-cart"></i> Cart (${cartCount})</a></li>
                  </ul>   
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
  </header>
  <!-- ***** Header Area End ***** -->

  <div class="page-heading header-text">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <span class="breadcrumb"><a href="#">Product</a> / Order</span>
          <h3>Order</h3>
        </div>
      </div>
    </div>
  </div>


  <div class="section properties">
    <div class="container">
   
      <ul class="properties-filter">
        <li>
          <a class="is_active" href="products">Show All</a>
        </li>
        <li>
          <a href="products?category=Fruits" data-filter=".adv">Fruits</a>
        </li>
        <li>
          <a href="products?category=Vegetables" data-filter=".str">Vegetables</a>
        </li>
        <li>
          <a href="products?category=Others" data-filter=".rac">Others</a>
        </li>
      </ul>
     <div class="row justify-content-center">
        <c:forEach var="product" items="${products}">
          <div class="col-lg-4 col-md-6 mb-4 d-flex justify-content-center">
            <div class="card text-center p-3">
              <img src="${product.imagePath}" class="card-img-top mx-auto" alt="${product.name}" style="max-width: 150px;">
              <div class="card-body">
                <h5 class="card-title">${product.name}</h5>
                <p class="card-text">$${product.price}</p>
                <p class="card-text">${product.description}</p>
                <form action="cart" method="post">
                    <input type="hidden" name="action" value="add">
                    <input type="hidden" name="productId" value="${product.id}">
                    <button type="submit" class="btn btn-primary">Add to cart</button>
                </form>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>

      <!-- Insert Coupon Codes -->
      <div class="row justify-content-center mt-4">
        <div class="col-md-6">
          <div class="card p-4">
            <h5 class="mb-3 text-center">Have a Coupon?</h5>
            <form id="apply-coupon-form">
              <div class="input-group">
                <input type="text" class="form-control" id="couponCode" placeholder="Enter coupon code">
                <button class="btn btn-outline-primary" type="submit">Apply</button>
              </div>
              <small class="form-text text-muted mt-2" id="couponFeedback"></small>
            </form>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-12">
          <ul class="pagination">
            <li><a class="is_active" href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">>></a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>

  <footer>
    <div class="container">
      <div class="col-lg-12">
        <p>FoodMart</p>
      </div>
    </div>
  </footer>

  <!-- Scripts -->
  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
  <script src="assets/js/isotope.min.js"></script>
  <script src="assets/js/owl-carousel.js"></script>
  <script src="assets/js/counter.js"></script>
  <script src="assets/js/custom.js"></script>

</body>
</html>