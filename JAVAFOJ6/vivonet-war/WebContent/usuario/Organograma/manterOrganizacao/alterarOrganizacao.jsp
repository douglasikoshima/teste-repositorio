<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/> 
 
 

<bean:define id="FormOrganizacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOrganizacao"/>
<html>
    <head>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script> 
        <script language="Javascript">
            function valida()
            {
                var form = document.forms[0];
                if(trim(form.dsOrganizacao.value) == "")
                {
                    alert('Favor preencher o campo!');
                    return false;
                }
                
                return true;
            }
        
            function grava() 
            {
                var form = document.forms[0];
                form.dsOrganizacao.value = trim(form.dsOrganizacao.value);
                if(valida())
                {
                    document.forms[0].target = '_parent';
                    document.forms[0].action = 'alteraItem.do';
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }
            function cancelar() {
                document.forms[0].target ='_parent';
                var action = 'begin.do';
                document.forms[0].action = action;
                parent.mostrar_div();
                document.forms[0].submit();
            }
            
        </script>
    </head>    
    <body onload="document.forms[0].dsOrganizacao.select();document.forms[0].dsOrganizacao.focus()">
    <form action="<%=request.getContextPath()%>/usuario/Organograma/manterOrganizacao/begin.do" id="begin" name="begin" onSubmit="return false;" method="POST">
    <acesso:controlHiddenItem nomeIdentificador="usu_altorg_verpagina">
        <vivo:quadro id="Descricao" width="390" height="340" label="Alteração de Item" scroll="true">
            <html:hidden property="idOrganizacao" name="FormOrganizacao"/>
            <html:hidden name="FormOrganizacao" property="tipoOrganizacao"/>
            <table>
                 
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td rowspan="2" valign="bottom" style="padding-left:4px;">
                        
                        <div id="dvNovaDesc" style="display:block;" >
                            <html:text name="FormOrganizacao" property="dsOrganizacao" maxlength="254" size="40"/>
                        </div>
                        
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><b>Tipo de Organização:</b></td>
                </tr>
                <tr>
                    <td colspan="3" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="usu_altorg_gravar">
                        <img id="btgrava" onclick="grava();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
                
            </table>
            </vivo:quadro>
            <script language="javascript" for="window" event="onload">
            <!--   
                parent.oculta_div();
            -->
            </script> 
    </acesso:controlHiddenItem> 
    </form>    
    </body>    
</html>