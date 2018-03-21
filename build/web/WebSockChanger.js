
window.onload = hideForm;
var wsUri = "ws://localhost:8080/VivekCloudSolutions/actions";
var socket = new WebSocket(wsUri);

socket.onmessage = function (evt) {
    onMessage(evt)
};


function showForm() {
    document.getElementById("_form").style.display = "block";
}
function hideForm() {
    document.getElementById("_form").style.display = "none";
}

function submitForm() {
    var form = document.getElementById('_form');

    var procSpeed = document.getElementById('_processorSpeed').value;
    var procCore = document.querySelector('input[name = _processorCore]:checked').value;
    var ramCap = document.getElementById('_ramCap').value;
    var ramSpeed = form.elements['_ramSpeed'].value;
    var net = form.elements['_type'].value;
    addServer(procSpeed, procCore, ramCap, ramSpeed, net);
    form.reset();
    hideForm();


}

function addServer(procSpeed, procCore, ramCap, ramSpeed, networkType) {
    var server = {
        pSpeed: procSpeed,
        pCore: procCore,
        rCap: ramCap,
        rSpeed: ramSpeed,
        nType: networkType,
        action: 'add'
    };
    socket.send(JSON.stringify(server));
}

function removeServer(servid) {
    var server = {
        id: servid,
        action: "remove"
    };
    socket.send(JSON.stringify(server));
}


function onMessage(evt) {
    if(evt.getString(action) == "add"){
    var dt = new Date();
    servers.innerHTML = servers.innerHTML + "<br><b>>></b><font color=white>" + evt.data + "</font>  on:  " + dt.getDate() + ":" + dt.getMonth() + ":" + dt.getFullYear() + " " + dt.getHours() + ":" + dt.getMinutes() + "<a href = '#' onClick = 'removeServer();'>Remove Server</a> \n";
    }
    
}
