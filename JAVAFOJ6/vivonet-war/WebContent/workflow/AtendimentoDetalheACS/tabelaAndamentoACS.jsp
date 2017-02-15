<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento Detalhe ACS
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/17 17:18:00 $
-->
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
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheACSForm"/>
<bean:define id="atdDetalhe" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheACSForm.atendimentoDetalheACS"/>

<html>
    <head>
        <title>
            Web Application Page
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="wor_tbland_verpagina">
        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="760" layoutHeight="133" tableWidth="760" styleId="tableACSIFrame" sortable="true">
            <vivo:tbHeader>					
                <vivo:tbHeaderColumn align="left"  width="30%" type="string">Nome Usu&aacute;rio Grupo</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="55%" type="string">Nome Usu&aacute;rio</vivo:tbHeaderColumn> 
                <vivo:tbHeaderColumn align="left" width="15%" type="string">Data Andamento</vivo:tbHeaderColumn>					                
            </vivo:tbHeader>
            <logic:notEmpty name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheACSForm.atendimentoDetalheACS.atendimentoDetalheACSAndamentoVOArray">                                            
                <vivo:tbRows>
                    <logic:iterate name="atdDetalhe" property="atendimentoDetalheACSAndamentoVOArray" id="andamento">
                            <vivo:tbRow key="linha">
                                <vivo:tbRowColumn><bean:write name="andamento" property="nmUsuarioGrupo"/></vivo:tbRowColumn>                      
                                <vivo:tbRowColumn><bean:write name="andamento" property="nmUsuario"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="andamento" property="dtAndamento"/></vivo:tbRowColumn>                                       
                            </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </logic:notEmpty>
        </vivo:tbTable>
        
        <script>
            document.body.style.backgroundColor = '#ededed';
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
   
        </acesso:controlHiddenItem>
    </body>
</html>
