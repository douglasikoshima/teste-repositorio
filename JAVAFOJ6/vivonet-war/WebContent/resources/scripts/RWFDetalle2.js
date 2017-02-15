zsa="if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();"
zso="if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();"
function fechamento() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/fechamentoBegin.do?idAtividade="+abaValue+"&idTabelaMotivo=4&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function encerramentoBKO() { carregaPesqForm(); i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/encerramentoBegin.do?docTec=0&idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function encerramentoDocTecBKO() { carregaPesqForm(); i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/encerramentoBegin.do?docTec=1&idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function concluir() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/concluirBegin.do?idAtividade="+abaValue+"&idTabelaMotivo=3&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function devolverBKO() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/devolverBegin.do?idAtividade="+abaValue+"&idTabelaMotivo=2&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function encaminhar() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/encaminharBegin.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function encaminharCRI() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/encaminharBeginCRI.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function prosseguirBko() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/prosseguirBeginBko.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function trocarCri() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/trocarBeginCri.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function adquirir() {
    if (top.frameApp.document.getElementById("inMirrorRC")!=null){
        flRC = top.frameApp.document.getElementById("inMirrorRC").value;
        i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/adquirirBegin.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl+"&inRC="+flRC;
    }else{
        i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/adquirirBegin.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;
    }
}
function finalizar() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/finalizarBegin.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function reabertura() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/reaberturaBegin.do?idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function pausa() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/pausaBegin.do?idAtividade="+abaValue+"&idTabelaMotivo=5&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function cancelar() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/cancelarBegin.do?idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function solucaoTecnica() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/testesBegin.do?idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function suspeito() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/suspeitoBegin.do?idAtividade="+abaValue+"&idTabelaMotivo=6&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function insistencia() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/insistenciaBegin.do?idAtividade=" + abaValue + "&idAtendimento=" + idAtd+"&inCRI="+inCRI+"&fila="+fl+ '&qtInsistencia='+document.all["qtInsistencia"].value;}
function documentoAssociado() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/documentoAssociadoBegin.do?idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl+'&idProcedencia=<bean:write name="procVO" property="idProcedencia" format=""/>';}
function respostaCliente() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/trocarBeginRc.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inRC="+inRC+"&fila="+fl;}
function inserirComentario() {i.src = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/inserirComentario.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
//**----------------------------------------------------------------------------------------------------------------------------------------------------------**//
function reaberturaAbaRel() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/reaberturaBeginAbaRel.do?idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function insistenciaAbaRel() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/insistenciaBeginAbaRel.do?idAtividade=" + abaValue + "&idAtendimento=" + idAtd+"&inCRI="+inCRI+"&fila="+fl+ '&qtInsistencia='+document.all["qtInsistencia"].value;}
function cancelarAbaRel() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/cancelarBeginAbaRel.do?idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function inserirComentarioAbaRel() {i.src = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/inserirComentarioAbaRel.do?idAtividade="+abaValue+"&idTabelaMotivo=1&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}

/** NFe *****************************************************************************************************************************************************************************/
function atualizarDadosOV() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/atualizarDadosOVBegin.do?idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
function cancelarOV() { i.src="/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/cancelarOVBegin.do?idAtividade="+abaValue+"&idAtendimento="+idAtd+"&inCRI="+inCRI+"&fila="+fl;}
/************************************************************************************************************************************************************************************/

function changeOpcao(opcao) {
    if(opcao.value>0){
        opSel=true;abaValue = opcao.value; abaOper();
    }else{
        opSel=false;
        bt05.innerText='Selecionar Operação';
        abaHist();
    }
}

function carregaPesqForm() {
    document.getElementById("ifrmPesqForm").src = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/pesquisaFormulario.do";
}

function abaHist() {
    abaSelected(btAba, bt01);
    DoResizeHeaderTableVivo();
    dAH.style.display='';
    dAC.style.display='none';
    if(dAE)dAE.style.display='none';
}

function abaEnc() {
    abaSelected(btAba, btEnc);
    DoResizeHeaderTableVivo();
    dAC.style.display='none';
    dAH.style.display='none';
    if(ae){
        ae=false;
    }else{
        dAE.style.display='';
        return false;
    }
}

function abaDetalhes() {
    abaSelected(btAbaInfo, btInfo01);
    DoResizeHeaderTableVivo();
    dAD.style.display='';
    dAA.style.display='none';
    dAS.style.display='none';
    dAN.style.display='none';
    dLA.style.display='none';
}

function abaAtendimento() {
    abaSelected(btAbaInfo, btInfo02);
    DoResizeHeaderTableVivo();
    dAD.style.display='none';
    dAS.style.display='none';
    dAN.style.display='none';
    dLA.style.display='none';
    if(at){
        at=false;
    }else{
        dAA.style.display='';
        return false;
    }
}

function abaSolicitante() {
    abaSelected(btAbaInfo, btInfo03);
    DoResizeHeaderTableVivo();
    dAD.style.display='none';
    dAA.style.display='none';
    dAN.style.display='none';
    dLA.style.display='none';
    if(sol){
        sol=false;
    }else{
        dAS.style.display='';
        return false;
    }
}

function abaNivelProc() {
    abaSelected(btAbaInfo, btInfo04);
    dAD.style.display='none';
    dAA.style.display='none';
    dAS.style.display='none';
    dLA.style.display='none';
    if(niv){
        niv=false;
        DoResizeHeaderTableVivo();
    }else{
        dAN.style.display='';
        DoResizeHeaderTableVivo();
        return false;
    }
}

function abaLinhasAssoc() {
    abaSelected(btAbaInfo, btInfo05);
    dAD.style.display='none';
    dAA.style.display='none';
    dAS.style.display='none';
    dAN.style.display='none';
    if(niv){
        niv=false;
        DoResizeHeaderTableVivo();
    }else{
        dLA.style.display='';
        DoResizeHeaderTableVivo();
        return false;
    }
}


function mostraComentario(linha){
    //var idAtdHco=linha.cells(0).innerText;
    //document.getElementById("ifrmComentAcao").src="RDComentarioHistorico.do?idAtdHco="+idAtdHco;
    document.getElementById("ifrmComentAcao").src="RDComentarioHistorico.do?idAtdHco="+linha;
}

function showComentario(index){
    var o = document.getElementById("divComentarios");
    if(top.frameApp.ifrHist){
        o.innerHTML = top.frameApp.ifrHist.arrayComentarios[index];
    }else{
        if(top.frameApp.ifrmAtendimento){
            o.innerHTML = top.frameApp.ifrmAtendimento.ifrHist.arrayComentarios[index];
        }else{
            if(top.frameApp.iframePopupTI.ifrHist){
                o.innerHTML = top.frameApp.iframePopupTI.ifrHist.arrayComentarios[index];
            }else{
                if(top.frameApp.iframeDetalheHistorico)
                    o.innerHTML = top.frameApp.iframeDetalheHistorico.ifrHist.arrayComentarios[index];
            }
        }
    }
}

function hideComentario(idAtendimento){
    document.getElementById("divComentarios").innerHTML = "";
}