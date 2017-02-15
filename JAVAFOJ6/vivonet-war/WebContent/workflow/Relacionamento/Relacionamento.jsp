<!--
Módulo.....: Workflow
Caso de Uso: Relacionamento
Versão.....: $Author: a5112272 $ - $Revision: 1.4 $ - $Date: 2011/06/01 19:19:46 $
-->

<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>
<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm" />
<bean:define id="aEstados"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.wfEstados.WFEstadoVOArray" />
<bean:define id="aSubEstados"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.atendimentoPesquisaVO.WFSubEstados.WFSubEstadoVOArray" />

<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js" ></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script>
            var altura = 0;
            var div = null;
            
            parent.mostrar_div();
            
            function arvoreContato() {
                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    
                    dvArvore.style.display = '';
                    document.forms["relacionamentoForm"].method = "POST";
                    document.forms["relacionamentoForm"].action = "obterArvoreContato.do";
                    document.forms["relacionamentoForm"].target = "ifrmArvore";
                    document.forms["relacionamentoForm"].submit();
                }

            function obtemSubEstadosIFrame(estado) {
                //Verifica se a pessoa selecionou a opção -- Selecione--                
                if(estado.selectedIndex == 0){                
                    var aOptComponent = document.forms["relacionamentoForm"].elements["subEstadoSelecionado"];
                    var aOptComponentOrigin = document.forms["relacionamentoForm"].elements["estadoSelecionado"];                                                                                       
                    
                    while(aOptComponent.options.length != 0)                                                         
                        aOptComponent.options.remove(0);

                    oOption = document.createElement("OPTION");               
                    aOptComponent.options.add(oOption);
                    oOption.innerText = "-- Selecione --";
                    oOption.value     = ""; 
                                                                                                                         
                    return false;
                }

                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                //Monta o path seleção
                document.forms["relacionamentoForm"].target = "iSubEstado";
                document.forms["relacionamentoForm"].action = "pesquisarSubEstado.do";
                document.forms["relacionamentoForm"].submit();  
            }
            
            function pesquisarRelacionamento(){
                dtInicioDe  = document.forms["relacionamentoForm"]['dtAberturaInicio'].value;
                dtInicioAte = document.forms["relacionamentoForm"]['dtAberturaFim'].value ;                                
                dtFimDe = document.forms["relacionamentoForm"]['dtFechamentoInicio'].value;
                dtFimAte = document.forms["relacionamentoForm"]['dtFechamentoFim'].value;
                    
                //Valida as datas
                if( !(validaDataFinal(dtInicioDe,dtInicioAte)) && dtInicioDe != "" && dtInicioAte != "") {
                    document.forms["relacionamentoForm"]['dtAberturaInicio'].focus();
                    alert('A data de abertura em inicio e fim são inválidas, favor informar novamente.');
                    return;
                }

                if( !(validaDataFinal(dtFimDe,dtFimAte)) && dtFimDe != "" && dtFimAte != "") {
                    document.forms["relacionamentoForm"]['dtFechamentoInicio'].focus();
                    alert('A data de fechamento em inicio e fim são inválidas, favor informar novamente.');
                    return;
                }
            
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                
                //Monta o path seleção
                document.forms["relacionamentoForm"].target = "iRelacionamento";
                document.forms["relacionamentoForm"].action = "pesquisarRelacionamento.do";
                document.forms["relacionamentoForm"].submit();
            }
            
            function limpar() {
                document.forms["relacionamentoForm"]['dtAberturaInicio'].value   = "";
                document.forms["relacionamentoForm"]['dtAberturaFim'].value      = "";
                document.forms["relacionamentoForm"]['dtFechamentoInicio'].value = "";
                document.forms["relacionamentoForm"]['dtFechamentoFim'].value    = "";
                document.forms["relacionamentoForm"]['idAtendimento'].value      = "";
                document.forms["relacionamentoForm"]['contato'].value            = "";
                document.forms["relacionamentoForm"]['idContato'].value            = "";
                
                document.forms["relacionamentoForm"]['estadoSelecionado'].value  = "";
                
                document.forms["relacionamentoForm"]['dtAberturaInicio'].focus();
                
                obtemSubEstadosIFrame( document.forms["relacionamentoForm"]['estadoSelecionado'] );
            }
            
            function update(row) {
                top.atendimentoProcesso();
            }
            
            function validaData(data){
                if(data.value == '')
                    return false;
                if(!validate_date(data.value)){
                    data.value = '';
                    data.focus();
                    alert("Data inválida");
                }
            }
        
            function obtemDataAbertura(){
                data = new Date();
                dia = data.getDate();
                mes = data.getMonth() + 1;
                ano = data.getFullYear();
                
                if(dia < 16){
                    if(mes == 5 || mes == 7 || mes == 10 || mes == 12) //o mes anterior tem 30 dias
                        dia += 15;
                    else if(mes == 3)  //o mes anterior é fevereiro ****falta fazer pra qd for ano bissexto****
                        dia += 13;
                    else{ 
                        if(mes == 1){  //quando for janeiro
                            mes = 13;
                            ano -= 1;
                        }
                        dia += 16;
                    }
                    mes -= 1;
                }
                else
                    dia -= 15;
                
                dtAbertura.value = dia + "/" + mes + "/" + ano; 

            }

            //faz o radio de Linha ser selecionado (checked) pois é a pesquisa default
            function setPesquisaLinha(){
                    document.all['tpPesquisa'][1].checked = true;
            }
            
            //faz o radio de Pessoa ser selecionado (checked)
            function setPesquisaPessoa(){
                    document.all['tpPesquisa'].checked = true;
            }

            //Controle tamanho da página
            function rezisePage() {
                altura = document.parentWindow.parent.document.getElementById("fraAbas").offsetHeight;
                div = document.getElementById("iRelacionamento");
                
                //Configura o tamanho inicial
                div.style.height = (top.up ? 75 : 283);
                
                //Configura o tamanho da grade
                iRelacionamento.tableRelacionamentoIFrame_body.parentElement.style.height = (top.up ? "45px" : "256px");            
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            //Ajusta o tamanho da página
            rezisePage();

            parent.oculta_div();

            //Inicializa a pesquisa            
            <logic:equal name="Form" property="tipoPesquisa" value="1">
                setPesquisaLinha();
            </logic:equal>
            <logic:equal name="Form" property="tipoPesquisa" value="0">
                setPesquisaPessoa();
            </logic:equal>
        -->
        </SCRIPT>
        <SCRIPT FOR=window EVENT=onresize LANGUAGE="JScript">
        <!--
            //Ajusta o tamanho da página
            rezisePage();
        -->
        </SCRIPT>
    </head>
    <body style="margin-left:5px; margin-right:5px" onload="teste()">
    <acesso:controlHiddenItem nomeIdentificador="wor_rel_verpagina">
        <form action="pesquisarRelacionamento.do" method="post" name="relacionamentoForm">
            <html:hidden name="Form" property="idPessoaDePara" />
            <html:hidden name="Form" property="tpRelacionamento" />
            <html:hidden name="Form" property="idPessoaLinhaHistorico" />
            <html:hidden name="Form" property="user" />
            <html:hidden name="Form" property="idContato" />
            <html:hidden name="Form" property="inPrimeiraChamada" />
            <table width="758" border="0" style="border:1px solid #adadad; background-color:#E7E7E7;">
                <tr>
                    <td rowspan="2">
                        <table width="75px">
                            <tr>
                                
                                <td>
                                    <logic:equal name="Form" property="tipoPesquisa" value="1">
                                        <html:radio styleClass="radio" name="Form" property="tpPesquisa" value="1" /> Cliente
                                    </logic:equal>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <logic:equal name="Form" property="tipoPesquisa" value="1">
                                        <html:radio styleClass="radio" name="Form" property="tpPesquisa" value="2" /> Linha
                                    </logic:equal>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <html:radio styleClass="radio" name="Form" property="tpPesquisa" value="3" /> Pessoa
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td colspan="5">
                        <table border="0">
                            <tr>
                                <td>Abertura de:</td>
                                <td><html:text name="Form" property="dtAberturaInicio" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtAberturaInicio', '%d/%m/%Y');"></td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;Até:&nbsp;&nbsp;<html:text name="Form" property="dtAberturaFim" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtAberturaFim', '%d/%m/%Y');"></td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;Processo:</td>
                                <td width="121"><html:text name="Form" property="idAtendimento" onkeyup ="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');"/></td>
                            </tr>
                            <tr>
                                <td>Fechamento de:</td>
                                <td><html:text name="Form" property="dtFechamentoInicio" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtFechamentoInicio', '%d/%m/%Y');"></td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;Até:&nbsp;&nbsp;<html:text name="Form" property="dtFechamentoFim" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtFechamentoFim', '%d/%m/%Y');"></td>
                                <td>&nbsp;&nbsp;&nbsp;&nbsp;Contato:</td>
                                <td width="121"><html:text name="Form" property="contato" size="45" readonly="true"/></td><td><img id="imgLupa" src="<%=request.getContextPath()%>/resources/images/lupa_bg_transp.gif" onclick="arvoreContato()" style='cursor:pointer' border="0"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="5">
                        <table border="0">
                            <tr>
                                <td width="70" align="right">Estado:</td>
                                <td width="70">
                                    <html:select name="Form" property="estadoSelecionado" style="WIDTH:200px" onchange="obtemSubEstadosIFrame(this);">
                                        <html:option value="">-- Selecione --</html:option>
                                        <html:options collection="aEstados" property="idEstado" labelProperty="dsEstado" /> 
                                    </html:select>
                                </td>
                                <td width="70" align="right">SubEstado:</td>
                                <td width="70">
                                    <html:select name="Form" property="subEstadoSelecionado" style="WIDTH:200px" />    
                                </td>
                                <td width="100" align="center">
                                <acesso:controlHiddenItem nomeIdentificador="wor_rel_pesquisar">
                                    <vivo:botao id="btPesquisar" width="60px" height="25px" value="Pesquisar" styleClass="btTemplate" onclick="pesquisarRelacionamento();"/>
                                </acesso:controlHiddenItem>
                                </td>
                                <td width="100" align="center">
                                    <vivo:botao id="btLimpar" width="60px" height="25px" value="Limpar" styleClass="btTemplate" onclick="limpar();"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <div id="divRelacionamento" style="visibility:visible;POSITION:relative;">
                <iframe name="iRelacionamento" id="iRelacionamento" src="iRelacionamento.jsp" width="758" height="75" scrolling="no"></iframe>
            </div>
            <iframe name="iSubEstado" src="iSubEstado.jsp" style="visibility:hidden;" width="1" height="1"></iframe>
        </form>
        <vivo:quadroFlutuante id="dvArvore"     idIframe="ifrmArvore"     width="720" height="220" spacesTop="90" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="auto" />
  
    </acesso:controlHiddenItem>
    </body>                        
</html>
