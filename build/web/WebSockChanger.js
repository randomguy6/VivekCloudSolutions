
window.onload = hideForm;


//var socket = new Websocket("ws://localhost:8080/VivekCloudSolutions/actions");

var wsUri = "ws://localhost:8080/VivekCloudSolutions/actions";
 var socket = new WebSocket(wsUri); 

//var username; 
 //socket.onopen = function(evt) { onOpen(evt) }; 
 socket.onmessage = function(evt) { onMessage(evt) }; 
 //socket.onerror = function(evt) { onError(evt) }; 
 //var output = document.getElementById("output"); 
 

function showForm() {
     //alert("dd");
    document.getElementById("_form").style.display = "block";
}
function hideForm() {
   // alert("hideForm");
    document.getElementById("_form").style.display = "none";
}



function submitForm() {
  var form = document.getElementById('_form');
  	  
          var procSpeed = document.getElementById('_processorSpeed').value;
  	  //var procCore = document.getElementByName('_processorCore').value;
  	  var procCore = document.querySelector('input[name = _processorCore]:checked').value;
  	 // alert("submit4 "+procCore);
  	  var ramCap = document.getElementById('_ramCap').value;
  	 // alert("submit5 "+ramCap);
  	  var ramSpeed = form.elements['_ramSpeed'].value;
  	 // alert("ramSpeed "+ramSpeed);
  	  var net = form.elements['_type'].value;
         // alert('before add server nettype='+net);
  	 addServer(procSpeed, procCore, ramCap, ramSpeed, net);
    //alert("submit 8");
    form.reset();
    hideForm();

    
}

function addServer(procSpeed, procCore, ramCap, ramSpeed, networkType) {
     //  alert("addServer");
    var server = {
        pSpeed : procSpeed,
        pCore : procCore,
        rCap : ramCap,
        rSpeed : ramSpeed,
        nType : networkType,
        action : 'add'
    };
//alert(JSON.stringify(server));
socket.send(JSON.stringify(server));

}
/*function showServers(server) {
    var doc = document.getElementById("servers");
    
}*/

function onMessage(evt) { 
     //console.log("onMessage: " + evt.data); 
        // serverdetails.innerHTML += evt.data + "\n"; 
       var dt= new Date();
         servers.innerHTML = servers.innerHTML+"<br><b>>></b><font color=white>"+ evt.data + "</font>  on:  "+dt.getDate() +":"+dt.getMonth()+":"+dt.getFullYear()+" "+dt.getHours()+":"+dt.getMinutes() + " \n";
 }
