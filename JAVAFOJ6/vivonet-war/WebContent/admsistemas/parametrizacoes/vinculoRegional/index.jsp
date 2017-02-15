<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="parametrosForm" type="admsistemas.parametrizacoes.vinculoRegional.VinculoRegionalController.ParametrosForm" />

 <script>
        
        function showVinculos(obj) {
            document.forms[0].action = 'filtrar.do';
            document.forms[0].submit();
        }
        
        function limite() {
            if (document.forms[0].selecionadas.length == 5){
                alert('Você pode vincular apenas 5 regionais');
                return true;
            } 
        }
        
        
        function transfereSelecaoLista(listaOrigem, listaDestino){
        
            if (listaDestino == document.forms[0].selecionadas) {
            
                if (limite()){
                    abort;
                } 
                
            }
        
            
          var i;
          for(i = 0; i<listaOrigem.options.length; i++)
            if(listaOrigem.options[i].selected)
              listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    
          for(i = listaOrigem.options.length-1; i>=0; i--)
            if(listaOrigem.options[i].selected)
              listaOrigem.options[i] = null;
          
        }       
        function gravar() {
            var lista = document.forms[0].selecionadas;
			if ( lista.length > 0) {            
                for(i = 0;i<lista.length;i++){
                   document.forms[0].selecionadas.options[i].selected =true; 
                }             
                document.forms[0].action = 'gravar.do';
                document.forms[0].submit();            
            } else {
                
                if (confirm('Deseja apagar todos os vínculos?')){                
                    document.forms[0].action = 'desvinculartodos.do';
                    document.forms[0].submit();      
                }
            }
        }

    </script>


<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">

    <netui-template:setAttribute name="title" value="Parametrização de Vínculos entre Regionais"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
    </netui-template:section>

    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="470" width="790" label="Sistema" operacoes="Parametrização de Vínculos entre Regionais" scroll="no">

        <form name="gravarForm" id="gravarForm" action="gravar.do" method="post">
			<table width="400" align="center">
                <tr>
                    <td colspan="3" align="left">Regional:
                        <html:select name="Form" property="regionalSelecionada" styleId="regionalSelecionadaId" style="width:270px;" styleClass="SELECT" onchange="showVinculos(this);">
                            <option value="">-- Selecione --</option>
                            <logic:present name="Form" property="listaRegionais">
                                <logic:iterate id="lista" name="Form" property="listaRegionais" type="br.com.vivo.fo.workflow.vo.WFRegionalVODocument.WFRegionalVO">                            
                                    <option value="<bean:write name="lista" property="idRegional"/>" <%=lista.getIdRegional().equals(Form.getRegionalSelecionada())?"selected":""%>>
                                        <bean:write name="lista" property="dsRegional"/>
                                    </option>
                                </logic:iterate>
                            </logic:present>
                        </html:select>           
                    </td>         
                </tr>
                <tr>
                    <td height="50px" colspan="3">
                        <b>Vicular Regionais</b>
                    </td>
                </tr>
                <tr>    
                    <td>UFs vinculadas</td>
                    <td></td>
                    <td>UFs não vinculadas</td>
                </tr>                
                <tr>
                    <td>
                        <html:select name="Form" property="selecionadas"  size="10" multiple="true" style="width:125px;">
                            <logic:iterate id="it" name="Form" property="regionaisSelecionadas">
                                <option value="<bean:write name="it" property="idRegional"/>"><bean:write name="it" property="dsRegional"/></option>
                            </logic:iterate>                            
                        </html:select>                 
                    </td>
                    <td>
                        <a id="btLeftSimp" href="javascript:transfereSelecaoLista(document.forms[0].disponiveis, document.forms[0].selecionadas);"><img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" /></a><br><br>
                        <a id="btRightSimp" href="javascript:transfereSelecaoLista(document.forms[0].selecionadas, document.forms[0].disponiveis);"><img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" /></a>
                    </td>
                    <td>
                        <html:select name="Form" property="disponiveis"  size="10" multiple="true" style="width:125px;">
                            <logic:iterate id="it" name="Form" property="regionaisDisponiveis">
                                <option value="<bean:write name="it" property="idRegional"/>"><bean:write name="it" property="dsRegional"/></option>
                            </logic:iterate>                            
                        </html:select>                   
                    </td>
                </tr>   
                <tr>
                    <td>
                        <br>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="right">
                        <img onmouseup="gravar();"
						     align="middle"
							 id="btSalvar"
							 src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"
							 style="border:none;" />
                    </td>
                </tr>                     
            </table>
        </form>
        </vivo:sessao>
        <vivo:alert atributo="msgRetorno" scope="request" />
    </netui-template:section>
</netui-template:template>