<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">

    <script>
        function initPagina() { }
    </script>

    <netui-template:section name="bodySection">
            <table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="#F5F5F5">
                <tr class="TableTitleL1">
                    <td class="TableTitleL1">Vincular Regras de Encaminhamento</td>
                </tr>
                <tr class="TableTitleC3">
                    <td>
                    <table width="99%" border="0" cellspacing="3" cellpadding="0" align="center">
                        <tr>
                            <td width="18%" align="center">
                                <table width="100%" class="BorderTable3" cellspacing="1" cellpadding="0" align="center">
                                    <tr>
                                        <td class="TableTitleC1">
                                            <netui:label value="Grupos de"/><br/>
                                            <netui:label value="Tratamento"/> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">
                                            <netui:select dataSource="{}" multiple="true" styleClass="Combo5F">
                                                <netui:selectOption value="Grupo1"/>
                                                <netui:selectOption value="Grupo2"/>
                                                <netui:selectOption value="Grupo3"/>
                                                <netui:selectOption value="Grupo4"/>
                                            </netui:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">
                                            <netui:button type="submit" value="Visualizar Encam."/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="78%" align="center">
                                <table width="100%" class="BorderTable3" cellspacing="1" cellpadding="0" align="center">
                                    <tr>
                                        <td class="TableTitleC1" colspan="4">
                                            <netui:label value="Regras de Encaminhamento"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="TableTitleC2" width="25%">
                                            <netui:label value="Tipo de Linha"/>
                                        </td>
                                        <td class="TableTitleC2" width="25%">
                                            <netui:label value="Tipo de Segmentação"/>
                                        </td>
                                        <td class="TableTitleC2" width="25%">
                                            <netui:label value="Tipo de Carteirização"/>
                                        </td>
                                        <td class="TableTitleC2" width="25%">
                                            <netui:label value="Tipo de Procedencia"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center" width="25%">
                                            <netui:select dataSource="{}" multiple="true" styleClass="Combo5F">
                                                <netui:selectOption value="Corporativo"/>
                                                <netui:selectOption value="Gestão"/>
                                                <netui:selectOption value="Pós-pago"/>
                                                <netui:selectOption value="Pré-pago"/>
                                            </netui:select>
                                        </td>
                                        <td align="center" width="25%">
                                            <netui:select dataSource="{}" multiple="true" styleClass="Combo5F">
                                                <netui:selectOption value="Diamante"/>
                                                <netui:selectOption value="Rubi"/>
                                                <netui:selectOption value="Esmeralda"/>
                                                <netui:selectOption value="Ouro"/>
                                                <netui:selectOption value="Prata"/>
                                                <netui:selectOption value="Bronze"/>
                                            </netui:select>
                                        </td>
                                        <td align="center" width="25%">
                                            <netui:select dataSource="{}" multiple="true" styleClass="Combo5F">
                                                <netui:selectOption value="Carteirizacao1"/>
                                                <netui:selectOption value="Carteirizacao2"/>
                                                <netui:selectOption value="Carteirizacao3"/>
                                            </netui:select>
                                        </td>
                                        <td align="center" width="25%">
                                            <netui:select dataSource="{}" multiple="true" styleClass="Combo5F">
                                                <netui:selectOption value="Procedencia1"/>
                                                <netui:selectOption value="Procedencia2"/>
                                                <netui:selectOption value="Procedencia3"/>
                                            </netui:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" class="TableCellL1">
                                            &nbsp;<netui:label value="Peso" styleClass="TableCellL1"/>&nbsp;<netui:textBox dataSource="{}" styleClass="Combo5F"/>
                                    
                                        </td>
                                        <td colspan="2" align="right">
                                            <netui:button type="submit" value="Inserir Regra Encam."/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr class="TableTitleC3">
                    <td>
                    <table width="98%" class="BorderTable3" cellspacing="2" cellpadding="0" align="center">
                        <tr>
                            <td class="TableTitleL1" align="center">
                                <netui:label value="Regras de Encaminhamento Associadas ao Grupo Selecionado"/> 
                            </td>
                        </tr>
                        <tr>
                            <td class="TableTitleL1">
                                <table width="100%" class="BorderTable2" cellspacing="2" cellpadding="0" align="center">
                                    <tr>
                                        <td class="TableTitleC2" width="25%">
                                            <netui:label value="Tipo de Linha"/>
                                        </td>
                                        <td class="TableTitleC2" width="25%">
                                            <netui:label value="Tipo de Segmentação"/>
                                        </td>
                                        <td class="TableTitleC2" width="25%">
                                            <netui:label value="Tipo de Carteirização"/>
                                        </td>
                                        <td class="TableTitleC2" width="25%">
                                            <netui:label value="Tipo de Procedência"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="TableCellL1" width="25%">
                                            <netui:label value="Label"/>
                                        </td>
                                        <td class="TableCellL1" width="25%">
                                            <netui:label value="Label"/>
                                        </td>
                                        <td class="TableCellL1" width="25%">
                                            <netui:label value="Label"/>
                                        </td>
                                        <td class="TableCellL1" width="25%">
                                            <netui:label value="Label"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="TableCellL1" width="25%">
                                            <netui:label value="Label"/>
                                        </td>
                                        <td class="TableCellL1" width="25%">
                                            <netui:label value="Label"/>
                                        </td>
                                        <td class="TableCellL1" width="25%">
                                            <netui:label value="Label"/>
                                        </td>
                                        <td class="TableCellL1" width="25%">
                                            <netui:label value="Label"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </td>
                </tr>
            </table>

    </netui-template:section>
</netui-template:template>