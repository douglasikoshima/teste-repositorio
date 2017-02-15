<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormFatura" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="tiForm"/>

<script>
    function setNroLinha(strValor,tipoRelacionamiento, idProspect){
        tdNroConta.innerHTML ="<b>Nº Conta:</b> "+strValor;
        simbolo.innerHTML ='&nbsp;';
        bindLupaFatura(tipoRelacionamiento, idProspect);
    }
    
    function limparDados(){
        fatVenc.innerHTML = "";
        fatCicl.innerHTML = "";
        fatSald.innerHTML = "";
        fatFPag.innerHTML = "";
    }


    function mostraFatura() {
        top.frameApp.mostrar_div();
        document.forms[0].action="loadFaturamento.do";    
        document.forms[0].method = "POST";    
        document.forms[0].submit();        
    }
    
    
    function bindLupaFatura(tipoRelacionamiento, idProspect){
        if(tipoRelacionamiento==6 || tipoRelacionamiento==7 || idProspect!=""){
            document.getElementById('imgLupaFatura').onclick = null;
            document.getElementById('imgLupaFatura').style.cursor='';
            document.getElementById('imgVerFatura').onclick = null;
            document.getElementById('imgVerFatura').style.cursor='';
        }else{
            document.getElementById('imgLupaFatura').onclick = top.frameApp.abreLupaFatura;
            document.getElementById('imgLupaFatura').style.cursor='hand';
            document.getElementById('imgVerFatura').onclick = mostraFatura;
            document.getElementById('imgVerFatura').style.cursor='hand';
        }
    }
    
</script>
<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
<!--
    parent.parent.oculta_div();  
-->
</SCRIPT>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="cli_dfat_verpagina">
<html:form action="/cliente/TelaInicial/loadFaturamento.do">

<table width="345" height="75" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="109" valign="top">
            <acesso:controlHiddenItem nomeIdentificador="cli_dfat_abrirlupafat">
            <img src="/FrontOfficeWeb/resources/images/ti_lupa_fatura.jpg" width="109" height="75" id="imgLupaFatura">
            </acesso:controlHiddenItem>
        </td>
        <td width="3"></td>
        <td width="233" valign="top">
            <table width="233" height="75" border="0" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/ti_bg_fatura.gif">
                <tr>
                    <td height="2" colspan="4" valign="top"></td>
                    </tr>
                <tr>
                    <td colspan="2" valign="top" style="text-indent:4px;" id="tdNroConta"><b>Nº Conta:&nbsp;</b><bean:write name="FormFatura" property="fatura.nrConta"/></td>
                    <td width="21" valign="top" align="center">
                    <span style="color:#FF0000;" id="simbolo"><b></b></span>
                    <logic:equal value="S" name="FormFatura" property="fatura.inContaCobranca">
                        <script>
                          simbolo.innerHTML = '$';
                        </script>
                    </logic:equal>
                    </td>
                    <td width="20">
                    <acesso:controlHiddenItem nomeIdentificador="cli_dfat_mostrarlupafat">
                    <img src="/FrontOfficeWeb/resources/images/ti_bt_verfatura.gif" width="15" height="13" id="imgVerfatura">
                    </acesso:controlHiddenItem>
                    </td>                  
                </tr>
                <tr>
                    <td colspan="4" height="2"></td>
                    </tr>
                <tr>
                    <td width="100" style="text-indent:5px;"><b>Vcto:</b> <span id="fatVenc"> <bean:write name="FormFatura" property="fatura.dtVencimento"/> </span></td>
                    <td colspan="3"><b>Ciclo:</b> <span id="fatCicl"> <bean:write name="FormFatura" property="fatura.dsCicloFatura"/> </span></td>
                    </tr>
                <tr>
                    <td style="text-indent:5px;"><b>Valor Ult. Fatura:</b></td>
                    <td width="65"><span id="fatSald"><bean:write name="FormFatura" property="fatura.saldo"/></span></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="4" style="text-indent:5px;"><b>Forma pgto.:</b> <span id="fatFPag"> <bean:write name="FormFatura" property="fatura.dsFormaPagamento"/> </span></td>
                    </tr>
            </table>
        </td>
    </tr>
</table>
<script>
    tipoRelacionamiento = '<%=(((ParametrosVO)session.getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento()!=null?((ParametrosVO)session.getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento():ConstantesCRM.SVAZIO)%>';
    idProspect = '';
  //  alert(tipoRelacionamiento+"-"+idProspect);
    bindLupaFatura(tipoRelacionamiento, idProspect);
    top.frameApp.oculta_div();
<logic:equal name="FormFatura" value="ERRO" property="dsErro">
    alert('Sistema de FATURAMENTO temporariamente fora de serviço!');
</logic:equal>
</script>
</html:form>
</acesso:controlHiddenItem>
</body>