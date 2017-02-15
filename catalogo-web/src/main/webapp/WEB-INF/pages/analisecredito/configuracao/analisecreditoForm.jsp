<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<catalogo:defaultTemplate titulo="Home Catalogo">
   <jsp:attribute name="headScripts">
		<link type="text/css" href="/catalogo/jquery/css/cupertino/jquery-ui-1.8.17.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="/catalogo/jquery/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="/catalogo/jquery/js/jquery-ui-1.8.17.custom.min.js"></script>	
		<style>
			ul.connectedSortable { list-style-type: none; margin: 0; padding: 0; float: left; margin-bottom: 10px;  margin-right: 10px; border: 1px solid #5875A1; height: 100px; width: 245px; float: left; overflow-y: scroll; overflow-x: hidden;}
			ul.connectedSortable li { margin: 0 5px 5px 5px; padding: 5px; font-size: 11px; width: 100%; }
			.contentScore{float: left; margin-top: 5px; text-align: left;}
			li.ui-state-default div.ui-state-default {width: 50%; float: left; font-size: 11px; border: 0px;}
		</style>
		<script type="text/javascript" src="/catalogo/static_server/js/configuracaoanalisecredito.js"></script>
   </jsp:attribute>
   <jsp:body>
		<script>carregaMenu('mn_config_analise');</script>
		<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/search.do" styleId="acaoForm" onsubmit="false">
			<div class="secao_conteudo">
				<catalogo:contentBox title="Configuração Análise de Crédito" requiredFields="true" doubt="true">
					
					<div class="fleft" style="width:32%; margin-bottom: 20px; " id="contentIdServico" > 
						<div class="label-form-bold label_required" style="text-align: right; width: 50px;"><label for="nome">Nome:<font size="1px" color="#EEB422">*</font></label></div>
						<html:text property="nome" styleId="nome" style="width: 200px;"/>
					</div>
					<div class="fleft" style="width:36%; margin-bottom: 20px;" >
						<div class="label-form-bold label_required" style="text-align: right; width:125px;"><label for=canal>Canal Atendimento:<font size="1px" color="#EEB422">*</font></label></div>
						<html:select styleClass="required" styleId="canal" style="width:150px;" property="canal" >
	                    	 <html:option value="0">-- Selecione --</html:option>
	                    	 <html:option value="Loja Própria">Loja Própria</html:option>
	                    	 <html:option value="Televendas">Televendas</html:option>
	                    	 <html:option value="CallCenter">CallCenter</html:option>
	                    </html:select>
					</div>
					<div class="fleft" style="margin-bottom: 20px; " id="contentIdServico" > 
						<div class="label-form-bold label_required" style="text-align: right; width: 50px;"><label for="status">Status:<font size="1px" color="#EEB422">&nbsp;&nbsp;&nbsp;</font></label></div>
						<c:if test="${actionForm.status != null}">
							<html:text property="status" styleId="status" disabled="true" style="width: 190px;"/>
						</c:if>
						<c:if test="${actionForm.status == null}">
							<html:text property="status" styleId="status" disabled="true" style="width: 190px;" value="Não Ativa - Em Andamento"/>
						</c:if>
					</div>
					
					<div id="tabs" style="width: 98%;">
						<ul>
							<li><a href="#tabs-1">VOZ</a></li>
							<li><a href="#tabs-2">DADOS</a></li>
							<li><a href="#tabs-3">SMART</a></li>
							<li><a href="#tabs-4">IPHONE</a></li>
							<li><a href="#tabs-5">SERVIÇOS</a></li>
						</ul>
						<div id="tabs-1">
							<p>	
								<div class="fleft" id="contentIdServico" > 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 175px;"><label for="qtPlanos">Quantidade de Planos Acima:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<html:select style="width: 160px;" styleId="qtPlanos" property="qtPlanos">
										<html:option>Nenhum</html:option>
										<html:option>1 acima</html:option>
										<html:option>2 acima</html:option>
										<html:option>3 acima</html:option>
									</html:select>
								</div>
								<br />
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Filtro de Planos/Serviços:</h3>
								<div class="fleft" style="clear: left; width: 50%;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="nmSearch">Nome:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="nmSearch" id="nmSearch" style="width: 250px;" /><br />
								</div>
								<div class="fleft" style="clear: left; width: 50%; height: 25px;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="valorDe">Preço de R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorDe" id="valorDe" style="width: 100px; float: left; margin-left: 3px;" onkeypress="return formatar_moeda(this,'.',',',event);" />
									<div class="label-form-bold label_required" style="text-align: right; float: left; width: 50px;"><label for="valorAte">Até R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorAte" id="valorAte" style="width: 100px; float: left;" onkeypress="return formatar_moeda(this,'.',',',event);" />
								</div>
								<div class="fleft" style="clear: left; width: 43%; text-align: right; ">
								  <html:text property="botao_buscar_form" styleId="botao_buscar_form" style="clear: both;" onClick="javascript:document.location.href = '#'" styleClass="btNavegacao74" value="Buscar" alt="Buscar" title=""/>
								</div>
								<div style="float: left; font-weight: bold;">
									Plano/Serviços Disponíveis:
									<br /><br />
									<ul id="sortable1" class="connectedSortable" style="float: left;"> 
										<li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO 250MB</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 15</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 60</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 650</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 900</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO ADM1</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Plano Pessoal</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 120</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Básico GSM</div><div class="ui-state-default">R$233,00</div></li>
									</ul>
								</div>
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Configuração Selecionada:</h3>
								<div class="contentScore label-form-bold">Score 01:<ul id="sortable2" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 02:<ul id="sortable3" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 03:<ul id="sortable4" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 04:<ul id="sortable5" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 05:<ul id="sortable6" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 06:<ul id="sortable7" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 07:<ul id="sortable8" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 08:<ul id="sortable9" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<div class="botao" style="float: right; margin-right: 0px; clear: both;">
								    <html:text property="botao_gravar" styleId="botao_gravar" onClick="javascript:document.location.href='#'" styleClass="btNavegacao74" value="Salvar" alt="Salvar" title=""/>
								</div>
							</p>
						</div>
						<div id="tabs-2">
							<p>	
								<div class="fleft" id="contentIdServico" > 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 175px;"><label for="qtPlanos">Quantidade de Planos Acima:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<html:select style="width: 160px;" styleId="qtPlanos" property="qtPlanos">
										<html:option>Nenhum</html:option>
										<html:option>1 acima</html:option>
										<html:option>2 acima</html:option>
										<html:option>3 acima</html:option>
									</html:select>
								</div>
								<br />
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Filtro de Planos/Serviços:</h3>
								<div class="fleft" style="clear: left; width: 50%;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="nmSearch">Nome:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="nmSearch" id="nmSearch" style="width: 250px;" /><br />
								</div>
								<div class="fleft" style="clear: left; width: 50%; height: 25px;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="valorDe">Preço de R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorDe" id="valorDe" style="width: 100px; float: left; margin-left: 3px;" onkeypress="return formatar_moeda(this,'.',',',event);" />
									<div class="label-form-bold label_required" style="text-align: right; float: left; width: 50px;"><label for="valorAte">Até R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorAte" id="valorAte" style="width: 100px; float: left;" onkeypress="return formatar_moeda(this,'.',',',event);" />
								</div>
								<div class="fleft" style="clear: left; width: 43%; text-align: right; ">
								 <html:text property="botao_buscar_form" styleId="botao_buscar_form" style="clear: both;" onClick="javascript:document.location.href = '#'" styleClass="btNavegacao74" value="Buscar" alt="Buscar" title=""/>
								 </div>
								<div style="float: left; font-weight: bold;">
									Plano/Serviços Disponíveis:
									<br /><br />
									<ul id="sortable11" class="connectedSortable" style="float: left;"> 
										<li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO 250MB</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 15</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 60</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 650</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 900</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO ADM1</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Plano Pessoal</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 120</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Básico GSM</div><div class="ui-state-default">R$233,00</div></li>
									</ul>
								</div>
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Configuração Selecionada:</h3>
								<div class="contentScore label-form-bold">Score 01:<ul id="sortable12" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 02:<ul id="sortable13" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 03:<ul id="sortable14" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 04:<ul id="sortable15" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 05:<ul id="sortable16" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 06:<ul id="sortable17" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 07:<ul id="sortable18" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 08:<ul id="sortable19" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<div class="botao" style="float: right; margin-right: 0px; clear: both;">
								    <html:text property="botao_gravar" styleId="botao_gravar" onClick="javascript:document.location.href='#'" styleClass="btNavegacao74" value="Salvar" alt="Salvar" title=""/>
								</div>
							</p>
						</div>
						<div id="tabs-3">
							<p>	
								<div class="fleft" id="contentIdServico" > 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 175px;"><label for="qtPlanos">Quantidade de Planos Acima:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<html:select style="width: 160px;" styleId="qtPlanos" property="qtPlanos">
										<html:option>Nenhum</html:option>
										<html:option>1 acima</html:option>
										<html:option>2 acima</html:option>
										<html:option>3 acima</html:option>
									</html:select>
								</div>
								<br />
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Filtro de Planos/Serviços:</h3>
								<div class="fleft" style="clear: left; width: 50%;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="nmSearch">Nome:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="nmSearch" id="nmSearch" style="width: 250px;" /><br />
								</div>
								<div class="fleft" style="clear: left; width: 50%; height: 25px;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="valorDe">Preço de R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorDe" id="valorDe" style="width: 100px; float: left; margin-left: 3px;" onkeypress="return formatar_moeda(this,'.',',',event);" />
									<div class="label-form-bold label_required" style="text-align: right; float: left; width: 50px;"><label for="valorAte">Até R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorAte" id="valorAte" style="width: 100px; float: left;" onkeypress="return formatar_moeda(this,'.',',',event);" />
								</div>
								<div class="fleft" style="clear: left; width: 43%; text-align: right; ">
								<html:text property="botao_buscar_form" styleId="botao_buscar_form" style="clear: both;" onClick="javascript:document.location.href = '#'" styleClass="btNavegacao74" value="Buscar" alt="Buscar" title=""/>
								</div>
								<div style="float: left; font-weight: bold;">
									Plano/Serviços Disponíveis:
									<br /><br />
									<ul id="sortable21" class="connectedSortable" style="float: left;"> 
										<li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO 250MB</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 15</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 60</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 650</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 900</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO ADM1</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Plano Pessoal</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 120</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Básico GSM</div><div class="ui-state-default">R$233,00</div></li>
									</ul>
								</div>
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Configuração Selecionada:</h3>
								<div class="contentScore label-form-bold">Score 01:<ul id="sortable22" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 02:<ul id="sortable23" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 03:<ul id="sortable24" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 04:<ul id="sortable25" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 05:<ul id="sortable26" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 06:<ul id="sortable27" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 07:<ul id="sortable28" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 08:<ul id="sortable29" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<div class="botao" style="float: right; margin-right: 0px; clear: both;">
								    <html:text property="botao_gravar" styleId="botao_gravar" onClick="javascript:document.location.href='#'" styleClass="btNavegacao74" value="Salvar" alt="Salvar" title=""/>
								</div>
							</p>
						</div>
						<div id="tabs-4">
							<p>	
								<div class="fleft" id="contentIdServico" > 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 175px;"><label for="qtPlanos">Quantidade de Planos Acima:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<html:select style="width: 160px;" styleId="qtPlanos" property="qtPlanos">
										<html:option>Nenhum</html:option>
										<html:option>1 acima</html:option>
										<html:option>2 acima</html:option>
										<html:option>3 acima</html:option>
									</html:select>
								</div>
								<br />
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Filtro de Planos/Serviços:</h3>
								<div class="fleft" style="clear: left; width: 50%;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="nmSearch">Nome:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="nmSearch" id="nmSearch" style="width: 250px;" /><br />
								</div>
								<div class="fleft" style="clear: left; width: 50%; height: 25px;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="valorDe">Preço de R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorDe" id="valorDe" style="width: 100px; float: left; margin-left: 3px;" onkeypress="return formatar_moeda(this,'.',',',event);" />
									<div class="label-form-bold label_required" style="text-align: right; float: left; width: 50px;"><label for="valorAte">Até R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorAte" id="valorAte" style="width: 100px; float: left;" onkeypress="return formatar_moeda(this,'.',',',event);" />
								</div>
								<div class="fleft" style="clear: left; width: 43%; text-align: right; ">
								<html:text property="botao_buscar_form" styleId="botao_buscar_form" style="clear: both;" onClick="javascript:document.location.href = '#'" styleClass="btNavegacao74" value="Buscar" alt="Buscar" title=""/>
								</div>
								<div style="float: left; font-weight: bold;">
									Plano/Serviços Disponíveis:
									<br /><br />
									<ul id="sortable31" class="connectedSortable" style="float: left;"> 
										<li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO 250MB</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 15</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 60</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 650</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 900</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO ADM1</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Plano Pessoal</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 120</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Básico GSM</div><div class="ui-state-default">R$233,00</div></li>
									</ul>
								</div>
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Configuração Selecionada:</h3>
								<div class="contentScore label-form-bold">Score 01:<ul id="sortable32" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 02:<ul id="sortable33" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 03:<ul id="sortable34" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 04:<ul id="sortable35" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 05:<ul id="sortable36" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 06:<ul id="sortable37" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 07:<ul id="sortable38" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 08:<ul id="sortable39" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<div class="botao" style="float: right; margin-right: 0px; clear: both;">
								    <html:text property="botao_gravar" styleId="botao_gravar" onClick="javascript:document.location.href='#'" styleClass="btNavegacao74" value="Salvar" alt="Salvar" title=""/>
								</div>
							</p>
						</div>
						<div id="tabs-5">
							<p>	
								<div class="fleft" id="contentIdServico" > 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 175px;"><label for="qtPlanos">Quantidade de Planos Acima:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<html:select style="width: 160px;" styleId="qtPlanos" property="qtPlanos">
										<html:option>Nenhum</html:option>
										<html:option>1 acima</html:option>
										<html:option>2 acima</html:option>
										<html:option>3 acima</html:option>
									</html:select>
								</div>
								<br />
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Filtro de Planos/Serviços:</h3>
								<div class="fleft" style="clear: left; width: 50%;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="nmSearch">Nome:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="nmSearch" id="nmSearch" style="width: 250px;" /><br />
								</div>
								<div class="fleft" style="clear: left; width: 50%; height: 25px;"> 
									<div class="label-form-bold label_required fleft" style="text-align: right; width: 85px;"><label for="valorDe">Preço de R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorDe" id="valorDe" style="width: 100px; float: left; margin-left: 3px;" onkeypress="return formatar_moeda(this,'.',',',event);" />
									<div class="label-form-bold label_required" style="text-align: right; float: left; width: 50px;"><label for="valorAte">Até R$:<font size="1px" color="#EEB422">&nbsp;&nbsp;</font></label></div>
									<input type="text" name="valorAte" id="valorAte" style="width: 100px; float: left;" onkeypress="return formatar_moeda(this,'.',',',event);" />
								</div>
								<div class="fleft" style="clear: left; width: 43%; text-align: right; ">
								<html:text property="botao_buscar_form" styleId="botao_buscar_form" style="clear: both;" onClick="javascript:document.location.href = '#'" styleClass="btNavegacao74" value="Buscar" alt="Buscar" title=""/>
								</div>
								<div style="float: left; font-weight: bold;">
									Plano/Serviços Disponíveis:
									<br /><br />
									<ul id="sortable41" class="connectedSortable" style="float: left;"> 
										<li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO 250MB</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 15</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 60</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO VOCE 650</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 900</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">PLANO ADM1</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Plano Pessoal</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">VIVO 120</div><div class="ui-state-default">R$233,00</div></li>
										<li class="ui-state-default"><div class="ui-state-default">Básico GSM</div><div class="ui-state-default">R$233,00</div></li>
									</ul>
								</div>
								<br />
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<br />
								<h3 style="font-size: 11px;	font-weight: bold; width:95%; color: #0364CB; margin-bottom:20px; display:inline; float:left; text-transform: uppercase">Configuração Selecionada:</h3>
								<div class="contentScore label-form-bold">Score 01:<ul id="sortable42" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 02:<ul id="sortable43" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 03:<ul id="sortable44" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 04:<ul id="sortable45" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 05:<ul id="sortable46" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 06:<ul id="sortable47" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 07:<ul id="sortable48" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="contentScore label-form-bold">Score 08:<ul id="sortable49" class="connectedSortable"><li class="ui-state-default ui-state-disabled"><div class="ui-state-default">Descrição</div><div class="ui-state-default">Valor</div></li></ul></div>
								<div class="barra" style="margin-left: -10px; margin-right: -20px; clear: both;"></div>
								<div class="botao" style="float: right; margin-right: 0px; clear: both;">
								    <html:text property="botao_gravar" styleId="botao_gravar" onClick="javascript:document.location.href='#'" styleClass="btNavegacao74" value="Salvar" alt="Salvar" title=""/>
								</div>
							</p>
						</div>
					</div>
					
					<br clear="all"/><br clear="all"/>
					<div class="barra"></div>
					<div class="botao">
						<html:text property="botao_concluir" styleId="botao_concluir" onClick="cancelarAnaliseCredito()" styleClass="btNavegacao74" value="Concluir" alt="Concluir" title=""/><span>&nbsp;</span>
					    <html:text property="botao_cancelar" styleId="botao_cancelar" onClick="cancelarAnaliseCredito()" styleClass="btNavegacao74" value="Cancelar" alt="Cancelar" title=""/>				    				    
					</div>
			</catalogo:contentBox>
			</div>
         </html:form>     
    </jsp:body>        
</catalogo:defaultTemplate>