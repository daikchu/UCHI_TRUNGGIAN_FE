
var myApp = angular.module('osp', ['ngSanitize']);

myApp.controller('templateDetailController',['$scope','$http','$window','$sce',function ($scope,$http,$window,$sce) {


    $scope.viewAsDoc=function () {
        $("#viewHtmlAsWord").html($("#editor").html());
        $("#viewContentAsWord").modal('show');
    }

    $scope.downloadWord=function () {
        $("#editor").wordExport();
    }
}]);

