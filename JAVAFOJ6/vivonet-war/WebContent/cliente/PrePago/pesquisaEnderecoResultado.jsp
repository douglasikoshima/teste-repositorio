<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Enderecos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="prePagoEnderecoForm.pesquisaEnderecoVO"/>
<bean:define id="End"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="prePagoEnderecoForm"/>

<script type="text/javascript">
    parent.IN_PESQUISA_ENDERECO_BASE_FO = <bean:write name="End" property="pesquisaEnderecoBaseFO" />;
</script>

<vivo:tbTable  selectable="true" onRowClick="" layoutWidth="675" layoutHeight="155" tableWidth="675" styleId="tableTitle" sortable="true">
    <vivo:tbHeader>
            <vivo:tbHeaderColumn align="left"   width="5%"  type="string"></vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left"   width="10%" type="string">CEP</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left"   width="25%" type="string">Município</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left"   width="25%" type="string">Logradouro</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left"   width="10%" type="string">Lado</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left"   width="20%" type="string">Bairro</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="5%"  type="string">UF</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <logic:empty name="Enderecos" property="erro">
        <logic:notEmpty name="msg">
            <%--// POG para controle do tratamento qdo não encontrado --%>
            <tr><!--vivo:tbRow key="linha"--><td colspan="7" bgcolor="#f7f9fa"><font color="#f7f9fa"><span id="msgEnd"><bean:write name="msg"/></span></font></td><!--/vivo:tbRow--></tr>
            <vivo:tbRows>
            </vivo:tbRows>
        </logic:notEmpty>
        <logic:notEmpty name="erro">
            <tr><!--vivo:tbRow key="linha"--><td colspan="7" bgcolor="#f7f9fa"><font color="#f7f9fa"><span id="errEnd"><bean:write name="erro"/></span></font></td><!--/vivo:tbRow--></tr>
            <vivo:tbRows>
            </vivo:tbRows>
        </logic:notEmpty>
        <logic:empty name="msg">
            <vivo:tbRows>
                <logic:iterate id="resultados" name="Enderecos" property="listaEnderecos.enderecoVOArray" indexId="idx">
                    <bean:define id="addrTit" name="resultados" property="nmTituloLogradouro" type="java.lang.String"/>
                    <vivo:tbRow key="Linha">
                        <vivo:tbRowColumn><input name="enderecoSelecionado" id="enderecoSelecionado" type="radio" class="radio" value="<bean:write name="resultados" property="idEndereco" />"></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="resultados" property="nrCEP"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="resultados" property="nmMunicipio"/></vivo:tbRowColumn>
                        <logic:equal name="resultados" property="nmTipoLogradouro" value="<%=addrTit%>">
                            <vivo:tbRowColumn><bean:write name="resultados" property="nmTipoLogradouro"/> <bean:write name="resultados" property="nmLogradouro"/></vivo:tbRowColumn>
                        </logic:equal>
                        <logic:notEqual name="resultados" property="nmTipoLogradouro" value="<%=addrTit%>">
                            <vivo:tbRowColumn><bean:write name="resultados" property="nmTipoLogradouro"/> <bean:write name="resultados" property="nmTituloLogradouro"/> <bean:write name="resultados" property="nmLogradouro"/></vivo:tbRowColumn>
                        </logic:notEqual>
                        <vivo:tbRowColumn><bean:write name="resultados" property="dsLado"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="resultados" property="nmBairro"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="resultados" property="UFVO.sgUF"/></vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </logic:empty>
    </logic:empty>
</vivo:tbTable>
<logic:empty name="msg"><logic:empty name="erro">
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <div align="right">
        <img onClick="recuperarEndereco();" src="/FrontOfficeWeb/resources/images/bt_selecionar_nrml.gif" style="border:none;"/>
    </div>
</logic:empty></logic:empty>
