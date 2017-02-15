<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <style type="text/css">
            ul{ width: 100%; margin: 1em 0; padding: 0; counter-reset: ul; }
            ul li{ line-height: 10px; list-style-type: none; padding-left: 10px; }
            br{ clear: left; }
        </style>
        <script type="text/javascript" language="javascript">
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

            function validaIMEI(){
                $('msgRetorno').hide();
                $('msgRetorno').update("");
                $('msgDesbloquear').hide();
                $('msgDesbloqueio').update("");
                if(!isNumeric($F('nrSerial'))){
                    alert("O campo IMEI deve ser numérico.");
                    return false;
                }
                if($('nrSerial').value.length<15){
                    alert("O campo IMEI deve possuir 15 dígitos.");
                    return false;
                }
                return true;
            }

            function onKeyDown() {
                var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();
                if (event.ctrlKey && (pressedKey == "c" || pressedKey == "v")) {
                    event.returnValue = false;
                }
            } // onKeyDown

            function pesquisar(){
                if(validaIMEI())
                new Ajax.Request('pesquisarIphone.do', {
                    method: 'get',
                    parameters: {
                        nrSerial: $F('nrSerial'), limit: 15
                    },
                    contentType: 'text/xml',
                    onComplete: function(r){
                        var xmlString = r.responseText;
                        oXml       = new ActiveXObject("Microsoft.XMLDOM");
                        oXml.async = false;
                        var regExp = new RegExp('&', 'gi') ;
                        xmlString  = xmlString.replace(regExp,"&amp;");
                        oXml.loadXML(xmlString);
                        if(oXml.selectSingleNode("/xml-fragment/severity") != null) {
                            severity         = oXml.selectSingleNode("/xml-fragment/severity").text
                            friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                            exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                            if(severity==4){
                                $('msgDesbloqueio').update(friendlyMessage);
                                $('msgDesbloquear').show();
                            }else{
                                $('msgRetorno').update(friendlyMessage);
                                $('msgRetorno').show();
                            }
                        }else{
                            friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                            exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                            $('msgRetorno').update(friendlyMessage);
                            $('msgRetorno').show();
                        }
                        if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                    },
                    onFailure: function(e){alert("[Failure] "+e+"\n");},
                    onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport);}
                });
            }

            function desbloquear(){
                if(validaIMEI())
                new Ajax.Request('desbloqueioAparelhoIphone.do', {
                    method: 'get',
                    parameters: {
                        nrSerial: $F('nrSerial'), limit: 15
                    },
                    contentType: 'text/xml',
                    onComplete: function(r){
                        var xmlString = r.responseText;
                        oXml       = new ActiveXObject("Microsoft.XMLDOM");
                        oXml.async = false;
                        var regExp = new RegExp('&', 'gi') ;
                        xmlString  = xmlString.replace(regExp,"&amp;");
                        oXml.loadXML(xmlString);
                        if(oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {
                            friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                            exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                            $('msgRetorno').update(friendlyMessage);
                            $('msgRetorno').show();
                        }
                        if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                    },
                    onFailure: function(e){alert("[Failure] "+e+"\n");},
                    onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport);}
                });
            }

       </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                if(window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.stopAnimation();
            -->
        </SCRIPT>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0" style="margin-left:3px;background-color:#ffffff;font-family:Tahoma;font-size:11px;">
    <!--acesso:controlHiddenItem nomeIdentificador="cli_imei_verpagina"-->
    <form action="desbloqueioAparelhoIphone.do" onsubmit="return false;">
        <div class="tbl_bgGray" style="padding:7px;height:155px;">
            <!--acesso:controlHiddenItem nomeIdentificador="cli_imei_btpesquisar"-->
            <ul>
                <li>
                    <b>IMEI :</b>&nbsp;
                    <input type="text" name="nrSerial" id="nrSerial" size="20" maxlength="15" onkeydown="onKeyDown();" onkeypress="return ((event.keyCode>31&&event.keyCode<48)||event.keyCode>57?false:true);"/>
                    <span><img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="border:none;cursor:pointer;" onClick="pesquisar();"/></span>
                    <div id="msgDesbloquear" style="display:none;">
                        <span id="msgDesbloqueio"></span>
                        <span id="btDesbloquear">&nbsp;<img src="/FrontOfficeWeb/resources/images/bt_desbloquear_nrml.gif" style="border:none;cursor:pointer;" onClick="desbloquear();"/></span>
                    </div>
                </li>
                <li>&nbsp;</li>
                <li>
                    <div id="msgRetorno" style="display:none;"></div>
                </li>
            </ul>
            <!--/acesso:controlHiddenItem-->
        </div>
    </form>
    <!--/acesso:controlHiddenItem-->
    </body>
</html>