<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Notas de URA"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection">
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="ntura_idx_verpagina">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
		<script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
		<script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="../../resources/scripts/calendar.js"></script>
        <link rel="stylesheet" type="text/css" href="../../resources/css/calendar.css">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="470" width="790" label="Vivo Net >> Workflow" operacoes="Notas de URA" scroll="no">
            <html:form action="/workflow/NotasURA/pesquisar.do" styleId="pesquisarForm">
            <input type="hidden" name="idAtendimentoNota">
            <table cellpadding="3" cellspacing="0" border="0" width="100%" class="tbl_bgGray">
                <tr>
                    <td align="right" valign="middle" width="15%">
                        <b>Nº Processo:</b>
                    </td>
                    <td colspan="3" align="left" valign="middle" width="25%">
                        <html:text property="notaVO.idAtendimento" size="13" onkeyup="Formatar(this.value, this.form.name, this.name, 'numero')"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" valign="middle">
                        <b>Data Início:</b>
                    </td>
                    <td align="left" valign="middle">
                        <html:text property="notaVO.dtAberturaIni" size="10" maxlength="10"  onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtAbIni" src="/FrontOfficeWeb/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('notaVO.dtAberturaIni', '%d/%m/%Y');">
                    </td>
                    <td align="right" valign="middle">
                        <b>Data Final:</b>
                    </td>
                    <td align="left" valign="middle">
                        <html:text property="notaVO.dtAberturaFim" size="10" maxlength="10"  onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtAbIni" src="/FrontOfficeWeb/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('notaVO.dtAberturaFim', '%d/%m/%Y');">
                    </td>
                </tr>
                <tr>
                    <td align="right" valign="middle">
                        <b>Login PA:</b>
                    </td>
                    <td align="left" valign="middle">
                        <html:text property="notaVO.login" size="20" maxlength="250"/>
                    </td>
                    <td align="right" valign="middle">
                        <b>RE Consultor:</b>
                    </td>
                    <td align="left" valign="middle">
                        <html:text property="notaVO.reConsultor" size="12" maxlength="12" onkeyup="Formatar(this.value, this.form.name, this.name, 'numero')"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" valign="middle">
                        <b>Nome Usuário:</b>
                    </td>
                    <td align="left" valign="middle" colspan="3">
                        <html:text property="notaVO.nmUsuario" size="70" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <td align="right" valign="middle">
                        <b>Cliente Contatado:</b>
                    </td>
                    <td align="left" valign="middle" colspan="3">
                        <html:checkbox property="notaVO.cliContatado" value="1" styleClass="radio"/> &nbsp;&nbsp;&nbsp;&nbsp;
                        <b>Cliente Transferido:</b>
                        <html:checkbox property="notaVO.cliTransferido" value="1" styleClass="radio"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <b>Notas sem Consultor:</b>
                        <html:checkbox property="notaVO.notaConsultor" value="1" styleClass="radio"/>
                    </td>
                    <td align="right" valign="middle" width="25%">
                    <acesso:controlHiddenItem nomeIdentificador="ntura_idx_pesquisar">
                        <img hspace="8" vspace="6" style="border:none;" onClick="pesquisar(); return false;" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/>
                        <img hspace="8" vspace="6" style="border:none;" onClick="limpar(); return false;" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
                <tr>
                </tr>
            </table>
            <iframe id="ifrmPesquisa" name="ifrmPesquisa" style="display:none;" width="780" height="300" frameborder="0" scrolling="no"></iframe>
            <table width="100%"><tr>
            <td align="right">
            <acesso:controlHiddenItem nomeIdentificador="ntura_idx_incluir">
            <img hspace="8" vspace="6" style="border:none;" onClick="incluir(); return false;" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" alt="Incluir Nota" rolloverImage="/FrontOfficeWeb/resources/images/bt_incluir_over.gif"/>
            </acesso:controlHiddenItem>
            </td>
            </tr></table>
            </html:form>
            <vivo:quadroFlutuante id="divIncluirAlterar" idIframe="ifrmIncluirAlterar" scroll="no" spacesLeft="50" spacesTop="30" url="<%=request.getContextPath()%>" label="Detalhe nota" display="none" height="400" width="600"/>
            <vivo:quadroFlutuante id="dvDetalheNota" idIframe="ifrmDetalheNota" width="610" height="425" spacesTop="10" spacesLeft="75" display="none" url="<%=request.getContextPath()%>" label="Detalhe Notas" onclick="ifrmDetalheNota.fecharNotaUra();restartRefresh();"/>
            <vivo:quadroFlutuante id="dvEditarNota" idIframe="ifrmAlteraNota" width="610" height="425" spacesTop="10" spacesLeft="75" display="none" url="<%=request.getContextPath()%>" label="Editar Notas" onclick="ifrmAlteraNota.fecharNotaUra();restartRefresh();"/>
<script>
f = document.forms[0];
var treadRefresh;
var currentTab;
var currentMlSeconds;
function detNota(idAtendimento){
    screen_block();
    stopRefresh();
    document.forms[0]['idAtendimentoNota'].value = idAtendimento;
    dvDetalheNota.style.display = '';
    document.forms[0].action     = 'lerNotasURADetalhe.do';
    document.forms[0].target     = 'ifrmDetalheNota';
    document.forms[0].submit();
}

function alteraNota(idAtendimentoNota){
    screen_block();
    stopRefresh();
    document.forms[0]['idAtendimentoNota'].value = idAtendimentoNota;
    dvEditarNota.style.display = '';
    document.forms[0].action     = 'editarNotaURA.do';
    document.forms[0].target     = 'ifrmAlteraNota';
    document.forms[0].submit();
}

function pesquisar() {
    f = document.forms[0];
    document.getElementById('ifrmPesquisa').style.display="";
    screen_block();
    f.action='pesquisar.do';
    f.target='ifrmPesquisa';
    f.submit();
}

function incluir() {
    screen_block();
    divIncluirAlterar.style.display="block";
    f.target="ifrmIncluirAlterar";
    f.idAtendimentoNota.value='';
    f.action="editarNotaURA.do";
    f.submit();
}

function validaTelefone(obj) {
    if(obj.value!="" && obj.value.length < 13) {
        alert("Linha invalida, favor corrigir!");
    }
}

function limpar() {
    f.reset();
    pesquisar();
}

function screen_block() {
    //Liga animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
}

function screen_unblock() {
    //Desliga animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
}

function validaDataOnBlur(data){
    if(data.value == '')
        return false;
    if(!validaData(data.value)){
        data.value = '';
        data.focus();
        alert("Data inválida");
    }
}
function restartRefresh() {
   // startRefresh(abaSelecionada,currentMlSeconds);
}

function stopRefresh() {
    //Limpa a tread se necessario
    window.clearInterval(treadRefresh);
}


function testaEnter() {
    if (window.event.keyCode==13) {
        return false;
    } else {
        return true;
    }

}
</script>

        </vivo:sessao>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>