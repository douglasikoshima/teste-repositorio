//---------------------------------------------------------------------
//*
//* Class: ExtratoPontos
//---------------------------------------------------------------------
//*
//* Retorna XML de saldo de pontos.
//* 
//---------------------------------------------------------------------

#include <tuxfw.h>
#include "Ponto.hpp"

//#define DEBUG_CLASS_PONTO

static char* xml_inf = "<?xml version='1.0' encoding='ISO-8859-1'?>\n";

// Construtor e Destrutor
CPonto::CPonto()
{
	m_pxmlExtrato = NULL;

	m_iNrLancamentos = -1;

	this->setUser("");

#ifdef DEBUG_CLASS_PONTO

	char*	m_pxmlExtratoTemp = \
			"<?xml version='1.0' encoding='ISO-8859-1'?>\n" \
			"" \
			"<msgBody>\n" \
			"	<CONTA>\n" \
			"		<NUM_CONTA>4572206</NUM_CONTA>\n" \
			"		<SALDO>180602</SALDO>\n" \
			"		<EXTRATO>\n" \
			"			<DATA_LANCAMENTO>31/03/2004 00:00:00</DATA_LANCAMENTO>\n" \
			"			<DEBITO_CREDITO>C</DEBITO_CREDITO>\n" \
			"			<VALOR_LANCAMENTO>3279</VALOR_LANCAMENTO>\n" \
			"			<DESCR_LANCAMENTO>Créd lig serv 1</DESCR_LANCAMENTO>\n" \
			"		</EXTRATO>\n" \
			"		<EXTRATO>\n" \
			"			<DATA_LANCAMENTO>29/02/2004 00:00:00</DATA_LANCAMENTO>\n" \
			"			<DEBITO_CREDITO>C</DEBITO_CREDITO>\n" \
			"			<VALOR_LANCAMENTO>3186</VALOR_LANCAMENTO>\n" \
			"			<DESCR_LANCAMENTO>Créd lig serv 2</DESCR_LANCAMENTO>\n" \
			"		</EXTRATO>\n" \
			"		<EXTRATO>\n" \
			"			<DATA_LANCAMENTO>29/02/2004 00:00:00</DATA_LANCAMENTO>\n" \
			"			<DEBITO_CREDITO>C</DEBITO_CREDITO>\n" \
			"			<VALOR_LANCAMENTO>3186</VALOR_LANCAMENTO>\n" \
			"			<DESCR_LANCAMENTO>Créd lig serv 3</DESCR_LANCAMENTO>\n" \
			"		</EXTRATO>\n" \
			"		<EXTRATO>\n" \
			"			<DATA_LANCAMENTO>29/02/2004 00:00:00</DATA_LANCAMENTO>\n" \
			"			<DEBITO_CREDITO>C</DEBITO_CREDITO>\n" \
			"			<VALOR_LANCAMENTO>3186</VALOR_LANCAMENTO>\n" \
			"			<DESCR_LANCAMENTO>Créd lig serv 4</DESCR_LANCAMENTO>\n" \
			"		</EXTRATO>\n" \
			"		<EXTRATO>\n" \
			"			<DATA_LANCAMENTO>29/02/2004 00:00:00</DATA_LANCAMENTO>\n" \
			"			<DEBITO_CREDITO>C</DEBITO_CREDITO>\n" \
			"			<VALOR_LANCAMENTO>3186</VALOR_LANCAMENTO>\n" \
			"			<DESCR_LANCAMENTO>Créd lig serv 5</DESCR_LANCAMENTO>\n" \
			"		</EXTRATO>\n" \
			"		<EXTRATO>\n" \
			"			<DATA_LANCAMENTO>29/02/2004 00:00:00</DATA_LANCAMENTO>\n" \
			"			<DEBITO_CREDITO>C</DEBITO_CREDITO>\n" \
			"			<VALOR_LANCAMENTO>3186</VALOR_LANCAMENTO>\n" \
			"			<DESCR_LANCAMENTO>Créd lig serv 6</DESCR_LANCAMENTO>\n" \
			"		</EXTRATO>\n" \
			"	</CONTA>\n" \
			"</msgBody>";

	m_pxmlExtrato = (char *) malloc( strlen(m_pxmlExtratoTemp) + 1);

	strcpy(m_pxmlExtrato, m_pxmlExtratoTemp);

#endif

}

CPonto::~CPonto()
{
	free (m_pxmlExtrato);
}

/*

  GETs

*/

int CPonto::getNrLancamentos()
{
	return m_iNrLancamentos;
}

char* CPonto::getXMLExtrato()
{
    return m_pxmlExtrato;
}

char* CPonto::getUser()
{
	return m_cUser;
}

/*

  SETs

*/

void CPonto::setNrLancamentos(int value)
{
	m_iNrLancamentos = value;
}

void CPonto::setUser(char * value)
{
	strcpy(m_cUser, value);
}

// Métodos de negócio

#ifdef DEBUG_CLASS_PONTO

void CPonto::consultarExtrato(int iCdAreaRegistro,
                               int iNrLinha) 
{
	// do nothing
}

#else

void CPonto::consultarExtrato(int iCdAreaRegistro,
                               int iNrLinha) 
{
	// Monta XML de entrada para consulta no serviço de Atlys.
	XMLGen		xmlInput;

	int			iXMLLen;
	int			iRet = TUXFWRET_ERROR;

	// Montando o XML de entrada com o sistema legado
	xmlInput.addItem("COD_AREA", iCdAreaRegistro);
	xmlInput.addItem("NUM_LINE", iNrLinha);
	xmlInput.addItem("TRAZ_EXTRATO", "S");
	xmlInput.addItem("NUM_LANCAMENTOS", this->getNrLancamentos());
	xmlInput.addItem("TRAZ_EXPIRACAO", "N");
	
	// Faz a solicitação ao serviço de consulta de HEXA no Atlys.
	TuxRemoteService* remoteService;
	TuxMessage* inputMessage;

	remoteService = new TuxRemoteService();
	inputMessage = new TuxMessage();
	
	inputMessage->setUser(this->getUser());
	inputMessage->setMessageBody(&xmlInput);

	tuxfw_getlogger()->information("\r\nEXTRATO::XML_ENTRADA:\r\n%s\r\n", xmlInput.retrieveXML(&iXMLLen)); 

	try
	{
		// Repassa configuracoes ao manipulador do serviço remoto e invoca o servico.
		remoteService->setServiceName("SaldoPontos");
		remoteService->setInputMessage(inputMessage);

		iRet = remoteService->remoteCall();
	}
	catch(...)
	{
		// vamos asseguar que iRet esteja realmente com erro
		iRet = TUXFWRET_ERROR;
	}

	if(iRet == TUXFWRET_OK)
	{
		// temporário para montagem do XML
		char* m_pxmlExtratoTemp;
		int	  iAllocLen;

		// Guarda mensagem de retorno
		m_pxmlExtratoTemp = remoteService->getOutputMessage()->getMessageBody();
		
		iAllocLen = strlen(xml_inf) + strlen(m_pxmlExtratoTemp) + 1;

		m_pxmlExtrato = (char *) malloc(iAllocLen);
		
		memset(m_pxmlExtrato, 0, iAllocLen);

		// criando xml
		memcpy(m_pxmlExtrato, xml_inf, strlen(xml_inf));
		memcpy(&m_pxmlExtrato[strlen(xml_inf)], m_pxmlExtratoTemp, strlen(m_pxmlExtratoTemp));
				
		free(m_pxmlExtratoTemp);

		tuxfw_getlogger()->information("\r\nEXTRATO::XML_SAIDA:\r\n%s\r\n", m_pxmlExtrato); 	
	}

	delete inputMessage;
	delete remoteService;

	if(iRet != TUXFWRET_OK)
		throw new TuxBasicSvcException("11E0999", "ERRO DE COMUNICACAO COM O SISTEMA DE PONTOS");

}

#endif