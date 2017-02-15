<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script>
            function mostraScript(){
                document.forms[0].method = "POST";
                document.forms[0].action = "ActionObtemScript.do";
                document.forms[0].submit();
            }

            function abreGeral(){
                dvNrProcesso.style.display = '';
                document.forms["frmHistorico"].target = "ifrmNrProcesso";
                document.forms["frmHistorico"].action = "/FrontOfficeWeb/fidelizacao/consultaHistorico.jsp";
                document.forms["frmHistorico"].submit();
                //dv_dvNrProcesso.innerText = sMsg;
            }

            function abreVaiPensar(){
                dvVaiPensar.style.display = '';
                document.forms["frmSelecao"].target = "ifrmVaiPensar";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/fidelizacao/ConsultaMatrizOferta/mensagemEncerramentoVaiPensar.jsp";
                document.forms["frmSelecao"].submit();
            }

            function abreCancelarLinha(){
                dvCancelarLinha.style.display = '';
                document.forms["frmSelecao"].target = "ifrmCancelarLinha";
                document.forms["frmSelecao"].action = "/FrontOfficeWeb/fidelizacao/ConsultaMatrizOferta/mensagemEncerramentoCancelarLinha.jsp";
                document.forms["frmSelecao"].submit();
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <!--Quadro com o script de retencao, este dive fica oculto até o usuario escolher qual a linha que esta sendo retida-->
        <form action="ActionObtemScript.do" method="post">
            <vivo:quadro id="qdMain" height="270" width="720" label="Script">
            <table width="100%" border="0">
                <tr align="right" valign="top" >
                    <td align="left"><b>
                    Qual é o Destino Previsto?</b><br>
                    </tr>
                <tr align="left">
                    <td >
                    <netui:radioButtonGroup dataSource="{}">
                        <netui:radioButtonOption value="1" style="border:none;background-color:#E3ECF4;">TIM</netui:radioButtonOption>
                        <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Oi</netui:radioButtonOption>
                        <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Ficar sem celular</netui:radioButtonOption>
                        <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Mudar de estado</netui:radioButtonOption>
                        <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Mudar plano</netui:radioButtonOption>
                        <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Trocar de Aparelho</netui:radioButtonOption>
                        <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Cancelar Linha</netui:radioButtonOption>
                        <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Mudar de país</netui:radioButtonOption>
                        <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Possui VIVO pós</netui:radioButtonOption>
                        <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Concorrência TIM</netui:radioButtonOption>
                         <br>
                        <netui:radioButtonOption value="" style="border:none;background-color:#E3ECF4;">Migração Pré Pago VIVO</netui:radioButtonOption>

                    </netui:radioButtonGroup>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="2" width="50%">
	                    <img onmouseup="document.forms[0].action='showArvoreScript.do?acao=arvoreScript';document.forms[0].submit()"
	                    	 class="button"
	                    	 src="/FrontOfficeWeb/resources/images/bt_navegscript_nrml.gif" border="0" />
	                    <img onmouseup="document.forms[0].action=document.forms[0].action+'?acao=matrizOferta';document.forms[0].submit();"
	                    	 src="/FrontOfficeWeb/resources/images/bt_proxima_nrml.gif" border="0" />
                    </td>
                </tr>
            </table>
            </vivo:quadro>
        </form>
        <!-- quadro com o historico de atendimento do cliente-->
    </div>
        <tr>
            <td align="right" valign="baseline" >
                <img src="./../resources/images/bt_manelet_nrml.gif" 
					 class="button"
					 onmouseup="document.forms[0].action=document.forms[0].action+'?acao=manual';document.forms[0].submit()" />
                
                <img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif"
                	 href="/FrontOfficeWeb/fidelizacao/AgendamentoContato/AgendaContatoPtp.jsp?acao=agendarContato"/>
                
                <img onmouseup="document.forms[0].action='sair.do';document.forms[0].submit()"
                	 src="./../resources/images/bt_sair_nrml.gif" />
                
            </td>
        </tr>
    </table>
     </netui-template:section>
</netui-template:template>
