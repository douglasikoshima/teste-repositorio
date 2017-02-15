<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="javascript">
            function cancela(acao, obj){
                //dvCancelarAtd.style.display='';
                parent.parent.mostrar_div();
                document.forms["formLigacaoIndevida"].submit();
                /*
                document.forms[0].action="/FrontOfficeWeb/fidelizacao/ConsultaMatrizOferta/filtroFimRetencao.do?matrizOferta=nao&acao="+acao;
                parent.parent.mostrar_div();
                document.forms[0].submit();
                */
            }

            function cancelarAtd(valor){
                dvCancelarAtd.style.display='none';
                document.forms[0].action="cancelarAtendimento.do";
                //document.forms[0].action="/FrontOfficeWeb/fidelizacao/ConsultaMatrizOferta/filtroFimRetencao.do?matrizOferta=nao&acao="+acao;
                parent.parent.mostrar_div();
                document.forms[0].submit();
            }

            function proxima(obj){
                valida = false;
                var idDestinoPrev = 0;
                i=0;
                if (document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}']){
                    if(document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].length){
                        for(i=0; i < document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].length; i++){
                            if(document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'][i].checked == true){
                                valida = true;
                                idDestinoPrev = document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'][i].value;
                                break;
                            }
                        }
                    }else{
                        valida = (document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].checked == true);
                    }
                }
                if (valida == false){
                    alert("Favor selecionar uma resposta!");
                }else{
                    parent.parent.mostrar_div();
                    document.forms[0].dsDestinoPrevisto.value = document.forms[0].elements["{pageContext.listaDestinoPrevistoVO.itemListaVOArray["+i+"].descricao}"].value;
                    document.forms[0].idDestinoPrevisto.value = idDestinoPrev;
                    document.showMatrizOfertaForm.action = obj.href;
                    document.showMatrizOfertaForm.submit();
                }
            }

            function agendar(){
                param = "";
                if(document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}']){
                    if(document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].length){
                        for(i=0; i < document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].length; i++){
                            if(document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'][i].checked){
                                param = "?destinoPrevisto=" + document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'][i].value;
                                break;
                            }
                        }
                    }else{
                        if(document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].checked){
                            param = "?destinoPrevisto=" + document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].value;
                        }
                    }
                }
                parent.agendar(param);
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                parent.parent.oculta_div();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_dp_verpagina">
    <html:form action="/fidelizacao/getMatrizOferta.do" styleId="showMatrizOfertaForm" method="post" onsubmit="return false;">
        <input type="hidden" name="dsDestinoPrevisto" value=""/>
        <input type="hidden" name="idDestinoPrevisto"/>
        <input type="hidden" name="progresso" value="2"/>
        <vivo:quadro id="qdMain" height="310" width="760" label="Consultar Intenção de Cancelamento" scroll="no">
            <table width="100%">
                <tr>
                    <td>
                        <b>Qual o Destino Previsto?</b>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div style="width:744px;height:260px;overflow:auto;margin-bottom:1px solid #adadad;">
                        <table>
                            <bean:define id="listaDestinoPrevistoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaDestinoPrevistoVO" />
                            <netui-data:repeater dataSource="{pageContext.listaDestinoPrevistoVO.itemListaVOArray}">
                                <netui-data:repeaterHeader></netui-data:repeaterHeader>
                                <netui-data:repeaterItem>
                                    <tr>
                                        <td width="15">
                                            <netui:radioButtonGroup dataSource="idDestinoPrevisto" optionsDataSource="{container.item.id}" labelStyle="display:none;" style="border:none;background-color:#e3ecf4;"/>
                                        </td>
                                        <td width="100%">
                                        <netui:hidden dataSource="{container.item.descricao}"  />
                                        <netui:label value="{container.item.descricao}" />
                                        </td>
                                    </tr>
                                </netui-data:repeaterItem>
                                <netui-data:repeaterFooter></netui-data:repeaterFooter>
                            </netui-data:repeater>
                        </table>
                        </div>
                    </td>
                </tr>
            </table>
            <table width="750" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <img onmouseup="document.forms[0].action='voltarDPrevisto.do';document.forms[0].submit();return false;"
                        	 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                        	 onclick="parent.parent.mostrar_div();" />
                    </td>
                    <td align="center">
                        <acesso:controlHiddenItem nomeIdentificador="fid_dp_manualeletronico">
                            <img onmouseup="window.open('http://intranet.vivo-sp.com.br/manual/retencao/index.htm');return false;"
                            	 src="/FrontOfficeWeb/resources/images/bt_manelet_nrml.gif"
                            	 class="button" />
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="fid_dp_agendar">
                            <img src="/FrontOfficeWeb/resources/images/bt_agendar_nrml.gif" style="border:none;cursor:hand;" border="0" onClick="agendar();return false;" />
                        </acesso:controlHiddenItem>
                        <acesso:controlHiddenItem nomeIdentificador="fid_dp_cancelarlinha">
                            <img src="/FrontOfficeWeb/resources/images/bt_cancelatend_nrml.gif"
        						 style="border:none;cursor:hand;"
        						 onClick="cancela('ligacao');return false;" />
                        </acesso:controlHiddenItem>
                    </td>
                    <td align="right">
                        <acesso:controlHiddenItem nomeIdentificador="fid_dp_proximo">
                            <img href="getMatrizOferta.do?acao=matrizOferta"
                            	 onClick="proxima(this);return false;"
                            	 src="/FrontOfficeWeb/resources/images/bt_proxima_nrml.gif"
                            	 border="0">
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <div id='dvCancelarAtd' name='dvCancelarAtd' style='z-index:998 ;position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad; display:none;'>
            <table border='0' cellpadding='0' cellspacing='0'>
                <tr>
                    <td style='height:115;' colspan='2'></td>
                </tr>
                <tr>
                    <td style='width:230;'></td>
                    <td>
                        <table width='450' height='21' cellpadding='0' cellspacing='0' background='/FrontOfficeWeb/resources/images/window_topbg.gif' bgcolor='#0066cb' class='tbl_titulo'>
                            <tr>
                                <td width='386' height='21'>
                                    <div id='dv_dvCancelarAtd' align="center">Cancelar Atendimento</div>
                                </td>
                                <td width='64' valign='top' background='/FrontOfficeWeb/resources/images/window_topbtbg.gif'>
                                    <div align='right'>
                                        <img hspace='3' src='/FrontOfficeWeb/resources/images/window_fechar.gif' onclick="dvCancelarAtd.style.display='none';" style='cursor:hand;'>
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <table width='450' height='100' border='0' cellpadding='0' cellspacing='1' bgcolor='#adadad'>
                            <tr>
                                <td bgcolor='#E3ECF4' align='center'>
                                    <table border="0" cellpadding="0" cellspacing="0" align="center" background="#545454">
                                        <tr>
                                            <td valign="middle" align="center">
                                                <bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="actionRetencaoForm" />
                                                <html:select name="Form" property="idTipoEncerramento"></html:select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="middle" align="center">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td valign="middle" align="center">
                                                <img src="/FrontOfficeWeb/resources/images/bt_ok_nrml.gif" onClick="cancelarAtd();return false;" border="0"/>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </html:form>
    <div style="display:none">
        <iframe name="getLigacaoIndevida" height="0" width="0" style="display:none"></iframe>
        <form id="formLigacaoIndevida" name="formLigacaoIndevida" action="getLigacaoIndevida.do" target="getLigacaoIndevida"></form>
    </div>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
