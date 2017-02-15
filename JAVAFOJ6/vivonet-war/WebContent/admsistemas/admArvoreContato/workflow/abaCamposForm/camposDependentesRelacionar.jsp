<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>


<bean:define id="FormFormulario"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario"/>
<bean:define id="arrayCamposDependentes"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.arrayGrupoCamposDependentes" type="br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument.AdmGrupoCamposDependentesVO[]" />
<bean:define id="camposExistentes"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.camposExistentes" />
<!--bean:define id="ArvoreCamposDependentes" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formFormulario.ArvoreCamposDependentes" /-->

<netui-template:template templatePage="/resources/jsp/templateAdmSistClean.jsp">

<netui-template:section name="bodySection">
<!--acesso:controlHiddenItem nomeIdentificador="adm_vcf_verpagina"-->

    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript">

        var inOperacao             = "";
        var flAlteracao            = false;
        //var qtdeNiveis             = <bean:write name="FormFormulario" property="qtdeNiveis" />;
        var qtdeNiveis             = 20;
        var idItemSelecionado      = "";
        var idItemPai              = "";
        var nrNivelItemSelecionado = "";
        var inItemAtivo            = "";                                    // "ARVORE" ou "CAMPODEPENDENTE"
        var idFirstParent          = "";
        var idItemUnico            = "";

        function excluirItem() {
            if (inItemAtivo == "" || inItemAtivo == "CAMPODEPENDENTE") {
                alert("Selecione um item da árvore de campos dependentes para exclusão de relacionamento.");
                return false;
            }
            var f = document.forms[0];
            if (idItemSelecionado != "" && nrNivelItemSelecionado != "" && nrNivelItemSelecionado != 1) {
                f.inOperacao.value = "EXCLUIRCAMPO";
                f.action = "camposDependentes.do?idItemUnico=" + idItemUnico + "&nrNivelItemSelecionado=" + nrNivelItemSelecionado + "&idItemPai=" + idItemPai + "&idFirstParent=" + idFirstParent;
                f.submit();
            }
        }

        function salvar(){
            if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
            var f = document.forms[0];
            f.inOperacao.value = "GRAVARARVORE";
            f.submit();
        }

        function associarCampo() {

            var f = document.forms[0];
            var qtdeSelecionados = 0;

            for (i = 0; i < $('aCamposExistentes').options.length; i++)
                if ($('aCamposExistentes').options[i].selected == true)
                    qtdeSelecionados++;

            if (qtdeSelecionados == 0) {
                alert('Selecione ao menos um valor para associação \nà árvore de campos dependentes.');
            } else {
                if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
                f.inOperacao.value = "ASSOCIARCAMPO";
                f.action = "camposDependentes.do?idItemUnico=" + idItemUnico;
                f.submit();
            }
        }
        

        function getCamposDependentes(idPrimeiroPai, nrNivel, idCampo, idUnico, idPai, descricao) {

            if(idPrimeiroPai==null)idPrimeiroPai=parent.idPrimeiroPai;
            if(idPai==null)idPai=parent.idPai;
            if(nrNivel==null)nrNivel=parent.nrNivel;
            if(idCampo==null)idCampo=parent.idCampo;

            inItemAtivo = "ARVORE";
            idItemPai = idPai;
            idFirstParent = idPrimeiroPai;
            idItemUnico = idUnico;

            parent.idPrimeiroPai = idPrimeiroPai;
            parent.idPai         = idPai;
            parent.nrNivel       = nrNivel;

            //if (nrNivelItemSelecionado != nrNivel && idItemSelecionado != idCampo) {

                idItemSelecionado      = idCampo;
                nrNivelItemSelecionado = nrNivel;

                $('spanCampoDependente').innerHTML = "";
                while ($('aCamposExistentes').length > 0) {
                    $('aCamposExistentes').options[0] = null;
                }

                if (nrNivel == qtdeNiveis) {
                    //alert("Este valor faz parte do último nível de campos disponível.");
                } else {
                    if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
                    new Ajax.Request('getCamposDependentes.do', {
                        method: 'get',
                        parameters: {
                            nrNivel: nrNivel, limit: 12,
                            idCampo: idCampo, limit: 12,
                            idUnico: idUnico, limit: 12,
                            descricaoPai: descricao, limit: 200
                        },
                        contentType: 'text/xml',
                        onComplete: function (originalRequest) {
                            var objRaiz, idCampo, nmCampo;
                            var xmlString = originalRequest.responseText;
                            var oXml      = new ActiveXObject("microsoft.xmldom");
                            oXml.async    = false;
                            var regExp    = new RegExp ('&', 'gi') ;
                            xmlString     = xmlString.replace(regExp,"&amp;");
                            oXml.loadXML(xmlString);

                            if (oXml.selectSingleNode("/xml-fragment/friendlyMessage") != null) {

                                /*exceptionMessage = oXml.selectSingleNode("/xml-fragment/exceptionMessage").text;
                                friendlyMessage  = oXml.selectSingleNode("/xml-fragment/friendlyMessage").text;

                                top.frameApp.$('errTitle').innerHTML = "Erro";
                                top.frameApp.$('errCode').innerHTML = "";
                                top.frameApp.$('errMsg').innerHTML = friendlyMessage;
                                top.frameApp.$('errDetails').value = exceptionMessage;
                                top.frameApp.$('dvAnimarAguardeErro').style.display = "";*/

                            } else {

                                objRaiz = oXml.getElementsByTagName("AdmCampoVO");
                                nmCampo = oXml.selectSingleNode("/xml-fragment/AdmGruposCamposDependentesVO/AdmGrupoCamposDependentesVO/nmCampoDependente").text;
                                parent.idCampo = oXml.selectSingleNode("/xml-fragment/AdmGruposCamposDependentesVO/AdmGrupoCamposDependentesVO/idCampoDependente").text;

                                $('spanCampoDependente').innerHTML = nmCampo;
                                if (objRaiz) {
                                    for (i=0; i < objRaiz.length; i++ ) {
                                        id      = objRaiz[i].childNodes[0].text;
                                        idCampo = objRaiz[i].childNodes[1].text;
                                        nmCampo = objRaiz[i].childNodes[2].text;
                                        dsPai   = objRaiz[i].childNodes[3].text;
                                        //idPai   = objRaiz[i].childNodes[4].text;
                                        $('aCamposExistentes').options[$('aCamposExistentes').options.length] = new Option(nmCampo, idCampo + "|" + nmCampo + "|" + id + "|" + idPai);
                                    }
                                }
                            }
                            if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
                        }, onException: function(e){
                            if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
                        }
                    });
                }
            //}
        }

    </script>

    <script language="javascript" for="window" event="onload">
        if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.stopAnimation();
        document.body.style.backgroundColor = '#ededed';

        <% if(null!=request.getAttribute("parametrosIdUnico")&&!ConstantesCRM.SVAZIO.equals(request.getAttribute("parametrosIdUnico"))){ %>
            getCamposDependentes(null,null,null,<%=request.getAttribute("parametrosIdUnico")%>, null);
        <% } %>

    </script>

    <form action="camposDependentes.do" onSubmit="return false" method="post">
    <html:hidden name="FormFormulario" property="idContato"/>
    <html:hidden name="FormFormulario" property="inOperacao"/>

    <table width="700" border="0" cellspacing="0" cellpadding="0" align="center" style="margin:5px 0 0 10px;font-weight:bold;">
        <tr>
            <td colspan="3" valign="top" nowrap style="font-weight:normal;border-bottom:1px dotted #ccc">
                <span style="font-weight:bold;">Grupo:</span> 
                <bean:write name="FormFormulario" property="nmGrupoCampos" />
            </td>
        </tr>
        <tr>
            <td width="400" height="250" valign="top">
                <div id="divArvore" style="width:400px;height:240px;overflow:auto;margin:5px 5px 5px 0">
                    <script>
                        <% try { %>
                            <%=(String)request.getAttribute("arvore")%>
                        <% } catch(Exception ex) {} %>
                    </script>
                </div>
            </td>
            <td width="10" style="border-left:1px dotted #ccc">&nbsp;</td>
            <td width="290" valign="top">
                <table>
                    <tr>
                        <td colspan="2" style="font-weight:bold;">Nome do Campo Dependente: <span style="font-weight:normal" id="spanCampoDependente"></span></td>
                    </tr>
                    <tr>
                        <td valign="top">
                            <img id="btExcluirSimp" onMouseUp="excluirItem()" src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif" onmouseout="this.src='/FrontOfficeWeb/resources/images/bt_x_nrml.gif'" onmouseover="this.src='/FrontOfficeWeb/resources/images/bt_x_over.gif'" style="border:none;cursor:pointer;" />
                            <img id="btLeftSimp"  onMouseUp="associarCampo(); return false;"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" style="border:none;" />
                        </td>
                        <td valign="top">
                            <html:select name="FormFormulario" property="aCamposExistentes" styleId="aCamposExistentes" multiple="true" style="width:230px;height:228px" onfocus="inItemAtivo='CAMPODEPENDENTE';">
                                <html:options collection="camposExistentes" property="idCampo" labelProperty="nmCampo" />
                            </html:select>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td valign="top" colspan="3" align="right" style="border-top:1px dotted #ccc">
                <img vspace="3" id="btSalvar1" onClick="salvar();" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_gravar_over.gif'" border="0" style="cursor:pointer" />
            </td>
        </tr>
    </table>
    
    </form>

    <vivo:alert atributo="msg" scope="request" />

<!--/acesso:controlHiddenItem-->
</netui-template:section>
</netui-template:template>