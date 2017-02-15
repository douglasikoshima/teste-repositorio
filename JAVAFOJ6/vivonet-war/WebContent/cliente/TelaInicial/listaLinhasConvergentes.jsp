<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="FormPesquisa" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="pesquisaForm" />

<netui-template:template templatePage="./../../resources/jsp/CRMTemplateSemCabec.jsp">
  <netui-template:setAttribute value="FrontOffice - Atendimento" name="title" />
  <netui-template:setAttribute value="Atendimento" name="modulo" />
  <netui-template:section name="headerSection">
    <netui:exceptions showMessage="false"/>
      <script>
      onload = function() {
        if (top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
      }
      carregaTI = function() {
        var selecionado = null;
        for (i=0; i < document.forms[0].linhaSelecionada.length; i++) {
          if (document.forms[0].linhaSelecionada[i].checked) {
            selecionado = i;
          }
        }
        if (selecionado != null) {
          var idLinhaTelefonica = document.forms[0].linhaSelecionada[selecionado].value;
          var nrLinha = document.forms[0].linhaSelecionada[selecionado].accessKey;
          top.frameApp.fechaLupa();
          try {
            top.frameApp.pesquisaLinhaAjax(idLinhaTelefonica, nrLinha);
          } catch(e) {}

        } else {
          alert("Nenhuma linha foi selecionada.")
        }
      }
    </script>
  </netui-template:section>
  <netui-template:section name="bodySection">
    <html:form action="/cliente/TelaInicial/carregaTelaInicialXml.do">
      <input type="hidden" name="valor" value="<%=request.getAttribute("valor")%>">
      <input type="hidden" name="idPessoa" value="">
      <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
      <div style="background-color:#ededed;border:1px solid #adadad;padding:10px;margin:0 5px 5px 0;">
        Foram encontradas múltiplas linhas. Escolha a linha desejada e clique em <strong>Prosseguir</strong>.
      </div>
      <vivo:tbTable selectable="false" layoutWidth="750" tableWidth="750" layoutHeight="300" styleId="tableTitle" sortable="true">
        <vivo:tbHeader>
          <vivo:tbHeaderColumn align="center" width="10%"  type="string"></vivo:tbHeaderColumn>
          <vivo:tbHeaderColumn align="center" width="30%" type="string">Número da Linha</vivo:tbHeaderColumn>
          <vivo:tbHeaderColumn align="center" width="30%" type="string">Tipo da Linha</vivo:tbHeaderColumn>
          <vivo:tbHeaderColumn align="center" width="30%" type="string">Nome do Cliente</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows>
          <logic:iterate id="listaLinhas" name="FormPesquisa" property="linhasConvergentes.linhaArray">
            <vivo:tbRow key="linha">
              <vivo:tbRowColumn>
                <input type="radio" accesskey="<bean:write name="listaLinhas" property="nrLinha"/>" class="radio" name="linhaSelecionada" value="<bean:write name="listaLinhas" property="idLinhaTelefonica"/>" />
              </vivo:tbRowColumn>
              <vivo:tbRowColumn>
                <bean:write name="listaLinhas" property="nrLinha" />
              </vivo:tbRowColumn>              
              <vivo:tbRowColumn>
                <bean:write name="listaLinhas" property="dsTipoLinha" />
              </vivo:tbRowColumn>
              <vivo:tbRowColumn>
                <bean:write name="listaLinhas" property="nmPessoa" />
              </vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
        </vivo:tbRows>        
      </vivo:tbTable>
    
      <table width="780">
        <tr>
          <td align="right">
            <img src="/FrontOfficeWeb/resources/images/bt_prosseguir_nrml.gif" style="cursor:pointer;" onclick="carregaTI()" id="btProsseguir" />
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
          </td>
        </tr>        
      </table>
    </html:form>

  </netui-template:section>
</netui-template:template>