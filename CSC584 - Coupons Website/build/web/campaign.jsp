<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Objects"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<jsp:useBean id="campaignDAO" class="dao.CampaignDAO" />
<jsp:useBean id="couponDAO" class="dao.CouponDAO" />
<jsp:useBean id="productDAO" class="dao.ProductDAO" />
<c:set var="campaigns" value="${campaignDAO.getAllCampaigns()}" />
<c:set var="products" value="${productDAO.getAllProducts()}" />

<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <title>FoodMart Campaigns</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Additional CSS Files -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/templatemo-villa-agency.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    
    <style>
        .campaign-card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            transition: transform 0.3s;
            margin-bottom: 30px;
            border-left: 5px solid #4e73df;
        }
        .campaign-card:hover {
            transform: translateY(-5px);
        }
        .campaign-header {
            background-color: #f8f9fa;
            padding: 15px;
            border-bottom: 1px solid #eee;
        }
        .coupon-item {
            padding: 10px;
            margin: 5px 0;
            background-color: #f8f9fa;
            border-radius: 5px;
        }
        .badge-active {
            background-color: #28a745;
        }
        .badge-inactive {
            background-color: #6c757d;
        }
        .campaign-dates {
            color: #6c757d;
            font-size: 0.9rem;
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
                        <% if (isAdmin) { %>
                        <li><a href="dashboard">Dashboard</a></li>
                        <li><a href="CouponManager">Coupon Manager</a></li>
                        <% } %>
                      <li><a href="products">Order</a></li>
                      <li><a href="campaign.jsp" class="active">Campaign</a></li>
                      <% if (username != null) { %>
                        <li><a href="logout.jsp">Logout</a></li>
                      <% } else { %>
                        <li><a href="login.jsp">Login</a></li>
                      <% } %>
                      <li><a href="cart.jsp"><i class="fa fa-shopping-cart"></i> Cart </a></li>
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
          <span class="breadcrumb"><a href="#">Home</a>  /  Campaigns</span>
          <h3>Current Campaigns</h3>
        </div>
      </div>
    </div>
  </div>

  <div class="single-property section">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <c:choose>
            <c:when test="${not empty campaigns}">
              <c:forEach var="campaign" items="${campaigns}">
                <div class="card campaign-card mb-4">
                  <!-- [Previous campaign header section remains the same] -->
                  <div class="card-body">
                    <p class="card-text">${campaign.description}</p>
                    
                    <h5 class="mt-4">Related Coupons:</h5>
                    <c:set var="coupons" value="${couponDAO.getAllCoupons()}" />
                    <c:set var="hasCoupons" value="false" />
                    
                    <c:forEach var="coupon" items="${coupons}">
                      <c:if test="${coupon.campaignId == campaign.campaignId}">
                        <c:set var="hasCoupons" value="true" />
                        <div class="coupon-item">
                          <div class="d-flex justify-content-between">
                            <div>
                              <strong>${coupon.code}</strong> - 
                              <c:choose>
                                <c:when test="${coupon.discountType == 'PERCENT'}">
                                  ${coupon.discountValue}% OFF
                                </c:when>
                                <c:otherwise>
                                  RM${coupon.discountValue} OFF
                                </c:otherwise>
                              </c:choose>
                            </div>
                            <div>
                              Expires: <fmt:formatDate value="${coupon.expirationDate}" pattern="MMM d, yyyy"/>
                            </div>
                          </div>
                          <c:if test="${coupon.usageLimit != null}">
                            <small class="text-muted">Usage limit: ${coupon.usageLimit} times</small><br>
                          </c:if>
                          <c:choose>
                            <c:when test="${coupon.productId == null || coupon.productId == 0}">
                              <small class="text-muted">Applicable to all products</small>
                            </c:when>
                            <c:otherwise>
                              <c:set var="productName" value="Unknown Product" />
                              <c:forEach var="product" items="${products}">
                                <c:if test="${product.id == coupon.productId}">
                                  <c:set var="productName" value="${product.name}" />
                                </c:if>
                              </c:forEach>
                              <small class="text-muted">Applicable to: ${productName}</small>
                            </c:otherwise>
                          </c:choose>
                        </div>
                      </c:if>
                    </c:forEach>
                    
                    <c:if test="${not hasCoupons}">
                      <div class="alert alert-info">No coupons available for this campaign</div>
                    </c:if>
                  </div>
                </div>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <div class="alert alert-info">No campaigns available at this time</div>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
</div>

  <footer class="footer-no-gap">
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