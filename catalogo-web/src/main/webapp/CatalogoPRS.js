/************* PARA CONHECIMENTO GERAL ******************
***  TODAS AS CHAMADAS ESTÃO SENDO FEITA USANDO AJAX  ***
***  TEM TRATAMENTO DE SUCESSO E FALHA NA VOLTA DA    ***
***  CHAMADA AJAX.                                    ***
***  												  ***
***  O controller pode retornar um código javascript  ***
***  que pode acionar ações na tela como clicar no    ***
***  botão de busca.								  ***
***                                                   ***
***  Cuidade ao modificar código javasript, isso pode ***
***  afetar todas as página.						  ***
***  												  ***
*********************************************************
*********************************************************/

/**
Variaveis globais.
(variaveis globais são globais somente para aquele browser que está acessando do sistema).

- show_popup: na hora de submeter um form, o resultado pode ser a abertura de um popup
				ou mostrar um erro na tela. Se a chamada for bem sucedida precisava 
				saber qual popup mostrar na tela.
- doNotHideSpinner: na hora de submeter um form, o retorno da chamada pode ser uma outra chamada.
					neste caso, não pode esconder a mensagem "Carregando...".
- selectGlobalDestino: utilizado para criar uma função Ajax generica para casos onde o resultado
				de um select interfere na apresentação de outro select.
*/
var show_popup = "";
var doNotHideSpinner = false;
var scroll_div_destino = null;
var flagConteudoEditado = false;
var selectGlobalDestino = null;


/**
* Tratamento do botão que permite de mostrar e esconder as seções das páginas 
*	(filtros de busca, resultado de pesquisa, cadastros).
*/
function showHideSecaoConteudo(imagem) {
	var element = $(imagem)
	imgsrc = element.src;
	imgclose = '/catalogo/static_server/img/botoes/bt-barra-open.gif';
	imgopen = '/catalogo/static_server/img/botoes/bt-barra-close.gif';
	var conteudo = element.up(1).next();
	if(conteudo.visible()){
		element.src = imgclose;
		Effect.SlideUp(element.up(1).next());
	}else{
		element.src = imgopen;
		Effect.SlideDown(element.up(1).next());
	}
}

/**
* Permite de mostrar e esconder as seções das paginas(Hack para IE, correção de bug de layout) 
* é acionado ao exibir detalhes do erro (+).
*/

function showHideSecaoConteudoAux(imagem) {
	var element = $(imagem)
	imgsrc = element.src;
	imgclose = '/catalogo/static_server/img/botoes/bt-barra-open.gif';
	imgopen = '/catalogo/static_server/img/botoes/bt-barra-close.gif';
	var conteudo = element.up(1).next();
	if(conteudo.visible()){
		element.src = imgclose;
		$(element.up(1).next()).hide();
	}else{
		element.src = imgopen;
		$(element.up(1).next()).show();
	}
}




/****************************************************************************/
/** Movimentando a linha da tabela ******************************************/
/****************************************************************************/
var whichrow = false;
var tabela = false;

function thisrow(x){
	if (whichrow) whichrow.style.background = 'transparent';
	tabela = arguments[1];
	whichrow = x;
	whichrow.style.background = '#F2F4F7';
}

function moverow(x){
	if (whichrow && tabela == arguments[1]) {
		if (x == 'up' && whichrow.previousSibling.previousSibling == null) return;
		if (x == 'down' && whichrow.nextSibling.nextSibling == null) return;
	
		if (x == 'up' && whichrow.previousSibling){
			whichrow.parentNode.insertBefore(whichrow, whichrow.previousSibling);
		} else if (x == 'down' && whichrow.nextSibling){
			whichrow.parentNode.insertBefore(whichrow.nextSibling, whichrow);
		}
	}
}

/****************************************************************************/
/** Movimentando do MENU ****************************************************/
/****************************************************************************/

function TwMenu_X(obj){
	var nivel = arguments[1];
	var modo  = arguments[2];
	var mn = obj.replace('sub','mn');

	if(document.getElementById(obj)!=null || document.getElementById(mn)!=null){
		
		if(document.getElementById(obj)==null){
			var obj = obj.replace('sub','mn');
		}

		if(document.getElementById(obj).style.display=="none" || document.getElementById(obj).style.display==""){
			document.getElementById(obj).style.display = "block";
			switch (parseInt(nivel)) {
			case 1:
			   document.getElementById(mn).className = "on";
			   break
			case 2:
			   document.getElementById(mn).className = "sub_ativo";
			   break
			case 3:
			   document.getElementById(mn).className = "linkAtivo";
			   break
			} 
		}else{
			document.getElementById(obj).style.display = "none";
			switch (parseInt(nivel)) {
			case 1:
			   document.getElementById(mn).className = "off";
			   break
			case 2:
			   document.getElementById(mn).className = "sub_inativo";
			   break
			case 3:
			   document.getElementById(obj).style.display = "block";
			   document.getElementById(mn).className = "linkAtivo";
			   break
			} 
		}
	}
}


/****************************************************************************/
/** MENU (CODIGO FORNECIDO PELA VIVO ****************************************/
/****************************************************************************/
function TwMenu(obj){

	var nivel = arguments[1];
	var modo  = arguments[2];
	var mn = obj.replace('sub','mn');
	
	if(document.getElementById(obj)!=null || document.getElementById(mn)!=null){
		
		if(document.getElementById(obj)==null){
			var obj = obj.replace('sub','mn');
		}

		if(document.getElementById(obj).style.display=="none" || document.getElementById(obj).style.display==""){
			document.getElementById(obj).style.display = "block";
			switch (parseInt(nivel)) {
			case 1:
			   document.getElementById(mn).className = "menu-item-pai-on";
			   break
			case 2:
			   document.getElementById(mn).className = "menu-item-ativo";
			   break
			case 3:
			   document.getElementById(mn).className = "menu-item-link-ativo";
			   break
			} 
		}else{
			document.getElementById(obj).style.display = "none";
			switch (parseInt(nivel)) {
			case 1:
			   document.getElementById(mn).className = "menu-item-pai-off";
			   break
			case 2:
			   document.getElementById(mn).className = "menu-item";
			   break
			case 3:
			   document.getElementById(obj).style.display = "block";
			   document.getElementById(mn).className = "menu-item-link";
			   break
			} 
		}
	}
}

/**
* Metodo chamada na inicialização da página Home
*/
function init(){
	    var url = document.location.href;
		var url_array = url.split('/');

		var page = url_array[url_array.length-1];
		
		if(page=='home.jsp'){
			$('menu_home').className = 'menu-item-pai-on';
			return;
		}
			
		
		
		var menu = page.replace('.jpf', '');
		var id_menu = 'menu_'+menu;
		
		if($(id_menu)){
			$(id_menu).className = 'menu-item-link-ativo';
			$(id_menu).up(0).style.display='block';
			$(id_menu).up(1).className = 'menu-item-ativo';
			$(id_menu).up(2).style.display='block';
			$(id_menu).up(3).className = 'menu-item-pai-on';
		}
}


/**
* Mostra o spinner (a mensagem: "Carregando...").
*/
function showSpinner(){
	if($('right_section')==null)
		return;
	$('right_section').select('SELECT').each(function(n){
			n.style.visibility='hidden';
		}
	);
	$('higher_cortina').show();
	$('carregando').style.top = 300 + document.viewport.getScrollOffsets().top;
	$('carregando').style.left = 430;
	if($('higher_cortina').getHeight() < $('right_section').getHeight())
		$('higher_cortina').style.height = $('right_section').getHeight();
	$('carregando').show();
	$('spinner').show();
}

/**
* Mostra o spinner (a mensagem: "Carregando...").
*/
function hideSpinner(doNotShowSpinner){
	$('higher_cortina').hide();
	$('carregando').hide();
	$('spinner').hide();
	if(doNotShowSpinner == null){
		$('right_section').select('SELECT').each(function(n){
				n.style.visibility='';
			}
		);
	}
}

/**
* Passando a coluna do header da tabela, inicia a ordenação da tabela por esta coluna.
*/
function sortByColumn(th){
	if(th.up('table').hasClassName('table_header')){
		th.up('table').select('th').invoke('removeClassName', 'sortdesc');
		th.up('table').select('th').invoke('removeClassName', 'sortasc');
		SortableTable.sort(null, th.up('table').next().select('th')[th.cellIndex]);
		th.addClassName(th.up('table').next().select('th')[th.cellIndex].className);
	}else{
		SortableTable.sort(null, th);
	}
}

/**
* Verifica se tem algum elemento na página que suporta ordenação (tem class 'sortable').
	Se tiver, associa a ação de ordenação ao clicar numa coluna do header da tabela. 
*/
function initSortTable(){
	var ths = $$('.sortable');
	ths.each(function(th) {
		$(th).observe('click',function(event){
			var th = Event.element(event);
			sortByColumn(th);
		});
	});
	var tables_header = $$('.table_header');
	if(tables_header != null && tables_header.length > 0){
		for (var index_tables = 0; index_tables < tables_header.length; ++index_tables) {
			var table_header = tables_header[index_tables];
			if(!table_header.hasClassName('largura_processada') && table_header.next().select('.table_body') != null && table_header.next().select('.table_body')[0] != null){
				var table_body = table_header.next().select('.table_body')[0];
				var ths_body = table_body.select('th');
				var ths_table_header = table_header.select('th');
				$(table_header).style.width = $(table_header).getWidth()-17;
				for( var index_ths = 0 ; index_ths < ths_table_header.length ; index_ths++){
					var th_table_header = $(ths_table_header[index_ths]);
					if(!th_table_header.hasClassName('no_dynamic_width')){
						var classVisibleBefore = $(th_table_header).className;
						$(th_table_header).className = '';
						$(th_table_header).style.width = $(ths_body[index_ths]).getWidth()-21;
						$(th_table_header).className = classVisibleBefore;
					}
				}
				$(table_header).style.width = table_body.select('thead')[0].getWidth();
				//table_body.select('thead')[0].style.height = $(table_header).getHeight();
				table_header.absolutize();
				table_header.addClassName('largura_processada');
				table_body.tHead.style.visibility='hidden';
			}
		}
	}
}

/**
*  Metodo usado para fazer a chamada Ajax de um form.
*	Params:
*	  - (*) button: o botão que iniciou a chamada a funcão. Este botão deve esta dentro do form. 
*	  - div_destino: o div onde sera colocado o HTML retornado pela chamada
*	  - popup: Popup a ser fechado se o retorno da chamada for bem sucedida ( o send nunca abre popup)
*	  - id_bloco: o bloco (div) acima do qual estará colocado a mensagem de erro se o retorno 
*					da chamada não for bem sucedido
*	  - doNotHideSpinner: O retorno da chamada pode ser um javascript acionando uma outra ação (click
							num botão de pesquisa). Neste caso, não pode esconder a mensagem de "aguarde".
*/
function send(button, div_destino, popup, id_bloco, doNotHideSpinner, id_bloco_required){
	if(hasEditavel()){
 		return false;
 	}
	var form = $(button).form;
	// Verifica se alguns itens obrigatório do form estão em branco
	
	if(checkRequired(form, id_bloco_required != null?id_bloco_required:id_bloco)){
		// Verifica se as datas submetidas estão no formato correto e existem 
		checkDatas(form)
		return false;
	}
	
	// Verifica se as datas submetidas estão no formato correto e existem 
	if(checkDatas(form)){
		return false;
	} 
	
	// Mostra a mensagem de "Aguarde" 
	showSpinner();
	
	// Serializa os itens do form a serem submetidos 
	var params = $H(form.serialize(true));
	// Se o popup a ser fechado for informado, seta ele como parametro da chamada
	//	Precisamos fazer isso para ter essa informação na volta da chamada Ajax 
	if(popup){
		params.set('popup', popup);
	}
	
	// Setando este parametro como parametro da chamada para ter essa informação na volta da chamada Ajax 
	if(doNotHideSpinner!=null){
		params.set('doNotHideSpinner', true);
	}
	
	// Posiciona o div de erros acima do bloco informado 
	posicionarDivErros(id_bloco);
	
	// Se o div destino for informado, a chamada Ajax ira automaticamente coloca o resultado neste div 
	if(div_destino){
		// Precisa passar o div_destino em parametro para poder focar a tela neste div ao volta da chamada 
		params.set('div_destino', div_destino);
		new Ajax.Updater({success: div_destino}, form.action, {asynchronous:true, parameters:params, onSuccess: retornoSend, onComplete: doComplete,
			onFailure: doFailure});
	}else{
		new Ajax.Request(form.action, {asynchronous:true, parameters:params, onSuccess: retornoSend, onComplete: doComplete, onFailure: doFailure});
	}
	return true;
}

/**
*	Função chamada ao voltar bem sucedido da função send.
*	 Params:
*		- (*) t: objeto automaticamento recebido, com as informações sobre a chamada.
*/
function retornoSend(t){
	// Chama o tratamento de retorno bem sucedido padrão 
	doSuccess();
	
	// Seta variavel global estipulando que não é para esconder a mensagem de "Aguarde" no tratamento final da chamada Ajax (doComplete) 
	if(t.request.parameters.doNotHideSpinner != null){
		doNotHideSpinner = true;
	}
	
	// Fecha o popup passado em parametro da chamada 
	if(t.request.parameters.popup != null){
		fecharPopup(t.request.parameters.popup);
	}
}

/**
* Funcção executada em ultimo a cada chamada Ajax
*	 Params:
*		- (*) t: objeto automaticamento recebido, com as informações sobre a chamada.
*/
function doComplete(t){
	var showSelects = true;	
	// Abre popup salvo na variavel global
	if(t.request.parameters.popup != null){
		if(show_popup != ""){
			showSelects = false;
			retornoAbrirPopup(show_popup);
		}
	}
	
	// Inicializa a ordenação das tabelas
	initSortTable();
	
	// Posiciona o usuário no resultado da chamada (caso esse resultado for fora de visibilidade)
	
	if(t.request.parameters.div_destino != null){
		var div_destino = $(t.request.parameters.div_destino);
		if(div_destino.viewportOffset().top < 0 || (div_destino.cumulativeOffset().top - document.viewport.getHeight()) > document.viewport.getScrollOffsets().top)
			$(t.request.parameters.div_destino).scrollTo();
	}

	if(t != null){	
		//Se o resultado da chamada for outra chamada, não esconde a mensagem de "aguarde".
		if(t.request.parameters.doNotHideSpinner == null && !doNotHideSpinner){
			if(showSelects){
				hideSpinner();
			}else{
				hideSpinner('do_not_show_select');
			}
		}

		if(t.request.parameters.div_destino != null && scroll_div_destino != null && $(scroll_div_destino) != null){
			$(scroll_div_destino).scrollTo();
		}
	}

	monitorarConteudoEditavel();

	//reseta a variavel
	doNotHideSpinner = false;
	scroll_div_destino = null;
}

function setEditando(){
	flagConteudoEditado = true;
}

function clearEditando(){
	flagConteudoEditado = false;
}

function monitorarConteudoEditavel(){
	var editaveis = $$('.editavel');
	editaveis.each(function(editavel) {
		editavel.observe('change', function(){
				flagConteudoEditado = true;
			});
	});
	
	var clearEditaveis = $('right_section').select('.clearEditavel');
	clearEditaveis.each(function(clearEditavel) {
		clearEditavel.observe('click', function(event){
				flagConteudoEditado = false;
			});
	});
}


/**
*	Função executada quando o retorno da chamada for bem sucedida
*/
function doSuccess(t){
	// Posiciona o div de erros acima do 'right_section'. 
	// O div 'right_section' é o div a direita do menu. Esse div nunca desaparece.
	posicionarDivErros('right_section');
	if(t != null){
		if(t.request.parameters.div_destino != null){
			scroll_div_destino = t.request.parameters.div_destino;
		}
	}
	clearEditando();
}

/**
*	Função executada quando o retorno da chamada não for com sucesso. Basicamente mostra os erros na tela.
*	 Params:
*		- (*) t: objeto automaticamento recebido, com as informações sobre a chamada.
*/
function doFailure(t, customText){
	if(t != null){
		// Aloca o retorno no div de erros
		$('conteudo_divErros').innerHTML = t.responseText;
		// Verifica a necessidade desse trecho que fecha o popup
		if(t.request.parameters.popup != null){
			fecharPopup(t.request.parameters.popup);
		}
	}
	if(customText != null){
		$('conteudo_divErros').innerHTML;
	}
	// Mostra os erros na tela.
	$('divErros').show();
	// TODO: verificar se a mensagem de erro já não estiver visivel na tela
	$('divErros').scrollTo();
	//Esconde a mensagem de aguarde
	hideSpinner();
}

/**
*	Posiciona o div de mensagens de erro. Essa função é executada antés da chamada, não na volta.
*	 Params:
*		- (*) id_bloco: o bloco acima do qual serão mostrados os erros.
*/
function posicionarDivErros(id_bloco){
	if($('right_section')== null)
		return;
	// Se caso o div sumiu (sobre escrito por uma chamada ajax), recria ele.
	if( $('divErros') == null){
		if($('right_section')== null)
			return;
		$('right_section').insert({before: createDivErros()});
	}
	
	//Esconde o div 
	$('divErros').hide();
	
	//Esvazia o div
	$('conteudo_divErros').innerHTML = '';
	// Se for especificado o lugar onde for ficar o div de erros, coloca o div de erros acima dele. Se não colocar acima do 'right_section'
	if(id_bloco != null){
		var bloco = $(id_bloco);
		bloco.insert({before: $('divErros')});
	}else{
		$('right_section').insert({before: $('divErros')});
	}
}


/**
* 	Função permitindo de abrir um link pelo ajax.
*	 Params:
*		- div_destino: onde será mostrado o resultado da chamada, se tiver
*		- (*) url: url a ser chamada
*		- id_bloco: bloco acima do qual serão mostrados os erros
*		- inputArray: Lista dos input a serem enviados como parametros da chamada
*/
function abrirLink(div_destino, url, id_bloco, inputArray){
	if(hasEditavel()){
 		return false;
 	}
	posicionarDivErros(id_bloco);
	showSpinner();
	var params =  new Hash();
	//Coloca os ids e valores dos inputs num no map de parametros
	if(inputArray){
		$(inputArray).each(function(input){
			if(input != null){
				params.set(input.id, input.value);
			}
		});
	}
	if(div_destino){
		$(div_destino).innerHtml = ""
		params.set("div_destino", div_destino);
		new Ajax.Updater({success: div_destino}, url, {method:'post', parameters:params, onSuccess: doSuccess, onComplete: doComplete, onFailure: doFailure});
	}else{
		new Ajax.Request(url, {method:'post',parameters:params, onSuccess: doSuccess, onComplete: doComplete, onFailure: doFailure});
	}
	return true;
}

/**
* 	Função abrindo o popup 1 (tem 2 niveis de popup).
*	 Params:
*		- (*) url: url a ser chamada
*		- inputArray: Lista dos input a serem enviados como parametros da chamada
*		- id_bloco: bloco acima do qual serão mostrados os erros
*/
function abrirPopup1(url, inputArray, id_bloco){
	if(hasEditavel()){
 		return false;
 	}
	posicionarDivErros(id_bloco);
	var params =  new Hash();
	//Coloca os ids e valores dos inputs num no map de parametros
	if(inputArray){
		$(inputArray).each(function(input){
			if(input != null){
				params.set(input.id, input.value);
			}
		});
	}
	//Define qual popup estamos abrindo. Será usado no retorno da chamada.
	params.set('popup', 'popup1');

	// Mostra a cortina do popup (sombra a tras do popup)
	$('popup1Cortina').show();
	showSpinner();
	new Ajax.Updater({success:'popup1'}, url, {parameters:params, onSuccess: retornoAbrirPopup1, onFailure: doFailure, onComplete: doComplete });
	return true;
}

/**
* 	Função abrindo o popup 1 (tem 2 niveis de popup).
*	 Params:
*		- (*) url: url a ser chamada
*		- inputArray: Lista dos input a serem enviados como parametros da chamada
*		- id_bloco: bloco acima do qual serão mostrados os erros
*/
function abrirPopup2(url, inputArray, id_bloco){
	if(hasEditavel()){
 		return false;
 	}
	posicionarDivErros(id_bloco);
	var params =  new Hash();

	if(inputArray){
		$(inputArray).each(function(input){
			params.set(input.id, input.value);
		});
	}
	
	params.set('popup', 'popup2');

	$('popup2Cortina').show();
	showSpinner();
	new Ajax.Updater({success:'popup2'}, url, {parameters:params, onSuccess: retornoAbrirPopup2, onFailure: doFailure, onComplete: doComplete });
	return true;
}

/**
* 	Função abrindo o popup mostrando os erros de validação dos forms.
*/
function abrirPopupErros(){

	var popup = $('divErros');
	
	popup.show();
	if ($('popup_erros_ul').select('li').length == 2){
		$('popup_erros_ul').style.height = '40px';
	}
	
	if(popup.visible())
		return;
	
	popup.scrollTo();
}


/**
* 	Função abrindo o popup mostrando as regras de alteração de senha
*/
function abrirPopupRegrasAlteracaoSenha(){

	$('popup_regras_alteracao_senhaCortina').show();
	var popup = $('popup_regras_alteracao_senha');
	popup.style.visibility = 'hidden';
	popup.show();
	popup.style.height = popup.getHeight();
	
	popup.style.width=500;
	popup.style.left = (180+410) - (popup.getWidth()/2);
	popup.style.top = 150;
	popup.style.height=250;
	popup.style.visibility = '';
	ajustarPopupIF('popup_regras_alteracao_senha');
}

/**
* 	Função que fecha o popup de erros de validação de forms
*/
function fecharPopupErros(){
	$('popup_errosIF').hide();
	$('popup_erros').hide();
	$('popup_errosCortina').hide();
}

/**
* 	Cada Popup tem um iFrame associado permitindo de resolver o problema de selec do IE 
*		(quando se usa popup model, se tiver um select a traz ele aparece sempre no primeiro plano).
*	Essa função ajusta do tamanho do iFrame ao tamanho atual do popup.
*/
function ajustarPopupIF(id_popup){
	var popup = $(id_popup)
	if ($(id_popup + 'IF')) {
		var iframe = $(id_popup + 'IF');
		iframe.style.position = popup.style.position;
		iframe.style.top = popup.style.top;
		iframe.style.left = popup.style.left;
		iframe.style.height = popup.getHeight()-5;
		iframe.style.width = popup.style.width;
		iframe.style.display = '';		
	}
	if ($(id_popup + 'Cortina')) {
		var cortina = $(id_popup + 'Cortina');
		cortina.style.height = document.viewport.getScrollOffsets().top + 1000;
	}
}

/**
* 	Função executada em caso de sucesso de uma chamada abrindo o popup 1;
*/
function retornoAbrirPopup1(){
	doSuccess();
	// Seta a variavel global show_popup para o doComplete poder abrir o popup
	// Não poderiamos abrir o popup neste momento porque ele não estaria completamento renderizado
	// e daria muitos problemas ao determinar o tamanho dele.
	show_popup = 'popup1';
}

/**
* 	Função executada em caso de sucesso de uma chamada abrindo o popup 2;
*/
function retornoAbrirPopup2(){
	doSuccess();
	// Seta a variavel global show_popup para o doComplete poder abrir o popup
	// Não poderiamos abrir o popup neste momento porque ele não estaria completamento renderizado
	// e daria muitos problemas ao determinar o tamanho dele.
	show_popup = 'popup2';
}

/**
* 	Função executada pelo doComplete se a variavel de ambient show_popup foi definido no onSuccess da chamada abrindo o popup
*	 Params:
*		- (*) id_popup: id do popup a ser aberto (pode ser popup1 ou popup2)
*/
function retornoAbrirPopup(id_popup){
	var popup = $(id_popup);
	//Esconde o popup de um jeito que podemos ajusta do tamanho dele
	popup.style.visibility = 'hidden';
	popup.show();
	
	//Se o popup estiver composto de um box (maioria dos popups), teremos que ajusta o tamanho do popup em função do tamnaho do box
	if($(popup).select('.box_middle_center') && $(popup).select('.box_middle_center')[0]){
		//podemos forçar a largura do popup
		if($('larguraPopup')){
			popup.style.width = $F('larguraPopup')
		}else{
			// Podemos definir o popup como largo.
			if($$('.largePopup')){
				popup.style.width='610';
			}else{
				// Se o tamanho do box estiver < 300, deixa a largura como 410, senão, 610
				if($(popup).select('.box_middle_center')[0].getWidth()<300){
					popup.style.width='410';
				}else{
					popup.style.width='610';
				}
			}
		}
	}else{
		popup.style.width=410;
	}
	
	//Se tiver uma tabela, no box, temos que ajusta o width dele para 100%. Não poderia ter sido feito automaticamente antes de redimensionar o popup
	if($(popup).select('.box_middle') != null && $(popup).select('.box_middle')[0] && $(popup).select('.box_middle')[0].down('table') !=  null){
		$(popup).select('.box_middle')[0].down('table').width='100%';
	}

	//Se o popup estiver composto de um box (maioria dos popups), teremos que ajusta o tamanho do box e não o tamnaho do box
	if($(popup).select('.box_middle_center') && $(popup).select('.box_middle_center')[0]){
		if($(popup).select('.box_middle_center')[0].getHeight()>350){
			$(popup).select('.box_middle_center')[0].style.height='350';
			if($(popup).select('.no_vertical_scroll') == null){
				$(popup).select('.box_middle_center')[0].addClassName('vertical-scroll');
			}
		}else{
			if($('alturaPopup')){
				$(popup).select('.box_middle_center')[0].style.height = $F('alturaPopup')
			}else{
				if($(popup).select('.box_middle_center')[0].getHeight()>100){
					$(popup).select('.box_middle_center')[0].style.height = 270;
				}
			}
		}
	}
	
	// Posiciona o popup no centro da parte direita da página
	popup.style.left = (180+410) - (popup.getWidth()/2);

	// O popup 1 está 100px a baixo do topo da parte visivel da página, o popup 2 está um pouco mais para baixo dele (125px do topo)
	if(id_popup == 'popup1'){
		popup.style.top = 100 + document.viewport.getScrollOffsets().top;
	}else{
		popup.style.top = 125 + document.viewport.getScrollOffsets().top;
	}
	
	// Ajusta o IFrame do popup e mostra o popup
	ajustarPopupIF('popup1');
	popup.style.visibility = '';
	popup.style.display = 'block';
	show_popup = "";
	if(id_popup == 'popup2'){
	ajustarPopupIF('popup2');
	}
}


/**
* Fecha o popup passado em parametro
*/
function fecharPopup(id_popup){
	if(hasEditavel())
		return;
	clearEditando();
	if($('right_section') != null){
		$('right_section').select('SELECT').each(function(n){
					n.style.visibility='';
				}
			);
	}
	$(id_popup+'IF').hide();
	$(id_popup).hide();
	$(id_popup).innerHTML = '';
	$(id_popup+'Cortina').hide();
}

function selecionaRadioGroupPopup(input_destino, checkbox){
	if(checkbox.checked){
		$(input_destino).value = checkbox.value;
	}else{
		$(input_destino).value = '';
	}
}

/**
* Num popup simples de seleção de filtro (popup de seleção de UF) de uma pesquisa, o id do item selecionado será concatenado 
*	com os outros já selecionados num formato CSV. A concatenação será salva num input que será mandado na chamada da pesquisa.
*  Params:
*	- (*) input_destino: input que receberá os ids concatenados e que fará parte da chamada da busca
*	- (*) checkbox: o checkbox que foi clicado
*/
function selecionaCheckBoxPopup(input_destino, checkbox){
	if(checkbox.checked){
		$(input_destino).value = addValorCSV($F(input_destino), checkbox.value);
		if($(input_destino+'Nomes') != null){
			selecionaNomeCheckBoxPopup(input_destino+'Nomes', $(checkbox).up().next().down().innerHTML, true);
		}
	}else{
		$(input_destino).value = removeValorCSV($F(input_destino), checkbox.value);
		if($(input_destino+'Nomes') != null){
			selecionaNomeCheckBoxPopup(input_destino+'Nomes', $(checkbox).up().next().down().innerHTML, false);
		}
	}
}

/**
* Num popup simples de seleção de filtro (popup de seleção de UF) de uma pesquisa, o nome do item selecionado será concatenado 
*	com os outros já selecionados num formato CSV. A concatenação será salva num input que será mandado na chamada da pesquisa.
*  Params:
*	- (*) input_destino: input que receberá os ids concatenados e que fará parte da chamada da busca
*	- (*) nome: O nome a ser adicionado
*/
function selecionaNomeCheckBoxPopup(input_destino, nome, adiciona){
	if(adiciona){
		$(input_destino).value = addValorCSV($F(input_destino), nome);
	}else{
		$(input_destino).value = removeValorCSV($F(input_destino), nome);
	}
}

/**
* Função generica que adiciona mais um valor a uma String CSV.
*  Params:
*	- (*) csv: String no forma to csv a qual será adicionado o valor novo
*	- (*) valor: valor a ser adicionado
*/
function addValorCSV(csv, valor){
	if( csv == null || csv.length==0){
		csv = valor;
	}else{
		var valores = $A(csv.split(",")).compact();
		if(valores.indexOf(valor)==-1){
			csv = csv +","+valor;
		}
	}
	return csv;
}

/**
*
* Função generica que remove um valor de uma String CSV.
*  Params:
*	- (*) csv: String no forma to csv a qual será removido o valor novo
*	- (*) valor: valor a ser removido
*/
function removeValorCSV(csv, valor){
	if(csv.length!=0){
		var valores = $A(csv.split(",")).compact();
		var valores_atualizado = valores.without(valor);
		csv = "";
		for(var i = 0; i < valores_atualizado.length; i++){
			if(i !=0){
				csv = csv + ",";
			}
			csv = csv + valores_atualizado[i];
		}
	}
	return csv;
}

/**
* Função usada na página de Parametrização de modelo. Permite de selecionar os ids das caracteristicas
*	(no filtro da pesquisa e na alteração das caracteristicas).
*/
function selecionarCaracteristica(input_destino, checkbox, input_destino_nomes, nome){
	if( $F(input_destino).length > 0){
		var caracs = $H($F(input_destino).evalJSON());
		if(input_destino_nomes != null && $F(input_destino_nomes).length > 0){
			var nomesCaracs = $H($F(input_destino_nomes).evalJSON());
		}
	}else{
		var caracs = $H();
		var nomesCaracs = $H();
	}
	var valores = $('valores_caracteristica_' +checkbox.value).select('.checkbox');
	for(var j = 0; j < valores.length; j++){
		valores[j].checked=false;
	}
	if(checkbox.checked){
		caracs.set(checkbox.value, "");
		if(input_destino_nomes != null){
			nomesCaracs.set(nome, "");
		}
	}else{
		caracs.unset(checkbox.value);
		if(input_destino_nomes != null){
			nomesCaracs.unset(nome);
		}
	}
	$(input_destino).value = caracs.toJSON();
	if(input_destino_nomes != null){
		$(input_destino_nomes).value = nomesCaracs.toJSON();
	}
}

function selecionarValorCaracteristica(input_destino, idCaracteristica, checkbox, input_destino_nomes, nomeCaracteristica, nomeValorCaracteristica){
	if( $F(input_destino).length > 0){
		var caracs = $H($F(input_destino).evalJSON());
		if($(input_destino_nomes) != null){
			if($F(input_destino_nomes).length > 0){
				var nomesCaracs = $H($F(input_destino_nomes).evalJSON());
			}else{
				var nomesCaracs = $H();
			}
		}
	}else{
		return;
	}
	
	var listaValoresCaracteristica = caracs.get(idCaracteristica);
	if(listaValoresCaracteristica == null){
		checkbox.checked=false;
		return;
	}

	if(checkbox.checked){
		//$(input_destino).value = addValorCSV($F(input_destino), checkbox.value);
		var listaValores = caracs.get(idCaracteristica);
		if(listaValores != null){
			var resultado = addValorCSV(listaValores, checkbox.value);
			caracs.set(idCaracteristica, resultado);
		}
		if($(input_destino_nomes) != null){
			var listaNomesValores = nomesCaracs.get(nomeCaracteristica);
			if(listaNomesValores != null){
				var resultado = addValorCSV(listaNomesValores, nomeValorCaracteristica);
				nomesCaracs.set(nomeCaracteristica, resultado);
			}
		}
	}else{
		//$(input_destino).value = removeValorCSV($F(input_destino), checkbox.value);
		var listaValores = caracs.get(idCaracteristica);
		var resultado = removeValorCSV(listaValores, checkbox.value);
		caracs.set(idCaracteristica, resultado);
		if($(input_destino_nomes) != null){
			var listaNomesValores = nomesCaracs.get(nomeCaracteristica);
			var resultado = removeValorCSV(listaNomesValores, nomeValorCaracteristica);
			nomesCaracs.set(nomeCaracteristica, resultado);
		}
	}
	$(input_destino).value = caracs.toJSON();
	if($(input_destino_nomes) != null){
		$(input_destino_nomes).value = nomesCaracs.toJSON();
	}
}

function selecionarNomesCaracteristicas(input_destino_nomes, nome, checkbox){
	if($F(input_destino_nomes).length > 0){
		var nomesCaracs = $H($F(input_destino_nomes).evalJSON());
	}else{
		var nomesCaracs = $H();
	}
	
	if(checkbox.checked){
		nomesCaracs.set(nome, "");
	}else{
		nomesCaracs.unset(nome);
	}
	$(input_destino_nomes).value = nomesCaracs.toJSON();
}

function selecionarNomeValorCaracteristica(input_destino_nomes, nomeCaracteristica, nomeValorCaracteristica, checkbox){
	if($F(input_destino_nomes).length > 0){
		var nomesCaracs = $H($F(input_destino_nomes).evalJSON());
	}else{
		return;
	}
	
	var listaNomesValoresCaracteristica = nomesCaracs.get(nomeCaracteristica);
	if(listaNomesValoresCaracteristica == null){
		return;
	}

	if(checkbox.checked){
		var listaNomesValores = nomesCaracs.get(nomeCaracteristica);
		var resultado = addValorCSV(listaNomesValores, nomeValorCaracteristica);
		nomesCaracs.set(nomeCaracteristica, resultado);
	}else{
		var listaNomesValores = nomesCaracs.get(nomeCaracteristica);
		var resultado = removeValorCSV(listaNomesValores, nomeValorCaracteristica);
		nomesCaracs.set(nomeCaracteristica, resultado);
	}
	$(input_destino_nomes).value = nomesCaracs.toJSON();
}

function selecionarTecnologiasFrequencias_Tecnologia(input_destino, checkbox, idTecnologiaPai){
	if(idTecnologiaPai != 0 && checkbox.checked){
		if($('tecnologias_cb_'+idTecnologiaPai).checked==false){
			$('tecnologias_cb_'+idTecnologiaPai).checked=true;
			$('tecnologias_cb_'+idTecnologiaPai).onclick();
		}
	}else{
		if(idTecnologiaPai == 0 && !checkbox.checked){
			var checkboxes = $('lista_selecionar_tecnologias').select('.checkbox');
			for(var i = 0; i < checkboxes.length; i++){
				if($F(checkboxes[i].next('input')) == checkbox.value){
					checkboxes[i].checked=false;
					checkboxes[i].onclick();
				}
			}
		}
		lista_selecionar_tecnologias
	}
	if( $F(input_destino).length > 0){
		var tecnos = $H($F(input_destino).evalJSON());
	}else{
		var tecnos = $H();
	}
	if(checkbox.checked){
		tecnos.set(checkbox.value, $H());
	}else{
		tecnos.unset(checkbox.value);
		var tiposFrequencias = $('tiposFrequencia_tecnologia_'+checkbox.value).select('.checkbox');
		for(var i = 0; i < tiposFrequencias.length; i++){
			tiposFrequencias[i].checked=false;
			var frequencias = $('frequencias_tipoFrequencia_'+checkbox.value+'_'+tiposFrequencias[i].value).select('.checkbox');
			for(var j = 0; j < frequencias.length; j++){
				frequencias[j].checked=false;
			}
		}
	}
	$(input_destino).value = tecnos.toJSON();
}

function selecionarTecnologiasFrequencias_TipoFrequencia(input_destino, idTecnologia, checkbox){
	if( $F(input_destino).length > 0){
		var tecnos = $H($F(input_destino).evalJSON());
	}else{
		checkbox.checked=false;
		return;
	}

	
	var hashTiposFrequencias = tecnos.get(idTecnologia);
	if(hashTiposFrequencias == null){
		checkbox.checked=false;
		return;
	}
	hashTiposFrequencias = $H(hashTiposFrequencias);
	if(checkbox.checked){
		if( hashTiposFrequencias.get(checkbox.value) == null){
			hashTiposFrequencias.set(checkbox.value, "");
		}
	}else{
		hashTiposFrequencias.unset(checkbox.value);
		var frequencias = $('frequencias_tipoFrequencia_'+idTecnologia+'_'+checkbox.value).select('.checkbox');
		for(var i = 0; i < frequencias.length; i++){
			frequencias[i].checked=false;
		}
	}
	tecnos.set(idTecnologia, hashTiposFrequencias);
	$(input_destino).value = tecnos.toJSON();
}

function selecionarTecnologiasFrequencias_Frequencia(input_destino, idTecnologia, idTipoFrequencia, checkbox){
	if( $F(input_destino).length > 0){
		var tecnos = $H($F(input_destino).evalJSON());
	}else{
		checkbox.checked=false;
		return;
	}
	
	var hashTipoFrequencia = tecnos.get(idTecnologia);
	if(hashTipoFrequencia == null){
		checkbox.checked=false;
		return;
	}
	hashTipoFrequencia = $H(hashTipoFrequencia);
	var listaFrequencias = hashTipoFrequencia.get(idTipoFrequencia);
	if(listaFrequencias == null){
		checkbox.checked=false;
		return;
	}
	var resultado;
	if(checkbox.checked){
		resultado = addValorCSV(listaFrequencias, checkbox.value);
	}else{
		resultado = removeValorCSV(listaFrequencias, checkbox.value);
	}
	hashTipoFrequencia.set(idTipoFrequencia, resultado);
	tecnos.set(idTecnologia, hashTipoFrequencia);
	$(input_destino).value = tecnos.toJSON();
}

function jsonListarFabricantes(url, id_tipo_produto, suffix_select){
	posicionarDivErros('right_section');
	if(id_tipo_produto == ""){
		$('select_fabricante').options.length = 1;
		return;
	}
	showSpinner();
	if($('select_modelo')){
		$('select_modelo').options.length = 1;
	}
	var params = new Hash();
	params.set('idTipoProduto', id_tipo_produto);
	if(suffix_select != null){
		params.set('suffix_select', suffix_select);
	}
	new Ajax.Request(url, {evalJSON: 'true',  method: 'get',parameters: params, onFailure: doFailure, onComplete: retornoListarFabricantes })
}

function retornoListarFabricantes(originalRequest){

	var suffix_select = "";
   if(originalRequest.request.parameters.suffix_select){
   	var suffix_select = "_" + originalRequest.request.parameters.suffix_select;
   }
   // CARTAO, fabricante opcional
   if(originalRequest.request.parameters.idTipoProduto &&  originalRequest.request.parameters.idTipoProduto == 9){
   		if($('select_fabricante'+suffix_select) != null && !($('select_fabricante'+suffix_select).hasClassName('notRequired'))){
	   		setOptional('select_fabricante'+suffix_select);
	   	}
   }else{
   		if($('select_fabricante'+suffix_select) != null && !($('select_fabricante'+suffix_select).hasClassName('notRequired'))){
	   		setRequired('select_fabricante'+suffix_select);
	   	}
   }
   
   //CARTÃO, tecnologia opcional e escondido
   if(originalRequest.request.parameters.idTipoProduto && originalRequest.request.parameters.idTipoProduto == 9){
   		if($('div_select_tecnologias_frequencias') && $('hiddenTecnologiasFrequencias') != null && !($('hiddenTecnologiasFrequencias').hasClassName('notRequired'))){
	   		$('div_select_tecnologias_frequencias').style.visibility="hidden";
	   		setOptional('hiddenTecnologiasFrequencias');
	   	}
   }else{
   		if($('div_select_tecnologias_frequencias')){
   			$('div_select_tecnologias_frequencias').style.visibility="";
   		}
   }
   
   if(originalRequest.request.parameters.idTipoProduto && (
   	originalRequest.request.parameters.idTipoProduto == 1 || originalRequest.request.parameters.idTipoProduto == 6 || originalRequest.request.parameters.idTipoProduto == 2 || originalRequest.request.parameters.idTipoProduto == 11
   	)){
   		if($('hiddenTecnologiasFrequencias') != null && !($('hiddenTecnologiasFrequencias').hasClassName('notRequired'))){
	   		setRequired('hiddenTecnologiasFrequencias');
	   	}
   }else{
   		if($('hiddenTecnologiasFrequencias') != null && !($('hiddenTecnologiasFrequencias').hasClassName('notRequired'))){
	   		setOptional('hiddenTecnologiasFrequencias');
	   	}
   }
   
   
   
   var select_fabricante = $('select_fabricante'+suffix_select)
   select_fabricante.options.length = 1;
   
   	// Get JSON values 
   jsonRaw = originalRequest.responseText;
   // Eval JSON response into variable 
   if(jsonRaw != null && !jsonRaw.blank()){
	   jsonContent = eval("(" + jsonRaw + ")");
		if(jsonContent != null && jsonContent.arrayFabricantes.length!=0){
			select_fabricante.disabled=false
			for (i = 0; i < jsonContent.arrayFabricantes.length; i++) {
				select_fabricante.options[i+1] = new Option(jsonContent.arrayFabricantes[i].nome, jsonContent.arrayFabricantes[i].id);
			}
			if(jsonContent.arrayFabricantes.length == 1 && jsonContent.arrayFabricantes[0].id == '-1'){
				select_fabricante.selectedIndex = 1;
				if($('select_modelo'+suffix_select)){
					jsonListarModelos(select_fabricante.next('a').href, $F('select_tipo_produto'+suffix_select), $F(select_fabricante));
				}
			}
			if($('select_tecnologia'+suffix_select) != null){
				if(jsonContent.tecnologiaObrigatoria){
					$('select_tecnologia'+suffix_select).up().style.visibility = '';
					$('select_tecnologia'+suffix_select).disabled = '';
				}else{
					$('select_tecnologia'+suffix_select).up().style.visibility = 'hidden';
					$('select_tecnologia'+suffix_select).disabled = 'true';
				}
			}
		}
	}
	hideSpinner();
}

function jsonListarModelos(url, id_tipo_produto, id_fabricante){
	posicionarDivErros('right_section');
	if(id_tipo_produto == "" || id_fabricante == ""){
		$('select_modelo').options.length = 1;
		return;
	}
	showSpinner();
	var params = new Hash();
	params.set('idTipoProduto', id_tipo_produto);
	params.set('idFabricante', id_fabricante);
	new Ajax.Request(url, {evalJSON: 'true',  method: 'get',parameters: params, onFailure: doFailure, onComplete: retornoListarModelos })
}

function retornoListarModelos(originalRequest){
	if(originalRequest.responseText == null || originalRequest.responseText.replace("/^\s+|\s+$/g","") == ""){
		var select_modelo = $('select_modelo')
  		select_modelo.options.length = 1;
  		hideSpinner();
	}
	// Get JSON values 
   jsonRaw = originalRequest.responseText;
   // Eval JSON response into variable 
   jsonContent = eval("(" + jsonRaw + ")");

   // Loop over address book length.
   var select_modelo = $('select_modelo')
   select_modelo.options.length = 1;
   for (i = 0; i < jsonContent.arrayModelos.length; i++) {
   		select_modelo.options[i+1] = new Option(jsonContent.arrayModelos[i].nome, jsonContent.arrayModelos[i].id);
   }
   hideSpinner();
}

function checkRequired(form, id_bloco, first){
	if($(id_bloco) != null){
		posicionarDivErros(id_bloco);
	}
	clearErrors();
	var hasErrors = false;
	var camposObrigatorios = "";
	
	// Verificacao especifica para consulta de servicos
	var at_least_one = false;
	var requiredElements = $(form).select('.at_least_one');
	if(requiredElements.length > 0){
		at_least_one = true;
	}
	for(var i = 0; i < requiredElements.length; i++){
		if(!requiredElements[i].disabled){
			if(!$F(requiredElements[i]).blank()){
				at_least_one = false;
			}
		}
	}
	if(at_least_one){
		hasErrors = true;
		camposObrigatorios += "É Obrigatório informar o(s) campo(s) Nome do Serviço <b>ou</b> o Código do serviço";
	}
	
	// Verificacao especifica para Modelo de Vendas
	var at_least_two = false;
	var requiredElements = $(form).select('.at_least_two');
	if(requiredElements.length > 0){
		at_least_two = true;
	}
	for(var i = 0; i < requiredElements.length; i++){
		if(!requiredElements[i].disabled){
			if(!$F(requiredElements[i]).blank()){
				at_least_two = false;
			}
		}
	}
	if(at_least_two){
		hasErrors = true;
		camposObrigatorios += "É Obrigatório informar o(s) campo(s) Código do Modelo de Vendas <b>ou</b> o Nome do Modelo de Vendas";
	}
	
	requiredElements = $(form).select('.required');
	for(var i = 0; i < requiredElements.length; i++){
		if(!requiredElements[i].disabled){
			if($F(requiredElements[i]).blank() || $F(requiredElements[i]) == "{}"){
				hasErrors = true;
				if(camposObrigatorios != ""){
					camposObrigatorios += ", ";
				}else{
					camposObrigatorios += "É Obrigatório informar o(s) campo(s) ";
				}
				camposObrigatorios += requiredElements[i].previous('.label_required').innerHTML.replace('\*', '').replace(':', '');
			}
		}
	}
	if(hasErrors){
		addError(camposObrigatorios, true);
	}
	var requiredElements = $(form).select('.min_size_required');
	for(var i = 0; i < requiredElements.length; i++){
		if(!requiredElements[i].disabled){
			var minSize = requiredElements[i].previous('.min_size_required_value').innerHTML;
			if(!$F(requiredElements[i]).blank() && $F(requiredElements[i]).length < minSize){
				hasErrors = true;
				addError(requiredElements[i].previous('.min_size_required_message').innerHTML, true);
			}
		}
	}
	
	if($('periodo_de') != null &&  $('periodo_ate') != null){
		if( (!$F('periodo_de').blank() && $F('periodo_ate').blank())
			|| ($F('periodo_de').blank() && !$F('periodo_ate').blank())){
				hasErrors = true;
				addError('Obrigatório informar início <b>e</b> fim do período.', true);
			}
	}
	if(hasErrors){
		abrirPopupErros();
	}
	return hasErrors;
}

function checkDatas(form){
	var datas = $(form).select('.data');
	var hasErrors = false;
	for(var i = 0; i < datas.length; i++){
		if(!datas[i].disabled){
			if($F(datas[i])!=""){
				if(!isValidData($F(datas[i]))){
					addError(datas[i].previous('.data_nome_campo').innerHTML.replace('*', '').replace(':', '')+ ': Data inválida ou fora do padrão DD/MM/AAAA.', true);
					hasErrors = true;
				}
			}
		}
	}
	if(hasErrors){
		abrirPopupErros();
	}
	return hasErrors;
}

function clearErrors(){
	var conteudo_divErros = $('conteudo_divErros');
	conteudo_divErros.innerHTML = '';
	var lista = new Element('ul', { 'id': 'popup_erros_ul' });
	conteudo_divErros.insert(lista);
}

function addError(campo, textOnly, showNow){
	if(showNow){
		clearErrors();
	}
	var conteudo_divErros = $('popup_erros_ul');
	if(textOnly){
		var li = new Element('li', { 'class': 'foo' }).update(campo);
	}else{
		var li = new Element('li', { 'class': 'foo' }).update(campo+" campo obrigatório.");
	}
	conteudo_divErros.insert(li);
	if(showNow){
		abrirPopupErros();
	}
}

function selecionarTodosCheckbox(checkboxes, checked){
	checkboxes.each(function(checkbox){
		checkbox.checked = !checked;
		checkbox.click();
	});
}

function selecionarTodosCheckboxUfs(checkboxes, checked){
	checkboxes.each(function(checkbox){
		checkbox.checked = !checked;
		checkbox.click();
	});
}

function mostrarValoresCaracteristica(div_valores, id_caracteristica){
	$(div_valores).select('table').invoke('hide');
	if($('valores_caracteristica_'+id_caracteristica)){
		$('valores_caracteristica_'+id_caracteristica).show();
		$('valores_caracteristica_'+id_caracteristica).style.visibility = '';
	}
	
}

function mostrarTiposFrequenciaTecnologia(div_tiposFrequencia, id_tecnologia){
	$(div_tiposFrequencia).select('table').invoke('hide');
	if($('tiposFrequencia_tecnologia_'+id_tecnologia)){
		$('tiposFrequencia_tecnologia_'+id_tecnologia).show();
		$('tiposFrequencia_tecnologia_'+id_tecnologia).style.visibility = '';
	}
	$('div_frequencias').select('table').invoke('hide');
	
}

function mostrarFrequenciasTipoFrequenciaTecnologia(div_frequencias, id_tecnologia, id_tipoFrequencia){
	$(div_frequencias).select('table').invoke('hide');
	if($('frequencias_tipoFrequencia_'+id_tecnologia + '_' + id_tipoFrequencia)){
		$('frequencias_tipoFrequencia_'+id_tecnologia + '_' + id_tipoFrequencia).show();
		$('frequencias_tipoFrequencia_'+id_tecnologia + '_' + id_tipoFrequencia).style.visibility = '';
	}
	
}

function retornoUploadImagem(id, id_bloco) {
	posicionarDivErros('right_section');
	var i = id;
	if (i.contentDocument) {
		var d = i.contentDocument;
	} else if (i.contentWindow) {
		var d = i.contentWindow.document;
	} else {
		var d = window.frames[id].document;
	}

	if (d.location.href == "about:blank") {
		return;
	}

	if(d.body.firstChild.tagName == 'INPUT'){
		$(d.body.firstChild.value).onclick()
	}else{
		posicionarDivErros(id_bloco);
		$('conteudo_divErros').innerHTML = d.body.innerHTML;
		doFailure();
	}
}

function retornoDownloadFile(id, id_bloco) {
	posicionarDivErros(id_bloco);
	clearErrors();
	var i = id;
	if (i.contentDocument) {
		var d = i.contentDocument;
	} else if (i.contentWindow) {
		var d = i.contentWindow.document;
	} else {
		var d = window.frames[id].document;
	}
	if(!d.body.innerHTML.blank()){
		addError(d.body.innerHTML, true);
		abrirPopupErros();
		
	}
}


function checkFiltrosDesconto(){
	if($F('select_canal') != "" && $F('select_fpagamento') != ""){
		return true;
	}else{
		return false;
	}
}

function setRequired(id_elemento){
	$(id_elemento).addClassName('required');
	$(id_elemento).previous('.label_required').innerHTML = $(id_elemento).previous('.label_required').innerHTML.replace('\*', '') + '<font size="1px" color="#EEB422" valign="center">*</font>';
}

function setOptional(id_elemento){
	$(id_elemento).removeClassName('required');
	$(id_elemento).previous('.label_required').innerHTML = $(id_elemento).previous('.label_required').innerHTML.replace('\*', '');
}

function changeRequirementFormaCondPagamento(index, id_elemento){
	if(index <3){
		setRequired(id_elemento);
		$(id_elemento).enable();
	}else{
		setOptional(id_elemento);
		$(id_elemento).selectedIndex=0;
		$(id_elemento).disable();
	}
}

function limparForm(button){
	var form = $($(button).form);
	form.getElements().each(clear);
}

function clear(s){
	if(s.type){
		switch (s.type.toUpperCase()) {
			case "TEXT":
				s.value="";
				break
			case "SELECT-ONE":
				s.selectedIndex=0;
				break
			case "CHECKBOX":
				s.checked=false;
				break
			case "HIDDEN":
				s.value="";
				break
			case "TEXTAREA":
				s.value="";
				break
			case "FILE":
				s.value="";
				break
			default:
				return;
		}
	}
}

function extractValue(element){
	return $F(element);
}

function selectTodosCheckbox(form, class_checkboxes, checked){
	var checkboxes =  $(form).select(class_checkboxes);
	for( var i = 0 ; i < checkboxes.length; i++ ){
		checkboxes[i].checked = checked;
	}
}

function selectTodosCheckboxPlanoServico(form, class_checkboxes, checked){
	var checkboxes =  $(form).select(class_checkboxes);
	for( var i = 0 ; i < checkboxes.length; i++ ){
		checkboxes[i].checked = !checked;
		checkboxes[i].click();
	}
}

function toggleCheckbox(url, checked, id_bloco){
	posicionarDivErros(id_bloco);
	var params =  new Hash();
	if(checked){
		params.set('checked', 'S');
	}else{
		params.set('checked', 'N');
	}
	showSpinner();
	new Ajax.Request(url, {method:'post',parameters:params,onSuccess: doSuccess,onFailure: doFailure, onComplete: retornoToggleCheckbox });
}

function toggleCheckboxFrequenciaTecnologiaTipoFrequencia(url, checked, id_bloco){
	if(checked){
		posicionarDivErros(id_bloco);
		var params =  new Hash();
		params.set('checked', 'S');
		showSpinner();
		new Ajax.Request(url, {method:'post',onSuccess: doSuccess,parameters:params,onFailure: doFailure });
	}else{
		posicionarDivErros(id_bloco);
		var params =  new Hash();
		params.set('popup', 'popup2');
		params.set('checked', 'N');
	
		$('popup2Cortina').show();
		showSpinner();
		new Ajax.Updater({success:'popup2'}, url, {parameters:params, onSuccess: retornoAbrirPopup2, onFailure: doFailure, onComplete: doComplete });
		
		
	}
}

function retornoToggleCheckbox(t){
	doComplete(t);
}

function mascara_data(e, input){ 
	var tecla;
	if(document.all) // Internet Explorer
		tecla = event.keyCode;
	else 
		tecla = e.which;  // Nestcape
	var mydata = ''; 
	mydata = mydata + input.value; 
	if (tecla == 8)
		return true;
	if(mydata.length < 2 || (mydata.length > 2  && mydata.length <5) || (mydata.length > 5 && mydata.length < 10)){
		if(tecla > 47 && tecla < 58) // numeros de 0 a 9
			return true;
		else
			return false;
	}else{
		if(mydata.length == 2 || mydata.length == 5){
			if (tecla == 47) // slash
				return true;
			else
				return false;
		}
	}
	return false;
}

/**
 * Verifica se uma data e valida
 * @param {String} strData Data (dd/mm/yyyy)
 * @return {Boolean} Retorna true se a data for valida, false caso contrario
 */
function isValidData(strData) {
    var bissexto = 0;
    var tam = strData.length;

	if (strData == '' || tam == 0) {
		return true;
	} 
	else if (tam == 10) {
        var dia = strData.substr(0,2)
        var mes = strData.substr(3,2)
        var ano = strData.substr(6,4)
        if ((ano > 1900)||(ano < 2100)) {
            switch (mes) {
                case '01':
                case '03':
                case '05':
                case '07':
                case '08':
                case '10':
                case '12':
                    if  (dia <= 31) {
                        return true;
                    }
                    break
                case '04':        
                case '06':
                case '09':
                case '11':
                    if  (dia <= 30) {
                        return true;
                    }
                    break
                case '02':
                    // Validando ano Bissexto / fevereiro / dia
                    if ((ano % 4 == 0) || (ano % 100 == 0) || (ano % 400 == 0)){ 
                        bissexto = 1; 
                    } 
                    if ((bissexto == 1) && (dia <= 29)) { 
                        return true;                 
                    } 
                    if ((bissexto != 1) && (dia <= 28)) { 
                        return true; 
                    }            
                    break                        
            }
        }
    } 
    return false;
}

function checkMaxImagemModelo(totalImagens){
	clearErrors();	
	if(totalImagens >= 50){
		addError('Número de multímidias do modelo ultrapassa o limite máximo de 50');
		abrirPopupErros();
		return false;
	}
	return true;
}

function checkSelecionarCaracteristicasModelo(caracteristicasSelecionadas, checkBox){
	posicionarDivErros('div_erros_popup');
	clearErrors();	
	if(!$F(caracteristicasSelecionadas).blank()){
		var mapaCaracteristicas = $H($F(caracteristicasSelecionadas).evalJSON());
		var caracs = mapaCaracteristicas.keys();
		for (var i = 0; i < caracs.length; i++) {
			var carac = caracs[i];
			if(mapaCaracteristicas.get(carac).blank() && ((checkBox == null) || (carac != checkBox.value))){
				if($('valores_caracteristica_'+carac) != null){
					if( $('valores_caracteristica_'+carac).select('input') != null && $('valores_caracteristica_'+carac).select('input').length != 0){
						addError('Obrigatório selecionar, no mínimo, um valor para características com valores.', true);
						abrirPopupErros();
						return false;
					}
				}
			}
		}
	}
	return true;
}

function checkSelecionarTecTpFreqFreq(selecionadas){
	posicionarDivErros('div_erros_popup');
	clearErrors();
	var hasErrors = false;
	var mapaSelecionadas = $H($F(selecionadas).evalJSON());
	var tecnologias = mapaSelecionadas.keys();
	for (var i = 0; i < tecnologias.length; i++) {
		var tecnologia = tecnologias[i];
		var tiposFrequenciaMap = $H(mapaSelecionadas.get(tecnologia));
		if(tiposFrequenciaMap.keys().length == 0 ){
			if($('tiposFrequencia_tecnologia_'+tecnologia) != null){
				if( $('tiposFrequencia_tecnologia_'+tecnologia).select('input') != null && $('tiposFrequencia_tecnologia_'+tecnologia).select('input').length != 0){
					addError('Obrigatório informar tipo de frequência e frequência para a tecnologia '+ $('label_tecnologia_'+tecnologia).innerHTML, true);
					hasErrors = true;
				}
			}
		}else{
			var tiposFrequencias = tiposFrequenciaMap.keys();
			for(var j = 0; j < tiposFrequencias.length; j++){
				var tipoFrequencia = tiposFrequencias[j];
				if(tiposFrequenciaMap.get(tipoFrequencia).blank()){
					if($('frequencias_tipoFrequencia_'+tecnologia+'_'+tipoFrequencia) != null){
						if( $('frequencias_tipoFrequencia_'+tecnologia+'_'+tipoFrequencia).select('input') != null && $('frequencias_tipoFrequencia_'+tecnologia+'_'+tipoFrequencia).select('input').length != 0){
							addError('Obrigatório informar tipo de frequência e frequência para a tecnologia '+ $('label_tecnologia_'+tecnologia).innerHTML, true);
							hasErrors = true;
						}
					}
				}else{
					var frequencias = $A(tiposFrequenciaMap.get(tipoFrequencia).split(",")).compact();
					if(frequencias.length != $('qt_frequencia_'+tecnologia+'_'+tipoFrequencia).innerHTML){
						addError('Quantidade de frequência incorreta para o tipo de frequência '+ $('label_tipo_frequencia_'+tecnologia+'_'+tipoFrequencia).innerHTML+ ' e tecnologia '+$('label_tecnologia_'+tecnologia).innerHTML, true);
						hasErrors = true;
					}
				}
			}
		}
	}
	if(hasErrors){
		abrirPopupErros();
		return false;
	}
	return true;
}

function createDivErros(){
var divErros = new Element('div', {'id': 'divErros', 'style':'display:none;background-color: white;'});
var html = '<div class="box" style="position:relative;">'
+'						<div class="box_simples_header">'
+'							<div class="box_simples_top_center">&nbsp;'
+'							</div>'
+'							<div class="box_simples_top_left">'
+'							</div>'
+'							<div class="box_simples_top_right">'
+'							</div>'
+'						</div>'
+'						<div class="box_middle"  style="position:relative;">'
+'							<table border="0" cellpadding="0" cellspacing="0" width="100%">'
+'							<tr>'
+'								<td rowspan="2" class="box_middle_left">'
+'								</td>'
+'								<td>'
+'									<div class="box_middle_center">'
+'										<div id="conteudo_divErros" class="box_middle_center_conteudo">'
+'										</div>'
+'									</div>'
+'								</td>'
+'								<td rowspan="2" class="box_middle_right">'
+'								</td>'
+'							</tr>'
+'						</table>'
+'						</div>'
+'						<div class="box_bottom">'
+'							<div class="box_bottom_center">'
+'							</div>'
+'							<div class="box_bottom_left">'
+'							</div>'
+'							<div class="box_bottom_right">'
+'							</div>'
+'						</div>'
+'					</div>';
divErros.innerHTML = html;
return divErros;
}

function maxSize(size, input){
	if($F(input).length > size){
		input.value=$F(input).substring(0,size);
	}
}

function clearAndShow(id_div){
	var div = $(id_div);
	if(div != null){
		div.innerHTML = '';
		div.show();
	}
}

function desabilitar(id_input){
	var botao = $(id_input);
	if(botao != null){
		if(botao.hasClassName('btNavegacao74')){
			var className = 'btNavegacao74';
		}else if(botao.hasClassName('btNavegacao120')){
			var className = 'btNavegacao120';
		}
		if(className != null){
			botao.removeClassName(className);
			botao.addClassName(className+'Inativo');
		}
		botao.disable();
	}
}

function habilitar(id_input){
	var botao = $(id_input);
	if(botao != null){
		if(botao.hasClassName('btNavegacao74Inativo')){
			var className = 'btNavegacao74';
		}else if(botao.hasClassName('btNavegacao120Inativo')){
			var className = 'btNavegacao120';
		}
		if(className != null){
			botao.removeClassName(className+'Inativo');
			botao.addClassName(className);
		}
		botao.enable();
	}
}

function checkSelecaoProdutos(array_ids_produtos){
	if(array_ids_produtos.compact().size() == 0){
		posicionarDivErros('resultado_pesquisa');
		clearErrors();
		addError('Por favor, selecione os produtos a serem associados.', true);
		abrirPopupErros();
		return true;
	}
	return false;
}

function checkSelecaoServicos(array_ids_servicos){
	if(array_ids_servicos.compact().size() == 0){
		posicionarDivErros('resultado_pesquisa');
		clearErrors();
		addError('Por favor, selecione os serviços a associar.', true);
		abrirPopupErros();
		return true;
	}
	return false;
}

function checkSelecaoServicosPopup(array_ids_servicos){
	if(array_ids_servicos.compact().size() == 0){
		posicionarDivErros('popup_nova_categoria');
		clearErrors();
		addError('Se não houver dados a ser validado, favor escolher a opção "Fechar"', true);
		abrirPopupErros();
		return true;
	}
	return false;
}

function checkSelecaoPlanosPopup(array_ids_planos){
	if(array_ids_planos.compact().size() == 0){
		posicionarDivErros('popup_altera_planos');
		clearErrors();
		addError('Se não houver dados a ser validado, favor escolher a opção "Fechar"', true);
		abrirPopupErros();
		return true;
	}
	return false;
}

function hasEditavel(){
	if(flagConteudoEditado){
	 	continuar = confirm("Há dados inseridos e não gravados no sistema. As informações serão perdidas. Deseja continuar?");
		if(continuar){
			return false;
		}else{
		 	return true;
		 }
	}
	return false;
}


 function chamadaLinkMenu(url) { 
 	if(hasEditavel()){
 		return false;
 	}
 	clearEditando();
 	
 // Inicia o objeto Ajax  
 	showSpinner();
 	posicionarDivErros('right_section');
	new Ajax.Updater({success:'right_section'}, url, {  
     method: 'get',  
     onComplete: doComplete, onFailure: doFailure, onSuccess: retornoChamadaLinkMenu}
     );  
 }

//Função para capturar a tecla enter quando pressionada em um campo de 
function CapturaTeclaEntra(evt, id_button) {
var tecla = event.keyCode

if (tecla == 13) {
	$(id_button).onclick();
	return true;
} else {
	return false;
  }	
}

// Tela: Formas e Condições de pagamento: ao selecionar uma forma de pagamento, deve ativar o campo de valor minimo da parcela.
function selectFormaPgto(checkbox){
	if(checkbox.checked){
		$(checkbox).up(1).select('.valor_minimo')[0].enable();
	}
	else{
		$(checkbox).up(1).select('.valor_minimo')[0].disable();
	}
}

function mostrarValoresDaChaveNoPopup(div_valores, prefix_valores, idChave){
	$(div_valores).select('table').invoke('hide');
	if($(prefix_valores+idChave)){
		$(prefix_valores+idChave).show();
		$(prefix_valores+idChave).style.visibility = '';
	}
}

function selecionarChavePopup(input_destino, checkbox, prefix,  input_destino_nomes, nome){
	if($F(input_destino).length > 0){
		var idsChaves = $H($F(input_destino).evalJSON());
		if(input_destino_nomes != null && $F(input_destino_nomes).length > 0){
			var nomesChaves = $H($F(input_destino_nomes).evalJSON());
		}
	}else{
		var idsChaves = $H();
		var nomesChaves = $H();
	}
	
	var valores = $(prefix +checkbox.value).select('.checkbox');
	for(var j = 0; j < valores.length; j++){
		valores[j].checked=false;
	}
	if(checkbox.checked){
		idsChaves.set(checkbox.value, "");
		if(input_destino_nomes != null){
			nomesChaves.set(nome, "");
		}
	}else{
		idsChaves.unset(checkbox.value);
		if(input_destino_nomes != null){
			nomesChaves.unset(nome);
		}
	}
	$(input_destino).value = idsChaves.toJSON();
	if(input_destino_nomes != null){
		$(input_destino_nomes).value = nomesChaves.toJSON();
	}
}

function selecionarChavePopupForRadio(input_destino, checkbox, prefix,  input_destino_nomes, nome){
	var idsChaves = $H();
	var nomesChaves = $H();
	var valores = $(prefix +checkbox.value).select('.checkbox');
	for(var j = 0; j < valores.length; j++){
		valores[j].checked=false;
	}
	if(checkbox.checked){
		idsChaves.set(checkbox.value, "");
		if(input_destino_nomes != null){
			nomesChaves.set(nome, "");
		}
	}else{
		idsChaves.unset(checkbox.value);
		if(input_destino_nomes != null){
			nomesChaves.unset(nome);
		}
	}
	$(input_destino).value = idsChaves.toJSON();
	if(input_destino_nomes != null){
		$(input_destino_nomes).value = nomesChaves.toJSON();
	}
}

function selecionarValorDaChavePopup(input_destino, chave, checkbox){
	if( $F(input_destino).length > 0){
		var chaves = $H($F(input_destino).evalJSON());
	}else{
		checkbox.checked=false;
		return;
	}
	
	var valoresDaChave = chaves.get(chave);
	if(valoresDaChave == null){
		checkbox.checked=false;
		return;
	}
	var resultado;
	if(checkbox.checked){
		resultado = addValorCSV(valoresDaChave, checkbox.value);
	}else{
		resultado = removeValorCSV(valoresDaChave, checkbox.value);
	}
	chaves.set(chave, resultado);
	$(input_destino).value = chaves.toJSON();
}

function formaPagtoMostrarBotaoDetalhesTipoProduto(select){
	if(select.selectedIndex == 1){
		$('botaoDetalhesTipoProduto').style.visibility = '';
	}else{
		$('botaoDetalhesTipoProduto').style.visibility = 'hidden';
	}
}
function formaPagtoMostrarDetalhesTipoProduto(botao){
	if($(botao).style.visibility == ''){
		$('detalhesTipoProduto').style.top=$(botao).positionedOffset().top - 87;
		$('detalhesTipoProduto').style.left=$(botao).positionedOffset().left - 162;
		$('detalhesTipoProduto').show();
	}
}

function formaPagtoEsconderDetalhesTipoProduto(botao){
	if($(botao).style.visibility == ''){
		$('detalhesTipoProduto').hide();
	}
}

function jsonListarCategorias(url, id_plataforma, id_sistema){
	posicionarDivErros('right_section');
	if(id_plataforma == ""){
		$('select_categoria').options.length = 1;
		return;
	}
	showSpinner();
	var params = new Hash();
	params.set('idPlataforma', id_plataforma);
	if(id_sistema != null)
		params.set('idSistema', id_sistema);
	new Ajax.Request(url, {evalJSON: 'true',  method: 'get',parameters: params, onFailure: doFailure, onComplete: retornoListarCategorias })
}

function retornoListarCategorias(originalRequest){

	if($('select_sistema') != null){
	   if(originalRequest.request.parameters.idPlataforma &&  originalRequest.request.parameters.idPlataforma == 3){
   			setRequired('select_sistema');
	   }else{
	   		setOptional('select_sistema');
	   }
	}
	
	//$('select_categoria').options.length = 1;
   var select_categoria = $('select_categoria');
   select_categoria.options.length = 1;
   
   	// Get JSON values 
   jsonRaw = originalRequest.responseText;
   // Eval JSON response into variable 
   if(jsonRaw != null && !jsonRaw.blank()){
	   jsonContent = eval("(" + jsonRaw + ")");
		if(jsonContent != null && jsonContent.arrayCategorias.length!=0){
			select_categoria.disabled=false
			for (i = 0; i < jsonContent.arrayCategorias.length; i++) {
				select_categoria.options[i+1] = new Option(jsonContent.arrayCategorias[i].nome, jsonContent.arrayCategorias[i].id);
			}
		}
	}
	hideSpinner();
}


function setRequiredTecnologia(option, id_elemento){
	if(option.value == 3){
		$('divSistema').show();
	}else{
		$('divSistema').hide()
	}
	
	if(option.value == 2){
		setRequired(id_elemento);
	}
	else{
		setOptional(id_elemento);
	}
}

function setRequiredUF(option, id_elemento){
	if(option.value == 2){
		setRequired(id_elemento);
	}
	else{
		setOptional(id_elemento);
	}
}

function sistemaAtysSetRequiredTecnologia(option, id_elemento){
	if(option.value == 2){
		setRequired(id_elemento);
	}
	else{
		setOptional(id_elemento);
	}
}

function sistemaAtysSetRequiredUF(option, id_elemento){
	if(option.value == 2){
		setRequired(id_elemento);
	}
	else{
		setOptional(id_elemento);
	}
}

function setRequiredSistema(option, id_elemento){
	if(option.value == 3){
		setRequired(id_elemento);
	}
	else{
		setOptional(id_elemento);
	}
}

function definirTipoPesquisa(url, id, id_regional, id_categoria, id_titular, id_servicos, id_planos, form){
	$(form).action = url;	
	if($(id).checked && id == 'radio_plano'){
		$(form).reset();
		$(id).checked = true;
		$(id_regional).show();
		$(id_categoria).hide();
		$(id_titular).show();
		$(id_planos).show();
		$(id_servicos).hide();
		posicionarDivErros();
	}
	if($(id).checked && id == 'radio_servico'){
		$(form).reset();
		$(id).checked = true;
		$(id_regional).hide();
		$(id_titular).hide();
		$(id_categoria).show();
		$(id_planos).hide();
		$(id_servicos).show();
		posicionarDivErros();
	}
}

function displayDivSistema(plataforma, div_sistema){
	(plataforma == 3) ? $(div_sistema).show() : $(div_sistema).hide();
}



function isIntNumber(evt){
	var key;
	if(window.event) {
		key = evt.keyCode;
	}
	else if(evt.which) {
		key = evt.which;
	}
	if (key >= 48 && key <= 57 || key == 8 || key == 0 || key == 32) {
		return true;
	}
	else {
		return false;
	}		
}

function isFormatDate(evt, date, bar){
	var key;
	var len  = date.value.length;
	if(window.event) { key = evt.keyCode; }
	else if(evt.which) { key = evt.which; }
	
	if ((key >= 48 && key <= 57) || (key == 8 || key == 0 || key == 32)) {
		  if(len == 2 || len == 5){
		    	key != 8 ? date.value += bar : '';
		    }
		return true;
	}
	else {
		return false;
	}
}

function validarFormImportarAquivoCsv(button, id_bloco, id_bloco_required, arquivo){
	var form = $(button).form;
	var nmAquivo = $(arquivo).value;
	var extensao = nmAquivo.substring(nmAquivo.lastIndexOf('.'), nmAquivo.length).toUpperCase();
	if(checkRequired(form, id_bloco_required != null?id_bloco_required:id_bloco)){
		return false;
	}
	if(extensao != '.CSV'){
		addError(' Tipo de Arquivo Inválido', true);
		abrirPopupErros();
		return false;
	}
	return true;
}

function retornoUploadArquivo(id, id_bloco) {
	posicionarDivErros('right_section');
	var i = id;
	if (i.contentDocument) {
		var d = i.contentDocument;
	} else if (i.contentWindow) {
		var d = i.contentWindow.document;
	} else {
		var d = window.frames[id].document;
	}

	if (d.location.href == "about:blank") {
		return;
	}

	if(d.body.firstChild.tagName == 'INPUT'){
		$(d.body.firstChild.value).onclick()
	}else{
		posicionarDivErros(id_bloco);
		$('conteudo_divErros').innerHTML = d.body.innerHTML;
		doFailure();
	}
}

function verificarSelecaoAll(checkboxList, checkboxTodos){
	if(checkboxList.checked == false){
		$(checkboxTodos).checked = false;
	}
}

function listarFabricantesParaAssociacao(url, id_tipo_produto, suffix_select){
	posicionarDivErros('right_section');
	if(id_tipo_produto == ""){
		$('select_fabricante').options.length = 1;
		return;
	}
	showSpinner();
	if($('select_modelo')){
		$('select_modelo').options.length = 1;
	}
	var params = new Hash();
	params.set('idTipoProduto', id_tipo_produto);
	if(suffix_select != null){
		params.set('suffix_select', suffix_select);
	}
	new Ajax.Request(url, {evalJSON: 'true',  method: 'get',parameters: params, onFailure: doFailure, onComplete: doCompleteFabricantes })
}

function doCompleteFabricantes(fabricantesReq){
	var suffix_select = "";
   if(fabricantesReq.request.parameters.suffix_select){
   	var suffix_select = "_" + fabricantesReq.request.parameters.suffix_select;
   }
   // CARTAO, fabricante opcional
   if(fabricantesReq.request.parameters.idTipoProduto &&  fabricantesReq.request.parameters.idTipoProduto == 9){
   		if($('select_fabricante'+suffix_select) != null && !($('select_fabricante'+suffix_select).hasClassName('notRequired'))){
	   		setOptional('select_fabricante'+suffix_select);
	   	}
   }else{
   		if($('select_fabricante'+suffix_select) != null && !($('select_fabricante'+suffix_select).hasClassName('notRequired'))){
	   		setRequired('select_fabricante'+suffix_select);
	   	}
   }
   
   //CARTÃO, tecnologia opcional e escondido
   if(fabricantesReq.request.parameters.idTipoProduto && fabricantesReq.request.parameters.idTipoProduto == 9){
   		if($('div_select_tecnologias_frequencias') && $('hiddenTecnologiasFrequencias') != null && !($('hiddenTecnologiasFrequencias').hasClassName('notRequired'))){
	   		$('div_select_tecnologias_frequencias').style.visibility="hidden";
	   		setOptional('hiddenTecnologiasFrequencias');
	   	}
   }else{
   		if($('div_select_tecnologias_frequencias')){
   			$('div_select_tecnologias_frequencias').style.visibility="";
   		}
   }
   
   if(fabricantesReq.request.parameters.idTipoProduto && (
   	fabricantesReq.request.parameters.idTipoProduto == 1 || fabricantesReq.request.parameters.idTipoProduto == 6 || fabricantesReq.request.parameters.idTipoProduto == 2 || fabricantesReq.request.parameters.idTipoProduto == 11
   	)){
   		if($('hiddenTecnologiasFrequencias') != null && !($('hiddenTecnologiasFrequencias').hasClassName('notRequired'))){
	   		setRequired('hiddenTecnologiasFrequencias');
	   	}
   }else{
   		if($('hiddenTecnologiasFrequencias') != null && !($('hiddenTecnologiasFrequencias').hasClassName('notRequired'))){
	   		setOptional('hiddenTecnologiasFrequencias');
	   	}
   }
   
   var select_fabricante = $('select_fabricante'+suffix_select)
   select_fabricante.options.length = 1;
   
   	// Get JSON values 
   jsonRaw = fabricantesReq.responseText;
   // Eval JSON response into variable 
   if(jsonRaw != null && !jsonRaw.blank()){
	   jsonContent = eval("(" + jsonRaw + ")");
		if(jsonContent != null && jsonContent.arrayFabricantes.length!=0){
			select_fabricante.disabled=false
			for (i = 0; i < jsonContent.arrayFabricantes.length; i++) {
				select_fabricante.options[i+1] = new Option(jsonContent.arrayFabricantes[i].nome, jsonContent.arrayFabricantes[i].id);
			}
			if(jsonContent.arrayFabricantes.length == 1 && jsonContent.arrayFabricantes[0].id == '-1'){
				select_fabricante.selectedIndex = 1;
				if($('select_modelo'+suffix_select)){
					jsonListarModelos(select_fabricante.next('a').href, $F('select_tipo_produto'+suffix_select), $F(select_fabricante));
				}
			}
		}
	}
	hideSpinner();
}

function checkSelecaoProdutosSemAssociacao(array_ids_produtos){
	if(array_ids_produtos.compact().size() == 0){
		posicionarDivErros('resultado_pesquisa_popup');
		clearErrors();
		addError('Por favor, selecione um ou mas produtos a associar.', true);
		abrirPopupErros();
		return true;
	}
	return false;
}

function checkSelecaoServicosSemAssociacao(array_ids_servicos){
	if(array_ids_servicos.compact().size() == 0){
		posicionarDivErros('resultado_pesquisa_popup');
		clearErrors();
		addError('Por favor, selecione um ou mas serviços a associar.', true);
		abrirPopupErros();
		return true;
	}
	return false;
}


function listarNomeMatrizOferta(url, dataVigencia, suffix_select){
	posicionarDivErros('right_section');
	if(dataVigencia == ""){
		$('select_matrizOferta').options.length = 1;
		return;
	}
	showSpinner();
	var params = new Hash();
	params.set('dtVigencia', dataVigencia);
	if(suffix_select != null){
		params.set('suffix_select', suffix_select);
	}
	new Ajax.Request(url, {evalJSON: 'true',  method: 'get',parameters: params, onFailure: doFailure, onComplete: retornoListarNomeMatrizOferta })
}


function retornoListarNomeMatrizOferta(originalRequest){
	var suffix_select = "";
   if(originalRequest.request.parameters.suffix_select){
   	var suffix_select = "_" + originalRequest.request.parameters.suffix_select;
   }

   var select_matrizOferta = $('select_matrizOferta'+suffix_select)
   select_matrizOferta.options.length = 1;
   jsonRaw = originalRequest.responseText;
   if(jsonRaw != null && !jsonRaw.blank()){
	   jsonContent = eval("(" + jsonRaw + ")");
		if(jsonContent != null && jsonContent.arrayMatrizOferta.length!=0){
			select_matrizOferta.disabled=false
			for (i = 0; i < jsonContent.arrayMatrizOferta.length; i++) {
				select_matrizOferta.options[i+1] = new Option(jsonContent.arrayMatrizOferta[i].nome, jsonContent.arrayMatrizOferta[i].id);
			}
			if(jsonContent.arrayMatrizOferta.length == 1 && jsonContent.arrayMatrizOferta[0].id == ''){
				select_matrizOferta.selectedIndex = 0;
			}
		}
	}
	hideSpinner();
}

function listarTipoCarteira(url, id_tipo_cliente){
	posicionarDivErros('right_section');
	if(id_tipo_cliente == ""){
		$('select_carteira').options.length = 1;
		return;
	}
	showSpinner();
	var params = new Hash();
	params.set('idTipoCliente', id_tipo_cliente);
	new Ajax.Request(url, {evalJSON: 'true',  method: 'get',parameters: params, onFailure: doFailure, onComplete: retornoListarTipoCarteira })
}

function retornoListarTipoCarteira(carteiraRequest){
	if(carteiraRequest.responseText == null || carteiraRequest.responseText.replace("/^\s+|\s+$/g","") == ""){
		var select_carteira = $('select_carteira')
  		select_carteira.options.length = 1;
  		hideSpinner();
	}
   jsonRaw = carteiraRequest.responseText;
   jsonContent = eval("(" + jsonRaw + ")");
   var select_carteira = $('select_carteira')
   select_carteira.options.length = 1;
   for (i = 0; i < jsonContent.carteiras.length; i++) {
   		select_carteira.options[i+1] = new Option(jsonContent.carteiras[i].descricao, jsonContent.carteiras[i].id);
   }
   hideSpinner();
}

/**
* Alterado o nome porque ja existe uma function com o mesmo nome: "definirTipoPesquisa". Calixto 
*/

function definirTipoPesquisaAcessoPerfil(option, idTextBox){
	$('select_plataforma').value = ''
	$('hidden_lista_perfil_plano_perfil').value = '';
	$('hidden_lista_perfil_servico_perfil').value = '';
	$('nome_tecnico').value = '';
	$('hidden_nmComercial_plano_perfil').value = '';
	$('hidden_nmComercial_servico_perfil').value = '';
	habilitarTextBoxNmComercial(idTextBox, 4);	
	
	if(option == 'planoPerfil' || option == 'servicoPerfil'){
		setRequired('hidden_lista_perfil_plano_perfil');
		setOptional('hidden_lista_perfil_servico_perfil');
		$('div_perfil_planoPerfilOuServicoPerfil').show();
		$('div_incluir_novas_restricoes').hide();
		$('div_perfil_perfilPlanoOuPerfilServico').hide();
		if(option == 'planoPerfil'){
			$('div_nmComercial_planoPerfilRadio').show();
			$('div_nmComercial_perfilPlanoCheckbox').hide();
			$('div_nmComercial_servicoPerfilRadio').hide();
			$('div_nmComercial_perfilServicoCheckbox').hide();
		}
		if(option == 'servicoPerfil'){
			$('div_nmComercial_planoPerfilRadio').hide();
			$('div_nmComercial_perfilPlanoCheckbox').hide();
			$('div_nmComercial_servicoPerfilRadio').show();
			$('div_nmComercial_perfilServicoCheckbox').hide();
		}
	}
	else if(option == 'perfilPlano' || option == 'perfilServico'){
		setRequired('hidden_lista_perfil_servico_perfil');
		setOptional('hidden_lista_perfil_plano_perfil');
		$('div_perfil_perfilPlanoOuPerfilServico').show();
		$('div_perfil_planoPerfilOuServicoPerfil').hide();
		$('div_incluir_novas_restricoes').hide();
		if(option == 'perfilPlano'){
			$('div_nmComercial_planoPerfilRadio').hide();
			$('div_nmComercial_perfilPlanoCheckbox').show();
			$('div_nmComercial_servicoPerfilRadio').hide();
			$('div_nmComercial_perfilServicoCheckbox').hide();
		}
		if(option == 'perfilServico'){
			$('div_nmComercial_planoPerfilRadio').hide();
			$('div_nmComercial_perfilPlanoCheckbox').hide();
			$('div_nmComercial_servicoPerfilRadio').hide();
			$('div_nmComercial_perfilServicoCheckbox').show();
		}
	}
}

function limparTextfieldNmComercial(campoTextfield){
	campoTextfield.each(function(textfield){
		$(textfield).value = '';
	});
}

function clearAndDisableTextBox(idTextbox){
	if(idTextbox){
		$(idTextbox).value = '';
		$(idTextbox).readOnly = true;
	}
}

function habilitarTextBoxNmComercial(idCampo, n){
	for(i = 0; i < n; i++){
		$(idCampo+i).readOnly = false;
		$(idCampo+i).value = '';
	}
}

function desabilitarPopup(valueText, idPopup, campoHidden){
	if(valueText.length > 0){
		$(idPopup).disabled = true;
		campoHidden.each(function(hidden){
			$(hidden).value = '';
		});
	}else{
		$(idPopup).disabled = false;
		campoHidden.each(function(hidden){
			$(hidden).value = '';
		});
	}
}

/**
* Esta Function pega o estado do checkbox de restrições de acesso a planos e serviços,
* E seta um determinado valor no campo hidden de cada checkbox. Se checado: setar no hidden o valar 'S' se não 'N'.
**/
function setValueArrayRestricoes(checkbox, idHidden){
	if(checkbox){
		$(idHidden).value = 'S';
	}else{
		$(idHidden).value = 'N';
	}
}

function checkAtivacaoAndDesativacao(checkConsulta, checkAtiva, idAtiva, checkDesativa, idDesativa){
	if(checkConsulta && !checkAtiva){
		$(idAtiva).click();
	}
	if(checkConsulta && !checkDesativa){
		$(idDesativa).click();
	}
}

function setTipoRestricao(radio, div_plano, div_servico, hiddem_plano, hiddem_serv){
	if(radio.value == 'plano'){
		$(div_plano).show();
		$(div_servico).hide();
		setRequired(hiddem_plano);
		setOptional(hiddem_serv);
	}
	if(radio.value == 'servico'){
		$(div_servico).show();
		$(div_plano).hide();
		setRequired(hiddem_serv);
		setOptional(hiddem_plano);
	}
}

function setChecked(radio) {
	if(radio.value == 'plano') {
		document.getElementById("radioServico").checked = false;
	}
	if(radio.value == 'servico') {
		document.getElementById("radioPlano").checked = false;
	}
	
}

function definirTipoPesquisaVariavelModelo(radio){
	if(radio == 'variavel'){
		$('div_form_pesquisar_variavel').show();
		$('div_form_pesquisar_modelo').hide();
		setOptional('select_tipo_produto');
		setOptional('select_fabricante');
		setRequired('hidden_lista_tipo_cliente');
		setRequired('hidden_lista_carteira');
		setRequired('hidden_lista_segmento');
		setRequired('hidden_lista_canal');
		setRequired('hidden_lista_uf');
	}
	if(radio == 'modelo'){
		$('div_form_pesquisar_modelo').show();
		$('div_form_pesquisar_variavel').hide();
		setOptional('select_fabricante');
		setOptional('hidden_lista_tipo_cliente');
		setOptional('hidden_lista_carteira');
		setOptional('hidden_lista_segmento');
		setOptional('hidden_lista_canal');
		setOptional('hidden_lista_uf');
		setRequired('select_tipo_produto');
	}
}

function changeAcesso(op){
	$('hidden_perfilSCACheck').value = '';
	$('hidden_perfilSCARadio').value = '';
	$('select_plataforma').value = '';
	$('nmTecnico').value = '';
	$('hidden_nmComercial_planoRadio').value = '';
	$('hidden_nmComercial_servicoRadio').value = '';
	$('hidden_nmComercial_planoCheck').value = '';
	$('hidden_nmComercial_servicoCheck').value = '';

	if(op == 1){
		$('perfil_scaCheck').show();
		$('perfil_scaRadio').hide();
		$('hidden_perfilSCACheck').removeClassName('required');
		$('hidden_perfilSCARadio').removeClassName('required');
		$('hidden_perfilSCACheck').addClassName('required');
		$('nm_comercialPlanoRadio').show();
		$('nm_comercialServicoRadio').hide();
		$('nm_comercialPlanoCheck').hide();
		$('nm_comercialServicoCheck').hide();
	}
	if(op == 2){
		$('perfil_scaCheck').show();
		$('perfil_scaRadio').hide();
		$('hidden_perfilSCACheck').removeClassName('required');
		$('hidden_perfilSCARadio').removeClassName('required');
		$('hidden_perfilSCACheck').addClassName('required');
		$('nm_comercialPlanoRadio').hide();
		$('nm_comercialServicoRadio').show();
		$('nm_comercialPlanoCheck').hide();
		$('nm_comercialServicoCheck').hide();
	}
	if(op == 3){
		$('perfil_scaCheck').hide();
		$('perfil_scaRadio').show();
		$('hidden_perfilSCARadio').removeClassName('required');
		$('hidden_perfilSCACheck').removeClassName('required');
		$('hidden_perfilSCARadio').addClassName('required');
		$('nm_comercialPlanoRadio').hide();
		$('nm_comercialServicoRadio').hide();
		$('nm_comercialPlanoCheck').show();
		$('nm_comercialServicoCheck').hide();
	}
	if(op == 4){
		$('perfil_scaCheck').hide();
		$('perfil_scaRadio').show();
		$('hidden_perfilSCARadio').removeClassName('required');
		$('hidden_perfilSCACheck').removeClassName('required');
		$('hidden_perfilSCARadio').addClassName('required');
		$('nm_comercialPlanoRadio').hide();
		$('nm_comercialServicoRadio').hide();
		$('nm_comercialPlanoCheck').hide();
		$('nm_comercialServicoCheck').show();
	}
}

/**
* Function para checar e deschecar as restrições de acesso na tela Inclusão de Restriçoes no menu Parametrização acesso Plano.
**/
function setCheckedAllRestricoes(checkbox, desativacao, ativacao){
	desativacao.click();
	ativacao.click();
}


function addParameterInAnchor(id, p_value){
	id.href += '?vlParameter=' +p_value;
}

function buscarListaOfertaSap(url, id_matriz_0ferta){
	posicionarDivErros('right_section');
	if(id_matriz_0ferta == ""){
		$('select_ofertasap').options.length = 1;
		return;
	}
	showSpinner();
	var params = new Hash();
	params.set('idMatrizOferta', id_matriz_0ferta);
	new Ajax.Request(url, {evalJSON: 'true',  method: 'get',parameters: params, onFailure: doFailure, onComplete: retornoBuscarListaOfertaSap })
}

function retornoBuscarListaOfertaSap(req){
	if(req.responseText == null || req.responseText.replace("/^\s+|\s+$/g","") == ""){
		var select_ofertasap = $('select_ofertasap')
  		select_ofertasap.options.length = 1;
  		hideSpinner();
	}
	
   jsonRaw = req.responseText;
   jsonContent = eval("(" + jsonRaw + ")");

   var select_ofertasap = $('select_ofertasap')
   select_ofertasap.options.length = 1;
   for (i = 0; i < jsonContent.arrayOfertas.length; i++) {
   		select_ofertasap.options[i+1] = new Option(jsonContent.arrayOfertas[i].nome, jsonContent.arrayOfertas[i].id);
   }
   hideSpinner();
}

function habilitaDesabilitaPreco(radio) {
   if(radio.id == "id_parcelado") {
   	  $('id_valor_item').addClassName('required');
      $('id_valor_item').removeClassName('readonly');
      $('id_valor_item').enable();
      $('id_valor_item_vista').addClassName('readonly');
      $('id_valor_item_vista').removeClassName('required');
      $('id_valor_item_vista').disable();
   } else if(radio.id == "id_vista") {
      $('id_valor_item_vista').addClassName('required');
      $('id_valor_item_vista').removeClassName('readonly');
      $('id_valor_item_vista').enable();
      $('id_valor_item').addClassName('readonly');
      $('id_valor_item').removeClassName('required');
      $('id_valor_item').disable();
   }
}

function addFullValueSelect(selectOne, selectTwo){
	var len = selectOne.options.length;
	for(i = 0; i < len; i++){
		var option = document.createElement("option");
		option.value = selectOne.options[i].value;
		option.innerHTML = selectOne.options[i].text;
		selectTwo.appendChild(option);
		option.selected = true;
	}
	for(i = 0; i <= len; i++){
		selectOne.remove(option[i]);
	}
}

function removeFullValueSelect(selectOne, selectTwo){
	var len = selectTwo.options.length;
	for(i = 0; i < len; i++){
		var option = document.createElement("option");
		option.value = selectTwo.options[i].value;
		option.innerHTML = selectTwo.options[i].text;
		selectOne.appendChild(option);
	}
	for(i = len - 1; i >= 0; i--){
		selectTwo.remove(option[i]);
	}
}

function addOneValueSelect(selectOne, selectTwo){
	var len = selectOne.options.length;
	var index = selectOne.selectedIndex;
	for(i = 0; i < len; i++){
		if(selectOne.options[i] != undefined && selectOne.options[i].selected){
			var option = document.createElement("option");
			option.value = selectOne.options[i].value;
			option.innerHTML = selectOne.options[i].text;
			selectTwo.appendChild(option);
			option.selected = true;
		}
	}
	if(index != -1){
		for(i = len - 1; i >= 0; i--){
			 if(selectOne.options[i].selected){
				 selectOne.removeChild(selectOne.options[i]); 
			 }
		 }
	}
}

function removeOneValueSelect(selectOne, selectTwo){
	var len = selectTwo.options.length;
	var index = selectTwo.selectedIndex;
	for(i = 0; i < len; i++){
		if(selectTwo.options[i] != undefined && selectTwo.options[i].selected){
			var option = document.createElement("option");
			option.value = selectTwo.options[i].value;
			option.innerHTML = selectTwo.options[i].text;
			selectOne.appendChild(option);
		}
	}
	if(index != -1){
		for(i = len - 1; i >= 0; i--){
			 if(selectTwo.options[i].selected){
				 selectTwo.removeChild(selectTwo.options[i]); 
			 }
		 }
	}
}

function setSelectTipoProduto(url, idTipoLinha, selectTipoProduto){
	posicionarDivErros('right_section');
	if(idTipoLinha == ""){
		$(selectTipoProduto.id).options.length = 1;
		return;
	}
	showSpinner();
	var params = new Hash();
	params.set('idTipoLinha', idTipoLinha);
	new Ajax.Request(url, {evalJSON: 'true', method: 'get', parameters: params, OnFailure: doFailure, onComplete: responseBuscarListaTipoProduto})
}

function responseBuscarListaTipoProduto(ajaxResponse){
	var selectTipoProduto = $('select_tipo_produto');
	if(ajaxResponse.responseText == null || ajaxResponse.responseText.replace("/^\s+|\s+$/g","") == ""){
		selectTipoProduto.options.length = 1;
		hideSpinner();
	}else{
		jsonRaw = ajaxResponse.responseText;
		jsonContent = eval("(" + jsonRaw + ")");
		selectTipoProduto.options.length = 1;
		for(i = 0; i < jsonContent.arrayTpProduto.length; i++){
			selectTipoProduto.options[i+1] = new Option(jsonContent.arrayTpProduto[i].descricao, jsonContent.arrayTpProduto[i].id);
		}
		hideSpinner();
	}
	
}

function setSelectDestino(url, idSelectOrigem, selectDestino){
	posicionarDivErros('right_section');
	if(idSelectOrigem == ""){
		selectDestino.options.length = 1;
		return;
	}
	showSpinner();
	var params = new Hash();
	params.set('idSelectOrigem', idSelectOrigem);
	selectGlobalDestino = selectDestino;
	new Ajax.Request(url, {evalJSON: 'true', method: 'get', parameters: params, OnFailure: doFailure, onComplete: responseBuscarListaDestino})
}

function responseBuscarListaDestino(ajaxResponse){
	var selectDestino = selectGlobalDestino;
	selectGlobalDestino = null;
	if(ajaxResponse.responseText == null || ajaxResponse.responseText.replace("/^\s+|\s+$/g","") == ""){
		selectDestino.options.length = 1;
		hideSpinner();
	}else{
		jsonRaw = ajaxResponse.responseText;
		jsonContent = eval("(" + jsonRaw + ")");
		selectDestino.options.length = 1;
		for(i = 0; i < jsonContent.arrayDestino.length; i++){
			selectDestino.options[i+1] = new Option(jsonContent.arrayDestino[i].descricao, jsonContent.arrayDestino[i].id);
		}
		hideSpinner();
	}
}

function limpaSelectModeloAndOrgVendas(idSelectOrigem){
	if (idSelectOrigem == "") {
		$('select_modelo').options.length = 1;
		$('select_orgvendas').options[0].selected = true;
		return;
	}
}

function checkSelecaoTabela(array_ids_itens){
	if(array_ids_itens.compact().size() == 0){
		posicionarDivErros('resultado_pesquisa');
		clearErrors();
		addError('Por favor, selecione ao menos um item.', true);
		abrirPopupErros();
		return true;
	}
	return false;
}

function selectOrNoSelectAllMultipleDispPP(bool_value) {
	var len_acao = $('acoes-selecionadas').options.length;
	var len_orgvendas = $('org-vendas-selecionadas').options.length;
	var len_cn_atend = $('canais-atend-selecionadas').options.length;
	
	for(i = 0; i < len_acao; i++){
		$('acoes-selecionadas').options[i].selected = bool_value;
	}
	
	for(i = 0; i < len_orgvendas; i++){
		$('org-vendas-selecionadas').options[i].selected = bool_value;
	}
	
	for(i = 0; i < len_cn_atend; i++){
		$('canais-atend-selecionadas').options[i].selected = bool_value;
	}
}

function pauseReCall(button, millis) {
 
	showSpinner();
	
	var date = new Date();
	var curDate = null;	
 
	do { 
		curDate = new Date();
	} while(curDate-date < millis);
	
	hideSpinner();
	
	button.click();
}

function pauseReCall2(nmObject, millis) {
 
	showSpinner();
	
	var date = new Date();
	var curDate = null;	
 
	do { 
		curDate = new Date();
	} while(curDate-date < millis);
	
	hideSpinner();
	
	$(nmObject).click();
}

function checkNoContainsSelect() {
	var len_acao = $('acoes-selecionadas').options.length;
	var len_orgvendas = $('org-vendas-selecionadas').options.length;
	var len_cn_atend = $('canais-atend-selecionadas').options.length;
	
	if (len_acao == 0 || len_orgvendas == 0 || len_cn_atend == 0) {
		return true;
	} else {
		return false;
	}
}

function submitConfigPP() {
	
	if ( checkNoContainsSelect() ) {
		var caminho = 'http://'+document.location.host+'/catalogo/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/popupConfirmacaoGravarInd.do';
		
		abrirPopup1(caminho, null, 'right_section');
	} else {
		var caminho = 'http://'+document.location.host+'/catalogo/br/com/vivo/catalogoPRS/pageflows/param/programaPontos/produtos/popupConfirmacaoGravar.do';
		
		abrirPopup1(caminho, null, 'right_section');
	}
}

function ajustesSizePopup1(top, width, left) {

	if (top != null) {
		$('popup1').style.top = top;
	}
	
	if (width != null) {
		$('popup1').style.width = width;
	}
	
	if (left != null) {
		$('popup1').style.left = left;
	}
	//$('popup1').style.width = '900px';
	//$('popup1').style.left = '50px';
}

function generateContentError(camposErro){
	document.getElementById('conteudo_divErros').innerHTML = camposErro;
	document.getElementById('divErros').style.display = "inline";
}

function maxLengthTextArea(textAreaField, limit) {
	if (textAreaField.value.length >= limit) {
		textAreaField.value = textAreaField.value.substring(0, limit-1);
	}
}

function generateContentErrorForm(camposErro){
	document.getElementById('contentErrorForm').innerHTML = camposErro;
	document.getElementById('divErrosMidle').style.display = "inline";
}

function desabilitaCores(selectCores) {

	var els = selectCores.form.elements;
	for(i = 0; i < els.length; i++) {
	    var fullid = new String(els[i].id);
	    if(fullid.substring(0, 10) == 'select_cor') {
	        els[i].disabled=true;
	        els[i].selectedIndex=0;
	    }
	}
	$('id_cor_field').value='';
	selectCores.disabled=false;
}

function desabilitaCoresSubmit(botaoOk) {
	var els = botaoOk.form.elements;
	for(i = 0; i < els.length; i++) {
	    var fullid = new String(els[i].id);
	    if(fullid.substring(0, 10) == 'select_cor') {
	        els[i].disabled=true;
	        els[i].selectedIndex=0;
	    }
	}
}

function verificaModeloSelecionado(botaoOk) {
	var els = botaoOk.form.elements;
	var valid = false;
	for(i = 0; i < els.length; i++) {
	    var fullid = new String(els[i].id);
	    if(fullid.substring(0, 23) == 'checkbox_grupo_produto_') {
	        if(els[i].checked==true) {
	        	valid=true;
	        }
	    }
	}
	if(valid) {
		return true;
	} else {
		posicionarDivErros('listaModelosCompativeis');
		clearErrors();
		addError('Por favor, selecione o modelo a ser associado.', true);
		abrirPopupErros();
		return false;
	}
}

function alteraDataInicioVigencia(dataElement) {

	var dataAtual = new Date();
	var dataString = $(dataElement).value;
	
	dataAtual.setHours(0);
	dataAtual.setMinutes(0);
	dataAtual.setSeconds(0);
	dataAtual.setMilliseconds(0);

	var dataInput = new Date( dataString.split("/")[2], dataString.split("/")[1] - 1, dataString.split("/")[0] );
	var caminho = 'http://'+document.location.host+'/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/itens/popupConfirmacaoGravar.do';
	
	if (dataAtual > dataInput ) {
		abrirPopup1(caminho, null, 'right_section');
		
		return false;
	}
	
	return true;

}

function catalogoSubmit(url, form){
	document.forms[form].action = url;
	document.forms[form].submit();
}
 
 /*Função Pai de Mascaras*/
function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}

/*Função que Executa os objetos*/
function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}

/*Função que Determina as expressões regulares dos objetos*/
function leech(v){
    v=v.replace(/o/gi,"0")
    v=v.replace(/i/gi,"1")
    v=v.replace(/z/gi,"2")
    v=v.replace(/e/gi,"3")
    v=v.replace(/a/gi,"4")
    v=v.replace(/s/gi,"5")
    v=v.replace(/t/gi,"7")
    return v
}

/*Função que permite apenas numeros*/
function integer(v){
    return v.replace(/\D/g,"")
}

/*Função que padroniza telefone (11) 4184-1241*/
function telefone(v){
    v=v.replace(/\D/g,"")                 
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2") 
    v=v.replace(/(\d{4})(\d)/,"$1-$2")    
    return v
}

/*Função que padroniza telefone (11) 41841241*/
function telefoneCall(v){
    v=v.replace(/\D/g,"")                 
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2")    
    return v
}

/*Função que padroniza CPF*/
function cpf(v){
    v=v.replace(/\D/g,"")                    
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       
                                             
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2") 
    return v
}

/*Função que padroniza CEP*/
function cep(v){
    v=v.replace(/D/g,"")                
    v=v.replace(/^(\d{5})(\d)/,"$1-$2") 
    return v
}

/*Função que padroniza CNPJ*/
function cnpj(v){
    v=v.replace(/\D/g,"")                   
    v=v.replace(/^(\d{2})(\d)/,"$1.$2")     
    v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3") 
    v=v.replace(/\.(\d{3})(\d)/,".$1/$2")           
    v=v.replace(/(\d{4})(\d)/,"$1-$2")              
    return v
}

/*Função que permite apenas numeros Romanos*/
function romanos(v){
    v=v.toUpperCase()             
    v=v.replace(/[^IVXLCDM]/g,"") 
    
    while(v.replace(/^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/,"")!="")
        v=v.replace(/.$/,"")
    return v
}

/*Função que padroniza o Site*/
function site(v){
    v=v.replace(/^http:\/\/?/,"")
    dominio=v
    caminho=""
    if(v.indexOf("/")>-1)
        dominio=v.split("/")[0]
        caminho=v.replace(/[^\/]*/,"")
        dominio=dominio.replace(/[^\w\.\+-:@]/g,"")
        caminho=caminho.replace(/[^\w\d\+-@:\?&=%\(\)\.]/g,"")
        caminho=caminho.replace(/([\?&])=/,"$1")
    if(caminho!="")dominio=dominio.replace(/\.+$/,"")
        v="http://"+dominio+caminho
    return v
}

/*Função que padroniza DATA*/
function data(v){
    v=v.replace(/\D/g,"") 
    v=v.replace(/(\d{2})(\d)/,"$1/$2") 
    v=v.replace(/(\d{2})(\d)/,"$1/$2") 
    return v
}

/*Função que padroniza DATA*/
function hora(v){
    v=v.replace(/\D/g,"") 
    v=v.replace(/(\d{2})(\d)/,"$1:$2")  
    return v
}

/*Função que padroniza valor monétario*/
function valor(v){
    v=v.replace(/\D/g,"") //Remove tudo o que não é dígito
    v=v.replace(/^([0-9]{3}\.?){3}-[0-9]{2}$/,"$1.$2");
    v=v.replace(/(\d)(\d{2})$/,"$1,$2") //Coloca ponto antes dos 2 últimos digitos
    v=v.replace(/(\d{3})(\d)/g,"$1.$2")
    return v
}

/*Função que padroniza Area*/
function area(v){
    v=v.replace(/\D/g,"") 
    v=v.replace(/(\d)(\d{2})$/,"$1.$2") 
    return v
}

function setAllCheckBoxes(FormName, FieldName, CheckValue, checkAllName) {
				
	if (document.acaoForm(checkAllName).checked == false) {
		CheckValue = false;
	} else {
		CheckValue = true;
	}
	
	if(!document.forms[FormName])
		return;
	var objCheckBoxes = document.forms[FormName].elements[FieldName];
	if(!objCheckBoxes)
		return;
	var countCheckBoxes = objCheckBoxes.length;
	if(!countCheckBoxes)
		objCheckBoxes.checked = CheckValue;
	else
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++)
				objCheckBoxes[i].checked = CheckValue;
}

function verificarCheckBoxes(FormName, FieldName, CheckValue) {
	var isCheck = false;
	
	if(!document.forms[FormName])
		return;
	var objCheckBoxes = document.forms[FormName].elements[FieldName];
	if(!objCheckBoxes)
		return;
	var countCheckBoxes = objCheckBoxes.length;
	if(countCheckBoxes == null) {
		isCheck = (objCheckBoxes.checked == true);
	} else {
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++) {
			if (objCheckBoxes[i].checked == true){
				isCheck = true;
			}
		}
	}
	
	return isCheck;
}


function sendNoSpinner(button, div_destino, popup, id_bloco, doNotHideSpinner, id_bloco_required){
	if(hasEditavel()){
 		return false;
 	}
	var form = $(button).form;
	// Verifica se alguns itens obrigatório do form estão em branco
	
	if(checkRequired(form, id_bloco_required != null?id_bloco_required:id_bloco)){
		// Verifica se as datas submetidas estão no formato correto e existem 
		checkDatas(form)
		return false;
	}
	
	// Verifica se as datas submetidas estão no formato correto e existem 
	if(checkDatas(form)){
		return false;
	} 
	
	// Serializa os itens do form a serem submetidos 
	var params = $H(form.serialize(true));
	// Se o popup a ser fechado for informado, seta ele como parametro da chamada
	//	Precisamos fazer isso para ter essa informação na volta da chamada Ajax 
	if(popup){
		params.set('popup', popup);
	}
	
	// Setando este parametro como parametro da chamada para ter essa informação na volta da chamada Ajax 
	if(doNotHideSpinner!=null){
		params.set('doNotHideSpinner', true);
	}
	
	// Posiciona o div de erros acima do bloco informado 
	posicionarDivErros(id_bloco);
	
	// Se o div destino for informado, a chamada Ajax ira automaticamente coloca o resultado neste div 
	if(div_destino){
		// Precisa passar o div_destino em parametro para poder focar a tela neste div ao volta da chamada 
		params.set('div_destino', div_destino);
		new Ajax.Updater({success: div_destino}, form.action, {asynchronous:true, parameters:params, onSuccess: retornoSend, onComplete: doComplete,
			onFailure: doFailure});
	}else{
		new Ajax.Request(form.action, {asynchronous:true, parameters:params, onSuccess: retornoSend, onComplete: doComplete, onFailure: doFailure});
	}
	return true;
}
