<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- <div> -->
<div class="secao_conteudo">
	<fmt:bundle basename="catalogoprs_messages" >
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Parametriza&ccedil;&atilde;o de Acesso a Planos e Servi&ccedil;os</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<html:img styleId="img_expand" onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
	<!-- </div> -->
	<div id="restricoes_acesso_plano_servico">
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
				<html:form styleId="form_acesso_plno_servico" action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/pesquisarAcessoPlanosServicos.do" >
					<html:hidden property="paginaSolicitada" styleId="pagina_solicitada" />
					<div class="legendaObrigatorio help">(*) Campo Obrigat&oacute;rio</div>
		            <div class="link_manual" title="Dúvida">
		            	<fmt:message key="param.consulta.modelo" var="paramConsultaModelo"/>
		            	<html:link href="/catalogo/static_server/manual/manual_catalogo.html#${paramConsultaModelo}" target="_blank">
		            		<html:img src="/catalogo/static_server/img/botoes/bt-duvida.gif"/>
		            	</html:link>
					</div>
					
					<div id="conteudo_form_top" style="float: left">
						<div class="fleft">
							<div class="label-form-bold label_required" >Pesquisar Por:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:select property="tpPesquisa" styleId="select_tpPesquisa" tabindex="1" value="" styleClass="required" style="width:214px" 
							onChange="clearAndShow('resultado_pesquisa');limparTextfieldNmComercial($(this).up('form').select('.nmComTextfild'));definirTipoPesquisaAcessoPerfil($F(this), 'textfield_nmComercial_')">
								<html:option value="">-- Selecione --</html:option>
								<html:option value="planoPerfil">Plano/Perfil</html:option>
								<html:option value="servicoPerfil">Serviço/Perfil</html:option>
								<html:option value="perfilPlano">Perfil/Plano</html:option>
								<html:option value="perfilServico">Perfil/Servi&ccedil;o</html:option>
							</html:select>
						</div>
					</div>
					
					<br clear="all" /><br clear="all" />
					<div class="barra"></div>
					<br clear="all" />
					
					<div id="div_perfil_planoPerfilOuServicoPerfil">
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 140px;">Perfil:</div>							
							<html:text property="idsPerfilPpSp" styleId="hidden_lista_perfil_plano_perfil" styleClass="hide"/>														
							<html:text property="textfield_plano_perfil" styleId="textfield_plano_perfil" readonly="true" styleClass="width: 230px;"  />							
							<html:button bundle="messages" property="btnPerfil_Plano_Perfil" styleId="btnPerfil_Plano_Perfil" tabindex="2" onclick="abrirPopup1($(this).next().href, [$('hidden_lista_perfil_plano_perfil')])" value="..." altKey="catalogo.acesso.planoServico.Perfil" titleKey="catalogo.acesso.planoServico.Perfil" />														
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/buscarListaPerfilSCAPlanoPerfilServicoPerfil.do" property="lnkPlano_Perfil_Servico_Perfil" styleId="lnkPlano_Perfil_Servico_Perfil" paramId="hidden_lista_perfil_plano_perfil" styleClass="hide"/>														
						</div>
					</div>
					<div id="div_perfil_perfilPlanoOuPerfilServico" style="display: none">
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 140px;">Perfil:</div>
							<html:text property="idsPerfilPpPs" styleId="hidden_lista_perfil_servico_perfil" styleClass="hide"/>							
							<html:text property="textfield_servico_perfil" styleId="textfield_servico_perfil" readonly="true" styleClass="width: 230px;" />							
							<html:button bundle="messages" property="btnPerfil_Servico_Perfil" styleId="btnPerfil_Servico_Perfil" tabindex="3" onclick="abrirPopup1($(this).next().href, [$('hidden_lista_perfil_servico_perfil')])" value="..." altKey="catalogo.acesso.planoServico.Perfil" titleKey="catalogo.acesso.planoServico.Perfil" />							
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/buscarListaPerfilSCAPerfilPlanoPerfilServico.do" property="lnkPerfil_Plano_Perfil_Servico" styleId="lnkPerfil_Plano_Perfil_Servico" paramId="hidden_lista_perfil_servico_perfil" styleClass="hide" />
						</div>
					</div>
					
					<div class="fleft">
						<div class="label-form-bold label_required" style="width:160px;">Plataforma:<font size="1px" color="#EEB422" valign="center">*</font></div>
						<html:select property="idPlataforma" styleId="select_plataforma" styleClass="required" style="width:209px;" tabindex="4">
							<html:option value="">-- Selecione --</html:option>
							<c:forEach var="plataformaTO" items="${plataformas}">
								<html:option value="${plataformaTO.idPlataforma}">${plataformaTO.nmPlataforma}</html:option>
							</c:forEach>
						</html:select>
					</div>
					
					<br clear="all" /><br clear="all" />
					<div class="fleft">
						<div class="label-form-bold" style="width:140px;">Nome T&eacute;cnico:</div>
						<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome T&eacute;cnico".</div>
						<div class="hide min_size_required_value">3</div>
						<html:text property="nmTecnico" styleId="nome_tecnico" tabindex="5" style="width:230px;" styleClass="min_size_required"/>						
					</div>
					
					<div id="div_nmComercial_planoPerfilRadio">
						<div class="fleft">
							<div class="label-form-bold" style="width: 160px;">Nome Comercial:</div>
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome Comercial".</div>
							<div class="hide min_size_required_value">3</div>													
							<html:text property="nmComPlanoPerfilPop" styleId="hidden_nmComercial_plano_perfil" styleClass="hide nmComHidden"/>
							<html:text property="nmComPlanoPerfilText" styleId="textfield_nmComercial_0" tabindex="6" style="width: 190px"  styleClass="min_size_required nmComTextfild" onkeyup="desabilitarPopup($F(this), 'popupPlanoPerfil', $(this).up('form').select('.nmComHidden'))"/>
							<html:button bundle="messages" property="popupPlanoPerfil" styleId="popupPlanoPerfil" tabindex="7" onclick="clearAndDisableTextBox('textfield_nmComercial_0');abrirPopup1($(this).next().href, [$('hidden_nmComercial_plano_perfil')])" value="..." altKey="catalogo.acesso.planoServico.nmComercial" titleKey="catalogo.acesso.planoServico.nmComercial"/>							
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/buscarListaNmComercial.do?tp_pesquisa=planoPerfil" styleClass="hide" paramId="tp_pesquisa" paramName="parametrizacaoAcessoForm" paramProperty="planoPerfil" paramScope="request"/>
						</div>
					</div>
					<div id="div_nmComercial_servicoPerfilRadio" style="display: none">
						<div class="fleft">
							<div class="label-form-bold" style="width: 160px;">Nome Comercial:</div>
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome Comercial".</div>
							<div class="hide min_size_required_value">3</div>						
							<html:text property="nmComServicoPerfilPop" styleId="hidden_nmComercial_servico_perfil" styleClass="hide nmComHidden"/>
							<html:text property="nmComServicoPerfilText" styleId="textfield_nmComercial_1" tabindex="8" style="width: 190px" styleClass="min_size_required nmComTextfild" onkeyup="desabilitarPopup($F(this), 'popupPlanoPerfil', $(this).up('form').select('.nmComHidden'))"/>
							<html:button bundle="messages" property="popupServicoPerfil" styleId="popupServicoPerfil" tabindex="9" onclick="clearAndDisableTextBox('textfield_nmComercial_1');abrirPopup1($(this).next().href, [$('hidden_nmComercial_servico_perfil')])" value="..." altKey="catalogo.acesso.planoServico.nmComercial" titleKey="catalogo.acesso.planoServico.nmComercial"/>							
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/buscarListaNmComercial.do?tp_pesquisa=servicoPerfil" styleClass="hide" paramId="tp_pesquisa" paramName="parametrizacaoAcessoForm" paramProperty="servicoPerfil" paramScope="request" />
						</div>
					</div>
					<div id="div_nmComercial_perfilPlanoCheckbox" style="display: none">
						<div class="fleft">
							<div class="label-form-bold" style="width: 160px;">Nome Comercial:</div>
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome Comercial".</div>
							<div class="hide min_size_required_value">3</div>							
							<html:text property="nmComPerfilPlanoPop" styleId="hidden_nmComercial_perfil_plano" styleClass="hide nmComHidden" />							
							<html:text property="nmComPerfilPlanoText" styleId="textfield_nmComercial_2" tabindex="10" style="width: 190px" styleClass="min_size_required nmComTextfild" onkeyup="desabilitarPopup($F(this), 'popupPlanoPerfil', $(this).up('form').select('.nmComHidden'))" />
							<html:button bundle="messages" property="popupPerfilPlano" styleId="popupPerfilPlano" tabindex="11" onclick="clearAndDisableTextBox('textfield_nmComercial_2');abrirPopup1($(this).next().href, [$('hidden_nmComercial_perfil_plano')])" value="..." altKey="catalogo.acesso.planoServico.nmComercial" titleKey="catalogo.acesso.planoServico.nmComercial" />														
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/buscarListaNmComercial.do?tp_pesquisa=perfilPlano" styleClass="hide" paramId="tp_pesquisa" paramName="parametrizacaoAcessoForm" paramProperty="perfilPlano" paramScope="request"/>
						</div>
					</div>
					<div id="div_nmComercial_perfilServicoCheckbox" style="display: none">
						<div class="fleft">
							<div class="label-form-bold" style="width: 160px;">Nome Comercial:</div>
							<div class="hide min_size_required_message">Obrigat&oacute;rio informar, no m&iacute;nimo, 3 caracteres para o campo "Nome Comercial".</div>
							<div class="hide min_size_required_value">3</div>
							<html:text property="nmComPerfilServicoPop" styleId="hidden_nmComercial_perfil_servico" styleClass="hide nmComHidden" />							
							<html:text property="nmComPerfilServicoText" styleId="textfield_nmComercial_3" tabindex="12" style="width: 190px" styleClass="min_size_required nmComTextfild" onkeyup="desabilitarPopup($F(this), 'popupPlanoPerfil', $(this).up('form').select('.nmComHidden'))"  />
							<html:button bundle="messages" property="popupPerfilServico" styleId="popupPerfilServico" tabindex="13" onclick="clearAndDisableTextBox('textfield_nmComercial_3');abrirPopup1($(this).next().href, [$('hidden_nmComercial_perfil_servico')])" value="..." altKey="catalogo.acesso.planoServico.nmComercial" titleKey="catalogo.acesso.planoServico.nmComercial" />							
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/buscarListaNmComercial.do?tp_pesquisa=perfilServico" styleClass="hide" paramId="tp_pesquisa" paramName="parametrizacaoAcessoForm" paramProperty="perfilServico" paramScope="request"  />
						</div>
					</div>
					
					<br clear="all" /><br clear="all" />
					<div class="barra"></div>
					<div class="botao">
						<html:button bundle="messages" tabindex="17" property="btnLimpar" styleId="btnLimpar" onClick="habilitarTextBoxNmComercial('textfield_nmComercial_', 4);$('resultado_pesquisa').hide();limparForm(this);$('div_incluir_novas_restricoes').hide();return false;" styleClass="btNavegacao74" value="Limpar" titleKey="catalogo.global.Limpar"/>
						<span>&nbsp;</span>												
						<html:button tabindex="16" property="botao_pesquisar_acesso_plano_servico" styleId="botao_pesquisar_acesso_plano_servico" styleClass="btNavegacao74" value="Pesquisar" onMouseDown="$('pagina_solicitada').value='1';" onClick="clearAndShow('resultado_pesquisa');$('div_incluir_novas_restricoes').hide();send(this, 'resultado_pesquisa', null, 'right_section')"/>						
						<span>&nbsp;</span>
						<cata:temPermissao acao="configurarPerfilAcesso">
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/abrirPopupCopiarRestricoes.do" styleId="botao_copiar_restricao_acesso" onClick="if(abrirPopup1(this.href, null , 'resultado_pesquisa'))return false;">
								<html:button tabindex="15" property="btnCopiarRestricoes" styleId="btnCopiarRestricoes" styleClass="btNavegacao120" value="Copiar Restri&ccedil;&atilde;o" title=""/>
							</html:link>																			
						</cata:temPermissao>
						<span>&nbsp;</span>
						<cata:temPermissao acao="configurarPerfilAcesso">
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/incluirNovasRestricoesAcesso.do" styleId="botao_nova_restricao_acesso_plano_servico" onClick="if(abrirLink('div_incluir_novas_restricoes', this.href, 'right_section')){$('resultado_pesquisa').hide();clearAndShow('div_incluir_novas_restricoes');}return false">
								<html:button property="btnIncluirRestric" styleId="btnIncluirRestric" styleClass="btNavegacao120" value="Incluir Restri&ccedil;&atilde;o" alt="" title="" />
							</html:link>
						</cata:temPermissao>
					</div>
				</html:form>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
	</fmt:bundle>	
</div>

<br clear="all" />
<div id="resultado_pesquisa" style="position: relative; color: #FFFFFF"></div>
<div id="div_erro_nova_restricao" style="position: relative;"></div>
<div id="div_incluir_novas_restricoes" style="display: none;"></div>
<div id="div_include_popup_confirm" style="position: relative; color: #FFFFFF"></div>
