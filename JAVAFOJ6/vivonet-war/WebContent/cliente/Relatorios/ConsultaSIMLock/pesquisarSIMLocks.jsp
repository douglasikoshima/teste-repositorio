<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="lockForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lockForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Cliente"/>
    <netui-template:setAttribute name="modulo" value="Cliente"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/formatPhoneNumber.js"></script>
        <script language="javascript">
            <!--
                var int = 0;
                var status = "";
                function showFiltro(tipo){
                    if(tipo=="P"){
                        $('divDatas').show();
                        $('divLogin').hide();
                        $('divImei').hide();
                    }else if(tipo=="L"){
                        $('divDatas').hide();
                        $('divLogin').show();
                        $('divImei').hide();
                    }else if(tipo=="I"){
                        $('divDatas').hide();
                        $('divLogin').hide();
                        $('divImei').show();
                    }else{
                        $('divDatas').hide();
                        $('divLogin').hide();
                        $('divImei').hide();
                    }
                }

                function showFiltroOpcional(tipo){
                    $('vlOpcional').value = '';
                    if(tipo=="0"){
                        $('divCampo').hide();
                    }else{
                        $('divCampo').show();
                    }
                }

                function validaCampo(tipo, obj){
                    if(tipo=='LIN'){
                        obj.setAttribute("onblur","formatPhoneNumberObj(this)");
                    }else if(tipo=='CPF'){
                        checaCPF(obj);
                        obj.setAttribute("onblur","");
                    }else if(tipo=='CPJ'){
                        checaCNPJ(obj);
                        obj.setAttribute("onblur","");
                    }else if(tipo=='NRG'){
                    	obj.setAttribute("onblur","");
                        if(obj.value.length > 12){
                            obj.value = obj.value.substring(0,12);
                            return false;
                        }else{
                            checaStrEspecial(obj);
                        }
                    }else if(tipo=='PAS'){
                        checaStrEspecial(obj);
                    }
                    return false;
                }

                function limpaForm(){
                    $('dtIni').value='';
                    $('dtFim').value='';
                    $('nmLogin').value='';
                    $('nrImei').value='';
                }

                function download(){
                    if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
                    document.forms[0].method = "POST";
                    document.forms[0].target = 'ifrmDownload';
                    document.forms[0].action = "gerarArquivoParametros.do?dtIni="+$F('dtIni')
                                                +"&dtFim="+$F('dtFim')
                                                +"&nrImei="+$F('nrImei')
                                                +"&log="+$F('nmLogin')
                                                +"&opc="+$F('fitroOp')
                                                +"&doc="+$F('vlOpcional');
                    $('ifrmDownload').src = document.forms[0].action;
                    $('ifrmDownload').onreadystatechange = function(){
                        if(top.frameApp.dvAnimarAguarde!=null)
                            top.frameApp.stopAnimation();
                    }
                    //document.forms[0].submit();
                }

                function validaDataOnBlur(data){
                    if(data.value == '')
                        return false;
                    if(!validaData(data.value)){
                        data.value = '';
                        data.focus();
                        alert("Data inválida");
                    }
                }

                function showMessage(msg){
                    alert(msg);
                }
            -->
        </script>
        <style type="text/css">
            <!--
                a {text-decoration:none; color:#000;}
                a:hover {color:#0000ff;}
            -->
        </style>

    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cli_simlock_verpagina">
        <!-- Menu de Gestao de Usuarios -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <vivo:quadro id="qdMain" height="478" width="790" label="Vivo Net >> Relatórios >> Consulta de SIMLocks Desbloqueados">
        <html:form action="/cliente/Relatorios/ConsultaSIMLock/gerarArquivoParametros.do" onsubmit="return false;" method="post">
            <iframe name="ifrmDownload" id="ifrmDownload" style="visibility: hidden;" width="0" height="0"></iframe>
            <div id="ttip" style="display:none;position:absolute;width:170px;"></div>
            <table width="100%" cellspacing="1" cellpadding="1">
                <tr>
                    <td valign="top">
                        <table width="97%" border="0" cellspacing="1" cellpadding="1" align="center">
                            <tr>
                                <td valign="top">Digite os parâmetros de pesquisa para a consulta de SIMLocks Desbloqueados.</td>
                            </tr>
                            <tr>
                                <td align="center" valign="top">
                                    <table width="100%" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                                        <tr>
                                            <td width="15%" align="right">
                                                <span style="text-indent:4px;">Filtro Obrigatório:</span>
                                            </td>
                                            <td width="15%">
                                                <html:select name="lockForm" property="opcional" style="margin-left:3px;" onchange="showFiltro(this.value);limpaForm();">
                                                    <option value="0">-- Selecione --</option>
                                                    <option value="P">Periodo</option>
                                                    <option value="L">Login</option>
                                                    <option value="I">IMEI</option>
                                                </html:select>
                                            </td>
                                            <td align="left">
                                                <div id="divDatas" style="display:none;">
                                                    <html:text property="dtInicial" name="lockForm" styleId="dtIni" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                                                    <img id="imgCalendDtAbIni" src="/FrontOfficeWeb/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicial', '%d/%m/%Y');">&nbsp;Até&nbsp;
                                                    <html:text property="dtFinal" name="lockForm" size="10" styleId="dtFim" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                                                    <img id="imgCalendDtAbFim" src="/FrontOfficeWeb/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFinal', '%d/%m/%Y');">
                                                </div>
                                                <div id="divLogin" style="display:none;">
                                                    <html:text name="lockForm" property="login" styleId="nmLogin" style="width:210px;" styleClass="input" maxlength="254"/>
                                                </div>
                                                <div id="divImei" style="display:none;">
                                                    <html:text name="lockForm" property="imei" styleId="nrImei" style="width:210px" styleClass="input" maxlength="15" onkeyup="validaIMEI(this);"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right">
                                                <span style="text-indent:4px;">Filtro Opcional :</span>
                                            </td>
                                            <td align="left">
                                                <html:select name="lockForm" property="opcional" styleId="fitroOp" style="margin-left:3px;" onchange="showFiltroOpcional(this.value);$('vlOpcional').value='';">
                                                    <option value="0">-- Selecione --</option>
                                                    <option value="LIN">Linha</option>
                                                    <option value="CPF">CPF</option>
                                                    <option value="NRG">RG</option>
                                                    <option value="PAS">Passaporte</option>
                                                    <option value="CPJ">CNPJ</option>
                                                </html:select>
                                            </td>
                                            <td align="left">
                                                <div id="divCampo" style="display:none;">
                                                    <html:text name="lockForm" property="nrDoc" styleId="vlOpcional" style="width:150px" maxlength="20" styleClass="input" onkeyup="validaCampo($('fitroOp').value, this);"/>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left" colspan="2">
                                            </td>
                                            <td align="right" colspan="2" style="padding-right:30px;">
                                                <img id="btLimpar" style="border:none;cursor:pointer;" onClick="limparForm();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                                <acesso:controlHiddenItem nomeIdentificador="cli_simlock_pesquisar">
                                                    <img id="gerar_arquivo" src="/FrontOfficeWeb/resources/images/bt_gerararq_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gerararq_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gerararq_over.gif'" onClick="download();return false;" style="border:none;cursor:pointer;"/>
                                                </acesso:controlHiddenItem>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="3"><span id="texto"></span></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top" width="100%" style="padding:5 0 0 0">
                                    <span style="width:365px;">
                                        <img id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="top.frameApp.location.href='/FrontOfficeWeb/voltar.do';return false;" style="border:none;cursor:pointer;"/>
                                    </span>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <iframe name="ifrmDownload" id="ifrmDownload" style="visibility:hidden;" width="0" height="0" onreadystatechange="if(top.frameApp.dvAnimarAguarde!=null)top.frameApp.stopAnimation();"></iframe>
        </html:form>
        </vivo:quadro>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>

