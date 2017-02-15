<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                var f = document.forms[0];
                f.action = 'pesquisar.do';
                f.submit();
            }

            function validaForm(){
                var f = document.forms[0];
                if(f.idSelAparelhos.options.length<1){
                    alert('É necessário a seleção de ao menos um(a) valor de Aparelhos');
                    return false;
                }
                return true;
            }

            function gravar(){
                if(validaForm()){
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    var f = document.forms[0];
                    for(j = 0; j<f.idSelAparelhos.options.length; j++) {
                        f.idSelAparelhos.options[j].selected = true;
                    }
                    f.action = "gravarAparelhos.do";
                    parent.selecionaAbaFiltro();
                    f.submit();
                }
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
                        <table>
                            <tr>
                                <td>
                                    <center><b>Aparelhos Disponíveis</b></center>
                                    <select name="idDispAparelhos" id="idDispAparelhos" style="width:315px;height:318px;" size="22" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaAparelhos.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                                <td width="100" align="center">
                                    <table>
                                        <tr>
                                            <td><img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispAparelhos'),$('idSelAparelhos'),false);"/></td>
                                        </tr>
                                        <tr>
                                            <td><img src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispAparelhos'),$('idSelAparelhos'),false,true);"/></td>
                                        </tr>
                                        <tr>
                                            <td><img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispAparelhos'),$('idSelAparelhos'),true);"/></td>
                                        </tr>
                                        <tr>
                                            <td><img src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" style="border:none;cursor:pointer;" onClick="transfereSelecaoLista($('idDispAparelhos'),$('idSelAparelhos'),true,true);"/></td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <center><b>Aparelhos Selecionados</b></center>
                                    <select name="idSelAparelhos" id="idSelAparelhos" style="width:315px;height:318px;" size="22" class="SELECT" multiple>
                                        <logic:iterate id="it" name="Form" property="listaSelAparelhos.itArray" indexId="idx">
                                            <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                                        </logic:iterate>
                                    </select>
                                </td>
                            </tr>
                        </table>
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