<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="netui" uri="http://beehive.apache.org/netui/tags-html-1.0"%>
<div id="AlterarCaracteristica" class="secao_conteudo">
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Alterar de Caracter&iacute;stica:</div>
				<div class="conteudo_box_top_esq"></div>
				<div class="conteudo_box_top_dir openclose"><img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif"
				alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" /></div>
		</div>
		<div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
					    <br>
						<div class="legendaObrigatorio">(*) Campo Obrigat&oacute;rio</div>
						<netui:form action="alterarCaracteristica">
						<netui:hidden dataSource="actionForm.idCaracteristica" dataInput="${id_caracteristica}"/>
						<table>
							<tr>
								<td width="20%">
									<div class="label-form-bold label_required">Nome&nbsp;Caracter&iacute;stica:<font size="1px" color="#EEB422" valign="center">*</font></div> 
									<netui:textBox styleClass="required editavel" dataSource="actionForm.nomeCaracteristica" defaultValue="${nome_caracteristica}" maxlength="200" onKeyPress="return semPontoVirgula(event);"/>
								</td>
							</tr>
							<tr>
								<td width="50%">
									<div class="label-form-bold">Descri&ccedil;&atilde;o: &nbsp;</div>
									<netui:textArea styleClass="editavel" defaultValue="${descricao_caracteristica}" dataSource="actionForm.descricaoCaracteristica" cols="47" rows="5" style="vertical-align:top;" onKeyUp="maxSize(200, this);"/>
								</td>
						
							</tr>
							<tr>
								<td>
									<div class="hide label-form-bold">* Justificativa:</div>
									<netui:textArea tagId="justificativa_alterar_caracteristica" styleClass="hide" dataSource="actionForm.justificativaAlteracao" cols="47" rows="3" style="vertical-align:top;"/>
								</td>
							</tr>
						</table>
						<br>
						<div class="barra">
						</div>
						<div style="text-align: right;" colspan="4">
							<input type="button" value="Gravar" class="btNavegacao74" onClick="clearEditando();if(!checkRequired($(this).form, 'altCaracteristica')){abrirPopup1($(this).next('a'), null, 'altCaracteristica')}else{return false;};setEditando();" title="${bundle.default['catalogo.AlterarCaracteristica.Gravar']}"/>
							<netui:anchor styleClass="hide" action="popupAlterarCaracteristica">
								<netui:parameter name="id_caracteristica" value="${id_caracteristica}"/>
							</netui:anchor>
							<input class="hide" type="button" id="botao_gravar_caracteristica" value="Gravar" class="btNavegacao74" onClick="clearEditando();send(this, null, null, 'altCaracteristica');clearEditando();" title="${bundle.default['catalogo.AlterarCaracteristica.Gravar']}"/>
							<input type="button" value="Cancelar" class="btNavegacao74 clearEditavel" onclick="$('altCaracteristica').hide()" title="${bundle.default['catalogo.AlterarCaracteristica.Cancelar']}"/>
						</div>
						</netui:form>
					</div>
				</div>
			<div class="conteudo_box_bottom"></div>
		</div>
	</div>
	<br>
</div>
