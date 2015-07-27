routingExample.controller('capstonePlanCtrl', ['$scope', '$location',
    function($scope, $location) {
        $scope.ordered_columns = [];
        $scope.all_columns = [{
            "title": "#",
            "type": "string",
            "checked": true
        }, {
            "title": "Phase",
            "type": "number",
            "checked": true
        }, {
            "title": "Task",
            "type": "string",
            "checked": true

        }, {
            "title": "start Date",
            "type": "string",
            "checked": true
        }, {
            "title": "End Date",
            "type": "string",
            "checked": true
        }];

        // i.e. the rows
        $scope.data = [{
            "#": "1",
            "Phase": "Ideate",
            "Task": "Complete high level user story",
            "start Date": "2-Feb-2015",
            "End Date": "6-Feb-2015"
        }, {
            "#": "2",
            "Phase": "Prototype",
            "Task": "Feature 1 + Platform",
            "start Date": "9-Feb-2015",
            "End Date": "13-Feb-2015"
        }, {
            "#": "3",
            "Phase": "Prototype",
            "Task": "Feature 2 & 3 + Advanced Features",
            "start Date": "16-Feb-2015",
            "End Date": "20-Feb-2015"
        }, {
            "#": "4",
            "Phase": "Ideate",
            "Task": "Complete high level user story",
            "start Date": "2-Feb-2015",
            "End Date": "6-Feb-2015"
        }, {
            "#": "5",
            "Phase": "Prototype",
            "Task": "Feature 1 + Platform",
            "start Date": "9-Feb-2015",
            "End Date": "13-Feb-2015"
        }, {
            "#": "6",
            "Phase": "Prototype",
            "Task": "Feature 2 & 3 + Advanced Features",
            "start Date": "16-Feb-2015",
            "End Date": "20-Feb-2015"
        }];

        $scope.$watch('all_columns', function() {
            update_columns();
        }, true);

        var update_columns = function() {
            $scope.ordered_columns = [];
            for (var i = 0; i < $scope.all_columns.length; i++) {
                var column = $scope.all_columns[i];
                if (column.checked) {
                    $scope.ordered_columns.push(column);
                }
            }
        };

    }
]);