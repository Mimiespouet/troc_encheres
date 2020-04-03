<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Afficher le détail d'un enchère</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
			<%@ include file="entete.html"%>
		<p>${success}</p>
		<c:if test="${error != null}">
					<div class="col-lg-6 col-sm-12">
						<div class="alert alert-danger" role="alert">
							<p>${errorLogin}</p>
						</div>
					</div>
		</c:if>	
		<div class="col-lg-6 col-sm-12">
			<c:choose>
				<c:when test="${enchere.utilisateur.pseudo != null}">
					<h3 class="my-4 col-lg-12 col-sm-12">${enchere.utilisateur.pseudo} a remporté l'enchère</h3>
				</c:when>
				<c:otherwise>
					<h3 class="my-4 col-lg-12 col-sm-12">Enchère terminée. Personne n'a enchérit sur votre article</h3>
				</c:otherwise>
			</c:choose>	
				<p>${error}</p>
	
	                <h3>${enchere.article.nomArticle}</h3>
					<br>
	
					<p>Meilleure offre : ${enchere.montantEnchere} pts par ${enchere.article.acheteur.pseudo}</p>
					<br>
					
					<p>Mise à prix : ${enchere.article.prixInitial} pts</p>
					<br>
					
					<p>Date de fin de l'enchère : ${enchere.article.dateFinEncheres}</p>
					<br>
					
					<p>Adresse de retrait :</p>

					<c:choose>
						<c:when test="${enchere.article.retrait.rue != null}">
							<p>${enchere.article.retrait.rue}</p>
							<p>${enchere.article.retrait.codePostal} ${enchere.article.retrait.ville}</p>
						</c:when>
						<c:otherwise>
							<p>${enchere.article.vendeur.rue}</p>
							<p>${enchere.article.vendeur.codePostal} ${enchere.article.vendeur.ville}</p>
						</c:otherwise>
					</c:choose>

					<p>Vendeur de l'article : ${enchere.article.vendeur.pseudo}</p> 
					<br>

				<div class="col-lg-6 col-sm-12">
					<a id="back" href="accueil" class="btn btn-primary">Retour</a>
				</div>
								
				<c:if test="${enchere.utilisateur.pseudo != null}">
					<div class="col-lg-6 col-sm-12">
					<a href="mailto:${enchere.utilisateur.email}">Contacter ${enchere.utilisateur.pseudo}</a>
				</div>
				
				<div class="col-lg-6 col-sm-12">
					<a id="done" href="accueil" class="btn btn-primary">Retrait effectué</a>
				</div>
				</c:if>
			
		</div>
			
		<%@ include file="piedDePage.html"%>
	</div>
	<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
</body>
</html>