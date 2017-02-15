<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="Form"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="suspensaoTemporariaForm" />
<bean:define id="diasSuspTemp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="diasSuspensaoTemp" />
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/fidelizacao-susptemp.js"></script>
        <script>
            function carregarData(){

                var dia  = new Date();
                var mes  = dia.getMonth() + 1;
                var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

                if(document.forms[0].elements["data"].value == ""){
                    alert("Favor preencher data inicial");
                }else if(validaDataFinal(hoje, document.forms[0].elements["data"].value)){
                    document.forms[0].dtInicio.value = document.forms[0].data.value
                    somaDiasData(document.forms[0].dtInicio,document.forms[0].dtFim,<bean:write name="diasSuspTemp"/>)
                }else{
                     alert("Data menor que a data atual, favor corrigir!");
                }
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_stmpo_verpagina">
    <div style="width:764px;height:331px;overflow:auto;margin-bottom:1px solid #adadad;">
    <jsp:include page="informacoesCliente.jsp"/>
    <vivo:quadro id="qdMain" height="245" width="760" label="Suspensão Temporária" scroll="no">
    <form  action="reterSuspensaoTemporaria.do" method="post">
    <html:hidden property="tipoEncerramento" name="Form" value="3"/>
<acesso:controlInitEnv/>
    <table width="100%">
        <tr>
            <td width="20%">Data:</td>
            <td>
                <html:text property="data" name="Form" readonly="true" styleClass="textfield" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('data', '%d/%m/%Y');">
                <acesso:controlHiddenItem nomeIdentificador="fid_stmpo_calc">
                <img src="/FrontOfficeWeb/resources/images/bt_calcdtfinal_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_calcdtfinal_over.gif" onClick="carregarData(); return false" style="border:none;" />
                </acesso:controlHiddenItem>
            </td>
        </tr>
        <acesso:controlHiddenItem nomeIdentificador="fid_stmpo_excecao">
        <tr>
            <td><b>Oferta de Exceção:</b></td>
<%--             <td><netui : checkBox dataSource="inExcecao" onClick="checaExcecao(this)" styleClass="radio"/></td> --%>
            <td><html:checkbox name="Form" property="inExcecao" onClick="checaExcecao(this)" styleClass="radio"/></td>
        </tr>
        </acesso:controlHiddenItem>
    </table>

    <table width="100%">
        <tr><td></td></tr>
        <tr align="left">
            <td align="center">
                <font face="verdana"><b> Cliente retido com Suspensão Temporária por <bean:write name="diasSuspTemp"/> dias ,de</b>
                <html:text property="dtInicio" readonly="true" name="Form" styleClass="textfield" size="10"/>
                <font face="verdana"><b>até</b><html:text property="dtFim" readonly="true" name="Form" onkeyup="checaData(this)" styleClass="textfield" size="10"/>
                <font face="verdana"><b>.</b>
                <br>
                <font face="verdana"><b>O mesmo está ciente que após este prazo a linha é automaticamente reativada ,</b>
                <br>
                <font face="verdana"><b>sendo necessário um novo contato caso este não seja seu interesse.</b>
            </td>
        </tr>
    </table>

    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="100"></div>

    <table width="100%">
        <tr><td>&nbsp;</td></tr>
        <tr>
            <td>
                <img onmouseup="document.forms[0].action='sair.do';document.forms[0].submit();"
                	 src="/FrontOfficeWeb/resources/images/bt_anterior_nrml.gif"
                	 border="0"
                	 style="display:none"
                	 onClick="parent.parent.mostrar_div();" />
            </td>
            <td align="center">
                <img onmouseup="document.forms[0].action='voltarOfertas.do';document.forms[0].submit();"
                	 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                	 class="button"
                	 onClick="parent.parent.mostrar_div();" />
                <acesso:controlHiddenItem nomeIdentificador="fid_stmpo_manualeletronico">
					<a href="http://intranet.vivo-sp.com.br/manual/retencao/index.htm" target="_blank">
						<img src="/FrontOfficeWeb/resources/images/bt_manelet_nrml.gif"
							 class="button">
					</a>
				</acesso:controlHiddenItem>
                
                <acesso:controlHiddenItem nomeIdentificador="fid_stmpo_agendar">
					<img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_agendar_over.gif'" style="border:none;cursor:hand;" border="0" onClick="agendar();">
				</acesso:controlHiddenItem>
                
                <acesso:controlHiddenItem nomeIdentificador="fid_stmpo_vaipensar">
					<img src="/FrontOfficeWeb/resources/images/bt_vaipensar_nrml.gif"
						 class="button"
						 onClick="cancela('vaipensar'); return false" />
				</acesso:controlHiddenItem>
            </td>
            <td align="right">
            <acesso:controlHiddenItem nomeIdentificador="fid_stmpo_reter">
               <img onClick="reter()"  src="/FrontOfficeWeb/resources/images/bt_reter_nrml.gif" border="0" style="border:none;cursor:hand;" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_reter_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_reter_over.gif'">
			</acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
    </form>

    <form id="frmSelecao" name="frmSelecao" method="post">
		<input type="hidden" name="CODIGO" value="">
    </form>

    </vivo:quadro>
       </div>
</acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
