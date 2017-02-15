<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:blankTemplate titulo="Home Catalogo">

    <jsp:attribute name="headScripts">
    	<script>
			var $jq = jQuery.noConflict();
			function pesquisar() {
				$jq("#resultado_pesquisa").css('display','none');
				if (document.getElementById('cdServicoCatalogoMestre').value == "" &&
					document.getElementById('cdServicoCatalogoSubordinado').value == "" &&
					document.getElementById('cdServicoOrigemMestre').value == "" &&
					document.getElementById('cdServicoOrigemSubordinado').value == "" &&
					document.getElementById('cdTipoServicoMestre').value == "" &&
					document.getElementById('cdTipoServicoSubordinado').value == "" &&
					document.getElementById('nmServicoCatalogoMestre').value == "" &&
					document.getElementById('nmServicoCatalogoSubordinado').value == "" &&
					document.getElementById('nmServicoOrigemMestre').value == "" &&
					document.getElementById('nmServicoOrigemSubordinado').value == "") {
					$jq("#msg_obrigatorio").css('display','block');
					return;
				}
				$jq("#msg_obrigatorio").css('display','none');
				document.getElementById('acaoForm').submit();
			}
			
			function exportar(obj) {
				var clazz = $jq(obj).attr("class");
				if (clazz == "btNavegacao74") {
					document.location.href ='/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/relacionamento/exportar.do';
				}
			}
			
			function init() {
				if (document.getElementById('erro').value != "") {
					document.getElementById('divErros').style.display = "block";
			       	document.getElementById('conteudo_divErros').innerHTML = document.getElementById('erro').value;
				}
			}
    	</script>
    </jsp:attribute>
    
    <jsp:body>
        <html:form styleId="acaoForm" action="/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/relacionamento/search.do" >
        	<div class="line">
		        <catalogo:contentBox title="Pesquisa" doubt="false" requiredFields="false">
					<div class="line" id="msg_obrigatorio" style="display:none;">
			        	<div class="box_middle_center_conteudo" style="color:red !important;">Favor preencher ao menos um par&acirc;metro da pesquisa do Servi&ccedil;o</div>
			        	<div class="barra"></div>
					</div>
		        	<div class="line">
		        		<div class="linerow bold"><label style="color:#0364cb !important;">Servi&ccedil;o Rela&ccedil;&atilde;o</label></div>
		        	</div>
					<div class="line">
						<div class="linerow bold"><label for="cdTipoServicoMestre">Tipo de Servi&ccedil;o Rela&ccedil;&atilde;o:</label></div>
						<div class="linerow bold"><label for="cdServicoOrigemMestre">C&oacute;digo do Servi&ccedil;o Origem:</label></div>
						<div class="linerow bold"><label for="cdServicoCatalogoMestre">C&oacute;digo do Servi&ccedil;o Cat&aacute;logo:</label></div>
						<div class="linerow bold"><label for="nmServicoOrigemMestre">Nome do Servi&ccedil;o Origem:</label></div>
					</div>
					<div class="line">
						<div class="linerow bold">
							<html:select property="cdTipoServicoMestre" styleClass="select200" >
								<html:option value="">&nbsp;</html:option>
								<c:forEach var="tipoServicoTO" items="${tipoServicoTOList}">
									<html:option value="${tipoServicoTO.cdTipoServico}">${tipoServicoTO.dscTipoServico}</html:option>
								</c:forEach>
							</html:select>
						</div>
						<div class="linerow bold"><html:text property="cdServicoOrigemMestre" styleId="cdServicoOrigemMestre" style="width: 150px"/></div>
						<div class="linerow bold"><html:text property="cdServicoCatalogoMestre" styleId="cdServicoCatalogoMestre" style="width: 150px"/></div>
						<div class="linerow bold"><html:text property="nmServicoOrigemMestre" styleId="nmServicoOrigemMestre" style="width: 150px"/></div>
					</div>
					<div class="line">
						<div class="linerow bold"><label for="nmServicoCatalogoMestre">Nome do Servi&ccedil;o Cat&aacute;logo:</label></div>
					</div>
					<div class="line">
						<div class="linerow bold"><html:text property="nmServicoCatalogoMestre" styleId="nmServicoCatalogoMestre" style="width: 150px"/></div>
					</div>
					<div class="barra"></div>
					<div class="line">
		        		<div class="linerow bold"><label style="color:#0364cb !important;">Relacionamento</label></div>
		        	</div>
					<div class="line">
						<div class="linerow bold"><label for="sgTipoRelacionamento">Sigla do Relacionamento:</label></div>
						<div class="linerow bold"><label for="dscTipoRelacionamento">Nome do Relacionamento:</label></div>
					</div>
					<div class="line">
						<div class="linerow bold">
							<html:select property="sgTipoRelacionamento" styleClass="select200" >
								<html:option value="">&nbsp;</html:option>
								<c:forEach var="tipoRelacionamentoTO" items="${tipoRelacionamentoTOList}">
									<html:option value="${tipoRelacionamentoTO.sgTipoRelacionamento}">${tipoRelacionamentoTO.sgTipoRelacionamento}</html:option>
								</c:forEach>
							</html:select>
						</div>
						<div class="linerow bold"><html:text property="dscTipoRelacionamento" styleId="dscTipoRelacionamento" style="width: 150px"/></div>
					</div>
					<div class="barra"></div>
					<div class="line">
		        		<div class="linerow bold"><label style="color:#0364cb !important;">Servi&ccedil;o Relacionado</label></div>
		        	</div>
					<div class="line">
						<div class="linerow bold"><label for="cdTipoServicoSubordinado">Tipo de Servi&ccedil;o Relacionado:</label></div>
						<div class="linerow bold"><label for="cdServicoOrigemSubordinado">C&oacute;digo do Servi&ccedil;o Origem:</label></div>
						<div class="linerow bold"><label for="cdTipoServicoSubordinado">C&oacute;digo do Servi&ccedil;o Cat&aacute;logo:</label></div>
						<div class="linerow bold"><label for="nmServicoOrigemSubordinado">Nome do Servi&ccedil;o Origem:</label></div>
					</div>
					<div class="line">
						<div class="linerow bold">
							<html:select property="cdTipoServicoSubordinado" styleClass="select200" >
								<html:option value="">&nbsp;</html:option>
								<c:forEach var="tipoServicoTO" items="${tipoServicoTOList}">
									<html:option value="${tipoServicoTO.cdTipoServico}">${tipoServicoTO.dscTipoServico}</html:option>
								</c:forEach>
							</html:select>
						</div>
						<div class="linerow bold"><html:text property="cdServicoOrigemSubordinado" styleId="cdServicoOrigemSubordinado" style="width: 150px"/></div>
						<div class="linerow bold"><html:text property="cdServicoCatalogoSubordinado" styleId="cdServicoCatalogoSubordinado" style="width: 150px"/></div>
						<div class="linerow bold"><html:text property="nmServicoOrigemSubordinado" styleId="nmServicoOrigemSubordinado" style="width: 150px"/></div>
					</div>
					<div class="line">
						<div class="linerow bold"><label for="nmServicoCatalogoSubordinado">Nome do Servi&ccedil;o Cat&aacute;logo:</label></div>
					</div>
					<div class="line">
						<div class="linerow bold"><html:text property="nmServicoCatalogoSubordinado" styleId="nmServicoCatalogoSubordinado" style="width: 150px"/></div>
					</div>
					<div class="barra"></div>
					<div class="botao" id="divBotoes" style="display:block">
					    <html:button property="Pesquisar" value="Pesquisar" alt="Pesquisar" styleClass="btNavegacao74" onclick="pesquisar();" title=""/><span>&nbsp;</span>
					</div>
				</catalogo:contentBox>
				<c:if test="${relacionamentoRelatorioFixaTOList != null}">
					<div id="resultado_pesquisa"> 
						<catalogo:contentBox  title="Resultado da Pesquisa" doubt="false" requiredFields="false">
							<display:table name="relacionamentoRelatorioFixaTOList" id="relacionamentoRelatorioFixaTO" pagesize= "15" cellspacing="0" cellpadding="0" style="width: 99%;" requestURI = "ordenar.do" class="tabela-padrao-new tablesorter table_body" >
								<display:column property="nmTipoServicoMestre" title="Tipo de Servi&ccedil;o Rela&ccedil;&atilde;o" sortable="true" headerClass="sortable" />
								<display:column property="nmServicoCatalogoMestre" title="Nome do Servi&ccedil;o Cat&aacute;logo Rela&ccedil;&atilde;o" sortable="true" headerClass="sortable" />
								<display:column property="sgTipoRelacionamento" title="Sigla do Relacionamento" sortable="true" headerClass="sortable" />
								<display:column property="nmTipoServicoSubordinado" title="Tipo de Servi&ccedil;o Relacionado" sortable="true" headerClass="sortable" />
								<display:column property="nmServicoCatalogoSubordinado" title="Nome do Servi&ccedil;o Cat&aacute;logo Relacionado" sortable="true" headerClass="sortable" />
							</display:table>
							<div class="barra"></div>
							<div class="botao">
							    <cata:temPermissao acao="exportarRelatorioRelacionamento">
							        <html:button property="Pesquisar" value="Exportar" alt="${exportar}" styleClass="${styleBotaoExportar}" onclick="exportar(this);" title="Exportar"/><span>&nbsp;</span>
								</cata:temPermissao>
							</div>
						</catalogo:contentBox>
					</div>
				</c:if>
			</div>
			<html:hidden property="erro" styleId="erro" value="${erro}"/>
        </html:form>
  </jsp:body>        
</catalogo:blankTemplate>  