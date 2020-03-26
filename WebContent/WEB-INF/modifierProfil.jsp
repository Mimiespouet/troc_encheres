<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer compte</title>
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
	
			<h3 class="my-4 col-lg-12 col-sm-12">Mon Profil</h3>
	
	 
          
	            <form action="" method="post">
	   
	                
	                <label for="pseudo">Pseudo :</label>
	                <input type="text" id="pseudo" name="pseudo" value="${utilisateur.pseudo}">
					<br>
	
	                <label for="name">Nom :</label>
	                <input type="text" id="name" name="name" value="${utilisateur.nom}">
					<br>
	
					<label for="firstname">Prénom :</label>
	             	<input type="text" id="firstname" name="firstname" value="${utilisateur.prenom}">
					<br>
					
					<label for="mail">Email :</label>
	                <input type="email" id="mail" name="mail" value="${utilisateur.email}">
					<br>
					
					<label for="tel">Téléphone :</label>
	                <input type="tel" id="tel" name="tel" value="${utilisateur.telephone}">
					<br>
					
					<label for="address">Rue :</label>
	                <input type="text" id="address" name="address" value="${utilisateur.rue}">
					<br>
					
					<label for="cpo">Code Postal :</label>
	                <input type="text"id="cpo" name="cpo" value="${utilisateur.codePostal}">
					<br>
					
					<label for="city">Ville :</label>
	                <input type="text" id="city" name="city" value="${utilisateur.ville}">
					<br>
					
					
					<label for="password">Mot de passe :</label>
	                <input type="password" id="password" name="password" value="${utilisateur.motDePasse}">
					<br>
					
					<label for="password">Confirmation :</label>
	                <input type="password" id="password" name="password" value="${utilisateur.motDePasse}">
					<br>
					
					<label for="credit">Credit :</label>
	                <p>${utilisateur.credit}</p>
					<br>
					
					
				  </form>
				</div>	
					
				<div class="col-lg-6 col-sm-12">	
					<form>
						<button id="save" name="save">
		            		Enregistrer
		            	</button>
					</form>
					
	            </div>
	            <div class="col-lg-6 col-sm-12">
	            	<form>
	            		<button id="delete" name="delete">
	            			Supprimer mon compte
	            		</button>
	            	</form>
	            </div>
	            	
	            <div class="col-lg-6 col-sm-12">
						
					<a id="previous" href="profil" class="btn btn-primary">Retour</a>
	       		</div>
	            	
	      
				
	            
	          
        	
	 </div>
	<%@ include file="piedDePage.html" %>
	
</body>
</html>