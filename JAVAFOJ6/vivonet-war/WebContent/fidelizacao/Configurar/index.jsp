<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:section name="headerSection">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/abas.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/rfidelizacaoconf.js" charset="ISO-8859-1"></script>
    <%-- Script para criacao da abas --%>
    <script>
        var aba = new Abas();
        var abaAparelho;
        <%--var abaOfertas;--%>
        var abaMensagens;
        var abaPontos;
        var abaScript;
        var abaBonus;
        var abaPlano;
        var abaSimular;
        var abaDescontos;
        var abaRelDesc;

        function initPagina(){
             eval(zsa);
            <acesso:controlHiddenItem nomeIdentificador="fid_cindex_aparelho">
				abaAparelho     = aba.criaAba("Matriz de Aparelhos", "<%=request.getContextPath()%>/fidelizacao/Configurar/ConfigurarMatrizAparelho/begin.do");
                document.getElementById(abaAparelho).onclick = new Function("mostra(this); eval(zsa);");
			</acesso:controlHiddenItem>
                abaParcelamento= aba.criaAba("Parcelamento", "<%=request.getContextPath()%>/fidelizacao/Configurar/Parcelamento/begin.do");
                document.getElementById(abaParcelamento).onclick = new Function("mostra(this); eval(zsa);");

                abaDescontos= aba.criaAba("Descontos", "<%=request.getContextPath()%>/fidelizacao/Configurar/CadastrarDescontos/begin.do");
                document.getElementById(abaDescontos).onclick = new Function("mostra(this); eval(zsa);");

                abaRelDesc= aba.criaAba("Associar Descontos", "<%=request.getContextPath()%>/fidelizacao/Configurar/RelMatrizDescGrupo/begin.do");
                document.getElementById(abaRelDesc).onclick = new Function("mostra(this); eval(zsa);");

            <acesso:controlHiddenItem nomeIdentificador="fid_cindex_migracao">
				abaMigracao     = aba.criaAba("Migração","<%=request.getContextPath()%>/fidelizacao/Configurar/ConfigurarMigracao/begin.do");
                document.getElementById(abaMigracao).onclick = new Function("mostra(this); eval(zsa);");
			</acesso:controlHiddenItem>

				abaScript       = aba.criaAba("Matriz Script","<%=request.getContextPath()%>/fidelizacao/Configurar/MatrizTemplate/begin.do");
                document.getElementById(abaScript).onclick = new Function("mostra(this); eval(zsa);");

                abaSimular = aba.criaAba("Simulador", "<%=request.getContextPath()%>/fidelizacao/Configurar/simulador/begin.do");
                document.getElementById(abaSimular).onclick = new Function("mostra(this); top.frameApp.mostrar_div();");

            <%--acesso:controlHiddenItem nomeIdentificador="fid_cindex_script">
				abaScript       = aba.criaAba("Matriz Script","<%=request.getContextPath()%>/fidelizacao/Configurar/ConfigurarMatrizScript/begin.do");
                document.getElementById(abaScript).onclick = new Function("mostra(this); eval(zsa);");
			</acesso:controlHiddenItem--%>
            <%--acesso:controlHiddenItem nomeIdentificador="fid_cindex_oferta">
				abaOfertas      = aba.criaAba("Matriz de Ofertas","<%=request.getContextPath()%>/fidelizacao/Configurar/ConfigurarMatrizOferta/begin.do");
                document.getElementById(abaOfertas).onclick = new Function("mostra(this); eval(zsa);");
			</acesso:controlHiddenItem--%>
            <acesso:controlHiddenItem nomeIdentificador="fid_cindex_mensagem">
				abaMensagens    = aba.criaAba("Mensagens","<%=request.getContextPath()%>/fidelizacao/Configurar/ConfigurarMensagemResultado/begin.do");
                document.getElementById(abaMensagens).onclick = new Function("mostra(this); eval(zsa);");
			</acesso:controlHiddenItem>
            <acesso:controlHiddenItem nomeIdentificador="fid_cindex_excecao">
				abaExcecao      = aba.criaAba("Oferta de Exceção","<%=request.getContextPath()%>/fidelizacao/OfertaExcecao/begin.do");
                document.getElementById(abaExcecao).onclick = new Function("mostra(this); eval(zsa);");
			</acesso:controlHiddenItem>

            <acesso:controlHiddenItem nomeIdentificador="fid_cindex_aparelho">
				aba.selecionaAba(document.getElementById(abaAparelho), true);
			</acesso:controlHiddenItem>
            aba.habilitaAbas();
        }

        function fecharMigra(lPesquisa){
            document.getElementById('ifrmCfgMigra').src = 'about:blank';
            document.getElementById('dvCfgMigra').style.display = 'none';
            if(lPesquisa){
                top.frameApp.fraAbas.pesquisar();
            }
        }
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_cindex_verpagina">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="5"></div>
        <vivo:sessao id="configRetencao" width="790" height="470" label="Retenção" operacoes="Configuração" scroll="no">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr id="trAbas">
            </table>
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="tbl_bgGray" height="425" valign="top" width="780">
                        <iframe id="fraAbas" width="778" height="423" frameborder="0" scrolling="no" src="<%=request.getContextPath()%>/fidelizacao/Configurar/nada.html"></iframe>
                    </td>
                </tr>
                <tr>
                    <td>
                        <img onmouseup="window.location.href='sair.do';" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" border="0" />
                    </td>
                </tr>
            </table>
        </vivo:sessao>
        <script language="javaScript">initPagina(); </script>
        <vivo:quadroFlutuante id="dvNrProcesso" scroll="true" idIframe="ifrmNrProcesso" height="400" width="700" spacesTop="100" spacesLeft="50" display="none" url="<%=request.getContextPath()%>" label="Dados do Aparelho"/>
        <vivo:quadroFlutuante id="dvMatOfertas" idIframe="ifrmMatOfertas" height="400" width="750" spacesTop="100" spacesLeft="25" display="none" url="<%=request.getContextPath()%>" label="Consulta à Matriz de Ofertas"/>
        <vivo:quadroFlutuante id="dvDetalheOfertas" idIframe="ifrmDetalheOfertas" height="400" width="750" spacesTop="100" spacesLeft="25" display="none" url="<%=request.getContextPath()%>" label="Detalhamento de Oferta"/>
        <vivo:quadroFlutuante id="dvCfgMigra" idIframe="ifrmCfgMigra" height="400" width="780" spacesTop="100" spacesLeft="5" display="none" url="<%=request.getContextPath()%>" label="Configuração de Migração" onclick="fecharMigra(false);"/>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
