<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>
<bean:define id="atdVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm.atendimentoDetalheVO.atendimentoVO"/>

<form action="processoAction.do" method="post">
    <table width="100%">
        <tr valign="top">
            <td width="15%"><b>Nome do Contato:</b></td>
            <td width="35%" style="color:#006699;"><bean:write name="atdVO" property="nmContato"/></td>
            <td width="15%"><b>Tel. do Contato:</b></td>
            <td width="35%" style="color:#006699;"><bean:write name="atdVO" property="nrTelefone"/></td>
        </tr>
        <tr valign="top">
            <td><b>Conta</b></td>
            <td style="color:#006699;"><bean:write name="form" property="contaFormatada"/></td>
            <td><b>Linha</b></td>
            <td style="color:#006699;"><bean:write name="form" property="linhaFormatada"/></td>
        </tr>
    </table>
    <vivo:tbTable selectable="false" layoutWidth="725" layoutHeight="68" tableWidth="725" styleId="tbTipoRetorno" sortable="false" onRowClick="">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="40%" type="string">Tipo de Retorno</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="60%" type="string">Retorno</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate id="atdTposComVO" name="atdVO" property="WFAtendimentoContatoComunicVOArray" indexId="idxConCom">
                <vivo:tbRow key="linha" zebrar="S">
                    <vivo:tbRowColumn><bean:write name="atdTposComVO" property="dsTipoComunicacao"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="atdTposComVO" property="dsComunicacao"/></vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
</form>
   
<script>
    document.body.style.backgroundColor = '#ededed';
</script>
