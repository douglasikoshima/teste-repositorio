<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>

<acesso:controlInitEnv/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<bean:define id="indicadoresForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="indicadoresForm"/>
<acesso:controlHiddenItem nomeIdentificador="wor_indr_verpagina">

<!--Resumo-->
<vivo:quadro id="qdResumo" height="375" width="775" label="&nbsp;Avaliação Resumo" scroll="N">
    <vivo:tbTable selectable="false" layoutWidth="750" layoutHeight="375" tableWidth="750" styleId="tdResumos" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Ordem</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Horário</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Semana
Anterior (<bean:write name="indicadoresForm" property="indicadoresVO.dtSemanaAnterior" />)</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Dia
Anterior (<bean:write name="indicadoresForm" property="indicadoresVO.dtDiaAnterior" />)</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="20%" type="string">Data Atual &nbsp;&nbsp;(<bean:write name="indicadoresForm" property="indicadoresVO.dtAtual" />)&nbsp;&nbsp;</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate id="resumo" name="indicadoresForm" property="indicadoresVO.resumoAcompanhamentoArray">
            <vivo:tbRow key="linha1" zebrar="S">
                <vivo:tbRowColumn><b><bean:write name="resumo" property="ordem" /></b></vivo:tbRowColumn>
                <vivo:tbRowColumn><b><bean:write name="resumo" property="horario" /></b></vivo:tbRowColumn>
                <vivo:tbRowColumn><b><bean:write name="resumo" property="semanaAnterior" /></b></vivo:tbRowColumn>
                <vivo:tbRowColumn><b><bean:write name="resumo" property="diaAnterior" /></b></vivo:tbRowColumn>
                <vivo:tbRowColumn><b><bean:write name="resumo" property="dataAtual" /></b></vivo:tbRowColumn>
            </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
</vivo:quadro>
</acesso:controlHiddenItem>
<script language="javascript">
    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>