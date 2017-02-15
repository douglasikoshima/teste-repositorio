<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterTipoCampanhaActionForm" />
<bean:define id="aCampanha"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterTipoCampanhaActionForm.listaCampanha" />

<html>
   <head>
        <title>
            Web Application Page
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    </head>
    <body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center"> 
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr> 
				<td align="center"> <br>
					<table border="0" cellspacing="0" cellpadding="0">
					<tr> 
						<td valign="top"> 
							<vivo:quadro id="qdMain" width="760" height="400" label="&nbsp;">
							<form action="ManterTipoCampanhaAction.do" name="manterTipoCampanhaActionForm" method="post">
							<table border="0"  width="100%" cellpadding="1" cellspacing="10" class="tbl_bgGray" height="100%">
							<tr align="center"> 
								<td colspan="4" align="left"> 
									<table border="0" cellspacing="0" cellpadding="0">
									<tr> 
										<td class="tblEstatica_campoNome" width="40%" align="right">
											<netui:label value="Tipo Campanha:"/>
										</td> 
										<td align="left">
											<netui:textBox dataSource="{actionForm.sgTipoCampanha}" maxlength="50" />
											&nbsp;
											<img src="/FrontOfficeWeb/resources/images/bt_icon_mais.gif"
												 class="button"
											<% if((request.getAttribute("btMostra") != null)&&(request.getAttribute("btMostra").equals("EDITAR"))) {%>
													 id="Editar"
													 onmouseup="document.forms[0].action='ManterTipoCampanhaAction.do?acao=salvar';document.forms[0].submit();"
											<% } else { %>
													 id="Incluir"
													 onmouseup="document.forms[0].action='ManterTipoCampanhaAction.do?acao=incluir';document.forms[0].submit();"
											<% } %>	/>
										</td>
										</td>
									</tr>
									<tr> 
										<td class="tblEstatica_campoNome" width="40%" align="right">
											<netui:label value="DescriÁ„o:"/>
										</td>	
										<td align="left">	
											&nbsp;<netui:textArea dataSource="{actionForm.dsTipoCampanha}" rows="3" cols="40" styleClass="textfield" style="width: 300; color: #006699;"/>
										</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td valign="top" align="center">
									<vivo:tbTable selectable="true" layoutWidth="685" layoutHeight="200" tableWidth="700" styleId="tableTitle" sortable="true">
										<vivo:tbHeader>
											<vivo:tbHeaderColumn align="rigth"  width="80%" type="string">Descri√ß√£o</vivo:tbHeaderColumn>					
											<vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
											<vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
										</vivo:tbHeader>
										<vivo:tbRows>
											<logic:iterate id="item" name="aCampanha" indexId="c">
												<vivo:tbRow key="linha1">
													<vivo:tbRowColumn>
														<bean:write name="item" property="sigla" />
													</vivo:tbRowColumn>
													<vivo:tbRowColumn>
														<img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif"
															 onmouseup="document.forms[0].action='ManterTipoCampanhaAction.do?acao=excluir&codigo=<bean:write name="item" property="codigo" />';document.forms[0].submit();"
															 class="button" />
													</vivo:tbRowColumn>
													<vivo:tbRowColumn>
														<img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"
															 onmouseup="document.forms[0].action='ManterTipoCampanhaAction.do?acao=editar&codigo=<bean:write name="item" property="codigo" />&indice=<%=c%>';document.forms[0].submit();"
															 class="button" />
													</vivo:tbRowColumn>	
												</vivo:tbRow>
											</logic:iterate>
										</vivo:tbRows>	
									</vivo:tbTable>
								</td>           
							</tr>
							</table>
							<netui:hidden dataSource="{actionForm.idTipoCampanha}" />
							</form>
							</vivo:quadro>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</body>	
</html>