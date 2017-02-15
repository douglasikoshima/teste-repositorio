<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<acesso:controlHiddenItem nomeIdentificador="ntura_his_verpagina">
<bean:define id="notasForm" name="notasForm" scope="request" />
<bean:define id="notaHistVO" name="notasForm" property="notaHistVO"/>
<bean:define id="notaVO" name="notasForm" property="notaVO"/>
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<%
 int i=0;
%>
	<vivo:tbTable selectable="true" layoutWidth="570" layoutHeight="240" tableWidth="570" styleId="tableTitle" sortable="true">
		<vivo:tbHeader>
			<vivo:tbHeaderColumn align="center" width="30" type="string">Motivo</vivo:tbHeaderColumn>
			<vivo:tbHeaderColumn align="center" width="15" type="string">Usuário</vivo:tbHeaderColumn>
			<vivo:tbHeaderColumn align="center" width="20" type="string">Operação</vivo:tbHeaderColumn>
			<vivo:tbHeaderColumn align="center" width="15" type="string">Data</vivo:tbHeaderColumn>
			<vivo:tbHeaderColumn align="center" width="20" type="string">Comentário</vivo:tbHeaderColumn>
		</vivo:tbHeader>
		<vivo:tbRows>
			<logic:iterate name="notasForm" property="notaHistVO" id="hist">
            <%i++;%>
				<vivo:tbRow key="linha">
					<vivo:tbRowColumn>
                        <vivo:hint maxLength="10"><bean:write name="hist" property="dsMotivo"/></vivo:hint>
                    </vivo:tbRowColumn>
					<vivo:tbRowColumn>
						<vivo:hint maxLength="15"><bean:write name="hist" property="nmPessoa"/></vivo:hint>
					</vivo:tbRowColumn>
					<vivo:tbRowColumn>
						<vivo:hint maxLength="20"><bean:write name="hist" property="dsOperacao"/></vivo:hint>
					</vivo:tbRowColumn>
					<vivo:tbRowColumn>
						<vivo:hint maxLength="20"><bean:write name="hist" property="dtOperacao"/></vivo:hint>
					</vivo:tbRowColumn>
					<vivo:tbRowColumn>
						<vivo:hint maxLength="20"><bean:write name="hist" property="dsObservacao"/></vivo:hint>
					</vivo:tbRowColumn>
				</vivo:tbRow>
			</logic:iterate>
		</vivo:tbRows>
	</vivo:tbTable>
<script>
<%
    String dtAberturaFim = ((WFAtdNotaVO)notaVO).getDtAberturaFim();  
    boolean disabled = (dtAberturaFim != null && !dtAberturaFim.equals(ConstantesCRM.SVAZIO))?true:false;
    if(i==3 || disabled)
    {%>
        parent.document.forms[0]['acaoSel'].value=-1;
    <%}
%>
        if( top.frameApp.dvAnimarAguarde != null ){
            top.frameApp.stopAnimation();
        }
parent.screen_unblock();
</script>
</acesso:controlHiddenItem>