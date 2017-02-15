<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="uploadForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Upload de Arquivos - Processamento"/>
    <netui-template:setAttribute name="modulo" value="Administração"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/validacao.js"></script>
        <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script type="text/javascript" language="javascript">
            function download(idFunc, nmFileBad, nrPos){
                var f = document.forms['formDown'];
                f.sgFuncionalidade.value = idFunc;
                f.nrPos.value = nrPos;
                f.nmFileBad.value = nmFileBad;
                if (idFunc == "ANTFO") {
                    f.action = "downloadAnatel.do";
                }
                f.submit();
            }

            function downloadAnatel(nrPos){
                var f = document.forms['formDown'];
                f.nrPos.value = nrPos;
                f.action = "downloadAnatel.do";
                f.submit();
            }


            function downloadLogErro(nrPos){
                var f = document.forms['formDown'];
                f.nrPos.value = nrPos;
                f.action = "downloadLogErro.do";
                f.submit();
            }


            function consultar(){
                var f = document.forms['formCons'];
                if(validar()){
                    f.submit();
                }
            }

            function showFiltro(tipo){
                    if(tipo=="ANTFO"){
                        $('divDatas').show();
                    }else{
                        $('divDatas').hide();
                    }
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
            function validar() {
                var f = document.forms['formCons'];
                if(f.sgFuncionalidade.value.length <=0){
                    alert('Favor selecionar uma funcionalidade');
                    return false;
                }

                var  tp = f.sgFuncionalidade.value;
                if (tp =="ANTFO") {
                    if ($F('dtIni').strip() == "") {
                        alert("Selecione a Data Inicial");
                        return false;
                    }
                    if ($F('dtFim').strip() == "" ) {
                        alert("Selecione a Data Final.");
                        return false;
                    }
                    if (!valida1mes($('dtIni'),$('dtFim'))) {
                        alert("Intervalo de datas maior que 30 dias.");
                        return false;
                    }

                    if ($F('dtIni').strip() != "" && $F('dtFim').strip() != "" && !validaDataFinal($F('dtIni'), $F('dtFim'))) {
                        alert("Data Inicial maior que Data Final.");
                        return false;
                    }
                }

                return true;
            }

            function valida1mes(objInicial,objFinal){
                objSomaDias = document.all["somaDias"];
                somaDiasData(objInicial,objSomaDias,30);
                resposta = validaDataFinal(objFinal.value,objSomaDias.value);
                return resposta;
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="468" width="790" label="Parametrização" operacoes="Consulta de Processamento" scroll="no">
            <table width="760" border="0" cellspacing="0" cellpadding="0" align="center" style="margin-top:10px;">
                <tr>
                    <td valign="top" align="left">
                        <html:form styleId="formCons" method="post" enctype="multipart/form-data" onsubmit="return false;" action="/admsistemas/arquivos/consultaProcessamento.do">
                        <input type="hidden" name="somaDias">
                        <table width="760" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-bottom:10px;">
                            <tr>
                                <td style="padding:5px;" align="right" width="100">Funcionalidade:</td>
                                <td style="padding-left:9px;" align="left" width="250">
                                    <html:select name="Form" property="sgFuncionalidade" style="width:200px;" onchange="showFiltro(this.value);">
                                        <option value="">Selecione</option>
                                        <option value="BLIND" <logic:equal name="Form" property="sgFuncionalidade" value="BLIND">selected</logic:equal>>Blindagem de Clientes</option>
                                        <option value="CNSLT" <logic:equal name="Form" property="sgFuncionalidade" value="CNSLT">selected</logic:equal>>Carga de Consultores</option>
                                        <option value="GSTPJ" <logic:equal name="Form" property="sgFuncionalidade" value="GSTPJ">selected</logic:equal>>Carga de Gestores PJ</option>
                                        <option value="SOLAP" <logic:equal name="Form" property="sgFuncionalidade" value="SOLAP">selected</logic:equal>>Solicitar Aparelhos</option>
                                        <option value="PIMEI" <logic:equal name="Form" property="sgFuncionalidade" value="PIMEI">selected</logic:equal>>Fechamento de Processos IMEI</option>
                                        <option value="MSBLK" <logic:equal name="Form" property="sgFuncionalidade" value="MSBLK">selected</logic:equal>>Carga de Mensagens de Blacklist de Crédito</option>
                                    	<option value="BLCRD" <logic:equal name="Form" property="sgFuncionalidade" value="BLCRD">selected</logic:equal>>Carga Blacklist de Crédito</option>
                                    	<option value="BLDDS" <logic:equal name="Form" property="sgFuncionalidade" value="BLDDS">selected</logic:equal>>Carga de NRC em Blacklist de Dados</option>
                                   		<option value="CCLTR" <logic:equal name="Form" property="sgFuncionalidade" value="CCLTR">selected</logic:equal>>Carga Cluster</option>                                    
                                    </html:select>
                                </td>
                                 <td align="left">
                                    <logic:equal name="Form" property="sgFuncionalidade" value="ANTFO">
                                        <div id="divDatas" style="display:block;">
                                    </logic:equal>
                                    <logic:notEqual name="Form" property="sgFuncionalidade" value="ANTFO">
                                        <div id="divDatas" style="display:none;">
                                    </logic:notEqual>
                                        Período&nbsp;<html:text property="dtInicial" name="Form" styleId="dtIni" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                                        <img id="imgCalendDtAbIni" src="/FrontOfficeWeb/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicial', '%d/%m/%Y');">&nbsp;Até&nbsp;
                                        <html:text property="dtFinal" name="Form" size="10" styleId="dtFim" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                                        <img id="imgCalendDtAbFim" src="/FrontOfficeWeb/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFinal', '%d/%m/%Y');">
                                    </div>

                                </td>
                                <td align="left" style="padding-right:5px;">
                                    <img src="/FrontOfficeWeb/resources/images/bt_consultar_nrml.gif" onclick="consultar();" style="border:none;cursor:pointer;"/>
                                </td>
                            </tr>
                        </table>
                       </html:form>

                    </td>
                </tr>
                <tr>
                    <td valign="top" height="320">
                        <logic:present name="Form" property="arquivoProcessamentoVO">

                            <logic:equal name="Form" property="sgFuncionalidade" value="GSTPJ">

                                <vivo:tbTable selectable="true" styleId="tbArquivosLote" layoutHeight="315" layoutWidth="760" tableWidth="760" sortable="true">
                                    <vivo:tbHeader>
                                        <vivo:tbHeaderColumn width="95%" type="String">Nome do Arquivo</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn width="5%" type="String"></vivo:tbHeaderColumn>
                                    </vivo:tbHeader>
                                    <logic:present name="Form" property="arquivoProcessamentoVO.itemArray">
                                    <vivo:tbRows>
                                        <logic:iterate id="it" name="Form" property="arquivoProcessamentoVO.itemArray" indexId="idx" type="br.com.vivo.fo.admsistemas.vo.ArquivoProcessamentoVODocument.ArquivoProcessamentoVO.Item">
                                            <%
                                            int lIndex = (it.getNmFileBad() == null || ConstantesCRM.SVAZIO.equals(it.getNmFileBad()))
                                                ? 0
                                                : it.getNmFileBad().lastIndexOf("/") + 1;
                                            String nmArquivo = it.getNmFileBad().substring(lIndex, it.getNmFileBad().length());
                                            %>
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><%=nmArquivo%></vivo:tbRowColumn>
                                                <vivo:tbRowColumn>
                                                    <img src="/FrontOfficeWeb/resources/images/bt_icon_download.gif" border="0" style="cursor:pointer;" onclick="download('<bean:write name="Form" property="sgFuncionalidade"/>','<bean:write name="it" property="nmFileBad"/>',<%=idx%>);">
                                                </vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </logic:iterate>
                                    </vivo:tbRows>
                                    </logic:present>
                                </vivo:tbTable>

                            </logic:equal>

                            <logic:equal name="Form" property="sgFuncionalidade" value="ANTFO">
                                 <vivo:tbTable selectable="true" styleId="tbArquivosLote" layoutHeight="310" layoutWidth="775" tableWidth="775" sortable="true">
                                    <vivo:tbHeader>
                                        <vivo:tbHeaderColumn width="15%" type="String">Data de Envio</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn width="25%" type="String">Arquivo</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn width="09%" type="String">Login</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn width="15%" type="String">Data/Hora Processamento</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn width="10%" type="String">Status Carga</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn width="9%" type="String" align="center">Registros Rejeitados</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn width="9%" type="String" align="center">Total de Registros</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn width="3%"  type="String" align="center">Rel. Upload</vivo:tbHeaderColumn>
                                        <vivo:tbHeaderColumn width="5%"  type="String" align="center">Arq. de Erro</vivo:tbHeaderColumn>
                                    </vivo:tbHeader>
                                    <logic:present name="Form" property="arquivoProcessamentoVO.itemArray">
                                    <vivo:tbRows>
                                        <logic:iterate id="it" name="Form" property="arquivoProcessamentoVO.itemArray" indexId="idx">
                                            <vivo:tbRow key="linha1">
                                                <vivo:tbRowColumn><bean:write name="it" property="dtEnvio"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="it" property="nmFileOriginal"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="it" property="dsLoginUsuario"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="it" property="dtProcessamento"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="it" property="dsStatusCarga"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="it" property="qtRegRejeitados"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn><bean:write name="it" property="qtRegTotal"/></vivo:tbRowColumn>
                                                <vivo:tbRowColumn>
                                                     <logic:notEqual name="it" property="idAtendimentoAnatelArquivo" value="0">
                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_download.gif" title="Relatório Upload" border="0" style="cursor:pointer;" onclick="downloadAnatel('<%=idx%>');">
                                                    </logic:notEqual>
                                                </vivo:tbRowColumn>
                                                <vivo:tbRowColumn>
                                                    <logic:notEqual name="it" property="idAtendimentoAnatelArquivo" value="0">
                                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_download.gif" title="Arquivos de Erro" border="0" style="cursor:pointer;" onclick="downloadLogErro('<%=idx%>');">
                                                    </logic:notEqual>
                                                </vivo:tbRowColumn>
                                            </vivo:tbRow>
                                        </logic:iterate>
                                    </vivo:tbRows>
                                    </logic:present>
                                </vivo:tbTable>


                            </logic:equal>

                            <logic:notEqual name="Form" property="sgFuncionalidade" value="ANTFO">
                                <logic:notEqual name="Form" property="sgFuncionalidade" value="GSTPJ">
                                    <vivo:tbTable selectable="true" styleId="tbArquivosLote" layoutHeight="315" layoutWidth="760" tableWidth="760" sortable="true">
                                        <vivo:tbHeader>
                                            <vivo:tbHeaderColumn width="15%" type="String">Data de Envio</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn width="20%" type="String">Login</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn width="15%" type="String">Data/Hora Processamento</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn width="25%" type="String">Status da Carga</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn width="10%" type="String">Registros Rejeitados</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn width="10%" type="String">Total de Registros</vivo:tbHeaderColumn>
                                            <vivo:tbHeaderColumn width="5%"  type="String"></vivo:tbHeaderColumn>
                                        </vivo:tbHeader>
                                        <logic:present name="Form" property="arquivoProcessamentoVO.itemArray">
                                        <vivo:tbRows>
                                            <logic:iterate id="it" name="Form" property="arquivoProcessamentoVO.itemArray" indexId="idx">
                                                <vivo:tbRow key="linha1">
                                                    <vivo:tbRowColumn><bean:write name="it" property="dtEnvio"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="it" property="dsLoginUsuario"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="it" property="dtProcessamento"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="it" property="dsStatusCarga"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="it" property="qtRegRejeitados"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn><bean:write name="it" property="qtRegTotal"/></vivo:tbRowColumn>
                                                    <vivo:tbRowColumn>
                                                        <logic:equal name="it" property="hasFileBad" value="1">
                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_download.gif" border="0" style="cursor:pointer;" onclick="download('<bean:write name="Form" property="sgFuncionalidade"/>','<bean:write name="it" property="nmFileBad"/>',<%=idx%>);">
                                                        </logic:equal>
                                                    </vivo:tbRowColumn>
                                                </vivo:tbRow>
                                            </logic:iterate>
                                        </vivo:tbRows>
                                        </logic:present>
                                    </vivo:tbTable>
                                </logic:notEqual>
                            </logic:notEqual>

                            <div id="dvLegendas" style="margin-top:3px">
                                <table style="background-color:#fff;border:1px solid #adadad;" width="777" align="left">
                                    <tr>
                                        <td valign="middle" style="padding-left:10px;padding-right:15px;" width="10%" nowrap><b>Legendas:</b></td>
                                        <td valign="middle" align="left" nowrap style="padding-right:15px;" width="90%"><img align="middle" src="<%=request.getContextPath()%>/resources/images/bt_icon_download.gif" />&nbsp;Download</td>
                                    </tr>
                                </table>
                            </div>
                            <html:form styleId="formDown" method="post" enctype="multipart/form-data" onsubmit="return false;" action="/admsistemas/arquivos/download.do" target="frmDownload" style="height:1px;margin:0;padding:0">
                                <input type="hidden" name="sgFuncionalidade" value="">
                                <input type="hidden" name="idProcessamento" value="">
                                <input type="hidden" name="nmFileBad" value="">
                                <input type="hidden" name="nrPos" value="">
                            </html:form>
                            <iframe src="about:blank" id="frmDownload" name="frmDownload" style="display:none;" width="1" height="1"></iframe>
                        </logic:present>
                    <td>
                </tr>
                <tr>
                    <td align="left">
                        <input type="image" id="btVoltar"
                             value="Voltar"
                             src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                             onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'"
                             style="border:none;margin:10px 4px;"
                             onmouseup="top.location.href='/FrontOfficeWeb/voltar.do'"/>
                    </td>
                </tr>
            </table>
        </vivo:sessao>
        <vivo:alert atributo="msgStatus" scope="request"/>
    </netui-template:section>
</netui-template:template>