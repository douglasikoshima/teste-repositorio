<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:verifySession/>
<netui-template:template templatePage="./../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Menu Principal"/>
    <netui-template:setAttribute name="modulo" value="Administração de Sistemas"/>

    <netui-template:section name="headerSection">
        <link href="<%=request.getContextPath()%>/resources/css/esacStyle.css" type="text/css" rel="stylesheet"/>
        
    </netui-template:section>

    <netui-template:section name="bodySection">    

        <vivo:body idTable="tbMain" idDiv="dvMain" height="520" width="768">
            <vivo:quadro id="qdMain" height="490" width="758">

            <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tbl_bgGray">
                <tr align="center">
                    <td class="tbl_titulo">Menu da Administração de Sistemas</td>
                </tr>
                <tr>
                    <td>
                        <table width="100%" cellspacing="2" cellpadding="2" align="center">
                            <tr>
                                <td class="tblEstatica_campoNome" width="50%">
                                    <netui:label value="Árvore de Contatos"/>
                                </td>
                                <td class="tblEstatica_campoNome" width="50%">
                                    <netui:label value="Workflow"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="tblEstatica_campoValor" width="50%">
                                    <netui:anchor href="admArvore/indexAdmArvore.jsp">Manter Àrvore de Contatos</netui:anchor>
                                </td>
                                <td class="tblEstatica_campoValor" width="50%">
                                    <netui:anchor href="admWorkflow/indexAdmWorkflow.jsp">Manter Workflow</netui:anchor>
                                </td>
                            </tr>
                        </table>                    
                    </td>
                </tr>
            </table>
            
            </vivo:quadro>
        </vivo:body>
    </netui-template:section>
</netui-template:template>
