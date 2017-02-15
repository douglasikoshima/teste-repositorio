<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="fidelizacaoForm"
             type="workflow.AtendimentoFila.Portabilidade.PortabilidadeController.FidelizacaoForm" />

<jsp:include page="fidelizacaoDadosLinha.jsp" />

<div id="fidelizacaoWrapper">

    <vivo:quadro id="divBonus" height="111" width="743" label="Bônus" scroll="no">
        <table width="100%">
            <tr>
                <td width="15%">Bônus cadastrados:</td>
                <td>
                    <html:select onchange="qtDiasValidadeBonus=this.options[this.options.selectedIndex].validade" name="Form" title="" property="bonusSelecionado.idBonus" styleId="indexBonusSelecionado" style="width:268px" size="1">
                        <option value="">-- Selecione --</option>
                        <logic:iterate indexId="c" name="Form" property="listaBonusVO.bonusVOArray" id="iteratorBonus">
                        <option value="<%=c%>" validade="<bean:write name="iteratorBonus" property="validade" />"><bean:write name="iteratorBonus" property="descricao" /></option>
                        </logic:iterate>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>Início de vigência:</td>
                <td>
                    <html:text readonly="true" name="Form" property="dtInicioVigencia" styleId="dtInicioVigencia" style="width:70px" /> 
                    <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtInicioVigencia', '%d/%m/%Y');">
                    <img src="<%=request.getContextPath()%>/resources/images/bt_calcular_nrml.gif" border="0" onclick="carregarDataVigenciaBonus()" style="cursor:pointer" />
                </td>
            </tr>
            <tr>
                <td>Fim de vigência:</td>
                <td>
                    <html:text readonly="true" name="Form" property="dtFimVigencia" styleId="dtFimVigencia" style="width:70px" />
                </td>
            </tr>
            <tr>
                <td><strong>Oferta de Exceção</strong>:</td>
                <td>
                    <html:checkbox name="Form" property="inExcecao" styleId="inExcecao" styleClass="checkbox" />
                </td>
            </tr>
        </table>
    </vivo:quadro>

</div>

<jsp:include page="fidelizacaoNavigation.jsp"/>

<script type="text/javascript" language="javascript">
    operacaoAtual = 'fidelizacaoBonus';
    proximaOperacao = 'finalizacao';
</script>