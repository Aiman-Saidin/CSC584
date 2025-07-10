<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Your Cart - FoodMart</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Additional CSS Files -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/templatemo-villa-agency.css">
</head>
<body>
  <!-- Header (same as order.jsp) -->
  <%@include file="header.jsp"%>

  <div class="page-heading header-text">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <h3>Your Cart</h3>
        </div>
      </div>
    </div>
  </div>

  <div class="container mt-5">
    <div class="row">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">
            <h4>Cart Items</h4>
          </div>
          <div class="card-body">
            <c:choose>
              <c:when test="${empty cart}">
                <p>Your cart is empty</p>
              </c:when>
              <c:otherwise>
                <table class="table">
                  <thead>
                    <tr>
                      <th>Product</th>
                      <th>Price</th>
                      <th>Quantity</th>
                      <th>Total</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="entry" items="${cart}">
                      <c:set var="item" value="${entry.value}"/>
                      <tr>
                        <td>${item.product.name}</td>
                        <td><fmt:formatNumber value="${item.product.price}" type="currency"/></td>
                        <td>
                          <form action="cart" method="post" class="d-inline">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="productId" value="${item.product.id}">
                            <input type="number" name="quantity" value="${item.quantity}" min="1" class="form-control" style="width: 70px;" onchange="this.form.submit()">
                          </form>
                        </td>
                        <td><fmt:formatNumber value="${item.getTotalPrice()}" type="currency"/></td>
                        <td>
                          <form action="cart" method="post" class="d-inline">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="productId" value="${item.product.id}">
                            <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                          </form>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>
      
      <div class="col-md-4">
        <div class="card">
          <div class="card-header">
            <h4>Order Summary</h4>
          </div>
          <div class="card-body">
            <!-- Coupon Form -->
            <form action="cart" method="post">
              <input type="hidden" name="action" value="applyCoupon">
              <div class="mb-3">
                <label for="couponCode" class="form-label">Coupon Code</label>
                <div class="input-group">
                  <input type="text" class="form-control" id="couponCode" name="couponCode" placeholder="Enter coupon code">
                  <button type="submit" class="btn btn-primary">Apply</button>
                </div>
                <c:if test="${not empty couponMessage}">
                  <div class="text-${not empty appliedCoupon ? 'success' : 'danger'} mt-2">${couponMessage}</div>
                </c:if>
              </div>
            </form>
            
            <hr>
            
            <!-- Order Summary -->
            <c:set var="subtotal" value="0"/>
            <c:forEach var="entry" items="${cart}">
              <c:set var="subtotal" value="${subtotal + entry.value.getTotalPrice()}"/>
            </c:forEach>
            
            <p>Subtotal: <fmt:formatNumber value="${subtotal}" type="currency"/></p>
            
            <c:if test="${not empty appliedCoupon}">
              <c:set var="discount" value="0"/>
              <c:choose>
                <c:when test="${appliedCoupon.discountType == 'PERCENT'}">
                  <c:set var="discount" value="${subtotal * appliedCoupon.discountValue / 100}"/>
                </c:when>
                <c:otherwise>
                  <c:set var="discount" value="${appliedCoupon.discountValue}"/>
                </c:otherwise>
              </c:choose>
              <p>Discount: -<fmt:formatNumber value="${discount}" type="currency"/></p>
              <form action="cart" method="post" class="mb-3">
                <input type="hidden" name="action" value="removeCoupon">
                <button type="submit" class="btn btn-sm btn-outline-danger">Remove Coupon</button>
              </form>
            </c:if>
            
            <c:set var="total" value="${subtotal - (empty discount ? 0 : discount)}"/>
            <h5>Total: <fmt:formatNumber value="${total}" type="currency"/></h5>
            
            <form action="checkout" method="post">
              <button type="submit" class="btn btn-primary w-100 mt-3">Proceed to Checkout</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Footer (same as order.jsp) -->
  <%@include file="footer.jsp"%>

  <!-- Scripts -->

  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>