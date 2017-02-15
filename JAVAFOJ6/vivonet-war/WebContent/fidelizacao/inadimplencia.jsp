<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm" />
<bean:define id="Cliente"	  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosClienteForm" />
<bean:define id="Adimplencia" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaAdimplenciaForm" />
<bean:define id="Regionais"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.regionais" />
<bean:define id="pageFlow"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"/>
<bean:define id="Endereco"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="ofertaAparelhoForm.enderecoVO" />

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/fidelizacao-aparelhos-entrega.js"></script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
            -->
        </SCRIPT>
        <script language="javascript">
            function encaminharParaAnalise(){
                if (confirm("Deseja encaminhar para análise BKO?")){
                    var nrCPF = onlyNumbers(window.top.frameApp.$('ddClienteDocumento').innerHTML);
                    parent.parent.mostrar_div();
                    document.forms[0].action = "encaminhar.do?nrCPF="+nrCPF;
                    document.forms[0].submit();
                }
            }

            function reterPorRestricao(){
                var nrCPF = onlyNumbers(window.top.frameApp.$('ddClienteDocumento').innerHTML);
                parent.parent.mostrar_div();
                document.forms[0].action = "reterPorRestricao.do?nrCPF="+nrCPF;
                document.forms[0].submit();
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <acesso:controlHiddenItem nomeIdentificador="fid_adimp_verpagina">
            <div style="width:764px;height:350px;overflow:auto;margin-bottom:1px solid #adadad;">
                <jsp:include page="informacoesCliente.jsp"/>
                <form action="reterAparelho.do" name="ofertaAparelhoForm" method="post">
                    <html:hidden name="Form" property="idAparelho" />
                    <html:hidden name="Form" property="SAP" />
                    <html:hidden name="Form" property="cor" />
                    <html:hidden name="Form" property="modelo" />
                    <table cellpadding="0" cellspacing="0" style="border:1px solid #adadad; background-color:#E7E7E7; margin-bottom:5px;" width="760" border="0">
                        <tr style="background-color:#fff;">
                            <td style="padding:5px;">
                                <b>Cliente: </b>
                                <bean:write name="Cliente" property="nomeCliente" />
                            </td>
                            <td colspan="2" align="right" style="padding-right:60px;">
                                <b>CPF: </b>
                                <bean:write name="Adimplencia" property="nrDocumento" />
                            </td>
                        </tr>
                        <tr>
                            <td style="padding:5px;">
                                <b>Status de adimpl&ecirc;ncia: </b>
                                <bean:write name="Adimplencia" property="statusAvaliacao" />
                            </td>
                            <td style="padding:5px;">
                                <b>Data da requisi&ccedil;&atilde;o: </b>
                                <bean:write name="Adimplencia" property="dtRequisicao" />
                            </td>
                            <td style="padding:5px;">
                                <b>Data da resposta: </b>
                                <bean:write name="Adimplencia" property="dtResposta" />
                            </td>
                        </tr>
                
                    </table>
            
                    <vivo:quadro id="historico" height="160" width="760" label="Hist&oacute;rico de Cr&eacute;dito">
                        <pre style="font-size:11px;font-family:Tahoma;Verdana;line-height:16px;">
                            <netui:label value="{pageContext.Adimplencia.dsHistorico}" escapeWhiteSpaceForHtml="true"/>
                        </pre>
                    </vivo:quadro>
                    <table width="760">
                        <tr>
                            <td>
                                <img src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif"
                                	 border="0"
                                	 onmouseup="parent.parent.mostrar_div();document.forms[0].action='voltarDEA.do';document.forms[0].submit();"/>
                            </td>
                            <td align="right">
                                <acesso:controlHiddenItem nomeIdentificador="fid_adimp_reterporrestricao">
                                    <img action="reterPorRestricao.do?Adimplencia=nrDocumento"
                                    	 src="<%=request.getContextPath()%>/resources/images/bt_reterrestricao_nrml.gif"
                                    	 class="button"
                                    	 onClick="reterPorRestricao(); return false;" />
                                </acesso:controlHiddenItem>
                                <acesso:controlHiddenItem nomeIdentificador="fid_adimp_encaminhar">
                                    <img action="encaminhar.do"
                                    	 src="<%=request.getContextPath()%>/resources/images/bt_encaminhar_nrml.gif"
                                    	 class="button"
                                    	 onmouseup="encaminharParaAnalise();return false;"/>
                                </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
