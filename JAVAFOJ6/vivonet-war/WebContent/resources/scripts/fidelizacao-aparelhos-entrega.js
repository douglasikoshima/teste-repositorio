function cancela(acao, obj){
    document.forms[0].elements["tipoEncerramento"].value="5";   
    document.forms[0].action="filtroFimRetencao.do?acao="+acao;
    parent.parent.mostrar_div();
    document.forms[0].target ="";
    document.forms[0].submit();
}

function testarCaracteresEspeciais(obj,objname){
    msg =  "";
    caracteres = "0123456789abcdefghijklmnopqrstuvwxyz ";
    for(i=0; i < obj.value.length; i++){
        if(caracteres.indexOf(obj.value.toLowerCase().charAt(i))<0){
            msg+=obj.value.charAt(i)+" ";
        }
    }
    if(msg != ""){
        return objname + ": "+msg+"\n";
    }
    return msg;
}

function testarCamposComCaracteres(){
    msg = "";
    msg+=testarCaracteresEspeciais(document.ofertaAparelhoForm.elements["rua"], "Rua");
    msg+=testarCaracteresEspeciais(document.ofertaAparelhoForm.elements["numero"], "Número");
    msg+=testarCaracteresEspeciais(document.ofertaAparelhoForm.elements["complemento"], "Complemento");
    msg+=testarCaracteresEspeciais(document.ofertaAparelhoForm.elements["bairro"], "Bairro");
    msg+=testarCaracteresEspeciais(document.ofertaAparelhoForm.elements["cidade"], "Cidade");
    msg+=testarCaracteresEspeciais(document.ofertaAparelhoForm.elements["{actionForm.nmContato}"], "Nome Contato");
    msg+=testarCaracteresEspeciais(document.ofertaAparelhoForm.elements["{actionForm.rgContato}"], "RG");
    if(msg!=""){
        alert("Por favor, retirar caracteres especiais dos seguintes campos:\n" + msg);
        return false;
    
    }else{
        return true;
    }
}

function reter(obj) {
    f = document.forms[0];
	var dia  = new Date();
	var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
    var idEntrega = null;

    //Verifica se existem os dois radios (Delivery/Loja)
    if (f.idEntrega.length != undefined) {
        //Delivery
        if(f.idEntrega[0].checked){
            idEntrega = 0;
        }
        //Loja
        else if(f.idEntrega[1].checked){
            idEntrega = 1;
        }
    } else {
        if(f.idEntrega.checked){
            idEntrega = document.ofertaAparelhoForm.idEntrega.value;
        } 
    }

    if (idEntrega == 0) {
        //Maximo de caracteres que o SAP aceita para os campos
        maxRua    = 60;     //Rua considera a somatoria dos campos (Rua/Numero/Complemento)
        maxBairro = 35;
        maxCidade = 35;
        maxEstado = 3;
        maxCEP    = 10;

        if (f.complemento.value=="")
        	qtdeCaracSepComplemento = 0; 
        else
        	qtdeCaracSepComplemento = 3;
        
        qtdeCaracSepNumero = 2;

        ruaLength    = trim(f.rua.value).length+trim(f.complemento.value).length+trim(f.numero.value).length+qtdeCaracSepNumero+qtdeCaracSepComplemento;
        bairroLength = trim(f.bairro.value).length;
        cidadeLength = trim(f.cidade.value).length;
        estadoLength = trim(f.idUFSelecionado.value).length;
        cepLength    = trim(f.cep.value).length;
    }
    /*if(f.elements["{actionForm.modelo}"].value == ""){
        alert("Favor preencher o campo Modelo!");
    
	}else if(f.elements["{actionForm.cor}"].value == ""){
        alert("Favor preencher o campo Cor!");
    
	}else if(f.elements["{actionForm.preco}"].value == ""){
        alert("Favor preencher o campo Preço!");
    
	}else */
    if (idEntrega == null){
        alert('Por favor, selecione Delivery ou Loja.');

    }else if ( idEntrega == 0 && (trim(f.rua.value) == ""
                                  || trim(f.numero.value) == ""
                                  || trim(f.cidade.value) == ""
                                  || trim(f.idUFSelecionado.value) == ""
                                  || trim(f.bairro.value) == ""
                                  || trim(f.cep.value) == "")){
        alert('Algum dos campos referentes a dados de endereço está vazio.');

    }else if (idEntrega == 0 && ruaLength > maxRua){
        qtdeCaracteresNumeroComplemento = f.numero.value.length+f.complemento.value.length;
        qtdePermitidaRua = maxRua - (qtdeCaracSepComplemento+qtdeCaracSepNumero+qtdeCaracteresNumeroComplemento);
        alert("A quantidade de caracteres nos campos de endereço ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");

    }else if (idEntrega == 0 && bairroLength > maxBairro){
        alert("A quantidade de caracteres no campo Bairro ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
    
    }else if (idEntrega == 0 && cidadeLength > maxCidade){
        alert("A quantidade de caracteres no campo Cidade ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
    
    }else if (idEntrega == 0 && estadoLength > maxEstado){
        alert("A quantidade de caracteres no campo Estado ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
    
    }else if (idEntrega == 0 && cepLength > maxCEP){
        alert("A quantidade de caracteres no campo CEP ultrapassa o máximo \n permitido pelo sistema legado. Correção necessária.");
    
    }else if(idEntrega == 0 
            && trim(document.ofertaAparelhoForm.nmContato.value) == "" 
            && trim(document.ofertaAparelhoForm.rgContato.value) == "" 
            && trim(document.ofertaAparelhoForm.telContato.value) == ""){
        alert("Por favor, preencha Autorização de Entrega para Terceiros ou \nselecione opção para agendamento de retirada em Loja!");

    }else if(idEntrega == 0 && document.ofertaAparelhoForm.rgContato.value==""){
        alert("Por favor, preencha o RG de Contato");

	}else if(idEntrega == 0 && document.ofertaAparelhoForm.telContato.value.length < 13){
        alert("Telefone contato inválido");

    }else if(idEntrega == 1 && trim(document.ofertaAparelhoForm.nmLoja.value)==""){
    	document.ofertaAparelhoForm.nmLoja.value = trim(document.ofertaAparelhoForm.nmLoja.value);
          alert("Por favor preencher o campo Nome da Loja corretamente!");

	}else if(idEntrega == 1 && document.ofertaAparelhoForm.qtdEstoque.value == ""){
		  alert("Favor preencher o campo Quantidade em Estoque!");

	}else if(idEntrega == 1 && document.ofertaAparelhoForm.dtRetirada.value == ""){
		  alert("Favor preencher Data de retirada do aparelho");

	}else if(idEntrega == 1 && !validaDataFinal(hoje, document.ofertaAparelhoForm.dtRetirada.value)){
		  alert("Data de retirada do aparelho menor que data atual. Por favor corrigir!");

	}else{
        f.target ="";
		f.action = "reterAparelho.do?acao=ok";
        f.idUFSelecionado.disabled = false;
        parent.parent.mostrar_div();
        f.submit();
    }
}
 
function pesquisaEndereco() {
    f = document.forms[0];

    var params = "";
    params += "idMotivoAlteracaoEndereco="+f.idMotivoAlteracaoEndereco.value;

    dvPesqEndereco.style.display = '';
    parent.parent.mostrar_div();
    f.action="pesquisaEndereco.do?"+params;
    f.target="ifrmPesqEndereco";
    f.submit();
}
 
function abreUrl(url){
	window.open(url,"janela");
}

function agendar(){
	parent.dvNrProcesso.style.display = '';
	parent.parent.mostrar_div();
	document.forms[0].target = "ifrmNrProcesso";
	document.forms[0].action = "getDadosAgendarContato.do";
	document.forms[0].submit();
} 