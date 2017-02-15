<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/> 

  

<bean:define id="FormNivel" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formNivel"/>
<html>
    <head>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script> 
        <script language="Javascript">
            function valida() {
                if (trim(document.forms[0].dsNivel.value) == ""){
                    alert("Favor preencher o campo!");
                    return(false);
                } else {
                    return(true);
                }
            }
            function grava() {
                document.forms[0].dsNivel.value = trim(document.forms[0].dsNivel.value);
                if(valida()) {
                    document.forms[0].target = '_parent';
                    document.forms[0].action = 'alteraItem.do';
                    top.frameApp.mostrar_div();
                    document.forms[0].submit();
                }
            }
            function cancelar() {
                document.forms[0].target ='_parent';
                var action = 'begin.do';
                document.forms[0].action = action;
                top.frameApp.mostrar_div();
                document.forms[0].submit();
            }
            
            
        </script>
    </head>
    <acesso:controlHiddenItem nomeIdentificador="usu_altniv_verpagina">
    <body onload="document.forms[0].dsNivel.select();document.forms[0].dsNivel.focus()"><!-- onunload="JavaScript:cancelar();"-->    
     <form action="<%=request.getContextPath()%>/usuario/Organograma/manterNiveis/begin.do" id="begin" name="begin" method="POST" onSubmit="return false;">
        <html:hidden name="FormNivel" property="idNivel" value='<%=request.getParameter("idNivel")%>'/>
        <!--html:hidden name="FormNivel" property="idNivelPai" value='< %=request.getParameter("idNivelPai")%>'/-->
        <vivo:quadro id="Descricao" width="390" height="340" label='<%=request.getParameter("dsNivel")%>' scroll="true">
            <input type="hidden" name="idQuestionario" value="<%=request.getParameter("idNivel")%>" >
            <table>
                 
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td rowspan="2" valign="bottom" style="padding-left:4px;">
                        
                        <div id="dvNovaDesc" style="display:block;" >
                            <html:text name="FormNivel" property="dsNivel" maxlength="254" size="50"/>
                        </div>
                        
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><b>Nome do Nível:</b></td>
                </tr>
                <tr>
                    <td colspan="3" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="usu_altniv_gravar">
                        <img id="btgrava" onclick="grava();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
                
            </table>
            </vivo:quadro>
            <script language="javascript" for="window" event="onload">
            <!--   
                top.frameApp.oculta_div();
            -->
            </script> 
    </form>
    </body>
    </acesso:controlHiddenItem>  
</html>