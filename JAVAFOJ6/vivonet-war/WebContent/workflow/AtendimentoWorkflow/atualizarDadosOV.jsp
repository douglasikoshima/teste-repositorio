<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="atdWFComumVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO"/>
<bean:define id="gruposVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFGrupoVOArray"/>
<bean:define id="motivosVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.atendimentoWorkflowVO.atendimentoWorkflowComumVO.WFMotivoVOArray"/>
<bean:define id="usuarioVIVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.usuarioVIVO" />
<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    
    <script for="window" event="onload">
        <!--
            <logic:notEmpty name="VALIDACAO_SERASA_CNPJ" scope="request">
                alert("A informação abaixo está inconsistente conforme encaminhada pelo SERASA : CNPJ. Favor alterar.");
            </logic:notEmpty>                                    
            <logic:notEmpty name="VALIDACAO_SERASA_IE" scope="request">
                alert("A informação abaixo está inconsistente conforme encaminhada o SERASA: Inscrição Estadual. Favor alterar.");
            </logic:notEmpty>                                    
            <logic:notEmpty name="VALIDACAO_SERASA_CNPJ_IE" scope="request">
                alert("A(s) informação(ões) abaixo estão inconsistentes conforme encaminhada pelo SERASA : CNPJ e Inscrição Estadual. Favor alterar.");
            </logic:notEmpty>                                                
        -->
    </script>   
    
    <script>
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
    </script>
    <script language="javascript">
        function checaTextarea(obj, tamanho){
            obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
        }
        
        function submitAplicar() {

            //Liga animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    
            if (parent.dvRetornoOperacao) {
                parent.dvRetornoOperacao.style.visibility="";
                targetFrame = "iframeRetornoOperacao";
            } else {
                targetFrame = "";
            }        
            
            document.forms["formEncaminhar"].method = "POST";
            //document.forms["formEncaminhar"].action = "confirmaAtualizacaoDados.do";
            document.forms["formEncaminhar"].target = targetFrame;
            document.forms["formEncaminhar"].submit();
        }
        
        function retornarAnterior()  {
            parent.submitPesquisar();
        }

        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        document.body.style.backgroundColor = '#ededed';
    </script>
</head>
<body>  
    <form action="confirmaAtualizacaoDados.do" name="atendimentoForm" method="post" tagId="formEncaminhar" id="formEncaminhar">
        
        <html:hidden name="form" property="idAtendimento"/>
        <html:hidden name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.IDSTATUSSAP"/>
        <html:hidden name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.IDRETENCAO"/>
        
      
        
    
        <div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>        
        <table width="100%">
            <tr valign="top">
                <td width="10%">Nome:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.NOME" style="width=250"/></td>
                <td width="10%"></td>
                <td width="40%"></td>
            </tr>
            <tr valign="top">
                <td width="10%">Rua:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.RUACLI" style="width:250px"/></td>
                <td width="10%">Numero:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.NUMCLI" style="width=250"/></td>
            </tr>            
            <tr valign="top">
                <td width="10%">Complemento:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.COMPLCLI" style="width=250"/></td>
                <td width="10%">Bairro:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.BAIRROCLI" style="width=250"/></td>
            </tr>                                       
            <tr valign="top">            
                <td width="10%">Cidade:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.CIDADECLI" style="width=250"/></td>
                <td width="10%">Cep:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.CEPCLI" style="width=250"/></td>
            </tr>                           
            <tr valign="top">
                <td width="10%">Estado:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.ESTADOCLI" style="width=250"/></td>
                <td width="10%">Pais:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.PAISCLI" style="width=250"/></td>
            </tr>                              
        </table>
        
        <br/>

        <table width="100%">
            <tr valign="top">
                <td width="10%">Rua:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.RUAENT" style="width=250"/></td>
                <td width="10%">Numero:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.NUMENT" style="width=250"/></td>
            </tr>                        
            <tr valign="top">
                <td width="10%">Complemento:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.COMPLENT" style="width=250"/></td>
                <td width="10%">Bairro:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.BAIRROENT" style="width=250"/></td>
            </tr>                 
            <tr valign="top">
                <td width="10%">Cidade:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.CIDADEENT" style="width=250"/></td>
                <td width="10%">Cep:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.CEPENT" style="width=250"/></td>
            </tr>                           
            <tr valign="top">
                <td width="10%">Estado:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.ESTADOENT" style="width=250"/></td>
                <td width="10%">Pais:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.PAISENT" style="width=250"/></td>
            </tr>                               
        </table>      
        
        <br/>
        
        <table width="100%">
            <tr valign="top">
                <td width="10%">Telefone:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.TELEFONE" style="width=250"/></td>
                <td width="10%">Inscricao Municipal:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.INSCRMUNICIPAL" style="width=250"/></td>
            </tr>            
            <tr valign="top">
                <td width="10%">Documento:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.DOCUMENTO" style="width=250"/></td>
                <td width="10%">Inscricao Estadual:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.INSCRESTADUAL" style="width=250"/></td>
            </tr>                     
            <tr valign="top">
                <td width="10%">E-mail:</td>
                <td width="40%"><html:text name="form" property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.CLIENTE.EMAIL01" style="width=250"/></td>
                <td width="10%"></td>
                <td width="40%"></td>
            </tr>                                       
        </table>          
        
        <br/>
        
        <table width="100%">
            <tr valign="top">
                <td width="100%">
                    <html:textarea property="WFAcaoOrdemVendaVO.dadosOrdemVendaVO.DSOBSERVACAO" rows="3" style="width:700px" onkeyup="checaTextarea(this, 500);"/>
                </td>
            </tr>                                          
        </table>                    
        
        <table width="100%">
            <tr>
                <td align="center">
                    <img hspace="10" onClick="submitAplicar(); return false" style="border:none;" id="btAplicar" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
                </td>
            </tr>
        </table>        
        
    </form>
    <iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
</body>
</html>