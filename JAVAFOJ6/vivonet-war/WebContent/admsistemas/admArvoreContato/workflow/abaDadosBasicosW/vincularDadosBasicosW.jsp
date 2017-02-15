<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="FormDadosBasicosW"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosBasicosW"/>
<bean:define id="TipoRetornoContatoVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formDadosBasicosW.tipoRetornoContatoVO"/>

<netui-data:getData resultId="warningFrame" value="{globalApp.warningFrame}" />

<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_vdb_verpagina">
        <form action="salvaDadosBasicosW" name="salvaDadosBasicosW" id="salvaDadosBasicosW" method="post">
            <html:hidden name="FormDadosBasicosW" property="idContato"/>
    
            <script language="Javascript">        
                
                oldObj = "";
                oldValor = "";
                inteiro = new RegExp("[0-9]");
                
                function checaHora(obj){
                  valor = obj.value;
                  if(valor != oldValor || oldObj != obj){
                    for(i=0;i<valor.length;i++){
                      if(!inteiro.test(valor.charAt(i))){
                        valor = valor.substring(0,i) + valor.substring(i+1,valor.length)
                        if(valor.length == 1){
                          !inteiro.test(valor)?valor = "":0;
                        }
                        i = 0;
                      }
                    }
                    valor = valor.substring(0,valor.length - 2) + ":" +  valor.substring(valor.length - 2,valor.length)
                    for(i=0;valor.charAt(0) == "0" && valor.length > 4 ;i++){
                      valor.charAt(0) == "0"?valor = valor.substring(1,valor.length):0;
                    }
                    valor == ":00"?valor = "0:00":0;
                    obj.value = valor;
                    oldValor = valor;
                    oldObj = obj;
                  }
                }
                
                function validaHora(){
                  valor = document.forms[0].qtDiasPrazoContato.value.split(':')
                  if(document.forms[0].qtDiasPrazoContato.value == ""){
                    alert('Favor preencher o campo hora!');
                    return;
                  }else if(document.forms[0].qtDiasPrazoContato.value.length < 4){
                    alert('Hora inválida, favor Corrigir!');
                    return;
                  }else if(valor[1] && parseInt(valor[1]) > 60){
                    alert('Minutos inválido, favor Corrigir!');
                    return;
                  }else{
                    salvar();
                    
                  }
                }
                document.body.style.backgroundColor = "#ededed";
                
                function initPagina() { }
                
                function salvar(){
                    
                    if(document.forms[0].qtDiasPrazoContato.value==''){
                        alert('Prazo inválido.');
                        return;
                    }else{
                        if(document.forms[0].vlPesoContato.value==''){
                            alert('Peso inválido.');
                            return;
                        }else{
                            document.forms[0].submit();
                        }
                    }
                }
                
            </script>
            <vivo:quadro id="qdAba" height="385" width="747">
                <table width="100%" cellpadding="0" cellspacing="0">
                    <tr><td height="4"></td></tr>
                    <tr>
                        <td>
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                            <tr>
                                <td width="22%" class="tblEstatica_campoNome">&nbsp;
                                    <netui:label value="Prazo de Atendimento:" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="78%" class="tblEstatica_campovalor">
                                    <html:text  name="FormDadosBasicosW" property="qtDiasPrazoContato" maxlength="5" styleClass="input" style='width:60px; text-indent:4px;' onkeyup="checaHora(this)"/> (em horas - hh:mm)
                                </td>
                            </tr>
                            <tr>
                                <td width="22%" class="tblEstatica_campoNome">&nbsp;
                                    <netui:label value="Retorno:" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="78%">&nbsp;                              
                                    <html:select name="FormDadosBasicosW" property="arraytipoRetornoContato" styleClass="SELECT" size="1">
                                        <html:options collection="TipoRetornoContatoVO" property="idTipoRetornoContato" labelProperty="nmTipoRetornoContato" /> 
                                    </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td width="22%">&nbsp;&nbsp;
                                    <netui:label value="Peso/Prioridade:" styleClass="tblEstatica_campoNome"/>
                                </td>
                                <td width="78%" align="left">
                                    <html:text  name="FormDadosBasicosW" property="vlPesoContato" styleClass="input" style='width:60px'/>
                                </td>
                            </tr>                                        
                            <tr><td height="4"></td></tr>
                        </table>
                        </td>
                    </tr>
                    <tr><td height="1"></td></tr>
                    <tr>
                    <tr>
                        <td>
                            <table cellpadding="0" cellspacing="0" border="0" align="center" width="95%">
                                <tr>
                                    <td align="right">
	                                    <acesso:controlHiddenItem nomeIdentificador="adm_vdb_gravar">
	                                        <img class="button" id="btSalvar2" onmouseup="validaHora();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" border="0" />
	                                    </acesso:controlHiddenItem>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr><td height="4"></td></tr>
                </table>
            </vivo:quadro>

        <%=pageContext.getAttribute("warningFrame")%>
            
        </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>

                
