import { getSkillValue, SKILLS } from './SkillsSelector.js';

export class ClipboardParser {


    parse(callback) {
        let _this = this;
        navigator.clipboard.readText()
            .then(function (clipText) {
                try {
                    return callback(_this.getPlayerSkills(clipText));
                } catch (error) {
                    console.error(error);
                    alert("error parsing the player data, make sure the player was copied in the clipboard properly.")
                }
            });
    }

    getPlayerSkills(text) {
        let rows = text.split("table]")[3].split("(");
        let s = [];
        for (let i = 0; i < rows.length; i++) {
            s.push(rows[i].split(")")[0]);
        }

        return {
            name: text.split(" [playerid")[0],
            skills: {
                keeper: SKILLS[getSkillValue(s[1])],
                defending: SKILLS[getSkillValue(s[2])],
                playmaking: SKILLS[getSkillValue(s[3])],
                winger: SKILLS[getSkillValue(s[4])],
                passing: SKILLS[getSkillValue(s[5])],
                scoring: SKILLS[getSkillValue(s[6])],
            }
        }
    }

}