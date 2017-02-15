<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-template-1.0" prefix="netui-temp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="resultado_busca_servicos" style="position:relative;">
	<div>
		<div class="conteudo_box_top">
			<div class="conteudo_box_top_center">Resultado da Pesquisa:</div>
			<div class="conteudo_box_top_esq"></div>
			<div class="conteudo_box_top_dir openclose">
				<img onclick="showHideSecaoConteudo(this);" src="/catalogo/static_server/img/botoes/bt-barra-close.gif" alt="Reduzir/Expandir Bloco" title="Reduzir/Expandir Bloco" />
			</div>
		</div>
			<div>
				<div class="conteudo_box_middle">
					<div class="conteudo_box_middle_mg">
						<div style="width: 100%; position: relative;">
							<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_header">
								<thead>
									<tr>
										<th class="sortable">Nome Comercial</th>
										<th class="sortable">Nome T&eacute;cnico</th>
										<th class="sortable">UF</th>
										<th class="sortable">Valor de<br/>Assinatura</th>
										<th class="sortable">C&oacute;digo<br/>SERASA</th>
										<th class="sortable">Dispon&iacute;vel<br/>para Venda</th>
										<th>Atribuir<br/>Alterar</th>
									</tr>
								</thead>
							</table>
							
							
							
							
							<div style="height:300px;" class="both-scroll">
								<netui-data:repeater dataSource="modelosVenda" defaultText="<br><span align='center'>Dados n&atildeo; encontrado para esse crit&eacute;rio de pesquisa.</span>">
									<netui-data:repeaterHeader>
										<table cellspacing="0" cellpadding="0" class="tabela-padrao tablesorter table_body" >
											<thead>
												<tr>
													<th class="sortable">Nome Comercial</th>
													<th class="sortable">Nome T&eacute;cnico</th>
													<th class="sortable">UF</th>
													<th class="sortable">Valor de<br/>Assinatura</th>
													<th class="sortable">C&oacute;digo<br/>SERASA</th>
													<th class="sortable">Dispon&iacute;vel<br/>para Venda</th>
													<th>Atribuir<br/>Alterar</th>
												</tr>
											</thead>
											<tbody>
									</netui-data:repeaterHeader>
										<netui-data:repeaterItem>
											<tr>
												<td style="text-align: left;">01</td>
												<td style="center">02</td>
												<td class="center">03</td>
												<td class="center">04</td>
												<td class="center">05</td>
												<td class="center">06</td>
												<td class="center">07</td>
											</tr>
										</netui-data:repeaterItem>
										<netui-data:repeaterFooter>
											</tbody>
										</table>
										</netui-data:repeaterFooter>
								</netui-data:repeater>
							</div>
							
							
							
							
							
						</div>
					</div>
				</div>
			</div>
		<div class="conteudo_box_bottom"></div>
	</div>
</div>
<iframe id='target_download2' name='target_download2' src='' style='display:none;' onload="retornoDownloadFile(this, 'resultado_busca_servicos');"></iframe>
<div id="lista_variaveis" style="position:relative;"></div>	
