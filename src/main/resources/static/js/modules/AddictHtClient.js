
export class AddictHtClient {

    analyzePlayers( playerDtos ) {

        // positionId, @RequestParam String centralOrSideFlag

        let p = playerDtos[0];
        let qp = this.getQueryParams();

        this.calculateContributionForPlayer(p, qp);
    }



    calculateContributionForPlayer(playerDto, qp) {

        let json = JSON.stringify(playerDto);
        let url = "/calculatedContributions?" + qp;
        
        var xhr = new XMLHttpRequest();
        xhr.open('POST', url, true); 
        xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        xhr.onload = function () {
            console.log("response: ");
            console.log(this.responseText);
        };
        xhr.send(json);
 
    }

    getQueryParams(){
        let posDto = addictHT.positionSelector.getPositionDto();
        return "positionId=" + posDto.positionId + "&centralOrSideFlag=" + posDto.centralOrSideFlag;
    }
 

}