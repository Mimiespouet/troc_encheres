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
			
			<h3 class="my-4 col-lg-12 col-sm-12">${articleVendu.nomArticle}</h3>
			
				<p>${error}</p>
	
	                <p>Description : ${enchere.article.description}</p>
					<br>
	
					<p>Meilleure offre : ${enchere.montantEnchere}</p>
					<br>
					
					<p>Mise à prix : ${enchere.article.prixInitial}</p>
					<br>
					
					<p>Date de fin de l'enchère : ${enchere.article.dateFinEnchere}</p>
					<br>
					
					<p>Adresse de retrait :</p>
	                
	                 <!-- articleVendu.retrait-->
	                <p>${retrait.rue}</p>
					<br>
					<p>${retrait.codepostal}</p>
					<p>${retrait.ville}</p>
					<br>
					
					<p>Vendeur de l'article : ${enchere.article.utilisateur.vendeur.pseudo}</p> 
					<br>

				<div class="col-lg-6 col-sm-12">
					<a id="cancel" href="accueil" class="btn btn-primary">Annuler la vente</a>
				</div>
				
				<div class="col-lg-6 col-sm-12">
					<a id="back" href="accueil" class="btn btn-primary">Retour</a>
				</div>
		
		</div>
			
	</div>	
	
	<%@ include file="piedDePage.html" %>
</body>
</html>