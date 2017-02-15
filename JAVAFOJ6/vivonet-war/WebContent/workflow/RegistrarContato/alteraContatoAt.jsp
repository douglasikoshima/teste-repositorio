<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="abaContato" />

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
                document.getElementById("dsComunicacao").value = trim(document.getElementById("dsComunicacao").value);
                document.forms[0].action="salvarContato.do?tipo="+tipo;	    
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                document.forms[0].submit();
	        }
        }
        
        function escolheMascara(obj){
            tipo = document.getElementById("idFormaRetorno").value;
            if (tipo == 2){ //Email        
                qtdeCaracteres = 50;            
            } else {        //Telefone
                //checaTelefone(obj);
				maskPhoneNumberObj(obj);
                qtdeCaracteres = 14;
            }
            obj.maxLength = qtdeCaracteres;
        }
    
        function valida(){
            tipo = document.getElementById("idFormaRetorno").value;
            obj = document.getElementById("contato.dsContato");
            
            if (tipo == 2){          //Email
                if(trim(obj.value) == ""){
                    return("Favor preencher um email!");
                }else if(!validaEmail(obj.value)){
                    return("Email invalido, favor corrigir!");
                }else{
                    return("TRUE");
                }            
            } else {    //Telefone Comercial
                if(trim(obj.value) == ""){
                    return("Favor preencher um Telefone!");
                }else if(obj.value.length < 12){
                    return("Telefone invalido, favor corrigir!");
                }else{
                    return("TRUE");
                }
            } 
        }

        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    </script>
</head>
<body style="background-color:#E3ECF4;">
    <acesso:controlHiddenItem nomeIdentificador="wor_acat_verpagina">
        <form action="salvarContato.do" target="_parent" method="post">
            <html:hidden name="Form" property="idPessoa"/> 
            <html:hidden name="Form" property="idComunicacao" styleId="idContato"/>
            <html:hidden name="Form" property="contato.tipoComunicacaoVO.idFormaRetorno" styleId="idFormaRetorno"/>
            <html:hidden name="Form" property="dsComunicacao" styleId="dsComunicacao"/>
            <table border="0" cellspacing="10" cellpadding="0">
                <tr>
                    <td><b>Tipo:</b></td>
                    <td>
                        <html:text name="Form" property="contato.tipoComunicacaoVO.dsTipoComunicacao" size="70" styleId="dsTpContato" readonly="true"/>
                    </td>
                </tr>             
                <tr> 
                    <td><b>Descrição:</b></td>
                    <td>
                        <html:text name="Form" property="contato.dsContato" size="70" maxlength="255" styleId="dsComunicacao" onkeyup="escolheMascara(this)" onkeydown="escolheMascara(this)"/>                        
                    </td>                    
                </tr>                  
                <tr> 
                    <td>Seqüência Contato:</td>
                    <td>
                        <html:text name="Form" property="contato.nrSequencia" size="5" maxlength="2" onkeyup="checaInteiro(this)"/>
                    </td>                    
                </tr>           
                <tr>
                    <td  align="right" colspan="2">
                    <acesso:controlHiddenItem nomeIdentificador="wor_ac_gravar">
                        <img style="border:0px;" onclick="salvar('alteracao')" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>                   
       </form>
    </acesso:controlHiddenItem>
</body>