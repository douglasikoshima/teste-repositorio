<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro" />
<bean:define id="ListaArquivosGerados" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreParametro.arquivosGerados"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Arquivo Gerado - Batch"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="Javascript">

        function enviaArquivo(arqNome) {
            document.forms[0].target = '';
            document.forms[0].dsPathArquivo.value = arqNome;
            document.forms[0].action = 'disponibilizaArquivo.do';
            document.forms[0].submit();
        }

        consultaFiltrosSelecionados = function(nmArquivo, idArquivo) {
            dvFiltros = document.getElementById("dvFiltrosSelecionados");
            document.getElementById("dv_dvFiltrosSelecionados").innerHTML = nmArquivo;
            dvFiltros.style.display = "block";
            ifrmFiltrosSelecionados.document.body.style.fontFamily = "Tahoma";
            ifrmFiltrosSelecionados.document.body.style.fontSize = "11px";
            ifrmFiltrosSelecionados.document.body.innerHTML = "&nbsp;carregando...";
            document.forms[0].idArquivo.value = idArquivo;
            document.forms[0].target = 'ifrmFiltrosSelecionados';
            document.forms[0].action = 'consultaFiltrosSelecionados.do?idArquivo='+idArquivo;
            document.forms[0].submit();
        }

        </script>
    </netui-template:section>

    <netui-template:section name="bodySection">

        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <vivo:sessao id="qdMain" height="475" width="790" label="Variáveis de Árvore de Contatos" operacoes="Arquivos Gerados" scroll="no">

        <form action="consultaFiltrosSelecionados.do" method="post">
        <html:hidden name="Form" property="idArquivo" />
        <html:hidden name="Form" property="dsPathArquivo" />
        <vivo:tbTable selectable="true" layoutWidth="762" layoutHeight="370" tableWidth="762" styleId="tableTitle" >
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left"   width="34%" type="string">Nome do arquivo</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left"   width="16%" type="string">Solicitante</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left"   width="20%" type="string">Data/Hora da solicitação</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left"   width="20%" type="string">Data/Hora da geração</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:iterate name="ListaArquivosGerados" id="itemArquivo">
                    <vivo:tbRow key="linha">
                        <vivo:tbRowColumn>
                            <bean:write name="itemArquivo" property="nmArquivo" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="itemArquivo" property="nmLoginSolicitante" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="itemArquivo" property="dtSolicitacao" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <bean:write name="itemArquivo" property="dtGeracao" />
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <logic:notEmpty name="itemArquivo" property="dtGeracao">
                            <img align="absmiddle" alt="Baixar arquivo gerado" src="<%=request.getContextPath()%>/resources/images/bt_icon_download.gif" onmouseup="enviaArquivo('<bean:write name="itemArquivo" property="dsPath" />')"/>
                            </logic:notEmpty>
                        </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <img style="cursor:pointer;" align="absmiddle" title="Consulta a filtros selecionados" src="<%=request.getContextPath()%>/resources/images/bt_icon_filtro.gif" onmouseup="consultaFiltrosSelecionados('<bean:write name="itemArquivo" property="nmArquivo" />','<bean:write name="itemArquivo" property="id" />')" />
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
        <table cellpadding="0" cellspacing="0" style="border:1px solid #adadad; background-color:#E7E7E7; margin-bottom:5px;" width="780" border="0">
            <tr style="background-color:#fff;">
                <td style="font-size:9px;line-height:25px;padding:2px 0 2px 10px" align="left">
                    <img align="absmiddle" alt="Baixar arquivo gerado" src="<%=request.getContextPath()%>/resources/images/bt_icon_download.gif" /> Baixar arquivo gerado
                    <img style="margin:0 2px 0 20px;" align="absmiddle" title="Consulta a filtros selecionados" src="<%=request.getContextPath()%>/resources/images/bt_icon_filtro.gif" /> Consulta a filtros selecionados
                </td>
            </tr>
        </table>

        <vivo:quadroFlutuante id="dvFiltrosSelecionados" idIframe="ifrmFiltrosSelecionados" height="300" width="350" spacesTop="60" spacesLeft="250" display="none" url="<%=request.getContextPath()%>" label="Filtros selecionados"/>

        </form>

        </vivo:sessao>

        <vivo:alert atributo="msgErro" scope="request"/>

    </netui-template:section>
</netui-template:template>