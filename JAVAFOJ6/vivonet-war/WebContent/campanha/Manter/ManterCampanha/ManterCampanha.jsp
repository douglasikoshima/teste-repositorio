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

<bean:define id="Form"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterCampanhaActionForm" />
<bean:define id="aCampanha"	name="Form" property="listaCampanha" />

<html>
    <head>

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script language="javascript">

        function excluir(obj) {
            if (confirm('Deseja realmente excluir este registro?')){
                valor = obj.href.split("?");
                document.manterCampanhaActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterCampanha/excluirCampanha.do?" + valor[1];
                document.manterCampanhaActionForm.submit();
                top.frameApp.mostrar_div();
            }
        }

        function inclui(obj) {
            if (validaCampos()) {
                // valor = obj.href.split("?");
                // valor[1]?action = document.manterCampanhaActionForm.action + "?" + valor[1]:action = document.manterCampanhaActionForm.action;
                // document.manterCampanhaActionForm.action = action;
                document.manterCampanhaActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterCampanha/incluirCampanha.do";
                document.manterCampanhaActionForm.submit();
                top.frameApp.mostrar_div();
            }
        }

        function alterar(obj){
            if (validaCampos()) {
                valor = obj.href.split("?");
                //valor[1]?action = document.manterCampanhaActionForm.action + "?" + valor[1]:action = document.manterCampanhaActionForm.action;
                //document.manterCampanhaActionForm.action = action;
                document.manterCampanhaActionForm.action = "/FrontOfficeWeb/campanha/Manter/ManterCampanha/editarCampanha.do?" + valor[1];
                document.manterCampanhaActionForm.submit();
                top.frameApp.mostrar_div();
            }
        }

		function validaCampos() {
			if(trim(document.manterCampanhaActionForm.elements["sgCampanha"].value) == "") {
                alert("Favor preencher o campo Campanha!");
				return false;
			} else if (document.manterCampanhaActionForm.elements["sgCampanha"].value.length > 255) {
				alert("Tamanho do campo Campanha maior que 255 carcteres!");
				return false;
			}
			if(trim(document.manterCampanhaActionForm.elements["dsCampanha"].value) == "") {
                alert("Favor preencher o campo Descrição!");
				return false;
			} else if (document.manterCampanhaActionForm.elements["dsCampanha"].value.length > 1000) {
				alert("Tamanho do campo Descrição maior 255 carcteres!");
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
        top.frameApp.oculta_div();
    </script>
</head>

<body>
<acesso:controlHiddenItem nomeIdentificador="cam_mc_verpagina">
    <form action="ManterCampanhaAction.do" name="manterCampanhaActionForm" method="post">

    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="70">
                <netui:label value="Campanha:"/>
            </td>
            <td align="left">
			 <% if((request.getAttribute("btMostra") != null)&&(request.getAttribute("btMostra").equals("EDITAR"))) {%>

                <html:text name="Form"
                           property="sgCampanha"
                           styleClass="textfield"
                           maxlength="255"
                           tabindex="1"
                           disabled="true"
                           style="width:690px"  />

			  <% } else { %>

				<html:text name="Form"
                           property="sgCampanha"
                           styleClass="textfield"
                           maxlength="255"
                           tabindex="1"
                           style="width:690px"  />

			  <% } %>
            </td>
        </tr>
        <tr><td height="6"></td></tr>
        <tr>
            <td valign="top">
                <netui:label value="Descrição:"/>
            </td>
            <td style="padding-left:3px;">
                <html:textarea name="Form"
                               property="dsCampanha"
                               tabindex="2"
                               rows="3"
                               cols="40"
                               styleClass="textfield"
                               style="width:690px;color:#006699;"
                               onKeyDown="checaTextarea(this,1000)"
                               onKeyPress="checaTextarea(this,1000)"
                               onKeyUp="checaTextarea(this,1000)"
                               onClick="checaTextarea(this,1000)"
                               onBlur="checaTextarea(this,1000)" />
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right">

                <logic:equal name="Form" property="idAcao" value="1">
                    <acesso:controlHiddenItem nomeIdentificador="cam_mc_incl">
                        <a href="javascript:alterar(this)"><img src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" style="border:none" /></a>
                    </acesso:controlHiddenItem>
      		        <img tabindex="4" id="Limpar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" onClick="limparCampos(); return false"/>
                </logic:equal>

				<logic:equal name="Form" property="idAcao" value="0">
                    <acesso:controlHiddenItem nomeIdentificador="cam_mc_incl">
                        <img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onmouseup="inclui(this);" class="button" />
                    </acesso:controlHiddenItem>
                    <img tabindex="4" id="Editar" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" style="border:none;" onClick="limparCampos(); return false"/>
                </logic:equal>

            </td>
        </tr>
    </table>

    <vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="230" tableWidth="745" styleId="tableTitle" sortable="true">

        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="rigth"  width="45%" type="string">Campanha</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="rigth"  width="45%" type="string">Descrição</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
        </vivo:tbHeader>

        <vivo:tbRows>

        	<logic:iterate id="item" name="aCampanha" indexId="c">

        		<vivo:tbRow key="linha1">
                    <vivo:tbRowColumn>
                    	<bean:write name="item" property="sigla" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                    	<bean:write name="item" property="descricao" />
					</vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                    <acesso:controlHiddenItem nomeIdentificador="cam_mc_alt">
                    	<img class="button"
                    		 onmouseup="document.forms[0].action='ManterCampanhaAction.do?acao=editar&codigo=<bean:write name="item" property="codigo" />&indice=<%=c%>';document.forms[0].submit();"
                    		 src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" />
                    </acesso:controlHiddenItem>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                    <acesso:controlHiddenItem nomeIdentificador="cam_mc_excl">
                    	<img class="button"
                    	     href="ManterCampanhaAction.do?acao=excluir&codigo=<bean:write name="item" property="codigo" />"
                    		 onmouseup="excluir(this)"
                    		 src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" />
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
                        <td width="200" valign="middle">&nbsp;Alterar Campanha</td>
                        <td width="15" align="left"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"></td>
                        <td width="200" valign="middle"> &nbsp;Excluir Campanha</td>
                    </tr>
    </table>

	<html:hidden name="Form" property="idCampanhaEncontrada"/>
	<html:hidden name="Form" property="idCampanha"/>
	<html:hidden name="Form" property="idAcao"/>
	<html:hidden name="Form" property="idErro"/>

    </form>
	<logic:equal name="Form" property="idAcao" value="0">
		<script>
			document.body.style.backgroundColor = '#ededed';
            if(document.manterCampanhaActionForm.idCampanhaEncontrada.value >0)
            {
                alert("Dado(s) da Campanha já cadastrado(s)!");
            }
		</script>
    </logic:equal>
	<logic:equal name="Form" property="idAcao" value="1">
		<script>
			document.body.style.backgroundColor = '#ededed';
			if(document.manterCampanhaActionForm.idCampanhaEncontrada.value >0)
			{
				alert("Dado(s) da Campanha já cadastrado(s)!");
			}
		</script>
    </logic:equal>
	<logic:equal name="Form" property="idErro" value="1">
		<script>
			document.body.style.backgroundColor = '#ededed';
			alert("Não foi possível excluir campanha pois a mesma possui dependências!");
		</script>
    </logic:equal>

</acesso:controlHiddenItem>
</body>
</html>