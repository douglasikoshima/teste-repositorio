<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="detalhesProcessoForm.detalheHistoricoRetencao"
             type="br.com.vivo.fo.fidelizacao.vo.DetalheHistoricoRetencaoVODocument.DetalheHistoricoRetencaoVO" />

<%
int caractLength = (Form.getCaractOfertasAceitas() != null && Form.getCaractOfertasAceitas().getCaracteristicaArray().length > 1) ?
                   Form.getCaractOfertasAceitas().getCaracteristicaArray().length / 2
                   : 0;
%>

<vivo:quadro id="qdMain" height="105" width="735" label="Detalhamento do hist&oacute;rico" scroll="yes">
    <table border="0" cellpadding="0" cellspacing="0"><tr><td valign="top">
    <table>
        <tr>
            <td colspan="3" align="left">
                Intenção de Cancelamento:
            </td>
            <td>
                <strong><bean:write name="Form" property="intencao" /></strong>
            </td>

        </tr>
        <tr>
            <td colspan="3" align="left">
                Destino Previsto:
            </td>
            <td>
                <strong><bean:write name="Form" property="destino" /></strong>
            </td>
        </tr>
        <tr>
            <td colspan="3" align="left">
                Oferta Aceita:
            </td>
            <td>
                <strong><bean:write name="Form" property="ofertaAceita" /></strong>
            </td>
        </tr>
        <tr>
            <td colspan="3" align="left">
                Ofertas Recusadas:
            </td>
            <td>
                <select name="form" style="width:197px" class="SELECT" size="1">
                    <logic:iterate id="ofertasRealizadas" name="Form" property="listaOfertaRealizadaVO.ofertaRealizadaVOArray">
                    <option value="<bean:write name="ofertasRealizadas" property="id" />"><bean:write name="ofertasRealizadas" property="descricao" /></option>
                    </logic:iterate>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="3" align="left">
                Observação:
            </td>
            <td><strong><bean:write name="Form" property="comentario" /></strong>
            </td>
        </tr>
    </table>
    </td><td valign="top">
    <table>
        <% if (caractLength > 0) { %>
            <tr><td valign="top"><table>
                <logic:iterate id="carac" name="Form" property="caractOfertasAceitas.caracteristicaArray"  offset="caractLength">
                    <tr>
                        <td>
                            <bean:write name="carac" property="nome"/>:
                        </td>
                        <td>
                            <logic:equal name="carac" property="nome" value="meio pagamento">
                            <span title="<bean:write name="carac" property="valor"/>" style="width:65px;overflow:hidden;text-overflow:ellipsis;"><nobr><strong><bean:write name="carac" property="valor"/></strong></nobr></span>
                            </logic:equal>

                            <logic:notEqual name="carac" property="nome" value="meio pagamento">
                            <strong><bean:write name="carac" property="valor"  /></strong>
                            </logic:notEqual>
                        </td>
                    </tr>
                </logic:iterate>
                </table></td>
			</tr>
        <% } else if (caractLength == 0) { %>
                <logic:iterate id="carac" name="Form" property="caractOfertasAceitas.caracteristicaArray">
                    <tr>
                        <td>
                            <bean:write name="carac" property="nome"/>:
                        </td>
                        <td>
                            <strong><bean:write name="carac" property="valor"/></strong>
                        </td>
                    </tr>
                </logic:iterate>
        <% } %>
    </table>
    </td></tr></table>
</vivo:quadro>

<style type="text/css">
    #clienteWrapper {
        margin:5px;
    }
</style>