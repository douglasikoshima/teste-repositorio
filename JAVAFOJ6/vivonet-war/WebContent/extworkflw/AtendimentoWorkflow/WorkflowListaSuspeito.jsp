<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento Workflow
-->
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
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>

<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<vivo:body idTable="tbMain" idDiv="dvMain" height="240" width="770">
<form action="listaSuspeitoGravar.do" name="atendimentoForm" method="post" tagId="formSuspeito" id="formSuspeito">
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
            <%String textoContato = "";%>
            <logic:iterate id="atdVO" name="form" property="atendimentosVO" indexId="idx">
            <%String idClassRow = "";
              textoContato = ((AtendimentoVO)atdVO).getArvoreAtendimentoVO().getDescricaoCompleta().replaceAll("/"," / ");%>
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
    <table width="730" cellspacing="1" cellpadding="1" border="0">
        <tr>
            <td width="50%">
            <table width="100%">
                <tr>
                    <td width="10%">Motivo:</td>
                    <td width="90%">
                    <html:select name="form" property="motivoSel" title="motivoSel" style="width=200px">
                        <html:option value="-1">-- Selecione --</html:option>
                        <html:options collection="motivosVO" property="idMotivo" labelProperty="dsMotivo"/>
                    </html:select>
                    </td>
                </tr>
                <tr>
            </table>
            </td>
            <td width="50%">
            <table width="100%">
                <tr>
                    <td width="20%">Comentário:</td>
                    <td width="80%">
                        <html:textarea name="form" property="comentario" rows="2" style="width=260" onkeyup="checaTextarea(this, 500);"/>
                    </td>
                </tr>
            </table>
            </td>
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
</form>
<script type="text/javascript">
    <!--
        var fl = '<bean:write name="form" property="fila"/>';
        function checaTextarea(obj, tamanho){
            obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
        }

        function submitAplicar() {
            if(document.forms[0].elements["motivoSel"].value <= 0) {
                alert("Selecione um Motivo!");
                return;
            }

            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

            document.forms["formSuspeito"].method = "POST";
            document.forms["formSuspeito"].action = "<%=request.getContextPath()%>/extworkflw/AtendimentoWorkflow/listaSuspeitoGravar.do";
            document.forms["formSuspeito"].target = "";
            document.forms["formSuspeito"].submit();
        }

        function fechar(){
            try {
                parent.ativar_combos();
            } catch (e) {}
            parent.dvSuspeito.style.display = 'none';
            if (parent.document.getElementById("ifrmdvSuspeito")) {
                parent.document.getElementById("ifrmdvSuspeito").style.display='none';
            }
            document.forms["formSuspeito"].method = "POST";
            document.forms["formSuspeito"].action = "<%=request.getContextPath()%>/extworkflw/AtendimentoWorkflow/atendimentoWorkflowVoltar.do";
            document.forms["formSuspeito"].target = "iframeUsuario";
            document.forms["formSuspeito"].submit();

        }

        try {
            parent.desativar_combos();
        } catch (e) {}

        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    -->
</script>
</vivo:body>