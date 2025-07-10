<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    // Check if user is logged in
    HttpSession userSession = request.getSession(false);
    if (userSession == null || userSession.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return; // Important to stop further execution
    }
%>
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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
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
                     <li><a href="CouponManager" class="active">Coupon Manager</a></li>
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
          
            
            <div class="container mt-5">
        <h1 class="mb-4">Coupon & Campaign Manager</h1>
        
        <!-- Tab Navigation -->
        <ul class="nav nav-tabs" id="managerTabs" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="history-tab" data-bs-toggle="tab" href="#history" role="tab">History</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="create-tab" data-bs-toggle="tab" href="#create" role="tab">Create New</a>
            </li>
            
        </ul>
        
        <!-- Tab Content -->
        <div class="tab-content" id="managerTabsContent">
            <!-- Create New Tab -->
            <div class="tab-pane fade" id="create" role="tabpanel">
                <div class="row">
                    <!-- Create Coupon Section -->
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header bg-primary text-white">
                                <h3>Create New Coupon</h3>
                            </div>
                            <div class="card-body">
                                <form action="coupon" method="post">
                                    <input type="hidden" name="action" value="create">

                                    <div class="mb-3">
                                        <label for="couponCode" class="form-label">Coupon Code *</label>
                                        <input type="text" class="form-control" id="couponCode" name="code" required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="discountType" class="form-label">Discount Type *</label>
                                        <select class="form-select" id="discountType" name="discount_type" required>
                                            <option value="PERCENT">Percentage</option>
                                            <option value="FIXED">Fixed Amount</option>
                                        </select>
                                    </div>

                                    <div class="mb-3">
                                        <label for="discountValue" class="form-label">Discount Value *</label>
                                        <input type="number" step="0.01" class="form-control" id="discountValue" 
                                               name="discount_value" required min="0.01">
                                    </div>

                                    <div class="mb-3">
                                        <label for="expirationDate" class="form-label">Expiration Date *</label>
                                        <input type="date" class="form-control" id="expirationDate" 
                                               name="expiration_date" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="usageLimit" class="form-label">Usage Limit (minimum 1)</label>
                                        <input type="number" class="form-control" id="usageLimit" name="usage_limit" min="1">
                                    </div>
                                    <div class="mb-3">
                                        <label for="couponCampaign" class="form-label">Attach to Campaign (optional)</label>
                                        <select class="form-select" id="couponCampaign" name="campaign_id">
                                            <option value="">-- No Campaign --</option>
                                            <c:forEach var="campaign" items="${campaigns}">
                                                <option value="${campaign.campaignId}">${campaign.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="couponProduct" class="form-label">Apply to Product (optional)</label>
                                        <select class="form-select" id="couponProduct" name="product_id">
                                            <option value="">-- No Specific Product --</option>
                                            <c:forEach var="product" items="${products}">
                                                <option value="${product.id}">${product.name} ($${product.price})</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="mb-3 form-check">
                                        <input type="checkbox" class="form-check-input" id="couponActive" name="is_active" checked>
                                        <label class="form-check-label" for="couponActive">Active</label>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Create Coupon</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Create Campaign Section -->
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header bg-success text-white">
                                <h3>Create New Campaign</h3>
                            </div>
                            <div class="card-body">
                                <form action="campaign" method="post">
                                    <input type="hidden" name="action" value="create">
                                    <div class="mb-3">
                                        <label for="campaignName" class="form-label">Campaign Name</label>
                                        <input type="text" class="form-control" id="campaignName" name="name" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="campaignDescription" class="form-label">Description</label>
                                        <textarea class="form-control" id="campaignDescription" name="description" rows="3"></textarea>
                                    </div>

                                    <div class="mb-3">
                                        <label for="startDate" class="form-label">Start Date</label>
                                        <input type="date" class="form-control" id="startDate" name="start_date" 
                                               required pattern="\d{4}-\d{2}-\d{2}">
                                        <small class="form-text text-muted">Format: YYYY-MM-DD</small>
                                    </div>
                                    <div class="mb-3">
                                        <label for="endDate" class="form-label">End Date</label>
                                        <input type="date" class="form-control" id="endDate" name="end_date" 
                                               required pattern="\d{4}-\d{2}-\d{2}">
                                        <small class="form-text text-muted">Format: YYYY-MM-DD</small>
                                    </div>
                                    <div class="mb-3 form-check">
                                        <input type="checkbox" class="form-check-input" id="campaignActive" name="is_active" checked>
                                        <label class="form-check-label" for="campaignActive">Active</label>
                                    </div>
                                    <button type="submit" class="btn btn-success">Create Campaign</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- History Tab -->
            <div class="tab-pane fade show active" id="history" role="tabpanel">
                <!-- Coupon History -->
                <div class="card mb-4">
                    <div class="card-header">
                        <h3>Coupon History</h3>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Code</th>
                                        <th>Type</th>
                                        <th>Value</th>
                                        <th>Expires</th>
                                        <th>Usage Limit</th>
                                        <th>Campaign</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="coupon" items="${coupons}">
                                        <tr>
                                            <td>${coupon.code}</td>
                                            <td>${coupon.discountType}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${coupon.discountType == 'PERCENT'}">
                                                        ${coupon.discountValue}%
                                                    </c:when>
                                                    <c:otherwise>
                                                        $${coupon.discountValue}
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatDate value="${coupon.expirationDate}" pattern="MMM dd, yyyy"/></td>
                                            <td>${coupon.usageLimit == null ? 'Unlimited' : coupon.usageLimit}</td>
                                            <td>
                                                <c:forEach var="campaign" items="${campaigns}">
                                                    <c:if test="${campaign.campaignId == coupon.campaignId}">
                                                        ${campaign.name}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <span class="badge ${coupon.isActive ? 'bg-success' : 'bg-secondary'}">
                                                    ${coupon.isActive ? 'Active' : 'Inactive'}
                                                </span>
                                            </td>
                                            <td>
                                                <button class="btn btn-sm btn-warning edit-coupon" data-id="${coupon.couponId}">Edit</button>
                                                <button class="btn btn-sm btn-danger delete-coupon" data-id="${coupon.couponId}">Delete</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <!-- Campaign History -->
                <div class="card">
                    <div class="card-header">
                        <h3>Campaign History</h3>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Period</th>
                                        <th>Status</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="campaign" items="${campaigns}">
                                        <tr>
                                            <td>${campaign.name}</td>
                                            <td>${campaign.description}</td>
                                            <td>
                                                <fmt:formatDate value="${campaign.startDate}" pattern="MMM dd, yyyy"/> - 
                                                <fmt:formatDate value="${campaign.endDate}" pattern="MMM dd, yyyy"/>
                                            </td>
                                            <td>
                                                <span class="badge ${campaign.isActive ? 'bg-success' : 'bg-secondary'}">
                                                    ${campaign.isActive ? 'Active' : 'Inactive'}
                                                </span>
                                            </td>
                                            <td>
                                                <button class="btn btn-sm btn-warning edit-campaign" data-id="${campaign.campaignId}">Edit</button>
                                                <button class="btn btn-sm btn-danger delete-campaign" data-id="${campaign.campaignId}">Delete</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Edit Coupon Modal -->
    <!-- Edit Coupon Modal -->
    <div class="modal fade" id="editCouponModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Edit Coupon</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editCouponForm" action="coupon" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="coupon_id" id="editCouponId">

                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="editCouponCode" class="form-label">Coupon Code</label>
                                <input type="text" class="form-control" id="editCouponCode" name="code" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="editDiscountType" class="form-label">Discount Type</label>
                                <select class="form-select" id="editDiscountType" name="discount_type" required>
                                    <option value="PERCENT">Percentage</option>
                                    <option value="FIXED">Fixed Amount</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="editDiscountValue" class="form-label">Discount Value</label>
                                <input type="number" step="0.01" class="form-control" id="editDiscountValue" 
                                       name="discount_value" required min="0.01">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="editExpirationDate" class="form-label">Expiration Date</label>
                                <input type="date" class="form-control" id="editExpirationDate" 
                                       name="expiration_date" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="editUsageLimit" class="form-label">Usage Limit</label>
                                <input type="number" class="form-control" id="editUsageLimit" name="usage_limit">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="editCampaignId" class="form-label">Campaign</label>
                                <select class="form-select" id="editCampaignId" name="campaign_id">
                                    <option value="">-- No Campaign --</option>
                                    <c:forEach var="campaign" items="${campaigns}">
                                        <option value="${campaign.campaignId}">${campaign.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="editProductId" class="form-label">Apply to Product</label>
                                <select class="form-select" id="editProductId" name="product_id">
                                    <option value="">-- No Specific Product --</option>
                                    <c:forEach var="product" items="${products}">
                                        <option value="${product.id}">${product.name} ($${product.price})</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="editCouponActive" name="is_active">
                            <label class="form-check-label" for="editCouponActive">Active</label>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Edit Campaign Modal -->
    <!-- Edit Campaign Modal -->
    <div class="modal fade" id="editCampaignModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Edit Campaign</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editCampaignForm" action="campaign" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="campaign_id" id="editCampaignId2">

                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="editCampaignName" class="form-label">Campaign Name</label>
                            <input type="text" class="form-control" id="editCampaignName" name="name" required>
                        </div>

                        <div class="mb-3">
                            <label for="editCampaignDescription" class="form-label">Description</label>
                            <textarea class="form-control" id="editCampaignDescription" name="description" rows="3"></textarea>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="editStartDate" class="form-label">Start Date</label>
                                <input type="date" class="form-control" id="editStartDate" name="start_date" required>
                            </div>
                            <div class="col-md-6">
                                <label for="editEndDate" class="form-label">End Date</label>
                                <input type="date" class="form-control" id="editEndDate" name="end_date" required>
                            </div>
                        </div>

                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="editCampaignActive" name="is_active">
                            <label class="form-check-label" for="editCampaignActive">Active</label>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
            
            
            
        </div>

        <div class="main-content mt-5">
          
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

  
  <script>
    $(document).ready(function() {
        // Handle edit coupon button click
        $('.edit-coupon').click(function() {
            const couponId = $(this).data('id');
            $.get('coupon?action=get&coupon_id=' + couponId, function(data) {
                // Populate the edit form
                $('#editCouponId').val(data.couponId);
                $('#editCouponCode').val(data.code);
                $('#editDiscountType').val(data.discountType);
                $('#editDiscountValue').val(data.discountValue);
                $('#editExpirationDate').val(data.expirationDate.split(' ')[0]);
                $('#editUsageLimit').val(data.usageLimit);
                $('#editCouponActive').prop('checked', data.isActive);
                $('#editCampaignId').val(data.campaignId);
                $('#editProductId').val(data.productId);

                // Show the modal
                $('#editCouponModal').modal('show');
            }).fail(function() {
                alert('Failed to load coupon data');
            });
        });

        // Handle delete coupon button click
        $('.delete-coupon').click(function() {
            if (confirm('Are you sure you want to delete this coupon?')) {
                const couponId = $(this).data('id');
                $.post('coupon', {
                    action: 'delete',
                    coupon_id: couponId
                }, function() {
                    location.reload(); // Refresh the page
                }).fail(function() {
                    alert('Failed to delete coupon');
                });
            }
        });

        // Handle edit campaign button click
        $('.edit-campaign').click(function() {
            const campaignId = $(this).data('id');
            console.log(campaignId);
            $.get('campaign?action=get&campaign_id=' + campaignId, function(data) {
                // Populate the edit form
                $('#editCampaignId2').val(data.campaignId);
                $('#editCampaignName').val(data.name);
                $('#editCampaignDescription').val(data.description);
                $('#editStartDate').val(data.startDate.split(' ')[0]);
                $('#editEndDate').val(data.endDate.split(' ')[0]);
                $('#editCampaignActive').prop('checked', data.isActive);

                // Show the modal
                $('#editCampaignModal').modal('show');
            }).fail(function() {
                alert('Failed to load campaign data');
            });
        });

        // Handle delete campaign button click
        $('.delete-campaign').click(function() {
            if (confirm('Are you sure you want to delete this campaign?')) {
                const campaignId = $(this).data('id');
                $.post('campaign', {
                    action: 'delete',
                    campaign_id: campaignId
                }, function() {
                    location.reload(); // Refresh the page
                }).fail(function() {
                    alert('Failed to delete campaign');
                });
            }
        });
    });
    </script>
  
    
    
  </body>
</html>