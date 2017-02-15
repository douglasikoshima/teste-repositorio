<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm"/>
<bean:define id="Motivos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm.correspDevolvidaVO.motivoDevolucaoVOArray"/>
<bean:define id="Tipos"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm.correspDevolvidaVO.tipoCorrespondenciaVOArray"/>
<bean:define id="Status"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm.correspDevolvidaVO.statusCorrespondenciaArray"/>

<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
</head>

<body>
<acesso:controlHiddenItem nomeIdentificador="cli_tcr_verpagina">
    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="760" layoutHeight="214" tableWidth="760" styleId="tableTitle" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="left" width="25%" type="string">Nome</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="13%" type="string">Linha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="13%" type="string">Conta</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="14%" type="string">Tipo da Correspondência</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="14%" type="string">Motivo da Devolução</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="8%" type="date">Data da Devolução</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="8%" type="date">Data do Registro</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="5%" type="string">&nbsp</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate name="Form" property="correspDevolvidaVO.listaCorrespDevolvidaArray" indexId="idx" id="listaCorrespDevolvidaArray">
                <vivo:tbRow key="Linha">
                    <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="nmPessoa"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="nrLinha"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="nrConta"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="dsTipoCorrespondencia"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="dsMotivoDevolucao"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="dtDevolucao"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="dtRegistro"/><html:hidden name="listaCorrespDevolvidaArray" property="idCorrespondencia" styleId="id"/></vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <acesso:controlHiddenItem nomeIdentificador="cli_tcr_alterar">
                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onClick="parent.validaPesquisa(<%=idx%>)" alt="Alterar informações referentes à correspondência devolvida."/>
                        </acesso:controlHiddenItem>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>                                                                                                                                     
        </vivo:tbRows>
    </vivo:tbTable>

    <div><img width="1" height="5" src="/FrontOfficeWeb/resources/images/transp.gif"></div>    
    
    <table width="776" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
        <tr>
            <td width="100"><b>Legendas:</b></td>
            <td>
                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar informações referentes à correspondência devolvida.
            </td>
        </tr>
    </table>
    
    <script>
        document.body.style.backgroundColor = '#e3ecf4';
    </script>    
    <script for="window" event="onload">
        parent.oculta_div();
    </script>
</acesso:controlHiddenItem>
</body>