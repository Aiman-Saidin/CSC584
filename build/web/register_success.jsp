<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Registration Successful - FoodMart</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="assets/css/fontawesome.css">
  <link rel="stylesheet" href="assets/css/templatemo-villa-agency.css">
</head>

<body>

  <!-- ***** Header Start ***** -->
  <header class="header-area header-sticky">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <nav class="main-nav">
            <a href="index.jsp" class="logo"><h1>FoodMart</h1></a>
            <ul class="nav">
              <li><a href="index.jsp">Home</a></li>
              <li><a href="CouponManager">Coupon Manager</a></li>
              <li><a href="products">Order</a></li>
              <li><a href="campaign.jsp">Campaign</a></li>
              <li><a href="login.jsp" class="active">Login</a></li>
            </ul>
            <a class='menu-trigger'><span>Menu</span></a>
          </nav>
        </div>
      </div>
    </div>
  </header>
  <!-- ***** Header End ***** -->

  <div class="container text-center" style="margin-top: 120px;">
    <h1 class="text-success mb-3">✅ Registration Successful!</h1>
    <p class="lead">Your account has been created.</p>
    <a href="login.jsp" class="btn btn-primary mt-3">Click here to login</a>
  </div>

  <footer class="mt-5">
    <div class="container">
      <div class="col-lg-12">
        <p>FoodMart</p>
      </div>
    </div>
  </footer>

  <!-- Bootstrap JS -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
