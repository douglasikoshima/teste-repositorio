<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associarGrupoDescontoForm" />
<bean:define id="ListaGrupoRetencao"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associarGrupoDescontoForm.listaGrupoRetencao" />
<bean:define id="ListaDisponiveis"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associarGrupoDescontoForm.listaMtzDescDisp" />
<bean:define id="ListaAssociados"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associarGrupoDescontoForm.listaMtzDescAssoc" />

<html>
   <head>
        <title>
            Vivonet
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
		<script language="javascript">


            carregaListas = function(){

                var f = document.forms[0];
                if(f.idGrupoRetencao.value!=''){
                    var action = 'carregarListas.do';
                    f.action = action;
                    top.frameApp.mostrar_div();
                    f.submit();
                }else{
                    while(f.elements["listaAssocGravar"].options.length > 0)
                    {
                          f.elements["listaAssocGravar"].options[0]     = null;
                    }
                    while(f.elements["listaDispGravar"].options.length > 0)
                    {
                          f.elements["listaDispGravar"].options[0]     = null;
                    }

                }
            }

            function TransfereSelecaoLista(listaOrigem, listaDestino)
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
                var f = document.forms[0];

                if(f.idGrupoRetencao.value==''){
                    alert('É necessário a seleção de um Grupo de Retenção.');
                    return false;
                }


                var action = 'salvar.do';
                f.action = action;
                for(i = 0;i<f.listaAssocGravar.length;i++){
                   f.listaAssocGravar.options[i].selected = true;
                }
                for(i = 0;i<f.listaDispGravar.length;i++){
                   f.listaDispGravar.options[i].selected = true;
                }

                top.frameApp.mostrar_div();
                f.submit();

            }

            function changeCanal()
            {

            }

		</script>

    <SCRIPT FOR=window EVENT=onload>
        parent.oculta_div();
    </script>

    </head>

    <!--acesso:controlHiddenItem nomeIdentificador="cam_alca_verpagina"-->

	<table height="100%" bgcolor="#ededed" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>

                        <form action="salvar.do" method="post">
							<table border="0"  width="100%" cellpadding="1" cellspacing="10" >
							<tr>
								<td colspan="2" valign="top" align="center">
									<table border="0" cellspacing="0" cellpadding="3" >
                                            <tr class="corpo">
                                                <td class="tblEstatica_campoNome" valign="top">
                                                <netui:label value="Grupo de Retenção:"/></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td valign="top">
													<html:select name="Form" property="idGrupoRetencao" size="1" style="width=220px;height=10px" onchange="carregaListas();">
															 <logic:equal name="Form" property="idGrupoRetencao" value="">
                                                                <option value="" selected>Selecione</option>
                                                            </logic:equal>
                                                             <logic:notEqual name="Form" property="idGrupoRetencao" value="">
                                                                <option value="">Selecione</option>
                                                            </logic:notEqual>
															<html:options collection="ListaGrupoRetencao" property="id" labelProperty="ds"/>
													</html:select>
												</td>
												<td>
                                                </td>
                                   </table>
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" valign="top">
											<table width="100%" border="0" cellspacing="1" cellpadding="2">
											 <tr>
												<td align="center" class="tblEstatica_campoNome" valign="top">Matriz de descontos Disponíveis:</td>
												<td>&nbsp;</td>
												<td align="center" class="tblEstatica_campoNome" valign="top">Matriz de descontos Associadas:</td>
											</tr>
											<tr>
												<td align="center">
													 <html:select name="Form" property="listaDispGravar" size="10" style="width=200px;height=110px" multiple="true" ondblclick="javascript: TransfereSelecaoLista(associarGrupoDescontoForm.listaDispGravar, associarGrupoDescontoForm.listaAssocGravar);">
															<html:options collection="ListaDisponiveis" property="id" labelProperty="ds"/>
													</html:select>
												</td>
												<td align="center">
														<table width="100%" border="0" cellspacing="1" cellpadding="1">
														<tr>
															<td align="center">
																<img id="btRightSimp2" onclick=" TransfereSelecaoLista(listaDispGravar, listaAssocGravar);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"  style="boder:none"/>
															</td>
														</tr>
														<tr>
															<td align="center">
																<img id="btLeftSimp2" onclick=" TransfereSelecaoLista(listaAssocGravar, listaDispGravar);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif"  style="boder:none"/>
															</td>
														</tr>
														</table>
												  </td>
												  <td align="center">
														 <html:select name="Form" property="listaAssocGravar" size="10" style="width=200px;height=110px" multiple="true" ondblclick="javascript: TransfereSelecaoLista(associarGrupoDescontoForm.listaAssocGravar, associarGrupoDescontoForm.listaDispGravar);">
																<html:options collection="ListaAssociados" property="id" labelProperty="ds"/>
														</html:select>
												  </td>
											</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td colspan="3">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" valign="top">
											<table width="100%" border="0" cellspacing="1" cellpadding="0">
											<tr>
													<td colspan="4">&nbsp;</td>
											</tr>
											<tr>
													<td width="10">&nbsp;</td>
													<td width="285">&nbsp;</td>
													<td align="right" width="30">
                                                    <!--acesso:controlHiddenItem nomeIdentificador="cam_alca_grav"-->
														<img onclick=" salvar();" src="/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_registrar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_registrar_nrml.gif';" border="0" style="border:none;cursor:hand;"/>
													<!--/acesso:controlHiddenItem-->
                                                    </td>

											</tr>
											</table>
										</td>
									</tr>
									</table>
                                   <vivo:alert atributo="msgErro" scope="request" />
                                </form>

				</td>
		  </tr>
		  </table>

     <!--/acesso:controlHiddenItem-->

</html>