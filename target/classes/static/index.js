angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data;
        });
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {

            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.addToCart = function (productId){
        $http.get('http://localhost:8189/app/api/v1/cart/add/' + productId).then(function(response){
            $scope.loadCart();
        });
    }

    $scope.deleteFromCart = function (productId){
            $http.get('http://localhost:8189/app/api/v1/cart/delete/' + productId).then(function(response){
                $scope.loadCart();
            });
        }
        $scope.cleanCart = function (){
                    $http.get('http://localhost:8189/app/api/v1/cart/clean').then(function(response){
                        $scope.loadCart();
                    });
                }

    $scope.loadCart = function(){
        $http.get('http://localhost:8189/app/api/v1/cart').then(function(response){
            $scope.cart = response.data;
        });
    }

    $scope.loadProducts();
    $scope.loadCart();
});