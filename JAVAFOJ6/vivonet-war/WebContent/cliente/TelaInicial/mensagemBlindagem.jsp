<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
    <head>
    </head>
    <body style="margin:0px;">
        <form action="prosseguirBlindagem.do" method="post" onsubmit="return false;" style="margin:0px;" name="formBlind" id="formBlind" target="ifrmBlind">
            <input type="hidden" name="inAtendimento" value="S">
            <table width="100%" height="100%" align="center" cellpadding="2" cellspacing="2" style="background:#E3ECF4;padding:5px;">
                <tr>
                    <td height="5"></td>
                </tr>
                <tr valign="top">
                    <td align="left">
                        <p><%= request.getAttribute("blindagemAviso")%>, ou <a href="<%= request.getAttribute("blindagemURL")%>" target="_blank">Clique Aqui</a></p>
                    </td>
                </tr>
                <tr>
                    <td align="left"><input type="checkbox" value="0" id="inibeMsg" name="inibeMsg" style="border:none;"/>&nbsp;Inibir esta mensagem no próximo atendimento</td>
                    <td align="right"><img onclick="prossegirAtendimento();" src="/FrontOfficeWeb/resources/images/bt_prosseguiratendimento_nrml.gif" border="0" style="cursor:pointer;"></td>
                </tr>
            </table>
        </form>
        <iframe name="ifrmBlind" id="ifrmBlind" src="about:blank" width="1" height="1" style="display:none;"></iframe>
    </body>
</html>