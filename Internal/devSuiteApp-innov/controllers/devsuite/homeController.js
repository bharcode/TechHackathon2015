    routingExample.controller('CarouselDemoCtrl', function ($scope) {
      $scope.myInterval = 5000;
      $scope.slides = devSuiteCarouselData;

    });

    routingExample.controller('NavCtrl', ['$scope', '$location', function ($scope, $location) {
      $scope.breadcrumbs = [];
      $scope.menu = devSuiteMenuData;
    }]);

    routingExample.controller('MainDespCtrl', function ($scope) {
        $scope.maindesp = devsuiteMainDespData;
    });

    /********************* History controller ***********************/


    routingExample.controller('MyHistCtrl', ['$scope', '$location', '$http', '$filter',
        function($scope, $location, $http, $filter) {

            angular.element(document).ready(function() {
                $http.get('http://bglc02m969efh01:8080/getallapi').success(function(response) {
                    $scope.items = response;
                    $scope.initApiTable($scope.items);
                }).error(function(err) {
                    console.log('err');
                });



    $scope.initApiTable = function() {
        $scope.sort = {
            sortingOrder: 'id',
            reverse: false
        };

        $scope.gap = 1;

        $scope.filteredItems = [];
        $scope.groupedItems = [];
        $scope.itemsPerPage = 5;
        $scope.pagedItems = [];
        $scope.currentPage = 0;
        /*    $scope.items = [{"jarname":"test.jar","inputparams":"java.lang.String, java.lang.String, java.lang.String","apiname":"product-1","classname":"com.aexp.hackathon.string.Product","outputparams":"java.lang.String","methodname":"product"},
         {"jarname":"DemoProject.jar","inputparams":"java.lang.String, java.lang.String","apiname":"sum-2","classname":"com.aexp.hackathon.string.Sum","outputparams":"java.lang.String","methodname":"sum"},
         {"jarname":"DemoProject.jar","inputparams":"java.lang.String, java.lang.String, java.lang.String","apiname":"sum-3","classname":"com.aexp.hackathon.string.Sum","outputparams":"java.lang.String","methodname":"sum"},
         {"jarname":"gdas.jar","inputparams":"com.americanexpress.gdas.pojo.GdasRequest","apiname":"getName-4","classname":"com.americanexpress.gdas.service.NameService","outputparams":"com.americanexpress.gdas.pojo.GdasNameResponse","methodname":"getName"}];

         */
        var searchMatch = function (haystack, needle) {
            if (!needle) {
                return true;
            }
            return haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
        };

        // init the filtered items
        $scope.search = function () {
            $scope.filteredItems = $filter('filter')($scope.items, function (item) {
                for (var attr in item) {
                    if (searchMatch(item[attr], $scope.query))
                        return true;
                }
                return false;
            });
            // take care of the sorting order
            if ($scope.sort.sortingOrder !== '') {
                $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sort.sortingOrder, $scope.sort.reverse);
            }
            $scope.currentPage = 0;
            // now group by pages
            $scope.groupToPages();
        };


        // calculate page in place
        $scope.groupToPages = function () {
            $scope.pagedItems = [];

            for (var i = 0; i < $scope.filteredItems.length; i++) {
                if (i % $scope.itemsPerPage === 0) {
                    $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [$scope.filteredItems[i]];
                } else {
                    $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
                }
            }
        };

        $scope.range = function (size, start, end) {
            var ret = [];
            console.log(size, start, end);

            if (size < end) {
                end = size;
                start = size - $scope.gap;
            }
            for (var i = start; i < end; i++) {
                ret.push(i);
            }
            console.log(ret);
            return ret;
        };

        $scope.prevPage = function () {
            if ($scope.currentPage > 0) {
                $scope.currentPage--;
            }
        };

        $scope.nextPage = function () {
            if ($scope.currentPage < $scope.pagedItems.length - 1) {
                $scope.currentPage++;
            }
        };

        $scope.setPage = function () {
            $scope.currentPage = this.n;
        };

        // functions have been describe process the data for display
        $scope.search();


    }

            });



        }
    ]);