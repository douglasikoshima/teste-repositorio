<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<bean:define id="form" scope="request" name="rWFContatoForm"/>
<bean:define id="RDContatoVO" name="form" property="rDContato"/>
<script type="text/javascript" src="../../resources/scripts/frameweb.js"></script>
<table width="100%" cellpadding="0" cellspacing="0">
    <tr valign="top">
        <td nowrap><b>Nome do Contato: </b><span style="color:#006699;"><bean:write name="RDContatoVO" property="nmContato"/></span></td>
        <td nowrap><b>Tel. do Contato: </b><span style="color:#006699;"><bean:write name="RDContatoVO" property="nrTelefone"/></span></td>
        <td nowrap><b>Conta: </b><span style="color:#006699;"><bean:write name="RDContatoVO" property="nrConta"/> - <bean:write name="RDContatoVO" property="nrDigitoConta"/></span></td>
        <td nowrap><b>Linha: </b><span style="color:#006699;">(<bean:write name="RDContatoVO" property="cdAreaRegistro"/>)<bean:write name="RDContatoVO" property="nrLinha"/></span></td>
        <logic:notEqual name="RDContatoVO" property="nmFalandoCom" value="">
        <td nowrap><b>Falando com: </b><span title="<bean:write name="RDContatoVO" property="nmFalandoCom"/>" style="color:#006699;"><bean:write name="RDContatoVO" property="nmFalandoCom"/></span></td>
        </logic:notEqual>
    </tr>
</table>
<vivo:tbTable selectable="false" layoutWidth="725" layoutHeight="38" tableWidth="725" styleId="tbTipoRetorno" sortable="false" onRowClick="">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="40%" type="string">Tipo de Retorno</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="60%" type="string">Retorno</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="atdTposComVO" name="RDContatoVO" property="WFAtendimentoContatoComunicVOArray" indexId="idxConCom">
        <vivo:tbRow key="linha" zebrar="S">
        <vivo:tbRowColumn><bean:write name="atdTposComVO" property="dsTipoComunicacao"/></vivo:tbRowColumn>
        <vivo:tbRowColumn><bean:write name="atdTposComVO" property="dsComunicacao"/></vivo:tbRowColumn>
        </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>

<a href="abaVoltarSolicitanteEx.do" target="ifrFProc" id="abaVoltar"></a>
<%--
</acesso:controlHiddenItem>
--%>
<script>
    DoResizeHeaderTableVivo();
    document.body.style.backgroundColor='#ededed';
    parent.showIfrS('Cont');
    abaVoltar.click();
</script>