<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<script type="text/javascript" src="../../resources/scripts/RWFUser.js"></script>
<script>
function carrDisp() {
    parent.showDiv("Disp", ifrDisp.document.body.innerHTML);
    if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
}
</script>

<bean:define id="form" name="rwfInboxUserForm" />
<bean:define id="inDisponivelWF" name="form" property="inDisponivelWF"/>
<bean:define id="retorno" name="form" property="retorno"/>

<table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
    <tr>
        <td height="40" width="30px">Login:</td>
        <td width="160px"><bean:write name="form" property="nmLoginUsuario"/></td>
        <td>Status:</td>
        <td>
            <iframe src="" id="ifrDisp" name="ifrDisp" width="0" height="0" frameborder="0"></iframe>
            <div id="dDisp">
                <table cellspacing="1" cellpadding="1">
                    <tr>
                        <td>
                            <a target="ifrDisp" onclick="if(top.frameApp.dvAnimarAguarde!=null)top.frameApp.startAnimation();" href="analistaDisponivel.do?disp=1" style="border:0px;"><img src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("1") ? "bt_disponivel_disponivel.gif" : "bt_disponivel_indisponivel.gif")%>' style="border:0px;"/></a>
                        </td>
                        <td>
                            <a target="ifrDisp" onclick="if(top.frameApp.dvAnimarAguarde!=null)top.frameApp.startAnimation();" href="analistaDisponivel.do?disp=0" style="border:0px;"><img src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("0") ? "bt_indisponivel_disponivel.gif" : "bt_indisponivel_indisponive.gif")%>' style="border:0px;"/></a>
                        </td>
                    </tr>
                </table>
             </div>
        </td>
    </tr>
    <tr>
        <td id="tdBarraCTI" align="right" style="diplay.none" colspan="4">
            <table class="tbl_bggray" width="765" cellspacing="2" cellpadding="3" border="0">
            <tr>
            <td height="40" width="15%">Campanha:</td>
            <td width="40%">
                <html:select name="form" property="retorno" title="numCampanhaSel" style="width=150px" onchange="trocarCampanha(this.value)">
                    <html:options collection="retorno" property="idRetornoWFCTI" labelProperty="dsRetornoWFCTI" />
                </html:select></td>
            <td width="45%" align="right"><img id="imgDisponibilidade" src="../../resources/images/icon_atend_disponivel.gif" onclick="mudaDisponibilidade()" style='cursor:pointer' border="0"></td>
            </tr>
            </table>
        </td>
    </tr>
</table>

<script>mostraBarraCTI();parent.carrUser();</script>