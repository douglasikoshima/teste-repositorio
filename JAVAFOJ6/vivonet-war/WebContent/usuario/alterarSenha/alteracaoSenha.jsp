<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="Form"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="alterarSenhaForm"/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="Alteração de Senha" name="title"></netui-template:setAttribute>
    <netui-template:setAttribute value="Administração de Sistemas" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    </netui-template:section>
<netui-template:section name="bodySection">
    <logic:equal name="Form" property="inMenu" value="">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
    </logic:equal>
    <div><img width="1" height="5" src="<%=request.getContextPath()%>/resources/images/transp.gif"></div>
    <script>
        function enviaDados(){
            if(valida()){
                document.forms[0].target = '';
                document.forms[0].action = 'alterarSenha.do';
                //mostrar_div();
                document.forms[0].submit();
            }
        }
        
        function validatePwd(fieldvalue) {
            var errorMsg = "";
            if(fieldvalue.indexOf(" ") > -1) {
                errorMsg += "Senhas não podem conter espaços.\n";
            }
            var mRgx = new RegExp("^(?=.{7,9})((?=.*[A-Z])|(?=.*[a-z]))(?=.*[0-9])((?=.*\W)|(?=.*_)).*$");
            if(fieldvalue.match(mRgx)){
                if(fieldvalue.length<7) {
                     errorMsg += "A senha válida deve possuir pelo menos 7 (sete) caracteres e no máximo 9 (nove) caracteres.\n";
                }
                if(!(fieldvalue.match(/\d/))) {
                     errorMsg += "A senha válida deve conter pelo menos um número.\n";
                }
                if(!(fieldvalue.match(/[a-zA-Z]/))) {
                     errorMsg += "A senha válida deve conter pelo menos uma letra maiúscula ou minúscula.\n";
                }
                if(!(fieldvalue.match(/\W+/))) {
                     errorMsg += "A senha válida deve conter pelo menos um caracter especial - #,@,%,!\n";
                }
            }
            if(errorMsg != ""){
                msg = "Por favor, corrija os problemas com a sua senha e tente novamente.\n\n";
                alert(msg+errorMsg);
                return false;
            }
            return true;
        }
 
        function valida(){
            var form = document.forms[0];
            if(form.senhaAntiga.value == ''){
                alert('Senha Atual é um campo obrigatório.');
                return false;
            }else if(form.senhaNova.value == ''){
                alert('Nova Senha é um campo obrigatório.');
                return false;
            }else if(form.senhaNovaConfirma.value == ''){
                alert('Confirma Senha é campo obrigatório.');
                return false;
            }
            if(form.senhaNova.value != form.senhaNovaConfirma.value){
                alert('Confirmação de Senha difere da Nova Senha informada, tente novamente.');
                form.senhaNova.value            = '';
                form.senhaNovaConfirma.value    = '';
                form.senhaNova.focus();
                return false;
            }
            if(!validatePwd(form.senhaNova.value)){
                form.senhaNova.value            = '';
                form.senhaNovaConfirma.value    = '';
                form.senhaNova.focus();
                return false;
            }
            if(form.senhaNova.value == form.senhaAntiga.value){
                alert('A Nova Senha deve ser diferente da Senha Atual');
                form.senhaNovaConfirma.value = '';
                form.senhaNova.value = '';
                form.senhaNova.focus();
                return false;
            }
            if(form.senhaNova.value.length < 7) {
                alert('Senha invalida.\nA senha deverá conter no minimo 7 caracteres!');
                form.senhaNovaConfirma.value = '';
                form.senhaNova.value = '';
                form.senhaNova.focus();
                return false;
            }
            if(form.senhaNova.value.length > 9){
                alert('Senha invalida.\nA senha deverá conter no maxímo 9 caracteres!');
                form.senhaNovaConfirma.value = '';
                form.senhaNova.value = '';
                form.senhaNova.focus();
                return false;
            }
            return true;
        }

        function limpar(){
            var form = document.forms[0];
            form.senhaNova.value        = '';
            form.senhaAntiga.value      = '';
            form.senhaNovaConfirma.value= '';
            return false;
        }
        <logic:notEqual name="Form" property="inMenu" value="">
            function oculta_div(obj){
                idAnime.style.display="none";
            }

            function mostrar_div(obj){
                idAnime.style.display="block";
            }
        </logic:notEqual>
        function voltar(){
            document.forms[0].target = '';
            document.forms[0].action = '<%=request.getContextPath()%>/';
            document.forms[0].submit();
        }

        // funções para digitar somente letras, numeros e os caracteres "ponto,under line e traço"
        //JBernardes
        alphaNumeric_StrEspecias = new RegExp("[0-9a-zA-Z\._-]");
        alphaNumeric = new RegExp("[0-9a-zA-Z]");

        function checaCaracteresLogin(obj){
            valor = obj.value;
            for(i=0;i<valor.length;i++){
                if(!alphaNumeric_StrEspecias.test(valor.charAt(i))){
                    valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                    alert('Caracteres permitidos no Login são:\n de \"a\" até \"z\" maiúsculas ou minúsculas,números e os caracteres especias ponto,underline e hífen(\".\","\_"\ e \"-\")');
                    i = -1;
                }
            }
            obj.focus();
            obj.value = valor;
        }

        function checaCaracteresSenha(obj){
            //valor = obj.value;
            //for(i=0;i<valor.length;i++){
                //if(!alphaNumeric.test(valor.charAt(i))){
                    //valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                    //alert('Caracteres permitidos na Senha são:\n de \"a\" até \"z\" maiúsculas ou minúsculas e números!');
                    //i = -1;
                //}
            //}
            //obj.focus();
            //.value = valor;
        }
    </script>
    <vivo:sessao id="alteracao" height="470" width="790" label="Administração de Sistemas" operacoes="Alteração de Senha" scroll="no">
    <form action="<%=request.getContextPath()%>/usuario/alterarSenha/alterarSenha.do" id="alterarSenha" name="alterarSenha" method="POST">
        <html:hidden name="Form" property="idUser"/>
        <html:hidden name="Form" property="login"/>
            <table width="100%" height="400">
                <tr>
                    <td valign="middle">
                        <table class="tbl_bggray" align="center" width="350" height="200" cellpadding="5" cellspacing="0" border="0">
                            <tr>
                                <td align="center" valign="middle">
                                <table cellpadding="5" border="0">
                                    <tr>
                                        <td width="100"><b>Login:</b></td>
                                        <td><bean:write name="Form" property="login"/></td>
                                    </tr>
                                    <tr>
                                        <td><b>Senha Atual:</b></td>
                                        <td><html:password name="Form" property="senhaAntiga" maxlength="9" onkeydown="checaCaracteresSenha(this)" onkeyup="checaCaracteresSenha(this)"/></td>
                                    </tr>
                                    <tr>
                                        <td><b>Nova Senha:</b></td>
                                        <td><html:password name="Form" property="senhaNova" maxlength="9" onkeydown="checaCaracteresSenha(this)" onkeyup="checaCaracteresSenha(this)"/></td>
                                    </tr>
                                    <tr>
                                        <td><b>Confirma Senha:</b></td>
                                        <td><html:password name="Form" property="senhaNovaConfirma" maxlength="9" onkeydown="checaCaracteresSenha(this)" onkeyup="checaCaracteresSenha(this)"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="right">
                                            <img id="imgLimpar" style="border:none;cursor:hand" onClick="limpar(); return false" vspace="5" src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'"/>
                                            <img id="imgGravar" style="border:none;cursor:hand" onClick="enviaDados(); return false" vspace="5" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'"/>
                                        </td>
                                    </tr>
                                </table>

                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
    </form>
    <div id='idAnime'  style='display:none;z-index:2000 ;position:absolute; top:0px; left:0px; width:100%; height:100%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif); '>
        <table border='1' cellpadding='0' cellspacing='0' height="100%" width="100%">
            <tr>
                <td align="center" valign="middle">
                    <iframe frameborder="0" scrolling="no" height='63' width='81'  src='http:<%=request.getContextPath()%>/resources/images/carregar.html'></iframe>
                </td>
            </tr>
        </table>
    </div >
    <script language="javascript" for="window" event="onload">
        try{
            top.idAnime_PERFIL.style.display="none";
        }catch (e){}
        alert("A alteração de senha deve ser realizada conforme as instruções abaixo\n\n\t- Deve possuir letras, números e carateres especiais (#,@,%,!);\n\t- O tamanho deve ser de no mínimo 7 (sete) e no máximo 9 (nove) caracteres;\n\t- Não deve conter sequencias numericas (123, 654);\n\t- Não pode conter partes do Nome ou do Login;\n\nExemplo: grb@387");
    </script>
    <vivo:alert atributo="msgStatus" scope="request"/>
    <logic:equal name="Form" property="isSucesso" value="true">
        <script>
            fecharSessao();
        </script>
    </logic:equal>
        <br><br>
        <logic:equal name="Form" property="inMenu" value="">
            <img style="border:none;" onClick="voltar();"  src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif"/>
        </logic:equal>
    </vivo:sessao>
    </netui-template:section>
</netui-template:template>