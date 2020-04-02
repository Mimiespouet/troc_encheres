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
			
			<h3 class="my-4 col-lg-12 col-sm-12">${enchere.article.acheteur.pseudo} a remporté l'enchère</h3>
			
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
				
				<div class="col-lg-6 col-sm-12">
					<a href="mailto:EMAILADDRESS">Contacter ${enchere.article.acheteur.pseudo}</a>
				</div>
				
				<div class="col-lg-6 col-sm-12">
					<a id="done" href="accueil" class="btn btn-primary">Retrait effectué</a>
				</div>
		
		</div>
			
	</div>	
	
	<%@ include file="piedDePage.html" %>
</body>
</html>