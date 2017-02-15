<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript">
            pesquisarOfertas = function(){
                var f = document.forms[0];
                if(f.idSelDestinos.options.length<1){
                    alert('É necessário informar ao menos 1 valor no campo Destinos previstos');
                    return false;
                }
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                for(j = 0; j<f.idSelDestinos.options.length; j++) {
                    f.idSelDestinos.options[j].selected = true;
                }
                f.action = 'pesquisarOfertas.do';
                f.submit();
            }

            function validaForm(){
                var f = document.forms[0];
                if(f.idSelOfertas.options.length<1){
                    //alert('É necessário a seleção de ao menos um(a) valor de Ofertas');
                    //return false;
                    if(confirm('Deseja realmente excluir esta matriz de oferta?')){
                        return true;
                    }
                    return false;
                }
                return true;
            }

            function gravar(){
                if(validaForm()){
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    var f = document.forms[0];
                    for(j = 0; j<f.idSelOfertas.options.length; j++) {
                        f.idSelOfertas.options[j].selected = true;
                    }
                    f.action = "gravarOfertas.do";
                    parent.selecionaAbaFiltro();
                    f.submit();
                }
            }

            loadDestinos = function(obj){
                var f = document.forms[0];
                //if(obj.value!=''){
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    f.action = "pesquisarDestinos.do";
                    f.submit();
                //}
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
            <logic:notEmpty name="msgError" scope="request">
            alert('<bean:write name="msgError" scope="request"/>');
            </logic:notEmpty>
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <form method="post" action="" id="formAparelho" name="formAparelho" enctype="multipart/form-data" style="margin:0px;">
            <table width="95%" align="center" cellpadding="2" cellspacing="2">
                <tr>
                    <td colspan="2">
                        <table width="745" align="center">
                            <tr>
                                <td>Fidelização</td>
                                <td><html:checkbox name="Form" property="inFidelizacao" style="border:none;" value="1"/></td>
                            </tr>
                            <tr>
                                <td>Intenção de Cancelamento</td>
                                <td>
                                    <html:select name="Form" property="idIntencao" style="width:480px;" styleClass="SELECT" onchange="loadDestinos(this);">
                                        <option value="">-- Selecione --</option>
                                        <html:optionsCollection name="Form" property="listaIntencao.itArray" value="id" label="ds"/>
                                    </html:select>
                                </td>
                           </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <table width="100%" align="left">
                            <tr>
                                <td align="center">Destinos previstos Disponíveis</td>
                                <td>&nbsp;</td>
                                <td align="center">Destinos previstos Selecionados</td>
                            </tr>
                            <tr>
                                <td width="43%" align="center">
                                    <select name="idDispDestinos" id="idDispDestinos" style="width:315px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaDestinos.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                                <td width="14%" align="center">
                                    <table>
                                        <tr>
                                            <td><img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispDestinos'),$('idSelDestinos'),false);"/></td>
                                        </tr>
                                        <tr>
                                            <td><img src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispDestinos'),$('idSelDestinos'),false,true);"/></td>
                                        </tr>
                                        <tr>
                                            <td><img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispDestinos'),$('idSelDestinos'),true);"/></td>
                                        </tr>
                                        <tr>
                                            <td><img src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispDestinos'),$('idSelDestinos'),true,true);"/></td>
                                        </tr>
                                    </table>
                                </td>
                                <td width="43%" align="center">
                                    <select name="idSelDestinos" id="idSelDestinos" style="width:315px;" size="6" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaSelDestinos.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <img src="./../../../resources/images/bt_pesquisar_nrml.gif" style="cursor:pointer;" onClick="pesquisarOfertas();">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <vivo:quadro id="qdMain" height="160" width="745" label="Configuração da Matriz de Ofertas">
                            <table width="100%">
                                <tr>
                                    <td align="center">
                                        Ofertas Disponíveis
                                        <select name="idDispOfertas" id="idDispOfertas" style="width:315px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaOfertas.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                    <td>
                                        <table>
                                            <tr>
                                                <td><img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispOfertas'),$('idSelOfertas'),false);"/></td>
                                            </tr>
                                            <tr>
                                                <td><img src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispOfertas'),$('idSelOfertas'),false,true);"/></td>
                                            </tr>
                                            <tr>
                                                <td><img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispOfertas'),$('idSelOfertas'),true);"/></td>
                                            </tr>
                                            <tr>
                                                <td><img src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispOfertas'),$('idSelOfertas'),true,true);"/></td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td align="center">
                                        Ofertas Selecionadas
                                        <select name="idSelOfertas" id="idSelOfertas" style="width:315px;" size="9" class="SELECT" multiple>
                                            <logic:iterate id="it" name="Form" property="listaSelOfertas.itArray" indexId="idx">
                                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                        </vivo:quadro>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="right">
                        <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" style="cursor:pointer;" onclick="gravar();"/>
                    </td>
                </tr>
            </table>
        </form>
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
    </netui-template:section>
</netui-template:template>