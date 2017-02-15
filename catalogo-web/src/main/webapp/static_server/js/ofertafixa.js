var $jq = jQuery.noConflict();
/* Chamar as Abas quando carregar o documento */
$jq(document).ready(function() {
    $jq("#dataVigenteInicio,#dataVigenteFim").datepicker();
    /* Habilitar e Desablitiar os checkboxs */
    $jq('#todos').live("click",function() {
        /* Descobre quais os checkboxs pertencentes ao "pai" */
        var check= $jq(this).next().next().children().children();
        /* Se o #todos for igual a checado */
        if ($jq(this).attr("checked")){
            $jq(check).each(function(){$jq(this).attr("checked", true);});
        } else {
            $jq(check).each(function(){$jq(this).attr("checked", false);});
        }
    });
    $jq('.marcar').live("click",function() {
        var parentCheck = $jq(this).parent().parent().parent().children()[1];
        if ($jq(this).attr("checked")){
            var check= $jq(parentCheck).next().next().children().children();
            var i = 0;
            $jq(check).each(function(){
                if ($jq(this).attr("checked")) {
                    i++;
                }
            });
            if (i == $jq(check).length){
                $jq(parentCheck).attr("checked",true);                
            }
        } else {
            $jq(parentCheck).attr("checked",false);
        }
    });
});

function selectAba(obj) {
    $jq('span#abas a').removeClass('selected');
    // Acrescenta uma classe CSS marcando visualmente a aba como selecionada 
    $jq(obj).addClass('selected');
    // Oculta todas as abas abertas
    $jq('ul#conteudos li').hide();
    // Exibe a aba que foi clicada 
    $jq('ul#conteudos ' + $jq(obj).attr('href')).show();
    return false;
} 

function carregarDependentes(obj) {
    if (obj.id == 'idServicoLinha') {
        carregarParaServicoLinha(obj);
    } else if (obj.id == 'idServicoPlano') {
        carregarParaServicoPlano(obj);
    } else if (obj.id == 'idServicoLinhaNew') {
    
    	// evento onchange do combo de Servi�o Linha
        carregarParaServicoLinhaNew(obj);
        
    } else if (obj.id == 'idServicoPlanoNew') {
    
    	// evento onchange do combo de Servi�o Plano
        carregarParaServicoPlanoNew(obj);
        
    } else if (obj.id == 'idTipoServico') {
        carregarParaTipoServico(obj);
    } else if (obj.id == 'idUf'){
        carregarParaIdUf(obj);
    } else if (obj.id == 'idAreaRegistro'){
        carregarParaIdAreaRegistro(obj);
    } else if (obj.id == 'idServicoOfertaComplementar') {
    	carregarParaNovoPsServico(obj);
    } else if (obj.id == 'psServicoNew') {
    	carregarComboDesdeInput(obj);    	
    } else if (obj.id == 'psServivoLinhaNew') {
    
    	// evento onclick do button-input PS de Linha
    	carregarComboServLinhaDesdeInput(obj); 
    } else if (obj.id == 'psServicoPlanoNew') {
    
    	// evento onclick do button-input PS de Plano
    	carregarComboServPlanoDesdeInput(obj);
    	
    } else if (obj.id == 'opSolicitacaoComercialNormalNew' ) {

    	// evento onclick do button-input Op. Comercial Linha    	
    	carregarComboSolComerNormDesdeInput(obj);
    	
    } else if (obj.id == 'opSolicitacaoComercialPreCadastroNew') {
        	
    	// evento onclick do button-input Op. Comercial Pr�-Cadastro
    	carregarComboSolComerPreCadastroDesdeInput(obj);
    
    
    } else if (obj.id == 'opSolicitacaoComercialPlanoNew') {
		
		// evento onclick do button-input Op. Comercial Plano
		carregarComboOpComerPlanoDesdeInput(obj);

    
    } else if (obj.id == 'idSolicitacaoComercialNormalNew') {
    
    	// evento onchange do combo de Sol. Comercial Normal
    	carregarInputSolComerNorm(obj);
    	
    } else if (obj.id == 'idSolicitacaoComercialPreCadastroNew') {
     	
     	// evento onchange do combo de Sol. Comercial Pre-Cadastro		
    	carregarInputOpComerPreCadastro(obj);
    	
    } else if (obj.id == 'idSolicitacaoComercialPlanoNew') {
    
    	// evento onchange do combo de Sol. Comercial Plano
		carregarInputSolComerPlano(obj);
		
    } 
}


function carregarComboSolComerPreCadastroDesdeInput(opSolicitacaoComercialPreCadastroNew) {
	carregarComboSolComerPreCadastro(opSolicitacaoComercialPreCadastroNew.value);
}


function carregarComboSolComerPreCadastro(id) {
	//evento onclick do button-input Op. Comercial Pr�-Cadastro
	cleanMessages();
	//objeto do combo
	var solComPreCadas = document.getElementById('idSolicitacaoComercialPreCadastroNew');
	var encontrado = false;
	
	for (var i = 0; i < solComPreCadas.options.length; i++) {
		var valor = solComPreCadas.options[i].value;
		valor = valor.split('&')[1];
	
		// objeto do input
		var inPut = document.getElementById('opSolicitacaoComercialPreCadastroNew').value;
		if (valor == inPut) {
			solComPreCadas.selectedIndex = i;
			encontrado = true;
			break;
		}
	}
			
	if (!encontrado && id != null) {
		showError("C&oacute;digo incompat&iacute;vel com o Servi&ccedil;o Linha");
	} 
}


function carregarComboSolComerNormDesdeInput(opSolicitacaoComercialNormalNew) {	
	carregarComboSolComerNormal(opSolicitacaoComercialNormalNew.value);
}

function carregarComboSolComerNormal(id) {
 	//evento onclick do button-input Op. Comercial Linha
 	cleanMessages();
 	// objeto do combo
 	var solComerLinha = document.getElementById('idSolicitacaoComercialNormalNew');
	var encontrado = false;
		
	for (var i = 0; i < solComerLinha.options.length; i++) {
		var valor = solComerLinha.options[i].value;
		valor = valor.split('&')[1];
		
		// objeto do input
		var inPut = document.getElementById('opSolicitacaoComercialNormalNew').value;
		if (valor == inPut) {
			solComerLinha.selectedIndex = i;
			encontrado = true;
			break;		
		}	
	}
	
	if (!encontrado && id != null) {
		showError("C&oacute;digo incompat&iacute;vel com o Servi&ccedil;o Linha");
	} 	
}

function carregarComboOpComerPlanoDesdeInput(opSolicitacaoComercialPlanoNew) {	
	carregarComboOpComerPlano(opSolicitacaoComercialPlanoNew.value);
}


function carregarComboOpComerPlano(id) {
	//evento onclick do button-input Op. Comercial Plano
	cleanMessages();
	// objeto do combo
	var solComerPlano = document.getElementById('idSolicitacaoComercialPlanoNew');
	var encontrado = false;

	for (var i = 0; i < solComerPlano.options.length; i++) {
		var valor = solComerPlano.options[i].value;
		valor = valor.split('&')[1];
		
		// objeto do input
		var inPut = document.getElementById('opSolicitacaoComercialPlanoNew').value;
		if (valor == inPut) {
			solComerPlano.selectedIndex = i;
			encontrado = true;
			break;
		}
	}
	
	if (!encontrado && id != null) {
		showError("C&oacute;digo incompat&iacute;vel com o Servi&ccedil;o Linha");
	} 	
}




function carregarInputOpComerPreCadastro(obj) {
	
	var objCombo = document.getElementById('idSolicitacaoComercialPreCadastroNew');
	
	for (var i = 0; i < objCombo.options.length; i++) { 
	
		if (objCombo.selectedIndex != 0) {
			var cdoperacaocomercial = obj.value;
			cdoperacaocomercial = cdoperacaocomercial.split('&')[1];		
			document.getElementById('opSolicitacaoComercialPreCadastroNew').value = cdoperacaocomercial;
		} else {
			document.getElementById('opSolicitacaoComercialPreCadastroNew').value = '';
			break;	
		}	
	}
}

function carregarInputSolComerPlano(obj) {

	var objCombo = document.getElementById('idSolicitacaoComercialPlanoNew');

	for (var i = 0; i < objCombo.options.length; i++) { 
	
		if (objCombo.selectedIndex != 0) {
			var cdoperacaocomercial = obj.value;
			cdoperacaocomercial = cdoperacaocomercial.split('&')[1];		
			document.getElementById('opSolicitacaoComercialPlanoNew').value = cdoperacaocomercial;
		} else { 
			document.getElementById('opSolicitacaoComercialPlanoNew').value = '';
			break;
		}			
	}
}


function carregarInputSolComerNorm(opSolicitacaoComercialNormalNew) {		

	var objCombo = document.getElementById('idSolicitacaoComercialNormalNew');

	for (var i = 0; i < objCombo.options.length; i ++) {
		
		if (objCombo.selectedIndex != 0) {
			var valor = opSolicitacaoComercialNormalNew.value;
			valor = valor.split('&')[1];
			document.getElementById('opSolicitacaoComercialNormalNew').disabled = false;
			document.getElementById('opSolicitacaoComercialNormalNew').value = valor;   
		
		} else {
			document.getElementById('opSolicitacaoComercialNormalNew').value = '';		
        	break;
		}
	}
}


function carregarComboServPlanoDesdeInput(psServicoPlanoNew) {
	// evento onclick do button-input PS de Plano
	carregarComboServicoPlano(psServicoPlanoNew.value);
}


function carregarComboServicoPlano(id) {
	// evento onclick do button-input PS de Plano
	cleanMessages();
	// objeto do combo
	var servPlano = document.getElementById('idServicoPlanoNew');
	var encontrado = false;	
	
	for (var i = 0; i < servPlano.length; i++) {
		var valor = servPlano.options[i].value;
		valor = valor.split('&')[1];
		
		// objeto do input
		var inPut = document.getElementById('psServicoPlanoNew').value
		if (valor == inPut) {
			servPlano.selectedIndex = i;
			encontrado = true;			
			break;
		}			
	}
	
	if (!encontrado && id != null) {
		showError("C&oacute;digo incompat&iacute;vel com o Servi&ccedil;o Linha");
	}		
}


function carregarComboDesdeInput(psServicoNew) {
    carregarComboOferComplementar(psServicoNew.value);
}

function carregarComboServLinhaDesdeInput(psServivoLinhaNew) {
	// evento onclick do button-input PS de Linha
    carregarComboServLinha(psServivoLinhaNew.value);
}

function carregarComboServLinha(id) {
	
	cleanMessages();
	var servLinha = document.getElementById('idServicoLinhaNew');
	var encontrado = false;
	
	for (var i = 0; i < servLinha.options.length; i++) {	
		var valor = servLinha.options[i].value;				
		valor = valor.split('&')[1];		
		var inPut = document.getElementById('psServivoLinhaNew').value;
		if (valor == inPut) {
			servLinha.selectedIndex = i;
			encontrado = true;
			break;
		}
	}
	
	if (!encontrado && id != null) {
		showError("C&oacute;digo incompat&iacute;vel com o Servi&ccedil;o Linha");
	}
}

function carregarComboOferComplementar(id) {
	/*disabled input */
	document.getElementById('psServicoNew').disabled = false;
	
	cleanMessages();      
    var dd = document.getElementById('idServicoOfertaComplementar');
	var encontrado = false;

    for (var i = 0; i < dd.options.length; i++) {
		var valor = dd.options[i].value;
		valor = valor.split('&')[1];
    	if (valor == document.getElementById('psServicoNew').value) {
        	dd.selectedIndex = i;
        	encontrado = true;
            break;
        }
    } 
    
    if(!encontrado && id != null) {
    	showError("C&oacute;digo Servi&ccedil;o Incompat&iacute;vel com o Tipo Servi&ccedil;o dispon&iacute;vel");
    }        
}

function carregarParaIdAreaRegistro(areaRegistro) {
    document.getElementById('idCidade').value = '';
    if(areaRegistro.value == '') {
        document.getElementById('idAreaRegistro').disabled = 'disabled';
    } else {
        document.getElementById('idCidade').options[0].text = 'CARREGANDO';
        carregarCombo(areaRegistro.value, 'selectIdCidade',"");    
    }
}

function carregarParaIdUf(uf) {
    document.getElementById('idAreaRegistro').value = '';
    document.getElementById('idCidade').value = '';
    document.getElementById('idCidade').disabled = 'disabled';
    if(uf.value == '') {
        document.getElementById('idAreaRegistro').disabled = 'disabled';
    } else {
        document.getElementById('idAreaRegistro').options[0].text = 'CARREGANDO';
        carregarCombo(uf.value, 'selectIdAreaRegistro',"");    
    }
}



function carregarParaTipoServico(tipoServico) {	
    document.getElementById('idServicoOfertaComplementar').value = '';
    document.getElementById('psServicoNew').value = '';

    if (tipoServico.value == '') {    	
        document.getElementById('idServicoOfertaComplementar').disabled = 'disabled';
        document.getElementById('psServicoNew').disabled = 'disabled';
    } else {    	
        document.getElementById('idServicoOfertaComplementar').options[0].text = 'CARREGANDO';
        carregarCombo(tipoServico.value, 'selectServicoOfertaComplementar',"");
        document.getElementById('psServicoNew').disabled = '';
    }
}

function carregarParaNovoPsServico(psServico) {	
	
	var dd = document.getElementById('idServicoOfertaComplementar');	
	for (var i = 0; i < dd.options.length; i++) {
		
		if (dd.selectedIndex != 0) { 
			document.getElementById('psServicoNew').value = '';		        
    		var ps = document.getElementById('idServicoOfertaComplementar').value;
    		ps = ps.split('&')[1];        
			document.getElementById('psServicoNew').disabled = false;
    		document.getElementById('psServicoNew').value = ps;    
		} else {
			document.getElementById('psServicoNew').value = '';
        	break;
		}
		
    } 
	       
}

function carregarParaServicoPlano(servicoPlano) {
    document.getElementById('idSolicitacaoComercialPlano').value = '';
    //document.getElementById('opSolicitacaoComercialPlano').value = '';
    if (servicoPlano.value == '') {
        document.getElementById('idSolicitacaoComercialPlano').disabled = 'disabled';
        //document.getElementById('opSolicitacaoComercialPlano').disabled = 'disabled';
    } else {
        document.getElementById('idSolicitacaoComercialPlano').options[0].text = 'CARREGANDO';
        carregarCombo(servicoPlano.value, 'selectSolicitacaoComercialPlano', "")
    }
}

function carregarParaServicoPlanoNew(servicoPlano) {
	
	document.getElementById('idSolicitacaoComercialPlanoNew').value = '';
    document.getElementById('opSolicitacaoComercialPlanoNew').value = '';

    if (servicoPlano.value == '') {
        document.getElementById('idSolicitacaoComercialPlanoNew').disabled = 'disabled';
        document.getElementById('opSolicitacaoComercialPlanoNew').disabled = 'disabled';
        document.getElementById('psServicoPlanoNew').value = '';
    } else {
    	document.getElementById('idSolicitacaoComercialPlanoNew').options[0].text = 'CARREGANDO';    	
    	document.getElementById('opSolicitacaoComercialPlanoNew').disabled = false;
    	
        carregarCombo(servicoPlano.value, 'selectSolicitacaoComercialPlanoNew', "New");
    	var objCombo = document.getElementById('idServicoPlanoNew');
    	for (var i = 0; i < objCombo.options.length; i++) { 
    		if (objCombo.selectedIndex != 0) {
        		var valor = servicoPlano.value;
				valor = valor.split('&')[1];
				document.getElementById('psServicoPlanoNew').disabled = false;
				document.getElementById('psServicoPlanoNew').value = valor;
			}
    	}
    }
}

function carregarParaServicoLinha(servicoLinhaInput) {
	document.getElementById('idSolicitacaoComercialPreCadastro').value = '';
	document.getElementById('idSolicitacaoComercialNormal').value = '';
	document.getElementById('idServicoPlano').value = '';   
	document.getElementById('idSolicitacaoComercialPlano').value = '';   
	document.getElementById('idSolicitacaoComercialPlano').disabled = 'disabled';     
	document.getElementById('idSolicitacaoComercialPreCadastro').disabled = 'disabled';       
	document.getElementById('idSolicitacaoComercialNormal').disabled = 'disabled';
	document.getElementById('idServicoPlano').disabled = 'disabled';
    if (servicoLinhaInput.value == '') {
        return;
    }
    document.getElementById('idSolicitacaoComercialPreCadastro').options[0].text = 'CARREGANDO';
    document.getElementById('idSolicitacaoComercialNormal').options[0].text = 'CARREGANDO';
    document.getElementById('idServicoPlano').options[0].text = 'CARREGANDO';
    carregarCombo(servicoLinhaInput.value,'selectSolicitacaoComercialNormal', "");
    carregarCombo(servicoLinhaInput.value,'selectSolicitacaoComercialPreCadastro', "");
    carregarCombo(servicoLinhaInput.value,'selectServicoPlano', "");
}

function carregarParaServicoLinhaNew(servicoLinhaInput) {
    document.getElementById('opSolicitacaoComercialPreCadastroNew').value = '';
    document.getElementById('idSolicitacaoComercialPreCadastroNew').value = '';
    document.getElementById('opSolicitacaoComercialNormalNew').value = '';
    document.getElementById('idSolicitacaoComercialNormalNew').value = '';
    document.getElementById('psServicoPlanoNew').value = '';
    document.getElementById('idServicoPlanoNew').value = '';
    document.getElementById('opSolicitacaoComercialPlanoNew').value = '';
    document.getElementById('idSolicitacaoComercialPlanoNew').value = '';
    document.getElementById('opSolicitacaoComercialPlanoNew').disabled = 'disabled';
    document.getElementById('idSolicitacaoComercialPlanoNew').disabled = 'disabled';
    document.getElementById('opSolicitacaoComercialPreCadastroNew').disabled = 'disabled';  
    document.getElementById('idSolicitacaoComercialPreCadastroNew').disabled = 'disabled';
    document.getElementById('opSolicitacaoComercialNormalNew').disabled = 'disabled';      
    document.getElementById('idSolicitacaoComercialNormalNew').disabled = 'disabled';
    document.getElementById('psServicoPlanoNew').disabled = 'disabled';
    document.getElementById('idServicoPlanoNew').disabled = 'disabled';
    carregarInputPsLinha(servicoLinhaInput);
    if (servicoLinhaInput.value == '') {
        return;
    }
    document.getElementById('idSolicitacaoComercialPreCadastroNew').options[0].text = 'CARREGANDO';
    document.getElementById('idSolicitacaoComercialNormalNew').options[0].text = 'CARREGANDO';
    document.getElementById('idServicoPlanoNew').options[0].text = 'CARREGANDO';
    carregarCombo(servicoLinhaInput.value,'selectSolicitacaoComercialNormalNew', "New");
    carregarCombo(servicoLinhaInput.value,'selectSolicitacaoComercialPreCadastroNew', "New");
    carregarCombo(servicoLinhaInput.value,'selectServicoPlanoNew', "New");
}

function carregarInputPsLinha(servicoLinhaInput) {
	// objeto do combo
	var objCombo = document.getElementById('idServicoLinhaNew');
	
	for (var i = 0; i < objCombo.options.length; i++) {
		if (objCombo.selectedIndex != 0) {			
			var cdPS = servicoLinhaInput.value;	
			cdPS = cdPS.split('&')[1];	
			document.getElementById('psServivoLinhaNew').value = cdPS;
			document.getElementById('opSolicitacaoComercialNormalNew').disabled = false;
			document.getElementById('psServicoPlanoNew').disabled = false;
			document.getElementById('opSolicitacaoComercialPreCadastroNew').disabled = false;
		} else {
			document.getElementById('psServivoLinhaNew').value = '';			
			break;		
		}	
	}
}


function carregarCombo(id, element, neo){
	if (neo != "New") {
    	document.getElementById('divErros').style.display = "none";
    	document.getElementById('conteudo_divErros').innerHTML = '';
    }

    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregar.do',
        data: {
            "id" : id,
            "element" : element,
            "neo" : neo
        },
        context: document.body,
        success: function(data){
			var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById(element).innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function pesquisar(){
    var caminho = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/search.do";
    $jq('.detalharOfertas').css('display','none');
    document.getElementById("ofertaSelecionada").innerHTML = "";
    carregarConsulta(caminho,"");
}

function carregarConsulta(caminho, reload){
    if (reload != "reload") {
        cleanMessages();
    }
																
    document.getElementById("pesquisaOfertas").innerHTML = carregandoDados();
	$jq("#pesquisaOfertas").css('display','block').offset().top;
	document.getElementById("pesquisaOfertas").scrollIntoView();
    var tipoOfertaChecked = obterTipoOfertaChecked('checkboxgroup_pesquisa');
    $jq.ajax({
        type: "GET",
        url: caminho,
        data: {
            "cdOferta" : document.getElementById('cdOferta').value,
            "nmOferta" : document.getElementById('nmOferta').value,
            "idServicoLinha" : document.getElementById('idServicoLinha').value,
            "idSolicitacaoComercialNormal" : document.getElementById('idSolicitacaoComercialNormal').value,
            "idSolicitacaoComercialPreCadastro" : document.getElementById('idSolicitacaoComercialPreCadastro').value,
            "idServicoPlano" : document.getElementById('idServicoPlano').value,
            "idSolicitacaoComercialPlano" : document.getElementById('idSolicitacaoComercialPlano').value,
            "status" : document.getElementById('status').value,
            "dataVigenteInicio" : document.getElementById('dataVigenteInicio').value,
            "dataVigenteFim" : document.getElementById('dataVigenteFim').value,
            "tipoOfertaChecked" : tipoOfertaChecked,
            "reload" : reload,
            "pendenteConfiguracao" : document.getElementById('pendenteConfiguracao').value,
            "psServivoLinha" : document.getElementById('psServivoLinha').value,
            "psServicoPlano" : document.getElementById('psServicoPlano').value,
            "opSolicitacaoComercialPreCadastro" : document.getElementById('opSolicitacaoComercialPreCadastro').value,
            "opSolicitacaoComercialNormal" : document.getElementById('opSolicitacaoComercialNormal').value,
            "opSolicitacaoComercialPlano" : document.getElementById('opSolicitacaoComercialPlano').value
        },
        context: document.body,
        success: function(data){
			var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
                showError($jq(data)[i].value);
                document.getElementById("pesquisaOfertas").innerHTML = ""
            } else {
                document.getElementById("pesquisaOfertas").innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
            document.getElementById("pesquisaOfertas").innerHTML = "";
        }  
    });
}

function removeForm(data){
	var data1 = data.replace('<form name="ofertaFixaForm" method="post" action="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregar.do">','');
	var data2 = data1.replace('</form>','');
	return data2
}

$jq('.sortable a').live("click",function() {
    acao($jq(this).attr('href'));
    return false;
})
$jq('.pagelinks a').live("click", function() {
    acao($jq(this).attr('href'));
    return false;
})

function acao(acao){
    var local = acao.split("?")[0].substr(acao.lastIndexOf("/")+1);
    var caminho = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/order.do?" + acao.split("?")[1];
    $jq.ajax({
        type: "GET",
        data: {
        	"element" : local
        },
        url: caminho,
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById(local).innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function obterTipoOfertaChecked(checkboxGroupClass) {
    var tipoOfertaChecked = "";

    $jq("#"+checkboxGroupClass+" input:checkbox:checked").each(function(){
        tipoOfertaChecked += $jq(this).val() + "|";
    });
    
    return tipoOfertaChecked.slice(0,tipoOfertaChecked.lastIndexOf('|'));
}

function carregandoDados(){
    return "<img src='/catalogo/static_server/img/carregando.gif'>";
}

function gravar(force){	
    if ((document.getElementById('disponibilidadeAlterada').value == "N" || document.getElementById('complementarAlterada').value == "N" ) 
    		&& !confirm("Existe abas que n\xe3o foram verificadas, deseja prosseguir?")){
        return;
    }
    cleanMessages();
    copiarDadosFormulario();
	var idOfertafixa = "";
    if (document.getElementById("idOfertafixa") != null) {
        idOfertafixa = document.getElementById("idOfertafixa").value;
    }
   
    $jq.ajax({
        type: "GET",
        url: "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/gravar.do",
        data: {
            "idOfertafixa" : idOfertafixa,
            "cdOferta" : document.getElementById('cdOfertaNewAba').value,
            "nmOferta" : document.getElementById('nmOfertaNewAba').value,
            "idServicoLinha" : document.getElementById('idServicoLinhaNewAba').value.split('&')[0],
            "psServivoLinha" : document.getElementById('idServicoLinhaNewAba').value.split('&')[1],            
            "idSolicitacaoComercialNormal" : document.getElementById('idSolicitacaoComercialNormalNewAba').value.split('&')[0],
            "opSolicitacaoComercialNormal" : document.getElementById('idSolicitacaoComercialNormalNewAba').value.split('&')[1],                        
            "idSolicitacaoComercialPreCadastro" : document.getElementById('idSolicitacaoComercialPreCadastroNewAba').value.split('&')[0],
            "opSolicitacaoComercialPreCadastro" : document.getElementById('idSolicitacaoComercialPreCadastroNewAba').value.split('&')[1],
            "idServicoPlano" : document.getElementById('idServicoPlanoNewAba').value.split('&')[0],
            "psServicoPlano" : document.getElementById('idServicoPlanoNewAba').value.split('&')[1], 
            "idSolicitacaoComercialPlano" : document.getElementById('idSolicitacaoComercialPlanoNewAba').value.split('&')[0],
            "opSolicitacaoComercialPlano" :  document.getElementById('idSolicitacaoComercialPlanoNewAba').value.split('&')[1],	                                        
            "dataVigenteInicio" : document.getElementById('dataVigenteInicioNewAba').value,
            "dataVigenteFim" : document.getElementById('dataVigenteFimNewAba').value,
            "tipoOfertaChecked" : document.getElementById('tipoOfertaCheckedAba').value,
            "areaRegistroChecked" : document.getElementById('areaRegistroCheckedAba').value,
            "operacao" : 'gravar',
            "force" : force

        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else if($jq(data)[i].id == "success" && $jq(data)[i].value != "") {
                showSuccess($jq(data)[i].value);
                $jq('.detalharOfertas').css('display','none');
                document.getElementById("ofertaSelecionada").innerHTML = "";
                document.getElementById("pesquisaOfertas").style.display = "block";
                document.getElementById("pesquisaOfertas").innerHTML = removeForm(data);
            } else if ($jq(data)[i].id == "confirm" && $jq(data)[i].value != ""){
				showError(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}
function cleanMessages(){    
    document.getElementById('divErros').style.display = "none";
    document.getElementById('conteudo_divErros').innerHTML = "";
    document.getElementById('conteudo_success').style.display = "none";
    document.getElementById('conteudo_success').innerHTML = "";
    document.getElementById("iconPendenteConfiguracao").style.display = "none";
}

function excluir(idOfertafixa) {
    cleanMessages();
    if(!confirm('Deseja realmente excluir essa oferta?')) {
        return false;
    }
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/excluir.do',
        data: {
            "idOfertafixa" : idOfertafixa
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else if($jq(data)[i].id == "success" && $jq(data)[i].value != "") {
                showSuccess($jq(data)[i].value);
                $jq('#pesquisaOfertas').css('display','none');
                $jq('.detalharOfertas').css('display','none');
                document.getElementById("ofertaSelecionada").innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
	        showError(data.responseText);
        }  
    });
}
function carregarFormularioNovaOfertaComplementar(element){
	cleanMessages();
    var idServicoOfertaComplementar = '';
    if (document.getElementById('idServicoOfertaComplementar') != null) {
        idServicoOfertaComplementar = document.getElementById('idServicoOfertaComplementar').value;
    }    
    
    if (element == "resultadoPesquisaComplementar") {

    	var valor = document.getElementById('psServicoNew').value;
    	var idServOferCompStr = document.getElementById('idServicoOfertaComplementar');    	
    	
    	for (var i = 0; i < idServOferCompStr.options.length; i++) {    		
    		var str = idServOferCompStr.options[i].value;
    		
    		if (str != '') {
    			var x = document.getElementById("idServicoOfertaComplementar").selectedIndex;
    			var y = document.getElementById("idServicoOfertaComplementar").options;    			    			
    			var val = y[x].value;
    			val = val.split('&')[1];

    			if (valor != val) {    				
    				showError("C&oacute;digo incompat&iacute;vel com o Servi&ccedil;o Linha");
    				return;
    			}    			
    		}    	
    	}   	
   		if (valor == '') {
   			showError("C&oacute;digo incompat&iacute;vel com o Servi&ccedil;o Linha");
   			return;
   		}
    }

    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregarFormularioNovaOfertaComplementar.do',
        data: {
            "element" : element,
            "idServicoOfertaComplementar" : idServicoOfertaComplementar
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById(element).innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
	        showError(data.responseText);
        }  
    });
}

function ofertaComplementarCheckbox(obj){
    if (obj.checked){
        var par = $jq('#'+obj.value).parent().parent();
        var tdidVigencia = par.children("td:nth-child(5)"); 
        $jq(tdidVigencia).html("<input type='text' value='"+tdidVigencia.html()+"' id='input"+obj.value+"' />"); 
    } else {
        var value = $jq('#input'+obj.value).val();
        var par = $jq('#'+obj.value).parent().parent();
        var tdidVigencia = par.children("td:nth-child(5)");         
        $jq(tdidVigencia).html(value);
    }
}

$jq(".ckbInput").live("click",function() {
    var idInput = 'input' + $jq(this).val();
    if (document.getElementById(idInput) == null) {
        if ($jq(this).attr("checked") == "checked") {
            var par = $jq(this).parent().parent();
            var tdidVigencia = par.children("td:nth-child(5)"); 
            $jq(tdidVigencia).html("<input type='text' id='"+idInput+"' value='"+tdidVigencia.html()+"' /><span class='required'>*</span><input type='hidden' id='h"+idInput+"' value='"+tdidVigencia.html()+"' />"); 
        }
    } else {
        if ($jq(this).attr("checked") != "checked") {
            var par = $jq(this).parent().parent();
            var tdidVigencia = par.children("td:nth-child(5)"); 
            $jq(tdidVigencia).html($jq('#hinput'+$jq(this).val()).val()); 
        }    
    }
    
})

function validarCompatibilidadeComplementar(solicitacaoComercialChecked){
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/validarCompatibilidadeComplementar.do',
        data: {
            "solicitacaoComercialChecked" : solicitacaoComercialChecked
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if ($jq(data)[i].id == "confirm" && $jq(data)[i].value != ""){
                showError(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        } 
    });
}

function adicionarComplementar(){
    //cleanMessages();
    var solicitacaoComercialChecked = "";
    $jq(".ckbInput:checked").each(function(){
        solicitacaoComercialChecked += $jq(this).val() + "|" + $jq('#input'+$jq(this).val()).val() + "|" + $jq('#hinput'+$jq(this).val()).val() + ";";
    });
    solicitacaoComercialChecked = solicitacaoComercialChecked.slice(0,solicitacaoComercialChecked.lastIndexOf(';'));
    validarCompatibilidadeComplementar(solicitacaoComercialChecked);
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/adicionarComplementar.do',
        data: {
            "solicitacaoComercialChecked" : solicitacaoComercialChecked
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
                showError($jq(data)[i].value);
            } else {
                document.getElementById("ofertaComplementar").innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });    
}
function excluirOfertaComplementar(idSolicitacaoComercial) {
    cleanMessages();
    if(!confirm('Deseja realmente excluir essa oferta?')) {
        return false;
    }
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/excluirOfertaComplementar.do',
        data: {
            "idSolicitacaoComercial" : idSolicitacaoComercial
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else if($jq(data)[i].id == "success" && $jq(data)[i].value != "") {
                showSuccess($jq(data)[i].value);
                document.getElementById("displayOfertaComplementarList").innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function carregarFormularioCNL(){
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregarFormularioCNL.do',
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById('pesquisarCNL').innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function carregarFormularioAreaRegistro() {
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregarFormularioAreaRegistro.do',
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById('pesquisarAreaRegistro').innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function pesquisarAreaRegistro() {
    var idUf = document.getElementById("idUf").value;
    var cdAreaRegistro = document.getElementById("cdAreaRegistro").value;
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/pesquisarAreaRegistro.do',
        data: {
            "idUf" : idUf,
            "cdAreaRegistro" : cdAreaRegistro
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById('adicionarAreaRegistro').innerHTML = removeForm(data);
				var checked = $jq(".ckbAreaRegistro").size() == $jq(".ckbAreaRegistro:checked").size();
		        $jq('.ckbAreaRegistroTodos').attr("checked",checked);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });    
}

function pesquisarCNL(){
    var idUf = document.getElementById("idUf").value;
    var idAreaRegistro = document.getElementById("idAreaRegistro").value;
    var idCidade = document.getElementById("idCidade").value;
    
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/pesquisarCNL.do',
        data: {
            "idUf" : idUf,
            "idAreaRegistro" : idAreaRegistro,
            "idCidade" : idCidade
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById('adicionarCNL').innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function adicionarAreaRegistro() {
    copiarDadosFormulario();
    var areaRegistroChecked = "";
    $jq(".ckbAreaRegistro:checked").each(function(){
        areaRegistroChecked += $jq(this).val() + "|";
    });
    areaRegistroChecked = areaRegistroChecked.slice(0,areaRegistroChecked.lastIndexOf('|'));
	$jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/adicionarOfertafixaFatorAreaRegistro.do',
        data: {
            "idUf" : document.getElementById("idUf").value,
            "areaRegistroChecked" : areaRegistroChecked,
            "operacao" : "adicionarAreaRegistro"
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById("conteudoAreaRegistro").innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function adicionarLocalidade(idCidade){
    copiarDadosFormulario();
    var localidadeChecked = "";
    $jq(".ckbLocalidade:checked").each(function(){
        localidadeChecked += $jq(this).val() + "|";
    });
    localidadeChecked = localidadeChecked.slice(0,localidadeChecked.lastIndexOf('|'));
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/adicionarOfertafixaFatorLocalidade.do',
        data: {
            "idCidade" : idCidade,            
            "localidadeChecked" : localidadeChecked
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById("conteudoAgrupadorCNL").innerHTML = removeForm(data);
                $jq(".accordion h3").click(function(){
                    $jq(this).next().slideToggle("slow");
                });
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function configurarFormularioAba(abaSelecionada) {
    /* alterar css para aba selecionada */
    selectAba(abaSelecionada);
    /* obtem o id da aba selecionada */
    var idAbaSelecionada = abaSelecionada.id;
    var idElementoConteudoAba = "";
    if (idAbaSelecionada == "aba1Link") {
        idElementoConteudoAba = "detalhesOfertas";
    } else if (idAbaSelecionada == "aba2Link") {
        idElementoConteudoAba = "ofertaComplementar";
    } else if (idAbaSelecionada == "aba3Link") {
        idElementoConteudoAba = "disponibilidade";
    }
    /* mostrar div responsavel por disponibilizar o formulario */
    
	$jq(".detalharOfertas,#" + idElementoConteudoAba).css('display','block').offset().top;
	document.getElementById(idElementoConteudoAba).scrollIntoView();
    /* exibir mensagem para usuario informando que esta carregando a pagina */
    document.getElementById(idElementoConteudoAba).innerHTML = carregandoDados();
}

function alternarAbas(abaSelecionada, carregar) {	
	var operacao = document.getElementById("operacao").value;
	var idAbaAtual = "";
	$jq(".selected").each(function(){
	    idAbaAtual = $jq(this).attr("id");
	});
    /* copiar dados dos formularios das abas para passar para o servidor */
    copiarDadosFormulario();
    /* limpar mensagens anteriores */
    cleanMessages();
    /* adicionar e alternar para conteudo para aba selecionada */
    configurarFormularioAba(abaSelecionada);
    /* obter id da oferta caso seja uma alteracao */
    var idOfertafixa = "";
    if (document.getElementById("idOfertafixa") != null) {
        idOfertafixa = document.getElementById("idOfertafixa").value;
    }
    /* obter id da aba selecionada */
    var idAbaSelecionada = abaSelecionada.id;

    document.getElementById("gravarOfertas").style.display = "block";
    /* solicita o formulario da aba enviado os atuais do formulario */ 

    
    var idSolicitacaoComercialNormal = document.getElementById("idSolicitacaoComercialNormalNewAba").value;
    var indexSolComerNormal = idSolicitacaoComercialNormal.indexOf('&');

	if ( indexSolComerNormal != -1) {				
		idSolicitacaoComercialNormal = document.getElementById("idSolicitacaoComercialNormalNewAba").value.split('&')[1];	
	}
	
	var idSolComerPreCad = document.getElementById("idSolicitacaoComercialPreCadastroNewAba").value;
	var indexIdSolComerPreCad = idSolComerPreCad.indexOf('&');
	
	if (indexIdSolComerPreCad != -1) {
		idSolComerPreCad = document.getElementById("idSolicitacaoComercialPreCadastroNewAba").value.split('&')[1];
	}
	
	var idSolComerPlano = document.getElementById("idSolicitacaoComercialPlanoNewAba").value;
	var indexIdSolComerPlano = idSolComerPlano.indexOf('&');
	
	if (indexIdSolComerPlano != -1) {
		idSolComerPlano = document.getElementById("idSolicitacaoComercialPlanoNewAba").value.split('&')[1]; 
	}
	
	if (operacao == "copiar") {
		var dtInicio = "";
		var dtFim = "";		
	
		if ( (idAbaAtual == "aba2Link" || idAbaAtual == "aba3Link" ) && (idAbaSelecionada == "aba1Link")  ) {			
			dtInicio = document.getElementById("dataVigenteInicioNewAba").value;			
	        dtFim = document.getElementById("dataVigenteFimNewAba").value;										        
		}
		
		if ( (idAbaAtual == "aba1Link" || idAbaAtual == "aba3Link" ) && (idAbaSelecionada == "aba2Link")  ) {			
			dtInicio = document.getElementById("dataVigenteInicioNewAba").value;			
	        dtFim = document.getElementById("dataVigenteFimNewAba").value;										        
		}
		
		if ( (idAbaAtual == "aba1Link" || idAbaAtual == "aba2Link" ) && (idAbaSelecionada == "aba3Link")  ) {			
			dtInicio = document.getElementById("dataVigenteInicioNewAba").value;			
	        dtFim = document.getElementById("dataVigenteFimNewAba").value;										        
		}

		var idServicoLinhaNewAba = document.getElementById("idServicoLinhaNewAba").value.split('&')[0];

		$jq.ajax({
			type: "GET",
			url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregarAba.do',
			data: {
				"idOfertafixa" : idOfertafixa,
				"idAbaSelecionada" : idAbaSelecionada,
				"idAbaAtual" : idAbaAtual,
				"operacao" : document.getElementById("operacao").value,
				"cdOferta" : document.getElementById("cdOfertaNewAba").value,
				"nmOferta" : document.getElementById("nmOfertaNewAba").value,
	            "idServicoLinha" : document.getElementById("idServicoLinhaNewAba").value.split('&')[0],
	            "psServivoLinha" : document.getElementById("idServicoLinhaNewAba").value.split('&')[1],  				
				"idSolicitacaoComercialNormal" : document.getElementById("idSolicitacaoComercialNormalNewAba").value.split('&')[0],
				"opSolicitacaoComercialNormal" : idSolicitacaoComercialNormal,
	            "idSolicitacaoComercialPreCadastro" : document.getElementById("idSolicitacaoComercialPreCadastroNewAba").value.split('&')[0],
	            "opSolicitacaoComercialPreCadastro" : idSolComerPreCad,
	            "idSolicitacaoComercialPlano" : document.getElementById("idSolicitacaoComercialPlanoNewAba").value.split('&')[0],
	            "opSolicitacaoComercialPlano" : idSolComerPlano,
	            "idServicoPlano" : document.getElementById("idServicoPlanoNewAba").value.split('&')[0],
	            "psServicoPlano" : document.getElementById("idServicoPlanoNewAba").value.split('&')[1],            	            	            												
				"dataVigenteInicio" : dtInicio,
            	"dataVigenteFim" : dtFim,
	            "tipoOfertaChecked" : document.getElementById("tipoOfertaCheckedAba").value,
	            "areaRegistroChecked" : document.getElementById("areaRegistroCheckedAba").value,            
	            "carregar" : carregar
			},
			context: document.body,
			success: function(data){
				var i = $jq(data).length - 1;
            	if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
					selectAba(document.getElementById(idAbaAtual));
					showError($jq(data)[i].value);
            	} else {
            		$jq(data).each(function(){
					    if ($jq(this).attr("id") == "statusOfertafixa" && $jq(this).val() == "finalizado") {
							document.getElementById("gravarOfertas").style.display = "none";
					    }
					});
            		
            	
           			var detalhesOfertas = document.getElementById("detalhesOfertas");
			        detalhesOfertas.innerHTML = "";
			        var ofertaComplementar = document.getElementById("ofertaComplementar");
			        ofertaComplementar.innerHTML = "";
			        var disponibilidade = document.getElementById("disponibilidade");
			        disponibilidade.innerHTML = "";

            		if (idAbaSelecionada == "aba1Link") {
		                if (document.getElementById("operacao").value == "novo") {
		                    document.getElementById("operacao").value = "alterar";
		                }
			            detalhesOfertas.innerHTML = removeForm(data);		           
			            ofertaComplementar.innerHTML = "";
			            disponibilidade.innerHTML = "";
			            $jq("#dataVigenteInicioNew,#dataVigenteFimNew").datepicker();
		            } else if (idAbaSelecionada == "aba2Link") {
		            	document.getElementById('complementarAlterada').value = "S";	            	
		                ofertaComplementar.innerHTML = removeForm(data);
			            detalhesOfertas.innerHTML = "";
			            disponibilidade.innerHTML = "";
		            } else if (idAbaSelecionada == "aba3Link") {
		            	document.getElementById('disponibilidadeAlterada').value = "S";
		                disponibilidade.innerHTML = removeForm(data);
						ofertaComplementar.innerHTML = "";
			            detalhesOfertas.innerHTML = "";
						$jq(".accordion h3").click(function(){
		                    $jq(this).next().slideToggle("slow");
		                });
		            } else {
		                detalhesOfertas.style.display = "none";
		                ofertaComplementar.style.display = "none";
		                disponibilidade.style.display = "none";
		                showError("Solicita&ccedil;&atilde;o inv&aacute;lida!");
		            }
		            var nomeOferta = document.getElementById("dsOfertafixa").value;
	                if (nomeOferta != "") {
	                    document.getElementById("ofertaSelecionada").innerHTML = "Nome da Oferta: " + nomeOferta;
	                } else {
	                    document.getElementById("ofertaSelecionada").innerHTML = "";
	                }
            		
            		
            		
				}
			},
			error: function(data, error){
	            showError(data.responseText);
	            document.getElementById("detalhesOfertas").innerHTML = "";
	            document.getElementById("ofertaComplementar").innerHTML = "";
	            document.getElementById("disponibilidade").innerHTML = "";
        }  
			
		
		});
		
	} else {
				 
	    $jq.ajax({
	        type: "GET",
	        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregarAba.do',
	        data: {
				"idOfertafixa" : idOfertafixa,
				"idAbaSelecionada" : idAbaSelecionada,
				"operacao" : document.getElementById("operacao").value,
				"cdOferta" : document.getElementById('cdOfertaNewAba').value,
				"nmOferta" : document.getElementById('nmOfertaNewAba').value,
	            "idServicoLinha" : document.getElementById('idServicoLinhaNewAba').value.split('&')[0],
	            "psServivoLinha" : document.getElementById('idServicoLinhaNewAba').value.split('&')[1],  				
				"idSolicitacaoComercialNormal" : document.getElementById('idSolicitacaoComercialNormalNewAba').value.split('&')[0],
				"opSolicitacaoComercialNormal" : idSolicitacaoComercialNormal,
	            "idSolicitacaoComercialPreCadastro" : document.getElementById('idSolicitacaoComercialPreCadastroNewAba').value.split('&')[0],
	            "opSolicitacaoComercialPreCadastro" : idSolComerPreCad,
	            "idSolicitacaoComercialPlano" : document.getElementById('idSolicitacaoComercialPlanoNewAba').value.split('&')[0],
	            "opSolicitacaoComercialPlano" : idSolComerPlano,
	            "idServicoPlano" : document.getElementById('idServicoPlanoNewAba').value.split('&')[0],
	            "psServicoPlano" : document.getElementById('idServicoPlanoNewAba').value.split('&')[1],  
	            "dataVigenteInicio" : document.getElementById('dataVigenteInicioNewAba').value,
	            "dataVigenteFim" : document.getElementById('dataVigenteFimNewAba').value,
	            "tipoOfertaChecked" : document.getElementById('tipoOfertaCheckedAba').value,
	            "areaRegistroChecked" : document.getElementById('areaRegistroCheckedAba').value,
	            "carregar" : carregar
	        },
	        context: document.body,
	        success: function(data){
				var i = $jq(data).length - 1;
	            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
					selectAba(document.getElementById(idAbaAtual));
					showError($jq(data)[i].value);
	            } else {
					$jq(data).each(function(){
					    if ($jq(this).attr("id") == "statusOfertafixa" && $jq(this).val() == "finalizado") {
							document.getElementById("gravarOfertas").style.display = "none";
					    }
					});
		            var detalhesOfertas = document.getElementById("detalhesOfertas");
		            detalhesOfertas.innerHTML = "";
		            var ofertaComplementar = document.getElementById("ofertaComplementar");
		            ofertaComplementar.innerHTML = "";
		            var disponibilidade = document.getElementById("disponibilidade");
		            disponibilidade.innerHTML = "";
		            if (idAbaSelecionada == "aba1Link") {
		                if (document.getElementById("operacao").value == "novo") {
		                    document.getElementById("operacao").value = "alterar";
		                }
			            detalhesOfertas.innerHTML = removeForm(data);		           
			            ofertaComplementar.innerHTML = "";
			            disponibilidade.innerHTML = "";
			            $jq("#dataVigenteInicioNew,#dataVigenteFimNew").datepicker();
		            } else if (idAbaSelecionada == "aba2Link") {
		            	document.getElementById('complementarAlterada').value = "S";	            	
		                ofertaComplementar.innerHTML = removeForm(data);
			            detalhesOfertas.innerHTML = "";
			            disponibilidade.innerHTML = "";
		            } else if (idAbaSelecionada == "aba3Link") {
		            	document.getElementById('disponibilidadeAlterada').value = "S";
		                disponibilidade.innerHTML = removeForm(data);
						ofertaComplementar.innerHTML = "";
			            detalhesOfertas.innerHTML = "";
						$jq(".accordion h3").click(function(){
		                    $jq(this).next().slideToggle("slow");
		                });
		            } else {
		                detalhesOfertas.style.display = "none";
		                ofertaComplementar.style.display = "none";
		                disponibilidade.style.display = "none";
		                showError("Solicita&ccedil;&atilde;o inv&aacute;lida!");
		            }
		            var nomeOferta = document.getElementById("dsOfertafixa").value;
	                if (nomeOferta != "") {
	                    document.getElementById("ofertaSelecionada").innerHTML = "Nome da Oferta: " + nomeOferta;
	                } else {
	                    document.getElementById("ofertaSelecionada").innerHTML = "";
	                }
	            }
	        },
	        error: function(data, error){
	            showError(data.responseText);
	            document.getElementById("detalhesOfertas").innerHTML = "";
	            document.getElementById("ofertaComplementar").innerHTML = "";
	            document.getElementById("disponibilidade").innerHTML = "";
	        }  
	    });
	
	
	}

    
    
}

function showError(msgTxt) {
	document.getElementById('divErros').style.display = "block";
	document.getElementById('divErros').scrollIntoView();
	document.getElementById('conteudo_divErros').innerHTML = msgTxt;
}

function showSuccess(msgTxt) {
	document.getElementById('conteudo_success').style.display = "block";
	document.getElementById('conteudo_success').scrollIntoView();
 	document.getElementById('conteudo_success').innerHTML = msgTxt;
}

function novaOferta() {
	/* remover valor de id da oferta, para caso alguma oferta ja tenha sido carregada*/
	document.getElementById("idOfertafixa").value = "";
    /* ocultar pesquisa e mensagens */
    document.getElementById('pesquisaOfertas').style.display = "none";
    /*informar o tipo de operacao realizada*/
    document.getElementById("operacao").value = "novo";
    document.getElementById("complementarAlterada").value = "N";
    document.getElementById("disponibilidadeAlterada").value = "N";
    /* carregar aba selecionada */
    alternarAbas(document.getElementById('aba1Link'), "carregar");
}

function visualizarOferta(idOfertafixa) {
    /*informar o tipo de operacao realizada*/
    document.getElementById("operacao").value = "visualizar";
    document.getElementById("idOfertafixa").value = idOfertafixa;
    alternarAbas(document.getElementById('aba1Link'), "carregar");
}

function alterarOferta(idOfertafixa) {
    /*informar o tipo de operacao realizada*/
    document.getElementById("operacao").value = "alterar";
    document.getElementById("idOfertafixa").value = idOfertafixa;
    alternarAbas(document.getElementById('aba1Link'), "carregar");
}

function copiarOferta(idOfertafixa) {
	/*informar o tipo de operacao realizada */
	document.getElementById("operacao").value = "copiar";	
    document.getElementById("idOfertafixa").value = idOfertafixa;
    alternarAbas(document.getElementById('aba1Link'), "carregarCopia");
    
	
}

function copiarDadosFormulario(){
	if (document.getElementById("cdOfertaNew") != null) {
		document.getElementById("cdOfertaNewAba").value = document.getElementById("cdOfertaNew").value;
	}
	if (document.getElementById("nmOfertaNew") != null) {
	    document.getElementById("nmOfertaNewAba").value = document.getElementById("nmOfertaNew").value;	    
	}
	if (document.getElementById("dataVigenteInicioNew") != null) {
	    document.getElementById("dataVigenteInicioNewAba").value = document.getElementById("dataVigenteInicioNew").value;
	}
	if (document.getElementById("dataVigenteFimNew") != null) {
		document.getElementById("dataVigenteFimNewAba").value = document.getElementById("dataVigenteFimNew").value;
	}
	if (document.getElementById("idServicoLinhaNew") != null) {
		document.getElementById("idServicoLinhaNewAba").value = document.getElementById("idServicoLinhaNew").value;
	}	
	if (document.getElementById("idSolicitacaoComercialNormalNew") != null) {
		document.getElementById("idSolicitacaoComercialNormalNewAba").value = document.getElementById("idSolicitacaoComercialNormalNew").value;		
	}		
	if (document.getElementById("idSolicitacaoComercialPreCadastroNew") != null) {
		document.getElementById("idSolicitacaoComercialPreCadastroNewAba").value = document.getElementById("idSolicitacaoComercialPreCadastroNew").value;		
	}
	if (document.getElementById("idServicoPlanoNew") != null) {
		document.getElementById("idServicoPlanoNewAba").value = document.getElementById("idServicoPlanoNew").value;
	}			
	if (document.getElementById("idSolicitacaoComercialPlanoNew") != null) {
	    document.getElementById("idSolicitacaoComercialPlanoNewAba").value = document.getElementById("idSolicitacaoComercialPlanoNew").value;
	}
	
	var idAbaAtual = "";
	$jq(".selected").each(function(){
	    idAbaAtual = $jq(this).attr("id");
	});
	var tipoOfertaChecked = "";
	if (idAbaAtual == "aba1Link") {
	    $jq("#checkboxgroup_editar input:checkbox:checked").each(function(){
	        tipoOfertaChecked += $jq(this).val() + "|";
		});
	    if ($jq("#comboTipoOfertaLinhaSpeedy").val() != null && $jq("#comboTipoOfertaLinhaSpeedy").val() != "") {
	        tipoOfertaChecked += $jq("#comboTipoOfertaLinhaSpeedy").val() + "|";
		}
		if ($jq("#comboTipoOfertaSpeedySolo").val() != null && $jq("#comboTipoOfertaSpeedySolo").val() != "") {
			tipoOfertaChecked += $jq("#comboTipoOfertaSpeedySolo").val() + "|";
		}
		tipoOfertaChecked = tipoOfertaChecked.slice(0,tipoOfertaChecked.lastIndexOf('|'));
	    $jq("#tipoOfertaCheckedAba").attr("value", tipoOfertaChecked);
    }
    if (idAbaAtual == "aba3Link") {
	    var areaRegistroChecked = "";
	    $jq(".ckbAreaRegistro:checked").each(function(){
	        areaRegistroChecked += $jq(this).val() + "|";
	    });
	    areaRegistroChecked = areaRegistroChecked.slice(0,areaRegistroChecked.lastIndexOf('|'));
	    $jq("#areaRegistroCheckedAba").attr("value", areaRegistroChecked);
    }
}

function excluirCNLConfigurada(idCidade){
    cleanMessages();
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/excluirAgrupadorCNL.do',
        data: {
            "idCidade" : idCidade
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById("displayAgrupadoCNLTOList").innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

$jq('.ckbAreaRegistroTodos').live("click",function() {
    /* Se o #todos for igual a checado */
    if ($jq(this).attr("checked")){
        $jq(".ckbAreaRegistro").each(function(){$jq(this).attr("checked", true);});
    } else {
        $jq(".ckbAreaRegistro").each(function(){$jq(this).attr("checked", false);});
    }
});
$jq('.ckbAreaRegistro').live("click",function() {
    if ($jq(this).attr("checked")){
        var check= $jq(".ckbAreaRegistro");
        var i = 0;
        $jq(check).each(function(){
            if ($jq(this).attr("checked")) {
                i++;
            }
        });
        if (i == $jq(check).length){
            $jq('.ckbAreaRegistroTodos').attr("checked",true);                
        }
    } else {
        $jq('.ckbAreaRegistroTodos').attr("checked",false);
    }
});



function validarNumeros(e)
{
	var keynum = window.event ? window.event.keyCode : e.which;
	if ((keynum == 8) || (keynum == 46))
		return true;
 
	return /\d/.test(String.fromCharCode(keynum));
}