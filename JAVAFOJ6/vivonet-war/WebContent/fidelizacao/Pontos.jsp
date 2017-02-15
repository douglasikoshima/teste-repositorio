<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/fidelizacao-pontos.js" ></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="fid_ptos_verpagina">
            <div style="width:764px;height:331px;overflow:auto;margin-bottom:1px solid #adadad;">
            <jsp:include page="informacoesCliente.jsp"/>
            <vivo:quadro id="qdMain" height="248" width="760" label="Pontos" scroll="no">
            <form id="frmSelecao" name="frmSelecao" method="post">
                <input type="hidden" name="CODIGO" value="">
            </form>
            <html:form action="/fidelizacao/reterPontos.do" method="post">
                <input type="hidden" name="tipoEncerramento" value="3"/>
                <table width="100%">
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="18%">Quantidade:</td>
                        <td width="15%" align="left">
                            <html:text property="quantidade" styleClass="textfield" size="14" maxlength="6" onkeyup="checaInteiro(this)"/>
                        </td>
                        <td>
                        <acesso:controlHiddenItem nomeIdentificador="fid_ptos_pontos">
        					<img style="border:none;" src="/FrontOfficeWeb/resources/images/bt_progpts_nrml.gif" onClick="abreSimuladorPontos(); return false"/>
        				</acesso:controlHiddenItem>
                        </td>
                    </tr>
                    <acesso:controlHiddenItem nomeIdentificador="fid_ptos_excecao">
                    <tr>
                        <td><b>Oferta de Exceção:</b></td>
                        <td>
                            <html:checkbox property="inExcecao" style="border:none;background-color:#E3ECF4;"/>
                        </td>
                    </tr>
                    </acesso:controlHiddenItem>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="140"></div>
                <table width="100%">
                    <tr>
                        <td>
                            <img onmouseup="document.forms[1].action='voltarOfertas.do';document.forms[1].submit()"
                            	 class="button"
                            	 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                            	 border="0"
                            	 onClick="parent.parent.mostrar_div();"/>
                        </td>
                        <td colspan="6" align="center">
                            <acesso:controlHiddenItem nomeIdentificador="fid_ptos_manualeletronico">
                                <a href="http://intranet.vivo-sp.com.br/manual/retencao/index.htm" target="_blank">
                                	<img src="/FrontOfficeWeb/resources/images/bt_manelet_nrml.gif" />
                                </a>
            				</acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="fid_ptos_agendar">
                                <img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_agendar_over.gif'" style="border:none;cursor:hand;" border="0" onClick="agendar();">
            				</acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="fid_ptos_vaipensar">
                                <img src="/FrontOfficeWeb/resources/images/bt_vaipensar_nrml.gif"
                                	 border="0"
                                	 onClick="cancela('vaipensar');return false;" />
            				</acesso:controlHiddenItem>
                        </td>
                        <td align="right">
                        <acesso:controlHiddenItem nomeIdentificador="fid_ptos_reter">
                             <img onClick="reter();" src="/FrontOfficeWeb/resources/images/bt_reter_nrml.gif" border="0" style="border:none;cursor:hand;" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_reter_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_reter_over.gif'">
        				</acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
            </html:form>
            <vivo:quadroFlutuante id="dvMsgPontos" idIframe="ifrmMensagem" height="50" width="300" spacesTop="50" spacesLeft="50" display="none" url="<%=request.getContextPath()%>" label=""/>
        </vivo:quadro>
        </div>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
