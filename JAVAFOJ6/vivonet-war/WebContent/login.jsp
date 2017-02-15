<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formInicio"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Login - Vivo Net"/>
    <netui-template:setAttribute name="modulo" value=""/>
    <netui-template:section name="headerSection">
        <script language="javascript">
            <!--
                function verificaEnter(obj){
                    if (window.event && event.keyCode == '13' && obj.name == 'user'){
                        document.forms["formLogin"].pass.focus();
                        return false;
                    }else if(window.event && event.keyCode == '13' && obj.name == 'pass'){
                        document.getElementById('env_login').focus();
                        return false;
                    }
                }

                function verificaLogin(){
                    var error = false;
                    var f = document.forms[0];
                    var xmlhtttp = new ActiveXObject("microsoft.xmlhttp");
                    xmlhtttp.open("POST","<%=request.getContextPath()%>/login.do?user="+f.user.value+"&pass="+escape(f.pass.value),false);
                    xmlhtttp.setRequestHeader("Content-Type","text/xml");
                    xmlhtttp.setRequestHeader("chartset","ISO-8859-1");
                    xmlhtttp.send();
            
                    if(xmlhtttp.status >= 400) {
                        var strError = "Error status      : " + xmlhtttp.status;
                        strError    += "\nError readyState: " + xmlhtttp.readyState;
                        strError    += "\nStatus   text   : " + xmlhtttp.statusText;
                        strError    += "\nResponse text   :\n" + xmlhtttp.responseText;
                        error = true;
                        alert(strError);
                    }
                    if(!error){
                        oXml       = new ActiveXObject("microsoft.xmldom");
                        var str    = xmlhtttp.responseText;
                        var re     = new RegExp ('&', 'gi') ;
                        str        = str.replace(re,"&amp;");
                        oXml.async = false;
                        if(!oXml.loadXML(str)){
                            var strError =   "Error code   : " + oXml.parseError.errorCode;
                            strError    += "\nError Reason : " + oXml.parseError.reason;
                            strError    += "\nError status : " + xmlhtttp.status;
                            strError    += "\nError readySt: " + xmlhtttp.readyState;
                            strError    += "\nStatus text  : " + xmlhtttp.statusText;
                            strError    += "\nError line   : " + oXml.parseError.line;
                            strError    += "\nError linepos: " + oXml.parseError.linepos;
                            strError    += "\nError srcText: " + oXml.parseError.srcText;
                            alert(strError);
                        }else{
                            var problemas = oXml.selectSingleNode("/login/problemas").text;
                            var mensagem  = oXml.selectSingleNode("/login/mensagem").text;
                            var expirada  = oXml.selectSingleNode("/login/senha/expirada").text;
                            var expirando = oXml.selectSingleNode("/login/senha/expirando").text;
                            var alterar   = oXml.selectSingleNode("/login/senha/alterar").text;
                            var caminho   = oXml.selectSingleNode("/login/senha/path").text;
                            var autenticado = oXml.selectSingleNode("/login/autenticado").text;
                            if(problemas=="1"){
                                if(autenticado=="1"){
                                    if(expirada=="1"){
                                        alert(mensagem);
                                        //window.location = caminho;
                                        return false;
                                    }else if(expirando=="1"){
                                        if(messageBox(mensagem)==6){
                                            window.location = "<%=request.getContextPath()%>/"+caminho;
                                        }else{
                                            window.location = "<%=request.getContextPath()%>/autenticado.do";
                                        }
                                    }else if(alterar=="1"){
                                        //window.location = "<%=request.getContextPath()%>/autenticado.do";
                                        window.location = caminho;
                                    }else{
                                        if(messageBox(mensagem)==6){
                                            window.location = "<%=request.getContextPath()%>/autenticado.do";
                                        }else{
                                            return false;
                                        }
                                    }
                                }else{
                                    alert(mensagem);
                                    return false;
                                }
                            }else{
                                window.location = "<%=request.getContextPath()%>/autenticado.do";
                            }
                        }
                    }
                }

                function validaEnvio(){
                    var form = document.forms[0];
                    if(trim(form.user.value) == ''){
                        alert('Login é um campo obrigatório, favor preencher.');
                        document.forms["formLogin"].user.focus();
                        return false;
                    }else if (trim(form.user.value).length < 6) {
                    	  alert('O login deve conter no mínimo 6 caracteres!');
                        document.forms["formLogin"].user.focus();
                        return false;
                    }else if(trim(form.pass.value) == ''){
                        alert('Senha é um campo obrigário, favor preencher.');
                        document.forms["formLogin"].pass.focus();
                        return false;
                    }else {
                        verificaLogin();
                        return false;
                    }
                }
            -->
        </script>
        <script language="VBScript">
            function messageBox (pergunta)
                messageBox = msgbox(pergunta, 4, "Vivo Net")
            end function
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <table border="0" width="790" height="510" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center" valign="middle">
                    <table border='0' cellpadding='0' cellspacing='0' height='100%' width='100%'>
                        <tr valign='top'>
                            <td></td> 
                        </tr>
                        
                        <tr valign='top'>
                            <td align="center" valign="middle" >
                                <form action="login.do" method="POST" name="formLogin" onsubmit="return false;">
                                    <table width="443" height="208" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/resources/images/login_bg.jpg">
                                        <tr>
                                            <td width="85" height="84"></td>
                                            <td width="125"></td>
                                            <td width="233"></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td valign="top" height="20" colspan="2"><img src="<%=request.getContextPath()%>/resources/images/login_titulo.gif"></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td valign="top" style="font-size:9px;color:#bfbfbe;" height="11">&nbsp;usuário</td>
                                            <td valign="top" style="font-size:9px;color:#bfbfbe;" height="11">&nbsp;senha</td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td valign="top">
                                                <html:text  name="Form" value=""
                                                            property="user" 
                                                            alt="Login" 
                                                            style="border:1px solid #e2e2e3;width:120px;height:17px;" 
                                                            tabindex="1" 
                                                            onkeypress="return verificaEnter(this);" 
                                                            maxlength="254"/>
                                            </td>
                                            <td valign="top">
                                                <input type="password" value="" name="pass" alt="Senha" style="border:1px solid #e2e2e3;width:72px;height:17px;" tabindex="2" onkeypress="return verificaEnter(this);" >&nbsp;&nbsp;
                                                <input type="image" align="middle" tabindex="3" id="env_login" name="env_login" src="<%=request.getContextPath()%>/resources/images/bt_login_nrml.gif" border="0" onclick="return validaEnvio();" style="border:none">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" align="center" valign="middle" style="color:#ff0000;">
                                                <logic:notEqual name="Form" property="msgStatus" value="">
                                                    <bean:write name="Form" property="msgStatus" />
                                                </logic:notEqual>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <script for=window event="onload">
            document.forms['formLogin'].user.focus();
        </script>
    </netui-template:section>
</netui-template:template>
