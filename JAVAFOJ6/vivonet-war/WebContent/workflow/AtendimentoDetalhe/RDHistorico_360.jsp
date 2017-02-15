<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoHistoricoVODocument.AtendimentoHistoricoVO"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="form" scope="request" name="rWFHistoricoForm"/>
<bean:define id="altura" name="form" property="altura"/>
<bean:define id="estadosVO" name="form" property="estadoVO.WFEstadoVOArray"/>

<html>
<head>
    <title>Histórico de Processo</title>
	<style type="text/css">
	    div.historico_comentarios {
	        padding:5px;
	        color:#1e68c9;
	        width:760px;
	        height:40px;
	        overflow:auto;
	        border:1px solid #dfe6f0;
	        background-color:#fff;
	        clear:both;
	        display:block;
	    }
	    div#historico_holder {
	        padding-top:5px;
	        overflow:hidden;
	        height:200px;
	    }
	</style>
</head>
<body>
<div id="historico_holder">

    <table width="780">
        <tr>
            <td valign="top">Estado:</td>
            <td valign="top">
                <html:select name="form"
                             property="estadoVO"
                             title="estadoSel"
                             style="width:240px"
                             onchange="loadSubEstados(this.value);">
                    <html:option value="-1">&nbsp;</html:option>
                    <html:options collection="estadosVO" property="idEstado" labelProperty="dsEstado" />
                </html:select>
            </td>
            <td valign="top" nowrap>Subestado:</td>
            <td id="tdSubEstados" valign="top">
                <select name="subEstadoSel" style="width:240px" title="subEstadoSel">
                    <option value="-1">&nbsp;</option>
                </select>
            </td>
            <td valign="top" align="right">
                <input type="button" class="input_small" value="Pesquisar" onmouseup="submitPesquisarHist()" />
            </td>
        </tr>
    </table>

    <vivo:tbTable selectable="true"
                  layoutWidth="760"
                  layoutHeight="100"
                  tableWidth="760"
                  styleId="tableHistoricoProcesso"
                  sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="21%" type="string">Motivo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="13%" type="string">Grupo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="12%" type="string">Atendente</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Opera&ccedil;&atilde;o</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Estado / Subestado</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="14%" type="data">Data</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows scroll="false">
            <logic:iterate id="atdHistVO" name="form" property="historicoVO" indexId="idxHist">
            <%String idAndamento = ((AtendimentoHistoricoVO)atdHistVO).getIdAndamento();%>
            <vivo:tbRow onRowClick='<%="$(\'divComentarios\').update(arrayComentarios[\'"+idxHist+"\']);"%>' key="linha">
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
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    <div style="clear:both;display:block;padding-top:5px;">
        <b>Coment&aacute;rios</b><br>
        <div id="divComentarios" class="historico_comentarios"></div>
    </div>
</div>

<script type="text/javascript">
    <logic:iterate id="atdHistVOComent" name="form" property="historicoVO" indexId="idxHist">
	<%-- arrayComentarios[<%=idxHist %>] = "<bean:write name="atdHistVOComent" property="dsComentario"/>"; --%>
    arrayComentarios[<bean:write name="idxHist" />] = "<bean:write name="atdHistVOComent" property="dsComentario"/>";
    </logic:iterate>
</script>

</body>
</html>