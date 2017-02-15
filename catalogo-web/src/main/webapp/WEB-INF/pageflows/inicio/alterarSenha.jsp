<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <base/>
        <style type="text/css">
	       	@import url("/catalogo/static_server/css/default.css");
			@import url("/catalogo/static_server/css/menu.css");
			@import url("/catalogo/static_server/css/conteudo.css");
			@import url("/catalogo/static_server/css/tabela.css");
			@import url("/catalogo/static_server/css/button.css");
			@import url("/catalogo/static_server/css/popup.css");
			@import url("/catalogo/static_server/css/form.css");
			@import url("/catalogo/static_server/css/image.css");
			@import url("/catalogo/static_server/css/header.css");
		</style>
		
		<script src="/catalogo/static_server/js/prototype.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/jquery.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/jquery.easing.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/jquery.metadata.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/jquery.media.js" type="text/javascript"></script>	
		<script src="/catalogo/static_server/js/jquery.validate.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/jquery.maskedinput.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/jquery.tablesorter.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/jquery.modal.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/webtoolkit.scrollabletable.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/webtoolkit.jscrollable.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/tree-pack.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/menu.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/help.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/functions.js" type="text/javascript"></script>
		<script src="/catalogo/CatalogoPRS.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/prototype.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/scriptaculous.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/table.js" type="text/javascript"></script>
		<script src="/catalogo/static_server/js/teclado.js" type="text/javascript"></script>
		<title>FrontOffice VIVO</title>
    </head>
    
    <!-- Se ocorreu algum erro não abre a tela de regras novamente -->
 		<c:if test="${flagPaginaAcessada == null}">
   		   <img src="/catalogo/static_server/img/transparent.gif" onLoad="abrirPopupRegrasAlteracaoSenha();" style="position: absolute;"/> 
    	</c:if>
    
    <body>
    	<div id="popup1Cortina" style="z-index: 10; display: none;" class="cortina">
		</div>
		<iframe id="popup1IF" style="display:none; background-color: transparent;" class="popupIF"></iframe>
		<div id="popup1" style="display:none; background-color: transparent;z-index: 25;top:100px;" class="popup">
		</div>
		<div id="header">
			<div id="logo" class="Logo">
			</div>
			<div id="header_close">
				<a href="/catalogo/br/com/vivo/catalogoPRS/pageflows/inicio/popupConfirmaLogout.do" onclick="abrirPopup1(this.href, null , 'div_alterar_senha');return false;">
					<img title="Fechar" alt="Fechar" src="/catalogo/static_server/img/botoes/btSair.gif"/>
				</a>
			</div>
		</div>
		<div id="largePopup" style="display:none;">
		</div>
		<!-- Regras de alteração de senha -->
		<div id="popup_regras_alteracao_senhaCortina" style="z-index: 80; display:none;" class="cortina">
		</div>
		<iframe id="popup_regras_alteracao_senhaIF" style="display:none; background-color: transparent;" class="popupIF"></iframe>
		<div id="popup_regras_alteracao_senha" style="display:none; background-color: transparent; z-index:90; width:500px" class="popup">
			<div class="popup_header">
				<div class="popup_box_top_center">
					<div class="box_titulo">Confirmação</div>
				</div>
				<div class="popup_box_top_left">
				</div>
				<div class="popup_box_top_right_close" onclick="fecharPopup('popup_regras_alteracao_senha');">
				</div>
			</div>
			<div class="popup_middle">
				<div class="popup_box_middle_center" style="height: 100%">
					<div id="popup_erros_conteudo" class="popup_box_middle_center_conteudo" align="left" style="position: relative;">
						<div style="height:150px;">
							<br clear="all"/>
							<div align="center" style="font-size: 13px;">
								<strong>
									A alteração da senha deve ser realizada conforme as instruções abaixo:
								</strong>
							</div>
							<br clear="all"/>
							<br clear="all"/>
							<ul id="popup_erros_ul" class="dados-list">
								<li>Deve possuir letras maiúsculas, minúsculas, números e caracteres especiais (!@#$%¨&*.,;&lt;&gt;).</li>
								<li>O tamanho deve ser de no mínimo 8 caracteres.</li>
								<li>Não deve conter seqüências numéricas (1234567).</li>
								<li>Não pode coincidir com o nome do usuário ou login.</li>
								<li>Não pode ser igual às ultimas senhas já utilizadas.</li>
							</ul>
							<br clear="all"/>
						</div>
						<div style="position:absolute; bottom:10px;" class="botao">
							<input class="btOk" type="button" onclick="fecharPopup('popup_regras_alteracao_senha');" value="OK"/>
						</div>
					</div>
				</div>
				<div class="popup_box_middle_left"></div>
				<div class="popup_box_middle_right"></div>
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
		
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/inicio/alterarSenha.do">
			<div id="div_alterar_senha" align="center" style="width:1000px;">
			<table height="500px" border="0" cellpadding="2" cellspacing="3" width="400px">
				<tr>
					<td valign="middle" align="center">
					<div class="tabelaLogin BgCinza_topoCenter">
					<div class="tabelaLogin BgCinza_bordaLeft">
					<div class="tabelaLogin BgCinza_bordaRight">
					<div class="tabelaLogin BgCinza_topoLeft">
					<div class="tabelaLogin BgCinza_baseCenter">
					<div class="tabelaLogin BgCinza_baseLeft">
					<div class="tabelaLogin BgCinza_baseRight">
					<div class="tabelaLogin BgCinza_topoRigth">
					<div class="tituloLogin">Alterar Senha</div>
					
					<table width="90%" border="0" cellpadding="2" cellspacing="2" align="center">
						<tr>
							<td width="55" align="left" nowrap="nowrap"><span class="lblLogin">Usu&aacute;rio:</span></td>
							<td align="left">
								<html:text property="usuario" styleId="usuario" value="${alterar_senha_username}" style="width: 186px" readonly="true"/>
							</td>
							
						</tr>
						<tr>
							<td align="left" nowrap="nowrap"><span class="lblLogin">Senha Atual:</span></td>
							<td align="left">
								<html:password property="senhaAtual" styleId="senhaAtual" style="width: 186px"/>
							</td>
							
						</tr>
						<tr>
							<td align="left" nowrap="nowrap"><span class="lblLogin">Nova Senha:</span></td>
							<td align="left">
								<html:password property="novaSenha" styleId="novaSenha" style="width: 186px"/>
							</td>
							
						</tr>
						<tr>
							<td align="left" nowrap="nowrap"><span class="lblLogin">Confirmar Senha:</span></td>
							<td align="left">
								<html:password property="novaSenhaConfirmar" styleId="novaSenhaConfirmar" style="width: 186px"/>
							</td>
							 
						</tr>
						<tr>
							<td colspan="2" align="center">
								<div align="center">
									${razao_alterar_senha}
								</div>
							</td>
						</tr>
						<tr>
							<td align="right" colspan="3">
								<div align="center"><br />
									<html:submit bundle="messages" value="Alterar" styleClass="btNavegacao74" title="Alterar" />
									<br/><br/>
									<label class="erroLogin"> <%-- <html:errors bundle="messages" name="erroAlterarSenha"/> --%></label>
								
									<br><br>
								</div>
							</td>
						</tr>
					</table>
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
		</html:form>
		</div>
    </body>
</html>