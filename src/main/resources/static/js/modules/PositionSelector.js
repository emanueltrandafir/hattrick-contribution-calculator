

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

    lwb: [DIRECTIONS.normal, DIRECTIONS.toCenter, DIRECTIONS.offensive],
    lcb: [DIRECTIONS.normal, DIRECTIONS.toLeft, DIRECTIONS.offensive],
    cb: [DIRECTIONS.normal, DIRECTIONS.offensive],
    rcb: [DIRECTIONS.normal, DIRECTIONS.toRight, DIRECTIONS.offensive],
    rwb: [DIRECTIONS.normal, DIRECTIONS.toCenter, DIRECTIONS.offensive],

    lw: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toCenter, DIRECTIONS.offensive],
    lcm: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toLeft, DIRECTIONS.offensive],
    cm: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.offensive],
    rcm: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toRight, DIRECTIONS.offensive],
    rw: [DIRECTIONS.deffensive, DIRECTIONS.normal, DIRECTIONS.toCenter, DIRECTIONS.offensive],

    lf: [DIRECTIONS.normal, DIRECTIONS.deffensive, DIRECTIONS.toLeft],
    cf: [DIRECTIONS.normal, DIRECTIONS.deffensive],
    rf: [DIRECTIONS.normal, DIRECTIONS.deffensive, DIRECTIONS.toRight],
};

export class PositionSelector {

    constructor() {
        this.selectedPosition = {};
    }

    onPositionClick(clicked) {
        
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

        let _this = addictHT.positionSelector;
        _this.selectedPosition.direction = dirKey;

        setTimeout(function () {
            let el = document.getElementById(event.toElement.id);
            el.style.backgroundColor = "orange";
        }, 100);

        _this.updatePositionDisplay();
    }

    updatePositionDisplay() {
        let pos = "N/A";
        if (this.selectedPosition != {}) {
            pos = this.selectedPosition.id.toUpperCase() + " " + this.selectedPosition.direction;
        }
        document.querySelector(".center.white-font").innerText = "Position: " + pos;
    }

}