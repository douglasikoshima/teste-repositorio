//
//  INICIO - Codigo para Abas
//

var IMG_ABA_LEFT = "/FrontOfficeWeb/resources/images/aba_left.gif";
var IMG_ABA_LEFT_OFF = "/FrontOfficeWeb/resources/images/aba_left_off.gif";
var IMG_ABA_RIGHT = "/FrontOfficeWeb/resources/images/aba_right.gif";
var IMG_ABA_RIGHT_OFF = "/FrontOfficeWeb/resources/images/aba_right_off.gif";
var IMG_BKG = "/FrontOfficeWeb/resources/images/aba_bkg.gif";
var IMG_BKG_OFF = "/FrontOfficeWeb/resources/images/aba_bkg_off.gif";

function fraLoadUrlNoCache(frameName, url){
    var d = new Date();
    (url.indexOf("?") == -1) ? 	(url += '?a=' + d.getTime()) : (url += '&a=' + d.getTime());
    //parent.frames[frameName].location.href = url;
    var fonte = document.getElementById(frameName);
    fonte.src = url;
}

function desabilitaMouseOver(){
	event.srcElement.style.cursor = "default";
}

function habilitaMouseOver(){
	event.srcElement.style.cursor = "hand";
}

function Abas(){
	this.arrayAbas = new Array();
	this.idDetalhe = null;
	this.urlDetalhe = "";
	this.habilitadas = true;
}

Abas.prototype.desabilitaAbas = function(){
	if(this.habilitadas){
		var i;
		for(i=0; i<this.arrayAbas.length; i++){
			document.getElementById(this.arrayAbas[i] + "BG").className = document.getElementById(this.arrayAbas[i] + "BG").className + "_dis";
			if(document.getElementById(this.arrayAbas[i]).onclick != null)
				document.getElementById(this.arrayAbas[i]).funcaoClick = document.getElementById(this.arrayAbas[i]).onclick;
			document.getElementById(this.arrayAbas[i]).onclick = null;
			document.getElementById(this.arrayAbas[i]).onmouseover = desabilitaMouseOver;
		}
		this.habilitadas = false;
	}
}

Abas.prototype.habilitaAbas = function(){
	if(!this.habilitadas){
		var i, classe;
		for(i=0; i<this.arrayAbas.length; i++){
			classe = new String(document.getElementById(this.arrayAbas[i] + "BG").className);
			document.getElementById(this.arrayAbas[i] + "BG").className = classe.substr(0, classe.length-4);
			document.getElementById(this.arrayAbas[i]).onmouseover = habilitaMouseOver;
			if(document.getElementById(this.arrayAbas[i]).onclick == null)
				document.getElementById(this.arrayAbas[i]).onclick = document.getElementById(this.arrayAbas[i]).funcaoClick;
		}
		this.habilitadas = true;
	}
}

Abas.prototype.selecionaAba = function(td, carregaPagina, frame){
    if(frame==undefined) frame="fraAbas";
	if(td != null){
		this.liberaAbas();
		document.getElementById(td.id + "AR").src = IMG_ABA_RIGHT;
		document.getElementById(td.id + "AL").src = IMG_ABA_LEFT;
		document.getElementById(td.id + "BG").background = IMG_BKG;
		document.getElementById(td.id + "BG").className = "abaSelected";
		if(carregaPagina){
//			parent.desabilitaSubmit();
/*			if(td.id == this.idDetalhe)
				fraLoadUrlNoCache("fraContato", this.urlDetalhe);
			else */
				fraLoadUrlNoCache(frame, td.url);
		}
	}
}

Abas.prototype.liberaAbas = function(){
	var i;
	for(i=0; i<this.arrayAbas.length; i++){
		document.getElementById(this.arrayAbas[i] + "AR").src = IMG_ABA_RIGHT_OFF;
		document.getElementById(this.arrayAbas[i] + "AL").src = IMG_ABA_LEFT_OFF;
		document.getElementById(this.arrayAbas[i] + "BG").background = IMG_BKG_OFF;
		document.getElementById(this.arrayAbas[i] + "BG").className = "abaUnselected";
	}
}

function mostra(td){
	td.ctrlAbas.selecionaAba(td, true);
}

Abas.prototype.criaAba = function(texto, url){
	var id = "aba" + this.arrayAbas.length;
	this.arrayAbas[this.arrayAbas.length] = id;

	var html = "<td valign=\"bottom\" onclick=\"mostra(this)\" url=\"" + url + "\"onmouseover=\"this.style.cursor='hand';\" id=\"" + id + "\">";
	var elem = document.createElement(html);

	html = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
         " 	<tr>  " +
         "   	<td width='9' height='16'><img src='"+IMG_ABA_LEFT_OFF +"' width='9' height='16' id='" + id + "AL'></td> " +
         "    <td align=\"center\" background='"+IMG_BKG_OFF+"' id='" + id + "BG'>" + texto + "</td> " +
         "    <td width='9' height='16'><img src='"+IMG_ABA_RIGHT_OFF+"' width='9' height='16' id='" + id + "AR'></td> " +
         "  </tr> " +
         "</table> ";

	elem.innerHTML = html;
	document.getElementById("trAbas").appendChild(elem);
	document.getElementById(id).ctrlAbas = this;

	return id;
}

//
//  FIM - Codigo para Abas
//

//
//  INICIO - Codigo para Habilita-Desabilita
//
function desabilitaMouseOver(){
	event.srcElement.style.cursor = "default";
}

function desabilita(objImg, objLink){
	if(objImg == null || objLink == null)
		return;

	if(typeof(objLink.estado) == "undefined" || objLink.estado == "ativo"){
		objLink.funcaoMouseOver = objLink.onmouseover;
		objLink.onmouseover = desabilitaMouseOver;
		objLink.funcaoClick = objLink.onclick;
		objLink.onclick = null;
		objLink.estado = "inativo";

		objImg.textoAlt = objImg.alt;
		objImg.alt = "";

		var imgSrc = objImg.src;
		var iPos = imgSrc.indexOf(".gif");
		imgSrc = imgSrc.substring(0, iPos);
		imgSrc += "_off.gif";
		objImg.src = imgSrc;
	}
}

function habilita(objImg, objLink){
	if(objImg == null || objLink == null)
		return;

  if(typeof(objLink.estado) != "undefined" && objLink.estado == "inativo"){
		objLink.onmouseover = objLink.funcaoMouseOver;
		objLink.onclick = objLink.funcaoClick;
		objLink.estado = "ativo"

		var imgSrc = objImg.src;
		var iPos = imgSrc.indexOf("_off.gif");
		imgSrc = imgSrc.substring(0, iPos);
		imgSrc += ".gif";
		objImg.src = imgSrc;
		objImg.alt = objImg.textoAlt;
	}
}

//
//  FIM - Codigo para Habilita-Desabilita
//