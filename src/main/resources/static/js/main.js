
window.onload = function(){
    
    window.addictHT = {
        players : []
    }

    initSkills();
}


let skills = {
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

function getSkillValue(skillName){
    for(var i=0; i<=20; i++){
        if( skills[i] == skillName ){
            return i;
        }
    }
}

function initSkills(){
    initSkillOptionsForElem("keeper-skill");
    initSkillOptionsForElem("def-skill");
    initSkillOptionsForElem("passing-skill");
    initSkillOptionsForElem("pm-skill");
    initSkillOptionsForElem("scoring-skill");
    initSkillOptionsForElem("winger-skill");
}

function initSkillOptionsForElem( id ){
    let parentElement = document.getElementById( id );
    for(var i=0; i<=20; i++){
        parentElement.innerHTML +=  "<option>" + skills[i] + "</option>";
    }
}

function addPlayer() {

    let player = {};
    player["keeper"] = document.getElementById("keeper-skill").value;
    player["defending"] = document.getElementById("def-skill").value;
    player["passing"] = document.getElementById("passing-skill").value;
    player["playmaking"] = document.getElementById("pm-skill").value;
    player["scoring"] = document.getElementById("scoring-skill").value;
    player["winger"] = document.getElementById("winger-skill").value;

    document.getElementById("keeper-skill").value = "non-existent (0)";
    document.getElementById("def-skill").value = "non-existent (0)";
    document.getElementById("passing-skill").value = "non-existent (0)";
    document.getElementById("pm-skill").value = "non-existent (0)";
    document.getElementById("scoring-skill").value = "non-existent (0)";
    document.getElementById("winger-skill").value = "non-existent (0)";

    addictHT.players.push(player);
    updatePlayersSummary();
}
 
function updatePlayersSummary(){

    for(let i=0; i<3; i++){
        
        document.getElementById("player"+(i+1)).innerHTML = ""; 
        if( i > addictHT.players.length-1 ){
            continue;
        }

        let player = addictHT.players[i];
        let html = `<div>
            <div class="mb-2"> Player ` + (i+1) + ` </div>` 
            + getPlayerSkillRowAsHtml("Keeper" , getSkillValue(player.keeper))
            + getPlayerSkillRowAsHtml("Defending" , getSkillValue(player.defending))
            + getPlayerSkillRowAsHtml("Playmaking" , getSkillValue(player.playmaking))
            + getPlayerSkillRowAsHtml("Passing" , getSkillValue(player.passing))
            + getPlayerSkillRowAsHtml("Winger" , getSkillValue(player.winger))
            + getPlayerSkillRowAsHtml("Scorring" , getSkillValue(player.scoring))  
            + ` </div>
            <button type="button" class="btn btn-outline-danger mt-2" onclick="removePlayer(`+ i +`)">remove</button>`;
        
        document.getElementById("player"+(i+1)).innerHTML = html; 
    }
}

function getPlayerSkillRowAsHtml(skillName, value){
    let html = skillName + " : " + value + " <br>";
    if( value >= 7 ){
        html = "<strong>" + html + "</strong>";
    }
    return html;
}

function removePlayer(index){
    addictHT.players.splice(index,1);
    updatePlayersSummary();
}


/* position selector */

let selectedPosition = {};

const DIRECTIONS = {
    offensive : "↓",
    deffensive : "↑",
    toCenter: "center",
    toLeft: "→",
    toRight: "←",
    normal: "normal"
}
const DIRECTIONS_MAPPING = {
    gk:[ DIRECTIONS.normal ],

    lwb:[ DIRECTIONS.normal , DIRECTIONS.toCenter , DIRECTIONS.offensive ],
    lcb:[ DIRECTIONS.normal , DIRECTIONS.toLeft ,    DIRECTIONS.offensive ],
    cb: [ DIRECTIONS.normal , DIRECTIONS.offensive ],
    rcb:[ DIRECTIONS.normal , DIRECTIONS.toRight ,   DIRECTIONS.offensive ],
    rwb:[ DIRECTIONS.normal , DIRECTIONS.toCenter, DIRECTIONS.offensive ],

    lw: [ DIRECTIONS.deffensive , DIRECTIONS.normal , DIRECTIONS.toCenter , DIRECTIONS.offensive ],
    lcm:[ DIRECTIONS.deffensive , DIRECTIONS.normal , DIRECTIONS.toLeft , DIRECTIONS.offensive ],
    cm: [ DIRECTIONS.deffensive , DIRECTIONS.normal , DIRECTIONS.offensive ],
    rcm:[ DIRECTIONS.deffensive , DIRECTIONS.normal , DIRECTIONS.toRight , DIRECTIONS.offensive ],
    rw: [ DIRECTIONS.deffensive , DIRECTIONS.normal , DIRECTIONS.toCenter , DIRECTIONS.offensive ],
    
    lf: [ DIRECTIONS.normal , DIRECTIONS.deffensive , DIRECTIONS.toLeft],
    cf: [ DIRECTIONS.normal , DIRECTIONS.deffensive ],
    rf: [ DIRECTIONS.normal , DIRECTIONS.deffensive , DIRECTIONS.toRight],
};


function onPositionClick(clicked){
    removeDirOptions();

    selectedPosition.view = clicked;
    selectedPosition.id = clicked.id;
    selectedPosition.direction = "normal";

    let i=0;
    for(let dir of DIRECTIONS_MAPPING[clicked.id]){
        let btn = document.createElement("div"); 
        btn.className = "direction-option";    
        btn.innerText = dir;
        btn.onclick = onDirSelected;
        clicked.appendChild(btn);
        btn.id = "option" + i;
        i++;
    }
}

function removeDirOptions(){
    let el = document.querySelector(".direction-option");
    while(el){
        el.remove();
        el = document.querySelector(".direction-option");
    }
}

function onDirSelected(event){
    let dir = event.toElement.innerHTML;
    let dirKey = Object.keys(DIRECTIONS).filter(o => DIRECTIONS[o] == dir)[0];
    selectedPosition.direction = dirKey;

    setTimeout( function(){ 
        let el =document.getElementById(event.toElement.id);
        el.style.backgroundColor = "orange";
    } , 100 );

    updatePositionDisplay();
}

function updatePositionDisplay(){
    let pos = "N/A";
    if(selectedPosition != {}){
        pos = selectedPosition.id.toUpperCase() + " " + selectedPosition.direction;
    }
    document.querySelector(".center.white-font").innerText = "Position: " + pos;
}




/*  reading and parsing player data from clipboard text  */

function readPlayerFromKeyboard(){
    navigator.clipboard.readText()
        .then(function(clipText){
            try {
                onPlayerParsed(getPlayerSkills(clipText));  
            } catch (error) {
                alert("error parsing the player data, make sure the player was copied in the clipboard properly.")        
            }
        });
} 

function getPlayerSkills(text){  
    let s = [];
    text.split("[table]")[1]
        .split("[td]")
        .filter(el => el.includes("td"))
        .forEach(el=>{ 
            el = el.replace("[b]","").replace("[i]",""); 
            let arr = el.split("(");
            let label = arr[0];
            let value = arr[1].split(")")[0];
            s.push({
                label: label,
                value: value
            });  
        });
        
    let name = text.split(" [playerid")[0];
    let skills = {};
    skills.stamina = s[0];
    skills.keeper = s[1];
    skills.playmaking = s[2];
    skills.passing = s[3];
    skills.winger = s[4];
    skills.defending = s[5];
    skills.scorring = s[6];
    skills.setpieces = s[7];
    
    let player = {
        name : name,
        skills : skills
    };
    
    window.player = player;
    return player;
};

function onPlayerParsed(player){
    console.log(player);

    document.getElementById("player-name").innerText = player.name;

    // let skillsStr = "";
    // Object.keys(player.skills).forEach( s => skillsStr+= s + ":" + player.skills[s].value +" ; " );
    // document.getElementById("player-skills").innerText = skillsStr;

}