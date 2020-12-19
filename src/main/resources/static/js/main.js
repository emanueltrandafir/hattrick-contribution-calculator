"use strict";
import { SkillsSelector } from './modules/SkillsSelector.js';
import { SelectedPlayerDetails } from './modules/SelectedPlayerDetails.js';
import { PositionSelector } from './modules/PositionSelector.js';
import { ClipboardParser } from './modules/ClipboardParser.js';
import { AddictHtClient } from './modules/AddictHtClient.js';

window.onload = function(){
    
    window.addictHT = {
        players : [],
        skillSelector : new SkillsSelector(),
        selectedPlayersDetails : new SelectedPlayerDetails(),
        positionSelector : new PositionSelector(),
        clipboardParser : new ClipboardParser()
    }

    initListeners()
}

function initListeners(){
    addictHT.addPlayer = () => {
        if(addictHT.players.length == 3){
            alert( "cannot compare more than 3 players!" );
            return;
        }
        let player = addictHT.skillSelector.getPlayerAndClearSelection();
        player.name = "Player " + (addictHT.players.length + 1);
        addictHT.players.push(player);
        addictHT.selectedPlayersDetails.renderDetails( addictHT.players );
    };

    addictHT.removePlayer = (index) => {
        addictHT.players.splice(index,1);
        addictHT.selectedPlayersDetails.renderDetails( addictHT.players );
    };

    addictHT.onPositionClick = (clicked) => {
        addictHT.positionSelector.onPositionClick(clicked);

        let client = new AddictHtClient();
        client.analyzePlayers( getPlayerDtos() );

    };

    addictHT.readFromClipboard = () => {
        if(addictHT.players.length == 3){
            alert( "cannot compare more than 3 players!" );
            return;
        }
        addictHT.clipboardParser.parse( (player)=> {
            if( player != null ){  
                addictHT.players.push(player);
                addictHT.selectedPlayersDetails.renderDetails( addictHT.players );
            }
        });         
    }

}

    
function getPlayerDtos() {
    let playerDtos = [];
    for (let player of addictHT.players) {

        let playerDto = {
            name: player.name, 
            skills: {}
        };
        for(let skillLabel of Object.keys(player.skills)){
            playerDto.skills[ skillLabel ] = {
                label : skillLabel,
                value : getTextBetweenBrackets( player.skills[ skillLabel ] )
            }
        }
        playerDtos.push( playerDto );
    }
    return playerDtos;
}


function getTextBetweenBrackets(text) { 
    return text.split("(")[1].split(")")[0];
}