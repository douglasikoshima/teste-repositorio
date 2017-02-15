<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<div class="secao_conteudo">
	<div id="div_erros"></div>
	<div class="conteudo_box_top">
		<div class="conteudo_box_top_center">Consulta de Acesso a Planos e Servi&ccedil;os</div>
		<div class="conteudo_box_top_esq"></div>
		<div class="conteudo_box_top_dir openclose">
			<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"	alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
		</div>
	</div>
	<div>
		<div class="conteudo_box_middle">
			<html:form styleId="acaoForm" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/pesquisar.do">
			    <html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
				<div class="conteudo_box_middle_mg relative">
					<br clear="all" />
					<div id="conteudo_form_top" style="float: left">
						<div class="fleft">
							<div class="label-form-bold label_required" style="width:150px;">Pesquisar por:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:select property="idTpPesquisa" styleId="select_tipo_pesquisa" styleClass="required" onChange="changeAcesso($F(this));" style="width:230px;" tabindex="1">
								<html:option  value="1">Plano/Perfil</html:option>
								<html:option  value="2">Serviço/Perfil</html:option>
								<html:option  value="3">Perfil/Plano</html:option>
								<html:option  value="4">Perfil/Serviço</html:option>
							</html:select>								
						</div>
					</div>
				</div>
				<br clear="all" /><br clear="all" />
				<div class="barra"></div>
				<br clear="all" />
				<div id="div_parametros">
					<div id="conteudo_form_middle" class="fleft">
						<div id="perfil_scaCheck" style="display: block">
							<div class="label-form-bold label_required" style="width:150px;">Perfil:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:text property="idsPerfilCheck" styleId="hidden_perfilSCACheck" styleClass="hide required" style="width:210px"/>
							<html:text property="textfieldPerfilSCACheck" styleId="textfieldPerfilSCACheck" styleClass="width:210px" />
							<html:button bundle="messages" property="bot_perfilSCACheck" tabindex="2" styleId="bot_perfilSCACheck" onclick="abrirPopup1($(this).next().href, [$('hidden_perfilSCACheck')])" value="..." altKey="catalogo.acesso.planoServico.Perfil" titleKey="catalogo.acesso.planoServico.Perfil"/>
							<html:link property="lnkPerfilSCACheck" styleId="lnkPerfilSCACheck" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/listarPerfilSCA.do?perfil=1" paramId="hidden_perfilSCACheck" styleClass="hide"/>
						</div>
						<div id="perfil_scaRadio" style="display: none">
							<div class="label-form-bold label_required" style="width:150px;">Perfil:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:text property="idsPerfilRadio" styleId="hidden_perfilSCARadio" styleClass="hide" style="width:210px" />
							<html:text property="textfieldPerfilSCARadio" style="textfieldPerfilSCARadio" styleClass="width:210px" />
							<html:button bundle="messages" property="bot_perfilSCARadio" tabindex="3" styleId="bot_perfilSCARadio" onclick="abrirPopup1($(this).next().href, [$('hidden_perfilSCARadio')])" value="..." altKey="catalogo.acesso.planoServico.Perfil" titleKey="catalogo.acesso.planoServico.Perfil"/>
							<html:link property="lnkPerfilSCARadio" styleId="lnkPerfilSCARadio" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/listarPerfilSCA.do?perfil=2" paramId="hidden_perfilSCARadio" styleClass="hide"/>
						</div>
					</div>
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:150px;">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:select property="idPlataforma" styleId="select_plataforma" styleClass="required" style="width:230px;" tabindex="4">
								<html:option value="">-- Selecione --</html:option>
								<c:forEach var="plataformaTO" items="${plataformas}">
									<html:option value="${plataformaTO.idPlataforma}">${plataformaTO.nmPlataforma}</html:option>
								</c:forEach>
							</html:select>							
					</div>
					<br clear="all" /><br clear="all" />
					<div class="fleft">
						<div class="label-form-bold" style="width:150px;">Nome T&eacute;cnico:</div>
						<html:text property="nmTecnico" styleId="nmTecnico" tabindex="5" style="width:230px;"/>
					</div>
					<div class="fleft">
						<div id="nm_comercialPlanoRadio" style="display: block">
							<div class="label-form-bold" style="width:150px;">Nome Comercial:</div>
							<html:text property="nmComercialPlano" styleId="hidden_nmComercial_planoRadio" styleClass="hide" style="width: 150px" />
							<html:text style="width:210px;" property="nmComercialPlanoTextBox" styleId="hidden_nmComercial_planoTextBox"/>
							<html:button property="bot_nm_planoRadio" tabindex="6" styleId="bot_nm_planoRadio" onclick="abrirPopup1($(this).next().href, [$('hidden_nmComercial_planoRadio')]); clearAndDisableTextBox('hidden_nmComercial_planoTextBox')" value="..."/>
							<html:link property="lnkNmComercialPlanoRadio" styleId="lnkNmComercialPlanoRadio" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/preencheNmComercial.do?nmComercial=1" styleClass="hide"/>
						</div>
						<div id="nm_comercialServicoRadio" style="display: none">
							<div class="label-form-bold" style="width:150px;">Nome Comercial:</div>
							<html:text property="nmComercialServico" styleId="hidden_nmComercial_servicoRadio" styleClass="hide" style="width: 150px"/>
							<html:text style="width:210px;" property="nmComercialServicoTextBox" styleId="hidden_nmComercial_servicoTextBox"/>
							<html:button property="bot_nm_servicoRadio" tabindex="7" styleId="bot_nm_servicoRadio" onclick="abrirPopup1($(this).next().href, [$('hidden_nmComercial_servicoRadio')]); clearAndDisableTextBox('hidden_nmComercial_servicoTextBox')" value="..."/>
							<html:link property="lnkNmComercialServicoRadio" styleId="lnkNmComercialServicoRadio" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/preencheNmComercial.do?nmComercial=2" styleClass="hide"/>
						</div>
						<div id="nm_comercialPlanoCheck" style="display: none">
							<div class="label-form-bold" style="width:150px;">Nome Comercial:</div>
							<html:text property="idPlano" styleId="hidden_nmComercial_planoCheck" styleClass="hide" style="width: 150px"/>
							<html:text style="width:210px;" property="nmComercialPlanoTextBox2" styleId="hidden_nmComercial_planoTextBox2"/>
							<html:button property="bot_nm_plano_check" tabindex="8" styleId="bot_nm_plano_check" onclick="abrirPopup1($(this).next().href, [$('hidden_nmComercial_planoCheck')]); clearAndDisableTextBox('hidden_nmComercial_planoTextBox2')" value="..."/>
							<html:link property="lnkIdPlanoCheck" styleId="lnkIdPlanoCheck" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/preencheNmComercial.do?nmComercial=3" styleClass="hide"/>
						</div>
						<div id="nm_comercialServicoCheck" style="display: none">
							<div class="label-form-bold" style="width:150px;">Nome Comercial:</div>
							<html:text property="idServico" styleId="hidden_nmComercial_servicoCheck" styleClass="hide" style="width: 150px"/>
							<html:text style="width:210px;" property="nmComercialServicoTextBox2" styleId="hidden_nmComercial_servicoTextBox2"/>
							<html:button property="bot_nm_servicoCheck" tabindex="9" styleId="bot_nm_servicoCheck" onclick="abrirPopup1($(this).next().href, [$('hidden_nmComercial_servicoCheck')]); clearAndDisableTextBox('hidden_nmComercial_servicoTextBox2')" value="..."/>
							<html:link property="lnkIdServicoCheck" styleId="lnkIdServicoCheck" action="/br/com/vivo/catalogoPRS/pageflows/consulta/acesso/planosServicos/preencheNmComercial.do?nmComercial=4" styleClass="hide"/>
						</div>
					</div>
					<br clear="all" /><br clear="all" />
					<div class="barra"></div>
					<div class="botao">
						<html:button bundle="messages" tabindex="11" property="bot_limpar" styleId="bot_limpar" styleClass="btNavegacao74" onClick="clearAndShow('resultado_pesquisa');limparForm(this);return false;" value="Limpar" titleKey="catalogo.global.Limpar"/>
						<span>&nbsp;</span>
						<html:button tabindex="10" property="bot_pesquisa" styleId="bot_pesquisa" onMouseDown="$('pagina_solicitada').value='1'" onClick="clearAndShow('resultado_pesquisa');send(this, 'resultado_pesquisa', null , 'div_erros', null);" styleClass="btNavegacao74" value="Pesquisar"/>
					</div>
				</div>
			</html:form>
		</div>
		<div class="conteudo_box_bottom">
		</div>
	</div>
</div>
<br/>

<br/>
<div id="resultado_pesquisa" style="position: relative;"></div>