<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/4-col-portfolio.css"
	rel="stylesheet">
</head>
<body>

	<div id="container">
		<div class="col-lg-6 col-sm-12">
			<%@ include file="entete.html"%>
		</div>

		<div class="col-lg-6 col-sm-12">
		<p>${success}</p>
		<p>${error}</p>
			<form action="vendreArticle" method="post">
				<div>
					<label for="nom">Article :</label>
					<input type="text" placeholder="Entrer le nom de l'article" id="nom" name="nom" size="30" required>
				</div>

				<div>
					<label for="categorie">Catégorie :</label> 
					<select id="categorie" name="categorie">				      				    	
					        <option value="Informatique">Informatique</option>
					        <option value="Livres">Livres</option>
					        <option value="Nourriture">Nourriture</option>
					        <option value="Vêtements">Vêtements</option>
					</select>
				</div>

				<div>
					<label for="description">Description :</label><br>
					<textarea id="description" name="description" rows="5" cols="33" maxlength="100" placeholder="Entrer la description de l'article" required></textarea>
				</div>

				<div>
					<label for="photo">Photo de l'article</label> <input
						type="file" id="photo" name="photo"
						accept="image/png, image/jpeg">
				</div>

				<div>
					<label for="prixInitial">Prix initial :</label> <input
						type="number" id="prixInitial" name="prixInitial" min="1"
						max="10000" required>
				</div>
					<div>
						<label for="dateDebut">Début de l'enchère :</label> <input
							id="dateDebut" type="datetime-local" name="dateDebut"
							required>
					</div>


					<div>
						<label for="dateFin">Fin de l'enchère :</label> <input
							id="dateFin" type="datetime-local" name="dateFin"
							required>
					</div>

					<div>
						<label for="adresse">Retrait :</label> <input
							type="text"
							placeholder="Entrer l'adresse de retrait de l'article"
							id="adresse" name="adresse"  size="40">
					</div>

					<div>
					<button id="enregistrer" name="enregistrer">Enregistrer</button>
					
						<a id="annulerVente"
							href="accueil" class="btn btn-primary">Annuler</a>
					</div>
			</form>
		</div>

	</div>

	<%@ include file="piedDePage.html"%>

</body>
</html>