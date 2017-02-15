<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="workflow.AtendimentoFila.AtendimentoFilaController.AtendimentoFilaForm"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/weblogic-tags.tld" prefix="wl"%>

<bean:define id="form"     name="atendimentoFilaForm" scope="session" />

<% request.getSession().removeAttribute("atendimentoFilaForm"); %>

<bean:define id="gruposVO"      name="form" property="atendimentoInformacaoVO.WFGrupoVOArray"/>
<bean:define id="usuariosVIVO"  name="form" property="atendimentoInformacaoVO.usuarioVIVOArray"/>
<bean:define id="atdFilaPesqVO" name="form" property="atdFilaPesqVO"/>
<bean:define id="formCamposVO"  name="form" property="formularioVO.formularioCampoVOArray"/>
<bean:define id="camposValorVO" name="form" property="camposValorVO"/>
<bean:define id="ufVO"          name="form" property="formularioVO.UFVOArray"/>
<bean:define id="tipoLinhaVO"   name="form" property="formularioVO.tipoLinhaVOArray"/>

<link rel="stylesheet" type="text/css" href="../../resources/css/calendar.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script type="text/javascript" src="../../resources/scripts/calendar.js"></script>
<script type="text/javascript" src="../../resources/scripts/validacao.js"></script>
<script type="text/javascript" src="../../resources/scripts/vivoval.js"></script>
<script type="text/javascript" src="../../resources/scripts/RWFFila.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<script>

    function carregaComboCampos() {
        dComboCampos.innerHTML=ifComboCampos.document.body.innerHTML;
        dComboCampos.style.display='';eval(zso)
    }

    function carregaCampos() {
        dvd.innerHTML=iframeValoresDominio.document.body.innerHTML;
        document.forms[0].tipoLinhaSel.value=-1;
        document.forms[0].regionalSel.value= -1;
        submitTipoLinhaUF();
        dvd.style.display='';f.valor.value="";eval(zso)
    }

    function carregaFilas() {
        df.innerHTML=ifrFilas.document.body.innerHTML;
        df.style.display='';
        DoResizeHeaderTableVivo();
        eval(zso)
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

    function retornarAnterior()  {
        dvEncaminhar.style.display='none';
        dvSuspeito.style.display='none';
        ativar_combos();
        document.forms[0].method = "POST";
        document.forms[0].action = "/FrontOfficeWeb/workflow/AtendimentoWorkflow/atendimentoWorkflowVoltar.do";
        document.forms[0].target = "iframeUsuario";
        document.forms[0].submit();
    }

</script>
<table id="tbConfiguracoes" cellpadding="0" cellspacing="0" border="0" style="display:none"></table>
<form action="begin.do" method="post" tagId="formFila" id="formFila">
<html:hidden property="fila" name="form"></html:hidden>
<html:hidden property="inCRI" name="form"></html:hidden>
<html:hidden property="inResp" name="form"></html:hidden>

<html:hidden property="atdFilaPesqVO.WFGrupoVO.idGrupo" name="form"></html:hidden>
<html:hidden property="atdFilaPesqVO.WFEstadoVO.idEstado" name="form"></html:hidden>
<html:hidden property="atdFilaPesqVO.WFSubEstadoVO.idSubEstado" name="form"></html:hidden>

<%--html:hidden property="idUsuario" name="form"></html:hidden>--%>
<html:hidden property="abaSelecionada" name="form"></html:hidden>
<html:hidden property="atdFilaPesqVO.idContato" name="form"></html:hidden>
<input type="hidden" name="somaDias">
<input type="hidden" name="grupoSel" value="-1">
<input type="hidden" name="usuarioSel" value="-1">
<table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
<tr>
    <td>Atualização: </td>
    <td width="5%"><bean:write name="form" property="atualizacao" format="###"/> min</td>
    <td width="10%" align="right">Vermelho: </td>
    <td width="5%"><div id="lblVermelho">0</div></td>
    <td width="10%" align="right">Amarelo: </td>
    <td width="5%"><div id="lblAmarelo">0</div></td>
    <td width="10%" align="right">Normal: </td>
    <td width="5%"><div id="lblNormal">0</div></td>
    <td width="20%" align="right">Registros Retornados: </td>
    <td width="5%"><div id="total"><bean:write name="form" property="atendimentoInformacaoVO.nrRegistros" format="###"/></div></td>
    <td width="20%" align="right">Registros Encontrados: </td>
    <td width="5%"><div id="total2"><bean:write name="form" property="atendimentoInformacaoVO.nrRegistros" format="###"/></div></td>
</tr>
</table>
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
<iframe id="ifrFilas" name="ifrFilas" style="visibility:hidden" width="0" height="0" scrolling="no"></iframe>
<div id="df">
<vivo:tbTable resize="false" selectable="true" layoutWidth="750" layoutHeight="210" tableWidth="750" styleId="tbResultadoPesquisa" sortable="true" onRowClick="linhaSel(this);">
 <vivo:tbHeader>
  <vivo:tbHeaderColumn align="center" width="6%" type="string">&nbsp;</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="7%" type="string">Alerta</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="10%" type="string">Processo</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="23%" type="string">Contato</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="10%" type="string">N. Linha</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="14%" type="string">Estado / Sub-Estado</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="10%" type="string">Operador</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="10%" type="date">Abertura</vivo:tbHeaderColumn>
  <vivo:tbHeaderColumn align="center" width="10%" type="date">Fechamento</vivo:tbHeaderColumn>
 </vivo:tbHeader>
 <vivo:tbRows></vivo:tbRows>
</vivo:tbTable>
</div>
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

<iframe scrolling="yes" style="visibility:hidden;" name="iframeEstado" height="1px" width="1px"></iframe>
<iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
<iframe scrolling="yes" style="visibility:hidden;" name="iframeCampo" height="1px" width="1px"></iframe>
<input type="hidden" name="inMirrorRC" value="1">
</form>


<vivo:quadroFlutuante id="dvTrocar" idIframe="ifrmTrocar" width="780" height="290" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Trocar" onclick="ifrmTrocar.fechar()"/>
<vivo:quadroFlutuante id="dvEncaminhar" scroll="no" idIframe="ifrmEncaminhar" width="780" height="290" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Encaminhar" onclick="ifrmEncaminhar.fechar()"/>
<vivo:quadroFlutuante id="dvSuspeito"   idIframe="ifrmSuspeito"   width="780" height="260" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Suspeito"   onclick="ifrmSuspeito.fechar()"/>
<vivo:quadroFlutuante id="dvAlerta" idIframe="ifrmAlerta" width="443" height="220" spacesTop="0" spacesLeft="175" display="none" url="<%=request.getContextPath()%>" label="Alertas" onclick=""/>
<vivo:quadroFlutuante id="dvArvore" idIframe="ifrmArvore" width="720" height="220" spacesTop="190" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="auto" onclick="ativar_combos();"/>
<script>
f=document.forms["formFila"]; filtro=top.frameCTI.filtro; var controleDetalhe=true;var abaPesquisaBasica=true;var up=true;frmsState=null;pava=true;
//exibicaoAbaPesquisas(<bean:write name="form" property="abaSelecionada"/>);
//top.frameApp.oculta_div();

document.forms[0].target = "ifrFilas";
document.forms[0].action = "/FrontOfficeWeb/workflow/AtendimentoFila/pesquisar.do?inMirrorRC="+document.forms[0].inMirrorRC.value;
document.forms[0].submit();
var inRC = "1";
<%--
loadSubEstados('<%=((AtendimentoFilaForm)form).getEstadoSel()%>');
habilitaData(<bean:write name="form" property="optGrpSel"/>);
f.estadoSel.value='<%=((AtendimentoFilaForm)form).getEstadoSel()%>';
f.subEstadoSel.value='<%=((AtendimentoFilaForm)form).getSubEstadoSel()%>';
<logic:equal parameter="voltar" value="1">
f.elements['atdFilaPesqVO.nrLinha'].focus();
f.elements['atdFilaPesqVO.nrLinha'].blur();
submitPesquisaBasica();
</logic:equal>
--%>
</script>