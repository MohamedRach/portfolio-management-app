<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>

</h1>

<script>
    const stockData = '<%=request.getAttribute("data")%>';
    console.log(stockData)
</script>
</body>
</html>