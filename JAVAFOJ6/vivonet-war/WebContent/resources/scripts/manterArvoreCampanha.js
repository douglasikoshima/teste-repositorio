//var ultimo_comando_executado = "";

function abreCampanha() {
    
    dvNrProcesso.style.display = '';
    
    document.forms["frmSelecao"].target = "ifrmNrProcesso";
    document.forms["frmSelecao"].action = "../ManterCampanha/begin.do";
    document.forms["frmSelecao"].submit();
    dv_dvNrProcesso.innerText = "Incluir Campanha";
}

function abreSubCampanha() {
    dvNrProcesso.style.display = '';
    
    document.forms["frmSelecao"].target = "ifrmNrProcesso";
    document.forms["frmSelecao"].action = "../ManterSubCampanha/begin.do?page=subcampanha";
    document.forms["frmSelecao"].submit();
    dv_dvNrProcesso.innerText = "Incluir Sub Campanha";
}

function abreLista() {
    dvNrProcesso.style.display = '';
    
    document.forms["frmSelecao"].target = "ifrmNrProcesso";
    document.forms["frmSelecao"].action = "../../CarregarLista/begin.do?acao=editar";
    document.forms["frmSelecao"].submit();
    dv_dvNrProcesso.innerText = "Associar Listas Externas";
}

function abreHistorico() {
    dvNrProcesso.style.display = '';
    document.forms["frmSelecao"].target = "ifrmNrProcesso";
    document.forms["frmSelecao"].action = "../../HistoricoCampanha/HistoricoCampanha1.jsp";
    document.forms["frmSelecao"].submit();
}

function abreParametros() {
    dvNrProcesso.style.display = '';
    document.forms["frmSelecao"].target = "ifrmNrProcesso";
    document.forms["frmSelecao"].action = "../ManterSubCampanha/begin.do?page=parametros";
    document.forms["frmSelecao"].submit();
    dv_dvNrProcesso.innerText = "Parametrizar Campanha";
}

function abreSimulacao() {
    dvNrProcesso.style.display = '';
    document.forms["frmSelecao"].target = "ifrmNrProcesso";
    document.forms["frmSelecao"].action = "../../SimulacaoPerguntas/begin.do";
    document.forms["frmSelecao"].submit();
    dv_dvNrProcesso.innerText = "Simular Campanha";
}

function abreGeral(sUrl, sMsg)
{
    if(sUrl != "") {
        /*
        parent.document.forms["scriptCampanhaActionForm"].campanhaSelecionada.disabled = true;
        parent.document.forms["scriptCampanhaActionForm"].subCampanhaSelecionada.disabled = true;
        parent.document.forms["scriptCampanhaActionForm"].versaoSelecionada.disabled = true;    
        document.forms["frmSelecao"].target = "";
        */
        document.forms["frmSelecao"].target = "ifrmAuxiliar";    
        document.getElementById("dvAuxiliar").style.display = "block";
        document.forms["frmSelecao"].action = sUrl;
        document.forms["frmSelecao"].submit();        
    }

}

function Insert()
{
    if (tree.getSelected()) 
    { 
        var sIcon =  tree.getSelected().icon;
        var sDescricao = tree.getSelected().text;
        var sUrl = "";
        var sMsg = "";
    
        
        /****************** Códigos ***************************************************/
        var iCodIndexPerg		 = document.frmSelecao.idPergunta.value;
        var iCodIndexResp		 = document.frmSelecao.idResposta.value;
        
        var iCodIndexsStatus		 = document.frmSelecao.idStatusCampanha.value;
        var iCodIndexsMotivo		 = 0;
        var iCodIndexsSubMotivo		 = 0;
                        
        if(sIcon.indexOf("_canal_") >0){
            sUrl = "incluirPergunta.do?Desc=" + sDescricao;
            sMsg = "Incluir Pergunta";
        }else if(sIcon.indexOf("_perg_") >0){
            sUrl = "incluirResposta.do?indexPergunta=" + iCodIndexPerg + "&Desc=" + sDescricao+ "&sgTipoApresentacaoPergunta=" + document.frmSelecao.sgTipoApresentacaoPergunta.value;
            sMsg = "Incluir Resposta";
        }else if(sIcon.indexOf("icon_stmtsb") >0){
            sUrl = "incluirStatus.do";
            sMsg = "Configurar Status";	
        }else if(sIcon.indexOf("icon_st") >0){
            sUrl = "incluirStatus.do?indexStatus=" + iCodIndexsStatus;
            sMsg = "Configurar Status";
        }else if(sIcon.indexOf("icon_mt") >0){
            sUrl = "editarStatus.do.do?indexStatus=" + iCodIndexsStatus + "&indexMotivo=" + iCodIndexsMotivo;
            sMsg = "Configurar Motivo";
        }
        
        abreGeral(sUrl, sMsg);
        
        
    }
    else
    {
        alert('Selecione um nível da árvore');
    }
}	

function Update()
{
    if (tree.getSelected()) 
    { 
        var sIcon =  tree.getSelected().icon;
        var sDescricao = tree.getSelected().text;
        var sUrl = "";
        var sMsg = "";
        
        
        /****************** Códigos ***************************************************/
        var iCodIndexPerg			 = document.frmSelecao.idPergunta.value;
        var iCodIndexResp			 = document.frmSelecao.idResposta.value;
        
        var iCodIndexsStatus		 = document.frmSelecao.idStatusCampanha.value;
        var iCodIndexsMotivo		 = 0;
        var iCodIndexsSubMotivo		 = 0;
        
        if(sIcon.indexOf("_subcamp_") >0){
            sUrl = "editarSubCampanha.do";
            sMsg = "Alterar Campanha";
        }else if(sIcon.indexOf("_perg_") >0){
            sUrl = "editarPergunta.do?indexPergunta=" + iCodIndexPerg;
            sMsg = "Alterar Pergunta";
        }else if(sIcon.indexOf("_resp_") >0){	
            sUrl = "editarResposta.do?indexResposta=" + iCodIndexResp;
            sMsg = "Alterar Resposta";
        }else if(sIcon.indexOf("icon_st") >0){
            sUrl = "editarStatus.do?indexStatus=" + iCodIndexsStatus;
            sMsg = "Alterar Status";
        }else if(sIcon.indexOf("icon_mt") >0){
            sUrl = "editarStatus.do?indexStatus=" + iCodIndexsStatus + "&indexMotivo=" + iCodIndexsMotivo;
            sMsg = "Alterar Motivo";
        }else if(sIcon.indexOf("icon_sb") >0){
            sUrl = "editarStatus.do?indexStatus=" + iCodIndexsStatus + "&indexMotivo=" + iCodIndexsMotivo + "&indexSubMotivo=" + iCodIndexsSubMotivo;
            sMsg = "Alterar Sub Motivo";
        }
        
        abreGeral(sUrl, sMsg);
        
    }
    else
    {
        alert('Selecione um nível da árvore');
    }
}

function deleteSelectedNode() {
    if ( tree.getSelected() ) {
        tree.getSelected().remove();
    }
}

function Delete()
{
    if (tree.getSelected()) 
    { 
        if (confirm('Deseja realmente excluir?'))
        {
            var sIcon =  tree.getSelected().icon;
            
            var sUrl = "";
            var sMsg = "";
        
            
            /****************** Códigos ***************************************************/
            var iCodIndexPerg		 = document.frmSelecao.idPergunta.value;
            var iCodIndexResp		 = document.frmSelecao.idResposta.value;
            var iCodIndexCanal       = document.frmSelecao.idCanalCampanha.value;
            var _target              = "";
            var iCodIndexsStatus	 = document.frmSelecao.idStatusCampanha.value;            
            document.frmSelecao.idPergunta.value		= iCodIndexPerg;
            document.frmSelecao.idResposta.value		= iCodIndexResp;
            document.frmSelecao.idCanalCampanha.value	= iCodIndexCanal;
            document.frmSelecao.idStatusCampanha.value	= iCodIndexsStatus;
            
            
            
            if(sIcon.indexOf("_subcamp_") >0){
                sUrl = "deleteSubCampanha.do";
                _target = "ifrmAuxiliar";
            }else if(sIcon.indexOf("_canal_") >0){
                sUrl = "deleteCanal.do";
            }else if(sIcon.indexOf("_perg_") >0){
                sUrl = "deletePergunta.do";
                _target = "ifrmAuxiliar";
            }else if(sIcon.indexOf("_resp_") >0){
                sUrl = "deleteResposta.do";
                _target = "ifrmAuxiliar";
            }else if(sIcon.indexOf("icon_st") >0){
                sUrl = "deleteStatus.do";
                _target = "ifrmAuxiliar";
            }
            
            document.forms["frmSelecao"].target = _target;
            document.forms["frmSelecao"].action = sUrl;
            document.forms["frmSelecao"].submit();            
            parent.mostrar_div();
            
        }
    }	
    else
    {
            alert('Selecione um nível da árvore');
    }
    
    //parent.oculta_div();
}	

function SetCodigoPerg(iCod, idTipoApresentacao, xmlParam) {

    var sIcon =  tree.getSelected().icon;			
    
    document.frmSelecao.idPergunta.value = iCod;            
    document.frmSelecao.idTipoApresentacao.value = idTipoApresentacao;
    document.frmSelecao.sgTipoApresentacaoPergunta.value = idTipoApresentacao;
    
    document.getElementById("Acao").style.display = "block";			
    document.getElementById("btnAlterar").style.display = "block";
    document.getElementById("btnExcluir").style.display = "block";			
    
    if(sIcon.indexOf("perg_texto") >0){
        document.getElementById("btnIncluir").style.display = "none";
    } else {
        document.getElementById("btnIncluir").style.display = "block";
    }

    xmlParam = xmlParam + "<idPergunta>"+ iCod +"</idPergunta>";
    
    if (!tree.getSelected().isAddEnabled()) 
    {
        return;
    }

    parent.mostrar_div();

    frames["ifrArvore"].document.forms.montarArvore.elements("idPergunta").value = iCod;
    frames["ifrArvore"].document.forms.montarArvore.elements("inResposta").value = "false";
    frames["ifrArvore"].document.forms.montarArvore.elements("xmlParam").value = xmlParam;
    frames["ifrArvore"].document.forms.montarArvore.target="ifrArvore";
    frames["ifrArvore"].document.forms.montarArvore.submit();
}

function SetCodigoResp(iCodResp,iCodPerg, xmlParam) {

    document.frmSelecao.idPergunta.value = iCodPerg;    
    document.frmSelecao.idResposta.value = iCodResp;    
    document.getElementById("Acao").style.display = "block";
    document.getElementById("btnAlterar").style.display = "block";

    document.getElementById("btnExcluir").style.display = "block";
    document.getElementById("btnIncluir").style.display = "none";    

    parent.mostrar_div();

    frames["ifrArvore"].document.forms.montarArvore.elements("inResposta").value = "true";
    frames["ifrArvore"].document.forms.montarArvore.elements("idPergunta").value = iCodPerg;
    frames["ifrArvore"].document.forms.montarArvore.elements("xmlParam").value = xmlParam;
    frames["ifrArvore"].document.forms.montarArvore.target="ifrArvore";
    frames["ifrArvore"].document.forms.montarArvore.submit();
}

function SetCodigoCamp() {
    document.getElementById("Acao").style.display = "block";
    document.getElementById("btnAlterar").style.display = "block";
    document.getElementById("btnIncluir").style.display = "none";
    document.getElementById("btnExcluir").style.display = "block";
    
}

function ViewAction() {

    document.getElementById("Acao").style.display = "block";
    document.getElementById("btnAlterar").style.display = "none";
    document.getElementById("btnExcluir").style.display = "none"; 
    document.getElementById("btnIncluir").style.display = "block";

    xmlParam = "<inCanal>0</inCanal>";

    if (!tree.getSelected().isAddEnabled()) 
    {
        return;
    }
    
    parent.mostrar_div();
    
    frames["ifrArvore"].document.forms[0].elements("xmlParam").value = xmlParam;
    frames["ifrArvore"].document.forms.montarArvore.elements("inResposta").value = "false";
    frames["ifrArvore"].document.forms[0].target="ifrArvore";
    frames["ifrArvore"].document.forms[0].submit();
}

function SetCodigoCanal(idCanal) {

    var idCanalAux = document.frmSelecao.idCanalCampanha.value;
    var sDescricao = "";

    if(tree.getSelected()){
        sDescricao = tree.getSelected().text;
    }
    if(idCanalAux !=idCanal) {
        document.frmSelecao.idCanalCampanha.value = idCanal;
        document.frmSelecao.dsCanalCampanha.value = sDescricao;
        document.frmSelecao.action = "CarregarAction.do";
    }else{
        document.frmSelecao.idCanalCampanha.value = idCanal;
    }
    
    document.getElementById("Acao").style.display = "block";
    document.getElementById("btnIncluir").style.display = "block";
    document.getElementById("btnAlterar").style.display = "none";
    document.getElementById("btnExcluir").style.display = "none";
    
    document.frmSelecao.idCanalCampanha.value = idCanal;
    
    xmlParam = "<inCanal>1</inCanal><idCanal>"+ idCanal +"</idCanal>";

    if (!tree.getSelected().isAddEnabled()) 
    {
        return;
    }

    parent.mostrar_div();

    frames["ifrArvore"].document.forms.montarArvore.elements("inResposta").value = "false";
    frames["ifrArvore"].document.montarArvore.elements("idCanal").value = idCanal;
    frames["ifrArvore"].document.montarArvore.elements("idCanalCampanha").value = idCanal;
    frames["ifrArvore"].document.montarArvore.elements("dsCanalCampanha").value = sDescricao;
    frames["ifrArvore"].document.montarArvore.elements("xmlParam").value = xmlParam;
    frames["ifrArvore"].document.montarArvore.target="ifrArvore";
    frames["ifrArvore"].document.forms[0].submit();
}

function OcultaAcao() {
    document.getElementById("Acao").style.display = "none";
}

function fechaQuadroAuxiliar() {
  document.getElementById("dvAuxiliar").style.display = "none";
  document.frames["ifrmAuxiliar"].src = "about:blank";   
}

function SetCodigoStatus(iCod, xmlParam) {
    document.frmSelecao.idStatusCampanha.value = iCod;
    document.getElementById("Acao").style.display = "block";
    
    document.getElementById("btnIncluir").style.display = "block";
    document.getElementById("btnExcluir").style.display = "block";
    
    document.getElementById("btnAlterar").style.display = "none";
    
    xmlParam = xmlParam + "<idStatus>" + iCod + "</idStatus>";

    parent.mostrar_div();

    frames["ifrArvore"].document.forms.montarArvore.elements("inResposta").value = "false";
    frames["ifrArvore"].document.forms[0].elements("idStatus").value = iCod;
    frames["ifrArvore"].document.forms[0].elements("xmlParam").value = xmlParam;
    frames["ifrArvore"].document.forms[0].target="ifrArvore";
    frames["ifrArvore"].document.forms[0].submit();
}

function SetCodigoMotivo(idMotivo,idStatus){
    OcultaAcao();
    document.frmSelecao.idStatusCampanha.value = idStatus;
    
    xmlParam = "<inCanal>0</inCanal><idStatus>" + idStatus + "</idStatus><idMotivo>" + idMotivo + "</idMotivo>";

    parent.mostrar_div();

    frames["ifrArvore"].document.forms.montarArvore.elements("inResposta").value = "false";
    frames["ifrArvore"].document.forms[0].elements("idMotivo").value = idMotivo;
    frames["ifrArvore"].document.forms[0].elements("xmlParam").value = xmlParam;
    frames["ifrArvore"].document.forms[0].target="ifrArvore";
    frames["ifrArvore"].document.forms[0].submit();
}

function SetCodigoSubMotivo(idSubMotivo,idMotivo,idStatus) {
    OcultaAcao();
    document.frmSelecao.idStatusCampanha.value = idStatus;
}

function fecharFrame()
{
  document.getElementById("dvNrProcesso").style.display = "none";
  document.getElementById("dvNrProcesso").src = "about:blank";
}