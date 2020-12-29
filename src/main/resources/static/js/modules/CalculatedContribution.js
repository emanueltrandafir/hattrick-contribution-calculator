

export class CalculatedContribution {

    onResponse( response ){
        this.clearAllRatings();
        let _this = this;
        setTimeout(()=>{
            for(let key of Object.keys(response)){
                let value = response[key];
                let positionCode = key.split("code=")[1].split(",")[0];
                _this.dsplayContribution(positionCode, value);
            }
        }, 100 );
    }

    clearAllRatings(){
        ["l-def","c-def","r-def","mid","l-att","c-att","r-att"].forEach((id) => {
            document.getElementById(id).innerHTML = "";
        })
    }

    dsplayContribution(code, value){
        let raitingBox = this.getRaitingBoxElement(code);
        if(raitingBox == null){
            throw ("could not find element for code = " + code);
        }
        raitingBox.innerHTML = "<br><div class='raiting-value'> +" + value + "</div>"
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