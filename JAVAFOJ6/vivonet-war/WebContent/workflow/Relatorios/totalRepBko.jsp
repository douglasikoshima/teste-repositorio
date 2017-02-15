<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="regionaisVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroRegionalVOArray"/>
<bean:define id="operadorasVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroOperadoraVOArray"/>
<bean:define id="ufVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroUFVOArray"/>
<bean:define id="segmentosVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroSegmentoVOArray"/>
<bean:define id="estadosVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFEstadoVOArray"/>
<bean:define id="carteirasVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroCarteiraVOArray"/>
<bean:define id="gruposVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFGrupoVOArray"/>
<bean:define id="representantesVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRFRVOArray"/>
<bean:define id="diretoriaVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroDiretoriaVOArray"/>
<bean:define id="areaVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroAreaVOArray"/>
<bean:define id="sessaoVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroSessaoVOArray"/>
<bean:define id="cargoVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroCargoVOArray"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
<script language="javascript">

    function submitGerar() {

        erros = "";

        if (erros == "") {
            //Inicio animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

            document.forms[0].method = "POST";
            document.forms[0].action = "gerarTotalRepresentanteBKO.do";
            document.forms[0].target = "";
            document.forms[0].submit();
        } else {
            alert(erros);
            return false;
        }
    }

    function submitOperadoras() {
        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms[0].method = "POST";
        document.forms[0].action = "obterRegionais.do";
        document.forms[0].target = "ifrmCombos";
        document.forms[0].submit();
    }

    function submitGrupo() {
        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms[0].method = "POST";
        document.forms[0].action = "obterRepresentantes.do";
        document.forms[0].target = "ifrmCombos";
        document.forms[0].submit();
    }

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="wor_rtrep_verpagina">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <vivo:sessao id="qdMain" height="475" width="790" label="Vivo Net >> Workflow >> Relatórios" operacoes="Total por Representante BKO" scroll="N">
            <form id="formFiltro" action="gerarTotalRepresentanteBKO.do" method="post">
                <table cellpadding="5" cellspacing="0" border="0" width="100%" height="100%">
                    <tr>
                        <td align="left" valign="top" colspan="2">
                            <table cellpadding="2" cellspacing="0" border="0" width="100%">
                                <tr>
                                    <td colspan="2"><b>Filtros para Geração do Relatório:</b></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Grupo:</b>
                                    </td>
                                    <td align="left">
                                        <div id="ttip" style="display:none;position:absolute;max-width:450px;"></div>
                                        <html:select name="relatorioForm" property="grupoSel" style="width: 450px" onchange="submitGrupo();focaTipCampos(this.options[this.selectedIndex].text,this, 20, 162, 30);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 20, 162, 30);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 20, 162, 30);" onblur="HideTip();" onmouseout="HideTip();">
                                            <html:option value="-1" key="grupoSel">Todos</html:option>
                                            <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                                        </html:select>
                                        <html:hidden name="relatorioForm" value="nmGrupo" property="quebra"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Representante:</b>
                                    </td>
                                    <td align="left">
                                        <html:select name="relatorioForm" property="representanteSel" style="width: 450px">
                                            <html:option value="-1" key="representanteSel">Todos</html:option>
                                            <html:options collection="representantesVO" property="id" labelProperty="ds"/>
                                        </html:select>
                                        <html:hidden name="relatorioForm" value="nmLoginUsuarioAtual" property="quebra"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Operadora:</b>
                                    </td>
                                    <td align="left">
                                        <html:select onchange="submitOperadoras();" name="relatorioForm" property="operadoraSel" style="width: 450px">
                                            <html:option value="-1" key="operadoraSel">Todos</html:option>
                                            <html:options collection="operadorasVO" property="idOperadora" labelProperty="dsOperadora"/>
                                        </html:select>
                                        <html:checkbox name="relatorioForm" value="nmGrupoOperadora" property="quebra" styleClass="checkbox" style="border:none;background:none;"> Quebra</html:checkbox>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="20%">
                                        <b>UF:</b>
                                    </td>
                                    <td align="left" width="80%">
                                        <html:select name="relatorioForm" property="regionalSel" style="width:450px">
                                            <html:option value="-1" key="regionalSel">Todos</html:option>
                                            <html:options collection="regionaisVO" property="idRegional" labelProperty="dsRegional"/>
                                        </html:select>
                                        <html:checkbox name="relatorioForm" value="UF" property="quebra" styleClass="checkbox" style="border:none;background:none;"> Quebra</html:checkbox>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Status:</b>
                                    </td>
                                    <td align="left">
                                        <html:select multiple="true" size="3" name="relatorioForm" property="estadoSel" style="width: 450px">
                                            <html:options collection="estadosVO" property="idEstado" labelProperty="dsEstado"/>
                                        </html:select>
                                        <html:checkbox name="relatorioForm" value="dsFase" property="quebra" styleClass="checkbox" style="border:none;background:none;"> Quebra</html:checkbox>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Carteira:</b>
                                    </td>
                                    <td align="left">
                                        <html:select name="relatorioForm" property="carteiraSel" style="width: 450px">
                                            <html:option value="-1" key="carteiraSel">Todos</html:option>
                                            <html:options collection="carteirasVO" property="idCarteira" labelProperty="dsCarteira"/>
                                        </html:select>
                                        <html:checkbox name="relatorioForm" value="dsTipoCarteira" property="quebra" styleClass="checkbox" style="border:none;background:none;"> Quebra</html:checkbox>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left"><b>Segmento:</b></td>
                                    <td align="left">
                                        <html:select name="relatorioForm" property="segmentoSel" style="width: 450px">
                                            <html:option value="-1" key="segmentoSel">Todos</html:option>
                                            <html:options collection="segmentosVO" property="idSegmento" labelProperty="dsSegmento"/>
                                        </html:select>
                                        <html:checkbox name="relatorioForm" value="dsSegmentacao" property="quebra" styleClass="checkbox" style="border:none;background:none;"> Quebra</html:checkbox>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        Atualização:
                                    </td>
                                    <td align="left">
                                    <html:select name="relatorioForm" property="atualizacao" style="width:450px">
                                        <html:option value="-1" key="atualizacao">Desativado</html:option>
                                        <html:option value="5" key="atualizacao">5 Minutos</html:option>
                                        <html:option value="15" key="atualizacao">15 Minutos</html:option>
                                        <html:option value="30" key="atualizacao">30 Minutos</html:option>
                                    </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left"><b>Prazo</b></td>
                                    <td align="left">
                                        <html:radio name="relatorioForm" value="V" property="prazo" styleClass="radio">Vivo</html:radio>
                                        <html:radio name="relatorioForm" value="A" property="prazo" styleClass="radio">Anatel</html:radio>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" valign="bottom">
                            <acesso:controlHiddenItem nomeIdentificador="wor_rtrep_gerar">
                                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" id="btVoltar"  value="Voltar"  onClick="document.location='../../index.jsp'; return false"/>
                            </acesso:controlHiddenItem>
                        </td>
                        <td align="right" valign="bottom">
                            <acesso:controlHiddenItem nomeIdentificador="wor_rtrep_gerar">
                                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" id="btGerar"  value="Gerar"  onClick="submitGerar(); return false"/>
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                <iframe scrolling="yes" style="visibility:hidden;" name="ifrmCombos" height="0" width="0"></iframe>
            </form>
        </vivo:sessao>
<script>
var moveToolTip = true;

xBump=yBump=10;
MSIE=document.all;
NS6=document.getElementById&&!document.all;
if(MSIE||NS6){
    ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
}

function carregaToolTip(content) {
 ttipObj.innerHTML=content;
 ttipObj.style.display='';
}
</script>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
