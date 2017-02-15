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

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="parametrosSubCampanhaActionForm" />
<bean:define id="aCanalDisp" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="parametrosSubCampanhaActionForm.listaCanalDisp" />

<html>
    <head>
        <title> Web Application Page </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
				<script language="javascript">

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
                    var objForm = document.parametrosSubCampanhaActionForm;

					if(objForm.canalDispSelecionado.selectedIndex <=0)
					{
						alert("Canal Atendimento não selecionado!");
                        return false;
					}
					else if(parseInt(objForm.elements["iPublicoTotal"].value) <= 0){
						alert("Público Total não pode ser menor ou igual a 0!");
                        objForm.elements["iPublicoTotal"].select();
                        objForm.elements["iPublicoTotal"].focus();
                        return false;
					}
					else if(parseInt(objForm.elements["STMA"].value) <= 0){
						alert("Tempo médio de Atendimento não pode ser menor ou igual a 0!");
                        objForm.elements["STMA"].select();
                        objForm.elements["STMA"].focus();
                        return false;
					}
					else if(parseInt(objForm.elements["fCampMetaDiaria"].value) <= 0){
						alert("Meta Diária não pode ser menor ou igual a 0!");
                        objForm.elements["fCampMetaDiaria"].select();
                        objForm.elements["fCampMetaDiaria"].focus();
                        return false;
					} else if((parseInt(objForm.elements["fCampAdesao"].value) + parseInt(objForm.elements["fCampNaoAdesao"].value)) <= 0) {
                        alert("A soma da porcentagem de adesões e não adesões não pode ser igual 0%!");
                        objForm.elements["fCampAdesao"].select();
                        objForm.elements["fCampAdesao"].focus();
                        return false;
                    }
                    else if((parseInt(objForm.elements["fCampAdesao"].value) + parseInt(objForm.elements["fCampNaoAdesao"].value)) > 100)
                    {
                        alert("Porcentagem de adesões e não adesões não pode superar 100%!");
                        objForm.elements["fCampAdesao"].select();
                        objForm.elements["fCampAdesao"].focus();
                        return false;
                    }
					/********************* OPERADOR **********************************************************/
					else if(parseInt(objForm.elements["fOperMetaContEfetivo"].value) <= 0){
						alert("A Meta diária contato efetivo não pode ser menor ou igual a 0!");
                        objForm.elements["fOperMetaContEfetivo"].select();
                        objForm.elements["fOperMetaContEfetivo"].focus();
                        return false;
					} else if((parseInt(objForm.elements["fOperAdesao"].value) + parseInt(objForm.elements["fOperNaoAdesao"].value)) <= 0) {
                        alert("Porcentagem de adesões e não adesões não ser igual a 0%!");
                        objForm.elements["fOperAdesao"].select();
                        objForm.elements["fOperAdesao"].focus();
                        return false;
                    }
					else if((parseInt(objForm.elements["fOperAdesao"].value) + parseInt(objForm.elements["fOperNaoAdesao"].value)) > 100)
                    {
                        alert("Porcentagem de adesões e não adesões não pode superar 100%!");
                        objForm.elements["fOperAdesao"].select();
                        objForm.elements["fOperAdesao"].focus();
                        return false;
                    }
                    else if(parseInt(objForm.elements["fOperMetaContEfetivo"].value) > parseInt(objForm.elements["iPublicoTotal"].value))
                    {
                        alert("A Meta diária contato efetivo não pode ser maior que Público Total!");
                        objForm.elements["fOperMetaContEfetivo"].select();
                        objForm.elements["fOperMetaContEfetivo"].focus();
                        return false;
                    }
                    else if(parseInt(objForm.elements["fCampMetaDiaria"].value) > parseInt(objForm.elements["iPublicoTotal"].value))
                    {
                        alert("A Meta diária contato efetivo não pode ser maior que Público Total!");
                        objForm.elements["fCampMetaDiaria"].select();
                        objForm.elements["fCampMetaDiaria"].focus();
                        return false;
                    }
                    else if(parseInt(objForm.elements["fCampContSucesso"].value) > parseInt(objForm.elements["iPublicoTotal"].value))
                    {
                        alert("A Qtd de contatos com sucesso não pode ser maior que Público Total!");
                        objForm.elements["fCampContSucesso"].select();
                        objForm.elements["fCampContSucesso"].focus();
                        return false;
                    }
                    else if(parseInt(objForm.elements["fOperContSucesso"].value) > parseInt(objForm.elements["iPublicoTotal"].value))
                    {
                        alert("A Qtd de contatos com sucesso não pode ser maior que Público Total!");
                        objForm.elements["fOperContSucesso"].select();
                        objForm.elements["fOperContSucesso"].focus();
                        return false;
                    }
					else
					{
						objForm.action = "<%=request.getContextPath()%>/campanha/Manter/ManterSubCampanha/salvarParametrosSubCampanha.do";
						objForm.submit();
                        parent.mostrar_div();
					}
			   }

			   function buscar()
			   {
					if(document.forms["parametrosSubCampanhaActionForm"].canalDispSelecionado.selectedIndex <=0)
					{
						alert("Canal Atendimento não selecionado!");
					}
					else
					{
						document.forms["parametrosSubCampanhaActionForm"].target = "";
						document.forms["parametrosSubCampanhaActionForm"].action = "<%=request.getContextPath()%>/campanha/Manter/ManterSubCampanha/pesquisarParametrosSubCampanha.do";
						document.forms["parametrosSubCampanhaActionForm"].submit();
                        parent.mostrar_div();
					}
			   }

			  function cadeia_numerica(str)
			  {
					var tam = str.length;
					var nome = str;
					var i = 0;
					 for(i=0; i < tam ; i++) {
							caracter = nome.substring(i,i+1);
						if ( caracter < "0" || caracter > "9" ) {
							//i = tam + 1000;
							return false;
						}
					}
					return true;
				}

				function VerificaCampoNumerico(obj)
				{
					if((trim(obj.value)).length!=0)
					{

						if(!cadeia_numerica(obj.value))
						{
							if(obj.value < 0)
							{
								alert("Prencha o campo com um número maior que zero.");
							}
							else
							{
								alert("Prencha o campo com um número inteiro.");
							}
							obj.select();
							obj.focus();
                            return false;
						}

					}
					else
					{
						obj.value="0";
					}

                    return true;
				}

				function verificaCampoPorcentagem(obj)
				{
                    if(VerificaCampoNumerico(obj))
                    {
                        if(parseInt(obj.value) > 100)
                        {
                            alert("Porcentagem não pode superar 100%!");
							obj.select();
							obj.focus();
                            return false;
                        }
                    }
				}

                function canalDispSelecionado_onchange()
                {
			        document.getElementById("panel1").style.display = "none";
                    document.getElementById("panel2").style.display = "none";
                }

		</script>
    <SCRIPT FOR=window EVENT=onload>
        parent.oculta_div();

        /*
         * Oculta frames enquanto o canal não for
         * carregado
        */
        <logic:equal value="1" name="showPanel">
            if(document.forms["parametrosSubCampanhaActionForm"].canalDispSelecionado.selectedIndex > 0)
            {
                document.getElementById("panel1").style.display = "block";
                document.getElementById("panel2").style.display = "block";
            }
        </logic:equal>
    </script>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="cam_pas_verpagina">

    <table bgcolor="#ededed" height="100%" width="100%" border="0" cellspacing="0" cellpadding="10">
        <tr valign="top">
            <td>
            <form action="ParametrosSubCampanhaAction.do" name="parametrosSubCampanhaActionForm" method="post">
                <table  width="100%" border="0" cellspacing="0" cellpadding="10">
                    <tr valign="top">
                        <td valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="1">
                            <tr>
                                <td valign="top"  height="100%">
                                <table width="100%" border="0" height="100%" cellpadding="1" cellspacing="05">
                                    <tr>
                                        <td colspan="3" valign="top" align="center">
                                        <table border="0" cellspacing="0" cellpadding="3" >
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" valign="top">
                                                <netui:label value="Canal Atendimento:"/></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td valign="top">
														<html:select name="Form" property="canalDispSelecionado" size="1" style="width=400px;height=10px" onchange="canalDispSelecionado_onchange();" >
																<html:option value=""></html:option>
																<html:options collection="aCanalDisp" property="codigo" labelProperty="descricao"/>
														</html:select>
												</td>
												<td>
                                                <acesso:controlHiddenItem nomeIdentificador="cam_pas_pesq">
													<img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"  onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';" style="cursor:hand;border=0;" onClick="buscar();"/>
												</acesso:controlHiddenItem>
                                                </td>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr id="panel1" style="display:none;">
                                        <td  valign="top">
                                        <table width="100%" border="0" cellspacing="0" cellpadding="1">
                                            <tr>
                                                <td>
                                                <table border="0" cellspacing="1" cellpadding="2" class="tbl_bgblue" height="250" width="100%">
                                                    <tr align="left">
                                                        <td colspan="2" class="tblEstatica_campoNome"><netui:label value="Campanha"/></td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="Público Total:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                            <html:text name="Form" property="iPublicoTotal" styleClass="Textfield" maxlength="10" onBlur="VerificaCampoNumerico(this);" onClick="this.select();" size="20" />
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="TMA(minutos):"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                            <html:text name="Form" property="STMA" onBlur="VerificaCampoNumerico(this);" onClick="this.select();" styleClass="Textfield" maxlength="2" size="20"/>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="Meta diária contato efetivo:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                            <html:text name="Form" property="fCampMetaDiaria" styleClass="Textfield" onBlur="VerificaCampoNumerico(this);" onClick="this.select();" maxlength="10" size="20" />
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="Qtd contatos com sucesso:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                        <html:text name="Form" property="fCampContSucesso" styleClass="Textfield" onBlur="VerificaCampoNumerico(this);" onClick="this.select();" maxlength="10" size="20"/>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="% contatos efetivos:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                        <html:text name="Form" property="fCampContEfetivo" styleClass="Textfield" onBlur="verificaCampoPorcentagem(this);" onClick="this.select();" maxlength="10" size="20"/>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="% de adesões:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                            <html:text name="Form" property="fCampAdesao" styleClass="Textfield"  onBlur="verificaCampoPorcentagem(this);" onClick="this.select();" maxlength="10" size="20"/>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="% de não adesões:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                            <html:text name="Form" property="fCampNaoAdesao" styleClass="Textfield" onBlur="verificaCampoPorcentagem(this);" onClick="this.select();" maxlength="10" size="20"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                        <td width="50%" valign="top">
                                        <table width="100%" border="0" cellspacing="0" cellpadding="1">
                                            <tr>
                                                <td>
                                                <table border="0" cellspacing="1" cellpadding="2" class="tbl_bgblue" width="100%" height="250">
                                                    <tr align="left" >
                                                        <td colspan="2" class="tblEstatica_campoNome" valign="top"><netui:label value="Operador"/></td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right"><netui:label value="Meta diária contato efetivo:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                        <html:text name="Form" property="fOperMetaContEfetivo" styleClass="Textfield" onBlur="VerificaCampoNumerico(this);" onClick="this.select();" maxlength="10" size="20"/>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="Qtd contatos com sucesso:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                            <html:text name="Form" property="fOperContSucesso" styleClass="Textfield" onBlur="VerificaCampoNumerico(this);" onClick="this.select();" maxlength="10" size="20"/>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="% contatos efetivos:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                            <html:text name="Form" property="fOperContEfetivo" styleClass="Textfield" onBlur="verificaCampoPorcentagem(this);"  onClick="this.select();" maxlength="10" size="20"/>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="% de adesões:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                            <html:text name="Form" property="fOperAdesao" styleClass="Textfield"  onBlur="verificaCampoPorcentagem(this);"  onClick="this.select();" maxlength="10" size="20"/>
                                                        </td>
                                                    </tr>
                                                    <tr align="center">
                                                        <td class="tblEstatica_campoNome" align="right" valign="top"><netui:label value="% de não adesões:"/>&nbsp;
                                                        </td>
                                                        <td align="left" valign="top">
                                                            <html:text name="Form" property="fOperNaoAdesao" styleClass="Textfield" onBlur="verificaCampoPorcentagem(this);"  onClick="this.select();" maxlength="10" size="20"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                    </tr>
                                    <tr id="panel2" style="display:none;">
                                        <td colspan="2" valign="top">
                                        <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                            <tr>
                                                <td align="right">
                                                <acesso:controlHiddenItem nomeIdentificador="cam_pas_sal">
                                                <img src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" border="0"  onClick="salvar();" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif'" style="border:none;cursor:hand;"/>
                                                </acesso:controlHiddenItem>
                                                </td>
                                            </tr>
                                        </table>
                                        </td>
                                    </tr>
                                </table>
                                </td>
                            </tr>
                        </table>
                        </td>
                    </tr>
                </table>
                <html:hidden name="Form" property="iIdCampanha" />
                <html:hidden name="Form" property="iIdSubCampanha" />
				<html:hidden name="Form" property="iAcao" />
            </form>
            </td>
        </tr>
    </table>


    </acesso:controlHiddenItem>
</html>