<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormManterBanner" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner"/>

<bean:define id="aArea"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner.comboFiltro.areaArray"/>

<bean:define id="aTipoLinha"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner.comboFiltro.tipoLinhaArray"/>

<bean:define id="aGrupoUsuario"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner.comboFiltro.grupoUsuarioArray"/>

<bean:define id="aUf"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formUploadBanner.comboFiltro.UFArray"/>


<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Grupo"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>

    <netui-template:section name="headerSection">

        <script language="Javascript">

            var idAreaBannerArray   = new Array(50);
            var idGrupoBannerArray  = new Array(50);
            var idBannerArray       = new Array(50);   

            function enviarArquivo()
            {
                var form = document.forms[0];

                form.target = '';
                form.action = 'uploadBanner.do';
                form.submit();
            
            }
            
            function limpar()
            {
            
            
            }
            
            function pesquisar()
            {
                var form = document.forms[0];
                
                form.action = 'pesquisarBanner.do';
                form.target = 'innerBrowserPesquisaBanner';
                mostrar_div();
                form.submit();
                
            
            }
            
            function valida(op){
            
            
                if(op == 'desassociar'){
                    
                    for(i=0 ;i < idBannerArray.length; i++){
                        if(idBannerArray[i] == '0')
                        {
                            alert("Existe(m) item(ns) selecionado(s) que não possui(em) Banner associado.\nVerifique a seleção, e tente novamente.");
                            return false;
                            
                        }
                    
                    }
                    
                }
                
                var auxIdx = 0;
                var arrayId = new Array(3);
                arrayId[0] = false;
                arrayId[1] = false;
                arrayId[2] = false;
                
                for(i = 0; i < idAreaBannerArray.length; i++){
                    
                    if(idAreaBannerArray[i] == 1){
                        arrayId[0] = true;
                        
                    }else if(idAreaBannerArray[i] == 2){
                        arrayId[1] = true;
                        
                    }else if(idAreaBannerArray[i] == 3){
                        arrayId[2] = true;
                    }
                }

                for(i = 0; i < arrayId.length; i++){
                    if(arrayId[i] == true){
                        auxIdx = auxIdx+1;
                    }
                }
                
                if(auxIdx > 1 && op == 'associar'){
                    alert('Não é possível associar um Banner para Areas distintas.\n Verifique a seleção efetuada.');
                    return false;
                }


                var strGrupoBanner = '';
                for(i=0; i < idGrupoBannerArray.length ; i++){
                    if(idGrupoBannerArray[i] != '' && idGrupoBannerArray[i] != 0 && idGrupoBannerArray[i] != undefined)
                    {
                        strGrupoBanner = strGrupoBanner + idGrupoBannerArray[i]+';';
                    }
                }
                
                document.forms[0].idGrupoBannerArray.value = strGrupoBanner;
                
                if(document.forms[0].idGrupoBannerArray.value == '')
                {
                    alert('Não existe(m) item(ns) selecionado(s).\n Selecione pelo menos um item na lista de Banner.');
                    return false;
                }
                
                return true;            
            
            }

            function desassociar(){
                if(valida('desassociar')){
                    var form = document.forms[0];

                    form.action = 'desassociarBanner.do';
                    form.target = "innerBrowserPesquisaBanner";  
                    mostrar_div();
    
                    form.submit();
                
    
                }
            
            }
            
            function associar(){
                if(valida('associar')){
                    var idArea = '';
                    for(i = 0;i < idAreaBannerArray.length;i++){
                        if(idAreaBannerArray[i] != '' && idAreaBannerArray[i] != undefined){
                            idArea = idAreaBannerArray[i];
                            break;
                        }
                    }

                    var form = document.forms[0];
                    
                    form.idArea.value = idArea;
                    
                    divAssociarBanner.style.display = '';
                    form.action = 'carregaAssociarBanner.do';
                    form.target = "ifrmAssociarBanner";  
                    mostrar_div();
    
                    form.submit();
                
                }
            }


            function upload()
            {
                var form = document.forms[0];

                divUploadBanner.style.display = '';
                form.action = 'carregaIncluirBanner.do';
                form.target = "ifrmUploadBanner";  
                mostrar_div();

                form.submit();
            }
            
            function paginarPesquisa(op, botao){

                var form = document.forms[0];
                if(botao == true){
                    var pagAtual = form.paginaAtual.value;
                    pagAtual = parseInt(pagAtual)+parseInt(op);
                    
                    form.paginaAtual.value = pagAtual;
                    
                }else{
                    
                    form.paginaAtual.value = op;
                }

                form.action = 'paginarPesquisa.do';
                form.target = 'innerBrowserPesquisaBanner';
                mostrar_div();
                form.submit();
    
            }   
            
            function checkLista(obj){
            
                var strArray = obj.value.split(';');
            
                var idBanner        = strArray[0];
                var idAreaBanner    = strArray[1];
                var idGrupoBanner   = strArray[2];
                var idx             = strArray[3];
                
                if(obj.checked == true){
                    idAreaBannerArray[idx]  = idAreaBanner;
                    idGrupoBannerArray[idx] = idGrupoBanner;
                    idBannerArray[idx]      = idBanner;
                
                }else{

                    idAreaBannerArray[idx]  = '';
                    idGrupoBannerArray[idx] = '';
                    idBannerArray[idx]      = '';
                }
            
            }   
            
            function checaTodos(obj){

                for(i=0;i < 50; i++){

                    if(eval('document.forms[0].chk_banner' + i))
                    {
                        eval('document.forms[0].chk_banner' + i + '.checked = ' + obj.checked);
                        checkLista(eval('document.forms[0].chk_banner' + i));
                    }
                }
            
            }
            
            function limpar()
            {
                
                for(i = 0; i < document.forms[0].areaBannerArray.length; i++){
                    document.forms[0].areaBannerArray.options[i].selected = false;
                }

                for(i = 0; i < document.forms[0].ufArray.length; i++){
                    document.forms[0].ufArray.options[i].selected = false;
                }

                for(i = 0; i < document.forms[0].tipoLinhaArray.length; i++){
                    document.forms[0].tipoLinhaArray.options[i].selected = false;
                }

                for(i = 0; i < document.forms[0].grupoUsuarioArray.length; i++){
                    document.forms[0].grupoUsuarioArray.options[i].selected = false;
                }

                return false;
                
            }
            
            function loadImagem(nomeImagem , dsTipoBanner, idAreaBannerParam)
            {
            
                var form = document.forms[0];

                divImagemBanner.style.display = '';
                
                form.nmImagem.value     = nomeImagem;
                form.dsTipoBanner.value = dsTipoBanner;
                form.idArea.value       = idAreaBannerParam; 
                
                form.action = 'carregaImagemBanner.do';
                form.target = "ifrmImagemBanner";  
                mostrar_div();

                form.submit();            
            
            }

        </script>

    </netui-template:section>
    
    <netui-template:section name="bodySection">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />

            <vivo:sessao id="qdMain" height="475" width="790" label="VOL/TAV" operacoes="Upload de Banner" scroll="no">
            
            <acesso:controlHiddenItem nomeIdentificador="ver_voltav_manter_banner">
            
                <form name="formUpload" method="post" onsubmit="return false;">
                            
                <table border="0">
                    <tr>
                        <td>

                            <vivo:quadro width="775" height="145" id="qdrFiltros" scroll="no" label="Filtros">

                                <table border="0" width="100%"> 
                                    <tr>
                                        <td>
                                            <table align="left" width="100%" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td>
                                                        <b>Area do Banner</b>
                                                    </td>
                                                    <td>
                                                        <b>UF</b>
                                                    </td>
                                                    <td>
                                                        <b>Tipo de Linha</b>
                                                    </td>
                                                    <td>
                                                        <b>Grupo de Usuário</b>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <html:select name="FormManterBanner" property="areaBannerArray" multiple="true" style="width:150px;" size="6">
                                                                <html:options collection="aArea" property="idArea" labelProperty="nmArea" /> 
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <html:select name="FormManterBanner" property="ufArray" multiple="true" style="width:150px;" size="6">
                                                            <html:options collection="aUf" property="idUf" labelProperty="sgUf" />                                                                                                                 
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <html:select name="FormManterBanner" property="tipoLinhaArray" multiple="true" style="width:150px;" size="6">
                                                            <html:options collection="aTipoLinha" property="idTipoLinha" labelProperty="dsTipoLinha" />                                                         
                                                        </html:select>
                                                    </td>
                                                    <td>
                                                        <html:select name="FormManterBanner" property="grupoUsuarioArray" multiple="true" style="width:150px;" size="6">
                                                            <html:options collection="aGrupoUsuario" property="idGrupoUsuario" labelProperty="nmGrupoUsuario" />
                                                        </html:select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                    <td >
                                                        <br>
                                                        <img style="cursor:hand;border:none" id="btLimpar" onclick="limpar();" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'" />
                                                        
                                                        <acesso:controlHiddenItem nomeIdentificador="voltav_banner_pesquisar">
                                                            <img style="cursor:hand;border:none" id="btPesquisar" onclick="return pesquisar();" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" />
                                                        </acesso:controlHiddenItem>
                                                    </td>
                                                </tr>
                                            </table>       
                                       </td>
                                </tr>
                        </table>

                </vivo:quadro>   
                  </td>
              </tr>
              <tr>
                    <td>
                            <vivo:quadro width="775" height="265" id="qdrResultado" scroll="no" label="Lista de Banner">
                                <table width="100%">
                                    <tr>
                                        <td align="center">
                                         <div id="lista_manter_banner" >
                                            <b>* Nenhuma busca foi realizada até o momento.</b>                                        
                                         </div>
                                        </td>
                                    </tr>
                                </table>
                            </vivo:quadro>   
                    </td>
              </tr>
              <tr>
                <td>
                        <table border="0" width="100%">
                            <tr>
                                <td align="left" valign="top">
                                    <img  style="border:none;" onClick="window.location.href='/FrontOfficeWeb/index.jsp'; return false" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/>
                                </td>
                                <td align="right" valign="top">
                                    <acesso:controlHiddenItem nomeIdentificador="voltav_banner_desassociar">
                                        <img style="cursor:hand;border:none;visibility:hidden;" id="btDesassociar" onclick="desassociar();" src="/FrontOfficeWeb/resources/images/bt_desassociar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_desassociar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_desassociar_over.gif'" />
                                    </acesso:controlHiddenItem>
                                    <acesso:controlHiddenItem nomeIdentificador="voltav_banner_associar">
                                        <img style="cursor:hand;border:none;visibility:hidden;" id="btAssociar" onClick="associar();" src="/FrontOfficeWeb/resources/images/bt_associar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_associar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_associar_over.gif'" />
                                    </acesso:controlHiddenItem>
                                    <acesso:controlHiddenItem nomeIdentificador="voltav_banner_incluir">
                                        <img style="cursor:hand;border:none;" id="btIncluir" onclick="upload();" src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_incluir_over.gif'" />&nbsp;
                                    </acesso:controlHiddenItem>
                                </td>
                            </tr>
                        </table>
                    </td>
              </tr>
            </table>

            <!--table>
                <tr>
                    <td>Caminho:</td>
                    <td>< html:file name="FormManterBanner" property="imagemUpload" size="25"/>
                </td>
                <td><input type="button" name="Enviar" value="Enviar" onclick="enviarArquivo();"></td>
            </tr>
        </table -->

        <iframe scrolling="no"  name="innerBrowserPesquisaBanner" height="0px" width="0px"></iframe>              

            <vivo:quadroFlutuante label="VOL/TAV > Upload de Banner > Associar Banner" scroll="no" src="" idIframe="ifrmAssociarBanner" id="divAssociarBanner" spacesLeft="40" height="280" spacesTop="130" url="<%=request.getContextPath()%>" display="none" width="700" ></vivo:quadroFlutuante>
            <vivo:quadroFlutuante label="VOL/TAV > Upload de Banner > Incluir Banner" scroll="no" src="" idIframe="ifrmUploadBanner" id="divUploadBanner" spacesLeft="130" height="170" spacesTop="130" url="<%=request.getContextPath()%>" display="none" width="520" ></vivo:quadroFlutuante>
            <vivo:quadroFlutuante label="VOL/TAV > Upload de Banner > Imagem Banner" scroll="false" src="" idIframe="ifrmImagemBanner" id="divImagemBanner" spacesLeft="140" height="80" spacesTop="160" url="<%=request.getContextPath()%>" display="none" width="500" ></vivo:quadroFlutuante>
            
            <!--html:hidden name="FormManterBanner" property="paginaAtual" / -->
            <html:hidden name="FormManterBanner" property="idGrupoBannerArray"/>
            <html:hidden name="FormManterBanner" property="nmImagem"/>
            <html:hidden name="FormManterBanner" property="dsTipoBanner"/>
            <html:hidden name="FormManterBanner" property="idArea"/>
    
        </form>
        </acesso:controlHiddenItem>

    </vivo:sessao>

        <vivo:alert atributo="msgStatus" scope="request"/>

    </netui-template:section>
</netui-template:template>
