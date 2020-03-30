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
	                <label for="articleName">Nom de l'article :</label>
	                <p>${articleVendu.nomArticle}</p>
					<br>
	
	                <label for="description">Description :</label>
	                <p>${articleVendu.description}</p>
					<br>
					
					<label for="categorie">Catégorie :</label>
	                <p>${categorie.libelle}</p> <!-- articleVendu.categorie.libelle-->
					<br>
	
					<label for="bestOffer">Meilleure offre :</label>
	             	<p>${enchere.montantEnchere}</p>
					<br>
					
					<label for="initialPrice">Mise à prix :</label>
	                <p>${articleVendu.prixInitial}</p>
					<br>
					
					<label for="startDate">Date de début de l'enchère :</label>
	                <p>${articleVendu.dateDebutEnchere}</p>
					<br>
					
					<label for="endDate">Date de fin de l'enchère :</label>
	                <p>${articleVendu.dateFinEnchere}</p>
					<br>
					
					<label for="adress">Adresse de retrait :</label>
	                
	                 <!-- articleVendu.retrait-->
	                <p>${retrait.rue}</p>
					<br>
					<p>${retrait.codepostal}</p>
					<p>${retrait.ville}</p>
					<br>
					
					<label for="seller">Vendeur de l'article :</label>
	                <p>${utilisateur.vendeur}</p> <!-- articleVendu.vendeur.pseudo -->
					<br>
					
<%-- 
	<!--comparer avec l'heure actuelle, si c'est après l'heure actuelle ça n'apparait plus-->
		<c:if test=${LocalDateTime.now().isAfter(article.dateFinEnchere)}> 
		
			<form action="" method="post">
				<label for="proposition">Ma proposition :</label>
	                <input type="number"id="proposition" name="proposition" value="${enchere.montantEnchere + 1}" min="${enchere.montantEnchere + 1}">
					<br> 
			</form>
				<div class="col-lg-6 col-sm-12">
					<a id="validerMaProposition" href="" class="btn btn-primary">Enchérir</a>
				</div> 
		
		</c:if>	
--%>
				
				<div class="col-lg-6 col-sm-12">
					<a id="retour" href="accueil" class="btn btn-primary">Retour</a>
				</div>
		
		</div>
			
	</div>	
	
	<%@ include file="piedDePage.html" %>
</body>
</html>