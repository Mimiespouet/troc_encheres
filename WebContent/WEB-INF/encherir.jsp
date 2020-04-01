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
			
			<h3 class="my-4 col-lg-12 col-sm-12">Détail vente</h3>
			
				<p>${error}</p>
			
	           
	            <!-- changer les label en paragraphe car ce n'est pas un formulaire -->
	                <p>Nom de l'article :</p>
	                <p>${articleVendu.nomArticle}</p>
					<br>
	
	                <p>Description :</p>
	                <p>${articleVendu.description}</p>
					<br>
					
					<p>Catégorie :</p>
	                <p>${categorie.libelle}</p> <!-- articleVendu.categorie.libelle-->
					<br>
	
					<p>Meilleure offre :</p>
	             	<p>${enchere.montantEnchere}</p>
					<br>
					
					<p>Mise à prix :</p>
	                <p>${articleVendu.prixInitial}</p>
					<br>
					
					<p>Date de début de l'enchère :</p>
	                <p>${articleVendu.dateDebutEnchere}</p>
					<br>
					
					<p>Date de fin de l'enchère :</p>
	                <p>${articleVendu.dateFinEnchere}</p>
					<br>
					
					<label for="adress">Adresse de retrait :</label>
	                
	                 <!-- articleVendu.retrait-->
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
					<br>
					

	<!--comparer avec l'heure actuelle, si c'est après l'heure actuelle ça n'apparait plus-->
		<c:if test="${LocalDateTime.now().isAfter(article.dateFinEnchere)}"> 
		
			<form action="" method="post">
				<label for="proposition">Ma proposition :</label>
	                <input type="number"id="proposition" name="proposition" value="${enchere.montantEnchere + 1}" min="${enchere.montantEnchere + 1}">
					<br> 
			</form>
				<div class="col-lg-6 col-sm-12">
					<a id="validerMaProposition" href="" class="btn btn-primary">Enchérir</a>
				</div> 
		
		</c:if>	

				
				<div class="col-lg-6 col-sm-12">
					<a id="retour" href="accueil" class="btn btn-primary">Retour</a>
				</div>
		
		</div>
			
	</div>	
	
	<%@ include file="piedDePage.html" %>
</body>
</html>