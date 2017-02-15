<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<bean:define id="motivoForm" name="motivoForm" />
<bean:define id="disp"  name="motivoForm" property="WFManterMotivosVO.acoesDisponivel.WFAcaoVOArray"/>
<bean:define id="assoc" name="motivoForm" property="WFManterMotivosVO.acoesAssociado.WFAcaoVOArray"/>

<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<form action="associarMotivo.do" method="post">
<script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/utils.js"></script>
<script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
<table cellpadding="3" border="0" align="center">
    <tr>
        <td align="left" colspan="3">
            &nbsp;<b>Motivo:</b>
            <html:hidden name="motivoForm" property="idMotivo"/> <html:text name="motivoForm" property="dsMotivo" size="60" readonly="true"/>
        </td>
    </tr>
    <tr>
        <td>
            &nbsp;<b>Disponíveis</b>
        </td>
        <td></td>
        <td>
            &nbsp;<b>Associadas</b>
        </td>
    </tr>
    <tr>
        <td align="left">
            <html:select name="motivoForm" property="acoesDisp" size="10" style="width:200px" multiple="true" onmouseover="ativarToolTip(this);">
                <html:options collection="disp" property="idAtividade" labelProperty="dsAtividade" />
            </html:select>
        </td>
        <td align="center" width="30">
            <vivo:botao id="bt1" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].acoesDisp, document.forms[0].acoesAssoc);return false;"/>
            <vivo:botao id="bt2" width="25px" height="20px" value=">>" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.forms[0].acoesDisp, document.forms[0].acoesAssoc);return false;"/>
            <vivo:botao id="bt3" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].acoesAssoc, document.forms[0].acoesDisp);return false;"/>
            <vivo:botao id="bt4" width="25px" height="20px" value="<<" styleClass="btTemplate" onclick="transfereSelecaoListaTodos(document.forms[0].acoesAssoc, document.forms[0].acoesDisp);return false;"/>
        </td>
        <td>
            <html:select name="motivoForm" property="acoesAssoc" size="10" style="width:200px" multiple="true" onmouseover="ativarToolTip(this);">
                <html:options collection="assoc" property="idAtividade" labelProperty="dsAtividade" />
            </html:select>
        </td>
    </tr>
    <tr>
        <td align="right" colspan="3">
            <img hspace="8" vspace="6" style="border:none;" onClick="gravar(); return false;" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
            <img hspace="8" vspace="6" style="border:none;" onClick="parent.divAssoc.style.display='none'; return false;" src="/FrontOfficeWeb/resources/images/bt_fechar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_fechar_over.gif"/>
        </td>
    </tr>
</table>
</form>
<iframe id="ifrmResultadoOperacao" name="ifrmResultadoOperacao" style="width:0px; height:0px; display:none;"></iframe>
<script>
function transfereSelecaoLista(listaOrigem, listaDestino) {
    var i;
    for(i = 0; i<listaOrigem.options.length; i++)
        if(listaOrigem.options[i].selected)
            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);

    for(i = listaOrigem.options.length-1; i>=0; i--)
        if(listaOrigem.options[i].selected)
            listaOrigem.options[i] = null;

    sortOptions(listaDestino);

    return false;
}

function transfereSelecaoListaTodos(listaOrigem, listaDestino) {
    var i;
    for(i = 0; i<listaOrigem.options.length; i++)
        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
    for(i = listaOrigem.options.length-1; i>=0; i--)
        listaOrigem.options[i] = null;

    sortOptions(listaDestino);

    return false;
}

function gravar() {
    f=document.forms[0];

    var campo = new Array(f.acoesDisp, f.acoesAssoc);
    var campoDS;

    for(x = 0; x < campo.length; x++){
        temp = campo[x];
        for( y = 0; y < temp.options.length; y++ )
            temp.options[y].selected = true;
    }

    campo = f.acoesAssoc;
    for( i = 0; i < campo.options.length; i++ ) {
        var campoDS = document.createElement("<INPUT TYPE=HIDDEN NAME='acoesAssocDS'>");
        f.insertBefore(campoDS);
        campoDS.value=campo.options[i].text;
    }


    parent.divAssoc.style.display='none';
    parent.screen_block();
    f.target="ifrmResultadoOperacao";
    f.submit();
}
function fechar() {
    parent.divAssoc.style.display='none';
    parent.pesquisar();
}

</script>