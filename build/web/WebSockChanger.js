
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
//  var dropDown = document.getElementById('ramOptions');
    var procSpeed = Number(document.getElementById('_processorSpeed').value);
    var procCore = Number(document.querySelector('input[name = _processorCore]:checked').value);
    var ramCap = Number(document.getElementById('_ramCap').value);
    var ramSpeed = Number(form.elements['_ramSpeed'].value);
    var net = form.elements['_type'].value;
    addServer(procSpeed, procCore, ramCap, ramSpeed, net);
    form.reset();
    hideForm();
}

function addServer(procSpeed, procCore, ramCap, rSpeed, networkType) {
    var ramSpeed = rSpeed;
    if(rSpeed<1000){
        ramSpeed *= 1000;
    }
        
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
    var message = JSON.parse(evt.data);
    if(message.action === "add"){
    showServer(message);
    }
    else if(message.action === "remove"){
        document.getElementById(message.id).remove();
    }
    
}

function showServer(server){
    var servers = document.getElementById("servers");
    var div = document.createElement("div");
        div.setAttribute("id", server.id);
        div.innerHTML = "<b>Ram Speed:</b> ("+server.ramSpeed+" MHz), <b>Ram Capacity:</b> ("+server.ramCap+ "GB), <b>Processor core count:</b> ("+server.processorCore +"-core) <b>Processor speed:</b> ("+server.processorSpeed+ " GHz) <b>Network type:</b> ("+server.Type +"). <a href = '#' onClick = 'removeServer("+server.id+");'>Remove Server</a> \n";
        servers.appendChild(div);
    //var creds = document.createAttribute("span");
    //    creds.innerHTML = server;
    //    div.appendChild(creds);
}
