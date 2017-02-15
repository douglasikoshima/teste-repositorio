<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Grupos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="grupoCRIForm.grupoCRI.gruposArray"/>
<bean:define id="Form"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="grupoCRIForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Gerenciamento de Árvore de Perguntas"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Clientes"/>
    <netui-template:section name="headerSection">
        <head>
          <title></title>
          <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
          <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
          <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
          <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
          <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
          <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
          <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
          <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
          <script>
            
            function controlCombos(){
                if(document.getElementById('pesquisa').style.display==''){
                    document.getElementById('pesquisa').style.display='none';
                    if(buscaCliente.document.getElementById('idGrupoSelecionado')){
                        buscaCliente.document.getElementById('idGrupoSelecionado').style.display='none';
                    }
                }else{
                    document.getElementById('pesquisa').style.display='';
                    if(buscaCliente.document.getElementById('idGrupoSelecionado')){
                        buscaCliente.document.getElementById('idGrupoSelecionado').style.display='';
                    }
                }
            }
            
            function pesquisaNome(){
                if (document.forms[0].pesquisa.value == 'NOME' || document.forms[0].pesquisa.value == 'RAZAO') {                 
                    var tipo = document.forms[0].pesquisa.value;
                    controlCombos();
                    dvPesquisaNome.style.display = '';
                    document.forms[0].action     = 'irPesquisaNome.do';
                    document.forms[0].target     = 'ifrmPesqNome';
                    mostrar_div();
                    document.forms[0].submit();
                    document.forms[0].pesquisa.selectedIndex = 0;
                }              
             }   
                      
            function validaPesquisa(){
                tipo = document.forms[0].pesquisa.selectedIndex;
                if(trim(document.getElementById('valorPesquisaPuro').value)==''){
                    switch(tipo){
                        case 0:
                            return('Forneça um número de Linha!');
                        break;
                        case 2:
                            return('Forneça um número de Conta!');
                        break;
                        case 3:
                            return('Forneça um número de CPF!');
                        break;
                        case 4:
                            return('Forneça um número de CNPJ!');
                        break;
                        case 5:
                            return('Forneça um RNE!');
                        break;
                        case 6:
                            return('Forneça um Passaporte!');
                        break;
                        case 7:
                            return('Forneça uma Inscrição Estadual!');
                        break;
                        case 9:
                            return('Forneça uma Certidão de Nascimento!');
                        break;
                        case 10:
                            return('Forneça um RG!');
                        break;
                        case 11:
                            return('Forneça uma Inscrição Municipal!');
                        break;
                    }
                }else if(document.forms[0].pesquisa.selectedIndex==0 &&
                         document.getElementById('valorPesquisaPuro').value.length < 10){
                    return ('Forneça uma linha válida!');
                }else if(document.forms[0].pesquisa.selectedIndex==3 &&
                         !validaCPF(document.getElementById('valorPesquisaPuro').value)){
                    return ('Forneça um número de CPF válido!');
                }else if(document.forms[0].pesquisa.selectedIndex==4 &&
                         !validaCNPJ(document.getElementById('valorPesquisaPuro').value)){
                    return ('Forneça um número de CNPJ válido!');
                }else{
                    return true;
                }
            }
            
            function escolheMascara(obj){
                tipo = obj.form.pesquisa.selectedIndex;
                
                switch(tipo){
                    case 0:			//Celular
                        //obj.setAttribute("onblur","formatPhoneNumberObj(this)");
                    	maskPhoneNumberObj(obj);
                        qtdeCaracteres = 14;
                    break;
            
                    case 1: 		//Nome do Cliente
                        qtdeCaracteres = 255;
                    break;
                    
                    case 2: 		//Conta
                        checaInteiro(obj);
                        qtdeCaracteres = 40;
                    break;
            
                    case 3: 		//CPF
                        checaCPF(obj);
                        qtdeCaracteres = 14;
                    break;
            
                    case 4: 		//CNPJ
                        checaCNPJ(obj);
                        qtdeCaracteres = 18;
                    break;
            
                    case 5: 		//RNE
                        checaInteiro(obj);
                        qtdeCaracteres = 25;
                    break;
            
                    case 6: 		//Passaporte
                        qtdeCaracteres = 25;
                    break;
                    
                    case 7: 		//IE
                        checaInteiro(obj);
                        qtdeCaracteres = 25;
                    break;    
                    
                    case 9:         //CN
                        qtdeCaracteres = 25;
                    break;
                        
                    case 10: 		//RG
                        qtdeCaracteres = 25;
                    break;
                    
                    case 11: 		//IM
                        checaInteiro(obj);
                        qtdeCaracteres = 25;
                    break;
                }
                obj.maxLength = qtdeCaracteres;
            }
            
            function abrePesquisa(){
                if(document.forms[0].pesquisa.selectedIndex==0 || 
                   document.forms[0].pesquisa.selectedIndex==3 || 
                   document.forms[0].pesquisa.selectedIndex==4){
                    document.getElementById('valorPesquisaPuro').value = limpaInteiro(document.getElementById('valorPesquisa').value);
                }else{
                    document.getElementById('valorPesquisaPuro').value = document.getElementById('valorPesquisa').value;
                }
                if(validaPesquisa()==true){
                    valorPesquisaOld = document.getElementById('valorPesquisa').value;
                    document.getElementById('valorPesquisa').value = document.getElementById('valorPesquisaPuro').value;
                    if(document.forms[0].pesquisa.selectedIndex==0 ||
                       document.forms[0].pesquisa.selectedIndex==2){
                        document.forms[0].target='buscaCliente';
                    }else{
                        document.getElementById('pesquisa').style.display='none';
                        if(buscaCliente.document.getElementById('idGrupoSelecionado')){
                            buscaCliente.document.getElementById('idGrupoSelecionado').style.display='none';
                        }
                        dvResultados.style.display = '';
                        document.forms[0].target='ifrmPesqNomeResultados';
                    }
                    document.getElementById('sgTipoPesquisa').value = document.forms[0].pesquisa.value;
                    mostrar_div();
                    document.forms[0].action = 'pesquisarCRI.do';
                    document.forms[0].submit();
                    document.getElementById('valorPesquisa').value = valorPesquisaOld;
                }else{
                    alert(validaPesquisa());
                    focoValorPesquisa();
                }
            }
         
            function preValidaKey(){
                if (window.event.keyCode == 13){
                    window.event.keyCode = 0;
                    abrePesquisa();
                }
            }
            
            function focoValorPesquisa(){
                setTimeout("document.getElementById('valorPesquisa').focus()",300);
            }
            
            function limparTela(){
                document.getElementById('valorPesquisa').value='';
                document.forms[0].action = 'limparPesquisa.do';
                document.forms[0].target = 'buscaCliente';
                mostrar_div();
                document.forms[0].submit();
            }
          
          </script>
            <script for="window" event="onload">
                if (document.getElementById('valorPesquisa')!=null){
                    if (document.getElementById('valorPesquisa')!='undefined') focoValorPesquisa();
                }
            </script>
        </head>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <acesso:controlHiddenItem nomeIdentificador="cli_ag_cr_verpagina">
        <div style="height:10px;"><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="1"></div>
        <vivo:quadro id="cadastro" width="790" height="470" label="Associar Grupo CRI" >
        <div>
            <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="2">
        </div>
            <vivo:quadro id="lista" width="780" height="420" label="Consulta" >
                  <table id="tbbusca" width="770" height="29" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                    <tr>
                      <td colspan="3"><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="2"></td>
                    </tr>
                        <form action="pesquisarCRI.do" onsubmit="return false;" method="post">
                        <tr>
                          <td width="12%" class="bfixa_texto_black" style="text-indent:6px;"><b>Pesquisa</b></td>
                            <!--wl:cache-->
                            <td width="15%">
                              <select tabindex="1" name="pesquisa" id="pesquisa"  style="z-index:1;" onChange="document.getElementById('valorPesquisa').value='';pesquisaNome();">
                                <option label="celular" value="CELULAR">N&uacute;mero da linha</option>
                                <option label="nome" value="NOME">Nome do Cliente</option>
                                <option label="conta" value="CONTA">N&uacute;mero da Conta</option>
                                <option label="CPF" value="CPF">CPF</option>
                                <option label="CNPJ" value="CNPJ">CNPJ</option>
                                <option label="RNE" value="RNE">RNE</option>
                                <option label="Passaporte" value="PAS">Passaporte</option>
                                <option label="IE" value="IE">Inscri&ccedil;&atilde;o Estadual</option>
                                <option label="razao" value="RAZAO">Razão Social</option>
                                <option label="CN" value="CN">Certid&atilde;o de Nascimento</option>
                                <option label="RG" value="RG">RG</option>
                                <option label="IM" value="IM">Inscri&ccedil;&atilde;o Municipal</option>
                              </select>
                            </td>
                            <td width="15" valign="middle">
                               <html:text name="Form" tabindex="2" property="grupoCRI.pesquisa.filtro.dsValor" styleId="valorPesquisa" disabled="false" style="width:150px; height:15px;" onkeyup="document.getElementById('valorPesquisa').value=trim(document.getElementById('valorPesquisa').value);escolheMascara(this);preValidaKey();" value="" onkeydown="document.getElementById('valorPesquisa').value=trim(document.getElementById('valorPesquisa').value);escolheMascara(this);preValidaKey();"/>
                               <html:hidden name="Form" property="grupoCRI.pesquisa.filtro.sgTipoPesquisa" styleId="sgTipoPesquisa"/>
                               <input type="hidden" id="valorPesquisaPuro">
                            </td>
                          <td width="12%">
                            <acesso:controlHiddenItem nomeIdentificador="cli_ag_cr_bt_pesquisar">
                            &nbsp;<img id="ok" hspace="5" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif';" style="border:none;cursor:hand;" onClick="abrePesquisa(); return false;"/>
                            </acesso:controlHiddenItem>
                          </td>
                        
                          <td >
                            <img id="limpar" align="right" hspace="5" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif';" style="border:none;cursor:hand;" onClick="limparTela(); return false;"/>
                          </td>
                          <td width="12%">
                            <img id="voltar" align="right" hspace="5" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif';" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif';" style="border:none;cursor:hand;" onClick="window.location.href='/FrontOfficeWeb/index.jsp';"/>
                          </td>
                        </tr>
                        </form>
                    </table>
                  <div>
                    <img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="1">
                  </div>
                 <iframe name="buscaCliente" src="blank.jsp" id="buscaCliente" width="770" frameborder="0" height="360"></iframe>
                <vivo:quadroFlutuante id="dvPesquisaNome" idIframe="ifrmPesqNome"           width="580" height="80"  spacesTop="65" spacesLeft="75" display="none" url="<%=request.getContextPath()%>" label="Pesquisa de Clientes" scroll="no" onclick="controlCombos(); focoValorPesquisa();"/>
                <vivo:quadroFlutuante id="dvResultados"   idIframe="ifrmPesqNomeResultados" width="750" height="390" spacesTop="0"  spacesLeft="8"  display="none" url="<%=request.getContextPath()%>" label="Pesquisa de Clientes" scroll="no" onclick="controlCombos(); focoValorPesquisa();"/>
            </vivo:quadro> 
        </vivo:quadro>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>