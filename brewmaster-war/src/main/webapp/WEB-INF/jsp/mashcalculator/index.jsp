<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app>
    <head>
        <title>Mash Temperature Calculator</title>
    </head>
    <body>
        <div ng-controller="MashCalculator">
            <p>Use this to calculate infusion temperatures.</p>
            <p>{{mashtun.thermalMass}}</p>
            <input ng-model=""/>
        </div>
        <script src="<c:url value="/lib/angular/angular.js"/>"></script>
        <script src="<c:url value="/mashcalculator/js/controllers.js"/>"></script>
    </body>
</html>