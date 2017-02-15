<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterSubMotivoActionForm" />
<bean:define id="aSubMotivo" name="Form" property="listaSubMotivo" />

<html>
<head>

    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

    <script language="javascript">

        function excluir(obj)
        {
            if (confirm('Deseja realmente excluir este registro?'))
            {
                valor = obj.href.split("?");
                //valor[1]?action = document.manterSubMotivoActionForm.action + "?" + valor[1]:action = document.manterSubMotivoActionForm.action;
                //anchor_submit_form("Netui_Form_0",action);
                document.manterSubMotivoActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterSubMotivo/excluirSubMotivo.do?" + valor[1];
                document.manterSubMotivoActionForm.submit();
                top.frameApp.mostrar_div()
            }
        }


        function inclui(obj)
        {
            if (validaCampos())
			{
                valor = obj.href.split("?");
                //valor[1]?action = document.manterSubMotivoActionForm.action + "?" + valor[1]:action = document.manterSubMotivoActionForm.action;
                document.manterSubMotivoActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterSubMotivo/incluirSubMotivo.do?" + valor[1];
                document.manterSubMotivoActionForm.submit();
                top.frameApp.mostrar_div();
            }
        }

        function alterar(obj) {
            if (validaCampos()) {
                valor = obj.href.split("?");
                document.manterSubMotivoActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterSubMotivo/alterarSubMotivo.do?" + valor[1];
                document.manterSubMotivoActionForm.submit();
                top.frameApp.mostrar_div();
            }
        }

		function validaCampos() {
			if(trim(document.manterSubMotivoActionForm.elements["sgSubMotivo"].value) == "") {
                alert("Favor preencher o campo Sub Motivo!");
                document.manterSubMotivoActionForm.elements["sgSubMotivo"].focus();
				return false;
			} else if (document.manterSubMotivoActionForm.elements["sgSubMotivo"].value.length > 255) {
				alert("Tamanho do campo Sub  Motivo maior que 255 carcteres!");
                document.manterSubMotivoActionForm.elements["sgSubMotivo"].focus();
				return false;
			}
			if(document.manterSubMotivoActionForm.elements["sgSubMotivo"].value == document.manterSubMotivoActionForm.sgSubMotivoOld.value) {
				alert("Campo Sub Motivo não modificado!");
                document.manterSubMotivoActionForm.elements["sgSubMotivo"].focus();
				return false;
			}
			return true;
		}

        function limparCampos() {
            window.location.href = "begin.do";
            top.frameApp.mostrar_div();
        }

    </script>
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.oculta_div()

    <logic:equal name="Form" property="idAcao" value="0">
        document.body.style.backgroundColor = '#ededed';
        if(document.manterSubMotivoActionForm.idSubMotivoEncontrado.value >0)
        {
            if(document.manterSubMotivoActionForm.idSubMotivoEncontrado.value >0)
            {
                alert("Sub Motivo já cadastrado!");
            }
        }
    </logic:equal>
	<logic:equal name="Form" property="idAcao" value="1">
        document.body.style.backgroundColor = '#ededed';
        if(document.manterSubMotivoActionForm.idSubMotivoEncontrado.value >0)
        {
            alert("Sub Motivo já cadastrado!");
        }
    </logic:equal>
	<logic:equal name="Form" property="idErro" value="1">
        document.body.style.backgroundColor = '#ededed';
        alert("Não foi possível excluir sub motivo pois o mesmo possui dependências!");
    </logic:equal>
    </script>
</head>

<body>
<acesso:controlHiddenItem nomeIdentificador="cam_musc_verpagina">
    <form action="ManterSubMotivoAction.do" name="manterSubMotivoActionForm" method="post">

    <table border="0" width="100%" cellpadding="0" cellspacing="0" height="100%">
        <tr>
            <td colspan="4">
                <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        <netui:label value="Sub Motivo:"/>&nbsp;
                        <html:text name="Form" property="sgSubMotivo" styleClass="textfield" maxlength="180" style="width:580px" />
                    </td>
                    <td style="padding-left:10px;">
                    <logic:equal name="Form" property="idAcao" value="1">
                        <acesso:controlHiddenItem nomeIdentificador="cam_musc_alte">
                            <img class="button"
                            	 id="Editar"
                            	 vspace="5"
                            	 align="middle"
                            	 src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif"
                            	 href="ManterSubMotivoAction.do?acao=pesquisar"
                            	 onClick="alterar(this); return false" />
                            <img vspace="5" align="middle" id="Limpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" onClick="limparCampos(); return false"/>
                        </acesso:controlHiddenItem>
                    </logic:equal>
					<logic:equal name="Form" property="idAcao" value="0">
                        <acesso:controlHiddenItem nomeIdentificador="cam_musc_incl">
                        	<img class="button"
                            	 id="Editar"
                            	 vspace="5"
                            	 align="middle"
                            	 src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
                            	 href="ManterSubMotivoAction.do?acao=pesquisar"
                            	 onClick="inclui(this); return false" />
                            <img align="middle" vspace="5" id="Limpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" onClick="limparCampos(); return false"/>
                        </acesso:controlHiddenItem>
                    </logic:equal>
                    </td>
                </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td valign="top">
                <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="300" tableWidth="750" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="rigth"  width="80%" type="string">Lista de Submotivos</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                </vivo:tbHeader>

                <vivo:tbRows>

                	<logic:iterate id="item" name="aSubMotivo" indexId="c">

                		<vivo:tbRow key="linha1">
                            <vivo:tbRowColumn>
                            	<bean:write name="item" property="sigla" />
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                            <acesso:controlHiddenItem nomeIdentificador="cam_musc_edit">
                            	<img class="button"
                            		 src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"
                            		 onClick="top.frameApp.mostrar_div();"
                            		 onmouseup="document.forms[0].action='ManterSubMotivoAction.do?acao=editar&codigo=<bean:write name="item" property="codigo" />&indice=<%=c%>';document.forms[0].submit()" />
                            </acesso:controlHiddenItem>
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                            <acesso:controlHiddenItem nomeIdentificador="cam_musc_excl">
                            	<img class="button"
                            		 src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif"
                            		 onClick="excluir(this);"
                            		 href="ManterSubMotivoAction.do?acao=excluir&codigo=<bean:write name="item" property="codigo" />" />
                            </acesso:controlHiddenItem>
                            </vivo:tbRowColumn>
                        </vivo:tbRow>

                	</logic:iterate>

                </vivo:tbRows>
                </vivo:tbTable>

                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

                <table width="763" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                    <tr>
                        <td valign="middle" width="200"><b>&nbsp;Legendas:</b></td>
                        <td width="15" ><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"></td>
                        <td width="200" valign="middle">&nbsp;Alterar Submotivo</td>
                        <td width="15" align="left"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"></td>
                        <td width="200" valign="middle"> &nbsp;Excluir Submotivo</td>
                    </tr>
                </table>

            </td>
        </tr>
        <!-- Result -->
        </table>

    <html:hidden name="Form" property="idSubMotivo" />
	<html:hidden name="Form" property="idSubMotivoEncontrado"/>
	<html:hidden name="Form" property="sgSubMotivoOld"/>
	<html:hidden name="Form" property="idAcao"/>
	<html:hidden name="Form" property="idErro"/>
    </form>

</acesso:controlHiddenItem>
</body>
</html>