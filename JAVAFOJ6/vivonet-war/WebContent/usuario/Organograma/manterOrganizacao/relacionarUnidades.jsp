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

<bean:define id="arrayUnidadesExistentesVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOrganizacao.arrayUnidadesExistentesVO"/>
<bean:define id="arrayUnidadesRelacionadosVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOrganizacao.arrayUnidadesRelacionadosVO"/>

<html>
    <head>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
        <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

        <script language="Javascript">
        
            function mudaDescricao(boolean) {
                if (boolean == true) {
                    document.getElementById('dvNovaDesc').style.display = 'block';
                    document.getElementById('dvExistDesc').style.display = 'none';
                } else if (boolean == false) {
                    document.getElementById('dvExistDesc').style.display = 'block';
                    document.getElementById('dvNovaDesc').style.display = 'none';
                }
            }
            
        function transfereSelecaoLista(listaOrigem, listaDestino)
        {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    // Trata a primeira posicao vazia do list, se houver
                    if ((listaDestino.options.length == 1) && (trim(listaDestino.options[0].text) == "")) {
                        listaDestino.options[0] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    } else {
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;

        }
    
        function gravar(){
            var lista = document.forms[0].arrayUnidadesRelacionados;
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].arrayUnidadesRelacionados.options[i].selected =true; 
            }
            
        }
        
        
        function salvarItem() {
            gravar();
            document.forms[0].target ='_parent';
            document.forms[0].action = "salvaRelacion.do";
            parent.mostrar_div();
            document.forms[0].submit(); 
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
    
    <body><!-- onunload="JavaScript:cancelar();"-->
    <acesso:controlHiddenItem nomeIdentificador="usu_reluni_verpagina">
    	<form action="<%=request.getContextPath()%>/usuario/Organograma/manterOrganizacao/begin.do" id="begin" name="begin" onSubmit="return false;" method="POST">            
            
            <html:hidden name="FormOrganizacao" property="idOrganizacao"/>
            <html:hidden name="FormOrganizacao" property="dsOrganizacao"/>
            <html:hidden name="FormOrganizacao" property="tipoOrganizacao"/>

            <vivo:quadro id="Descricao" width="390" height="340" label='Relacionar Unidades' scroll="no">
                                
                <table cellpadding="0" cellspacing="0" width="380">
                    <tr>
                        <td width="155" align="center">Unidades Disponíveis</td>
                        <td width="70"></td>
                        <td width="155" align="center">Unidades Selecionados</td>
                    </tr>
                    <tr>
                        <td>
                            <html:select name="FormOrganizacao" property="arrayUnidadesExistentes" multiple="true" style="width:155px;height:292px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].arrayUnidadesExistentes, document.forms[0].arrayUnidadesRelacionados);">
                                <html:options collection="arrayUnidadesExistentesVO" property="idUnidade" labelProperty="nmUnidade" /> 
                            </html:select>
                        </td>
                        <td align="center" style="padding-left:3px;">
                            <img id="btRightSimp1" onclick="transfereSelecaoLista(document.forms[0].arrayUnidadesExistentes, document.forms[0].arrayUnidadesRelacionados); return false" style="border:none;cursor:hand;" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'"/><br><br>
                            <img id="btRightSimp2" onclick="transfereSelecaoLista(document.forms[0].arrayUnidadesRelacionados, document.forms[0].arrayUnidadesExistentes); return false" style="border:none;cursor:hand;" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" />       
                        </td>
                        <td>
                            <html:select name="FormOrganizacao" property="arrayUnidadesRelacionados"  multiple="true" style="width:155px;height:292px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].arrayUnidadesRelacionados, document.forms[0].arrayUnidadesExistentes);">
                                <html:options collection="arrayUnidadesRelacionadosVO" property="idUnidade" labelProperty="nmUnidade" /> 
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1" align="right">
                            
                        </td>
                        <td colspan="2" align="right">
                        <acesso:controlHiddenItem nomeIdentificador="usu_reluni_gravar">
                            <img vspace="5" hspace="10" id="btSalvar1" onclick="salvarItem();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="border:none;cursor:hand;"/>
                        </acesso:controlHiddenItem>
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