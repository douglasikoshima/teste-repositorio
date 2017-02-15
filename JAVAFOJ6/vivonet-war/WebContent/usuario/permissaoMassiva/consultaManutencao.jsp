<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="uploadForm"/>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Consulta de Processamento - Manutenção de Usuários"/>
    <netui-template:setAttribute name="modulo" value="Administração"/>
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/validacao.js"></script>
        <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script type="text/javascript" language="javascript">
            function download(idFunc, nmFileBad, nrPos){
            	
                var f = document.forms['formDown'];
                f.sgFuncionalidade.value = idFunc;
                f.nrPos.value = nrPos;
                f.nmFileBad.value = nmFileBad;
                f.submit();
            }

            function downloadLogErro(nrPos){
            	
                var f = document.forms['formDown'];
                
                f.nrPos.value = nrPos;
                f.action = "downloadLogErro.do";
                f.submit();
            }

            function consultar(){
            	
                var f = document.forms['formCons'];
                if(validar()){
                    f.submit();
                }
            }
            
            function validar() {

                var f = document.forms['formCons'];
                
                if(f.sgFuncionalidade.value.length <=0){
                    alert('Favor selecionar uma funcionalidade');
                    return false;
                }

                return true;
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
        <div>
            <img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5">
        </div>
        <vivo:sessao id="qdMain" height="468" width="790" label="Permissão Massiva de Usuários" operacoes="Consultar Arquivos" scroll="no">
            <table width="760" border="0" cellspacing="0" cellpadding="0" align="center" style="margin-top:10px;">
                <tr>
                    <td valign="top" align="left"><html:form styleId="formCons" method="post" enctype="multipart/form-data" onsubmit="return false;" action="/usuario/permissaoMassiva/consultaManutencao.do">
                        <input type="hidden" name="somaDias">
                        <table width="760" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;margin-bottom:10px;">
                            <tr>
                                <td style="padding:5px;" align="right" width="100">Funcionalidade:</td>
                                    <td style="padding-left: 9px;" align="left" width="250"><html:select name="Form" property="sgFuncionalidade" style="width:200px;">
                                        <option value="">Selecione</option>
                                        <option value="MANUTA" <logic:equal name="Form" property="sgFuncionalidade" value="MANUTA">selected</logic:equal>>Manutenção Acesso</option>
                                        <option value="MANUTL" <logic:equal name="Form" property="sgFuncionalidade" value="MANUTL">selected</logic:equal>>Manutenção Login</option>
                                        <option value="MANUTC" <logic:equal name="Form" property="sgFuncionalidade" value="MANUTC">selected</logic:equal>>Criação de Usuários</option>
                            </html:select></td>
                            <td align="left" style="padding-right: 5px;"><img src="/FrontOfficeWeb/resources/images/bt_consultar_nrml.gif" onclick="consultar();" style="border: none; cursor: pointer;" /></td>
                            </tr>
                        </table>
            </html:form></td>
                </tr>
                <tr>
        <td valign="top" height="320"><logic:present name="Form" property="arquivoProcessamentoVO">

                             <vivo:tbTable selectable="true" styleId="tbArquivosLote" layoutHeight="310" layoutWidth="760" tableWidth="760" sortable="true">
                                <vivo:tbHeader>
                                    <vivo:tbHeaderColumn width="15%" type="String">Data de Envio</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn width="25%" type="String">Nome do Arquivo</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn width="9%" type="String">Login</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn width="15%" type="String">Data/Hora Processamento</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn width="10%" type="String">Status Carga</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn width="9%" type="String" align="center">Registros Rejeitados</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn width="9%" type="String" align="center">Total de Registros</vivo:tbHeaderColumn>
                                    <vivo:tbHeaderColumn width="8%"  type="String" align="center">Arquivo de Erro</vivo:tbHeaderColumn>
                                </vivo:tbHeader>
                                <logic:present name="Form" property="arquivoProcessamentoVO.itemArray">
                                <vivo:tbRows>
                                    <logic:iterate id="it" name="Form" property="arquivoProcessamentoVO.itemArray" indexId="idx">
                                        <vivo:tbRow key="linha1">
                                            <vivo:tbRowColumn>
                                        <bean:write name="it" property="dtEnvio" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="it" property="nmFileOriginal" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="it" property="dsLoginUsuario" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="it" property="dtProcessamento" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="it" property="dsStatusCarga" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="it" property="qtRegRejeitados" />
                                    </vivo:tbRowColumn>
                                    <vivo:tbRowColumn>
                                        <bean:write name="it" property="qtRegTotal" />
                                    </vivo:tbRowColumn>
                                            <vivo:tbRowColumn>
                                                <logic:equal name="it" property="dsStatusCarga" value="PROCESSADO COM ERRO">
                                            		<img src="/FrontOfficeWeb/resources/images/bt_icon_download.gif" title="Arquivos de Erro" border="0" style="cursor: pointer;" onclick="downloadLogErro('<%=idx%>');">
                                        		</logic:equal>
                                        		<logic:equal name="it" property="dsStatusCarga" value="ERRO DE LAYOUT">
                                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_download.gif" title="Arquivos de Erro" border="0" style="cursor:pointer;" onclick="downloadLogErro('<%=idx%>');">
                                        		</logic:equal>
                                            </vivo:tbRowColumn>
                                        </vivo:tbRow>
                                    </logic:iterate>
                                </vivo:tbRows>
                                </logic:present>
                            </vivo:tbTable>

                            <div id="dvLegendas" style="margin-top:3px">
                                <table style="background-color:#fff;border:1px solid #adadad;" width="777" align="left">
                                    <tr>
                                        <td valign="middle" style="padding-left:10px;padding-right:15px;" width="10%" nowrap><b>Legendas:</b></td>
                                        <td valign="middle" align="left" nowrap style="padding-right:15px;" width="90%"><img align="middle" src="<%=request.getContextPath()%>/resources/images/bt_icon_download.gif" />&nbsp;Download</td>
                                    </tr>
                                </table>
                            </div>
                <html:form styleId="formDown" method="post" enctype="multipart/form-data" onsubmit="return false;" action="/admsistemas/arquivos/download.do" target="frmDownload"
                           style="height:1px;margin:0;padding:0">
                                <input type="hidden" name="sgFuncionalidade" value="">
                                <input type="hidden" name="idProcessamento" value="">
                                <input type="hidden" name="nmFileBad" value="">
                                <input type="hidden" name="nrPos" value="">
                            </html:form>
                            <iframe src="about:blank" id="frmDownload" name="frmDownload" style="display:none;" width="1" height="1"></iframe>
                        </logic:present>
                    <td>
                </tr>
                <tr>
                    <td align="left">
            <input type="image" id="btVoltar" value="Voltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onmouseover="this.src = '/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border: none; margin: 10px 4px;" onmouseup="top.location.href = '/FrontOfficeWeb/voltar.do'" />
                    </td>
                </tr>
            </table>
        </vivo:sessao>
        <vivo:alert atributo="msgStatus" scope="request"/>
    </netui-template:section>
</netui-template:template>