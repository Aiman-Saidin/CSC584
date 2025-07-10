<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.*,javax.servlet.*"%>
<%@page import="java.util.Objects"%>
<%
    // Check if user is logged in
    HttpSession userSession = request.getSession(false);

    
    // Safely check for admin status
    Object adminAttr = userSession.getAttribute("isAdmin");
    boolean isAdmin = adminAttr != null && Boolean.parseBoolean(adminAttr.toString());
%>
<%
  String username = (session != null) ? (String) session.getAttribute("username") : null;
%>

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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/templatemo-villa-agency.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
  </head>

<body>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty checkoutSuccess}">
    <div class="alert alert-success alert-dismissible fade show text-center">
        ${checkoutSuccess}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <c:remove var="checkoutSuccess" scope="session"/>
</c:if>
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
                        <li><a href="index.jsp" class="active">Home</a></li>
                        <% if (isAdmin) { %>
                        <li><a href="dashboard">Dashboard</a></li>
                        <li><a href="CouponManager">Coupon Manager</a></li>
                        <% } %>
                        <li><a href="products">Order</a></li>
                        <li><a href="campaign.jsp">Campaign</a></li>

                        <% if (username != null) { %>
                          <li><a href="logout.jsp">Logout</a></li>
                        <% } else { %>
                          <li><a href="login.jsp">Login</a></li>
                        <% } %>

                        <li><a href="cart.jsp"><i class="fa fa-shopping-cart"></i> Cart</a></li>


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

  <div class="main-banner">
    <div class="owl-carousel owl-banner">
      <div class="item item-1">
        <div class="header-text">
          <span class="category">Baling, <em>Malaysia</em></span>
          <h2>Hurry!<br>Let's Shop until you drop</h2>
        </div>
      </div>
    </div>
  </div>

  <div class="featured section">
    <div class="container">
      <div class="row">
        <div class="col-lg-4">
          <div class="left-image">
            <img src="assets/images/mart.png" alt="">
            <a href="order.jsp"><img src="assets/images/featured-icon.png" alt="" style="max-width: 60px; padding: 0px;"></a>
          </div>
        </div>
        <div class="col-lg-5">
          <div class="section-heading">
            <h6>| Featured</h6>
            <h2>Best Shop &amp; for your groceries</h2>
          </div>
          <div class="accordion" id="accordionExample">
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                  Best useful links ?
                </button>
              </h2>
              <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                Discover why <strong>FoodMart</strong> is your go-to destination for everyday groceries. From fresh produce to pantry staples, we offer everything you need in one place — always affordable and always fresh.</div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                  How does this work ?
                </button>
              </h2>
              <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                 Simply walk in, browse our organized aisles, and find your favorite items with ease. Our friendly staff is here to help you every step of the way.
                </div>
              </div>
            </div>
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingThree">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                  Why is FoodMart the best ?
                </button>
              </h2>
              <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                 FoodMart stands out for its commitment to quality, customer satisfaction, and community. We bring you the best products, top-notch service, and a shopping experience that makes you feel at home.
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-3">
          <div class="info-table">
            <ul>
              <li style="display: flex; align-items: center;">
                <img src="assets/images/store.png" alt="Store Icon" style="max-width: 52px;">
                  <h4>
                  Retail Unit<br>
                  <span>Fully Equipped Store</span>
                  </h4>
              </li>
              <li style="display: flex; align-items: center;">
                <img src="assets/images/cart.jpg" alt="Cart Icon" style="max-width: 52px;">
                  <div>
                    <h4 style="margin: 0; line-height: 1.2;">Retail Ready</h4>
                      <span style="color: #888; font-size: 14px;">Contract Available</span>
                  </div>
              </li>
              <li>
                <img src="assets/images/grocery.jpg" alt="" style="max-width: 52px;">
                <h4>Groceries<br><span>Available Supplies</span></h4>
              </li>
              <li>
                <img src="assets/images/open.jpg" alt="" style="max-width: 52px;">
                <h4>Open<br><span>24/7 Open</span></h4>
              </li>
            </ul>
          </div>
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