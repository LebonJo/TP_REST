angular.module('bankonet')

.controller("CreationController", function (BankonetService, $location){

	"use strict";

	var creationCtrl = this;

	creationCtrl.createEmploye = function (){
		BankonetService.createEmploye(creationCtrl.nom, creationCtrl.prenom, creationCtrl.fonction)
		.then(function (result){
    		$location.path("/");
	    	return result;
	    });
    }
});