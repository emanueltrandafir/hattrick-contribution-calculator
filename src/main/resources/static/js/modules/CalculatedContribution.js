
export class CalculatedContribution {

    onResponse( response, name ){ 

        let details = this.getPlayerDisplayDetailsByName(name);
        addictHT.responses[details.index] = {
            color:details.color,
            data: response,
        };
        console.log("responsese received: " + Object.keys(addictHT.responses).length + " / "  + addictHT.players.length);

        if(Object.keys(addictHT.responses).length == addictHT.players.length){
            let resp = [addictHT.responses[0]];
            if(addictHT.responses[1] != undefined){
                resp.push(addictHT.responses[1]);
            }
            if(addictHT.responses[2] != undefined){
                resp.push(addictHT.responses[2]);
            } // TODO: refactor this mess! 
            resp.forEach((r)=>{ this.displayPlayerRaiting(r); })
        }
    }

    displayPlayerRaiting(response){
        let data = response.data;
        for(let key of Object.keys(data)){
            let value = data[key];
            let positionCode = key.split("code=")[1].split(",")[0];
            this.dsplayContribution(positionCode, value, response.color);
        }
    }
    
    dsplayContribution(code, value, color){
        let raitingBox = this.getRaitingBoxElement(code);
        if(raitingBox == null){
            throw ("could not find element for code = " + code);
        }
        raitingBox.innerHTML += "<br><div class='raiting-value' style='color:" + color + "'> +" + value.toFixed(2) + "</div>"
    }


    getPlayerDisplayDetailsByName(name){
        for(let i=0; i<=addictHT.players.length; i++){
            let p = addictHT.players[i];
            if(p.name == name){
                return {
                    index: i,
                    color: i==0? "#87c7ff" : i==1? "#fa7070" : "#e37cf7",
                }
            }
        }
    } 

    clearAllRatings(){
        ["l-def","c-def","r-def","mid","l-att","c-att","r-att"].forEach((id) => {
            document.getElementById(id).innerHTML = "";
        })
    }

    getRaitingBoxElement(code){
        switch(code){
            case "LDef": return document.getElementById("l-def");
            case "CDef": return document.getElementById("c-def");
            case "RDef": return document.getElementById("r-def");
            case "Mid": return document.getElementById("mid");
            case "LAt": return document.getElementById("l-att");
            case "CAt": return document.getElementById("c-att");
            case "RAt": return document.getElementById("r-att");
            default: throw ("could not match element id for code = " + code);
        }
    }
}