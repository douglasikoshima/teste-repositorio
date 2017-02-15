<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define id="FormManterBanner" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner"/>

<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

    <script language="javaScript">
        parent.oculta_div()
        var valorIdBanner = '';
        
            function mostraImagemBanner(nomeImagem , dsTipoBanner, idAreaBannerParam)
            {
                var form = document.forms[0];

                form.nmImagem.value     = nomeImagem;
                form.dsTipoBanner.value = dsTipoBanner;
                form.idArea.value       = idAreaBannerParam; 
                
                divImagemBanner.style.display = '';
                
                form.action = 'carregaImagemBanner.do';
                form.target = "ifrmImagemBanner";  
                top.frameApp.mostrar_div();

                form.submit();
            }
            
            function getIdBanner(valor){
                valorIdBanner = valor;  
            }
            
            function associar()
            {
                var form = document.forms[0];

                if(valorIdBanner == '')
                {
                    alert('Selecione um Banner para completar a operação.');
                    return false;
                }
                form.idBanner.value = valorIdBanner;
                form.target = 'innerBrowserAssociarBanner';
                form.action = 'associarBanner.do';
                top.frameApp.mostrar_div();
                form.submit();

            }
        
    </script>

</head>
<body>
    <form name="imagemForm" method="post" onsubmit="return false;">
    
        <table>
            <tr>
                <td align="center" colspan="2">
                    <vivo:tbTable selectable="true" layoutWidth="665" layoutHeight="210" tableWidth="665" styleId="tableTitle" sortable="true" >
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="20%" type="string">Descrição de Banner</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="40%" type="string">Link do Banner</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Area</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="10%" type="string">Tipo</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%"  type="string">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate name="FormManterBanner" property="bannerVO" id="lista" indexId="idx">
                                <vivo:tbRow key="linha">
                                    <vivo:tbRowColumn>
                                        <input type="radio" name="idBanner" class="radio" value="<bean:write name="lista" property="idBanner" />" onclick="getIdBanner(this.value);">
                                    </vivo:tbRowColumn>
    
                                    <vivo:tbRowColumn>
                                        <vivo:hint maxLength="80"><bean:write name="lista" property="dsBanner" /></vivo:hint>
                                    </vivo:tbRowColumn>
    
                                    <vivo:tbRowColumn>
                                            <a style="text-decoration:underline;color:#0000ff;" onmouseup="window.open('<bean:write name="lista" property="urlBanner" />');"><bean:write name="lista" property="urlBanner" /></a>
                                    </vivo:tbRowColumn>
    
                                    <vivo:tbRowColumn>
                                        <vivo:hint maxLength="80"><bean:write name="lista" property="dsAreaBanner" /></vivo:hint>
                                    </vivo:tbRowColumn>
    
                                    <vivo:tbRowColumn>
                                        <vivo:hint maxLength="80"><bean:write name="lista" property="dsTipoBanner" /></vivo:hint>
                                    </vivo:tbRowColumn>
    
                                    <vivo:tbRowColumn>
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_pesquisar_lista.gif" border="0" alt="Visualizar Imagem" onclick="mostraImagemBanner('<bean:write name="lista" property="nmBanner" />', '<bean:write name="lista" property="idTipoBanner" />','<bean:write name="lista" property="idAreaBanner" />');">
                                    </vivo:tbRowColumn>
                                </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Link do Banner:</b> 
                    <html:text name="FormManterBanner" property="nmLink" maxlength="100" style="width:400px;"/>
                </td>
                <td align="right">
                    <img style="cursor:hand;border:none" id="btAssociar" onclick="associar();" src="/FrontOfficeWeb/resources/images/bt_associar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_associar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_associar_over.gif'" />
                </td>
            </tr>
        </table>

            <vivo:quadroFlutuante label="VOL/TAV > Upload de Banner > Associar Banner > Imagem Banner" scroll="false" src="" idIframe="ifrmImagemBanner" id="divImagemBanner" spacesLeft="10" height="80" spacesTop="10" url="<%=request.getContextPath()%>" display="none" width="500" ></vivo:quadroFlutuante>
            
            <html:hidden name="FormManterBanner" property="idGrupoBannerArray"/>
            <html:hidden name="FormManterBanner" property="idBanner"/>
            <html:hidden name="FormManterBanner" property="paginaAtual"/>
            <html:hidden name="FormManterBanner" property="nmImagem"/>
            
            <html:hidden name="FormManterBanner" property="dsTipoBanner"/>
            <html:hidden name="FormManterBanner" property="idArea"/>
    </form>
    
        <iframe scrolling="yes"  name="innerBrowserAssociarBanner" height="0px" width="0px"></iframe>              
        
    <script language="javaScript">
        document.body.style.backgroundColor = '#ededed';
    </script>

</body>
</html>