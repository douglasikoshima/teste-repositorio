<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="desbloqueioImei"/>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <style type="text/css">
            ul{ width: 100%; margin: 1em 0; padding: 0; counter-reset: ul; }
            ul li{ line-height: 10px; list-style-type: none; padding-left: 10px; }
            br{ clear: left; }
        </style>
        <script>
            function isNumeric(sText){
                var validChars = "0123456789";
                var isNumber=true;
                var Char;
                for(i=0;i<sText.length && isNumber==true; i++){
                    Char = sText.charAt(i);
                    if(validChars.indexOf(Char)==-1){
                        isNumber = false;
                    }
                }
                return isNumber;
            }
            function validaIMEI(oForm){
                if(!isNumeric(oForm.imei.value)){
                    alert("O campo IMEI deve ser numérico.");
                    return false;
                }
                if(oForm.imei.value.length<15){
                    alert("O campo IMEI deve possuir 15 dígitos.");
                    return false;
                }
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
                document.forms[0].submit();
            }
           </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                if(window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.stopAnimation();
                document.body.style.backgroundColor = '#ededed';
            -->
        </SCRIPT>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0" style="margin-left:3px;font-family:Tahoma;font-size:11px;">
    <acesso:controlHiddenItem nomeIdentificador="cli_imei_verpagina">
    <form action="/FrontOfficeWeb/cliente/TelaInicial/desbloqueioAparelhoGsm.do" method="post">
        <logic:empty name="Form" property="desbloqueioGsmVO.errorDescription">
            <div class="tbl_bgGray" style="padding:7px;height:155px;">
                <acesso:controlHiddenItem nomeIdentificador="cli_imei_btpesquisar">
                <ul>
                    <li>
                        <b>Identificação do Aparelho (IMEI):</b>&nbsp;
                        <input type="text" size="20" maxlength="15" name="imei" onkeypress="return ((event.keyCode>31&&event.keyCode<48)||event.keyCode>57?false:true);"/>
                        <span>
                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" style="border:none" onClick="validaIMEI(document.forms[0])"/>
                        </span>
                    </li>
                </ul>
                <ul>
                    <li>
                        <b>Código de Desbloqueio do Aparelho (SIMLock):</b>&nbsp;
                        <bean:write name="Form" property="desbloqueioGsmVO.nrSimLock"/>
                    </li>
                </ul>
                </acesso:controlHiddenItem>
            </div>
        </logic:empty>
        <logic:notEmpty name="Form" property="desbloqueioGsmVO.errorDescription">
            <div class="tbl_bgGray" style="padding-top:60px;height:155px;width:100%;text-align:center;font-weight:bold;color:#ff0000;">
                <bean:write name="Form" property="desbloqueioGsmVO.errorDescription"/>
            </div>
        </logic:notEmpty>
    </form>
    </acesso:controlHiddenItem>
    </body>
</html>