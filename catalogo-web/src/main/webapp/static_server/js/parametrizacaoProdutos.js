var $jq = jQuery.noConflict();
var exibirModelo = true; 
var imagens = new Array();
var imgAtual = 0;
function populaFabricante(){
	var tipoProduto = new Object();
	
	if($jq('#tipoProduto').val() == 10){
		$jq('#filtroTecnologia').val('DEFAULT');
		$jq('#filtroTecnologia').hide('fast');
	}else{
		$jq('#filtroTecnologia').show('fast');
	}
	if($jq('#tipoProduto').val()!= 'DEFAULT'){
		
		tipoProduto.idTipoProduto=$jq('#tipoProduto').val();
		
		$jq.getJSON('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/getFabricante.do',tipoProduto).done(function(data){
			
			$jq('#fabricante').find('option[value!="DEFAULT"]').remove();
			$jq.each(ordena(data,'nome'),function(index,fabricante){
				
				$jq('#fabricante').append('<option value="'+fabricante.idFabricante+'" >'+fabricante.nome+'</option>');
			});
			$jq('#fabricante').css('width','1%');
			$jq('#fabricante').css('width','100%');
		});
	}
}
function populaModelo(){

	var modelo = new Object();
	var pesquisar = false;
	if($jq('#tipoProduto').val()!= 'DEFAULT'){
	
		modelo.idTipoProduto = $jq('#tipoProduto').val();
		if($jq('#fabricante').val()!= 'DEFAULT'){
	
			modelo.idFabricante=$jq('#fabricante').val();
			pesquisar = true;
			if($jq('#tecnologia').val()!= 'DEFAULT'){
			
				modelo.idTecnologia = $jq('#tecnologia').val();
			}
		}
	}
	if(pesquisar){

		$jq.getJSON('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/getModelo.do',modelo).done(function(data){

			$jq('#modelo').find('option[value!="DEFAULT"]').remove();	
			$jq.each(ordena(data,'nome'),function(index,modelo){

				$jq('#modelo').append('<option value="'+modelo.id+'" >'+modelo.nome+'</option>');
			});
			$jq('#modelo').css('width','1%');
			$jq('#modelo').css('width','100%');
		});
	}
}
function habilitaModelo(campo){

	if(campo.checked){

		$jq('#modelo').show('fast');
	}else{

		$jq('#modelo').hide('fast');
	}
}
function pesquisar(){

	validarCampos($jq("#pesquisa_simplificada"));
	if(staticMaps.camposValidado){

		$jq('#tResultado').html('');
		var thead = '<thead><tr><th><input type="checkbox" onclick="checkAll(this,$jq(\'#tResultado\'));"></th>';
		thead = thead + '<th>'+mensagens.Codigo+'</th>';
		thead = thead + '<th>'+mensagens.DescProduto+'</th>';
		thead = thead + '<th>'+mensagens.DescProdutoCatalogo+'</th>';
		thead = thead + '<th>'+mensagens.TipoProduto+'</th>';
		thead = thead + '<th>'+mensagens.Tecnologia+'</th>';
		thead = thead + '<th>'+mensagens.Fabricante+'</th>';
		thead = thead + '<th>'+mensagens.Modelo+'</th>';	
		thead = thead + '<th>'+mensagens.Cor+'</th>';
		thead = thead + '</tr></thead>';
		$jq('#tResultado').append(thead);
		var tbody = '<tbody>';
		tbody = tbody + '</tbody>';
		$jq('#tResultado').append(tbody);
		$jq('#resultado').show();
		$jq('#idBtRemoverModelo').hide();
		var filtros = new Object();
		filtros.idTipoProduto = $jq('#tipoProduto').val()!='DEFAULT'?$jq('#tipoProduto').val():0;
		filtros.idFabricante = $jq('#fabricante').val()!='DEFAULT'?$jq('#fabricante').val():0;
		filtros.idTecnologia = $jq('#tecnologia').val()!='DEFAULT'&&$jq('#tecnologia:visible').val()?$jq('#tecnologia').val():0;
		filtros.filtrarPorModelos = $jq("#modeloAtivo:checked").size() == 1?1:0;
		exibirModelo = filtros.filtrarPorModelos;
		filtros.idModelo = $jq("#modeloAtivo:checked").size() == 1 && $jq('#modelo').val()!='DEFAULT'?$jq('#modelo').val():0;
		filtros.codigoProduto = $jq('#codigoProduto').val();
		$jq('#tResultado').dataTable().fnSetColumnVis(7,true);
		$jq('#tResultado').dataTable().fnSetColumnVis(5,true);
		$jq('#tResultado').dataTable({
		    "sAjaxSource": "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/pesquisarProdutos.do",
		    "sDom": '<"top">rt<"bottom"flpi><"clear">',
			"bServerSide": true,
			"bDestroy": true,
			"bSort": false,
			"bLengthChange":true,
			"bFilter": false,
			"bProcessing": true,
			"bPaginate":true,
			"sPaginationType": "full_numbers",
		    "iDisplayLength":20,
		    "showInfo": false,
	    	"oLanguage": dataTableLanguage,
			"fnInfoCallback": function( oSettings, iStart, iEnd, iMax, iTotal, sPre ) {

				if(!iMax){

					$jq('#tResultado_paginate').remove();
					return '';
				}
				$jq('#tResultado_paginate')					
						.find('.paginate_button_disabled').each(function(){

					$jq(this).attr('class','');
	                $jq(this).attr('style','');
				});
				return mensagens.Mostrandode+"&nbsp;"+iStart+"&nbsp;"+mensagens.ate+"&nbsp;"+iEnd+"&nbsp;"+mensagens.de+"&nbsp;"+iMax+"&nbsp;"+mensagens.Registros+"&nbsp;";
			},
			"fnInitComplete": function(oSettings, json) {

				if(exibirModelo){
					
					$jq('#idBtRemoverModelo').show();
				}
				$jq('#tResultado').dataTable().fnSetColumnVis(7,exibirModelo);
				$jq('#tResultado').dataTable().fnSetColumnVis(5,$jq('#tipoProduto').val()!=10);
	      		habilitaTooltip();
		    },
	        "fnServerParams": function ( aoData ) {

				var a = '{';
				$jq.each(filtros,function(key,value){

					a = a + '"'+key+'": '+(parseInt(value)>-1 || value.substr(0,1)=='['?value:'"'+value+'"')+',';
				});
				a = a.substr(0,a.length-1)+'}';
				aoData.push( { "name":"form","value":a } );
	       	}, 
	       	"fnDrawCallback" : function(oSettings){

	   			var legenda = $jq('#tResultado_info');
				$jq('.paging_full_numbers').attr('class','');
				$jq(legenda).attr('class','').attr('style','float:left');
				$jq('#tResultado_paginate').prepend($jq(legenda));
				$jq('#tResultado_paginate')
						.find('.paginate_button:not(".paginate_button_disabled")')
						.each(function(){

	   				$jq(this).attr('style','color: #0066CF;cursor:pointer');
				});
	   		},
	   		"aoColumnDefs": 
	       	[
	       		{
					"sWidth": "4%", 
					"aTargets": [ 0, 8 ] 
				},{
					"sWidth": "8%", 
					"aTargets": [ 1, 4, 5, 6, 7 ] 
				},{
					"sWidth": "18%", 
					"aTargets": [ 2, 3 ] 
				},{
	           		"mRender": function ( data, type, row ) {
	                   return '<input type="checkbox" name="ckServico" value="'+data+'">';
	               	},
	               	"aTargets": [ 0 ]
	        	},{
		           	"mRender": function ( data, type, row ) {
	           	
	           			var style = 'color: #3D3D3D;text-align: left;text-decoration: underline;cursor: pointer;'
	           			if(data.length > 60){

							return '<div style="'+style+'" onclick="popularDetatalheProduto($jq(this).parents(\'tr\').attr(\'id\'));" title="'+data+'">'+data.substr(0,60)+'...</div>';;
	           			}else{

		           			return '<div style="'+style+'" onclick="popularDetatalheProduto($jq(this).parents(\'tr\').attr(\'id\'));">'+data+'</div>';
	           			}
	               },
	               "aTargets": [ 2, 3 ]
	           	},{
	           	"mRender": function ( data, type, row ) {

					if(data != ''){

						return '<input type="hidden" id="idGrupoProduto" value="'+data[0]+'">'+data[1];
					}else{
					
						return '';
					}
	               },
	               "aTargets": [ 7 ]
				},{
	           	"mRender": function ( data, type, row ) {

					if(data != ''){

						var style = 'background-color: #'+data[1]+'; border-radius: 10px; float: right; height: 20px; position: relative; width: 20px;';
						return '<div style="'+style+'" title="'+data[0]+'"></div>'				
					}else{

						return '';
					}
	               },
	               "aTargets": [ 8 ]
				}
			]
		});
	}
}
function popularDetatalheProduto(idProduto){

	var produto = new Object();
	produto.idProduto = idProduto;
	$jq.getJSON('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/consultarProduto.do',produto).done(function(data){

		limparBloco($jq('#descricaoProduto'),false);
		$jq('#descTipoProduto').find('option').remove();
		$jq.each(ordena(data.tipoProdutos,"nmTipoProduto"),function(index,tipoProduto){

    		$jq('#descTipoProduto').append('<option value="'+tipoProduto.idTipoProduto+'">'+tipoProduto.nmTipoProduto+'</option>');
		});
		$jq('#descIdProduto').val(data.idProduto);
		$jq('#descCodigoSAP').val(data.codigoProduto);
		$jq('#descNomeComercial').val(data.nomeComercial);
		$jq('#descDescricaoProduto').val(data.descProduto);
		$jq('#descComposicaoProduto').val(data.descProduto);
		$jq('#descTipoProduto').val(data.idTipoProduto==0?"DEFAULT":data.idTipoProduto);
		$jq('#descDestaqueProduto').val(data.destaqueProduto);
		$jq('#descCorProduto').val(data.nomeCor);
		$jq('#descricaoProduto').dialog({
			create: function(event,ui) {
				var title = $jq('.ui-dialog-titlebar-close').parent();
				$jq('[role="button"]').remove();
				$jq(title).append('<div class="popup_padrao_box_top_right_close" onclick="if(confirm(mensagens.Asalteracoesseraoperdidas+\'?\')) $jq(\'#descricaoProduto\').dialog(\'close\');"> </div>');
				$jq('#ui-id-1').append(mensagens.DescricaoProdutoCatalogo);
				$jq('#ui-id-1').removeClass('ui-widget-header').addClass('titlePopup');
	      	},
	      	buttons: {
	      		"Salvar": function (){

		      		$jq('#descricaoProduto').dialog('close');
	      		}
	      	}
		});
		$jq('#descricaoProduto').dialog('close');
		$jq('#descricaoProduto').dialog({
			create: function(event,ui) {
				var title = $jq('.ui-dialog-titlebar-close').parent();
				$jq('[role="button"]').remove();
				$jq(title).append('<div class="popup_padrao_box_top_right_close" onclick="if(confirm(mensagens.Asalteracoesseraoperdidas+\'?\')) $jq(\'#descricaoProduto\').dialog(\'close\');"> </div>');
				$jq('#ui-id-1').append(mensagens.DescricaoProdutoCatalogo);
				$jq('#ui-id-1').removeClass('ui-widget-header').addClass('titlePopup');
	      	},
	      	buttons: {
	      		"Salvar": function (){

					salvarProduto();
		      		$jq('#descricaoProduto').dialog('close');
	      		}
	      	},
	      	modal: true,
		    height: 550, 
		    resizable: false,
		    width: 500
		});
	});	
}
function abrirVincularModelo(){

	if($jq('input[type="checkbox"]:checked',$jq('#tResultado')).size()==0){

		alert(mensagens.Erro07);	
		return;
	}
	var modelo = new Object();
	$jq('#tabelaModelosResultados').html('');
	var thead = '<thead><tr>';
	thead = thead + '<th>'+mensagens.Modelo+'</th>';
	thead = thead + '<th>'+mensagens.Fabricante+'</th>';
	thead = thead + '<th>'+mensagens.Cor+'</th>';
	thead = thead + '<th>'+mensagens.Detalhes+'</th>';
	thead = thead + '</tr></thead>';
	$jq('#tabelaModelosResultados').append(thead);
	var tbody = '<tbody>';
	tbody = tbody + '</tbody>';
	$jq('#tabelaModelosResultados').append(tbody);
	modelo.idTipoProduto = $jq('#tipoProduto').val();
	modelo.idFabricante = $jq('#fabricante').val();
	if($jq('#tecnologia:visible').size()>0){
	
		modelo.idTecnologia = $jq('#tecnologia').val()!='DEFAULT'?$jq('#tecnologia').val():0;
	}else{
	
		modelo.idTecnologia = 0;
	}
	
	$jq('#tabelaModelosResultados').attr('style','width:470px');	
	$jq('#tabelaModelosResultados').dataTable({
	    "sAjaxSource": "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/recuperarModelos.do",
	    "sDom": '<"top">rt<"bottom"flpi><"clear">',
		"bServerSide": true,
		"bDestroy": true,
		"bSort": false,
		"bLengthChange":true,
		"bFilter": false,
		"bProcessing": true,
		"bPaginate":false,
	    "showInfo": false,
    	"oLanguage": dataTableLanguage,
    	"fnServerParams": function ( aoData ) {
			var a = '{';
			$jq.each(modelo,function(key,value){
			
				a = a + '"'+key+'": '+(parseInt(value)>-1 || value.substr(0,1)=='['?value:'"'+value+'"')+',';
			});
			a = a.substr(0,a.length-1)+'}';
			aoData.push( { "name":"form","value":a } );
		},
		"fnInitComplete": function(oSettings, json) {

      		habilitaTooltip();
		},
		"aoColumnDefs": 
       	[
       		{
				"mRender": function ( data, type, row ) {
					if($jq('#tipoProduto').val()!=10){
						return '<div style="width:100%;float:left"><div style="width:29%;float:left"><input type="radio" name="ckModelo" value="'+data+'"></div><div style="width:70%;float:right;padding-top:3px;">'+row[1]+'</div></div>';
					}else{
						return '<div style="width:100%;float:left"><div style="width:29%;float:left"><input type="checkbox" name="ckModelo" value="'+data+'"></div><div style="width:70%;float:right;padding-top:3px;">'+row[1]+'</div></div>';
					}
				},
				"aTargets": [ 0 ]
        	},{
				"mRender": function ( data, type, row ) {
					
					var select = '<select id="modeloCor"><option value="0"></option>';
					$jq.each(ordena(data,"nomeCor"), function(index, cor){
					
						select = select + '<option value="'+cor.idCor+'">'+cor.nomeCor+'</option>';
					}); 
					return select;
				},
				"aTargets": [ 2 ]
        	},{
				"mRender": function ( data, type, row ) {
					
					return '<img style="cursor:pointer;" onclick="abrirDetalheModelo('+data+');" src="/catalogo/static_server/img/botoes/bt-detalhes-ico.gif" title="'+mensagens.Detalhes+'">';
				},
				"aTargets": [ 3 ]
        	}
        ]
	});
	
	var popupParams = {
		create: function(event, ui) {
			createPopupTitle('associarModelo', mensagens.AssociarModelo, 'if(confirm(mensagens.Asalteracoesseraoperdidas+\'?\')) $jq(\'#associarModelo\').dialog(\'close\');');
      	},
      	buttons: {
      		"Salvar": function (){
				if (vincularModelos()) {
	      			$jq('#associarModelo').dialog('close');
	      		}
      		}
      	},
      	modal: true,
	    resizable: false,
	    width: 510
	};
	$jq('#associarModelo').dialog(popupParams);
	$jq('#associarModelo').dialog('close');
	$jq('#associarModelo').dialog(popupParams);
}
function salvarProduto(){

	var produto = new Object();
	produto.idProduto = $jq('#descIdProduto').val();
	produto.nomeComercial = $jq('#descNomeComercial').val();
	produto.idTipoProduto = $jq('#descTipoProduto').val()!='DEFAULT'?$jq('#descTipoProduto').val():0;
	produto.destaqueProduto = $jq('#descDestaqueProduto').val();
	$jq.getJSON('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/salvarProduto.do',produto).done(function(data){
		if(data.salvo){
      		pesquisar();
			alert($jq('#mensagemSucesso').text());
		} else {
			alert(data.mensagem);
		}
	});
}
function vincularModelos(){

	var modelos = "[";
	var idProdutos = "[";
	var vinculos = new Object();
	if( $jq('input[name="ckModelo"]:checked',$jq('#tabelaModelosResultados')).size() <= 0 ){

		alert(mensagens.Erro01);	
		return false;
	}
	$jq('input[name="ckModelo"]:checked',$jq('#tabelaModelosResultados')).each(function(){

		var idCor = $jq(this).parents('tr').find('#modeloCor').val();
		modelos = modelos + '{ "idGrupoProtudo" ='+$jq(this).val() +', "idCor" = '+idCor+'},';
		
	});
	$jq('input[type="checkbox"]:checked',$jq('#tResultado')).each(function(){

		idProdutos = idProdutos + $jq(this).val() +',';
	});
	vinculos.idProdutos = idProdutos.substr(0,idProdutos.length-1)+']';
	vinculos.grupoProdutos = modelos.substr(0,modelos.length-1)+']';
	vinculos.removerAntigos = $jq('#tipoProduto').val()!=10;
	$jq.getJSON('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/vincularModelos.do',vinculos).done(function(data){

		alert($jq('#mensagemSucessoModelo').text());
		pesquisar();
	});
	return true;
}
function removerVinculoModelo(){

	var idGrupoProduto = "[";
	var idProdutos = "[";
	var vinculos = new Object();
	if( $jq('input[type="checkbox"]:checked',$jq('#tResultado')).size() <= 0 ){
	
		alert(mensagens.Erro07);	
		return false;
	}
	if(confirm($jq('#mensagemConfirmModelo').text())){

		$jq('input[type="checkbox"]:checked',$jq('#tResultado')).each(function(){

			idProdutos = idProdutos + $jq(this).val() +',';
			idGrupoProduto = idGrupoProduto + $jq(this).parents('tr').find('#idGrupoProduto').val()+',';
		});
		vinculos.idProdutos = idProdutos.substr(0,idProdutos.length-1)+']';
		vinculos.idGrupoProduto = idGrupoProduto.substr(0,idGrupoProduto.length-1)+']';
		vinculos.removerAntigos = $jq('#tipoProduto').val()!=10;
		$jq.getJSON('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/removerVinculos.do',vinculos).done(function(data){

			alert($jq('#mensagemSucessoRemoverModelo').text());
			pesquisar();
		});
	}
}
function abrirDetalheModelo(idModelo){

	var obj = new Object();
	obj.idModelo = idModelo;
	$jq.getJSON('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/produtosNovo/consultarModelos.do',obj).done(function(data){

		imagens = new Array();
		$jq.each(data.multimidias,function(index,m){
			
			var img = '<img src="'+data.folderImage+'/'+m.nomeMultimidia+'" width="210" height="200">';
			imagens.push(img);
			
		});
		if(imgAtual > 0 && imagens.length>1){
			$jq('#btDetalheAnterior').attr('style','cursor:pointer');
		}
		if(imgAtual < imagens.length-1){
			$jq('#btDetalhePosterior').attr('style','cursor:pointer');
		}
		var div = '<div style="float:left">'+imagens[0]+'</div><div style="float:left;padding-left:5px;">';
		$jq.each(data['caracteristicas'],function(index,value){
		
			div = div + '<img id="btDetalhePosterior" src="/catalogo/static_server/img/botoes/bt-fotos-proxima.gif" style="padding-right:5px;">'+ value + '<br clear="all">';
		});
		div = div + '</div>';
		$jq('#imgModelo').html('');
		$jq('#imgModelo').append(div)
		$jq('#detalheModelo').dialog({

			create: function(event,ui) {
				createPopupTitle('detalheModelo', mensagens.AssociarModelo, null);
	      	},
	      	modal: true,
		    height: 520, 
		    resizable: false,
		    width: 500
		});
		$jq('#detalheModelo').dialog('close');
		$jq('#detalheModelo').dialog({

			create: function(event,ui) {
				createPopupTitle('detalheModelo', mensagens.AssociarModelo, null);
	      	},
	      	modal: true,
		    height: 520, 
		    resizable: false,
		    width: 500
		});
	});
}
function imagemDetalheAnterior(){

	if(imgAtual > 0 ){
	
		$jq('#btDetalheAnterior').attr('style','');
		$jq('#btDetalhePosterior').attr('style','');
		imgAtual--;
		$jq('#imgModelo').html('');
		$jq('#imgModelo').append(imagens[imgAtual]);
		if(imgAtual > 0 && imagens.length>1){
			$jq('#btDetalheAnterior').attr('style','cursor:pointer');
		}
		if(imgAtual < imagens.length-1){
			$jq('#btDetalhePosterior').attr('style','cursor:pointer');
		}
	}
}
function imagemDetalhePosterior(){

	if((imgAtual+1) < imagens.length){
	
		$jq('#btDetalheAnterior').attr('style','');
		$jq('#btDetalhePosterior').attr('style','');
		imgAtual++;
		$jq('#imgModelo').html('');
		$jq('#imgModelo').append(imagens[imgAtual]);
		if(imgAtual > 0 && imagens.length>1){
			$jq('#btDetalheAnterior').attr('style','cursor:pointer');
		}
		if(imgAtual < imagens.length-1){
			$jq('#btDetalhePosterior').attr('style','cursor:pointer');
		}
	}
}
function limparBloco(contexto,esconder){

	$jq('input[type!="button"][type!="submit"][type!="radio"], select',contexto ).val('');
	if(esconder){
		$jq(contexto).hide('fast');
	}
}
function limparTabelaPesquisa(){

	$jq('#resultadoTabelaPesquisa').children().remove();
	$jq('#resultadoTabelaPesquisa').append('<table id="tResultado" class="tabela-padrao table_body"></table>');
	$jq('#resultado').hide('fast');
}
function limparFormulario(){
	$jq("#tipoProduto").attr('value','DEFAULT');
	$jq("#fabricante option[value!='DEFAULT']").remove();
	$jq("#tecnologia option[value!='DEFAULT']").remove();
	$jq("#modeloAtivo")[0].checked = false;
	$jq("#modelo").hide();
	$jq("#codigoProduto")[0].value;
}
function limparTela(){
	limparFormulario();
	limparBloco($jq('#formulario'),false);
	limparTabelaPesquisa();
}
function ordena(json,attr){
	


    var tamanho = json.length;
    for(var s=1;s<tamanho;s++){

        var segundo = json[s];
        for(var p=0;p<s;p++){
        
        	var primeiro = json[p];
        	if(primeiro[attr].toLowerCase() > segundo[attr].toLowerCase()){
				json[p]=segundo;
				json[s]=primeiro;
				s=1;
				break;
			}
        }
    }
    return json;
}
function habilitaTooltip(){

	$jq('[title]').qtip({content:{text:false},
		style: {
	        classes: 'qtip-blue qtip-shadow'
	    }
    });
}
function checkAll(campo,context){

	$jq('input[type="checkbox"]',context).prop('checked',campo.checked);
}

function createPopupTitle(id, title, fechar) {
	var dialog = $jq('#' + id).dialog('widget');
	dialog.attr('id', id + 'Dialog');
	var popup = $jq('#' + id + 'Dialog');
	var titleBar = $jq('.ui-dialog-titlebar-close', popup).parent();
	$jq('[role="button"]', popup).remove();
	if (!fechar) {
		fechar = '$jq(\'#' + id + '\').dialog(\'close\');';
	}
	$jq(titleBar).append('<div style="float: right; width: 60px;"><div class="popup_padrao_box_top_right_close" style="float: right;" onclick="' + fechar + '"></div></div>');
	var titleMessage = dialog.attr('aria-labelledby');
	$jq('#' + titleMessage, popup).append(title);
	$jq('#' + titleMessage, popup).removeClass('ui-widget-header').addClass('titlePopup');
}