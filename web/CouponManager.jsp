<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <title>Coupons Page</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="assets/css/fontawesome.css">
    <link rel="stylesheet" href="assets/css/templatemo-villa-agency.css">
    <link rel="stylesheet" href="assets/css/owl.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
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
                     <li><a href="CouponManager.jsp" class="active">Coupon Manager</a></li>
                      <li><a href="order.jsp">Order</a></li>
                      <li><a href="campaign.jsp">Campaign</a></li>
                      <li><a href="index.jsp">Logout</a></li>
                      <li><a href="#"><i class="fa fa-calendar"></i> Get your coupon</a></li>
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
          <span class="breadcrumb"><a href="#">Admin</a>  /  Coupons</span>
          <h3>Coupon Manager</h3>
        </div>
      </div>
    </div>
  </div>

<div class="single-property section">
  <div class="container">
    <div class="row">
      <div class="col-lg-13">
        <div class="main-content">
          <h4>Create New Coupon</h4>
          <form id="create-coupon-form" class="form-control" action="CouponServlet" method="post">
            <div class="row">
              <div class="col-md-6 mb-3">
                <label for="code" class="form-label">Coupon Code</label>
                <input type="text" id="code" name="code" class="form-control" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="discountType" class="form-label">Discount Type</label>
                <select id="discountType" name="discountType" class="form-control" required>
                  <option value="percentage">Percentage</option>
                  <option value="fixed">Fixed Amount</option>
                </select>
              </div>
              <div class="col-md-6 mb-3">
                <label for="discountValue" class="form-label">Discount Value</label>
                <input type="number" id="discountValue" name="discountValue" class="form-control" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="expirationDate" class="form-label">Expiration Date</label>
                <input type="date" id="expirationDate" name="expirationDate" class="form-control" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="usageLimit" class="form-label">Usage Limit</label>
                <input type="number" id="usageLimit" name="usageLimit" class="form-control" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="campaignId" class="form-label">Campaign ID</label>
                <input type="text" id="campaignId" name="campaignId" class="form-control">
              </div>
              <div class="col-md-6 mb-3">
                <label for="productId" class="form-label">Product ID</label>
                <input type="text" id="productId" name="productId" class="form-control">
              </div>
              <div class="col-12">
                <button type="submit" class="btn btn-primary">Create Coupon</button>
              </div>
            </div>
          </form>
        </div>

        <div class="main-content mt-5">
          
        </div>

      </div>
      
    </div>
  </div>
</div>

  <div class="section best-deal">
    <div class="container">
      <div class="row">
        <div class="col-lg-4">
          <div class="section-heading">
            <h4>All Coupons History</h4>
          </div>
        </div>
        <div class="col-lg-12">
          <div class="tabs-content">
          <div class="table-responsive">
            <table class="table table-bordered table-striped">
              <thead class="thead-dark">
                <tr>
                  <th>Code</th>
                  <th>Type</th>
                  <th>Value</th>
                  <th>Expiration</th>
                  <th>Usage Limit</th>
                  <th>Campaign ID</th>
                  <th>Product ID</th>
                  <th>Active</th>
                </tr>
              </thead>
              <tbody id="coupon-list">
                <tr>
                  <td>WELCOME10</td>
                  <td>Percentage</td>
                  <td>10%</td>
                  <td>2025-12-31</td>
                  <td>100</td>
                  <td>cmp123</td>
                  <td>prd456</td>
                  <td>Yes</td>
                </tr>
                <tr>
                  <td>FREESHIP</td>
                  <td>Fixed</td>
                  <td>RM5</td>
                  <td>2025-06-30</td>
                  <td>50</td>
                  <td>cmp999</td>
                  <td>prd001</td>
                  <td>No</td>
                </tr>
              </tbody>
            </table>
          </div>
          </div>
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