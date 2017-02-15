<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterRespostaCampanhaActionForm" />
<bean:define id="aPergunta" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterRespostaCampanhaActionForm.listaPergunta" />
<bean:define id="pageFlow" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"/>
<html>
    <head>
        <title></title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link href="<%=request.getContextPath()%>/resources/css/esacStyle.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"  charset="ISO-8859-1"></script>
	<script language="javascript"   charset="ISO-8859-1">
               function incluir()
			   {
					 if (trimTextArea(document.manterRespostaCampanhaActionForm.sDsScriptResposta.value) =="")
                     {
						  alert("Favor preencher o campo Script da Resposta!");
						  document.manterRespostaCampanhaActionForm.sDsScriptResposta.select();
					  }
                      else if (trimTextArea(document.manterRespostaCampanhaActionForm.sDsResposta.value) =="")
                      {
						  alert("Favor preencher o campo Observações!");
						  document.manterRespostaCampanhaActionForm.sDsResposta.select();
					  }
					  else
					  {  
                            top.frameApp.mostrar_div();
                            document.manterRespostaCampanhaActionForm.target = "";
							document.manterRespostaCampanhaActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterResposta/incluirResposta.do";
							document.manterRespostaCampanhaActionForm.submit();
                            
					  }		
			   }
               
               function alterar()
			   {
					 if (trimTextArea(document.manterRespostaCampanhaActionForm.sDsScriptResposta.value) =="")
                     {
						  alert("Favor preencher o campo Script da Resposta!");
						  document.manterRespostaCampanhaActionForm.sDsScriptResposta.select();
					  }
                      else if (trimTextArea(document.manterRespostaCampanhaActionForm.sDsResposta.value) =="")
                      {
						  alert("Favor preencher o campo Observações!");
						  document.manterRespostaCampanhaActionForm.sDsResposta.select();
					  }
					  else
					  {  
                        var objForm = document.manterRespostaCampanhaActionForm;
                        var um = "";
                        var dois = objForm.elements["wlw-radio_button_group_key:{actionForm.IDisponibilidade}"][0].checked;

                        if( objForm.elements["wlw-radio_button_group_key:{actionForm.iEncerramento}"] != null ){
                            um = objForm.elements["wlw-radio_button_group_key:{actionForm.iEncerramento}"][0].checked;
                        } else {
                                  um = "false";
                        }

                        top.frameApp.mostrar_div();
                        document.manterRespostaCampanhaActionForm.target = "";
                        document.manterRespostaCampanhaActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterResposta/alterarResposta.do?encerra=" + um + "&" + "disponivel=" + dois;
                        document.manterRespostaCampanhaActionForm.submit();
					  }		
			   }               
			   function trimTextArea(valor) 
			   {
				
				  while ((valor.substring(0,1) == ' ') || (valor.substring(0,1) == '\n') || (valor.substring(0,1) == '\r'))
				  {
					valor = valor.substring(1,valor.length);
				  }
				  while ((valor.substring(valor.length-1,valor.length) == ' ') || (valor.substring(valor.length-1,valor.length) == '\n') || (valor.substring(valor.length-1,valor.length) == '\r'))
				  {
					valor = valor.substring(0,valor.length-1);
				  }
				  return valor;
				}

                function checaEncerramentoI()
                {
                    var objForm = document.manterRespostaCampanhaActionForm;
                    
                    if( objForm.elements["wlw-radio_button_group_key:{actionForm.iEncerramento}"] && 
                    objForm.perguntaSelecionada )
                    {                    
                        if ( objForm.elements["wlw-radio_button_group_key:{actionForm.iEncerramento}"][0].checked )
                        {
                            if ( objForm.perguntaSelecionada.selectedIndex > 0 )
                            {
                                if ( confirm("Para utilizar Encerramento Imediato,\na opção Avança para pergunta,\nficara desabilitada.\nConfirma?") )
                                {
                                    objForm.perguntaSelecionada.selectedIndex = 0;
                                }
                                else
                                {
                                    objForm.elements["wlw-radio_button_group_key:{actionForm.iEncerramento}"][1].checked = true;
                                }
                            }
                        }                    
                    }
                    
                    
                }
        </script>
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();
        checaEncerramentoI();
    </script>
    
    <vivo:alert atributo="msgAdminResposta" scope="request" />
    </head>
    <acesso:controlHiddenItem nomeIdentificador="cam_arca_verpagina">
    <form action="ProcessaRespostaCampanhaAction.do" name="manterRespostaCampanhaActionForm" method="post">
        <table  bgcolor="#ededed" border="0" width="100%" cellpadding="1" cellspacing="10" height="100%">
            <tr>
                <td valign="top">
                <table align="center">
                <tr>
                <td valign="top">
                <table border="0" cellspacing="1" cellpadding="2" width="100%">
                    <tr>
                        <td width="100"  valign="top" height="35"><b><netui:label value="Pergunta:"/></td>
                        <td valign="top"  colspan="3"><bean:write name="Form" property="sDsPergunta" /></td>
                    </tr>
                    <tr>
                        <td valign="top"><b><netui:label value="Canal de Atendimento:"/></td>
                        <td colspan="3"><bean:write name="Form" property="dsCanalCampanha" /></td>
                    </tr>
                    <tr>
                        <td valign="middle"><b><netui:label value="Script da Resposta:"/></td>
                        <td colspan="3">
                        <html:textarea name="Form" tabindex="1" property="sDsScriptResposta" rows="3" cols="50" style="font: arial;color: #006699;" onkeyup="checaTextarea(this,90);"/>
                        </td>
                    </tr>
                    <logic:equal value="RAD" name="pageFlow" property="sgTipoApresentacaoPergunta">
                    <tr>
                        <td ><b><netui:label value="Avança para pergunta:"/></td>
                        <td colspan="3">
                        <html:select name="Form" tabindex="2" property="perguntaSelecionada" size="1" style="width=420px;height=10px" onchange="checaEncerramentoI();">
                            <html:option value=""></html:option>
                            <html:options collection="aPergunta" property="idPergunta" labelProperty="dsScriptPergunta"/> 
                        </html:select>
                        </td>
                    </tr>
                    </logic:equal> 
                    <tr>
                        <td ><b><netui:label value="Resposta Ativa?"/>&nbsp;
                        </td>
                        <td>
                        <logic:equal name="Form" property="IDisponibilidade" value="1">
                            <netui:radioButtonGroup dataSource="{actionForm.IDisponibilidade}">
                                <netui:radioButtonOption tabindex="3" value="1" style="border:none;background-color:#ededed;">
                                    <netui:label value="Sim"/>
                                </netui:radioButtonOption>
                                <netui:radioButtonOption tabindex="4" value="0" style="border:none;background-color:#ededed;">
                                    <netui:label value="Não" style="border-style:none;"/>
                                </netui:radioButtonOption>
                            </netui:radioButtonGroup>
                        </logic:equal>
                        <logic:equal name="Form" property="IDisponibilidade" value="0">
                            <netui:radioButtonGroup dataSource="{actionForm.IDisponibilidade}">
                                <netui:radioButtonOption tabindex="3" value="0" style="border:none;background-color:#ededed;">
                                    <netui:label value="Sim"/>
                                </netui:radioButtonOption>
                                <netui:radioButtonOption tabindex="4" value="1" style="border:none;background-color:#ededed;">
                                    <netui:label value="Não" style="border-style:none;"/>
                                </netui:radioButtonOption>
                            </netui:radioButtonGroup>
                        </logic:equal>
                        </td>
                    </tr>                
                    <logic:equal value="RAD" name="pageFlow" property="sgTipoApresentacaoPergunta">
						<tr>
							<td valign="middle" ><b><netui:label value="Encerramento imediato?"/>&nbsp;
							</td>
							<td>
                        <logic:equal name="Form" property="iEncerramento" value="0">
							<netui:radioButtonGroup dataSource="{actionForm.iEncerramento}" >
								<netui:radioButtonOption tabindex="5" value="1" style="border:none;background-color:#ededed;" onClick="checaEncerramentoI();">
									<netui:label value="Sim"/>
								</netui:radioButtonOption>
								<netui:radioButtonOption tabindex="6" value="0" style="border:none;background-color:#ededed;">
									<netui:label value="Não"/>
								</netui:radioButtonOption>
							</netui:radioButtonGroup>
                        </logic:equal>
                        <logic:equal name="Form" property="iEncerramento" value="1">
							<netui:radioButtonGroup dataSource="{actionForm.iEncerramento}" >
								<netui:radioButtonOption tabindex="5" value="0" style="border:none;background-color:#ededed;" onClick="checaEncerramentoI();">
									<netui:label value="Sim"/>
								</netui:radioButtonOption>
								<netui:radioButtonOption tabindex="6" value="1" style="border:none;background-color:#ededed;">
									<netui:label value="Não"/>
								</netui:radioButtonOption>
							</netui:radioButtonGroup>
                        </logic:equal>
							</td>
						</tr>
					</logic:equal>
                    <tr>
                        <td valign="middle" ><b><netui:label value="Observações:"/>&nbsp;
                        </td>
                        <td colspan="3">
                        <html:textarea name="Form" tabindex="7" property="sDsResposta" rows="3" cols="50" style="font: arial;color: #006699;" onkeyup="checaTextarea(this,255);"/>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
            <tr>
                <td valign="top">
                <table width="100%" border="0" cellspacing="1" cellpadding="0">
                    <tr>
                        <logic:equal name="Form" property="acao" value="0">
                        <td align="right">
                        <acesso:controlHiddenItem nomeIdentificador="cam_arca_incluir">
                        <img tabindex="8" src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" border="0" onClick="incluir();" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif'" style="border:none;cursor:hand;"/>
                        </acesso:controlHiddenItem> &nbsp;
                        </td>
                        </logic:equal>
                        <logic:equal name="Form" property="acao" value="1">
                        <td align="right">
                        <img tabindex="8" src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" border="0" onClick="alterar();" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif'" style="border:none;cursor:hand;"/>
                        &nbsp;
                        </td>
                        </logic:equal>
                    </tr>
                </table>
                </td>
                </tr>
                </table>
                </td>
               
            </tr>
        </table>
        <html:hidden name="Form" property="idResposta"/>
        <html:hidden name="Form" property="iIdPergunta"/>
        <html:hidden name="Form" property="sDsPergunta"/>
        <html:hidden name="Form" property="dsCanalCampanha"/>
    </form>
    </acesso:controlHiddenItem>
</html>