<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="perfilForm" type="admsistemas.PerfilCRI.PerfilCRIController.PerfilForm"/>

<bean:define id="LinhaDisponivel"  name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.tipoLinhaVOArray" />
<bean:define id="LinhaAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.tipoLinhaVOArray" />

<bean:define id="SegmentacaoDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.segmentacaoVOArray" />
<bean:define id="SegmentacaoAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.segmentacaoVOArray" />

<bean:define id="CarteiraDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.carterizacaoVOArray" />
<bean:define id="CarteiraAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.carterizacaoVOArray" />

<bean:define id="ProcedenciaDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.procedenciaVOArray" />
<bean:define id="ProcedenciaAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.procedenciaVOArray" />

<bean:define id="NaturezaDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.admNaturezaVOArray" />
<bean:define id="NaturezaAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.admNaturezaVOArray" />

<bean:define id="ClienteDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.tipoClienteVOArray" />
<bean:define id="ClienteAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.tipoClienteVOArray" />

<bean:define id="GrupoDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.admGrupoAberturaVOArray" />
<bean:define id="GrupoAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.admGrupoAberturaVOArray" />

<bean:define id="CanalDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.canalVOArray" />
<bean:define id="CanalAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.canalVOArray" />

<bean:define id="RegionalDisponivel" name="Form" property="perfilVariaveisVO.perfilVariaveisDisponiveisVO.admUFOperadoraSimplVOArray" />
<bean:define id="RegionalAssociada" name="Form" property="perfilVariaveisVO.perfilVariaveisAssociadasVO.admUFOperadoraSimplVOArray" />

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
    <script language="javascript">
        function transfereSelecaoLista(listaOrigem, listaDestino){
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    // Trata a primeira posicao vazia do list, se houver
                    if ((listaDestino.options.length == 1) && (trim(listaDestino.options[0].text) == "")) {
                        listaDestino.options[0] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    } else {
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        }
        
        function valida(){
            var listaTipoLinhaAssociada = document.forms[0].tipoLinhaAssociada; 
            var listaSegmentacaoAssociada = document.forms[0].segmentacaoAssociada;  
            var listaCarterizacaoAssociada = document.forms[0].carterizacaoAssociada; 
            var listaProcedenciaAssociada = document.forms[0].procedenciaAssociada;  
            var listaNaturezaAssociada = document.forms[0].naturezaAssociada; 
            var listaTipoClienteAssociada = document.forms[0].tipoClienteAssociada;
            var listaCanalAssociada = document.forms[0].canalAssociada;
            var listaRegionalAssociada = document.forms[0].regionalAssociada;
            var listaGrupoAberturaAssociada = document.forms[0].grupoAberturaAssociada;

            //verifica se todos são vazios
            if(listaRegionalAssociada.length==null || listaRegionalAssociada.length==0){  
                if(listaCanalAssociada.length==null || listaCanalAssociada.length==0){  
                    if(listaProcedenciaAssociada.length==null || listaProcedenciaAssociada.length==0){     
                        if(listaNaturezaAssociada.length==null || listaNaturezaAssociada.length==0){      
                            if(listaTipoClienteAssociada.length==null || listaTipoClienteAssociada.length==0){      
                                if(listaSegmentacaoAssociada.length==null || listaSegmentacaoAssociada.length==0){  
                                    if(listaCarterizacaoAssociada.length==null || listaCarterizacaoAssociada.length==0){
                                       if(listaTipoLinhaAssociada.length==null || listaTipoLinhaAssociada.length==0){
                                          if(listaGrupoAberturaAssociada.length==null || listaGrupoAberturaAssociada.length==0){
                                                if(document.forms[0].idPerfil.value!='0'){
                                                    return true;
                                                }else{
                                                    alert('Escolha as variáveis!');
                                                    return false;
                                                }
                                          }
                                       }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(listaNaturezaAssociada.length==null || listaNaturezaAssociada.length==0){
                alert('Natureza sem associação!');
                return false;
            }           
            if(listaTipoClienteAssociada.length==null || listaTipoClienteAssociada.length==0){
                alert('Tipo Cliente sem associação!');
                return false;
            }           
            if(listaSegmentacaoAssociada.length==null || listaSegmentacaoAssociada.length==0){
                alert('Segmentacão sem associação!');
                return false;
            }           
            if(listaCarterizacaoAssociada.length==null || listaCarterizacaoAssociada.length==0){
                alert('Carteira sem associação!');
                return false;
            }           
            if(listaTipoLinhaAssociada.length==null || listaTipoLinhaAssociada.length==0){
                alert('Tipo Linha sem associação!');
                return false;
            }        
            if(listaRegionalAssociada.length==null || listaRegionalAssociada.length==0){
                alert('Regional sem associação!');
                return false;
            }    
            if(listaCanalAssociada.length==null || listaCanalAssociada.length==0){
                alert('Canal sem associação!');
                return false;
            }
            if(listaProcedenciaAssociada.length==null || listaProcedenciaAssociada.length==0){
                alert('Procedência sem associação!');
                return false;
            }
            if(listaGrupoAberturaAssociada.length==null || listaGrupoAberturaAssociada.length==0){
                alert('Grupo de Abertura sem associação!');
                return false;
            }
            return true;  
        }   
        
        function seleciona(){
            var lista = document.forms[0].tipoLinhaAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].tipoLinhaAssociada.options[i].selected =true; 
            }
            
            lista = document.forms[0].segmentacaoAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].segmentacaoAssociada.options[i].selected =true; 
            } 
            
            lista = document.forms[0].carterizacaoAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].carterizacaoAssociada.options[i].selected =true; 
            } 
            
            lista = document.forms[0].procedenciaAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].procedenciaAssociada.options[i].selected =true; 
            } 
            
            lista = document.forms[0].naturezaAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].naturezaAssociada.options[i].selected =true; 
            } 
            
            lista = document.forms[0].tipoClienteAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].tipoClienteAssociada.options[i].selected =true; 
            } 
        
            lista = document.forms[0].canalAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].canalAssociada.options[i].selected =true; 
            }
        
            lista = document.forms[0].regionalAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].regionalAssociada.options[i].selected =true; 
            } 

            lista = document.forms[0].grupoAberturaAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].grupoAberturaAssociada.options[i].selected =true; 
            }
            return true;                                                                        
        }
             
        function salvaPerfil(){
            if(valida()){
                seleciona();
                document.forms[0].target = 'ifrmVar';
                document.forms[0].action = 'incluirPerfil.do';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
            <form onsubmit="return false;" method="POST">
                    <html:hidden property="inAcao" name="Form" value="2"/>
                    <html:hidden property="idPerfil" name="Form"/>
                            <!--div id="espacoDiv" style="height:416px;display:block;" -->
                        <!--div id="divVariaveis" style="overflow:auto;width:775px;height:412px;display:none;"-->
                            <table border="0" align="right" cellspacing="0" cellpadding="1" class="tbl_bgGray" width="100%">
                                <tr>
                                    <td>
                                        <vivo:quadro id="qdTipoLinha" height="120" width="355" label="Tipo Linha">
                                            <table border="0" align="center">
                                                <tr>
                                                    <td>
                                                        Dispon&iacute;veis<br>
                                                        <html:select name="Form" property="tipoLinhaDisponivel" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="LinhaDisponivel" property="id" labelProperty="descricao"/>
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].tipoLinhaDisponivel, document.forms[0].tipoLinhaAssociada);return false;"/>
                                                        <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].tipoLinhaAssociada,document.forms[0].tipoLinhaDisponivel);return false;"/>                                           
                                                    </td>
                                                    <td>
                                                        Associados<br>                    
                                                        <html:select name="Form" property="tipoLinhaAssociada" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="LinhaAssociada" property="id" labelProperty="descricao"/>
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                    <td align="right">
                                        <vivo:quadro id="qdSegmentacao" height="120" width="355" label="Segmentação">
                                            <table border="0" align="right">
                                                <tr>
                                                    <td>
                                                        Dispon&iacute;veis<br>
                                                        <html:select name="Form" property="segmentacaoDisponivel" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="SegmentacaoDisponivel" property="idSegmentacao" labelProperty="descricao" />
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].segmentacaoDisponivel, document.forms[0].segmentacaoAssociada);return false;"/>
                                                        <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].segmentacaoAssociada,document.forms[0].segmentacaoDisponivel);return false;"/>
                                                    </td>
                                                    <td>
                                                        Associados<br>                    
                                                        <html:select name="Form" property="segmentacaoAssociada" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="SegmentacaoAssociada" property="idSegmentacao" labelProperty="descricao" />
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan=3 style="height=5px;"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <vivo:quadro id="qdCarterizacao" height="120" width="355" label="Carteirização">
                                            <table border="0">
                                                <tr>
                                                    <td>
                                                        Dispon&iacute;veis<br>
                                                        <html:select name="Form" property="carterizacaoDisponivel" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="CarteiraDisponivel" property="idTipoCarteira" labelProperty="descricao" />
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].carterizacaoDisponivel, document.forms[0].carterizacaoAssociada);return false;"/>
                                                        <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].carterizacaoAssociada,document.forms[0].carterizacaoDisponivel);return false;"/>
                                                    </td>
                                                    <td>
                                                        Associados<br>                    
                                                        <html:select name="Form" property="carterizacaoAssociada" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="CarteiraAssociada" property="idTipoCarteira"  labelProperty="descricao" />
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>                          
                                    </td>
                                    <td align="right">
                                        <vivo:quadro id="qdProcedencia" height="120" width="355" label="Procedência">
                                            <table border="0">
                                                <tr>
                                                    <td>
                                                        Dispon&iacute;veis<br>
                                                        <html:select name="Form" property="procedenciaDisponivel" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="ProcedenciaDisponivel" property="idProcedencia" labelProperty="descricao" />
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].procedenciaDisponivel, document.forms[0].procedenciaAssociada);return false;"/>
                                                        <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].procedenciaAssociada,document.forms[0].procedenciaDisponivel);return false;"/>                                           
                                                    </td>
                                                    <td>
                                                        Associados<br>                    
                                                        <html:select name="Form" property="procedenciaAssociada" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="ProcedenciaAssociada" property="idProcedencia"  labelProperty="descricao" />
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan=3 style="height=5px;"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <vivo:quadro id="qdNatureza" height="120" width="355" label="Natureza">
                                            <table border="0">
                                                <tr>
                                                    <td align="center">
                                                        Dispon&iacute;veis<br/>
                                                        <html:select name="Form" property="naturezaDisponivel" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="NaturezaDisponivel" property="idNatureza" labelProperty="dsNatureza" />
                                                        </html:select>
                                                    </td>
                                                    <td align="center" valign="middle">
                                                        <vivo:botao id="bt01" width="25px" height="25px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].naturezaDisponivel, document.forms[0].naturezaAssociada);return false;"/>
                                                        <vivo:botao id="bt03" width="25px" height="25px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].naturezaAssociada,document.forms[0].naturezaDisponivel);return false;"/>
                                                    </td>
                                                    <td align="center">
                                                        Associada<br/>
                                                        <html:select name="Form" property="naturezaAssociada" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="NaturezaAssociada" property="idNatureza" labelProperty="dsNatureza" />
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro> 
                                    </td>
                                    <td align="right">
                                        <vivo:quadro id="qdTipoCliente" height="120" width="355" label="Tipo Cliente">
                                            <table border="0">
                                                <tr>
                                                    <td>
                                                        Dispon&iacute;veis<br>
                                                        <html:select name="Form" property="tipoClienteDisponivel" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="ClienteDisponivel" property="codigo" labelProperty="descricao"/>
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].tipoClienteDisponivel, document.forms[0].tipoClienteAssociada);return false;"/>
                                                        <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].tipoClienteAssociada,document.forms[0].tipoClienteDisponivel);return false;"/>                                            
                                                    </td>
                                                    <td>
                                                        Associados<br>                    
                                                        <html:select name="Form" property="tipoClienteAssociada" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="ClienteAssociada" property="codigo" labelProperty="descricao"/>
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro> 
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <vivo:quadro id="qdRegional" height="120" width="355" label="Regional">
                                            <table border="0">
                                                <tr>
                                                    <td>
                                                        Dispon&iacute;veis<br>
                                                        <html:select name="Form" property="regionalDisponivel" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="RegionalDisponivel" property="idUFOperadora" labelProperty="dsUFOperadora"/>
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].regionalDisponivel, document.forms[0].regionalAssociada);return false;"/>
                                                        <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].regionalAssociada,document.forms[0].regionalDisponivel);return false;"/>
                                                    </td>
                                                    <td>
                                                        Associados<br>                    
                                                        <html:select name="Form" property="regionalAssociada" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="RegionalAssociada" property="idUFOperadora" labelProperty="dsUFOperadora"/>
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                    <td align="right">
                                        <vivo:quadro id="qdCanal" height="120" width="355" label="Canal">
                                            <table border="0">
                                                <tr>
                                                    <td>
                                                        Dispon&iacute;veis<br>
                                                        <html:select name="Form" property="canalDisponivel" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="CanalDisponivel" property="idCanal" labelProperty="descricao"/>
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].canalDisponivel, document.forms[0].canalAssociada);return false;"/>
                                                        <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].canalAssociada,document.forms[0].canalDisponivel);return false;"/>                                           
                                                    </td>
                                                    <td>
                                                        Associados<br>                    
                                                        <html:select name="Form" property="canalAssociada" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="CanalAssociada" property="idCanal" labelProperty="descricao"/>
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <vivo:quadro id="qdGrupo" height="120" width="355" label="Grupo de Abertura">
                                            <table border="0">
                                                <tr>
                                                    <td>
                                                        Dispon&iacute;veis<br>
                                                        <html:select name="Form" property="grupoAberturaDisponivel" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="GrupoDisponivel" property="idGrupo" labelProperty="nmGrupo"/>
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].grupoAberturaDisponivel, document.forms[0].grupoAberturaAssociada);return false;"/>
                                                        <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].grupoAberturaAssociada,document.forms[0].grupoAberturaDisponivel);return false;"/>
                                                    </td>
                                                    <td>
                                                        Associados<br>                    
                                                        <html:select name="Form" property="grupoAberturaAssociada" multiple="true" size="6" style="width=150px">
                                                            <html:options collection="GrupoAssociada" property="idGrupo" labelProperty="nmGrupo"/>
                                                        </html:select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </vivo:quadro>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" colspan="2"><br>
                                        <img hspace="5" style="border:none;cursor:hand;"  onclick="salvaPerfil();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"/>
                                    </td>
                                </tr>
                            </table>
                        <!--/div -->
                        <!-- /div -->
        <script language="javascript" for="window" event="onload">
        <!--   
            parent.oculta_div();
        -->
        </script> 
        <vivo:alert atributo="msgStatus" scope="request"/>
        </form>
        <iframe name="ifrmVar" id="ifrmVar" height="0px" width="0px" style="display:none;">
    </netui-template:section>
</netui-template:template>
