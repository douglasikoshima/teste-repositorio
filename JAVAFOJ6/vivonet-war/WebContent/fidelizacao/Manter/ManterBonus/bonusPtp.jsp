<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="headerSection">
        <script language="JavaScript" src="./../../../resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="./../../../resources/scripts/rfidelizacaomanut.js" ></script>
        <script language="javascript">eval(zso); </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_bptp_verpagina">
    <vivo:body idTable="tbMain" idDiv="dvMain" height="600" width="780">
    <form action="ListaBonusPtp" name="listaDefDestinoForm" id="listaDefDestinoForm" method="post">
        <table width="100%">
            <tr>
                <td>
                <table width="100%">
                    <tr>
                        <td>
                            B?nus: <netui:textBox dataSource="{}" styleClass="textfield" size="60" defaultValue=""/>
                         <td valign="top">
                            Validade em dias:<br>
                                <netui:textBox  dataSource="{}" styleClass="textfield" size="10"  defaultValue="180"/>
                        </td>
                        <td width = "150" align="right">
                            <acesso:controlHiddenItem nomeIdentificador="fid_bptp_pesquisar">
                                <img class="button"
                                	 src="./../../../resources/images/bt_pesquisar_nrml.gif" 
	                                 onmouseup="document.forms[0].action='ListaBonusPtp.do?acao=consultar';document.forms[0].submit();" />
                            </acesso:controlHiddenItem>
                            <acesso:controlHiddenItem nomeIdentificador="fid_bptp_incluir">
                            	<img class="button"
                                	 src="./../../../resources/images/bt_pesquisar_nrml.gif" 
	                                 onmouseup="document.forms[0].action='ListaBonusPtp.do?acao=incluir';document.forms[0].submit();" />
                            </acesso:controlHiddenItem>
                        </td>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
            <tr>
                <td>
                <vivo:tbTable selectable="true" onRowClick="" layoutWidth="700" layoutHeight="200" tableWidth="700" styleId="tableTitle" sortable="true">
                <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="45%" type="string">Descrição</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Validade</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn> </vivo:tbHeader>
                <vivo:tbRows>
                <netui-data:repeater dataSource="{}">
                    <netui-data:repeaterHeader> </netui-data:repeaterHeader>
                    <netui-data:repeaterItem>
                        <vivo:tbRow key="linha1"> <vivo:tbRowColumn>
                        <netui:label value="{container.item[1]}" defaultValue="&nbsp;"/> </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                            <acesso:controlHiddenItem nomeIdentificador="fid_bptp_excluir">
                            	<img class="button"
                                	 src="./../../../resources/images/bt_icon_excluir.gif" 
	                                 onmouseup="document.forms[0].action=document.forms[0].action+'?acao=excluir';document.forms[0].submit();" />
	                                 <!-- passar valor=index do iterate -->
                            </acesso:controlHiddenItem>
                        </vivo:tbRowColumn> <vivo:tbRowColumn>
                        <acesso:controlHiddenItem nomeIdentificador="fid_bptp_alterar">
                        	<img class="button"
                                 src="./../../../resources/images/bt_icon_alterar.gif" 
	                             onmouseup="document.forms[0].action=document.forms[0].action+'?acao=editar';document.forms[0].submit();" />
	                             <!-- passar valor=index do iterate -->
                        </acesso:controlHiddenItem>
                        </vivo:tbRowColumn> </vivo:tbRow>
                    </netui-data:repeaterItem>
                    <netui-data:repeaterFooter> </netui-data:repeaterFooter>
                </netui-data:repeater>
                </vivo:tbRows> </vivo:tbTable>
                </td>
            </tr>
            <tr>
                <td>
                <table width="100%">
                    <tr>
                        <td></td>
                        <td align="right"></td>
                    </tr>
                </table>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="fid_bptp_gravar">
                    	<img class="button"
                             src="./../../../resources/images/bt_salvar_nrml.gif" 
	                         onmouseup="document.forms[0].action='ListaBonusPtp.do?acao=editar';document.forms[0].submit();" />
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_bptp_cancelar">
                        <img class="button"
                             src="./../../../resources/images/bt_cancelar_nrml.gif" 
	                         onmouseup="document.forms[0].action='ListaBonusPtp.do?acao=editar';document.forms[0].submit();" />
                    </acesso:controlHiddenItem>
                  </td>
            </tr>
        </table>
    </form>
    </vivo:body>
</acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>