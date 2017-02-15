<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormDadosBasicos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosBasicosForm"/>
<!-- array de grupos - contém a lista de grupos existentes -->
<bean:define id="aMensagem"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosBasicosForm.listaMensagem"/>
<bean:define id="aFechamento"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosBasicosForm.listaFechamento"/>
<bean:define id="TipoRetornoContatoVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="dadosBasicosForm.tipoRetornoContatoVO"/>


<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_vdba_verpagina">

    <script>        
    
        document.body.style.backgroundColor = "#ededed";
        
                // variáveis gloabais.
                oldObj = "";
                oldValor = "";
                inteiro = new RegExp("[0-9]");
                var objMsg = '';
                
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
        
        function initPagina() { }
        
        function manipulaPagina(){
            var a = document.getElementById("div1");
            a.style.visibility = 'visible';
            var b = document.getElementById("div2");
            b.style.visibility = 'hidden';
            
        }
        
        function escondeAmbos(){
            var a = document.getElementById("div1");
            a.style.visibility = 'hidden';
            var b = document.getElementById("div2");
            b.style.visibility = 'hidden';
            objMsg = '';
        }
        
        function escondeLista(){
            var a = document.getElementById("div1");
            a.style.visibility = 'hidden';
            var b = document.getElementById("div2");
            b.style.visibility = 'visible';
            objMsg = 'area';
        }
        
        function exibeLista(){
            var a = document.getElementById("div1");
            a.style.visibility = 'visible';
            var b = document.getElementById("div2");
            b.style.visibility = 'hidden';
            objMsg = 'combo';
        }
        
        //válida campo obrigatórios.
        function valida()
        {
        
            if(document.forms[0].fechamento.selectedIndex == 0 )
            {

                alert('Fechamento é um campo obrigatório, favor selecionar.');            
                return false;

            }else if(document.forms[0].processoTec.selectedIndex == 0 )
            {
                alert('Processo técnico é um campo obrigatório, favor selecionar.');            
                return false;

            }else if(document.forms[0].arraytipoRetornoContato.selectedIndex == 0 )
            {
                alert('Retorno é um campo obrigatório, favor selecionar.');            
                return false;

            }else if(document.forms[0].qtDiasPrazoContato.value == '' )
            {
                alert('Prazo de atendimento é um campo obrigatório, favor preencher.');            
                return false;
                        
            }else if(document.forms[0].vlPesoContato.value == '' )
            {
                alert('Peso/Prioridade é um campo obrigatório, favor preencher.');            
                return false;
                
            }else if(objMsg == 'area')
            {
                if(document.forms[0].mensagem.value == '')
                {
                    alert('Favor preencher nova mensagem.');            
                    return false;
                }
            }else if(objMsg == 'combo')
            {
                if(document.forms[0].mensagem.selectedIndex == 0)
                {
                    alert('Selecione uma mensagem existente.');
                    return false;
                }

            }

            return true;
        }
        

        //envia dados do formulário.        
        function salva()
        {
            if(valida())
            {
                document.forms[0].action = 'salvaDados.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        
        }
        
    </script>
    
        <script language="javascript" for="window" event="onload">
        <!--   
            if('<bean:write name="FormDadosBasicos" property="msgError" />' != "")
            {
                alert('<bean:write name="FormDadosBasicos" property="msgError" />');
            }
        -->
        </script> 

        <form action="salvaDados" id="salvaDados" name="salvaDados" method="post">
        <html:hidden name="FormDadosBasicos" property="idContato" value='<%= request.getParameter("idContato") %>'/>
        <table width="777" cellpadding="0" cellspacing="0" bgcolor="#ededed">
                <tr><td height="4"></td></tr>
                <tr>
                    <td>
                    <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                        <tr>
                            <td width="21%" class="tblEstatica_campoNome">
                                <netui:label value="Fechamento:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="83%">
                                <html:select name="FormDadosBasicos" property="fechamento" style="text-indent:3px;" styleClass="SELECT">
                                    <html:option value="-1">Escolha uma opção...</html:option>
                                    <html:options collection="aFechamento" property="idTipoFechamento" labelProperty="nmTipoFechamento" /> 
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td width="21%" class="tblEstatica_campoNome">
                                <netui:label value="Processo Técnico:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="83%">
                                <html:select name="FormDadosBasicos" property="processoTec" style="text-indent:3px;" styleClass="SELECT">
                                    <html:option value="-1">Escolha...</html:option>
                                    <html:option value="1">Sim</html:option>
                                    <html:option value="0">Não</html:option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td width="21%" class="tblEstatica_campoNome">
                                <netui:label value="Retorno:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="83%">&nbsp;                              
                                <html:select name="FormDadosBasicos" property="arraytipoRetornoContato" styleClass="SELECT" size="1">
                                    <html:option value="-1">Escolha...</html:option>
                                    <html:options collection="TipoRetornoContatoVO" property="idTipoRetornoContato" labelProperty="nmTipoRetornoContato" /> 
                                </html:select>
                            </td>
                        </tr>

                        <tr>
                            <td width="21%" class="tblEstatica_campoNome">
                                <netui:label value="Prazo de Atendimento:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="83%" class="tblEstatica_campovalor">
                                <html:text  name="FormDadosBasicos" property="qtDiasPrazoContato" maxlength="5" styleClass="input" style='width:60px;' onkeyup="checaHora(this)"/> (em horas - hh:mm)
                            </td>
                        </tr>
                        <tr>
                            <td width="21%">
                                <netui:label value="Peso/Prioridade:" styleClass="tblEstatica_campoNome"/>
                            </td>
                            <td width="83%" align="left">
                                <html:text  name="FormDadosBasicos" property="vlPesoContato" styleClass="input" style='width:60px'/>
                            </td>
                        </tr>                                        
                        <tr><td height="4"></td></tr>
                    </table>
                    </td>
                </tr>
                <tr><td height="1"></td></tr>
                <tr>
                    <td>
                    
                    <center>
                    <table width="743">
                        <tr>
                            <td width="540">
                                <table width="540" height="94" class="tbl_bgGray" cellspacing="2" cellpadding="1">
                                    <tr>
                                        <td class="tbl_titulo" align="center" height="16" colspan="3">
                                            <netui:label value="Mensagem de Aviso"/> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="middle" width="114">
                                        
                                            <netui:radioButtonGroup dataSource="{}" tagId="msg">
                                                <netui:radioButtonOption value="0" styleClass="radio" onclick="escondeLista();">&nbsp;Nova</netui:radioButtonOption><br>
                                                &nbsp;<netui:radioButtonOption value="1" styleClass="radio" onclick="exibeLista();">&nbsp;Existente</netui:radioButtonOption><br>
                                                &nbsp;<netui:radioButtonOption value="2" styleClass="radio" onclick="escondeAmbos();">&nbsp;Sem mensagem</netui:radioButtonOption>
                                            </netui:radioButtonGroup>
                                        </td>
                                        <td width="376">
                                            <div id="div3" style="visibility:'visible'; position:relative; top: 0px; left: 25px; height: 24px; padding: 0px;">
                                                <div id="div1" style="visibility:'hidden'; position:absolute; top: 0px; left: 0px; height: 0px; padding: 0px;">
                                                    <html:select name="FormDadosBasicos" property="mensagem" style="text-indent:3px;width:370px" styleClass="SELECT">
                                                        <html:option value="-1">Escolha uma opção...</html:option>
                                                        <html:options collection="aMensagem" property="idMensagemAviso" labelProperty="dsMensagemAviso" /> 
                                                    </html:select>
                                                </div>
                                                <div id="div2" style="visibility:'hidden'; position:absolute; top: -12px; left: 0px; height: 0px; padding: 0px;">
                                                    <html:textarea name="FormDadosBasicos" property="mensagem" style="width:370px" styleClass="input" rows="3"/>
                                                </div>
                                            </div>
                                        </td>                                      
                                    </tr>
                                </table>    
                            </td>
                            <td width="203" align="center">
                            <acesso:controlHiddenItem nomeIdentificador="adm_vdba_salvar">
                                <img id="btSalvar2" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;" onclick="salva(); return false;"/>
                            </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>
                    </center>
                    </td>
                </tr>
                <tr><td height="4"></td></tr>
            </table>     
            
    <script language="javascript" for="window" event="onload">
        <!--   
            parent.oculta_div();
        -->
    </script> 
                  
            </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>

                
