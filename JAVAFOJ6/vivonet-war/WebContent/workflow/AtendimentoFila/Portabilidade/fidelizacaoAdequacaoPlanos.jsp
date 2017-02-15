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

<bean:define id="DadosLinha"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="detalhesProcessoForm.dadosLinha"
             type="br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument.DetalheLinhaVO" />

<jsp:include page="fidelizacaoDadosLinha.jsp" />

<div id="fidelizacaoWrapper">

    <vivo:quadro id="divAdequacaoPlanos" height="111" width="743" label="Adequação de Planos" scroll="no">
        <table width="100%">
            <tr>
                <td width="15%">Plano Atual:</td>
                <td style="color:#1865c5;padding-left:4px;">
                    <bean:write name="DadosLinha" property="plano" />
                </td>
            </tr>
            <tr>
                <td>Novo Plano:</td>
                <td>
                    <html:select name="Form" property="planoSelecionado.id" styleId="indexPlanoSelecionado" style="width:268px" size="1">
                        <option value="">-- Selecione --</option>
                        <logic:iterate indexId="c" name="Form" property="listaPlanoVO.planoVOArray" id="iteratorPlanos">
                        <option value="<%=c%>"><bean:write name="iteratorPlanos" property="descricao" /></option>
                        </logic:iterate>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>Oferta de Exceção:</td>
                <td>
                    <html:checkbox name="Form" property="inExcecao" styleId="inExcecao" styleClass="checkbox" />
                </td>
            </tr>
        </table>
    </vivo:quadro>

</div>

<jsp:include page="fidelizacaoNavigation.jsp"/>

<script type="text/javascript" language="javascript">
    proximaOperacao = 'finalizacao';
</script>