<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<acesso:controlInitEnv/>

<bean:define id="Form"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterLoteFormBean"/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:setAttribute name="title" value="Perfis"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script language="javascript" type="text/javascript">
            var objSomaDias;

            function gerarArquivo() {
                f = document.forms[0];
                if (f.dtInicial.value == "" || f.dtFinal.value == "") {
                    alert("Por favor, digite o periodo para geração de arquivo.");
                } else if (calcularDiasEntreDatas(f.dtInicial.value, f.dtFinal.value) < 0 ) {
                    alert("Data final deve ser maior do que a data inicial.");
                    return false;
                } else if (calcularDiasEntreDatas(f.dtInicial.value, f.dtFinal.value) > 30 ) {
                    alert("Período de datas não pode ser superior a 30 dias.");
                    return false;
                } else {
                    f.action = "gerarListaArquivos.do";
                    f.submit();
                }
            }

            function downloadArquivo() {
                var nmArquivo = "";
                f = document.forms[0];
                var idxRadio = f.idxArquivo;
                if(idxRadio.length==undefined || idxRadio.length==null){
                    if(idxRadio.checked == true){
                        nmArquivo = idxRadio.value;
                    }
                }else{
                    for (i = 0; i < idxRadio.length; i++) {
                        if (idxRadio[i].checked == true){
                            nmArquivo = idxRadio[i].value;
                            break;
                        }
                    }
                }
                if(nmArquivo=="" || nmArquivo.length==0){
                    alert("Selecione um arquivo para download.");
                }else{
                    f.target="ifrmDownload";
                    f.method="GET";
                    f.submit();
                }
            }

            function validaDataOnBlur(data) {
                if(data.value == '') {
                    return false;
                } else if(!validaData(data.value)) {
                    data.value = '';
                    data.focus();
                    alert("Data inválida");
                }
            }

            function calcularDiasEntreDatas(dataInicial, dataFinal) {
                var diaInicial = parseFloat(dataInicial.substring(0,2));
                var mesInicial = parseFloat(dataInicial.substring(3,5));
                var anoInicial = parseFloat(dataInicial.substring(6,10));

                var diaFinal = parseFloat(dataFinal.substring(0,2));
                var mesFinal = parseFloat(dataFinal.substring(3,5));
                var anoFinal = parseFloat(dataFinal.substring(6,10));

                var dtInicial = new Date(anoInicial, mesInicial, diaInicial);
                var dtFinal   = new Date(anoFinal, mesFinal, diaFinal);

                dif = dtFinal.getTime()-dtInicial.getTime();
                dif /= 86400000;
                dif = parseInt(dif);
                return dif;
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <acesso:controlHiddenItem nomeIdentificador="usu_manlote_dl_verpagina">
        <iframe name="ifrmDownload" id="ifrmDownload" style="visibility: hidden;" width="0" height="0"></iframe>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="460" width="790" label="Manutenção de Lote" operacoes="Download">
            <form action="downloadArquivo.do" enctype="multipart/form-data" method="post" onSubmit="return false;">
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
                <table width="760" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                    <tr>
                        <td style="padding:5px;" width="130" nowrap>
                            <b>Per&iacute;odo da consulta</b>
                        </td>
                        <td width="100%" style="padding:5px;">
                            De
                            <html:text  name="Form"
                                        property="dtInicial"
                                        size="10"
                                        maxlength="10"
                                        tabindex="1"
                                        onblur="validaDataOnBlur(this); return false;"
                                        onkeyup="Formatar(this.value, this.form.name, this.name, 'data');"
                                        onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                            <img id="imgCalendDtAbIni" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicial', '%d/%m/%Y');">
                            &nbsp;At&eacute;&nbsp;
                            <html:text  name="Form"
                                        property="dtFinal"
                                        size="10"
                                        maxlength="10"
                                        tabindex="2"
                                        onblur="validaDataOnBlur(this); return false;"
                                        onkeyup="Formatar(this.value, this.form.name, this.name, 'data');"
                                        onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                            <img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFinal', '%d/%m/%Y');">
                            &nbsp;&nbsp;
                            (M&aacute;ximo de 30 dias)
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right" style="padding-right:5px;">
                            <acesso:controlHiddenItem nomeIdentificador="usu_manlote_gerarquivos">
                            <img src="/FrontOfficeWeb/resources/images/bt_gerararq_nrml.gif"
                                 rolloverImage="/FrontOfficeWeb/resources/images/bt_gerararq_over.gif"
                                 onMouseUp="gerarArquivo(); return false;"
                                 style="border:none" />
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
                <logic:notEmpty name="Form" property="loteStatusUsuarioVO">
                <table width="760" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                        <td>
                            <vivo:tbTable selectable="true" styleId="tbArquivosLote" layoutHeight="200" layoutWidth="750" tableWidth="750">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn width="15" type="String">&nbsp;</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn width="600" type="String">Arquivo</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn width="135" type="String">Status</vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <vivo:tbRows>
                                    <logic:iterate id="iteratorArquivosLote" name="Form" property="loteStatusUsuarioVO">
                                    <vivo:tbRow key="linha1">
                                        <vivo:tbRowColumn>
                                            <logic:notEqual name="iteratorArquivosLote" property="dsStatus" value="Processamento">
                                            <input id="idxArquivo" type="radio" value="<bean:write name="iteratorArquivosLote" property="nmArquivoOriginal" />" name="idxArquivo" style="border:none;background:none;"/>
                                            </logic:notEqual>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <logic:equal name="iteratorArquivosLote" property="dsStatus" value="Processamento">
                                                <span style="color:#ff0000"><bean:write name="iteratorArquivosLote" property="nmArquivo" /></span>
                                            </logic:equal>
                                            <logic:notEqual name="iteratorArquivosLote" property="dsStatus" value="Processamento">
                                                <bean:write name="iteratorArquivosLote" property="nmArquivo" />
                                            </logic:notEqual>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <logic:notEqual name="iteratorArquivosLote" property="dsStatus" value="Processamento">
                                            <bean:write name="iteratorArquivosLote" property="dsStatus" />
                                            </logic:notEqual>
                                            <logic:equal name="iteratorArquivosLote" property="dsStatus" value="Processamento">
                                                <span style="color:#ff0000"><bean:write name="iteratorArquivosLote" property="dsStatus" /></span>
                                            </logic:equal>
                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                            </vivo:tbTable>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">
                            <acesso:controlHiddenItem nomeIdentificador="usu_manlote_download">
                            <img src="/FrontOfficeWeb/resources/images/bt_download_nrml.gif"
                                 rolloverImage="/FrontOfficeWeb/resources/images/bt_download_over.gif"
                                 onMouseUp="downloadArquivo(); return false;"
                                 style="border:none;margin:10px 0 0 0;" />
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                </logic:notEmpty>
                <vivo:alert atributo="msgStatus" scope="request" />
                <script language="javascript" for="window" event="onload">
                    oculta_div();
                </script>
            </form>
        </vivo:sessao>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>