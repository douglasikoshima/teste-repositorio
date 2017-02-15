<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:setAttribute name="title" value="Fidelização"/>
    <netui-template:setAttribute name="modulo" value="Relatório de Status SAP"/>

    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/chili/jquery.chili-2.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/chili/recipes.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/chili/formatPhoneNumber.js"></script>
        <script type="text/javascript">
            jQuery.noConflict();
            onload = function() {
                $('nrLinha').focus();
                if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();
            }
            buscarLog = function() {
                if ($F('nrLinha').blank()) {
                    alert('Por favor, digite um número de telefone para realizar a busca.');
                } else {
                    var params = $H();
                    params.set('nrLinha', $F('nrLinha'));
                    new Ajax.Updater('div-pesquisa', 'buscarListaStatusSAP.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onCreate: function() {
                            if (top.frameApp.dvAnimarAguarde) top.frameApp.startAnimation();
                        }, onSuccess: function() {
                            if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();
                        }, onComplete: function() {
                            if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation();
                        }, onFailure: function(e) {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            createErrorPopUp('erroPortabilidade', e.statusText, stackTrace, false);
                        }
                    });
                }
            }
            showXML = function(idx) {
                var params = $H();
                params.set('index', idx);
                new Ajax.Request('showXML.do', {
                    method: 'post',
                    parameters: params,
                    onSuccess: function(e) {
                        var xml = '<XML>' + e.responseText + '</XML>';
                        createNewPopUpText('popXML', 'XML IN', '600', '400', null, true, '<pre class="ln-"><code id="codeWrapper" class="html"></code></pre>', '', '');
                        jQuery('pre.ln- code').text(formatXml(xml));
                        jQuery('#codeWrapper').chili();
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.stopAnimation();
                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    }, on406: function(e) {
                        window.top.frameApp.createErrorPopUp('erroContaOnline', e.responseText, e.statusText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        </script>
        <style type="text/css">
            #table-parametros {
                border: 1px solid #c7daea;
                width: 100%;
                margin-top: 3px;
            }
            #div-pesquisa {
                border: 1px solid #c7daea;
                margin-top: 5px;
                height: 427px;
                padding: 5px;
            }
            #div-form {
                margin: 5px 0 5px 0;
            }
            #div-form form {
                margin: 0;
                padding: 0;
            }
        </style>

    </netui-template:section>

    <netui-template:section name="bodySection">

        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>

        <vivo:sessao id="qdMain" height="470" width="790" label="Fidelização" operacoes="Relatório Status SAP" scroll="no">

            <table id="table-parametros" cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <td colspan="27">
                            Número do telefone:
                            <input type="text"
                                   id="nrLinha"
                                   name="nrLinha"
                                   maxlength="14"
                                   style="width:80px;"
                                   onblur="formatPhoneNumberObj(this)" />
                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" onmouseup="buscarLog()" class="button" align="absmiddle" />
                        --</td>
                    </tr>
                </thead>
            </table>
            <div id="div-pesquisa"></div>
        </vivo:sessao>
        <vivo:alert atributo="msgRetorno" scope="request" />
    </netui-template:section>
</netui-template:template>