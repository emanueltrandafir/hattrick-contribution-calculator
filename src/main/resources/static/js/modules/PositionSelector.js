

const DIRECTIONS = {
    offensive: "↓",
    deffensive: "↑",
    toCenter: "center",
    toLeft: "→",
    toRight: "←",
    normal: "normal"
}
const DIRECTIONS_MAPPING = {
    gk: [DIRECTIONS.normal],

    lwb: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toRight, DIRECTIONS.offensive],
    lcb: [DIRECTIONS.normal, DIRECTIONS.toLeft, DIRECTIONS.offensive],
    cb: [DIRECTIONS.normal, DIRECTIONS.offensive],
    rcb: [DIRECTIONS.normal, DIRECTIONS.toRight, DIRECTIONS.offensive],
    rwb: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toRight, DIRECTIONS.offensive],

    lw: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toRight, DIRECTIONS.offensive],
    lcm: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toLeft, DIRECTIONS.offensive],
    cm: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.offensive],
    rcm: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toRight, DIRECTIONS.offensive],
    rw: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toLeft, DIRECTIONS.offensive],

    lf: [DIRECTIONS.normal, DIRECTIONS.deffensive, DIRECTIONS.toLeft],
    cf: [DIRECTIONS.normal, DIRECTIONS.deffensive],
    rf: [DIRECTIONS.normal, DIRECTIONS.deffensive, DIRECTIONS.toRight],
};

export class PositionSelector {

    constructor() {
        this.selectedPosition = {};
    }

    onPositionClick(clicked) {

        if (clicked.id == this.selectedPosition.id) {
            this.directionClicked = false;
            return;
        }
        this.removeDirOptions();
        this.selectedPosition.view = clicked;
        this.selectedPosition.id = clicked.id;
        this.selectedPosition.direction = "normal";

        let i = 0;
        for (let dir of DIRECTIONS_MAPPING[clicked.id]) {
            let btn = document.createElement("div");
            btn.className = "direction-option";
            btn.innerText = dir;
            btn.onclick = this.onDirSelected;
            clicked.appendChild(btn);
            btn.id = "option" + i;
            i++;
        }
    }

    removeDirOptions() {
        let el = document.querySelector(".direction-option");
        while (el) {
            el.remove();
            el = document.querySelector(".direction-option");
        }
    }

    onDirSelected(event) {

        let dir = event.toElement.innerHTML;
        let dirKey = Object.keys(DIRECTIONS).filter(o => DIRECTIONS[o] == dir)[0];
        
        setTimeout(function () {
            let _this = addictHT.positionSelector;
            _this.selectedPosition.direction = dirKey;

            let el = document.getElementById(event.toElement.id);
            for (let option of document.querySelectorAll(".direction-option")) {
                option.style.backgroundColor = "rgba(255, 255, 255, 0.4)";
            }
            el.style.backgroundColor = "orange";

            _this.updatePositionDisplay();

        }, 100);

    }

    updatePositionDisplay() {
        let pos = "N/A";
        if (this.selectedPosition != {}) {
            pos = this.selectedPosition.id.toUpperCase() + " " + this.selectedPosition.direction;
        }
        document.querySelector(".center.white-font").innerText = "Position: " + pos;
    }

    getPositionDto(){
        return{
            "positionId" : this.getPositionId(),
            "centralOrSideFlag" : this.getCentralOrSideFlag()
        }
    }

    getCentralOrSideFlag(){
        if( ["lcb","rcb","lcm","rcm","lf","rf"].indexOf(this.selectedPosition.id.toLowerCase()) != -1 ){
            return "S";
        }
        return "C";
    }

    getPositionId() {

        console.log(this);
        switch (this.selectedPosition.id.toLowerCase()) {
            case "gk": return 1;
            
            case "cb":
            case "rcb":
            case "lcb":
                switch (this.selectedPosition.direction) {
                    case "normal": return 2;
                    case "offensive": return 4;
                    case "toLeft":
                    case "toRight":
                        return 3; // does matter if left or right?
                } 
                break;

            case "rwb":
            case "lwb":
                switch (this.selectedPosition.direction) {
                    case "defensive": return 5;
                    case "normal": return 6;
                    case "offensive": return 8;
                    case "toLeft":
                    case "toRight":
                        return 7; // does matter if left or right?
                }
                break;

            case "cm":
            case "rcm":
            case "lcm":
                switch (this.selectedPosition.direction) {
                    case "defensive": return 9;
                    case "normal": return 10;
                    case "offensive": return 12;
                    case "toLeft":
                    case "toRight":
                        return 11; // does matter if left or right?
                }
                break;

            case "lw":
            case "rw":
                switch (this.selectedPosition.direction) {
                    case "defensive": return 13;
                    case "normal": return 14;
                    case "offensive": return 16;
                    case "toLeft":
                    case "toRight":
                        return 15; // does matter if left or right?
                }
                break;

            case "lf":
            case "cf":
            case "rf":
                switch (this.selectedPosition.direction) {
                    case "defensive": return 17;
                    case "normal": return 18;
                    case "toLeft":
                    case "toRight":
                        return 19; // does matter if left or right?
                }
                break;
        }
    }

}