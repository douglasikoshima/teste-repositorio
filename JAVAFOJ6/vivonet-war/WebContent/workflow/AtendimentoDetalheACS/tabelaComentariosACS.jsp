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
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
</head>

<body>
<acesso:controlHiddenItem nomeIdentificador="wor_tblcom_verpagina">
    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="760" layoutHeight="133" tableWidth="760" styleId="tableComentariosIFrame" sortable="true">
        <vivo:tbHeader>					
            <vivo:tbHeaderColumn align="left"  width="20%" type="string">Nome Usuário Grupo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="15%" type="string">Login Usuário</vivo:tbHeaderColumn> 
            <vivo:tbHeaderColumn align="center"  width="15%" type="data">Data de Abertura</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="50%" type="string">Observação</vivo:tbHeaderColumn> 					                
        </vivo:tbHeader>    
        <logic:notEmpty name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoDetalheACSForm.atendimentoDetalheACS.atendimentoDetalheACSComentariosVOArray">                                            
            <vivo:tbRows>
                <logic:iterate name="atdDetalhe" property="atendimentoDetalheACSComentariosVOArray" id="comentarios">
                        <vivo:tbRow key="linha1">
                            <vivo:tbRowColumn><bean:write name="comentarios" property="nmUsuarioGrupo"/></vivo:tbRowColumn>                      
                            <vivo:tbRowColumn><bean:write name="comentarios" property="nmLoginUsuario"/></vivo:tbRowColumn> 
                            <vivo:tbRowColumn><bean:write name="comentarios" property="dtAbertura" /></vivo:tbRowColumn>                      
                            <vivo:tbRowColumn><bean:write name="comentarios" property="observacao"/></vivo:tbRowColumn>                                      
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
