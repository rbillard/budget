<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>

	<title>Budget</title>
    
    <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.png" />">
    
	<jsp:include page="css.jsp"></jsp:include>
	<jsp:include page="js.jsp"></jsp:include>

</head>

<body>

	<div>
	    <form name="f" action="<c:url value='/login' />" method="post" class="form-horizontal">               
	        <fieldset>
	            <legend>Budget</legend>
	
	            <c:if test="${ param.error != null }">
	             <div class="alert alert-error">    
	                 Login/mot de passe incorrects.
	             </div>
	            </c:if>
	
	            <c:if test="${ param.logout != null }">
	             <div class="alert alert-success"> 
	                 Vous avez été deconnecté.
	             </div>
	            </c:if>
	            
	            <div class="form-group">
					<label for="username" class="col-sm-1 control-label">Login</label>
					<div class="col-sm-3">
						<input type="text" id="username" name="username" class="form-control" />
					</div>
				</div>
	            
	            <div class="form-group">
					<label for="password" class="col-sm-1 control-label">Mot de passe</label>
					<div class="col-sm-3">
						<input type="password" id="password" name="password" class="form-control" />
					</div>
				</div>
	                    
	            <div class="form-group">
					<div class="col-sm-offset-1 col-sm-10">
						<button type="submit" class="btn btn-primary">Connexion</button>
					</div>
				</div>
	            
	        </fieldset>
	        
	    </form>
	</div>

</body>
</html>