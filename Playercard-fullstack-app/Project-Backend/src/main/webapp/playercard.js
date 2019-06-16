

let userName;
let tag;
let char;

let profile = {
    name: userName,
    gamerTag: tag,
    character: char
}

const setName = (name) => { userName = name.value; };
const setGamerTag = (gamerTag) => { tag = gamerTag.value; };
const setCharacter = (character) => { char = character.value; };

function submit() {
    profile.name = userName;
    profile.gamerTag = tag;
    profile.character = char;
}

function newPlayer() {
    submit();
    userData = JSON.stringify(profile);
    fetchData("playercard", 'POST', userData)
        .then((x) => {
            localStorage.setItem("playercard", x);
            window.location = "card.html";
        }).catch((reason) => {
            console.log(reason)
        });
}

function goHome() {
    window.location = "home.html";
}
