// JavaScript Document

isIE=document.all; 
isNN=!document.all&&document.getElementById; 
isN4=document.layers; 
isHot=false;
scrWidth  = screen.width;
scrHeight = screen.height;
limDir = scrWidth - 700;
limInf = scrHeight - 500;

function ddInit(e){ 
	topDog=isIE? "BODY" : "HTML"; 
	whichDog=isIE? document.all.theLayer : document.getElementById("theLayer"); 
	hotDog=isIE? event.srcElement : e.target; 
	while (hotDog.id!="titleBar"&&hotDog.tagName!=topDog){ 
		hotDog=isIE? hotDog.parentElement : hotDog.parentNode; 
	} 
	if (hotDog.id=="titleBar"){ 
		offsetx=isIE? event.clientX : e.clientX; 
		offsety=isIE? event.clientY : e.clientY; 
		nowX=parseInt(whichDog.style.left); 
		nowY=parseInt(whichDog.style.top); 
		ddEnabled=true; 
		document.onmousemove=dd; 
	} 
} 

function dd(e){ 
	if (!ddEnabled) return;
	var vLeft = isIE? nowX+event.clientX-offsetx : nowX+e.clientX-offsetx;
	var vTop  = isIE? nowY+event.clientY-offsety : nowY+e.clientY-offsety;
	if(vLeft>0 && vLeft<limDir){
		whichDog.style.left= vLeft;
	}
	if( vTop>0 && vTop<limInf){
		whichDog.style.top= vTop;
	}
	return false; 
} 

/*function ddN4(whatDog){ 
	if (!isN4) return; 
	N4=eval(whatDog); 
	N4.captureEvents(Event.MOUSEDOWN¦Event.MOUSEUP); 
	N4.onmousedown=function(e){ 
		N4.captureEvents(Event.MOUSEMOVE); 
		N4x=e.x; 
		N4y=e.y; 
	} 
	N4.onmousemove=function(e){ 
		if (isHot){ 
			N4.moveBy(e.x-N4x,e.y-N4y); 
			return false; 
		} 
	} 
	N4.onmouseup=function(){ 
		N4.releaseEvents(Event.MOUSEMOVE); 
	} 
} */

function hideMe(){ 
	if (isIE || isNN){
		whichDog.style.visibility="hidden";
		document.getElementById("bg").style.visibility ="hidden";
	}
	else if (isN4) document.theLayer.visibility="hide"; 
} 

function showMe(){ 
	if (isIE || isNN){
		whichDog.style.visibility="visible";
		document.getElementById("bg").style.visibility ="visible";
	}
	else if (isN4) document.theLayer.visibility="show"; 
} 

document.onmousedown=ddInit; 
document.onmouseup=Function("ddEnabled=false"); 