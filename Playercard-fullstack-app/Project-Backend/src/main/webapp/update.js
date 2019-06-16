let urlParams = new URLSearchParams(window.location.search)
let userName;
let tag;
let char;

function getDetails() {
    fetchData('playercard/' + urlParams.get("id"), 'GET', null)
        .then((x) => {
            let data = JSON.parse(x);
            setFormFields(data);
        }).catch((reason) => {
            console.log(reason);
        });
}

function setFormFields(data) {
    document.getElementById("sName").value = data.name;
    document.getElementById("sTag").value = data.gamerTag;
    document.getElementById("sChar").value = data.character;
    userName = data.name;
    tag = data.tag;
    char = data.character;
}

window.onload = function () {
    getDetails();
}

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


function updateDetails(){
    submit();
    let newDetails = JSON.stringify(profile);
    fetchData('playercard/' + urlParams.get("id"), 'PUT', newDetails)
        .then((x) => {
            console.log(x);
            let msg = "You have successfully updated your record";
            let msgId = document.getElementById("updateMsg");
            msgId.innerHTML= msg;
           msgId.style.color = "#98FB98";
        }).catch((reason) => {
            console.log(reason);
        })
}