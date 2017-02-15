<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormCalendario"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario"/>

<logic:equal  name="FormCalendario" property="indicativoMunicipal" value="1">
    <bean:define id="UfsVOExistentes"  name="FormCalendario" property="listaUFVOExistente"/>
    <bean:define id="UfsVORelacionadas"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.listaUFVORelacionados"/>
</logic:equal>

<logic:equal  name="FormCalendario" property="indicativoMunicipal" value="0">
    <bean:define id="ListaUFs"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.listaUFs"/>
</logic:equal>

<logic:equal  name="FormCalendario" property="contadorAdmFeriadoLenght" value="1">
    <bean:define id="ListaMunicipiosExistentesVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.listaMunicipiosExistentesVO"/>
    <bean:define id="ListaMunicipiosRelacionadosVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formCalendario.listaMunicipiosRelacionadosVO"/>
</logic:equal>

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Manter Calendário"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="adm_mantpar_verpagina">
    
    <logic:notEqual  name="FormCalendario" property="idTipoFeriadoParam" value="3">
    <form action="gravaManutencaoUF.do" name="formCalendario" method="post">
    
    
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script>
        function mostraJanelaDetalhe(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        
        function abreJanela(){
            mostraJanelaDetalhe()
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'pesquisarFeriado.jsp';
        }

        function fechaJanela(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'nada.html';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }
    
        
        function selecionaDiv(visdiv){
            divUF        = document.getElementById('dvUFs');   
            
            if (visdiv == 'UF') {
                divUF.style.display = 'block';
                
            }
            
        }
        
        function transfereSelecaoLista(listaOrigem, listaDestino)
        {
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
        
        function Salvar() {
           // if (document.forms[0].arrayUFVORelacionado.options.length > 0){
                for ( i = 0; i < document.forms[0].arrayUFVORelacionado.options.length; i++ )                            
                    document.forms[0].arrayUFVORelacionado.options[i].selected = true;                
                mostrar_div();                
                document.forms[0].submit();
          //  }else{
         //       alert("Favor selecionar um Canal Relacionado!")
         //   }
        }        

    </script>
    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormCalendario" property="msgError" />' != "")
        {
            alert('<bean:write name="FormCalendario" property="msgError" />');
        }
        oculta_div();
    -->
    </script> 

        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
            <vivo:sessao id="qdMain" height="450" width="790" label="Calendário" operacoes="Manutenção de Parâmetros" scroll="yes">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
                <table align="center" width="757" border="0" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div></td>                            
                    </tr>
                    <tr>
                        <td width="105">                                    
                            <netui:label value="Data:" styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td style="text-align:left;" align="left">
                            <bean:write name="FormCalendario" property="dtFeriadoParam" />
                            
                        </td>
                        <td align="left" colspan="2">
                            &nbsp;
                            
                        </td>
                        <td colspan="2" width="100">
                        </td>                       
                    </tr>
                    <tr><td height="5"></td></tr>
                    <tr>
                        <td>
                            <netui:label value="Descrição: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td valign="top" colspan="5"> 
                            <bean:write name="FormCalendario" property="dsFeriadoParam" />
                        </td>
                    </tr>
                    <tr>
                                    
                        <td colspan="2">
                            <div id="divcampo" style="visibility:visible; top: 0px; left: 0px; height: 24px; padding: 0px;">
                            </div>
                        </td>                        
                        <td colspan="3">
                            <b>Feriado Móvel:</b>&nbsp;&nbsp;
                            <logic:equal  name="FormCalendario" property="indMovelParam" value="1">
                                Sim
                            </logic:equal>
                            <logic:notEqual  name="FormCalendario" property="indMovelParam" value="1">
                                N&atilde;o
                            </logic:notEqual>
                        </td>                       
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Tipo do Feriado: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td colspan="2" class="tblEstatica_campoValor" align="left">
                            <bean:write name="FormCalendario" property="dsTipoFeriadoParam" />
                        </td>
                        <td align="left">
                            <b><netui:label value="Relatório DRC:"/></b>&nbsp;
                            <logic:equal  name="FormCalendario" property="inRelatorioParam" value="1">
                                Sim
                            </logic:equal>
                            <logic:notEqual  name="FormCalendario" property="inRelatorioParam" value="1">
                                N&atilde;o
                            </logic:notEqual>
                        </td>
                        <td>&nbsp;</td>
                        <td class="tblEstatica_campoValor">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div></td>                            
                    </tr>
                </table>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                <center>
                
                
                
                
                
                <div id="dvUFs" style="display:block;">
                
                <vivo:quadro width="757" height="300" id="UFs" scroll="no" label="Relacione as UFs para as quais o feriado cadastrado é válido">
                <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
                            <tr>
                                <td width="335" align="center">UFs existentes</td>
                                <td width="77">&nbsp;</td>
                                <td width="335" align="center">UFs relacionadas</td>
                            </tr>
                            <tr>
                                <td align="center" valign="top" bgcolor="#ffffff">
                                    <html:select name="FormCalendario" property="arrayUFVOExistente" multiple="true" style="width:335px;height:266px;">
                                        <html:options collection="UfsVOExistentes" property="idUF" labelProperty="nmUF" /> 
                                    </html:select>
                                </td>
                                <td>
                                    <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                        <tr>
                                            <td align="center">
                                                <img id="btRightSimp" onclick="transfereSelecaoLista(document.formCalendario.arrayUFVOExistente, document.formCalendario.arrayUFVORelacionado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border-width:0;cursor:hand"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                                &nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center">
                                                <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formCalendario.arrayUFVORelacionado, document.formCalendario.arrayUFVOExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border-width:0;cursor:hand"/>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td align="center">
                                    <html:select name="FormCalendario" property="arrayUFVORelacionado" multiple="true" style="width:335px;height:266px;">
                                        <html:options collection="UfsVORelacionadas" property="idUF" labelProperty="nmUF" /> 
                                    </html:select>                                    
                                </td>
                            </tr>                            
                        </table>
                </vivo:quadro>
                </center>
               
                </div>
                
            
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>

            <table width="757" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td>
                        <img id="btVoltar"  onClick="location.href='begin.do?isMenu=false'" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'"     style="border:none;cursor:hand"/>
                    </td>
                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        
                    </td>
                    <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="adm_mantpar_gravaruf">
                        <img id="btSalvar"  onclick="Salvar();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"  onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'"     onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"     style="border:none;cursor:hand"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            </vivo:sessao>
            
            <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute; top:36; left:36; width:728; height:528; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">
                <table width="728" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                    <tr>
                        <td id="titleBar" style="cursor:move" width="540" height="21">Pesquisa de Feriados </td>
                        <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                            <div align="right">
                                <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanela();" style="cursor:hand;" alt="Fechar">
                            </div>
                        </td>
                    </tr>
                </table>
                <table width="728" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <iframe id="frameDetalhe" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="728" height="507"></iframe>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>
            
            </form>
        </logic:notEqual>


    <logic:equal  name="FormCalendario" property="idTipoFeriadoParam" value="3">
    <form action="gravaManutencaoMunicipal" name="formCalendario" method="post">
    <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/calendar.js"></script>
    <script>
        //globais
        var isChange = false;
        
        function mostraJanelaDetalhe(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'visible';
            var b = document.getElementById("bg");
            b.style.visibility = 'visible';
        }
        
        
        function abreJanela(){
            mostraJanelaDetalhe()
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'pesquisarFeriado.jsp';
        }

        function fechaJanela(){
            var x = document.getElementById("detalhe");
            x.style.visibility = 'hidden';
            divFrame = document.getElementById('frameDetalhe');
            divFrame.src = 'nada.html';
            var b = document.getElementById("bg");
            b.style.visibility = 'hidden';
        }
    
        
        function selecionaDiv(visdiv){
            
            divMunicipio = document.getElementById('dvMunicipios');       
            
            if (visdiv == 'Municipio') {
                divMunicipio.style.display = 'block';
                
            } 
            
        }
        
        function montaListaUF(){
            if(document.forms[0].idUF.selectedIndex != 0)
            {
                document.forms[0].target = '_self';
                document.forms[0].action = 'carregaUFRelacionada.do';
                document.forms[0].submit();
            }
        }  
         
        function Salvar() {
           // if (document.forms[0].arrayMunicipioRelacionado.options.length > 0){
                for ( i = 0; i < document.forms[0].arrayMunicipioRelacionado.options.length; i++ )
                    document.forms[0].arrayMunicipioRelacionado.options[i].selected = true;
                document.forms[0].submit();
          //  }else{
         //       alert("Favor selecionar um Canal Relacionado!")
         //   }
        }         
        
        function transfereSelecaoLista(listaOrigem, listaDestino)
        {
            isChange = true;
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

        function valida()
        {
            if(isChange)
            {
                return confirm('Existem alterações não salvas, deseja realmente sair ?');
                
            }else if((document.forms[0].arrayMunicipioRelacionado) && document.forms[0].arrayMunicipioRelacionado.options.length == 0)
            {
                return confirm('Nenhuma UF foi selecionada, deseja realmente sair ?')
            }   
            
            return true;     
        
        }
        
        function enviaVoltar()
        {
            if(valida())
            {
                //location.href='pesquisarFeriado.jsp' 
                document.forms[0].action = 'begin.do?isMenu=false';
                mostrar_div();
                document.forms[0].submit();
            }
        }

    </script>
    
    <script language="javascript" for="window" event="onload">
    <!--   
        if('<bean:write name="FormCalendario" property="msgError" />' != "")
        {
            alert('<bean:write name="FormCalendario" property="msgError" />');
        }
        
    -->
    </script> 
        <!-- Menu de Administração de Sistemas -->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
            <vivo:sessao id="qdMain" height="450" width="790" label="Calendário" operacoes="Manutenção de Parâmetros" scroll="yes">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
                <table align="center" width="757" border="0" cellpadding="0" cellspacing="0" class="tbl_bgGray">
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div></td>                            
                    </tr>
                    <tr>
                        <td width="105">                                    
                            <netui:label value="Data:" styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td style="text-align:left;" align="left">
                            <bean:write name="FormCalendario" property="dtFeriadoParam" />
                        </td>
                        <td align="left" colspan="2">
                            &nbsp;
                            
                        </td>
                        <td colspan="2" width="100">
                        </td>                       
                    </tr>
                    <tr><td height="5"></td></tr>
                    <tr>
                        <td>
                            <netui:label value="Descrição: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td valign="top" colspan="5"> 
                            <bean:write name="FormCalendario" property="dsFeriadoParam" />
                        </td>
                    </tr>
                    <tr>
                                    
                        <td colspan="2">
                            <div id="divcampo" style="visibility:visible; top: 0px; left: 0px; height: 24px; padding: 0px;">
                            </div>
                        </td>                        
                        <td colspan="3">
                            <b>Feriado Móvel:</b>&nbsp;&nbsp;
                            <logic:equal  name="FormCalendario" property="indMovelParam" value="1">
                                Sim
                            </logic:equal>
                            <logic:notEqual  name="FormCalendario" property="indMovelParam" value="1">
                                N&atilde;o
                            </logic:notEqual>
                        </td>                       
                    </tr>
                    <tr>
                        <td>
                            <netui:label value="Tipo do Feriado: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td colspan="2" class="tblEstatica_campoValor" align="left">
                            <bean:write name="FormCalendario" property="dsTipoFeriadoParam" />
                        </td>
                        <td align="left">
                            <b><netui:label value="Relatório DLRI:"/></b>&nbsp;
                            <logic:equal  name="FormCalendario" property="inRelatorioParam" value="1">
                                Sim
                            </logic:equal>
                            <logic:notEqual  name="FormCalendario" property="inRelatorioParam" value="1">
                                N&atilde;o
                            </logic:notEqual>
                        </td>
                        <td>&nbsp;</td>
                        <td class="tblEstatica_campoValor">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="6"><div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div></td>                            
                    </tr>
                </table>
                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
                <center>
                
                
                
                
                
                <div id="dvMunicipios" style="display:block;">
                
                <vivo:quadro width="757" height="300" id="UFs" scroll="no" label="Relacione os munic&iacute;pios para os quais o feriado cadastrado é válido">
                <table width="100%" border="0" cellspacing="1" cellpadding="1" align="center">
                        <tr>
                            <td height="4" colspan="2" valign="middle">
                                    <netui:label value="Selecione uma UF: " styleClass="tblEstatica_campoNome"/>
                                    &nbsp;&nbsp;                
                                    <html:select name="FormCalendario" property="idUF" style="width:200px" style="padding-left:4px;" styleClass="SELECT" onchange="Javascript:montaListaUF();">
                                        <html:option value="000">Escolha uma opção...</html:option>
                                        <html:options collection="ListaUFs" property="idUF" labelProperty="nmUF" /> 
                                    </html:select>                                     
                            </td>
                        </tr>
                            
                             <logic:equal  name="FormCalendario" property="contadorAdmFeriadoLenght" value="1">
                                <tr>
                                    <td width="335" align="center">Municípios existentes</td>
                                    <td width="77">&nbsp;</td>
                                    <td width="335" align="center">Municípios relacionados</td>
                                </tr>
                                <tr>
                                    <td align="center" valign="top" bgcolor="#ffffff">
                                        <html:select name="FormCalendario" property="arrayMunicipioExistente" multiple="true" style="width:335px;height:230px;" styleClass="SELECT" size="4">
                                            <html:options collection="ListaMunicipiosExistentesVO" property="idMunicipio" labelProperty="nmMunicipio" /> 
                                        </html:select>
                                    </td>
                                    <td>
                                        <table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                                            <tr>
                                                <td align="center">
                                                    
                                                    <img id="btRightSimp" onclick="transfereSelecaoLista(document.formCalendario.arrayMunicipioExistente, document.formCalendario.arrayMunicipioRelacionado);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border-width:0;cursor:hand"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    &nbsp;
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="center">
                                                    
                                                    <img id="btLeftSimp" onclick="transfereSelecaoLista(document.formCalendario.arrayMunicipioRelacionado, document.formCalendario.arrayMunicipioExistente);" src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'" style="border-width:0;cursor:hand"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td align="center">
                                        <html:select name="FormCalendario" property="arrayMunicipioRelacionado" multiple="true" style="width:335px;height:230px;" styleClass="SELECT" size="4">
                                            <html:options collection="ListaMunicipiosRelacionadosVO" property="idMunicipio" labelProperty="nmMunicipio" /> 
                                        </html:select>                                    
                                    </td>
                                </tr>
                             </logic:equal>                        
                        </table>
                </vivo:quadro>
                </center>
               
                </div>
                
            
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            
            
                <table width="757" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                        <td>
                            <img id="btVoltar"  onClick="enviaVoltar();" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_voltar_over.gif'"     style="border:none;cursor:hand"/>
                        </td>
                        <logic:equal  name="FormCalendario" property="contadorAdmFeriadoLenght" value="1">
                            <td align="right">
                            <acesso:controlHiddenItem nomeIdentificador="adm_mantpar_gravavmun">
                                <img id="btSalvar"  onclick="Salvar();" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"    onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'"   onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'"     style="border:none;cursor:hand"/>
                            </acesso:controlHiddenItem>
                            </td>
                        </logic:equal>
                    </tr>
                </table>
            
            </vivo:sessao>
            
            <div id="detalhe" style="z-index:999 ;visibility: hidden; position:absolute; top:36; left:36; width:728; height:528; border-style:solid; border-width:1px; border-color:#adadad; background-color:#e3ecf4;">
                <table width="728" height="21" cellpadding="0" cellspacing="0" background="/FrontOfficeWeb/resources/images/window_topbg.gif" bgcolor="#0066cb" class="tbl_titulo">
                    <tr>
                        <td id="titleBar" style="cursor:move" width="540" height="21">Pesquisa de Feriados </td>
                        <td width="64" valign="top" background="/FrontOfficeWeb/resources/images/window_topbtbg.gif">
                            <div align="right">
                                <img src="/FrontOfficeWeb/resources/images/window_fechar.gif" hspace="3" onClick="fechaJanela();" style="cursor:hand;" alt="Fechar">
                            </div>
                        </td>
                    </tr>
                </table>
                <table width="728" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <iframe id="frameDetalhe" src="/FrontOfficeWeb/admsistemas/nada.html" frameborder="0" width="728" height="507"></iframe>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="bg" style="z-index:998 ;visibility: hidden; position:absolute; top:0; left:0; width:100%; height:100%; border-style:solid; background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); border-width:1px; border-color:#adadad;"></div>
        </form>
        </logic:equal>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
