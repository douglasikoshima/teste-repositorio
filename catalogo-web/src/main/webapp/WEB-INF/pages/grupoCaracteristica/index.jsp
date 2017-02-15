<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="catalogo" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display" %>

<catalogo:defaultTemplate titulo="Catalogo">

		<jsp:attribute name="headScripts">	
			
			<script>			
				function novo() {
					document.getElementById("gravar_caracteristica").style.display = '';
					document.getElementById("idGrupoCaracteristica").value = "";
					document.getElementById("caracteristica").value = "";
					document.getElementById("divErrosMidle").style.display = 'none';
					document.getElementById('caracteristicaAssociada').value = "";
					document.location.href = '#gravar_caracteristica';
					document.getElementById("divSucess").style.display = "none";
					document.getElementById("grupoCaracteristicaForm").reset();
				}
				function cancelar() {
					document.getElementById("gravar_caracteristica").style.display = 'none';
					document.getElementById("divErrosMidle").style.display = 'none';
				}				
				function gravar() {
					if (validarForm()) {
						if (document.getElementById("caracteristicaAssociada").value == 'true' ) {
							if (confirm("Existem características associadas a esse grupo. Deseja alterá-lo?")) {
								document.getElementById("grupoCaracteristicaForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/grupoCaracteristica/gravar.do";
								document.getElementById("grupoCaracteristicaForm").submit();
							}
						} else {
							document.getElementById("grupoCaracteristicaForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/grupoCaracteristica/gravar.do";
							document.getElementById("grupoCaracteristicaForm").submit();
						}
					}
				}
				
				function alterar(idGrupoCaracteristica,nmGrupoCaracteristica,iconeGrupoCaracteristica,caracteristicaAssociada) {			
					document.getElementById("idGrupoCaracteristica").value = idGrupoCaracteristica;
					document.getElementById("caracteristica").value = nmGrupoCaracteristica;
					document.getElementById("nomeIcone").value = iconeGrupoCaracteristica;
 					document.getElementById("caracteristicaAssociada").value = caracteristicaAssociada;
					document.getElementById("gravar_caracteristica").style.display = '';
					document.location.href = '#gravar_caracteristica';
					document.getElementById("divSucess").style.display = "none";
					
				}
				
				function excluir(obj,nomeIcone) {					
					if (confirm("Tem certeza que deseja excluir este Grupo de Característica?")) {
						document.getElementById("idGrupoCaracteristica").value = obj;
						document.getElementById("nomeIcone").value = nomeIcone;
						document.getElementById("grupoCaracteristicaForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/grupoCaracteristica/excluir.do";
						document.getElementById("grupoCaracteristicaForm").submit();
					}
				}
				
				function validarForm() {
					
					var camposErro = "";
					var retorno = true;
					
					if (document.getElementById("caracteristica").value == "") {
						camposErro += "O preenchimento do campo grupo é obrigatório";
					} 
					
					if (camposErro != "") {
						generateContentErrorForm(camposErro);
						retorno = false;
					}
					
					return retorno;
				}
				
				function isType(path, type) {
					var lengthPath = new Number(path.length);
					if (path.indexOf(type) == -1 || 
						(lengthPath - path.indexOf(type)) != 4) {	
						return false;					
					}	
					return true;
				}
			</script>
		</jsp:attribute>
			
		<script>carregaMenu('mn_parametrizacao_grupo_caracteristicas');</script>
		<div class="secao_conteudo">
		<c:if test="${grupoCaracteristicaList != null}">
			<catalogo:contentBox title="Consultar grupo de característica" doubt="true">
				<br />
				<display:table name="grupoCaracteristicaList" id="grupoCaracteristicaTO" pagesize="15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "GrupoCaracteristicaAction.do" class="tabela-padrao-new tablesorter table_body" >
					<display:column title="Grupo de Característica" sortable="true" headerClass="sortable" >
						<html:link href="javascript:alterar(${grupoCaracteristicaTO.idGrupoCaracteristica},'${grupoCaracteristicaTO.nmGrupoCaracteristica}','${grupoCaracteristicaTO.iconeGrupoCaracteristica}', '${grupoCaracteristicaTO.existCaracteristicaAssociada}')">${grupoCaracteristicaTO.nmGrupoCaracteristica}</html:link>
					</display:column>
					<display:column title="Excluir" style="width:50px; text-align:center;" >
						<html:link href="javascript:excluir(${grupoCaracteristicaTO.idGrupoCaracteristica},'${grupoCaracteristicaTO.iconeGrupoCaracteristica}')">
							<html:img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Grupo de característica" />
						</html:link>
					</display:column>
					<display:footer >
						<tr>
							<td colspan="7">
							 	<table class="tabelaIcones">
									<tr>
										<td width="50px;"><label>&Iacute;cones:</label></td>
										<td width="15"><img src="/catalogo/static_server/img/botoes/bt-excluir.gif" alt="Excluir Grupo de Característica"/></td>
										<td>Excluir Grupo</td>
									</tr>
								</table>
							</td>
						</tr>
					</display:footer>
				</display:table>
				<br clear="all"/><br clear="all"/>
				<div class="barra"></div>
				<div class="botao">
					<html:button tabindex="2" styleId="botao_novo_form" property="btn_novo" onClick="novo();" styleClass="btNavegacao74" value="Novo" alt="" title=""/>
				</div>
			</catalogo:contentBox>
		</c:if>
		<div id="divErrosMidle" style="display:none; background-color: white;position:relative;"><catalogo:contentError id="contentErrorForm" /></div>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/produtos/grupoCaracteristica/gravar.do" styleId="grupoCaracteristicaForm" enctype="multipart/form-data" >		
		<html:hidden property="idGrupoCaracteristica" styleId="idGrupoCaracteristica"/>
		<html:hidden property="nomeIcone" styleId="nomeIcone"/>
		<html:hidden property="caracteristicaAssociada" styleId="caracteristicaAssociada"/>
			<div id="gravar_caracteristica" style="display:none">
			<catalogo:contentBox title="Associar Ícone e Grupo de Caracter&iacute;sticas" requiredFields="true">
				<div class="fleft" style="width: 60%; margin-bottom: 5px;">
					<div class="label-form-bold label_required" style="width: 50px;">Grupo:<font size="1px" color="#EEB422">*</font></div>
					<html:text tabindex="1" styleId="caracteristica" property="caracteristica" styleClass="required" size="50" />
				</div>
				
				<br clear="all"/><br clear="all"/>
				<div class="barra"></div>
				<div class="botao">											
				    <html:button tabindex="3" styleId="botao_cancelar_form" property="btn_cancelar" onClick="cancelar();" styleClass="btNavegacao74" value="Cancelar" alt="" title=""/>					    				    
				    <span>&nbsp;</span>
				    <html:button tabindex="2" styleId="botao_gravar_form" property="btn_gravar" onClick="gravar();" styleClass="btNavegacao74" value="Gravar" alt="" title=""/>					    				    
				</div>								
			</catalogo:contentBox>
			</div>
			<c:if test="${labelError != null}">
			<script>
				generateContentErrorForm("${labelError}")
				document.getElementById("gravar_caracteristica").style.display = '';
				document.location.href = "#gravar_caracteristica";
			</script>
		</c:if>
		</html:form>
			<c:if test="${errorLabel != null}">
			<script>
				document.getElementById('conteudo_divErros').innerHTML = "${errorLabel}";
				document.getElementById('divErros').style.display = "inline";				
			</script>
			</c:if>					
		</div>
</catalogo:defaultTemplate>		