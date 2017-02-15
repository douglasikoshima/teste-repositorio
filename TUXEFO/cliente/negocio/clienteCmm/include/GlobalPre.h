///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Global
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Renato Striitzel Russo
 * @author  Robinson Vieira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef GLOBALPRE
#define GLOBALPRE

/*******************************************************************************
 * definicoes globais
 *******************************************************************************/
#define LEN_DATE_ORA                    14    // YYYYMMDDHHMMSS
#define LEN_NUMBER_ORA                  25
#define LEN_DATE                        8
#define LEN_EOS                         1
#define LEN_AUX                         255
#define TIPO_COMUNICACAO_EMAIL          "5"
#define ID_USUARIO_ALTERACAO            "666"
#define LEN_RETURN_MESSAGE              256
#define LEN_MESSAGE_EXCEPTION           255
#define REGISTRO_NULO                   "0"
#define ID_TIPO_END_PESSOA_ENDERECO     "2"

#define OK                              0
#define NOK                             -1

/*******************************************************************************
 * definicoes referentes aos processo de log
 *******************************************************************************/
#define LEN_LOG_MESSAGE                 512

#define DEBUG                           0
#define INFORMATION                     1
#define WARNING                         2
#define ERROR                           3
#define CRITICAL                        4

/*******************************************************************************
 * definicoes referentes aos tipos de documentos
 *******************************************************************************/
#define ID_CPF                          1
#define ID_RG                           2
#define ID_PASS                         3
#define ID_RNE                          4
#define ID_CPR                          5
#define ID_CN                           6
#define ID_CNPJ                         7
#define ID_IE                           8
#define ID_EM                           9

/*******************************************************************************
 * definicoes referentes aos tipos de exceçoes.
 *******************************************************************************/
#define ERRO_EXECUCAO                   1
#define ERRO_PARAMETRO_NULL             2
#define ERRO_SEQUENCE                   3
#define ERRO_REGISTRO_NAO_ENCONTRADO    4

/*******************************************************************************
 * definicoes referentes aos tipos de linha.
 *******************************************************************************/
#define TIPO_LINHA_POS_PAGO             "1"
#define TIPO_LINHA_PRE_PAGO             "2"

/*******************************************************************************
 * definicoes referentes à mensagens de retorno dos serviços
 *******************************************************************************/
// SUCESSO
#define SUCESSO_EXECUCAO                "12I0000"

// SCntctPhNbr  12E1000-12E1049
#define E_APLICACAO_SCNTCTPHNBR         "12E1001"
#define E_PARAMETRO_SCNTCTPHNBR         "12E1002"
#define W_DELETE_SCNTCTPHNBR            "12W1000"
#define W_SEQUENCE_SCNTCTPHNBR          "12W1001"

// SAcctCycle   12E1050-12E1099
#define E_APLICACAO_SACCTCYCLE          "12E1051"
#define E_PARAMETRO_SACCTCYCLE          "12E1052"
#define W_DELETE_SACCTCYCLE             "12W1050"
#define W_SEQUENCE_SACCTCYCLE           "12W1051"

// SAcctStatus  12E1100-12E1049
#define E_APLICACAO_SACCTSTATUS         "12E1101"
#define E_PARAMETRO_SACCTSTATUS         "12E1102"
#define W_DELETE_SACCTSTATUS            "12W1100"
#define W_SEQUENCE_SACCTSTATUS          "12W1101"

// SCustAddr    12E1150-12E1199
#define E_APLICACAO_SCUSTADDR           "12E1151"
#define E_PARAMETRO_SCUSTADDR           "12E1152"
#define W_DELETE_SCUSTADDR              "12W1150"
#define W_SEQUENCE_SCUSTADDR            "12W1151"

// SCustIdntfc  12E1200-12E1249
#define E_APLICACAO_SCUSTIDNTFC         "12E1201"
#define E_PARAMETRO_SCUSTIDNTFC         "12E1202"
#define W_DELETE_SCUSTIDNTFC            "12W1200"
#define W_SEQUENCE_SCUSTIDNTFC          "12W1201"

// SCust        12E1250-12E1299
#define E_APLICACAO_SCUST               "12E1251"
#define E_PARAMETRO_SCUST               "12E1252"
#define W_DELETE_SCUST                  "12W1250"
#define W_SEQUENCE_SCUST                "12W1251"

// SSvc         12E1300-12E1349
#define E_APLICACAO_SSVC                "12E1301"
#define E_PARAMETRO_SSVC                "12E1302"
#define W_DELETE_SSVC                   "12W1300"
#define W_SEQUENCE_SSVC                 "12W1301"

// SPkg         12E1350-12E1399
#define E_APLICACAO_SPKG                "12E1351"
#define E_PARAMETRO_SPKG                "12E1352"
#define W_DELETE_SPKG                   "12W1350"
#define W_SEQUENCE_SPKG                 "12W1351"

// SSvcAgrLItem 12E1400-12E1449
#define E_APLICACAO_SSVCAGRLITEM        "12E1401"
#define E_PARAMETRO_SSVCAGRLITEM        "12E1402"
#define W_DELETE_SSVCAGRLITEM           "12W1400"
#define W_SEQUENCE_SSVCAGRLITEM         "12W1401"

// SCustAcct    12E1450-12E1499
#define E_APLICACAO_SCUSTACCT           "12E1451"
#define E_PARAMETRO_SCUSTACCT           "12E1452"
#define W_DELETE_SCUSTACCT              "12W1450"
#define W_SEQUENCE_SCUSTACCT            "12W1451"

// SSbscrpAsgm  12E1500-12E1549
#define E_APLICACAO_SSBSCRPASGM         "12E1501"
#define E_PARAMETRO_SSBSCRPASGM         "12E1502"
#define W_DELETE_SSBSCRPASGM            "12W1500"
#define W_SEQUENCE_SSBSCRPASGM          "12W1501"

// SSbscrp      12E1550-12E1599
#define E_APLICACAO_SSBSCRP             "12E1551"
#define E_PARAMETRO_SSBSCRP             "12E1552"
#define W_DELETE_SSBSCRP                "12W1550"
#define W_SEQUENCE_SSBSCRP              "12W1551"

// SAccessEqp   12E1600-12E1649
#define E_APLICACAO_SACCESSEQP          "12E1601"
#define E_PARAMETRO_SACCESSEQP          "12E1602"
#define W_DELETE_SACCESSEQP             "12W1600"
#define W_SEQUENCE_SACCESSEQP           "12W1601"

// SHirchAAsgm  12E1650-12E1699
#define E_APLICACAO_SHIRCHAASGM         "12E1651"
#define E_PARAMETRO_SHIRCHAASGM         "12E1652"
#define W_DELETE_SHIRCHAASGM            "12W1650"
#define W_SEQUENCE_SHIRCHAASGM          "12W1651"

// SAccessLNAsgm 12E1700-12E1749
#define E_APLICACAO_SACCESSLNASGM       "12E1701"
#define E_PARAMETRO_SACCESSLNASGM       "12E1702"
#define W_DELETE_SACCESSLNASGM          "12W1700"
#define W_SEQUENCE_SACCESSLNASGM        "12W1701"

/*******************************************************************************
 * definicoes referentes ao processo de desmembramento de nomes
 *******************************************************************************/
#define LEN_NOME                        255
#define LEN_NOME_PRIMEIRO               LEN_NOME
#define LEN_NOME_MEIO                   LEN_NOME
#define LEN_NOME_FIM                    LEN_NOME

/*******************************************************************************
 * definicoes de inserir ou apagar
 *******************************************************************************/
#define OP_INSERE                       "I"
#define OP_APAGA                        "D"

/*******************************************************************************
 * definicoes referentes ao tipo de pessoa
 *******************************************************************************/
#define PESSOA_FISICA                   1
#define PESSOA_JURIDICA                 2

/*******************************************************************************
 * definicoes referentes ao tipo de sistema
 *******************************************************************************/
#define ID_ATLYS                        "1"
#define ID_PPS                          "2"
#define ID_ACS                          "3"
#define ID_DW                           "4"
#define ID_URA                          "5"
#define ID_EASYPHONE                    "6"

#define ID_POS_PAGO                     "1"
#define ID_PRE_PAGO                     "2"
                                        
/*******************************************************************************
 * definicoes referentes ao Tipo de Relacionamento
 *******************************************************************************/
#define ID_TIPO_RELACIONAMENTO_C         "1"  // Cliente
#define ID_TIPO_RELACIONAMENTO_U         "2"  // Usuario
#define ID_TIPO_RELACIONAMENTO_R         "3"  // Consultor Relacionamento
#define ID_TIPO_RELACIONAMENTO_V         "4"  // Executivo de Vendas
#define ID_TIPO_RELACIONAMENTO_GC        "5"  // Gestor da Conta

/*******************************************************************************
 * definicoes referentes a hirch_acct_asgm (XML de entrada)
 *******************************************************************************/
#define LEN_ACCT_NBR                        15
#define LEN_HIRCH_ACCT_ASGM_SEQ_NBR         15
#define LEN_BAL_TRNF_FNCL_TRNSCT_TYPE_ID    2
#define LEN_PARENT_ACCT_NBR                 15
#define LEN_ACCT_HIRCH_ID                   18
#define LEN_HIRCH_ACCT_ASGM_EFF_DT          8
#define LEN_HIRCH_ACCT_ASGM_EXPR_DT         8
#define LEN_LIABLE_FLAG                     1
#define LEN_VER_NBR                         3
#define LEN_PERSON_ID                       15
#define LEN_HIRCH_ROOT_FLAG                 1
#define LEN_BAL_TRNF_IND                    1
#define LEN_DEPT_NAME_HIRCH                 20

/*******************************************************************************
 * definicoes referentes a Customer.LinhaConta (Tabela)
 *******************************************************************************/
#define LEN_IDLINHACONTA                LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_IDCONTA                     LEN_NUMBER_ORA
#define LEN_IDTIPORELACIONAMENTO        LEN_NUMBER_ORA
#define LEN_DTLINHACONTA                LEN_DATE_ORA
#define LEN_INLINHAMASTER               LEN_NUMBER_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Customer.PessoaLinhaHistorico (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOALINHAHISTORICO      LEN_NUMBER_ORA
#define LEN_DTRELACIONAMENTO            LEN_DATE_ORA
#define LEN_CDAREAREGISTRO              LEN_NUMBER_ORA
#define LEN_NRLINHA                     LEN_NUMBER_ORA
#define LEN_IDTIPORELACIONAMENTO        LEN_NUMBER_ORA
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Linha.TrocaNumero (Tabela)
 *******************************************************************************/
#define LEN_IDTROCANUMERO               LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_IDLINHABASE                 LEN_NUMBER_ORA
#define LEN_DTTROCA                     LEN_DATE_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Customer.PessoaLinha (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOALINHA               LEN_NUMBER_ORA
#define LEN_DTPESSOALINHA               LEN_DATE_ORA
#define LEN_DTPESSOALINHAOUT            10
#define LEN_IDTIPORELACIONAMENTO        LEN_NUMBER_ORA
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_DTULTIMAALTERACAOOUT        10

/*******************************************************************************
 * definicoes referentes a Customer.PessoaEndereco (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOAENDERECO            LEN_NUMBER_ORA
#define LEN_IDPESSOA                    LEN_NUMBER_ORA
#define LEN_NRSEQUENCIA                 LEN_NUMBER_ORA
#define LEN_IDPAIS                      LEN_NUMBER_ORA
#define LEN_NMMUNICIPIO                 255
#define LEN_NMLOCALIDADE                255
#define LEN_NMBAIRRO                    255
#define LEN_NMTIPOLOGRADOURO            255
#define LEN_NMTITULOLOGRADOURO          255
#define LEN_NMLOGRADOURO                255
#define LEN_NRENDERECO                  255
#define LEN_DSENDERECOCOMPLEMENTO       255
#define LEN_INENDERECOPREFERENCIAL      LEN_NUMBER_ORA
#define LEN_NRCEP                       255
#define LEN_DTCADASTRO                  LEN_DATE_ORA
#define LEN_DTCADASTROOUT               10
#define LEN_IDTIPOENDERECO              LEN_NUMBER_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA
#define LEN_DTEXPIRACAOOUT              10
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_IDUF                        LEN_NUMBER_ORA
#define LEN_DSAOSCUIDADOS               255
#define LEN_CDCAIXAPOSTAL               255
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_IDENDERECOSISTEMAORIGEM     255
#define LEN_INCOMUNICACAOPREFERENCIAL   LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_DTULTIMAALTERACAOOUT        10
 
/*******************************************************************************
 * definicoes referentes a Customer.ContaEndereco (Tabela)
 *******************************************************************************/
#define LEN_IDCONTAENDERECO             LEN_NUMBER_ORA
#define LEN_IDPESSOAENDERECO            LEN_NUMBER_ORA
#define LEN_IDCONTA                     LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Pkg (XML de Entrada)
 *******************************************************************************/
#define LEN_VER_NBR                     3
#define LEN_MEMBER_SVC_NAME             20
#define LEN_PKG_SVC_NAME                20
#define LEN_PERSON_ID                   15

/*******************************************************************************
 * definicoes referentes a Svc (XML de Entrada)
 *******************************************************************************/
#define LEN_SVC_NAME                    20
#define LEN_SVC_TYPE_CD                 2
#define LEN_SVC_PRVDR_ID                5
#define LEN_KIT_TYPE_CD                 20
#define LEN_ACCESS_NBR_RQR_FLAG         1
#define LEN_AIR_RATE_METHOD_CD          2
#define LEN_CALLNG_CIRCLE_TYPE_IND      5
#define LEN_ATTR_GROUP_NAME             20
#define LEN_AUTO_EXPR_FLAG              1
#define LEN_BAL_LIMIT_SBSCRP_FLAG       1
#define LEN_CNTRC_SVC_PERIOD_QTY        3
#define LEN_CNTRC_SVC_PERIOD_UNIT_CD    10
#define LEN_COPY_MEMBER_PRICE_FLAG      1
#define LEN_EQP_CNTRC_TYPE_CD           1
#define LEN_EQP_RQR_FLAG                1
#define LEN_ESCLT_PAGE_DATA_RQR_FLAG    1
#define LEN_PERSON_ID                   15
#define LEN_PRCNG_PRI                   2
#define LEN_PRICE_LOCK_IND              1
#define LEN_VER_NBR                     3
#define LEN_USAGE_ORIGIN_TEXT           10
#define LEN_TAX_AUTH_REQ_FLAG           1
#define LEN_SVC_SEARCH_CAT_CD           3
#define LEN_SVC_NOT_AVAIL_DT            8
#define LEN_SVC_DESC                    40
#define LEN_SVC_ITEM_EQP_CD             40
#define LEN_SVC_LONG_DESC               400
#define LEN_SVC_MAX_PURCH_QTY           3
#define LEN_FAX_IND                     1
#define LEN_SVC_MINM_PURCH_QTY          3
#define LEN_BAUD_RATE_IND               1
#define LEN_TOLL_TYPE_IND               2
#define LEN_DATA_TYPE_IND               1
#define LEN_SVC_AUTO_PROVSN_FLAG        1
#define LEN_EQP_TYPE_CD                 2
#define LEN_CUSTOM_BILL_TEXT_MASK_ID    50
#define LEN_CUSTOM_BILL_TEXT_RQRD_FLAG  1
#define LEN_CUSTOM_BILL_PROMPT_TEXT     40
#define LEN_LANG_TRAN_SEQ_NBR           15
#define LEN_NTWRK_PRE_PAID_FLAG         1
#define LEN_TAX_INCL_FLAG               1
#define LEN_PYMT_DRIVES_RATE_FLAG       1
#define LEN_CHNL_TYPE_IND               3
#define LEN_SVC_EFF_DUR_QTY             6
#define LEN_WAIVE_SETUP_IND             1
#define LEN_BONUS_ELGBL_FLAG            1

/*******************************************************************************
 * definicoes referentes a Linha.Pacote (Tabela)
 *******************************************************************************/
#define LEN_IDPACOTE                    LEN_NUMBER_ORA
#define LEN_IDSERVICO                   LEN_NUMBER_ORA
#define LEN_IDSERVICOPAI                LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_DTULTIMAALTERACAOOUT        10

/*******************************************************************************
 * definicoes referentes a Linha.PlanoServico (Tabela)
 *******************************************************************************/
#define LEN_IDSERVICO                   LEN_NUMBER_ORA
#define LEN_SGSERVICO                   255
#define LEN_NMSERVICO                   255
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_INPLANO                     LEN_NUMBER_ORA
#define LEN_IDSERVICOSISTEMAORIGEM      255
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA
#define LEN_DTEXPIRACAOOUT              10
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_DTULTIMAALTERACAOOUT        10

/*******************************************************************************
 * definicoes referentes a Linha.SerialLinha (Tabela)
 *******************************************************************************/
#define LEN_IDSERIALLINHA               LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_IDSERIALAPARELHO            LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Linha.SerialAparelho (Tabela)
 *******************************************************************************/
#define LEN_IDSERIALAPARELHO            LEN_NUMBER_ORA
#define LEN_SGSERIALAPARELHO            255
#define LEN_IDTIPOSERIAL                LEN_NUMBER_ORA
#define LEN_IDMODOFREQUENCIA            LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
/*******************************************************************************
 * definicoes referentes a Customer.PessoaDocumento (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOADOCUMENTO           LEN_NUMBER_ORA
#define LEN_IDPESSOA                    LEN_NUMBER_ORA
#define LEN_IDDOCUMENTO                 LEN_NUMBER_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_IDDOCUMENTOSISTEMAORIGEM    255
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Linha.LinhaBase (Tabela)
 *******************************************************************************/
#define LEN_IDLINHABASE                 LEN_NUMBER_ORA
#define LEN_IDAREAREGISTRO              LEN_NUMBER_ORA
#define LEN_NRLINHA                     LEN_NUMBER_ORA
#define LEN_DTSTATUSLINHA               LEN_DATE_ORA
#define LEN_NRMIN                       255
#define LEN_NRDIGITOLINHA               LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a sbscrp (XML de entrada)
 *******************************************************************************/
#define LEN_SBSCRP_ID                   15
#define LEN_COMM_SVC_AREA_ID            10
#define LEN_IXC_SVC_PRVDR_ID            5
#define LEN_PREV_IXC_SVC_PRVDR_ID       5
#define LEN_CELULR_ACTVTN_DT            8
#define LEN_SBSCRP_TYPE_CD              1
#define LEN_CUST_ID                     15
#define LEN_IXC_SVC_PRVDR_EFF_DT        LEN_DATE
#define LEN_OTA_PIN_NBR                 4
#define LEN_PAGER_SVC_PRVDR_ID          20
#define LEN_PAGER_ACCESS_NBR            32
#define LEN_PAGER_PIN_NBR               10
#define LEN_PAGER_TYPE_CD               5
#define LEN_PERSON_ID                   15
#define LEN_VER_NBR                     3
#define LEN_ACCESS_NBR                  32
#define LEN_TAX_ADDR_SEQ_NBR            5
#define LEN_SBSCRP_SVC_EXPR_DT          LEN_DATE
#define LEN_SBSCRP_SVC_EFF_DT           LEN_DATE
#define LEN_PREV_IXC_SVC_PRVDR_EFF_DT   LEN_DATE
#define LEN_CHNL_TYPE_IND               3

/*******************************************************************************
 * definicoes referentes a sbscrp_asgm (XML de entrada)
 *******************************************************************************/
#define LEN_PERSON_ID                   15
#define LEN_SBSCRP_ASGM_EFF_DT          8
#define LEN_VER_NBR                     3
#define LEN_SBSCRP_ASGM_EXPR_DT         8
#define LEN_ACCT_NBR                    15
#define LEN_SBSCRP_ID                   15
#define LEN_SBSCRP_SEQ_NBR              15

/*******************************************************************************
 * definicoes referentes a Linha.LinhaTelefonica (Tabela)
 *******************************************************************************/
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_IDLINHASISTEMAORIGEM        255
#define LEN_IDLINHABASE                 LEN_NUMBER_ORA
#define LEN_IDTIPOLINHA                 LEN_NUMBER_ORA
#define LEN_IDESTADOLINHA               LEN_NUMBER_ORA
#define LEN_DTESTADOLINHA               LEN_DATE_ORA
#define LEN_INDIVULGACAONRLINHA         LEN_NUMBER_ORA
#define LEN_DTAUTORIZACAODIVULGACAO     LEN_DATE_ORA
#define LEN_DTHABILITACAO               LEN_DATE_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_TSSINCRONISMOESTADO         LEN_NUMBER_ORA
#define LEN_SQSINCRONISMOESTADO         LEN_NUMBER_ORA
#define LEN_CDCHURNPROBABILIDADE        255
#define LEN_VLRCHURNPROBABILIDADE       LEN_NUMBER_ORA
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a ContaHierarquia (Tabela)
 *******************************************************************************/
#define LEN_IDCONTA                     LEN_NUMBER_ORA
#define LEN_IDCONTAPAI                  LEN_NUMBER_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Customer.Conta (Tabela)
 *******************************************************************************/
#define LEN_IDCONTA                     LEN_NUMBER_ORA
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_IDLAYOUTCONTA               LEN_NUMBER_ORA
#define LEN_IDTIPOCONTA                 LEN_NUMBER_ORA
#define LEN_CDCONTA                     100
#define LEN_CDDIGITOCONTA               255
#define LEN_CDCICLOFATURAMENTO          255
#define LEN_NRDIAVENCIMENTO             LEN_NUMBER_ORA
#define LEN_INCONTAPOREMAIL             LEN_NUMBER_ORA
#define LEN_TSSINCRONISMOPRINCIPAL      LEN_NUMBER_ORA
#define LEN_SQSINCRONISMOPRINCIPAL      LEN_NUMBER_ORA
#define LEN_TSSINCRONISMOCICLO          LEN_NUMBER_ORA
#define LEN_SQSINCRONISMOCICLO          LEN_NUMBER_ORA
#define LEN_TSSINCRONISMOSTATUS         LEN_NUMBER_ORA
#define LEN_SQSINCRONISMOSTATUS         LEN_NUMBER_ORA
#define LEN_IDSTATUSCONTA               LEN_NUMBER_ORA
#define LEN_IDCONTASISTEMAORIGEM        255
#define LEN_NRDIAPERIODOCICLODE         2
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes dos tipos de estruturas relacionadas com a classe Conta
 *******************************************************************************/
#define ID_ACCT_CYCLE                   1
#define ID_ACCT_STATUS                  2
#define ID_CUST_ACCT                    3

/*******************************************************************************
 * definicoes dos campos de entrada acct_cycle (XML de Entrada)
 *******************************************************************************/
#define LEN_ACCT_NBR                    15
#define LEN_CYCLE_CD                    2
#define LEN_ACCT_CYCLE_EFF_DT           LEN_DATE
#define LEN_ACCT_CYCLE_EFF_TM           LEN_DATE
#define LEN_PERSON_ID                   15
#define LEN_VER_NBR                     3

/*******************************************************************************
 * definicoes dos campos de entrada acct_status (XML de Entrada)
 *******************************************************************************/
#define LEN_VER_NBR                     3
#define LEN_ACCT_NBR                    15
#define LEN_ACCT_STATUS_EFF_DT          LEN_DATE
#define LEN_ACCT_STATUS_EFF_TM          LEN_DATE
#define LEN_ACCT_STATUS_ID              1
#define LEN_PERSON_ID                   15
#define LEN_REASON_CD                   5

/*******************************************************************************
 * definicoes referentes a SistemaOrigem (Tabela)
 *******************************************************************************/
#define LEN_SG_SISTEMA_ORIGEM           255
#define LEN_NM_SISTEMA_ORIGEM           255

/*******************************************************************************
 * definicoes referentes a cust_addr (XML de Entrada)
 *******************************************************************************/
#define LEN_CUST_ID                     15
#define LEN_ADDR_SEQ_NBR                5
#define LEN_ADDR_EFF_DT                 8
#define LEN_STATE_CD                    5
#define LEN_IDUF                        LEN_NUMBER_ORA
#define LEN_ADDR_ERROR_CD               40
#define LEN_ADDR_EXPR_DT                8
#define LEN_GEO_CITY_CD                 8
#define LEN_APT_DSGNTR_DESC             5
#define LEN_GEO_COUNTY_CD               3
#define LEN_APT_NBR                     8
#define LEN_GEO_STATE_CD                2
#define LEN_ATNTN_TEXT                  50
#define LEN_CITY_LIMIT_FLAG             1
#define LEN_COUNTY_NAME                 50
#define LEN_DELVRY_POINT_CD             3
#define LEN_PRIM_ADDR_FLAG              1
#define LEN_POST_OFFICE_BOX_NBR         10
#define LEN_POSTAL_PLUS_CD              5
#define LEN_POSTAL_CD                   10
#define LEN_PERSON_ID                   15
#define LEN_HOUSE_NBR                   10
#define LEN_HOUSE_DIRCTN_IND            2
#define LEN_VER_NBR                     3
#define LEN_TRLNG_DIR_CD                2
#define LEN_STREET_SUFFIX_TEXT          5
#define LEN_DELVRY_1_TEXT               50
#define LEN_STREET_NAME                 70
#define LEN_RURAL_ROUTE_TYPE_CD         2
#define LEN_DELVRY_2_TEXT               50
#define LEN_RURAL_ROUTE_NBR             6
#define LEN_DELVRY_3_TEXT               50
#define LEN_DSTRBN_CENTER_TEXT          60
#define LEN_CITY_NAME                   50
#define LEN_BILL_NAME                   50
#define LEN_ATHRTY_SEQ_NBR              10
#define LEN_VERT_COORD_NBR              10
#define LEN_HRZT_COORD_NBR              10
#define LEN_CNTRY_CD                    5
#define LEN_IDPAIS                      LEN_NUMBER_ORA
#define LEN_TAX_ATHRTY_ASGM_IND         2

/*******************************************************************************
 * definicoes referentes a Pessoa (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOA                    LEN_NUMBER_ORA
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_IDPESSOASISTEMAORIGEM       255
#define LEN_NMPESSOA                    255
#define LEN_NMNOME                      255
#define LEN_NMNOMEMEIO                  255
#define LEN_NMSOBRENOME                 255
#define LEN_DTCHURN                     LEN_DATE_ORA
#define LEN_INFALECIMENTOINFORMADO      LEN_NUMBER_ORA
#define LEN_DTFALECIMENTO               LEN_DATE_ORA
#define LEN_IDTIPOPESSOA                LEN_NUMBER_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_IDTIPOCARTEIRA              LEN_NUMBER_ORA
#define LEN_IDUF                        LEN_NUMBER_ORA
#define LEN_VLRCHURNPROBABILIDADE       LEN_NUMBER_ORA
#define LEN_DTTIPOCARTEIRA              LEN_DATE_ORA
#define LEN_IDPROBINADIMPLENCIA         LEN_NUMBER_ORA
#define LEN_IDCHURNPROBABILIDADE        LEN_NUMBER_ORA
#define LEN_DTCADASTRO                  LEN_DATE_ORA
#define LEN_DSCARGOCONTATO              255
#define LEN_DSDEPTOCONTATO              255
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Pessoa Comunicacao (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOACOMUNICACAO         LEN_NUMBER_ORA
#define LEN_IDPESSOA                    LEN_NUMBER_ORA
#define LEN_IDTIPOCOMUNICACAO           LEN_NUMBER_ORA
#define LEN_NRSEQUENCIA                 LEN_NUMBER_ORA
#define LEN_DSCONTATO                   255           
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_DTCADASTRO                  LEN_DATE_ORA  
#define LEN_IDCOMUNICACAOSISTEMAORIGEM  255           
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA  
#define LEN_INCOMUNICACAOPREFERENCIAL   LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA  

/*******************************************************************************
 * definicoes referentes a Pessoa Comunicacao (XML de Entrada)
 *******************************************************************************/
#define LEN_CUST_ID                     15
#define LEN_PHONE_NBR_XTENSN_TEXT       4
#define LEN_PHONE_NBR                   32
#define LEN_CNTCT_PHONE_NBR_TYPE_CD     5
#define LEN_PRIM_CNTCT_FLAG             1
#define LEN_CNTCT_PHONE_SEQ_NBR         15
#define LEN_CNTCT_PHONE_DT              8
#define LEN_PERSON_ID                   15
#define LEN_PAGER_PIN_NBR               10

/*******************************************************************************
 * definicoes referentes a customer.documento (Tabela)
 *******************************************************************************/
#define LEN_IDDOCUMENTO                 LEN_NUMBER_ORA
#define LEN_CDCPFCNPJBASE               255
#define LEN_CDCNPJFILIAL                255
#define LEN_CDCPFCNPJCONTROLE           255
#define LEN_NRDOCUMENTO                 255
#define LEN_SGORGAOEXPEDIDOR            255
#define LEN_DTEMISSAO                   LEN_DATE_ORA
#define LEN_IDPAIS                      LEN_NUMBER_ORA
#define LEN_IDUF                        LEN_NUMBER_ORA
#define LEN_IDTIPODOCUMENTO             LEN_NUMBER_ORA
 
/*******************************************************************************
 * definicoes referentes a cust_idntfc (XML de Entrada)
 *******************************************************************************/
#define LEN_CUST_ID                     15
#define LEN_CUST_IDNTFC_TYPE_CD         5
#define LEN_CUST_ID_TEXT                40
#define LEN_PRIM_CUST_IDNTFC_FLAG       1
#define LEN_CUST_IDNTFC_ATTR_1_TEXT     40
#define LEN_CUST_IDNTFC_ATTR_2_TEXT     40
#define LEN_CUST_IDNTFC_ATTR_3_TEXT     40
#define LEN_CUST_IDNTFC_ATTR_1_CD       5
#define LEN_CUST_IDNTFC_ATTR_2_CD       5
#define LEN_CUST_IDNTFC_ATTR_3_CD       5
#define LEN_PERSON_ID                   15
#define LEN_VER_NBR                     3

/*******************************************************************************
 * definicoes referentes a cust_acct (XML de Entrada)
 *******************************************************************************/
#define LEN_ACCT_NBR                    15
#define LEN_HELD_BILL_REASON_CD         2
#define LEN_COMM_SVC_AREA_ID            10
#define LEN_MAJOR_ACCT_NBR              16
#define LEN_CUST_ACCT_ESTAB_DT          8
#define LEN_GRNTR_CUST_ID               15
#define LEN_CUST_TYPE_CD                1
#define LEN_HIGH_PRI_REMARKS_CNTR       5
#define LEN_PERSON_ID                   15
#define LEN_CUST_ID                     15
#define LEN_CR_CLASS_CD                 2
#define LEN_PSWD_TEXT                   10
#define LEN_ACCT_TYPE_CD                10
#define LEN_MINM_SBSCRR_COMMIT_EXPR_DT  8
#define LEN_MINM_SBSCRR_COMMIT_EFF_DT   8
#define LEN_MINM_SBSCRR_CNTRC_EXPR_DT   8
#define LEN_MINM_SBSCRR_CNTRC_EFF_DT    8
#define LEN_PURCH_ORDER_EFF_DT          8
#define LEN_PURCH_ORDER_EXPR_DT         8
#define LEN_PURCH_ORDER_NBR             20
#define LEN_REAL_TM_RATE_FLAG           1
#define LEN_SINGLE_USER_FLAG            1
#define LEN_VER_NBR                     3
#define LEN_DORMANT_FLAG_CD             1
#define LEN_RPRTNG_CUST_ID              15
#define LEN_RPRTNG_ADDR_SEQ_NBR         5
#define LEN_HIRCH_RPRTNG_LEVEL_IND      1
#define LEN_ACCT_EVER_LIABLE_FLAG       1
#define LEN_WATCH_ACCT_IND              1
#define LEN_WATCH_BAL_IND               1
#define LEN_WRITE_OFF_ADJ_AMT           15
#define LEN_WRITE_OFF_AMT               15
#define LEN_WRITE_OFF_PYMT_AMT          15
#define LEN_HELD_BILL_EXPR_DT           8
#define LEN_RSTRCN_LIMIT_AMT            15
#define LEN_HOLD_BILL_IND               1
#define LEN_MLNG_ADDR_SEQ_NBR           5
#define LEN_ACCT_INTRFC_FAIL_LOGON_QTY  1
#define LEN_DEPT_CD                     5
#define LEN_DEP_REFUND_METHOD_IND       2
#define LEN_CR_BAL_AMT                  15
#define LEN_COLL_EXPR_DT                8
#define LEN_COLL_EFF_DT                 8
#define LEN_ACCESS_NBR                  32
#define LEN_MINM_SBSCRR_GRACE_PERIOD_QTY    3
#define LEN_MINM_SBSCRP_QTY             7
#define LEN_MINM_SBSCRR_THSHLD_FEE_AMT  15
#define LEN_PRE_PAID_ACCT_SZR_DT        8
#define LEN_PRE_PAID_ACCT_FINAL_DT      8
#define LEN_PRE_PAID_DISABL_DT_PRCSS_FLAG   1
#define LEN_CR_RSTRCN_IND               1
#define LEN_LAST_CR_RSTRCN_CHANGE_DT    8
#define LEN_LARGE_ACCT_FLAG             1
#define LEN_PRE_EVENT_SEND_FLAG         1
#define LEN_INSTLM_PLAN_DEBT_EFF_DT     8

/*******************************************************************************
 * definicoes referentes a PessoaConta (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOACONTA               LEN_NUMBER_ORA
#define LEN_IDTIPORELACIONAMENTO        LEN_NUMBER_ORA
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDCONTA                     LEN_NUMBER_ORA
#define LEN_DTPESSOACONTA               LEN_DATE_ORA
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Cust (Tabela)
 *******************************************************************************/
#define LEN_CUST_ID                     15
#define LEN_COMM_SVC_AREA_ID            10
#define LEN_LANG_CD                     5
#define LEN_IDTIPOCARTEIRA              LEN_NUMBER_ORA // cust_type_cd
#define LEN_AUTO_TYPE_CD                2
#define LEN_CR_CLASS_CD                 2
#define LEN_BIRTH_DT                    8
#define LEN_BUS_NAME                    128
#define LEN_CR_CLASS_ASGM_TM            8
#define LEN_CTZNSP_CNTRY_CD             5
#define LEN_DEPT_NAME                   25
#define LEN_DISABL_DIRECT_MAIL_FLAG     1
#define LEN_DISABL_DIRECT_CALL_FLAG     1
#define LEN_HIGH_PRI_REMARKS_CNTR       5
#define LEN_IDSEXO                      LEN_NUMBER_ORA // GENDER_CD
#define LEN_FIRST_NAME                  64
#define LEN_ETHNIC_ORIGIN_CD            2
#define LEN_EMP_QTY                     5
#define LEN_EMPLR_NAME                  128
#define LEN_DUNN_BRAD_NBR               9
#define LEN_DRVRS_LCNS_STATE_CD         5
#define LEN_STD_OCPTN_CD                2
#define LEN_STD_INDSTY_CD               2
#define LEN_SEARCH_NAME                 128
#define LEN_SEARCH_FIRST_NAME           64
#define LEN_PSYGRP_CD                   2
#define LEN_IDTRATAMENTO                LEN_NUMBER_ORA // PREFIX_NAME
#define LEN_PERSON_ID                   15
#define LEN_OWN_RES_FLAG                1
#define LEN_VER_NBR                     3
#define LEN_TITLE_TEXT                  10
#define LEN_TAX_ID_NBR                  9
#define LEN_SUFFIX_NAME                 16
#define LEN_ORGZTN_PERSON_IND           1
#define LEN_NATL_ORGZTN_FLAG            1
#define LEN_IDESTADOCIVIL               LEN_NUMBER_ORA // MRTL_STATUS_CD
#define LEN_MIDDLE_NAME                 64
#define LEN_LAST_NAME                   64
#define LEN_JRSD_CD                     2
#define LEN_INCOME_RANGE_CD             2
#define LEN_HSHLD_NBR_QTY               2
#define LEN_DRVRS_LCNS_NBR              25
#define LEN_CR_CLASS_ASGM_DT            8
#define LEN_BUS_YEAR_QTY                3
#define LEN_EMPLD_MONTHS_QTY            3
#define LEN_EMAIL_ADDR_TEXT             100
#define LEN_BUS_CUST_CNTCT_NAME         128
#define LEN_BUS_CUST_CNTCT_TITLE_TEXT   40
#define LEN_CUST_INTRFC_FAIL_LOGON_QTY  1
#define LEN_CUST_INTRFC_USER_ID         32
#define LEN_PSWD_RMNDR_TEXT             200
#define LEN_PSWD_TEXT_CUST              64
#define LEN_TAX_LBLTY_TYPE_CD           5
#define LEN_VAT_RGSTRT_ID               20
#define LEN_VALUE_CLASS_ID              15

/*******************************************************************************
 * definicoes referentes a PessoaFisica (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOA                    LEN_NUMBER_ORA
#define LEN_DTNASCIMENTO                LEN_DATE_ORA
#define LEN_NMMAE                       255
#define LEN_NMPAI                       255
#define LEN_IDTRATAMENTO                LEN_NUMBER_ORA
#define LEN_IDESTADOCIVIL               LEN_NUMBER_ORA
#define LEN_IDPAIS                      LEN_NUMBER_ORA
#define LEN_IDSEXO                      LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a PessoaJuridica (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOA                    LEN_NUMBER_ORA
#define LEN_NMPESSOAFILIAL              255
#define LEN_NMFANTASIA                  255
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a PessoaJuridica (Tabela)
 *******************************************************************************/
#define LEN_SBSCRP_ID                   15
#define LEN_SVC_AGRMNT_SEQ_NBR          15
#define LEN_COMM_SVC_AREA_ID            10
#define LEN_CALLNG_CIRCLE_SEQ_NBR       10
#define LEN_SVC_TYPE_CD                 2
#define LEN_SVC_NAME                    20
#define LEN_ACCESS_SVC_FLAG             1
#define LEN_REASON_CD                   5
#define LEN_ASGM_PNDNG_IND              1
#define LEN_BACKDT_CANCEL_EFF_DT        8
#define LEN_CHANGE_SVC_EFF_DT           8
#define LEN_CNTRC_EFF_DT                8
#define LEN_CNTRC_STSFCT_DT             8
#define LEN_GEO_CITY_CD                 8
#define LEN_GEO_COUNTY_CD               3
#define LEN_GEO_STATE_CD                2
#define LEN_INSTL_PAID_QTY              9
#define LEN_LAST_INSTLM_PAID_DT         8
#define LEN_PERSON_ID                   15
#define LEN_PRCNG_PRI                   2
#define LEN_PRICE_PLAN_GROUP_ID         15
#define LEN_PRICE_PLAN_SWAP_CNTR        5
#define LEN_VER_NBR                     3
#define LEN_USAGE_LIMIT_AMT             18
#define LEN_SYSTEM_CREATE_DT            8
#define LEN_CUSTOM_BILL_TEXT            50
#define LEN_SVC_QTY                     5
#define LEN_SVC_AGRMNT_TRMNTN_TM        8
#define LEN_SVC_AGRMNT_TRMNTN_DT        8
#define LEN_SVC_AGRMNT_EFF_TM           8
#define LEN_SVC_AGRMNT_EFF_DT           8
#define LEN_LEADER_SBSCRP_ID            15

/*******************************************************************************
 * definicoes referentes a Linha.PlanoServicoLinha (Tabela)
 *******************************************************************************/
#define LEN_SBSCRP_ID                   15
#define LEN_SVC_AGRMNT_SEQ_NBR          15
#define LEN_COMM_SVC_AREA_ID            10
#define LEN_CALLNG_CIRCLE_SEQ_NBR       10
#define LEN_SVC_TYPE_CD                 2
#define LEN_SVC_NAME                    20
#define LEN_ACCESS_SVC_FLAG             1
#define LEN_REASON_CD                   5
#define LEN_ASGM_PNDNG_IND              1
#define LEN_BACKDT_CANCEL_EFF_DT        8
#define LEN_CHANGE_SVC_EFF_DT           8
#define LEN_CNTRC_EFF_DT                8
#define LEN_CNTRC_STSFCT_DT             8
#define LEN_GEO_CITY_CD                 8
#define LEN_GEO_COUNTY_CD               3
#define LEN_GEO_STATE_CD                2
#define LEN_INSTL_PAID_QTY              9
#define LEN_LAST_INSTLM_PAID_DT         8
#define LEN_PERSON_ID                   15
#define LEN_PRCNG_PRI                   2
#define LEN_PRICE_PLAN_GROUP_ID         15
#define LEN_PRICE_PLAN_SWAP_CNTR        5
#define LEN_VER_NBR                     3
#define LEN_USAGE_LIMIT_AMT             18
#define LEN_SYSTEM_CREATE_DT            8
#define LEN_CUSTOM_BILL_TEXT            50
#define LEN_SVC_QTY                     5
#define LEN_SVC_AGRMNT_TRMNTN_TM        8
#define LEN_SVC_AGRMNT_TRMNTN_DT        8
#define LEN_SVC_AGRMNT_EFF_TM           8
#define LEN_SVC_AGRMNT_EFF_DT           8
#define LEN_LEADER_SBSCRP_ID            15

/*******************************************************************************
 * definicoes referentes a Linha.PlanoServicoLinha (Tabela)
 *******************************************************************************/
#define LEN_IDSERVICOLINHA              LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_IDSERVICO                   LEN_NUMBER_ORA
#define LEN_INPLANO                     LEN_NUMBER_ORA
#define LEN_DTVIGENCIAINICIO            LEN_DATE_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_DTVIGENCIAFINAL             LEN_DATE_ORA
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA
#define LEN_INCONTRATO                  LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_IDSERVLINHASISTORIGEM       255

/*******************************************************************************
 * definicoes referentes a PessoaRelacionamento (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOARELACIONAMENTO      LEN_NUMBER_ORA
#define LEN_IDPESSOADEPARARELACIONADA   LEN_NUMBER_ORA
#define LEN_IDTIPORELACIONAMENTO        LEN_NUMBER_ORA
#define LEN_DTINICIORELACIONAMENTO      LEN_DATE_ORA
#define LEN_DTFIMRELACIONAMENTO         LEN_DATE_ORA
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a PessoaDePara (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDPESSOA                    LEN_NUMBER_ORA
#define LEN_IDPESSOAORIGEM              LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a ACCESS_LINE_NBR_ASGM (Tabela)
 *******************************************************************************/
#define LEN_ACCESS_NBR                  32
#define LEN_REASON_CD                   5
#define LEN_SBSCRP_ID                   15
#define LEN_ACCESS_LINE_SEQ_NBR         15
#define LEN_SBSCRP_ACCESS_LINE_SEQ_NBR  15
#define LEN_ACCESS_NBR_ASGM_EXPR_DT     8
#define LEN_ACCESS_NBR_ASGM_EXPR_TM     8
#define LEN_ACCESS_NBR_EFF_DT           8
#define LEN_CREATE_DT                   8
#define LEN_ACCESS_NBR_EFF_TM           8
#define LEN_PERSON_ID                   15
#define LEN_SWITCH_STATUS_CD            1
#define LEN_VER_NBR                     3
#define LEN_PRICE_PLAN_GROUP_ID         15
#define LEN_MGRT_FROM_ACCESS_NBR        32

/*******************************************************************************
 * Macros para manipulacao dos dados XML
 *******************************************************************************/
#define GETXML(Struct, Campo) \
{\
    char* p = walkTree(dnode,#Campo,0); \
    strcpy(##Struct.##Campo,p ? p : ""); \
}

#define GETXML_OBRIGATORIO(Struct, Campo) \
{\
    char szAloca[LEN_RETURN_MESSAGE + LEN_EOS]; \
    char* p = walkTree(dnode,#Campo,0); \
    if(p == NULL) \
    { \
       sprintf(szAloca, "Parametro obrigatorio inexistente [%s]", #Campo); \
       throw sincException(ERRO_PARAMETRO_NULL, szAloca); \
    } \
    if(strlen(p) == 0) \
    { \
       sprintf(szAloca, "Parametro obrigatorio vazio [%s]", #Campo); \
       throw sincException(ERRO_PARAMETRO_NULL, szAloca); \
    } \
    strcpy(##Struct.##Campo, p); \
}

/*******************************************************************************
 * Macros para manipulacao de dados ProC/C++
 *******************************************************************************/
#define STRCPY_TO_ORA(dest, source) { \
    dest.len = (unsigned short) strlen(source);\
    strncpy((char *) dest.arr, (const char *) source, (size_t)dest.len); \
}
#define STRCPY_FROM_ORA(dest, source) { \
    dest[source.len] = 0; \
    strncpy((char *)dest,(const char *)source.arr, source.len); \
}
/*
#define STRNCPY_TO_ORA(dest, source, tam)\
    dest.len = tam;\
    memcpy((char *)dest.arr, (const char *)source, tam)
*/

/*******************************************************************************
 * Estrutura referente a hirch_acct_asgm (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char acct_nbr[LEN_ACCT_NBR + LEN_EOS];
    char hirch_acct_asgm_seq_nbr[LEN_HIRCH_ACCT_ASGM_SEQ_NBR + LEN_EOS];
    char bal_trnf_fncl_trnsct_type_id[LEN_BAL_TRNF_FNCL_TRNSCT_TYPE_ID + LEN_EOS];
    char parent_acct_nbr[LEN_PARENT_ACCT_NBR + LEN_EOS];
    char acct_hirch_id[LEN_ACCT_HIRCH_ID + LEN_EOS];
    char hirch_acct_asgm_eff_dt[LEN_HIRCH_ACCT_ASGM_EFF_DT + LEN_EOS];
    char hirch_acct_asgm_expr_dt[LEN_HIRCH_ACCT_ASGM_EXPR_DT + LEN_EOS];
    char liable_flag[LEN_LIABLE_FLAG + LEN_EOS];
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char hirch_root_flag[LEN_HIRCH_ROOT_FLAG + LEN_EOS];
    char bal_trnf_ind[LEN_BAL_TRNF_IND + LEN_EOS];
    char dept_name[LEN_DEPT_NAME_HIRCH + LEN_EOS];
} THirchAcctAsgm;

/*******************************************************************************
 * Estrutura referente a Linha.TrocaNumero (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdTrocaNumero[LEN_IDTROCANUMERO + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdLinhaBase[LEN_IDLINHABASE + LEN_EOS];
    char szDtTroca[LEN_DTTROCA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TTrocaNumero;

/*******************************************************************************
 * Estrutura referente a Pkg (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
    char member_svc_name[LEN_MEMBER_SVC_NAME + LEN_EOS];    // filho
    char pkg_svc_name[LEN_PKG_SVC_NAME + LEN_EOS];          // pai
    char person_id[LEN_PERSON_ID + LEN_EOS];
} TPkg;

/*******************************************************************************
 * Estrutura referente a Svc (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char svc_name[LEN_SVC_NAME + LEN_EOS];
    char svc_type_cd[LEN_SVC_TYPE_CD + LEN_EOS];
    char svc_prvdr_id[LEN_SVC_PRVDR_ID + LEN_EOS];
    char kit_type_cd[LEN_KIT_TYPE_CD + LEN_EOS];
    char access_nbr_rqr_flag[LEN_ACCESS_NBR_RQR_FLAG + LEN_EOS];
    char air_rate_method_cd[LEN_AIR_RATE_METHOD_CD + LEN_EOS];
    char callng_circle_type_ind[LEN_CALLNG_CIRCLE_TYPE_IND + LEN_EOS];
    char attr_group_name[LEN_ATTR_GROUP_NAME + LEN_EOS];
    char auto_expr_flag[LEN_AUTO_EXPR_FLAG + LEN_EOS];
    char bal_limit_sbscrp_flag[LEN_BAL_LIMIT_SBSCRP_FLAG + LEN_EOS];
    char cntrc_svc_period_qty[LEN_CNTRC_SVC_PERIOD_QTY + LEN_EOS];
    char cntrc_svc_period_unit_cd[LEN_CNTRC_SVC_PERIOD_UNIT_CD + LEN_EOS];
    char copy_member_price_flag[LEN_COPY_MEMBER_PRICE_FLAG + LEN_EOS];
    char eqp_cntrc_type_cd[LEN_EQP_CNTRC_TYPE_CD + LEN_EOS];
    char eqp_rqr_flag[LEN_EQP_RQR_FLAG + LEN_EOS];
    char esclt_page_data_rqr_flag[LEN_ESCLT_PAGE_DATA_RQR_FLAG + LEN_EOS];
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char prcng_pri[LEN_PRCNG_PRI + LEN_EOS];
    char price_lock_ind[LEN_PRICE_LOCK_IND + LEN_EOS];
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
    char usage_origin_text[LEN_USAGE_ORIGIN_TEXT + LEN_EOS];
    char tax_auth_req_flag[LEN_TAX_AUTH_REQ_FLAG + LEN_EOS];
    char svc_search_cat_cd[LEN_SVC_SEARCH_CAT_CD + LEN_EOS];
    char svc_not_avail_dt[LEN_SVC_NOT_AVAIL_DT + LEN_EOS];
    char svc_desc[LEN_SVC_DESC + LEN_EOS];
    char svc_item_eqp_cd[LEN_SVC_ITEM_EQP_CD + LEN_EOS];
    char svc_long_desc[LEN_SVC_LONG_DESC + LEN_EOS];
    char svc_max_purch_qty[LEN_SVC_MAX_PURCH_QTY + LEN_EOS];
    char fax_ind[LEN_FAX_IND + LEN_EOS];
    char svc_minm_purch_qty[LEN_SVC_MINM_PURCH_QTY + LEN_EOS];
    char baud_rate_ind[LEN_BAUD_RATE_IND + LEN_EOS];
    char toll_type_ind[LEN_TOLL_TYPE_IND + LEN_EOS];
    char data_type_ind[LEN_DATA_TYPE_IND + LEN_EOS];
    char svc_auto_provsn_flag[LEN_SVC_AUTO_PROVSN_FLAG + LEN_EOS];
    char eqp_type_cd[LEN_EQP_TYPE_CD + LEN_EOS];
    char custom_bill_text_mask_id[LEN_CUSTOM_BILL_TEXT_MASK_ID + LEN_EOS];
    char custom_bill_text_rqrd_flag[LEN_CUSTOM_BILL_TEXT_RQRD_FLAG + LEN_EOS];
    char custom_bill_prompt_text[LEN_CUSTOM_BILL_PROMPT_TEXT + LEN_EOS];
    char lang_tran_seq_nbr[LEN_LANG_TRAN_SEQ_NBR + LEN_EOS];
    char ntwrk_pre_paid_flag[LEN_NTWRK_PRE_PAID_FLAG + LEN_EOS];
    char tax_incl_flag[LEN_TAX_INCL_FLAG + LEN_EOS];
    char pymt_drives_rate_flag[LEN_PYMT_DRIVES_RATE_FLAG + LEN_EOS];
    char chnl_type_ind[LEN_CHNL_TYPE_IND + LEN_EOS];
    char svc_eff_dur_qty[LEN_SVC_EFF_DUR_QTY + LEN_EOS];
    char waive_setup_ind[LEN_WAIVE_SETUP_IND + LEN_EOS];
    char bonus_elgbl_flag[LEN_BONUS_ELGBL_FLAG + LEN_EOS];
} TSvc;

/*******************************************************************************
 * Estrutura referente a Linha.Pacote (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPacote[LEN_IDPACOTE + LEN_EOS];
    char szIdServico[LEN_IDSERVICO + LEN_EOS];
    char szIdServicoPai[LEN_IDSERVICOPAI + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szDtUltimaAlteracaoOut[LEN_DTULTIMAALTERACAOOUT + LEN_EOS];
} TPacote;

/*******************************************************************************
 * Estrutura referente a Linha.PlanoServico (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdServico[LEN_IDSERVICO + LEN_EOS];
    char szSgServico[LEN_SGSERVICO + LEN_EOS];
    char szNmServico[LEN_NMSERVICO + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szInPlano[LEN_INPLANO + LEN_EOS];
    char szIdServicoSistemaOrigem[LEN_IDSERVICOSISTEMAORIGEM + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPlanoServico;

/*******************************************************************************
 * Estrutura referente a Linha.SerialLinha (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdSerialLinha[LEN_IDSERIALLINHA + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szIdSerialAparelho[LEN_IDSERIALAPARELHO + LEN_EOS];
} TSerialLinha;

/*******************************************************************************
 * Estrutura referente a Linha.SerialAparelho (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdSerialAparelho[LEN_IDSERIALAPARELHO + LEN_EOS];
    char szSgSerialAparelho[LEN_SGSERIALAPARELHO + LEN_EOS];
    char szIdTipoSerial[LEN_IDTIPOSERIAL + LEN_EOS];
    char szIdModoFrequencia[LEN_IDMODOFREQUENCIA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TSerialAparelho;

/*******************************************************************************
 * Estrutura referente a Linha.LinhaBase (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdLinhaBase[LEN_IDLINHABASE + LEN_EOS];
    char szIdAreaRegistro[LEN_IDAREAREGISTRO + LEN_EOS];
    char szNrLinha[LEN_NRLINHA + LEN_EOS];
    char szDtStatusLinha[LEN_DTSTATUSLINHA + LEN_EOS];
    char szNrMin[LEN_NRMIN + LEN_EOS];
    char szNrDigitoLinha[LEN_NRDIGITOLINHA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_NUMBER_ORA + LEN_EOS];
} TLinhaBase;

/*******************************************************************************
 * Estrutura referente a Sbscrp (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char sbscrp_id[LEN_SBSCRP_ID + LEN_EOS];
    char comm_svc_area_id[LEN_COMM_SVC_AREA_ID + LEN_EOS];
    char ixc_svc_prvdr_id[LEN_IXC_SVC_PRVDR_ID + LEN_EOS];
    char prev_ixc_svc_prvdr_id[LEN_PREV_IXC_SVC_PRVDR_ID + LEN_EOS];
    char celulr_actvtn_dt[LEN_CELULR_ACTVTN_DT + LEN_EOS];
    char sbscrp_type_cd[LEN_SBSCRP_TYPE_CD + LEN_EOS];
    char cust_id[LEN_CUST_ID + LEN_EOS];
    char ixc_svc_prvdr_eff_dt[LEN_IXC_SVC_PRVDR_EFF_DT + LEN_EOS];
    char ota_pin_nbr[LEN_OTA_PIN_NBR + LEN_EOS];
    char pager_svc_prvdr_id[LEN_PAGER_SVC_PRVDR_ID + LEN_EOS];
    char pager_access_nbr[LEN_PAGER_ACCESS_NBR + LEN_EOS];
    char pager_pin_nbr[LEN_PAGER_PIN_NBR + LEN_EOS];
    char pager_type_cd[LEN_PAGER_TYPE_CD + LEN_EOS];
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
    char access_nbr[LEN_ACCESS_NBR + LEN_EOS];
    char tax_addr_seq_nbr[LEN_TAX_ADDR_SEQ_NBR + LEN_EOS];
    char sbscrp_svc_expr_dt[LEN_SBSCRP_SVC_EXPR_DT + LEN_EOS];
    char sbscrp_svc_eff_dt[LEN_SBSCRP_SVC_EFF_DT + LEN_EOS];
    char prev_ixc_svc_prvdr_eff_dt[LEN_PREV_IXC_SVC_PRVDR_EFF_DT + LEN_EOS];
    char chnl_type_ind[LEN_CHNL_TYPE_IND + LEN_EOS];
} TSbscrp;

/*******************************************************************************
 * Estrutura referente a Customer.ContaHierarquia (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdConta[LEN_IDCONTA + LEN_EOS];
    char szIdContaPai[LEN_IDCONTAPAI + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TContaHierarquia;

/*******************************************************************************
 * Estrutura referente a Customer.Conta (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdConta[LEN_IDCONTA + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdLayoutConta[LEN_IDLAYOUTCONTA + LEN_EOS];
    char szIdTipoConta[LEN_IDTIPOCONTA + LEN_EOS];
    char szCdConta[LEN_CDCONTA + LEN_EOS];
    char szCdDigitoConta[LEN_CDDIGITOCONTA + LEN_EOS];
    char szCdCicloFaturamento[LEN_CDCICLOFATURAMENTO + LEN_EOS];
    char szNrDiaVencimento[LEN_NRDIAVENCIMENTO + LEN_EOS];
    char szInContaPorEmail[LEN_INCONTAPOREMAIL + LEN_EOS];
    char szTsSincronismoPrincipal[LEN_TSSINCRONISMOPRINCIPAL + LEN_EOS];
    char szSqSincronismoPrincipal[LEN_SQSINCRONISMOPRINCIPAL + LEN_EOS];
    char szTsSincronismoCiclo[LEN_TSSINCRONISMOCICLO + LEN_EOS];
    char szSqSincronismoCiclo[LEN_SQSINCRONISMOCICLO + LEN_EOS];
    char szTsSincronismoStatus[LEN_TSSINCRONISMOSTATUS + LEN_EOS];
    char szSqSincronismoStatus[LEN_SQSINCRONISMOSTATUS + LEN_EOS];
    char szIdStatusConta[LEN_IDSTATUSCONTA + LEN_EOS];
    char szIdContaSistemaOrigem[LEN_IDCONTASISTEMAORIGEM + LEN_EOS];
    char szNrDiaPerioDoCicloDe[LEN_NRDIAPERIODOCICLODE + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TConta;

/*******************************************************************************
 * Estrutura referente a Conta (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char acct_nbr[LEN_ACCT_NBR + LEN_EOS];
    char held_bill_reason_cd[LEN_HELD_BILL_REASON_CD + LEN_EOS];
    char comm_svc_area_id[LEN_COMM_SVC_AREA_ID + LEN_EOS];
    char major_acct_nbr[LEN_MAJOR_ACCT_NBR + LEN_EOS];
    char cust_acct_estab_dt[LEN_CUST_ACCT_ESTAB_DT + LEN_EOS];
    char grntr_cust_id[LEN_GRNTR_CUST_ID + LEN_EOS];
    char cust_type_cd[LEN_CUST_TYPE_CD + LEN_EOS];
    char high_pri_remarks_cntr[LEN_HIGH_PRI_REMARKS_CNTR + LEN_EOS];
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char cust_id[LEN_CUST_ID + LEN_EOS];
    char cr_class_cd[LEN_CR_CLASS_CD + LEN_EOS];
    char pswd_text[LEN_PSWD_TEXT + LEN_EOS];
    char acct_type_cd[LEN_ACCT_TYPE_CD + LEN_EOS];
    char minm_sbscrr_commit_expr_dt[LEN_MINM_SBSCRR_COMMIT_EXPR_DT + LEN_EOS];
    char minm_sbscrr_commit_eff_dt[LEN_MINM_SBSCRR_COMMIT_EFF_DT + LEN_EOS];
    char minm_sbscrr_cntrc_expr_dt[LEN_MINM_SBSCRR_CNTRC_EXPR_DT + LEN_EOS];
    char minm_sbscrr_cntrc_eff_dt[LEN_MINM_SBSCRR_CNTRC_EFF_DT + LEN_EOS];
    char purch_order_eff_dt[LEN_PURCH_ORDER_EFF_DT + LEN_EOS];
    char purch_order_expr_dt[LEN_PURCH_ORDER_EXPR_DT + LEN_EOS];
    char purch_order_nbr[LEN_PURCH_ORDER_NBR + LEN_EOS];
    char real_tm_rate_flag[LEN_REAL_TM_RATE_FLAG + LEN_EOS];
    char single_user_flag[LEN_SINGLE_USER_FLAG + LEN_EOS];
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
    char dormant_flag_cd[LEN_DORMANT_FLAG_CD + LEN_EOS];
    char rprtng_cust_id[LEN_RPRTNG_CUST_ID + LEN_EOS];
    char rprtng_addr_seq_nbr[LEN_RPRTNG_ADDR_SEQ_NBR + LEN_EOS];
    char hirch_rprtng_level_ind[LEN_HIRCH_RPRTNG_LEVEL_IND + LEN_EOS];
    char acct_ever_liable_flag[LEN_ACCT_EVER_LIABLE_FLAG + LEN_EOS];
    char watch_acct_ind[LEN_WATCH_ACCT_IND + LEN_EOS];
    char watch_bal_ind[LEN_WATCH_BAL_IND + LEN_EOS];
    char write_off_adj_amt[LEN_WRITE_OFF_ADJ_AMT + LEN_EOS];
    char write_off_amt[LEN_WRITE_OFF_AMT + LEN_EOS];
    char write_off_pymt_amt[LEN_WRITE_OFF_PYMT_AMT + LEN_EOS];
    char held_bill_expr_dt[LEN_HELD_BILL_EXPR_DT + LEN_EOS];
    char rstrcn_limit_amt[LEN_RSTRCN_LIMIT_AMT + LEN_EOS];
    char hold_bill_ind[LEN_HOLD_BILL_IND + LEN_EOS];
    char mlng_addr_seq_nbr[LEN_MLNG_ADDR_SEQ_NBR + LEN_EOS];
    char acct_intrfc_fail_logon_qty[LEN_ACCT_INTRFC_FAIL_LOGON_QTY + LEN_EOS];
    char dept_cd[LEN_DEPT_CD + LEN_EOS];
    char dep_refund_method_ind[LEN_DEP_REFUND_METHOD_IND + LEN_EOS];
    char cr_bal_amt[LEN_CR_BAL_AMT + LEN_EOS];
    char coll_expr_dt[LEN_COLL_EXPR_DT + LEN_EOS];
    char coll_eff_dt[LEN_COLL_EFF_DT + LEN_EOS];
    char access_nbr[LEN_ACCESS_NBR + LEN_EOS];
    char minm_sbscrr_grace_period_qty[LEN_MINM_SBSCRR_GRACE_PERIOD_QTY + LEN_EOS];
    char minm_sbscrp_qty[LEN_MINM_SBSCRP_QTY + LEN_EOS];
    char minm_sbscrr_thshld_fee_amt[LEN_MINM_SBSCRR_THSHLD_FEE_AMT + LEN_EOS];
    char pre_paid_acct_szr_dt[LEN_PRE_PAID_ACCT_SZR_DT + LEN_EOS];
    char pre_paid_acct_final_dt[LEN_PRE_PAID_ACCT_FINAL_DT + LEN_EOS];
    char pre_paid_disabl_dt_prcss_flag[LEN_PRE_PAID_DISABL_DT_PRCSS_FLAG + LEN_EOS];
    char cr_rstrcn_ind[LEN_CR_RSTRCN_IND + LEN_EOS];
    char last_cr_rstrcn_change_dt[LEN_LAST_CR_RSTRCN_CHANGE_DT + LEN_EOS];
    char large_acct_flag[LEN_LARGE_ACCT_FLAG + LEN_EOS];
    char pre_event_send_flag[LEN_PRE_EVENT_SEND_FLAG + LEN_EOS];
    char instlm_plan_debt_eff_dt[LEN_INSTLM_PLAN_DEBT_EFF_DT + LEN_EOS];
} TCustAcct;

/*******************************************************************************
 * Estrutura referente a SbcrpAsgm (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char sbscrp_asgm_eff_dt[LEN_SBSCRP_ASGM_EFF_DT + LEN_EOS];
    char sbscrp_asgm_expr_dt[LEN_SBSCRP_ASGM_EXPR_DT + LEN_EOS];
    char acct_nbr[LEN_ACCT_NBR + LEN_EOS];
    char sbscrp_id[LEN_SBSCRP_ID + LEN_EOS];
    char sbscrp_seq_nbr[LEN_SBSCRP_SEQ_NBR + LEN_EOS];
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
    char cust_id[LEN_CUST_ID + LEN_EOS];
} TSbscrpAsgm;

/*******************************************************************************
 * Estrutura referente a Conta - AcctStatus (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
    char acct_nbr[LEN_ACCT_NBR + LEN_EOS];
    char acct_status_eff_dt[LEN_ACCT_STATUS_EFF_DT + LEN_EOS];
    char acct_status_eff_tm[LEN_ACCT_STATUS_EFF_TM + LEN_EOS];
    char idstatusconta[LEN_IDSTATUSCONTA + LEN_EOS];
    char acct_status_id[LEN_ACCT_STATUS_ID + LEN_EOS];
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char reason_cd[LEN_REASON_CD + LEN_EOS];
} TAcctStatus;

/*******************************************************************************
 * Estrutura referente a Conta - AcctCycle (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char acct_nbr[LEN_ACCT_NBR + LEN_EOS];
    char cycle_cd[LEN_CYCLE_CD + LEN_EOS];
    char acct_cycle_eff_dt[LEN_ACCT_CYCLE_EFF_DT + LEN_EOS];
    char acct_cycle_eff_tm[LEN_ACCT_CYCLE_EFF_TM + LEN_EOS];
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
} TAcctCycle;

/*******************************************************************************
 * Estrutura referente a Customer.PessoaConta (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaConta[LEN_IDPESSOACONTA + LEN_EOS];
    char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdConta[LEN_IDCONTA + LEN_EOS];
    char szDtPessoaConta[LEN_DTPESSOACONTA + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
} TPessoaConta;

/*******************************************************************************
 * Estrutura referente a Apoio.SistemaOrigem (Tabela)
 *******************************************************************************/
typedef struct {
    long iIdSistemaOrigem;
    char szSgSistemaOrigem[LEN_SG_SISTEMA_ORIGEM + LEN_EOS];
    char szNmSistemaOrigem[LEN_NM_SISTEMA_ORIGEM + LEN_EOS];
} TSistemaOrigem;

/*******************************************************************************
 * Estrutura referente a Customer.ContaEndereco (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdContaEndereco[LEN_IDCONTAENDERECO + LEN_EOS];
    char szIdPessoaEndereco[LEN_IDPESSOAENDERECO + LEN_EOS];
    char szIdConta[LEN_IDCONTA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TContaEndereco;

/*******************************************************************************
 * Estrutura referente a Customer.Pessoa (Tabela)
 *******************************************************************************/
typedef struct {
    char    szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char    szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char    szIdPessoaSistemaOrigem[LEN_IDPESSOASISTEMAORIGEM + LEN_EOS];
    char    szNmPessoa[LEN_NMPESSOA + LEN_EOS];
    char    szNmNome[LEN_NMNOME + LEN_EOS];
    char    szNmNomeMeio[LEN_NMNOMEMEIO + LEN_EOS];
    char    szNmSobrenome[LEN_NMSOBRENOME + LEN_EOS];
    char    szDtChurn[LEN_DTCHURN + LEN_EOS];
    char    szDtCadastro[LEN_DTCADASTRO + LEN_EOS];
    char    szInFalecimentoInformado[LEN_INFALECIMENTOINFORMADO + LEN_EOS];
    char    szDtFalecimento[LEN_DTFALECIMENTO + LEN_EOS];
    char    szIdTipoPessoa[LEN_IDTIPOPESSOA + LEN_EOS];
    char    szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char    szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char    szIdTipoCarteira[LEN_IDTIPOCARTEIRA + LEN_EOS];
    char    szIdUf[LEN_IDUF + LEN_EOS];
    char    szVlrChurnProbabilidade[LEN_VLRCHURNPROBABILIDADE + LEN_EOS];
    char    szDtTipoCarteira[LEN_DTTIPOCARTEIRA + LEN_EOS];
    char    szIdProbInadimplencia[LEN_IDPROBINADIMPLENCIA + LEN_EOS];
    char    szIdChurnProbabilidade[LEN_IDCHURNPROBABILIDADE + LEN_EOS];
    char    szDsCargoContato[LEN_DSCARGOCONTATO + LEN_EOS];
    char    szDsDeptoContato[LEN_DSDEPTOCONTATO + LEN_EOS];
    char    szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char    szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoa;

/*******************************************************************************
 * Estrutura referente a Customer.PessoaEndereco (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaEndereco[LEN_IDPESSOAENDERECO + LEN_EOS];
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szNrSequencia[LEN_NRSEQUENCIA + LEN_EOS];
    char szIdPais[LEN_IDPAIS + LEN_EOS];
    char szNmMunicipio[LEN_NMMUNICIPIO + LEN_EOS];
    char szNmLocalidade[LEN_NMLOCALIDADE + LEN_EOS];
    char szNmBairro[LEN_NMBAIRRO + LEN_EOS];
    char szNmTipoLogradouro[LEN_NMTIPOLOGRADOURO + LEN_EOS];
    char szNmTituloLogradouro[LEN_NMTITULOLOGRADOURO + LEN_EOS];
    char szNmLogradouro[LEN_NMLOGRADOURO + LEN_EOS];
    char szCdLogradouro[40 + LEN_EOS];
    char szInCnl[10 + LEN_EOS];
    char szCdIBGE[7 + LEN_EOS]; 
    char szNrEndereco[LEN_NRENDERECO + LEN_EOS];
    char szDsEnderecoComplemento[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];
    char szInEnderecoPreferencial[LEN_INENDERECOPREFERENCIAL + LEN_EOS];
    char szNrCep[LEN_NRCEP + LEN_EOS];
    char szDtCadastro[LEN_DTCADASTRO + LEN_EOS];
    // char szDtCadastroOut[LEN_DTCADASTROOUT + LEN_EOS];
    char szIdTipoEndereco[LEN_IDTIPOENDERECO + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    // char szDtExpiracaoOut[LEN_DTEXPIRACAOOUT + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szIdUf[LEN_IDUF + LEN_EOS];
    char szDsAosCuidados[LEN_DSAOSCUIDADOS + LEN_EOS];
    char szCdCaixaPostal[LEN_CDCAIXAPOSTAL + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdEnderecoSistemaOrigem[LEN_IDENDERECOSISTEMAORIGEM + LEN_EOS];
    char szInComunicacaoPreferencial[LEN_INCOMUNICACAOPREFERENCIAL + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    // char szDtUltimaAlteracaoOut[LEN_DTULTIMAALTERACAOOUT + LEN_EOS];
} TPessoaEndereco;

/*******************************************************************************
 * Estrutura referente a Pessoa Endereco (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char cust_id[LEN_CUST_ID + LEN_EOS];
    char addr_seq_nbr[LEN_ADDR_SEQ_NBR + LEN_EOS];
    char addr_eff_dt[LEN_ADDR_EFF_DT + LEN_EOS];
    char iduf[LEN_IDUF + LEN_EOS];  // state_cd
    char addr_error_cd[LEN_ADDR_ERROR_CD + LEN_EOS];
    char addr_expr_dt[LEN_ADDR_EXPR_DT + LEN_EOS];
    char geo_city_cd[LEN_GEO_CITY_CD + LEN_EOS];
    char apt_dsgntr_desc[LEN_APT_DSGNTR_DESC + LEN_EOS];
    char geo_county_cd[LEN_GEO_COUNTY_CD + LEN_EOS];
    char apt_nbr[LEN_APT_NBR + LEN_EOS];
    char geo_state_cd[LEN_GEO_STATE_CD + LEN_EOS];
    char atntn_text[LEN_ATNTN_TEXT + LEN_EOS];
    char city_limit_flag[LEN_CITY_LIMIT_FLAG + LEN_EOS];
    char county_name[LEN_COUNTY_NAME + LEN_EOS];
    char delvry_point_cd[LEN_DELVRY_POINT_CD + LEN_EOS];
    char prim_addr_flag[LEN_PRIM_ADDR_FLAG + LEN_EOS];
    char post_office_box_nbr[LEN_POST_OFFICE_BOX_NBR + LEN_EOS];
    char postal_plus_cd[LEN_POSTAL_PLUS_CD + LEN_EOS];
    char postal_cd[LEN_POSTAL_CD + LEN_EOS];
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char house_nbr[LEN_HOUSE_NBR + LEN_EOS];
    char house_dirctn_ind[LEN_HOUSE_DIRCTN_IND + LEN_EOS];
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
    char trlng_dir_cd[LEN_TRLNG_DIR_CD + LEN_EOS];
    char street_suffix_text[LEN_STREET_SUFFIX_TEXT + LEN_EOS];
    char delvry_1_text[LEN_DELVRY_1_TEXT + LEN_EOS];
    char street_name[LEN_STREET_NAME + LEN_EOS];
    char rural_route_type_cd[LEN_RURAL_ROUTE_TYPE_CD + LEN_EOS];
    char delvry_2_text[LEN_DELVRY_2_TEXT + LEN_EOS];
    char rural_route_nbr[LEN_RURAL_ROUTE_NBR + LEN_EOS];
    char delvry_3_text[LEN_DELVRY_3_TEXT + LEN_EOS];
    char dstrbn_center_text[LEN_DSTRBN_CENTER_TEXT + LEN_EOS];
    char city_name[LEN_CITY_NAME + LEN_EOS];
    char bill_name[LEN_BILL_NAME + LEN_EOS];
    char athrty_seq_nbr[LEN_ATHRTY_SEQ_NBR + LEN_EOS];
    char vert_coord_nbr[LEN_VERT_COORD_NBR + LEN_EOS];
    char hrzt_coord_nbr[LEN_HRZT_COORD_NBR + LEN_EOS];
    char idpais[LEN_IDPAIS + LEN_EOS]; // cntry_cd
    char tax_athrty_asgm_ind[LEN_TAX_ATHRTY_ASGM_IND + LEN_EOS];
} TCustAddr;

/*******************************************************************************
 * Estrutura referente a PessoaComunicacao (Tabela)
 *******************************************************************************/
typedef struct {
    char    szIdPessoaComunicacao[LEN_IDPESSOACOMUNICACAO + LEN_EOS];
    char    szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char    szIdTipoComunicacao[LEN_IDTIPOCOMUNICACAO + LEN_EOS];
    char    szNrSequencia[LEN_NRSEQUENCIA + LEN_EOS];
    char    szDsContato[LEN_DSCONTATO + LEN_EOS];
    char    szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char    szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char    szDtCadastro[LEN_DTCADASTRO + LEN_EOS];
    char    szIdComunicacaoSistemaOrigem[LEN_IDCOMUNICACAOSISTEMAORIGEM + LEN_EOS];
    char    szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char    szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char    szInComunicacaoPreferencial[LEN_INCOMUNICACAOPREFERENCIAL + LEN_EOS];
    char    szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char    szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaComunicacao;

/*******************************************************************************
 * Estrutura referente a cntct_phone_nbr (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char    ver_nbr[LEN_VER_NBR + LEN_EOS];
    char    cust_id[LEN_CUST_ID + LEN_EOS];
    char    phone_nbr_xtensn_text[LEN_PHONE_NBR_XTENSN_TEXT + LEN_EOS];
    char    phone_nbr[LEN_PHONE_NBR + LEN_EOS];
    char    idtipocomunicacao[LEN_CNTCT_PHONE_NBR_TYPE_CD + LEN_EOS];  //cntct_phone_nbr_type_cd;
    char    prim_cntct_flag[LEN_PRIM_CNTCT_FLAG + LEN_EOS];
    char    cntct_phone_seq_nbr[LEN_CNTCT_PHONE_SEQ_NBR + LEN_EOS];
    char    cntct_phone_dt[LEN_CNTCT_PHONE_DT + LEN_EOS];
    char    person_id[LEN_PERSON_ID + LEN_EOS];
    char    pager_pin_nbr[LEN_PAGER_PIN_NBR + LEN_EOS];
} TCntctPhoneNbr;

/*******************************************************************************
 * Estrutura referente a customer.documento (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdDocumento[LEN_IDDOCUMENTO + LEN_EOS];
    char szCdCpfCnpjBase[LEN_CDCPFCNPJBASE + LEN_EOS];
    char szCdCnpjFilial[LEN_CDCNPJFILIAL + LEN_EOS];
    char szCdCpfCnpjControle[LEN_CDCPFCNPJCONTROLE + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
    char szSgOrgaoExpedidor[LEN_SGORGAOEXPEDIDOR + LEN_EOS];
    char szDtEmissao[LEN_DTEMISSAO + LEN_EOS];
    char szIdPais[LEN_IDPAIS + LEN_EOS];
    char szIdUf[LEN_IDUF + LEN_EOS];
    char szIdTipoDocumento[LEN_IDTIPODOCUMENTO + LEN_EOS];
} TDocumento;

/*******************************************************************************
 * Estrutura referente a customer.pessoadocumento (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaDocumento[LEN_IDPESSOADOCUMENTO + LEN_EOS];
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szIdDocumento[LEN_IDDOCUMENTO + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdDocumentoSistemaOrigem[LEN_IDDOCUMENTOSISTEMAORIGEM + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
} TPessoaDocumento;

/*******************************************************************************
 * Estrutura referente a PessoaDocumentoB01 (VIEW)
 *******************************************************************************/
typedef struct {
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szIdTipoDocumento[LEN_IDTIPODOCUMENTO + LEN_EOS];
    char szIdPessoaDocumento[LEN_IDPESSOADOCUMENTO + LEN_EOS];
    char szIdDocumento[LEN_IDDOCUMENTO + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
    char szIdDocumentoSistemaOrigem[LEN_IDDOCUMENTOSISTEMAORIGEM + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdPessoaSistemaOrigem[LEN_IDPESSOASISTEMAORIGEM + LEN_EOS];
} TPessoaDocumentoB01;

/*******************************************************************************
 * Estrutura referente a cust_idntfc (XML de Entrada)
 *******************************************************************************/
typedef struct {
    char cust_id[LEN_CUST_ID + LEN_EOS];
//    char cust_idntfc_type_cd[LEN_CUST_IDNTFC_TYPE_CD + LEN_EOS];
    char idtipodocumento[LEN_CUST_IDNTFC_TYPE_CD + LEN_EOS];
    char cust_id_text[LEN_CUST_ID_TEXT + LEN_EOS];
    char prim_cust_idntfc_flag[LEN_PRIM_CUST_IDNTFC_FLAG + LEN_EOS];
    char cust_idntfc_attr_1_text[LEN_CUST_IDNTFC_ATTR_1_TEXT + LEN_EOS];
    char cust_idntfc_attr_2_text[LEN_CUST_IDNTFC_ATTR_2_TEXT + LEN_EOS];
    char cust_idntfc_attr_3_text[LEN_CUST_IDNTFC_ATTR_3_TEXT + LEN_EOS];
    char cust_idntfc_attr_1_cd[LEN_CUST_IDNTFC_ATTR_1_CD + LEN_EOS];
    char cust_idntfc_attr_2_cd[LEN_CUST_IDNTFC_ATTR_2_CD + LEN_EOS];
    char cust_idntfc_attr_3_cd[LEN_CUST_IDNTFC_ATTR_3_CD + LEN_EOS];
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
} TCustIdnTfc;

/*******************************************************************************
 * Estrutura referente a Cust(XML de Entrada)
 *******************************************************************************/
typedef struct {
    char    cust_id[LEN_CUST_ID + LEN_EOS];
    char    comm_svc_area_id[LEN_COMM_SVC_AREA_ID + LEN_EOS];
    char    lang_cd[LEN_LANG_CD + LEN_EOS];
    char    idtipocarteira[LEN_IDTIPOCARTEIRA + LEN_EOS]; // cust_type_cd
    char    auto_type_cd[LEN_AUTO_TYPE_CD + LEN_EOS];
    char    cr_class_cd[LEN_CR_CLASS_CD + LEN_EOS];
    char    birth_dt[LEN_BIRTH_DT + LEN_EOS];
    char    bus_name[LEN_BUS_NAME + LEN_EOS];
    char    cr_class_asgm_tm[LEN_CR_CLASS_ASGM_TM + LEN_EOS];
    char    ctznsp_cntry_cd[LEN_CTZNSP_CNTRY_CD + LEN_EOS];
    char    dept_name[LEN_DEPT_NAME + LEN_EOS];
    char    disabl_direct_mail_flag[LEN_DISABL_DIRECT_MAIL_FLAG + LEN_EOS];
    char    disabl_direct_call_flag[LEN_DISABL_DIRECT_CALL_FLAG + LEN_EOS];
    char    high_pri_remarks_cntr[LEN_HIGH_PRI_REMARKS_CNTR + LEN_EOS];
    char    idsexo[LEN_IDSEXO + LEN_EOS]; // gender_cd
    char    first_name[LEN_FIRST_NAME + LEN_EOS];
    char    ethnic_origin_cd[LEN_ETHNIC_ORIGIN_CD + LEN_EOS];
    char    emp_qty[LEN_EMP_QTY + LEN_EOS];
    char    emplr_name[LEN_EMPLR_NAME + LEN_EOS];
    char    dunn_brad_nbr[LEN_DUNN_BRAD_NBR + LEN_EOS];
    char    drvrs_lcns_state_cd[LEN_DRVRS_LCNS_STATE_CD + LEN_EOS];
    char    std_ocptn_cd[LEN_STD_OCPTN_CD + LEN_EOS];
    char    std_indsty_cd[LEN_STD_INDSTY_CD + LEN_EOS];
    char    search_name[LEN_SEARCH_NAME + LEN_EOS];
    char    search_first_name[LEN_SEARCH_FIRST_NAME + LEN_EOS];
    char    psygrp_cd[LEN_PSYGRP_CD + LEN_EOS];
    char    idtratamento[LEN_IDTRATAMENTO + LEN_EOS]; // prefix_name
    char    person_id[LEN_PERSON_ID + LEN_EOS];
    char    own_res_flag[LEN_OWN_RES_FLAG + LEN_EOS];
    char    ver_nbr[LEN_VER_NBR + LEN_EOS];
    char    title_text[LEN_TITLE_TEXT + LEN_EOS];
    char    tax_id_nbr[LEN_TAX_ID_NBR + LEN_EOS];
    char    suffix_name[LEN_SUFFIX_NAME + LEN_EOS];
    char    idtipopessoa[LEN_IDTIPOPESSOA + LEN_EOS]; // orgztn_person_ind
    char    natl_orgztn_flag[LEN_NATL_ORGZTN_FLAG + LEN_EOS];
    char    idestadocivil[LEN_IDESTADOCIVIL + LEN_EOS]; // mrtl_status_cd
    char    middle_name[LEN_MIDDLE_NAME + LEN_EOS];
    char    last_name[LEN_LAST_NAME + LEN_EOS];
    char    jrsd_cd[LEN_JRSD_CD + LEN_EOS];
    char    income_range_cd[LEN_INCOME_RANGE_CD + LEN_EOS];
    char    hshld_nbr_qty[LEN_HSHLD_NBR_QTY + LEN_EOS];
    char    drvrs_lcns_nbr[LEN_DRVRS_LCNS_NBR + LEN_EOS];
    char    cr_class_asgm_dt[LEN_CR_CLASS_ASGM_DT + LEN_EOS];
    char    bus_year_qty[LEN_BUS_YEAR_QTY + LEN_EOS];
    char    empld_months_qty[LEN_EMPLD_MONTHS_QTY + LEN_EOS];
    char    email_addr_text[LEN_EMAIL_ADDR_TEXT + LEN_EOS];
    char    bus_cust_cntct_name[LEN_BUS_CUST_CNTCT_NAME + LEN_EOS];
    char    bus_cust_cntct_title_text[LEN_BUS_CUST_CNTCT_TITLE_TEXT + LEN_EOS];
    char    cust_intrfc_fail_logon_qty[LEN_CUST_INTRFC_FAIL_LOGON_QTY + LEN_EOS];
    char    cust_intrfc_user_id[LEN_CUST_INTRFC_USER_ID + LEN_EOS];
    char    pswd_rmndr_text[LEN_PSWD_RMNDR_TEXT + LEN_EOS];
    char    pswd_text[LEN_PSWD_TEXT_CUST + LEN_EOS];
    char    tax_lblty_type_cd[LEN_TAX_LBLTY_TYPE_CD + LEN_EOS];
    char    vat_rgstrt_id[LEN_VAT_RGSTRT_ID + LEN_EOS];
    char    value_class_id[LEN_VALUE_CLASS_ID + LEN_EOS];
} TCust;

/*******************************************************************************
 * Estrutura referente a PessoaFisica (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szDtNascimento[LEN_DTNASCIMENTO + LEN_EOS];
    char szNmMae[LEN_NMMAE + LEN_EOS];
    char szNmPai[LEN_NMPAI + LEN_EOS];
    char szIdTratamento[LEN_IDTRATAMENTO + LEN_EOS];
    char szIdEstadoCivil[LEN_IDESTADOCIVIL + LEN_EOS];
    char szIdPais[LEN_IDPAIS + LEN_EOS];
    char szIdSexo[LEN_IDSEXO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaFisica;

/*******************************************************************************
 * Estrutura referente a PessoaJuridica (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szNmPessoaFilial[LEN_NMPESSOAFILIAL + LEN_EOS];
    char szNmFantasia[LEN_NMFANTASIA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaJuridica;

/*******************************************************************************
 * Estrutura referente a LinhaConta (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdLinhaConta[LEN_IDLINHACONTA + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdConta[LEN_IDCONTA + LEN_EOS];
    char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szDtLinhaConta[LEN_DTLINHACONTA + LEN_EOS];
    char szInLinhaMaster[LEN_INLINHAMASTER + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TLinhaConta;

/*******************************************************************************
 * Estrutura referente a Linha.LinhaTelefonica (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdLinhaSistemaOrigem[LEN_IDLINHASISTEMAORIGEM + LEN_EOS];
    char szIdLinhaBase[LEN_IDLINHABASE + LEN_EOS];
    char szIdTipoLinha[LEN_IDTIPOLINHA + LEN_EOS];
    char szIdEstadoLinha[LEN_IDESTADOLINHA + LEN_EOS];
    char szDtEstadoLinha[LEN_DTESTADOLINHA + LEN_EOS];
    char szInDivulgacaoNrLinha[LEN_INDIVULGACAONRLINHA + LEN_EOS];
    char szDtAutorizacaoDivulgacao[LEN_DTAUTORIZACAODIVULGACAO + LEN_EOS];
    char szDtHabilitacao[LEN_DTHABILITACAO + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szTsSincronismoEstado[LEN_TSSINCRONISMOESTADO + LEN_EOS];
    char szSqSincronismoEstado[LEN_SQSINCRONISMOESTADO + LEN_EOS];
    char szCdChurnProbabilidade[LEN_CDCHURNPROBABILIDADE + LEN_EOS];
    char szVlrChurnProbabilidade[LEN_VLRCHURNPROBABILIDADE + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TLinhaTelefonica;

/*******************************************************************************
 * Estrutura referente a Linha.PessoaLinha (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdPessoaLinha[LEN_IDPESSOALINHA + LEN_EOS];
    char szDtPessoaLinha[LEN_DTPESSOALINHA + LEN_EOS];
    char szDtPessoaLinhaOut[LEN_DTPESSOALINHAOUT + LEN_EOS];
    char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szDtUltimaAlteracaoOut[LEN_DTULTIMAALTERACAOOUT + LEN_EOS];
} TPessoaLinha;

/*******************************************************************************
 * Estrutura referente a Customer.PessoaLinhaHistorico (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
    char szDtRelacionamento[LEN_DTRELACIONAMENTO + LEN_EOS];
    char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
    char szNrLinha[LEN_NRLINHA + LEN_EOS];
    char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaLinhaHistorico;

/*******************************************************************************
 * Estrutura referente a Customer.PessoaDePara (Tabela)
 *******************************************************************************/
typedef struct {
    char    szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char    szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char    szIdPessoaOrigem[LEN_IDPESSOAORIGEM + LEN_EOS];
    char    szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char    szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaDePara;

/*******************************************************************************
 * Estrutura referente a Linha.PlanoServicoLinha (Tabela)
 *******************************************************************************/
typedef struct {
    char    szIdServicoLinha[LEN_IDSERVICOLINHA + LEN_EOS];
    char    szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char    szIdServico[LEN_IDSERVICO + LEN_EOS];
    char    szInPlano[LEN_INPLANO + LEN_EOS];
    char    szDtVigenciaInicio[LEN_DTVIGENCIAINICIO + LEN_EOS];
    char    szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char    szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char    szDtVigenciaFinal[LEN_DTVIGENCIAFINAL + LEN_EOS];
    char    szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char    szInContrato[LEN_INCONTRATO + LEN_EOS];
    char    szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char    szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char    szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char    szIdServLinhaSistOrigem[LEN_IDSERVLINHASISTORIGEM + LEN_EOS];
} TPlanoServicoLinha;

/*******************************************************************************
 * Estrutura referente a svc_agrmnt_line_item(XML de Entrada)
 *******************************************************************************/
typedef struct {
    char    sbscrp_id[LEN_SBSCRP_ID + LEN_EOS];
    char    svc_agrmnt_seq_nbr[LEN_SVC_AGRMNT_SEQ_NBR + LEN_EOS];
    char    comm_svc_area_id[LEN_COMM_SVC_AREA_ID + LEN_EOS];
    char    callng_circle_seq_nbr[LEN_CALLNG_CIRCLE_SEQ_NBR + LEN_EOS];
    char    svc_type_cd[LEN_SVC_TYPE_CD + LEN_EOS];
    char    svc_name[LEN_SVC_NAME + LEN_EOS];
    char    access_svc_flag[LEN_ACCESS_SVC_FLAG + LEN_EOS];
    char    reason_cd[LEN_REASON_CD + LEN_EOS];
    char    asgm_pndng_ind[LEN_ASGM_PNDNG_IND + LEN_EOS];
    char    backdt_cancel_eff_dt[LEN_BACKDT_CANCEL_EFF_DT + LEN_EOS];
    char    change_svc_eff_dt[LEN_CHANGE_SVC_EFF_DT + LEN_EOS];
    char    cntrc_eff_dt[LEN_CNTRC_EFF_DT + LEN_EOS];
    char    cntrc_stsfct_dt[LEN_CNTRC_STSFCT_DT + LEN_EOS];
    char    geo_city_cd[LEN_GEO_CITY_CD + LEN_EOS];
    char    geo_county_cd[LEN_GEO_COUNTY_CD + LEN_EOS];
    char    geo_state_cd[LEN_GEO_STATE_CD + LEN_EOS];
    char    instl_paid_qty[LEN_INSTL_PAID_QTY + LEN_EOS];
    char    last_instlm_paid_dt[LEN_LAST_INSTLM_PAID_DT + LEN_EOS];
    char    person_id[LEN_PERSON_ID + LEN_EOS];
    char    prcng_pri[LEN_PRCNG_PRI + LEN_EOS];
    char    price_plan_group_id[LEN_PRICE_PLAN_GROUP_ID + LEN_EOS];
    char    price_plan_swap_cntr[LEN_PRICE_PLAN_SWAP_CNTR + LEN_EOS];
    char    ver_nbr[LEN_VER_NBR + LEN_EOS];
    char    usage_limit_amt[LEN_USAGE_LIMIT_AMT + LEN_EOS];
    char    system_create_dt[LEN_SYSTEM_CREATE_DT + LEN_EOS];
    char    custom_bill_text[LEN_CUSTOM_BILL_TEXT + LEN_EOS];
    char    svc_qty[LEN_SVC_QTY + LEN_EOS];
    char    svc_agrmnt_trmntn_tm[LEN_SVC_AGRMNT_TRMNTN_TM + LEN_EOS];
    char    svc_agrmnt_trmntn_dt[LEN_SVC_AGRMNT_TRMNTN_DT + LEN_EOS];
    char    svc_agrmnt_eff_tm[LEN_SVC_AGRMNT_EFF_TM + LEN_EOS];
    char    svc_agrmnt_eff_dt[LEN_SVC_AGRMNT_EFF_DT + LEN_EOS];
    char    leader_sbscrp_id[LEN_LEADER_SBSCRP_ID + LEN_EOS];
} TSvcAgrmntLineItem;

/*******************************************************************************
 * Estrutura referente ao processo de desmembramento de nomes
 *******************************************************************************/
typedef struct {
    char szNomeCompleto[LEN_NOME + LEN_EOS];
    char szNomePrimeiro[LEN_NOME_PRIMEIRO + LEN_EOS];
    char szNomeMeio[LEN_NOME_MEIO + LEN_EOS];
    char szNomeFim[LEN_NOME_FIM + LEN_EOS];
} TDesmembraNome;

/*******************************************************************************
 * Estrutura referente a Customer.PessoaRelacionamento (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaRelacionamento[LEN_IDPESSOARELACIONAMENTO + LEN_EOS];
    char szIdPessoaDeParaRelacionada[LEN_IDPESSOADEPARARELACIONADA + LEN_EOS];
    char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szDtInicioRelacionamento[LEN_DTINICIORELACIONAMENTO + LEN_EOS];
    char szDtFimRelacionamento[LEN_DTFIMRELACIONAMENTO + LEN_EOS];
    char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaRelacionamento;

/*******************************************************************************
 * Estrutura referente a access_line_nbr_asgm(XML de Entrada)
 *******************************************************************************/
typedef struct {
    char access_nbr[LEN_ACCESS_NBR + LEN_EOS];
    char reason_cd[LEN_REASON_CD + LEN_EOS];
    char sbscrp_id[LEN_SBSCRP_ID + LEN_EOS];
    char access_line_seq_nbr[LEN_ACCESS_LINE_SEQ_NBR + LEN_EOS];
    char sbscrp_access_line_seq_nbr[LEN_SBSCRP_ACCESS_LINE_SEQ_NBR + LEN_EOS];
    char access_nbr_asgm_expr_dt[LEN_ACCESS_NBR_ASGM_EXPR_DT + LEN_EOS];
    char access_nbr_asgm_expr_tm[LEN_ACCESS_NBR_ASGM_EXPR_TM + LEN_EOS];
    char access_nbr_eff_dt[LEN_ACCESS_NBR_EFF_DT + LEN_EOS];
    char create_dt[LEN_CREATE_DT + LEN_EOS];
    char access_nbr_eff_tm[LEN_ACCESS_NBR_EFF_TM + LEN_EOS];
    char person_id[LEN_PERSON_ID + LEN_EOS];
    char idestadolinha[LEN_SWITCH_STATUS_CD + LEN_EOS]; //switch_status_cd
    char ver_nbr[LEN_VER_NBR + LEN_EOS];
    char price_plan_group_id[LEN_PRICE_PLAN_GROUP_ID + LEN_EOS];
    char mgrt_from_access_nbr[LEN_MGRT_FROM_ACCESS_NBR + LEN_EOS];
} TAccessLineNbrAsgm;

#endif /* GLOBAL */
