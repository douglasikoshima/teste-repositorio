<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="listaHistoricoVO"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="detalhesProcessoForm.historicoRetencao.historicoRetencaoVOArray"
             />

<div id="clienteWrapper">
    <vivo:tbTable selectable="true" layoutWidth="720" layoutHeight="130" tableWidth="720" styleId="tbHistoricoRetencao" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="16%" type="string">Login</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="16%" type="date">Data</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="16%" type="string">Processo</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="22%" type="string">Status</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="16%" type="string">Detalhe</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="14%" type="string">&nbsp;</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate name="listaHistoricoVO" id="iterHistorico">
            <vivo:tbRow key="linha1">
                <vivo:tbRowColumn><bean:write name="iterHistorico" property="login" /></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="iterHistorico" property="data" /></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="iterHistorico" property="processo" /></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="iterHistorico" property="status" /></vivo:tbRowColumn>
                <vivo:tbRowColumn>
                    <img src="/FrontOfficeWeb/resources/images/lupa_bg_transp.gif"
                         onclick="selectHistorico(this);getDetalheHistoricoRetencao(<bean:write name="iterHistorico" property="idRetencao" />);"/>
                </vivo:tbRowColumn>
                <vivo:tbRowColumn>
                    <!-- Normal -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="0">
                    </logic:equal>
                    <!-- Análise de crédito pendente -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="1">
                        <img title="Análise de crédito pendente"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_analisecredito_pendente.gif" />
                    </logic:equal>
                    <!-- Análise de crédito aprovada -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="2">
                        <img title="Análise de crédito aprovada"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_analisecredito_ok.gif" />
                    </logic:equal>
                    <!-- Análise de crédito reprovada -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="3">
                        <img title="Análise de crédito reprovada"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_analisecredito_nok.gif" />
                    </logic:equal>
                    <!-- Análise de endereço pendente -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="4">
                        <img title="Análise de endereço pendente"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_endereco_pendente.gif" />
                    </logic:equal>
                    <!-- Análise de endereço reprovada -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="5">
                        <img title="Análise de endereço reprovada"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_endereco_nok.gif" />
                    </logic:equal>
                </vivo:tbRowColumn>
            </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>

    <table cellpadding="0" cellspacing="0" style="border:1px solid #adadad; background-color:#E7E7E7; margin-bottom:5px;" width="735" border="0">
        <tr style="background-color:#fff;">
            <td style="font-size:9px;line-height:25px;padding:2px 0 2px 0" align="center">
                <img align="absmiddle" src="<%=request.getContextPath()%>/resources/images/bt_icon_analisecredito_pendente.gif" /> Análise de crédito pendente
                &nbsp;<img align="absmiddle" title="Análise de crédito aprovada" src="<%=request.getContextPath()%>/resources/images/bt_icon_analisecredito_ok.gif" /> Análise de crédito aprovada
                &nbsp;<img align="absmiddle" title="Análise de crédito reprovada" src="<%=request.getContextPath()%>/resources/images/bt_icon_analisecredito_nok.gif" /> Análise de crédito reprovada
                &nbsp;<img align="absmiddle" title="Análise de endereço pendente" src="<%=request.getContextPath()%>/resources/images/bt_icon_endereco_pendente.gif" /> Análise de endereço pendente
                &nbsp;<img align="absmiddle" title="Análise de endereço reprovada" src="<%=request.getContextPath()%>/resources/images/bt_icon_endereco_nok.gif" /> Análise de endereço reprovada
            </td>
        </tr>
    </table>

	<div id="containerDetalheHistoricoRetencao">
        <vivo:quadro id="qdMain" height="105" width="735" label="Detalhamento do hist&oacute;rico" scroll="yes"></vivo:quadro>
    </div>

</div>

<style type="text/css">
    #clienteWrapper {
        margin:5px;
    }
</style>