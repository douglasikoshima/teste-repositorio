<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="associacaoGrupoForm" type="admsistemas.admArvoreContato.workflow.AssociacaoGrupos.AssociacaoGruposController.AssociacaoGrupoForm"/>
<bean:define id="aGruposDisp"  name="Form" property="gruposProcessos.gruposDisponiveis.grupoProcessoVO.grupoVOArray" />
<bean:define id="aGruposAsso"  name="Form" property="gruposProcessos.gruposAbertura.grupoProcessoVO.grupoVOArray" />
<bean:define id="WFAcaoVO"     name="Form" property="WFAcaoVO" />

<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>      

    <script language="Javascript">
        
        function salvar(){
        
            if(validacao()){
                if (gravar()) {
                    parent.mostrar_div();
                    document.forms[0].action = "salvaAssociacaoGrupo.do";
                    document.forms[0].target = "frameEscondido";
                    document.forms[0].submit();
                }
            }
            else{
                alert('Tem que selecionar pelo menos um dado em cada variável!');
            }
                
            return false;
        }
        
        function apagar(){
        
            if(validacaoGrupos()){
                parent.mostrar_div();
                document.forms[0].persiste.value = "-1";
                document.forms[0].apagar.value = "1";
                document.forms[0].action = "salvaAssociacaoGrupo.do";
                document.forms[0].target = "frameEscondido";
                document.forms[0].submit();
                document.forms[0].apagar.value = "0";
            }
            return false;
        }

        function validacao(){
        
            var listaGruposAssociados = document.forms[0].gruposAssociados;
            var listaNaturezaAssociada = document.forms[0].naturezaAssociada; 
            var listaTipoClienteAssociada = document.forms[0].tipoClienteAssociada;
            var listaSegmentacaoAssociada = document.forms[0].segmentacaoAssociada;  
            var listaCarterizacaoAssociada = document.forms[0].carterizacaoAssociada;  
            var listaTipoLinhaAssociada = document.forms[0].tipoLinhaAssociada; 
            
            if(listaNaturezaAssociada.length==null || listaNaturezaAssociada.length==0){      
                if(listaTipoClienteAssociada.length==null || listaTipoClienteAssociada.length==0){      
                    if(listaSegmentacaoAssociada.length==null || listaSegmentacaoAssociada.length==0){  
                        if(listaCarterizacaoAssociada.length==null || listaCarterizacaoAssociada.length==0){
                            if(listaTipoLinhaAssociada.length==null || listaTipoLinhaAssociada.length==0){
                                document.forms["0"].persiste.value='-1';
                                return true;
                            }
                        }
                    }
                }
            }
            
            
            if(listaGruposAssociados.length==null || listaGruposAssociados.length==0){
                return false;
            }           
            
            if(listaNaturezaAssociada.length==null || listaNaturezaAssociada.length==0){
                return false;
            }           
            
            if(listaTipoClienteAssociada.length==null || listaTipoClienteAssociada.length==0){
                return false;
            }           
                      
            if(listaSegmentacaoAssociada.length==null || listaSegmentacaoAssociada.length==0){
                return false;
            }           
                   
            if(listaCarterizacaoAssociada.length==null || listaCarterizacaoAssociada.length==0){
                return false;
            }           
            
            if(listaTipoLinhaAssociada.length==null || listaTipoLinhaAssociada.length==0){
                return false;
            }
            
            return true;  
        }
        
        function validacaoGrupos(){
        
            var listaGruposDisponiveis = document.forms[0].gruposDisponiveis;
            
            if(listaGruposDisponiveis.length==null || listaGruposDisponiveis.length==0){
                return false;
            }           
            
            for(i = 0;i<listaGruposDisponiveis.length;i++){
               document.forms[0].gruposDisponiveis.options[i].selected = true;
            } 
            return true;  
        }

        function gravar(){
        
            var lista = document.forms[0].gruposAssociados;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].gruposAssociados.options[i].selected =true; 
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
            
            lista = document.forms[0].tipoLinhaAssociada;            
            //Processa gravação
            for(i = 0;i<lista.length;i++){
               document.forms[0].tipoLinhaAssociada.options[i].selected =true; 
            }
            
            return true;                                                                        
        }
        
        function transfereSelecaoLista(listaOrigem, listaDestino) 
        {
            var apagarGrupos = 0;
            if(listaDestino.name=='gruposDisponiveis')
            {
                if (confirm("Deseja apagar as variáveis associadas ao grupo e Tipo de Fechamento?")) 
                    apagarGrupos = 1;
                else
                    return;
            }
            
            for(var i = 0; i < listaOrigem.options.length; i++){
                if(listaOrigem.options[i].selected){               
                    if(!VerificaDuplicidadeLista(listaOrigem.options[i].text, listaDestino)){
                    
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                         
                       // if(listaDestino.name=='gruposDisponiveis'){                        
                            listaOrigem.options[i] = null;
                            i = -1;
                       // }
                    }
                }
            }
            if (apagarGrupos == 1)
                apagar();
        }
        
        function apagaSelecaoLista(listaOrigem) 
        {
            for(var i = 0; i < listaOrigem.options.length; i++){
                if(listaOrigem.options[i].selected){               
                    listaOrigem.options[i] = null;
                    i = -1;
                }
            }
        }
        
        function VerificaDuplicidadeLista(valor, lista) {
            for(var i = 0; i < lista.options.length; i++) {
                if(lista.options[i].text == valor) {
                    return(true);
                }
            }
            return(false);
        }
        
        function changeOpcao(objeto)
        {        
            //Liga animação
            selecionaAbaVariaveis = false;
            if(objeto.value!=-1){
                if( top.frameApp.dvAnimarAguarde != null ){
                    top.frameApp.startAnimation();
                }            
                document.forms[0].method = "POST";
                document.forms[0].action = "carregaGrupos.do";//?fechamento="+objeto.value;
                document.forms[0].target = "frameEscondido";
                document.forms[0].submit();  
            }
            else{
                var tamanho = document.forms["0"]["gruposDisponiveis"].options.length;
                for(var i = 0; i < tamanho; i++){                      
                    document.forms["0"]["gruposDisponiveis"].options[0] = null;
                }
                
                var tamanho = document.forms["0"]["gruposAssociados"].options.length;
                for(var i = 0; i < tamanho; i++){
                    document.forms["0"]["gruposAssociados"].options[0] = null;                
                }
                
            }            
        }
        
        function ocultarDiv()
        {
           if (typeof(parent.oculta_div) != "undefined")
            {
                parent.oculta_div();
            }
            else
            {
                parent.parent.oculta_div();
            }
        }
        
        var selecionaAbaVariaveis = false;
        function abaGrupos()
        {
            dvAbaVariaveis.style.display='none';
            dvAbaGrupo.style.display='';
            changeOpcao(document.forms["0"]["fechamento"]);
        }
        
        function abaVariaveis(obj)
        {   
        
            idContatoDados.innerHTML='<%=Form.getDsPath().replaceAll("\\\\","\\\\\\\\")%>';
            idTipoFechamentoDados.innerHTML=document.forms["0"].fechamento.options[document.forms["0"].fechamento.selectedIndex].text;
            idGrupoDados.innerHTML=document.forms["0"].gruposAssociados.options[document.forms["0"].gruposAssociados.selectedIndex].text;
            
            //Liga animação
            if( top.frameApp.dvAnimarAguarde != null ){
                top.frameApp.startAnimation();
            }  
            
            //Monta o path seleção
            document.forms["0"].target = "frameEscondido";
            document.forms["0"].action = "carregaVariaveis.do?fechamento="+document.forms["0"].fechamento.options[document.forms["0"].fechamento.selectedIndex].value+"&grupo="+obj.value;
            document.forms["0"].submit();
            
            abaSelecionada();
        }
        
        function abaSelecionada()
        {
            if(selecionaAbaVariaveis){
                abaSelected(btAbaInfo, btInfo02); 
                dvAbaGrupo.style.display='none';
                dvAbaVariaveis.style.display='';
            }
        }
        
    </script>

</head>
<body onload="ocultarDiv()">
<!--acesso:controlHiddenItem nomeIdentificador="adm_aret_verpagina"-->
    <html:form action="/admsistemas/admArvoreContato/workflow/AssociacaoGrupos/salvaAssociacaoGrupo.do" styleId="salvaAssociacaoGrupo" method="post">
            <html:hidden property="contato" name="Form"/>
            <html:hidden property="grupo" name="Form"/>
            <input type="hidden" name="persiste" id="persiste" value="0"/>
            <input type="hidden" name="apagar" id="apagar" value="0"/>
            <vivo:abaGrupo id="btAbaInfo" width="300" height="10" styleClass="abatexto">
                <vivo:abaItem id="btInfo01" onclick="abaSelected(btAbaInfo, btInfo01); abaGrupos();" value="Grupos" select="S"/>
                <vivo:abaItem id="btInfo02" onclick="abaSelecionada();" value="Variáveis"/>
            </vivo:abaGrupo>
            <div id="dvAbaGrupo">
                <table border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray" height="30" width="750" align="center">
                    <tr>
                        <td>
                            <table border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray" align="left">
                                <tr>
                                    <td width="100" style="text-indent:6px;" valign="middle" align="left">
                                        <b>Fechamento:</b>
                                    </td>
                                    <td width="100%" class="tblEstatica_campoNome" align="left" valign="middle">
                                        <html:select name="Form" property="fechamento" onchange="changeOpcao(this);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 40, 90, 25);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 40, 90, 25);"  onblur="HideTip();" onmouseout="HideTip();" style="width=200">
                                            <html:option value="-1">Selecionar Operação</html:option>
                                            <html:options collection="WFAcaoVO" property="idAtividade" labelProperty="dsAtividade"/>
                                        </html:select>
                                    </td>       
                                </tr>
                            </table>
                        </td>
                    </tr>    
                    <tr>
                        <td>            
                            <table align="center">
                                <tr>
                                    <td align="center">
                                        Grupos disponíveis<br>
                                        <html:select name="Form" property="gruposDisponiveis" multiple="true" size="22" style="width:300px;">
                                            <html:options collection="aGruposDisp" property="codigo" labelProperty="descricao" /> 
                                        </html:select>                                        
                                    </td>
                                    <td align="right" valign="middle" width="76">
                                        <img id="btRightSimp" onclick="transfereSelecaoLista(document.forms[0].gruposDisponiveis, document.forms[0].gruposAssociados);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" style="border:none;"/>
                                        <img id="btLeftSimp" onclick="transfereSelecaoLista(document.forms[0].gruposAssociados,document.forms[0].gruposDisponiveis);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;"/>
                                    </td>
                                    <td width="30"></td>
                                    <td align="center">
                                        Grupos associados<br>
                                        <html:select name="Form" property="gruposAssociados" multiple="false" size="22" style="width:300px;" ondblclick="selecionaAbaVariaveis=true;abaVariaveis(this);">
                                            <html:options collection="aGruposAsso" property="codigo" labelProperty="descricao" /> 
                                        </html:select>  
                                    </td>
                                </tr>
                            </table>                        
                        </td>
                    </tr>   
                </table>
            </div>
            <div id="dvAbaVariaveis" style="display:none;">
                <table border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray" width="750">
                    <tr>
                        <td>
                            <div style="overflow:auto;width:745px;height:350px;">
                                <table border="0" cellspacing="0" cellpadding="1" class="tbl_bgGray" width="725">
                                    <tr>
                                        <td>
                                            <vivo:quadro id="qdDados" height="120" width="350" label="Dados">
                                                <table border="0">
                                                    <tr>
                                                        <td align="right"><b>Contato:</b></td>
                                                        <td align="left" id="idContatoDados"></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right"><b>Grupo:</b></td>
                                                        <td align="left" id="idGrupoDados"></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right"><b>Tipo fechamento:</b></td>
                                                        <td align="left" id="idTipoFechamentoDados"></td>
                                                    </tr>
                                                </table>
                                            </vivo:quadro>
                                        </td>
                                        <td width="5"></td>
                                        <td>
                                            <vivo:quadro id="qdTipoCliente" height="120" width="350" label="Tipo Cliente">
                                                <table border="0">
                                                    <tr>
                                                        <td>
                                                            Dispon&iacute;veis<br>
                                                            <html:select name="Form" property="naturezaDisponivel" multiple="true" size="6" style="width=150px"/>
                                                        </td>
                                                        <td>
                                                            <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].naturezaDisponivel, document.forms[0].naturezaAssociada);return false;"/>
                                                            <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].naturezaAssociada,document.forms[0].naturezaDisponivel);return false;"/>                                            
                                                        </td>
                                                        <td>
                                                            Associados<br>                    
                                                            <html:select name="Form" property="naturezaAssociada" multiple="true" size="6" style="width=150px"/>
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
                                            <vivo:quadro id="qdNatureza" height="120" width="350" label="Natureza">
                                                <table border="0">
                                                    <tr>
                                                        <td align="center">
                                                            Dispon&iacute;veis<br/>
                                                            <html:select name="Form" property="tipoClienteDisponivel" multiple="true" size="6" style="width=150px"/>
                                                        </td>
                                                        <td align="center" valign="middle">
                                                            <vivo:botao id="bt01" width="25px" height="25px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].tipoClienteDisponivel, document.forms[0].tipoClienteAssociada);return false;"/>
                                                            <vivo:botao id="bt03" width="25px" height="25px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].tipoClienteAssociada,document.forms[0].tipoClienteDisponivel);return false;"/>
                                                        </td>
                                                        <td align="center">
                                                            Associada<br/>
                                                            <html:select name="Form" property="tipoClienteAssociada" multiple="true" size="6" style="width=150px"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </vivo:quadro>                            
                                        </td>
                                        <td width="5"></td>
                                        <td>
                                            <vivo:quadro id="qdSegmentacao" height="120" width="350" label="Segmentação">
                                                <table border="0">
                                                    <tr>
                                                        <td>
                                                            Dispon&iacute;veis<br>
                                                            <html:select name="Form" property="segmentacaoDisponivel" multiple="true" size="6" style="width=150px"/>
                                                        </td>
                                                        <td>
                                                            <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].segmentacaoDisponivel, document.forms[0].segmentacaoAssociada);return false;"/>
                                                            <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].segmentacaoAssociada,document.forms[0].segmentacaoDisponivel);return false;"/>
                                                        </td>
                                                        <td>
                                                            Associados<br>                    
                                                            <html:select name="Form" property="segmentacaoAssociada" multiple="true" size="6" style="width=150px"/>
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
                                            <vivo:quadro id="qdCarterizacao" height="120" width="350" label="Carteirização">
                                                <table border="0">
                                                    <tr>
                                                        <td>
                                                            Dispon&iacute;veis<br>
                                                            <html:select name="Form" property="carterizacaoDisponivel" multiple="true" size="6" style="width=150px"/>
                                                        </td>
                                                        <td>
                                                            <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].carterizacaoDisponivel, document.forms[0].carterizacaoAssociada);return false;"/>
                                                            <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].carterizacaoAssociada,document.forms[0].carterizacaoDisponivel);return false;"/>
                                                        </td>
                                                        <td>
                                                            Associados<br>                    
                                                            <html:select name="Form" property="carterizacaoAssociada" multiple="true" size="6" style="width=150px"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </vivo:quadro>
                                        </td>
                                        <td width="5"></td>
                                        <td>
                                            <vivo:quadro id="qdTipoLinha" height="120" width="350" label="Tipo Linha">
                                                <table border="0">
                                                    <tr>
                                                        <td>
                                                            Dispon&iacute;veis<br>
                                                            <html:select name="Form" property="tipoLinhaDisponivel" multiple="true" size="6" style="width=150px"/>
                                                        </td>
                                                        <td>
                                                            <vivo:botao id="bt01" width="25px" height="20px" value=">" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].tipoLinhaDisponivel, document.forms[0].tipoLinhaAssociada);return false;"/>
                                                            <vivo:botao id="bt03" width="25px" height="20px" value="<" styleClass="btTemplate" onclick="transfereSelecaoLista(document.forms[0].tipoLinhaAssociada,document.forms[0].tipoLinhaDisponivel);return false;"/>                                           
                                                        </td>
                                                        <td>
                                                            Associados<br>                    
                                                            <html:select name="Form" property="tipoLinhaAssociada" multiple="true" size="6" style="width=150px"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </vivo:quadro>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td width="100%" align="right">
                            <img vspace="5" align="middle" href="" id="btSalvar" onClick="salvar(); return false;" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_salvar_over.gif" style="border:none;"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
            
            <iframe scrolling="yes" style="visibility:hidden;" name="frameEscondido" id="frameEscondido" height="1px" width="1px"></iframe>
        </html:form>  

    <script>

    //tooltip de combo 
        var moveToolTip = true;;
                    
        xBump=yBump=10;
        MSIE=document.all;
        NS6=document.getElementById&&!document.all;
        if(MSIE||NS6){
            ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
        }
        
        function carregaToolTip(content) {
         ttipObj.innerHTML=content;
         ttipObj.style.display='';
        }       
    //fim de tooltip de combo
   
        document.body.style.backgroundColor = '#ededed';
    </script>
<!--/acesso:controlHiddenItem-->
</body>