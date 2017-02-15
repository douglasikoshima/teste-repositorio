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

<bean:define id="Form"                 name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm" />
<bean:define id="aAssuntoVO"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm.listaSubAssuntoVO.assuntos.assuntoVOArray" />
<bean:define id="aSubAssuntoVO"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm.listaSubAssuntoVO.subAssuntos.subAssuntoVOArray" />
<bean:define id="aDisponibilidadeVO"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaSubAssuntoForm.listaSubAssuntoVO.disponibilidades.disponibilidadeVOArray" />

<head>

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="javaScript">
        function valida() {
            if (trim(document.forms[0].subAssunto.value) == ""){
                alert("Favor preencher o campo Subassunto!");
                return(false);
            }else if (trim(document.forms[0].sequenciaApresentacao.value) == ""){
                alert("Favor preencher o campo SQ Apresentação!");
                return(false);
            } else {
            return(true);
        }
        }
    
        function salvarSubAssunto() {
            if(valida()) {
                var action   = "salvarSubAssunto.do"
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
        
        function cancelar(){
        
        <%if(request.getParameter("operacao").equals("INSERT")) {%>
            top.frameApp.dvInclusao.style.display = 'none';
        <%}else{%>
            top.frameApp.dvAlteracao.style.display = 'none';
        <%}%>
        }
        
        function mostraDisponibilidadeAssociada(){
            var disponibilidadeAssociada = "<bean:write name="Form" property="disponibilidadeAssociada"/>";        
            if(disponibilidadeAssociada == 0)
                listaSubAssuntoForm.disponibilidadeSelecionado.options[disponibilidadeAssociada+1].selected = true;
        }
        
    </script>
    <script for="window" event="onload">
           if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation(); 
    </script>

</head>
<body>
<acesso:controlHiddenItem nomeIdentificador="cli_iasa_verpagina">
        <form action="salvarSubAssunto.do" target="_parent" method="post" name="listaSubAssuntoForm">
              
            <table width="480" border="0" cellspacing="0" cellpadding="0">
                <tr><td  height="10"></td></tr>
                <tr> 
                    <td style="text-indent:6px;">Assunto:</td>
                    <td style="padding-left:3px;">
                        <html:hidden name="Form" property="assuntoSelecionado" />
                        <logic:equal name="Form" property="assuntoSelecionado" value="" >
                            <html:select name="Form" property="assuntoSelecionado" title="assuntoSelecionado" styleClass="SELECT" style="width:300px">
                                <html:options collection="aAssuntoVO" property="codigo" labelProperty="descricao" />
                            </html:select>
                        </logic:equal>
                        <logic:notEqual name="Form" property="assuntoSelecionado" value="" >
                            <html:select name="Form" property="assuntoSelecionado" title="assuntoSelecionado" styleClass="SELECT" style="width:300px" disabled="true">
                                <html:options collection="aAssuntoVO" property="codigo" labelProperty="descricao" />
                            </html:select>
                        </logic:notEqual>
                    </td>
                </tr>                                              
                <tr><td  height="10"></td></tr>              
                <tr>
                    <td style="text-indent:6px;" valign="baseline">
                        <html:hidden name="Form" property="codigoSubAssunto" />
                        Subassunto:
                    </td>
                    <td>
                        <html:text name="Form" property="subAssunto" maxlength="50" size="40" />
                    </td>                    
                </tr>
                <tr><td  height="5"></td></tr>                                               
                <tr> 
                    <td style="text-indent:6px;">SQ Apresentação:</td>
                    <td>
                        <html:text name="Form" property="sequenciaApresentacao" onkeyup="checaInteiro(this)" maxlength="3" title="sequenciaApresentacao" size="5" />
                    </td>
                </tr>
                <tr><td  height="5"></td></tr>               
                <tr> 
                    <td style="text-indent:6px;">Disponibilidade:</td>
                    <td style="padding-left:3px;">
                        <html:select name="Form" property="disponibilidadeSelecionado" title="disponibilidadeSelecionado" styleClass="SELECT" style="width:50px">
                            <html:options collection="aDisponibilidadeVO" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td  align="right" colspan="2">
                        <acesso:controlHiddenItem nomeIdentificador="cli_iasa_gravar">
                            <img style="border:0px;" onClick="salvarSubAssunto(); return false"          src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"/>
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            <script>
                mostraDisponibilidadeAssociada();
                document.body.style.backgroundColor = '#ededed';
            </script>
        </form>
</acesso:controlHiddenItem>
</body>
