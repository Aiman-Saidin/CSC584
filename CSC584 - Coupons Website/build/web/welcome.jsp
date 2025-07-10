<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.*,javax.servlet.*"%>
<%
  HttpSession session = request.getSession(false);
  if (session == null || session.getAttribute("username") == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Welcome</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <div class="container mt-5">
    <h1>Welcome, <%= session.getAttribute("username") %>!</h1>
    <p>You are now logged in.</p>
    <a href="logout.jsp" class="btn btn-danger">Logout</a>
  </div>
</body>
</html>
