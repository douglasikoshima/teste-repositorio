<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="abaDadosChip" />
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <style type="text/css">
            ul {
                width: 100%;
                margin: 1em 0;
                padding: 0;
                counter-reset: ul;
            };
            ul li{
                line-height: 10px;
                list-style-type: none;
                padding-left: 10px;
            };
            br{
                clear: left;
            };
        </style>
        <script language="javascript">
        <!--
            function getSenhasDesbloqueio(nrPuk){
                
                divDadosSenhaObj = document.getElementById("divDadosSenha");
                nrICCID = '<bean:write name="Form" property="abaDadosChip.nrICCID" />';
                
                divDadosSenhaObj.style.visibility = 'visible';
                divDadosSenhaObj.style.color      = '#000000';
                divDadosSenhaObj.style.fontWeight = 'normal';
                divDadosSenhaObj.style.marginLeft = '5px';
                divDadosSenhaObj.style.marginTop  = '10px';
                divDadosSenhaObj.innerHTML        = "Carregando dados...";
                
                var xmlhttp = new ActiveXObject("microsoft.xmlhttp");
                xmlhttp.open("GET","getDadosChip.do?nrICCID="+nrICCID,true);
                xmlhttp.setRequestHeader("Content-Type","text/xml");
                xmlhttp.setRequestHeader("chartset","ISO-8859-1");
                xmlhttp.send();

                xmlhttp.onreadystatechange = function() {
                
                    if (xmlhttp.readyState==4){
                        var texto = xmlhttp.responseText
                        texto = texto.replace("vo:","");
                        texto = unescape(texto);
                        oXml = new ActiveXObject("microsoft.xmldom");
                        
                        if(!oXml.loadXML(texto) || (oXml.selectSingleNode("/DadosChipVO/nrPUK1")==null && oXml.selectSingleNode("/DadosChipVO/nrPUK2")==null )){
                            if (!oXml.loadXML(texto)) {
                                divDadosSenhaObj.innerHTML = "<br>&nbsp;&nbsp;&nbsp;Erro durante carregamento dos dados.<br><b>Erro</b>: "+oXml.parseError.reason+". <b>Linha:</b> "+oXml.parseError.line;
                            } else {
                                divDadosSenhaObj.innerHTML = "Erro durante carregamento dos dados.";
                            }
                        
                        } else if (oXml.selectSingleNode("/DadosChipVO/errorDescription") != null && oXml.selectSingleNode("/DadosChipVO/errorDescription").text != "") {
                            divDadosSenhaObj.style.fontWeight = "bold";
                            divDadosSenhaObj.style.color = "#ff0000";
                            divDadosSenhaObj.innerHTML = oXml.selectSingleNode("/DadosChipVO/errorCode").text+" - "+oXml.selectSingleNode("/DadosChipVO/errorDescription").text;
                        
                        }else{
                            var nrPuk1 = oXml.selectSingleNode("/DadosChipVO/nrPUK1").text;
                            var nrPuk2 = oXml.selectSingleNode("/DadosChipVO/nrPUK2").text;
                
                            switch(nrPuk) {
                                case 1:
                                    if (nrPuk1=="null")
                                        nrPuk1 = "<i>Houve um problema durante carregamento do PUK1</i>";
                                    conteudoSenhas  = "<ul>";
                                    conteudoSenhas += "<li style='line-height:15px;'><b><span title='Senha 1 de Desbloqueio da Senha Pessoal do Chip'>PUK1</span>:</b> "+nrPuk1+"</li>";
                                    conteudoSenhas += "</ul>";
                                    break;
                                case 2:
                                    if (nrPuk2=="null")
                                        nrPuk2 = "<i>Houve um problema durante carregamento do PUK2</i>";
                                    conteudoSenhas  = "<ul>";
                                    conteudoSenhas += "<li style='line-height:15px;'><b><span title='Senha 2 de Desbloqueio da Senha Pessoal do Chip'>PUK2</span>:</b> "+nrPuk2+"</li>";
                                    conteudoSenhas += "</ul>";
                                    break;
                            }
                            divDadosSenhaObj.innerHTML = conteudoSenhas;
                        }
                    }
                }
            }
        -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.oculta_div();
            -->
        </SCRIPT>
    </head>
    <body rightmargin="0" leftmargin="0" topmargin="0" style="margin-left:3px;background-color:#ffffff;font-family:Tahoma;font-size:11px;">
    
    <acesso:controlHiddenItem nomeIdentificador="cli_achip_verpagina">
         
        <logic:empty name="Form" property="abaDadosChip.errorDescription">
         
        <div class="tbl_bgGray" style="padding:7px;height:155px;">
            <ul>
                <li><b>Número do Chip (ICCID):</b> <bean:write name="Form" property="abaDadosChip.nrICCID" /></li>
            </ul>
            <ul>
                <li><b>Tipo do Chip:</b> <bean:write name="Form" property="abaDadosChip.dsTipoChip" /></li>
            </ul>
            <ul>
                <li><b>Tamanho do Chip:</b> <bean:write name="Form" property="abaDadosChip.dsTamanhoChip" /></li>
            </ul>
            
            <acesso:controlHiddenItem nomeIdentificador="cli_achip_puk1">
            <img src="/FrontOfficeWeb/resources/images/bt_puk1_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_puk1_over.gif" style="border:none;" onMouseUp="getSenhasDesbloqueio(1)" />
            </acesso:controlHiddenItem>
            
            <acesso:controlHiddenItem nomeIdentificador="cli_achip_puk2">
            <img src="/FrontOfficeWeb/resources/images/bt_puk2_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_puk2_over.gif" style="border:none;" onMouseUp="getSenhasDesbloqueio(2)" />
            </acesso:controlHiddenItem>
            
            <div id="divDadosSenha"></div>
            
            
            
        </div>
         
        </logic:empty>
         
        <logic:notEmpty name="Form" property="abaDadosChip.errorDescription">
         
        <div class="tbl_bgGray" style="padding-top:60px;height:155px;width:100%;text-align:center;font-weight:bold;color:#ff0000;">
            <bean:write name="Form" property="abaDadosChip.errorDescription" />
        </div>
         
        </logic:notEmpty>
         
    </acesso:controlHiddenItem>
    
    </body>
</html>