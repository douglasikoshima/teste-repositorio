<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript">
            function move(objOrigem, objDestino){
                for (i=0; i<objOrigem.options.length; i++){
                  if (objOrigem.options[i].selected){
                      objDestino.options[objDestino.options.length] = new Option(objOrigem.options[i].text,objOrigem.options[i].value);
                      objOrigem.options[i] = null;
                      i--;
                  }
                }
            }

            function moveTodos(objOrigem, objDestino){
                lenghtOrigem = objOrigem.options.length;
                lenghtDestino = objDestino.options.length;
                todosText = new Array();
                todosValue = new Array();
                for (i=0; i<objOrigem.options.length; i++){
                  todosText[i] = objOrigem.options[i].text;
                  todosValue[i] = objOrigem.options[i].value;
                }
                for (i=0; i<objDestino.options.length; i++){
                  todosText[objOrigem.options.length + i] = objDestino.options[i].text;
                  todosValue[objOrigem.options.length + i] = objDestino.options[i].value;
                }
                for (i=lenghtOrigem; i>=0; i--){
                  objOrigem.options[i] = null;
                }
                for (i=lenghtDestino; i>=0; i--){
                  objDestino.options[i] = null;
                }
                for (i=0; i<todosText.length; i++){
                    objDestino.options[i] = new Option(todosText[i],todosValue[i]);
                }
            }

            function salvar(obj){
                if(document.forms[0].elements["wlw-select_key:{actionForm.idRegional}"].value == ""){
                    alert("Favor selecionar uma Regional!");
                }else{
                    valor = obj.href.split("?");
                    valor[1]?action = document.forms[0].action + "?" + valor[1]:action = document.forms[0].action;
                    anchor_submit_form("Netui_Form_0",action);
                }
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_mb_verpagina">
    <vivo:body idTable="tbMain" idDiv="dvMain" height="500" width="780">
    <form name="ActionMatrizBonus" action="ActionMatrizBonus.do" method="post">
        <table width="100%">
            <tr>
                <td width="30%">
                    Regional:<BR>
                    <select multiple="true" size="7" style="width:240px;"></select>
                </td>
            </tr>
        </table>
        <vivo:quadro id="qdMain" height="290" width="760" label="">
        <table width="100%">
            <tr>
                <td>
                <table>
                    <tr>
                        <td >
                        Bonus Disponíveis<br>
                        <select multiple="true" size="7" style="width:240px;" onDblClick="move('','')"></select>
                        </td>
                        <td>
                        <table>
                            <tr>
                                <td>
                                <img class="button" src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" border="0" onmouseup="move('','')" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                <img class="button" src="./../../../resources/images/bt_right_nrml.gif" border="0" onmouseup="moveTodos('','')" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_leftaln_nrml.gif" border="0" onmouseup="moveTodos('','')" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                <img src="/FrontOfficeWeb/resources/images/bt_left_nrml.gif" border="0" onmouseup="move('','')" />
                                </td>
                            </tr>
                        </table>
                        <td>
                        Bonus Selecionados<br>
                        <select multiple="true" size="7style="width:240px;" onDblClick="move('','')"></select>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
        </table>

        <table width="100%">
            <tr valign="top">
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="fid_mb_gravar">
                        <img class="button" src="./../../../resources/images/bt_salvar_nrml.gif" border="0" onmouseup="salvar(); return false" />
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_mb_cancelar">
                        <img class="button" src="./../../../resources/images/bt_cancelar_nrml.gif" border="0" onmouseup="window.location.href='ActionMatrizBonus.do';" />
                    </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        </vivo:quadro>
    </form>
    </vivo:body>

</acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>