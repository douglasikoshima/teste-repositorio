<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formMenu"/>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="VivoNet"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script language="JavaScript">
            <!--
                gravar = function(){
                    new Ajax.Request('gravarParametro.do', {
                        method: 'post',
                        asynchronous: false,
                        parameters: $('frmParam').serialize(),
                        onComplete: function(r){
                            if(r.responseText.indexOf("html")>-1){
                                window.top.location.href = '<%=request.getContextPath()%>';
                            }else{
                                var dom = parseXml(r.responseText);
                                var jsonString = xml2json(dom, '');
                                var jsonObj = jsonString.evalJSON();

                                if(top.frameApp!=null)
                                    if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();

                                if(jsonObj.message!=null){
                                    if(jsonObj.message.msg!=null){
                                        alert(jsonObj.message.msg);
                                        window.top.frameApp.location.href='manterAcesso.do';
                                    }
                                }
                            }
                        },
                        onCreate: function() {
                            if(top.frameApp!=null)
                                if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
                        },
                        onFailure: function(e){alert("[Failure] "+e+"\n");},
                        onException: function(transport, e){alert("[Exception] "+e.description+"\n"+transport);}
                    });
                }

                desabilitaRadio = function(){
                    if($('tpBloqNVivoSi').checked){
                        $('blqAcc').style.visibility = 'visible';
                    }else{
                        $('blqAcc').style.visibility = 'hidden';
                    }
                }

                gerarArquivo = function(){
                    var f = document.forms['frmParam'];
                    f.target = "frmDownload";
                    f.submit();
                }

                onload = function(){
                    if($('tpBloqNVivoNo').checked){
                        $('blqAcc').style.visibility = 'hidden';
                    }else{
                        $('blqAcc').style.visibility = 'visible';
                    }
                }
            -->
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <!-- Menu de Administração de Sistemas -->
        <div id="menuPrincipal"><jsp:include page="/resources/menus/MenuPrincipal.jsp" /></div>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <!--APLICACAO-->
        <vivo:body idTable="tbMain" idDiv="dvMain" height="498" width="790">
            <vivo:quadro id="qdMain" height="473" width="780" label="Parametrização > Manutenção de Acesso">
            <form name="frmParam" id="frmParam" method="post" enctype="multipart/form-data" onsubmit="return false;" style="margin:0;">
                <table width="90%" height="150" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;margin-left:5px;">
                    <tr>
                        <td><b>Bloquear acesso ao VOLE para números não Vivo?</b></td>
                        <td>
                            <input type="radio" name="tpBlqNVivo" id="tpBloqNVivoSi" value="FALSE" class="radio" onclick="desabilitaRadio(this);" <logic:equal name="Form" property="tpBlqNVivo" value="FALSE">checked</logic:equal>><b>Sim</b>&nbsp;
                            <input type="radio" name="tpBlqNVivo" id="tpBloqNVivoNo" value="TRUE" class="radio" onclick="desabilitaRadio(this);" <logic:equal name="Form" property="tpBlqNVivo" value="TRUE">checked</logic:equal>><b>Não</b>&nbsp;
                        </td>
                    </tr>
                    <tr id="blqAcc">
                        <td><b>Bloquear acesso para os clientes que já realizaram o primeiro acesso para números não Vivo?</b></td>
                        <td>
                            <input type="radio" name="tpBlqJaNVivo" id="tpBloqJaNVivoSi" value="FALSE" class="radio" <logic:equal name="Form" property="tpBlqJaNVivo" value="FALSE">checked</logic:equal>><b>Sim</b>&nbsp;
                            <input type="radio" name="tpBlqJaNVivo" id="tpBloqJaNVivoNo" value="TRUE" class="radio" <logic:equal name="Form" property="tpBlqJaNVivo" value="TRUE">checked</logic:equal>><b>Não</b>&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            Data da última alteração: <b><bean:write name="Form" property="dtUltimaAlt"/></b>&nbsp;&nbsp;
                            Pelo login: <b><bean:write name="Form" property="nmLogin"/></b>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <img src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" style="cursor:pointer;" onclick="gravar();"/>
                        </td>
                    </tr>
                </table>
                <%--vivo:quadro id="qdMain" height="200" width="770" label="Download do arquivo de acesso">
                    <table width="90%" height="150" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;margin-left:5px;">
                        <tr>
                            <td><input type="radio" name="tpRelNVivo" id="tpRelNVivo" value="0" class="radio"></td>
                            <td><b>Acesso linha não VIVO</b></td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="tpRelNVivo" id="tpRelJaNVivo" value="1" class="radio"></td>
                            <td><b>Acesso linha não VIVO com primeiro acesso realizado</b></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <img src="/FrontOfficeWeb/resources/images/bt_gerararq_nrml.gif" style="cursor:pointer;" onclick="gerarArquivo();"/>
                            </td>
                        </tr>
                    </table>
                </vivo:quadro--%>
            </form>
            <iframe src="about:blank" id="frmDownload" name="frmDownload" style="display:none;" width="1" height="1"></iframe>
            </vivo:quadro>
        </vivo:body>

    </netui-template:section>
</netui-template:template>
