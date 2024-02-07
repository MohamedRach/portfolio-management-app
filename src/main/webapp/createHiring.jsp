<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Hiring</title>
</head>
<body>

<h2>Create Hiring</h2>

<form action="createHiring" method="post">

    <!-- Autres champs du formulaire... -->

    <label for="id_conseiller">Choisir un conseiller:</label>
    <select name="id_conseiller" id="id_conseiller">
        <c:forEach var="conseiller" items="${conseillers}">
            <option value="${conseiller.id}">${conseiller.nom}</option>
        </c:forEach>
    </select>

    <!-- Autres champs du formulaire... -->

    <br>
    <input type="submit" value="Submit">
</form>

</body>
</html>
