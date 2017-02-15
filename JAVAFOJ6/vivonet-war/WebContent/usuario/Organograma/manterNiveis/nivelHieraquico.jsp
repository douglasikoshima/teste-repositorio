<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/> 
  
    <bean:define id="FormNivel" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formNivel"/>

    <netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Gerenciamento de Nível Hierárquico"/>
    <netui-template:setAttribute name="modulo" value="Organograma - Arvore de Niveis"/>

    <netui-template:section name="headerSection">

   <head>   
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
        function capturaParametros(idNivel, idNivelPai, dsNivel, op){
                document.forms[0].idNivel.value = idNivel;
                document.forms[0].idNivelPai.value = idNivelPai;
                document.forms[0].dsNivel.value = dsNivel;
                
                if(op==0)
                    habilitarBotton(0);
                else if(op==2)
                    habilitarBotton(2);
                else
                    habilitarBotton(1);
        }   
        function habilitarBotton(on) {

            var visible = '';
            if(on==0) {
                Javascript:document.getElementById('btinclui').style.display='none';
                Javascript:document.getElementById('btalterar').style.display='none';
                Javascript:document.getElementById('btexcluir').style.display='none';
                Javascript:document.getElementById('btrelcargo').style.display='none';
            } else if(on==2){
                Javascript:document.getElementById('btinclui').style.display='inline';
                Javascript:document.getElementById('btalterar').style.display='none';
                Javascript:document.getElementById('btexcluir').style.display='none';
                Javascript:document.getElementById('btrelcargo').style.display='none';
            } else {
                Javascript:document.getElementById('btinclui').style.display='inline';
                Javascript:document.getElementById('btalterar').style.display='inline';
                Javascript:document.getElementById('btexcluir').style.display='inline';
                Javascript:document.getElementById('btrelcargo').style.display='inline';
            }            
            ifrOperacoes.location.href='nothing.jsp';
            divFrame = document.getElementById('ifrOperacoes');
            divFrame.src = 'nothing.jsp';
        }
        
        function remover(){
            if(document.forms[0].idNivel.value==''){
                alert('Selecione um nó para ser excluido.');
                return;
            }
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].action = 'removeItem.do?idNivel='+document.forms[0].idNivel.value+'&idNivelPai='+document.forms[0].idNivelPai.value;
                mostrar_div();
                document.forms[0].submit();                
            } 
        }
        
        function alterar() {
            mostrar_div();
            divFrame = document.getElementById('ifrOperacoes');
            divFrame.src = 'alterarNivel.do?idNivel='+document.forms[0].idNivel.value+'&idNivelPai='+document.forms[0].idNivelPai.value+'&dsNivel='+document.forms[0].dsNivel.value;
        }
        
        function inclui(){
            mostrar_div();
            divFrame = document.getElementById('ifrOperacoes');
            divFrame.src = 'manterDescricao.do?idNivel='+document.forms[0].idNivel.value+'&idNivelPai='+document.forms[0].idNivelPai.value+'&dsNivel='+document.forms[0].dsNivel.value;
        }
        
        function relacionar() {
            mostrar_div();
            divFrame = document.getElementById('ifrOperacoes');
            divFrame.src = 'editaRelCargo.do?idNivel='+document.forms[0].idNivel.value+'&idNivelPai='+document.forms[0].idNivelPai.value+'&dsNivel='+document.forms[0].dsNivel.value;
        }
        
        </script>
    </head>
    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_nivh_verpagina">
    
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        
        <vivo:sessao id="qdMain" height="468" width="790" label="Usuários" operacoes="Níveis Hierárquicos" scroll="no">
            <form action="<%=request.getContextPath()%>/usuario/Organograma/manterNiveis/begin.do" id="begin" name="begin" method="POST">
                <html:hidden name="FormNivel" property="idNivel"/>
                <html:hidden name="FormNivel" property="idNivelPai"/>
                <html:hidden name="FormNivel" property="dsNivel"/>
                <table cellpadding="0" cellspacing="0" width="780" height="457">
                    <tr>
                        <td rowspan="2" width="390" valign="top" height="427">
                            <div id="arvoreNiveis" style="padding-left:10px;padding-top:10px;width:385px;height:435px;overflow:auto;" class="tbl_bgGray">
                                <script>
                                    <%=(String)request.getSession().getAttribute("arvore")%>
                                    <%request.getSession().setAttribute("arvore",(String)request.getSession().getAttribute("arvore"));%>
                                </script>
                            </div>
                        </td>
                        <td valign="top" width="390" height="80">
                            <vivo:quadro id="operacoesNiveis" width="390" height="60" label="Operações">
                                <table width="100%" height="100%">
                                    <tr>
                                        <td align="center">
                                        <acesso:controlHiddenItem nomeIdentificador="usu_nivh_incluir">
                                            <img id="btinclui" onclick="inclui();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>
                                        </acesso:controlHiddenItem>
                                        <acesso:controlHiddenItem nomeIdentificador="usu_nivh_alterar">
                                            <img id="btalterar" onclick="alterar();" src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_alterar_over.gif'" style="border:none;cursor:hand;"/>
                                        </acesso:controlHiddenItem>
                                        <acesso:controlHiddenItem nomeIdentificador="usu_nivh_remover">
                                            <img id="btexcluir" onclick="remover();" src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_excluir_over.gif'" style="border:none;cursor:hand;"/>
                                        </acesso:controlHiddenItem>
                                        <acesso:controlHiddenItem nomeIdentificador="usu_nivh_relacionar">
                                            <img id="btrelcargo" onclick="relacionar();" src="/FrontOfficeWeb/resources/images/bt_relcargo_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_relcargo_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_relcargo_over.gif'" style="border:none;cursor:hand;"/>
                                        </acesso:controlHiddenItem>
                                        </td>
                                    </tr>
                                </table>
                            </vivo:quadro>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" height="357">
                            <iframe scrolling="no" id="ifrOperacoes" src="nothing.jsp" frameborder="0" width="390" height="355"></iframe>
                        </td>
                    </tr>
                    <tr>
                        <td height="20">
                            <img onClick="window.location.href='/FrontOfficeWeb/inicio.jsp';" vspace="6" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
                        </td>
                    </tr>
                </table>
            </form>
        </vivo:sessao>
        
        <logic:equal name="FormNivel" property="flag" value="Sim">
        <bean:define id="arrayCargosExistentesVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formNivel.arrayCargosExistentesVO"/>
        <bean:define id="arrayCargosRelacionadosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formNivel.arrayCargosRelacionadosVO"/>
            <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
                //divFrame = document.getElementById('ifrOperacoes');
                //divFrame.src = 'editaRelCargo.do';
            </SCRIPT>
        </logic:equal>
        <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
            habilitarBotton(0);
            if('<bean:write name="FormNivel" property="msgError" />' != "")
            {
                alert('<bean:write name="FormNivel" property="msgError" />');
            }
            oculta_div();
        </script> 
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
