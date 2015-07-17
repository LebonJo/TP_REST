angular.module('bankonet')

.controller("EmployeController", function (BankonetService, $routeParams, $location){

	"use strict";

	var empCtrl = this;

	empCtrl.id = $routeParams['id'];
	empCtrl.nom = "";
	empCtrl.prenom = "";
	empCtrl.fonction = "";

	function fetchEmploye() {
		BankonetService.getEmploye(empCtrl.id)
		.then(function (result){
	    	empCtrl.nom = result.data.nom;
			empCtrl.prenom = result.data.prenom;
			empCtrl.fonction = result.data.fonction;
	    });
    };

    fetchEmploye();

    empCtrl.updateEmploye = function(){
    	var data = {
    		id : empCtrl.id,
    		nom : empCtrl.nom,
    		prenom : empCtrl.prenom,
    		fonction : empCtrl.fonction
    	};
    	BankonetService.updateEmploye(data)
    	.then(function (result){
    		$location.path("/");
    		return result;
    	});
    };
});