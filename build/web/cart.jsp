<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Your Shopping Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
    <!-- Add this near the top of the file, below the opening <body> tag -->
    <div class="container mt-5">
        <h1>Your Shopping Cart</h1>

        <!-- Coupon Application Form -->
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="card-title">Apply Coupon</h5>
                <form action="applyCoupon" method="post">
                    <div class="input-group">
                        <input type="text" class="form-control" name="couponCode" placeholder="Enter coupon code">
                        <button class="btn btn-outline-primary" type="submit">Apply</button>
                    </div>
                    <c:if test="${not empty couponMessage}">
                        <div class="mt-2 alert ${appliedCoupon != null ? 'alert-success' : 'alert-danger'}">
                            ${couponMessage}
                        </div>
                    </c:if>
                </form>
            </div>
        </div>

        <!-- Rest of your cart content... -->

        
    
    <div class="container mt-5">
        <h1>Your Shopping Cart</h1>
        
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
                        <c:forEach var="item" items="${cart}">
                            <tr>
                                <td>${item.product.name}</td>
                                <td>$${item.product.price}</td>
                                <td>${item.quantity}</td>
                                <td>$${item.totalPrice}</td>
                                <td>
                                    <form action="cart" method="post" style="display:inline">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="productId" value="${item.product.id}">
                                        <button type="submit" class="btn btn-sm btn-danger">Remove</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <!-- Update the total calculation section -->
                <div class="text-end">
                    <h4>Subtotal: $${subtotal = cart.stream().map(item -> item.totalPrice).sum()}</h4>

                    <c:if test="${not empty appliedCoupon}">
                        <h5>
                            Discount (${appliedCoupon.code} - 
                            <c:choose>
                                <c:when test="${appliedCoupon.discountType == 'PERCENT'}">
                                    ${appliedCoupon.discountValue}%
                                </c:when>
                                <c:otherwise>
                                    $${appliedCoupon.discountValue}
                                </c:otherwise>
                            </c:choose>):
                            $${discountAmount = appliedCoupon.discountType == 'PERCENT' ? 
                                subtotal * (appliedCoupon.discountValue / 100) : 
                                appliedCoupon.discountValue}
                        </h5>
                    </c:if>

                    <h3>
                        Total: 
                        $${total = (appliedCoupon != null) ? 
                            appliedCoupon.applyDiscount(subtotal) : subtotal}
                    </h3>

                    <a href="products" class="btn btn-secondary">Continue Shopping</a>
                    <a href="#" class="btn btn-primary">Checkout</a>
                </div>
    </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>