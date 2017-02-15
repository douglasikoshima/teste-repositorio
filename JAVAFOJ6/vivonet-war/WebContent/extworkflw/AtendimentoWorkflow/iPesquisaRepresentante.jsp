<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm" />
<bean:define id="admPerguntaVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.admPerguntaVO" />
<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>
</head>
<body bgcolor="#ededed">
<form action="pesquisaSatisfacaoGravar.do" method="post" name="atendimentoForm">
    <table border="0" width="100%">
        <html:hidden name="Form" property="arrayIndex"/>
        <html:hidden name="Form" property="idAtendimento"/>
        <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="TEXTO">
           <tr>
               <td>
                   <bean:write name="admPerguntaVO" property="dsPergunta" />
               </td>
            </tr>
            <tr>
               <td>
                   <html:text name="Form" property="respostaPesqSatisfa" />
               </td>
           <tr>
        </logic:equal>
        <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="MEMO">
           <tr>
               <td>
                   <bean:write name="admPerguntaVO" property="dsPergunta" />
               </td>
            </tr>
            <tr>
               <td>
                   <html:textarea name="Form" property="respostaPesqSatisfa" onkeyup="checaTextarea(this, 500);"/>
               </td>
           <tr>
        </logic:equal>
        <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="COMBO BOX">
            <bean:define id="respostas" name="admPerguntaVO" property="admRespostaVOArray" />

            <tr>
                <td>
                    <bean:write name="admPerguntaVO" property="dsPergunta" />
                </td>
            </tr>
            <tr>
                <td>
                    <html:select name="Form" property="respostaPesqSatisfa" >
                        <html:option value="">-- Selecione --</html:option>
                        <html:options collection="respostas" property="idResposta" labelProperty="dsResposta" />
                    </html:select>
                </td>
            </tr>
        </logic:equal>
        <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="LIST BOX">
            <bean:define id="respostas" name="admPerguntaVO" property="admRespostaVOArray" />

            <tr>
                <td>
                    <bean:write name="admPerguntaVO" property="dsPergunta" />
                </td>
            </tr>
            <tr>
                <td>
                    <html:select name="Form" property="respostaPesqSatisfa" multiple="true" size="3" style="width:300px;">
                        <html:options collection="respostas" property="idResposta" labelProperty="dsResposta" />
                    </html:select>
                </td>
            </tr>
        </logic:equal>
        <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="CHECK BOX">
            <tr>
                <td><bean:write name="admPerguntaVO" property="dsPergunta" /></td>
            </tr>
            <tr>
                <td>
                    <table border="0">
                        <logic:iterate id="respostas" name="admPerguntaVO" property="admRespostaVOArray">
                            <tr>
                                <td>
                                    <html:multibox  name="Form" property="respostaPesqSatisfa" styleClass="radio">
                                        <bean:write name="respostas" property="idResposta" />
                                    </html:multibox>
                                </td>
                                <td>
                                    <bean:write name="respostas" property="dsResposta" />
                                </td>
                            </tr>
                        </logic:iterate>
                    </table>
                </td>
            </tr>
        </logic:equal>
        <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="RADIO BUTTON">
            <tr>
                <td><bean:write name="admPerguntaVO" property="dsPergunta" /></td>
            </tr>
            <tr>
                <td>
                    <table border="0">
                        <logic:iterate id="respostas" name="admPerguntaVO" property="admRespostaVOArray">
                            <bean:define id="idResposta" name="respostas" property="idResposta" />
                            <tr>
                                <td>
                                    <html:radio name="Form" property="respostaPesqSatisfa" value='<%="" + idResposta + ""%>' styleClass="radio" />
                                </td>
                                <td>
                                    <bean:write name="respostas" property="dsResposta" />
                                </td>
                            </tr>
                        </logic:iterate>
                    </table>
                </td>
            </tr>
        </logic:equal>
    </table>
</form>
<table border="0" width="100%">
    <tr align="right">    
        <td align="right">
        <logic:notEqual name="Form" property="numPergunta" value="-1">
            <vivo:botao id="botaoVoltar" width="25px" height="20px" value="Voltar" styleClass="btTemplate" onclick="voltar()"/>
        </logic:notEqual>
        </td>
        <td align="left" width="50px;">
            <vivo:botao id="botaoTransfere" width="25px" height="20px" value="Próxima" styleClass="btTemplate" onclick="validaCampo()"/>
        </td>    
    </tr>
</table>
<script>
    function checaTextarea(obj, tamanho){
        obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }

    <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="TEXTO">
    function validaCampo(){
        <logic:equal name="admPerguntaVO" property="inObrigatoria" value="1">
        if(document.forms[0].respostaPesqSatisfa.value == ""){
            alert("O campo deve ser preenchido!")
            return false;
        }
        </logic:equal>
        avancaQuest();
    }
    </logic:equal>

    <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="MEMO">
    function validaCampo(){
        <logic:equal name="admPerguntaVO" property="inObrigatoria" value="1">
        if(document.forms[0].respostaPesqSatisfa.value == ""){
            alert("O campo deve ser preenchido!")
            return false;
        }
        </logic:equal>
        avancaQuest();
    }
    </logic:equal>

    <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="COMBO BOX">
    function validaCampo(){
        <logic:equal name="admPerguntaVO" property="inObrigatoria" value="1">
        if(document.forms[0].respostaPesqSatisfa.selectedIndex == 0){
            alert("Escolha uma opção!");
            return false;
        }
        </logic:equal>
        avancaQuest();
    }
    </logic:equal>

    <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="LIST BOX">
    function validaCampo(){
        <logic:equal name="admPerguntaVO" property="inObrigatoria" value="1">
        for(i=0;i<document.forms[0].respostaPesqSatisfa.length;i++){
            if(document.forms[0].respostaPesqSatisfa[i].selected){
                avancaQuest();
                return false;
            }
        }
        alert("Escolha pelo menos uma opção!");
        return false;
        </logic:equal>
        avancaQuest();
    }
    </logic:equal>

    <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="RADIO BUTTON">
    function validaCampo(){
        <logic:equal name="admPerguntaVO" property="inObrigatoria" value="1">
        if(document.forms[0].respostaPesqSatisfa.length != null){
            for(i=0;i<document.forms[0].respostaPesqSatisfa.length;i++)
                if(document.forms[0].respostaPesqSatisfa[i].checked == true){
                    avancaQuest();
                    return false;
                }
        }else{
            if(document.forms[0].respostaPesqSatisfa.checked == true){
                avancaQuest();
                return false;
            }
        }
        alert("Escolha uma opção!");
        return false;
        </logic:equal>
        avancaQuest();
    }
    </logic:equal>

    <logic:equal name="admPerguntaVO" property="dsTipoApresentacaoPergunta" value="CHECK BOX">
    function validaCampo(){
        <logic:equal name="admPerguntaVO" property="inObrigatoria" value="1">
        if(document.forms[0].respostaPesqSatisfa.length != null){
            for(i=0;i<document.forms[0].respostaPesqSatisfa.length;i++)
                if(document.forms[0].respostaPesqSatisfa[i].checked == true){
                    avancaQuest();
                    return false;
                }
        }else{
            if(document.forms[0].respostaPesqSatisfa.checked == true){
                avancaQuest();
                return false;
            }
        }
        alert("Escolha pelo menos uma opção!")
        return false;
        </logic:equal>
        avancaQuest();
    }
    </logic:equal>

    function avancaQuest(){
        document.forms[0].action = "pesquisaSatisfacaoApresentacao.do";
        document.forms[0].submit();
    }

    function voltar(){
        document.forms[0].action = "pesquisaSatisfacaoVoltar.do";
        document.forms[0].submit();
    }

    //Inicio animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    document.body.focus();
</script>
</body>
</html>