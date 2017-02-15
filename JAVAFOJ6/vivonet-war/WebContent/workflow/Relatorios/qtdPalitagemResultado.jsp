<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ColunasRelatorio"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ValoresRelatorio"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ValoresRelatorio.ValorColuna"%>
<%@ page import="workflow.Relatorios.RelatoriosController.RelatorioForm"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="stNiveis" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="stNiveisPalitagem"/>
<%
    String dsTituloRelatorio = ((RelatorioForm)relatorioForm).getwFRelatorioDinamicoVO().getDsTituloRelatorio();
    String detalheScript = ((RelatorioForm)relatorioForm).getDetalheScript();
%>
<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection">
        <script>
            <!--
            function abreNivel(filtro, dsFiltro, inProximoNivel) {
                if(inProximoNivel == 1){
                    //Inicio animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    document.forms[0].idFiltro.value=filtro;
                    document.forms[0].dsFiltro.value=dsFiltro;
                    document.forms[0].method = "POST";
                    document.forms[0].action = "<bean:write name="relatorioForm" property="detalheAction"/>.do";
                    document.forms[0].submit();
                } else {
                    alert("Este é o último nível desse registro!");
                }
            }

            function fecharQuadroFlutuante() {
                dvDetalhes.style.display = 'none';
            }

            function submitVoltar(){
                //Inicio animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                document.forms[0].method = "POST";
                document.forms[0].action = "gerarQtdPalitagemVoltar.do";
                document.forms[0].submit();
            }

            function imprimir() {
                window.showModalDialog("gerarQtdPalitagem1Imprimir.do","Relatorio de Palitagem","dialogWidth:675px;dialogHeight:450px");
            }
            -->
        </script>
    </netui-template:section>
<netui-template:section name="bodySection">
    <!--APLICACAO-->
    <%--<acesso:controlHiddenItem nomeIdentificador="wor_rrstdo_verpagina">--%>
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>

    <vivo:quadro id="qdrMain" height="475" width="790" label='<%= dsTituloRelatorio %>' scroll="no">
    <table cellpadding="5" cellspacing="2" border="0" width="100%">
        <tr>
            <td width="110" align="right"><b>Grupo Selecionado:</b></td>
            <td width="150"><bean:write name="relatorioForm" property="dsGrupo"/></td>
            <td align="center"><b>
                <logic:empty name="stNiveis">
                    <bean:write name="relatorioForm" property="nivelSel"/>
                </logic:empty>
                <logic:notEmpty name="stNiveis">
                    <logic:iterate id="niveis" name="stNiveis" type="java.lang.String" indexId="idxNiveis">
                        <logic:notEqual name="idxNiveis" value="0">/</logic:notEqual>
                        <bean:write name="niveis"/>
                    </logic:iterate>
                </logic:notEmpty>
                </b>
            </td>
            <td align="right" width="260">
                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_imprimir_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_imprimir_over.gif" id="btPrint" value="Imprimir" onClick="imprimir(); return false"/>
            </td>
        </tr>
    </table>
    <div style="width:760; heigth:365;">
    <vivo:tbTable selectable="false" onRowClick="" resize="false" layoutWidth="760" layoutHeight="365" tableWidth="760" styleId="tdTipoProcessosDet1" sortable="false">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="left" width="20%" type="string">Horários</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="left" width="80%" type="string">Detalhes</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
            <logic:iterate id="horarios" name="relatorioForm" property="htHorariosRelatorios" indexId="idxHorarios">
                <vivo:tbRow key="" zebrar="S">
                    <vivo:tbRowColumn key="">
                        <center><font size="4"><b><bean:write name="horarios" property="key"/></b></font></center>
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn key="">
                        <TABLE cellpadding="0" cellspacing="0" width="100%">
                            <TR>
                                <logic:iterate offset="1" id="colunasVO" name="relatorioForm" property="wFRelatorioDinamicoVO.colunasRelatorioArray" indexId="idx">
                                    <TD class="holderTabela" align="center" height="15" name="notNamed">
                                        <bean:write name="colunasVO" property="dsColuna"/>
                                    </TD>
                                </logic:iterate>
                                </TR>
                                <% int linhas = 0; %>
                                <logic:iterate id="valoresRelatorioVO" name="horarios" property="value" indexId="idx2">
                                    <%
                                    ValorColuna[] valorColuna = ((ValoresRelatorio)valoresRelatorioVO).getValorColunaArray();
                                    String inProximoNivel = ((ValoresRelatorio)valoresRelatorioVO).getInProximoNivel();
                                    int porcentagem = ((RelatorioForm)relatorioForm).getAlarme();
                                    double vlHoje  = Double.parseDouble( valorColuna[2].getValor() );
                                    double vlData1 = Double.parseDouble( valorColuna[3].getValor() );
                                    double vlData2 = Double.parseDouble( valorColuna[4].getValor() );
                                    double p = (vlHoje/(vlData1+vlData2) - 1) * -100;

                                    String fgColor = "darkred";
                                    if (porcentagem < p) {
                                        fgColor = "darkblue";
                                    }
                                    String rowScript = null;
                                    rowScript = "abreNivel('"+valorColuna[1].getIdValor()+"','"+valorColuna[1].getValor()+"','"+inProximoNivel+"')";
                                    String classLinha=null;
                                    if (linhas % 2 == 0)
                                        classLinha = "rowTabelaZebradoOff";
                                    else
                                        classLinha = "rowTabelaZebradoOn";
                                    linhas++;

                                    String linkProxNivel = "";
                                    if (inProximoNivel.equals("1"))
                                        linkProxNivel = "onMouseOver=\"hoverRow();\" onMouseOut=\"unhoverRow();\" onclick=\"" + rowScript + "\"";
                                    %>
                                    <TR <%=linkProxNivel%> chave="" status="consulta" class='<%=classLinha%>' zebrado='S'>
                                        <logic:iterate id="valoresColunasVO" offset="1" name="valoresRelatorioVO" property="valorColunaArray" indexId="idx3">
                                            <TD name="notNamed" align="left" witdh="25%" key="">
                                                <center><b>
                                                <logic:equal name="idx3" value="2">
                                                    <font color="<%=fgColor%>">
                                                </logic:equal>
                                                <bean:write name="valoresColunasVO" property="valor"/>
                                            </TD>
                                        </logic:iterate>
                                    </TR>
                                </logic:iterate>
                            </TABLE>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>
    </div>
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="left" width="30%" height="18">
                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" id="btVoltar"  value="Voltar"  onClick="submitVoltar(); return false"/>
            </td>
        </tr>
    </table>
    <form>
        <input type="hidden" name="dsFiltro">
        <input type="hidden" name="idFiltro">
    </form>
</vivo:quadro>
<%--</acesso:controlHiddenItem>--%>
<script>
    <logic:empty name="horarios">
    alert("Não existem mais níveis!");
    </logic:empty>

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
</script>
</netui-template:section>
</netui-template:template>