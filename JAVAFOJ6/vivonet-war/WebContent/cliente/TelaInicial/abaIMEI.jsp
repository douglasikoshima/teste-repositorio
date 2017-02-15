<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<!--acesso:controlInitEnv/-->
<html>
    <head>
        <link href="/FrontOfficeWeb/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript">
            function CarregaAba(nome){
                var pagina = "about:blank";
                $('fraIAbas').src = pagina;
                abaSelected($('btIAba'), $(nome));
                switch(nome){
                    case "btI01":
                        pagina = "/FrontOfficeWeb/cliente/TelaInicial/desbloqueioAparelhoGsm.do";
                        break;

                    case "btI02":
                        pagina = "/FrontOfficeWeb/cliente/TelaInicial/showAbaImeiIphone.do";
                        break;
                }
                $('fraIAbas').src = pagina;
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                //if(window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.stopAnimation();
                CarregaAba("btI01");
            -->
        </SCRIPT>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0" style="margin-left:3px;background-color:#ffffff;font-family:Tahoma;font-size:11px;">
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:abaGrupo id="btIAba" width="300" height="16" styleClass="abatexto">
            <vivo:abaItem id="btI01" value="GSM" onclick="CarregaAba(this.id);" select="S"/>
            <vivo:abaItem id="btI02" value="IPhone" onclick="CarregaAba(this.id);"/>
        </vivo:abaGrupo>
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="tbl_bgGray" height="375" valign="top" width="780">
                    <iframe id="fraIAbas" width="765" height="375" frameborder="0" scrolling="no" src="about:blank"></iframe>
                </td>
            </tr>
        </table>
    </body>
</html>