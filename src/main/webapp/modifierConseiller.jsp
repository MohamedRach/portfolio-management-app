<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Modification d'un conseiller</title>

</head>
<body>

<div class="container">
    <div class="col-md-8 offset-2 col-xm-8 col-sm-8 ">
        <div class="card my-5">
            <div class="card-header">
                <span>Modification d'un conseiller</span>

            </div>
            <div class="card-body">
                <form action="modifier-conseiller" method="post">
                    <div class="form-group">
                        <label class="label-control">ID :</label>
                        <input class="form-control" name="id" type="hidden" value="${conseiller.id}">
                        <label class="label-control">${conseiller.id}</label>
                    </div>
                    <div class="form-group">
                        <label class="label-control">Nom :</label>
                        <input class="form-control" name="nom" placeholder="Entrer le nom" value="${conseiller.nom}">
                    </div>
                    <div class="form-group">
                        <label class="label-control">Prenom :</label>
                        <input class="form-control" name="prenom" placeholder="Entrer le prénom" value="${conseiller.prenom}">
                    </div>
                    <div class="form-group">
                        <label class="label-control">Email :</label>
                        <input class="form-control" name="email" placeholder="Entrer l'email" value="${conseiller.email}">
                    </div>
                    <!-- Ajouter d'autres propriétés du conseiller au besoin -->
                    <button class="btn btn-warning btn-block" type="submit">Enregistrer</button>
                    <button class="btn btn-danger float-right" type="button" onclick="cancelAction()">Annuler</button>

                    <script>
                        function cancelAction() {
                            window.location.href = 'conseiller-servlet';
                        }
                    </script>


                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

