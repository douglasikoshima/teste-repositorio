<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/> 
  
<bean:define id="FormOrganizacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOrganizacao"/>
<bean:define id="ListaOrganizacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOrganizacao.listaOrganizacao"/>

<html>
    <head>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

        <script language="Javascript">
        <!--   
            function validaNovo() {
                if (trim(document.forms[0].dsOrganizacao.value) == ""){
                    alert("Favor preencher o campo!");
                    return(false);
                } else {
                    return(true);
                }
            }

            function validaExistente() 
            {
                if (document.forms[0].tipoOrganizacao.options.selectedIndex == 0){
                    alert("Favor escolher o campo!");
                    return(false);
                } else {
                    return(true);
                }
            }
        
            function gravaExistente() {
                if(validaExistente()) {
                    document.forms[0].target = '_parent';
                    document.forms[0].action = 'gravaItem.do';
                    document.forms[0].submit();
                }
            }

            function gravaNovo() {
                if(validaNovo()) {
                    document.forms[0].target = '_parent';
                    document.forms[0].action = 'gravaItem.do';
                    document.forms[0].submit();
                }
            }
        
            function mudaDescricao(boolean) {
                if (boolean == true) {
                    document.getElementById('dvNovaDesc').style.display = 'block';
                    document.getElementById('dvExistDesc').style.display = 'none';
                    document.getElementById('btNovo').style.display = 'block';
                    document.getElementById('btExistente').style.display = 'none';
                } else if (boolean == false) {
                    document.getElementById('dvExistDesc').style.display = 'block';
                    document.getElementById('dvNovaDesc').style.display = 'none';
                    document.getElementById('btNovo').style.display = 'none';
                    document.getElementById('btExistente').style.display = 'block';
                }
            }
            function cancelar() {
                document.forms[0].target ='_parent';
                var action = 'begin.do';
                document.forms[0].action = action;
                parent.mostrar_div();
                document.forms[0].submit();
            }

        //-->
        </script>    
        <script language="javascript" for="window" event="onload">
        <!--   
            if('<bean:write name="FormOrganizacao" property="msgError" />' != "")
            {
                alert('<bean:write name="FormOrganizacao" property="msgError" />');
            }
        //-->
        </script> 

    </head>
    
    <body><!-- onunload="JavaScript:cancelar();"-->
    <acesso:controlHiddenItem nomeIdentificador="usu_mdescr_verpagina">
    <form action="<%=request.getContextPath()%>/usuario/Organograma/manterOrganizacao/gravaItem.do" id="gravaItem" name="gravaItem" onSubmit="return false;" method="POST">    
        <vivo:quadro id="Descricao" width="390" height="340" label='Inclusão de Item' scroll="true">
                            
            <table>
                <tr>
                    <td><input name="desc" type="radio" class="radio" align="middle" onclick="mudaDescricao(true);document.forms[0].dsOrganizacao.focus()"></td>
                    <td>Nova</td>
                    <td rowspan="2" valign="bottom" style="padding-left:4px;">
                        
                        <html:hidden name="FormOrganizacao" property="idOrganizacao" />
                        <html:hidden name="FormOrganizacao" property="idOrganizacaoPai" />
                                                
                        <div id="dvNovaDesc" style="display:none;">
                            <html:text name="FormOrganizacao" property="dsOrganizacao" style="width:250px" maxlength="254" value="" size="40"/>
                        </div>

                        <div id="dvExistDesc" style="display:none;">
                            <html:select name="FormOrganizacao" property="tipoOrganizacao" style="width:250px" styleClass="SELECT">
                                <html:option value="000">Selecione uma opção...</html:option>
                                <html:options collection="ListaOrganizacao" property="idTipoOrganizacao" labelProperty="dsTipoOrganizacao" /> 
                            </html:select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><input name="desc" type="radio" class="radio" onclick="mudaDescricao(false);"></td>
                    <td>Existente</td>
                </tr>
                <tr>
                    
                    <td colspan="3" align="right">
                    <div id="btNovo" style="display:none;">
                    <acesso:controlHiddenItem nomeIdentificador="usu_mdescr_gravar">
                    	<img id="btgrava" onclick="gravaNovo();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>                        
                    </acesso:controlHiddenItem>
                    </div>
                    <div id="btExistente" style="display:none;">
                    <acesso:controlHiddenItem nomeIdentificador="usu_mdescr_gravar">
                    	<img id="btgrava" onclick="gravaExistente();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                    </div>
                    </td>
                    
                </tr>
            </table>
            
            </vivo:quadro>
            </form>
            <script language="javascript" for="window" event="onload">
            <!--   
                parent.oculta_div();
            -->
            </script> 
            </acesso:controlHiddenItem>
    </body>
    
</html>