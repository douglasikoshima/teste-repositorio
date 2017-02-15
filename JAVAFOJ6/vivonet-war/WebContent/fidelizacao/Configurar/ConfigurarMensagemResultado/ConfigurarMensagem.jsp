<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="lMensagem"                name="lMensagem" />
<bean:define id="ListaMsgEncerramentoForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaMsgEncerramentoForm"/>
<bean:define id="RegionalArray"            name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaMsgEncerramentoForm.regionalArray"/>
<bean:define id="AcaoRetencionArray"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaMsgEncerramentoForm.acaoRetencionArray"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
        <script language="javascript">
            function excluir(obj, act){
                if (confirm('Deseja realmente excluir este registro?')){
                    document.listaMsgEncerramentoForm.action = act;
                    document.listaMsgEncerramentoForm.submit();
                }
            }

            function inclui(obj){
                if(document.listaMsgEncerramentoForm.regionalSelecionado.value == ""){
                    alert("Favor selecionar uma Regional!");
                }else if(document.listaMsgEncerramentoForm.acaoRetencaoSelecionado.value == ""){
                    alert("Favor selecionar uma Ação de Retenção!");
                }else if(document.listaMsgEncerramentoForm.elements["descricao"].value == ""){
                    alert("Favor preencher o campo Descrição!");
                }else{
                    /*
                    valor = obj.href.split("?");
                    valor[1]?action = document.listaMsgEncerramentoForm.action + "?" + valor[1]:action = document.listaMsgEncerramentoForm.action;
                    anchor_submit_form("Netui_Form_0",action);
                    */
                    document.forms[0].action = obj.href;
                    document.forms[0].submit();
                }
            }

            function limpa(){
                document.getElementById('tableTitle_body').style.display="none"
            }

            semCaracterEspeciais = new RegExp("[0-9a-zA-z.% ]");

            function validacionStrEspecial(obj){
                valor = obj.value;
                for(i=0;i<valor.length;i++){
                    if(!semCaracterEspeciais.test(valor.charAt(i))){
                        valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                        i = -1;
                    }
                }
                obj.value = valor;
            }

            function validarCombos(obj){
                msg = "";
                if(document.forms[0].elements["regionalSelecionado"].options[document.forms[0].elements["regionalSelecionado"].selectedIndex].value==""){
                    msg+= "- Regional\n";
                }
                /*if(document.forms[0].elements["acaoRetencaoSelecionado"].options[document.forms[0].elements["acaoRetencaoSelecionado"].selectedIndex].value==""){
                    msg+= "- Ação de Retenção\n";
                }*/
                if(msg!=""){
                    alert("Por favor selecione:\n" + msg);
                    return false;
                }
                document.forms[0].action = 'ListaMsgEncerramento.do?acao=consultar';
                document.forms[0].submit();
            }
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            <!--
                top.frameApp.oculta_div();
            -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_cmens_verpagina">
    <form name="listaMsgEncerramentoForm" action="ListaMsgEncerramento.do" method="post">
        <table width="100%">
            <tr>
                <td align="center">
                <table width="745">
                    <tr>
                        <td  valign="top">
                            <netui:label value="Regional:"/><br>
                            <html:select name="ListaMsgEncerramentoForm" property="regionalSelecionado" style="width:150px" styleClass="SELECT" size="1" onchange="limpa();">
                                <option value="">Selecione</option>
                                <html:options collection="RegionalArray" property="id" labelProperty="descricao" />
                            </html:select>

                        </td>
                        <td>
                            <netui:label value="Ação de Retenção:"/><br>
                            <html:select name="ListaMsgEncerramentoForm" property="acaoRetencaoSelecionado" style="width:180px" styleClass="SELECT" size="1"  onmouseover="ativarToolTip(this);">
                                <option value="">Selecione</option>
                                <html:options collection="AcaoRetencionArray" property="id" labelProperty="descricao" />
                            </html:select>
                            <script language="Javascript">
                            for(i=0;i<document.forms[0].acaoRetencaoSelecionado.options.length;i++){
                                if(document.forms[0].acaoRetencaoSelecionado.options[i].value == ""){
                                    //document.forms[0].acaoRetencaoSelecionado.options[i] = null;
                                }
                            }
                            </script>
                        </td>
                        <td>
                            Descrição:<br>
                            <html:text name="ListaMsgEncerramentoForm"
                                       property="descricao"
                                       styleClass="textfield" maxlength="255" size="70" style="width:270px;" />
                        </td>
                        <td valign="bottom" align="right">
                            <html:hidden name="ListaMsgEncerramentoForm" property="idMensagemResultado" />
                            <acesso:controlHiddenItem nomeIdentificador="fid_cmens_incluir">
                                <img id="incluir" href="ListaMsgEncerramento.do?acao=salvar"
                                     src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
                                     onmouseup="inclui(this); return false"
                                     class="button" />
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="fid_cmens_pesquisar">
                                <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                                     onmouseup="validarCombos(this); return false"
                                     class="button" />
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>

                </table>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <vivo:tbTable selectable="true"  layoutWidth="730" layoutHeight="300" tableWidth="745" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="30%" type="string">Regional</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="45%" type="string">Descrição</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="15%" type="string">Ação de Retenção</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>

                        <vivo:tbRows>

                            <logic:iterate name="lMensagem" id="item" indexId="c">
                                <vivo:tbRow key="linha1">


                                    <vivo:tbRow key="linha1">
                                        <vivo:tbRowColumn>
                                            <bean:write name="item" property="dsRegional" />
                                        </vivo:tbRowColumn>

                                        <vivo:tbRowColumn>
                                            <bean:write name="item" property="descricao" />
                                        </vivo:tbRowColumn>

                                        <vivo:tbRowColumn>
                                            <bean:write name="item" property="dsAcaoRetencao" />
                                        </vivo:tbRowColumn>

                                        <vivo:tbRowColumn>
                                            <acesso:controlHiddenItem nomeIdentificador="fid_cmens_alterar">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"
                                                     onmouseup="document.forms[0].action='ListaMsgEncerramento.do?acao=editar&valor=<%=c%>';document.forms[0].submit();"
                                                     class="button" />
                                            </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                            <acesso:controlHiddenItem nomeIdentificador="fid_cmens_excluir">
                                                <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif"
                                                     onmouseup="excluir(this, 'ListaMsgEncerramento.do?acao=excluir&valor=<%=c%>')"
                                                     class="button" />
                                            </acesso:controlHiddenItem>
                                            </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </vivo:tbRow>
                            </logic:iterate>
                        </vivo:tbRows>
                    </vivo:tbTable>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                <table width="745" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                    <tr>
                        <td valign="middle"><b>&nbsp;Legendas:</b></td>
                        <td><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;Alterar Descrição</td>
                        <td><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;Excluir Descrição</td>
                        <td width="300"></td>
                    </tr>
                </table>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                </td>
            </tr>
        </table>
<script language="javascript">
inicio = location.href.indexOf("acao=")+5
fim = location.href.length
if(location.href.substring(inicio,fim) == "erro"){
    alert("Mensagem já cadastrado")
}
if(location.href.substring(inicio,inicio+6) == "editar"){
    document.getElementById('incluir').src = "/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif";
}
</script>
    </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>