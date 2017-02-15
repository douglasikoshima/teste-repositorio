///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase Global
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Renato Striitzel Russo
 * @author  Eder Martins
 * @version $Revision: 1.1.2.1.110.1.8.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/03/25 15:08:54 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef GLOBAL
#define GLOBAL


#define SUCESSO_EXECUCAO                "60I0000"
#define E_APLICACAO_PREPAGO             "12E1251"
#define E_PARAMETRO_PREPAGO             "12E1252"
#define W_DELETE_PREPAGO                "12W1250"
#define W_SEQUENCE_PREPAGO              "12W1251"


#define ID_SG_FRONTOFFICCE              "FO"
#define SG_NGIN                         "NGN"
/*******************************************************************************
 * definicoes para GRAVALEGADO
 *******************************************************************************/
#define GRAVALEGADO_SUCESSO          "OK"
#define GRAVALEGADO_FALHA            "NOK"

/*******************************************************************************
 * definicoes para Conta Grupo
 *******************************************************************************/
#define TIPO_CONTA_GRUPO                "GRU"
#define TIPO_ENDERECO_COBRANCA          "CO"

/******************************************************************************
*  DEFINICOES PARA PESQUISA DE VALORES ESPECIFICOS NA TABELA:
*    CUSTOMER.VALORPOSSIVEL
******************************************************************************/
#define PREPAGO_ID_ESCOLARIDADE	"90"
#define PREPAGO_ID_OCUPACAO		"107"

/*******************************************************************************
 * definicoes globais
 *******************************************************************************/
#define LEN_DATE_ORA                    14    // YYYYMMDDHHMMSS
#define LEN_NUMBER_ORA                  25
#define LEN_EOS                         1
#define LEN_AUX                         255
#define LEN_HEADER                      255
#define TIPO_COMUNICACAO_EMAIL          "5"
#define ID_USUARIO_ALTERACAO            "666"
#define LEN_RETURN_MESSAGE              256
#define LEN_MESSAGE_EXCEPTION           255
#define LEN_CAMPO_TEXTO_PADRAO_FO       255
#define QTD_ARRAY_PESSOA_DE_PARA        2
#define LEN_IDGRUPO                     255

#define ID_NULL                         "0"
#define SEM_VALOR                       "0"

/*******************************************************************************
 * definicoes referentes aos tipos de documentos
 *******************************************************************************/
#define ID_CPF                          1
#define ID_RG                           4
#define ID_PASS                         62
#define ID_RNE                          13
#define ID_CPR                          30
#define ID_CN                           31
#define ID_CNPJ                         2
#define ID_IE                           14
#define ID_EM                           9
#define ID_CNAE                         29

/*******************************************************************************
 * definicoes referentes aos tipos de documentos (SIGLAS)
 *******************************************************************************/
#define SG_CPF                          "CPF"
#define SG_FCPF                         "FCPF"
#define SG_KLCPF                        "KLCPF"
#define SG_RG                           "RG"
#define SG_PASS                         "PAS"
#define SG_RNE                          "RNE"
#define SG_CPR                          "CPR"
#define SG_CN                           "CIP"
#define SG_CNPJ                         "CNPJ"
#define SG_OCNPJ                        "OCNPJ"
#define SG_UCNPJ                        "UCNPJ"
#define SG_IE                           "IE"
#define SG_EM                           "IM"
#define SG_CNAE                         "CNAE"

/*******************************************************************************
 * definicoes referentes aos tipos de exceçoes.
 *******************************************************************************/
#define ERRO_EXECUCAO                   1
#define ERRO_PARAMETRO_NULL             2
#define ERRO_SEQUENCE                   3
#define ERRO_REGISTRO_NAO_ENCONTRADO    4
#define ERRO_LEGADO_SET_INTERCEPTACAO   5
#define ERRO_LEGADO_SET_CLIENTE         6

/*******************************************************************************
 * definicoes referentes ao processo de desmembramento de nomes
 *******************************************************************************/
#define LEN_NOME                        255
#define LEN_NOME_PRIMEIRO               LEN_NOME
#define LEN_NOME_MEIO                   LEN_NOME
#define LEN_NOME_FIM                    LEN_NOME

/*******************************************************************************
 * definicoes referentes ao tipo de pessoa
 *******************************************************************************/
#define PESSOA_FISICA                   1
#define PESSOA_JURIDICA                 2

#define PP_PESSOA_FISICA                "PF"
#define PP_PESSOA_JURIDICA              "PJ"

/*******************************************************************************
 * definicoes referentes a tipo conta
 *******************************************************************************/
#define ID_TIPO_CONTA_PRE               "3"
#define ID_TIPO_CONTA_GRUPO             "4"

/*******************************************************************************
 * definicoes referentes ao tipo de sistema
 *******************************************************************************/
#define ID_ATLYS                        "1"
#define ID_PPS                          "2"
#define ID_ACS                          "3"
#define ID_DW                           "4"
#define ID_URA                          "5"
#define ID_EASYPHONE                    "6"
#define ID_FRONTOFFICE                  "7"
#define ID_NGIN                         "272"

/*******************************************************************************
 * definicoes referentes ao Tipo de Endereco de Cobranca
 *******************************************************************************/
#define ID_TIPO_ENDERECO_COBRANCA       "1"

/*******************************************************************************
 * definicoes referentes ao Tipo de Relacionamento
 *******************************************************************************/
#define ID_TIPO_RELACIONAMENTO_U         "1"  // Usuario
#define ID_TIPO_RELACIONAMENTO_C         "2"  // Cliente
#define ID_TIPO_RELACIONAMENTO_R         "3"  // Consultor Relacionamento
#define ID_TIPO_RELACIONAMENTO_V         "4"  // Executivo de Vendas
#define ID_TIPO_RELACIONAMENTO_GC        "5"  // Gestor da Conta
#define ID_TIPO_RELACIONAMENTO_P         "6"  // Prospect
#define ID_TIPO_RELACIONAMENTO_NC        "7"  // Não Cliente
#define ID_TIPO_RELACIONAMENTO_GRUPOC    "8"  // Conta Grupo

/*******************************************************************************
 * definicoes referentes as Siglas de Tipo de Relacionamento
 *******************************************************************************/
#define SG_TIPO_RELACIONAMENTO_CG        "GRP" // Conta Grupo
#define SG_TIPO_RELACIONAMENTO_C         "C"   // Cliente

/*******************************************************************************
 * definicoes referentes aos tipos de linha.
 *******************************************************************************/
#define ID_TIPO_LINHA_POS_PAGO           "1"
#define ID_TIPO_LINHA_PRE_PAGO           "2"

/*******************************************************************************
 * definicoes referentes aos tipos de endereço.
 *******************************************************************************/
#define ID_TIPO_ENDERECO_FI              "0"  // Falso Insert
#define ID_TIPO_ENDERECO_RES             "1"  // Residencial
#define ID_TIPO_ENDERECO_COM             "2"  // Comercial
#define ID_TIPO_ENDERECO_COB             "3"  // Cobrança
#define ID_TIPO_ENDERECO_COR             "4"  // Correspondencia

/*******************************************************************************
 * definicoes referentes aos tipos de estado civil
 *******************************************************************************/
#define ID_TIPO_EC_SOLTEIRO               1
#define ID_TIPO_EC_CASADO                 2
#define ID_TIPO_EC_VIUVO                  4
#define ID_TIPO_EC_DIVORCIADO             5

#define SG_TIPO_EC_SOLTEIRO               "S"
#define SG_TIPO_EC_CASADO                 "C"
#define SG_TIPO_EC_VIUVO                  "V"
#define SG_TIPO_EC_DIVORCIADO             "D"

/*******************************************************************************
 * definicoes referentes aos tipos de sexo
 *******************************************************************************/
#define ID_SEXO_MASCULINO               1
#define ID_SEXO_FEMININO                2
#define ID_SEXO_NAO_CLASSIFICADO        0

#define SG_SEXO_MASCULINO               "M"
#define SG_SEXO_FEMININO                "F"
#define SG_SEXO_NAO_CLASSIFICADO        "NC"

/*******************************************************************************
 * definicoes referentes aos tipos de comunicacao
 *******************************************************************************/
#define ID_TIPO_COMUNICACAO_TEL_RES                 1
#define ID_TIPO_COMUNICACAO_TEL_COM                 2
#define ID_TIPO_COMUNICACAO_FAX                     5
#define ID_TIPO_COMUNICACAO_EMAIL_PARTICULAR        6
#define ID_TIPO_COMUNICACAO_PASSAPORTE              9

/*******************************************************************************
 * definicoes referentes ao processo de geracao de senhas
 *******************************************************************************/
#define IDTIPOHISTORICOSENHA            1
#define IDCANAL                         6
#define	IDTIPOSISTEMA                   2

/*******************************************************************************
 * definicoes referentes a Sexo (Tabela)
 *******************************************************************************/
#define LEN_IDSEXO                     LEN_NUMBER_ORA
#define LEN_SGSEXO                     LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_DSSEXO                     LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_IDUSUARIOALTERACAO         LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO          LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a TipoComunicacao (Tabela)
 *******************************************************************************/
#define LEN_IDTIPOCOMUNICACAO          LEN_NUMBER_ORA
#define LEN_SGCLASSIFICACAO            255
#define LEN_SGTIPOCOMUNICACAO          LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_DSTIPOCOMUNICACAO          LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_IDFORMARETORNO             LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_IDUSUARIOALTERACAO         LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO          LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a EstadoCivil (Tabela)
 *******************************************************************************/
#define LEN_IDESTADOCIVIL              LEN_NUMBER_ORA
#define LEN_SGESTADOCIVIL              LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_DSESTADOCIVIL              LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_IDUSUARIOALTERACAO         LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO          LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a TipoConta (Tabela)
 *******************************************************************************/
#define LEN_IDTIPOCONTA                LEN_NUMBER_ORA
#define LEN_SGTIPOCONTA                LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_DSTIPOCONTA                LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_IDUSUARIOALTERACAO         LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO          LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a TipoEnderecoCobranca (Tabela)
 *******************************************************************************/
#define LEN_IDTIPOENDERECOCOBRANCA     LEN_NUMBER_ORA
#define LEN_SGTIPOENDERECOCOBRANCA     LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_SGCLASSIFICACAO            255
#define LEN_DSTIPOENDERECOCOBRANCA     LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_IDUSUARIOALTERACAO         LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO          LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Parametro (Tabela)
 *******************************************************************************/
#define LEN_IDPARAMETRO                LEN_NUMBER_ORA
#define LEN_CDPARAMETRO                LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_DSPARAMETRO                LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_DSVALORPARAMETRO           LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_IDUSUARIOALTERACAO         LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO          LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a TipoRelacionamento (Tabela)
 *******************************************************************************/
#define LEN_IDTIPORELACIONAMENTO       LEN_NUMBER_ORA
#define LEN_SGTIPORELACIONAMENTO       LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_NMTIPORELACIONAMENTO       LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_IDUSUARIOALTERACAO         LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO          LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a apoio.cnae (Tabela)
 *******************************************************************************/
#define LEN_IDCNAE                      LEN_NUMBER_ORA
#define LEN_CDCNAE                      10
#define LEN_DSCNAE                      255
#define LEN_CDCFOP                      LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a apoio.uf (Tabela)
 *******************************************************************************/
#define LEN_IDUF                        LEN_NUMBER_ORA
#define LEN_SGUF                        255
#define LEN_NMUF                        255
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_NRFUSOHORARIO               LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Customer.PessoaSegmentacaoHistorico (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOASEGMENTACAO         LEN_NUMBER_ORA
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDSEGMENTACAO               LEN_NUMBER_ORA
#define LEN_VLRENTABILIDADE             LEN_NUMBER_ORA
#define LEN_DTSEGMENTACAO               LEN_DATE_ORA
#define LEN_DTRENTABILIDADE             LEN_DATE_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a tibco_ow.p_registracontato (Tabela)
 *******************************************************************************/
#define LEN_XML_REGCONTATO              4000
#define LEN_IDREGISTRACONTATO           LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Apoio.cfop (Tabela)
 *******************************************************************************/
#define LEN_IDCFOP                      LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Customer.PessoaSegmentacao (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDPESSOASEGMENTACAO         LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a linha.permissaolinhapup
 *******************************************************************************/
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_SGPERMISSAOPUP              10
#define LEN_INATIVO                     1
#define LEN_DTEXPIRACAO_CUSTOM          LEN_NUMBER_ORA // contem os dias a serem adicionados no sysdate
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Apoio.SistemaOrigem (Tabela)
 *******************************************************************************/
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_SGSISTEMAORIGEM             255
#define LEN_NMSISTEMAORIGEM             255
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_INDISPONIVEL                LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a FilaSetClientInfo (Tabela)
 *******************************************************************************/
#define LEN_IDFILASETCLIENTINFO         LEN_NUMBER_ORA
#define LEN_CDERRO                      20
#define LEN_DSERRO                      150
#define LEN_DTERRO                      LEN_DATE_ORA
#define LEN_XML                         2000
#define LEN_INTERCEPTADO                3


/*******************************************************************************
 * definicoes referentes a Customer.PessoaValorPossivel (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOAVALORPOSSIVEL       LEN_NUMBER_ORA
#define LEN_IDVALORPOSSIVEL             LEN_NUMBER_ORA
#define LEN_IDPESSOA                    LEN_NUMBER_ORA
#define LEN_DTEXCLUSAO                  LEN_DATE_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

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
#define LEN_SGTIPORELACIONAMENTO        LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a customer.pessoalinhapre (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOALINHA               LEN_NUMBER_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_INMUDANCATITULARIDADE       1
#define LEN_INSINCRONISMO               1
#define LEN_INUSUARIONAOINFORMADO       LEN_NUMBER_ORA

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
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_DTULTIMAALTERACAOOUT        10
#define LEN_INENDERECOSUJO              1

/*******************************************************************************
 * definicoes referentes a Customer.ContaEndereco (Tabela)
 *******************************************************************************/
#define LEN_IDCONTAENDERECO             LEN_NUMBER_ORA
#define LEN_IDPESSOAENDERECO            LEN_NUMBER_ORA
#define LEN_IDCONTA                     LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Linha.Pacote (Tabela)
 *******************************************************************************/
#define LEN_IDPACOTE                    LEN_NUMBER_ORA
#define LEN_IDSERVICO                   LEN_NUMBER_ORA
#define LEN_IDSERVICOPAI                LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_TSSINCRONISMO               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMO               LEN_NUMBER_ORA
#define LEN_DTEXPIRACAO                 LEN_DATE_ORA

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
#define LEN_INSERVICOINTRAGRUPO         LEN_NUMBER_ORA
#define LEN_CDTIPOSERVICOSISTEMAORIG    255

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
 * definicoes referentes a Customer.PessoaAtendimentoFO (Tabela)
 *******************************************************************************/
#define LEN_IDPESSOAATENDIMENTOFO       LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Linha.LinhaBase (Tabela)
 *******************************************************************************/
#define LEN_IDLINHABASE                 LEN_NUMBER_ORA
#define LEN_IDAREAREGISTRO              LEN_NUMBER_ORA
//#define LEN_NRLINHA                     LEN_NUMBER_ORA -- JA ESTA DEFINIDO!
#define LEN_DTSTATUSLINHA               LEN_DATE_ORA
#define LEN_NRMIN                       255
#define LEN_NRDIGITOLINHA               LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_IDESTADOLINHA               LEN_NUMBER_ORA
#define LEN_SQSINCRONISMOESTADO         LEN_NUMBER_ORA
#define LEN_TSSINCRONISMOESTADO         LEN_NUMBER_ORA

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
#define LEN_DTEFETIVACICLO              255
#define LEN_DTEFETIVASTATUS             255

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
 * definicoes referentes a apoio.tipoenderecocobranca (Tabela)
 *******************************************************************************/
#define LEN_IDTIPOENDERECOCOBRANCA      LEN_NUMBER_ORA

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
#define LEN_NMCONTATO                   255

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
 * definicoes referentes a Apoio.AreaRegistroBloqueado (Tabela)
 *******************************************************************************/
#define LEN_IDAREAREGISTROBLOQUEADO     LEN_NUMBER_ORA
#define LEN_IDAREAREGISTRO              LEN_NUMBER_ORA
#define LEN_INBLOQUEADO                 LEN_NUMBER_ORA
#define LEN_CDAREAREGISTROBLOQ          LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Apoio.TipoDocumento (Tabela)
 *******************************************************************************/
#define LEN_IDTIPODOCUMENTO             LEN_NUMBER_ORA
#define LEN_SGTIPODOCUMENTO             255
#define LEN_DSTIPODOCUMENTO             255
#define LEN_IDTIPOPESSOA                LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_NRPRIORIDADE                LEN_NUMBER_ORA
#define LEN_SGCLASSIFICACAO             255

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
#define LEN_DTFUNDACAO                  LEN_DATE_ORA

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
#define LEN_SQSERVICOINTRAGRUPO         LEN_NUMBER_ORA

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
 * definicoes referentes ao XML de entrada PrePagoPessoaVO (primeiro nivel)
 *******************************************************************************/
#define LEN_IDPESSOA                    LEN_NUMBER_ORA
#define LEN_INTIPOPESSOA                LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_INATUAL                     LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_INCLIENTEPOSPAGO            LEN_CAMPO_TEXTO_PADRAO_FO
#define LEN_INCLIENTEUSUARIO            LEN_NUMBER_ORA
#define LEN_INLISTAS                    LEN_NUMBER_ORA
#define LEN_IDLINHA                     LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes ao processo de obtencao dos dados XML
 *******************************************************************************/
#define OBRIGATORIO                     1
#define NOBRIGATORIO                    0

/*******************************************************************************
 * definicoes referentes ao processo de inclusao de dados para o processo Legado
 *******************************************************************************/
#define LEN_NUMBER_LEGADO               15
#define LEN_NOME_PESSOA_LEGADO          50
#define LEN_IE_LEGADO                   15
#define LEN_CPF_LEGADO                  16
#define LEN_RG_LEGADO                   15
#define LEN_ORGAO_EXPEDITOR_LEGADO      20
#define LEN_ESTADO_EXPEDICAO_LEGADO      2
#define LEN_PASSAPORTE_LEGADO            8
#define LEN_CNPJ_LEGADO                 15
#define LEN_CNAE_LEGADO                  3
#define LEN_TELEFONE_LEGADO             20
#define LEN_NOME_CONTATO_LEGADO         50
#define LEN_LOGRADOURO_LEGADO           10
#define LEN_ARRUAMENTO_LEGADO           60
#define LEN_COMPLEMENTO_LEGADO          32
#define LEN_BAIRRO_LEGADO               32
#define LEN_CEP_LEGADO                   8
#define LEN_CIDADE_LEGADO               60
#define LEN_FAX_LEGADO                  20
#define LEN_EMAIL_LEGADO                80
#define LEN_ESTADO_LEGADO                2
#define LEN_NUMERO_LEGADO               10
#define LEN_DATE_LEGADO                 10

/*******************************************************************************
 * Macros para manipulacao dos dados XML (Dados Legado)
 *******************************************************************************/
#define GETTREE_LEGADO(CampoDestino, DOMNode, Tag, Nivel, Obrigatoriedade, Tamanho, Mensagem) \
{\
    char szMessage[LEN_RETURN_MESSAGE + LEN_EOS]; \
    char *pTree; \
    ULOG("GETTREE_LEGADO - CampoDestino[%s](%d) Tag[%s] Nivel[%s] Obrigatoriedade[%s] Tamanho(%d)", #CampoDestino, sizeof(CampoDestino), Tag, #Nivel, #Obrigatoriedade, Tamanho); \
    memset(CampoDestino, 0x00, sizeof(CampoDestino)); \
    if((pTree = walkTree(DOMNode, Tag, Nivel)) != NULL) \
    {\
        if((strlen(pTree) == 0) && (Obrigatoriedade == OBRIGATORIO)) \
        {\
            sprintf(szMessage, "Campo %s sem valor", strlen(Mensagem) > 0 ? Mensagem : Tag); \
            XMLString::release(&pTree); \
            throw PrePagoException(ERRO_PARAMETRO_NULL, szMessage); \
        }\
        if(strlen(pTree) > Tamanho) \
        {\
            ULOG("GETTREE_LEGADO - EFETUANDO TRUNCAMENTO de [%s] para [%.*s]", pTree, Tamanho, pTree); \
        }\
        if((sizeof(CampoDestino)-LEN_EOS) < Tamanho) \
        {\
            sprintf(szMessage, "Campo %s maior que o esperado", strlen(Mensagem) > 0 ? Mensagem : Tag); \
            XMLString::release(&pTree); \
            throw PrePagoException(ERRO_PARAMETRO_NULL, szMessage); \
        }\
        memcpy(CampoDestino, pTree, Tamanho); \
        ULOG("GETTREE_LEGADO - CampoDestino[%s](%d)", CampoDestino, strlen(CampoDestino)); \
        XMLString::release(&pTree); \
    }\
    else \
    {\
        if(Obrigatoriedade == OBRIGATORIO) \
        {\
            sprintf(szMessage, "Campo %s obrigatorio", strlen(Mensagem) > 0 ? Mensagem : Tag); \
            throw PrePagoException(ERRO_PARAMETRO_NULL, szMessage); \
        }\
    }\
}

/*******************************************************************************
 * Macros para manipulacao dos dados XML
 *******************************************************************************/
#define GETTREE(CampoDestino, DOMNode, Tag, Nivel, Obrigatoriedade, Mensagem) \
{\
    char szMessage[LEN_RETURN_MESSAGE + LEN_EOS]; \
    char *pTree; \
    ULOG("GETTREE - CampoDestino[%s](%d) Tag[%s] Nivel[%s] Obrigatoriedade[%s]", #CampoDestino, sizeof(CampoDestino), Tag, #Nivel, #Obrigatoriedade); \
    memset(CampoDestino, 0x00, sizeof(CampoDestino)); \
    if((pTree = walkTree(DOMNode, Tag, Nivel)) != NULL) \
    {\
        if((sizeof(CampoDestino)-LEN_EOS) < (int)strlen(pTree)) \
        {\
            sprintf(szMessage, "Campo %s maior que o esperado", strlen(Mensagem) > 0 ? Mensagem : Tag); \
            XMLString::release(&pTree); \
            throw PrePagoException(ERRO_PARAMETRO_NULL, szMessage); \
        }\
        else if((strlen(pTree) == 0) && (Obrigatoriedade == OBRIGATORIO)) \
        {\
            sprintf(szMessage, "Campo %s sem valor", strlen(Mensagem) > 0 ? Mensagem : Tag); \
            XMLString::release(&pTree); \
            throw PrePagoException(ERRO_PARAMETRO_NULL, szMessage); \
        }\
        strcpy(CampoDestino, pTree); \
        ULOG("GETTREE - CampoDestino[%s] Valor[%s]", #CampoDestino, pTree); \
        XMLString::release(&pTree); \
    }\
    else \
    {\
        if(Obrigatoriedade == OBRIGATORIO) \
        {\
            sprintf(szMessage, "Campo %s obrigatorio", strlen(Mensagem) > 0 ? Mensagem : Tag); \
            throw PrePagoException(ERRO_PARAMETRO_NULL, szMessage); \
        }\
    }\
}

#define GETXML(Struct, Campo) \
{\
    char szAloca[LEN_RETURN_MESSAGE + LEN_EOS]; \
    char *p = walkTree(dnode, #Campo, 0); \
    ULOG("GETXML - pointer (%p)", p); \
    ULOG("GETXML - sizeof(%s.%s) - (%d)", #Struct, #Campo, (sizeof(Struct.Campo) - LEN_EOS)); \
    ULOG("Campo [%s] - Valor [%s] - Tamanho (%d)", #Campo, p ? p : "*** CAMPO NAO EXISTENTE ***", p ? strlen(p) : 0); \
    if(p != NULL) \
    {\
        if((sizeof(Struct.Campo)-LEN_EOS) < strlen(p)) \
        {\
           ULOG("Campo XML [%s] maior que o esperado", #Campo); \
           sprintf(szAloca, "Campo XML [%s] maior que o esperado", #Campo); \
           XMLString::release(&p); \
           throw PrePagoException(ERRO_PARAMETRO_NULL, szAloca); \
        }\
    }\
    strcpy(Struct.Campo,p ? p : ""); \
    if(p != NULL) \
    {\
        XMLString::release(&p); \
    }\
}

#define GETXML_OBRIGATORIO(Struct, Campo) \
{\
    char szAloca[LEN_RETURN_MESSAGE + LEN_EOS]; \
    char *p = walkTree(dnode, #Campo, 0); \
    ULOG("GETXML_OBRIGATORIO - pointer (%p)", p); \
    ULOG("GETXML_OBRIGATORIO - sizeof(%s.%s) - (%d)", #Struct, #Campo, (sizeof(Struct.Campo) - LEN_EOS)); \
    ULOG("Campo [%s] - Valor [%s] - Tamanho (%d)", #Campo, p ? p : "*** CAMPO NAO EXISTENTE ***", p ? strlen(p) : 0); \
    strcpy(Struct.Campo, "0"); \
    if(p == NULL) \
    { \
        ULOG("Parametro obrigatorio inexistente [%s]", #Campo); \
        sprintf(szAloca, "Parametro obrigatorio inexistente [%s]", #Campo); \
    } \
    else \
    { \
        if((sizeof(Struct.Campo)-LEN_EOS) < strlen(p)) \
        { \
            ULOG("Campo XML [%s] maior que o esperado", #Campo); \
            sprintf(szAloca, "Campo XML [%s] maior que o esperado", #Campo); \
            XMLString::release(&p); \
            throw PrePagoException(ERRO_PARAMETRO_NULL, szAloca); \
        } \
        else if(strlen(p) == 0) \
        { \
            ULOG("Parametro obrigatorio vazio [%s]", #Campo); \
            sprintf(szAloca, "Parametro obrigatorio vazio [%s]", #Campo); \
        } \
        else \
        { \
            ULOG("Parametro obrigatorio obtido [%s]", p); \
            strcpy(Struct.Campo, p); \
        } \
        XMLString::release(&p); \
    } \
}

#define GET_HEADER(Campo, Funcao, Obrigatoriedade) \
{\
    char szAloca[LEN_RETURN_MESSAGE + LEN_EOS], *p; \
    p = Funcao; \
    ULOG("GET_HEADER - Funcao[%s] Valor [%s](%p)", #Funcao, p, p); \
    memset(Campo, 0x00, sizeof(Campo)); \
    if(p == NULL) \
    {\
        sprintf(szAloca, "Parametro obrigatorio do header XML inexistente [%s] Funcao [%s]", #Campo, #Funcao); \
        ULOG("%s", szAloca); \
        if(Obrigatoriedade) \
        {\
            throw PrePagoException(ERRO_PARAMETRO_NULL, szAloca); \
        }\
    }\
    else \
    {\
        strcpy(Campo, p); \
        alltrim(Campo);\
        if(strlen(Campo) == 0)\
        {\
            sprintf(szAloca, "Parametro obrigatorio do header sem valor [%s] Funcao [%s]", #Campo, #Funcao); \
            ULOG("%s", szAloca); \
            if(Obrigatoriedade) \
            {\
                throw PrePagoException(ERRO_PARAMETRO_NULL, szAloca); \
            }\
        }\
        ULOG("Variavel[%s] de Valor[%s] Funcao[%s]", #Campo, Campo, #Funcao); \
        XMLString::release(&p); \
    }\
}

/*******************************************************************************
 * Macros para manipulacao de dados ProC/C++
 *******************************************************************************/
#define STRCPY_TO_ORA(dest, source) { \
    dest.len = (unsigned short) strlen(source);\
    strncpy((char *) dest.arr, (const char *) source, (size_t)dest.len); \
    ULOG("STRCPY_TO_ORA -> Valor[%.*s] Destino[%s] Origem[%s]", (int)dest.len, dest.arr, #dest, #source); }

#define STRCPY_FROM_ORA(dest, source) { \
    dest[source.len] = 0; \
    strncpy((char *)dest,(const char *)source.arr, source.len); \
    ULOG("STRCPY_FROM_ORA -> Valor[%s] Destino[%s] Origem[%s]", dest, #dest, #source); }

/*******************************************************************************
 * Macros gerais
 *******************************************************************************/
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

/*******************************************************************************
 * Estrutura referente a Customer.PessoaSegmentacao (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdPessoaSegmentacao[LEN_IDPESSOASEGMENTACAO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaSegmentacao;

/*******************************************************************************
 * Estrutura referente a Customer.PessoaSegmentacaoHistorico (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaSegmentacao[LEN_IDPESSOASEGMENTACAO + LEN_EOS];
    char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdSegmentacao[LEN_IDSEGMENTACAO + LEN_EOS];
    char szVlRentabilidade[LEN_VLRENTABILIDADE + LEN_EOS];
    char szDtSegmentacao[LEN_DTSEGMENTACAO + LEN_EOS];
    char szDtRentabilidade[LEN_DTRENTABILIDADE + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaSegmentacaoHistorico;

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
 * Estrutura referente a Linha.Pacote (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPacote[LEN_IDPACOTE + LEN_EOS];
    char szIdServico[LEN_IDSERVICO + LEN_EOS];
    char szIdServicoPai[LEN_IDSERVICOPAI + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
} TPacote;

/*******************************************************************************
 * Estrutura referente a Customer.Parametro (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdParametro[LEN_IDPARAMETRO + LEN_EOS];
    char szCdParametro[LEN_CDPARAMETRO + LEN_EOS];
    char szDsParametro[LEN_DSPARAMETRO + LEN_EOS];
    char szDsValorParametro[LEN_DSVALORPARAMETRO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
}TParametro;

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
    char szInServicoIntraGrupo[LEN_INSERVICOINTRAGRUPO + LEN_EOS];
    char szCdTipoServicoSistemaOrig[LEN_CDTIPOSERVICOSISTEMAORIG + LEN_EOS];
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
 * Estrutura referente a customer.pessoalinhapre (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaLinha[LEN_IDPESSOALINHA + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szInMudancaTitularidade[LEN_INMUDANCATITULARIDADE + LEN_EOS];
    char szInSincronismo[LEN_INSINCRONISMO + LEN_EOS];
    char szInUsuarioNaoInformado[LEN_INUSUARIONAOINFORMADO + LEN_EOS];
} TPessoaLinhaPre;

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
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szIdEstadoLinha[LEN_IDESTADOLINHA + LEN_EOS];
    char szSqSincronismoEstado[LEN_SQSINCRONISMOESTADO + LEN_EOS];
    char szTsSincronismoEstado[LEN_TSSINCRONISMOESTADO + LEN_EOS];
} TLinhaBase;

/*******************************************************************************
 * Estrutura referente a Linha.PessoaValorPossivel (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaValorPossivel[LEN_IDPESSOAVALORPOSSIVEL + LEN_EOS];
    char szIdValorPossivel[LEN_IDVALORPOSSIVEL + LEN_EOS];
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szDtExclusao[LEN_DTEXCLUSAO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaValorPossivel;

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
 * Estrutura referente a linha.permissaolinhapup
 *******************************************************************************/
typedef struct {
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szSgPermissaoPUP[LEN_SGPERMISSAOPUP + LEN_EOS];
    char szInAtivo[LEN_INATIVO + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO_CUSTOM + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TPermissaoLinhaPUP;

/*******************************************************************************
 * Estrutura referente a apoio.uf (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdUf[LEN_IDUF + LEN_EOS];
    char szSgUf[LEN_SGUF + LEN_EOS];
    char szNmUf[LEN_NMUF + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szNrFusoHorario[LEN_NRFUSOHORARIO + LEN_EOS];
} TUF;

/*******************************************************************************
 * Estrutura referente a Apoio.TipoDocumento (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdTipoDocumento[LEN_IDTIPODOCUMENTO + LEN_EOS];
    char szSgTipoDocumento[LEN_SGTIPODOCUMENTO + LEN_EOS];
    char szDsTipoDocumento[LEN_DSTIPODOCUMENTO + LEN_EOS];
    char szIdTipoPessoa[LEN_IDTIPOPESSOA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szNrPrioridade[LEN_NRPRIORIDADE + LEN_EOS];
    char szSgClassificacao[LEN_SGCLASSIFICACAO + LEN_EOS];
} TTipoDocumento;

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
    char szNrDiaPeriodoCicloDe[LEN_NRDIAPERIODOCICLODE + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtEfetivaCiclo[LEN_DTEFETIVACICLO + LEN_EOS];
    char szDtEfetivaStatus[LEN_DTEFETIVASTATUS + LEN_EOS];
} TConta;

/*******************************************************************************
 * Estrutura referente a Apoio.AreaRegistro (Tabela)
 *******************************************************************************/
typedef struct {
    char szInBloqueado					[LEN_INBLOQUEADO + LEN_EOS];
    char szCdAreaRegistro               [LEN_CDAREAREGISTROBLOQ + LEN_EOS];
} TAreaRegistroBloqueado;

/*******************************************************************************
 * Estrutura referente a infra.FILASETCLIENTINFO (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdFilaSetClientInfo[LEN_IDFILASETCLIENTINFO + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szXml[LEN_XML + LEN_EOS];
    char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
    char szNrLinha[LEN_NRLINHA + LEN_EOS];
    char szInterceptado[LEN_INTERCEPTADO + LEN_EOS];
    char szCdErro[LEN_CDERRO + LEN_EOS];
    char szDsErro[LEN_DSERRO + LEN_EOS];
} TFilaSetClientInfo;

/*******************************************************************************
 * Estrutura referente a tibco_ow.p_registracontato (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdRegistraContato[LEN_IDREGISTRACONTATO + LEN_EOS];
    char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
    char szNrLinha[LEN_NRLINHA + LEN_EOS];
    char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
    char szXML[LEN_XML_REGCONTATO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TRegistraContato;

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
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TPessoaConta;

/*******************************************************************************
 * Estrutura referente a Customer.ContaEndereco (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdContaEndereco[LEN_IDCONTAENDERECO + LEN_EOS];
    char szIdPessoaEndereco[LEN_IDPESSOAENDERECO + LEN_EOS];
    char szIdConta[LEN_IDCONTA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
	char szIdTipoEnderecoCobranca[LEN_IDTIPOENDERECOCOBRANCA + LEN_EOS];
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
    char    nrGrupo[LEN_IDPESSOA + LEN_EOS];
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
    char szIdTipoEndereco[LEN_IDTIPOENDERECO + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szIdUf[LEN_IDUF + LEN_EOS];
    char szDsAosCuidados[LEN_DSAOSCUIDADOS + LEN_EOS];
    char szCdCaixaPostal[LEN_CDCAIXAPOSTAL + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdEnderecoSistemaOrigem[LEN_IDENDERECOSISTEMAORIGEM + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szInEnderecoSujo[LEN_INENDERECOSUJO + LEN_EOS];
} TPessoaEndereco;

/*******************************************************************************
 * Estrutura referente a PessoaComunicacao (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaComunicacao[LEN_IDPESSOACOMUNICACAO + LEN_EOS];
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szIdTipoComunicacao[LEN_IDTIPOCOMUNICACAO + LEN_EOS];
    char szNrSequencia[LEN_NRSEQUENCIA + LEN_EOS];
    char szDsContato[LEN_DSCONTATO + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szDtCadastro[LEN_DTCADASTRO + LEN_EOS];
    char szIdComunicacaoSistemaOrigem[LEN_IDCOMUNICACAOSISTEMAORIGEM + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char szInComunicacaoPreferencial[LEN_INCOMUNICACAOPREFERENCIAL + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szNmContato[LEN_NMCONTATO + LEN_EOS];
} TPessoaComunicacao;

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
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
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
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
} TPessoaDocumento;

/*******************************************************************************
 * Estrutura referente a customer.PessoaAtendimentoFO (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoa[LEN_IDPESSOAATENDIMENTOFO + LEN_EOS];
} TPessoaAtendimentoFO;

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
    char szDtFundacao[LEN_DTFUNDACAO + LEN_EOS];
    char szIdCFOP[LEN_IDCFOP + LEN_EOS];
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
    char szSgTipoRelacionamento[LEN_SGTIPORELACIONAMENTO + LEN_EOS];
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
    char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szIdPessoaOrigem[LEN_IDPESSOAORIGEM + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaDePara;

/*******************************************************************************
 * Estrutura referente a Linha.PlanoServicoLinha (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdServicoLinha[LEN_IDSERVICOLINHA + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdServico[LEN_IDSERVICO + LEN_EOS];
    char szInPlano[LEN_INPLANO + LEN_EOS];
    char szDtVigenciaInicio[LEN_DTVIGENCIAINICIO + LEN_EOS];
    char szTsSincronismo[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo[LEN_SQSINCRONISMO + LEN_EOS];
    char szDtVigenciaFinal[LEN_DTVIGENCIAFINAL + LEN_EOS];
    char szDtExpiracao[LEN_DTEXPIRACAO + LEN_EOS];
    char szInContrato[LEN_INCONTRATO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdServLinhaSistOrigem[LEN_IDSERVLINHASISTORIGEM + LEN_EOS];
    char szSqServicoIntraGrupo[LEN_SQSERVICOINTRAGRUPO + LEN_EOS];
} TPlanoServicoLinha;

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
 * Estrutura referente ao VO de entrada na pesquisa de pre-pago: PrePagoPessoaVO
 *******************************************************************************/
typedef struct {
	char idPessoa[LEN_IDPESSOA + LEN_EOS];
	char inTipoPessoa[LEN_INTIPOPESSOA + LEN_EOS];
	char inAtual[LEN_INATUAL + LEN_EOS];
	char inClientePosPago[LEN_INCLIENTEPOSPAGO + LEN_EOS];
	char inClienteUsuario[LEN_INCLIENTEUSUARIO + LEN_EOS];
	char inListas[LEN_INLISTAS + LEN_EOS];
	char idLinha[LEN_IDLINHA + LEN_EOS];
} TPrePagoPessoaVO;

/*******************************************************************************
 * Estrutura referente a apoio.cnae
 *******************************************************************************/
typedef struct {
    char szIdCnae[LEN_IDCNAE + LEN_EOS];
    char szCdCnae[LEN_CDCNAE + LEN_EOS];
    char szDsCnae[LEN_DSCNAE + LEN_EOS];
    char szCdCfop[LEN_CDCFOP + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TCnae;

/*******************************************************************************
 * Estrutura referente a Apoio.SistemaOrigem (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szSgSistemaOrigem[LEN_SGSISTEMAORIGEM + LEN_EOS];
    char szNmSistemaOrigem[LEN_NMSISTEMAORIGEM + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szIndisponivel[LEN_INDISPONIVEL + LEN_EOS];
} TSistemaOrigem;

/*******************************************************************************
 * Estrutura referente a array de enderecos: TPessoaEnderecoArr
 *******************************************************************************/
typedef struct {
	TPessoaEndereco* pztPessoaEndereco;
	int              iQuantidade;
}TPessoaEnderecoArr;

/*******************************************************************************
 * Estrutura referente a array de contatos: TPessoaComunicacaoArr
 *******************************************************************************/
typedef struct {
	TPessoaComunicacao* pztPessoaComunicacao;
	int                 iQuantidade;
}TPessoaComunicacaoArr;

/*******************************************************************************
 * Estrutura referente a array de documentos: TDocumentoArr
 *******************************************************************************/
typedef struct {
	TDocumento* pztDocumento;
	char        idPessoa[LEN_IDPESSOA + LEN_EOS];
	int         iQuantidade;
}TDocumentoArr;

/*******************************************************************************
 * Estrutura referente a array de cnae: TCnaeArr
 *******************************************************************************/
typedef struct {
	TCnae* pztCnae;
	int         iQuantidade;
}TCnaeArr;

/*******************************************************************************
 * Estrutura referente a array de linhas: TDocumentoArr
 *******************************************************************************/
typedef struct {
	TPessoaLinha* pztPessoaLinha;
	int           iQuantidade;
}TPessoaLinhaArr;

/*******************************************************************************
 * Estrutura referente ao servico PPVALLIN de prepago
 *******************************************************************************/
typedef struct {
	char nrLinha[LEN_NRLINHA + LEN_EOS];
	char origemsistema[LEN_IDSISTEMAORIGEM + LEN_EOS];
}TValidaLinhaXmlIn;

/*******************************************************************************
 * Estrutura referente a Apoio.TipoConta (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdTipoConta[LEN_IDTIPOCONTA + LEN_EOS];
    char szSgTipoConta[LEN_SGTIPOCONTA + LEN_EOS];
    char szDsTipoConta[LEN_DSTIPOCONTA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
}TTipoConta;

/*******************************************************************************
 * Estrutura referente a Apoio.TipoEnderecoCobranca (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdTipoEnderecoCobranca[LEN_IDTIPOENDERECOCOBRANCA + LEN_EOS];
    char szSgTipoEnderecoCobranca[LEN_SGTIPOENDERECOCOBRANCA + LEN_EOS];
    char szSgClassificacao[LEN_SGCLASSIFICACAO + LEN_EOS];
    char szDsTipoEnderecoCobranca[LEN_DSTIPOENDERECOCOBRANCA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
}TTipoEnderecoCobranca;

/*******************************************************************************
 * Estrutura referente a Customer.TipoRelacionamento (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdTipoRelacionamento[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szSgTipoRelacionamento[LEN_SGTIPORELACIONAMENTO + LEN_EOS];
    char szNmTipoRelacionamento[LEN_NMTIPORELACIONAMENTO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
}TTipoRelacionamento;

/*******************************************************************************
 * Reseta VARCHAR()
 *******************************************************************************/
#define CONVIND(O,I) \
{ \
	if (I == -1) { \
		##O.arr[0]=0; \
	} else { \
		##O.arr[##O.len]=0; \
	} \
}
#endif /* GLOBAL */
