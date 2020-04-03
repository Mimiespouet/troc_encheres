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

<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
</head>
<body>

	<div class="container-fluid">
			<%@ include file="entete.html"%>

		<div class="col-lg-6 col-sm-12">
		<p>${success}</p>
		<c:if test="${error != null}">
			<div class="col-lg-6 col-sm-12">
				<div class="alert alert-danger" role="alert">
					<p>${errorLogin}</p>
				</div>
			</div>
		</c:if>
			<form action="vendreArticle" method="post">
				<div>
					<label for="nom">Article :</label>
					<input type="text" placeholder="Entrer le nom de l'article" id="nom" name="nom" size="30" required>
				</div>
				<div>
					<label for="categorie">Catégorie :</label> 
					<select name="noCategorie">
						<c:forEach var="categorie" items="${listeCategorie}">
							<c:choose>
								<c:when
									test="${articleVendu.categorie.noCategorie == categorie.noCategorie}">
									<option value="${categorie.noCategorie}" selected>${categorie.libelle}</option>
								</c:when>
								<c:otherwise>
									<option value="${categorie.noCategorie}">${categorie.libelle}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
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
						<label for="adresse">Retrait :</label> 
						<input type="text" placeholder="${vendeur.rue}" id="rue" name="rue"  size="40">
						<input type="text" placeholder="${vendeur.codePostal}" id="codePostal" name="codePostal"  size="40">
						<input type="text" placeholder="${vendeur.ville}" id="ville" name="ville"  size="40">
					</div>

					<div>
					<button id="enregistrer" name="enregistrer">Enregistrer</button>
					
						<a id="annulerVente"
							href="accueil" class="btn btn-primary">Annuler</a>
					</div>
			</form>
		</div>

		<%@ include file="piedDePage.html" %>
	</div>	

</body>
</html>