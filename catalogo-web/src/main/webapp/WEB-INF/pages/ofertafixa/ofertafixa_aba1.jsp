<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="catalogo" %>
<%@ taglib uri="CatalogoPRSTags" prefix="cata" %>
<%@ taglib uri="/WEB-INF/tags/displaytag.tld" prefix="display"%>

<html:form action="/br/com/vivo/catalogoPRS/pageflows/param/ofertafixa/carregar.do" >

<c:if test="${ofertafixaTO != null}">
	<input type="hidden" id="dsOfertafixa" value='${ofertafixaTO.dsOfertafixa == null ? "" : ofertafixaTO.dsOfertafixa}'/>
    <input type="hidden" id="inStatusConfiguracao" value="${ofertafixaTO.inStatusConfiguracao}"/>
    <catalogo:contentBox title="Oferta" doubt="false" requiredFields="true">
        <div class="boxline">
            <div class="linerow bold">C&oacute;digo da Oferta:<span class="required">*</span></div>
            <div class="linerow clear"><input type="text" id="cdOfertaNew" value='${ofertafixaTO.cdOfertafixa != null ? ofertafixaTO.cdOfertafixa : ""}' ${ofertafixaTO.cdOfertafixaDisabled} /></div>
        </div>                                            
        <div class="boxline">
            <div class="linerow bold">Nome da Oferta:<span class="required">*</span></div>
            <div class="linerow clear"><input class="changeable" type="text" id="nmOfertaNew" value='${ofertafixaTO.dsOfertafixa != null ? ofertafixaTO.dsOfertafixa : ""}' ${ofertafixaTO.dsOfertafixaDisabled} /></div>
        </div>
        <div class="boxline">
            <div class="linerow bold">Data In&iacute;cio Vig&ecirc;ncia:<span class="required">*</span></div>
            <div class="linerow clear"><input class="changeable" type="text" id="dataVigenteInicioNew" value='${ofertafixaTO.dtInicioFormatado != null ? ofertafixaTO.dtInicioFormatado : ""}' ${ofertafixaTO.dtInicialDisabled} /></div>
        </div>
        <div class="boxline">
            <div class="linerow bold">Data Fim Vigência:</div>
            <div class="linerow clear"><input class="changeable" type="text" id="dataVigenteFimNew" value='${ofertafixaTO.dtFimFormatado != null ? ofertafixaTO.dtFimFormatado : ""}' ${ofertafixaTO.dtFinalDisabled} /></div>
        </div>
        <div class="barra clear"></div>
        
        
        <div class="boxline">
            <div class="linerow bold">PS de Linha:<span class="required">*</span></div>
            <div class="linerow clear" id="selectPsServivoLinha">
            	<table>
            		<tr>
            			<td><input type="text" id="psServivoLinhaNew" onkeypress="return validarNumeros(event);" value="${ofertafixaTO.psServivoLinha}" ${ofertafixaTO.servicoOfertaFixaTOLinhaDisabled}/></td>
            			<td><html:button property="botao_ps_servico_linha" styleId="botao_ps_servico_linha" onClick="carregarDependentes(psServivoLinhaNew);carregarDependentes(idServicoLinhaNew)" style='width: 20px; display:${(ofertafixaTO.status eq "" || ofertafixaTO.status eq "naoiniciado") ? "block" : "none"} !important' styleClass="btnMais" value=">" alt=">" title=""/></td>
					</tr>
				</table>            			               
            </div> 
        </div>
        
        
        
        
        <div class="boxline">                        
            <div class="linerow bold">Servi&ccedil;o Linha:<span class="required">*</div>
            <div class="linerow clear">
                <select class="changeable" id="idServicoLinhaNew" onchange="carregarDependentes(this)" ${ofertafixaTO.servicoOfertaFixaTOLinhaDisabled}>
                    <option value="" ${ofertafixaTO.servicoOfertaFixaTOLinha.id == null ? "selected" : ""}>--Selecione--</option>
                    <c:forEach var="servicoFixaTO" items="${servicoLinhaList}">
                        <option value="${servicoFixaTO.idServico}&${servicoFixaTO.cdPS}" ${ofertafixaTO.servicoOfertaFixaTOLinha.id eq servicoFixaTO.idServico ? "selected" : ""}>${servicoFixaTO.nmComercial}</option>
                    </c:forEach>
                </select>
            </div> 
        </div>
        <div class="clear"></div>        
        <div class="boxline">
            <div class="linerow bold">Op. Comercial Linha:<span class="required">*</span></div>
            <div class="linerow clear" id="selectOpSolicitacaoComercialNormalNew">
            	<table>
            		<tr>
            			<td><input type="text" id="opSolicitacaoComercialNormalNew" onkeypress="return validarNumeros(event);" value="${ofertafixaTO.opSolicitacaoComercialNormal}" ${ofertafixaTO.servicoOfertaFixaTOLinha.id == null ? "disabled" : ""} ${ofertafixaTO.servicoOfertaFixaTOLinhaDisabled}/></td>
            			<td><html:button property="botao_op_comercial_linha" styleId="botao_op_comercial_linha" onClick="carregarDependentes(opSolicitacaoComercialNormalNew)" style='width: 20px; display:${(ofertafixaTO.status eq "" || ofertafixaTO.status eq "naoiniciado") ? "block" : "none"} !important' styleClass="btnMais" value=">" alt=">" title=""/></td>
					</tr>
				</table>            			
            </div> 	
        </div>
        
        
        
        <div class="boxline">                    
            <div class="linerow bold">Sol. Comercial Normal:<span class="required">*</div>
            <div class="linerow clear" id="selectSolicitacaoComercialNormalNew">
                <select class="changeable" id="idSolicitacaoComercialNormalNew" ${ofertafixaTO.servicoOfertaFixaTOLinha.id == null ? "disabled" : ""} ${ofertafixaTO.servicoOfertaFixaTOLinhaDisabled}>
                    <option value="" ${ofertafixaTO.solicitacaoComercialFixaTOLinha.idSolicitacaoComercial == null ? "selected" : ""}>--Selecione--</option>
                    <c:forEach items="${solicitacaoComercalFixaTONormalList}" var="solicitacaoComercialFixaTO" }>
                        <option value="${solicitacaoComercialFixaTO.idSolicitacaoComercial}&${solicitacaoComercialFixaTO.cdoperacaocomercial}" ${ofertafixaTO.solicitacaoComercialFixaTOLinha.idSolicitacaoComercial eq solicitacaoComercialFixaTO.idSolicitacaoComercial ? "selected" : ""}>${solicitacaoComercialFixaTO.nmSolicitacaoComercial}</option>
                    </c:forEach>                    
                </select>
            </div>
        </div>
        
        
        
        <div class="boxline">
            <div class="linerow bold">Op. Comercial Pr&eacute;-Cadastro:</div>
            <div class="linerow clear" id="selectOpSolicitacaoComercialPreCadastroNew">
            	<table>
            		<tr>
            			<td><input type="text" id="opSolicitacaoComercialPreCadastroNew" onkeypress="return validarNumeros(event);" value="${ofertafixaTO.opSolicitacaoComercialPreCadastro}" ${ofertafixaTO.servicoOfertaFixaTOLinha.id == null ? "disabled" : ""} ${ofertafixaTO.servicoOfertaFixaTOLinhaDisabled}/></td>
            			<td><html:button property="botao_op_comer_pre" styleId="botao_op_comer_pre" onClick="carregarDependentes(opSolicitacaoComercialPreCadastroNew)" style='width: 20px; display:${(ofertafixaTO.status eq "" || ofertafixaTO.status eq "naoiniciado") ? "block" : "none"} !important' styleClass="btnMais" value=">" alt=">" title=""/></td>
            		</tr>
            	</table>              
            </div>
        </div>
        <div class="boxline">
            <div class="linerow bold">Sol. Comercial Pr&eacute;-Cadastro:</div>
            <div class="linerow clear" id="selectSolicitacaoComercialPreCadastroNew">
                <select class="changeable" id="idSolicitacaoComercialPreCadastroNew" onchange="carregarDependentes(this)" ${ofertafixaTO.servicoOfertaFixaTOLinha.id == null ? "disabled" : ""} ${ofertafixaTO.servicoOfertaFixaTOLinhaDisabled}>
                    <option value="" ${ofertafixaTO.solicitacaoComercialFixaTOPreCad.idSolicitacaoComercial == null ? "selected" : ""}>-- Selecione --</option>
                    <c:forEach items="${solicitacaoComercalFixaTOPreCadastroList}" var="solicitacaoComercialFixaTO">
                        <option value="${solicitacaoComercialFixaTO.idSolicitacaoComercial}&${solicitacaoComercialFixaTO.cdoperacaocomercial}" ${ofertafixaTO.solicitacaoComercialFixaTOPreCad.idSolicitacaoComercial eq solicitacaoComercialFixaTO.idSolicitacaoComercial ? "selected" : ""}>${solicitacaoComercialFixaTO.nmSolicitacaoComercial}</option>
                    </c:forEach>                    
                </select>
            </div>
        </div>
        <div class="clear"></div>                                                    
        <div class="boxline">
			<div class="linerow bold">PS de Plano:<span class="required">*</span></div>
            <div class="linerow clear" id="selectPsServicoPlanoNew">
            	<table>
            		<tr>
            			<td><input type="text" id="psServicoPlanoNew" onkeypress="return validarNumeros(event);" value="${ofertafixaTO.psServicoPlano}" ${ofertafixaTO.servicoOfertaFixaTOLinha.id == null ? "disabled" : ""} ${ofertafixaTO.servicoOfertaFixaTOLinhaDisabled}/></td>
						<td><html:button property="botao_ps_plano" styleId="botao_ps_plano" onClick="carregarDependentes(psServicoPlanoNew);carregarDependentes(idServicoPlanoNew)" style='width: 20px; display:${(ofertafixaTO.status eq "" || ofertafixaTO.status eq "naoiniciado") ? "block" : "none"} !important' styleClass="btnMais" value=">" alt=">" title=""/></td>
            		</tr>            		
				</table>            		
            </div>
        </div>            
        <div class="boxline">                 
            <div class="linerow bold">Servi&ccedil;o Plano:<span class="required">*</span></div>
            <div class="linerow clear" id="selectServicoPlanoNew">
                <select class="changeable" id="idServicoPlanoNew" onchange="carregarDependentes(this)" ${ofertafixaTO.servicoOfertaFixaTOLinha.id == null ? "disabled" : ""} ${ofertafixaTO.servicoOfertaFixaTOLinhaDisabled}>
                    <option value="" ${ofertafixaTO.servicoOfertaFixaTOPlano.id == null ? "selected" : ""}>--Selecione--</option>
                    <c:forEach items="${servicoFixaTOPlanoList}" var="servicoFixaTO">
                        <option value="${servicoFixaTO.idServico}&${servicoFixaTO.cdPS}" ${ofertafixaTO.servicoOfertaFixaTOPlano.id eq servicoFixaTO.idServico ? "selected" : ""}>${servicoFixaTO.nmComercial}</option>
                    </c:forEach>                    
                </select>
            </div>
        </div>                                            
        <div class="boxline">
            <div class="linerow bold">Op. Comercial Plano:<span class="required">*</span></div>
            <div class="linerow clear" id="selectOpSolicitacaoComercialPlanoNew">
            <table>
				<tr>
					<td><input type="text" id="opSolicitacaoComercialPlanoNew" onkeypress="return validarNumeros(event);" value="${ofertafixaTO.opSolicitacaoComercialPlano}" ${ofertafixaTO.servicoOfertaFixaTOPlano.id == null ? "disabled" : ""} ${ofertafixaTO.servicoOfertaFixaTOPlanoDisabled}/></td>
					<td><html:button property="botao_op_comer_plano" styleId="botao_op_comer_plano" onClick="carregarDependentes(opSolicitacaoComercialPlanoNew)" style='width: 20px; display:${(ofertafixaTO.status eq "" || ofertafixaTO.status eq "naoiniciado") ? "block" : "none"} !important' styleClass="btnMais" value=">" alt=">" title=""/></td>
				</tr>			
            </table>              
            </div>
        </div>
        
        
        
        <div class="boxline">                 
            <div class="linerow bold">Sol. Comercial Plano:<span class="required">*</span></div>
            <div class="linerow clear" id="selectSolicitacaoComercialPlanoNew">
                <select class="changeable" id="idSolicitacaoComercialPlanoNew" ${ofertafixaTO.servicoOfertaFixaTOPlano.id == null ? "disabled" : ""} ${ofertafixaTO.servicoOfertaFixaTOPlanoDisabled}>
                    <option value="" ${ofertafixaTO.solicitacaoComercialFixaTOPlano.idSolicitacaoComercial == null ? "selected" : ""}>--Selecione--</option>
                    <c:forEach items="${solicitacaoComercalFixaTOPlanoList}" var="solicitacaoComercialFixaTO">
                        <option value="${solicitacaoComercialFixaTO.idSolicitacaoComercial}&${solicitacaoComercialFixaTO.cdoperacaocomercial}" ${ofertafixaTO.solicitacaoComercialFixaTOPlano.idSolicitacaoComercial eq solicitacaoComercialFixaTO.idSolicitacaoComercial ? "selected" : ""}>${solicitacaoComercialFixaTO.nmSolicitacaoComercial}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="clear valuecheck" id="checkboxgroup_editar">
            <div class="bold bottom">Tipo de Oferta:</div>
            <div class="boxline">
	            <div class="linerow bold">Linha Speedy</div>
	            <div class="linerow clear">
		            <select class="changeable" id="comboTipoOfertaLinhaSpeedy" ${ofertafixaTO.inConvergenteCobreDisabled}>
		            	<option value="">--Selecione--</option>
		                <option value="linhacobre" ${(ofertafixaTO.inConvergenteCobre eq "S") ? "selected" : ""} ${ofertafixaTO.inConvergenteCobreDisabled}>Cobre</option>
		                <option value="linhafibra" ${(ofertafixaTO.inConvergenteFibra eq "S") ? "selected" : ""} ${ofertafixaTO.inConvergenteFibraDisabled} >Fibra</option>
		            </select>
		        </div>
            </div>
			<div class="boxline">
				<div class="linerow bold">Speedy Solo</div>
				<div class="linerow clear">
		            <select class="changeable" id="comboTipoOfertaSpeedySolo" ${ofertafixaTO.inSpeedySoloCobreDisabled}>
		            	<option value="">--Selecione--</option>
		                <option value="solocobre" ${(ofertafixaTO.inSpeedySoloCobre eq "S") ? "selected" : ""} ${ofertafixaTO.inSpeedySoloCobreDisabled} >Cobre</option>
		                <option value="solofibra" ${(ofertafixaTO.inSpeedySoloFibra eq "S") ? "selected" : ""} ${ofertafixaTO.inSpeedySoloFibraDisabled} >Fibra</option>
		            </select>
		        </div>
	        </div>
            <div class="clear">
	            <div class="check">&nbsp;<input type="checkbox" class="checkbox changeable" id="tipoOfertaCheckBoxPortabilidade" value="portabilidade" ${(ofertafixaTO.inPortab eq "S") ? "checked" : ""} ${ofertafixaTO.inPortabDisabled} />Portabilidade</div>
	            <div class="check">&nbsp;<input type="checkbox" class="checkbox changeable" id="tipoOfertaCheckBoxFwt" value="fwt" ${(ofertafixaTO.inFWT eq "S") ? "checked" : ""} ${ofertafixaTO.inFWTDisabled} />FWT</div>
	            <div class="check">&nbsp;<input type="checkbox" class="checkbox changeable" id="tipoOfertaCheckBoxBasepontual" value="basepontual" ${(ofertafixaTO.inBasePontual eq "S") ? "checked" : ""} ${ofertafixaTO.inBasePontualDisabled} />Base Pontual</div>
	            <div class="check">&nbsp;<input type="checkbox" class="checkbox changeable" id="tipoOfertaCheckBoxInadimplente" value="inadimplente" ${(ofertafixaTO.inOfertaClienteInadimplente eq "S") ? "checked" : ""} ${ofertafixaTO.inOfertaClienteInadimplenteDisabled} />Inadimplente</div>
	            <div class="check">&nbsp;<input type="checkbox" class="checkbox changeable" id="tipoOfertaCheckBoxFatb" value="fatb" ${(ofertafixaTO.inFATB eq "S") ? "checked" : ""} ${ofertafixaTO.inFATBDisabled} />FATB</div>            
            </div>
        </div>
    </catalogo:contentBox>
</c:if>
</html:form>
<html:hidden property="statusOfertafixa" styleId="statusOfertafixa" value="${ofertafixaTO.status}"/>
<html:hidden property="${type_msg}" styleId="${type_msg}" value="${msg}"/>