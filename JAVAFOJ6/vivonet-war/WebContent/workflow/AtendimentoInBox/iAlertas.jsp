<!--
Módulo.....: Gestão de Processos
Caso de Uso: Atendimento InBox
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/25 18:27:57 $
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
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxForm"/>

<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
<acesso:controlHiddenItem nomeIdentificador="wor_alaibx_verpagina">
    <form action="begin.do" tagId="formAlerta" id="formAlerta" method="post">
        <vivo:tbTable selectable="false" layoutWidth="695" layoutHeight="200" tableWidth="695" styleId="tableTitle3" sortable="false" onRowClick="return false;">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="5%" type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Id</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">Criticidade</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="30%" type="string">Descrição</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="45%" type="string">Mensagem</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:iterate id="alertaVO" name="form" property="alertasVO" indexId="idx">
                    <bean:define name="alertaVO" property="nmIcone" id="nmIcone"/>

                    <% 
                    String pathIcone = "/FrontOfficeWeb/resources/images/" + nmIcone.toString();
                    String idClassRow = "";
                    %>

                    <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
                    <logic:notEmpty name="alertaVO" property="nmCor">
                        <%idClassRow = nmCor.toString(); %>    
                    </logic:notEmpty>

                    <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
                        <vivo:tbRowColumn>
                            <html:image src="<%=pathIcone%>"></html:image>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="alertaVO" property="idAlerta" format=""/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="alertaVO" property="nrCriticidade"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="alertaVO" property="dsAlerta"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="alertaVO" property="dsMensagem"/></vivo:tbRowColumn>
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
