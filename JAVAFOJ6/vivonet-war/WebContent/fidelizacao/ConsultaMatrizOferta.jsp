<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="Form"                      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaMatrizOfertaForm" />
<bean:define id="nrLinha"                   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm.detalheLinha.numero" />
<bean:define id="valorProRata"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm.detalheLinha.valorProRata" />
<bean:define id="listaOfertasVO"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaOfertasVO.itemListaDescricaoVOArray" />
<bean:define id="listaOfertasDisponiveisVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaOfertasDisponiveisVO.itemListaDescricaoVOArray" />
<bean:define id="listaOfertasRealizadasVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaOfertasRealizadasVO.itemListaDescricaoVOArray" />
<bean:define id="listaOfertasAceitasVO"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaOfertasAceitasVO.itemListaDescricaoVOArray" />

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/fidelizacao-matrizofertas.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
        <script language="javascript">
            var ofertas = new Array();
            <logic:iterate id="ofertas" name="listaOfertasVO">
                ofertas[<bean:write name="ofertas" property="id"/>] =  new Array("<bean:write name="ofertas" property="sigla"/>","<bean:write name="ofertas" property="descricao2"/>");
            </logic:iterate>
            <logic:notEmpty name="msgSAP">
                alert("<bean:write name="msgSAP"/>");
            </logic:notEmpty>
            </script>
            <script language="VBScript">
                function cancelarLinhaOLD
                    cancelarLinha = msgbox ("Tem certeza que deseja cancelar a linha ""<bean:write name="nrLinha"/>""?"+ CHR(13) +"Valor da multa contratual para cancelar a linha é R$ <bean:write name="valorProRata"/>.",4,"Cancelar Linha")
                end function
    
                function cancelarLinha
                    cancelarLinha = msgbox ("Tem certeza que deseja cancelar a linha ""<bean:write name="nrLinha"/>""?",4,"Cancelar Linha")
                end function
                
                function cancelarLinhasAssociadas
                    <logic:equal name="Form" property="inCancelamentoLinhasAssociadas" value="true">
                    cancelarLinhasAssociadas = msgbox("Deseja cancelar outras linhas deste mesmo cliente com o mesmo motivo?", 4, "Cancelar Linhas Associadas")
                    </logic:equal>
                    <logic:equal name="Form" property="inCancelamentoLinhasAssociadas" value="false">
                    cancelarLinhasAssociadas = 5
                    </logic:equal>
                end function
            </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_cmota_verpagina">
    <div style="width:764px;height:331px;overflow:auto;margin-bottom:1px solid #adadad;">
    <jsp:include page="informacoesCliente.jsp"/>
    <vivo:quadro id="qdMain" height="247" width="760" label="Matriz de Oferta" scroll="no">
        <form action="redirectMatrizOferta.do" name="consultaMatrizOfertaForm" method="post">
        <input type="hidden" name="tipoEncerramento">
        <input type="hidden" name="sgOferta">
        <table width="100%">
            <tr><td height="4"></td></tr>
            <tr>
                <td width="202" align="center">
                    Ofertas Disponíveis<br>
                    <html:select name="Form" property="ofertasDisp" size="8" style="width:200px;height:189px;" multiple="true" onmouseover="ativarToolTip(this);" ondblclick="move(consultaMatrizOfertaForm.ofertasDisp, consultaMatrizOfertaForm.ofertasReal);">
                        <html:options collection="listaOfertasDisponiveisVO" property="id" labelProperty="descricao1"/>
                    </html:select>
                </td>
                <td width="70" align="center" valign="middle">
                    <table height="50" width="100%">
                        <tr>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" onClick="move(consultaMatrizOfertaForm.ofertasDisp, consultaMatrizOfertaForm.ofertasReal); return false" style="border:none;"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" onClick="move(consultaMatrizOfertaForm.ofertasReal, consultaMatrizOfertaForm.ofertasDisp); return false" style="border:none;"/>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="202" align="center">
                    Oferta(s) realizada(s)<br>
                    <html:select name="Form" property="ofertasReal" size="8" style="width:200px;height:189px;" multiple="true" onmouseover="ativarToolTip(this);" ondblclick="moveUm(consultaMatrizOfertaForm.ofertasReal, consultaMatrizOfertaForm.ofertasAceita);">
                        <html:options collection="listaOfertasRealizadasVO" property="id" labelProperty="descricao1"/>
                    </html:select>
                </td>
                <td width="70" align="center" valign="middle">
                    <table height="50" width="100%">
                        <tr>
                            <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" onClick="moveUm(consultaMatrizOfertaForm.ofertasReal, consultaMatrizOfertaForm.ofertasAceita); return false" style="border:none;"/>
                            </td>
                        </tr>
                        <tr><td>
                                <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" onClick="move(consultaMatrizOfertaForm.ofertasAceita, consultaMatrizOfertaForm.ofertasReal); return false" style="border:none;"/>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="202" align="center">
                    Oferta aceita<br>
                    <html:select name="Form" property="ofertasAceita" size="8" style="width:200px;height:189px;" onmouseover="ativarToolTip(this);" multiple="true" >
                        <html:options collection="listaOfertasAceitasVO" property="id" labelProperty="descricao1"/>
                    </html:select>
                </td>
            </tr>
        </table>
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="6"></div>
        <table width="750" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <%if(ConstantesCRM.SONE.equals(request.getSession().getAttribute("inAdimplente"))){%>
                        <script type="text/javascript" language="javascript">
                            function getAnaliseCredito() {
                                top.frameApp.mostrar_div();
                                /*
                                document.forms[0].action = "efetivarRetencaoAnaliseCreditoAprovada.do?idRetencao=<%=request.getSession().getAttribute("idRetencaoOld")%>&inVoltar=1";
                                document.forms[0].submit();
                                */
                                top.frameApp.CarregaAba("bt06");
                            }
                        </script>
                        <img class="button"
                        	 action="voltarCMO"
                        	 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                        	 onClick="getAnaliseCredito(); return false;" />
                    <%}else{%>
                        <img action="voltarCMO"
                        	 class="button"
                        	 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                        	 border="0"
                        	 onClick="parent.parent.mostrar_div();"/>
                    <%}%>
                </td>
                <td align="center">
                    <acesso:controlHiddenItem nomeIdentificador="fid_cmo_manualeletronico">
                        <img class="button"
                        	 onmouseup="window.open('http://intranet.vivo-sp.com.br/manual/retencao/index.htm');"
                        	 src="/FrontOfficeWeb/resources/images/bt_manelet_nrml.gif" />
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_cmo_agendar">
                        <img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_agendar_over.gif'" style="border:none;cursor:hand;" border="0" onClick="cancela('agendar');" />
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_cmo_vaipensar">
                        <img src="/FrontOfficeWeb/resources/images/bt_vaipensar_nrml.gif"
                        	 class="button"
                        	 onClick="cancela('vaipensar');return false" />
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_cmo_cancelarlinha">
                        <img class="button"
                        	 src="/FrontOfficeWeb/resources/images/bt_cancellinha_nrml.gif"
                        	 onClick="cancela('cancelar');return false" />
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_cmo_reter">
                        <img class="button"
                        	 src="/FrontOfficeWeb/resources/images/bt_reter_nrml.gif"
                        	 style="border:none;"
                        	 onClick="reter();return false" />
                    </acesso:controlHiddenItem>
                </td>
                <td align="right" valign="bottom">
                <acesso:controlHiddenItem nomeIdentificador="fid_cmo_proxima">
                    <img class="button"
                    	 src="/FrontOfficeWeb/resources/images/bt_proxima_nrml.gif"
                    	 border="0"
                    	 onClick="proxima();return false" />
                </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        </form>
    </vivo:quadro>
    </div>
    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        <!--
            top.frameApp.oculta_div();
            top.frameApp.$("idAnime").style.display="none";
            <% if (request.getAttribute("inVoltar") == null){ %>
            //Para limpar combos
            f = document.forms[0];
            f.ofertasAceita.options[0] = null;
            f.ofertasAceita.options[0] = null;
            for (i = 0; i < f.ofertasReal.length; i++) {
                f.ofertasReal.options[0] = null;
            }
            f.ofertasReal.options[0] = null;
            f.ofertasReal.options[0] = null;
            <% } %>
        -->
      </SCRIPT>
      </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
