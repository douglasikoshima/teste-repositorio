<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="dados" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="prePagoEnderecoForm"/>
<script type="text/javascript">
    cdUFEndPrincipal = '';
    getSgUFById = function(idUF, objSelectUF) {
        var sgUF = '';
        for (var i = 0; i < objSelectUF.length; i++) {
            if (objSelectUF.options[i].value == idUF) {
                sgUF = objSelectUF.options[i].text;
                break;
            }
        }
        return sgUF;
    }
</script>
<vivo:tbTable selectable="true" layoutWidth="745" layoutHeight="100" tableWidth="745" styleId="tableTitle" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="15%" type="string">Endereço Principal</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="60%" type="string">Endereços Cadastrados</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="15%" type="string">Cidade</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:notEmpty name="dados" property="dadosEndereco">
        <logic:iterate id="list" name="dados" property="dadosEndereco" indexId="idx" type="br.com.vivo.fo.cliente.vo.DadosEnderecoDocument.DadosEndereco">
            <vivo:tbRow key="Linha">
                <vivo:tbRowColumn>
                    <logic:notEqual value="1" name="list" property="inEndPrincipal">NÃO</logic:notEqual>
                    <logic:equal value="1" name="list" property="inEndPrincipal">
                        SIM
                        <script type="text/javascript">
                        cdUFEndPrincipal = getSgUFById('<bean:write name="list" property="idUF" />', $('idUFEnderecoCli'));
                        </script>
                    </logic:equal>
                </vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="list" property="nmTipoLogradouro"/> <bean:write name="list" property="nmTitLogradouro"/> <bean:write name="list" property="nmLogradouro"/>, <bean:write name="list" property="nrLogradouro"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="list" property="nmMunicipio"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" style="cursor:pointer" border="0" onclick="loadDadosAlteraEndereco('<%=idx%>');"></vivo:tbRowColumn>
                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" style="cursor:pointer" border="0" onclick="excluirEndereco('<%=idx%>');"></vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
        </logic:notEmpty>
    </vivo:tbRows>
</vivo:tbTable>