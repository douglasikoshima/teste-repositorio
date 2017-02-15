<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
    <!-- Script para criacao da abas -->
    <script>
    

        var aba = new Abas();
        var abaCampanha;
        var abaSubCampanha;
        var abaMotivo;       
        var abaSubMotivo;       
		var abaPergunta;
        //var abaTipoCampanha;    
        
        
        function desabilitaSubmit()
        {
            aba.desabilitaAbas();
        }
        
        function habilitaSubmit()
        {
            aba.habilitaAbas();
        }
        
        function initPagina()
        {
            
            //abaTipoCampanha = aba.criaAba("Tipos de Campanha", "/FrontOfficeWeb/campanha/Manter/ManterTipoCampanha/begin.do");
            abaCampanha = aba.criaAba("Campanhas", "/FrontOfficeWeb/campanha/Manter/ManterCampanha/begin.do");
            
            abaPriCampanhas = aba.criaAba("Priorização de Campanhas", "/FrontOfficeWeb/campanha/Configurar/ConfigurarPrioridadeCampanha/begin.do");
            
			abaMotivo = aba.criaAba("Motivos", "/FrontOfficeWeb/campanha/Manter/ManterMotivo/begin.do");
			abaSubMotivo = aba.criaAba("Sub Motivos", "/FrontOfficeWeb/campanha/Manter/ManterSubMotivo/begin.do");
            
            
            /**Inicio do setando o loader***
            *******************************/
            document.getElementById(abaCampanha).onclick = new Function("mostra(this);top.frameApp.mostrar_div()");
            
            document.getElementById(abaMotivo).onclick = new Function("mostra(this);top.frameApp.mostrar_div()");
            
            document.getElementById(abaSubMotivo).onclick = new Function("mostra(this);top.frameApp.mostrar_div()");
            
            document.getElementById(abaPriCampanhas).onclick = new Function("mostra(this);top.frameApp.mostrar_div()");
                        
            /**Fim do setando o loader****
            *******************************/
                
            aba.selecionaAba(document.getElementById(abaCampanha), true);
            aba.habilitaAbas();
        }
        
                
        function selecionaAbaTipoCampanha()
        {
            aba.selecionaAba(document.getElementById(abaTipoCampanha), false);
        }
		function selecionaAbaCampanha()
        {
            aba.selecionaAba(document.getElementById(abaCampanha), false);
        }
        
        function selecionaAbaSubCampanha()
        {
            aba.selecionaAba(document.getElementById(abaSubCampanha), false);
        }
		function selecionaAbaMotivo()
        {
            aba.selecionaAba(document.getElementById(abaMotivo), false);
        }
        
        
        function selecionaAbaSubMotivo()
        {
            aba.selecionaAba(document.getElementById(abaSubMotivo), false);
        }
        
		 function selecionaAbaPergunta()
        {
            aba.selecionaAba(document.getElementById(abaPergunta), false);
        }
        
       
    </script> 
    <SCRIPT FOR=window EVENT=onload>
        top.frameApp.mostrar_div();
        initPagina();
    </script>    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cam_mmanut_verpagina">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <vivo:sessao id="manutencao" width="790" height="470" label="Campanha" operacoes="Manutenção" scroll="no">
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
    <table border="0" cellspacing="0" cellpadding="0">
        <tr id="trAbas">
    </table>
    <table width="780" cellpadding="0" cellspacing="5" class="tbl_bgGray">
        <tr>
            <td valign="top">
            <IFRAME ID=fraAbas WIDTH="765" HEIGHT="400" FRAMEBORDER=0 SCROLLING=NO SRC="/FrontOfficeWeb/campanha/Manter/nada.html"></IFRAME>
            </td>
        </tr>
        <tr>
            <td align="right">
            <img id="voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" onClick="top.location.href='/FrontOfficeWeb/index.jsp'; return false" style="border:none;cursor:hand;return false" hspace="5" vspace="5"/>
            </td>
        </tr>
    </table>
    </vivo:sessao>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
