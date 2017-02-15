<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="regionaisVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroRegionalVOArray"/>
<bean:define id="operadorasVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroOperadoraVOArray"/>
<bean:define id="ufVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroUFVOArray"/>
<bean:define id="segmentosVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroSegmentoVOArray"/>
<bean:define id="carteirasVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroCarteiraVOArray"/>
<bean:define id="gruposVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFGrupoVOArray"/>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<script language="javascript">

    function submitGerar() {

        erros = "";

        var dtInicio = document.forms[0].dtInicio;
        var dtFim = document.forms[0].dtFim;

        if (dtInicio.value == "") {
            erros += "Selecione a Data de In�cio!\n";
        }
        if (dtFim.value == "") {
            erros += "Selecione a Data Final!\n";
        }

        if (!validaDataFinal(dtInicio.value,dtFim.value)) {
            erros += "Data Inicial maior que Data Final!\n";
        }

        if (!valida1mes(dtInicio,dtFim)) {
            erros += "Intervalo de Datas maior que 30 dias!\n";
        }

        if (erros == "") {
            //Inicio anima��o
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

            document.forms[0].method = "POST";
            document.forms[0].action = "gerarAtdEncIncorreto.do";
            document.forms[0].target = "";
            document.forms[0].submit();
        } else {
            alert(erros);
            return false;
        }
    }

    function valida1mes(objInicial,objFinal){
        objSomaDias = document.forms[0].somaDias;
        somaDiasData(objInicial,objSomaDias,30);
        resposta = validaDataFinal(objFinal.value,objSomaDias.value);
        return resposta;
    }

    function validaData(data){
        if(data.value == '')
            return false;
        if(!validate_date(data.value)){
            data.value = '';
            data.focus();
            alert("Data inv�lida");
        }
    }

    function arvoreContato() {
        //Liga anima��o
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        dvArvore.style.display = '';
        document.forms[0].method = "POST";
        document.forms[0].action = "obterArvoreContato.do";
        document.forms[0].target = "ifrmArvore";
        document.forms[0].submit();
    }

    //Fim anima��o
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();


</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection">
    </netui-template:section>
    <netui-template:section name="bodySection">
        <!--APLICA��O->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <vivo:sessao id="qdMain" height="475" width="790" label="Vivo Net >> Workflow >> Relat�rios" operacoes="Processos Encaminhados Incorretos" scroll="N">
            <form id="formFiltro" tagId="formFiltro" action="gerarAtdEncIncorreto.do">
                <table cellpadding="5" cellspacing="0" border="0" width="100%" height="100%">
                    <tr>
                        <td align="left" valign="top" colspan="2">
                            <table cellpadding="2" cellspacing="0" border="0" width="100%">
                                <tr>
                                    <td colspan="2"><b>Filtros para Gera��o do Relat�rio:</b></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><b>Data de Entrada do Processo:</b></td>
                                    <td><input type="text" id="dtInicio" name="dtInicio" size="10" maxlength="10" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calend�rio" onclick="return showCalendar('dtInicio', '%d/%m/%Y');">&nbsp;
                                        <B>at�&nbsp;</b>
                                        <input type="text" id="dtFim" name="dtFim" size="10" maxlength="10" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calend�rio" onclick="return showCalendar('dtFim', '%d/%m/%Y');">&nbsp;
                                        <b>(M�ximo 30 dias)</b>
                                        <p>
                                    </td>
                                </tr>
                                <tr>
                                <tr>
                                    <td align="left">
                                        <b>Grupo:</b>
                                    </td>
                                    <td align="left">
                                        <div id="ttip" style="display:none;position:absolute;max-width:450px;"></div>
                                        <html:select name="relatorioForm" property="grupoSel" style="width: 450px" onchange="focaTipCampos(this.options[this.selectedIndex].text,this, 65, 275, 30);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 65, 275, 30);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 65, 275, 30);" onblur="HideTip();" onmouseout="HideTip();">
                                            <html:option value="-1" key="grupoSel">Todos</html:option>
                                            <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                                        </html:select>
                                        <html:hidden name="relatorioForm" value="nmGrupo" property="quebra"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left"><b>Contato:</b></td>
                                    <td align="left">
                                        <input type="text" name="textoContato" style="width:450px;" readonly="true">
                                        <html:hidden name="relatorioForm" property="contatoSel"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <iframe src="obterArvoreContato.do" scrolling="auto" width="100%" height="250"></iframe>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" valign="bottom">
                                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" id="btVoltar"  value="Voltar"  onClick="document.location='../../index.jsp'; return false"/>
                        </td>
                        <td align="right" valign="bottom">
                            <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" id="btGerar"  value="Gerar"  onClick="submitGerar(); return false"/>
                        </td>
                    </tr>
                </table>
                <iframe scrolling="yes" style="visibility:hidden;" name="ifrmCombos" height="0" width="0"></iframe>
                <vivo:quadroFlutuante id="dvArvore" idIframe="ifrmArvore" width="720" height="220" spacesTop="190" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="�rvore de Contato" scroll="yes"/>
                <input type="hidden" name="somaDias">
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

    </netui-template:section>
</netui-template:template>
