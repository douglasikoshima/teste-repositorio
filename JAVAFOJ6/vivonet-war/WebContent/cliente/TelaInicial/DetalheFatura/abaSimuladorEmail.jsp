<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaFaturamentoPos"/>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript">
            function voltar(){
                if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
                self.location.href = 'loadSimulador.do';
            }

            function enviarEmail(){
                if(validaForm()){
                    if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
                    var f = document.forms[0];
                    f.action = 'loadEnviaMailSimulador.do';
                    f.submit();
                }
            }

            function validaForm(){
                var f = document.forms[0];
                if(f.dsEmail.value=='NOVO'){
                    if(f.idTipoEmail.value==''){
                        alert('Favor preencher corretamente o campo Tipo do Email');
                        return false;
                    }
                    if(f.dsNovoEmail.value==''){
                        alert('Favor preencher corretamente o campo Novo E-mail');
                        return false;
                    }
                    if(!validaEmail(f.dsNovoEmail.value)){
                        alert('E-mail incorreto, Favor alterar.');
                        return false;
                    }
                }
                return true;
            }

            function ocultaEmailNovo(op){
                if(op == "NOVO"){
                    document.getElementById('trEmail').style.display = "block";
                }else{
                    document.getElementById('trEmail').style.display = "none";
                }
            }
        </script>
        <script FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            parent.parent.oculta_div();
            document.body.style.backgroundColor = '#e3ecf4';
            if(document.forms[0].dsEmail.options.length>1){
                document.forms[0].dsEmail.selectedIndex = 1;
            }else{
                ocultaEmailNovo(document.forms[0].dsEmail.value);
            }
        -->
        </script>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0">
         <form action="" name="formSimulador" method="post" onsubmit="return false;">
            <table width="100%" border="0" style="margin-top:2px;margin-left:2px;">
                <tr>
                    <td>
                        <fieldset>
                            <legend><b>E-mail</b></legend>
                            <table>
                                <tr>
                                    <td><b>&nbsp;E-mail:</b></td>
                                    <td>
                                        <select name="dsEmail" id="dsEmail" style="width:200px;" class="SELECT" onchange="ocultaEmailNovo(this.value);">
                                            <option value="NOVO">NOVO....</option>
                                            <logic:iterate id="it" name="Form" property="consultarEmailsVO.emailsArray" indexId="idx">
                                                <option value="<bean:write name="it" property="dsEmail"/>"><bean:write name="it" property="dsEmail"/></option>
                                            </logic:iterate>
                                        </select>
                                        <%--html:select name="Form"  property="idEmail" style="width:150px;" onchange="ocultaEmailNovo(this.value);">
                                            <html:option value="NOVO">NOVO....</html:option>
                                            <html:options collection="aEmail" property="dsEmail" labelProperty="dsEmail" />
                                        </html:select--%>
                                    </td>
                                </tr>
                                <tr id="trEmail" style="display:none;">
                                    <td><b>&nbsp;Tipo do E-Mail :</b></td>
                                    <td align="left">
                                        <select name="idTipoEmail" id="idTipoEmail" style="width:200px;" class="SELECT">
                                            <option value="">-- Selecione --</option>
                                            <logic:iterate id="it" name="Form" property="consultarTiposEmailVO.tiposEmailArray" indexId="idx">
                                                <option value="<bean:write name="it" property="idTipoEmail"/>"><bean:write name="it" property="dsTipoEmail"/></option>
                                            </logic:iterate>
                                        </select>
                                        <%--html:select name="Form"  property="idTipoEmail">
                                            <html:option value="0">-- Selecione --</html:option>
                                            <html:options collection="aTipoEmail"  property="idTipoEmail" labelProperty="dsTipoEmail"/>
                                        </html:select--%>
                                    </td>
                                    <td><b>&nbsp;E-Mail:</b></td>
                                    <td>
                                        <input type="text" name="dsNovoEmail" maxlength="50" size="40" value=""/>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <img src="/FrontOfficeWeb/resources/images/bt_enviaremail_nrml.gif" style="cursor:pointer;" onclick="enviarEmail();">&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>