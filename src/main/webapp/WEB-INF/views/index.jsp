<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en" ng-app="budgetApp">
<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Budget</title>
    
    <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.png" />">
    
	<link rel="stylesheet" href="<c:url value="/resources/css/app.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/css/bootstrap-theme.min.css" />">
	
	<script src="<c:url value="/resources/js/jquery/jquery-1.10.2.min.js" />"></script>
	
	<script src="<c:url value="/resources/js/angular/constants.js" />"></script>
	<script src="<c:url value="/resources/js/google/date.js" />"></script>
	<script src="<c:url value="/resources/js/angular/angular.min.js" />"></script>
	<script src="<c:url value="/resources/js/angular/angular-route.js" />"></script>
	<script src="<c:url value="/resources/js/angular/angular-resource.js" />"></script>
	<script src="<c:url value="/resources/js/angular/local/angular-locale_fr-fr.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap/ui-bootstrap-tpls-0.10.0.min.js" />"></script>
	<script src="<c:url value="/resources/js/angular/app.js" />"></script>
	<script src="<c:url value="/resources/js/angular/period.js" />"></script>
	<script src="<c:url value="/resources/js/angular/budget.js" />"></script>
	<script src="<c:url value="/resources/js/angular/operation.js" />"></script>
	<script src="<c:url value="/resources/js/angular/period-budget.js" />"></script>
	
	<script type="text/javascript">
		<c:url value="/" var="context" />
		var context = "${ context }";
	</script>
	
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
