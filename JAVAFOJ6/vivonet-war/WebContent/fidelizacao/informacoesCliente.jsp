<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fidelizacao.tld" prefix="fid"%>
<bean:define id="DadosClienteForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm"/>
<bean:define id="DetalheLinha"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm.detalheLinha"/>
        <fid:table>
            <fid:tr bgColor="#545454">
                <fid:headerTd>Linha</fid:headerTd>
                <fid:headerTd>Segmentação</fid:headerTd>
                <fid:headerTd>Rentabilidade</fid:headerTd>
                <fid:headerTd>Contrato de Fidelização</fid:headerTd>
                <fid:headerTd>Multa Contratual</fid:headerTd>
                <fid:headerTd>Término Contrato</fid:headerTd>
                <fid:headerTd>Plano Serviço</fid:headerTd>
                <fid:headerTd>Habilitação</fid:headerTd>
            </fid:tr>
            <fid:tr bgColor="#ffffff" onMouseOverColor="#F5F5F5">
                <fid:dataTd><netui:label value="{pageContext.DetalheLinha.numero}" /></fid:dataTd>
                <fid:dataTd><netui:label value="{pageContext.DetalheLinha.segmentacao}" /></fid:dataTd>
                <fid:dataTd><netui:label value="{pageContext.DetalheLinha.rentabilidade}" /></fid:dataTd>
                <fid:dataTd><netui:label value="{pageContext.DetalheLinha.contrato}" /></fid:dataTd>
                <fid:dataTd><netui:label value="{pageContext.DetalheLinha.valorMulta}" /></fid:dataTd>
                <fid:dataTd><netui:label value="{pageContext.DetalheLinha.dtFimContrato}" /></fid:dataTd>
                <fid:dataTd><netui:label value="{pageContext.DetalheLinha.plano}" /></fid:dataTd>
                <fid:dataTd><netui:label value="{pageContext.DetalheLinha.dtHabilitacao}" /></fid:dataTd>
            </fid:tr>
        </fid:table>
