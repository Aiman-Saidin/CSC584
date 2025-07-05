package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("    <title>FoodMart</title>\r\n");
      out.write("\r\n");
      out.write("    <!-- Bootstrap core CSS -->\r\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("    <!-- Additional CSS Files -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"assets/css/fontawesome.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"assets/css/templatemo-villa-agency.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"assets/css/owl.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"assets/css/animate.css\">\r\n");
      out.write("    <link rel=\"stylesheet\"href=\"https://unpkg.com/swiper@7/swiper-bundle.min.css\"/>\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("  <!-- ***** Preloader Start ***** -->\r\n");
      out.write("  <div id=\"js-preloader\" class=\"js-preloader\">\r\n");
      out.write("    <div class=\"preloader-inner\">\r\n");
      out.write("      <span class=\"dot\"></span>\r\n");
      out.write("      <div class=\"dots\">\r\n");
      out.write("        <span></span>\r\n");
      out.write("        <span></span>\r\n");
      out.write("        <span></span>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("  <!-- ***** Preloader End ***** -->\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("  <!-- ***** Header Area Start ***** -->\r\n");
      out.write("  <header class=\"header-area header-sticky\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("            <div class=\"col-12\">\r\n");
      out.write("                <nav class=\"main-nav\">\r\n");
      out.write("                    <!-- ***** Logo Start ***** -->\r\n");
      out.write("                    <a href=\"index.jsp\" class=\"logo\">\r\n");
      out.write("                        <h1>FoodMart</h1>\r\n");
      out.write("                    </a>\r\n");
      out.write("                    <!-- ***** Logo End ***** -->\r\n");
      out.write("                    <!-- ***** Menu Start ***** -->\r\n");
      out.write("                    <ul class=\"nav\">\r\n");
      out.write("                     <li><a href=\"index.jsp\" class=\"active\">Home</a></li>\r\n");
      out.write("                     <li><a href=\"CouponManager.jsp\">Coupon Manager</a></li>\r\n");
      out.write("                      <li><a href=\"order.jsp\">Order</a></li>\r\n");
      out.write("                      <li><a href=\"campaign.jsp\">Campaign</a></li>\r\n");
      out.write("                      <li><a href=\"login.jsp\">Login</a></li>\r\n");
      out.write("                      <li><a href=\"#\"><i class=\"fa fa-calendar\"></i> Get your coupon</a></li>\r\n");
      out.write("                  </ul>   \r\n");
      out.write("                    <a class='menu-trigger'>\r\n");
      out.write("                        <span>Menu</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                    <!-- ***** Menu End ***** -->\r\n");
      out.write("                </nav>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </header>\r\n");
      out.write("  <!-- ***** Header Area End ***** -->\r\n");
      out.write("\r\n");
      out.write("  <div class=\"main-banner\">\r\n");
      out.write("    <div class=\"owl-carousel owl-banner\">\r\n");
      out.write("      <div class=\"item item-1\">\r\n");
      out.write("        <div class=\"header-text\">\r\n");
      out.write("          <span class=\"category\">Baling, <em>Malaysia</em></span>\r\n");
      out.write("          <h2>Hurry!<br>Let's Shop until you drop</h2>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("\r\n");
      out.write("  <div class=\"featured section\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("      <div class=\"row\">\r\n");
      out.write("        <div class=\"col-lg-4\">\r\n");
      out.write("          <div class=\"left-image\">\r\n");
      out.write("            <img src=\"assets/images/mart.png\" alt=\"\">\r\n");
      out.write("            <a href=\"order.jsp\"><img src=\"assets/images/featured-icon.png\" alt=\"\" style=\"max-width: 60px; padding: 0px;\"></a>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-lg-5\">\r\n");
      out.write("          <div class=\"section-heading\">\r\n");
      out.write("            <h6>| Featured</h6>\r\n");
      out.write("            <h2>Best Shop &amp; for your groceries</h2>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"accordion\" id=\"accordionExample\">\r\n");
      out.write("            <div class=\"accordion-item\">\r\n");
      out.write("              <h2 class=\"accordion-header\" id=\"headingOne\">\r\n");
      out.write("                <button class=\"accordion-button\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collapseOne\" aria-expanded=\"true\" aria-controls=\"collapseOne\">\r\n");
      out.write("                  Best useful links ?\r\n");
      out.write("                </button>\r\n");
      out.write("              </h2>\r\n");
      out.write("              <div id=\"collapseOne\" class=\"accordion-collapse collapse show\" aria-labelledby=\"headingOne\" data-bs-parent=\"#accordionExample\">\r\n");
      out.write("                <div class=\"accordion-body\">\r\n");
      out.write("                Discover why <strong>FoodMart</strong> is your go-to destination for everyday groceries. From fresh produce to pantry staples, we offer everything you need in one place — always affordable and always fresh.</div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"accordion-item\">\r\n");
      out.write("              <h2 class=\"accordion-header\" id=\"headingTwo\">\r\n");
      out.write("                <button class=\"accordion-button collapsed\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collapseTwo\" aria-expanded=\"false\" aria-controls=\"collapseTwo\">\r\n");
      out.write("                  How does this work ?\r\n");
      out.write("                </button>\r\n");
      out.write("              </h2>\r\n");
      out.write("              <div id=\"collapseTwo\" class=\"accordion-collapse collapse\" aria-labelledby=\"headingTwo\" data-bs-parent=\"#accordionExample\">\r\n");
      out.write("                <div class=\"accordion-body\">\r\n");
      out.write("                 Simply walk in, browse our organized aisles, and find your favorite items with ease. Our friendly staff is here to help you every step of the way.\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"accordion-item\">\r\n");
      out.write("              <h2 class=\"accordion-header\" id=\"headingThree\">\r\n");
      out.write("                <button class=\"accordion-button collapsed\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#collapseThree\" aria-expanded=\"false\" aria-controls=\"collapseThree\">\r\n");
      out.write("                  Why is FoodMart the best ?\r\n");
      out.write("                </button>\r\n");
      out.write("              </h2>\r\n");
      out.write("              <div id=\"collapseThree\" class=\"accordion-collapse collapse\" aria-labelledby=\"headingThree\" data-bs-parent=\"#accordionExample\">\r\n");
      out.write("                <div class=\"accordion-body\">\r\n");
      out.write("                 FoodMart stands out for its commitment to quality, customer satisfaction, and community. We bring you the best products, top-notch service, and a shopping experience that makes you feel at home.\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("            </div>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"col-lg-3\">\r\n");
      out.write("          <div class=\"info-table\">\r\n");
      out.write("            <ul>\r\n");
      out.write("              <li style=\"display: flex; align-items: center;\">\r\n");
      out.write("                <img src=\"assets/images/store.png\" alt=\"Store Icon\" style=\"max-width: 52px;\">\r\n");
      out.write("                  <h4>\r\n");
      out.write("                  Retail Unit<br>\r\n");
      out.write("                  <span>Fully Equipped Store</span>\r\n");
      out.write("                  </h4>\r\n");
      out.write("              </li>\r\n");
      out.write("              <li style=\"display: flex; align-items: center;\">\r\n");
      out.write("                <img src=\"assets/images/cart.jpg\" alt=\"Cart Icon\" style=\"max-width: 52px;\">\r\n");
      out.write("                  <div>\r\n");
      out.write("                    <h4 style=\"margin: 0; line-height: 1.2;\">Retail Ready</h4>\r\n");
      out.write("                      <span style=\"color: #888; font-size: 14px;\">Contract Available</span>\r\n");
      out.write("                  </div>\r\n");
      out.write("              </li>\r\n");
      out.write("              <li>\r\n");
      out.write("                <img src=\"assets/images/grocery.jpg\" alt=\"\" style=\"max-width: 52px;\">\r\n");
      out.write("                <h4>Groceries<br><span>Available Supplies</span></h4>\r\n");
      out.write("              </li>\r\n");
      out.write("              <li>\r\n");
      out.write("                <img src=\"assets/images/open.jpg\" alt=\"\" style=\"max-width: 52px;\">\r\n");
      out.write("                <h4>Open<br><span>24/7 Open</span></h4>\r\n");
      out.write("              </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("\r\n");
      out.write("  <footer>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("      <div class=\"col-lg-12\">\r\n");
      out.write("        <p>FoodMart</p>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </footer>\r\n");
      out.write("\r\n");
      out.write("  <!-- Scripts -->\r\n");
      out.write("  <!-- Bootstrap core JavaScript -->\r\n");
      out.write("  <script src=\"vendor/jquery/jquery.min.js\"></script>\r\n");
      out.write("  <script src=\"vendor/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("  <script src=\"assets/js/isotope.min.js\"></script>\r\n");
      out.write("  <script src=\"assets/js/owl-carousel.js\"></script>\r\n");
      out.write("  <script src=\"assets/js/counter.js\"></script>\r\n");
      out.write("  <script src=\"assets/js/custom.js\"></script>\r\n");
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
