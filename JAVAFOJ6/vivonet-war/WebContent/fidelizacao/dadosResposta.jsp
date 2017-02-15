<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="javascript">
            function fecharjanela(){
                history.back(-1);
            }

            function abreGeral(){
                dvProximaRetido.style.display = '';
                document.forms["frmSelecao"].target = "ifrmProxima";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/fidelizacao/ConsultaMatrizOferta/mensagemEncerramentoRetido.jsp";
                document.forms["frmSelecao"].submit();
            }

            function abreVaiPensar(){
                dvVaiPensar.style.display = '';
                document.forms["frmSelecao"].target = "ifrmVaiPensar";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/fidelizacao/ConsultaMatrizOferta/mensagemEncerramentoVaiPensar.jsp";
                document.forms["frmSelecao"].submit();
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <vivo:body idTable="tbMain" idDiv="dvMain" height="500" width="770">
    <vivo:quadro id="qdMain" height="308" width="750" label="Pontos">
        <table  width="100%" >
            <tr>
                <td align="left" ><b>Cliente:</b></td>
                <td><netui:content value="Paulo José Santos"/></td>
                <td><b>Segmentação:</b></td>
                <td><netui:content value="Diamante 2"/></td>
                <td width="10%" ><b>Carteirização:</b></td>
                <td><netui:content value="Cinco Estrelas"/>
                <td><b>Tipo de Cliente:</b></td>
                <td><netui:content value="Pessoa Física"/>
            </tr>
        </table>
        <vivo:tbTable selectable="true" layoutWidth="650" layoutHeight="30" tableWidth="650" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Linha</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Segmentação</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Rentabilidade</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="5%" type="string">Contrato de Fidelização</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="5%" type="string">Multa Contratual</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Termino do Contrato</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Plano de Serviço</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="5%" type="string">Habilitação</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <vivo:tbRow key="linha1">
                    <vivo:tbRowColumn>(11)9999-8888</vivo:tbRowColumn>
                    <vivo:tbRowColumn>Rubi 3 </vivo:tbRowColumn>
                    <vivo:tbRowColumn>R$500,00</vivo:tbRowColumn>
                    <vivo:tbRowColumn>5478</vivo:tbRowColumn>
                    <vivo:tbRowColumn>R$200,00</vivo:tbRowColumn>
                    <vivo:tbRowColumn>05/05/2004</vivo:tbRowColumn>
                    <vivo:tbRowColumn>50</vivo:tbRowColumn>
                    <vivo:tbRowColumn>10/05/2004</vivo:tbRowColumn>
                </vivo:tbRow>
            </vivo:tbRows>
        </vivo:tbTable>
    <table>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td width="10%">Quantidade:</td>
            <td align="left">
            <netui:radioButtonGroup dataSource="{}">
                <netui:radioButtonOption value="" style="border:0px;">TIM</netui:radioButtonOption>
                <br>
                <netui:radioButtonOption value="" style="border:0px;">Oi</netui:radioButtonOption>
                <br>
                <netui:radioButtonOption value="" style="border:0px;">Ficar sem celular</netui:radioButtonOption>
            </netui:radioButtonGroup>
            </td>
        </tr>

        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td colspan="4" align="right">
	            <img src="/FrontOfficeWeb/resources/images/bt_manelet_nrml.gif" />
	            <a href="http://sac.celular.telerj.net.br/pontos/login"></a>
	            
	            <img onmouseup="window.location.href='sair.do';"
	            	 src="/FrontOfficeWeb/resources/images/bt_anterior_nrml.gif"
	            	 class="button"
	            	 border="0" />
	            	 
	            <img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif"
	            	 class="button"
	            	 onmouseup="window.location.href='/FrontOfficeWeb/fidelizacao/AgendamentoContato/AgendaContatoPtp.jsp?acao=agendarContato';">
	            <img src="/FrontOfficeWeb/resources/images/bt_vaipensar_nrml.gif" onClick="abreVaiPensar();"/>
	            <img src="/FrontOfficeWeb/resources/images/bt_cancellinha_nrml.gif" />
	           	<img src="/FrontOfficeWeb/resources/images/bt_retido_nrml.gif" onClick="abreGeral();" />
            </td>
        </tr>
    </table>
     <form id="frmSelecao" name="frmSelecao" method="post">
        <vivo:quadroFlutuante id="dvProximaRetido" idIframe="ifrmProxima" height="100" width="350" spacesTop="200" spacesLeft="50" display="none" url="<%=request.getContextPath()%>" label=""/>
        <vivo:quadroFlutuante id="dvVaiPensar" idIframe="ifrmVaiPensar" height="100" width="350" spacesTop="200" spacesLeft="50" display="none" url="<%=request.getContextPath()%>" label=""/>
    </form>
    </vivo:quadro> </vivo:body> </netui-template:section>
</netui-template:template>
