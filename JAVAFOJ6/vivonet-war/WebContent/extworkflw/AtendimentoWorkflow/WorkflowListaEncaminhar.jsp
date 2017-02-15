<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>


<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm" />
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFGrupoVOArray"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="usuarioVIVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.usuarioVIVO" />

<html:hidden name="form" property="inRC"/>

<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>


<vivo:body idTable="tbMain" idDiv="dvMain" height="290" width="770">
    <form action="listaEncaminharGravar.do" name="formEncaminhar" method="post" id="formEncaminhar">
        <%--<html:hidden property="idUsuario" name="form"></html:hidden>--%>
        <html:hidden name="form" property="idAtividade"/>
        <html:hidden name="form" property="inCRI"/>
        <html:hidden name="form" property="fila"/>
        <vivo:tbTable selectable="lista" layoutWidth="740" layoutHeight="110" tableWidth="740" styleId="tableTitle3" sortable="true" onRowClick="">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">N. Processo</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="30%" type="string">Contato</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="20%" type="string">Estado / Sub-Estado</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="20%" type="string">Operador</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Abertura</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Fechamento</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:iterate id="atdVO" name="form" property="atendimentosVO" indexId="idx">
                       <%
                              String idClassRow = "";
                              String textoContato = ((AtendimentoVO)atdVO).getArvoreAtendimentoVO().getDescricaoCompleta().replaceAll("/"," / ");
                        %>
                    <logic:iterate id="alertaVO" name="atdVO" property="alertaVOArray" indexId="idxAlerta">
                        <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
                            <logic:notEmpty name="alertaVO" property="nmCor">
                                <%idClassRow = nmCor.toString(); %>
                            </logic:notEmpty>
                    </logic:iterate>
                    <vivo:tbRow key="atendimento" idClass='<%= idClassRow %>'>
                        <vivo:tbRowColumn><bean:write name="atdVO" property="nrProtocolo" format="000000"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><vivo:hint maxLength="25" spaces="S"><%=textoContato%></vivo:hint></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="atdVO" property="WFEstadoVO.dsEstado"/> / <bean:write name="atdVO" property="WFSubEstadoVO.dsSubEstado"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <logic:notEmpty name="atdVO" property="usuarioVIVO">
                                <bean:write name="atdVO" property="usuarioVIVO.nmNome"/>
                            </logic:notEmpty>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="atdVO" property="dtAbertura"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="atdVO" property="dtFechamento"/></vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
<div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
<script>
var moveToolTip = true;;

xBump=yBump=10;
MSIE=document.all;
NS6=document.getElementById&&!document.all;
if(MSIE||NS6){
    ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
}

function carregaToolTip(content) {
 ttipObj.innerHTML=content;
 ttipObj.style.display='';
}
</script>
        <table width="730" cellspacing="1" cellpadding="1" border="0">
            <tr>
                <td style="height:5px;"></td>
            </tr>
        </table>
        <table width="730" cellspacing="1" cellpadding="1" border="0">
            <tr>
                <td width="50%">
                    <table width="100%">
                         <tr>
                            <td valign="top" width="10%">Grupo:</td>
                            <td valign="top" width="90%">
                                <html:select name="form" property="grupoSel" title="grupoSel" style="width=250px" onchange="submitGrupo();focaTipCampos(this.options[this.selectedIndex].text,this, 135, 48, 35);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 135, 48, 35);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 135, 48, 35);" onblur="HideTip();" onmouseout="HideTip();">
                                    <html:option value="-1" key="grupoSel">&nbsp;</html:option>
                                    <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                                </html:select>
                            </td>
                        </tr>
                       <tr>
                            <td valign="top" width="10%">Motivo:</td>
                            <td valign="top" width="90%">
                                <html:select name="form" property="motivoSel" title="motivoSel" style="width=250px">
                                    <html:option value="-1" key="motivoSel">&nbsp;</html:option>
                                    <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top">Usuário:</td>
                            <td valign="top">
                                <html:select name="form" property="usuarioSel" title="usuarioSel" style="width=250px">
                                    <html:option value="-1" key="usuarioSel">&nbsp;</html:option>
                                    <html:options collection="usuarioVIVO" property="idPessoaUsuario" labelProperty="nmNome" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="50%">
                    <table width="100%">
                        <tr>
                            <td valign="top" width="20%">Comentário:</td>
                            <td valign="top" width="80%"><html:textarea name="form" property="atendimentoWorkflowVO.atendimentoWorkflowComumVO.dsObservacao" rows="3" style="width:280px;height:70px;" onkeyup="checaTextarea(this, 500);"/></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <table width="730" cellspacing="1" cellpadding="1" border="0">
            <tr>
                <td style="height:5px;"></td>
            </tr>
        </table>
        <table border="0" width="100%">
            <tr>
                <td align="right">                
                    <vivo:botao id="btAplicar" width="100px" height="15px" value="Gravar" styleClass="btTemplate" onclick="submitAplicar();"/>                
                </td>
                <td align="left">                
                    <vivo:botao id="btFechar" width="100px" height="15px" value="Fechar" styleClass="btTemplate" onclick="fechar();"/>                
                </td>
            </tr>
        </table>
        <iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
    </form>
</vivo:body>

<script language="javascript">
    var fl = '<bean:write name="form" property="fila"/>';

    function checaTextarea(obj, tamanho){
      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }

    function submitAplicar() {
            if (document.forms[0].elements["motivoSel"].value <= 0) {
                alert("Selecione um Motivo!");
                return;
            }

            if (document.forms[0].elements["grupoSel"].value <= 0) {
                alert("Selecione um Grupo!");
                return;
            }

        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms["formEncaminhar"].method = "POST";
        document.forms["formEncaminhar"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/listaEncaminharGravar.do";
        document.forms["formEncaminhar"].target = "";
        document.forms["formEncaminhar"].submit();
    }

    function fechar()  {
        try {
            parent.ativar_combos();
        } catch (e) {}
        parent.dvEncaminhar.style.display='none';

        if (parent.document.getElementById("ifrmdvEncaminhar")) {
            parent.document.getElementById("ifrmdvEncaminhar").style.display='none';
        }

        document.forms["formEncaminhar"].method = "POST";
        document.forms["formEncaminhar"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/atendimentoWorkflowVoltar.do";
        document.forms["formEncaminhar"].target = "iframeUsuario";
        document.forms["formEncaminhar"].submit();

    }

    function submitGrupo() {
    	
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        var meuCliente = '';
        if (parent.inMeuCliente) {
            meuCliente = '?meuCliente=true';
        }

        document.forms["formEncaminhar"].method = "POST";
        document.forms["formEncaminhar"].action = "/FrontOfficeWeb/extworkflw/AtendimentoWorkflow/obterUsuario.do" + meuCliente;
        document.forms["formEncaminhar"].target = "iframeUsuario";
        document.forms["formEncaminhar"].submit();
    }

    try {
        parent.desativar_combos();
    } catch (e) {}

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>

