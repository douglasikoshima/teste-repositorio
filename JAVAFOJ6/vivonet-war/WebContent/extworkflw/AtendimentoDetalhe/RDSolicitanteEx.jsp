<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%
String idAtd=(String)request.getParameter("idAtd");
String inResp=(String)request.getParameter("inResp");
%>
<script type="text/javascript">
    count = 0;
    function showIfrS(a) {
        count++;
        if (count == 3) {
            showIfrSS("UCP");
        }
    }
    function showIfrSS(a) {
        d=eval("d"+a);
        f=eval("ifr"+a);
        dv=eval("dv"+a);
        d.style.display='';
        dv.style.display='';
        parent.showIfr("Solic");
        parent.showDiv("UCP", ifrUCP.document.body.innerHTML);
        parent.showDiv("FProc", ifrFProc.document.body.innerHTML);
        parent.showDiv("Cont", ifrCont.document.body.innerHTML);
    }
</script>
<table width="760" cellpadding="0" cellspacing="0" border="0" align="center">
    <tr>
        <td height="5"></td>
    </tr>
    <tr>
        <td valign="top">
            <table border='0' cellpadding='0' cellspacing='0' width='400' height='16' background='/FrontOfficeWeb/resources/images/aba_bkg_off.gif' class='abatexto'>
                <tr id='btAba0' valign='top'>
                    <td width='9'><img id='AbaLeft_btPP' src='/FrontOfficeWeb/resources/images/aba_left.gif' width='9' height='16'></td>
                    <td id='btPP' background='/FrontOfficeWeb/resources/images/aba_bkg.gif' class='abaSelected' height='16'>
                        <a href="javascript:soli();" target="">
                            <logic:equal parameter="inResp" value="1">Usuário</logic:equal>
                            <logic:equal parameter="inResp" value="2">Cliente</logic:equal>
                            <logic:equal parameter="inResp" value="6">Prospect</logic:equal>
                            <logic:equal parameter="inResp" value="7">Não Cliente</logic:equal>
                        </a>
                    </td>
                    <td width='9'><img id='AbaRight_btPP' src='/FrontOfficeWeb/resources/images/aba_right.gif' width='9' height='16'></td>
                    <td width='9'><img id='AbaLeft_btPF' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
                    <td id='btPF' style='cursor:pointer;' class='abaUnselected' height='16'><a href="javascript:frm();">Formulário de Processo</a></td>
                    <td width='9'><img id='AbaRight_btPF' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>
                    <td width='9'><img id='AbaLeft_btPC' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
                    <td id='btPC' style='cursor:pointer;' class='abaUnselected' height='16'><a href="javascript:cnt();">Contato</a></td>
                    <td width='9'><img id='AbaRight_btPC' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>
                </tr>
            </table>
        </td>
        <td>
        </td>
    </tr>
</table>
<div id="dvUCP" style="">
    <table height="78" width="760" align="center" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;">
        <tr>
            <td valign="top" class="BorderTable1" width="100%">
                <iframe src="" id="ifrUCP" name="ifrUCP" height="0" width="0" frameborder="0"></iframe>
                <div id="dUCP" style=""><a href="RDUserEx.do"></a></div>
             </td>
        </tr>
    </table>
</div>
<div id="dvFProc" style="display:none;">
    <table height="75" width="760" align="center" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;">
        <tr>
            <td valign="top" class="BorderTable1">
                <iframe id="ifrFProc" name="ifrFProc" src="" height="0" width="0" frameborder="0"></iframe>
                <div id="dFProc" style="overflow:auto;width:750px;height:66px;"><a href="RDProcessoEx.do"></a></div>
             </td>
        </tr>
    </table>
</div>
<div id="dvCont" style="display:none;">
    <table height="75" width="760" align="center" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;">
        <tr>
            <td valign="top" class="BorderTable1">
                <iframe id="ifrCont" name="ifrCont" src="" height="0" width="0" frameborder="0"></iframe>
                <div id="dCont" style="overflow:auto;height:66px;"><a href="RDContatoEx.do"></a></div>
             </td>
        </tr>
    </table>
</div>
<a href="abaVoltarSolicitanteEx.do" id="abaSolicitanteVoltar" target="ifrUCP"></a>

<script type="text/javascript">
    abaSolicitanteVoltar.click();
</script>