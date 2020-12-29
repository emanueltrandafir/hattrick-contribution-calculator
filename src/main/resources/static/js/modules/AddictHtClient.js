
export class AddictHtClient {

    analyzePlayers( playerDtos ) {

        let playerPosition = {
            'playerDto' :  playerDtos[0],
            'positionDto': addictHT.positionSelector.getPositionDto()
        }

        this.calculateContributionForPlayer(playerPosition);
    }



    calculateContributionForPlayer(playerPosition) {

        let json = JSON.stringify(playerPosition);

        console.log(json);

        let url = "/calculatedContributions";
        
        var xhr = new XMLHttpRequest();
        xhr.open('POST', url, true); 
        xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        xhr.onload = function () {
            addictHT.calculatedContribution.onResponse( JSON.parse( this.responseText ) );
        };
        xhr.send(json);
    }

}