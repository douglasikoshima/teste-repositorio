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

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="adminPerguntaActionForm" />
<bean:define id="aCanalDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="adminPerguntaActionForm.listaCanalDisp" />
<bean:define id="aCanalUtil" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="adminPerguntaActionForm.listaCanalUtil" />
<bean:define id="aPergunta" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="adminPerguntaActionForm.listaPergunta" />
<bean:define id="aApresentacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="adminPerguntaActionForm.listaApresentacao" />
<bean:define id="pageFlow" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" />

<html>
    <head>
        <title></title>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" charset="ISO-8859-1"></script>
		<script language="javascript"  charset="ISO-8859-1">
        function TransfereCanal(listaOrigem, listaDestino)
			  {

				for(var i = 0; i < listaOrigem.options.length; i++) {
						if(listaOrigem.options[i].selected) {
								var opt = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
								listaOrigem.options[i] = null;
								i--;
								listaDestino.options[listaDestino.options.length] = opt;

						}
					}
			   }

			   function salvar()
			   {
                    var formAction = "alterarPergunta.do";
					var form = document.adminPerguntaActionForm;
					 <logic:equal name="Form" property="iAcao" value="0" >
                    if(form.canalUtilSelecionado.options.length && form.canalUtilSelecionado.options[0] == "") {
                        alert("Favor incluir um Canal de Atendimento!");
                        return false;
                    } else if(!form.canalUtilSelecionado.options.length) {
                        alert("Favor incluir um Canal de Atendimento!");
                        return false;
                    } else {
                        for(var i = 0; i < form.canalUtilSelecionado.options.length; i++) {
                            form.canalUtilSelecionado.options[i].selected= true;
                        }
                        for(var i = 0; i < form.canalDispSelecionado.options.length; i++) {
                            form.canalDispSelecionado.options[i].selected= true;
                        }
                    }
                    formAction = "incluirPergunta.do";
                </logic:equal>

					  if (trimTextArea(form.sDsScript.value) == "")
                      {
						  alert("Favor preencher o campo Script da Pergunta!");
						  form.sDsScript.select();
					  }
                      else if (trimTextArea(form.sDsPergunta.value) =="")
                      {
						  alert("Favor preencher o campo Descrição da Pergunta!");
						  form.sDsPergunta.select();
					  }
					  else
					  {
							form.action = "/FrontOfficeWeb/campanha/Manter/ManterPergunta/" + formAction;
                            form.targe  = "";
							form.submit();
                            top.frameApp.mostrar_div();
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
                <logic:notEqual name="pageFlow" property="mensagemRetorno" value="">
                    alert("<bean:write name="pageFlow" property="mensagemRetorno" />");
                </logic:notEqual>
        </script>
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();
    </script>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="cam_apc_verpagina">


                    <table bgcolor="#ededed" height="100%" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td valign="top">
                            <form action="AdminPerguntaAction.do" name="adminPerguntaActionForm" tagId="perguntaForm" method="post">
                                <table border="0" width="100%" cellpadding="1" cellspacing="5"  height="100%	">
                                    <tr class="corpo">
                                        <logic:equal name="Form" property="iAcao" value="0" >
                                        &nbsp;
                                        <td colspan="4" align="right">
                                        <table border="0" cellspacing="0" cellpadding="3">
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" valign="top">
                                                <netui:label value="Canais Existentes:"/></td>
                                                <td>&nbsp;</td>
                                                <td class="tblEstatica_campoNome" valign="top">
                                                <netui:label value="Canais Usados:"/></td>
                                            </tr>
                                            <tr>
                                                <td valign="top" >
                                                <html:select name="Form" tabindex="1" property="canalDispSelecionado" size="2" style="width=240px;height=45px" multiple="true" ondblclick="javascript: TransfereCanal(adminPerguntaActionForm.canalDispSelecionado, adminPerguntaActionForm.canalUtilSelecionado);">
                                                <html:options collection="aCanalDisp" property="codigo" labelProperty="descricao"/> </html:select>
                                                </td>
                                                <td align="center">
                                                <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                                    <tr>
                                                        <td align="center">
                                                        <img id="btRightSimp2" onclick=" TransfereCanal(adminPerguntaActionForm.canalDispSelecionado, adminPerguntaActionForm.canalUtilSelecionado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="boder:none"/>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="center">
                                                        <img id="btLeftSimp2" onclick=" TransfereCanal(adminPerguntaActionForm.canalUtilSelecionado,adminPerguntaActionForm.canalDispSelecionado);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="boder:none"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                                </td>
                                                <td valign="top" width="285">
                                                <html:select name="Form" tabindex="2" property="canalUtilSelecionado" size="2" style="width=240px;height=45px" multiple="true" ondblclick="javascript: TransfereCanal(adminPerguntaActionForm.canalUtilSelecionado,adminPerguntaActionForm.canalDispSelecionado);">
                                                <html:options collection="aCanalUtil" property="codigo" labelProperty="descricao"/> </html:select>
                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                        </logic:equal>
                                        <logic:equal name="Form" property="iAcao" value="1" >
                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="Canal de Atendimento:"/>
                                        </td>
                                        <td><netui:label value="{actionForm.sSgCanal}" defaultValue="&nbsp;"/></td>
                                        <html:hidden name="Form" property="idCanalCampanha"/> </logic:equal>
                                    </tr>
                                    <tr >
                                        <td  align="right" valign="middle" width=""><b><netui:label value="Script da Pergunta:"/></b></td>
                                        <td colspan="3" width="">
                                        <html:textarea name="Form" tabindex="3" property="sDsScript" rows="3" cols="66" style="font: arial;color: #006699;" onkeyup="checaTextarea(this,1000);"/>
                                        </td>
                                    </tr>
                                    <tr >
                                        <td  valign="middle" width="" align="right"><b><netui:label value="Tipo de pergunta:"/></b></td>
                                        <td width="">
                                        <table border="0" cellspacing="0" cellpadding="0">
                                            <tr class="corpo">
                                                <td>
                                                <html:select name="Form" tabindex="4" property="tipoApresSelecionado" size="1" style="width=110px;height=10px">
                                                <html:options collection="aApresentacao" property="codigo" labelProperty="descricao"/> </html:select>
                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                        <td class="tblEstatica_campoNome" align="right" ><netui:label value="Encerramento imediato?"/>&nbsp;
                                        </td>
                                        <td valign="top" width="">
                                        <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                            <tr>
                                                <td>

                                                    <html:radio name="Form" property="iEncerramento" value="1" tabindex="5" styleClass="radio">Sim</html:radio>
                                                    <html:radio name="Form" property="iEncerramento" value="0" tabindex="5" styleClass="radio">Não</html:radio>

                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                    </tr>
                                    <tr class="corpo">
                                        <td valign="middle"  align="right"><b><netui:label value="Pergunta obrigatória?"/></b>&nbsp;
                                        </td>
                                        <td valign="top">
                                        <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                            <tr class="corpo">
                                                <td>

                                                    <html:radio name="Form" property="iObrigatoria" value="1" tabindex="5" styleClass="radio">Sim</html:radio>
                                                    <html:radio name="Form" property="iObrigatoria" value="0" tabindex="5" styleClass="radio">Não</html:radio>

                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                        <td class="tblEstatica_campoNome" align="right"><netui:label value="Pergunta ativa?"/>&nbsp;
                                        </td>
                                        <td valign="top">
                                        <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                            <tr class="corpo">
                                                <td>

                                                    <html:radio name="Form" property="iStatus" value="1" tabindex="5" styleClass="radio">Sim</html:radio>
                                                    <html:radio name="Form" property="iStatus" value="0" tabindex="5" styleClass="radio">Não</html:radio>

                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                    </tr>
                                    <tr class="corpo">
                                        <td align="right" valign="middle" ><b><netui:label value="Descrição da Pergunta:"/></b>&nbsp;
                                        </td>
                                        <td colspan="3" >
                                        <html:textarea name="Form" tabindex="11" property="sDsPergunta" rows="3" cols="66" style="font: arial;color: #006699;" onkeyup="checaTextarea(this,1000);"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" valign="top">
                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td align="right">
                                                <acesso:controlHiddenItem nomeIdentificador="cam_apc_grava">
                                                    <img tabindex="12" src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" border="0" onClick="salvar();" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif'" style="border:none;cursor:hand;"/>
                                                </acesso:controlHiddenItem>
                                                &nbsp;
                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                    </tr>
                                </table>
                                </td> </tr> </table>
                                <html:hidden name="Form" property="idPergunta" />
                                <html:hidden name="Form" property="idCampanha" />
                                <html:hidden name="Form" property="idSubCampanha" />
                                <html:hidden name="Form" property="iAcao" />
                                <html:hidden name="Form" property="idCanalCampanha" />
                            </form>
                            </td>
                        </tr>
                    </table>
                    </acesso:controlHiddenItem>
</html>