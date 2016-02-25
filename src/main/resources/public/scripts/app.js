/**
 * Created by shekhargulati on 10/06/14.
 */

var app = angular.module('todoapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

var API_CONTEXT = "/api/v1";

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).when('/edit/:id', {
        templateUrl: 'views/edit.html',
        controller: 'UpdateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get(API_CONTEXT + '/companies').success(function (data) {
        $scope.todos = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    });

});

app.controller('CreateCtrl', function ($scope, $http, $location) {
    $scope.employees = [{name: ''}];
    $scope.addEmployee = function() {
        $scope.employees.push({});
    }
    $scope.createTodo = function () {
        if($scope.todo) {
            $scope.todo.employees = $scope.employees;
        }
        console.log($scope.todo);
        $http.post(API_CONTEXT + '/companies', $scope.todo).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});

app.controller('UpdateCtrl', function ($scope, $http, $location, $routeParams) {
    var id = $routeParams.id;
    console.log("id : " + id);
    $scope.employees = [];

    $http.get(API_CONTEXT + '/companies/'+id).success(function(data) {
        $scope.todo = data;
        console.log($scope.todo);
        $scope.employees = $scope.todo.employees;
        console.log($scope.employees);
    }).error(function (data, status) {
        console.log('Error ' + data)
    });

    $scope.addEmployee = function() {
        $scope.employees.push({});
    }

    console.log("scope employees : " + $scope.employees);

    $scope.updateTodo = function () {
        if($scope.todo) {
            $scope.todo.employees = $scope.employees;
        }
        console.log($scope.todo);
        $http.put(API_CONTEXT + '/companies', $scope.todo).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});