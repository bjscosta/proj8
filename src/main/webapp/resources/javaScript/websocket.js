/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri;

if(document.location.protocol === "https:"){
    wsUri = "wss://"+ document.location.host + "/projeto8/whiteboardendpoint";
}
else{
    wsUri = "ws://"+ document.location.host + "/projeto8/whiteboardendpoint";
}

var websocket = new WebSocket(wsUri);

websocket.onerror = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

websocket.onmessage = function(evt) { onMessage(evt) };

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}
                
function onMessage(evt) {
    console.log("received: " + evt.data);
    
    if (typeof evt.data === "string") {
        
        var json = JSON.parse(evt.data);
        
        
        if (json.hasOwnProperty('editing')) {
            showEditing(json.editing);
            
        }
        
        else if(json.hasOwnProperty('aborting')){
            showAborting(json.aborting);
        }

        else{
        drawImageText(evt.data);
        }
    } 
    else {
        drawImageBinary(evt.data);
    }
}

websocket.binaryType = "arraybuffer";

function sendBinary(bytes) {
    console.log("sending binary: " + Object.prototype.toString.call(bytes));
    websocket.send(bytes);
}