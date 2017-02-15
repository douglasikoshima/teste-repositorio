#ifndef GLOBAL
#define GLOBAL

/* definicoes globais */
#define LEN_DATA_HORA               19 /* formato exemplo 17/11/2006 03:30:40 */
#define LEN_DATE					8
#define LEN_DATE_FORMATADA			10
#define LEN_SEQUENCE				10
#define LEN_EOS						1
#define LEN_NUMBER                  38
#define LEN_ID                      16
#define OK							0
#define NOK							1

#define OBRIGATORIO                     1
#define NOBRIGATORIO                    0

/* Definicoes de Sistema Origem */
#define SIGLA_SO_ACS            "ACS"
#define SIGLA_SO_ATM            "ATM"
#define SIGLA_SO_ATY            "ATY"
#define SIGLA_SO_FALSO_INSERT   "FALSO INSERT"
#define SIGLA_SO_FO             "FO"
#define SIGLA_SO_NGN            "NGN"
#define SIGLA_SO_PPS            "PPS"
#define SIGLA_SO_SIX            "SIX"
#define SIGLA_SO_VOL            "VOL"

/*******************************************************************************/
/* TIPOS DE LINHAS */
/*******************************************************************************/
#define ID_TPLINHA_POSPAGO_CDMA         1
#define ID_TPLINHA_PREPAGO_CDMA         2
#define ID_TPLINHA_NAO_CLASSIFICADO     3
#define ID_TPLINHA_CONTROLE_CDMA        4
#define ID_TPLINHA_POSPAGO_GSM          5
#define ID_TPLINHA_PREPAGO_GSM          6
#define ID_TPLINHA_CONTROLE_GSM         7

/*******************************************************************************/
/* Valores de Protocolos */
/*******************************************************************************/
#define TIPO_ABER_PROT_CONTA            "1"
#define TIPO_ABER_PROT_LINHA            "2"
#define TIPO_ABER_PROT_LINHA_CLIENTE    "3"
#define TIPO_ABER_PROT_PESSOA           "4"

#define STATPROT_EM_ATENDIMENTO         "Em Atendimento"
#define STATPROT_ENCERRADO              "Encerrado"
#define STATPROT_PENDENTE               "Pendente"
#define STATPROT_PARCCONCLUIDO          "Parcialmente Concluído"
#define STATPROT_CONCLUIDO              "Concluído"
/*******************************************************************************/
/* definicoes referentes aos campos utilizados na Tela Inicial */
/*******************************************************************************/
#define LEN_NMLOGRADOURO			255
#define LEN_NMTITULOLOGRADOURO		255
#define LEN_LOGRADOURO				255
#define LEN_NRENDERECO				255
#define LEN_DSENDERECOCOMPLEMENTO	255
#define LEN_BAIRRO					255
#define LEN_MUNICIPIO				255
#define LEN_CEP						10
#define LEN_NRDOCUMENTO				255
#define LEN_SGTIPODOCUMENTO			255
#define LEN_DSTIPODOCUMENTO			255
#define LEN_SGTIPOCOMUNICACAO		255
#define LEN_DSTIPOCOMUNICACAO		255
#define LEN_SGTIPOENDERECO		    255
#define LEN_DSTIPOENDERECO		    255
#define	LEN_SGORGAOEXPEDIDOR		255
#define	LEN_SGUF					255
#define	LEN_NMUF					255
#define	LEN_SGPAIS					255
#define	LEN_DSPAIS					255
#define LEN_DSNACIONALIDADE         255
#define LEN_CDCPFCNPJBASE			255
#define LEN_CDCNPJFILIAL			255
#define LEN_CDCPFCNPJCONTROLE		255
#define	LEN_SGTIPOEND				255
#define	LEN_DSTIPOEND				255
#define LEN_NMPESSOACONTATO			255
#define LEN_SGTIPOCOMUNICACAO		255
#define LEN_DSTIPOCOMUNICACAO		255
#define LEN_CONTATO					255
#define LEN_DSSEGLINHA				255
#define LEN_DTTERMINOCONTRATO		255
#define LEN_DSMULTACONTRATO			255
#define LEN_DSAPARELHO				255
#define LEN_DSMODO					255
#define LEN_NOME					255
#define LEN_DOCUMENTO				255
#define LEN_CARGO					255
#define LEN_SERVICO					255
#define LEN_NRLINHA					255
#define LEN_DSTIPOLINHA				255
#define LEN_DSTIPOCLIENTE			255
#define LEN_DSPLANOSERVICO			255
#define LEN_DSESTADOLINHA			255
#define LEN_DSTIPOCONTRATO			255
#define LEN_CONTRATOFIDE			255
#define LEN_LOCALIDADE			    255

#define LEN_NMTIPOLOGRADOURO	    255

#define LEN_SEGMENTACAO			    255
#define LEN_TIPOCARTEIRA		    255
#define LEN_SGTIPOCARTEIRA		    255
#define LEN_DSTIPOCARTEIRA		    255
#define LEN_CHURNPROBABILIDADE	    255
#define LEN_TIPOLINHA			    255
#define LEN_NRCONTA				    100
#define LEN_CICLOFATURA			    255
#define LEN_DSCODIGOSEGURANCA	    255
#define LEN_DSESTADOCIVIL		    255
#define LEN_SGTIPORELACIONAMENTO    255
#define LEN_DSTIPORELACIONAMENTO    255
#define LEN_NMTIPORELACIONAMENTO    255
#define LEN_DSASSUNTO               255
#define LEN_NMSUBASSUNTO            255
#define LEN_SQAPRESENTACAO          255
#define LEN_NMURLSUBASSUNTO         255
#define LEN_DSATRIBUTO              255
#define LEN_DSVALORPOSSIVEL         255
#define LEN_DSTIPOPESSOA            255
#define LEN_SGTIPOPESSOA            255
#define LEN_SGTIPOAPRESPERGUNTA     255
#define LEN_DSTIPOAPRESPERGUNTA     255
#define LEN_PESSOAFILIAL            255
#define LEN_FANTASIA                255
#define LEN_CDSIGLA                 255
#define LEN_NMSIGLA                 255
#define LEN_CDMEIOCONTATO           255
#define LEN_NMMEIOCONTATO           255
#define LEN_CDAREAREGISTRO			255
#define LEN_NMSERVICO				255
#define LEN_NMPESSOA				255
#define LEN_DSCABREVTITULOLOGRAD    7
#define LEN_DSCTITULOLOGRAD         25
#define LEN_NOMLOGRADOURO           60
#define LEN_INDNUMERACAOENDERECO    1
#define LEN_FLGCOMPLEMENTO          1
#define LEN_DSCTIPOLOGRAD           25
#define LEN_DSCABREVTIPOLOGRAD      7
#define LEN_NUMCODNACLOCALIDADE     8
#define LEN_DSCABREVLOCALIDADE      20
#define LEN_DSCLOCALIDADE           50
#define LEN_SGLCODNACLOCALIDADE     10
#define LEN_CDAREATARIFARIAMINICOM  255
#define LEN_SGLUF                   2
#define LEN_NOMUF                   255
#define LEN_SGLISOPAIS              3
#define LEN_SGLISO2PAIS             2
#define LEN_NOMPAIS                 40
#define LEN_NUMPAIS                 3
#define LEN_NUMISOPAIS              255
#define LEN_NOMBAIRRO               50

/*******************************************************************************/
/* referente ao simlock */
/*******************************************************************************/
#define LEN_IDPROCEDENCIA                   LEN_NUMBER_ORA
#define LEN_IDCANAL                         LEN_NUMBER_ORA
#define LEN_IDCONTATO                       LEN_NUMBER_ORA

/*******************************************************************************/
/* Macros para manipulacao de dados ProC/C++ */
/*******************************************************************************/

#define NO_SQL_ERROR					0
#define NO_MORE_ROWS					1403
#define NO_DATA_FOUND					1403
#define END_OF_CURSOR					1403
#define TOO_MANY_ROWS					1422
#define NULL_FETCHED					1405
#define COL_TRUNCATED					1406
#define DUPLICATE_KEY					-1


/*        strncpy((char *) dest.arr, (const char *) source, (size_t)dest.len); }*/
#define STRCPY_TO_ORA(dest, source){ \
        dest.len = (unsigned short) strlen(source); \
        strcpy((char *) dest.arr, (const char *) source);}

#define STRCPY_FROM_ORA(dest, source)\
        dest[source.len] = 0;\
        strncpy((char *)dest,(const char *)source.arr, source.len)

#define STRNCPY_TO_ORA(dest, source, tam){\
	            memset(&dest, 0x00, sizeof(dest));\
				strncpy((char *)dest.arr, (const char *)source, tam);\
                dest.len = (unsigned short) strlen((char*)dest.arr);}

#define STRNCAT_TO_ORA(dest, tok, tam) { \
        dest.len += (unsigned short) strlen(tok);\
        strncat((char *) dest.arr, (const char *) tok, tam); }


#define STRCPY_ORA_2_ORA(dest, source) { \
        dest.len = (unsigned short) source.len;\
        strncpy((char *) dest.arr, (const char *) source.arr, (size_t)source.len); }

#define ZERA_TO_ORA(source){\
        source.arr[source.len] = '\0';}

#define LTRIM(var1){\
    for(register r = strlen(var1); var1[--r] == ' ' ;); \
    var1[r+1] = '\0';}


// PREPAGO GLOBALS
/*******************************************************************************
 * definicoes globais
 *******************************************************************************/
#define LEN_DATE_ORA                    14    // YYYYMMDDHHMMSS
#define LEN_DATE_HOUR_FORM_ORA          19    // DD/MM/YYYY HH:MM:SS
#define LEN_CURRENCY_ORA                32    // 9999,999,999,990.00-
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

/*******************************************************************************
 * definicoes referentes aos processo de log
 *******************************************************************************/
#define LEN_LOG_MESSAGE                 512

#define LDEBUG                           0
#define LINFORMATION                     1
#define LWARNING                         2
#define LERROR                           3
#define LCRITICAL                        4

/*******************************************************************************
 * definicoes referentes aos tipos de documentos
 *******************************************************************************/
#define ID_CPF	  1
#define ID_CNPJ	  2
#define ID_FCPF	  3
#define ID_RG	  4
#define ID_CP	  5
#define ID_CM	  6
#define ID_CIP	  7
#define ID_IP	  8
#define ID_PAS	  9
#define ID_TE	 10
#define ID_CNH	 11
#define ID_CR	 12
#define ID_RNE	 13
#define ID_IE	 14
#define ID_OCNPJ 15
#define ID_IF	 16
#define ID_IM	 17
#define ID_RJC	 18
#define ID_RCC	 19
#define ID_RFI	 20
#define ID_RES	 21
#define ID_AUTDP 22
#define ID_CEI	 23
#define ID_RE	 24
#define ID_TCPF	 25
#define ID_UCNPJ 26
#define ID_UCPF	 27
#define ID_KLCPF 28
#define ID_CNAE	 29
#define ID_CPR	 30
#define ID_CN	 31
#define ID_FI	  0

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
 * definicoes referentes a formatacao/propriedades do XML
 *******************************************************************************/
#define XML_OUT_PROP_XMLNS			"xmlns"
#define XML_OUT_PROP_XMLNS_VALUE	"cliente.fo.vivo.com.br/vo"

                                        
/*******************************************************************************
 * definicoes referentes ao Tipo de Relacionamento
 *******************************************************************************/
#define ID_TIPO_RELACIONAMENTO_C         "1"  // Cliente
#define ID_TIPO_RELACIONAMENTO_U         "2"  // Usuario
#define ID_TIPO_RELACIONAMENTO_R         "3"  // Consultor Relacionamento
#define ID_TIPO_RELACIONAMENTO_V         "4"  // Executivo de Vendas
#define ID_TIPO_RELACIONAMENTO_GC        "5"  // Gestor da Conta

/*******************************************************************************
 * definicoes referentes a apoio.tipodocumento (Tabela)
 *******************************************************************************/
#define LEN_IDTIPODOCUMENTO             LEN_NUMBER_ORA
#define LEN_SGTIPODOCUMENTO             255
#define LEN_DSTIPODOCUMENTO             255
#define LEN_IDTIPOPESSOA                LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_NRPRIORIDADE                LEN_NUMBER_ORA
#define LEN_SGCLASSIFICACAO             255
#define LEN_INVISUALIZA                 LEN_NUMBER_ORA

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
#define LEN_IDTIPORELACIONAMENTO        LEN_NUMBER_ORA
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Customer.GestorConta (Tabela)
 *******************************************************************************/
#define LEN_IDNRCPF                     12
#define LEN_DSTIPOGESTOR                6
#define LEN_NMNOMEGC                    60
#define LEN_NMNOMEMEIOGC                60
#define LEN_NMSOBRENOMEGC               60
#define LEN_NMCARGO                     50
#define LEN_NMLOGRADOUROGC              60
#define LEN_NRENDERECOGC                10
#define LEN_NMENDERECOCOMPLEMENTO       20
#define LEN_NMBAIRROGC                  50
#define LEN_NMCIDADE                    50
#define LEN_NRCEPGC                     9
#define LEN_IDUF                        LEN_NUMBER_ORA
#define LEN_DSEMAIL                     100
#define LEN_IDUSUARIOALTERACAO	        LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO	        LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Customer.GestorContaContato (Tabela)
 *******************************************************************************/
#define LEN_IDNRLINHA                   9
#define LEN_NRRAMAL                     6
#define LEN_IDNRCPF                     12
#define LEN_CDAREAREGISTROGC            3
#define LEN_IDTIPOCOMUNICACAO           LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Customer.GestorContaPessoaConta (Tabela)
 *******************************************************************************/
#define LEN_IDNRCPF                     12
#define LEN_IDPESSOACONTA               LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Infra.FilaSetClientInfo (Tabela)
 *******************************************************************************/
#define LEN_IDFILASETCLIENTINFO         LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_DTTIMESTAMP                 20
#define LEN_CDERRO                      255
#define LEN_DSERRO                      255
#define LEN_DTERRO                      20
#define LEN_XML1                        4000
#define LEN_XML2                        4000
#define LEN_XML3                        4000

/*******************************************************************************
 * definicoes referentes a Infra.LogSIMLOCK (Tabela)
 *******************************************************************************/
#define LEN_IDLOGSIMLOCK                        LEN_NUMBER_ORA
#define LEN_IP                                  20
#define LEN_IMEI                                25
#define LEN_ESTADOCONSULTA                      10
#define LEN_IDTIPODOCUMENTO                     LEN_NUMBER_ORA
#define LEN_NRDOCUMENTOSL                       20
#define LEN_DTULTIMAALTERACAO                   LEN_DATE_ORA
#define LEN_IDPESSOALINHAHISTORICO              LEN_NUMBER_ORA
#define LEN_IDPESSOA                            LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO                  LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Infra.LogDeviceManager (Tabela)
 *******************************************************************************/
#define LEN_IDLOGDEVICEMANAGER                  LEN_NUMBER_ORA
#define LEN_IP                                  20
#define LEN_IDUSUARIOALTERACAO                  LEN_NUMBER_ORA
#define LEN_NOMEPARAMETROATUALIZADO             20
#define LEN_ESTADOCONSULTA                      10
#define LEN_DTULTIMAALTERACAO                   LEN_DATE_ORA
#define LEN_IDLINHATELEFONICA                   LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Apoio.Parametro (Tabela)
 *******************************************************************************/
#define LEN_IDPARAMETRO                 LEN_NUMBER_ORA
#define LEN_CDPARAMETRO                 255
#define LEN_DSPARAMETRO                 255
#define LEN_DSVALORPARAMETRO            255
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
 * definicoes referentes a Retencao.ParametroEntrega (Tabela)
 *******************************************************************************/
#define LEN_IDSISTEMAORIGEM             LEN_NUMBER_ORA
#define LEN_UFREGIAO                    2
#define LEN_QTDIAPICKING                2
#define LEN_QTDIAFATURAMENTO            2
#define LEN_QTDIAENTREGA                2
#define LEN_QTDIAFORNECIMENTO           2
#define LEN_QTDIAREGISTROSAIDA          2
#define LEN_QTDIACONFIRMAPICKING        2
 
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
 * definicoes referentes a Apoio.AreaRegistroBloqueado (Tabela)
 *******************************************************************************/
#define LEN_IDAREAREGISTROBLOQUEADO     LEN_NUMBER_ORA
#define LEN_IDAREAREGISTRO              LEN_NUMBER_ORA
#define LEN_INBLOQUEADO                 LEN_NUMBER_ORA
#define LEN_CDAREAREGISTROBLOQ          LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a Infra.LogGSM (Tabela)
 *******************************************************************************/
#define LEN_IDLOGGSM                    LEN_NUMBER_ORA
#define LEN_IDLINHABASE                 LEN_NUMBER_ORA
#define LEN_IP                          20
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA
#define LEN_ICCID                       200

/*******************************************************************************
 * definicoes referentes a apoio.tipocomunicacao
 *******************************************************************************/
#define LEN_IDTIPOCOMUNICACAO           LEN_NUMBER_ORA
#define LEN_SGTIPOCOMUNICACAO           255
#define LEN_DSTIPOCOMUNICACAO           255
#define LEN_IDFORMARETORNO              LEN_NUMBER_ORA
#define LEN_SGCLASSIFICACAO             255

/*******************************************************************************
 * definicoes referentes a Apoio.StatusUsuario (Tabela)
 *******************************************************************************/
#define LEN_IDSTATUSUSUARIO             LEN_NUMBER_ORA
#define LEN_SGSTATUSUSUARIO             255
#define LEN_DSSTATUSUSUARIO             255
#define LEN_INDISPONIBILIDADE           LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a Infra.LogFilaSetClientInfo (Tabela)
 *******************************************************************************/
#define LEN_IDLOGFILASETCLIENTINFO      LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_XML1                        4000
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

/*******************************************************************************
 * definicoes referentes a FilaSetClientInfo (Tabela)
 *******************************************************************************/
#define LEN_IDFILASETCLIENTINFO         LEN_NUMBER_ORA
#define LEN_DSERRO                      255
#define LEN_CDERRO                      255
#define LEN_DSERRO                      255
#define LEN_XML                         4000

/*******************************************************************************
 * definicoes referentes a Linha.LinhaBase (Tabela)
 *******************************************************************************/
#define LEN_IDLINHABASE                 LEN_NUMBER_ORA
#define LEN_IDAREAREGISTRO              LEN_NUMBER_ORA
#define LEN_DTSTATUSLINHA               LEN_DATE_ORA
#define LEN_NRMIN                       255
#define LEN_NRDIGITOLINHA               LEN_NUMBER_ORA

/*******************************************************************************
 * definicoes referentes a customer.pessoaportabilidade
 *******************************************************************************/
#define LEN_INSINCRONIZADO              LEN_NUMBER_ORA
#define LEN_IDTIPOPESSOA                LEN_NUMBER_ORA
#define LEN_IDPESSOADEPARA              LEN_NUMBER_ORA
#define LEN_IDTIPOLINHA                 LEN_NUMBER_ORA
#define LEN_CDAREAREGISTROPORT          LEN_NUMBER_ORA
#define LEN_NRLINHAPORT                 LEN_NUMBER_ORA
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

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
 * definicoes referentes a SistemaOrigem (Tabela)
 *******************************************************************************/
#define LEN_ID_SISTEMA_ORIGEM           LEN_NUMBER_ORA
#define LEN_SG_SISTEMA_ORIGEM           255
#define LEN_NM_SISTEMA_ORIGEM           255

/*******************************************************************************
 * definicoes referentes a acesso.usuario (Tabela)
 *******************************************************************************/
#define LEN_NMLOGINUSUARIO                  255

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
 * definicoes referentes a customer.LogIMEI (tabela)
 *******************************************************************************/
#define LEN_IDLOGIMEI                   LEN_NUMBER_ORA
#define LEN_IDLINHATELEFONICA           LEN_NUMBER_ORA
#define LEN_NRPROTOCOLO                 LEN_NUMBER_ORA
#define LEN_NMLOJA                      255
#define LEN_DSMARCA                     255
#define LEN_DSMODELO                    255
#define LEN_IMEI                        25
#define LEN_IDUSUARIOALTERACAO          LEN_NUMBER_ORA
#define LEN_DTULTIMAALTERACAO           LEN_DATE_ORA

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
 * definicoes referentes a acesso.grupo (Tabela)
 *******************************************************************************/
#define LEN_IDGRUPO                     LEN_NUMBER_ORA
#define LEN_NMGRUPO                     255

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

/**************************************************************************************
 * Macro para obtencao dos dados, validacao e desalocacao de memoria do XML de entrada
 **************************************************************************************/
#ifndef _DECLARE_VARS
    char szMessage[LEN_RETURN_MESSAGE + LEN_EOS];
    char *pTree;
#define _DECLARE_VARS
#endif
#define GETTREE(CampoDestino, DOMNode, Tag, Nivel, Obrigatoriedade, Mensagem) \
{\
    /* ULOG("GETTREE - CampoDestino[%s](%d) Tag[%s] Nivel[%s] Obrigatoriedade[%s]", #CampoDestino, sizeof(CampoDestino), Tag, #Nivel, #Obrigatoriedade); \ */ \
    memset(CampoDestino, 0x00, sizeof(CampoDestino)); \
    if((pTree = walkTree(DOMNode, Tag, Nivel)) != NULL) \
    {\
        if((sizeof(CampoDestino)-LEN_EOS) < (int)strlen(pTree)) \
        {\
            sprintf(szMessage, "Campo %s maior que o esperado", strlen(Mensagem) > 0 ? Mensagem : Tag); \
            XMLString::release(&pTree); \
            throw new TuxBasicSvcException("00E0000", szMessage); \
        }\
        else if((strlen(pTree) == 0) && (Obrigatoriedade == OBRIGATORIO)) \
        {\
            sprintf(szMessage, "Campo %s sem valor", strlen(Mensagem) > 0 ? Mensagem : Tag); \
            XMLString::release(&pTree); \
            throw new TuxBasicSvcException("00E0000", szMessage); \
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
            throw new TuxBasicSvcException("00E0000", szMessage); \
        }\
    }\
}


/*******************************************************************************
 * Macros para manipulacao dos dados XML
 *******************************************************************************/
#define GET_WALKTREE(Campo, Saida)\
{\
    char *pwt = walkTree(pDnode, Campo, 0);\
    ULOG("Obtendo tag [%s] valor [%s]", Campo, pwt?pwt:"..NULL..");\
    if(!pwt) {\
        ERROR(NRO_TAG_ACAO_NE); TAG_INEXISTENTE(Campo);\
        throw new TuxBasicSvcException(sNrMsg, sMsg);\
    }\
    else if(!*pwt) {\
        ERROR(NRO_TAG_ACAO_VI); TAG_VALOR_VAZIO(Campo);\
        if(pwt) XMLString::release(&pwt);\
        throw new TuxBasicSvcException(sNrMsg, sMsg);\
    }\
    ULOG("strlen(pwt)(%d) sizeof(Saida)(%d)", strlen(pwt), sizeof(Saida));\
    if(strlen(pwt) >= sizeof(Saida)) {\
        if(pwt) XMLString::release(&pwt);\
        throw new TuxBasicSvcException("13E0001", "Invasao de memoria!");\
    }\
    strcpy(Saida, pwt?pwt:"");\
    if(pwt) XMLString::release(&pwt);\
}

#define GET_WTREE(Campo, Saida, Obrigatorio)\
{\
    char *pwt = walkTree(pDnode, Campo, 0);\
    ULOG("Obtendo tag [%s] valor [%s] Obrigatorio(%d)", Campo, pwt?pwt:"..NULL..", Obrigatorio);\
    if(Obrigatorio) {\
        if(!pwt) {\
            ERROR(NRO_TAG_ACAO_NE); TAG_INEXISTENTE(Campo);\
            throw new TuxBasicSvcException(sNrMsg, sMsg);\
        }\
        else if(!*pwt) {\
            ERROR(NRO_TAG_ACAO_VI); TAG_VALOR_VAZIO(Campo);\
            if(pwt) XMLString::release(&pwt);\
            throw new TuxBasicSvcException(sNrMsg, sMsg);\
        }\
    }\
    ULOG("strlen(pwt)(%d) sizeof(Saida)(%d)", strlen(pwt), sizeof(Saida));\
    if(strlen(pwt) >= sizeof(Saida)) {\
        if(pwt) XMLString::release(&pwt);\
        throw new TuxBasicSvcException("13E0001", "Invasao de memoria!");\
    }\
    strcpy(Saida, pwt?pwt:"");\
    if(pwt) XMLString::release(&pwt);\
}

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
    char szDtUltimaAlteracaoOut[LEN_DTULTIMAALTERACAOOUT + LEN_EOS];
} TPacote;

/*******************************************************************************
 * Estrutura referente a Apoio.StatusUsuario (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdStatusUsuario[LEN_IDSTATUSUSUARIO + LEN_EOS];
    char szSgStatusUsuario[LEN_SGSTATUSUSUARIO + LEN_EOS];
    char szDsStatusUsuario[LEN_DSSTATUSUSUARIO + LEN_EOS];
    char szInDisponibilidade[LEN_INDISPONIBILIDADE + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TApoioStatusUsuario;

/*******************************************************************************
 * Estrutura referente a Infra.FilaSetClientInfo (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdFilaSetClientInfo[LEN_IDFILASETCLIENTINFO + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szDtTimeStamp[LEN_DTTIMESTAMP + LEN_EOS];
    char szCdErro[LEN_CDERRO + LEN_EOS];
    char szDsErro[LEN_DSERRO + LEN_EOS];
    char szDtErro[LEN_DTERRO + LEN_EOS];
    char szXml1[LEN_XML1 + LEN_EOS];
    char szCount[LEN_NUMBER_ORA + LEN_EOS]; // nao faz parte da tabela
} TFilaSetClientInfo;

/*******************************************************************************
 * Estrutura referente a Apoio.Parametro (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdParametro[LEN_IDPARAMETRO + LEN_EOS];
    char szCdParametro[LEN_CDPARAMETRO + LEN_EOS];
    char szDsParametro[LEN_DSPARAMETRO + LEN_EOS];
    char szDsValorParametro[LEN_DSVALORPARAMETRO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TApoioParametro;

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
 * Estrutura referente a portabilidade
 *******************************************************************************/
typedef struct {
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szIdPessoaDePara[LEN_IDPESSOADEPARA + LEN_EOS];
    char szSgTipoPessoa[LEN_SGTIPOPESSOA + LEN_EOS];
    char szNmPessoa[LEN_NMPESSOA + LEN_EOS];
    char szCdAreaRegistro[LEN_CDAREAREGISTROPORT + LEN_EOS];
    char szNrLinha[LEN_NRLINHAPORT + LEN_CDAREAREGISTROPORT + LEN_EOS];
    char szIdTipoLinha[LEN_IDTIPOLINHA + LEN_EOS];
    char szDsTipoLinha[LEN_DSTIPOLINHA + LEN_EOS];
    char szIdPessoaEndereco[LEN_IDPESSOAENDERECO + LEN_EOS];
    char szDsEndereco[LEN_NMTIPOLOGRADOURO + LEN_NMLOGRADOURO + LEN_EOS];
    char szNrEndereco[LEN_NRENDERECO + LEN_EOS];
    char szDsEnderecoComplemento[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];
    char szNmBairro[LEN_NMBAIRRO + LEN_EOS];
    char szNmMunicipio[LEN_NMMUNICIPIO + LEN_EOS];
    char szNrCEP[LEN_NRCEP + LEN_EOS];
    char szIdUF[LEN_IDUF + LEN_EOS];
    char szSgUF[LEN_SGUF + LEN_EOS];
    char szNmUF[LEN_NMUF + LEN_EOS];
    char szIdPais[LEN_IDPAIS + LEN_EOS];
    char szSgPais[LEN_SGPAIS + LEN_EOS];
    char szDsPais[LEN_DSPAIS + LEN_EOS];
    char szDsNacionalidade[LEN_DSNACIONALIDADE + LEN_EOS];
    char szIdDocumento[LEN_IDDOCUMENTO + LEN_EOS];
    char szDsTipoDocumento[LEN_DSTIPODOCUMENTO + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
} TPortabilidade;


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
 * Estrutura referente a infra.FILASETCLIENTINFO (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdFilaSetClientInfo[LEN_IDFILASETCLIENTINFO + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szXml[LEN_XML + LEN_EOS];
} TFilaSetClientInfoPRE;

/*******************************************************************************
 * Estrutura referente a Linha.LinhaBase (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdLinhaBase					[LEN_IDLINHABASE + LEN_EOS];
    char szIdAreaRegistro				[LEN_IDAREAREGISTRO + LEN_EOS];
    char szNrLinha						[LEN_NRLINHA + LEN_EOS];
    char szDtStatusLinha				[LEN_DTSTATUSLINHA + LEN_EOS];
    char szNrMin						[LEN_NRMIN + LEN_EOS];
    char szNrDigitoLinha				[LEN_NRDIGITOLINHA + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_NUMBER_ORA + LEN_EOS];
} TLinhaBase;

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
    char szIdConta						[LEN_IDCONTA + LEN_EOS];
    char szIdSistemaOrigem				[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdLayoutConta				[LEN_IDLAYOUTCONTA + LEN_EOS];
    char szIdTipoConta					[LEN_IDTIPOCONTA + LEN_EOS];
    char szCdConta						[LEN_CDCONTA + LEN_EOS];
    char szCdDigitoConta				[LEN_CDDIGITOCONTA + LEN_EOS];
    char szCdCicloFaturamento			[LEN_CDCICLOFATURAMENTO + LEN_EOS];
    char szNrDiaVencimento				[LEN_NRDIAVENCIMENTO + LEN_EOS];
    char szInContaPorEmail				[LEN_INCONTAPOREMAIL + LEN_EOS];
    char szTsSincronismoPrincipal		[LEN_TSSINCRONISMOPRINCIPAL + LEN_EOS];
    char szSqSincronismoPrincipal		[LEN_SQSINCRONISMOPRINCIPAL + LEN_EOS];
    char szTsSincronismoCiclo			[LEN_TSSINCRONISMOCICLO + LEN_EOS];
    char szSqSincronismoCiclo			[LEN_SQSINCRONISMOCICLO + LEN_EOS];
    char szTsSincronismoStatus			[LEN_TSSINCRONISMOSTATUS + LEN_EOS];
    char szSqSincronismoStatus			[LEN_SQSINCRONISMOSTATUS + LEN_EOS];
    char szIdStatusConta				[LEN_IDSTATUSCONTA + LEN_EOS];
    char szIdContaSistemaOrigem			[LEN_IDCONTASISTEMAORIGEM + LEN_EOS];
    char szNrDiaPerioDoCicloDe			[LEN_NRDIAPERIODOCICLODE + LEN_EOS];
    char szDtExpiracao					[LEN_DTEXPIRACAO + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TConta;


/*******************************************************************************
 * Estrutura referente a Customer.PessoaConta (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaConta				[LEN_IDPESSOACONTA + LEN_EOS];
    char szIdTipoRelacionamento			[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szIdPessoaDePara				[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdConta						[LEN_IDCONTA + LEN_EOS];
    char szDtPessoaConta				[LEN_DTPESSOACONTA + LEN_EOS];
    char szDtExpiracao					[LEN_DTEXPIRACAO + LEN_EOS];
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
 * Estrutura referente a Retencao.ParametroEntrega (Tabela + servico)
 *******************************************************************************/
typedef struct{
    char szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szUFRegiao[LEN_UFREGIAO + LEN_EOS];
    char szQtDiaPicking[LEN_QTDIAPICKING + LEN_EOS];
    char szQtDiaFaturamento[LEN_QTDIAFATURAMENTO + LEN_EOS];
    char szQtDiaEntrega[LEN_QTDIAENTREGA + LEN_EOS];
    char szQtDiaFornecimento[LEN_QTDIAFORNECIMENTO + LEN_EOS];
    char szQtDiaRegistroSaida[LEN_QTDIAREGISTROSAIDA + LEN_EOS];
    char szQtDiaConfirmaPicking[LEN_QTDIACONFIRMAPICKING + LEN_EOS];

    char szSgUF[LEN_SGUF + LEN_EOS];
    char szNmSistemaOrigem[LEN_NM_SISTEMA_ORIGEM + LEN_EOS];
} TParametroEntrega;

/*******************************************************************************
 * Estrutura referente a Customer.Endereco, dados XML entrada
 *******************************************************************************/
typedef struct {
    char    idPessoa					[LEN_IDPESSOA + LEN_EOS];
    char    idLinha						[LEN_IDPESSOA + LEN_EOS];
    char    nrLinha						[LEN_IDPESSOA + LEN_EOS];
    char    nrCEP						[LEN_NMPESSOA + LEN_EOS];
	char    idTipoEnderecoSelecionado	[LEN_NMPESSOA + LEN_EOS];
	char    dsTipoEnderecoSelecionado	[LEN_NMPESSOA + LEN_EOS];
    char    ListaTipoLogradouro			[LEN_NMPESSOA + LEN_EOS];
    char    nmTipoLogradouro			[LEN_NMPESSOA + LEN_EOS];
    char    nmTituloLogradouro			[LEN_NMPESSOA + LEN_EOS];
    char    idTipoLogradouro			[LEN_IDPESSOA + LEN_EOS];
    char    dsTipoLogradouro			[LEN_NMPESSOA + LEN_EOS];
    char    idTipoLogradouroSelecionado	[LEN_IDPESSOA + LEN_EOS];
    char    idEndereco					[LEN_NMPESSOA + LEN_EOS];
    char    nmEndereco					[LEN_NMPESSOA + LEN_EOS];
    char    nrEndereco					[LEN_NMPESSOA + LEN_EOS];
    char    dsEnderecoComplemento		[LEN_NMPESSOA + LEN_EOS];
    char    ListaMunicipio				[LEN_NMPESSOA + LEN_EOS];
    char    Municipio					[LEN_NMPESSOA + LEN_EOS];
    char    idMunicipio					[LEN_IDPESSOA + LEN_EOS];
    char    nmMunicipio					[LEN_NMPESSOA + LEN_EOS];
    char    idMunicipioSelecionado		[LEN_IDPESSOA + LEN_EOS];
    char    ListaBairro					[LEN_NMPESSOA + LEN_EOS];
    char    nmBairro					[LEN_NMPESSOA + LEN_EOS];
    char    idBairro					[LEN_IDPESSOA + LEN_EOS];
    char    dsBairro					[LEN_NMPESSOA + LEN_EOS];
    char    idBairroSelecionado			[LEN_IDPESSOA + LEN_EOS];
    char    ListaPais					[LEN_NMPESSOA + LEN_EOS];
    char    Pais						[LEN_NMPESSOA + LEN_EOS];
    char    idPais						[LEN_IDPESSOA + LEN_EOS];
    char    dsPais						[LEN_NMPESSOA + LEN_EOS];
    char    IdPaisSelecionado			[LEN_IDPESSOA + LEN_EOS];
    char    ListaEstado					[LEN_NMPESSOA + LEN_EOS];
    char    Estado						[LEN_NMPESSOA + LEN_EOS];
    char    idEstado					[LEN_IDPESSOA + LEN_EOS];
    char    dsEstado					[LEN_NMPESSOA + LEN_EOS];
    char    idEstadoSelecionado			[LEN_IDPESSOA + LEN_EOS];
    char    dsEstadoSelecionado			[LEN_NMPESSOA + LEN_EOS];
    char    nrLinhaAssociada			[LEN_NMPESSOA + LEN_EOS];
	
} TEnderecoXML;
/*******************************************************************************
 * Estrutura referente a Customer.PessoaFisica dados XML entrada
 *******************************************************************************/

#define MAXDOCUMENTOTAG 2
#define MAXTELEFONETAG  4


 typedef struct 
 {
	char	idTipoDocumento				[LEN_IDPESSOA + LEN_EOS];
	char	dsTipoDocumento				[LEN_NOME + LEN_EOS];
	char	idDocumento					[LEN_IDPESSOA + LEN_EOS];
	char	dsDocumento					[LEN_NOME + LEN_EOS];
	char	dsOrgaoEmissor				[LEN_NOME + LEN_EOS];
	char	dtExpedicao					[LEN_NOME + LEN_EOS];
	char    idDocumentoUF				[LEN_IDPESSOA + LEN_EOS];

 } TListaDocumento; 

  typedef struct 
 {
	char	idTipoTelefone				[LEN_NOME + LEN_EOS];
	char	dsTipoTelefone				[LEN_NOME + LEN_EOS];
	char	idTelefone					[LEN_IDPESSOA + LEN_EOS];
	char	nrTelefone					[LEN_NOME + LEN_EOS];
	char	nmContato					[LEN_NOME + LEN_EOS];

 } TListaTelefone; 

typedef struct {
	char	servico						[LEN_NOME + LEN_EOS];
	char	idLinha						[LEN_NOME + LEN_EOS];
	char	xmlns						[LEN_NOME + LEN_EOS];
    char    IdSistemaOrigem				[LEN_IDSISTEMAORIGEM + LEN_EOS];
	char	idPessoa					[LEN_IDPESSOA + LEN_EOS];
	char	idTipoRelacionamento		[LEN_IDPESSOA + LEN_EOS];
	char	idTipoLinha					[LEN_IDPESSOA + LEN_EOS];
	char	usuarioNaoInformado			[LEN_NOME + LEN_EOS];
	char	inTipoPessoa				[LEN_NOME + LEN_EOS];
	char	nrLinha						[LEN_NOME + LEN_EOS];
	char	codSeguranca				[LEN_NOME + LEN_EOS];
	char	dsNome						[LEN_NOME + LEN_EOS];
	char	dsTipoPessoa				[LEN_NOME + LEN_EOS];
	char	ListaSexo					[LEN_NOME + LEN_EOS];
	char	Sexo						[LEN_NOME + LEN_EOS];
	char	idSexo						[LEN_IDPESSOA + LEN_EOS];
	char	dsSexo						[LEN_NOME + LEN_EOS];
	char	idSexoSelecionado			[LEN_NOME + LEN_EOS];
	char	ListaTipoDocumento			[LEN_NOME + LEN_EOS];
	char	TipoDocumento				[LEN_NOME + LEN_EOS];
	char	idTipoDocumento				[LEN_IDPESSOA + LEN_EOS];
	char	dsTipoDocumento				[LEN_NOME + LEN_EOS];
	TListaDocumento	ListaDocumento		[MAXDOCUMENTOTAG];
	char	dtNascimento				[LEN_NOME + LEN_EOS];
	char	ListaTipoTelefone			[LEN_NOME + LEN_EOS];
	char	TipoTelefone				[LEN_NOME + LEN_EOS];
	char	idTipoTelefone				[LEN_IDPESSOA + LEN_EOS];
	char	dsTipoTelefone				[LEN_NOME + LEN_EOS];
	TListaTelefone ListaTelefone		[MAXTELEFONETAG]; 
	char	dsEmailParticular			[LEN_NOME + LEN_EOS];
	char	dsEmailComercial			[LEN_NOME + LEN_EOS];
	char	ListaEstadoCivil			[LEN_NOME + LEN_EOS];
	char	EstadoCivil					[LEN_NOME + LEN_EOS];
	char	idEstadoCivil				[LEN_IDPESSOA + LEN_EOS];
	char	dsEstadoCivil				[LEN_NOME + LEN_EOS];
	char	idEstadoCivilSelecionado	[LEN_NOME + LEN_EOS];
	char	Escolaridade				[LEN_NOME + LEN_EOS];
	char	idEscolaridade				[LEN_IDPESSOA + LEN_EOS];
	char	dsEscolaridade				[LEN_NOME + LEN_EOS];
	char	idEscolaridadeSelecionado	[LEN_IDPESSOA + LEN_EOS];
	char	ListaOcupacao				[LEN_NOME + LEN_EOS];
	char	Ocupacao					[LEN_NOME + LEN_EOS];
	char	idOcupacao					[LEN_IDPESSOA + LEN_EOS];
	char	dsOcupacao					[LEN_NOME + LEN_EOS];
	char	idOcupacaoSelecionado		[LEN_IDPESSOA + LEN_EOS];
	char	profissao					[LEN_NOME + LEN_EOS];
	char	codigoPropriedadeRural		[LEN_NOME + LEN_EOS];
	char	inUsuarioLinha				[LEN_NOME + LEN_EOS];
	char	idCNPJUF					[LEN_IDPESSOA + LEN_EOS];
	char	inTipoCliente				[LEN_IDPESSOA + LEN_EOS];
	TEnderecoXML Endereco;
} TPessoaFisicaXML;

/*******************************************************************************
 * Estrutura referente a Customer.PessoaJuridica dados XML entrada
 *******************************************************************************/
typedef struct {
	//------------------------------------------------------------------------------
    char    servico						[LEN_IDPESSOA + LEN_EOS];
    char    idPessoa						[LEN_IDPESSOA + LEN_EOS];
    char    IdSistemaOrigem					[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char    idTipoPessoa					[LEN_IDPESSOA + LEN_EOS];
    char    nrLinha						[LEN_IDPESSOA + LEN_EOS];
    char    nmContato						[LEN_NMPESSOA + LEN_EOS];
    char    idLinha						[LEN_IDPESSOA + LEN_EOS];
    char    codSeguranca					[LEN_NMPESSOA + LEN_EOS];
    char    idTipoTelefoneSelecionado				[LEN_IDPESSOA + LEN_EOS];
    char    nrInscricao						[LEN_IDPESSOA + LEN_EOS];
    char    nrTelefone						[LEN_IDPESSOA + LEN_EOS];
    char    idTipoInscricaoSelecionado		                [LEN_IDPESSOA + LEN_EOS];
    char    dsTipoPessoa					[LEN_NMPESSOA + LEN_EOS];
    char    idTipoLinha						[LEN_IDPESSOA + LEN_EOS];
    char    idTipoRelacionamento				[LEN_IDPESSOA + LEN_EOS];
    char    dsCNPJ						[LEN_NMPESSOA + LEN_EOS];
    char    idCNPJ						[LEN_NMPESSOA + LEN_EOS];
    char    nmFantasia						[LEN_NMNOME + LEN_EOS];
    char    nmRazaoSocial					[LEN_NMPESSOA + LEN_EOS];
    char    dsCNAE						[LEN_DTCHURN + LEN_EOS];
    char    dsCCM					        [LEN_DTCADASTRO + LEN_EOS];
    char    dsInscricaoEstadual		        		[LEN_NMPESSOA + LEN_EOS];
    char    dtFundacao						[LEN_IDTIPOPESSOA + LEN_EOS];
    char    idUsuario						[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char    usuarioLinha					[LEN_NMPESSOA + LEN_EOS];
    char    idTipoClassificacaoEmpresaSelecionado		[LEN_NMPESSOA + LEN_EOS];
	//------------------------------------------------------------------------------
    char    idTipoClassificacaoEmpresaSelecionada	[LEN_IDPESSOA + LEN_EOS];
	char    idClassificacaoTributariaSelecionado	[LEN_IDPESSOA + LEN_EOS];
    char    idClassificacaoTributariaSelecionada	[LEN_IDPESSOA + LEN_EOS];
	//------------------------------------------------------------------------------
	char	idCNPJUF								[LEN_IDPESSOA + LEN_EOS];
	char	idCNAE									[LEN_IDPESSOA + LEN_EOS];
	char	idCCM									[LEN_IDPESSOA + LEN_EOS];
	char	idTelefone								[LEN_IDPESSOA + LEN_EOS];
	char	inTipoCliente							[LEN_IDPESSOA + LEN_EOS];
	char	idGrupo									[LEN_IDPESSOA + LEN_EOS];
	//------------------------------------------------------------------------------
    TEnderecoXML 	Endereco;
    TListaTelefone ListaTelefone; 
	
} TPessoaJuridicaXML;

/*******************************************************************************
 * Global service..
 * Utilizado para realizar merge de todas as estruturas XML recebidas...
 *******************************************************************************/

typedef struct {

	TPessoaJuridicaXML		Cliente; 
	TEnderecoXML 			Endereco;
	TPessoaFisicaXML		Usuario; 
	TEnderecoXML 			EnderecoUsuario;
    char					idPessoaOrigem		[LEN_IDTIPOPESSOA + LEN_EOS];
	char					idLinhaOrigem		[LEN_IDTIPOPESSOA + LEN_EOS];
	char					servico				[LEN_IDTIPOPESSOA + LEN_EOS];
	char					idPessoa			[LEN_IDTIPOPESSOA + LEN_EOS];
    char					idEndereco			[LEN_NMPESSOA + LEN_EOS];
} TPessoaJuridicaGlobalXML;

//--------------------------------------------------------------------------------

typedef struct {

	TPessoaFisicaXML		Cliente; 
	TEnderecoXML 			Endereco;
	TPessoaFisicaXML		Usuario; 
	TEnderecoXML 			EnderecoUsuario;
    char					idPessoaOrigem		[LEN_IDTIPOPESSOA + LEN_EOS];
	char					idLinhaOrigem		[LEN_IDTIPOPESSOA + LEN_EOS];
	char					servico				[LEN_IDTIPOPESSOA + LEN_EOS];
	char					idPessoa			[LEN_IDTIPOPESSOA + LEN_EOS];
    char					idEndereco			[LEN_NMPESSOA + LEN_EOS];
} TPessoaFisicaGlobalXML;


/*******************************************************************************
 * Estrutura referente a Customer.PessoaEndereco (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaEndereco				[LEN_IDPESSOAENDERECO + LEN_EOS];
    char szIdPessoa						[LEN_IDPESSOA + LEN_EOS];
    char szNrSequencia					[LEN_NRSEQUENCIA + LEN_EOS];
    char szIdPais						[LEN_IDPAIS + LEN_EOS];
    char szNmMunicipio					[LEN_NMMUNICIPIO + LEN_EOS];
    char szNmLocalidade					[LEN_NMLOCALIDADE + LEN_EOS];
    char szNmBairro						[LEN_NMBAIRRO + LEN_EOS];
    char szNmTipoLogradouro				[LEN_NMTIPOLOGRADOURO + LEN_EOS];
    char szNmTituloLogradouro			[LEN_NMTITULOLOGRADOURO + LEN_EOS];
    char szNmLogradouro					[LEN_NMLOGRADOURO + LEN_EOS];
    char szCdLogradouro[40 + LEN_EOS];
    char szInCnl[10 + LEN_EOS];
    char szCdIBGE[7 + LEN_EOS]; 
    char szNrEndereco					[LEN_NRENDERECO + LEN_EOS];
    char szDsEnderecoComplemento		[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];
    char szInEnderecoPreferencial		[LEN_INENDERECOPREFERENCIAL + LEN_EOS];
    char szNrCep						[LEN_NRCEP + LEN_EOS];
    char szDtCadastro					[LEN_DTCADASTRO + LEN_EOS];
    // char szDtCadastroOut[LEN_DTCADASTROOUT + LEN_EOS];
    char szIdTipoEndereco				[LEN_IDTIPOENDERECO + LEN_EOS];
    char szTsSincronismo				[LEN_TSSINCRONISMO + LEN_EOS];
    char szDtExpiracao					[LEN_DTEXPIRACAO + LEN_EOS];
    // char szDtExpiracaoOut[LEN_DTEXPIRACAOOUT + LEN_EOS];
    char szSqSincronismo				[LEN_SQSINCRONISMO + LEN_EOS];
    char szIdUF							[LEN_IDUF + LEN_EOS];
    char szDsAosCuidados				[LEN_DSAOSCUIDADOS + LEN_EOS];
    char szCdCaixaPostal				[LEN_CDCAIXAPOSTAL + LEN_EOS];
    char szIdSistemaOrigem				[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdEnderecoSistemaOrigem		[LEN_IDENDERECOSISTEMAORIGEM + LEN_EOS];
    char szInComunicacaoPreferencial	[LEN_INCOMUNICACAOPREFERENCIAL + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
    // char szDtUltimaAlteracaoOut[LEN_DTULTIMAALTERACAOOUT + LEN_EOS];
} TPessoaEndereco;

/*******************************************************************************
 * Estrutura referente a PessoaComunicacao (Tabela)
 *******************************************************************************/
typedef struct {
    char    szIdPessoaComunicacao		[LEN_IDPESSOACOMUNICACAO + LEN_EOS];
    char    szIdPessoa					[LEN_IDPESSOA + LEN_EOS];
    char    szIdTipoComunicacao			[LEN_IDTIPOCOMUNICACAO + LEN_EOS];
    char    szNrSequencia				[LEN_NRSEQUENCIA + LEN_EOS];
    char    szDsContato					[LEN_DSCONTATO + LEN_EOS];
    char    szTsSincronismo				[LEN_TSSINCRONISMO + LEN_EOS];
    char    szSqSincronismo				[LEN_SQSINCRONISMO + LEN_EOS];
    char    szDtCadastro				[LEN_DTCADASTRO + LEN_EOS];
    char    szIdComunicacaoSistemaOrigem[LEN_IDCOMUNICACAOSISTEMAORIGEM + LEN_EOS];
    char    szIdSistemaOrigem			[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char    szDtExpiracao				[LEN_DTEXPIRACAO + LEN_EOS];
    char    szInComunicacaoPreferencial	[LEN_INCOMUNICACAOPREFERENCIAL + LEN_EOS];
    char    szIdUsuarioAlteracao		[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char    szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaComunicacao;

/*******************************************************************************
 * Estrutura referente a customer.documento (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdDocumento					[LEN_IDDOCUMENTO + LEN_EOS];
    char szCdCpfCnpjBase				[LEN_CDCPFCNPJBASE + LEN_EOS];
    char szCdCnpjFilial					[LEN_CDCNPJFILIAL + LEN_EOS];
    char szCdCpfCnpjControle			[LEN_CDCPFCNPJCONTROLE + LEN_EOS];
    char szNrDocumento					[LEN_NRDOCUMENTO + LEN_EOS];
    char szSgOrgaoExpedidor				[LEN_SGORGAOEXPEDIDOR + LEN_EOS];
    char szDtEmissao					[LEN_DTEMISSAO + LEN_EOS];
    char szIdPais						[LEN_IDPAIS + LEN_EOS];
    char szIdUf							[LEN_IDUF + LEN_EOS];
    char szIdTipoDocumento				[LEN_IDTIPODOCUMENTO + LEN_EOS];
    char szDsTipoDocumento				[LEN_DSTIPODOCUMENTO + LEN_EOS];
    char szNmPessoa						[LEN_NMPESSOA + LEN_EOS];

} TDocumento;

/*******************************************************************************
 * Estrutura referente a customer.pessoadocumento (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaDocumento			[LEN_IDPESSOADOCUMENTO + LEN_EOS];
    char szIdPessoa						[LEN_IDPESSOA + LEN_EOS];
    char szIdDocumento					[LEN_IDDOCUMENTO + LEN_EOS];
    char szTsSincronismo				[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo				[LEN_SQSINCRONISMO + LEN_EOS];
    char szIdSistemaOrigem				[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdDocumentoSistemaOrigem		[LEN_IDDOCUMENTOSISTEMAORIGEM + LEN_EOS];
    char szDtExpiracao					[LEN_DTEXPIRACAO + LEN_EOS];
} TPessoaDocumento;

/*******************************************************************************
 * Estrutura referente a PessoaDocumentoB01 (VIEW)
 *******************************************************************************/
typedef struct {
    char szIdPessoa						[LEN_IDPESSOA + LEN_EOS];
    char szIdTipoDocumento				[LEN_IDTIPODOCUMENTO + LEN_EOS];
    char szIdPessoaDocumento			[LEN_IDPESSOADOCUMENTO + LEN_EOS];
    char szIdDocumento					[LEN_IDDOCUMENTO + LEN_EOS];
    char szTsSincronismo				[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo				[LEN_SQSINCRONISMO + LEN_EOS];
    char szNrDocumento					[LEN_NRDOCUMENTO + LEN_EOS];
    char szIdDocumentoSistemaOrigem		[LEN_IDDOCUMENTOSISTEMAORIGEM + LEN_EOS];
    char szIdSistemaOrigem				[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdPessoaSistemaOrigem		[LEN_IDPESSOASISTEMAORIGEM + LEN_EOS];
	char szDsTipoDocumento				[LEN_TSSINCRONISMO+LEN_EOS];
	char szDsDocumento					[LEN_TSSINCRONISMO+LEN_EOS];
	char szSgOrgaoExpedidor				[LEN_TSSINCRONISMO+LEN_EOS];
	char szDtEmissao					[LEN_DTEMISSAO + LEN_EOS];
	char szIdUF					[LEN_IDPESSOA + LEN_EOS];
} TPessoaDocumentoB01;

/*******************************************************************************
 * Estrutura referente a PessoaFisica (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoa						[LEN_IDPESSOA + LEN_EOS];
    char szDtNascimento					[LEN_DTNASCIMENTO + LEN_EOS];
    char szNmMae						[LEN_NMMAE + LEN_EOS];
    char szNmPai						[LEN_NMPAI + LEN_EOS];
    char szIdTratamento					[LEN_IDTRATAMENTO + LEN_EOS];
    char szIdEstadoCivil				[LEN_IDESTADOCIVIL + LEN_EOS];
    char szIdPais						[LEN_IDPAIS + LEN_EOS];
    char szIdSexo						[LEN_IDSEXO + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaFisica;

/*******************************************************************************
 * Estrutura referente a Apoio.AreaRegistro (Tabela)
 *******************************************************************************/
typedef struct {
    char szInBloqueado					[LEN_INBLOQUEADO + LEN_EOS];
    char szCdAreaRegistro               [LEN_CDAREAREGISTROBLOQ + LEN_EOS];
} TAreaRegistroBloqueado;

/*******************************************************************************
 * Estrutura referente a PessoaJuridica (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoa						[LEN_IDPESSOA + LEN_EOS];
    char szNmPessoaFilial				[LEN_NMPESSOAFILIAL + LEN_EOS];
    char szNmPessoa						[LEN_NMPESSOAFILIAL + LEN_EOS];
    char szNmFantasia					[LEN_NMFANTASIA + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szdtFundacao					[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szIdTipoCarteira				[LEN_IDPESSOA + LEN_EOS];
} TPessoaJuridica;

/*******************************************************************************
 * Estrutura referente a LinhaConta (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdLinhaConta					[LEN_IDLINHACONTA + LEN_EOS];
    char szIdLinhaTelefonica			[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdConta						[LEN_IDCONTA + LEN_EOS];
    char szIdTipoRelacionamento			[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szDtLinhaConta					[LEN_DTLINHACONTA + LEN_EOS];
    char szInLinhaMaster				[LEN_INLINHAMASTER + LEN_EOS];
    char szTsSincronismo				[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo				[LEN_SQSINCRONISMO + LEN_EOS];
    char szDtExpiracao					[LEN_DTEXPIRACAO + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TLinhaConta;

/*******************************************************************************
 * Estrutura referente a apoio.tipocomunicacao
 *******************************************************************************/
typedef struct {
    char szIdTipoComunicacao[LEN_IDTIPOCOMUNICACAO + LEN_EOS];
    char szSgTipoComunicacao[LEN_SGTIPOCOMUNICACAO + LEN_EOS];
    char szDsTipoComunicacao[LEN_DSTIPOCOMUNICACAO + LEN_EOS];
    char szIdFormaRetorno[LEN_IDFORMARETORNO + LEN_EOS];
    char szSgClassificacao[LEN_SGCLASSIFICACAO + LEN_EOS];
} TTipoComunicacao;

/*******************************************************************************
 * Estrutura referente a Linha.LinhaTelefonica (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdLinhaTelefonica			[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdSistemaOrigem				[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char szIdLinhaSistemaOrigem			[LEN_IDLINHASISTEMAORIGEM + LEN_EOS];
    char szIdLinhaBase					[LEN_IDLINHABASE + LEN_EOS];
    char szIdTipoLinha					[LEN_IDTIPOLINHA + LEN_EOS];
    char szIdEstadoLinha				[LEN_IDESTADOLINHA + LEN_EOS];
    char szDtEstadoLinha				[LEN_DTESTADOLINHA + LEN_EOS];
    char szInDivulgacaoNrLinha			[LEN_INDIVULGACAONRLINHA + LEN_EOS];
    char szDtAutorizacaoDivulgacao		[LEN_DTAUTORIZACAODIVULGACAO + LEN_EOS];
    char szDtHabilitacao				[LEN_DTHABILITACAO + LEN_EOS];
    char szTsSincronismo				[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo				[LEN_SQSINCRONISMO + LEN_EOS];
    char szTsSincronismoEstado			[LEN_TSSINCRONISMOESTADO + LEN_EOS];
    char szSqSincronismoEstado			[LEN_SQSINCRONISMOESTADO + LEN_EOS];
    char szCdChurnProbabilidade			[LEN_CDCHURNPROBABILIDADE + LEN_EOS];
    char szVlrChurnProbabilidade		[LEN_VLRCHURNPROBABILIDADE + LEN_EOS];
    char szDtExpiracao					[LEN_DTEXPIRACAO + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TLinhaTelefonica;

/*******************************************************************************
 * Estrutura referente a Linha.LinhaTelefonica (Tabela PREPAGO) 
 *******************************************************************************/
typedef struct {
    char szIdLogIMEI                    [LEN_IDLOGIMEI + LEN_EOS];
    char szIdLinhaTelefonica            [LEN_IDLINHATELEFONICA + LEN_EOS];
    char szNrProtocolo                  [LEN_NRPROTOCOLO + LEN_EOS];
    char szDsMarca                      [LEN_DSMARCA + LEN_EOS];
    char szDsModelo                     [LEN_DSMODELO + LEN_EOS];
    char szNmLoja                       [LEN_NMLOJA + LEN_EOS];
    char szIMEI                         [LEN_IMEI + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TLogIMEI;

/*******************************************************************************
 * Estrutura referente a Linha.LinhaTelefonica (Tabela PREPAGO) 
 *******************************************************************************/
typedef struct {
    char nrLinha						[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char codSeguranca					[LEN_IDLINHASISTEMAORIGEM + LEN_EOS];
    char szIdLinhaBase					[LEN_IDLINHABASE + LEN_EOS];
    char szIdTipoLinha					[LEN_IDTIPOLINHA + LEN_EOS];
    char szIdEstadoLinha				[LEN_IDESTADOLINHA + LEN_EOS];
    char szDtEstadoLinha				[LEN_DTESTADOLINHA + LEN_EOS];
    char szInDivulgacaoNrLinha			[LEN_INDIVULGACAONRLINHA + LEN_EOS];
    char szDtAutorizacaoDivulgacao		[LEN_DTAUTORIZACAODIVULGACAO + LEN_EOS];
    char szDtHabilitacao				[LEN_DTHABILITACAO + LEN_EOS];
    char szTsSincronismo				[LEN_TSSINCRONISMO + LEN_EOS];
    char szSqSincronismo				[LEN_SQSINCRONISMO + LEN_EOS];
    char szTsSincronismoEstado			[LEN_TSSINCRONISMOESTADO + LEN_EOS];
    char szSqSincronismoEstado			[LEN_SQSINCRONISMOESTADO + LEN_EOS];
    char szCdChurnProbabilidade			[LEN_CDCHURNPROBABILIDADE + LEN_EOS];
    char szVlrChurnProbabilidade		[LEN_VLRCHURNPROBABILIDADE + LEN_EOS];
    char szDtExpiracao					[LEN_DTEXPIRACAO + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TLinhaBaseXML;

/*******************************************************************************
 * Estrutura referente a Linha.PessoaLinha (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdPessoaLinha				[LEN_IDPESSOALINHA + LEN_EOS];
    char szDtPessoaLinha				[LEN_DTPESSOALINHA + LEN_EOS];
    char szDtPessoaLinhaOut				[LEN_DTPESSOALINHAOUT + LEN_EOS];
    char szIdTipoRelacionamento			[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szIdPessoaDePara				[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdLinhaTelefonica			[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szDtUltimaAlteracaoOut			[LEN_DTULTIMAALTERACAOOUT + LEN_EOS];
} TPessoaLinha;

/*******************************************************************************
 * Estrutura referente a Infra.LogSIMLOCK (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdLogSIMLock[LEN_IDLOGSIMLOCK + LEN_EOS];
    char szIP[LEN_IP + LEN_EOS];
    char szIMEI[LEN_IMEI + LEN_EOS];
    char szEstadoConsulta[LEN_ESTADOCONSULTA + LEN_EOS];
    char szIdTipoDocumento[LEN_IDTIPODOCUMENTO + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TLogSIMLock;

/*******************************************************************************
 * Estrutura referente a Customer.GestorConta (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdNrCPF[LEN_IDNRCPF + LEN_EOS];
    char szDsTipoGestor[LEN_DSTIPOGESTOR + LEN_EOS];
    char szNmNome[LEN_NMNOMEGC + LEN_EOS];
    char szNmNomeMeio[LEN_NMNOMEMEIOGC + LEN_EOS];
    char szNmSobreNome[LEN_NMSOBRENOMEGC + LEN_EOS];
    char szNmCargo[LEN_NMCARGO + LEN_EOS];
    char szNmLogradouro[LEN_NMLOGRADOUROGC + LEN_EOS];
    char szNrEndereco[LEN_NRENDERECOGC + LEN_EOS];
    char szNmEnderecoComplemento[LEN_NMENDERECOCOMPLEMENTO + LEN_EOS];
    char szNmBairro[LEN_NMBAIRROGC + LEN_EOS];
    char szNmCidade[LEN_NMCIDADE + LEN_EOS];
    char szNrCEP[LEN_NRCEPGC + LEN_EOS];
    char szIdUF[LEN_IDUF + LEN_EOS];
    char szDsEmail[LEN_DSEMAIL + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DATA_HORA + LEN_EOS];
} TGestorConta;

/*******************************************************************************
 * Estrutura referente a Customer.GestorContaContato (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdNrLinha[LEN_IDNRLINHA + LEN_EOS];
    char szNrRamal[LEN_NRRAMAL + LEN_EOS];
    char szIdNrCPF[LEN_IDNRCPF + LEN_EOS];
    char szCdAreaRegistro[LEN_CDAREAREGISTROGC + LEN_EOS];
    char szIdTipoComunicacao[LEN_IDTIPOCOMUNICACAO + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TGestorContaContato;

/*******************************************************************************
 * Estrutura referente a Customer.GestorContaPessoaConta (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdNrCPF[LEN_IDNRCPF + LEN_EOS];
    char szIdPessoaConta[LEN_IDPESSOACONTA + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TGestorContaPessoaConta;

/*******************************************************************************
 * Estrutura referente a Infra.LogDeviceManager (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdLogDeviceManager[LEN_IDLOGDEVICEMANAGER + LEN_EOS];
    char szIP[LEN_IP + LEN_EOS];
    char szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szNomeParametroAtualizado[LEN_NOMEPARAMETROATUALIZADO + LEN_EOS];
    char szEstadoConsulta[LEN_ESTADOCONSULTA + LEN_EOS];
    char szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
} TLogDeviceManager;

/*******************************************************************************
 * Estrutura referente a Infra.LogGSM (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdLogGSM     				[LEN_IDLOGGSM + LEN_EOS];
    char szIdLinhaTelefonica      		[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIP           				[LEN_IP + LEN_EOS];
    char szIdUsuarioAlteracao   		[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szICCID                        [LEN_ICCID + LEN_EOS];
} TLogGSM;

/*******************************************************************************
 * Estrutura referente a Infra.LogFilaSetClientInfo (Tabela)
 *******************************************************************************/
typedef struct{
    char szIdLogFilaSetClientInfo   	[LEN_IDLOGFILASETCLIENTINFO + LEN_EOS];
    char szIdLinhaTelefonica      		[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szXML1           				[LEN_XML1 + LEN_EOS];
    char szIdUsuarioAlteracao   		[LEN_IDUSUARIOALTERACAO + LEN_EOS];
} TLogFilaSetClientInfo;

/*******************************************************************************
 * Estrutura referente a Customer.PessoaLinhaHistorico (Tabela)
 *******************************************************************************/
typedef struct {
    char szIdPessoaLinhaHistorico		[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
    char szDtRelacionamento				[LEN_DTRELACIONAMENTO + LEN_EOS];
    char szCdAreaRegistro				[LEN_CDAREAREGISTRO + LEN_EOS];
    char szNrLinha						[LEN_NRLINHA + LEN_EOS];
    char szIdTipoRelacionamento			[LEN_IDTIPORELACIONAMENTO + LEN_EOS];
    char szIdPessoaDePara				[LEN_IDPESSOADEPARA + LEN_EOS];
    char szIdLinhaTelefonica			[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdUsuarioAlteracao			[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaLinhaHistorico;

/*******************************************************************************
 * Estrutura referente a Customer.PessoaDePara (Tabela)
 *******************************************************************************/
typedef struct {
    char    szIdPessoaDePara			[LEN_IDPESSOADEPARA + LEN_EOS];
    char    szIdPessoa					[LEN_IDPESSOA + LEN_EOS];
    char    szIdPessoaOrigem			[LEN_IDPESSOAORIGEM + LEN_EOS];
    char    szIdUsuarioAlteracao		[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char    szDtUltimaAlteracao			[LEN_DTULTIMAALTERACAO + LEN_EOS];
} TPessoaDePara;

/*******************************************************************************
 * Estrutura referente a Linha.PlanoServicoLinha (Tabela)
 *******************************************************************************/
typedef struct {
    char    szIdServicoLinha			[LEN_IDSERVICOLINHA + LEN_EOS];
    char    szIdLinhaTelefonica			[LEN_IDLINHATELEFONICA + LEN_EOS];
    char    szIdServico					[LEN_IDSERVICO + LEN_EOS];
    char    szInPlano					[LEN_INPLANO + LEN_EOS];
    char    szDtVigenciaInicio			[LEN_DTVIGENCIAINICIO + LEN_EOS];
    char    szTsSincronismo				[LEN_TSSINCRONISMO + LEN_EOS];
    char    szSqSincronismo				[LEN_SQSINCRONISMO + LEN_EOS];
    char    szDtVigenciaFinal			[LEN_DTVIGENCIAFINAL + LEN_EOS];
    char    szDtExpiracao				[LEN_DTEXPIRACAO + LEN_EOS];
    char    szInContrato[LEN_INCONTRATO + LEN_EOS];
    char    szIdUsuarioAlteracao[LEN_IDUSUARIOALTERACAO + LEN_EOS];
    char    szDtUltimaAlteracao[LEN_DTULTIMAALTERACAO + LEN_EOS];
    char    szIdSistemaOrigem[LEN_IDSISTEMAORIGEM + LEN_EOS];
    char    szIdServLinhaSistOrigem[LEN_IDSERVLINHASISTORIGEM + LEN_EOS];
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
 * Estrutura referente ao processo de Grupo
 *******************************************************************************/
typedef struct {
    char szIdLinhaTelefonica[LEN_IDLINHATELEFONICA + LEN_EOS];
    char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];
    char szIdGrupo[LEN_IDGRUPO + LEN_EOS];
    char szNmGrupo[LEN_NMGRUPO + LEN_EOS];
} TGrupo;

/*******************************************************************************
 * Estrutura referente ao processo de envio de dados para o NGIN
 *******************************************************************************/
typedef struct {
    char szIdPessoa[LEN_IDPESSOA + LEN_EOS];
    char szLogradouro[LEN_NMTIPOLOGRADOURO + LEN_EOS];
    char cdLogradouro[40 + LEN_EOS];
    char inCnl[8 + LEN_EOS];
    char cdIbge[7 + LEN_EOS];
    char szEndereco[LEN_NMLOGRADOURO + LEN_EOS];
    char szComplemento[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];
    char szBairro[LEN_NMBAIRRO + LEN_EOS];
    char szCEP[LEN_NRCEP + LEN_EOS];
    char szCidade[LEN_NMMUNICIPIO + LEN_EOS];
    char szEstado[LEN_SGUF + LEN_EOS];
    char szPais[3 + LEN_EOS];
    char szNumero[LEN_NRENDERECO + LEN_EOS];
    char szTipoCliente[1 + LEN_EOS];
    char szNome[LEN_NMPESSOA + LEN_EOS];
    char szConfidencial[1 + LEN_EOS];
    char szDataNascimento[LEN_DATE_ORA + LEN_EOS];
    char szEstadoCivil[1 + LEN_EOS];
    char szCodSexo[1 + LEN_EOS];
} TDadosNGIN;

typedef struct {
    char szSgTipoDocumento[LEN_SGTIPODOCUMENTO + LEN_EOS];
    char szNrDocumento[LEN_NRDOCUMENTO + LEN_EOS];
    char szSgOrgaoExpedidor[LEN_SGORGAOEXPEDIDOR + LEN_EOS];
} TDadosDocumento;

#endif /* GLOBAL */
