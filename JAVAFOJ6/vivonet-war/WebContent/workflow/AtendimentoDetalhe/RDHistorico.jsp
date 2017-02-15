<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoHistoricoVODocument.AtendimentoHistoricoVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="form" scope="request" name="rWFHistoricoForm"/>
<bean:define id="altura" name="form" property="altura"/>
<script language="javascript" type="text/javascript" src="../../resources/scripts/frameweb.js"></script>
<script language="javascript" type="text/javascript">
    var arrayComentarios = new Array();
    <logic:iterate id="atdHistVOComent" name="form" property="historicoVO" indexId="idxHist">
	<%-- arrayComentarios[<%=idxHist %>] = "<bean:write name="atdHistVOComent" property="dsComentario"/>"; --%>
    arrayComentarios[<bean:write name="idxHist"/>] = "<bean:write name="atdHistVOComent" property="dsComentario"/>";
    </logic:iterate>
</script>
<form name="formHistorico" id="formHistorico" action="RDComentarioHistorico.do">
<vivo:tbTable selectable="true" layoutWidth="738" layoutHeight='<%=(String)altura%>' tableWidth="738" styleId="tableTitle" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="21%" type="string">Motivo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="13%" type="string">Grupo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="12%" type="string">Atendente</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="20%" type="string">Operação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="20%" type="string">Estado / Subestado</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="14%" type="data">Data</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows scroll="false">
        <logic:iterate id="atdHistVO" name="form" property="historicoVO" indexId="idxHist">
        <%String idAndamento = ((AtendimentoHistoricoVO)atdHistVO).getIdAndamento();%>
        <vivo:tbRow onRowClick='<%="showComentario(\'"+idxHist+"\');"%>' key="linha">
            <vivo:tbRowColumn><bean:write name="atdHistVO" property="dsMotivo"/></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdHistVO" property="dsGrupo"/></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdHistVO" property="nmNome"/></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdHistVO" property="dsOperacao"/></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdHistVO" property="dsEstado"/> / <bean:write name="atdHistVO" property="dsSubEstado"/></vivo:tbRowColumn>
            <vivo:tbRowColumn><bean:write name="atdHistVO" property="dtTratamento"/></vivo:tbRowColumn>
        </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
<b>Coment&aacute;rios</b><br>
<div id="divComentarios" style="padding:5px;color:#1e68c9;width:754px;height:60px;overflow:auto;border:1px solid #000;background-color:#fff;"></div>
</form>
<script>parent.showIfrInf('Hist');</script>