<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="FormGrupo"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm"/>
<bean:define id="canaisExist" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.canaisExistentesVO"/>
<bean:define id="canaisRelac" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaGruposForm.canaisRelacionadosVO"/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
    <script language="javascript">
        function transfereSelecaoLista(listaOrigem, listaDestino){
            var i;
            for(i = 0; i<listaOrigem.options.length; i++)
                if(listaOrigem.options[i].selected)
                    // Trata a primeira posicao vazia do list, se houver
                    if ((listaDestino.options.length == 1) && (trim(listaDestino.options[0].text) == "")) {
                        listaDestino.options[0] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    } else {
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }

            for(i = listaOrigem.options.length-1; i>=0; i--)
                if(listaOrigem.options[i].selected)
                    listaOrigem.options[i] = null;
        }

        function salvar() {
           // if (document.forms[0].canaisRelacionados.options.length > 0){
                for ( i = 0; i < document.forms[0].canaisRelacionados.options.length; i++ )
                    document.forms[0].canaisRelacionados.options[i].selected = true;
                parent.mostrar_div();
                document.forms[0].submit();
          //  }else{
         //       alert("Favor selecionar um Canal Relacionado!")
         //   }
        }
    </script>
    <script FOR=window EVENT=onload LANGUAGE="JScript">
        parent.oculta_div();
        if('<bean:write name="FormGrupo" property="msgError" />' != ""){
            alert('<bean:write name="FormGrupo" property="msgError" />');
        }
    </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="usu_rgc_verpagina">
        <script>
            document.body.style.backgroundColor = '#ededed';
        </script>
        <form name="listaGruposForm" action="salvaRelCanal.do" method="post">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
            <table width="720" cellpadding="0" cellspacing="0" align="center">
                <tr>
                    <td width="310" align="center"><b>Canais existentes</b></td>
                    <td width="100"></td>
                    <td width="310" align="center"><b>Canais relacionados</b></td>
                </tr>
                <tr>
                    <td align="left" valign="top">
                        <html:select name="FormGrupo" property="canaisExistentes" multiple="true" style="width:310px;height:175px;" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);">
                            <html:options collection="canaisExist" property="idCanal" labelProperty="nmCanal" />
                        </html:select>
                    </td>
                    <td align="center">
                        <img id="btRightSimp" onclick="transfereSelecaoLista(document.listaGruposForm.canaisExistentes, document.listaGruposForm.canaisRelacionados);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_rightaln_over.gif'" style="border:none;cursor:hand" /><br><br>
                        <img id="btLeftSimp" onclick="transfereSelecaoLista(document.listaGruposForm.canaisRelacionados, document.listaGruposForm.canaisExistentes);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_leftaln_over.gif'"  style="border:none;cursor:hand"/>
                    </td>
                    <td align="left" valign="top">
                        <html:select name="FormGrupo" property="canaisRelacionados" multiple="true" style="width:310px;height:175px;" size="8" ondblclick="transfereSelecaoLista(document.listaGruposForm.canaisRelacionados, document.listaGruposForm.canaisExistentes);">
                            <html:options collection="canaisRelac" property="idCanal" labelProperty="nmCanal" />
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="3">
                    <acesso:controlHiddenItem nomeIdentificador="usu_rgc_canais">
                        <img id="btSalvar1" onClick="salvar(); return false" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand;" vspace="10" />
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            <script language="javascript" type="text/javascript">
                idGrupo = parent.document.forms[0].idGrupo.value;
                if (parent.contadorChamada==0 && document.forms[0].canaisExistentes.length==1 && document.forms[0].canaisRelacionados.length==1){
                    parent.contadorChamada = 1;
                    window.top.frameApp.location.href = "/FrontOfficeWeb/usuario/manterGrupo/manterGrupoParam/begin.do?idGrupo="+idGrupo+"&abaSource=bt01";
                    parent.abaSelected(parent.btAba, parent.bt01);
                }
            </script>
        </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>