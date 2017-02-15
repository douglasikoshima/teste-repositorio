<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<acesso:controlInitEnv/>

<bean:define id="form"              name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" name="listaDefIntencaoForm" />
<bean:define id="listaIntencaoVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaIntencaoVO.itemListaVOArray"/>
<bean:define id="labelInput"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="labels[0]"/>
<bean:define id="labelAlterar"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="labels[1]"/>
<bean:define id="labelExcluir"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="labels[2]"/>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
        <script language="javascript">
        function excluir(obj){
            if (confirm('Deseja realmente excluir este registro?')){
                enviarForm(obj);
            }
        }
        function inclui(obj){
            document.forms[0].elements["descricao"].value = trim(document.forms[0].elements["descricao"].value);
            if(document.forms[0].elements["descricao"].value == ""){
                alert("Favor preencher o Destino Previsto!");
            }else{
                enviarForm(obj);
            }
        }
        semCaracterEspeciais = new RegExp("[0-9a-zA-z% ]");
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
        function preValidaKey(){
            if (window.event.keyCode == 13){
                window.event.keyCode = 0;
                return false;
            }
        }
        function enviarForm(obj){
            document.forms[0].elements["descricao"].value = trim(document.forms[0].elements["descricao"].value);
            top.frameApp.mostrar_div();
            document.forms[0].action = obj.href;
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
    <acesso:controlHiddenItem nomeIdentificador="fid_di_verpagina">
    <form name="listaDefIntencaoForm" id="listaDefIntencaoForm" action="listarIntencoes.do" onKeyPress="preValidaKey();" method="post">
        <html:hidden name="form" property="isEdit"/>
        <html:hidden name="form" property="inMsgRetorno" id="inMsgRetorno" />
        <table width="100%">
            <tr>
                <td>
                    <table width="770">
                        <tr>
                            <td width="155"><bean:write name="labelInput" /></td>
                            <td width="490">
                                <html:text name="form" property="descricao" maxlength="2000" style="width:480px;" />
                                <html:hidden name="form" property="id" /> </td>
                            <td width="135" valign="middle" nowrap align="right">
                                <acesso:controlHiddenItem nomeIdentificador="fid_di_incluir">
                                    <img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif"
                                         href="/FrontOfficeWeb/fidelizacao/Manter/ManterDefIntencao/persistirIntencao.do?acao=ok"
                                         class="button" onmouseup="inclui(this);" />
                                </acesso:controlHiddenItem>
                                <acesso:controlHiddenItem nomeIdentificador="fid_di_pesquisar">
                                    <img src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif"
                                         href="/FrontOfficeWeb/fidelizacao/Manter/ManterDefIntencao/listarIntencoes.do?acao=consultar"
                                         class="button" onmouseup="enviarForm(this);" />
                                </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <vivo:tbTable selectable="true" onRowClick="" layoutWidth="755" layoutHeight="320" tableWidth="755" styleId="tableTitle" sortable="true">
                        <vivo:tbHeader>
                            <vivo:tbHeaderColumn align="left" width="90%" type="string">Descrição</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                            <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
                        </vivo:tbHeader>
                        <vivo:tbRows>
                            <netui-data:repeater dataSource="{pageContext.listaIntencaoVO}">
                                <netui-data:repeaterHeader> </netui-data:repeaterHeader>
                                <netui-data:repeaterItem>
                                    <vivo:tbRow key="linha1">
                                        <vivo:tbRowColumn>
                                            <netui:label value="{container.item.descricao}" defaultValue="&nbsp;"/></vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="fid_di_alterar">
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif"
                                                 class="button"
                                                 href="/FrontOfficeWeb/fidelizacao/Manter/ManterDefIntencao/editarIntencao.do?acao=editar&descricao=<netui:content value="{container.item.descricao}" />&id=<netui:content value="{container.item.id}"/>"
                                                 onmouseup="enviarForm(this);" />
                                        </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                        <vivo:tbRowColumn>
                                        <acesso:controlHiddenItem nomeIdentificador="fid_di_excluir">
                                            <img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif"
                                                 class="button"
                                                 href="/FrontOfficeWeb/fidelizacao/Manter/ManterDefIntencao/excluirIntencao.do?acao=excluir&id=<netui:content value="{container.item.id}"/>"
                                                 onmouseup="excluir(this);" />
                                        </acesso:controlHiddenItem>
                                        </vivo:tbRowColumn>
                                    </vivo:tbRow>
                                </netui-data:repeaterItem>
                                <netui-data:repeaterFooter></netui-data:repeaterFooter>
                            </netui-data:repeater>
                        </vivo:tbRows>
                    </vivo:tbTable>
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                    <table width="772" height="26" border="0" cellspacing="0" cellpadding="5" style="border:1px solid #D4D7DE; background-color:#F7F9FA; font-size:10px;">
                        <tr>
                            <td width="100" valign="middle"><b>&nbsp;Legendas:</b></td>
                            <td width="150"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" align="middle"> &nbsp;<bean:write name="labelAlterar" /></td>
                            <td width="522"><img vspace="2" src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" align="middle"> &nbsp;<bean:write name="labelExcluir" /></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <script language="javascript">
            document.body.style.backgroundColor = '#ededed';
<logic:notEmpty name="msgErro">
    alert("<bean:write name="msgErro"/>");
</logic:notEmpty>

inicio = location.href.indexOf("acao=")+5;
fim = location.href.length;
if(location.href.substring(inicio,fim) == "erro"){
    alert("Já existe um registro cadastrado com essa descrição!");
}

if(location.href.substring(inicio,fim) == "erroMatriz"){
    alert("Existe Matriz cadastrada para esta Intenção.");
}

if(location.href.substring(inicio,fim) == "editar"){
    document.getElementById('incluir').firstChild.src = "/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif";
    document.getElementById('incluir').firstChild.onmouseover = over;
    document.getElementById('incluir').firstChild.onmouseout = out;

    function over(){
        swapImage(document.getElementById('incluir').firstChild,'/FrontOfficeWeb/resources/images/bt_alterar_over.gif');
    }

    function out(){
        swapImage(document.getElementById('incluir').firstChild,'/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif');
    }
}
        </script>
    </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>