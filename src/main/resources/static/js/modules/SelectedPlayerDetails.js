
import { getSkillValue, SKILLS } from './SkillsSelector.js';

export class SelectedPlayerDetails {
    
    MIN_SKILL_FOR_BOLD = 7;

    constructor (){
        this.detailsElem = {
            player1 : document.getElementById("player1"),
            player2 : document.getElementById("player2"),
            player3 : document.getElementById("player3")
        }

    }

    getPlayerDetailElems(){
        return [ this.detailsElem.player1, this.detailsElem.player2, this.detailsElem.player3 ];
    }


    clearAllDetails(){
        this.getPlayerDetailElems().forEach((el)=>{ el.innerHTML = ""});
    }

    renderDetails(players){

        this.clearAllDetails();
        for(let i=0; i<players.length; i++){
    
            let player = players[i];
            let html = `<div>
                <div class="mb-2"><strong>` + player.name + `</strong></div>` 
                + this.getPlayerSkillRowAsHtml("Keeper" , getSkillValue(player.skills.keeper))
                + this.getPlayerSkillRowAsHtml("Defending" , getSkillValue(player.skills.defending))
                + this.getPlayerSkillRowAsHtml("Playmaking" , getSkillValue(player.skills.playmaking))
                + this.getPlayerSkillRowAsHtml("Passing" , getSkillValue(player.skills.passing))
                + this.getPlayerSkillRowAsHtml("Winger" , getSkillValue(player.skills.winger))
                + this.getPlayerSkillRowAsHtml("Scoring" , getSkillValue(player.skills.scoring))  
                + ` </div>
                <button type="button" class="btn btn-outline-danger mt-2" onclick="addictHT.removePlayer(`+ i +`)">remove</button>`;
            
            this.getPlayerDetailElems()[i].innerHTML = html; 
        }
    }

    getPlayerSkillRowAsHtml(skillName, value){
        let html = skillName + " : " + value + " <br>";
        if( value >= this.MIN_SKILL_FOR_BOLD ){
            html = "<strong>" + html + "</strong>";
        }
        return html;
    }

    
}