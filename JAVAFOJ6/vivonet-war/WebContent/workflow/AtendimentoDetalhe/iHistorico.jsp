<!--
Módulo.....: Gestão de Processos
Caso de Uso: Detalhe do processo
-->

<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>


<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheForm"/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<script>
    function mostraComentario(linha){
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    
        //Obtem os dados para obter detalhes
        var idAtendimentoHistorico = linha.cells(0).innerText;
        
        parent.dvComentAcao.style.display = '';
        document.forms["formHistorico"].target = "ifrmComentAcao";
        document.forms["formHistorico"].action = "obterComentario.do?idAtendimentoHistorico="+idAtendimentoHistorico;
        document.forms["formHistorico"].submit();
    }

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>

<form name="formHistorico" id="formHistorico" action="obterComentario.do">

    <vivo:tbTable selectable="true" onRowClick="mostraComentario(this);" layoutWidth="738" layoutHeight="101" tableWidth="738" styleId="tableTitle" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>					
            <vivo:tbHeaderColumn align="center" width="29%" type="string">Motivo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="13%" type="string">Grupo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="14%" type="string">Atendente</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="13%" type="string">Operação</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="12%" type="string">Estado / SubEstado</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="14%" type="date">Data</vivo:tbHeaderColumn>                                                
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate id="atdHistVO" name="form" property="atendimentoDetalheVO.atendimentoVO.atendimentoHistoricoVOArray" indexId="idxHist">
                <vivo:tbRow key="linha">
                    <vivo:tbRowColumn><bean:write name="atdHistVO" property="idAndamento"/></vivo:tbRowColumn>
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
 
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
</form>
