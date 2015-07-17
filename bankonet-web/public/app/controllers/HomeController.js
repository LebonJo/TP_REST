angular.module('bankonet')

.controller("HomeController", function (BankonetService, $location){

	"use strict";

	var homeCtrl = this;

	function fetchEmployes (){
		BankonetService.getEmployes().then(function (result){
	    	homeCtrl.employes = result.data;
	    });
    }

    fetchEmployes();

    homeCtrl.toModif = function (id){
    	$location.path("/employe/" + id);
    }

    homeCtrl.supprimer = function (id){
    	BankonetService.deleteEmploye(id).then(function(){
	    	fetchEmployes();
	    });
    }
});