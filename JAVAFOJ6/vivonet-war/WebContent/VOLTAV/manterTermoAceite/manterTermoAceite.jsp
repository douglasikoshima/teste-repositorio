<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>
<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formTermoAceite" type="VOLTAV.manterTermoAceite.ManterTermoAceiteController.FormTermoAceite"/>

<netui-template:setAttribute name="title" value="Termo Aceite"/>
<netui-template:setAttribute name="modulo" value="VOL/TAV"/>

<netui-template:section name="headerSection">

<script type="text/javascript" language="javascript">
    
        function pesquisarServico()
        {
           if (document.getElementById("idServicoSelecionado").selectedIndex == 0) {
              document.forms[0].action = "manterTermoAceite.do"; 
           } else {
              document.forms[0].action = "buscarTermoPorServico.do"; 
           }
           mostrar_div();
           document.forms[0].submit();     
                
        }
        
        function gravarTermoAceite() {
            if(validar()) {
                document.forms[0].action = "salvarTermoPorServico.do";
                mostrar_div();
                document.forms[0].submit();
            }
        
        }
        
        function validar() {
        
            var servicoSelecionado = document.getElementById("idServicoSelecionado");
            var statusAtivo = document.forms[0].statusTermo[0];
            var statusInativo = document.forms[0].statusTermo[1];
            var textoTermo = document.getElementById("textoTermoAceite");
            
            if (servicoSelecionado.selectedIndex <= 0) {
                alert("Selecione um serviço para atualizar o termo de aceite.");
                return false;
            }
            
            if (!statusAtivo.checked && !statusInativo.checked) {
                alert("Selecione um status para o termo de aceite.");
                return false;
            }
            
             if (statusAtivo.checked && trim(textoTermo.value) == "") {
                alert("Para ativar o campo texto é obrigatório.");
                return false;
            }

            if (statusAtivo.checked && trim(textoTermo.value) == "") {
                alert("Texto obrigatório");
                return false;
            } else  if(statusAtivo.checked && textoTermo.value.length > 4000){
                    alert("Tamanho do texto não permitido, acima de 4000 caracteres.");
            }
        
            return true;
        }
        
        function verificaEnter(ev){
            if(ev.keyCode == 13)
                return false;
        }

</script> 
</netui-template:section>    
<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    
        <vivo:sessao id="listaServicos"  width="790" height="468" scroll="false" operacoes="Consulta de Termo Aceite">            
            <form action="buscarTermoPorServico.do" method="post" name="formTermoAceite">
           
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                            <td align="left"><b>Servi&ccedil;os:</b></td>
                           <td style="text-indent:6px;">&nbsp;
                                <bean:define id="listaItemMenuTermo" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formTermoAceite.listaItemMenuTermo"/>
                                <html:select name="Form" property="idServicoSelecionado" style="width:165pt;" onchange="pesquisarServico();">
                                    <html:option value="0">Escolha...</html:option>
                                    <html:options collection="listaItemMenuTermo" property="idItemMenu" labelProperty="nmItem"/>
                                </html:select>
                            </td>
                    </tr>
                    <tr>
                            <td align="left"><b>Status</b></td>
                            <td align="left">
                                 <html:radio name="Form" property="statusTermo" value="1" styleClass="radio"  id="radioAtivo"/><b>Ativo</b>
                                 <html:radio name="Form" property="statusTermo" value="0" styleClass="radio" id="radioInativo" /><b>Inativo</b>   
                            </td>
                    </tr>
                    </table>
                    
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tr>
                        <td colspan="4" valign="top">
                            <html:textarea name="Form" property="textoTermoAceite" 
                                                       style="width:750;px;height:300;font-family:Tahoma;font-size:11px;" 
                                                       onkeypress=""/>                                                         
                        </td>
                    </tr>           
              </table>
              <acesso:controlHiddenItem nomeIdentificador="voltav_termo_gravar">
              <table width="90%" border="0">
               <tr>
                        <td colspan="4">&nbsp;</td>
              </tr>
              <tr>
                        <td colspan="4" align="right">
                            <img id="btGravar" onClick="gravarTermoAceite();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'"  onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" style="cursor:hand;border:none;" />
                        </td>
               </tr>
            </table>  
            </acesso:controlHiddenItem>          
            </form>            
        </vivo:sessao>
        <iframe id="ifrmFalse" name="ifraSubmit" style="visibility:hidden;" width="0px" height="0px"></iframe>
        <vivo:alert atributo="msgStatus" scope="request"/>

 </netui-template:section>
</netui-template:template>