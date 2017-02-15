<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="servicoForm"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
	<acesso:controlHiddenItem nomeIdentificador="cli_sepp_verpagina">
        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="735" layoutHeight="360" tableWidth="735" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="247" type="string">Código do Serviço</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="68" type="string">Descrição do Serviço</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="121" type="string">Descrição do Estado</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="121" type="string">Período de Vigência</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:empty name="error">
                <logic:iterate id="item" name="form" property="servicoVO.servicosItemArray">                
                    <vivo:tbRow key="Linha">
                        <vivo:tbRowColumn><bean:write name="item" property="codigo"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="item" property="descricao"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="item" property="status"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="item" property="validade"/></vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
                </logic:empty>
                <logic:notEmpty name="error">
                    <vivo:tbRow key="Linha">
                        <td colspan="4"><bean:write name="error"/></td>
                    </vivo:tbRow>
                </logic:notEmpty>
            </vivo:tbRows>                            
        </vivo:tbTable>
	</acesso:controlHiddenItem>
    </body>
</html>
