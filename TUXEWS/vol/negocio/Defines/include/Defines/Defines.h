
#ifndef CDEFINES
#define CDEFINES

/*******************************************************************************/
/* Defines dos codigos de erro para o ProC/C++ */

#define NO_SQL_ERROR					0
#define NO_MORE_ROWS					1403
#define NO_DATA_FOUND					1403
#define END_OF_CURSOR					1403
#define TOO_MANY_ROWS					1422
#define NULL_FETCHED					1405
#define COL_TRUNCATED					1406
#define DUPLICATE_KEY					-1

/*******************************************************************************/

#ifndef COD_ERR_EXCEPT
	#define COD_ERR_EXCEPT "11E0000"
#endif

#define ASSERT_STR(value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			if (value == NULL) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "INEXISTENTE")); \
			if (!*value) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "VALOR_VAZIO")); \
		}

#define ASSERT_INT(output, value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			ASSERT_STR(value, tag) \
			if ((output = atoi(value)) < 0) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "VALOR_INVALIDO")); \
		}

#define ASSERT_LONG(output, value, tag) \
		{	char cTag[64]; \
			sprintf(cTag, "TAG_%s_", tag); \
			ASSERT_STR(value, tag) \
			if ((output = atol(value)) < 0) \
				throw new TuxBasicSvcException(COD_ERR_EXCEPT, strcat(cTag, "VALOR_INVALIDO")); \
		}

#define ASSERT_GET_CHAR(prop, value) \
		{ \
			if(NULL == prop) \
			{\
				strcpy(value, ""); \
				return value; \
			}\
			strcpy(value, prop);\
			return value;\
		}

#define ASSERT_SET_CHAR(prop, value) \
		{ \
			if(NULL == value || (strlen(value) + 1) > sizeof(prop)) \
				return; \
		}

#define ASSERT_SET_PCHAR(prop, value) \
		{ \
			if(NULL == value) \
				return; \
			if(prop) \
				delete prop; \
			prop = new char[strlen(value) + 1]; \
			strcpy(prop, value); \
		}

/*******************************************************************************/

#define REG_NAO_VALIDA_TAG 0
#define REG_VALIDA_TAG     1

#define REG_CONTATO(iResult, valTag) \
	int iResult = 0; \
	try \
	{ \
		CLinha oLinhaReg; \
		CParametro oParametro; \
		char*  pcTagXmlIn = NULL; \
		int    iCdAreaRegistro = -1; \
 		int    iNrLinha = -1; \
		int    iIdTipoRelacionamento = -1; \
		int    iIdContato = -1; \
		int    iIdCanal = -1; \
		int	   iIdTerminal = -1; \
		int    iInRegistrarContato = 1; \
		if(valTag) \
		{ \
			pcTagXmlIn = helper.walkTree(dnode,"inRegistrarContato", 0); \
			ASSERT_INT(iInRegistrarContato, pcTagXmlIn, "inRegistrarContato"); \
		} \
		if(iInRegistrarContato) \
		{ \
			pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0); \
			ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro"); \
			pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0); \
			ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha"); \
			pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0); \
			ASSERT_INT(iIdTipoRelacionamento, pcTagXmlIn, "idTipoRelacionamento"); \
			pcTagXmlIn = helper.walkTree(dnode,"cdContato", 0); \
			ASSERT_STR(pcTagXmlIn, "cdContato"); \
			oParametro.setChave(pcTagXmlIn); \
			oParametro.consultar(); \
			iIdContato = atoi(oParametro.getConsulta()); \
			pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0); \
			ASSERT_INT(iIdCanal, pcTagXmlIn, "idCanal"); \
			pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0); \
			if((pcTagXmlIn != NULL) && (strlen(pcTagXmlIn) > 0)) \
				ASSERT_INT(iIdTerminal, pcTagXmlIn, "idTerminal"); \
			oLinhaReg.setCdAreaRegistro(iCdAreaRegistro); \
			oLinhaReg.setNrLinha(iNrLinha); \
			oLinhaReg.getPessoa()->setIdTipoRelacionamento(iIdTipoRelacionamento); \
			oLinhaReg.getPessoa()->getRel()->setIdCanal(iIdCanal); \
			oLinhaReg.getPessoa()->getRel()->setIdContato(iIdContato); \
			oLinhaReg.registrarContato(iIdTerminal, this->getUser()); \
		} \
		iResult = 0; \
	} \
	catch(...){ iResult = 1; }

/*******************************************************************************/

#define ENV_MSG_NAO_VALIDA_TAG 0
#define ENV_MSG_VALIDA_TAG     1

#define ENV_MSG_P1(iResult, valTag) \
		int iResult = 0; \
		try \
		{ \
			CLinha     oLinhaEnv; \
			char*  pcTagXmlIn = NULL; \
			char   cdMsg[256]; \
			int    iCdAreaRegistro = -1; \
 			int    iNrLinha = -1; \
			int    iIdTipoRelacionamento = -1; \
			int    iInComunicarUsuario = 1;

#define ENV_MSG_P2(iResult, valTag) \
		if(valTag) \
		{ \
			pcTagXmlIn = helper.walkTree(dnode,"inComunicarUsuario", 0); \
			ASSERT_INT(iInComunicarUsuario, pcTagXmlIn, "inComunicarUsuario"); \
		} \
		if(iInComunicarUsuario) \
		{ \
			pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0); \
			ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro"); \
			pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0); \
			ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha"); \
			pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0); \
			ASSERT_INT(iIdTipoRelacionamento, pcTagXmlIn, "idTipoRelacionamento"); \
			pcTagXmlIn = helper.walkTree(dnode,"cdMsg", 0); \
			ASSERT_STR(pcTagXmlIn, "cdMsg"); \
			strcpy(cdMsg, pcTagXmlIn); \
			oLinhaEnv.setCdAreaRegistro(iCdAreaRegistro); \
			oLinhaEnv.setNrLinha(iNrLinha); \
			oLinhaEnv.getPessoa()->setIdTipoRelacionamento(iIdTipoRelacionamento); \
			oLinhaEnv.enviarMensagem(cdMsg, this->getUser()); \
		} \
		iResult = 0; \
	} \
	catch(...){ iResult = 1; }

#define ENV_MSG(iResult, valTag) \
			ENV_MSG_P1(iResult, valTag); \
			ENV_MSG_P2(iResult, valTag);

#define ENV_MSG_EMAIL(iResult, valTag) \
			ENV_MSG_P1(iResult, valTag); \
			oLinhaEnv.getPessoa()->setDsContato(oPessoa.getDsContato()); \
			ENV_MSG_P2(iResult, valTag);
			

/*******************************************************************************/
/* Definições de valores da base utilizadas no código */

#define PESSOA_USUARIO 1
#define PESSOA_CLIENTE 2

/*******************************************************************************/

/*******************************************************************************/
/* Definições de tamanhos padrão */

#define STR_SIZE 64

/*******************************************************************************/

#define ID_TP_SISTEMA_FO				2

#define	FRASE_SECRETA_ATIVADA			1
#define	FRASE_SECRETA_BLOQUEADA			2

#define ID_ALTER_FRASE_SECRETA			11
#define ID_BLOQ_FRASE_SECRETA			12
#define ID_REINIC_FRASE_SECRETA			13

#define ID_GRUPO_ABERTURA				62

#define ID_TP_COM_SMS					1
#define ID_TP_COM_EMAIL					2

#define ID_TP_APRES_PERGUNTA_MEMO		3
#define ID_TP_APRES_PERGUNTA_TXT		5

#define ATLYS							1
#define PPS								2
#define NGIN							3

#define ID_DESVIO_CHAMADA				510
#define ID_VIGENCIA_SERVICO				525
#define ID_DESBLOQUEIO_LDI				543
#define ID_BLOQUEIO_LDNLDI				512
#define ID_CHAMADA_ESPERA				516
#define ID_FAVORITOS					532
#define ID_HISTORICO_PROCESSOS			502
#define ID_CAIXA_POSTAL					514
#define ID_REINICIA_CAIXA_POSTAL		538

#define ID_TIPO_ENDERECO_COBRANCA				3
#define ID_TIPO_ENDERECO_COBRANCA_DEFAULT		1
#define ID_TIPO_ENDERECO_COBRANCA_REMITTANCE	4

#define ID_CANAL_VOL					15
#define ID_CANAL_TAV					13

#define ID_TIPO_HISTORICO_SENHA_PRIACESSO		14

#define ID_SISTEMA_ORIGEM_VOL                   9
#define ID_SISTEMA_ORIGEM_FO					7

#define SGCLASSIFICACAO_EMAIL					"E-MAIL"

#define TP_OPERACAO_ENCAMINHAMENTO				2

/*******************************************************************************/

#endif // CDEFINES