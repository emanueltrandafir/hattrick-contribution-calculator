
export const SKILLS = { 
    0: "non-existent (0)",
    1: "disastrous (1)",
    2: "wretched (2)",
    3: "poor (3)",
    4: "weak (4)",
    5: "inadequate (5)",
    6: "passable (6)",
    7: "solid (7)",
    8: "excellent (8)",
    9: "formidable (9)",
    10: "outstanding (10)",
    11: "brilliant (11)",
    12: "magnificent (12)",
    13: "world class (13)",
    14: "supernatural (14)",
    15: "titanic (15)",
    16: "extra-terrestrial (16)",
    17: "mythical (17)",
    18: "magical (18)",
    19: "utopian (19)",
    20: "divine (20)"
}


export function getSkillValue(skillName){
    for(var i=0; i<=20; i++){
        if( SKILLS[i].includes(skillName)){
            return i;
        }
    }
}

export class SkillsSelector {
    
    skillElements = {};

    constructor(){

        this.skillElements = {
            keeper : document.getElementById("keeper-skill"),
            defending : document.getElementById("def-skill"),
            passing : document.getElementById("passing-skill"),
            playmaking : document.getElementById("pm-skill"),
            scoring : document.getElementById("scoring-skill"),
            winger : document.getElementById("winger-skill")
        }

        this.initDropdownOptions();
    }

    initDropdownOptions(){
        Object.keys(this.skillElements).forEach( (key) => {
            for(var i=0; i<=20; i++){
                this.skillElements[key].innerHTML +=  "<option>" + SKILLS[i] + "</option>";
            }
        }); 
    }

    getPlayerAndClearSelection(){
        let player = this.getPlayer();
        this.resetSelection();
        return player;
    }

    getPlayer(){
        return {
            skills: {
                keeper : this.skillElements.keeper.value,
                defending : this.skillElements.defending.value,
                passing : this.skillElements.passing.value,
                playmaking : this.skillElements.playmaking.value,
                scoring : this.skillElements.scoring.value,
                winger : this.skillElements.winger.value
            }
        }
    }

    resetSelection(){
        Object.values(this.skillElements).forEach((s)=>{ s.value = SKILLS[0] });
    }

}