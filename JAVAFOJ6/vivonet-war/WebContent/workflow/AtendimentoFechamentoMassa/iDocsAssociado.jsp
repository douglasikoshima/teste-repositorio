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

<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoFechamentoMassaForm"/>

<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
<acesso:controlHiddenItem nomeIdentificador="wor_doass_verpagina">
    <form action="begin" name="atendimentoFechamentoMassaForm" method="post" tagId="formDocAssociado" id="formDocAssociado">
        <vivo:tbTable selectable="true" layoutWidth="730" layoutHeight="175" tableWidth="730" styleId="tableTitle3" sortable="false" onRowClick="return false;">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Tipo</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="15%" type="string">No.</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="data">Abertura</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="data">Fechamento</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="data">Previsão</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Estado</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left"   width="15%" type="string">Comentários</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left"   width="15%" type="string">Resposta</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%"  type="string">QPV</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows scroll="false">
                <logic:iterate id="docAssociadoVO" name="form" property="atdWFTecnicoDocsVO" indexId="idx">
                    <vivo:tbRow key="linha">
                        <vivo:tbRowColumn><bean:write name="docAssociadoVO" property="dsTipoDocumentoProcesso" format=""/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="docAssociadoVO" property="nrDocumento"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="docAssociadoVO" property="dtAbertura"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="docAssociadoVO" property="dtFechamento"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="docAssociadoVO" property="dtEstimadaFechamento"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="docAssociadoVO" property="dsEstadoDocumentoProcesso"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><vivo:hint maxLength="15" spaces="true"><bean:write name="docAssociadoVO" property="dsDocumento"/></vivo:hint></vivo:tbRowColumn>
                        <vivo:tbRowColumn><vivo:hint maxLength="15" spaces="true"><bean:write name="docAssociadoVO" property="dsResposta"/></vivo:hint></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="docAssociadoVO" property="qtdProcessosVinculados"/></vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
    </form>
   
</acesso:controlHiddenItem>
    <script language="javascript">
        
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    
    </script>

</body>

