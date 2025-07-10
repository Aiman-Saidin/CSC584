<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.Objects"%>
<%
    // Check if user is logged in
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    // Safely check for admin status
    Object adminAttr = userSession.getAttribute("isAdmin");
    boolean isAdmin = adminAttr != null && Boolean.parseBoolean(adminAttr.toString());
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <title>Dashboard | FoodMart</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Additional CSS Files -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/templatemo-villa-agency.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    
    <style>
        .stat-card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            transition: transform 0.3s;
            height: 100%;
        }
        .stat-card:hover {
            transform: translateY(-5px);
        }
        .stat-icon {
            font-size: 2rem;
            margin-bottom: 15px;
        }
        .recent-item {
            border-left: 3px solid #4e73df;
            padding-left: 10px;
            margin-bottom: 10px;
        }
        .chart-container {
            position: relative;
            height: 300px;
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
                     <li><a href="dashboard" class="active">Dashboard</a></li>
                     <li><a href="CouponManager">Coupon Manager</a></li>
                     <li><a href="products">Order</a></li>
                     <li><a href="campaign.jsp">Campaign</a></li>
                     <li><a href="logout.jsp">Logout</a></li>
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
          <span class="breadcrumb"><a href="#">Home</a>  /  Dashboard</span>
          <h3>Dashboard</h3>
        </div>
      </div>
    </div>
  </div>

  <div class="single-property section">
    <div class="container">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        
        <div class="row mb-4">
            <% if (isAdmin) { %>
            <!-- Admin Dashboard Stats -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="stat-card card border-left-primary shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                    Total Sales</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">
                                    <fmt:formatNumber value="${salesStats.totalSales}" type="currency" currencySymbol="RM"/>
                                </div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-xl-3 col-md-6 mb-4">
                <div class="stat-card card border-left-info shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                    Active Campaigns</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">${activeCampaigns.size()}</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xl-3 col-md-6 mb-4">
                <div class="stat-card card border-left-warning shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                    Total Users</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">${userCount}</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-users fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                            
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="stat-card card border-left-info shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                    Active Coupons</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">${activeCouponCount}</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-tags fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>                
                            
            <% } else { %>
            <!-- User Welcome Section -->
            <div class="col-12">
                <div class="card shadow mb-4">
                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Welcome back, ${username}!</h6>
                    </div>
                    <div class="card-body">
                        <p>Here's your recent activity on FoodMart. You can view your orders, manage your account, or start shopping.</p>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
        
        <div class="row">
            <% if (isAdmin) { %>
            <!-- Admin Dashboard Content -->
            <div class="col-lg-8">
                <!-- Recent Orders Card -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Recent Orders</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <!-- In the recent orders table, add a new column -->
                            <table class="table table-bordered" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Order ID</th>
                                        <th>Customer</th>
                                        <th>Date</th>
                                        <th>Amount</th>
                                        <th>Discount</th>
                                        <th>Final Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="order" items="${recentOrders}">
                                        <tr>
                                            <td>${order.orderId}</td>
                                            <td>${order.username}</td>
                                            <td><fmt:formatDate value="${order.orderDate}" pattern="MMM dd, yyyy"/></td>
                                            <td><fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="RM"/></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.discountApplied != null}">
                                                        <fmt:formatNumber value="${order.discountApplied}" type="currency" currencySymbol="RM"/>
                                                    </c:when>
                                                    <c:otherwise>-</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatNumber value="${order.finalAmount}" type="currency" currencySymbol="RM"/></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-lg-4">
                <!-- Popular Products Card -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Popular Products</h6>
                    </div>
                    <div class="card-body">
                        <c:forEach var="product" items="${popularProducts}">
                            <div class="recent-item">
                                <div class="text-truncate font-weight-bold">${product.name}</div>
                                <div class="small text-gray-500"><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="RM"/></div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                
                <!-- Active Campaigns Card -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Active Campaigns</h6>
                    </div>
                    <div class="card-body">
                        <c:forEach var="campaign" items="${activeCampaigns}">
                            <div class="recent-item">
                                <div class="text-truncate font-weight-bold">${campaign.name}</div>
                                <div class="small text-gray-500">
                                    <fmt:formatDate value="${campaign.startDate}" pattern="MMM dd"/> - 
                                    <fmt:formatDate value="${campaign.endDate}" pattern="MMM dd"/>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <% } else { %>
            <!-- User Dashboard Content -->
            <div class="col-lg-12">
                <!-- Recent Orders Card -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Your Recent Orders</h6>
                    </div>
                    <div class="card-body">
                        <c:choose>
                            <c:when test="${not empty recentOrders}">
                                <div class="table-responsive">
                                    <table class="table table-bordered" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Order ID</th>
                                                <th>Date</th>
                                                <th>Total</th>
                                                <th>Discount</th>
                                                <th>Final Amount</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="order" items="${recentOrders}">
                                                <tr>
                                                    <td>${order.orderId}</td>
                                                    <td><fmt:formatDate value="${order.orderDate}" pattern="MMM dd, yyyy"/></td>
                                                    <td><fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="RM"/></td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${order.discountApplied != null}">
                                                                <fmt:formatNumber value="${order.discountApplied}" type="currency" currencySymbol="RM"/>
                                                            </c:when>
                                                            <c:otherwise>-</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td><fmt:formatNumber value="${order.finalAmount}" type="currency" currencySymbol="RM"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <p>You haven't placed any orders yet. <a href="products">Start shopping now!</a></p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <% } %>
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