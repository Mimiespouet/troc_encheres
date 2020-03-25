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
			<form action="login" method="post">
				<div>
					<label for="nomArt">Article :</label>
					<input type="text" placeholder="Entrer le nom de l'article" id="nomArt" name="nomArt" size="30" required>
				</div>

				<div>
					<label for="catArt">Catégorie :</label> 
					<select>
						<optgroup label="Loisirs">
					        <option>DVD - Films</option>
					        <option>CD - Musique</option>
					        <option>Livres</option>
					        <option>Animaux</option>
					        <option>Vélos</option>
					        <option>Sports</option>
				    	</optgroup>
				    	<optgroup label="Mode">
					        <option>Vêtements</option>
					        <option>Chaussures</option>
					        <option>Bijoux</option>
				    	</optgroup>
				    	<optgroup label="Multimédia">
					        <option>Informatique</option>
					        <option>Consoles - Jeux Vidéos</option>
					        <option>Images - Sons</option>
				    	</optgroup>
					</select>
				</div>

				<div>
					<label for="descriptArt">Description :</label><br>
					<textarea id="descriptArt" name="descriptArt" rows="5" cols="33" maxlength="100" placeholder="Entrer la description de l'article"></textarea>
				</div>

				<div>
					<label for="photoArt">Photo de l'article</label> <input
						type="file" id="photoArticle" name="photoArticle"
						accept="image/png, image/jpeg">
				</div>

				<div>
					<label for="prixInit">Prix initial :</label> <input
						type="number" id="prixArticle" name="prixArticle" min="1"
						max="10000">
				</div>
					<div>
						<label for="debutEnchere">Début de l'enchère :</label> <input
							id="dateDebEnchere" type="datetime-local" name="debutEnchere"
							required>
					</div>


					<div>
						<label for="finEnchere">Fin de l'enchère :</label> <input
							id="dateFinEnchere" type="datetime-local" name="finEnchere"
							required>
					</div>

					<div>
						<label for="adresseRetrait">Retrait :</label> <input
							type="text"
							placeholder="Entrer l'adresse de retrait de l'article"
							id="adresseRetrait" name="adresse"  size="40" required>
					</div>

					<div>
						<a id="enregistrerVente" href="validerVente"
							class="btn btn-primary">Enregistrer</a> 
						<a id="annulerVente"
							href="accueil" class="btn btn-primary">Annuler</a>
					</div>
			</form>
		</div>

	</div>

	<%@ include file="piedDePage.html"%>

</body>
</html>