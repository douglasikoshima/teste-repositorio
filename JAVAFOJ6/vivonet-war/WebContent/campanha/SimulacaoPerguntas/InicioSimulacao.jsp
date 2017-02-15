<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="inicioSimuladorActionForm" />
<bean:define id="aCanal"		name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="inicioSimuladorActionForm.listaCanal" />
<html>
   <head>
        <title>
            Web Application Page
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
		<script language="javascript">				
		function submit()
		{
            //canalSelecionado
            var objForm = document.forms.inicioSimuladorActionForm;
            if(objForm.canalSelecionado.selectedIndex == 0)
            {
                alert("Canal Atendimento não selecionado!");
                //return false;
            }
            else
            {            
                objForm.action = "<%=request.getContextPath()%>/campanha/SimulacaoPerguntas/ExecSimulacaoAction.do";
                objForm.submit();
                parent.mostrar_div();
            }
		}
		</script>
        
    <SCRIPT FOR=window EVENT=onload>
        parent.oculta_div();
    </script>
        
    </head>
    <acesso:controlHiddenItem nomeIdentificador="cam_isi_verpagina">
   
	 <table height="100%" bgcolor="#ededed"  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td valign="top" align="center"> 
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr> 
				<td>
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
					<tr> 
						<td valign="top" >
							<form action="InicioSimuladorAction.do" name="inicioSimuladorActionForm" method="post">
							<table border="0"  width="100%" cellpadding="1" cellspacing="5" height="100%">
							<tr class="corpo"> 
								<td align="center">
									<table border="0" cellspacing="0" cellpadding="3" >
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" valign="top">
                                                <netui:label value="Canal Atendimento:"/></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td valign="top">
                                                    <html:select name="Form" property="canalSelecionado" size="1" style="width=400px;height=10px">
                                                        <option value="" selected></option>
                                                        <html:options collection="aCanal" property="codigo" labelProperty="descricao"/> 
                                                    </html:select>	
												</td>
												<td>
                                                    <acesso:controlHiddenItem nomeIdentificador="cam_isi_ok">
                                                      <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                                                      	   class="button"
                                                      	   rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"
                                                      	   onmouseup="submit();"/>														
													</acesso:controlHiddenItem>
                                                </td>
                                           	</tr>	
                                   </table>
								</td>
							</tr>	
							</table>                            
									<iframe scrolling="yes" style="visibility:hidden;" name="iframe" height="1px" width="1px"></iframe>
									<html:hidden name="Form" property="sgCanal" styleClass="input" />
									</form>
						</td>
					</tr>
					</table>
				</td>
		  </tr>
		  </table>
	<!--Quadro Flutuante-->
    <vivo:quadroFlutuante id="dvNrProcesso" idIframe="ifrmNrProcesso" 
        height="400" width="200" spacesTop="0" spacesLeft="0" scroll="true" 
        display="none" url="<%=request.getContextPath()%>" label="Questionário"/>          

       
    </acesso:controlHiddenItem>
</netui>