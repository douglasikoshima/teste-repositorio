<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="consultorForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultorForm" />
<bean:define id="Tipos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultorForm.associarCR.tipoDocumentoArray"/>
<bean:define id="Segs"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultorForm.associarCR.segmentacoesArray"/>
<bean:define id="Carts" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultorForm.associarCR.carteirizacoesArray"/>
<bean:define id="Cons"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultorForm.associarCR.consultorRelacionamentoArray"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:setAttribute value="Gestão de Clientes" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection"><head></head></netui-template:section>
    <netui-template:section name="bodySection">

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
	<script language="JavaScript">
		function validaDocumento(){
		
			tipo = document.forms[0].elements["associarCR.filtroConsulta.idTipoDocumentoFiltro"].options[document.forms[0].elements["associarCR.filtroConsulta.idTipoDocumentoFiltro"].selectedIndex].value;
			obj = document.getElementById("nrDocumento");

			if (tipo=="CPF"){           //CPF
				if(!validaCPF(obj.value)){
					return "CPF invalido, favor corrigir!";
				}else{
					return "TRUE";
				}
			}else if (tipo=="CNPJ") {   //CNPJ                    
				if(!validaCNPJ(obj.value)){
					return "CNPJ invalido, favor corrigir!";
				}else{
					return "TRUE";
				}
			}else{
				return "TRUE";
			}                
		}

		function escolheMascara(obj){
			tipo = document.forms[0].elements["associarCR.filtroConsulta.idTipoDocumentoFiltro"].options[document.forms[0].elements["associarCR.filtroConsulta.idTipoDocumentoFiltro"].selectedIndex].value;
			var qtdeCaracteres = 25;
			if (tipo=="CPF"){                                       //CPF
				checaCPF(obj);
				qtdeCaracteres = 14;                 
			} else if (tipo=="RNE"){                                //RNE
				qtdeCaracteres = 10;               
			} else if (tipo=="CNPJ"){                               //CNPJ
				checaCNPJ(obj);
				qtdeCaracteres = 18;
			} else if (tipo==0){                    //SELECIONE
				document.getElementById("nrDocumento").value='';
				qtdeCaracteres = 0;
			}
			obj.maxLength = qtdeCaracteres;
		}
		
		
		function realizaPesquisa(){
			tipo = document.forms[0].elements["associarCR.filtroConsulta.idTipoDocumentoFiltro"].options[document.forms[0].elements["associarCR.filtroConsulta.idTipoDocumentoFiltro"].selectedIndex].value;
			if(document.getElementById("idTipoDoc").value==0){
				document.getElementById("nrDocumento").value="";
			}
			if (tipo=="CPF" || tipo=="CNPJ"){
				document.getElementById("nrDocumento").value = limpaInteiro(document.getElementById("nrDocumento").value);                    
			}                
			document.forms[0].action="pesquisaClientes.do";
			document.forms[0].target="ifrmResultado";
			mostrar_div();
			document.forms[0].submit();
		}
		
		function validaPesquisa(){
			if(document.getElementById("idConsultor").value==0){
				if(document.getElementById("idCarteira").value==0 || document.getElementById("idSegmentacao").value==0){
					alert('Selecione um Consultor de Relacionamento ou uma Carteirização e uma Segmentação!');
				}else{
					if(document.getElementById("nrDocumento").value.length>0){
						if(validaDocumento()=="TRUE"){
							realizaPesquisa();
						}else{
							alert(validaDocumento());
						}
					}else{                        
						realizaPesquisa();
					}                        
				}
			}else{
				if(document.getElementById("nrDocumento").value.length>0){
						if(validaDocumento()=="TRUE"){
							realizaPesquisa();
						}else{
							alert(validaDocumento());
						}
					}else{                        
						realizaPesquisa();
					}
			}
		}
		
		function limpar(){
			document.forms[0].action="montaListas.do";
			document.forms[0].target="_parent";
			mostrar_div();
			document.forms[0].submit();
		}
	</script> 
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <script>
    mostrar_div();
    </script>
    <script for="window" event="onload">
        oculta_div();
    </script>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="cli_ka_verpagina">
    
	<vivo:sessao id="qdMain" height="470" width="790" label="Associar Consultor de Relacionamento com Cliente > Inclusão" operacoes="Status" scroll="no">
    <vivo:quadro height="145" id="operacoes" width="780" label="Consulta" scroll="no">
    <form action="montaListas.do">
    <table align="center" width="100%">
        <tr>
            <td align="center">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
            <table width="764">
                <tr>
                    <td height="25" width="75">Consultor:</td>
                    <td style="text-align:left;padding-left:3px">
                    <html:select name="consultorForm" property="associarCR.filtroConsulta.idConsultorFiltro" styleId="idConsultor" style="width:220px">
                    <html:option value="0">-- Selecione --</html:option>
                    <html:options collection="Cons" property="idConsultor" labelProperty="nmConsultor"/> </html:select>
                    </td>
                </tr>
            </table>
            <table width="764">
                <tr>
                    <td height="25" width="74">Cliente:</td>
                    <td width="240">
						<html:text name="consultorForm" property="associarCR.filtroConsulta.nmClienteFiltro" style="width:220px" styleId="nmCliente"/>
					</td>
                    <td width="62">Documento:</td>
                    <td width="230">
						<html:select name="consultorForm" property="associarCR.filtroConsulta.idTipoDocumentoFiltro" styleId="idTipoDoc" onchange="document.getElementById('nrDocumento').value=''" style="width:220px;">
							<html:option value="0">-- Selecione --</html:option>
							<html:options collection="Tipos" property="idTipoDocumento" labelProperty="dsTipoDocumento"/>
						</html:select>
                    </td>
                    <td>
						<html:text name="consultorForm" property="associarCR.filtroConsulta.nrDocumentoFiltro" styleId="nrDocumento" onkeyup="escolheMascara(this)"/>
                    </td>
                </tr>
            </table>
            <table width="764">
                <tr>
                    <td height="25" width="75">Carterização:</td>
                    <td width="230" style="text-align:left;padding-left:3px">
						<html:select name="consultorForm" property="associarCR.filtroConsulta.idCarteirizacaoFiltro" styleId="idCarteira"style="width:220px">
							<html:option value="0">-- Selecione --</html:option>
							<html:options collection="Carts" property="idCarteirizacao" labelProperty="dsCarteirizacao"/> </html:select>
						</td>
                    <td width="30">Segmentação:</td>
                    <td>
						<html:select name="consultorForm" property="associarCR.filtroConsulta.idSegmentacaoFiltro" styleId="idSegmentacao" style="width:220px">
							<html:option value="0">-- Selecione --</html:option>
							<html:options collection="Segs" property="idSegmentacao" labelProperty="dsSegmentacao"/>
						</html:select>
					</td>
                </tr>
            </table>
            <table width="749" align="center">
                <tr>
                    <td>
                        <img src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
						     onClick="window.location.href='/FrontOfficeWeb/index.jsp';"
						     vspace="5"
							 style="border:0px;cursor:hand;"
							 hspace="6"
							 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
							 onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'"
							 onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'"/>
                    </td>
                    <td align="right">
                    <img style="border:1px;" vspace="10" hspace="5" onClick="limpar(); return false" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" />
                    <acesso:controlHiddenItem nomeIdentificador="cli_ka_pesquisar">
						<img style="border:0px;"
						     vspace="10"
							 onClick="validaPesquisa(); return false"
							 src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" />
					</acesso:controlHiddenItem>                    
                    </td>
                </tr>
            </table>
            </td>
        </tr>
    </table>
    </form> 
	</vivo:quadro>
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <iframe name="ifrmResultado" marginwidth="0" marginheight="0" scrolling="no" id="tabela" frameborder="0" width="780" height="200"></iframe>
    <br>
    <vivo:quadroFlutuante id="dvInclusao" idIframe="ifrmIARelacionamento" width="400" height="100" spacesTop="170" spacesLeft="200" display="none" url="<%=request.getContextPath()%>" label="Associar Consultor de Relacionamento"/>
    </vivo:sessao>
    </acesso:controlHiddenItem>
    </body>
    </netui-template:section>
</netui-template:template>
