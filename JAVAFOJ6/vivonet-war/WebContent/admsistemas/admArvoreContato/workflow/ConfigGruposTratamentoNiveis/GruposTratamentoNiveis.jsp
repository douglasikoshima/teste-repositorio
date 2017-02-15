<!--
Módulo.....: Gestão de Sistemas
Caso de Uso: Configurar Grupo de Usuário com Níveis Workflow
Versão.....: $Author: a5112274 $ - $Revision: 1.1 $ - $Date: 2011/04/25 19:36:33 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposTratamentoNiveisForm" />
<bean:define id="aGruposTrat"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposTratamentoNiveisForm.gruposTratamentoArray" />
<link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>

<!--APLICACAO-->
<acesso:controlHiddenItem nomeIdentificador="adm_gtni_verpagina">    
    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="8"></div>
    
    <center>
    <vivo:quadro id="qdMain" height="350" width="740" label="&nbsp;Configurar Grupos em Tratamento com Níveis" scroll="no">
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="10"></div>
        
        <table width="720" cellpadding="0" cellspacing="0" align="center">
            <tr>
                <td>
                    <vivo:abaGrupo id="btAbaTratamento" width="720" height="10" styleClass="abatexto">
                    <acesso:controlHiddenItem nomeIdentificador="adm_gtni_abatra">
                        <vivo:abaItem id="btTratamento" onclick="exibicao(1);" value="Grupos Disponíveis em Tratamento" select="S"/>
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="adm_gtni_ababnive">
                        <vivo:abaItem id="btNiveis"     onclick="exibicao(0);" value="Configuração do Nível ao Grupo"/>
                    </acesso:controlHiddenItem>
                    </vivo:abaGrupo>
                </td>
            </tr>
        </table>
        <table width="720" height="310" cellpadding="0" cellspacing="10" style="border:1px solid adadad;" align="center">
            <tr>
                <td valign="top">
                
                    <!-- Div Tratamento -->
                    <div id="trTratamento" style="display:none;">
                        <html:select name="Form" property="grupoSelecionado" size="10" style="width:300px;height:215px;" onchange="consultaGrupo(this)">
                            <html:options collection="aGruposTrat" property="codigo" labelProperty="descricao"/> 
                        </html:select>
                    </div>
                    
                    <!-- Div Niveis -->
                    <div id="trNiveis" style="display:none;">
                        <iframe id="ifrmNiveis" frameborder="0" scrolling="auto" width="700" height="305" src="fundo.jsp"></iframe>
                    </div>
                </td>
            </tr>
        </table>
            
            
    </vivo:quadro>
    </center>
     <script language="javascript" for="window" event="onload">
        <!--   
            parent.oculta_div();
        -->
    </script> 
</acesso:controlHiddenItem>   
<script language=javascript>
    //Controle no click em tratamento
    var tratatamentoInicio;
    
    //Monta a exibição de tratamento
    exibicao(1);
    
    //Indicação de aguardando click em tratamento
    tratatamentoInicio = 0;

    //Controle da exibição
    function exibicao(index) {
    
        divNiveis        = document.getElementById('trNiveis');
        divTratamento    = document.getElementById('trTratamento');
    
        if( index == 0 ) {
            if( tratatamentoInicio == 0 ) return;
            
            abaSelected(btAbaTratamento, btNiveis);
            
            divTratamento.style.display = 'none';
            divNiveis.style.display     = '';

        } else {
            
            abaSelected(btAbaTratamento, btTratamento);
            
            divTratamento.style.display = '';
            divNiveis.style.display     = 'none';
        }
    }

    // consulta o grupo selecionado, obtendo a configuração atual dos níveis de tratamento
    function consultaGrupo(option) {
        //Start animação
        if( top.dvAnimarAguarde != null ) top.startAnimation();

        //Controle do click no tratamento
        tratatamentoInicio = 1;

        //Oculta o quadro tratamento
        exibicao(0);

        //Obtém o nome da celula
        var indexSelected = 0;
        
        for( indexSelected = 0; indexSelected < option.length; indexSelected++ )
            if( option[indexSelected].selected ) break;
        
        //Monta o nome do grupo no quadro 
        btNiveis.innerHTML = "&nbsp;Configuração do Nível ao Grupo [" + option[indexSelected].innerText + "]";

        ifrmNiveis.location.replace("ConsultaGruposTratamentoNiveis.do?codigoGrupo=" + option.value + "&idContato=<%=request.getParameter("idContato")%>");
    }

    //Fim animação
    if( top.dvAnimarAguarde != null ) top.stopAnimation();
    
    document.body.style.backgroundColor = '#ededed';
    
</script>