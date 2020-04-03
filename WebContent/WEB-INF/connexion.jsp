<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
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
		<c:if test="${error != null}">
					<div class="col-lg-6 col-sm-12">
						<div class="alert alert-danger" role="alert">
							<p>${errorLogin}</p>
						</div>
					</div>
		</c:if>
		<p>${success}</p>
		<div class="col-lg-6 col-sm-12">
			<form action="login" method="post">

				<label for="username"><b>Identifiant : </b></label>
				<c:choose>
					<c:when test="${utilisateur.pseudo != null }">
						<input type="text" id="username" name="username"
							value="${utilisateur.pseudo}" size="30" required>
						<br>
					</c:when>
					<c:otherwise>
						<input type="text" placeholder="Entrer votre identifiant"
							id="username" name="username" size="30" required>
						<br>
					</c:otherwise>
				</c:choose>
				<label for="password"><b>Mot de passe :</b></label>
				<c:choose>
					<c:when test="${utilisateur.motDePasse != null}">
						<input type="password" id="password" name="password"
							value="${utilisateur.motDePasse}" size="30" required>
						<br>
					</c:when>
					<c:otherwise>
						<input type="password" placeholder="Entrer votre mot de passe"
							id="password" name="password" size="30" required>
						<br>
					</c:otherwise>
				</c:choose>

				<div>
					<button id="connexion" name="connexion">Connexion</button>
				</div>

				<div>
					<c:choose>
						<c:when test="${utilisateur.pseudo != null}">
							<input type="checkbox" id="remember" name="remember"
								value="remember" checked>

						</c:when>
						<c:otherwise>
							<input type="checkbox" id="remember" name="remember"
								value="remember">
						</c:otherwise>
					</c:choose>
					<label for="remember"><b>Se souvenir de moi</b></label><br> <a
						id="ForgotPassword" href="">Mot de passe oublié</a>
				</div>
			</form>

			<a id="creaCompte" href="inscription" class="btn btn-primary">Créer
				un compte</a>

		</div>
		<%@ include file="piedDePage.html"%>
	</div>
	<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
</body>
</html>