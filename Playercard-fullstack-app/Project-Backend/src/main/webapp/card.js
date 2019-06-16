function showDetails() {
    let data = localStorage.getItem("playercard");
    let playercard = JSON.parse(data);
    document.getElementById("pcname").innerHTML = playercard.name;
    document.getElementById("pctag").innerHTML = playercard.gamerTag;
    document.getElementById("pcchar").innerHTML = playercard.character;
}

window.onload = function() {
    showDetails();
}
