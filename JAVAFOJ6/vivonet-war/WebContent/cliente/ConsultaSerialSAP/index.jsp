<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute value="FrontOffice - Atendimento" name="title"/>
<netui-template:setAttribute value="Atendimento" name="modulo"/>
<netui-template:section name="headerSection">
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javascript">
        <!--
            validaForm = function(){
                if($F('tpNrSerial')==''){
                    alert('Favor selecionar o tipo de consulta');
                    return false;
                }
                if($F('nrSerial').strip().empty()){
                    alert('Favor informar o '+$F('tpNrSerial'));
                    return false;
                }
                return true;
            }

            pesquisar = function(){
                limparDados();
                if(validaForm()){
                    if(window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.startAnimation();
                    new Ajax.Request('pesquisar.do', {
                        method: 'get',
                        parameters: {nrSerial: $F('nrSerial').strip(), limit:20},
                        onComplete: function(r){
                            if(window.top.frameApp.dvAnimarAguarde!=null) window.top.frameApp.stopAnimation();
                        },
                        onSuccess: function(t){
                            processarDados(t);
                        },
                        onFailure: function(e){alert("[Failure] "+e+"\n");},
                        onException: function(r, e){alert("[Exception] "+e.description+"\n"+r.transport.responseText);}
                    });
                }
            }

            processarDados = function(t){
                var xmlString = t.responseText;
                oXml       = new ActiveXObject("Microsoft.XMLDOM");
                oXml.async = false;
                var regExp = new RegExp('&', 'gi') ;
                xmlString  = xmlString.replace(regExp,"&amp;");
                oXml.loadXML(xmlString);
                if(oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {
                    friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;
                    exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                    alert(friendlyMessage);
                }else{
                    $('dsStatus').innerHTML = getNodeValue(oXml,"/xml-fragment/dsStatus");
                    $('dsRazaoSocial').innerHTML = getNodeValue(oXml,"/xml-fragment/dsRazaoSocial");
                    $('dsMaterial').innerHTML = getNodeValue(oXml,"/xml-fragment/dsMaterial");
                    $('dsDescricao').innerHTML = getNodeValue(oXml,"/xml-fragment/dsDescricao");
                }
            }

            limparDados = function(){
                $('dsStatus').innerHTML = "";
                $('dsRazaoSocial').innerHTML = "";
                $('dsMaterial').innerHTML = "";
                $('dsDescricao').innerHTML = "";
            }

            function getNodeValue(obj,key){
                try{
                    return obj.selectSingleNode(key)==null?"":obj.selectSingleNode(key).text;
                }catch(e){
                    return "";
                }
            }
        -->
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="body" height="470" width="790" label="Tracking" operacoes="Consulta Serial" scroll="N">
        <table width="100%">
            <tr>
                <td>
                    <fieldset id="psqPrevisao">
                        <legend>Pesquisa</legend>
                        <form method="post" action="pesquisar.do" id="trackingPsqForm" name="trackingPsqForm" style="margin:0px;" onsubmit="return false;">
                            <table width="100%" border="0">
                                <tr>
                                    <td align="right" width="20%">Tipo de Consulta:</td>
                                    <td align="left" width="10%">
                                        <select id="tpNrSerial" name="tpNrSerial" class="SELECT">
                                            <option value="">Selecione</option>
                                            <option value="Serial">Serial</option>
                                            <option value="ICCID">ICCID</option>
                                            <option value="IMEI">IMEI</option>
                                        </select>
                                    </td>
                                    <td align="left" width="10%">
                                        <html:text name="Form" property="nrSerial" styleId="nrSerial" maxlength="20" size="20"/>
                                    </td>
                                    <td align="right" width="70%">
                                        <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisar();" style="cursor:pointer;margin-right:30px;">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </fieldset>
                </td>
            </tr>
            <tr id="LinhaDados">
                <td>
                    <fieldset id="psqPrevisao">
                        <legend>Dados</legend>
                        <table width="100%" border="0" cellpadding="3" cellspacing="3">
                            <tr>
                                <td align="right" width="12%"><b>Status:</b></td>
                                <td align="left"><span id="dsStatus">&nbsp;</span></td>
                                <td align="right" width="12%"><b>Razão Social:</b></td>
                                <td align="left"><span id="dsRazaoSocial">&nbsp;</span></td>
                            </tr>
                            <tr>
                                <td align="right"><b>Material:</b></td>
                                <td align="left"><span id="dsMaterial">&nbsp;</span></td>
                                <td align="right"><b>Descrição:</b></td>
                                <td align="left"><span id="dsDescricao">&nbsp;</span></td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
        </table>
    </vivo:sessao>
</netui-template:section>
</netui-template:template>