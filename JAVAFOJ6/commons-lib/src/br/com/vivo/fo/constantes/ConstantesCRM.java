package br.com.vivo.fo.constantes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;

public class ConstantesCRM implements Serializable {

    private static final long             serialVersionUID                                      = -7775906191557419437L;

    // Elementos privados para tratamentos
    private static final SimpleDateFormat simpleDateFormat                                      = new SimpleDateFormat();
    // private static final Locale locale = new Locale("pt", "BR");
    // private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
    public static final String            TuxConnector                                          = "TuxConnector";

    // Padrão de data e hora do sistema
    public static final String            formatoDataHoraMinutoSegundo                          = "dd/MM/yyyy hh:mm:ss";
    public static final String            formatoDataHora                                       = "dd/MM/yyyy hh:mm";
    public static final String            formatoData                                           = "dd/MM/yyyy";
    public static final String            formatoHora                                           = "hh:mm";
    public static final String            formatoAnoMesDiaHoraMinutoSegundo                     = "yyyy-MM-dd HH:mm:ss";

    // HashMaps para objetos
    public static final String            CT_GrupoVO                                            = "CT_GrupoVO";
    public static final String            CT_TipoClienteVO                                      = "CT_TipoClienteVO";
    public static final String            CT_SegmentacaoVO                                      = "CT_SegmentacaoVO";
    public static final String            CT_CarterizacaoVO                                     = "CT_CarterizacaoVO";
    public static final String            CT_ProcedenciaVO                                      = "CT_ProcedenciaVO";
    public static final String            CT_TipoCarteiraVO                                     = "CT_TipoCarteiraVO";
    public static final String            CT_TipoPessoaVO                                       = "CT_TipoPessoaVO";
    public static final String            CT_TipoAberturaVO                                     = "CT_TipoAberturaVO";
    public static final String            CT_TipoLinhaVO                                        = "CT_TipoLinhaVO";
    public static final String            CT_CanalVO                                            = "CT_CanalVO";
    public static final String            CT_RegionalVO                                         = "CT_RegionalVO";

    // Controles possíveis em um formulário
    public static final String            CT_Text                                               = "TEXT";
    public static final String            CT_TextArea                                           = "TEXTAREA";
    public static final String            CT_Select                                             = "SELECT";
    public static final String            CT_Radio                                              = "RADIO";
    public static final String            CT_CheckBox                                           = "CHECKBOX";

    // Constantes Servie Houter
    public static final String            CD_PARAM_URL_SERVICE_ROUTER                           = "URL_SERVICE_ROUTER";
    public static final String            TIPO_LINHA_PRE_PAGO                                   = "2";
    public static final String            SERVICE_ROUTER_PRE                                    = "COMPSERV_M";
    public static final String            SERVICE_ROUTER_POS                                    = "EXTRATO_M";
    public static final String            TUX_USER_DEFAULT                                      = "2";

    // Constante perfil sessão
    public static final String            PERFIL_SESSION                                        = "usr_perfil_session";
    public static final String            PERFIL_USR_MIN                                        = "PERFIL_USR_MIN";

    // Constantes do workflow
    public static final int               CT_WF_SCRIPTDB_ABERTURA_CHAMADA_TELEFONICA            = 11;
    public static final int               CT_WF_SCRIPTDB_ABERTURA_INICIO                        = 1;
    public static final int               CT_WF_SCRIPTDB_ABERTURA_LINHAS                        = 2;
    public static final int               CT_WF_SCRIPTDB_ABERTURA_GRAVAR                        = 3;
    public static final int               CT_WF_SCRIPTDB_ABERTURA_GRAVAR_INSISTENCIA            = 4;
    public static final int               CT_WF_SCRIPTDB_ABERTURA_PESQUISA_COMUNICACAO          = 18;
    public static final int               CT_WF_SCRIPTDB_ABERTURA_PESQUISA_PROCESSOS_CORRENTES  = 83;
    public static final int               CT_WF_SCRIPTDB_ABERTURA_PESQUISA_TIPO_COMUNICACAO     = 164;
    public static final int               CT_WF_SCRIPTDB_ABERTURA_EXCLUIR_PROCESSO              = 183;
    public static final int               CT_WF_SCRIPTDB_ABERTURA_ENVIAR_PROCESSOS_CORRENTES    = 343;

    public static final int               CT_WF_SCRIPTDB_CTI_CHAMADA_TELEFONICA                 = 303;

    public static final int               CT_WF_SCRIPTDB_FILA_PESQUISA                          = 5;
    public static final int               CT_WF_SCRIPTDB_FILA_PESQUISA_SUBESTADO                = 6;
    public static final int               CT_WF_SCRIPTDB_FILA_PESQUISA_GRUPO                    = 13;
    public static final int               CT_WF_SCRIPTDB_FILA_ENCAMINHAR                        = 81;
    public static final int               CT_WF_SCRIPTDB_FORMULARIO_PESQUISA                    = 123;

    public static final int               CT_WF_SCRIPTDB_INBOX_PESQUISA                         = 7;
    public static final int               CT_WF_SCRIPTDB_INBOX_PESQUISA_USUARIO                 = 8;
    public static final int               CT_WF_SCRIPTDB_INBOX_DISPONIBILIDADE                  = 9;
    public static final int               CT_WF_SCRIPTDB_INBOX_DETALHE_ALERTA                   = 10;
    public static final int               CT_WF_SCRIPTDB_INBOX_PROXIMO_PROCESSO                 = 468;
    public static final int               CT_WF_SCRIPTDB_RELACIONAMENTO_PESQUISA_ESTADO         = 14;
    public static final int               CT_WF_SCRIPTDB_RELACIONAMENTO_PESQUISA_SUBESTADO      = 15;
    public static final int               CT_WF_SCRIPTDB_RELACIONAMENTO_PESQUISA_RELACIONAMENTO = 16;

    public static final int               CT_WF_SCRIPTDB_FECHAMENTO_MASSA_PESQUISA              = 21;
    public static final int               CT_WF_SCRIPTDB_FECHAMENTO_MASSA_PESQUISA_USUARIO      = 41;
    public static final int               CT_WF_SCRIPTDB_FECHAMENTO_MASSA_PESQUISA_DOC_TEC      = 103;

    public static final int               CT_WF_SCRIPTDB_DETALHE_PROCESSO_ACAO_CANCELAR         = 103;
    public static final int               CT_WF_SCRIPTDB_DETALHE_PROCESSO_ACAO_SUSPEITO         = 143;
    public static final int               CT_WF_SCRIPTDB_DETALHE_PROCESSO_ACAO_TESTES_PESQUISA  = 184;
    public static final int               CT_WF_SCRIPTDB_DETALHE_PROCESSO_ACAO_TESTES_GRAVAR    = 185;
    public static final int               CT_WF_SCRIPTDB_DETALHE_PESQUISA_SATISFACAO_PESQUISA   = 543;
    public static final int               CT_WF_SCRIPTDB_DETALHE_PESQUISA_SATISFACAO_GRAVAR     = 583;
    public static final int               CT_WF_SCRIPTDB_INBOX_CAMPANHA_RETORNO_PESQUISAR       = 223;

    public static final int               CT_WF_SCRIPTDB_DETALHE_PROCESSO_ACS                   = 229;

    public static final int               CT_WF_SCRIPTDB_DOCUMENTO_TEC_TIPOS_ESTADOS            = 205;
    public static final int               CT_WF_SCRIPTDB_DOCUMENTO_TEC_ASSOCIAR                 = 227;
    public static final int               CT_WF_SCRIPTDB_DOCUMENTO_TEC_ABERTURA                 = 228;
    public static final int               CT_WF_SCRIPTDB_DOCUMENTO_TEC_LISTAR                   = 231;
    public static final int               CT_WF_SCRIPTDB_DOCUMENTO_TEC_FECHAMENTO               = 232;

    public static final int               CT_WF_SCRIPTDB_MONITORAMENTO_GRUPOS                   = 165;
    public static final int               CT_WF_SCRIPTDB_MONITORAMENTO_PESQUISA                 = 166;

    public static final int               CT_WF_SCRIPTDB_CADASTRO_ADM_CAMPANHAS_PESQUISA        = 224;
    public static final int               CT_WF_SCRIPTDB_CADASTRO_ADM_CAMPANHAS_CONSULTAR       = 225;
    public static final int               CT_WF_SCRIPTDB_CADASTRO_ADM_CAMPANHAS_GRAVAR          = 226;

    public static final int               CT_CADASTRO_ADM_CAMPANHAS_PESQUISAR                   = 1;
    public static final int               CT_CADASTRO_ADM_CAMPANHAS_NOVO                        = 2;
    public static final int               CT_CADASTRO_ADM_CAMPANHAS_ALTERAR                     = 3;
    public static final int               CT_CADASTRO_ADM_CAMPANHAS_EXCLUIR                     = 4;
    public static final int               CT_CADASTRO_ADM_CAMPANHAS_VOLTAR                      = 5;
    public static final int               CT_CADASTRO_ADM_CAMPANHAS_NOVO_GRAVAR                 = 6;
    public static final int               CT_CADASTRO_ADM_CAMPANHAS_ALTERAR_GRAVAR              = 7;

    public static final int               CT_WF_SCRIPTDB_DETALHE_PROCESSO                       = 163;
    public static final int               CT_WF_SCRIPTDB_CORE_WORKFLOW                          = 230;
    public static final int               CT_WF_SCRIPTDB_OBTER_MOTIVOS_CANCELAMENTO             = 243;
    public static final int               CT_WF_SCRIPTDB_OBTER_MOTIVOS                          = 263;

    public static final int               CT_WF_SCRIPTDB_INDICADORES_GRUPOS                     = 444;
    public static final int               CT_WF_SCRIPTDB_INDICADORES_ESTADO_LINHA               = 463;
    public static final int               CT_WF_SCRIPTDB_INDICADORES_NATUREZA_CLIENTE           = 464;
    public static final int               CT_WF_SCRIPTDB_INDICADORES_SEGMENTACAO_CARTEIRA       = 465;
    public static final int               CT_WF_SCRIPTDB_INDICADORES_TIPO_PROCESSO              = 466;
    public static final int               CT_WF_SCRIPTDB_INDICADORES_RESUMO_ACOMPANHAMENTO      = 467;

    public static final int               CT_WF_SCRIPTDB_RELATORIOS_FILTROS                     = 383;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_FILTRO_REGIONAIS            = 403;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_FILTRO_REPRESENTANTES       = 483;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL1_REL2                   = 423;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL1_REL2_DETALHE           = 505;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL3                        = 503;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL4                        = 523;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_TIPO_1                 = 683;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_TIPO_2                 = 705;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_TIPO_3                 = 724;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_TIPO_4                 = 744;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_TIPO_5                 = 763;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_DETALHE_1              = 704;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_DETALHE_2              = 723;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_DETALHE_3              = 743;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_DETALHE_4              = 745;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL6_DETALHE_5              = 764;
    public static final int               CT_WF_SCRIPTDB_RELATORIOS_REL7                        = 783;

    public static final String            GENERATED_REPORTS_HOME                                = "/generated/reports";

    public static final String            USUARIO_LOGIN_CTI                                     = "usuarioLoginCTI";
    public static final String            USUARIO_LOGIN                                         = "usuarioLogin";
    public static final String            USUARIO_SITE                                          = "usuarioSite";
    public static final String            USUARIO_FORNECEDOR                                    = "usuarioFornecedor";
    public static final String            USUARIO_PERFIL                                        = "usuarioPerfil";
    public static final String            USUARIO_NOME                                          = "usuarioNome";
    public static final String            USUARIO_MENU                                          = "usuarioMenu";

    // Constantes de Campanha
    public static final String            CT_ARVORE_CAMPANHA                                    = "SC_ARV_CAM";
    public static final String            CT_AT_OPERACAO_EXECUTAR_CAMPANHA                      = "CT_AT_OP_EC";
    public static final String            SVAZIO                                                = "";

    // Constantes de Questionario
    public static final String            CT_QU_IDENTIFICADOR_ATENDIMENTO                       = "CT_QU_IDENA";

    public static final String            ADM_SESSION_FORM                                      = "formAdmSession";
    public static final String            SFORMERROR                                            = "formError";

    // Constantes de presentacao
    public static String                  STODOS                                                = "-- Todos --";
    public static String                  SNO                                                   = "Nao";
    public static String                  SYES                                                  = "Sim";
    public static String                  SOK                                                   = "OK";
    public static String                  SNOK                                                  = "NOK";
    public static String                  TELAINCIAL                                            = "/FrontOfficeWeb/begin.do";
    public static String                  FRAMEAPP                                              = "frameApp";
    public static String                  STRUE                                                 = "TRUE";
    public static String                  SFALSE                                                = "FALSE";
    public static String                  SPARAMETROS                                           = "parametros";
    public static String                  SACTION                                               = "acao";

    public static String                  SZERO                                                 = "0";
    public static String                  SONE                                                  = "1";
    public static String                  STWO                                                  = "2";
    public static String                  STHREE                                                = "3";
    public static String                  SFOUR                                                 = "4";
    public static String                  SFIVE                                                 = "5";
    public static String                  SSIX                                                  = "6";
    public static String                  SSEVEN                                                = "7";
    public static String                  SEIGHT                                                = "8";
    public static String                  SNINE                                                 = "9";

    public static String                  SContentType                                          = "text/xml;charset=ISO-8859-1";
    public static String                  SISO                                                  = "ISO-8859-1";
    public static String                  SUTF8                                                 = "UTF-8";

    public static final String            COD_CANAL_VOL                                         = "15";
    public static final String            COD_CANAL_TAV                                         = "13";
    public static final String            COD_CANAL_URA                                         = "09";
    public static final int               COD_CANAL_CRC                                         = 1;

    public static final int               COD_PROCEDENCIA_TELEFONE                              = 1;

    public static final String            COD_TPLINHA_POS                                       = "1";
    public static final String            COD_TPLINHA_PRE                                       = "2";
    public static final String            COD_TPLINHA_CON                                       = "4";

    public static final String            COD_TPSERV_PGCONTA                                    = "1";
    public static final String            COD_TPSERV_RECARGA                                    = "2";

    public static final int               ID_SISTEMA_ORIGEM_VIVONET                             = 7;

    /************ Fim ******************************************************/

    public static final String            COD_TECNOLOGIA_ESN                                    = "1";
    public static final String            COD_TECNOLOGIA_CHIP                                   = "2";

    public static final String            DES_TECNOLOGIA_TODAS                                  = "CDMA+GSM";
    public static final String            DES_TECNOLOGIA_ESN                                    = "CDMA";
    public static final String            DES_TECNOLOGIA_CHIP                                   = "GSM";

    public static final String            SSucesso                                              = "Operação realizada com sucesso.";
    public static final String            SUCCESS                                               = "success";
    public static final String            SERROR                                                = "error";

    public static final String            PASSWORD_RESET                                        = "654321";

    public static final String            NRPROTOCOLO                                           = "NRPROTOCOLO";
    public static final String            IN_TRAFEGO_DADOS_1005                                 = "IN_TRAFEGO_DADOS_1005";

    public static final String            ALSB3SOA_SECURITY_USER                                = "ALSB3SOA_SECURITY_USER";
    public static final String            ALSB3SOA_SECURITY_PASSWORD                            = "ALSB3SOA_SECURITY_PASSWORD";
    public static final String            PARAM_PATH_FILE_FO                                    = "PATH_FILE_FO";
    public static final String            SKEY_REQUEST_PAGEFLOW                                 = "com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow";

    public static final String getSoapHeader(String user, boolean getCabecalhoVivo) {

        String usuario = ConstantesCRM.SVAZIO;
        String senha = ConstantesCRM.SVAZIO;

        StringBuffer stringHeader = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("<SOAP-ENV:Header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">");

        if (getCabecalhoVivo) {
            stringHeader.append("<ger:cabecalhoVivo xmlns:ger=\"http://www.vivo.com.br/MC/Geral\">")
                    .append("    <ger:loginUsuario>VivoVivonet</ger:loginUsuario>")
                    .append("    <ger:canalAtendimento>1</ger:canalAtendimento>")
                    .append("    <ger:codigoSessao>1</ger:codigoSessao>")
                    .append("    <ger:nomeAplicacao>VivoVivonet</ger:nomeAplicacao>")
                    .append("    <ger:codigoFuncionalidade/>")
                    .append("</ger:cabecalhoVivo>");
        }

        try {
            usuario = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ALSB3SOA_SECURITY_USER).getDsValorParametro();
            senha = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ALSB3SOA_SECURITY_PASSWORD).getDsValorParametro();
        } catch (Exception e) {
        }

        if (!ConstantesCRM.SVAZIO.equals(usuario) && !ConstantesCRM.SVAZIO.equals(senha)) {
            stringHeader.append("<wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">")
                    .append("   <wsse:UsernameToken wsu:Id=\"UsernameToken-2914725\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">")
                    .append("      <wsse:Username>").append(usuario).append("</wsse:Username>")
                    .append("      <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">").append(senha).append("</wsse:Password>")
                    .append("   </wsse:UsernameToken>").append("</wsse:Security> ");
        }

        stringHeader.append("</SOAP-ENV:Header>");
        return stringHeader.toString();
    }

    /**
     * Monta mascara em um valor conforme informação da mascara
     * @param java
     *            .lang.String value O valor a se aplicar a mascara.
     * @param java
     *            .lang.String A mascara a ser aplicada no valor.
     * @return java.lang.String O valor com a aplicação da mascara
     */
    public static final String mask(String value, String mask) {

        if (value == null) {
            return null;
        } else if ((mask == null) || (mask.trim().length() == 0)) {
            return value;
        }

        // Monta elementos
        int valueIndex = 0, maskIndex = 0;
        char maskChar = ' ', resultChar = ' ';
        StringBuffer buffer = new StringBuffer();

        // Processa a mascara
        while ((valueIndex < value.length()) && (maskIndex < mask.length())) {
            maskChar = mask.charAt(maskIndex++);
            if (maskChar == '#') {
                resultChar = value.charAt(valueIndex++);
            } else {
                resultChar = maskChar;
            }
            buffer.append(resultChar);
        }

        return buffer.toString();
    }

    /**
     * Remoção de mascaras
     * @param java
     *            .lang.String O valor com as mascaras a serem retirada
     * @param java
     *            .lang.String Os elementos presentes na mascara para remoção
     * @return java.lang.String O elementos presentes no valor a se retirar
     */
    public static final String unmask(String value, String maskCharacters) {

        if (value == null) {
            return null;
        } else if ((maskCharacters == null) || (maskCharacters.trim().length() == 0)) {
            return value;
        }

        StringBuffer buffer = new StringBuffer(value.length());
        StringTokenizer cleaner = new StringTokenizer(value, maskCharacters);

        while (cleaner.hasMoreTokens()) {
            buffer.append(cleaner.nextToken());
        }

        return buffer.toString();
    }

    /**
     * Obtém uma data formata em um padrão pré-definido
     * @param oDate
     *            A data a ser formatada em qualquer padrão
     * @param formato
     *            A mascara a ser aplicada para exibição da data
     * @return Retorna um String com a data no formato pré-definido
     */
    public static final String exibeData(Object oDate, String formato) {

        if (oDate == null) {
            return null;
        }
        if ((formato == null) || (formato.trim().length() == 0)) {
            return null;
        }

        simpleDateFormat.applyPattern(formato);

        return simpleDateFormat.format((Date) oDate);
    }

    /**
     * [Fernando Gomes] TODO: Este método não deveria estar aqui Obtem o id de
     * usuario da HttpSession
     */
    static public String getCurrentUser(HttpSession session) {
        if (session == null) {
            return null;
        }
        String user = (String) session.getAttribute("usuarioIdSession");
        if ((user == null) || user.equals("")) {
            return null;
        }
        return user;
    }

    static public String formatPhoneNumber(String value){
        String number = "";
        if(value!=null){
            number = value.trim().replaceAll("\\D+", "");
            System.out.println("==>"+number);
            if(number.trim().length()>0){
                if(number.length()==10){
                    number = "(".concat(number.substring(0, 2)).concat(")").concat(number.substring(2, 6)).concat("-").concat(number.substring(6));
                
                }else if(number.length()==12 && number.startsWith("0")){
                    number = "(".concat(number.substring(1, 3)).concat(")").concat(number.substring(3, 8)).concat("-").concat(number.substring(8));
                
                }else if(number.length()==11 && number.startsWith("0")){
                    number = "(".concat(number.substring(1, 3)).concat(")").concat(number.substring(3, 7)).concat("-").concat(number.substring(7));

                }else if(number.length()==11){
                    number = "(".concat(number.substring(0, 2)).concat(")").concat(number.substring(2, 7)).concat("-").concat(number.substring(7));
                
                }else if(number.length()==8){
                    number = number.substring(0, 4).concat("-").concat(number.substring(4));
                
                }else if(number.length()==9){
                    number = number.substring(0, 5).concat("-").concat(number.substring(5));
                }
            }
        }
        return number; 
    }
    
    /**
     * [Fernando Gomes] TODO: Este método não deveria estar aqui Atribui o id de
     * usuario na HttpSession
     */
    static public void setCurrentUser(HttpSession session, String newUser) throws NullPointerException {
        if (session == null) {
            throw new NullPointerException("Sessão nula");
        }
        session.setAttribute("usuarioIdSession", newUser);
    }

    // letras
    public static String    SLETRAA                        = "A";
    public static String    SLETRAB                        = "B";
    public static String    SLETRAC                        = "C";
    public static String    SLETRAD                        = "D";
    public static String    SLETRAE                        = "E";
    public static String    SLETRAI                        = "I";
    public static String    SLETRAW                        = "W";

    // Constantes Plano Controle
    public static final int OPERACAO_SERVICO               = 1;  // Atlys
    public static final int OPERACAO_CONSUMO               = 2;  // Atlys
    public static final int OPERACAO_DETALHE_SALDO         = 3;  // NGIN
    public static final int OPERACAO_ESTIMATIVA_SALDO      = 4;  // Atlys
    public static final int OPERACAO_EXTRATO               = 5;  // NGIN
    public static final int OPERACAO_HISTORICO_MOVIMENTO   = 6;  // NGIN
    public static final int OPERACAO_PLANO                 = 7;  // NGIN
    public static final int OPERACAO_TARIFA_REDUZIDA       = 8;  // NGIN
    public static final int OPERACAO_CAIXA_POSTAL          = 9;  // NGIN
    public static final int OPERACAO_SUSPENDE_CELULAR      = 10; // Atlys
    public static final int OPERACAO_DETALHE_FATURA        = 11; // NGIN
    public static final int OPERACAO_DETALHE_LINHA         = 12;
    public static final int OPERACAO_HISTORICO_ATENDIMENTO = 13;
    public static final int OPERACAO_PROMOCOES             = 14;
    public static final int OPERACAO_FAVORITOS             = 15;
    public static final int OPERACAO_INFO_FATURAMENTO      = 16;

}
