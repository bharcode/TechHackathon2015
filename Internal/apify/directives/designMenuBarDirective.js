routingExample
    .directive('designMenuBar', function($modal) {
        return {
            restrict: 'A',
            scope: {
                submenuitems: "="
            },
            template: '<ul class="nav navbar-nav"><li ng-repeat="nodemenu in submenuitems"><img class="img-sub-menu" src={{nodemenu.src}} ng-click="subMenuPopUp($event)"></img></li></ul>',
            link: function(scope, element, attrs, $scope) {
                scope.subMenuPopUp = function(event) {
                    // var modalContent = event.target.getAttribute('src').match("\/(.*)")[1];  
                    var modalContent = event.target.getAttribute('src').match("\/(.*)")[1].match("\/(.*)")[1];
                    scope.$parent.open($modal, modalContent);
                };


            }

        }
    })