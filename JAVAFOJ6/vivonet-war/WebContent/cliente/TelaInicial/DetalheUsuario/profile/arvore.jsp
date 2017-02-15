<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterDadosForm" />

    <netui-template:section name="headerSection">
        <script type="text/javascript">
            idSubassuntoFun = function(idSubassunto) {
                top.frameApp.mostrar_div();
                document.forms[0].action="loadAtributos.do?idSubassunto="+idSubassunto;
                document.forms[0].method = "POST";
                document.forms[0].submit();
            }
        </script>
    </netui-template:section>

    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="cli_arvo_verpagina">
    <form target="frameAtributos"  id="fCustomerProf" action="loadOutrosDados.do" method="post" name="manterDadosForm">
    <table width="300" cellpadding="5" height="100%">
        <tr>
            <td valign="top">

                <vivo:quadro id="arvCust" width="280" height="320" label="&nbsp;">
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td rowspan="4" valign="top"></td>
                            <td rowspan="4" valign="top">
                                <script>
                                    <!--
                                        var webFXTreeConfig = {
                                            rootIcon        : '/FrontOfficeWeb/resources/images/icon_folder_open.gif',
                                            openRootIcon    : '/FrontOfficeWeb/resources/images/icon_folder_close.gif',
                                            folderIcon      : '/FrontOfficeWeb/resources/images/icon_folder_open.gif',
                                            openFolderIcon  : '/FrontOfficeWeb/resources/images/icon_folder_open.gif',
                                            fileIcon        : '/FrontOfficeWeb/resources/images/icon_seta_right.gif',
                                            iIcon           : '/FrontOfficeWeb/resources/images/I.png',
                                            lIcon           : '/FrontOfficeWeb/resources/images/L.png',
                                            lMinusIcon      : '/FrontOfficeWeb/resources/images/Lminus.png',
                                            lPlusIcon       : '/FrontOfficeWeb/resources/images/Lplus.png',
                                            tIcon           : '/FrontOfficeWeb/resources/images/T.png',
                                            tMinusIcon      : '/FrontOfficeWeb/resources/images/Tminus.png',
                                            tPlusIcon       : '/FrontOfficeWeb/resources/images/Tplus.png',
                                            blankIcon       : '/FrontOfficeWeb/resources/images/blank.png',
                                            defaultText     : 'Tree Item',
                                            defaultAction   : 'javascript:void(0);',
                                            defaultBehavior : 'classic',
                                            usePersistence	: true
                                        };
                                        var tree = new WebFXTree('Dados Comportamentais');
                                        tree.setBehavior('classic');
                                        <%=(String)session.getAttribute("arvore")%>
                                    -->
                                </script>
                            </td>
                        </tr>
                    </table>
                </vivo:quadro>

            </td>
        </tr>
    </table>
    </form>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>