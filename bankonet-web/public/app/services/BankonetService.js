angular.module('bankonet')

.factory("BankonetService", ["$http", function ($http){

	"use strict";

	var restUrl = "http://localhost:8080/bankonet-rest/api";

	return {

		getEmployes: function (){
			var BankonetService = this;
			return $http.get(restUrl + "/employes")
			.then(function (result){
				return result;
			});
		},

		getEmploye: function (id){
			var BankonetService = this;
			return $http.get(restUrl + "/employes/" + id)
			.then(function (result){
				return result;
			})
		},

		createEmploye: function (nom, prenom, fonction){
			var BankonetService = this;
			var request = $http({
			    method: "post",
			    url: restUrl + "/employes",
			    data: this.serializeData({
					nom : nom,
					prenom : prenom,
					fonction : fonction
			    }),
			    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8' }
			});
			return request.then(function (result){
				return result;
			});
		},

		updateEmploye: function (data){
			var BankonetService = this;
			var request = $http({
				method: "put",
				url: restUrl + "/employes",
				data: this.serializeData(data),
			    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8' }
			});
			return request.then(function (result){
				return result;
			});
		},

		deleteEmploye: function (id){
			var BankonetService = this;
			var request = $http({
				method: "delete",
				url: restUrl + "/employes/" + id
			});
			return request.then(function (result){
				return result;
			});
		},

        // I serialize the given Object into a key-value pair string. This
        // method expects an object and will default to the toString() method.
        // --
        // NOTE: This is an atered version of the jQuery.param() method which
        // will serialize a data collection for Form posting.
        // --
        // https://github.com/jquery/jquery/blob/master/src/serialize.js#L45
        serializeData: function (data) {

            // If this is not an object, defer to native stringification.
            if (!angular.isObject(data)) {
                return((data == null) ? "" : data.toString());
            }

            var buffer = [];

            // Serialize each key in the object.
            for(var name in data){
                if(!data.hasOwnProperty(name)) {
                    continue;
                }

                var value = data[name];

                buffer.push(encodeURIComponent(name) + "=" + encodeURIComponent((value == null) ? "" : value));

            }

            // Serialize the buffer and clean it up for transportation.
            var source = buffer
                .join( "&" )
                .replace( /%20/g, "+" )
            ;

            return(source);

        },
	}
}]);