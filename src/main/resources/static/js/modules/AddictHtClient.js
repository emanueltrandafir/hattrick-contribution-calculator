
export class AddictHtClient {

    analyzePlayers( playerDtos ) {
        playerDtos.forEach((p)=>{
            let dto = {
                'playerDto' :  p,
                'positionDto': addictHT.positionSelector.getPositionDto()
            }
            this.calculateContributionForPlayer(dto);
        });
    }


    calculateContributionForPlayer(dto) {

        let json = JSON.stringify(dto); 
        let url = "/calculatedContributions";
        var xhr = new XMLHttpRequest();

        xhr.open('POST', url, true); 
        xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        xhr.onload = function () {
            addictHT.calculatedContribution.onResponse( JSON.parse( this.responseText ), dto.playerDto.name );
        };

        xhr.send(json);
    }

}