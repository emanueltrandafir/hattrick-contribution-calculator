"use strict";
import { SkillsSelector } from './modules/SkillsSelector.js';
import { SelectedPlayerDetails } from './modules/SelectedPlayerDetails.js';
import { PositionSelector } from './modules/PositionSelector.js';
import { ClipboardParser } from './modules/ClipboardParser.js';

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
    };
}

 