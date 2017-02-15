<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<acesso:controlInitEnv/>
<bean:define id="lDetalhe"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lDetalhe"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_doe_verpagina">
        <table>
            <tr>
                <td>
                    <vivo:tbTable selectable="true" layoutWidth="665" layoutHeight="300" tableWidth="665" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="350" type="string">Nome</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="350" type="string">Valor</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <netui-data:repeater dataSource="{pageContext.lDetalhe}">
                                <netui-data:repeaterHeader> </netui-data:repeaterHeader>
                                <netui-data:repeaterItem>
                                    <vivo:tbRow key="linha1">
                                        <vivo:tbRowColumn>
                                        <netui:label value="{container.item.nome}" /> </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                        <netui:label value="{container.item.valor}" /> </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </netui-data:repeaterItem>
                                <netui-data:repeaterFooter> </netui-data:repeaterFooter>
                            </netui-data:repeater>
                        </vivo:tbRows>
                    </vivo:tbTable>
                <td>
            </tr>
        </table>
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
