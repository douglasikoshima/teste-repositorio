<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>

<form action="solucaoTecnicaGravar.do" method="post">
        <table width="100%">
            <tr valign="middle">
                <td>
                    Problema técnico ERB:<br>
                    <html:radio value="S" name="" property="">Sim</html:radio>
                    <html:radio value="N" name="" property="">Não</html:radio>
                </td>
                <td rowspan=4>
                   Observações:<br>
                   <netui:textArea rows="5" dataSource="{}" style="width=400"/>
                </td>
            </tr valign="middle">
            <tr>
                <td>
                    Problema técnico no aparelho do cliente:<br>
                    <netui:radioButtonGroup defaultValue="N" dataSource="{}">
                        <netui:radioButtonOption value="S" style="border;none;">Sim</netui:radioButtonOption>
                        <netui:radioButtonOption value="N" style="border;none;">Não</netui:radioButtonOption>
                    </netui:radioButtonGroup>
                </td>
            </tr>
            <tr valign="middle">
                <td>
                    Serviço olho mágico ativo com solução:<br>
                    <netui:radioButtonGroup defaultValue="S" dataSource="{}">
                        <netui:radioButtonOption value="S" style="border;none;">Sim</netui:radioButtonOption>
                        <netui:radioButtonOption value="N" style="border;none;">Não</netui:radioButtonOption>
                    </netui:radioButtonGroup>
                </td>
            </tr>
            <tr valign="middle">
                <td>
                    Solicitação do cliente/usuário/prospect/ex-cliente resolvida:<br>
                    <netui:radioButtonGroup defaultValue="S" dataSource="{}">
                        <netui:radioButtonOption value="S" style="border;none;">Sim</netui:radioButtonOption>
                        <netui:radioButtonOption value="N" style="border;none;">Não</netui:radioButtonOption>
                    </netui:radioButtonGroup>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <vivo:botao id="btAplicar" width="60px" height="10px" value="Aplicar" styleClass="btTemplate" onclick=""/>
                </td>
            </tr>
        </table>
</form>

