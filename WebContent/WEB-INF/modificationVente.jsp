<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification Vente Article</title>
<!-- Bootstrap core CSS -->
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
	
			<h3 class="my-4 col-lg-12 col-sm-12">Modification de la vente</h3>
	
	 		<form action="modifierVente" method="post">
          
	          <div>
					<label for="nom">Article :</label>
					<input type="text" id="nom" name="nom" size="30" value="${articleVendu.nomArticle}" required>
				</div>

				<div>
					<label for="categorie">Catégorie :</label> 
					<c:forEach var="categorie" items="${listeCategorie}">
  						<option value="${categorie}">${categorie.libelle}</option>
					</c:forEach>
				</div>
				<div>
					<label for="description">Description :</label><br>
					<textarea id="description" name="description" rows="5" cols="33" maxlength="100" value="${articleVendu.description}" required></textarea>
				</div>

				<div>
					<label for="photo">Photo de l'article</label> <input
						type="file" id="photo" name="photo"
						accept="image/png, image/jpeg">
				</div>

				<div>
					<label for="prixInitial">Prix initial :</label> <input
						type="number" id="prixInitial" name="prixInitial" min="1"
						max="10000" value="${articleVendu.prixInitial}" required>
				</div>
					<div>
						<label for="dateDebut">Début de l'enchère :</label> <input
							id="dateDebut" type="datetime-local" name="dateDebut"
							value="${articleVendu.dateDebutEncheres }"required>
					</div>


					<div>
						<label for="dateFin">Fin de l'enchère :</label> <input
							id="dateFin" type="datetime-local" name="dateFin"
							value="${articleVendu.dateFinEncheres}" required>
					</div>
					<label for="adresse">Retrait :</label> 
					<c:choose>
						<c:when test="${ retrait != null }">
							<div>
								<input
									type="text"
									value="${retrait.rue}  ${retrait.codePostal}  ${retrait.ville}"
									id="adresse" name="adresse"  size="40">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<input
									type="text"
									value="${vendeur.rue}  ${vendeur.codePostal}  ${vendeur.ville}"
									id="adresse" name="adresse"  size="40">
							</div>
						</c:otherwise>
					</c:choose>
					
			</form>
			<div class="col-lg-6 col-sm-12">
						<a id="saveModif" href='modifierVente' class="btn btn-primary">Enregistrer</a>
						<a id="annulerVente" href="accueil" class="btn btn-primary">Annuler</a>
					</div>
		</div>

	</div>

	<%@ include file="piedDePage.html" %>
</body>
</html>