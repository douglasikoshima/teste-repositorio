<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="parcelaForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/Formatacao.js"></script>
        <style type="text/css">
        </style>
        <script type="text/javascript">
            transfereSelecaoLista = function(listaOrigem, listaDestino, inverse, flAll){
                var i;
                if (inverse) {
                    for(i = 0; i<listaDestino.options.length; i++) {
                        if (flAll) listaDestino.options[i].selected = true;
                            if(listaDestino.options[i].selected)
                                listaOrigem.options[listaOrigem.options.length] = new Option(listaDestino.options[i].text, listaDestino.options[i].value);
                    }
                    for(i = listaDestino.options.length-1; i>=0; i--)
                        if(listaDestino.options[i].selected)
                            listaDestino.options[i] = null;
                } else {
                    for(i = 0; i<listaOrigem.options.length; i++) {
                        if (flAll) listaOrigem.options[i].selected = true;
                        if(listaOrigem.options[i].selected)
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    for(i = listaOrigem.options.length-1; i>=0; i--)
                        if(listaOrigem.options[i].selected)
                            listaOrigem.options[i] = null;
                }
            }

            function sortList(lb){
                arrTexts = new Array();
                arrValues = new Array();
                arrOldTexts = new Array();

                for(i=0; i<lb.length; i++){
                    arrTexts[i] = lb.options[i].text;
                    arrValues[i] = lb.options[i].value;
                    arrOldTexts[i] = lb.options[i].text;
                }

                arrTexts.sort();

                for(i=0; i<lb.length; i++){
                    lb.options[i].text = arrTexts[i];
                    for(j=0; j<lb.length; j++){
                        if (arrTexts[i] == arrOldTexts[j]){
                            lb.options[i].value = arrValues[j];
                            j = lb.length;
                        }
                    }
                }
            }

            carregarParcelas = function(){
                $('parcelaForm').action = 'carregarParcelas.do';
                $('parcelaForm').submit();
            }

            gravar = function(){
                if(validaForm()){
                    for(i = 0; i<$('idParcelasSel').options.length; i++){
                        $('idParcelasSel').options[i].selected = true;
                    }
                    $('parcelaForm').action = 'gravar.do';
                    $('parcelaForm').submit();
                }
            }

            validaForm = function(){
                var valor = parseFloat($('vlParcelaMinima').value.gsub('[^0-9,]','').gsub('[,]','.'), 10);

                if($('idTipoPessoa').value==""){
                    alert('Selecione um Tipo de Cliente');
                    return false;

                }else if($('idParcelasSel').options.length<=0){
                    alert('Selecione a Quantidade de Parcelas');
                    return false;

                }else if($('vlParcelaMinima').value==""){
                    alert('Informe um valor mínimo para as parcelas');
                    return false;

                }else if(valor==0){
                    alert('Informe um valor mínimo para as parcelas');
                    return false;

                }else if(valor < 10){
                    alert('O valor total da parcela não atingiu o valor mínimo necessário para efetuar o parcelamento. Favor consultar a política de vendas');
                    return false;

                }else if($('idTipoPessoa').options[$('idTipoPessoa').selectedIndex].text.indexOf('PF')>0){
                    //Pessoa Física: permite associar quantidade de parcelas menor ou igual a 6
                    if(!validaParcelas(36)){
                        alert('Quantidade de parcela ultrapassou a quantidade máxima definida pela política de vendas');
                        return false;
                    }

                }else if($('idTipoPessoa').options[$('idTipoPessoa').selectedIndex].text.indexOf('PJ')>0){
                    //Pessoa Jurídica: permite associar quantidade de parcelas menor ou igual a 10
                    if(!validaParcelas(36)){
                        alert('Quantidade de parcela ultrapassou a quantidade máxima definida pela política de vendas');
                        return false;
                    }
                }
                return true;
            }

            validaParcelas = function(limite){
                var isValid = true;
                for(i = 0; i<$('idParcelasSel').options.length; i++) {
                    var vlParcela = parseInt($('idParcelasSel').options[i].text, 10);
                    if(vlParcela>limite){
                        isValid = false;
                        break;
                    }
                }
                return isValid;
            }

        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <form method="post" action="" name="parcelaForm" id="parcelaForm" style="margin:0px;" onsubmit="return false;">

        <table width="95%" align="center" cellpadding="2" cellspacing="2" border="0" style="margin-top:20px;">
            <tr>
                <td width="25%" align="right">Tipo de Cliente:</td>
                <td width="25%" align="left">
                    <html:select name="Form" property="idTipoPessoa" styleId="idTipoPessoa" style="width:220px;" styleClass="SELECT" onchange="carregarParcelas();">
                        <option value="">Selecione</option>
                        <html:optionsCollection name="Form" property="optionsTipoPessoa" value="id" label="name"/>
                    </html:select>
                </td>
                <td></td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td width="" align="right">Quantidade de Parcelas:</td>
                <td align="left">
                    <html:select name="Form" property="idParcelasDisp" styleId="idParcelasDisp" style="width:220px;" size="5" styleClass="SELECT" multiple="true">
                        <html:optionsCollection name="Form" property="optionsDisp" value="id" label="name"/>
                    </html:select>
                </td>
                <td align="left" width="5%">
                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idParcelasDisp'),$('idParcelasSel'),false);">
                    <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idParcelasDisp'),$('idParcelasSel'),true);">
                </td>
                <td align="left">
                    <html:select name="Form" property="idParcelasSel" styleId="idParcelasSel" style="width:220px;" size="5" styleClass="SELECT" multiple="true">
                        <html:optionsCollection name="Form" property="optionsSel" value="id" label="name"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td colspan="4" height="15">
                </td>
            </tr>
            <tr>
                <td width="" align="right">Valor mínimo de parcelas:</td>
                <td align="left">
                    <html:text name="Form" property="vlParcelaMinima" styleId="vlParcelaMinima" style="text-align:right" styleClass="input" maxlength="10" onkeypress="checaReal(this);exibirValorFormatado(event);" onblur="checaReal(this);"/>&nbsp;*
                </td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4" height="15">
                    <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onclick="gravar();" align="right" style="cursor:pointer;"/>
                </td>
            </tr>
        </table>

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="50"></div>

        <table align="center" width="760" style="border:1px solid #adadad;background-color:#ffffff;" height="25">
            <tr>
                <td>* O valor mínimo definido será considerado o mesmo para todas as parcelas configuradas para o tipo de cliente</td>
            </tr>
        </table>

        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
        <vivo:alert atributo="msgErro" scope="request" />
    </form>
    </netui-template:section>
</netui-template:template>