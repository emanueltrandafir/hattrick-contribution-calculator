

const DIRECTIONS = {
    offensive: "↓",
    deffensive: "↑",
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
    rwb: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toLeft, DIRECTIONS.offensive],

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

    getPositionDto() {
        return {
            "basePosition": this.getBasePosition(),
            "positionSide": this.getPositionSide(),
            "orientation": this.getOrientation()
        }
    }

    getPositionSide() {
        if(this.getBasePosition() == "F" && this.getOrientation() != "tw"){
            return "C";
        }
        switch (this.selectedPosition.id.substring(0, 1)) {
            case "l": return "L";
            case "r": return "R";
            case "c":
            default: return "C";
        }
    }

    getOrientation() {
        switch (this.selectedPosition.direction) {

            case "offensive": return "o";
            case "deffensive": return "d";
            case "normal": return "";

            case "toLeft":
            case "toRight":
                switch (this.getBasePosition()) {
                    case "W":
                    case "WB":
                        return "tm";
                    default: return "tw";
                }
        }
    }

    getBasePosition() {
        switch (this.selectedPosition.id) {
            case "gk": return "GK"

            case "lcb":
            case "cb":
            case "rcb":
                return "CD";

            case "lwb":
            case "rwb":
                return "WB";

            case "lcm":
            case "cm":
            case "rcm":
                return "IM";

            case "lw":
            case "rw":
                return "W";

            case "lf":
            case "cf":
            case "rf":
                return "F";
        }
    }


}