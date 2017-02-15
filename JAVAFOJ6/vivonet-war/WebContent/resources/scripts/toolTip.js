var global_tool_tip_interval = null;

function MSIEBodyReturn(){
 return(document.compatMode&&document.compatMode!="BackCompat")?document.documentElement:document.body;
}

function focaTip(ttipText, itemHeight, itemLeft, combo)
{
    var comboHeight = document.forms[0].all[itemHeight];
    var comboLeft = document.forms[0].all[itemLeft];
    if (ttipText.length > 20)
    {
        moveToolTip = false;
    
        if (comboHeight.offsetLeft > 300)
        {        
            ttipObj.style.left = (comboHeight.offsetLeft + comboLeft.offsetLeft + 6) - (6 * (ttipText.length - 20));
        }
        else if (comboHeight.offsetLeft > 100)
        {        
            ttipObj.style.left = (comboHeight.offsetLeft + comboLeft.offsetLeft - 100) - (6 * (ttipText.length - 20));
        }
        else
        {
            ttipObj.style.left = comboHeight.offsetLeft + comboLeft.offsetLeft + 6;
        }
        ttipObj.style.top = comboHeight.offsetTop + combo.offsetTop + 22;
        ShowTip(ttipText);
    }
}

function focaTipCampos(ttipText, combo, top, left, len)
{
    if (ttipText.length > len)
    {
       moveToolTip = false;
        ttipObj.style.left = left;
        ttipObj.style.top = top;
        ShowTip(ttipText);
    }
}

function ativaTip(ttipText)
{
    moveToolTip = true;
    ShowTip(ttipText);
}

function alteraTip(ttipText)
{
    moveToolTip = false;
    ShowTip(ttipText);
}

function ShowTip(ttipText){

                 var content =

                '<table border="0" cellspacing="0" cellpadding="1" bgcolor="#ffffff"><td>' +

                '<table border="0" cellspacing="0" cellpadding="1" bgcolor=#000000"' + 

                '"><td align="center"><font face="sans-serif" color="#ffffff"' +

                '" size="-2">&nbsp\;' + ttipText +

                '&nbsp\;</font></td></table></td></table>';

    carregaToolTip(content);
     return false;
}

function MoveTip(e){
 if (moveToolTip)
 {
     xPos=(NS6)?e.pageX:event.x+MSIEBodyReturn().scrollLeft;
     yPos=(NS6)?e.pageY:event.y+MSIEBodyReturn().scrollTop;
     lEdge=(xBump<0)?xBump*(-1):-1000;
     rEdge=MSIE&&!window.opera?MSIEBodyReturn().clientWidth-event.clientX-xBump:window.innerWidth-e.clientX-xBump-20;
     bEdge=MSIE&&!window.opera?MSIEBodyReturn().clientHeight-event.clientY-yBump:window.innerHeight-e.clientY-yBump-20;
     if(rEdge<ttipObj.offsetWidth){
      ttipObj.style.left=MSIE?MSIEBodyReturn().scrollLeft+event.clientX-ttipObj.offsetWidth+"px":window.pageXOffset+e.clientX-ttipObj.offsetWidth+"px";
     }
     else if(xPos<lEdge){
      ttipObj.style.left=xBump+"px";
     }
     else{
      ttipObj.style.left=xPos+xBump+"px";
     }
     if(bEdge<ttipObj.offsetHeight){
      ttipObj.style.top=MSIE?MSIEBodyReturn().scrollTop+event.clientY-ttipObj.offsetHeight-yBump+"px":window.pageYOffset+e.clientY-ttipObj.offsetHeight-yBump+"px";
     }
     else{
      ttipObj.style.top=yPos+yBump+"px";
     }
 }
 
}



function HideTip(){
moveToolTip = true;
 if(MSIE||NS6){
  ttipObj.style.display="none";
  ttipObj.innerText="";
 }
}


// NOVA  FUNÇÃO PARA TOOLTIP.

function   showToolTip(obj, tamanho){    
    if (obj.readyState == "complete") {
        if (obj.options[obj.selectedIndex].text.length < tamanho) {     
            hideToolTip();
            return;
        }        
	   var div       = window.document.getElementById('PopupDiv');           
	   div.innerHTML = obj.options[obj.selectedIndex].text;       
	   var iframe    = window.document.getElementById('DivShim');       
	   div.style.display   = "block";       
	   iframe.style.width  = div.offsetWidth;
	   iframe.style.height = div.offsetHeight;
	   iframe.style.top    = div.style.top = window.event.y;       
	   iframe.style.left   = div.style.left = window.event.x+8;
	   iframe.style.zIndex = div.style.zIndex - 1;
	   iframe.style.display= "block";  
       toInterval();
    
    }
}

function intervalHideToolTip(){
    if(global_tool_tip_interval != null){
        hideToolTip();
        window.clearInterval(global_tool_tip_interval);
        global_tool_tip_interval = null;        
    } 
}

function toInterval(){
   window.clearInterval(global_tool_tip_interval);
   global_tool_tip_interval = window.setInterval("intervalHideToolTip()", 5000);  
}

function   hideToolTip(){
       var div    = window.document.getElementById('PopupDiv');
       var iframe = window.document.getElementById('DivShim');
       div.style.display   = "none";
       iframe.style.display= "none";
} 
