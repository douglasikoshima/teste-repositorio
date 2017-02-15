<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

   

<bean:define id="FormArvoreBaixaAssociada"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreBaixaAssociada"/>

<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <script language="Javascript">
    
        function capturaParametrosArvoreBaixaAssociada(idBaixa){
            document.forms[0].idBaixa.value = idBaixa;
        }
        
        function excluir(){
            if(document.forms[0].idBaixa.value==''){
                alert('Selecione um Item.');
                return;
            }else{
                if(window.confirm("Confirma remoção do item?"))
                {
                    document.forms[0].action ='excluirNo.do';
                    document.forms[0].target = '_parent';
                    parent.parent.mostrar_div();
                    document.forms[0].submit();
                }else
                    return;
            }
        }
        
        function expandirNoArvoreBaixa(idBaixa, inFolha)
        {
            // Se FOLHA
            if(inFolha == '1')
                document.getElementById('btnIncluir').style.display = 'block';
            else
               document.getElementById('btnIncluir').style.display = 'none';
               
            document.forms[0].idBaixa.value = idBaixa;
            alert("foi...");
            if (!tree.getSelected().isAddEnabled()) 
            {
                return;
            }
            alert("passou...");
            document.forms[0].method = "POST";
            document.forms[0].action = "montaArvoreParteAssociada.do";
            document.forms[0].target = "iframeEntrevistaBaixa";
            parent.parent.mostrar_div();
            document.forms[0].submit();


        }

        
    </script>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_aba_verpagina">
        <form action="excluirNo" name="excluirNo" id="excluirNo" method="post">
            <html:hidden name="FormArvoreBaixaAssociada" property="idContato"/>
            <html:hidden name="FormArvoreBaixaAssociada" property="idBaixa"/>
            <div style="width: 332px; top: 0px; left: 0px; height: 310px; padding: 5px; overflow: auto; border-bottom:1px solid #adadad;" class="tbl_bgGray">
                            <script>
                                <%try{%>
                                    <%=(String)session.getAttribute("arvoreBaixaAssociada")%>
                                    <%session.removeAttribute("arvoreBaixaAssociada");%>
                                <%}catch(Exception ex){}%>
                            </script>
                        </div>
                        
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                        
                        <table class="tbl_bgGray" width="332" height="38">
                            <tr>
                                <td width="150"><b>&nbsp;&nbsp;Excluir Folha da Árvore</b></td>
                                <td align="center">
                                <acesso:controlHiddenItem nomeIdentificador="adm_aba_excluir">
                                <img onclick="excluir(); return false" id="btnIncluir" style="border:none;cursor:hand;" src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_excluir_over.gif'"/>
                                </acesso:controlHiddenItem>
                                </td>
                            </tr>
                        </table>
        </form>
        
        <vivo:alert atributo="msgError" scope="request"/>
        
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
