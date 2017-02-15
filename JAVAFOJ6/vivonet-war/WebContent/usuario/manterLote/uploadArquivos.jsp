<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<acesso:controlInitEnv/>
<bean:define id="Form"               name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterLoteFormBean"/>
<bean:define id="ListaIdDescricaoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterLoteFormBean.listaIdDescricaoVO"/>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Perfis"/>
    <netui-template:setAttribute name="modulo" value="Gestão de Usuários"/>
    <netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript" type="text/javascript">
            function upload() {
                f = document.forms[0];
                if (f["file"].value == ""){
                    alert('Selecione um arquivo para upload');
                }else if (f.idStatusLote.value == ""){
                    alert('Selecione um status a ser atribuído ao lote.');
                }else{
                    if (validaArquivoEntrada(f["file"].value)){
                        f.submit();
                    }
                }
            }

            function validaArquivoEntrada(pathArquivo) {
                var indiceBarra  = pathArquivo.lastIndexOf("\\");
                var inErro = 0;
                var length = 32;
                var dsArquivo    = pathArquivo.substring(indiceBarra+1, pathArquivo.length).toUpperCase();
                var arqRadical   = dsArquivo.substring(0,10);
                var arqData      = dsArquivo.substring(10,18);
                var arqSeparador = dsArquivo.substring(18,21);
                var arqCodigoSD  = dsArquivo.substring(21,28);
                var arqExtensao  = dsArquivo.substring(29,dsArquivo.length);

                if (dsArquivo.length != length)
                    inErro = 1;
                else if(arqRadical != "VIVONETSDU")
                    inErro = 1;
                else if(arqSeparador != "-SD")
                    inErro = 1;
                else if(arqCodigoSD.length != 7)
                    inErro = 1;
                else if(arqExtensao != "TXT")
                    inErro = 1;

                if (inErro==0){
                    var dataDDMMAAAA = arqData.substring(6,8)+"/"+arqData.substring(4,6)+"/"+arqData.substring(0,4);
                    if (!validaData(dataDDMMAAAA)){
                        inErro = 1;
                    }
                }

                if (inErro==1){
                    var msgErro  = 'A nomenclatura do arquivo de entrada deve obrigatoriamente \nseguir a seguinte máscara: \n\n';
                    msgErro     += 'VIVONETSDU20060925-SD1234567.TXT\n\nAs únicas informações variáveis ';
                    msgErro     += 'dentro do nome do arquivo são: \n';
                    msgErro     += '20060925 -> Data de geração no formato AAAAMMDD \n';
                    msgErro     += '1234567   -> Número do SD (deve conter 7 posições) \n';
                    alert(msgErro);
                    return false;
                }
                return true;
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <acesso:controlHiddenItem nomeIdentificador="usu_manlote_up_verpagina">
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="460" width="790" label="Manutenção de Lote" operacoes="Upload">
            <form action="uploadArquivo.do" enctype="multipart/form-data" method="post">
            <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="10"></div>
            <table width="760" border="0" cellspacing="1" cellpadding="1" align="center" class="tbl_bgGray">
                <tr>
                    <td style="padding:5px;" width="130" nowrap>Arquivo para upload</td>
                    <td width="100%" style="padding:5px;">
                        <html:file name="Form" property="file" style="width:300px;" />
                    </td>
                </tr>
                <tr>
                    <td style="padding:5px;">Status do lote</td>
                    <td style="padding-left:9px;">
                        <html:select name="Form" property="idStatusLote" style="width:100px;">
                            <html:option value="">-- Selecione --</html:option>
                            <html:options collection="ListaIdDescricaoVO" property="id" labelProperty="descricao"/>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right" style="padding-right:5px;">
                        <acesso:controlHiddenItem nomeIdentificador="usu_manlote_upload">
                        <img src="/FrontOfficeWeb/resources/images/bt_upload_nrml.gif" onMouseUp="upload()" style="border:none" />
                        </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            <vivo:alert atributo="msgStatus" scope="request" />
            <script language="javascript" for="window" event="onload">
                oculta_div();
            </script>
            </form>
        </vivo:sessao>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>