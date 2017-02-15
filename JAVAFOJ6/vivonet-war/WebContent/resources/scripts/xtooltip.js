document.write('<iframe id="ifrTip" scrolling="no" frameborder="0" style="position:absolute; top:0px; left:0px; display:none;"></iframe>');
document.write('<div id="divTip" style="position:absolute;padding:2px; display:none;background-color:#000000; color:#ffffff; z-index:100;font-size:11px;"></div>');

var ifrTip = window.document.getElementById('ifrTip');
var divTip = window.document.getElementById('divTip');

document.onmousemove = setMouseXY;

var ultimoCombo = null;
var timer;
var oldX=0; 
var oldY=0;
var ativado = false;

function setMouseXY() {
    
    oldX = event.clientX;
    oldY = event.clientY;
    divTip.style.left = oldX+10;
    divTip.style.top  = oldY+10;
    ifrTip.style.left = oldX+10;
    ifrTip.style.top  = oldY+10;
    ifrTip.style.width  = divTip.offsetWidth;
    ifrTip.style.height = divTip.offsetHeight;
    
}

function ativarToolTip(combo, flgTipo) {
    ultimoCombo = combo;
    
    if(flgTipo == null)
    {
        combo.onchange = showAlert;
    }
    
    combo.onmouseout = desativarToolTip;
    resetTimer();
}

function desativarToolTip() {
    if (ultimoCombo != null) {
        var div       = window.document.getElementById('divTip');           
        var iframe    = window.document.getElementById('ifrTip');
        div.innerHTML = '';    
        div.style.display="none";
        iframe.style.display="none";
        //ultimoCombo.onchange = "";
        //ultimoCombo = null;
        clearTimeout(timer);
    }
}

function resetTimer() {
    clearTimeout(timer);
    timer = setTimeout('showAlert()',1000);    
}

function showAlert() {
    clearTimeout(timer);
    timer = setTimeout('desativarToolTip()',10000);
    updateToolTip();
}

function updateToolTip() {
    msg = "";
    oLenght = ultimoCombo.options.length;
    for (i=0; i< oLenght; i++) {
        if (ultimoCombo.options[i].selected) {
             msg += ultimoCombo.options[i].text + "<br>\n";
        }
    }
    if (msg == '') { 
        desativarToolTip(); 
        return;
    }
    var div       = window.document.getElementById('divTip');           
    div.innerHTML = msg;
    var iframe    = window.document.getElementById('ifrTip');

    div.style.left = oldX+10;
    div.style.top  = oldY+10;
    iframe.style.left = oldX+10;
    iframe.style.top  = oldY+10;
    iframe.style.width  = divTip.offsetWidth;
    iframe.style.height = divTip.offsetHeight;

    div.style.display   = "block";       
    iframe.style.display= "block";
    iframe.style.zIndex = divTip.style.zIndex - 1;
}