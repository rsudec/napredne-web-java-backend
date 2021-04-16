/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsocket;
function connect() {
    wsocket = new WebSocket("ws://localhost:8084/rsudec_aplikacija_2_pomoc/websocket");
    wsocket.onmessage = onMessage;

}

function onMessage(evt) {
    var msg = evt.data.split(",");
    console.log(msg[0]);
    console.log(msg[1]);
    document.getElementById("brAerodromaTxt").innerHTML = msg[0];
    document.getElementById("datumOsvjezavanja").innerHTML = msg[1];
}
function odaberiAerodrom(usr, aiprt) {
    console.log(usr);
    console.log(aiprt);


    wsocket.send(usr + "," + aiprt);


}
window.addEventListener("load", connect, false);
