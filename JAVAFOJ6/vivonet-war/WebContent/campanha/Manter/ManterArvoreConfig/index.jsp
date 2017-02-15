<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptCampanhaActionForm" />
<bean:define id="aCampanha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptCampanhaActionForm.listaCampanha" />
<bean:define id="aSubCampanha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptCampanhaActionForm.listaSubCampanha" />
<bean:define id="aVersao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptCampanhaActionForm.listaVersao" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
    <head>
     <script>   
        function consultaIFrameSubCampanha()  {					 
            
            /*
             * Caso a camapanha seja alterada limpo qualquer
             * aba aberta                        
            */       	
            var objForm = document.forms["scriptCampanhaActionForm"];
            document.getElementById("ifrmAbasCampanha").src = "nada.html";                        
            abaSelected(btAba, "");
                    
            //Monta o path seleção                     
            objForm.target = "iframe";
            objForm.action = "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/ObtemListaAction.do";
            objForm.submit();                         
            objForm.subCampanhaSelecionada.options.length = 0;                 
            objForm.versaoSelecionada.options.length = 0;                         
            mostrar_div();
        }
        
        function consultaIFrameVersao() {					 
            
            /*
             * Caso a camapanha seja alterada limpo qualquer
             * aba aberta                        
            */       	
            var objForm = document.forms["scriptCampanhaActionForm"];
            document.getElementById("ifrmAbasCampanha").src = "nada.html";                        
            abaSelected(btAba, "");        
            
            //Monta o path seleção                     
            objForm.target = "iframe";
            objForm.action = "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/obterVersao.do";
            objForm.submit();                         
            objForm.versaoSelecionada.options.length = 0;                 
            mostrar_div();
        }
     </script>
<SCRIPT FOR=window EVENT=onload>
    oculta_div();
</script>
        <title> Web Application Page </title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    </head>
    </netui-template:section> <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cam_icam_verpagina">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
    <script>   
        var moveToolTip = true;
                            
        xBump=yBump=10;
        MSIE=document.all;
        NS6=document.getElementById&&!document.all;
        if(MSIE||NS6){
            ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
        }

        function carregaToolTip(content) {
            ttipObj.innerHTML=content;
            ttipObj.style.display='';
        }
    </script>

    <vivo:sessao id="configuracao" width="790" height="470" label="Campanha" operacoes="Configuração" scroll="no">
    <form id="frmSelecao" name="frmSelecao" method="post">
        <input type="hidden" name="idCampanha" value="">
        <input type="hidden" name="idSubCampanha" value="">
        <input type="hidden" name="dsCampanha" value="">
    </form>
    <form name="scriptCampanhaActionForm" id="scriptCampanhaActionForm" action="ScriptCampanhaAction.do" method="post">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <table border="0" width="100%" >
            <tr>
                <td width="20%" valign="middle">                
                    &nbsp;&nbsp;<b>Campanha</b>
                </td>
                <td width="20%" valign="middle">
                    &nbsp;&nbsp;<b>Sub Campanha</b>
                </td>
                <td width="60%" valign="middle">            
                    &nbsp;&nbsp;<b>Versão</b>
                </td>
            </tr>        
            <tr>
                <td width="20%" valign="middle">                
                    <table border="0" width="100%" height="100%" cellpadding="3" >
                        <tr>
                            <td>
                            <html:select name="Form" property="campanhaSelecionada" size="1" style="width=200px;height=10px" onchange="consultaIFrameSubCampanha(); focaTipCampos(this.options[this.selectedIndex].text,this, 160, 20, 15);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 160, 20, 15);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 160, 20, 15);" onblur="HideTip();" onmouseout="HideTip();">
                                <html:option value=""></html:option>
                                <html:options collection="aCampanha" property="codigo" labelProperty="sigla"/>
                            </html:select>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="20%" valign="middle">
                <table width="100%" height="100%" cellpadding="3"  border="0">
                    <tr>
                        <td>
                            <html:select name="Form" property="subCampanhaSelecionada" size="1" style="width=200px;height=10px"  onchange="consultaIFrameVersao(); focaTipCampos(this.options[this.selectedIndex].text,this, 160, 240, 15);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 160, 240, 15);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 160, 240, 15);" onblur="HideTip();" onmouseout="HideTip();">
                                <html:option value=""></html:option>
                                <html:options collection="aSubCampanha" property="codigo" labelProperty="nmSubCampanha"/> 
                            </html:select>
                        </td>
                    </tr>
                </table>    
                </td>
                <td width="60%" valign="middle">            
                <table width="100%" height="100%" cellpadding="3"  border="0">
                    <tr>
                        <td>
                            <html:select name="Form" property="versaoSelecionada" size="1" style="width=100px;height=10px">
                                <html:option value=""></html:option>
                                <html:options collection="aVersao" property="codigo" labelProperty="descricao"/> 
                            </html:select>
                        </td>
                        <td>
                            <acesso:controlHiddenItem nomeIdentificador="cam_icam_incl">
                                <img onClick="selecionarSubCampanha();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" style="border:none;cursor:hand;" alt="Incluir Sub Campanha"/>
                            </acesso:controlHiddenItem>
                        </td>
                        <td>
                            <acesso:controlHiddenItem nomeIdentificador="cam_icam_sub">
                                <img onClick="if ( selecionarArvore() ){ abaSelected(btAba, bt01); }" src="/FrontOfficeWeb/resources/images/bt_carregar_nrml.gif" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_carregar_over.gif'" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_carregar_nrml.gif'" style="border:none;cursor:hand;" alt="Carregar Versão selecionada" />
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
        </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <table cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
                <td>
                    <vivo:abaGrupo id="btAba" width="80%" height="10px" styleClass="abatexto">
                        <acesso:controlHiddenItem nomeIdentificador="cam_icam_scr"><vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);selecionarArvore();" value="Script Campanha" select="n"/></acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="cam_icam_assc"><vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);selecionarAssociar();" value="Associar Listas" select="n"/></acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="cam_icam_ccp"><vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);selecionarCopia();" value="Cópia de Perguntas"/></acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="cam_icam_pra"><vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);selecionarMetricas();" value="Parametrização"/></acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="cam_icam_sit"><vivo:abaItem id="bt06" onclick="abaSelected(btAba, bt06);selecionarSimulacao();" value="Simulação"/></acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="cam_icam_prio"><vivo:abaItem id="bt07" onclick="abaSelected(btAba, bt07);selecionarPrioridade();" value="Prioridade"/></acesso:controlHiddenItem>
                    </vivo:abaGrupo>
                </td>
            </tr>
            <tr>
                <td class="tbl_bggray"><IFRAME id="ifrmAbasCampanha" name="ifrmAbasCampanha" WIDTH="100%" HEIGHT="365" FRAMEBORDER="0" SCROLLING=no SRC="nada.html"></IFRAME></td>
            </tr>
            <tr>
                <td><img id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="top.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;return false" hspace="5" vspace="5"/></td>
            </tr>
        </table>   
        <iframe scrolling="yes" style="visibility:hidden;" name="iframe" height="1px" width="1px"></iframe>
        <vivo:quadroFlutuante id="dvNrProcesso" idIframe="ifrmNrProcesso" height="335" width="725" spacesTop="1" spacesLeft="3" display="none" url="<%=request.getContextPath()%>" label="Atendimento"/>
    </form>
    </vivo:sessao>
 <script language="Javascript">    
        //Configura o processamento
        document.forms["frmSelecao"].target = "ifrmAbasCampanha";
		var idCampanha = -1;
		var idSubCampanha  = -1;
		var txtCampanha = "";
		
		function abreGeral(sUrl)
		{
            
				dvNrProcesso.style.display = '';
				
				document.forms["frmSelecao"].target = "ifrmNrProcesso";
				document.forms["frmSelecao"].action = sUrl;
				document.forms["frmSelecao"].submit();
		}
    
        function selecionarAssociar() 
		{
			LiberaCombo()
			if(VerificaCodigo())
			{
				document.forms.frmSelecao.action = "../../CarregarLista/begin.do";
				document.frmSelecao.submit();	
                mostrar_div();	
			}
            else
            {
				alert("É necessário selecionar uma Campanha, Sub Campanha e Versão\npara esta operação!");
			}            
        }
        
        function selecionarCopia()
		{
			LiberaCombo()
			if(VerificaCodigo())
			{
				document.forms.frmSelecao.action = "../ManterSubCampanha/begin.do?page=copia";
				document.frmSelecao.submit();			
                mostrar_div();		
			}	
            else
            {
				alert("É necessário selecionar uma Campanha, Sub Campanha e Versão\npara esta operação!");
			}            
        }
        
        function selecionarMetricas() 
		{
			LiberaCombo()
			if(VerificaCodigo())
			{
				//ifrmAbasCampanha.location.href = "../ManterSubCampanha/begin.do?page=parametros&idCampanha=" + idCampanha + "&idSubCampanha=" + idSubCampanha;
				document.forms.frmSelecao.action = "../ManterSubCampanha/begin.do?page=parametros";
				document.frmSelecao.submit();
                mostrar_div();				
			}
            else
            {
				alert("É necessário selecionar uma Campanha, Sub Campanha e Versão\npara esta operação!");
			}            
			
        }
        
        function selecionarSimulacao() 
		{
			LiberaCombo()
			if(VerificaCodigo())
			{
				//ifrmAbasCampanha.location.href = "../../SimulacaoPerguntas/begin.do?idCampanha=" + idCampanha + "&idSubCampanha=" + idSubCampanha;
				document.forms.frmSelecao.action = "../../SimulacaoPerguntas/begin.do";
				document.frmSelecao.submit();
                mostrar_div();
			}
            else
            {
				alert("É necessário selecionar uma Campanha, Sub Campanha e Versão\npara esta operação!");
			}            
         }   
		
		function selecionarArvore() {
			LiberaCombo()
			if(VerificaCodigo()) {
                document.forms["scriptCampanhaActionForm"].target = "ifrmAbasCampanha";
                document.forms["scriptCampanhaActionForm"].action = "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/CarregarAction.do";
                document.forms["scriptCampanhaActionForm"].submit();                         
                mostrar_div();
                return true;
			} else {
				alert("É necessário selecionar uma Campanha, Sub Campanha e Versão\npara esta operação!");
                return false;
			}
        }

		function selecionarSubCampanha()
		{
			LiberaCombo();
						
			idCampanha  = getIdSelecionado(document.forms["scriptCampanhaActionForm"].campanhaSelecionada);
			txtCampanha = getTextSelecionado(document.forms["scriptCampanhaActionForm"].campanhaSelecionada);
			
            if(idCampanha == "" || idCampanha <= 0)
            {
                alert("É necessário selecionar uma campanha antes de incluir uma subcampanha!");
                return false;
            }
            
            abaSelected(btAba, bt01);
                        
			document.forms.frmSelecao.idCampanha.value = idCampanha;
			document.forms.frmSelecao.dsCampanha.value = txtCampanha;
            
            document.forms.frmSelecao.action = "../ManterSubCampanha/begin.do?page=subcampanha&acao=incluir";
            document.frmSelecao.submit();
            mostrar_div();
        }
		
		function selecionarPrioridade() 
		{
            LiberaCombo()
			if(VerificaCodigo())
			{
				document.forms.frmSelecao.action = "../../Configurar/ConfigurarPrioridade/begin.do";
				document.frmSelecao.submit();
                mostrar_div();
			}	
            else
            {
				alert("É necessário selecionar uma Campanha, Sub Campanha e Versão\npara esta operação!");
			}            
        }
		
		function VerificaCodigo()
		{
			var idCampanha              = getIdSelecionado(document.forms["scriptCampanhaActionForm"].campanhaSelecionada);
			var idSubCampanhaFixa       = getIdSelecionado(document.forms["scriptCampanhaActionForm"].subCampanhaSelecionada);
            var idSubCampanhaHistorico  = getIdSelecionado(document.forms["scriptCampanhaActionForm"].versaoSelecionada);
			var txtCampanha             = getTextSelecionado(document.forms["scriptCampanhaActionForm"].campanhaSelecionada);
			
			document.forms.frmSelecao.idCampanha.value    = idCampanha;
			document.forms.frmSelecao.idSubCampanha.value = idSubCampanhaHistorico;											
			document.forms.frmSelecao.dsCampanha.value    = txtCampanha;		
			
			document.frmSelecao.target = "ifrmAbasCampanha";
            
			return ( ( idCampanha > 0 ) && ( idSubCampanhaFixa > 0 ) && (idSubCampanhaHistorico > 0 ) ) ;
		}
		
		function getIdSelecionado(obj)
		{
			idSelecionado = -1;
			if(obj.selectedIndex >=0)
				idSelecionado = obj.options[obj.selectedIndex].value;
				
			return 	idSelecionado;
		}
		
		function getTextSelecionado(obj)
		{
			txtSelecionado = "";
			if(obj.selectedIndex >=0)
				txtSelecionado = obj.options[obj.selectedIndex].text;
				
			return txtSelecionado;
		}

		if(VerificaCodigo())
        {
			abaSelected(btAba, bt01);selecionarArvore();
		}

		function LiberaCombo()
		{
			document.forms["scriptCampanhaActionForm"].campanhaSelecionada.disabled = false;
			document.forms["scriptCampanhaActionForm"].subCampanhaSelecionada.disabled = false;
            document.forms["scriptCampanhaActionForm"].versaoSelecionada.disabled = false;
		}
		
        function oculta_div(){
            top.frames.frameApp.document.getElementById("idAnime").style.display="none";        
        }
        
        function mostrar_div(){
            top.frames.frameApp.document.getElementById("idAnime").style.display="block";
        }    
    </script>

    </acesso:controlHiddenItem> </netui-template:section>
</netui-template:template>