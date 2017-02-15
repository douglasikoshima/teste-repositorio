<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="relatorioForm"
             type="VOLTAV.relatorios.PontosContato.PontosContatoController.RelatorioForm" />

<script language="javascript" for="window" event="onload">
    <logic:notEmpty name="msgStatus" scope="request">
    alert('<bean:write name="msgStatus" scope="request"/>');
    </logic:notEmpty>
</script>

<vivo:tbTable selectable="true" styleId="tbRelBlindagem" layoutHeight="300" layoutWidth="960" tableWidth="960">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn width="120" type="String" align="left">Gerente de Contas</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="120" type="String" align="left">Gerente de Seção</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="120" type="String" align="left">Gerente de Divisão</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="120" type="String" align="left">GAM</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="120" type="String" align="left">Parceiro Vivo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="120" type="String" align="left">Técnico Residente</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="120" type="String" align="left">Consultor de Relacionamento 1</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="120" type="String" align="left">Consultor de Relacionamento 2</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="itContato" name="Form" property="listaPontoContato">
        <vivo:tbRow key="linha1"  id="linha1">
            <vivo:tbRowColumn>
                <span title="<bean:write name="itContato" property="nmGerenteContas"/><bean:write name="itContato" property="nrTelefoneGerContasFormatado"/>  <bean:write name="itContato" property="dsEmailGerContas"/>" style="width:100px;overflow:hidden;text-overflow:ellipsis">
                    <bean:write name="itContato" property="nmGerenteContas"/><logic:notEmpty name="itContato" property="nrTelefoneGerContasFormatado"><br><bean:write name="itContato" property="nrTelefoneGerContasFormatado"/></logic:notEmpty><logic:notEmpty name="itContato" property="dsEmailGerContas"><br><bean:write name="itContato" property="dsEmailGerContas"/></logic:notEmpty>
                </span>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <span title="<bean:write name="itContato" property="dsGerenteSecao"/><bean:write name="itContato" property="nrTelefoneGerSecaoFormatado"/>  <bean:write name="itContato" property="dsEmailGerSecao"/>" style="width:100px;overflow:hidden;text-overflow:ellipsis">
                    <bean:write name="itContato" property="dsGerenteSecao"/><logic:notEmpty name="itContato" property="nrTelefoneGerSecaoFormatado"><br><bean:write name="itContato" property="nrTelefoneGerSecaoFormatado"/></logic:notEmpty><logic:notEmpty name="itContato" property="dsEmailGerSecao"><br><bean:write name="itContato" property="dsEmailGerSecao"/></logic:notEmpty>
                </span>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <span title="<bean:write name="itContato" property="dsGerenteDivisao"/><bean:write name="itContato" property="nrTelenoneGerDivisaoFormatado"/>  <bean:write name="itContato" property="dsEmailGerDivisao"/>" style="width:100px;overflow:hidden;text-overflow:ellipsis">
                    <bean:write name="itContato" property="dsGerenteDivisao"/><logic:notEmpty name="itContato" property="nrTelenoneGerDivisaoFormatado"><br><bean:write name="itContato" property="nrTelenoneGerDivisaoFormatado"/></logic:notEmpty><logic:notEmpty name="itContato" property="dsEmailGerDivisao"><br><bean:write name="itContato" property="dsEmailGerDivisao"/></logic:notEmpty>
                </span>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <span title="<bean:write name="itContato" property="dsGAM"/><bean:write name="itContato" property="nrTelefoneGAMFormatado"/>  <bean:write name="itContato" property="dsEmailGAM"/>" style="width:100px;overflow:hidden;text-overflow:ellipsis">
                    <bean:write name="itContato" property="dsGAM"/><logic:notEmpty name="itContato" property="nrTelefoneGAMFormatado"><br><bean:write name="itContato" property="nrTelefoneGAMFormatado"/></logic:notEmpty><logic:notEmpty name="itContato" property="dsEmailGAM"><br><bean:write name="itContato" property="dsEmailGAM"/></logic:notEmpty>
                </span>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <span title="<bean:write name="itContato" property="nmDealer"/><bean:write name="itContato" property="nrTelefoneDealerFormatado"/>  <bean:write name="itContato" property="dsEmailDealer"/>" style="width:100px;overflow:hidden;text-overflow:ellipsis">
                    <bean:write name="itContato" property="nmDealer"/><logic:notEmpty name="itContato" property="nrTelefoneDealerFormatado"><br><bean:write name="itContato" property="nrTelefoneDealerFormatado"/></logic:notEmpty><logic:notEmpty name="itContato" property="dsEmailDealer"><br><bean:write name="itContato" property="dsEmailDealer"/></logic:notEmpty> <logic:notEmpty name="itContato" property="dsRazaoSocialDealer"><br><bean:write name="itContato" property="dsRazaoSocialDealer"/></logic:notEmpty><logic:notEmpty name="itContato" property="dsCidadeDealer"><br><bean:write name="itContato" property="dsCidadeDealer"/></logic:notEmpty>
                </span>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                <span title="<bean:write name="itContato" property="nmTecnicoResidente"/><bean:write name="itContato" property="nrTelefoneTecnicoFormatado"/>  <bean:write name="itContato" property="dsEmailTecnico"/>" style="width:100px;overflow:hidden;text-overflow:ellipsis">
                    <bean:write name="itContato" property="nmTecnicoResidente"/><logic:notEmpty name="itContato" property="nrTelefoneTecnicoFormatado"><br><bean:write name="itContato" property="nrTelefoneTecnicoFormatado"/></logic:notEmpty><logic:notEmpty name="itContato" property="dsEmailTecnico"><br><bean:write name="itContato" property="dsEmailTecnico"/></logic:notEmpty>
                </span>
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                    <span title="<bean:write name="itContato" property="nmConsultor1"/><bean:write name="itContato" property="nrTelConsultor1"/>  <bean:write name="itContato" property="dsEmailConsultor1"/>" style="width:100px;overflow:hidden;text-overflow:ellipsis">    
                        <bean:write name="itContato" property="nmConsultor1"/><logic:notEmpty name="itContato" property="nrTelConsultor1"><br><bean:write name="itContato" property="nrTelConsultor1"/></logic:notEmpty><br><logic:notEmpty name="itContato" property="dsEmailConsultor1"><br><bean:write name="itContato" property="dsEmailConsultor1"/></logic:notEmpty>
                    </span>                    
            </vivo:tbRowColumn>
            <vivo:tbRowColumn>
                    <span title="<bean:write name="itContato" property="nmConsultor2"/><bean:write name="itContato" property="nrTelConsultor2"/>  <bean:write name="itContato" property="dsEmailConsultor2"/>" style="width:100px;overflow:hidden;text-overflow:ellipsis">    
                        <bean:write name="itContato" property="nmConsultor2"/><logic:notEmpty name="itContato" property="nrTelConsultor2"><br><bean:write name="itContato" property="nrTelConsultor2"/></logic:notEmpty><br><logic:notEmpty name="itContato" property="dsEmailConsultor2"><br><bean:write name="itContato" property="dsEmailConsultor2"/></logic:notEmpty>
                    </span>                                    
            </vivo:tbRowColumn>   
        </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>