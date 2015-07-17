angular.module("bankonet")

.config(function ($routeProvider){
				
	$routeProvider
	.when("/", {
		templateUrl: "views/home.html",
		controller: "HomeController",
		controllerAs: "homeCtrl"
	})
	.when("/parId", {
		templateUrl: "views/parId.html",
		controller: "ParIdController",
		controllerAs: "parIdCtrl"
	})
	.when("/newEmploye", {
		templateUrl: "views/newEmploye.html",
		controller: "CreationController",
		controllerAs: "creationCtrl"
	})
	.when("/employe/:id", {
		templateUrl: "views/employe.html",
		controller: "EmployeController",
		controllerAs: "empCtrl"
	})
	.otherwise({
		redirectTo: "/"
	})
})