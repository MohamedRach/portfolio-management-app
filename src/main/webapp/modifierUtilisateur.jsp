<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Modification d'un utilisateur</title>

</head>
<body>

<div class="container">
    <div class="col-md-8 offset-2 col-xm-8 col-sm-8 ">
        <div class="card my-5">
            <div class="card-header">
                <span>Modification d'un utilisateur</span>

            </div>
            <div class="card-body">
                <form action="modifier-utilisateur" method="post">
                    <div class="form-group">
                        <label class="label-control">ID :</label>
                        <input class="form-control" name="id" type="hidden" value="${user.id }">
                        <label class="label-control">${user.id }</label>
                    </div>
                    <div class="form-group">
                        <label class="label-control">Nom :</label>
                        <input class="form-control" name="nom" placeholder="Entrer le nom" value="${user.nom}">
                    </div>
                    <div class="form-group">
                        <label class="label-control">Prenom :</label>
                        <input class="form-control" name="prenom" placeholder="Entrer le prénom" value="${user.prenom}">
                    </div>
                    <div class="form-group">
                        <label class="label-control">Email :</label>
                        <input class="form-control" name="email" placeholder="Entrer l'email" value="${user.email}">
                    </div>
                    <!-- Add other user properties as needed -->
                    <button class="btn btn-warning btn-block" type="submit">Enregistrer</button>
                    <button class="btn btn-danger float-right" type="button" onclick="cancelAction()">Annuler</button>

                    <script>
                        function cancelAction() {
                            window.location.href = 'hello-servlet';
                        }
                    </script>


                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
