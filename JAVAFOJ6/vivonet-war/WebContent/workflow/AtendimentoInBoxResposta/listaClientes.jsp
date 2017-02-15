<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        parent.oculta_div();
        
        // Submeter o formulário automaticamente caso um único resultado seja encontrado:
        if (document.forms[0].clienteSelecionado && !document.forms[0].clienteSelecionado.length){
            atualizaIdPessoa();
            parent.dvResultados.style.display = 'none';
            document.forms[0].target = 'buscaCliente';
            document.forms[0].submit();
        }
    -->
</SCRIPT>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="grupoCRIForm"/>

<netui-template:template templatePage="./../../resources/jsp/CRMTemplateSemCabec.jsp">
    <netui-template:setAttribute value="FrontOffice - Atendimento" name="title"/>
    <netui-template:setAttribute value="Atendimento" name="modulo"/>
    <netui-template:section name="headerSection">
    <netui:exceptions showMessage="false"/>
    </netui-template:section>
    
    <script>
        function atualizaIdPessoa(nmPessoa){
            if (document.forms[0].clienteSelecionado.length){
                for(i=0; i < document.forms[0].clienteSelecionado.length; i++){
                    if (document.forms[0].clienteSelecionado[i].checked){
                        document.getElementById('valorPesquisa').value = document.forms[0].clienteSelecionado[i].value;
                        document.forms[0]['nmPessoa'].value=nmPessoa;
                    }
                }
            }else{
                document.getElementById('valorPesquisa').value = document.getElementById('clienteSelecionado').value;
                document.forms[0]['nmPessoa'].value=nmPessoa;
            }
        }
        
        function selecionaCliente(){
            valor = null;
            if (document.forms[0].clienteSelecionado.length){
                for(i=0; i < document.forms[0].clienteSelecionado.length; i++){
                    if (document.forms[0].clienteSelecionado[i].checked){
                        valor = document.forms[0].clienteSelecionado[i].value;
                    }
                }
            }else{
                valor = document.forms[0].clienteSelecionado.value;
            }
            if(valor == null){
                alert("Favor selecionar um Cliente!");
            } else {
                parent.dvResultados.style.display = 'none';
                parent.document.forms[0]['valorPesquisa'].value = document.forms[0]['nmPessoa'].value;
                parent.document.forms[0]['idPessoa'].value = valor;
            }
        }
    </script>
    
    <netui-template:section name="bodySection">
        <form>
            <html:hidden name="Form" property="grupoCRI.pesquisa.filtro.sgTipoPesquisa" value="CLIENTE"/>
            <html:hidden name="Form" property="grupoCRI.pesquisa.filtro.dsValor" styleId="valorPesquisa"/>
            <input type="hidden" name="nmPessoa">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <table class="tbl_bgGray" width="730">
                <tr>
                    <td align="center" id="textoPesquisa"></td>
                </tr>        
            </table>
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
            <logic:equal name="Form" property="grupoCRI.pesquisa.inErro" value="1">
                <script>
                    alert('Muitos resultados de retorno! Favor refinar a pesquisa!');
                </script>
            </logic:equal>
            <vivo:tbTable selectable="true" layoutWidth="720" layoutHeight="300" tableWidth="720" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                    <vivo:tbHeaderColumn align="center" width="5%"  type="string"></vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="35%" type="string">Nome</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="20%" type="string">Documento</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="20%" type="string">Nº Documento</vivo:tbHeaderColumn>
                    <vivo:tbHeaderColumn align="center" width="20%" type="string">Tipo</vivo:tbHeaderColumn>
                </vivo:tbHeader>        
                <vivo:tbRows scroll="auto">
                    <logic:iterate id="listaClientes" name="Form" property="grupoCRI.pesquisa.pessoasArray">
                        <vivo:tbRow key="Linha">
                            <vivo:tbRowColumn><input type="radio" class="radio" name="clienteSelecionado" value="<bean:write name="listaClientes" property="idPessoa"/>" onclick="atualizaIdPessoa('<bean:write name="listaClientes" property="nmPessoa"/>');" id="clienteSelecionado"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="listaClientes" property="nmPessoa"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="listaClientes" property="sgClassificacaoDocumento"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="listaClientes" property="nrDocumento"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="listaClientes" property="dsTipoPessoa"/></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
                </vivo:tbRows>
            </vivo:tbTable>    
            <table width="720">
                <tr>                
                    <td align="right">
                        <img id="btProsseguir" src="/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_prosseguir_over.gif';" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif';" style="cursor:pointer;border:none;" onclick="selecionaCliente(); return false;">
                        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
                    </td>
                </tr>
            </table>
            <script language="Javascript">
                if (document.forms[0].clienteSelecionado && document.forms[0].clienteSelecionado.length){
                    texto = "Foram encontrados múltiplos resultados. Selecione a opç&atilde;o desejada e clique em <b>Prosseguir</b>.";
                }else{
                    texto = "Não foi encontrado nenhum registro.";
                    document.getElementById('btProsseguir').style.display = "none";
                }
                document.getElementById('textoPesquisa').innerHTML = texto;
            </script>
        </form>
    </netui-template:section>
</netui-template:template>