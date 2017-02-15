<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.commons.utils.BrowserDetect"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Recontato Online"/>
    <netui-template:setAttribute name="modulo" value="Relatórios"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/TelaInicialAbaRelacionamento.css">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>

        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/effects.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/accordion.js"></script>
        
        <script language="javascript" type="text/javascript">        
        
            function pesquisaConta(){
                if($F('conta').strip().empty()){
                    alert("Favor informar a conta.");
                    return false;
                }
                document.forms[0].action = 'pesquisaConta.do';
                document.forms[0].submit();    
            }
        
            function validaForm(){  
          
        
                if (trim($F('dataInicio'))=="") {
                    alert("Os filtros Data Inicial e Data Final são obrigatórios.");
                    $('dataInicio').focus();
                    return false;
                }              
                 
               if (trim($F('dataTermino'))=="") {
                    alert("Os filtros Data Inicial e Data Final são obrigatórios.");
                    $('dataTermino').focus();
                    return false;
                }                     
                 
                                       
                if(!validaDataFinal($F('dataInicio'),$F('dataTermino'))){
                    alert('A Data Inicial não pode ser Maior do que a Data Final.');
                    return false;
                }

				dia = new Date();
				mes = dia.getMonth() + 1;
				hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
                
                if(!validaDataFinal($F('dataTermino'),hoje)){
                    alert('Data de Término maior que o dia de hoje.');
                    return false;
                }
        
                dtInicio  = $F('dataInicio');
                dtTermino = $F('dataTermino');
 
                arD1 = dtInicio.split("/");

				var dt1 = new Date();
                dt1.setFullYear(arD1[2],arD1[1]-1,arD1[0]);

                arD2 = dtTermino.split("/");
				var dt2 = new Date();
                dt2.setFullYear(arD2[2],arD2[1]-1,arD2[0]);

				var hoje = new Date();
				qtDiasDecorridos = diasDecorridos(dt1, hoje);
                
				if (qtDiasDecorridos > 90) {
					alert('O período máximo de busca é retroativo de três (3) meses.');
					return false;
				}                
 
                                                                 
                return true;
            }

            function pesquisar(){
                if(validaForm()){
                    if (self.$('dvAnimarAguarde')) self.startAnimation();
                    var params = $H();
                    params.set('dataInicio', $F('dataInicio'));
                    params.set('dataTermino', $F('dataTermino'));
                    params.set('ddd', $F('ddd'));
                    params.set('numeroTelefone', $F('numeroTelefone'));
                    params.set('tipoLinha', $F('tipoLinha'));
                    params.set('motivoRecontato', $F('motivoRecontato'));
                    params.set('segmentacao', $F('segmentacao'));
                    params.set('fornecedor', $F('fornecedor'));
                    params.set('site', $F('site'));
                    
                    $('dataInicio').value = $F('dataInicio');
                    $('dataTermino').value = $F('dataTermino');
                    $('ddd').value = $F('ddd');
                    $('numeroTelefone').value = $F('numeroTelefone');
                    $('tipoLinha').value = $F('tipoLinha');
                    $('motivoRecontato').value = $F('motivoRecontato');
                    $('segmentacao').value = $F('segmentacao');
                    $('fornecedor').value = $F('fornecedor');
                    $('site').value = $F('site');

                    new Ajax.Updater('dataTable', '<%=request.getContextPath()%>/admsistemas/relatorioRechamadaDetalhado/pesquisar.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();
                            
                            var bottomAccordion = new accordion('vertical_container', {});
							if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();                            

                        }, onFailure: function(e) {
                            if (self.$('dvAnimarAguarde')) self.stopAnimation();
                            var stacktrace = e.getHeader("stacktrace") ? e.getHeader("stacktrace") : "";
                            top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stacktrace, false);
                        },
                        onComplete: function(e) {
                        	montarPaginacao();
                        }
                    });
                }
            }

            function limpar(){
                document.forms[0].action = '<%=request.getContextPath()%>/admsistemas/relatorioRechamadaDetalhado/clean.do';
                document.forms[0].submit();  
            }

            function exportar(){
                var f = document.forms[0];
                if(validaForm()){
                    if($F('dataInicio')!=""){
                        $('dataInicio').value = $F('dataInicio');
                        $('dataTermino').value = $F('dataTermino');

                        f.action = "<%=request.getContextPath()%>/admsistemas/relatorioRechamadaDetalhado/exportar.do";
                        f.target = "frmDown";
                        f.submit();
                    }else{
                        alert("Favor realizar a Pesquisa.");
                    }
                }
            }
            
            
            function exibirMensagem (id) {
                var div = document.getElementById(id);
                
                if (div.style.display == "none") {
                    div.style.display = "block";
                } else {
                    div.style.display = "none";
                }

            }

            function avancar(){
            	var paginaAtual = document.getElementById("pagNum").value;
            	var totalPag = document.getElementById("totalpag").value;
            	paginaAtual++;
            	if(paginaAtual > totalPag) paginaAtual = 1;
            	trocar(paginaAtual);
            }
            
            function retroceder(){
            	var paginaAtual = document.getElementById("pagNum").value;
            	var totalPag = document.getElementById("totalpag").value;
            	paginaAtual--;
            	if(paginaAtual == 0) paginaAtual = totalPag;
            	trocar(paginaAtual);
            }
            
            function trocar(newPage){
            	var paginaAtual = document.getElementById("pagNum").value;
	
            	if(paginaAtual != newPage){
	            	
            		if(validaForm()){
                        if (self.$('dvAnimarAguarde')) self.startAnimation();
                        var params = $H();
                        params.set('dataInicio', $F('dataInicio'));
                        params.set('dataTermino', $F('dataTermino'));
                        params.set('ddd', $F('ddd'));
                        params.set('numeroTelefone', $F('numeroTelefone'));
                        params.set('tipoLinha', $F('tipoLinha'));
                        params.set('motivoRecontato', $F('motivoRecontato'));
                        params.set('segmentacao', $F('segmentacao'));
                        params.set('fornecedor', $F('fornecedor'));
                        params.set('site', $F('site'));
                        params.set('pagNum', newPage);
                        
                        $('dataInicio').value = $F('dataInicio');
                        $('dataTermino').value = $F('dataTermino');
                        $('ddd').value = $F('ddd');
                        $('numeroTelefone').value = $F('numeroTelefone');
                        $('tipoLinha').value = $F('tipoLinha');
                        $('motivoRecontato').value = $F('motivoRecontato');
                        $('segmentacao').value = $F('segmentacao');
                        $('fornecedor').value = $F('fornecedor');
                        $('site').value = $F('site');
                        $('pagNum').value = newPage;

                        new Ajax.Updater('dataTable', '<%=request.getContextPath()%>/admsistemas/relatorioRechamadaDetalhado/montarPaginacao.do', {
                            method: 'post',
                            parameters: params,
                            evalScripts: true,
                            onSuccess: function() {
                                if (self.$('dvAnimarAguarde')) self.stopAnimation();
                                
                                var bottomAccordion = new accordion('vertical_container', {});
    							if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();            

                            }, 
                            onFailure: function(e) {
                                if (self.$('dvAnimarAguarde')) self.stopAnimation();
                                var stacktrace = e.getHeader("stacktrace") ? e.getHeader("stacktrace") : "";
                                top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stacktrace, false);
                            },
                            onComplete: function(e) {
                            	document.getElementById("pagNum").value = newPage;
                            	montarPaginacao();
                            }
                        });
                    }
            		
            	}
            }
            
            function montarPaginacao(){
            	var paginaAtual = document.getElementById("pagNum").value;
            	var totalPag = document.getElementById("totalpag").value;
            	var paginacao = document.getElementById("paginacao");
            	var paginas = "";
            	
            	paginas += '<a href="javascript:void(0);" onclick="retroceder();"> &lt;&lt; </a> ';
            	for(var i = 1; i <= totalPag; i++){
            		if(i == paginaAtual)
            			paginas += i + " ";
            		else
            			paginas += '<a href="javascript:void(0);" onclick="trocar(' + i + ');">' + i + '</a> ';
            	}
            	paginas += '<a href="javascript:void(0);" onclick="avancar();"> &gt;&gt; </a>';
            	paginacao.innerHTML = paginas;
            }

        </script>
        <script language="javascript" type="text/javascript" for="window" event="onload">
            <logic:notEmpty name="msgStatus" scope="request">
            alert('<bean:write name="msgStatus" scope="request"/>');
            </logic:notEmpty>
            oculta_div();
        </script>
        <style type="text/css">
        <!--
            div.scroller {
            height: 250px;
	            width: 780px;
	            overflow: auto;
            }
            div.scroller table {
            height: 50px;
	            margin: 0;
            }
                        
            #divTableTitle2 table,#divTableTitle2 div table  {width:1300px;}      
            #divTableTitle2 table, #divTableTitle2 div table {
                background:#656565;
                height:25px;
                color:#ffffff;
                font-family:Tahoma;
                font-size:10px;
                font-weight:bold;
                line-height:20px;
            }
            #divTableTitle2 td {
                border-right:1px solid #ffffff;
                padding-left:10px;
            }            
        //-->
    </style>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="460" width="790" label="Relatório" operacoes="Recontato Online">
            <form  name="formRel"  action="pesquisar.do" enctype="multipart/form-data" method="post" onsubmit="return false;" style="margin:0px;">

            <input type="hidden" name="idmensagem">
            
            <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <fieldset>
                <table cellpadding="2" cellspacing="2" width="750px">
                    <tr>
						<td width="700" colSpan="4" align="left">
							Data Inicial: <input onblur="checaData(this)" style="WIDTH: 70px" id="dtValidadeDe" onkeyup="checaData(this)" name="dataInicio" maxLength="10"> 
							<IMG style="CURSOR: pointer; MARGIN-RIGHT: 20px" title="Calendário" onclick="return showCalendar('dtValidadeDe', '%d/%m/%Y');" src="<%=request.getContextPath()%>\resources\images\calendario.gif">
							Data Final: &nbsp;&nbsp; <input onblur="checaData(this)" style="WIDTH: 70px" id="dtValidadeAte" onkeyup="checaData(this)" name="dataTermino" maxLength="10"> 
							<IMG style="CURSOR: pointer" title="Calendário" onclick="return showCalendar('dtValidadeAte', '%d/%m/%Y');" src="<%=request.getContextPath()%>\resources\images\calendario.gif">
						</td>
                    </tr>    
                    <tr>
						<td width="100" align="right">DDD:</td>
						<td width="160" align="left">
                            <html:select name="Form" property="ddd" styleId="ddd" style="width:150px;" styleClass="SELECT">
								<option selected value="">-- Todas --</option> 
                                <html:optionsCollection name="Form" property="listaDDD.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
							<td width="100" align="right">N&#250;mero Telefone:</td>
								<td width="450" align="left">
								<input onblur="formatPhoneNumberObj(this)" id="numeroTelefone" name="numeroTelefone" maxLength="11" size="18"> 
							</td>
                    </tr>                         
                    <tr>
							<td align="right">Tipo de Linha:</td>
							<td align="left">
                            <html:select name="Form" property="tipoLinha" styleId="tipoLinha" style="width:150px;" styleClass="SELECT">
								<option selected value="">-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaTipoLinha.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
							<td align="right">Motivo Recontato:</td>
							<td align="left">
                            <html:select name="Form" property="motivoRecontato" styleId="motivoRecontato    " style="width:150px;" styleClass="SELECT">
								<option selected value="">-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaMotivoRecontato.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
                    </tr>                                      
                    <tr>
							<td align="right">Segmenta&#231;&#227;o:</td>
							<td align="left">
                            <html:select name="Form" property="segmentacao" styleId="segmentacao" style="width:150px;" styleClass="SELECT">
								<option selected value="">-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaSegmento.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
							<td align="right">Site:</td>
							<td align="left">
                            <html:select name="Form" property="site" styleId="site  " style="width:150px;" styleClass="SELECT">
								<option selected value="">-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaSite.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
                    </tr>       
                    <tr>
							<td align="right">Fornecedor:</td>
							<td align="left">
                            <html:select name="Form" property="fornecedor" styleId="fornecedor" style="width:150px;" styleClass="SELECT">
								<option selected value="">-- Todas --</option>
                                <html:optionsCollection name="Form" property="listaFornecedor.itArray" value="id" label="ds"/>
                            </html:select>                            
                        </td>
						<td colSpan="2" align="right">
							<IMG style="CURSOR: pointer" onclick="pesquisar();" src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif"> 
							<IMG style="CURSOR: pointer" onclick="limpar();" src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif"> 
						</td>
                    </tr>
                </table>
            </fieldset>

            <div class="scroller">
                <div id="dataTable">
                    <div id="divTableTitle2">
                        <table cellspacing="0" cellpadding="0">
                            <tr>          
                                <td align="center" width="100">Seq&#252;&#234;ncia</td>
                                <td align="center" width="100">Canal Atual</td>
                                <td align="center" width="100">Data/Hora</td>
                                <td align="center" width="100">Grupo Anterior</td>
                                <td align="center" width="100">Fornecedor Anterior</td>
                                <td align="center" width="100">Site Anterior</td>
                                <td align="center" width="100">Login Anterior</td>
                                <td align="center" width="100">Motivo da Recontato</td>
                                <td align="center" width="100">Submotivo de Recontato</td>
                                <td align="center" width="100">N&#250;mero Linha</td>
                                <td align="center" width="100">Tipo de Linha</td>
                                <td align="center" width="100">Segmenta&#231;&#227;o</td>
					            <td align="center" width="100">* Nota da URA</td>
                                <td align="center" width="100">N&#186; Protocolo</td>
                            </tr>
                        </table>
                    </div> 
                </div>
            </div>


            </form>

			<table width="707">
                <tr>
					<td id="paginacao">
					</td>
					<td align="right">
						<img src="<%=request.getContextPath()%>/resources/images/bt_exportar_nrml.gif" onclick="exportar();" style="border:none;cursor:pointer;" />
					</td>
                </tr>
            </table>
            <span style="width:750px;">* Nota da URA: Não será possível visualizar as notas do dia atual, pois o processo que armazena esta informação é executado uma vez por dia.</span>
            
        </vivo:sessao>
        
        <iframe id="frmDown" name="frmDown" src="about:blank" width="1" height="1" style="display:none"></iframe>
        <iframe id="frmSessionAlive" name="frmSessionAlive" src="sessionAlive.jsp" width="1" height="'" style="display: none;"></iframe>
        
    </netui-template:section>
</netui-template:template>