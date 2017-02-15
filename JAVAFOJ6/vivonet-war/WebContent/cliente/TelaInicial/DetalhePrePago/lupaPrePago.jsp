<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script>
            function validaPesquisa() {
                if (document.getElementById("nrCont").value == "" && document.getElementById("nrLinh").value == "")
                    alert('Preencher ao menos um dos campos de busca!');
                else if (document.getElementById("nrLinh").value != "" && document.getElementById("nrLinh").value.length != 9 && document.getElementById("nrLinh").value.length != 10)
                    alert('O número da linha deve ter necessáriamente 9 ou 10 dígitos!');
                else {
                    document.forms[0].action = 'pesquisar.do';
                    document.forms[0].method = "POST";
                    document.forms[0].submit();
                }
            }

            function carregaLinhas(ndxConta, idConta) {
                document.forms[0].ndxContaSelecionada.value = ndxConta;
                document.forms[0].idContaSelecionada.value = idConta;
                document.forms[0].action = 'mostraLinhas.do';
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }

            function CarregaAba(nome){
                var dyniframe = document.getElementById("iframeprepago");
                var pagina = "";
                if(nome=="bt01") pagina = "/FrontOfficeWeb/cliente/TelaInicial/DetalhePrePago/begin.do";
                if(nome=="bt02") pagina = "loadHistoricoMovimentos.do";
                if(nome=="bt03") pagina = "loadExtrato.do";
                if(nome=="bt04") pagina = "loadSimulador.do";
                dyniframe.src = pagina;
            }

            function CheckMBRow(Obj) {
                qtElemForm = document.form.length;
                // Volta as outras linhas ao estado normal
                for (i=1; i<qtElemForm+1; i++){
                    carinha = document.getElementById(i);
                    carinha.parentNode.parentNode.className = 'normal';
                }
                // Depois de voltar as linhas, pinta a linha Selecionada
                Row = Obj.parentNode.parentNode;
                Chk = Obj.checked;
                Row.className = (Chk ? 'selecionada' : 'normal');
            }

        </script>
    </head>
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;" onload="abaSelected(btAba, bt01);CarregaAba('bt01');">

        <vivo:abaGrupo id="btAba" width="770" height="16" styleClass="abatexto">
            <acesso:controlHiddenItem nomeIdentificador="cli_lpp_abasaldo">
                <vivo:abaItem id="bt01" onclick="abaSelected(btAba, bt01);CarregaAba(this.id);" value="Saldo" select="S"/>
            </acesso:controlHiddenItem>
            <acesso:controlHiddenItem nomeIdentificador="cli_lpp_abamov">
                <vivo:abaItem id="bt02" onclick="abaSelected(btAba, bt02);CarregaAba(this.id);" value="Hist&oacute;rico de Movimentos"/>
            </acesso:controlHiddenItem>
            <%-- String strCod = ((ParametrosVO)session.getAttribute(ConstantesCRM.SPARAMETROS)).getNrCodArea();
             if(null != strCod && !strCod.substring(0,1).equals("1")){--%>
            <acesso:controlHiddenItem nomeIdentificador="cli_lpp_abaextrato">
                <vivo:abaItem id="bt03" onclick="abaSelected(btAba, bt03);CarregaAba(this.id);" value="Extrato/Comprovante de Servi&ccedil;os"/>
            </acesso:controlHiddenItem>
           <%--}--%>
            <acesso:controlHiddenItem nomeIdentificador="cli_lpp_abasimul">
                <vivo:abaItem id="bt04" onclick="abaSelected(btAba, bt04);CarregaAba(this.id);" value="Simulador de Planos"/>
            </acesso:controlHiddenItem>
        </vivo:abaGrupo>
        <table width="770" height="400" style="border-left:1px solid #adadad;border-right:1px solid #adadad;border-bottom:1px solid #adadad;">
            <tr>
                <td>
                    <iframe id="iframeprepago" width="762" height="399" src="" scrolling="no" marginheight="0" marginwidth="0" frameborder="0"></iframe>
                </td>
            </tr>
        </table>
    </body>
</html>
