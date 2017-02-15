<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
	<netui-template:setAttribute name="title" value="Vivo Net >> Campanha"/>
    <netui-template:setAttribute name="modulo" value="Campanha"/>
    <netui-template:section name="headerSection">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
    <!-- Script para criacao da abas -->
    <script>
    

        var aba = new Abas();
		var abaAgendar;
        var abaCarregar;
        var abaExecutar;
        var abaHistorico;
        var abaRelatorio;
		var abaSimular;
		var abaManter;
		var abaConfigurar;
                        
            
        
        
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
            
            
			abaAgendar = aba.criaAba("Agendar Contato", "/FrontOfficeWeb/campanha/AgendarContato/begin.do");
			abaCarregar = aba.criaAba("Carregar Lista", "/FrontOfficeWeb/campanha/CarregarLista/begin.do");
			abaExecutar = aba.criaAba("Executar Campanha", "/FrontOfficeWeb/campanha/ExecutarCampanha/begin.do");
            abaHistorico = aba.criaAba("Histórico Campanhas", "/FrontOfficeWeb/campanha/HistoricoCampanha/begin.do");
            abaRelatorio = aba.criaAba("Relatórios", "/FrontOfficeWeb/campanha/Relatorio/begin.do");
			abaSimular = aba.criaAba("Simular Campanha", "/FrontOfficeWeb/campanha/SimulacaoPerguntas/begin.do");
			abaManter = aba.criaAba("Manter", "/FrontOfficeWeb/campanha/Manter/begin.do");
            abaConfigurar = aba.criaAba("Script", "/FrontOfficeWeb/campanha/Configurar/ConfigurarScript/begin.do");
			abaConfigurar = aba.criaAba("Campanha", "/FrontOfficeWeb/campanha/GestaoCampanha/begin.do");        
			
            aba.selecionaAba(document.getElementById(abaAgendar), true);
            aba.habilitaAbas();
        }
        
        function selecionaAbaAgendar()
        {
            aba.selecionaAba(document.getElementById(abaAgendar), false);
        }
		
		function selecionaAbaCarregar()
        {
            aba.selecionaAba(document.getElementById(abaCarregar), false);
        }
                
        function selecionaAbaExecutar()
        {
            aba.selecionaAba(document.getElementById(abaExecutar), false);
        }
        
        function selecionaAbaHistorico()
        {
            aba.selecionaAba(document.getElementById(abaHistorico), false);
        }
              
        function selecionaAbaRelatorio()
        {
            aba.selecionaAba(document.getElementById(abaRelatorio), false);
        }
       
	                 
        function selecionaAbaSimular()
        {
            aba.selecionaAba(document.getElementById(abaSimular), false);
        }
		
		function selecionaAbaManter()
        {
            aba.selecionaAba(document.getElementById(abaManter), false);
        }
		
		function selecionaAbaConfigurar()
        {
            aba.selecionaAba(document.getElementById(abaConfigurar), false);
        }
		
		function selecionaAbaCampanha()
        {
            aba.selecionaAba(document.getElementById(abaCampanha), false);
        }


    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    
    <table border="0" cellspacing="0" cellpadding="0">
        <tr><td height="2"></td></tr>
    </table>
    <table border="0" cellspacing="0" cellpadding="0">
        <tr id="trAbas">
    </table>
    <table width="790" cellpadding="1" cellspacing="10" bgcolor="#F5F5F5" class="BorderTable1">
    <tr>
        <td bgcolor="#0092DD" valign="top">
            <IFRAME ID=fraAbas WIDTH="780" HEIGHT="500" FRAMEBORDER=0 SCROLLING=NO SRC="/FrontOfficeWeb/fidelizacao/Configurar/nada.html"></IFRAME>
        </td>
    </tr>
    </table>
    <script language="javaScript">
        initPagina();
    </script>
    </netui-template:section>
</netui-template:template>
