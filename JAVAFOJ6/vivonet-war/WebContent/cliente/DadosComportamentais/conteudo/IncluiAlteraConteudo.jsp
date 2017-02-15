<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"                 name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm" />
<bean:define id="aAssuntoVO"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.assuntos.assuntoVOArray" />
<bean:define id="aSubAssuntoVO"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.subAssuntos.subAssuntoVOArray" />
<bean:define id="aTipoApresenacaoVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.tiposApresentacao.tipoApresentacaoVOArray" />
<bean:define id="aDisponibilidadeVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.disponibilidades.disponibilidadeVOArray" />
<bean:define id="aCanalVODisp"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.listaConteudoVO.canais.canalArray" />
<bean:define id="aCanalVOSel"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaConteudoForm.canaisSelecionadosVO" />

<head>

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    
    
    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].conteudo.value) == ""){
                alert("Favor preencher o campo Conteúdo!");
                return(false);
            }else if (trim(document.forms[0].sequenciaApresentacao.value) == ""){
                alert("Favor preencher o campo SQ Apresentação!");
                return(false);
            } else {
                return(true);
            }
        }
    
        function salvarConteudo() {
            for(i=0; i < document.listaConteudoForm.canaisSelecionados.options.length; i++){
                document.listaConteudoForm.canaisSelecionados.options[i].selected = true;
            }
            if(valida()) {
                var action   = "salvarConteudo.do"
                var operacao = "<bean:write name="operacao" scope="request"/>";
                
                switch(operacao) {
                    case "INSERT":
                        action += "?operacao=INSERT";
                        break;
                        
                    case "UPDATE":
                        action += "?operacao=UPDATE&index=<bean:write name="index" scope="request"/>";
                        break;
                }
                document.forms[0].action = action;
                //document.forms[0].target = "executa";
                document.forms[0].submit();
            }
        }
        
        
        
        function mostraDisponibilidadeAssociada(){   
            var disponibilidadeAssociada = "<bean:write name="Form" property="disponibilidadeAssociada"/>";         
            if(disponibilidadeAssociada == 0)
                listaConteudoForm.disponibilidadeSelecionado.options[disponibilidadeAssociada+1].selected = true;
        }
        
        //copia os elementos selecionados para o combo destino, mas não os apaga do combo origem
        function transfereSelecaoLista(listaOrigem, listaDestino)
        {
            var i;
            for(i = 0; i<listaOrigem.options.length; i++){
                if(listaOrigem.options[i].selected && testaDestino(listaOrigem.options[i].value, listaDestino)){
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                }
            }
        }
        
        //testa se o elemento selecionado do combo origem já está no combo destino
        function testaDestino(valor, listaDestino)
        {   var i;
            if(listaDestino.options.length == 0){
                return true;
            }
            else
            {
                for(i = 0; i < listaDestino.options.length; i++)
                    if(valor == listaDestino.options[i].value)
                        return false;
                return true;
            }
        }
                
        function removeSelecaoLista(listaSelecionada)
        {
            var i;
            for(i = listaSelecionada.options.length-1; i>=0; i--)
                if(listaSelecionada.options[i].selected)
                    listaSelecionada.options[i] = null;
                    
            return false;
        }           
    </script>

</head>

<body>
<acesso:controlHiddenItem nomeIdentificador="cli_iac_verpagina">
    <html:form action="salvarConteudo" target="_parent"> 
        <logic:equal name="Form" property="inMsgRetorno" value="true">
            <script>
                alert('Conteúdo não pôde ser incluído/alterado pois já existe um conteúdo com essa descrição!');
            </script>
        </logic:equal>
        <html:hidden name="Form" property="codigoConteudo" />
        
        <table width="480" border="0" cellspacing="0" cellpadding="0">
            <tr><td  height="10"></td></tr>
            <tr> 
                <td style="text-indent:6px;">Assunto:</td>
                <td style="padding-left:3px;">
                    <html:hidden name="Form" property="assuntoSelecionado" />
                    <html:select name="Form" property="assuntoSelecionado" title="assuntoSelecionado" onchange="JavaScript:obterSubAssuto();"  styleClass="SELECT" style="width:300px" disabled="true">
                        <html:options collection="aAssuntoVO" property="codigo" labelProperty="descricao" />
                    </html:select>
                </td>
            </tr>
            <tr><td  height="5"></td></tr>
            <tr> 
                <td style="text-indent:6px;">Subassunto:</td>
                <td style="padding-left:3px;">
                    <html:hidden name="Form" property="subAssuntoSelecionado" />
                    <html:select name="Form" property="subAssuntoSelecionado" title="subAssuntoSelecionado"  styleClass="SELECT" style="width:300px" disabled="true">
                        <html:options collection="aSubAssuntoVO" property="codigo" labelProperty="descricao" />
                    </html:select>
                </td>
            </tr>  
            <tr><td  height="20"></td></tr>              
            <tr> 
                <td style="text-indent:6px;" valign="baseline">Conteúdo:</td>
                <td>
                    <html:text name="Form" property="conteudo" maxlength="100" title="conteudo" size="40" style="width:300px;" />
                </td>                    
            </tr>
            <tr><td height="5"></td></tr>                
            <tr> 
                <td style="text-indent:6px;">Tipo de Apresentação:</td>
                <td style="padding-left:3px;">
                    <html:select name="Form" property="tipoApresentacaoSelecionado" title="tipoApresentacaoSelecionado" styleClass="SELECT" style="width:300px;">
                        <html:options collection="aTipoApresenacaoVO" property="codigo" labelProperty="descricao" />
                    </html:select>
                </td>
            </tr>
            <tr><td  height="5"></td></tr>
            <tr>
                <td style="text-indent:6px;">SQ Apresentação:</td>
                <td>
                    <html:text name="Form" property="sequenciaApresentacao" maxlength="3" onkeyup="checaInteiro(this)" title="sequenciaApresentacao" size="5" />
                </td>
            </tr>
            <tr><td height="5"></td></tr>               
            <tr> 
                <td style="text-indent:6px;">Disponibilidade:</td>
                <td style="padding-left:3px;">
                    <html:select name="Form" property="disponibilidadeSelecionado" title="disponibilidadeSelecionado" styleClass="SELECT" style="width:50px">
                        <html:options collection="aDisponibilidadeVO" property="codigo" labelProperty="descricao" />
                    </html:select>
                </td>
            </tr>
            <tr><td height="5"></td></tr>                
            <tr>
            <td colspan="2">
                <table width="480">
                    <tr>
                        <td rowspan="2" width="11"></td>
                        <td width="198" rowspan="2"><center>Canais Disponíveis</center>
                            <html:select name="Form" style="width:198px;" property="canaisDisponiveis" multiple="true" size="4"> 
                                <html:options collection="aCanalVODisp"  property="idCanal" labelProperty="nmCanal"/>                                        
                            </html:select>
                        </td>
                        <td width="72">
                            <img vspace="10" src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif" style="border:none;"  onclick="transfereSelecaoLista(document.listaConteudoForm.canaisDisponiveis, document.listaConteudoForm.canaisSelecionados);return false" rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif"/><br>
                            
                        </td>
                        <td rowspan="2" width="199"><center>Canais Selecionados</center>
                            <html:select name="Form" style="width:198px;" property="canaisSelecionados" multiple="true" size="4"> 
                                <html:options collection="aCanalVOSel"  property="idCanal" labelProperty="nmCanal"/>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td><img src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" style="border:none;" onclick="removeSelecaoLista(document.listaConteudoForm.canaisSelecionados);return false" rolloverImage="/FrontOfficeWeb/resources/images/bt_left_over.gif"/></td>
                    </tr>
                </table>
            </td>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="cli_iac_gravar">
                        <img style="border:none;" onClick="salvarConteudo(); return false" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
                    </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
         
        <script>
            document.body.style.backgroundColor = '#ededed';
            mostraDisponibilidadeAssociada();
        </script>                      
    
    </html:form>
</acesso:controlHiddenItem>
</body>
