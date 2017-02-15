<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="showDetalheHistoricoForm"/>
<bean:define id="caractLength" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="showDetalheHistoricoForm.caractLength"/>
<bean:define id="ofertaRecusadaArray"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="showDetalheHistoricoForm.ofertaRecusadaArray"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
                <!--
                    top.frameApp.oculta_div();
                -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <vivo:quadro id="qdMain" height="125" width="748" label="Detalhamento do hist&oacute;rico" scroll="yes">
            <form action="getHistorico.do" method="post">
                <table border="0" cellpadding="0" cellspacing="0"><tr><td valign="top">
                <table>
                    <tr>
                        <td colspan="3" align="left">
                            Intenção de Cancelamento:
                        </td>
                        <td>
                            <b><bean:write name="form" property="intencao"/></b>
                        </td>
        
                    </tr>
                    <tr>
                        <td colspan="3" align="left">
                            Destino Previsto:
                        </td>
                        <td>
                            <b><bean:write name="form" property="destino"/></b>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="left">
                            Oferta Aceita:
                        </td>
                        <td>
                            <b><bean:write name="form" property="ofertaAceita"/></b>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="left">
                            Ofertas Recusadas:
                        </td>
                        <td>
                            <html:select name="form" property="observacao" style="width:197" styleClass="SELECT" size="1">
                                <html:options collection="ofertaRecusadaArray" property="id" labelProperty="descricao" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="left">
                            Observação:
                        </td>
                        <td><b><bean:write name="form" property="observacao"/></b>
                        </td>
                    </tr>
        
                </table>
                </td><td valign="top">
                <table>
                    <logic:notEmpty name="caractLength">
                        <logic:greaterThan name="caractLength" value="0">
                            <tr><td valign="top"><table>
                            <logic:iterate id="carac" name="form" property="caractOfertasAceitas.caracteristicaArray" length="caractLength">
                                <tr>
                                    <td>
                                        <bean:write name="carac" property="nome"/>:
                                    </td>
                                    <td>
                                        <b><bean:write name="carac" property="valor"/></b>
                                    </td>
                                </tr>
                            </logic:iterate>
                            </table></td><td valign="top"><table>
                            <logic:iterate id="carac" name="form" property="caractOfertasAceitas.caracteristicaArray"  offset="caractLength">
                                <tr>
                                    <td>
                                        <bean:write name="carac" property="nome"/>:
                                    </td>
                                    <td>
                                        <logic:equal name="carac" property="nome" value="meio pagamento">
                                        <span title="<bean:write name="carac" property="valor"/>" style="width:65px;overflow:hidden;text-overflow:ellipsis;"><nobr><b><bean:write name="carac" property="valor"/></b></nobr></span>
                                        </logic:equal>
        
                                        <logic:notEqual name="carac" property="nome" value="meio pagamento">
                                        <b><bean:write name="carac" property="valor"  /></b>
                                        </logic:notEqual>
                                    </td>
                                </tr>
                            </logic:iterate>
                            </table></td></tr>
                        </logic:greaterThan>
                        <logic:equal name="caractLength" value="0">
                            <logic:iterate id="carac" name="form" property="caractOfertasAceitas.caracteristicaArray">
                                <tr>
                                    <td>
                                        <bean:write name="carac" property="nome"/>:
                                    </td>
                                    <td>
                                        <b><bean:write name="carac" property="valor"/></b>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </logic:equal>
                    </logic:notEmpty>
                </table>
                </td></tr></table>
            </form>
        </vivo:quadro>
    </netui-template:section>
</netui-template:template>

