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
        let s = text.split("[table]")[1]
            .replaceAll("[tr]", "")
            .replaceAll("[/tr]", "")
            .replaceAll("[b]", "")
            .replaceAll("[/b]", "")
            .replaceAll("[i]", "")
            .replaceAll("[/i]", "")
            .replaceAll("[td]", "**")
            .replaceAll("[/td]", "**")
            .split("**");

        return {
            name: text.split(" [playerid")[0],
            skills: {
                stamina: SKILLS[getSkillValue( s[1] )],
                keeper: SKILLS[getSkillValue( s[3] )],
                playmaking: SKILLS[getSkillValue( s[5] )],
                passing: SKILLS[getSkillValue( s[7] )],
                winger: SKILLS[getSkillValue( s[9] )],
                defending: SKILLS[getSkillValue( s[11] )],
                scoring: SKILLS[getSkillValue( s[13] )],
                setpieces: SKILLS[getSkillValue( s[15] )]
            }
        };
    }

}