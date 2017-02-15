<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="aparelhoForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript">
            pesquisar = function(){
                if(validaForm()){
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    var f = document.forms[0];
                    for(j = 0; j<f.idSelRegional.options.length; j++) {
                        f.idSelRegional.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelTpCliente.options.length; j++) {
                        f.idSelTpCliente.options[j].selected = true;
                    }
                    //BREDES COMENTOU
                    //for(j = 0; j<f.idSelTpLinha.options.length; j++) {
                        //f.idSelTpLinha.options[j].selected = true;
                    //}
                    for(j = 0; j<f.idSelGrupos.options.length; j++) {
                        f.idSelGrupos.options[j].selected = true;
                    }
                    for(j = 0; j<f.idSelSegmentacao.options.length; j++) {
                        f.idSelSegmentacao.options[j].selected = true;
                    }
                    parent.selecionaAbaAparelho();
                    parent.habilitaSubmit();
                    f.action = 'pesquisar.do';
                    f.submit();
                }
            }

            consultarMatriz = function(){
                parent.matrizOferta();
            }

            function validaForm(){
                var f = document.forms[0];
                if(f.idSelRegional.options.length<1){
                    alert('É necessário informar ao menos 1 valor no campo Regional');
                    return false;
                }
                if(f.idSelTpCliente.options.length<1){
                    alert('É necessário informar ao menos 1 valor no campo Tipo de Cliente');
                    return false;
                }
                //BREDES COMENTOU
                //if(f.idSelTpLinha.options.length<1){
                    //alert('É necessário informar ao menos 1 valor no campo Tipo de Linha');
                    //return false;
                //}
                if(f.idSelGrupos.options.length<1){
                    alert('É necessário informar ao menos 1 valor no campo Grupos');
                    return false;
                }
                if(f.idSelSegmentacao.options.length<1){
                    alert('É necessário informar ao menos 1 valor no campo Segmentações');
                    return false;
                }
                return true;
            }

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
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                <logic:notEmpty name="msgError" scope="request">
                alert('<bean:write name="msgError" scope="request"/>');
                </logic:notEmpty>
                parent.desabilitaSubmit();
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <form method="post" action="" id="formAparelho" name="formAparelho" enctype="multipart/form-data" style="margin:0px;">
            <table width="95%" align="left">
                <tr>
                    <td align="left">
                        <table width="100%">
                            <tr>
                                <td align="center">Regionais Disponíveis</td>
                                <td>&nbsp;</td>
                                <td align="center">Regionais Selecionadas</td>
                            </tr>
                            <tr>
                                <td width="45%" align="center">
                                    <select name="idDispRegional" id="idDispRegional" style="width:150px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaRegional.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                                <td width="10%" align="center">
                                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispRegional'),$('idSelRegional'),false);">
                                    <br>
                                    <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispRegional'),$('idSelRegional'),true);">
                                </td>
                                <td width="45%" align="center">
                                    <select name="idSelRegional" id="idSelRegional" style="width:150px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaSelRegional.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td align="left">
                        <table width="100%">
                            <tr>
                                <td align="center">Tipos de Clientes Disponíveis</td>
                                <td>&nbsp;</td>
                                <td align="center">Tipos de Clientes Selecionadas</td>
                            </tr>
                            <tr>
                                <td width="45%" align="center">
                                    <select name="idDispTpCliente" id="idDispTpCliente" style="width:150px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaClientes.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                                <td width="10%" align="center">
                                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpCliente'),$('idSelTpCliente'),false);">
                                    <br>
                                    <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpCliente'),$('idSelTpCliente'),true);">
                                </td>
                                <td width="40%" align="center">
                                    <select name="idSelTpCliente" id="idSelTpCliente" style="width:150px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaSelClientes.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                
                <tr>
                
                    <td align="left">
                        <table width="100%">
                            <tr>
                                <td align="center">Segmentações Disponíveis</td>
                                <td>&nbsp;</td>
                                <td align="center">Segmentações Selecionadas</td>
                            </tr>
                            <tr>
                                <td width="45%" align="center">
                                    <select name="idDispSegmentacao" id="idDispSegmentacao" style="width:150px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaSegmentacao.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                                <td width="10%" align="center">
                                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispSegmentacao'),$('idSelSegmentacao'),false);">
                                    <br>
                                    <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispSegmentacao'),$('idSelSegmentacao'),true);">
                                </td>
                                <td width="45%" align="center">
                                    <select name="idSelSegmentacao" id="idSelSegmentacao" style="width:150px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaSelSegmentacao.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>                
                
                     <!--BREDES COMENTOU-->
                    <!--td align="left">
                        <table width="100%">
                            <tr>
                                <td align="center">Tipo de Linha Disponíveis</td>
                                <td>&nbsp;</td>
                                <td align="center">Tipo de Linha Selecionadas</td>
                            </tr>
                            <tr>
                                <td width="45%" align="center">
                                    <select name="idDispTpLinha" id="idDispTpLinha" style="width:150px;" size="6" class="SELECT" multiple>
                                        <!--logic:iterate id="it" name="Form" property="listaLinhas.itArray" indexId="idx"-->
                                            <!--option value="<!--bean:write name="it" property="id"/>"--><!--bean:write name="it" property="ds"/></option>
                                        <!--/logic:iterate>
                                    </select>
                                </td>
                                <td width="10%" align="center">
                                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpLinha'),$('idSelTpLinha'),false);">
                                    <br>
                                    <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispTpLinha'),$('idSelTpLinha'),true);">
                                </td>
                                <td width="45%" align="center">
                                    <select name="idSelTpLinha" id="idSelTpLinha" style="width:150px;" size="6" class="SELECT" multiple>
                                        <!--logic:iterate id="it" name="Form" property="listaSelLinhas.itArray" indexId="idx">
                                            <option value="<!--bean:write name="it" property="id"/>"><!--bean:write name="it" property="ds"/></option>
                                        <!--/logic:iterate>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                    -->
                    
                    
                    <td align="left">
                        <table width="100%">
                            <tr>
                                <td align="center">Grupos Disponíveis</td>
                                <td>&nbsp;</td>
                                <td align="center">Grupos Selecionadas</td>
                            </tr>
                            <tr>
                                <td width="45%" align="center">
                                    <select name="idDispGrupos" id="idDispGrupos" style="width:150px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaGrupos.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                                <td width="10%" align="center">
                                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispGrupos'),$('idSelGrupos'),false);">
                                    <br>
                                    <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispGrupos'),$('idSelGrupos'),true);">
                                </td>
                                <td width="45%" align="center">
                                    <select name="idSelGrupos" id="idSelGrupos" style="width:150px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaSelGrupos.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                
                 <!--BREDES COMENTOU-->
                <!--tr>
                    <td align="left">
                        <table width="100%">
                            <tr>
                                <td align="center">Segmentações Disponíveis</td>
                                <td>&nbsp;</td>
                                <td align="center">Segmentações Selecionadas</td>
                            </tr>
                            <tr>
                                <td width="45%" align="center">
                                    <select name="idDispSegmentacao" id="idDispSegmentacao" style="width:150px;" size="6" class="SELECT" multiple>
                                        <!--logic:iterate id="it" name="Form" property="listaSegmentacao.itArray" indexId="idx">
                                            <option value="<!--bean:write name="it" property="id"/>"><!--bean:write name="it" property="ds"/></option>
                                        <!--/logic:iterate>
                                    </select>
                                </td>
                                <td width="10%" align="center">
                                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispSegmentacao'),$('idSelSegmentacao'),false);">
                                    <br>
                                    <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="cursor:pointer;" onclick="transfereSelecaoLista($('idDispSegmentacao'),$('idSelSegmentacao'),true);">
                                </td>
                                <td width="45%" align="center">
                                    <select name="idSelSegmentacao" id="idSelSegmentacao" style="width:150px;" size="6" class="SELECT" multiple>
                                        <!--logic:iterate id="it" name="Form" property="listaSelSegmentacao.itArray" indexId="idx">
                                            <option value="<!--bean:write name="it" property="id"/>"><!--bean:write name="it" property="ds"/></option>
                                        <!--/logic:iterate>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>&nbsp;</td>
                </tr-->
                <tr>
                    <td>&nbsp;</td>
                    <td align="right">
                        <!--img src="/FrontOfficeWeb/resources/images/bt_consmat_nrml.gif" style="cursor:pointer;" onclick="consultarMatriz();"/-->&nbsp;&nbsp;
                        <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="cursor:pointer;" onclick="pesquisar();"/>
                    </td>
                </tr>
            </table>
        </form>
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
    </netui-template:section>
</netui-template:template>