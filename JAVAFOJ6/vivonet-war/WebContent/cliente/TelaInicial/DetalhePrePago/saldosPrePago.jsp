<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="detalhesSaldoForm"/>

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
                var dyniframe = document.getElementById("fraSubAbas");
                var pagina = "";
                if(nome=="btx01") pagina = "loadFatura.do";
                if(nome=="btx02") pagina = "loadDetalhada.do";
                if(nome=="btx03") pagina = "loadPagamento.do";
                if(nome=="btx04") pagina = "loadAjustes.do";
                if(nome=="btx05") pagina = "loadCobranca.do";
                if(nome=="btx06") pagina = "loadLinhaIntraGrupo.do";
                if(nome=="btx07") pagina = "loadEstimativa.do";
                dyniframe.src = pagina;
                
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

            }
            
        </script>
    </head>
    
    <script language="jscript" for="window" event="onload">
    if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
    </script>
    
    <body style="margin-left:5px; margin-right:5px; margin-top:5px;">
    <acesso:controlHiddenItem nomeIdentificador="cli_sapp_verpagina">
        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="735" layoutHeight="360" tableWidth="735" styleId="tableTitle" sortable="true">
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="center" width="435" type="string">Tipo de Saldo</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="125" type="string">Valor</vivo:tbHeaderColumn>                
                <vivo:tbHeaderColumn align="center" width="145" type="string">Data de Validade</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                <logic:empty name="error">
                    <logic:iterate id="itens" name="form" property="detalhesSaldoVO.detalhesSaldoItemArray">                
                        <vivo:tbRow key="Linha">
                            <vivo:tbRowColumn><bean:write name="itens" property="tipoSaldo"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="itens" property="saldo" /></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="itens" property="prValidade"/></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                </logic:empty>
                <logic:notEmpty name="error">
                    <vivo:tbRow key="Linha">
                        <td colspan="3"><bean:write name="error"/></td>
                    </vivo:tbRow>
                </logic:notEmpty>
                <!-- fim -->
            </vivo:tbRows>
        </vivo:tbTable>

	</acesso:controlHiddenItem>
    </body>
</html>
