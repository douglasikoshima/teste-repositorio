<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="simuladorForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="javascript">
            var step = '<%=request.getAttribute("step")%>';

            function pesquisar(passo){
                var f = document.forms[0];
                if(passo=='int'){
                    if(validaPesqInt()){
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                        step = 'dest';
                        f.action = 'getIntencao.do?step='+step;
                        f.submit();
                    }
                }else if(passo=='dest'){
                    if(validaPesqDest()){
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                        step = 'oferta';
                        f.action = 'getDestinos.do?step='+step;
                        f.submit();
                    }
                }else if(passo=='oferta'){
                    if(validaPesqOfer()){
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                        step = 'start';
                        f.action = 'getOfertas.do?step='+step;
                        f.submit();
                    }
                }else if(passo=='start'){
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    step = '';
                    f.action = 'begin.do';
                    f.submit();
                }
            }

            validaPesqInt = function(){
                var f = document.forms[0];
                if(f.idSelRegional.value==''){
                    alert('É necessário a seleção de um valor de Regional');
                    return false;
                }
                if(f.idSelTpCliente.value==''){
                    alert('É necessário a seleção de um valor de Tipo de Cliente');
                    return false;
                }
                if(f.idSelTpLinha.value==''){
                    alert('É necessário a seleção de um valor de Tipo de Linha');
                    return false;
                }
                if(f.idSelGrupos.value==''){
                    alert('É necessário a seleção de um valor de Grupos');
                    return false;
                }
                if(f.idSelSegmentacao.value==''){
                    alert('É necessário a seleção de um valor de Segmentacao');
                    return false;
                }
                return true;
            }

            validaPesqDest = function(){
                var f = document.forms[0];
                if(f.idSelIntencao.value==''){
                    alert('É necessário a seleção de um valor de Intencao');
                    return false;
                }
                return true;
            }

            validaPesqOfer = function(){
                var f = document.forms[0];
                if(f.idSelDestinos.value==''){
                    alert('É necessário a seleção de um valor de Destinos');
                    return false;
                }
                return true;
            }

            reiniciar = function(){
                var f = document.forms[0];
                f.action = 'begin.do';
                f.submit();
            }

            numberElements = function(oSrc,oDst){
                $(oDst).innerText = $(oSrc).length;
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                <logic:present name="Form" property="listaIntencao.itArray[0]">
                $('lstIntencao').show();
                numberElements('idSelIntencao','nInt');
                $('btImg').src = '/FrontOfficeWeb/resources/images/bt_proxima_nrml.gif';
                </logic:present>
                <logic:present name="Form" property="listaDestinos.itArray[0]">
                $('lstDestinos').show();
                numberElements('idSelDestinos','nDst');
                $('btImg').src = '/FrontOfficeWeb/resources/images/bt_proxima_nrml.gif';
                </logic:present>
                <logic:present name="Form" property="listaOfertas.itArray[0]">
                $('lstOfertas').show();
                numberElements('idDispOfertas','nOft');
                $('btImg').hide();
                </logic:present>
                <logic:notEmpty name="msgError" scope="request">
                alert('<bean:write name="msgError" scope="request"/>');
                </logic:notEmpty>
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <form method="post" action="" id="simuladorForm" name="simuladorForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;">
        <div style="margin-left:5px;">
        <table width="99%" border="0" cellspacing="1" cellpadding="1">
            <tr>
                <td width="">Regional</td>
                <td width="">Tipo de Cliente</td>
                <td width="">Tipo de Linha</td>
                <td width="">Grupos</td>
                <td width="">Segmentação</td>
            </tr>
            <tr>
                <td width="">
                    <html:select name="Form" property="idSelRegional" style="width:150px;" styleClass="SELECT">
                        <option value="">-- Selecione --</option>
                        <html:optionsCollection name="Form" property="listaRegional.itArray" value="id" label="ds"/>
                    </html:select>
                </td>
                <td width="">
                    <html:select name="Form" property="idSelTpCliente" style="width:150px;" styleClass="SELECT">
                        <option value="">-- Selecione --</option>
                        <html:optionsCollection name="Form" property="listaClientes.itArray" value="id" label="ds"/>
                    </html:select>
                </td>
                <td width="">
                    <html:select name="Form" property="idSelTpLinha" style="width:150px;" styleClass="SELECT">
                        <option value="">-- Selecione --</option>
                        <html:optionsCollection name="Form" property="listaLinhas.itArray" value="id" label="ds"/>
                    </html:select>
                </td>
                <td width="">
                    <html:select name="Form" property="idSelGrupos" style="width:150px;" styleClass="SELECT">
                        <option value="">-- Selecione --</option>
                        <html:optionsCollection name="Form" property="listaGrupos.itArray" value="id" label="ds"/>
                    </html:select>
                </td>
                <td width="">
                    <html:select name="Form" property="idSelSegmentacao" style="width:150px;" styleClass="SELECT">
                        <option value="">-- Selecione --</option>
                        <html:optionsCollection name="Form" property="listaSegmentacao.itArray" value="id" label="ds"/>
                    </html:select>
                </td>
            </tr>
        </table>
        <div id="lstIntencao" style="display:none;">
            <table width="99%" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td width="">Intenções (<span id="nInt"></span>)</td>
                </tr>
                <tr>
                    <td width="">
                        <html:select name="Form" property="idSelIntencao" style="width:100%;" size="7" styleClass="SELECT">
                            <html:optionsCollection name="Form" property="listaIntencao.itArray" value="id" label="ds"/>
                        </html:select>
                        <script language="javascript">
                            $('idSelIntencao').value = '<bean:write name="Form" property="idSelIntencao"/>';
                        </script>
                        <%--select name="idDispIntencao" id="idDispIntencao" style="width:100%;" size="7" class="SELECT"-->
                            <logic:iterate id="it" name="Form" property="listaIntencao.itArray" indexId="idx">
                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                            </logic:iterate>
                        <!--/select--%>
                    </td>
                </tr>
            </table>
        </div>
        <div id="lstDestinos" style="display:none;">
            <table width="99%" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td width="">Destinos (<span id="nDst"></span>)</td>
                </tr>
                <tr>
                    <td width="">
                        <html:select name="Form" property="idSelDestinos" style="width:100%;" size="7" styleClass="SELECT">
                            <html:optionsCollection name="Form" property="listaDestinos.itArray" value="id" label="ds"/>
                        </html:select>
                        <script language="javascript">
                            $('idSelDestinos').value = '<bean:write name="Form" property="idSelDestinos"/>';
                        </script>
                        <%--select name="idDispDestinos" id="idDispDestinos" style="width:100%;" size="7" class="SELECT"-->
                            <logic:iterate id="it" name="Form" property="listaDestinos.itArray" indexId="idx">
                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                            </logic:iterate>
                        <!--/select--%>
                    </td>
                </tr>
            </table>
        </div>
        <div id="lstOfertas" style="display:none;">
            <table width="99%" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td width="">Ofertas (<span id="nOft"></span>)</td>
                </tr>
                <tr>
                    <td width="">
                        <select name="idDispOfertas" id="idDispOfertas" style="width:100%;" size="7" class="SELECT">
                            <logic:iterate id="it" name="Form" property="listaOfertas.itArray" indexId="idx">
                                <option value="<bean:write name="it" property="id"/>"><bean:write name="it" property="ds"/></option>
                            </logic:iterate>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        </div>
        <div style="margin-top:3px;width:776px;height:14px;">
            <!--acesso:controlHiddenItem nomeIdentificador="fid_mb_pesquisar"-->
                <img id="btImg" src="/FrontOfficeWeb/resources/images/bt_simular_nrml.gif" style="cursor:pointer;" align="right" onclick="pesquisar(step);">
                <img id="btIni" src="/FrontOfficeWeb/resources/images/bt_reiniciar_nrml.gif" style="cursor:pointer;" align="right" onclick="reiniciar();">
            <!--/acesso:controlHiddenItem-->
        </div>
    </form>
    <script>
        document.body.style.backgroundColor = '#ededed';
    </script>
    </netui-template:section>
</netui-template:template>