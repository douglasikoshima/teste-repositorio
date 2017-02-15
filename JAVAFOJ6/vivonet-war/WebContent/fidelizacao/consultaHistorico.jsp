<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="fidelizacao.FidelizacaoController.ShowDetalheHistoricoForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>
<bean:define id="listaHistoricoVO"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaHistoricoVO.historicoRetencaoVOArray"/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
                <!--
                    top.frameApp.oculta_div();
                    top.frameApp.$("idAnime").style.display = "none";
                -->
        </SCRIPT>
        <script>
            function selectHistorico(obj){
                for(i=0; i< document.getElementById("table_body").rows.length; i++){
                    if(document.getElementById("table_body").rows[i].cells[4].all[0] == obj || document.getElementById("table_body").rows[i].cells[4].all[1] == obj){
                        document.getElementById("table_body").rows[i].style.backgroundColor="#cecece";
                    }else{
                        document.getElementById("table_body").rows[i].style.backgroundColor="#f5f5f5";
                    }
                }
            }

            function getAnaliseCredito(idRetencao, acao) {
                top.frameApp.mostrar_div();
                top.frameApp.$("idAnime").style.display = "block";
                parent.ifrmAnalisesBKO.location.href = "analisarCredito.do?idRetencao="+idRetencao+"&acao="+acao;
                parent.parent.parent.mostrar_div();
                parent.dvAnalisesBKO.style.display = '';
                if (acao == "REPROVADA") {
                    parent.dv_dvAnalisesBKO.innerHTML = "Detalhamento do hist&oacute;rico";
                } else {
                    parent.dv_dvAnalisesBKO.innerHTML = "An&aacute;lise de Cr&eacute;dito";
                }
            }

            function getAnaliseEndereco(idRetencao, acao) {
                top.frameApp.mostrar_div();
                top.frameApp.$("idAnime").style.display = "block";
                parent.ifrmAnalisesBKO.location.href = "analisarEndereco.do?idRetencao="+idRetencao+"&acao="+acao;
                parent.parent.parent.mostrar_div();
                parent.dvAnalisesBKO.style.display = '';
                if (acao == "REPROVADA") {
                    parent.dv_dvAnalisesBKO.innerHTML = "Detalhamento do hist&oacute;rico";
                } else {
                    parent.dv_dvAnalisesBKO.innerHTML = "An&aacute;lise de Endere&ccedil;o";
                }
            }

            function efetivarRetencaoAnaliseCreditoAprovada(idRetencao) {
                top.frameApp.mostrar_div();
                top.frameApp.$("idAnime").style.display = "block";
                parent.ifrmAnalisesBKO.location.href = "efetivarRetencaoAnaliseCreditoAprovada.do?idRetencao="+idRetencao;
                parent.parent.parent.mostrar_div();
                parent.dvAnalisesBKO.style.display = '';
                parent.dv_dvAnalisesBKO.innerHTML = "Finaliza&ccedil;&atilde;o de Reten&ccedil;&atilde;o - An&aacute;lise de cr&eacute;dito aprovada";
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_conhis_verpagina">
    <vivo:tbTable selectable="true" layoutWidth="735" layoutHeight="100" tableWidth="730" styleId="table" sortable="true">
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
                    <img src="/FrontOfficeWeb/resources/images/lupa_bg_ffffff.gif" onclick="selectHistorico(this);top.frameApp.mostrar_div();historicoSelecionado.location.href='getHistorico.do?idRetencao=<bean:write name="iterHistorico" property="idRetencao" />'"/>
                </vivo:tbRowColumn>
                <vivo:tbRowColumn>

                    <!-- Normal -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="0">

                    </logic:equal>

                    <!-- Análise de crédito pendente -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="1">

                        <acesso:controlHiddenItem nomeIdentificador="fid_conhis_analcredpendente">
                        <img title="Análise de crédito pendente"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_analisecredito_pendente.gif"
                             onclick="getAnaliseCredito('<bean:write name="iterHistorico" property="idRetencao" />'),'PENDENTE'" />
                        </acesso:controlHiddenItem>

                    </logic:equal>

                    <!-- Análise de crédito aprovada -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="2">

                        <acesso:controlHiddenItem nomeIdentificador="fid_conhis_analcredaprovada">
                        <img title="Análise de crédito aprovada"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_analisecredito_ok.gif"
                             onclick="efetivarRetencaoAnaliseCreditoAprovada('<bean:write name="iterHistorico" property="idRetencao" />')" />
                        </acesso:controlHiddenItem>

                    </logic:equal>

                    <!-- Análise de crédito reprovada -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="3">

                        <acesso:controlHiddenItem nomeIdentificador="fid_conhis_analcredreprovada">
                        <img title="Análise de crédito reprovada"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_analisecredito_nok.gif"
                             onclick="getAnaliseCredito('<bean:write name="iterHistorico" property="idRetencao" />','REPROVADA')" />
                        </acesso:controlHiddenItem>

                    </logic:equal>

                    <!-- Análise de endereço pendente -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="4">

                        <acesso:controlHiddenItem nomeIdentificador="fid_conhis_analendpendente">
                        <img title="Análise de endereço pendente"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_endereco_pendente.gif"
                             onclick="getAnaliseEndereco('<bean:write name="iterHistorico" property="idRetencao" />','PENDENTE')" />
                        </acesso:controlHiddenItem>

                    </logic:equal>

                    <!-- Análise de endereço reprovada -->
                    <logic:equal name="iterHistorico" property="idTipoAnalise" value="5">

                        <acesso:controlHiddenItem nomeIdentificador="fid_conhis_analendreprovada">
                        <img title="Análise de endereço reprovada"
                             src="<%=request.getContextPath()%>/resources/images/bt_icon_endereco_nok.gif"
                             onclick="getAnaliseEndereco('<bean:write name="iterHistorico" property="idRetencao" />','REPROVADA')" />
                        </acesso:controlHiddenItem>

                    </logic:equal>

                </vivo:tbRowColumn>
            </vivo:tbRow>

            </logic:iterate>

        </vivo:tbRows>

    </vivo:tbTable>

    <table cellpadding="0" cellspacing="0" style="border:1px solid #adadad; background-color:#E7E7E7; margin-bottom:5px;" width="750" border="0">
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

    <iframe name="historicoSelecionado" id="historicoSelecionado" src="getHistorico.do" height="140" width="748" frameborder="0"></iframe>
    <vivo:quadroFlutuante id="dvPesqEndereco" idIframe="ifrmPesqEndereco" width="760" height="312" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Pesquisa de Endereço"/>

    </acesso:controlHiddenItem>

    </netui-template:section>
</netui-template:template>
