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
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterMotivoActionForm" />
<bean:define id="aMotivo" name="Form" property="listaMotivo" />

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
                document.manterMotivoActionForm.target = "";
                document.manterMotivoActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterMotivo/excluirMotivo.do?" + valor[1];
                document.manterMotivoActionForm.submit();
                top.frameApp.mostrar_div();
            }
        }

        function inclui(obj)
        {
            if (validaCampos())
			{
                valor = obj.href.split("?");
                //valor[1]?action = document.manterMotivoActionForm.action + "?" + valor[1]:action = document.manterMotivoActionForm.action;
                //document.manterMotivoActionForm.action = action;
                document.manterMotivoActionForm.target = "";
                document.manterMotivoActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterMotivo/incluirMotivo.do?" + valor[1];
                document.manterMotivoActionForm.submit();
                top.frameApp.mostrar_div();
            }
        }


        function alterar(obj)
        {
            if (validaCampos())
			{
                valor = obj.href.split("?");
                //valor[1]?action = document.manterMotivoActionForm.action + "?" + valor[1]:action = document.manterMotivoActionForm.action;
                document.manterMotivoActionForm.target = "";
                document.manterMotivoActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterMotivo/editarMotivo.do?" + valor[1];
                document.manterMotivoActionForm.submit();
                top.frameApp.mostrar_div();
            }
        }

		function validaCampos()
		{
			if(trim(document.manterMotivoActionForm.elements["sgMotivo"].value) == ""){
                alert("Favor preencher o campo Motivo!");
                document.manterMotivoActionForm.elements["sgMotivo"].focus();
				return false;
			}else if (document.manterMotivoActionForm.elements["sgMotivo"].value.length > 255){
				alert("Tamanho do campo Motivo maior que 255 carcteres!");
                document.manterMotivoActionForm.elements["sgMotivo"].focus();
				return false;
			}
			return true;
		}

        function limparCampos()
        {
            window.location.href = "begin.do";
            top.frameApp.mostrar_div();
        }
    </script>

        <SCRIPT FOR=window EVENT=onload>
            top.frameApp.oculta_div()

        <logic:equal name="Form" property="idAcao" value="0">
                document.body.style.backgroundColor = '#ededed';
                if(document.manterMotivoActionForm.idMotivoEncontrado.value >0)
                {
                    /*
                    if (confirm("Motivo já existente, deseja atualizá-lo?"))
                    {
                        document.manterMotivoActionForm.action = document.manterMotivoActionForm.action + "?acao=INCLUIR";
                        document.manterMotivoActionForm.submit();
                    }
                    */
                    if(document.manterMotivoActionForm.idMotivoEncontrado.value >0)
                    {
                        alert("Motivo já cadastrado!");
                    }
                }
        </logic:equal>
        <logic:equal name="Form" property="idAcao" value="1">
                document.body.style.backgroundColor = '#ededed';
        </logic:equal>
        <logic:equal name="Form" property="idErro" value="1">
                document.body.style.backgroundColor = '#ededed';
                alert("Não foi possível excluir motivo pois o mesmo possui dependências!");
        </logic:equal>
        </script>
</head>

<body>

<acesso:controlHiddenItem nomeIdentificador="cam_mmc_verpagina">

<form action="ManterMotivoAction.do" name="manterMotivoActionForm" method="post" id="manterMotivoActionForm">

<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <table border="0"  width="100%" cellpadding="1" cellspacing="0" height="100%">
    <tr>
        <td colspan="4">
            <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <netui:label value="Motivo:"/>
                </td>
                <td>
                    <html:text name="Form" property="sgMotivo" styleClass="textfield" maxlength="180" style="width:400px" />
                </td>
                <td>
                    &nbsp;
    				<logic:equal name="Form" property="idAcao" value="1">
                        <acesso:controlHiddenItem nomeIdentificador="cam_mmc_incl">
                        	<img class="button"
                        		 id="Editar"
                        		 href="ManterMotivoAction.do?acao=pequisar"
                        		 src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif"
                        		 onClick="alterar(this);" />
                        </acesso:controlHiddenItem>
                        <img id="Editar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" onClick="limparCampos(); return false"/>
                    </logic:equal>
    				<logic:equal name="Form" property="idAcao" value="0">
                        <acesso:controlHiddenItem nomeIdentificador="cam_mmc_incl">

                        <img class="button"
    	               		 id="Incluir"
    	               		 href="ManterMotivoAction.do?acao=pequisar"
    	               		 src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
    	               		 onClick="inclui(this);" />
                        </acesso:controlHiddenItem>
                            <img id="Editar"
                                 src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif"
                                 style="border:none;"
                                 onClick="limparCampos(); return false"/>
                    </logic:equal>
                </td>
                <td width="180" align="right">
                   <b> <netui:label value="Aderiu?"/> </b>

                      <netui:radioButtonGroup dataSource="{pageContext.Form.idAderiu}" >
                        <netui:radioButtonOption value="1" style="border:none;background-color:#ededed;">
                            <netui:label value="Sim"/>
                        </netui:radioButtonOption>
                        <netui:radioButtonOption value="0" style="border:none;background-color:#ededed;">
                            <netui:label value="Não"/>
                        </netui:radioButtonOption>
                      </netui:radioButtonGroup>
                </td>
            </tr>
            </tr>
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        </td>
    </tr>

    <tr>
        <td valign="top">
            <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="300" tableWidth="745" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="rigth"  width="90%" type="string">Lista de Motivos</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%" type=""></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%" type=""></vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>

            	<logic:iterate id="item" name="aMotivo" indexId="c">

            		<vivo:tbRow key="linha1">
                        <vivo:tbRowColumn>
                        	<bean:write name="item" property="sigla" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <acesso:controlHiddenItem nomeIdentificador="cam_mmc_alte">
                            <img class="button"
                            	 src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"
                            	 onmouseup="document.forms[0].action='ManterMotivoAction.do?acao=editar&codigo=<bean:write name="item" property="codigo" />&indice=<%=c%>';document.forms[0].submit()"
                            	 onClick="top.frameApp.mostrar_div();" />
                        </acesso:controlHiddenItem>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <acesso:controlHiddenItem nomeIdentificador="cam_mmc_excl">
                        	<img class="button"
                            	 src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif"
                            	 href="ManterMotivoAction.do?acao=excluir&codigo=<bean:write name="item" property="codigo" />"
                            	 onClick="excluir(this);" />
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
                    <td width="200" valign="middle">&nbsp;Alterar Motivo</td>
                    <td width="15" align="left"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"></td>
                    <td width="200" valign="middle"> &nbsp;Excluir Motivo</td>
                </tr>
            </table>
        </td>
    </tr>
    <!-- Result -->
    </table>
    <netui:hidden dataSource="{actionForm.idMotivo}" />
	<netui:hidden dataSource="{actionForm.idMotivoEncontrado}" />
	<html:hidden name="Form" property="idMotivoEncontrado"/>
	<html:hidden name="Form" property="idMotivo"/>
	<html:hidden name="Form" property="sgMotivoOld"/>
	<html:hidden name="Form" property="idAcao"/>
	<html:hidden name="Form" property="idErro"/>
    </form>
</acesso:controlHiddenItem>
</body>
<vivo:alert atributo="msgError" scope="request" />
</html>