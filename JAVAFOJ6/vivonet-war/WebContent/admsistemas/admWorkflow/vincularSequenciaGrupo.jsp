<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">

    <script>
        function initPagina() { }
    </script>

    <netui-template:section name="bodySection">
            <table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="#F5F5F5">
                <tr class="TableTitleL1">
                    <td class="TableTitleL1">Vincular Sequ&ecirc;ncia dos Grupos</td>
                </tr>
                <tr class="TableTitleC3">
                    <td>
                    <table width="99%" border="0" cellspacing="3" cellpadding="0" align="center">
                        <tr>
                            <td width="100%" align="center">
                                <table width="100%" class="BorderTable3" cellspacing="1" cellpadding="0" align="center">
                                    <tr>
                                        <td class="TableTitleC1" width="50%" >
                                            <netui:label value="Grupos Disponíveis"/><br/>
                                        </td>
                                        <td class="TableTitleC1" width="50%">
                                            <netui:label value="Sequência"/><br/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center" width="50%">
                                            <netui:select dataSource="{}" multiple="true" styleClass="Combo3F" size="16">
                                                <netui:selectOption value="Grupo4"/>
                                                <netui:selectOption value="Grupo5"/>
                                                <netui:selectOption value="Grupo6"/>
                                                <netui:selectOption value="Grupo7"/>
                                            </netui:select>
                                        </td>
                                        <td align="center" width="50%">
                                            <table width="100%" cellspacing="1" cellpadding="0" align="center">
                                                <tr>
                                                    <td class="TableTitleC2" colspan="2">
                                                        <netui:label value="Abertura"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center" width="20%">
                                                        <input type="submit" value=">"/><br/>
                                                        <input type="submit" value="<"/><br/>
                                                        <input type="submit" value="<<"/>
                                                    </td>
                                                    <td align="center" width="80%">
                                                        <netui:select dataSource="{}" multiple="true" styleClass="Combo3F">
                                                            <netui:selectOption value="Grupo1"/>
                                                        </netui:select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="TableTitleC2" colspan="2">
                                                        <netui:label value="Tratamento"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center" width="20%">
                                                        <input type="submit" value=">"/><br/>
                                                        <input type="submit" value="<"/><br/>
                                                        <input type="submit" value="<<"/>
                                                    </td>
                                                    <td align="center" width="80%">
                                                        <netui:select dataSource="{}" multiple="true" styleClass="Combo3F">
                                                            <netui:selectOption value="Grupo2"/>
                                                        </netui:select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="TableTitleC2" colspan="2">
                                                        <netui:label value="Retorno"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center" width="20%">
                                                        <input type="submit" value=">"/><br/>
                                                        <input type="submit" value="<"/><br/>
                                                        <input type="submit" value="<<"/>
                                                    </td>
                                                    <td align="center" width="80%">
                                                        <netui:select dataSource="{}" multiple="true" styleClass="Combo3F">
                                                            <netui:selectOption value="Grupo3"/>
                                                        </netui:select>
                                                    </td>
                                                </tr>
                                            </table>
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