<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formMenu" type="VOLE.Parametrizacao.ParametrizacaoController.FormMenu"/>

<html:html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript">
            <!--
                alteraStatusItemMenu = function(idUf, idTipoCarteira, idSegmentacao, idItemMenu, inAtivo, idx){
                    var params = $H();
                    params.set('idUf', idUf);
                    params.set('idTipoCarteira', idTipoCarteira);
                    params.set('idSegmentacao', idSegmentacao);
                    params.set('idItemMenu', idItemMenu);
                    params.set('inAtivo', inAtivo);

                    new Ajax.Request('alterarStatusMenu.do', {
                        method: 'post',
                        asynchronous: false,
                        parameters: params,
                        onComplete: function(r){
                            var dom = parseXml(r.responseText);
                            var jsonString = xml2json(dom, '');
                            var jsonObj = jsonString.evalJSON();

                            if(jsonObj.message!=null){
                                if(jsonObj.message.msg!=null){
                                    if(jsonObj.message.msg=="success"){
                                        var nmDiv = 'btOnOff'+idx;
                                        if(inAtivo=='1'){
                                            var onoff = "<img src=\"/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif\" border=\"0\" alt=\"Habilitar Item\" onclick=\"alteraStatusItemMenu('"+idUf+"', '"+idTipoCarteira+"', '"+idSegmentacao+"', '"+idItemMenu+"', '0','"+idx+"');\"/>";
                                            $(nmDiv).update(onoff);
                                        }else{
                                            var onoff = "<img src=\"/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif\" border=\"0\" alt=\"Desabilitar Item\" onclick=\"alteraStatusItemMenu('"+idUf+"', '"+idTipoCarteira+"', '"+idSegmentacao+"', '"+idItemMenu+"', '1','"+idx+"');\"/>";
                                            $(nmDiv).update(onoff);
                                        }
                                    }else{
                                        alert(jsonObj.message.msg);
                                    }
                                }
                            }

                            if(top.frameApp!=null)
                                if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                        },
                        onCreate: function() {
                            if(top.frameApp!=null)
                                if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
                        },
                        onFailure: function(e){alert("[Failure] "+e+"\n");},
                        onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport);}
                    });
                }

                paginar = function(offset){
                    document.forms[0].offSet.value = offset;

                    if(top.frameApp!=null)
                        if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();

                    document.forms[0].submit();
                }

                onload = function(){
                    if(top.frameApp!=null)
                        if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                }

            -->
        </script>
    </head>
    <body>
        <html:form action="/VOLE/Parametrizacao/paginarPesquisa.do" method="POST" onsubmit="return false;">
            <html:hidden name="Form" property="resultado.pgIni"/>
            <html:hidden name="Form" property="resultado.pgFim"/>
            <html:hidden name="Form" property="resultado.pgCur"/>
            <html:hidden name="Form" property="resultado.pgNum"/>
            <html:hidden name="Form" property="offSet"/>
        </html:form>
        <vivo:tbTable selectable="true" layoutWidth="725" layoutHeight="300" tableWidth="725" styleId="tbResultadoPesquisa" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="06%" type="string">Canal</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left"   width="39%" type="string">Menu</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="05%" type="string">UF</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="25%" type="string">Carteira</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="20%" type="string">Segmentação</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="05%" type="string">Status</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows scroll="false">
                <logic:iterate id="it" name="Form" property="resultado.listaItemMenuArray" indexId="idx">
                    <vivo:tbRow key="linha">
                        <vivo:tbRowColumn><bean:write name="it" property="dsCanal"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="it" property="nmItem"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="it" property="sgUf"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="it" property="dsTipoCarteira"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn><bean:write name="it" property="dsSegmentacao"/></vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <div id="btOnOff<%=idx%>">
                            <logic:equal name="it" property="inAtivo" value="1">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" border="0" alt="Desabilitar Item" onclick="alteraStatusItemMenu('<bean:write name="it" property="idUfOperadora"/>', '<bean:write name="it" property="idTipoCarteira"/>', '<bean:write name="it" property="idSegmentacao"/>', '<bean:write name="it" property="idItemMenu"/>', '<bean:write name="it" property="inAtivo"/>', '<%=idx%>');"/>
                            </logic:equal>
                            <logic:equal name="it" property="inAtivo" value="0">
                                <img src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" border="0" alt="Habilitar Item" onclick="alteraStatusItemMenu('<bean:write name="it" property="idUfOperadora"/>', '<bean:write name="it" property="idTipoCarteira"/>', '<bean:write name="it" property="idSegmentacao"/>', '<bean:write name="it" property="idItemMenu"/>', '<bean:write name="it" property="inAtivo"/>', '<%=idx%>');"/>
                            </logic:equal>
                            </div>
                        </vivo:tbRowColumn>
                    </vivo:tbRow>
                </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>

        <table align="center">
            <tr>
                <td>
                    <%
                    int ini = Integer.parseInt(Form.getResultado().getPgIni());
                    int fim = Integer.parseInt(Form.getResultado().getPgFim());
                    int cur = Integer.parseInt(Form.getResultado().getPgCur());
                    int pgs = Integer.parseInt(Form.getResultado().getPgNum());%>

                    <%if((cur-1)>0){%>
                        <a href="javascript:;" onclick="paginar(-1);"><<</a>
                    <%}%>
                    <%for(; ini <= fim; ini++){ %>
                        <%if(cur==ini){%>
                            <%=ini%>
                        <%}else{%>
                            <a href="javascript:;" onclick="paginar('<%=ini%>');" title="Página <%=ini%>"><%=ini%></a>
                        <%}%>
                    <%}%>

                     <%if((pgs!=cur) && (pgs>fim)){%>
                        <a href="javascript:;" onclick="paginar('<%=ini%>');" title="Página <%=ini%>">>></a>
                    <%}%>
                </td>
            </tr>
        </table>

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>

        <table width="725" border="0" align="center" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:9px;">
            <tr>
                <td valign="middle" width="70"><b>&nbsp;Legendas:</b></td>
                <td><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="4"/></td>
                <td><img src="/FrontOfficeWeb/resources/images/bt_icon_desabilitar.gif" align="left"/>&nbsp;Desabilita Item</td>
                <td><img src="/FrontOfficeWeb/resources/images/bt_icon_habilitar.gif" align="left"/>&nbsp;Habilita Item</td>
            </tr>
        </table>
    </body>
</html:html>