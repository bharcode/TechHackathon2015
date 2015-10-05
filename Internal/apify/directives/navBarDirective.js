routingExample
  .directive('navBarHeader', function () {
        return {
            restrict: 'A',
            scope: {
			menuitems: "="
		      },
            template: '<div class="nav-hdr-wrap">'+
                '<div class="nav-hdr-logo-wrap"><img src="img/amexLogo.gif" style="width:70px;"/>'+
                '</div>'+
                '<div class="nav-hdr-home-wrap"><div>'+
                '<img class="nav-hdr-home" src="img/homeLogo.png"/>'+
                '</div></div></div>'

        }
    })

routingExample
  .directive('navBar', function () {
        return {
            restrict: 'A',
            scope: {
			menuitems: "="
		      },
            template: '<ul class="nav navbar-nav"><li ng-repeat="node in menuitems"><a style="color:#fff" href={{node.href}}>{{node.text}}</a></li></ul>'

        }
    })