<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.GestorContasVODocument.GestorContasVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gestorContasForm.gestorContasVO" type="br.com.vivo.fo.cliente.vo.GestorContasVODocument.GestorContasVO" />

<vivo:tbTable selectable="true" layoutWidth="758" layoutHeight="370" tableWidth="758" styleId="tbResultadoPesquisa" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="18%" type="string">Gestor</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="18%" type="string">Cliente</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="18%" type="string">Celular</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="18%" type="string">Número da Conta</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="18%" type="string">Tipo de Gestor</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows scroll="false">
        <logic:iterate id="itG" name="Form" property="tabela.linhaArray" type="GestorContasVO.Tabela.Linha">
            <vivo:tbRow key="linha" idClass="teste">
                <vivo:tbRowColumn key="ctd"><bean:write name="itG" property="nmGestor"/></vivo:tbRowColumn>
                <vivo:tbRowColumn key="ctd"><bean:write name="itG" property="nmCliente"/></vivo:tbRowColumn>
                <vivo:tbRowColumn key="ctd"><%="("+itG.getNrCelular().substring(0,2)+")"+itG.getNrCelular().substring(2,6)+"-"+itG.getNrCelular().substring(5)%></vivo:tbRowColumn>
                <vivo:tbRowColumn key="ctd"><bean:write name="itG" property="nrConta"/></vivo:tbRowColumn>
                <vivo:tbRowColumn key="ctd"><bean:write name="itG" property="tpGestor"/></vivo:tbRowColumn>
                <vivo:tbRowColumn key="ctd">
                    <img onmouseup="inserirAlterarGestor('<bean:write name="itG" property="nrCPF"/>');" alt="Alterar Gestor" src="<%=request.getContextPath()%>/resources/images/bt_icon_alterar.gif" style="cursor:pointer;"/>
                </vivo:tbRowColumn>
                <vivo:tbRowColumn key="ctd">
                    <img onmouseup="excluirGestor('<bean:write name="itG" property="nrCPF"/>');" alt="Excluir Gestor" src="<%=request.getContextPath()%>/resources/images/bt_icon_excluir.gif" style="cursor:pointer;"/>
                </vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>

<script type="text/javascript" language="javascript">
    var tds = $('tbResultadoPesquisa').select('[key="ctd"]');
    for (var i = 0; i < tds.length; i++) {
        tds[i].setStyle({
            verticalAlign: 'top',
            paddingTop: '5px'
        });
    }
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
</script>