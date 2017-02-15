<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form"		        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastrarMigracaoForm" />
<bean:define id="listaMigracaoVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaMigracaoVO.migracaoVOArray"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="/FrontOfficeWeb/resources/css/calendar.css">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/rfidelizacao.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/fidelizacao-migracao.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
        <script language="javascript">
            <logic:notEmpty name="error">
                alert('<bean:write name="error"/>');
            </logic:notEmpty>

            function checaTelefonePre(obj){
                valor = obj.value;
                if(valor != oldValor || oldObj != obj){
                    for(i=0;i<valor.length;i++){
                        if(!inteiro.test(valor.charAt(i))){
                            valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                            i = -1;
                        }
                    }
                    if(valor.length < 1){
                        valor = "";
                    }else if(valor.length < 2){
                        valor = "(" + <bean:write name="ddd"/> + ")" + valor;
                    }else if(valor.length < 3){
                        //valor = "(" + <bean:write name="ddd"/>;
                        valor = "(" + <bean:write name="ddd"/> + ")";
                    }else if(valor.length < 7){
                        valor = "(" + <bean:write name="ddd"/> + ")" + valor.substring(2,6);
                    }else{
                        valor = "(" + <bean:write name="ddd"/> + ")" + valor.substring(2,6) + "-" + valor.substring(6,10);
                    }
                    obj.value = valor;
                    oldValor = valor;
                    oldObj = obj;
                }
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
                liberaData();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_mpre_verpagina">
   <div style="width:764px;height:331px;overflow:auto;margin-bottom:1px solid #adadad;">
   <jsp:include page="informacoesCliente.jsp"/>

    <vivo:quadro id="qdMain" height="248" width="760" label="Migração Pré-Pago" scroll="no">

    <form id="frmSelecao" name="frmSelecao" method="post">
		<input type="hidden" name="CODIGO" value="">
    </form>
    <form action="reterMigracao.do" name="cadastrarMigracaoForm" method="post" style="margin:0px;">
        <html:hidden property="validade" name="Form" styleClass="textfield"/>
        <html:hidden property="idTipoEncerramento" name="Form" value="3"/>
        <html:hidden property="tipoEncerramento" name="Form" value="3"/>
        <table width="100%" height="130">
            <tr>
                <td>&nbsp;</td>
            </tr>
    		<tr>
                <td>Migrações Cadastrados:</td>
                <td>
                   <html:select name="Form" property="migracaoSelecionada" style="width:200px" styleClass="SELECT" size="1"  onmouseover="ativarToolTip(this);">
    					<option value="0">Selecione</option>
                        <html:options collection="listaMigracaoVO" property="idMigracao" labelProperty="descricao" />
                   </html:select>
                </td>
            </tr>
    		<tr>
    			<td>Data Inicial:</td>
    			<td>
                     <html:text property="dtInicio" name="Form" readonly="true" styleClass="textfield" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');"/>
                     <img src="/FrontOfficeWeb/resources/images/bt_calcular_nrml.gif"
                     	  onClick="carregarData();return false;" border="0" formSubmit="false" />
                </td>
    		</tr>
    		<tr>
                <td>Validade do Crédito:</td>
    			<td><html:text property="dtFim" name="Form" readonly="true" styleClass="textfield" size="10"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;visibility:hidden" title="Calendário" onclick="return showCalendar('dtFim', '%d/%m/%Y');" id="calendar2"></td>
            </tr>
            <tr>
                <td width="20%">Número Atual:</td>
                <td>
    				<html:text property="nrAtual" name="Form" readonly="true" styleClass="textfield" maxlength="14" size="20" disabled="true" onkeyup="maskPhoneNumberObj(this)"/>
                </td>
            </tr>
            <tr>
                <td>Novo Número Pré-Pago:</td>
                <td>
    	            <html:text property="nrNovoPre" name="Form" styleClass="textfield" maxlength="14" size="20"  onkeyup="maskPhoneNumberObj(this)" value='<%="("+request.getAttribute("ddd").toString()+")"%>'/>
                </td>
            </tr>
            <tr>
                <td>Saldo Pré Pago:</td>
                <td>
    				<html:text property="disValor" name="Form" disabled="true" styleClass="textfield" size="10" onkeypress="checaReal(this)"/>
    				<html:hidden property="valor" name="Form"/>
                </td>
            </tr>
            <acesso:controlHiddenItem nomeIdentificador="fid_mpre_excecao">
            <tr>
                <td><b>Oferta de Exceção:</b></td>
                <td>
    				<html:checkbox name="Form" style="border:none;background-color:#E3ECF4;" property="inExcecao" value="1" onclick="liberaData(this);"/>
                </td>
            </tr>
            </acesso:controlHiddenItem>
        </table>

        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="05"></div>
    
        <table width="100%">
            <tr>
                <td>
                    <img class="button"
                    	 onmouseup="document.forms[1].action=documentvoltarOfertas.do;document.forms[1].submit()"
                    	 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                    	 border="0"
                    	 onClick="parent.parent.mostrar_div();"/>
                </td>
                <td colspan="6" align="center">
                <acesso:controlHiddenItem nomeIdentificador="fid_mpre_manualeletronico">
                    <img onmouseup="window.open('http://intranet.vivo-sp.com.br/manual/retencao/index.htm');"
                    	 src="/FrontOfficeWeb/resources/images/bt_manelet_nrml.gif" border="0" />
                </acesso:controlHiddenItem>
                <acesso:controlHiddenItem nomeIdentificador="fid_mpre_agendar">
                    <img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_agendar_over.gif'" style="border:none;cursor:hand;" border="0" onClick="agendar();">
                </acesso:controlHiddenItem>
                <acesso:controlHiddenItem nomeIdentificador="fid_mpre_vaipensar">
                    <img src="/FrontOfficeWeb/resources/images/bt_vaipensar_nrml.gif"
                    	 border="0"
                    	 onClick="cancela('vaipensar'); return false" />
                </acesso:controlHiddenItem>
                </td>
                <td align="right">
                <acesso:controlHiddenItem nomeIdentificador="fid_mpre_reter">
                    <img onClick="reter()"  src="/FrontOfficeWeb/resources/images/bt_reter_nrml.gif" border="0" style="border:none;cursor:hand;" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_reter_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_reter_over.gif'">
                </acesso:controlHiddenItem>
                </td>
            </tr>
    		<html:select name="Form" property="idValidadeSelecionada" size="1" style="width=0px;height=0px;visibility:hidden">
    			<option value="0">Selecione</option>
    			<html:options collection="listaMigracaoVO" property="idMigracao" labelProperty="validade"/>
    		</html:select>
    		<html:select name="Form" property="idValorSelecionado" size="1" style="width=0px;height=0px;visibility:hidden">
    			<option value="0">Selecione</option>
    			<html:options collection="listaMigracaoVO" property="idMigracao" labelProperty="vlBonus"/>
    		</html:select>
        </table>
    </form>

    <vivo:quadroFlutuante id="dvMsgPontos" idIframe="ifrmMensagem" height="100" width="300" spacesTop="50" spacesLeft="50" display="none" url="<%=request.getContextPath()%>" label=""/>

    </vivo:quadro>
    </div>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
