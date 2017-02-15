<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/CatalogoPRS.tld" prefix="cata" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>

<div class="secao_conteudo">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Inclus&atilde;o de Restri&ccedil;&otilde;es</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<html:img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
	</div>
	
	<div>
		<div class="conteudo_box_middle">
			<div class="conteudo_box_middle_mg relative">
			<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/gravarNovasRestricoesAcessoPlanoServico.do" styleId="form_acesso_plno_servico">											
					<html:hidden property="paginaSolicitada" styleId="pagina_solicitada"/>
					
					<br clear="all" />
					<div class="fleft">
						<div class="label-form-bold label_required" style="width: 160px">Tipo da Restri&ccedil;&atilde;o:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<div style="padding: 0px 56px 0px 0px; float:left;">
								<html:radio property="radioPlano" styleId="radioPlano" value="plano" styleClass="semBorda" onClick="setTipoRestricao(this, 'div_nome_comercial_plano', 'div_nome_comercial_servico', 'hidden_nmComercial_nova_restricao_plano', 'hidden_nmComercial_nova_restricao_servico');setChecked(this);">
									<b>Plano</b>
								</html:radio>
							</div>
							<div style="padding: 0px 50px 0px 10px; float: left;">
								<html:radio property="radioServico" styleId="radioServico" value="servico" styleClass="semBorda" onClick="setTipoRestricao(this, 'div_nome_comercial_plano', 'div_nome_comercial_servico', 'hidden_nmComercial_nova_restricao_plano', 'hidden_nmComercial_nova_restricao_servico');setChecked(this);">
									<b>Servi&ccedil;o</b>
								</html:radio>
							</div>
					</div>
					
					<br clear="all" /><br clear="all" />
					<div id="div_nome_comercial_plano">
						<div class="fleft">																								
							<div class="label-form-bold label_required" style="width: 160px;">Nome Comercial:</div>
							<html:text property="nmComPlanoPerfilPop" styleId="hidden_nmComercial_nova_restricao_plano" styleClass="hide nmComHidden"/>
							<html:text property="txtnmComPlanoPerfilPop"  style="width: 190px" readonly="true"/>
							<html:button bundle="messages" property="btn_nmCom_plano" tabindex="7" onclick="abrirPopup1($(this).next().href, [$('hidden_nmComercial_nova_restricao_plano')])" value="..." altKey="catalogo.matrizOferta.itens.Organizacoes" titleKey="catalogo.matrizOferta.itens.Organizacoes"/>
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/buscarListaNmComercialRestricaoPlanoServico.do?tp_pesquisa=planoPerfil" styleClass="hide" />
						</div>
					</div>
					<div id="div_nome_comercial_servico" style="display: none">
						<div class="fleft">						
							<div class="label-form-bold label_required" style="width: 160px;">Nome Comercial:</div>
							<html:text property="nmComServicoPerfilPop" styleId="hidden_nmComercial_nova_restricao_servico" styleClass="hide nmComHidden"/>
							<html:text property="txtnmComServicoPerfilPop" style="width: 190px" readonly="true"/>
							<html:button bundle="messages" property="btn_nmCom_servico" tabindex="7" onclick="abrirPopup1($(this).next().href, [$('hidden_nmComercial_nova_restricao_servico')])" value="..." altKey="catalogo.matrizOferta.itens.Organizacoes" titleKey="catalogo.matrizOferta.itens.Organizacoes"/>
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/buscarListaNmComercialRestricaoPlanoServico.do?tp_pesquisa=servicoPerfil" styleClass="hide" />
						</div>
					</div>
					
					<div id="div_perfil_plano">
						<div class="fleft">
							<div class="label-form-bold label_required" style="width: 120px;">Perfil:<font size="1px" color="#EEB422" valign="center">*</font></div>
							<html:text property="idsPerfilPpSp" styleId="hidden_lista_perfil_nova_restricao_plano_servico" styleClass="hide required" />							
							<html:text property="textfield_plano_perfil" styleId="textfield_plano_perfil" readonly="true" style="width: 195px"/>
							<html:button bundle="messages" property="btn_perfil_plano" tabindex="2" onclick="abrirPopup1($(this).next().href, [$('hidden_lista_perfil_nova_restricao_plano_servico')])" value="..." altKey="catalogo.matrizOferta.itens.Organizacoes" titleKey="catalogo.matrizOferta.itens.Organizacoes" />
							<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/buscarListaPerfilSCANovaRestricaoPlanoServico.do" styleClass="hide" />
						</div>
					</div>
		
					<br clear="all" /><br clear="all" />
					
					<div class="fleft">
						<div class="label-form-bold" style="width: 160px;">Restri&ccedil;&atilde;o:</div>
						<div class="fleft" style="width: 120px">
							<html:checkbox property="ckb_consulta" styleId="ckb_consulta" onClick="setValueArrayRestricoes(this.checked, 'checkConsulta'); setCheckedAllRestricoes(this, $('ckb_desativacao'), $('ckb_ativacao'))" styleClass="semBorda belongsToForm"/>
							<html:hidden property="consulta" styleId="checkConsulta"/>
							<label>Consulta</label>
						</div>
					</div>
					<div class="fleft" style="width: 120px">
						<html:checkbox property="ckb_ativacao" styleId="ckb_ativacao" onClick="setValueArrayRestricoes(this.checked, 'checkAtivacao')" styleClass="semBorda belongsToForm"/>						
						<html:hidden property="ativacao" styleId="checkAtivacao"/>
						<label>Ativa&ccedil;&atilde;o</label>
					</div>
					<div class="fleft" style="width: 120px">
						<html:checkbox property="ckb_desativacao" styleId="ckb_desativacao" onClick="setValueArrayRestricoes(this.checked, 'checkDesativacao')" styleClass="semBorda belongsToForm"/>						
						<html:hidden property="desativacao" styleId="checkDesativacao" />						
						<label>Desativa&ccedil;&atilde;o</label>
					</div>
					
					<br clear="all" /><br clear="all" />
					<div class="barra"></div>
					<div class="botao">
						<html:button bundle="messages" tabindex="17" styleId="botao_cancelar_restricoes_plano_servico" property="btn_cancelar_restric_pla_serv" onClick="limparForm(this);clearAndShow('div_incluir_novas_restricoes');return false" styleClass="btNavegacao74" value="Cancelar" titleKey="catalogo.global.Limpar"/>
						<span>&nbsp;</span>																																															                 
						<html:button tabindex="16" styleId="botao_gravar_restricoes_plano_servico" property="btn_gravar_restic_pla_serv" styleClass="btNavegacao74" value="Gravar" onClick="if(send(this, 'resultado_pesquisa', null, 'div_erro_nova_restricao')){return false}"/>
						<html:link styleId="idLinkPopUpSuccess" action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/planosServicos/openPopupConfirm.do" styleClass="hide" onclick="abrirPopup1(this.href, null, 'div_include_popup_confirm');return false"/>
						<html:link action="/br/com/vivo/catalogoPRS/pageflows/param/acesso/openPopupConfirmError.do" styleId="idLinkPopUpError" styleClass="hide" onClick="abrirPopup1(this.href, null, 'div_include_popup_confirm');return false"/>
					</div>
				</html:form>
			</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>