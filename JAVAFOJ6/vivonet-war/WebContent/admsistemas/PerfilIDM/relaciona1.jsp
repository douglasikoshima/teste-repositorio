<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilIdmForm"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">

        <script type="text/javascript" language="javascript">
        <!--
            transfereSelecaoLista = function(listaOrigem, listaDestino, inverse, flAll){
                var i;
                if (inverse) {
                    for(i = 0; i<listaDestino.options.length; i++) {
                        if (flAll) listaDestino.options[i].selected = true;
                            if(listaDestino.options[i].selected)
                                listaOrigem.options[listaOrigem.options.length] = new Option(listaDestino.options[i].text, listaDestino.options[i].value);
                    }
                    for(i = listaDestino.options.length-1; i>=0; i--)
                        if(listaDestino.options[i].selected)
                            listaDestino.options[i] = null;
                } else {
                    for(i = 0; i<listaOrigem.options.length; i++) {
                        if (flAll) listaOrigem.options[i].selected = true;
                        if(listaOrigem.options[i].selected)
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                    for(i = listaOrigem.options.length-1; i>=0; i--)
                        if(listaOrigem.options[i].selected)
                            listaOrigem.options[i] = null;
                }
            }

            onload = function(){
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            }

            validaForm = function(){
                if(($('idPerfil').value=='' || $('idPerfil').value=='0') && $('nmPerfil').value==''){
                    if(!parent.hasPerfil() && parent.$('nmPerfil').value==''){
                        alert('Por favor, informe o Perfil ou o Nome para criação.');
                        return false;

                    }else{
                        $('idPerfil').value = parent.$('idPerfil').value;
                        $('nmPerfil').value = parent.$('nmPerfil').value;
                        if($('idPerfil').value=='0') $('idPerfil').value = '';
                    }
                }

                if(!$('arrSelecionados').options.length>0){
                    alert('Por favor, selecione pelo menos 1(um) item para relacionar.');
                    return false;
                }
                return true;
            }

            gravar = function(){
                if(validaForm()){
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();

                    for(i = 0; i<$('arrSelecionados').options.length; i++)
                        $('arrSelecionados').options[i].selected = true;

                    $('FormRelaciona').target = 'frameApp';
                    $('FormRelaciona').submit();
                }
            }
        -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <form name="FormRelaciona" id="FormRelaciona" action="<bean:write name="Form" property="action"/>" method="POST" onsubmit="return false;">
            <html:hidden name="Form" property="idPerfil" styleId="idPerfil"/>
            <html:hidden name="Form" property="nmPerfil" styleId="nmPerfil"/>
            <table width="100%" class="tbl_bgGray">
                <tr>
                    <td>
                        <table width="95%" align="center">
                            <tr>
                                <td align="center" colspan="2">

                                    <fieldset>
                                    <legend style="font-weight:bold;"><bean:write name="Form" property="nmTela"/></legend>
                                    <table width="100%">
                                        <tr>
                                            <td>
                                            <table width="95%" align="center">
                                                <tr><td height="4"></td></tr>
                                                <tr>
                                                    <td align="center" colspan="2">
                                                        <table width="100%" align="center">
                                                            <tr>
                                                                <td width="47%" align="center">Disponíveis</td>
                                                                <td width="5%">&nbsp;</td>
                                                                <td width="47%" align="center">Selecionados</td>
                                                            </tr>
                                                            <tr><td height="4"></td></tr>
                                                            <tr>
                                                                <td width="47%" align="center">&nbsp;
                                                                    <select name="arrDisponiveis" id="arrDisponiveis" style="width:225px;" size="8" class="SELECT" multiple>
                                                                        <logic:present name="Form" property="lstDisponiveis">
                                                                            <logic:iterate id="it" name="Form" property="lstDisponiveis.itArray">
                                                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                                                            </logic:iterate>
                                                                        </logic:present>
                                                                    </select>
                                                                </td>
                                                                <td width="5%" align="center">
                                                                    <img onclick="transfereSelecaoLista($('arrDisponiveis'),$('arrSelecionados'), false);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand;"/>
                                                                    <br/><br/>
                                                                    <img onclick="transfereSelecaoLista($('arrDisponiveis'),$('arrSelecionados'), true);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border:none;cursor:hand;"/>
                                                                </td>
                                                                <td width="47%" align="center">&nbsp;
                                                                    <select name="arrSelecionados" id="arrSelecionados" style="width:225px;" size="8" class="SELECT" multiple>
                                                                        <logic:present name="Form" property="lstSelecionados">
                                                                            <logic:iterate id="it1" name="Form" property="lstSelecionados.itArray" indexId="idx">
                                                                                <option value="<bean:write name="it1" property="id"/>"><bean:write name="it1" property="ds"/></option>
                                                                            </logic:iterate>
                                                                        </logic:present>
                                                                    </select>
                                                                </td>
                                                            </tr>
                                                            <tr><td height="6"></td></tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr><td height="4"></td></tr>
                                                <tr>
                                                    <td align="right" colspan="4">
                                                        <img src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" onClick="gravar();" style="border:none;cursor:hand;"/>
                                                    </td>
                                                </tr>
                                                <tr><td height="2"></td></tr>
                                            </table>
                                            </td>
                                        </tr>
                                    </table>
                                    </fieldset>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </netui-template:section>
</netui-template:template>