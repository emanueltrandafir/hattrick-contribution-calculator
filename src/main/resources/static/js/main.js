"use strict";
import { SkillsSelector, SKILLS } from './modules/SkillsSelector.js';
import { SelectedPlayerDetails } from './modules/SelectedPlayerDetails.js';
import { PositionSelector } from './modules/PositionSelector.js';
import { ClipboardParser } from './modules/ClipboardParser.js';
import { AddictHtClient } from './modules/AddictHtClient.js';
import { AuthService } from './modules/AuthService.js';
import { CalculatedContribution } from './modules/CalculatedContribution.js';

window.onload = function () {

    window.addictHT = {
        players: [],
        skillSelector: new SkillsSelector(),
        selectedPlayersDetails: new SelectedPlayerDetails(),
        positionSelector: new PositionSelector(),
        clipboardParser: new ClipboardParser(),
        calculatedContribution: new CalculatedContribution(),
        responses  : {},
        authService : new AuthService()
    }

    addictHT.getPlayerDtos = function () {
        let playerDtos = [];
        for (let player of addictHT.players) {
            let playerDto = {
                name: player.name,
                skills: {}
            };
            for (let skillLabel of Object.keys(player.skills)) {
                playerDto.skills[skillLabel] = {
                    label: skillLabel.split("(")[0],
                    value: getTextBetweenBrackets(player.skills[skillLabel])
                }
            }
            playerDtos.push(playerDto);
        }
        return playerDtos;
    }

    initListeners();
    
    addictHT.authService.checkIfLoggingIn();
}

function initListeners() {
    addictHT.addPlayer = () => {
        if (addictHT.players.length == 3) {
            alert("cannot compare more than 3 players!");
            return;
        }
        let player = addictHT.skillSelector.getPlayerAndClearSelection();
        player.name = "Player " + (addictHT.players.length + 1);
        addictHT.players.push(player);
        addictHT.selectedPlayersDetails.renderDetails(addictHT.players);
    };

    addictHT.removePlayer = (index) => {
        addictHT.players.splice(index, 1);
        addictHT.selectedPlayersDetails.renderDetails(addictHT.players);
    };

    addictHT.onPositionClick = (clicked) => {
        addictHT.positionSelector.onPositionClick(clicked);
        setTimeout(() => {
            addictHT.calculatedContribution.clearAllRatings();
            addictHT.responses = {};
            let client = new AddictHtClient();
            client.analyzePlayers(addictHT.getPlayerDtos());
        }, 200);
    };

    addictHT.readFromClipboard = () => {
        if (addictHT.players.length == 3) {
            alert("cannot compare more than 3 players!");
            return;
        }
        addictHT.clipboardParser.parse((player) => {
            if (player != null) {
                addictHT.players.push(player);
                addictHT.selectedPlayersDetails.renderDetails(addictHT.players);
            }
        });
    };

    addictHT.loadPlayerFromTeam = (name) => {
        console.log(name);
        let player = {};
        addictHT.teamPlayers.forEach((p)=>{
            if(name == p.name){
                player = {
                    name: p.name,
                    skills: {
                        defending: SKILLS[p.defending],
                        keeper: SKILLS[p.goalkeeper],
                        passing: SKILLS[p.passing],
                        playmaking: SKILLS[p.playmaking],
                        scoring: SKILLS[p.scoring],
                        winger: SKILLS[p.winger],
                    }
                };
                addictHT.players.push(player);
                addictHT.selectedPlayersDetails.renderDetails( addictHT.players );
            }
        });

    }


}

function getTextBetweenBrackets(text) {
    return text.split("(")[1].split(")")[0];
}


