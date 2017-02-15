//---------------------------------------------------------------------
//*
//* Class: ExtratoPontos
//---------------------------------------------------------------------
//*
//* Retorna XML de saldo de pontos.
//* 
//---------------------------------------------------------------------

#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Ponto/Ponto.hpp>
#include <Util/Util.hpp>

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
            "		<LINHAS>\n" \
            "			<LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
			"				<NUM_LINHA>9637698</NUM_LINHA>\n" \
            "			</LINHA>\n" \
            "			<LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99226218</NUM_LINHA>\n" \
            "		    </LINHA>\n" \
            "		    <LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99259840</NUM_LINHA>\n" \
            "		    </LINHA>\n" \
            "		    <LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99876218</NUM_LINHA>\n" \
            "			</LINHA>\n" \
            "		</LINHAS>\n" \
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
            "		<EXPIRACAO>\n" \
            "			<DATA_EXPIRACAO>31/05/2005 00:00:00</DATA_EXPIRACAO>\n" \
            "			<QTD_PONTOS>1082</QTD_PONTOS>\n" \
            "		</EXPIRACAO>\n" \
            "		<EXPIRACAO>\n" \
            "			<DATA_EXPIRACAO>30/06/2005 00:00:00</DATA_EXPIRACAO>\n" \
			"			<QTD_PONTOS>4958</QTD_PONTOS>\n" \
            "		</EXPIRACAO>\n" \
			"	</CONTA>\n" \
			"	<CONTA>\n" \
			"		<NUM_CONTA>4572206</NUM_CONTA>\n" \
            "		<LINHAS>\n" \
            "			<LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
			"				<NUM_LINHA>96376989</NUM_LINHA>\n" \
            "			</LINHA>\n" \
            "			<LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99226218</NUM_LINHA>\n" \
            "		    </LINHA>\n" \
            "		    <LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99259840</NUM_LINHA>\n" \
            "		    </LINHA>\n" \
            "		    <LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99876218</NUM_LINHA>\n" \
            "			</LINHA>\n" \
            "		</LINHAS>\n" \
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
            "		<EXPIRACAO>\n" \
            "			<DATA_EXPIRACAO>31/05/2005 00:00:00</DATA_EXPIRACAO>\n" \
            "			<QTD_PONTOS>1082</QTD_PONTOS>\n" \
            "		</EXPIRACAO>\n" \
            "		<EXPIRACAO>\n" \
            "			<DATA_EXPIRACAO>30/06/2005 00:00:00</DATA_EXPIRACAO>\n" \
			"			<QTD_PONTOS>4958</QTD_PONTOS>\n" \
            "		</EXPIRACAO>\n" \
			"	</CONTA>\n" \
			"	<CONTA>\n" \
			"		<NUM_CONTA>4572206</NUM_CONTA>\n" \
            "		<LINHAS>\n" \
            "			<LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
			"				<NUM_LINHA>9637698</NUM_LINHA>\n" \
            "			</LINHA>\n" \
            "			<LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99226218</NUM_LINHA>\n" \
            "		    </LINHA>\n" \
            "		    <LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99259840</NUM_LINHA>\n" \
            "		    </LINHA>\n" \
            "		    <LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99876218</NUM_LINHA>\n" \
            "			</LINHA>\n" \
            "		</LINHAS>\n" \
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
            "		<EXPIRACAO>\n" \
            "			<DATA_EXPIRACAO>31/05/2005 00:00:00</DATA_EXPIRACAO>\n" \
            "			<QTD_PONTOS>1082</QTD_PONTOS>\n" \
            "		</EXPIRACAO>\n" \
            "		<EXPIRACAO>\n" \
            "			<DATA_EXPIRACAO>30/06/2005 00:00:00</DATA_EXPIRACAO>\n" \
			"			<QTD_PONTOS>4958</QTD_PONTOS>\n" \
            "		</EXPIRACAO>\n" \
			"	</CONTA>\n" \
			"	<CONTA>\n" \
			"		<NUM_CONTA>4572206</NUM_CONTA>\n" \
            "		<LINHAS>\n" \
            "			<LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
			"				<NUM_LINHA>9637698</NUM_LINHA>\n" \
            "			</LINHA>\n" \
            "			<LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99226218</NUM_LINHA>\n" \
            "		    </LINHA>\n" \
            "		    <LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99259840</NUM_LINHA>\n" \
            "		    </LINHA>\n" \
            "		    <LINHA>\n" \
            "		        <AREA>21</AREA>\n" \
            "		        <NUM_LINHA>99876218</NUM_LINHA>\n" \
            "			</LINHA>\n" \
            "		</LINHAS>\n" \
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
            "		<EXPIRACAO>\n" \
            "			<DATA_EXPIRACAO>31/05/2005 00:00:00</DATA_EXPIRACAO>\n" \
            "			<QTD_PONTOS>1082</QTD_PONTOS>\n" \
            "		</EXPIRACAO>\n" \
            "		<EXPIRACAO>\n" \
            "			<DATA_EXPIRACAO>30/06/2005 00:00:00</DATA_EXPIRACAO>\n" \
			"			<QTD_PONTOS>4958</QTD_PONTOS>\n" \
            "		</EXPIRACAO>\n" \
			"	</CONTA>\n" \
			"</msgBody>";

	m_pxmlExtrato = new char[strlen(m_pxmlExtratoTemp) + 1];

	strcpy(m_pxmlExtrato, m_pxmlExtratoTemp);

#endif

}

CPonto::~CPonto()
{
	if(m_pxmlExtrato)
		delete m_pxmlExtrato;
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
	// Monta XML de entrada para consulta
	XMLGen		xmlInput;

	//int			iXMLLen;
	int			iRet = TUXFWRET_ERROR;

	bool        blPontosFora = false;

	if(m_pxmlExtrato)
		delete m_pxmlExtrato;

	// Montando o XML de entrada com o sistema legado
	xmlInput.addItem("COD_AREA", iCdAreaRegistro);
	xmlInput.addItem("NUM_LINE", iNrLinha);
	xmlInput.addItem("TRAZ_EXTRATO", "S");
	xmlInput.addItem("NUM_LANCAMENTOS", this->getNrLancamentos());
	xmlInput.addItem("TRAZ_EXPIRACAO", "S");
	xmlInput.addItem("QTD_MESES", "3");
	
	// Faz a solicitação ao serviço de consulta de HEXA no Atlys.
	TuxRemoteService* remoteService;
	TuxMessage* inputMessage;

	remoteService = new TuxRemoteService();
	inputMessage = new TuxMessage();
	
	//inputMessage->setUser(this->getUser());
	inputMessage->setUser("VOL");
	inputMessage->setMessageBody(&xmlInput);

	//tuxfw_getlogger()->information("\r\nEXTRATO::XML_ENTRADA:\r\n%s\r\n", xmlInput.retrieveXML(&iXMLLen)); 

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
		char* pcStatusCode = NULL;
		char* pcStatusText = NULL;
		int	  iAllocLen;

		pcStatusCode = remoteService->getOutputMessage()->getStatusCode();
		
		if(pcStatusCode[2] != 'I')
		{
			pcStatusText = remoteService->getOutputMessage()->getStatusText();

			TuxBasicSvcException e(pcStatusCode, pcStatusText);

			delete inputMessage;
			delete remoteService;

			free(pcStatusCode);
			free(pcStatusText);

			throw e;
		}
		
		free(pcStatusCode);

		// Guarda mensagem de retorno
		m_pxmlExtratoTemp = remoteService->getOutputMessage()->getMessageBody();

		iAllocLen = strlen(xml_inf) + strlen(m_pxmlExtratoTemp) + 1;

		m_pxmlExtrato = new char[iAllocLen];
		
		memset(m_pxmlExtrato, 0, iAllocLen);

		// criando xml
		memcpy(m_pxmlExtrato, xml_inf, strlen(xml_inf));
		memcpy(&m_pxmlExtrato[strlen(xml_inf)], m_pxmlExtratoTemp, strlen(m_pxmlExtratoTemp));
				
		tuxfw_getlogger()->information("EXTRATO::XML_SAIDA:\r\n%s\r\n", m_pxmlExtrato); 	

		free(m_pxmlExtratoTemp);
	}

	delete inputMessage;
	delete remoteService;

	if(iRet != TUXFWRET_OK)
		throw TuxBasicSvcException("11E0999", "ERRO FATAL NA COMUNICACAO COM O SISTEMA DE PONTOS");

}

#endif