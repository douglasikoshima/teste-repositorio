//Demais controles
var timer;
var intervalo;
var y = 0;
var x = 0;
var moScroll;
var linhaSelecionada;

var dataIniScroll;
var objScrollH;
var floaterEndBlink;

//Monta a resolução do cliente
var inResize  = 0;
var topWindow;
var windowWidth;
var windowHeight;

//Elementos para animação
var anTreadWait      = "";
var anTreadMoveLeft  = "";
var anTreadMoveRight = "";
var indiceAniAguarde = 0;
var posAniLeft       = 0;

//Controle das cores da tabela
var trRowSelectClass = "";

//Controle para o resize da janela
function windowResize() {
    try {
        //Monta primeira carga
        if( topWindow == null ) {
            topWindow    = top.document.body;
            windowWidth  = topWindow.clientWidth;
            windowHeight = topWindow.clientHeight;
        }

        //Controla as entradas no resize para uma unica vez
        inResize++;
        if( inResize > 1) {
            inResize = 0;
            return;
        }

        //Redimenciona a animação
        var windowWidthDif = (topWindow.clientWidth - windowWidth);
        posAniLeft += windowWidthDif;
        dvAnimarAguarde.style.left = posAniLeft;

        //Obtém a posição do cliente
        windowWidth   = topWindow.clientWidth;
        windowHeight  = topWindow.clientHeight;
    } catch(e) {}
}

//Função de start da animação de aguarde
function startAnimation() {
    try {
        //Limpa todas as tread
        //window.clearInterval(anTreadWait);
        //window.clearInterval(anTreadMoveLeft);
        //window.clearInterval(anTreadMoveRight);

        //Oculta as imagens
        //imgAguarde[indiceAniAguarde].style.display = 'none';

        //Processa animação
        //indiceAniAguarde              = 0;
        //posAniLeft                    = windowWidth;
        //dvAnimarAguarde.style.left    = posAniLeft;
        //dvAnimarAguarde.style.display = "";
        // anTreadWait                   = window.setInterval( 'animationLoad()', 150 );
        // anTreadMoveLeft               = window.setInterval( 'animationMoveLeft()', 25 );
        mostrar_div();
    } catch(e) {}
}

//Função de stop da animação de aguarde
function stopAnimation() {
    try {
        //window.clearInterval(anTreadMoveLeft);
        // anTreadMoveRight = window.setInterval( 'animationMoveRight()', 25 );
        oculta_div();
    } catch(e) {}
}

//Função de apresentação da animação
function animationLoad() {
    try {
        if( imgAguarde.length > 1 ) {
            imgAguarde[indiceAniAguarde].style.display = 'none';
            indiceAniAguarde++;

            if( indiceAniAguarde == imgAguarde.length ) indiceAniAguarde = 0;
            imgAguarde[indiceAniAguarde].style.display = '';
        } else {
            imgAguarde.style.display = "";
        }
    } catch(e) {}
}

function animationMoveLeft() {
    try {
        if( posAniLeft < (topWindow.clientWidth - (parseInt(tbAnimarAguarde.width) + 14)) ) {
            window.clearInterval(anTreadMoveLeft);
            return;
        }

        posAniLeft -= 5;
        dvAnimarAguarde.style.left = posAniLeft;
    } catch(e) {}
}

function animationMoveRight() {
    try {
        if( posAniLeft >= parseInt(topWindow.clientWidth) ) {
            dvAnimarAguarde.style.display = "none";

            window.clearInterval(anTreadMoveRight);
            window.clearInterval(anTreadWait);
            return;
        }

        posAniLeft += 5;
        dvAnimarAguarde.style.left = posAniLeft;
    } catch(e) {}
}

//Controle das abas
function abaSelected(abas, abaDestion) {
    var tdElement;
    var imgElementLeft;
    var imgElementRight;

    if( abaDestion.className == "abaSelected" )
        return;

    for( x = 1; x < abas.cells.length; x+=3 ) {
        tdElement       = abas.cells(x);
        imgElementLeft  = document.getElementById("AbaLeft_"  + tdElement.id);
        imgElementRight = document.getElementById("AbaRight_" + tdElement.id);

        //Desmonta o selecionado
        if( (tdElement.id != abaDestion.id) && (abas.cells(x).className != "abaUnselected") ) {
            //eval(imgElementLeft  + ".src = '/FrontOfficeWeb/resources/images/aba_left_off.gif'");
            //eval(imgElementRight + ".src = '/FrontOfficeWeb/resources/images/aba_right_off.gif'");
            imgElementLeft.src  = '/FrontOfficeWeb/resources/images/aba_left_off.gif';
            imgElementRight.src = '/FrontOfficeWeb/resources/images/aba_right_off.gif';

            abas.cells(x).className    = "abaUnselected";
            abas.cells(x).background   = "";
            abas.cells(x).style.cursor = "hand";

        //Monta o selecionado
        } else if( (tdElement.id == abaDestion.id) && (abas.cells(x).className == "abaUnselected") ) {
            //eval(imgElementLeft  + ".src = '/FrontOfficeWeb/resources/images/aba_left.gif'");
            //eval(imgElementRight + ".src = '/FrontOfficeWeb/resources/images/aba_right.gif'");
            imgElementLeft.src  = '/FrontOfficeWeb/resources/images/aba_left.gif';
            imgElementRight.src = '/FrontOfficeWeb/resources/images/aba_right.gif';

            abas.cells(x).className    = "abaSelected";
            abas.cells(x).background   = "/FrontOfficeWeb/resources/images/aba_bkg.gif";
            abas.cells(x).style.cursor = "";
        }
    }

    //Especial para ABA CORRESP. DEVOLVIDA na TELA INICIAL!!!
    try{
        if(abas!='btAba' && abaDestion!='bt07'){
            if(document.getElementById('titCDevolvida').style.color == '#ffffff'){
                document.getElementById('titCDevolvida').style.color = '#FF0000';
            }
        }
    }catch(e){
        return;
    }

}

function mostrarCritica () {

    if( !document.all("caixaMovelDeCritica") ) return false;


    var oSrc = window.event.srcElement;

    if(oSrc.textoCritica && oSrc.textoCritica.length > 0){

        if(oSrc.title) {
          oSrc.oldTitle = oSrc.title;
          oSrc.title = "";
        }
        caixaMovelDeCritica.style.display = "";
        if(caixaMovelDeCritica.innerHTML != oSrc.textoCritica) {
            caixaMovelDeCritica.innerHTML = oSrc.textoCritica;
        }
        caixaMovelDeCritica.style.left = (window.event.x - (caixaMovelDeCritica.offsetWidth / 2)) + "px";
        caixaMovelDeCritica.style.top = (window.event.y + 10) + "px";

        if(caixaMovelDeCritica.offsetLeft + caixaMovelDeCritica.offsetWidth > corpoTela.offsetWidth) {
            caixaMovelDeCritica.style.left = (corpoTela.offsetWidth - caixaMovelDeCritica.offsetWidth - 5) + "px";
        }

        if(caixaMovelDeCritica.offsetLeft < 0) {
            caixaMovelDeCritica.style.left = "0px";
        }

        if(caixaMovelDeCritica.offsetTop + caixaMovelDeCritica.offsetHeight > corpoTela.offsetHeight) {
            caixaMovelDeCritica.style.top = (corpoTela.offsetTop - caixaMovelDeCritica.offsetHeight - 5) + "px";
        }

        mbElementosCriticaVisiveis = false;

    }else{
        if( oSrc.oldTitle ) {
          oSrc.title = oSrc.oldTitle;
        }
        escondeCritica();
    }
}

function escondeCritica () {

    if(!caixaMovelDeCritica) return false;

    if(caixaMovelDeCritica.style.display != "none") {
        caixaMovelDeCritica.innerHTML = "";
        caixaMovelDeCritica.style.display = "none";
    }

}

function selecionarLinha () {

  var oSrc = window.event.srcElement;

  if(oSrc.tagName.toUpperCase() == "TD"){

	selecionarLinhaByObj( oSrc.parentElement );

  }

}


function selecionarLinhaByObj ( poLinha ) {
  if( linhaSelecionada )
    linhaSelecionada.className = "";

  linhaSelecionada = poLinha;
  linhaSelecionada.className = "selecionada";
}

function iniciaScroll ( poScroll, plInt ) {

  dataIniScroll = new Date();
  objScrollH = document.all(poScroll.id + "_corpoTabela");
  intervalo = (plInt * 80) * -1;
  aX = objScrollH.offsetTop;

  timerScrollH = window.setInterval('fazScroll()', 10);
}

function iniciaTimerScroll() {
 parar();
 timer = window.setTimeout("fazScroll()", 1);
}

function fazScroll () {
  var dataNow = new Date();
  var tempo = dataIniScroll - dataNow;

  var mover = false;
  var deslocamento = ((tempo / 1000) * intervalo);

  var altura = objScrollH.offsetHeight;
  var alturaPai = objScrollH.parentElement.offsetHeight;
  var deslocMaxNeg = (altura - alturaPai) * -1;

  if ( altura < alturaPai ) return false;

  if( deslocamento > 0 ) {

    if(aX + deslocamento >= 0) {
      objScrollH.style.top = "0px";
    }else{
      mover = true;
    }

  }else{


    if( ( aX + deslocamento ) <= ( deslocMaxNeg ) ) {
      objScrollH.style.top = deslocMaxNeg + "px";
    }else{
      mover = true;
    }

  }

  if(mover) {
    objScrollH.style.top = (aX + deslocamento) + "px";
  }

}

var timerScrollH;

function iniciaScrollH ( poScroll, plInt ) {

  dataIniScroll = new Date();
  objScrollH = poScroll;
  intervalo = plInt;
  aX = objScrollH.offsetLeft;

  timerScrollH = window.setInterval('fazScrollH()', 10);

}

function fazScrollH () {

  var dataNow = new Date();
  var tempo = dataIniScroll - dataNow;

  var mover = false;
  var deslocamento = ((tempo / 1000) * intervalo);

  if( deslocamento > 0 ) {

    if(aX + deslocamento >= 0) {
      objScrollH.style.left = "0px";
    }else{
      mover = true;
    }

  }else{

    var largura = objScrollH.offsetWidth;
    var larguraPai = objScrollH.parentElement.offsetWidth;
    var deslocMaxNeg = (largura - larguraPai) * -1;

    if( ( aX + deslocamento ) <= ( deslocMaxNeg ) ) {
      objScrollH.style.left = deslocMaxNeg + "px";
    }else{
      mover = true;
    }

  }

  if(mover) {
    objScrollH.style.left = (aX + deslocamento) + "px";
  }

}

function parar () {

 if(timerScrollH) clearInterval(timerScrollH);

}

function formataValor( poCampo ) {

	var Campo = poCampo;
	vr = Campo.value;

	var continua = true;
	var numeroUm;

        vr = vr.replace( /[,.]/g ,'');

	if(vr.length == 0){
		return;
	}

	//limpa os zeros a esquerda
	while (continua) {

	   if (vr.length > 0) {
		numeroUm = vr.substr(0,1);

		if ((numeroUm == 0) && (vr.length > 1)) {
			vr = vr.substr(1,vr.length);
		} else if ((numeroUm == 0) && (vr.length == 1)) {
			vr = "";
			continua = false;
		} else {
			continua = false;
		}

	   } else {
		continua = false;
	   }
	}


	// formata o campo
	if (vr.length == 0) {
		vr = "0";
	} else if (vr.length == 1) {
		vr = "0,0" + vr;
	} else if (vr.length == 2) {
		vr = "0," + vr;
	} else {
		vr = vr.substr(0,vr.length - 2) + "," + vr.substr(vr.length - 2, 2);
	}


	Campo.value = vr;
}


function montarXmlTabela( poTabela ) {

  var linha;
  var celula;
  var retorno = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>";

  retorno += "<tabela>\n";

  for(var irow = 0; irow < poTabela.rows.length; irow++) {

    linha = poTabela.rows(irow);

    retorno += "  <linha id=\"" + irow + "\" chave=\"" + linha.chave + "\" status=\"" + linha.status + "\" >\n"

    for(var icel = 0; icel < linha.cells.length; icel++) {

      celula = linha.cells(icel);

      retorno += "    <celula id=\"" + icel + "\"" + ((celula.nome) ? " nome=\"" + celula.nome + "\"" : "") + ">";

      if ((celula.children.length > 0)) {
	  if(celula.children(0).tagName == "INPUT" || celula.children(0).tagName == "SELECT"){
	        if(celula.children(0).type.toUpperCase() == "CHECKBOX"){
                    if(celula.children(0).checked == true)
                        retorno += "true";
                    else
                        retorno += "false";
                }
                else{
                    retorno += celula.children(0).value;
                }
	  }else if(celula.children(0).tagName == "SPAN"){
                retorno += celula.children(0).value;
          }else{
	        retorno += celula.children(0).innerText;
	  }
      } else {
        retorno += celula.innerText;
      }

      retorno += "</celula>\n"

    }

    retorno += "  </linha>\n"

  }

  retorno += "</tabela>";

  return retorno;

}

function desaparecer( poCelula ){

  poCelula.style.display = "none";

}

function inserirLinha(poTabela, poModelo) {

  var textoRelacionado;
  var oRow = poTabela.insertRow();
  var textoRow;
  oRow.chave = poModelo.chave;
  oRow.status = "incluido";
  oRow.indice = poTabela.rows.length - 1;
  for(var i = 0; i < poModelo.cells.length; i++) {
    var oCell = oRow.insertCell();
    if( poModelo.cells(i).key ) {
	    oCell.key = poModelo.cells(i).key;
	}
    oCell.nome      = poModelo.cells(i).nome;
    oCell.innerHTML = poModelo.cells(i).innerHTML;
    oCell.width = poModelo.cells(i).width;
    oCell.align = poModelo.cells(i).align;
    oCell.id = (oCell.nome + poTabela.rows.length);
  }
}

function alterado() {

	var elemento = window.event.srcElement.parentElement.parentElement;

	elemento.status = ((elemento.status == "consulta") ? "alterado" : elemento.status );

}

function excluido() {

	var checkBox = window.event.srcElement;
	var elemento = window.event.srcElement.parentElement.parentElement;

	if(elemento.status != "cancelado" && elemento.status != "excluido") {
		elemento.status = ((elemento.status == "incluido") ? "cancelado" : "excluido");
		elemento.className = "marcadoParaExclusao";
        window.event.srcElement.set = "botaoRemoverV"
        window.event.srcElement.src = '/FrontOfficeWeb/resources/images/' + window.event.srcElement.set + 'Hover.jpg';

	} else {
		elemento.status = ((elemento.status == "excluido") ? "alterado" : "incluido");
		elemento.className = "";
        window.event.srcElement.set = "botaoRemover"
        window.event.srcElement.src = '/FrontOfficeWeb/resources/images/' + window.event.srcElement.set + 'Hover.jpg';
	}
}

function validaTecla( psCaracteresAceitos ) {
    var iloop;
    var elemento;
    var tipoValidacao;
    var carAceitos = psCaracteresAceitos;
    var bRet;

    elemento = window.event.srcElement;

   if ( psCaracteresAceitos == "" ) {
     return true;
   }

   window.event.returnValue = false;
   bRet = false;

   for(iloop=0; iloop <= carAceitos.length; iloop++)
   {
        if(window.event.keyCode == carAceitos.charCodeAt(iloop))
        {
              window.event.returnValue = true;
              bRet = true;
        };
   }

   if(window.event.keyCode == 13)
   {
	    window.event.returnValue = true;
        bRet = true;
   };

   return bRet;

}

function bloqueiaMouse() {
   if (window.event.button == 2) alert("Redigite o conteúdo do campo.");
}

function desabilitaColar() {
   if(((window.event.keyCode == 86 || window.event.keyCode == 118) && window.event.ctrlKey) || (window.event.keyCode == 45 && window.event.shiftKey))    {
	window.event.returnValue = false;
   }
}

function formataCartaoSerie() {
	var Campo = window.event.srcElement;
	var tecla = window.event.keyCode;
	vr = Campo.value;
        var Direita;
        var Esquerda;

	if (vr.length == 7) {
          vr = vr.replace('/','');
	  if (vr.length == 7) {
            vr = vr.substr(0,1) + "/" + vr.substr(1,vr.length -1);
          }
        } else if (vr.length > 7) {
          vr = vr.replace('/','');
          Direita = vr.substr(vr.length - 6, vr.length - 1);
          Esquerda = vr.substr(0, vr.length - 6);
          vr = Esquerda + "/" + Direita;
        }

	Campo.value = vr;

}

function FormataCampo() {
	var Campo = window.event.srcElement;
	var tecla = window.event.keyCode;
	vr = Campo.value;
	var mask = Campo.mascara;
	contadorFormatadores = 0;
	var redutor = 0;

	//if(tecla >= 37 && tecla <=40) return false; //Desabilita a função se a tcla é uma das setas do teclado.

	if((tecla == 8) && (vr.length == (Campo.maxLength - 1)))
	{
		for(var pos = vr.length - 1; pos >= 0; pos--)
		{
			if(mask.charAt(pos) == "#")
			{
				vr = vr.substr(0, pos);
				Campo.value = vr;
				break;
			}
		}
		return false;

	} else {
		if((tecla == 8) && (mask.charAt(vr.length) != "#"))
		{
			vr = vr.substr(0, vr.length - 1);
			Campo.value = vr;
			return false;
		}
	}


	for(var pos = 0; pos < mask.length; pos++)
	{

		if(mask.charAt(pos) != "#")
		{

			if (vr.charAt(pos - redutor) == mask.charAt(pos))
			{
				vr = vr.substr(0, pos - redutor) + vr.substr(pos - redutor + 1, vr.length - 1);
				Campo.value = vr;
				redutor++;
			}
			contadorFormatadores++;
		}
	}

	tam = vr.length;

	if(Campo.maxLength)
	{
		if(tam > (Campo.maxLength - contadorFormatadores))
		{
			vr = vr.substr(0, Campo.maxLength - contadorFormatadores);
		}
	}

	adicional = 0;

	for(var pos = 0; pos <= vr.length; pos++)
	{

		if(mask.charAt(pos) != "#")
		{
			vr = vr.substr(0, pos + adicional) + mask.charAt(pos) + vr.substr(pos + adicional, tam - 1);
			adicional++;
		}
	}

	Campo.value = vr;


}

function changeClass( psNewClass ) {
  window.event.srcElement.className = psNewClass;
}

function voltarTela(psEvento) {
    if(psEvento.length==0)
        alert("A Tela anterior não pôde ser acessada.");

    else {
        document.frmMain.evento.value = psEvento;
        document.frmMain.submit();
    }
}

function validarForm() {

    var lbRetorno = true;
    var loCampo;
    for(var i = 0; i < frmMain.elements.length; i++) {
        loCampo = frmMain.elements(i);

        if( loCampo.tagName.toUpperCase() == "TEXTAREA" || (loCampo.tagName.toUpperCase() == "INPUT" && ( loCampo.type.toUpperCase() == "TEXT" || loCampo.type.toUpperCase() == "PASSWORD" ) )) {

            if( loCampo.parentElement.parentElement.modelo != 'sim' ) {

			    if(loCampo.parentElement.parentElement.status != "excluido" && loCampo.parentElement.parentElement.status != "cancelado" ) {

			         if(!loCampo.readOnly && loCampo.obrigatorio == "sim" && loCampo.value == "") {

			             criticar( loCampo, "Este campo é de preenchimento obrigatório." );

			             lbRetorno = false;

			         } else if( loCampo.tipo == "textoData" && !check_date( loCampo ) ) {

			      		criticar( loCampo, "Data Inválida.<br/>Preencha com uma data no formato \"dd/mm/aaaa\".");

			      		lbRetorno = false;

			         } else if( loCampo.tipo == "textoDataRef" && !check_date_ref( loCampo ) ) {

			      		criticar( loCampo, "Data Inválida.<br/>Preencha com mês e ano no formato \"mm/aaaa\".");

			      		lbRetorno = false;

			         } else if( loCampo.tipo == "textoHorario" && !check_hour( loCampo ) ) {

			      		criticar( loCampo, "Horário Inválido.<br/>Preencha com um horário no formato \"hh:mm\".");

			      		lbRetorno = false;

			      	} else {

			      		removerCritica( loCampo );

			      	}

		        }

            }

        }

    }

    return lbRetorno;

}

function executar( psEvento ) {

  mostrarDivAguarde();
  startAnimarAguarde();
  frmMain.evento.value = psEvento;
  frmMain.submit();

}

function executarComValidacao( psEvento ) {

  if( validarForm() ) {

    executar( psEvento );

  }

}

function formatarDecimais( plQtd ) {

        var poInput = window.event.srcElement;

	poInput.value = poInput.value.replace( /[.,]/g, "" );

	if(poInput.maxLength) {
		if(poInput.value.length > poInput.maxLength -1) {
			poInput.value = poInput.value.substr(0, poInput.maxLength -1);
		}
	}

	if(poInput.value.charAt(0) == "0" && poInput.value.length > 1) {
		poInput.value = poInput.value.substr(1, poInput.value.length);
	}

	if(poInput.value.length > plQtd) {
		var lsintpart = poInput.value.substr(0, poInput.value.length - plQtd);
		var lsdecpart = poInput.value.substr(poInput.value.length - plQtd, plQtd);
		poInput.value = lsintpart + "," + lsdecpart;
	}

}

function focoSePuder( poObj ) {

  if ( ( ( poObj.tagName.toUpperCase() == "INPUT" && poObj.type != "hidden" ) || poObj.tagName.toUpperCase() == "TEXTAREA" || poObj.tagName.toUpperCase() == "SELECT" ) && poObj.style.display != "none" && !poObj.readOnly) {

    //poObj.focus();

  }

}

function check_date(field){
var checkstr = "0123456789";
var DateField = field;
var Datevalue = "";
var DateTemp = "";
var seperator = "/";
var day;
var month;
var year;
var leap = 0;
var err = 0;
var i;
   err = 0;

   DateValue = DateField.value;

   if( DateValue == "" ) return true;

   /* Delete all chars except 0..9 */
   for (i = 0; i < DateValue.length; i++) {
	  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
	     DateTemp = DateTemp + DateValue.substr(i,1);
	  }
   }
   DateValue = DateTemp;
   /* Always change date to 8 digits - string*/
   /* if year is entered as 2-digit / always assume 20xx */
   if (DateValue.length == 6) {
      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
   if (DateValue.length != 8) {
      err = 19;}
   /* year is wrong if year = 0000 */
   year = DateValue.substr(4,4);
   if (year == 0) {
      err = 20;
   }
   /* Validation of month*/
   month = DateValue.substr(2,2);
   if ((month < 1) || (month > 12)) {
      err = 21;
   }
   /* Validation of day*/
   day = DateValue.substr(0,2);
   if (day < 1) {
     err = 22;
   }
   /* Validation leap-year / february / day */
   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
      leap = 1;
   }
   if ((month == 2) && (leap == 1) && (day > 29)) {
      err = 23;
   }
   if ((month == 2) && (leap != 1) && (day > 28)) {
      err = 24;
   }
   /* Validation of other months */
   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
      err = 25;
   }
   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
      err = 26;
   }
   /* if 00 ist entered, no error, deleting the entry */
   if ((day == 0) && (month == 0) && (year == 00)) {
      err = 0; day = ""; month = ""; year = ""; seperator = "";
   }
   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
   if (err == 0) {
      DateField.value = day + seperator + month + seperator + year;
   }
   /* Error-message if err != 0 */
   else {
      return false;
   }

   return true;

}

function check_date_ref(field){

var checkstr = "0123456789";
var DateField = field;
var DateValue = "";
var DateTemp = "";
var seperator = "/";
var day;
var month;
var year;
var leap = 0;
var err = 0;
var i;

   err = 0;
   DateValue = DateField.value;

   if( DateValue == "" ) return true;


   /* Delete all chars except 0..9 */
   for (i = 0; i < DateValue.length; i++) {
	  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
	     DateTemp = DateTemp + DateValue.substr(i,1);
	  }
   }

   DateValue = DateTemp;

   if (DateValue.length == 4) {
      DateValue = DateValue.substr(0,2) + '20' + DateValue.substr(2,2); }

   if (DateValue.length != 6) {
      err = 19;}
   /* year is wrong if year = 0000 */
   year = DateValue.substr(2,4);
   if (year == 0) {
      err = 20;
   }
   /* Validation of month*/
   month = DateValue.substr(0,2);
   if ((month < 1) || (month > 12)) {
      err = 21;
   }

   /* if no error, write the completed date to Input-Field (e.g. 12.2001) */
   if (err == 0) {
      DateField.value = month + seperator + year;
   }

   /* Error-message if err != 0 */
   else {
      return false;
   }

   return true;

}

function check_hour(field){

var checkstr = "0123456789";
var DateField = field;
var Datevalue = "";
var DateTemp = "";
var seperator = ":";
var horas;
var minutos;
var err = 0;
var i;

   err = 0;
   DateValue = DateField.value;

   if( DateValue == "" ) return true;


   /* Delete all chars except 0..9 */
   for (i = 0; i < DateValue.length; i++) {
	  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
	     DateTemp = DateTemp + DateValue.substr(i,1);
	  }
   }

   DateValue = DateTemp;

   if (DateValue.length != 4) {
      err = 19;
   }

   horas = DateValue.substr(0,2);
   if ( (horas < 0) || (horas > 23) ) {
      err = 20;
   }

   minutos = DateValue.substr(2,2);
   if ((minutos < 0) || (minutos > 59)) {
      err = 21;
   }

   /* Se não deu erro... */
   if (err == 0) {
      DateField.value = horas + seperator + minutos;
   }

   /* Error-message if err != 0 */
   else {
      return false;
   }

   return true;

}

function criticar( poCampo, psMsg ) {

  poCampo.className = "textCriticado";
  poCampo.textoCritica = psMsg;

}

function removerCritica( poCampo ) {

  var lsClasse;

  if(poCampo.className != "textBloqueado") {

	if(poCampo.obrigatorio == "sim") {
	  lsClasse = "textObrigatorio";
	} else {
	  lsClasse = "text";
	}

	poCampo.textoCritica = "";
	poCampo.className = lsClasse;

  }

}

function normalizar( poCampo ) {

  var lsClasse;

  if(poCampo.obrigatorio == "sim") {
    lsClasse = "textObrigatorio";
  } else {
    lsClasse = "text";
  }

  poCampo.textoCritica = "";
  poCampo.className = lsClasse;

}

function desabilitar( poCampo ) {

    if( poCampo.tagName.toUpperCase() == "SELECT") {
      poCampo.disabled = true;
    }

    poCampo.textoCritica = "";
    poCampo.className = "textBloqueado";
    poCampo.readOnly = true;

}

function habilitar( poCampo ) {

    if( poCampo.tagName.toUpperCase() == "SELECT") {
      poCampo.disabled = false;
    }

    normalizar( poCampo );
    poCampo.readOnly = false;

}

  function hoverMeTreeView( poObj ) {

    if(!poObj.classePadrao) {
      poObj.className = "textoHover";
    }else{
      poObj.className = poObj.classePadrao + "Hover";
    }

  }

  function unHoverMeTreeView( poObj ) {

    var loItem = poObj.parentElement.parentElement.parentElement;

    if( poObj.classePadrao ) {

      if( loItem.selecionado == "sim" ) {
        poObj.className = poObj.classePadrao + "Selecionado";
      }else{
        poObj.className = poObj.classePadrao;
      }

    }else{

      if( loItem.selecionado == "sim" ) {
        poObj.className = "textoSelecionado";
      }else{
        poObj.className = "texto";
      }

    }

  }

function selecionarItemTreeView( poObj ) {
    var loItem = poObj.parentElement.parentElement.parentElement;
    var loSpan = loItem;

    while( loSpan.tipo != "arvore" )
      loSpan = loSpan.parentElement;

    var loTexto;

    if( loSpan.multiSelecao == "sim" ) {

      if ( loItem.selecionado == "sim" ) {

        loItem.selecionado = "nao";
        if( !poObj.classePadrao ) {
          poObj.className = "texto";
        }else{
          poObj.className = poObj.classePadrao;
        }

      } else {

        loItem.selecionado = "sim";
        if( !poObj.classePadrao ) {
          poObj.className = "textoSelecionado";
        }else{
          poObj.className = poObj.classePadrao + "Selecionado";
        }

      }

    } else {

      if( loItem.selecionado != "sim" ) {

        for( var i = 0; i < loSpan.all.tags("table").length; i++ ) {

          if( loSpan.all.tags("table")[i].selecionado == "sim" ) {
            loTexto = loSpan.all.tags("table")[i].rows(0).cells( loSpan.all.tags("table")[i].rows(0).cells.length - 1 );
            loTexto.className = "texto";
            loSpan.all.tags("table")[i].selecionado = "nao";
          }
        }

        loItem.selecionado = "sim";
        if( !poObj.classePadrao ) {
          poObj.className = "textoSelecionado";
        }else{
          poObj.className = poObj.classePadrao + "Selecionado";
        }

      }
    }
  }

  function mudarTodosTreeView ( poTreeView, pbSelecionado) {

    var loSpan = poTreeView;
    var loTexto;

    for( var i = 0; i < loSpan.all.tags("table").length; i++ ) {
        loTexto = loSpan.all.tags("table")[i].rows(0).cells( loSpan.all.tags("table")[i].rows(0).cells.length - 1 );

        if( pbSelecionado ) {

          loSpan.all.tags("table")[i].selecionado = "sim";

          if( !loTexto.classePadrao ) {
            loTexto.className = "textoSelecionado";
          }else{
            loTexto.className = loTexto.classePadrao + "Selecionado";
          }

        }else{

          loSpan.all.tags("table")[i].selecionado = "nao";

          if( !loTexto.classePadrao ) {
            loTexto.className = "texto";
          }else{
            loTexto.className = loTexto.classePadrao;
          }

        }
    }

  }

  function marcarTodosTreeView ( poTreeView ) {

    mudarTodosTreeView ( poTreeView, true );

  }

  function desmarcarTodosTreeView ( poTreeView ) {

    mudarTodosTreeView ( poTreeView, false );

  }

  function montarXmlTreeView ( poTreeView ) {


    var lsRetorno = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<arvore>\n" + montarRetornoXmlTVW( poTreeView, "" ) + "</arvore>";

    return lsRetorno;

  }

  function mudarEstadoDeItemTvw( poImg ) {

    var loSpanFilho = poImg.parentElement.parentElement.parentElement.parentElement.parentElement.lastChild;

    if( poImg.escondido == "sim" ) {
      loSpanFilho.style.display = '';
      poImg.escondido = "nao";

      if(poImg.ultimo == "sim") {
        poImg.src = "/FrontOfficeWeb/resources/images/lastnodeminus.gif";
      }else{
        poImg.src = "/FrontOfficeWeb/resources/images/nodeminus.gif";
      }

    }else{

      loSpanFilho.style.display = 'none';
      poImg.escondido = "sim";

      if(poImg.ultimo == "sim") {
        poImg.src = "/FrontOfficeWeb/resources/images/lastnodeplus.gif";
      }else{
        poImg.src = "/FrontOfficeWeb/resources/images/nodeplus.gif";
      }
    }
  }

  function montarRetornoXmlTVW ( poTV, psRetorno ) {

    var lsRetorno = psRetorno;

    for( var idiv = 0; idiv < poTV.children.length; idiv++ ) {

      var loDiv = poTV.children[idiv];

      if( loDiv.tagName == "DIV" ) {

        var loItem = loDiv.children(0);

        var lsCodigo = loItem.chave;
        var lsNodeId = loItem.itemNodeId;
        var lsDesc = loItem.rows(0).cells( loItem.rows(0).cells.length - 1 ).innerText;
        var lsImg = null;


        if ( ( loItem.rows(0).cells.length > 1 ) && ( loItem.rows(0).cells( loItem.rows(0).cells.length - 2 ).children(0).tagName == "IMG" ) ) {
          if(loItem.rows(0).cells( loItem.rows(0).cells.length - 2 ).children(0).tagImagem) {
            lsImg = loItem.rows(0).cells( loItem.rows(0).cells.length - 2 ).children(0).tagImagem;
          }
        }

        lsRetorno = lsRetorno + "<item"

        if( loItem.rows(0).cells( loItem.rows(0).cells.length - 1 ).classePadrao ) {
          lsRetorno = lsRetorno + " classe=\"" + loItem.rows(0).cells( loItem.rows(0).cells.length - 1 ).classePadrao + "\"";
        }

        if(loItem.selecionado == 'sim') {
          lsRetorno = lsRetorno + " selecionado=\"sim\"";
        }
        if(loItem.selecionadoInicial == 'sim') {
          lsRetorno = lsRetorno + " selecionadoInicial=\"sim\"";
        }

        lsRetorno = lsRetorno + " node-id=\"" + lsNodeId + "\"";

        lsRetorno = lsRetorno + ">\n";

        lsRetorno = lsRetorno + "<codigo>" + lsCodigo + "</codigo>\n";
        lsRetorno = lsRetorno + "<descricao>" + lsDesc + "</descricao>\n";

        if( lsImg != null ) {
          lsRetorno = lsRetorno + "<imagem>" + lsImg + "</imagem>\n";
        }

        if( loDiv.children.length > 1 ) {
          lsRetorno = montarRetornoXmlTVW ( loDiv.children(1), lsRetorno );
        }


        lsRetorno = lsRetorno + "</item>\n";

      }

    }

    return lsRetorno;

  }

  function mostrarDivAguarde() {

    conteudo.style.display = "none";

	if( document.all("imgMenu") ) {

	    document.all("imgMenu").style.display = "none";
	}

	statusBar.innerText = "";

    aguarde.style.display = "";
    doAnimarAguarde();
    startAnimarAguarde();

  }

var aniAguarde;
var indiceAniAguarde = 0;

function startAnimarAguarde() {

	aniAguarde = window.setInterval( 'doAnimarAguarde()', 100 );

}

function doAnimarAguarde() {


	if( imgAguarde.length > 1 ) {

		imgAguarde[indiceAniAguarde].style.display = 'none';
		indiceAniAguarde++;
		if( indiceAniAguarde == imgAguarde.length ) indiceAniAguarde = 0;
		imgAguarde[indiceAniAguarde].style.display = '';

	}else{
		imgAguarde.style.display = "";
	}

}

function stopAnimarAguarde() {

	window.clearTimeOut( aniAguarde );
    conteudo.style.display = "";
    aguarde.style.display = "none";

}

function mascarar( psMask, poCampo ) {



var poControle = (( poCampo ) ? poCampo : window.event.srcElement );
//var poControle = window.event.srcElement;
var mask = psMask;
var val = poControle.value;
var newVal = "";
var atraso = 0;
var type = "";

//Silvio Cicoti - 27/08 Corrige inserção de dados por mouse e teclado

type = document.customerProspectSearchForm.parameterType.value;

//tira os espaços em branco, exceto do nome.
if(type!="Name")
{
	val = val.split(" ");
	val = val.join("");
}

if(type!="Name" && type!="ESN")
{
	if(!validaTecla(val))
	{
		for(var i = 0;i < val.length;i++)
		{
			if (!isNaN(val.charAt(i)) )
			{
				newVal = newVal + val.charAt(i);
			}
		}
		val = newVal;
	}
}

// Ate aqui. Silvio Cicoti.

if(mask.length ==0) return false;

atraso = 0;

poControle.maxLength = mask.length;

for(var i=0; i < mask.length; i++) {


  if( mask.charAt(i) != "#" ) {

    if( mask.charAt(i) == val.charAt(i - atraso) ) {

      val = val.substring(0, i - atraso) + val.substring(i - atraso + 1, val.length);
      atraso++;

    }

  }

  if( (i - atraso) >= (val.length ) ) {
    break;
  }

}

atraso=0;

newVal = "";

for(var i=0; i < mask.length; i++) {

  if( mask.charAt(i) == "#" ) {

    newVal += val.charAt(i - atraso);

  } else {

    if( !((i - atraso) >= (val.length )) ) {
      newVal += mask.charAt(i);
      atraso++;
    }

  }

  if( (i - atraso) >= (val.length ) ) {
    break;
  }

}

poControle.value = newVal;

}

//------------------------ SCROLL

function sincronizarScrollLateral( poDivTabela ) {


	var divSrc = poDivTabela.children(0).rows(1).cells(0).children(0);
	var divSync = poDivTabela.children(0).rows(0).cells(0).children(0);

	if( divSync.parentElement.parentElement.style.display == "none" ) {
		divSync = divSrc;
		divSrc = poDivTabela.children(0).rows(2).cells(0).children(0);
	}

	if( divSrc.parentElement.parentElement.style.display == "none" ) {
		divSrc = poDivTabela.children(0).rows(2).cells(0).children(0);
	}

	divSync.scrollLeft = divSrc.scrollLeft;


}

function inserirLinhaComDados() {

	var myArgs = inserirLinhaComDados.arguments;
	var myTable = myArgs[0];
	var myTemplate = myArgs[1];
	var myDoScroll = myArgs[2];
	var lineKey = ((myArgs[3]) ? myArgs[3] : "");
	var myNewRow;
	var myCell;
	var cellCount = 0;
	var mySon;

	inserirLinha( myTable, myTemplate );

	myNewRow = myTable.rows( myTable.rows.length -1 );
	myNewRow.chave = lineKey

	for( var i=4; i< myArgs.length; i++ ) {

		myCell = myNewRow.cells[ cellCount ];
		myCell.key = myArgs[i];
		i++;

		if( myCell.children(0) ) {

			mySon = myCell.children(0);

			if( (mySon.tagName.toUpperCase() == "INPUT" && mySon.type.toUpperCase() != "CHECKBOX" ) || (mySon.tagName.toUpperCase() == "SELECT") ) {

				mySon.value = myArgs[i];

			} else if(mySon.tagName.toUpperCase() == "INPUT" && mySon.type.toUpperCase() == "CHECKBOX" ) {


				mySon.checked = myArgs[i];
			}

		} else {

			myCell.innerHTML = myArgs[i];

		}


		cellCount++;

	}

	if( myDoScroll ) {

		myTable.parentElement.scrollTop = 99999999;

	}


}

function removerLinha( poLinha ){

	var myTable = poLinha.parentElement.parentElement;

	for( var i=0; i<myTable.rows.length; i++) {

		if(poLinha == myTable.rows(i)) {
			myTable.deleteRow(i);
			break;
		}

	}

}

function limparTabela( poTabela ) {

	var myTable = poTabela;

	while( poTabela.rows.length > 0 ) {
		poTabela.deleteRow(0);
	}

}

function verificarExistenciaDeValor () {

	var myArgs = verificarExistenciaDeValor.arguments;

	var myTable = myArgs[0];
	var myRow;
	var val1;
	var val2;
	var ignoreCase;
	var encontrou = false;



	for( var i=0; i < myTable.rows.length; i++) {

		myRow = myTable.rows(i);

		encontrou = true;

		for ( var iArg = 1; iArg < myArgs.length; iArg+=3 ) {

			if( myRow.cells(myArgs[iArg]).key != "" ) {
				val1 = myRow.cells(myArgs[iArg]).key;
			}else{
				val1 = trim(myRow.cells(myArgs[iArg]).innerText);
			}

			if( myArgs.length >= (iArg + 1) ) {
				val2 = myArgs[iArg +1];
			}else{
				val2 = "";
			}

			if( myArgs.length >= (iArg + 2) ) {
				ignoreCase = myArgs[iArg + 2];
			}else{
				ignoreCase = false;
			}

			if( ignoreCase ) {

				val1 = val1.toUpperCase();
				val2 = val2.toUpperCase();

			}

			if( val1 != val2 ) {
				encontrou = false;
				break;
			}

		}

		if(encontrou) break;

	}

	return encontrou;

}

function controlDependentQuestion( poComboBox ) {

	var myOption = null;
	var myDep;

	for(var i=0; i<poComboBox.options.length; i++) {

		myOption = poComboBox.options(i);

		if( myOption.dependent ) {

			myDep = document.all("div_holder_" + myOption.dependent)

			if( myDep ) {

				if( myOption.selected || (myOption.dependent == poComboBox.options( poComboBox.selectedIndex ).dependent ) ) {
					myDep.style.display = "";
				}else{
					myDep.style.display = "none";
				}

			}

		}

	}

}

function criarHiddenFieldsDeTabela( poTabela, poForm ) {

  var linha;
  var celula;
  var retorno = "";
  var myName = "";

  var headerRow = poTabela.parentElement.parentElement.parentElement.parentElement.parentElement;
  headerRow = headerRow.rows(0).cells(0).children(0).children(0).rows(0);

  var headerCell = null;

  for(var irow = 0; irow < poTabela.rows.length; irow++) {

    linha = poTabela.rows(irow);

    for(var icel = 0; icel < linha.cells.length; icel++) {

      celula = linha.cells(icel);
      headerCell = headerRow.cells(icel);

      if( headerCell.nome ) {
	    myName = headerCell.nome.replace("@", irow);
	  } else {
	  	myName = "notNamed";
	  }

      retorno += "<input type=\"hidden\" name=\"" + myName + "\" id=\"" + myName + "\" value=\"" ;

	  if( celula.key && celula.key != "" ){

	        retorno += celula.key;

	  }else{

	      if ((celula.children.length > 0)) {
		  if(celula.children(0).tagName == "INPUT" || celula.children(0).tagName == "SELECT"){
		        if(celula.children(0).type.toUpperCase() == "CHECKBOX"){
	                    if(celula.children(0).checked == true)
	                        retorno += "true";
	                    else
	                        retorno += "false";
	                }
	                else{
	                    retorno += celula.children(0).value;
	                }
		  }else if(celula.children(0).tagName == "SPAN"){
	                retorno += celula.children(0).value;
	          }else{
		        retorno += trim(celula.children(0).innerText);
		  }
	      } else {
	        retorno += trim(celula.innerText);
	      }

	  }

      retorno += "\" />\n"

    }

  }

  if(poForm) {
	  poForm.insertAdjacentHTML("beforeEnd", retorno);
	  return true;
  }else{
      return false;
  }

}

function hoverRow() {
	var poRow = window.event.srcElement.parentElement;

	while( !(poRow.parentElement) && (poRow.tagName != "TR") )
		poRow = poRow.parentElement;

	if( linhaSelecionada != poRow ) {
        //Controle do estilo
        var trRowSelectionado;

        //Obtém a cor da linha corrente
        trRowSelectClass = poRow.className;

        //Verifica se é do tipo alerta
        if(      poRow.className == "rowTabelaAlertaAlto"  )    trRowSelectionado = "rowTabelaAlertaAltoHover";
        else if( poRow.className == "rowTabelaAlertaMedio")     trRowSelectionado = "rowTabelaAlertaMedioHover";
        else if( poRow.className == "rowTabelaAlertaBaixo" )    trRowSelectionado = "rowTabelaAlertaBaixoHover";
        else                                                    trRowSelectionado = "selecionavelHover";

        //Monta a cor da linha
		poRow.className = trRowSelectionado;
	}
}

function unhoverRow() {
	var poRow = window.event.srcElement.parentElement;

	while( !poRow.parentElement && !(poRow.tagName == "TR") )
		poRow = poRow.parentElement;

	if( linhaSelecionada != poRow ) {
		poRow.className = trRowSelectClass;
	}
}

function hoverHeader() {
	var poHeader = window.event.srcElement;

	while( poHeader.parentElement && ( poHeader.tagName != "TD" ) )
		poHeader = poHeader.parentElement;

	poHeader.className = "hover";
}

function unhoverHeader() {
	var poHeader = window.event.srcElement;

	while( poHeader.parentElement && ( poHeader.tagName != "TD" ) )
		poHeader = poHeader.parentElement;

	poHeader.className = "normal";
}

function limparLinhaSelecionada() {
	if( linhaSelecionada ) {
		linhaSelecionada.className = ""	;
		linhaSelecionada = null;
	}
}

var tabSelecionada;

function hoverTab( poTab ) {
	if( tabSelecionada != poTab )
		poTab.className = "hover"
}

function unhoverTab( poTab ) {
	if( tabSelecionada != poTab )
		poTab.className = "normal"
}

function selecionarTab( poTab ) {
	var loConteudo;

	if( tabSelecionada ) {
		tabSelecionada.className = "normal"
		loConteudo = document.all(tabSelecionada.conteudo);
		if( loConteudo )
			loConteudo.style.display = "none";
	}

	if( poTab ) {
		poTab.className = "selecionada"
		loConteudo = document.all(poTab.conteudo);
		if( loConteudo )
			loConteudo.style.display = "";

		tabSelecionada = poTab;
	}
}

function ordenarTabelaEx( poTabela, plIndiceOrdenacao, psTipo ) {
	var lsNomeTabela = poTabela.id;
	var loTabela = poTabela;

	var imgSeta;
	var i = 0;

	var loCelulaAtual;

	var loCelula = window.event.srcElement;

	var loTabelaSrc;

	while( loCelula.parentElement && ( loCelula.tagName != "TD" ) )
		loCelula = loCelula.parentElement;

	loTabelaSrc = loCelula.parentElement.parentElement.parentElement;

	if( loTabela.indiceOrdenacao ) {
		loCelulaAtual = loTabelaSrc.rows(0).cells( parseInt(loTabela.indiceOrdenacao) );

		for( i=0; i < loCelulaAtual.getElementsByTagName("SPAN").length; i++ ){
			if( loCelulaAtual.getElementsByTagName("SPAN")(i).indicadorOrdenacao == "sim" ) {
				imgSeta = loCelulaAtual.getElementsByTagName("SPAN")(i);
			}
		}
	}

	if( loCelula != loCelulaAtual ) {
		if( imgSeta )
			imgSeta.outerHTML = "";

		loCelula.insertAdjacentHTML( "beforeEnd", "<span class='setaAbaixo' indicadorOrdenacao='sim' />" );
		imgSeta = loCelula.children( loCelula.children.length - 1 );

		loTabela.ordenado = "D";
	}

	if( !loTabela.ordenado || loTabela.ordenado.toUpperCase() == "D" ){
		ordenarTabela( loTabela, plIndiceOrdenacao, true, psTipo );
		loTabela = document.all( lsNomeTabela );
		loTabela.ordenado = "A";
		imgSeta.className = "setaAbaixo";

	} else {
		ordenarTabela( loTabela, plIndiceOrdenacao, false, psTipo );
		loTabela = document.all( lsNomeTabela );
		loTabela.ordenado = "D";
		imgSeta.className = "setaAcima";
	}

	loTabela.indiceOrdenacao = plIndiceOrdenacao.toString();

	loCelula.atualOrdenada = "sim";
}

function ordenarTabela( poTabela, plIndiceOrdenacao, pbAscendente, psTipo ) {

	var linha;
	var linhaAnterior;
	var valor;
	var valorAnterior;
	var arrIndice = new Array();
	var indiceTemp;
	var alterou = true;
	var inverter = false;
	var novoHtml;
	var strTemp;

	var indiceLinhaSelecionada;
	var i;

    //Controles para zebragem
    var zebrarID = 1;
    var zebradoClass = new Array("rowTabelaZebradoOn", "rowTabelaZebradoOff");   //"Zebrado ON", "Zebrado OFF"
    var rowElement;
    var classElement;

	if( poTabela.rows.length > 1 ) {
		for( i=0; i < poTabela.rows.length; i++) {
			if(linhaSelecionada && poTabela.rows(i) == linhaSelecionada)
				indiceLinhaSelecionada = i;

			arrIndice[i] = i;
		}

		while( alterou ) {
			alterou = false;

			linha = poTabela.rows(0);

			for( i=1; i < poTabela.rows.length; i++) {
				linhaAnterior = poTabela.rows( arrIndice[i - 1] );
				linha = poTabela.rows( arrIndice[i] );
				valor = linha.cells( plIndiceOrdenacao ).innerText;
				valorAnterior = linhaAnterior.cells( plIndiceOrdenacao ).innerText;

				inverter = false;

				if(psTipo == "date" || psTipo == "" || psTipo == "string") {

					if( psTipo == "date" ) {
						valor = dataLimpaAnoMesDia( valor );
						valorAnterior = dataLimpaAnoMesDia( valorAnterior );
					}

					if( valor.toUpperCase() < valorAnterior.toUpperCase() ) {
						inverter = true;
					}

				} else if( psTipo == "number" ){
					if( ( ( !isNaN( parseFloat( valor.replace( /,/g , "" ) ) ) ) ? parseFloat( valor.replace( /,/g , "" ) ) : 0 ) < ( (!isNaN( parseFloat( valorAnterior.replace( /,/g , "" ) ) ) ) ? parseFloat( valorAnterior.replace( /,/g , "" ) ) : 0 ) ) {
						inverter = true;
					}
				}

				if(inverter) {
					alterou = true;
					indiceTemp = arrIndice[i];
					arrIndice[i] = arrIndice[i - 1];
					arrIndice[i - 1] = indiceTemp;
				}
			}
		}

		novoHtml = "<tbody>";

		if(pbAscendente) {
			for( i = 0; i < arrIndice.length; i++ ) {
                //Obtém a linha corrente
                rowElement = poTabela.rows( arrIndice[i] ).outerHTML

                //Plota a zebragem se necessário
                if( poTabela.rows(i).zebrado == "S" ) {
                    if( zebrarID == 0 ) zebrarID = 1;
                    else                zebrarID = 0;

                    //Obtém o nome da classe
                    classElement = classAtribute(rowElement);

                    //So plota se necessário
                    if( classElement != ("class=" + (zebrarID == 0 ? zebradoClass[0] : zebradoClass[1])) )
                        rowElement   = rowElement.replace(classElement, "class=" + (zebrarID == 0 ? zebradoClass[0] : zebradoClass[1]) )
                }

                novoHtml += rowElement;
            }

		} else {
			for( i = arrIndice.length - 1; i >= 0; i-- )
				novoHtml += poTabela.rows( arrIndice[i] ).outerHTML;
		}

		novoHtml += "</tbody>"

		strTemp = poTabela.outerHTML;

		strTemp = strTemp.replace( poTabela.innerHTML, novoHtml );

		poTabela.outerHTML = strTemp;

		if(linhaSelecionada) {
			//Aqui, a referencia em obj da tabela já se perdeu. Reatando referencia:
			poTabela = document.all( poTabela.id );

			//com a referencia reatada, precisa re-selecionar a linha que estava selecionada
			//achando o indice que foi guardado no começo da funcao e re-selecionando a linha
			//equivalente.

			for( i = 0; i < arrIndice.length; i++ ) {
                if( arrIndice[i] == indiceLinhaSelecionada ) {
                    if(!pbAscendente) {
                        i = arrIndice.length - i - 1;
                    }
                    linhaSelecionada = poTabela.rows(i);
                    break;
                }
			}
		}
	}
}

function dataLimpaAnoMesDia( psData ) {
	var lsData = psData.replace( /[ \.\/\-\,\_\|\\\:]/g, "" );
	var lsHora = "";

	if( lsData.length >= 14) {
		lsData = lsData.substr(0,14)
		lsHora = lsData.substr(8, 6);
		lsData = lsData.substr(0, 8);
	}

	if(lsData.length == 6){
		lsData = "20" + lsData.substr(4,2) + lsData.substr(2,2) + lsData.substr(0,2);
	}else if (lsData.length == 7){
		lsData = "0" + lsData.substr(4,3) + lsData.substr(2,2) + lsData.substr(0,2);
	}else if (lsData.length == 8){
		lsData = lsData.substr(4,4) + lsData.substr(2,2) + lsData.substr(0,2);
	}

	return lsData + lsHora;
}

function transferirSelecionada( poTabelaDestino ) {

	if( !linhaSelecionada ) {
		alert("Não há nenhuma linha selecionada");
		return false;
	}

	if( linhaSelecionada.parentElement.parentElement == poTabelaDestino	)
		alert("A linha selecionada já faz parte da lista de destino.");

	else {
		transferirLinha( linhaSelecionada, poTabelaDestino );
		selecionarLinhaByObj( poTabelaDestino.rows( poTabelaDestino.rows.length - 1 ) );
		ordenarTabela( poTabelaDestino, 0, true, "string" );
	}
}

function transferirTodas( poTabelaOrigem, poTabelaDestino ) {

	if( linhaSelecionada && (linhaSelecionada.parentElement.parentElement == poTabelaOrigem)	) {
		limparLinhaSelecionada();
	}

	while( poTabelaOrigem.rows.length > 0 ) {

		transferirLinha( poTabelaOrigem.rows( 0 ), poTabelaDestino );

	}

	ordenarTabela( poTabelaDestino, 0, true, "string" );

}

function transferirLinha( poLinha, poTabelaDestino ) {

	inserirLinha( poTabelaDestino, poLinha );
	removerLinha( poLinha );

}

function ltrim(value) {
	return value.replace( /(^ +)/g, ""  );
}

function rtrim(value) {
	return value.replace( /( +$)/g, ""  );
}

function trim(value) {
	return value.replace( /(^ +)|( +$)/g, ""  );
}

function fecharQuadroFlutuante() {

	var oSrc = window.event.srcElement;
	var oQuadro = oSrc.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement;

	oQuadro.style.display = "none";

}

function createFloater( psId, psContent, plWidth, plHeight, poVertical, poBotaoFechar ) {

	var myBody = corpoTela;
	var imgSemaphore = window.event.srcElement;

	var html = "<div id='floater_" + psId + "' style='position: absolute; width: 250px; height: 57px;'>" + psContent + "</div>";

	var objOffSetTop;

	var sumLeft = 0;
	var sumTop = 0;

	myBody.insertAdjacentHTML( "beforeEnd", html );

	var newObj = document.all( "floater_" + psId );


	objOffSetTop = imgSemaphore;

	while( objOffSetTop != myBody ) {

		sumLeft += ( objOffSetTop.clientLeft + objOffSetTop.offsetLeft - objOffSetTop.scrollLeft);
		sumTop += ( objOffSetTop.clientTop + objOffSetTop.offsetTop - objOffSetTop.scrollTop);

		objOffSetTop = objOffSetTop.offsetParent;

	}

	if( poVertical ) {
		sumTop += imgSemaphore.offsetHeight;
	} else {
		sumLeft += imgSemaphore.offsetWidth;
	}

	if( ( sumTop + plHeight ) > document.body.clientHeight - 25) {
		sumTop = document.body.clientHeight - plHeight - 25;
		if( sumTop < 0 ) {
			sumTop = 0;
		}
	}
	if( ( sumLeft + plWidth ) > document.body.clientWidth - 10) {
		sumLeft = document.body.clientWidth - plWidth - 10;
		if( sumLeft < 0 ) {
			sumLeft = 0;
		}
	}

	newObj.style.left = sumLeft.toString() + "px" ;
	newObj.style.top = sumTop.toString() + "px";

}

function destroyFloater( psId ) {
	if( floaterEndBlink )
		eval( floaterEndBlink );

	var obj = document.all( "floater_" + psId );

	if( obj )
		obj.outerHTML = "";
}

function classAtribute(atribute) {
    if( atribute == null ) return;

    var regExpressClassStart, regExpressClassEnd, posClassStart, posClassEnd;
    regExpressClassStart = /class=/i;
    regExpressClassEnd   = / /i;
    posClassStart        = atribute.search(regExpressClassStart);
    posClassEnd          = atribute.substring(posClassStart).search(regExpressClassEnd);

    return( atribute.substring(posClassStart, (posClassStart + posClassEnd)) );
}
/**
 * Função criada para corrigir o problema de alinhamento
 * da taglib vivotable.
 * data: 19.10.2004
 */
function DoResizeHeaderTableVivo(){
	//verificaPalavra(30);
    var errorAlign = false;
	var tbTable = document.getElementsByTagName("TABLE");
	var length = tbTable.length;
	for(var i=0;i<length;i++){
		if(tbTable[i].tbheader != undefined){
			var table = tbTable[i];
			var tableId = table.tbheader+"_body";
			var tableBody =  document.getElementById(tableId);
            if (tableBody.rows.length > 0){
                table.width = tableBody.clientWidth+16;
                for(var j=0; j < tableBody.rows[0].cells.length;j++){
                    try{
						table.rows[0].cells[j].width = tableBody.rows[0].cells[j].clientWidth - 10;
                    }catch(erro){}
                }
                for(var j=0; j < tableBody.rows[0].cells.length;j++){
                    try{
                        if(table.rows[0].cells[j].clientWidth > tableBody.rows[0].cells[j].clientWidth-10){
							tableBody.rows[0].cells[j].innerHTML = "<span style=\"width:"+(table.rows[0].cells[j].clientWidth-10)+"px; height:1px;\">"+tableBody.rows[0].cells[j].innerHTML+"</span>";
                        }else
						if(table.rows[0].cells[j].clientWidth < tableBody.rows[0].cells[j].clientWidth-10){
							table.rows[0].cells[j].innerHTML = "<span style=\"width:"+(tableBody.rows[0].cells[j].clientWidth-10)+"px; height:1px;\">"+table.rows[0].cells[j].innerHTML+"</span>";
						}else{
							table.rows[0].cells[j].width = tableBody.rows[0].cells[j].clientWidth - 10;
						}
                    }catch(erro){}
                }
            }
		}
	}
}

//Bloqueia Control-N
checkCtrl = function() {
    var ch = String.fromCharCode(event.keyCode);
    if ((event.ctrlLeft || event.CtrlRight) && (ch == "n" || ch == "N"))
        return false;
}
document.onkeydown = checkCtrl;

function parseXml(xml) {
    var dom = null;
    if (window.DOMParser) {
        try {
            dom = (new DOMParser()).parseFromString(xml, "text/xml");
        } catch (e) { dom = null; }
    } else if (window.ActiveXObject) {
        try {
            dom = new ActiveXObject('Microsoft.XMLDOM');
            dom.async = false;
            if (!dom.loadXML(xml)) // parse error ..
                window.alert(dom.parseError.reason + dom.parseError.srcText);
        } catch (e) { dom = null; }
    } else
        alert("Navegador não suportado.");
    return dom;
}

createWindowPopUp = function(id, title, width, height, top, inApresentacao){
    width = (width) ? width : 700;
    height = (height) ? height : 450;
    var idCapitalized = id.capitalize();
    var html = '';

    // Get highest zIndex
    var highestZIndex;
    var arrayPopups = window.top.frameApp.$$('div.popUpDiv');
    var myArray = new Array();
    arrayPopups.each(function(element, index) {
        myArray[index] = element.getStyle('zIndex');
    });
    myArray.sort(function(a,b){return a - b});
    highestZIndex = myArray[myArray.length-1];
    if (!highestZIndex) {
        highestZIndex = 998;
    }
    html += '<div class="popUpDiv" id="' + id + '" style="z-index:' + (highestZIndex+1) + ';position:absolute;top:0;left:0;width:150%;height:150%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif);display:none;">';
    html += '    <div id="wrapper' + idCapitalized + '">';
    html += '        <div id="header' + idCapitalized + '">';
    html += '            <span id="title' + idCapitalized + '"></span>';
    html += '            <span id="upperRightButton_div' + id +'" style="float:right;">';
    html += '                <img id="imgClose_div' + id +'" hspace="3" src="/FrontOfficeWeb/resources/images/window_fechar.gif" ';
    html += "                   onmouseup=\"$('" + id + "').remove();\" ";
    html += '                   style="cursor:pointer;">';
    html += '            </span>';
    html += '        </div>';
    html += '        <div id="container' + idCapitalized + '">';
    html += '        </div>';
    html += '    </div>';
    html += '    <iframe style="display:none;display:block;position:absolute;top:0;left:0;z-index:-1;filter:mask();width:3000px;height:3000px;"></iframe>';
    html += '</div>';

    Element.insert(window.top.frameApp.document.body, html)

    if (inApresentacao) {
        $(id).show();
    } else {
        $(id).hide();
    }

    $('title' + idCapitalized).update(title);
    $('title' + idCapitalized).setStyle({
        float: 'left',
        padding: '3px 0 0 3px',
        color: '#fff',
        fontWeight: 'bold'
    });

    $('container' + idCapitalized).setStyle({
        width: width + 'px',
        background: '#fff',
        border: '1px solid #adadad',
        height: height + 'px',
        overflowX: 'hidden',
        overflowY: 'auto'
    });

    $('header' + idCapitalized).setStyle({
        width: width + 'px',
        height: '21px',
        backgroundImage: 'url(/FrontOfficeWeb/resources/images/window_topbg.gif)',
        backgroundColor: '#0066cb'
    });

    $('wrapper' + idCapitalized).setStyle({
        marginLeft: (800-width)/2 + 'px',
        width: width + 'px',
        height: height + 'px'
    });

    if (top != null) {
        $('wrapper' + idCapitalized).setStyle({ marginTop: (top-21) + 'px' });
    } else {
        $('wrapper' + idCapitalized).setStyle({ marginTop: (600-height)/2 + 'px' });
    }
}

createNewPopUp = function(id, title, width, height, top, action, inApresentacao, params) {
    createWindowPopUp(id, title, width, height, top, inApresentacao);
    var idCapitalized = id.capitalize();
    if (action != null && inApresentacao) {
        new Ajax.Updater('container' + idCapitalized, action, {
            evalScripts: true,
            parameters: params,
            method: 'get'
        });
    }
}

createNewPopUpText = function(id, title, width, height, top, inApresentacao, text, subtitle, style) {
    createWindowPopUp(id, title, width, height, top, inApresentacao);
    var idCapitalized = id.capitalize();
    var html = '';

    html += '<table width="100%" align="center" cellpadding="2" cellspacing="2" height="100%" border="0" bgcolor="#E3ECF4">';
    if(!subtitle.strip().empty()){
        html += '    <tr valign="top"><td align="left"><b>'+subtitle+'</b></td></tr>';
    }
    html += '    <tr valign="top"><td height="100%" valign="middle" align="left" style="'+style+'">'+text+'</td></tr>';
    html += '</table>';

    $('container' + idCapitalized).update(html);
}

createErrorPopUp = function(id, friendlyMessage, exceptionDetails, flRestart) {
    var html;
    var idCapitalized = id.capitalize();
    if (friendlyMessage.indexOf('NotLoggedInException') >= 0
        || (exceptionDetails != '' && exceptionDetails.indexOf('NotLoggedInException') >= 0)) {
        friendlyMessage = "Usuário não conectado ao sistema.";
        flRestart = true;
    } else if (friendlyMessage.toLowerCase().indexOf('not acceptable') >= 0) {
        friendlyMessage = "Serviço indisponível no momento. Por favor, tente mais tarde.";
    }
    html  = '<div id="' + id + '" style="padding-right:0;top:0;left:0;padding-left:0;padding-bottom:0;padding-top:0;width:100%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif);height:200%;position:absolute;z-index:99999">';
    html += '   <div id="holder">';
    html += '       <iframe width="500" height="150" frameborder="0" marginheight="0" marginwidth="0"></iframe>';
    html += '       <div id="head">';
    html += '           <div id="close"></div>';
    html += '           <span id="' + idCapitalized + 'errTitle">Erro</span>';
    html += '       </div>';
    html += '       <div id="foot">';
    html += '           <textarea id="' + idCapitalized + 'errDetails" rows="0" style="overflow:hidden;margin-right:20px;background-color:#e4ebf5;color:#e4ebf5;border:none;font-family:Tahoma;font-size:10px;height:15px;width:340px;">' + exceptionDetails + '</textarea>';
    html += '           <img src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" border="0" style="cursor:pointer" onmouseup="';
    if (flRestart) {
        html += '$(\'holder\').remove();window.top.frameApp.location.href = \'/FrontOfficeWeb/begin.do\';';
    } else {
        html += '$(\'' + id + '\').remove();';
    }
    html += '" />';
    html += '       </div>';
    html += '       <div id="left">';
    html += '           <img id="' + idCapitalized + 'errIcon" src="/FrontOfficeWeb/resources/images/erroFO.gif" />';
    html += '           <br/>';
    html += '           <span id="' + idCapitalized + 'errCode"></span>';
    html += '       </div>';
    html += '       <div id="content">';
    html += '           <div id="' + idCapitalized + 'errMsg">' + friendlyMessage + '</div>';
    html += '       </div>';
    html += '   </div>';
    html += '</div>';
    Element.insert(window.top.frameApp.$('body'), html);
}

isSelected = function(abaID) {
    if ($(abaID).hasClassName('abaSelected')) {
        return true;
    }
    return false;
}

addValueToSelectList = function(label, value, list) {
    list.options[list.options.length] = new Option(label, value);
}

clearSelectList = function(l) {
    try {
        if (l) {
            while (l.options.length > 0) {
                l.options[0] = null;
            }
        }
    }catch(e){}
}

getAmountSelectedItems = function(list) {
    var c = 0;
    try {
        if (list.tagName == 'SELECT') {
            for (var i = 0; i < list.options.length; i++) {
                if (list.options[i].selected) {
                    c++;
                }
            }
        }
    }catch(e){}
    return c;
}

selectAllOptions = function(list) {
    try {
        if (list.tagName == 'SELECT') {
            for (var i = 0; i < list.options.length; i++) {
                list.options[i].selected = true;
            }
        }
    }catch(e){}
}

unselectAllOptions = function(list) {
    try {
        if (list.tagName == 'SELECT') {
            for (var i = 0; i < list.options.length; i++) {
                list.options[i].selected = false;
            }
        }
    }catch(e){}
}

selectListItemByValue = function(value, list) {
    try {
        if (list.tagName == 'SELECT') {
            for (var i = 0; i < list.options.length; i++) {
                if (list.options[i].value == value) {
                    list.options[i].selected = true;
                    break;
                }
            }
        }
    } catch(e) {}
}

removeListItemByValue = function(value, list) {
    try {
        if (list.tagName == 'SELECT') {
            for (var i = 0; i < list.options.length; i++) {
                if (list.options[i].value == value) {
                    list.options[i] = null;
                    break;
                }
            }
        }
    } catch(e) {}
}

getSelectedItemText = function(list) {
    for (var i = 0; i < list.options.length; i++) {
        if (list.options[i].selected) {
            return list.options[i].text;
        }
    }
}

replaceAll = function(string, token, newtoken) {
	while (string.indexOf(token) != -1) {
 		string = string.replace(token, newtoken);
	}
	return string;
}

formatXml = function(xml) {
    var formatted = '';
    var reg = /(>)(<)(\/*)/g;
    xml = xml.replace(reg, '$1\r\n$2$3');
    var pad = 0;
    jQuery.each(xml.split('\r\n'), function(index, node) {
        var indent = 0;
        if (node.match( /.+<\/\w[^>]*>$/ )) {
            indent = 0;
        } else if (node.match( /^<\/\w/ )) {
            if (pad != 0) {
                pad -= 1;
            }
        } else if (node.match( /^<\w[^>]*[^\/]>.*$/ )) {
            indent = 1;
        } else {
            indent = 0;
        }
        var padding = '';
        for (var i = 0; i < pad; i++) {
            padding += '  ';
        }
        formatted += padding + node + '\r\n';
        pad += indent;
    });
    return formatted;
}