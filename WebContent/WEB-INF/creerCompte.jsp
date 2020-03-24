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
	
		<h3 class="my-4 col-lg-12 col-sm-12">Créer un compte</h3>
	
	 
          
            <form action="" method="post">
   
                
                <label for="pseudo"><b>Pseudo : </b></label>
                <input type="text" id="pseudo" name="pseudo" required>
				<br>

                <label for="name"><b>Nom :</b></label>
                <input type="text" id="name" name="name" required>
				<br>

				<label for="firstname"><b>Prénom :</b></label>
                <input type="text" id="firstname" name="firstname" required>
				<br>
				
				<label for="mail"><b>Email :</b></label>
                <input type="email" id="mail" name="mail" required>
				<br>
				
				<label for="tel"><b>Téléphone :</b></label>
                <input type="tel" id="tel" name="tel">
				<br>
				
				<label for="address"><b>Rue :</b></label>
                <input type="text" id="address" name="address" required>
				<br>
				
				<label for="cpo"><b>Code Postal :</b></label>
                <input type="text"id="cpo" name="cpo" required>
				<br>
				
				<label for="city"><b>Ville :</b></label>
                <input type="text" id="city" name="city" required>
				<br>
				
				<label for="password"><b>Mot de passe :</b></label>
                <input type="password" id="password" name="password" required>
				<br>
				
				<label for="password"><b>Confirmation :</b></label>
                <input type="password" id="password" name="password" required>
				<br>
				
				
				
				<button id="nvCompte" name="nvCompte">
            	Créer
            	</button>

            
            	<button id="annuler" name="annuler">
            	Annuler
            	</button>
            
            </form>
        </div>
	
	<%@ include file="piedDePage.html" %>
	
</body>
</html>