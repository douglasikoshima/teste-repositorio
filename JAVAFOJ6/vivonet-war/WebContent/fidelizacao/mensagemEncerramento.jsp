<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<acesso:controlInitEnv/>

<bean:define id="Form"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="consultaMatrizOfertaForm" />
<bean:define id="listaMensagensVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaMensagensVO.itemListaVOArray" />

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript">
            function finaliza(){
                <% if(ConstantesCRM.SONE.equals(request.getAttribute("inAtualizacaoContato"))){ %>
                if(trim($F('dsContato'))=="" || trim($F('dsContato')).length < 12){
                    alert("Por favor, digite o número de contato do cliente.")
                    setTimeout(function(){$('dsContato').focus()},300);
                    return false;
                }
                <% } %>
                
				<%--
				if(document.forms[0].elements["{actionForm.observacao}"].value.length > 400){
                    alert("Tamanho da mensagem superior do que o permitido.\nPor favor reescrever a observação utilizando 400 caracteres.");
                    document.forms[0].elements["{actionForm.observacao}"].value = document.forms[0].elements["{actionForm.observacao}"].value.substr(0,400);
                    return false;
                }--%>
                
				if(document.forms[0].elements["observacao"].value.length > 400){
                    alert("Tamanho da mensagem superior do que o permitido.\nPor favor reescrever a observação utilizando 400 caracteres.");
                    document.forms[0].elements["observacao"].value = document.forms[0].elements["observacao"].value.substr(0,400);
                    return false;
                }
                //document.forms[0].target = "frmRetencao";
                //document.forms[0].action="MsgEncerramento.do?acao=adicionarObs&user=<%=request.getAttribute("user")%>";
                parent.parent.mostrar_div();
                document.forms[0].submit();
            }

            function verificarTmnMsg(obj){
                if(obj.value.length > 400){
                    obj.value = obj.value.substr(0,400);
                }
            }
            <%String nrProtocolo = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO); %>
            updateNrProtocolo = function() {
				top.frameApp.nrProtocolo = '<%=nrProtocolo!=null?nrProtocolo:ConstantesCRM.SVAZIO%>'
                top.frameApp.$('nrProtocolo').update('<%=nrProtocolo!=null?nrProtocolo:ConstantesCRM.SVAZIO%>');
                top.frameApp.setAlteracaoSMSSolicitacaoProtocolo('alteracaoSMS');
            }

            <logic:notEmpty name="msgSAP">
                alert("<bean:write name="msgSAP"/>");
            </logic:notEmpty>
        </script>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
                <!--
                    parent.parent.oculta_div();
                -->
        </SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_me_verpagina">
    <vivo:body idTable="tbMain" idDiv="dvMain" height="440" width="760">
    <form action="finalizarRetencao.do" method="post">
    <br><br>
    <table width="100%">
        <tr align="left" >
            <td align="center">
                <b><netui-data:repeater dataSource="{pageContext.listaMensagensVO}">
                    <netui-data:repeaterHeader> </netui-data:repeaterHeader>
                    <netui-data:repeaterItem>
                         <netui:label value="{container.item.descricao}" />&nbsp;
                    </netui-data:repeaterItem>
                    <netui-data:repeaterFooter></netui-data:repeaterFooter>
                </netui-data:repeater>
                </b>
            </td>
        </tr>
    </table>

    <% if(ConstantesCRM.SONE.equals(request.getAttribute("inAtualizacaoContato"))){ %>
    <vivo:quadro id="qdMain" height="50" width="740" label="Atualiza&ccedil;&atilde;o de Contato">
        <input type="hidden" name="idPessoa" id="idPessoa" value="<%=request.getAttribute("idPessoa")%>" />
        <span style="line-height:22px">O número de contato deste cliente não é atualizado há mais de 30 dias. Por favor, atualize-o.<br/></span>
        <label for="dsContato">Contato:</label>
        <input type="text" maxlength="14" name="dsContato" style="width:130px;" id="dsContato" value="<%=request.getAttribute("dsContato")%>" onkeyup="maskPhoneNumberObj(this)" />
    </vivo:quadro>

    <% }else{ %>
    <br><br><br>
    <% } %>
    <br><br>
    <vivo:quadro id="qdMain" height="170" width="740" label="Observação">
    <table>
        <tr>
            <td>
             <html:textarea name="Form" property="observacao" styleClass="textfield" style="width=720px;" rows="8" onKeyDown="verificarTmnMsg(this);" onKeyUp="verificarTmnMsg(this);" onKeyPress="verificarTmnMsg(this);" onClick="verificarTmnMsg(this);" onBlur="verificarTmnMsg(this);"/>
             <html:hidden name="Form" property="idRetencao" />
            </td>
        </tr>
        <tr>
            <td align="right" valign="bottom">
            <acesso:controlHiddenItem nomeIdentificador="fid_me_ok">
                 <img src="/FrontOfficeWeb/resources/images/bt_ok_nrml.gif" style="border:none;cursor:hand;" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_ok_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_ok_over.gif'"  onClick="finaliza();"/>
            </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
     </vivo:quadro>
    </form>

    <vivo:alert atributo="nrProtocolo" scope="request" afterFunction="updateNrProtocolo()" />

    </vivo:body >
</acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>


