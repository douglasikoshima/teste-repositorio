<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.commons.utils.BrowserDetect"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentoVODocument.AtendimentoRelacionamentoVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form"                 name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm" type="workflow.Relacionamento.RelacionamentoController.RelacionamentoForm"/>
<bean:define id="processosDeProtocolo" name="Form" property="processosDeProtocolo" />

<%!
private String getFormattedValue(String s, int qtMaxChr) {
    String r = "";
    if (s.length() >= qtMaxChr) {
        r = s.substring(0, qtMaxChr-3) + "...";
    } else {
        r = s;
    }
	return r;
}
%>

<html>
<body>
    <% if (request.getAttribute("msgErro") != null) { %>
    <div style="width:745px"><%=request.getAttribute("msgErro")%></div>
    <% } else { %>
    <div style="width:745px">
        <table width="745" border="0" class="tableProcesso" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <td valign="top"height="29" width="27"></td>
                    <td width="255" valign="top"><img src="<%=request.getContextPath()%>/resources/images/tableProcessosContato.gif" width="100" height="27" alt="Contato" /></td>
                    <td width="160" valign="top"><img src="<%=request.getContextPath()%>/resources/images/tableProcessosEstadoSubestado.gif" width="109" height="27" alt="Estado/Subestado" /></td>
                    <td width="100" valign="top"><img src="<%=request.getContextPath()%>/resources/images/tableProcessosOperador.gif" width="100" height="27" alt="Abertura" /></td>
                    <td width="100" valign="top"><img src="<%=request.getContextPath()%>/resources/images/tableProcessosAbertura.gif" width="100" height="27" alt="Abertura" /></td>
                    <td width="100" valign="top"><img src="<%=request.getContextPath()%>/resources/images/tableProcessosFechamento.gif" width="100" height="27" alt="Fechamento" /></td>
                </tr>
            </thead>
            <tbody>
                <logic:iterate id="atendimentoVO" name="processosDeProtocolo" property="protocoloVOArray[0].atendimentoVOArray" type="br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO">
                <%
                boolean inACS = "S".equalsIgnoreCase(atendimentoVO.getInACS()) ? true : false;
                String idAtendimento = atendimentoVO.getIdAtendimento();
                boolean inPortout = (atendimentoVO.getInPortout() == 1) ? true : false;
                String textoContato = atendimentoVO.getArvoreAtendimentoVO().getDescricaoCompleta().replaceAll("/"," / ");
                %>
                <tr>
                    <td height="35">
                        <img src="<%=request.getContextPath()%>/resources/images/iconProcesso.gif"
                        style="padding-left:3px;cursor:pointer"
                        align="middle"
                        onmouseup="<%=Form.isHistoricoRelacionamento()?"obtemDetalhesHistorico":"obtemDetalhes"%>(<%=inACS%>,'<%=idAtendimento%>',<%=inPortout%>)" />
                    </td>
                    <td>
                        <span style="width:255px;text-transform:uppercase" title="<bean:write name="atendimentoVO" property="arvoreAtendimentoVO.descricaoCompleta" />"><%=getFormattedValue(textoContato, 84)%>
                        </span>
                    </td>
                    <td>
                        <span style="width:160px" title="<bean:write name="atendimentoVO" property="WFEstadoVO.dsEstado" />/<bean:write name="atendimentoVO" property="WFSubEstadoVO.dsSubEstado" />">
                            <%=getFormattedValue(atendimentoVO.getWFEstadoVO().getDsEstado() + " / " + atendimentoVO.getWFSubEstadoVO().getDsSubEstado(), 45)%>
                        </span>
                    </td>
                    <td><bean:write name="atendimentoVO" property="usuarioVIVO.nmLoginUsuario" /></td>
                    <td><%= (!ConstantesCRM.SVAZIO.equals(atendimentoVO.getDtAbertura())) ? atendimentoVO.getDtAbertura().substring(0, atendimentoVO.getDtAbertura().length() - 3) : ConstantesCRM.SVAZIO %></td>
                    <td valign="middle">
                        <span style="vertical-align:middle;display:block;float:left;">
                        <%= (!ConstantesCRM.SVAZIO.equals(atendimentoVO.getDtFechamento())) ? atendimentoVO.getDtFechamento().substring(0, atendimentoVO.getDtFechamento().length() - 3) : ConstantesCRM.SVAZIO %>
                        </span>
                        <% if (inACS) {  %>
                        <span id="spanACS" style="vertical-align:middle;display:block;float:right;width:18px;height:15px;">
                            <img src="<%=request.getContextPath()%>/resources/images/icon_acs.gif">
                        </span>
                        <% } %>
                    </td>
                </tr>
                <tr><td colspan="5" class="lineSeparator" valign="top"><img src="<%=request.getContextPath()%>/resources/images/spacer.gif" height="6" /></td></tr>
                </logic:iterate>
            </tbody>
        </table>
    </div>
    <% } %>
</body>
</html>