<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<% String inDisponivelWF=(String)request.getParameter("disp");%>

<img id="btDisponivel" src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("1") ? "bt_disponivel_disponivel.gif" : "bt_disponivel_indisponivel.gif")%>' style="border:0px;"/>
<img id="btIndisponivel" src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("0") ? "bt_indisponivel_disponivel.gif" : "bt_indisponivel_indisponive.gif")%>' style="border:0px;"/>

<script>

    parent.document.getElementById('btDisponivel').src=document.getElementById('btDisponivel').src;
    parent.document.getElementById('btIndisponivel').src=document.getElementById('btIndisponivel').src;
    parent.document.forms[0]['inDisponivelWF'].value='<%=inDisponivelWF%>';
    if(top.frameApp.dvAnimarAguarde!=null){
        top.frameApp.stopAnimation();
    }
    
    //parent.abaSelecionada=1;
    //parent.exibicaoAbaInBox(1);
    parent.submitPesquisar();
    parent.exibicaoAbaPesquisas(0);
</script>