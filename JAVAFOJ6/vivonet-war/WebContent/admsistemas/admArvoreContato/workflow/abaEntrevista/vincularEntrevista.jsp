<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormArvoreBaixa"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formArvoreBaixa"/>

<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <script language='Javascript'>
    
        function capturaParametrosArvoreBaixa(idBaixa, inFolha)
        {
            // Se FOLHA
            if(inFolha == '1')
            {
                document.getElementById('btnIncluir').style.display = 'block';
                document.getElementById('btnExcluir').style.display = 'none';

            }
            else
            {
               document.getElementById('btnIncluir').style.display = 'none'; 
               document.getElementById('btnExcluir').style.display = 'none'; 

            }
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
                    document.forms[0].target = '';
                    parent.mostrar_div();
                    document.forms[0].submit();
                }else
                    return;
            }
        }
        
        
        function expandirNoArvoreBaixa(idBaixa, inFolha)
        {
        
            // Se FOLHA
            if(inFolha == '1')
            {
                document.getElementById('btnIncluir').style.display = 'block';
                document.getElementById('btnExcluir').style.display = 'none';
            }
            else
            {
               document.getElementById('btnIncluir').style.display = 'none';
               document.getElementById('btnExcluir').style.display = 'none';

            }
               
            document.forms[0].idBaixa.value = idBaixa;

            if (!tree.getSelected().isAddEnabled()) 
            {
                return;
            }
            document.forms[0].method = "POST";
            document.forms[0].action = "montaArvoreParte.do";
            document.forms[0].target = "iframeEntrevistaBaixa";
            parent.mostrar_div();
            document.forms[0].submit();


        }

        function expandirNoArvoreBaixaAssociada(idBaixa, inFolha)
        {
            // Se FOLHA
            if(inFolha == '1')
            {
                document.getElementById('btnExcluir').style.display = 'block';
                document.getElementById('btnIncluir').style.display = 'none';

            }
            else
            {
               document.getElementById('btnExcluir').style.display = 'none';
               document.getElementById('btnIncluir').style.display = 'none';

            }
               
            document.forms[0].idBaixa.value = idBaixa;

            if (!tree.getSelected().isAddEnabled()) 
            {
                return;
            }
            document.forms[0].method = "POST";
            document.forms[0].action = "montaArvoreParte.do?isAssociada=true";
            document.forms[0].target = "iframeEntrevistaBaixa";
            parent.mostrar_div();
            document.forms[0].submit();


        }
        
        function capturaParametrosArvoreBaixaAssociada(idBaixa, inFolha)
        {

            // Se FOLHA
            if(inFolha == '1')
            {
                document.getElementById('btnExcluir').style.display = 'block';
                document.getElementById('btnIncluir').style.display = 'none';
            }
            else
            {
               document.getElementById('btnExcluir').style.display = 'none';     
               document.getElementById('btnIncluir').style.display = 'none';     
            }

            document.forms[0].idBaixa.value = idBaixa;
        }
        

        function inserir(){
            if(document.forms[0].idBaixa.value==''){
                alert('Selecione um Item.');
                return;
            }else{
                document.forms[0].action ='inserirNo.do';
                document.forms[0].target = '';
                parent.mostrar_div();
                document.forms[0].submit();

            }
        }

    </script>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_ave_verpagina">
        <form action="inserirNo" name="inserirNo" id="inserirNo" method="post">
        <html:hidden name="FormArvoreBaixa" property="idContato"/>
        <html:hidden name="FormArvoreBaixa" property="idBaixa"/>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                
                <table width="725" cellpadding="0" cellspacing="0" align="center">
                    <tr>
                        <td width="372" valign="middle" colspan="2">
                            <vivo:quadro id="operArvBaixa" width="372" height="363" label="&Aacute;rvore de Baixa">
                                
                                <div style="width: 362px; top: 0px; left: 0px; height: 310px; padding: 5px; overflow: auto; border-bottom:1px solid #adadad;" class="tbl_bgGray">
                                <script>
                                    <%try{%>
                                        <%=(String)request.getAttribute("arvoreBaixa")%>
                                        <%request.removeAttribute("arvoreBaixa");%>
                                        <%}catch(Exception ex){}%>
                                </script>
                            </div>
                            
                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                            
                            <table class="tbl_bgGray" border="0" width="362" height="38">
                                <tr>
                                    <td width="150"><b>&nbsp;&nbsp;Incluir Folha da Árvore</b></td>
                                    <td align="center">
                                    <!--acesso:controlHiddenItem nomeIdentificador="adm_ave_incluir" -->
                                    <img style="border:none;cursor:hand;display:none;" id="btnIncluir" onclick="inserir(); return false" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'"/>
                                    <!--/acesso:controlHiddenItem -->
                                    </td>
                                </tr>
                            </table>
                            
                            </vivo:quadro>
                            
                        </td>
                        <td width="10"></td>
                        <td width="342" rowspan="2" valign="top">
                            <vivo:quadro id="operArvBaixaAssociada" width="342" height="363" label="&Aacute;rvore de Baixa Associada">
                                
                                <div style="width: 332px; top: 0px; left: 0px; height: 310px; padding: 5px; overflow: auto; border-bottom:1px solid #adadad;" class="tbl_bgGray">
                                                <script>
                                                    <%try{%>
                                                        <%=(String)session.getAttribute("arvoreBaixaAssociada")%>
                                                        <%session.removeAttribute("arvoreBaixaAssociada");%>
                                                    <%}catch(Exception ex){}%>
                                                </script>
                                            </div>
                                            
                                            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
                                            
                                            <table class="tbl_bgGray" border="0" width="332" height="38">
                                                <tr>
                                                    <td width="150"><b>&nbsp;&nbsp;Excluir Folha da Árvore</b></td>
                                                    <td align="center">
                                                        <img onclick="excluir(); return false" id="btnExcluir" style="border:none;cursor:hand;display:none;" src="/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_excluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_excluir_over.gif'"/>
                                                    </td>
                                                </tr>
                                            </table>                                
                                
                                
                                <!-- iframe id="ifrmArvBaixaAssociada" src="arvoreBaixaAssociada.jsp" width="332" height="352" frameborder="0" scrolling="no"></iframe -->
                                
                            </vivo:quadro>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td></td>
                    </tr>
                
                </table>
                    <iframe scrolling="yes" style="display:none;" name="iframeEntrevistaBaixa" height="0px" width="0px"></iframe>

            </form> 
    
    <script language="javascript" for="window" event="onload">
        <!--   
            parent.oculta_div();
    </script> 

        <vivo:alert atributo="msgError" scope="request"/>

        </acesso:controlHiddenItem>   
        </netui-template:section>
</netui-template:template>
