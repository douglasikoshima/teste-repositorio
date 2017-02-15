<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="fidelizacao.Configurar.ConfigurarMatrizAparelho.ConfigurarMatrizAparelhoController"%>
<%@ page import="fidelizacao.Configurar.ConfigurarMatrizAparelho.ConfigurarMatrizAparelhoController.ShowDadosAparelhoForm"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosAparelhoForm" />
<bean:define id="DadosAparelho" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosAparelhoForm.detalheAparelho" />
<bean:define id="ParcelasSel"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosAparelhoForm.parcelasSel" />
<bean:define id="DescontosSel"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosAparelhoForm.descontosSel" />
<bean:define id="Parcelas"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosAparelhoForm.parcelas" />

<head>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/rfidelizacaoconf.js" charset="ISO-8859-1"></script>
<link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">

<script language="javascript">
<%
ConfigurarMatrizAparelhoController configurarMatrizAparelhoController = (ConfigurarMatrizAparelhoController)request.getSession().getAttribute("com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow");
ShowDadosAparelhoForm form = configurarMatrizAparelhoController.getDadosAparelhoForm();
%>

function salvar() {
    var inicio =0;
    var fim =0;
    var erroGSM = false;

    if( (document.showDadosAparelhoForm.parcelasSelecionadas.options.length == 0) || (document.showDadosAparelhoForm.descontosSelecionados.options.length == 0)){
        alert('Por favor, selecionar valores de Parcela e de Desconto!')
        return false;

    }else{
        <%if(ConstantesCRM.SONE.equals(form.getDetalheAparelho().getInChipAvulso()) || ConstantesCRM.SONE.equals(form.getDetalheAparelho().getInChipPreProgramado())){%>
        var objetoCodigoSAP, vlCodigoSAP;
        //Validacao de Codigo SAP
        if(document.showDadosAparelhoForm.elements["contador"].length){
            for(i=0; i < document.showDadosAparelhoForm.elements["contador"].length; i++){
                objetoCodigoSAP = "{pageFlow.dadosAparelhoForm.arrayCor["+i+"].codigoSAP}";
                vlCodigoSAP = document.showDadosAparelhoForm.elements[objetoCodigoSAP].value;
                document.showDadosAparelhoForm.elements[objetoCodigoSAP].value = document.showDadosAparelhoForm.elements[objetoCodigoSAP].value.toUpperCase();
                if(!validarCodigoSAP(vlCodigoSAP)){
                    return false;
                }
            }
        }else if(document.showDadosAparelhoForm.elements["contador"] != undefined && document.showDadosAparelhoForm.elements["contador"].value == "1") {
            vlCodigoSAP = document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.arrayCor[0].codigoSAP}"].value;
            document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.arrayCor[0].codigoSAP}"].value = document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.arrayCor[0].codigoSAP}"].value.toUpperCase();
            if (!validarCodigoSAP(vlCodigoSAP)) {
                return false;
            }
        }

        //Validacao de valores de aparelho
        var maiorDescontoSelecionado   = getMaiorDescontoSelecionado();

        var maiorValorUnitarioChip     = getMaiorValorUnitarioChip();

        var menorValorUnitarioAparelho = getMenorValorUnitarioAparelho();

        var porcentagem = maiorDescontoSelecionado/100;

        var codAPAR = getCodigoAparelho(menorValorUnitarioAparelho);

        var codCHIP = getCodigoChip(maiorValorUnitarioChip);

        /* REGRA: O valor final do aparelho corresponde a aplicacao do maior
         * desconto selecionado ao menor valor unitario de aparelho. Então
         * subtrai-se do resultado obtido o maior valor unitário de Chip.
         * Ref: SM250 - Projeto W - DES v1.4
         */
        var vlFinalAparelho  = menorValorUnitarioAparelho-(porcentagem*menorValorUnitarioAparelho)-maiorValorUnitarioChip;

        if (vlFinalAparelho <= 0){
            var msgAlert;
            msgAlert  = "O valor do aparelho "+codAPAR+" com desconto é inferior ao valor do chip "+codCHIP+",";
            msgAlert += "\no que pode gerar uma Nota Fiscal com valor zero ou negativo.";
            msgAlert += "\n\nModificar o desconto, o valor do aparelho ou o valor do chip.";
            alert(msgAlert);
            erroGSM = true;
        }
        <%}%>

        if(erroGSM == false){
            for(i=0; i < document.showDadosAparelhoForm.elements.length; i++){
                if(document.showDadosAparelhoForm.elements['inicio'] == document.showDadosAparelhoForm.elements[i]){
                    inicio = i;
                }
                if(document.showDadosAparelhoForm.elements['fim'] == document.showDadosAparelhoForm.elements[i]){
                    fim = i;
                }
            }

            document.showDadosAparelhoForm.arrayCodigoSAP.options[0] = new Option(document.showDadosAparelhoForm.elements[inicio+1].value,document.showDadosAparelhoForm.elements[inicio+1].value);
            document.showDadosAparelhoForm.arrayPreco.options[0] = new Option(document.showDadosAparelhoForm.elements[inicio+2].value.replace(".",""),document.showDadosAparelhoForm.elements[inicio+2].value.replace(".",""));
            document.showDadosAparelhoForm.arrayQtdeEstoque.options[0] = new Option(document.showDadosAparelhoForm.elements[inicio+3].value,document.showDadosAparelhoForm.elements[inicio+3].value);

            if(document.showDadosAparelhoForm.elements["contadorDDD"] != undefined && document.showDadosAparelhoForm.elements["contadorDDD"].length){
                for (i=0; i < document.showDadosAparelhoForm.elements["contadorDDD"].length; i++) {
                    objetoVlUnitChip = "{pageFlow.dadosAparelhoForm.listaDDD["+i+"].vlUnitario}";
                    document.showDadosAparelhoForm.elements[objetoVlUnitChip].value = document.showDadosAparelhoForm.elements[objetoVlUnitChip].value.replace(".","").replace(",",".");
                }
            }else if(document.showDadosAparelhoForm.elements["contadorDDD"] != undefined && document.showDadosAparelhoForm.elements["contadorDDD"].value == "1") {
                document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.listaDDD[0].vlUnitario}"].value = document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.listaDDD[0].vlUnitario}"].value.replace(".","").replace(",",".");
            }
            if(document.showDadosAparelhoForm.elements["contador"].length){
                for(i=0; i < document.showDadosAparelhoForm.elements["contador"].length; i++){
                    document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.arrayCor["+i+"].preco}"].value = document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.arrayCor["+i+"].preco}"].value.replace(".","").replace(",",".");
                }
            }else{
                document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.arrayCor[0].preco}"].value = document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.arrayCor[0].preco}"].value.replace(".","").replace(",",".");
            }
            if (document.showDadosAparelhoForm.arrayPreco.options){
                for(i=0; i < document.showDadosAparelhoForm.arrayPreco.options.length; i++){
                    document.showDadosAparelhoForm.arrayPreco.options[i].selected = true;
                }
            }
            if (document.showDadosAparelhoForm.arrayCodigoSAP.options){
                for(i=0; i < document.showDadosAparelhoForm.arrayCodigoSAP.options.length; i++){
                    document.showDadosAparelhoForm.arrayCodigoSAP.options[i].selected = true;
                }
            }
            if (document.showDadosAparelhoForm.arrayQtdeEstoque.options){
                for(i=0; i < document.showDadosAparelhoForm.arrayQtdeEstoque.options.length; i++){
                    document.showDadosAparelhoForm.arrayQtdeEstoque.options[i].selected = true;
                }
            }
            if (document.showDadosAparelhoForm.parcelasSelecionadas.options){
                for(i=0; i < document.showDadosAparelhoForm.parcelasSelecionadas.options.length; i++){
                    document.showDadosAparelhoForm.parcelasSelecionadas.options[i].selected = true;
                }
            }
            if (document.showDadosAparelhoForm.descontosSelecionados.options){
                for(i=0; i < document.showDadosAparelhoForm.descontosSelecionados.options.length; i++){
                    document.showDadosAparelhoForm.descontosSelecionados.options[i].selected = true;
                }
            }
            erro = false;
            for (i=inicio+1; i < fim; i++) {
                if(document.showDadosAparelhoForm.elements[i].value == ""){
                    erro = true;
                }
            }
            if(erro){
                alert("Favor não deixar campos em branco!");
            } else {
                document.showDadosAparelhoForm.action = "showDadosAparelho.do?acao=salvar";
                document.showDadosAparelhoForm.submit();
            }
        }
    }

    function inicia() {
        for(i=0; i < document.showDadosAparelhoForm.elements.length; i++){
            if(document.showDadosAparelhoForm.elements['inicio'] == document.showDadosAparelhoForm.elements[i]){
                inicio = i;
            }
            if(document.showDadosAparelhoForm.elements['fim'] == document.showDadosAparelhoForm.elements[i]){
                fim = i;
            }
        }
        for (x = inicio+1; x < fim; x++){
            x++;
            checaReal(document.showDadosAparelhoForm.elements[x]);
            x++;
        }
        checaReal(document.showDadosAparelhoForm.elements["{actionForm.valorMulta}"]);
    }
}

getMenorValorUnitarioAparelho = function () {
    var arrayVlUnitAparelho;

    if(document.showDadosAparelhoForm.elements["contador"].length){
        arrayVlUnitAparelho = new Array(document.showDadosAparelhoForm.elements["contador"].length);
        for (i=0; i < document.showDadosAparelhoForm.elements["contador"].length; i++) {
            objetoVlUnitAparelho = "{pageFlow.dadosAparelhoForm.arrayCor["+i+"].preco}";
            vlUnitAparelho = trim(document.showDadosAparelhoForm.elements[objetoVlUnitAparelho].value);
            vlUnitAparelho = vlUnitAparelho.replace(".","").replace(",",".");
            arrayVlUnitAparelho[i] = vlUnitAparelho;
        }
        var arrayReordenado = arrayVlUnitAparelho.sort(sortNumber);
        return arrayReordenado[0];

    }else if(document.showDadosAparelhoForm.elements["contador"] != undefined && document.showDadosAparelhoForm.elements["contador"].value == "1") {
        vlUnitAparelho = trim(document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.arrayCor[0].preco}"].value);
        vlUnitAparelho = vlUnitAparelho.replace(".","").replace(",",".");
        return vlUnitAparelho;
    }
}

function getCodigoAparelho(valorAparelho){
    var codigoSAPAparelho = "";

    if(document.showDadosAparelhoForm.elements["contador"].length){
        for (i=0; i < document.showDadosAparelhoForm.elements["contador"].length; i++) {
            objetoVlUnitAparelho = "{pageFlow.dadosAparelhoForm.arrayCor["+i+"].preco}";
            vlUnitAparelho = trim(document.showDadosAparelhoForm.elements[objetoVlUnitAparelho].value);
            vlUnitAparelho = vlUnitAparelho.replace(".","").replace(",",".");

            if(vlUnitAparelho==valorAparelho){
                objetoCodigoSAP = "{pageFlow.dadosAparelhoForm.arrayCor["+i+"].codigoSAP}";
                codigoSAPAparelho = document.showDadosAparelhoForm.elements[objetoCodigoSAP].value;
                break;
            }
        }
        return codigoSAPAparelho;
    }else if(document.showDadosAparelhoForm.elements["contador"] != undefined && document.showDadosAparelhoForm.elements["contador"].value == "1") {
        codigoSAPAparelho = document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.arrayCor[0].codigoSAP}"].value;
        return codigoSAPAparelho;
    }
}

getMaiorValorUnitarioChip = function () {
    var arrayVlUnitChip;
    if(document.showDadosAparelhoForm.elements["contadorDDD"].length){
        arrayVlUnitChip = new Array(document.showDadosAparelhoForm.elements["contadorDDD"].length);
        for (i=0; i < document.showDadosAparelhoForm.elements["contadorDDD"].length; i++) {
            objetoVlUnitChip = "{pageFlow.dadosAparelhoForm.listaDDD["+i+"].vlUnitario}";
            vlUnitChip = trim(document.showDadosAparelhoForm.elements[objetoVlUnitChip].value);
            vlUnitChip = vlUnitChip.replace(".","").replace(",",".");
            arrayVlUnitChip[i] = vlUnitChip;
        }
        var arrayReordenado = arrayVlUnitChip.sort(sortNumber);
        return arrayReordenado[arrayReordenado.length-1];
    }else if(document.showDadosAparelhoForm.elements["contadorDDD"] != undefined && document.showDadosAparelhoForm.elements["contadorDDD"].value == "1") {
        vlUnitChip = trim(document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.listaDDD[0].vlUnitario}"].value);
        vlUnitChip = vlUnitChip.replace(".","").replace(",",".");
        return vlUnitChip;
    }
}

function getCodigoChip(valorChip){
    var codigoSAPChip = "";
    if(document.showDadosAparelhoForm.elements["contadorDDD"].length){
        for (i=0; i < document.showDadosAparelhoForm.elements["contadorDDD"].length; i++) {
            objetoVlUnitChip = "{pageFlow.dadosAparelhoForm.listaDDD["+i+"].vlUnitario}";
            vlUnitChip = trim(document.showDadosAparelhoForm.elements[objetoVlUnitChip].value).replace(".","").replace(",",".");
            if(vlUnitChip==valorChip){
                objetoCodigoSAP = "{pageFlow.dadosAparelhoForm.listaDDD["+i+"].nrCodigoSAP}";
                codigoSAPChip = document.showDadosAparelhoForm.elements[objetoCodigoSAP].value;
                break;
            }
        }
        return codigoSAPChip;
    }else if(document.showDadosAparelhoForm.elements["contadorDDD"] != undefined && document.showDadosAparelhoForm.elements["contadorDDD"].value == "1") {
        codigoSAPChip = document.showDadosAparelhoForm.elements["{pageFlow.dadosAparelhoForm.listaDDD[0].nrCodigoSAP}"].value;
        return codigoSAPChip;
    }
}

getMaiorDescontoSelecionado = function() {
    objDescSelecionados = document.showDadosAparelhoForm.descontosSelecionados;
    var arraySelecionados = new Array(objDescSelecionados.options.length);
    if (objDescSelecionados.options){
        for (i = 0; i < objDescSelecionados.options.length; i++){
            arraySelecionados[i] = trim(objDescSelecionados.options[i].value);
        }
    }
    var arrayReordenado = arraySelecionados.sort(sortNumber);
    return arrayReordenado[arrayReordenado.length-1];
}

function sortNumber(a,b){return a-b}

validarCodigoSAP = function(valor){
    if (valor.charAt(1).toUpperCase() != "G") {
        alert("O aparelho utilizado não é GSM. Não é possível \nrealizar gravação dos dados.");
        return false;
    } else {
        return true;
    }
}

semCaracterEspeciais = new RegExp("[0-9a-zA-z%]");

function validacionStrEspecial(obj){
    valor = obj.value;
    for(i=0;i<valor.length;i++){
        if(!semCaracterEspeciais.test(valor.charAt(i))){
            valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
            i = -1;
        }
    }
    obj.value = valor;
}

</script>
</head>

<body>
    <acesso:controlHiddenItem nomeIdentificador="fid_dap_verpagina">
    <form name="showDadosAparelhoForm" id="showDadosAparelhoForm" action="showDadosAparelho" method="post">
    <netui:hidden dataSource="idAparelho" />
    <netui:hidden dataSource="inChipAvulso" />
    <netui:hidden dataSource="inChipPreProgramado" />

    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

    <table width="670" align="center" class="tbl_bgBlue" >
        <tr>
            <td width="50">&nbsp;<b>Marca:</b></td>
            <td width="50"><netui:label value="{pageContext.Form.marca}"/></td>
        </tr>
        <tr>
            <td>&nbsp;<b>Modelo:</b></td>
            <td><netui:label value="{pageContext.Form.modelo}"/>
                <netui:select dataSource="arrayIdEstoque" size="1" optionsDataSource="{pageContext.Form.arrayIdEstoque}" style="visibility:visible; height:0px" multiple="true" nullable="false"  tagId="arrayEstoque"/>
                <html:select name="Form" property="arrayPreco"       size="1" multiple="true" style="visibility:hidden; height:1px"/>
                <html:select name="Form" property="arrayCodigoSAP"   size="1" multiple="true" style="visibility:hidden; height:1px"/>
                <html:select name="Form" property="arrayQtdeEstoque" size="1" multiple="true" style="visibility:hidden; height:1px"/>
            </td>
        </tr>
    </table>

    <table width="670" align="center">
        <tr>
            <td colspan="2">
                <input Type="hidden" name="inicio"/>
                <vivo:tbTable selectable="true" layoutWidth="640" layoutHeight="100" tableWidth="640" styleId="tableTitle" sortable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="left" width="20%" type="string">Cor</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="20%" type="number">Código SAP</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="20%" type="number">Valor unitário</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="20%" type="number">Quantidade em estoque</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                    <netui-data:repeater dataSource="{pageFlow.dadosAparelhoForm.arrayCor}">
                        <netui-data:repeaterHeader> </netui-data:repeaterHeader>
                        <netui-data:repeaterItem>
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn><netui:label value="{container.item.cor}" style="border-style:none;" /></vivo:tbRowColumn>
                                <vivo:tbRowColumn><netui:textBox onKeyUp="validacionStrEspecial(this)" dataSource="{container.item.codigoSAP}" style="border-style:none;text-transform:uppercase;" maxlength="13"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><netui:textBox dataSource="{container.item.preco}"       style="border-style:none;" maxlength="8" onKeyUp="checaVLBonus(this);" onKeyPress="checaVLBonus(this);"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><netui:textBox dataSource="{container.item.qtdeEstoque}" style="border-style:none;" maxlength="4" onKeyUp="checaInteiro(this);" onKeyPress="checaInteiro(this);"/></vivo:tbRowColumn>
                                <input type="hidden" name="contador" value="1">
                            </vivo:tbRow>
                        </netui-data:repeaterItem>
                        <netui-data:repeaterFooter>
                        </netui-data:repeaterFooter>
                    </netui-data:repeater>
                    </vivo:tbRows>
                </vivo:tbTable>
                <input Type="hidden" name="fim"/>
            </td>
        </tr>

        <logic:equal name="DadosAparelho" property="inChipAvulso" value="1">
        <tr><td colspan="2"><b>Tipo de chip: </b>Chip Avulso</td></tr>
        </logic:equal>
        <logic:equal name="DadosAparelho" property="inChipPreProgramado" value="1">
        <tr><td colspan="2"><b>Tipo de chip: </b>Chip Pré-programado</td></tr>
        </logic:equal>

<%if("1".equals(form.getDetalheAparelho().getInChipAvulso()) || "1".equals(form.getDetalheAparelho().getInChipPreProgramado())){%>
        <tr>
            <td colspan="2">
                <vivo:tbTable layoutWidth="420" layoutHeight="100" tableWidth="420" styleId="tbListaDDD" sortable="false" selectable="true">
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="left" width="25"  type="string">DDD</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="130" type="number">Código SAP</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="120" type="number">Valor unitário</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="left" width="130" type="number">Quantidade em estoque</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows>
                    <netui-data:repeater dataSource="{pageFlow.dadosAparelhoForm.listaDDD}">
                        <netui-data:repeaterHeader> </netui-data:repeaterHeader>
                            <netui-data:repeaterItem>
                                <vivo:tbRow key="linha1">
                                    <vivo:tbRowColumn>
                                        <netui:label value="{container.item.nrDDD}" style="border:none;" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <!--netui:label value="{container.item.nrCodigoSAP}" style="border:none;" /-->
                                        <netui:textBox dataSource="{container.item.nrCodigoSAP}" style="border-style:none;" disabled="true" readonly="true" size="15"/>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <netui:textBox dataSource="{container.item.vlUnitario}" style="border-style:none;" maxlength="8" onKeyUp="checaVLBonus(this);" onKeyPress="checaVLBonus(this);"/>
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <netui:textBox
                                            dataSource="{container.item.qtEstoque}"
                                            style="border-style:none;"
                                            maxlength="4"
                                            onKeyUp="checaInteiro(this)"/>
                                    </vivo:tbRowColumn>
                                    <input type="hidden" name="contadorDDD" value="1">
                                </vivo:tbRow>
                            </netui-data:repeaterItem>
                        <netui-data:repeaterFooter/>
                    </netui-data:repeater>
                    </vivo:tbRows>
                </vivo:tbTable>
            </td>
        </tr>
        <% } %>
        <tr>
            <td colspan="2"><b>Valor da multa contratual:</b> R$ <netui:textBox dataSource="valorMulta" styleClass="textfield" defaultValue="000" maxlength="8" onKeyUp="checaReal(this);" onKeyPress="checaReal(this);" size="20"/></td>
        </tr>
    </table>

    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

    <table align="center" width="670">
        <tr>
            <td width="132">
                <center>Parcelas Dispon&iacute;veis</center>
                <html:select name="Form" property="lixoParcelas"  size="10" multiple="true" style="width:125px;" ondblclick="moveFixo(document.showDadosAparelhoForm.lixoParcelas,document.showDadosAparelhoForm.parcelasSelecionadas);">
                    <html:options collection="Parcelas" property="id" labelProperty="descricao" />
                </html:select>
            </td>
            <td width="60" align="center">
                <table>
                    <tr>
                        <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none" onClick="moveFixo(document.showDadosAparelhoForm.lixoParcelas,document.showDadosAparelhoForm.parcelasSelecionadas); return false"/>
                        </td>
                    <tr>
                        <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif" style="border:none" onClick="moveFixoTodos(document.showDadosAparelhoForm.lixoParcelas,document.showDadosAparelhoForm.parcelasSelecionadas); return false"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none" onClick="deleteFixo(document.showDadosAparelhoForm.parcelasSelecionadas,document.showDadosAparelhoForm.parcelasSelecionadas); return false"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_left_over.gif" style="border:none" onClick="deleteFixoTodos(document.showDadosAparelhoForm.parcelasSelecionadas,document.showDadosAparelhoForm.parcelasSelecionadas); return false"/>
                        </td>
                    </tr>
                </table>
            </td>
            <td width="133">
                <center>Parcelas Selecionadas</center>
                <html:select name="Form" property="parcelasSelecionadas"  size="10" multiple="true" style="width:125px;" ondblclick="deleteFixo(document.showDadosAparelhoForm.parcelasSelecionadas);">
                    <html:options collection="ParcelasSel" property="id" labelProperty="valor" />
                </html:select>
            </td>
            <td width="20"></td>
            <td width="132">
                <center>Descontos Disponíveis</center>
                <html:select name="Form" property="lixoDescontos"  size="10" multiple="true" style="width:125px;" ondblclick="moveFixo(document.showDadosAparelhoForm.lixoDescontos,document.showDadosAparelhoForm.descontosSelecionados);">
                    <%for(int i =0; i <= 99; i++){%>
                        <option value="<%=i%>"/><%=i%></option>
                    <%}%>
                </html:select>
            </td>
            <td width="60" align="center">
                <table>
                    <tr>
                        <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none" onClick="moveFixo(document.showDadosAparelhoForm.lixoDescontos,document.showDadosAparelhoForm.descontosSelecionados); return false" />
                        </td>
                    <tr>
                        <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif" style="border:none" onClick="moveFixoTodos(document.showDadosAparelhoForm.lixoDescontos,document.showDadosAparelhoForm.descontosSelecionados); return false" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none" onClick="deleteFixo(document.showDadosAparelhoForm.descontosSelecionados); return false" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_left_over.gif" style="border:none" onClick="deleteFixoTodos(document.showDadosAparelhoForm.descontosSelecionados); return false" />
                        </td>
                    </tr>
                </table>
            </td>
            <td width="133">
                <center>Descontos Selecionados</center>
                <html:select name="Form" property="descontosSelecionados"  size="10" multiple="true" style="width:125px" ondblclick="deleteFixo(document.showDadosAparelhoForm.descontosSelecionados);">
                    <html:options collection="DescontosSel" property="valor" labelProperty="valor" />
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="7" align="right">
                <acesso:controlHiddenItem nomeIdentificador="fid_dap_gravar">
                    <img vspace="6" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none" onClick="salvar(); return false"/>

</acesso:controlHiddenItem>
            </td>
        </tr>
    </table>

    </form>

    <script language="javascript">
        document.body.style.backgroundColor = '#ededed';
    </script>

</acesso:controlHiddenItem>
</body>