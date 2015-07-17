angular.module('bankonet')

.controller("ParIdController", function (BankonetService, $location){

	"use strict";

	var parIdCtrl = this;

	parIdCtrl.id = 0;

	parIdCtrl.msgResult = "Pas de résultat";

	parIdCtrl.getEmploye = function (){
		BankonetService.getEmploye(parIdCtrl.id)
		.then(function (result){
	    	parIdCtrl.msgResult = "Résultat";
	    	parIdCtrl.employe = result.data;
	    })
	    .catch(function (result){
	    	parIdCtrl.msgResult = "Pas de résultat";
	    	parIdCtrl.employe = null;
	    });
    },

    parIdCtrl.toModif = function (id){
    	$location.path("/employe/" + id);
    },

    parIdCtrl.supprimer = function (id){
    	BankonetService.deleteEmploye(id).then(function(){
	    	$location.path("/");
	    });
    }


});