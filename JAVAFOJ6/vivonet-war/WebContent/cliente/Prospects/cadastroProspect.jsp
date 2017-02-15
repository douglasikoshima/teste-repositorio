<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm" type="cliente.Prospects.ProspectsController.CadastroProspectForm"/>
<bean:define id="TpDocto"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.tipoDocumentoVOArray"/>
<bean:define id="TpCommc"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.tipoComunicacaoVOArray"/>
<bean:define id="Paises"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.paisVOArray"/>
<bean:define id="UFs"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.UFVOArray"/>
<bean:define id="Contatos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroProspectForm.cadastroProspectVO.prospectVO.contatoProspectVOArray"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Cadastro Prospect" name="title"></netui-template:setAttribute>
    <netui-template:setAttribute value="Gestão de Clientes" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
		<link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
		<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
		<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>
		<script>
			dia = new Date();
			mes = dia.getMonth() + 1;
			hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
			
			function bemVindo(){
			    document.forms[0].action = "<%=request.getContextPath()%>/index.jsp";
			    document.forms[0].submit();
			}
			
			function get_radio_value(){
			    for (var i=0; i < document.forms[0].tipoPessoaSelecionado.length; i++)
			       {
			       if (document.forms[0].tipoPessoaSelecionado[i].checked)
			          {
			          var rad_val = document.forms[0].tipoPessoaSelecionado[i].value;
			          return rad_val;
			          }
			       }
			}
			
			function reload_docs(){
			    idTpPessoa = get_radio_value();
			    document.forms[0].action="loadProspect.do?idTipoPessoa="+idTpPessoa;
			    document.forms[0].submit();
			}
			
			function validaProspect(){
				idTpPessoa = get_radio_value();
			    tipo = document.forms[0].elements["cadastroProspectVO.prospectVO.tipoDocumento"].options[document.forms[0].elements["cadastroProspectVO.prospectVO.tipoDocumento"].selectedIndex].text;
				obj = document.forms[0].elements['cadastroProspectVO.prospectVO.numeroDocumento'];
			    document.getElementById("primeiroNome").value = trim(document.getElementById("primeiroNome").value)
			    document.getElementById("sobreNome").value = trim(document.getElementById("sobreNome").value)
			    if (document.getElementById("primeiroNome").value == "" || document.getElementById("sobreNome").value == ""){
			        alert('Preencha nome e sobrenome do Prospect!');
				} else if (tipo=="CPF"){		//CPF
					if(trim(obj.value) == ""){
						alert("Favor preencher um CPF!");
					}else if(!validaCPF(obj.value)){
						alert("CPF invalido, favor corrigir!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            obj.value = limpaInteiro(obj.value);                        
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
			        }
				} else if (tipo=="RG") { 		//RG
					if(trim(obj.value) == ""){
						alert("Favor preencher um RG!");
					}else if(document.forms[0].elements['cadastroProspectVO.prospectVO.orgaoExpedidor'].value == ""){
						alert("Favor preencher o campo Órgão Expedidor!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
				} else if (tipo=="PASSAPORTE") { 		//Passaporte
					if(trim(obj.value) == ""){
						alert("Favor preencher um Passaporte!");
					}else if(document.forms[0].elements['cadastroProspectVO.prospectVO.orgaoExpedidor'].value == ""){
						alert("Favor preencher o campo Órgão Expedidor!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
				} else if (tipo=="RNE") { 		//RNE
					if(trim(obj.value) == ""){
						alert("Favor preencher um RNE!");
					}else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value == ""){
						alert("Favor preencher o campo Data de Expedição!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
				} else if (tipo=="CPR") { 		//CPR
					if(trim(obj.value) == ""){
						alert("Favor preencher um CPR!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
				} else if (tipo=="CERTIDÃO DE NASCIMENTO") { 		//Certidao de Nascimento
					if(trim(obj.value) == ""){
						alert("Favor preencher uma CertidÃ£o de Nascimento!");
					}else if(document.forms[0].elements['cadastroProspectVO.prospectVO.orgaoExpedidor'].value == ""){
						alert("Favor preencher o campo Órgão Expedidor!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
				} else if (tipo=="CNPJ") { 		//CNPJ
					if(trim(obj.value) == ""){
						alert("Favor preencher um CNPJ!");
					}else if(!validaCNPJ(obj.value)){
						alert("CNPJ invalido, favor corrigir!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            obj.value = limpaInteiro(obj.value);
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
				} else if (tipo=="INSCRIÇÃO ESTADUAL") { 		//Inscricao Estadual
					if(trim(obj.value) == ""){
						alert("Favor preencher uma InscriÃ§Ã£o Estadual!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
				} else if (tipo=="INSCRIÇÃO MUNICIPAL") { 		//Inscricao Municipal
					if(trim(obj.value) == ""){
						alert("Favor preencher uma InscriÃ§Ã£o Municipal!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
				} else if (tipo=="CARTEIRA NACIONAL DE HABILITAÇÃO") { 		//Carteira Nacional de Habilitacao
					if(trim(obj.value) == ""){
						alert("Favor preencher uma Carteira Nacional de HabilitaÃ§Ã£o!");
					}else if(document.forms[0].elements['cadastroProspectVO.prospectVO.orgaoExpedidor'].value == ""){
						alert("Favor preencher o campo Órgão Expedidor!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
				} else if (tipo=="REGISTRO DE EMPREGADO") { 		//Registro de Empregado
					if(trim(obj.value) == ""){
						alert("Favor preencher um Registro de Empregado!");
					}else if(document.forms[0].elements['cadastroProspectVO.prospectVO.orgaoExpedidor'].value == ""){
						alert("Favor preencher o campo Órgão Expedidor!");
					}else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value == ""){
						alert("Favor preencher o campo Data de Expedição!");
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){                
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
					}
			    }else{
			        if(trim(obj.value) == ""){
						alert("Favor preencher o número de documento!");		
			        }else if(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value != "" && !validaDataFinal(document.forms[0].elements['cadastroProspectVO.prospectVO.dataExpedicao'].value,hoje)){
			            alert('Data de Expedição maior que o dia de hoje, favor corrigir!');
					}else{
			            document.forms[0].action="salvarProspect.do?destino=POPUP&idTipoPessoa="+idTpPessoa;
			            document.forms[0].submit();
			        }
			    }
			}
			
			function validaContato(){
				tipo = document.forms[0].idComunicacao.selectedIndex;
				obj = document.forms[0].dsComunicacao;
			    if (document.forms[0].idComunicacao.value == ""){
			        alert('Selecione o tipo de contato!');
				} else if (tipo==0){		//Telefone Residencial
					if(trim(obj.value) == ""){
						alert("Favor preencher um Telefone!");
					}else if(obj.value.length < 12){
						alert("Telefone invalido, favor corrigir!");
			        }else{
			            document.forms[0].action="adicionarContatoProspect.do";
			            document.forms[0].submit();
					}
				} else if (tipo==1) { 		//Telefone Comercial
					if(trim(obj.value) == ""){
						alert("Favor preencher um Telefone!");
					}else if(obj.value.length < 12){
						alert("Telefone invalido, favor corrigir!");
			        }else{
			            document.forms[0].action="adicionarContatoProspect.do";
			            document.forms[0].submit();
					}
				} else if (tipo==2) { 		//Telefone Recado
					if(trim(obj.value) == ""){
						alert("Favor preencher um Telefone!");
					}else if(obj.value.length < 12){
						alert("Telefone invalido, favor corrigir!");
			        }else{
			            document.forms[0].action="adicionarContatoProspect.do";
			            document.forms[0].submit();
					}
				} else if (tipo==3) { 		//FAX
					if(trim(obj.value) == ""){
						alert("Favor preencher um Telefone!");
					}else if(obj.value.length < 12){
						alert("Telefone invalido, favor corrigir!");
			        }else{
			            document.forms[0].action="adicionarContatoProspect.do";
			            document.forms[0].submit();
					}
				} else if (tipo==4) { 		//Endereco de email
					if(trim(obj.value) == ""){
						alert("Favor preencher um email!");
					}else if(!validaEmail(obj.value)){
						alert("Email invalido, favor corrigir!");
			        }else{
			            document.forms[0].action="adicionarContatoProspect.do";
			            document.forms[0].submit();
					}
				} else if (tipo==6) { 		//Celular
					if(trim(obj.value) == ""){
						alert("Favor preencher um Telefone!");
					}else if(obj.value.length < 12){
						alert("Telefone invalido, favor corrigir!");
			        }else{
			            document.forms[0].action="adicionarContatoProspect.do";
			            document.forms[0].submit();
					}
			    }
			}
			
			function removerContato(idCont) {
			    if(confirm('Deseja realmente excluir este registro?')){
			        document.forms[0].ndxContatoARemover.value = idCont;
			        document.forms[0].action = "removeContatoProspect.do";
			        document.forms[0].submit();
			    }
			}
			
			function limpa() {
			    document.forms[0].action = "loadProspect.do";
			    document.forms[0].submit();
			}
			
			function escolheMascara(obj){
			    tipo = document.forms[0].elements["cadastroProspectVO.prospectVO.tipoDocumento"].options[document.forms[0].elements["cadastroProspectVO.prospectVO.tipoDocumento"].selectedIndex].text;
				if (tipo=="CPF"){				//CPF
					checaCPF(obj);
					qtdeCaracteres = 14;
				} else if (tipo=="RG") { 		//RG
					qtdeCaracteres = 25;
				} else if (tipo=="PASSAPORTE") { 		//Passaporte
					qtdeCaracteres = 25;
				} else if (tipo=="RNE") { 		//RNE
					qtdeCaracteres = 10;
				} else if (tipo=="CPR") { 		//CPR
					qtdeCaracteres = 25;
				} else if (tipo=="CERTIDÃO DE NASCIMENTO") { 		//Certidao de Nascimento    
					qtdeCaracteres = 25;
				} else if (tipo=="CNPJ") { 		//CNPJ
					checaCNPJ(obj);
					qtdeCaracteres = 18;
				} else if (tipo=="INSCRIÇÃO ESTADUAL") { 		//Inscricao Estadual
					qtdeCaracteres = 25;
				} else if (tipo=="INSCRIÇÃO MUNICIPAL") { 		//Inscricao Municipal
					qtdeCaracteres = 25;
				} else if (tipo=="CARTEIRA NACIONAL DE HABILITAÇÃO") { 		//Carteira Nacional de Habilitacao
					qtdeCaracteres = 25;
				} else if (tipo=="REGISTRO DE EMPREGADO") { 		//Registro de Empregado
					qtdeCaracteres = 25;
				} else {
			        qtdeCaracteres = 25;
			    }
				obj.maxLength = qtdeCaracteres;
			}
			
			function escolheMascara2(obj){
				tipo = obj.form.elements["idComunicacao"].selectedIndex;
				if (tipo==0){				//Telefone Residencial
					maskPhoneNumberObj(obj);
					qtdeCaracteres = 14;
				} else if (tipo==1) { 		//Telefone Comercial
					maskPhoneNumberObj(obj);
					qtdeCaracteres = 14;
				} else if (tipo==2) { 		//Telefone Recado
					maskPhoneNumberObj(obj);
					qtdeCaracteres = 14;
				} else if (tipo==3) { 		//FAX
					maskPhoneNumberObj(obj);
					qtdeCaracteres = 14;
				} else if (tipo==4) { 		//Endereco de email
					qtdeCaracteres = 255;
				} else if (tipo==5) { 		//Pager
					qtdeCaracteres = 25;
				} else if (tipo==6) { 		//Celular
					maskPhoneNumberObj(obj);
					qtdeCaracteres = 14;
				}
				obj.maxLength = qtdeCaracteres;
			}
	
			onload = function() {
				parent.oculta_div();
			}
		</script>
	</netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cli_cp_verpagina">
    <%-- <jsp:include page="/resources/menus/MenuPrincipal.jsp" /> --%>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <vivo:sessao id="prospect" width="790" height="470" label="Prospect" operacoes="Inclus&atilde;o" scroll="no">
		<html:form action="/cliente/Prospects/salvarProspect.do" styleId="salvarProspect" method="post">
	        <html:hidden name="Form" property="ndxContatoARemover"></html:hidden>
	        <logic:equal name="Form" property="inMsgRetorno" value="true">
	            <script>
	                alert('Erro ao gravar Prospect! Número de documento já utilizado por outro prospect!');
	                <%
	                    Form.setInMsgRetorno("false");
	                %>
	            </script>
	        </logic:equal>
            <table width="780" class="tbl_bgGray" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <table border="0" cellpadding="0" cellspacing="0" height="30">
                            <tr>
                                <td>&nbsp;&nbsp;Pessoa Física</td>
                                <logic:equal value="1" name="Form" property="idTipoPessoa">
                                    <td><input type="radio" name="tipoPessoaSelecionado" value="1" onclick="reload_docs()" checked style="border=none" class="radio"/></td>
                                    <td>&nbsp;&nbsp;Pessoa Jurídica</td>
                                    <td><input type="radio" name="tipoPessoaSelecionado" value="2" onclick="reload_docs()" style="border=none" class="radio"/></td>
                                </logic:equal>
                                <logic:equal value="2" name="Form" property="idTipoPessoa">
                                    <td><input type="radio" name="tipoPessoaSelecionado" value="1" onclick="reload_docs()" style="border=none" class="radio"/></td>
                                    <td>&nbsp;&nbsp;Pessoa Jurídica</td>
                                    <td><input type="radio" name="tipoPessoaSelecionado" value="2" onclick="reload_docs()" checked style="border=none" class="radio"/></td>
                                </logic:equal>
                            </tr>
                        </table>
                        <table width="740" border="0" cellspacing="0" cellpadding="0" height="30">
                            <tr>
                                <td width="100">&nbsp;&nbsp;Primeiro Nome: </td>
                                <td width="183"><html:text name="Form" property="cadastroProspectVO.prospectVO.nmPrimeiro" styleClass="textfield" maxlength="50" styleId="primeiroNome" style="width:170px;" size="10"/></td>
                                <td width="78">Nome do Meio: </td>
                                <td width="110"><html:text name="Form" property="cadastroProspectVO.prospectVO.nmMeio" styleClass="textfield" maxlength="50" style="width:100px;" styleId="nomeMeio" size="10"/></td>
                                <td width="53">Sobrenome:</td>
                                <td><html:text name="Form" property="cadastroProspectVO.prospectVO.nmSobrenome" styleClass="textfield" maxlength="50" style="width:162px;" styleId="sobreNome" size="10"/></td>
                            </tr>
                        </table>
                        <table width="740" border="0" cellspacing="0" cellpadding="0" height="30">
	                        <tr>
	                            <td width="102">&nbsp;&nbsp;Documento:</td>
                                <td width="200">
                                	<html:select style="width:200px;" name="Form" property="cadastroProspectVO.prospectVO.tipoDocumento" onchange="document.forms[0].elements['cadastroProspectVO.prospectVO.numeroDocumento'].value = ''">
                                       <html:options collection="TpDocto" property="idTipoDocumento" labelProperty="dsTipoDocumento"/>
                                	</html:select>
                                </td>
                                <td width="133"><html:text name="Form" property="cadastroProspectVO.prospectVO.numeroDocumento" maxlength="25" styleClass="textfield" onkeyup="escolheMascara(this)" styleId="nrDocto" size="20"/></td></td>
                                <script language="javascript">
                                for(i=0;i<document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options.length;i++){
                                    if(document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options[i].text == "CPF"){
                                        if(document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options[i].selected){
                                            checaCPF(document.forms[0].elements['cadastroProspectVO.prospectVO.numeroDocumento'])
                                        }
                                    }
                                }
                                for(i=0;i<document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options.length;i++){
                                    if(document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options[i].text == "CNPJ"){
                                        if(document.forms[0].elements['cadastroProspectVO.prospectVO.tipoDocumento'].options[i].selected){
                                            checaCNPJ(document.forms[0].elements['cadastroProspectVO.prospectVO.numeroDocumento'])
                                        }
                                    }
                                }
                                </script>
                                <td width="30">Pa&iacute;s:</td>
                                <td width="167" ><html:select style="width:160px" name="Form" property="cadastroProspectVO.prospectVO.idPais">
                                       <html:options collection="Paises" property="idPais" labelProperty="nmPais"/>
                                    </html:select></td>
                                <td width="23">UF:</td>
                                <td><html:select name="Form" property="cadastroProspectVO.prospectVO.idUFDocumento" style="width:40px;">
                                       <html:options collection="UFs" property="idUF" labelProperty="sgUF"/>
                                    </html:select>
                             	</td>
	                         </tr>
                         </table>
                         <table width="740" border="0" cellspacing="0" cellpadding="0" height="30">
                            <tr>
                                <td width="100">&nbsp;&nbsp;Org&atilde;o Expedidor: </td>
                                <td width="55"><html:text name="Form" property="cadastroProspectVO.prospectVO.orgaoExpedidor" styleClass="textfield" size="40" maxlength="255" style="width:50px;"/></td>
                                <td width="105">&nbsp;&nbsp;Data de Expedi&ccedil;&atilde;o: </td>
                                <td><html:text  name="Form" property="cadastroProspectVO.prospectVO.dataExpedicao" styleClass="textfield" size="12" readonly="true"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="CalendÃ¡rio" onclick="return showCalendar('cadastroProspectVO.prospectVO.dataExpedicao', '%d/%m/%Y');"></td>
                            </tr>
                         </table>
						<table width="740" border="0" cellspacing="0" cellpadding="0" height="30">
	                         <tr>
	                             <td width="102">&nbsp;&nbsp;Contato:</td>
	                             <td width="50">
		                             <html:select style="width:180px;padding-top:3px;"  name="Form" property="idComunicacao" onchange="document.forms[0].elements['dsComunicacao'].value = ''">
	                                     <html:options collection="TpCommc" property="idTipoComunicacao" labelProperty="dsTipoComunicacao"/>
									</html:select>
                                   </td>
                                   <td width="357">
                                   	<html:text name="Form" property="dsComunicacao" style="vertical-align:top;" maxlength="255" onkeyup="escolheMascara2(this)" size="40"/>
                                   </td>
                                   <td>
                                    <acesso:controlHiddenItem nomeIdentificador="cli_cp_incluir">
                                    	<img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onclick="validaContato(); return false" align="absmiddle" style="border:none;"/>
                                       </acesso:controlHiddenItem>
                                   </td>
                               </tr>
                        </table>
                        <table width="750" border="0" cellspacing="0" cellpadding="0" style="text-indent:5px;line-height:22px;">
                            <tr>
                             <td style="padding-left:5px;">
                                 <vivo:tbTable selectable="true" onRowClick="" layoutWidth="750" layoutHeight="250" tableWidth="750" styleId="tableTitle" sortable="true">
                                     <vivo:tbHeader>
                                         <vivo:tbHeaderColumn align="left" width="270" type="string">Tipo</vivo:tbHeaderColumn>
                                         <vivo:tbHeaderColumn align="left" width="450" type="string">Valor</vivo:tbHeaderColumn>
                                         <vivo:tbHeaderColumn align="center" width="20" type="string">&nbsp;</vivo:tbHeaderColumn>
                                     </vivo:tbHeader>
                                     <vivo:tbRows>
                                         <logic:iterate id="info" name="Contatos" indexId="ndx" >
                                             <vivo:tbRow key="Linha">
                                                 <vivo:tbRowColumn><bean:write name="info" property="dsTipoComunicacao"/></vivo:tbRowColumn>
                                                 <vivo:tbRowColumn><bean:write name="info" property="descricao"/></vivo:tbRowColumn>
                                                 <vivo:tbRowColumn><acesso:controlHiddenItem nomeIdentificador="cli_cp_excluir"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" border="0" onClick="removerContato('<%=ndx%>');return false"></acesso:controlHiddenItem></vivo:tbRowColumn>                                                                    </vivo:tbRow>
                                         </logic:iterate>
                                     </vivo:tbRows>
                                 </vivo:tbTable>
                                 </td>
                             </tr>
                        </table>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td>
                                    <img onClick="window.history.back(); return false"  vspace="5" hspace="8" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" style="border:none;"/>
                                </td>
                                <td align="right"> 
                                    <acesso:controlHiddenItem nomeIdentificador="cli_cp_limpar">                           
                                        <img vspace="5" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" style="border:none;" onClick="limpa(); return false"/>
                                    </acesso:controlHiddenItem>
                                    <acesso:controlHiddenItem nomeIdentificador="cli_cp_gravar">
                                        <img onClick="validaProspect(); return false" vspace="5" hspace="8" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" style="border:none;"/>                                                        
                                    </acesso:controlHiddenItem>
                                </td>
                            </tr>
                        </table>
                    </td>                               
                </tr>                                                                                          
            </table>
        </html:form>
    </vivo:sessao>    
</acesso:controlHiddenItem>           
</netui-template:section>
</netui-template:template>