<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Blindagem de Clientes"/>
    <netui-template:setAttribute name="modulo" value="Relatórios"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript" type="text/javascript">
            function valida1mes(objInicial,objFinal){
                objSomaDias = document.forms[0].somaDias;
                somaDiasData(objInicial,objSomaDias,30);
                resposta = validaDataFinal(objFinal.value,objSomaDias.value);
                return resposta;
            }

            function validaForm(f){
                if($F('dtIniBlind').strip().empty()){
                    alert("Favor informar um período corretamente");
                    return false;
                }
                if($F('dtFimBlind').strip().empty()){
                    alert("Favor informar um período corretamente");
                    return false;
                }
                if(!validaDataFinal(f.dtIniBlind.value, f.dtFimBlind.value)){
                    alert("Data Inicial maior que Data Final!");
                    return false;
                }
                if( !valida1mes(f.dtIniBlind, f.dtFimBlind) ){
                    alert("o período informado é superior a 30 dias");
                    return false;
                }
                return true;
            }

            function pesquisar(){
                var f = document.forms[0];
                if(validaForm(f)){
                    f.submit();
                }
            }

            function limpar(){
                self.location.href = 'begin.do';
            }
        </script>
        <script language="javascript" for="window" event="onload">
            <logic:notEmpty name="msgStatus" scope="request">
            alert('<bean:write name="msgStatus" scope="request"/>');
            </logic:notEmpty>
            oculta_div();
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="460" width="790" label="Relatório" operacoes="Blindagem de Clientes">
            <html:form action="/admsistemas/relatorio/listarDados.do" enctype="multipart/form-data" method="post" onsubmit="return false;">
	            <input type="hidden" id="somaDias" name="somaDias" />
	            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
	            <table cellpadding="3" cellspacing="2" width="100%">
	                <tr>
	                    <td align="right" width="10%">*&nbsp;Data Inicial:</td>
	                    <td align="left" width="20%">
	                        <html:text styleId="dtIniBlind" name="Form" property="dtIniBlind" size="11" maxlength="10" onblur="validaData(this);" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>&nbsp;
	                        <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtIniBlind', '%d/%m/%Y');">&nbsp;
	                    </td>
	                    <td align="right" width="10%">*&nbsp;Data Final:</td>
	                    <td align="left" width="20%">
	                        <html:text styleId="dtFimBlind" name="Form" property="dtFimBlind" size="11" maxlength="10" onblur="validaData(this);" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>&nbsp;
	                        <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFimBlind', '%d/%m/%Y');">&nbsp;
	                    </td>
	                    <td><img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisar();" style="cursor:pointer;"></td>
	                    <td><img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onclick="limpar();" style="cursor:pointer;"></td>
	                    <td>&nbsp;</td>
	                </tr>
	            </table>

	            <vivo:tbTable selectable="true" styleId="tbRelBlindagem" layoutHeight="300" layoutWidth="740" tableWidth="740">
	                <vivo:tbHeader>
	                    <vivo:tbHeaderColumn width="50%" type="String">Data</vivo:tbHeaderColumn>
	                    <vivo:tbHeaderColumn width="25%" type="String">Qtde de clientes Fidelizados</vivo:tbHeaderColumn>
	                    <vivo:tbHeaderColumn width="25%" type="String">Qtde de clientes Inibidos</vivo:tbHeaderColumn>
	                </vivo:tbHeader>
	                <vivo:tbRows>
	                    <logic:present name="Form" property="relatorioBlindagemVO.linhasArray[0]">
	                    <logic:iterate id="it" name="Form" property="relatorioBlindagemVO.linhasArray" indexId="indx">
	                    <vivo:tbRow key="linha1">
	                        <vivo:tbRowColumn><div align="left"  style="padding-left:20px;"><bean:write name="it" property="dtReferencia"/></div></vivo:tbRowColumn>
	                        <vivo:tbRowColumn><div align="right" style="padding-rigth:10px;margin-rigth:10px;"><bean:write name="it" property="qtCliFid"/></div></vivo:tbRowColumn>
	                        <vivo:tbRowColumn><div align="right" style="padding-rigth:10px;margin-rigth:10px;"><bean:write name="it" property="qtCliBlq"/></div></vivo:tbRowColumn>
	                    </vivo:tbRow>
	                    </logic:iterate>
	                    <vivo:tbRow key="linha2">
	                        <vivo:tbRowColumn><div id="dvTotal"  align="right"><b>Totais</b></div></vivo:tbRowColumn>
	                        <vivo:tbRowColumn><div align="right" style="padding-rigth:10px;margin-rigth:10px;"><bean:write name="Form" property="relatorioBlindagemVO.qtTotalFid"/></div></vivo:tbRowColumn>
	                        <vivo:tbRowColumn><div align="right" style="padding-rigth:10px;margin-rigth:10px;"><bean:write name="Form" property="relatorioBlindagemVO.qtTotalBlq"/></div></vivo:tbRowColumn>
	                    </vivo:tbRow>
	                    </logic:present>
	                </vivo:tbRows>
	            </vivo:tbTable>
            </html:form>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>