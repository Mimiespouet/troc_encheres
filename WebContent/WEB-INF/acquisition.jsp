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
			
			<h3 class="my-4 col-lg-12 col-sm-12">Vous avez remporté l'enchère</h3>
			
			
	                <p>Nom de l'article : ${articleVendu.nomArticle}</p>
					<br>
	
	
					<p>Meilleure offre :</p>
	             	<p>${enchere.montantEnchere}</p>
					<br>
					
					<p>Mise à prix :</p>
	                <p>${articleVendu.prixInitial}</p>
					<br>
					
					
					
					<p>Retrait :</p> 
					   <c:choose>
					        <c:when test="${ retrait != null }">
					             <div>
					                 <p>${retrait.rue}</p>
					                 <p>${retrait.codePostal} ${retrait.ville}</p>
					             </div>
					        </c:when>
					        <c:otherwise>
					             <div>
					                 <p>${vendeur.rue}</p>
					                 <p>${vendeur.codePostal}  ${vendeur.ville}</p>
					             </div>
					        </c:otherwise>
					      </c:choose>
									
					<p>Vendeur de l'article :</p>
	                <p>${utilisateur.vendeur}</p> <!-- articleVendu.vendeur.pseudo -->
	                <p>Tel :</p>
	                <p>${articleVendu.vendeur.telephone}</p> <!-- articleVendu.vendeur.telephone -->
					<br>
					

				
				<div class="col-lg-6 col-sm-12">
					<a id="retraitOK" href="accueil" class="btn btn-primary">Retrait effectué</a>
				</div>
		
		</div>
			
	</div>	
	
	<%@ include file="piedDePage.html" %>
</body>
</html>