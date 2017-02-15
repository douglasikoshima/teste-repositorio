<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm" type="cliente.CDevolvida.telaInicial.tratarCorrespTIController.FiltroForm"/>
<bean:define id="Status"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filtroForm.correspDevolvidaVO.statusCorrespondenciaArray"/>
<head>
    <link href="/FrontOfficeWeb/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script>
	    dia = new Date();
		mes = dia.getMonth() + 1;
		hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
		function valida1mes(objInicial,objFinal){
			objSomaDias = document.forms[0].somaDias;
			somaDiasData(objInicial,objSomaDias,31);
			resposta = validaDataFinal(objFinal.value,objSomaDias.value);
			return resposta;
		}

        function validaPesquisa( parametro ){                                                                                      
            if (parametro=="pesquisa"){
    			if(document.forms[0].elements['filtroCorrespondencia.dtRegistroIni'].value != "" && document.forms[0].elements['filtroCorrespondencia.dtRegistroFim'].value == ""){
    				alert('Favor selecionar uma Data de Registro Final')
    			}else if(document.forms[0].elements['filtroCorrespondencia.dtRegistroIni'].value == "" && document.forms[0].elements['filtroCorrespondencia.dtRegistroFim'].value != ""){
    				alert('Favor selecionar uma Data de Registro Final')
    			}else if(document.forms[0].elements['filtroCorrespondencia.dtRegistroIni'].value != "" && document.forms[0].elements['filtroCorrespondencia.dtRegistroFim'].value != "" && !validaDataFinal(document.forms[0].elements['filtroCorrespondencia.dtRegistroIni'].value,document.forms[0].elements['filtroCorrespondencia.dtRegistroFim'].value)){
    				alert('Data de Registro Inicial maior que Data de Registro Final, favor corrigir!')
    			}else if(document.forms[0].elements['filtroCorrespondencia.dtRegistroIni'].value != "" && document.forms[0].elements['filtroCorrespondencia.dtRegistroFim'].value != "" && !valida1mes(document.forms[0].elements['filtroCorrespondencia.dtRegistroIni'],document.forms[0].elements['filtroCorrespondencia.dtRegistroFim'])){
    				alert('Data de Registro Final deve ser no maximo\n31 dias maior do que Data Registro Inicial,\nfavor corrigir!')
    			}else if(document.forms[0].elements['filtroCorrespondencia.dtRegistroIni'].value != "" && document.forms[0].elements['filtroCorrespondencia.dtRegistroFim'].value != "" && !validaDataFinal(document.forms[0].elements['filtroCorrespondencia.dtRegistroFim'].value,hoje)){
    			    alert('Data de Registro Final maior que o dia de hoje, favor corrigir!');
				}else{
	                document.forms[0].action="pesquisar.do?destino="+parametro;	                
                    parent.mostrar_div();
                    document.forms[0].submit();
				}
            }else{
                if (document.forms[0].id.value==""){
                    alert('Execute uma pesquisa e selecione um item válido para tratar!');
                
                }else{
                    //parent.dvAtendCorresp.style.display = '';
                    //document.forms[0].target = "ifrmAtendCorresp";
                    if (document.forms[0].id.length){
                        document.forms[0].action="pesquisar.do?destino=altera"+"&idArray="+parametro+"&idCorresp="+document.forms[0].id[parametro].value;
                    }else{
                        document.forms[0].action="pesquisar.do?destino=altera"+"&idArray="+parametro+"&idCorresp="+document.forms[0].id.value;
                    }                    
                    parent.mostrar_div();
                    document.forms[0].submit();
                }
            }
        }

        function fechar(){
            window.top.frameApp.ti_frameAbas.location.href = '/FrontOfficeWeb/cliente/CDevolvida/telaInicial/begin.do?start=true';
        }

        function limparPesquisa(){
            document.forms[0].action="limparConsulta.do";
            document.forms[0].submit();
        }
    </script>    
</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="cli_fcti_verpagina">
	<form action="pesquisar.do" method="POST" name="filtroForm">
    <vivo:sessao id="correspDevolvida" width="765" height="350" label="Correspondência Devolvida" operacoes="Pesquisa" scroll="no">
    <table width="600" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td>
                <html:hidden name="Form" property="idPessoa"/>
                <html:hidden name="Form" property="idTipoRelacionamento"/>
								<input type="Hidden" name="somaDias">
                <table  width="755" border="0" cellspacing="0" cellpadding="0" class="tbl_bgGray">
                    <tr>
                        <td nowrap style="text-indent:6px;">Data de Registro:</td>
                        <td nowrap>
                            <html:text name="Form" property="filtroCorrespondencia.dtRegistroIni" styleId="dtRegistroIni" size="10" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('filtroCorrespondencia.dtRegistroIni', '%d/%m/%Y');"> at&eacute;
                            <html:text name="Form" property="filtroCorrespondencia.dtRegistroFim" styleId="dtRegistroFim" size="10" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('filtroCorrespondencia.dtRegistroFim', '%d/%m/%Y');">
                        </td>
                        <td nowrap style="text-indent:6px;"><b>Status:</b></td>
                        <td>
                            <html:select name="Form" styleId="comboStatus" property="filtroCorrespondencia.idStatus" style="width:200px">
                                <html:option value="">TODOS</html:option>                             
                                <html:options property="idStatus" collection="Status" labelProperty="dsStatus"/>
                            </html:select>
                        </td>
                        
                        <td nowrap> &nbsp; </td>
                        <td nowrap align="right">&nbsp;</td>
                        <td align="right" colspan="2">
                        <acesso:controlHiddenItem nomeIdentificador="cli_fcti_pesq"><img vspace="5" style="border:none;" onClick="validaPesquisa('pesquisa'); return false" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif"/></acesso:controlHiddenItem>&nbsp </td>
                        <td nowrap  align="right">&nbsp;</td>
                        <td align="right">
                        <img onClick="limparPesquisa(); return false" vspace="5" style="border:0px" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif"/>&nbsp </td>                         
                    </tr>
                </table>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>                
               <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            </td>                
        </tr>                
        <tr>
            <td valign="top">                    
                <vivo:tbTable onRowClick="" selectable="true" layoutWidth="735" layoutHeight="250" tableWidth="735" styleId="tableTitle" sortable="true" >
                    <vivo:tbHeader>
                        <vivo:tbHeaderColumn align="center" width="27%" type="string">Nome</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="15%" type="string">Linha</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="15%" type="string">Conta</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="20%" type="string">Tipo Correspondência</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="18%" type="string">Motivo Devolução</vivo:tbHeaderColumn>
                        <vivo:tbHeaderColumn align="center" width="5%" type="string">&nbsp</vivo:tbHeaderColumn>
                    </vivo:tbHeader>
                    <vivo:tbRows scroll="false">
                        <logic:iterate name="Form" property="correspDevolvidaVO.listaCorrespDevolvidaArray" indexId="idx" id="listaCorrespDevolvidaArray">
                            <vivo:tbRow key="Linha">
                                <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="nmPessoa"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="nrLinha"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="nrConta"/></vivo:tbRowColumn>                            
                                <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="dsTipoCorrespondencia"/><html:hidden name="listaCorrespDevolvidaArray" property="idCorrespondencia" styleId="id"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="listaCorrespDevolvidaArray" property="dsMotivoDevolucao"/><html:hidden name="listaCorrespDevolvidaArray" property="idTipoRelacionamento" styleId="idTipoRelacionamento"/></vivo:tbRowColumn>                                
                                <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="cli_fcti_alterar"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0" onClick="validaPesquisa(<%=idx%>)" alt="Alterar Correspondência Devolvida" /></acesso:controlHiddenItem></vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>                                                                                                                                     
                    </vivo:tbRows>
                </vivo:tbTable>
            </td>
        </tr>
    </table>
    
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
    
    <table width="751" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
        <tr>
            <td valign="middle" width="100"><b>&nbsp;Legendas:</b></td>
            <td width="651"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="absmiddle"> &nbsp;Alterar Correspondência Devolvida</td>
        </tr>
    </table>
    
    <logic:equal name="Form" property="inStart" value="true">
        <script> 
            top.frameApp.fraAbas.location.href = '/FrontOfficeWeb/cliente/CDevolvida/telaInicial/begin.do';
        </script>
        <%Form.setInStart("false");%>
    </logic:equal>
    </vivo:sessao>
    </form>     
</acesso:controlHiddenItem>   
</body>



<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--        
        parent.oculta_div();
    -->
</SCRIPT>