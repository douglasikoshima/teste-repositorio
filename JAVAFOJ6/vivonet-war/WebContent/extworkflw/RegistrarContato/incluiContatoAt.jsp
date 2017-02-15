<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="abaContato" />
<bean:define id="Tipos" name="Form" property="listaTipos.tipoComunicacaoVOArray"/>

<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script language="javaScript">
        function salvar(tipo){
            if (valida()!="TRUE"){
                alert(valida());            
            }else{
                document.getElementById("dsContato").value = trim(document.getElementById("dsContato").value);
                document.forms[0].action="salvarContato.do?tipo="+tipo;
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();  
                document.forms[0].submit();
            }
        }
    
        function escolheMascara(obj){
            tipo = document.forms[0].elements["idTipoSelecionado"].options[document.forms[0].elements["idTipoSelecionado"].selectedIndex].value;
            <logic:iterate id="tipoVO" name="Form" property="listaTipos.tipoComunicacaoVOArray" indexId="idx">
                <logic:equal name="tipoVO" property="idFormaRetorno" value="2">
                    if (tipo == '<bean:write name="tipoVO" property="idTipoComunicacao"/>')
                        qtdeCaracteres = 50;
                </logic:equal>
                <logic:notEqual name="tipoVO" property="idFormaRetorno" value="2">
                    if (tipo == '<bean:write name="tipoVO" property="idTipoComunicacao"/>'){ //Telefone Recado
                        //checaTelefone(obj);
                    	maskPhoneNumberObj(obj);
                        qtdeCaracteres = 14;
                    }            
                </logic:notEqual>
            </logic:iterate>
            obj.maxLength = qtdeCaracteres;
        }
        
        function valida(){    
            tipo = document.forms[0].elements["idTipoSelecionado"].options[document.forms[0].elements["idTipoSelecionado"].selectedIndex].value;
            obj = document.getElementById("dsContato");
            <logic:iterate id="tipoVO" name="Form" property="listaTipos.tipoComunicacaoVOArray" indexId="idx">
                <logic:equal name="tipoVO" property="idFormaRetorno" value="2">
                    if (tipo == '<bean:write name="tipoVO" property="idTipoComunicacao"/>'){       // E-mail
                        if(trim(obj.value) == ""){
                            return("Favor preencher um email!");
                        }else if(!validaEmail(obj.value)){
                            return("Email invalido, favor corrigir!");
                        }else{
                            return("TRUE");
                        }
                    }
                </logic:equal>
                <logic:notEqual name="tipoVO" property="idFormaRetorno" value="2">
                    if (tipo == '<bean:write name="tipoVO" property="idTipoComunicacao"/>'){       // E-mail
                        if(trim(obj.value) == ""){
                            return("Favor preencher um Telefone!");
                        }else if(obj.value.length < 12){
                            return("Telefone invalido, favor corrigir!");
                        }else{
                            return("TRUE");
                        }
                    }
                </logic:notEqual>
            </logic:iterate>
            
        }
    
        function checkTipoContato(obj) {
            <%
            ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
            %>
            if (obj.options[obj.selectedIndex].text == "CELULAR"
                && top.frameApp.idRelacionamento != 6){
                document.forms[0]["contato.dsContato"].value = '<%= parametros.getNrLinha() %>';
                document.forms[0]["contato.nrSequencia"].value = '';
                escolheMascara(document.forms[0]["contato.dsContato"]);
                document.forms[0]["contato.dsContato"].readOnly = true;
                document.forms[0]["contato.nrSequencia"].readOnly = true;
            } else {
                document.forms[0]["contato.dsContato"].value = "";
                document.forms[0]["contato.nrSequencia"].readOnly = false;
                document.forms[0]["contato.dsContato"].readOnly = false;
            }
        }
    </script>
</head>
<body style="background-color:#E3ECF4;">    
        <form action="salvarContato.do" target="_parent" method="post">
            <html:hidden name="Form" property="idPessoa"/>
            <table border="0" cellspacing="10" cellpadding="0">
                <tr>
                    <td valign="top" width="80"><b>Tipo:</b></td>
                    <td>
                        <html:select name="Form" style="width:250px;text-indent:3px;" property="idTipoSelecionado" onchange="checkTipoContato(this)">
                            <html:options collection="Tipos" property="idTipoComunicacao" labelProperty="dsTipoComunicacao"/>
                        </html:select>
                    </td>
                </tr>             
                <tr> 
                    <td valign="top"><b>Descrição:</b></td>
                    <td>
                        <html:text name="Form" property="contato.dsContato" maxlength="255" style="width:350px;" styleId="dsContato" onkeyup="escolheMascara(this)" onkeydown="escolheMascara(this)"/>
                    </td>
                </tr>
                <tr> 
                    <td>Seqüência Contato:</td>
                    <td>
                        <html:text name="Form" property="contato.nrSequencia" size="5" maxlength="2" onkeyup="checaInteiro(this)"/>
                    </td>                    
                </tr>
                <tr>
                    <td align="right" colspan="2">                    
                        <img style="border:none;" onclick="salvar('novo')" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"/>                    
                    </td>
                </tr>
            </table>
        </form>
        <script>
            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            
            //Apenas se nao for prospect
            if (top.frameApp.idRelacionamento != 6) {
                document.forms[0]["contato.dsContato"].value = '<%= parametros.getNrLinha() %>';
                escolheMascara(document.forms[0]["contato.dsContato"]);
                document.forms[0]["contato.dsContato"].readOnly = true;
                document.forms[0]["contato.nrSequencia"].readOnly = true;
            
            } else {
                var d = document.forms[0].idTipoSelecionado;
                for (i=0; i<d.length;i++) {
                    if (d.options[i].text == "CELULAR") {
                        d.options.remove(i);
                        break;
                    }
                }
            }
        </script>  
    
</body>