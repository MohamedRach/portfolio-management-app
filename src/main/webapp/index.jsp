<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
   <style>
       *{
           margin: 0;
           padding: 0;
       }
       .hhh{
           margin-top: 200px;
       }
   </style>
</head>
<body>
<h1 class="hhh"><c:out value="sadik mohamed" /></h1>
<side-bar menu="user"></side-bar>

<script src="js/sideBare.js"></script>
</body>
</html>