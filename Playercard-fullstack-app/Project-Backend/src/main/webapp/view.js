


function viewPlayerCards() {
    fetchData("playercard", 'GET', null)
        .then((r) => {
            console.log("Successful");
            console.log('PlayerCard', r);
            return JSON.parse(r);
        }).then((data) => {
            return generateRows(data);
        }).catch((reason) => {
            console.log(reason)
        });
}

function deletePlayer(id) {
    if (confirm('Are you sure you want to delete it?')) {
        fetchData('playercard/' + id, 'DELETE', null)
            .then((x) => {
                console.log(x);
                document.getElementById("players-table").innerHTML = "";

                document.getElementById("viewPlayers").click();
            }).catch((reason) => {
                console.log(reason);
            });
    }

    return false;
}

function generateRows(players) {
    for (let player of players) {
        player.actions = '<a href="update.html?id=' + player.id + '">Update</a><br />' +
            '<a href="#" onclick="deletePlayer(' + player.id + ')">Delete</a> ';
        console.log(player);
        createTableRow(player);
    }
}

function createTableRow(player) {
    let tableRef = document.getElementById("players-table");
    let row = tableRef.insertRow(-1);

    let index = 0;
    for (let key in player) {
        let cell = row.insertCell(index);
        cell.innerHTML = player[key];
        index++;
    }
}