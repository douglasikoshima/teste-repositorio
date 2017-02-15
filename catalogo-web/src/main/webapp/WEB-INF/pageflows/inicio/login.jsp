<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/default.css"> 
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/header.css"> 
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/button.css"> 
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/form.css">
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/image.css">
		<link rel="stylesheet" type="text/css" href="/catalogo/static_server/css/popup.css">
		<script src="/catalogo/static_server/js/prototype.js" type="text/javascript"></script>
		<title>FrontOffice VIVO</title>
		
    </head>
    <body>
    <c:if test="${alterarSenha == null && senhaQuaseExpirando == null && erroLogin == null}">
    	<c:set var="displayPopupLogin" value="none"/>
    </c:if>
    <div id="popupLoginCortina" style="z-index: 10; display: ${displayPopupLogin};width:100%;height:100%" class="cortina-login">
	</div>
	<div id="popupLogin" style="display:${displayPopupLogin};background-color: transparent;z-index: 25;top:100px;width:395px;height:150px;left:300px;" class="popup">
		<div class="popupConfirmacao">
			<div class="popup_header">
				<div class="popup_box_top_center">
					<div class="box_titulo">Confirmação</div>
				</div>
				<div class="popup_box_top_left">
				</div>
				<div class="popup_box_top_right_close" onclick="$('popupLogin').hide();$('popupLoginCortina').hide();">
				</div>
			</div>
			<div class="popup_middle">
				<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
					<tr>
						<td class="box_middle_left">
						</td>
						<td valign="middle" align="center" style="font-size:12px;color:#7d7d7d;font-weight: bold;font-family:Verdana,Arial,Helvetica;padding-left:20px;padding-right: 20px;">
							<html:errors bundle="messages" property="erroLogin" />
							<c:if test="${alterarSenha != null}">

								<br>${alterarSenha}<br>
								<br><br>
								<html:link action="/br/com/vivo/catalogoPRS/pageflows/inicio/carregarAlterarSenha.do?alterar_senha_username=${alterar_senha_username}" onClick="return true;" styleclass="btNavegacao120" style="padding-top:3px;text-align: center;">
									Alterar Senha
								</html:link>
							</c:if>
							
							<c:if test="${senhaQuaseExpirando != null}">

								<br>A senha será expirada em ${senhaQuaseExpirando} dia(s) . Deseja alterar a senha?<br>
								<br><br>
								<html:link action="/br/com/vivo/catalogoPRS/pageflows/inicio/carregarAlterarSenha.do?alterar_senha_username=${alterar_senha_username}" onClick="return true;" styleclass="btNavegacao74" style="padding-top:3px;text-align: center;">
									Sim
								</html:link>
								&nbsp;
								<html:link href="/catalogo/br/com/vivo/catalogoPRS/pageflows/inicio/goHome.do" styleClass="btNavegacao74" style="padding-top:3px;text-align: center;">
									N&atilde;o
								</html:link>

							</c:if>
						</td>
						<td class="box_middle_right">
						</td>
					</tr>
				</table>
			</div>
			<div class="popup_bottom">
				<div class="popup_box_bottom_center">
				</div>
				<div class="popup_box_bottom_left">
				</div>
				<div class="popup_box_bottom_right">
				</div>
			</div>
		</div>
	</div>
		<div id="header">
			<div id="logo" class="Logo">
			</div>
		</div>
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/inicio/doLoginAction.do" >		
			<div align="center" style="width:1000px;">
			<table height="500px" border="0" cellpadding="2" cellspacing="3">
				<tr>
					<td height="150px;">&nbsp;</td>
				</tr>
				<tr>
					<td valign="top" align="center">
					<div class="tabelaLogin BgCinza_topoCenter">
					<div class="tabelaLogin BgCinza_bordaLeft">
					<div class="tabelaLogin BgCinza_bordaRight">
					<div class="tabelaLogin BgCinza_topoLeft">
					<div class="tabelaLogin BgCinza_baseCenter">
					<div class="tabelaLogin BgCinza_baseLeft">
					<div class="tabelaLogin BgCinza_baseRight">
					<div class="tabelaLogin BgCinza_topoRigth">
					<div class="tituloLogin"></div>
					
					<table width="90%" border="0" cellpadding="2" cellspacing="2" align="center">
						<tr>
							<td align="right"><span class="lblLogin">Usu&aacute;rio:</span></td>
							<td align="left">
							    <html:text property="usuario" styleId="usuario" style="width: 186px"/>
							</td>
						</tr>
						<tr>
							<td align="right"><span class="lblLogin">Senha:</span></td>
							<td align="left">
							    <html:password property="senha" styleId="senha" style="width: 186px"/>
							</td>
						</tr>
						<tr>
							<td></td>
							<td class="erroLogin" width="230px">							
								<%-- <html:errors name="usuario"/>  --%>
								<%-- <html:errors name="senha"/> --%>
								<html:errors bundle="messages" property="usuarioInvalido" />
								<html:errors bundle="messages" property="campoObrigatorio" />
							</td>
						</tr>
						<c:if test="${loginSucedido}">
							<tr>
								<td colspan="2" align="center">
									<html:button bundle="messages" property="btn_continuar" value="Continuar" styleClass="btNavegacao74" onclick="$('spinnerLogin').show();window.location.href='/catalogo/br/com/vivo/catalogoPRS/pageflows/inicio/goHome.do';" titleKey="catalogo.login.Continuar"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="sucessoLogin" align="center">
									<br>Login efetuado com sucesso.<br>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="sucessoLogin" width="290px">
									<br>
									Último login bem sucedido: <span><fmt:formatDate pattern="dd MMMMMMMMM, yyyy [hh:mm:ss]" value="${date}" /></span>
									 a partir do IP ${loginStats.ipLastLogin}.<br>
									Número de tentativas de login sem sucesso desde então: ${logged_user.numTentativasLogin}<br>
								</td>
							</tr>
						</c:if>
						
						
						<c:if test="${loginSucedido == null && senhaQuaseExpirando == null}">
							<tr>
								<td align="center" colspan="2" >
									<html:submit property="bt_acessar" value="Acessar" styleClass="btNavegacao74" bundle="messages" titleKey="catalogo.login.Acessar"/>
								</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="2" align="right"><img style="display:none;" id="spinnerLogin" src="/catalogo/static_server/img/tree/spinner.gif" />&nbsp;</td>
						</tr>
					</table>
					<div class="tituloLogin hide"></div>
					</div>
					</div>
					</div>
					</div>
					</div>
					</div>
					</div>
					</div>
					</td>
				</tr>
			</table>
			</div>
		</html:form>			
		</div>
    </body>
</html>