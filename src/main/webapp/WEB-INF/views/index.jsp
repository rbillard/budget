<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en" ng-app="budgetApp">
<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Budget</title>
    
    <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.png" />">
    
	<jsp:include page="css.jsp"></jsp:include>
	<jsp:include page="js.jsp"></jsp:include>
	
</head>

<body>

	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-header">
          <a class="navbar-brand" href="#/period/list">Budget</a>
        </div>
		<div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
			<li><a href="#/period/list"/>Périodes</a></li>
			<li><a href="#/period/create"/>Ajouter période</a></li>
			<li><a href="#/budget/list"/>Budgets</a></li>
			<li><a href="#/budget/create"/>Ajouter budget</a></li>
			<li><a href="<c:url value="/login?logout" />">Déconnexion</a></li>
          </ul>
        </div>
	</nav>

  	<div id="content" ng-view></div>
 
 	<footer>
 		<div class="navbar-inverse"></div>
 		<div></div>
 	</footer>
 	
</body>
</html>
