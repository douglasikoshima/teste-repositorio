<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="pageFlow" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" />
<bean:define id="listaStatusCampanha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaStatusCampanha" />
<bean:define id="listasCampanha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listasCampanha" />
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pesquisarListaActionForm" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:section name="headerSection">

   <head>
        <title>
            Web Application Page
        </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script>
        function carregar()
        {
            //top.frameApp.mostrar_div();
            //divIncluiLista.style.display = 'block';
            //ifrmIncluiLista.src = 'CarregarListaExterna.jsp';

            divIncluiLista.style.display = '';
            document.forms[0].target = "ifrmIncluiLista";
            document.forms[0].action = "CarregarListaExterna.jsp";
            //top.frameApp.mostrar_div();
            document.forms[0].submit();
            ifrmIncluiLista.src = 'CarregarListaExterna.jsp';
        }
        function pesquisar()
        {
            document.forms.pesquisarListaActionForm.method = "post";
            document.forms.pesquisarListaActionForm.target = "";
            document.forms.pesquisarListaActionForm.action = "/FrontOfficeWeb/campanha/CarregarLista/pesquisarListaAction.do";
            document.forms.pesquisarListaActionForm.submit();
            top.frameApp.mostrar_div();
        }

        function deleteLista(idLista)
        {
            if (confirm('Deseja realmente excluir este registro?'))
            {
                document.forms.pesquisarListaActionForm.method = "post";
                document.forms.pesquisarListaActionForm.target = "";
                document.forms.pesquisarListaActionForm.action = "/FrontOfficeWeb/campanha/CarregarLista/deleteLista.do?idLista=" + idLista;
                document.forms.pesquisarListaActionForm.submit();
                top.frameApp.mostrar_div();
            }

        }
        </script>

    </head>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cam_pesqlistas_verpagina">

    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div();

        //<logicnotEmpty name="form" property="msgError">
            //alert("<beanwrite name="form" property="msgError"/>");
        //</logicnotEmpty>
    </script>

    <vivo:alert atributo="isMsgErroCargaLista" />

		<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
		<div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

	<vivo:sessao id="lista" width="790" height="470" label="Campanha" operacoes="Pesquisar lista" scroll="no">

							<form name="pesquisarListaActionForm" action="pesquisarListaAction.do" method="post">
							<table height="100%" border="0"  width="100%" cellpadding="1" cellspacing="10">
							<tr>
								<td colspan="2" valign="top">
									<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tbl_bggray">
									<tr valign="top">
										<td class="tblEstatica_campoNome" align="right"><netui:label value="Nome Lista:"/></td>
										<td>
											<netui:textBox dataSource="{pageContext.form.nmLista}" size="40" maxlength="60"/>
										</td>
									</tr>
									<tr valign="top">
										<td class="tblEstatica_campoNome" align="right"><netui:label value="Status carga:"/></td>
										<td>
                                            <html:select name="form" property="inStatusCarga" size="1" style="width=150px;height=10px">
                                                <html:option value="">TODOS</html:option>
                                                <html:options collection="listaStatusCampanha" property="inStatusCarga" labelProperty="dsStatusCarga"/>
                                            </html:select>
										</td>
									</tr>

                                        <tr>
										<td colspan="2" valign="top">
											<table height="100%"  border="0" cellspacing="1" cellpadding="0" align="right">
											<tr>
													<td valign="middle" >

                                                    <acesso:controlHiddenItem nomeIdentificador="cam_bt_pesq">
                                                        <img  src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"  style="border:none;cursor:hand" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';" onClick="pesquisar();" />
                                                    </acesso:controlHiddenItem>

                                                    <acesso:controlHiddenItem nomeIdentificador="cam_bt_carregar">
                                                        <img src="/FrontOfficeWeb/resources/images/bt_carregar_nrml.gif"  style="border:none;cursor:hand" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_carregar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_carregar_nrml.gif';" onClick="carregar();" />
                                                    </acesso:controlHiddenItem>
                                                    </td>

											</tr>
											</table>
										</td>
									</tr>
                                    </table>
                                    <!-- Resultados de pesquisa -->
                                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                    <table class="tbl_bggray" width="100%">
									<tr>
										<td colspan="2" valign="top">
                                            <vivo:tbTable selectable="true" layoutWidth="735"  layoutHeight="280" tableWidth="735" styleId="tableTitle" sortable="true">
                                            <vivo:tbHeader>
                                                <vivo:tbHeaderColumn align="center"  width="31%" type="string">Nome lista</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="31%" type="string">Descrição de erro</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="16%" type="number">Qtde. de tentativa</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="10%" type="date">Data criação</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="6%" type="">&nbsp;</vivo:tbHeaderColumn>
                                                <vivo:tbHeaderColumn align="center" width="6%" type="">&nbsp;</vivo:tbHeaderColumn>
                                            </vivo:tbHeader>
                                            <vivo:tbRows>
                                                <logic:iterate id="item"  name="listasCampanha" indexId="index">
                                                        <vivo:tbRow key="linha1">
                                                            <vivo:tbRowColumn><bean:write name="item" property="nmLista" /></vivo:tbRowColumn>
                                                            <vivo:tbRowColumn><bean:write name="item" property="dsErroCarga" /></vivo:tbRowColumn>
                                                            <vivo:tbRowColumn><bean:write name="item" property="qtTentativas" /></vivo:tbRowColumn>
                                                            <vivo:tbRowColumn><bean:write name="item" property="dtCriacao" /></vivo:tbRowColumn>
                                                            <logic:equal  name="item" property="inStatusCarga" value="0">
                                                                <td align="center" width="20%"><img src="/FrontOfficeWeb/resources/images/icon_disponivel.gif" />&nbsp;</td>
                                                            </logic:equal>

                                                            <logic:equal name="item" property="inStatusCarga" value="1">
                                                                <td align="center" width="20%"><img src="/FrontOfficeWeb/resources/images/icon_sucesso.gif" />&nbsp;</td>
                                                            </logic:equal>

                                                            <logic:equal name="item" property="inStatusCarga" value="2">
                                                                <td align="center" width="20%"><img src="/FrontOfficeWeb/resources/images/icon_erro.gif" />&nbsp;</td>
                                                            </logic:equal>

                                                            <logic:equal name="item" property="inStatusCarga" value="3">
                                                                <td align="center" width="20%"><img src="/FrontOfficeWeb/resources/images/icon_emcarga.gif" />&nbsp;</td>
                                                            </logic:equal>

                                                            <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" style="cursor:hand;" onClick="deleteLista('<bean:write name='item' property='idLista' />');"></vivo:tbRowColumn>

                                                        </vivo:tbRow>
                                                </logic:iterate>
                                            </vivo:tbRows>
                                            </vivo:tbTable>

                                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                                            <table width="766" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                                                <tr>
                                                    <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
                                                    <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/icon_disponivel.gif" align="middle"> &nbsp;Disponivel</td>
                                                    <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/icon_emcarga.gif" align="middle"> &nbsp;Em carga</td>
                                                    <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/icon_erro.gif" align="middle"> &nbsp;Erro</td>
                                                    <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/icon_sucesso.gif" align="middle"> &nbsp;Sucesso</td>
                                                    <td width="166"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Lista</td>
                                                </tr>
                                            </table>
										</td>
									</tr>

									</table>
                                    <img id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="top.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;return false" hspace="5" vspace="5"/>
									</form>


	 </vivo:sessao>

        <vivo:quadroFlutuante  label="Carregar lista" scroll="no" src="" idIframe="ifrmIncluiLista" id="divIncluiLista" onclick=""spacesLeft="150" height="160" spacesTop="120" url="<%=request.getContextPath()%>" display="none" width="420"/>

     </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>