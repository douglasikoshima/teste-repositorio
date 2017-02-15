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
  
    <bean:define id="FormOrganizacao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOrganizacao"/>
       
    
    <netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Gerenciamento de Nível Hierárquico"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    
    
    
    <netui-template:section name="headerSection">
    
   <head>
   
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript">
        function capturaParametros(idOrganizacao, idOrganizacaoPai, dsOrganizacao, tipoOrganizacao, op){
            
                document.forms[0].idOrganizacao.value = idOrganizacao;
                document.forms[0].idOrganizacaoPai.value = idOrganizacaoPai;
                document.forms[0].dsOrganizacao.value = dsOrganizacao;
                document.forms[0].tipoOrganizacao.value = tipoOrganizacao;
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
                //ifrOperacoes.location.href='nothing.jsp';
                //divFrame = document.getElementById('ifrOperacoes');
                //divFrame.src = 'nothing.jsp';
            } else if(on==2) {
                Javascript:document.getElementById('btinclui').style.display='inline';
                Javascript:document.getElementById('btalterar').style.display='none';
                Javascript:document.getElementById('btexcluir').style.display='none';
                Javascript:document.getElementById('btrelcargo').style.display='none';
                //ifrOperacoes.location.href='nothing.jsp';
                //divFrame = document.getElementById('ifrOperacoes');
                //divFrame.src = 'nothing.jsp';
            } else {
                //Javascript:document.getElementById('btinclui').style.display='inline';
                //Javascript:document.getElementById('btalterar').style.display='inline';
                //Javascript:document.getElementById('btexcluir').style.display='inline';
                //Javascript:document.getElementById('btrelcargo').style.display='inline';
                //divFrame = document.getElementById('ifrOperacoes');
                //divFrame.src = 'editaRelUnidade.do?idOrganizacao='+document.forms[0].idOrganizacao.value+'&idOrganizacaoPai='+document.forms[0].idOrganizacaoPai.value+'&dsOrganizacao='+document.forms[0].dsOrganizacao.value;  
            }
            ifrOperacoes.location.href='nothing.jsp';
            divFrame = document.getElementById('ifrOperacoes');
            divFrame.src = 'nothing.jsp';
        }
        
        function remover(){
            if(document.forms[0].idOrganizacao.value==''){
                alert('Selecione um nó para ser excluido.');
                return;
            }
            if (window.confirm("Confirma remoção do item?")) {
                document.forms[0].action = 'removeItem.do?idOrganizacao='+document.forms[0].idOrganizacao.value+'&idOrganizacaoPai='+document.forms[0].idOrganizacaoPai.value;
                top.frameApp.mostrar_div();
                document.forms[0].submit();            
            } 
        }
        
        function alterar() {
            mostrar_div();
            divFrame = document.getElementById('ifrOperacoes');
            divFrame.src = 'alterarOrganizacao.do?idOrganizacao='+document.forms[0].idOrganizacao.value+'&idOrganizacaoPai='+document.forms[0].idOrganizacaoPai.value+'&dsOrganizacao='+document.forms[0].dsOrganizacao.value+'&tipoOrganizacao='+document.forms[0].tipoOrganizacao.value;      
        }
        
        function inclui(){
            mostrar_div();
            divFrame = document.getElementById('ifrOperacoes');
            divFrame.src = 'manterDescricao.do?idOrganizacao='+document.forms[0].idOrganizacao.value+'&idOrganizacaoPai='+document.forms[0].idOrganizacaoPai.value+'&dsOrganizacao='+document.forms[0].dsOrganizacao.value+'&tipoOrganizacao='+document.forms[0].tipoOrganizacao.value;      
        }
        
        function relacionar() {
            top.frameApp.mostrar_div();
            divFrame = document.getElementById('ifrOperacoes');
            divFrame.src = 'editaRelUnidade.do?idOrganizacao='+document.forms[0].idOrganizacao.value+'&idOrganizacaoPai='+document.forms[0].idOrganizacaoPai.value+'&dsOrganizacao='+document.forms[0].dsOrganizacao.value+'&tipoOrganizacao='+document.forms[0].tipoOrganizacao.value;      
        }
        
        </script>   
        <script language="javascript" for="window" event="onload">
            <!--   
                habilitarBotton(0);
                if('<bean:write name="FormOrganizacao" property="msgError" />' != "")
                {
                    alert('<bean:write name="FormOrganizacao" property="msgError" />');
                }
                oculta_div();
            -->
            </script>         
    </head>
    
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_nivorg_verpagina">

        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <script>
            mostrar_div();
        </script>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        
        <vivo:sessao id="qdMain" height="468" width="790" label="Usuários" operacoes="Níveis Organizacionais" scroll="no">
        
         <form action="<%=request.getContextPath()%>/usuario/Organograma/manterOrganizacao/begin.do" id="begin" name="begin" onSubmit="return false;" method="POST">            
            <html:hidden name="FormOrganizacao" property="idOrganizacao"/>
            <html:hidden name="FormOrganizacao" property="idOrganizacaoPai"/>
            <html:hidden name="FormOrganizacao" property="dsOrganizacao"/>
            <html:hidden name="FormOrganizacao" property="tipoOrganizacao"/>
            
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
                                    <acesso:controlHiddenItem nomeIdentificador="usu_nivorg_incluir">      
                                    	<img id="btinclui" onclick="inclui();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" style="border:none;cursor:hand;"/>
                                    </acesso:controlHiddenItem>
                                    <acesso:controlHiddenItem nomeIdentificador="usu_nivorg_alterar">
                    					<img id="btalterar" onclick="alterar();" src="/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_alterar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_alterar_over.gif'" style="border:none;cursor:hand;"/>                                    
                                    </acesso:controlHiddenItem>
                                    <acesso:controlHiddenItem nomeIdentificador="usu_nivorg_remover">
                                        <img id="btexcluir" onclick="remover();" src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_excluir_over.gif'" style="border:none;cursor:hand;"/>                                    
                                    </acesso:controlHiddenItem>
                                    <acesso:controlHiddenItem nomeIdentificador="usu_nivorg_relacionar">
                                        <img id="btrelcargo" onclick="relacionar();" src="/FrontOfficeWeb/resources/images/bt_relacionamento_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_relacionamento_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_relacionamento_over.gif'" style="border:none;cursor:hand;"/>                                    
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
                        <img vspace="6" onClick="window.location.href='/FrontOfficeWeb/inicio.jsp';" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'" style="border:none;cursor:hand;"/>
                    </td>
                </tr>

            </table>
            <logic:equal name="FormOrganizacao" property="flag" value="Sim">
            <bean:define id="arrayUnidadesExistentesVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOrganizacao.arrayUnidadesExistentesVO"/>
            <bean:define id="arrayUnidadesRelacionadosVO"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formOrganizacao.arrayUnidadesRelacionadosVO"/>                
            </logic:equal>            
            </form>
        </vivo:sessao>

       
        
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
