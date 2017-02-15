<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.DecimalFormatSymbols"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="migracaoForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <link href="./../../../resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
            <!--
            function alterar(idMigra){
                parent.$('dv_dvCfgMigra').innerText = 'Alteração de Migração';
                parent.$('dvCfgMigra').style.display = 'block';
                parent.$('ifrmCfgMigra').src = './ConfigurarMigracao/alterarBegin.do?idMigracao='+idMigra;
            }

            function excluir(idMigra){
                if(confirm('Deseja realmente excluir esta Migração?')){
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    var f = document.forms[0];
                    f.idMigracao.value = idMigra;
                    f.action = "excluir.do";
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
                parent.$('dv_dvCfgMigra').innerText = 'Inclusão de Migração';
                parent.$('dvCfgMigra').style.display = 'block';
                parent.$('ifrmCfgMigra').src = './ConfigurarMigracao/incluirBegin.do';
            }
            -->
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                <logic:notEmpty name="msgError" scope="request">
                    alert('<bean:write name="msgError" scope="request"/>');
                </logic:notEmpty>
                if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
                document.body.style.backgroundColor = '#ededed';
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_cmig_verpagina">
        <form method="post" action="" id="migracaoForm" name="migracaoForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;">
        <html:hidden name="Form" property="idMigracao"/>
        <table width="100%">
            <tr>
                <td>
                    <table width="770" border="0">
                        <tr>
                            <td>Regional:</td>
                            <td>Tipo de Cliente:</td>
                            <td>Tipo de Linha:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <html:select name="Form" property="idRegional" style="width:150px;" styleClass="SELECT">
                                    <option value="">-- Todos --</option>
                                    <html:optionsCollection name="Form" property="listaRegional.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                            <td>
                                <html:select name="Form" property="idTpCliente" style="width:150px;" styleClass="SELECT">
                                    <option value="">-- Todos --</option>
                                    <html:optionsCollection name="Form" property="listaClientes.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                            <td>
                                <html:select name="Form" property="idTpLinha" style="width:150px;" styleClass="SELECT">
                                    <option value="">-- Todos --</option>
                                    <html:optionsCollection name="Form" property="listaLinhas.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                            <td>
                                <acesso:controlHiddenItem nomeIdentificador="fid_cmig_pesquisar">
                                    <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisar();" style="cursor:pointer;">
                                </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <vivo:tbTable selectable="true" layoutWidth="750" layoutHeight="250" tableWidth="750" styleId="tableTitleX4" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left"  width="70%" type="string">Descrição</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="right" width="10%" type="string">Bônus em R$</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="right" width="10%" type="string">Validade</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="center" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <logic:iterate id="it" name="Form" property="fidelizacaoVO.tabelas.migracao.linhaArray" indexId="idx" type="FidelizacaoVO.Tabelas.Migracao.Linha">
                            <vivo:tbRow key="linhax1">
                                <vivo:tbRowColumn><bean:write name="it" property="dsMigra"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <bean:write name="it" property="vlBonus" />
                                    <!--%=(new DecimalFormat("###,##0.00",new DecimalFormatSymbols(new Locale("pt","BR")))).format(new Double(it.getVlBonus()))%-->
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn><bean:write name="it" property="vdMigra"/></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <acesso:controlHiddenItem nomeIdentificador="fid_cmig_alterar">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alterar('<bean:write name="it" property="idMigra"/>');">
                                    </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <acesso:controlHiddenItem nomeIdentificador="fid_cmig_excluir">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="excluir('<bean:write name="it" property="idMigra"/>')">
                                    </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                    <div><img src="./../../../resources/images/transp.gif" width="1" height="5"></div>
                    <table width="765" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                        <tr>
                            <td width="100" valign="middle"><b>&nbsp;Legendas:</b></td>
                            <td width="150" ><img vspace="2" src="./../../../resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Migração</td>
                            <td width="495"><img vspace="2" src="./../../../resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Migração</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <div style="float:right;margin-top:10px;">
            <acesso:controlHiddenItem nomeIdentificador="fid_cmig_incluir">
                <img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onclick="incluir();" style="cursor:pointer;"/>
            </acesso:controlHiddenItem>
        </div>
    </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>