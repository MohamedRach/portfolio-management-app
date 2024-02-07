<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Activity</title>
</head>
<body>
<h2>Create a New Activity</h2>

<form action="createActivity" method="post" >
    <div class="form-group">
        <label class="form-control" name="id_user" >User ID:</label>
        <input type="text" name="id_user" required><br>
    </div>
    <label class="form-control" name="desc_buy" >Description for Buy:</label>
    <input type="text" name="desc_buy" required><br>

    <label class="form-control" name="desc_sell" >Description for Sell:</label>
    <input type="text" name="desc_sell" required><br>

    <input type="submit" value="Create Activity">
</form>

<br>
<a href="${pageContext.request.contextPath}/activityList">Back to Conseillers</a>
</body>
</html>
