function mostraBarraCTI()
{           
	tdBarraCTI.style.display='none';
	if(top.frameCTI.isCarregadoOCX())
	{    
		if(top.frameCTI.isAgenteLogado())
		{    
			tdBarraCTI.style.display='';
			obtemDisponibilidadeCTI();
		}
	}
}
function obtemDisponibilidadeCTI() {
	var estadoAgente = top.frameCTI.obtemEstadoAgente();
	var loginOk = estadoAgente.search('logado');
	var disponivelOk = estadoAgente.search('disponivel');
    
	if(loginOk >= 0) { 
		if(disponivelOk >= 0) {
			document.getElementById('imgDisponibilidade').src = '/FrontOfficeWeb/resources/images/icon_atend_disponivel.gif';
		}else{
			document.getElementById('imgDisponibilidade').src = '/FrontOfficeWeb/resources/images/icon_atend_indisponivel.gif';
		}
	}else{
		document.getElementById('imgDisponibilidade').src = '/FrontOfficeWeb/resources/images/icon_atend_indisponivel.gif';
	}
}
function mudaDisponibilidade() {

	if(obtemEstadoLigacaoCTI()!=1)
	{
		top.frameCTI.alterarEstado();
		obtemDisponibilidadeCTI();
	} else {
		alert('Estado com Ligação');
	}
}
function obtemEstadoLigacaoCTI() {
	var estadoLigacao = top.frameCTI.obtemEstadoLigacao();
	var comLigacao = estadoLigacao.search('Com');
	var semLigacao = estadoLigacao.search('Sem');
	if(comLigacao >= 0) { return 1;
	}else{
		if(semLigacao >= 0){ return 2;	}
	}
}

function trocarCampanha(itemValue) {
	var campanhas = top.frameCTI.obtemCampanhas();
	var posHifen = campanhas.search('-');
	if(posHifen >= 0) {
		var campanhaAnterior = campanhas.substr(0, posHifen);
		campanhaAnterior = Trim(campanhaAnterior);
		top.frameCTI.alterarEstadoEspecifico('N');
		top.frameCTI.fecharCampanha(campanhaAnterior);
	}
	if(itemValue != -1) {
		oOption = document.getElementById('retorno');
		for(i = 0; i < oOption.options.length; i++ ) {
			if(oOption.options(i).value == itemValue) {
				top.frameCTI.abrirCampanha(oOption.options(i).text);
				top.frameCTI.alterarEstadoEspecifico('S');
				break;
			}
		}
	}
	obtemDisponibilidadeCTI();
}
function lTrim(d){c=new String(d);return c.replace(/^\s*/,"");}
function rTrim(d){c = new String(d);return c.replace(/\s+$/,"");}
function Trim(d){return rTrim(lTrim(d));}
