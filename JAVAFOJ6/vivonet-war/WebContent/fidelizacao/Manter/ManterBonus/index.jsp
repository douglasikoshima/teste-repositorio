<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<netui-template:template templatePage="/resources/jsp/templateRetencao.jsp">
    <netui-template:section name="heaserSection">
        <script language="JavaScript" src="./../../../resources/scripts/frameweb.js" ></script>
        <script language="JavaScript" src="./../../../resources/scripts/rfidelizacaomanut.js" ></script>
        <script language="javascript">eval(zso); </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="fid_inxbun_verpagina">
    <vivo:body idTable="tbMain" idDiv="dvMain" height="600" width="780">

    <form action="ActionManterBonus.do" method="post">
        <table width="100%">
            <tr>
                <td>
                    <table width="100%">
                        <tr>
                            <td>
                                Procurar por: <netui:textBox dataSource="{actionForm.textoProcura}" styleClass="textfield" size="60" defaultValue=""/>
                                <acesso:controlHiddenItem nomeIdentificador="fid_inxbun_pesq">
                                    <img class="button"
                                    	 src="./../../../resources/images/bt_pesquisar_nrml.gif"
                                    	 onmouseup="document.forms[0].action='ActionManterBonus.do?acao=consultar';document.forms[0].submit();" />
                                </acesso:controlHiddenItem>
                            </td>
                            <td width="150" align="right">
                                <acesso:controlHiddenItem nomeIdentificador="fid_inxbun_incluir">
                                	<img class="button"
                                    	 src="./../../../resources/images/bt_pesquisar_nrml.gif"
                                    	 onmouseup="document.forms[0].action='ActionManterBonus.do?acao=incluir';document.forms[0].submit();" />
                                 </acesso:controlHiddenItem>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <%-- 
            <tr>
                <td>
                <vivo:tbTable selectable="true" onRowClick="" layoutWidth="700" layoutHeight="200" tableWidth="700" styleId="tableTitle" sortable="true">
	                <vivo:tbHeader>
		                <vivo:tbHeaderColumn align="left" width="45%" type="string">Descri??o</vivo:tbHeaderColumn>
		                <vivo:tbHeaderColumn align="left" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
		                <vivo:tbHeaderColumn align="left" width="5%" type="string">&nbsp;</vivo:tbHeaderColumn>
		            </vivo:tbHeader>
	                <vivo:tbRows>
                <netui-data:repeater dataSource="{pageFlow.lista}">
                    <netui-data:repeaterHeader> </netui-data:repeaterHeader>
                    <netui-data:repeaterItem>
                        <vivo:tbRow key="linha1"> <vivo:tbRowColumn>
                        <netui:label value="{container.item[1]}" defaultValue="&nbsp;"/> </vivo:tbRowColumn>
                        <vivo:tbRowColumn>
                        <acesso:controlHiddenItem nomeIdentificador="fid_inxbun_excluir">
                            <img src="./../../../resources/images/bt_icon_excluir.gif"
                            	 onmouseup="document.forms[0].action=document.forms[0].action='acao=excluir&valor=<%=c%>';document.forms[0].submit();"
                        </acesso:controlHiddenItem>
                        </vivo:tbRowColumn> <vivo:tbRowColumn>
                        <acesso:controlHiddenItem nomeIdentificador="fid_inxbun_editar">
                        	<img src="./../../../resources/images/bt_icon_editar.gif"
                            	 onmouseup="document.forms[0].action=document.forms[0].action='acao=edtar&valor=<%=c%>';document.forms[0].submit();"
                        </acesso:controlHiddenItem>
                        </vivo:tbRowColumn> </vivo:tbRow>
                    </netui-data:repeaterItem>
                    <netui-data:repeaterFooter> </netui-data:repeaterFooter>
                </netui-data:repeater>
                </vivo:tbRows> </vivo:tbTable>
                </td>
            </tr>
            --%>
        </table>
    </form>
    <div id="telaInsere" style="visibility:<%= request.getAttribute("valorDiv")%>">
        <table width="100%">
           <tr>
                <td >Descrição do Bonus:</td>
            </tr>
            <tr>
               <td valign="top" >
               <form action="ActionManterBonus.do" method="post">
                    <netui:textBox dataSource="{actionForm.descricao}" styleClass="textfield" size="60" defaultValue=""/>
                    <netui:hidden dataSource="{actionForm.id}"/>
                    <acesso:controlHiddenItem nomeIdentificador="fid_inxbun_gravar">
                        <img onmouseup="document.forms[0].action='ActionManterBonus.do?acao=ok';document.forms[0].submit()"
                        	 class="button"
                        	 src="./../../../resources/images/bt_salvar_nrml.gif" />
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="fid_inxbun_cancelar">
                        <img action="document.forms[0].action='ActionManterBonus.do?acao=cancelaDiv';document.forms[0].submit()"
                        	 class="button"
                        	 src="./../../../resources/images/bt_cancelar_nrml.gif" />
                    </acesso:controlHiddenItem>
                </form>
               </td>
            </tr>
        </table>
    </div>
    </vivo:body>
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
