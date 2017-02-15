<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="bonusForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
        <script language="javascript">
            function alterar(idBonus){
                parent.$('dv_dvManBonus').innerText = 'Alterar de Bônus';
                parent.$('dvManBonus').style.display = 'block';
                parent.$('ifrmManBonus').src = './ManterBonus/alterarBegin.do?idBonus='+idBonus+'&tpOperacao='+$F('tpOperacao');
            }

            function excluir(idBonus){
                if(confirm('Deseja realmente excluir este bônus?')){
                    var f = document.forms[0];
                    f.action = "excluir.do";
                    f.idBonus.value = idBonus;
                    f.submit();
                }
            }

            function pesquisar() {
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                var f = document.forms[0];
                f.action = "pesquisar.do";
                f.submit();
            }

            function incluir(){
                parent.$('dv_dvManBonus').innerText = 'Inclusão de Bônus';
                parent.$('dvManBonus').style.display = 'block';
                parent.$('ifrmManBonus').src = './ManterBonus/incluirBegin.do?tpOperacao='+$F('tpCanal');
            }

            function selCanalRetencao(tpCanal){
                $('bonusForm').reset();
                $('tpCanal').value = tpCanal;
                $('tpOperacao').value = tpCanal;
                if(tpCanal=='A'){
                    $('blkPsq').show();
                    $('blkTabela').show();
                    $('lbTpBonus').show();
                    $('cmpTpBonus').show();
                    $('dvBtIncluir').show();
                    $('lbGrpPacote').hide();
                    $('cmpGrpPacote').hide();
                    $('lbTpCliente').show();
                    $('cmpTpCliente').show();
                }else if(tpCanal=='U'){
                    $('blkPsq').show();
                    $('blkTabela').show();
                    $('lbGrpPacote').show();
                    $('cmpGrpPacote').show();
                    $('dvBtIncluir').show();
                    $('lbTpBonus').hide();
                    $('cmpTpBonus').hide();
                    $('lbTpCliente').hide();
                    $('cmpTpCliente').hide();
                }else{
                    $('blkPsq').hide();
                    $('blkTabela').hide();
                    $('lbTpBonus').hide();
                    $('lbGrpPacote').hide();
                    $('cmpTpBonus').hide();
                    $('cmpGrpPacote').hide();
                    $('dvBtIncluir').hide();
                    $('lbTpCliente').hide();
                    $('cmpTpCliente').hide();
                }
            }


        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                <logic:notEmpty name="msgError" scope="request">
                    alert('<bean:write name="msgError" scope="request"/>');
                </logic:notEmpty>
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                document.body.style.backgroundColor = '#ededed';
                <logic:equal name="Form" property="tpOperacao" value="A">
                    selCanalRetencao('A');
                </logic:equal>
                <logic:equal name="Form" property="tpOperacao" value="U">
                    selCanalRetencao('U');
                </logic:equal>
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
		<acesso:controlHiddenItem nomeIdentificador="fid_mbus_verpagina">
        <form method="post" action="" id="bonusForm" name="bonusForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;">
            <html:hidden name="Form" property="idBonus"/>
            <html:hidden name="Form" property="tpOperacao"/>
            <table width="100%" border="0" align="left">
                <tr>
                    <td>
                        <table width="770" border="0" align="left">
                            <tr>
                                <td width="100">Canal&nbsp;Retenção:</td>
                                <td align="left">
                                    <select id="tpCanal" name="tpCanal" style="width:150px;" class="SELECT" onchange="selCanalRetencao(this.value);">
                                        <option value="">-- Selecione --</option>
                                        <option value="A">Atendimento</option>
                                        <option value="U">URA</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr id="blkPsq" style="display:none;">
                    <td>
                        <table width="770" border="0">
                            <tr>
                                <td width="100">Regional:</td>
                                <td width="285">
                                    <html:select name="Form" property="idRegional" style="width:150px;" styleClass="SELECT">
                                        <option value="">-- Todas --</option>
                                        <html:optionsCollection name="Form" property="listaRegional.itArray" value="id" label="ds"/>
                                    </html:select>
                                </td>
                                <td width="100"><span id="lbTpCliente" style="display:none;">Tipo de Cliente:</span></td>
                                <td width="285">
                                    <div id="cmpTpCliente" style="display:none;">
                                        <html:select name="Form" property="idTpCliente" style="width:150px;" styleClass="SELECT">
                                            <option value="">-- Todas --</option>
                                            <html:optionsCollection name="Form" property="listaClientes.itArray" value="id" label="ds"/>
                                        </html:select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span id="lbTpBonus" style="display:none;">Tipo Bônus:</span>
                                    <span id="lbGrpPacote" style="display:none;">Grupo de Pacote:</span>
                                </td>
                                <td>
                                    <div id="cmpTpBonus" style="display:none;">
                                        <html:select name="Form" property="idTpBonus" style="width:150px;" styleClass="SELECT">
                                            <option value="">-- Todas --</option>
                                            <html:optionsCollection name="Form" property="listaTpBonus.itArray" value="id" label="ds"/>
                                        </html:select>
                                    </div>
                                    <div id="cmpGrpPacote" style="display:none;">
                                        <html:select name="Form" property="idGrpPacote" style="width:150px;" styleClass="SELECT">
                                            <option value="">-- Todas --</option>
                                            <html:optionsCollection name="Form" property="listaGrpPacote.itArray" value="id" label="ds"/>
                                        </html:select>
                                    </div>
                                </td>
                                <td>Tipo de Linha:</td>
                                <td>
                                    <html:select name="Form" property="idTpLinha" style="width:150px;" styleClass="SELECT">
                                        <option value="">-- Todas --</option>
                                        <html:optionsCollection name="Form" property="listaLinhas.itArray" value="id" label="ds"/>
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td>Segmentação:</td>
                                <td>
                                    <html:select name="Form" property="idSegmentacao" style="width:150px;" styleClass="SELECT">
                                        <option value="">-- Todas --</option>
                                        <html:optionsCollection name="Form" property="listaSegmentacao.itArray" value="id" label="ds"/>
                                    </html:select>
                                </td>
                                <td></td>
                                <td align="right">
                                    <acesso:controlHiddenItem nomeIdentificador="fid_mb_pesquisar">
                                        <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" style="cursor:pointer;" onclick="pesquisar();">
                                    </acesso:controlHiddenItem>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr id="blkTabela" style="display:none;">
                    <td>
                        <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="225" tableWidth="750" styleId="tbTitle" sortable="true" resize="false">
                            <vivo:tbHeader>
                                <vivo:tbHeaderColumn align="left" width="150" type="string">Tipo Bônus</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="450" type="string">Descrição</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="76"  type="string">Validade</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="37"  type="string">&nbsp;</vivo:tbHeaderColumn>
                                <vivo:tbHeaderColumn align="left" width="37"  type="string">&nbsp;</vivo:tbHeaderColumn>
                            </vivo:tbHeader>
                            <vivo:tbRows>
                                <logic:iterate id="it" name="Form" property="fidelizacaoVO.tabelas.bonus.linhaArray" indexId="idx">
                                    <vivo:tbRow key="linha0">
                                        <vivo:tbRowColumn><bean:write name="it" property="dsTpBonus"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="it" property="dsBonus"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn><bean:write name="it" property="vdBonus"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                             <acesso:controlHiddenItem nomeIdentificador="fid_mb_alterar">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" style="cursor:pointer;" onclick="alterar('<bean:write name="it" property="idBonus"/>');">
                                            </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <acesso:controlHiddenItem nomeIdentificador="fid_mb_excluir">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" style="cursor:pointer;" onclick="excluir('<bean:write name="it" property="idBonus"/>');">
                                            </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </logic:iterate>
                            </vivo:tbRows>
                        </vivo:tbTable>
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                        <table width="772" height="20" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                            <tr>
                                <td width="100" valign="middle"><b>&nbsp;Legendas:</b></td>
                                <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle">&nbsp;Alterar Bônus</td>
                                <td width="522"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle">&nbsp;Excluir Bônus</td>
                            </tr>
                        </table>
                        <div style="margin-top:10px;display:none;float:right;" id="dvBtIncluir">
                            <!--acesso:controlHiddenItem nomeIdentificador="fid_mb_incluir"-->
                            <img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onclick="incluir();" style="cursor:pointer;"/>
                            <!--/acesso:controlHiddenItem-->
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
