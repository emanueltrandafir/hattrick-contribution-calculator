
export class AuthService {


    logInOut(){
        this.isLoggedIn()? this.logOut() : this.logIn();
    }

    logIn(){
        console.log("logging in..");
        this.getAuthorizationUrl();
    }

    logOut(){
        console.log("logging out..");
        localStorage.removeItem("ht_oauth_token");
        localStorage.removeItem("ht_oauth_secret");
        document.getElementById("log-in-out-btn").innerText = "Log In";
        document.querySelector("#team-dropdown").style.display = "none";
    }

    isLoggedIn(){
        return this.getOauthToken() != null && this.getOauthSecret() != null;
    }

    getOauthToken(){
        return localStorage.getItem("ht_oauth_token");
    }
    getOauthSecret(){
        return localStorage.getItem("ht_oauth_secret");
    }

    checkIfLoggingIn(){
        let urlParams = new URLSearchParams(window.location.search);
        let token = urlParams.get('oauth_token');
        let verifier = urlParams.get('oauth_verifier');

        if( this.isLoggedIn() ){
            this.loggedIn();
            return;
        }

        if( token!=null && verifier!=null ){
            //logging in in proccess
            this.authenticate(token, verifier);
            return;
        } 
        else {
            // not logged in and not logging in
        }
    }

    loggedIn(){
        console.log("logged in!");
        document.getElementById("log-in-out-btn").innerText = "Log Out";
        this.makeRequest("GET", "/auth/players?token=" + this.getOauthToken() + "&secret=" + this.getOauthSecret() )
            .then((response)=>{ 
                let players = JSON.parse(response);
                window.addictHT.teamPlayers = players;
                this.showTeam(players);
            });
    }

    showTeam(players){
        document.querySelector("#team-dropdown").style.display = "block";
        let dropdown = document.querySelector("#team-dropdown .dropdown-menu");
        dropdown.innerHTML = "";
        
        players.forEach((p)=>{
            dropdown.innerHTML += `<a class="dropdown-item" onclick="addictHT.loadPlayerFromTeam('` + p.name + `')">` + p.name + `</a>`;
        });
    }

    getAuthorizationUrl(){
        this.makeRequest("GET", "/auth/authorization")
            .then( (authUrl)=>{ window.location.href = authUrl } );
    }

    authenticate(token, verifier){
        this.makeRequest("GET", "/auth/authentification?token=" + token + "&verifier=" + verifier)
            .then((response)=>{ 
                response = JSON.parse(response);
                localStorage.setItem("ht_oauth_token", response.token);
                localStorage.setItem("ht_oauth_secret", response.secret);
                window.location.search = "";
            });
    }

    makeRequest(method, url) {
        return new Promise(function (resolve, reject) {
            var xhr = new XMLHttpRequest();
            xhr.open(method, url);
            xhr.onload = function () {
                if (this.status >= 200 && this.status < 300) {
                    resolve(xhr.response);
                } else {
                    reject({
                        status: this.status,
                        statusText: xhr.statusText
                    });
                }
            };
            xhr.onerror = function () {
                reject({
                    status: this.status,
                    statusText: xhr.statusText
                });
            };
            xhr.send();
        });
    }
}