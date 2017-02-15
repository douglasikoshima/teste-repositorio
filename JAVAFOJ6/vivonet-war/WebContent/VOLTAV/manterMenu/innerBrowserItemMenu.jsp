<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="FormManterMenu" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterMenu"/>
<bean:define id="aItemMenu"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formManterMenu.menuComboVO.itemMenuArray"/>

    <div id="inner_item_menu">
          <vivo:quadro width="745" height="110" id="qdrItemMenu" scroll="no" label="Item Menu">
            <table align="center" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="310" align="center" valign="top">
                        Existente<br>
                        <html:select name="FormManterMenu" property="idItemMenuExistArray" multiple="true" style="width:330px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idItemMenuExistArray, document.forms[0].idItemMenuAssocArray);">
                            <html:options collection="aItemMenu" property="idItemMenu" labelProperty="nmItemMenu" /> 
                        </html:select>
                    </td>
                    <td width="70" align="center" valign="bottom">
                        <img class="button" id="btRightSimp" onmouseup="transfereSelecaoLista(document.forms[0].idItemMenuExistArray, document.forms[0].idItemMenuAssocArray);" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif" border="0"/><br><br>
                        <img class="button" id="btLeftSimp" onmouseup="transfereSelecaoLista(document.forms[0].idItemMenuAssocArray, document.forms[0].idItemMenuExistArray);"src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_leftaln_over.gif" border="0"/>
                    </td>
                    <td width="310" align="center" valign="top">
                        Associada<br>
                        <html:select name="FormManterMenu" property="idItemMenuAssocArray" multiple="true" style="width:330px;" size="6" ondblclick="transfereSelecaoLista(document.forms[0].idItemMenuAssocArray, document.forms[0].idItemMenuExistArray);">
                        </html:select>
                    </td>
                    <tr>
                </table>                    
           </vivo:quadro>
    </div>
    
    <script>
        parent.document.getElementById('div_item_menu').innerHTML = document.getElementById('inner_item_menu').innerHTML;
        parent.parent.oculta_div();
    </script>