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

<bean:define id="Form"                name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm" />
<bean:define id="aAssuntoVO"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.assuntos.assuntoVOArray" />
<bean:define id="aSubAssuntoVO"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.subAssuntos.subAssuntoVOArray" />
<bean:define id="aConteudoVO"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.conteudos.conteudoVOArray" />
<bean:define id="aDisponibilidadeVO"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaValorPossivelForm.listaValorPossivelVO.disponibilidades.disponibilidadeVOArray" />

<head>

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].valorPossivel.value) == ""){
                alert("Favor preencher o campo Valor Possível!");
                return(false);
            }else if (trim(document.forms[0].sequenciaApresentacao.value) == ""){
                alert("Favor preencher o campo SQ Apresentação!");
                return(false);
            } else {
                return(true);
            }
        }
    
        function salvarValorPossivel() {
            if(valida()) {
                var action   = "salvarValorPossivel.do"
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
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();      
                document.forms[0].submit();
            }
        }
        
    </script>
    <script for="window" event="onload">
           if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();      
    </script>

    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" ></script>

</head>

<body leftmargin="10" topmargin="10" rightmargin="10" bottommargin="10">
<acesso:controlHiddenItem nomeIdentificador="cli_vl_verpagina">

        <form action="salvarValorPossivel.do" target="_parent" method="post" name="listaValorPossivelForm">
        <logic:equal name="Form" property="inMsgRetorno" value="true">
            <script>
                alert('Valor Possível não pôde ser incluído/alterado pois já existe um valor possível com essa descrição!');
            </script>
        </logic:equal>    
            
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="15"></div>
            
            <table width="480" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td style="text-indent:6px;">Assunto:</td>
                    <td>
                        <html:hidden name="Form" property="assuntoSelecionado" />
                        <html:select name="Form" property="assuntoSelecionado" title="assuntoSelecionado" styleClass="SELECT" style="width:300px" disabled="true">
                            <html:options collection="aAssuntoVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                </tr>                                              
                <tr><td  height="5"></td></tr>              
                <tr> 
                    <td style="text-indent:6px;">Subassunto:</td>
                    <td>
                        <html:hidden name="Form" property="subAssuntoSelecionado" />
                        <html:select name="Form" property="subAssuntoSelecionado" title="subAssuntoSelecionado" styleClass="SELECT" style="width:300px" disabled="true">
                            <html:options collection="aSubAssuntoVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                </tr>
                <tr><td height="5"></td></tr>
                <tr> 
                    <td style="text-indent:6px;">Conte&uacute;do:</td>
                    <td>
                        <html:hidden name="Form" property="conteudoSelecionado" />
                        <html:select name="Form" property="conteudoSelecionado" title="conteudoSelecionado" styleClass="SELECT" style="width:300px" disabled="true">
                            <html:options collection="aConteudoVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                </tr>
                <tr><td height="5"></td></tr>
                <tr> 
                    <td style="text-indent:6px;" valign="baseline">
                        <html:hidden name="Form" property="codigoValorPossivel"/>Valor Possível:
                    </td>
                    <td>
                        <html:text name="Form" property="valorPossivel" maxlength="50" title="valorPossivel" size="40" />
                    </td>                    
                </tr> 
                <tr><td  height="5"></td></tr>                                          
                <tr> 
                    <td style="text-indent:6px;">Sequência de apresentação:</td>
                    <td>
                        <html:text name="Form" property="sequenciaApresentacao" onkeyup="checaInteiro(this)" maxlength="3" title="sequenciaApresentacao" size="5" />
                    </td>
                </tr>
                <tr><td  height="5"></td></tr>               
                <tr> 
                    <td style="text-indent:6px;">Disponibilidade:</td>
                    <td>
                        <html:select name="Form" property="disponibilidadeSelecionado" title="disponibilidadeSelecionado" styleClass="SELECT" style="width:50px">
                            <html:options collection="aDisponibilidadeVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="2">
                        <acesso:controlHiddenItem nomeIdentificador="cli_vl_gravar">
                            <img style="border:0px;" onClick="salvarValorPossivel(); return false"      src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table> 
            
            <script>
                document.body.style.backgroundColor = '#ededed';
     
    
                <%if(request.getParameter("operacao").equals("INSERT")) {%>
                    //alert('insert');
                    //document.forms[0].valorPossivel.value = '';
                <%}else{%>
                    //alert('edita');
                    
                <%}%>
            </script>
        </form>
</acesso:controlHiddenItem>
</body>
