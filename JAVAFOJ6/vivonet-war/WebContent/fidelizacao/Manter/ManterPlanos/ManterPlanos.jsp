<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="planosForm"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
            function alterarPlano(idPlano){
                parent.$('dv_dvManPlanos').innerText = 'Alteração de Planos';
                parent.$('dvManPlanos').style.display = 'block';
                parent.$('ifrmManPlanos').src = './ManterPlanos/alterarBegin.do?idPlano='+idPlano;
            }

            function excluirPlano(idPlano){
                if(confirm('Deseja realmente excluir este plano?')){
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    var f = document.forms[0];
                    f.idPlano.value = idPlano;
                    f.action = "excluir.do";
                    f.submit();
                }
            }

            function pesquisarPlanos() {
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                var f = document.forms[0];
                f.action = "pesquisar.do";
                f.submit();
            }

            function incluir(){
                parent.$('dv_dvManPlanos').innerText = 'Inclusão de Planos';
                parent.$('dvManPlanos').style.display = 'block';
                parent.$('ifrmManPlanos').src = './ManterPlanos/incluirBegin.do';
            }
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
    <acesso:controlHiddenItem nomeIdentificador="fid_mpl1_verpagina">
        <form method="post" action="" id="planosForm" name="planosForm" enctype="multipart/form-data" style="margin:0px;" onsubmit="return false;">
        <html:hidden name="Form" property="idPlano"/>
        <table width="100%" border="0">
            <tr>
                <td>
                    <table width="770" border="0">
                        <tr>
                            <td>Regional:</td>
                            <td>
                                <html:select name="Form" property="idRegional" style="width:150px;" styleClass="SELECT">
                                    <option value="">-- Todas --</option>
                                    <html:optionsCollection name="Form" property="listaRegional.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                            <td>Tipo de Cliente:</td>
                            <td>
                                <html:select name="Form" property="idTpCliente" style="width:150px;" styleClass="SELECT">
                                    <option value="">-- Todas --</option>
                                    <html:optionsCollection name="Form" property="listaClientes.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                            <td>Tipo de Linha:</td>
                            <td>
                                <html:select name="Form" property="idTpLinha" style="width:150px;" styleClass="SELECT">
                                    <option value="">-- Todas --</option>
                                    <html:optionsCollection name="Form" property="listaLinhas.itArray" value="id" label="ds"/>
                                </html:select>
                            </td>
                            <td>
                                <acesso:controlHiddenItem nomeIdentificador="fid_mpl1_pesquisar">
                                    <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onclick="pesquisarPlanos()" style="cursor:pointer;">
                                </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <vivo:tbTable selectable="true" layoutWidth="755" layoutHeight="300" tableWidth="755" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="90%" type="string">Descrição do plano</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                        <logic:iterate id="it" name="Form" property="fidelizacaoVO.tabelas.planos.itArray" indexId="idx">
                            <vivo:tbRow key="linha1">
                                <vivo:tbRowColumn><span style="width:675;text-overflow:ellipsis;overflow:hidden;display:inline;"><bean:write name="it" property="ds"/></span></vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <acesso:controlHiddenItem nomeIdentificador="fid_mpl1_alterar">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alterarPlano('<bean:write name="it" property="id"/>');">
                                    </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                                <vivo:tbRowColumn>
                                    <acesso:controlHiddenItem nomeIdentificador="fid_mpl1_excluir">
                                        <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="excluirPlano('<bean:write name="it" property="id"/>')">
                                    </acesso:controlHiddenItem>
                                </vivo:tbRowColumn>
                            </vivo:tbRow>
                        </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                    <table width="772" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                        <tr>
                            <td width="100" valign="middle"><b>&nbsp;Legendas:</b></td>
                            <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Plano</td>
                            <td width="522"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Plano</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <div style="float:right;margin-top:10px;">
            <acesso:controlHiddenItem nomeIdentificador="fid_mpl1_incluir">
                <img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onclick="incluir();" style="cursor:pointer;"/>
            </acesso:controlHiddenItem>
        </div>
    </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
