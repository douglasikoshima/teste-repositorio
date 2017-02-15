//---------------------------------------------------------------------------
//                         (c) Consórcio Indra/PT-SI.
//                            xxxxxxxxxxxxxxxxxxxxxxx
//                                xxxxxxxxxxxxxx
//-----------------------------------------------------------------------------
// Los contenidos de este fichero son propiedad de Telefónica Consórcio Indra/PT-SI. 
// titular del copyright. Este fichero solo podra ser copiado, distribuido y utilizado, 
// en su totalidad o en parte, con el permiso escrito de Consórcio Indra/PT-SI 
// o de acuerdo con los terminos y condiciones establecidas en el acuerdo/contrato bajo 
// el que se suministra.
//---------------------------------------------------------------------------
//*  Modulo                   : LSTQTDFAVOR
//*  Fichero                  : LstQtdFavor
//*  Tipo                     : .cpp
//*  Autor                    : Jones Randis
//*  Fecha primera version    : 
//*  Version actual           : 
//*//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Retornar a quantidade de linhas favoritas permitida para a UF.
//*  
//*//---------------------------------------------------------------------------
//*  Dependencias:
//*
//*  Linha.hpp
//*//---------------------------------------------------------------------------
//*  Consideraciones de portabilidad:
//*
//*  
////---------------------------------------------------------------------------

#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <Util/Util.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTQTDFAVOR);

int callGetFavoritos(char *pcCdAreaRegistro, char *pcNrLinha, char *pcUsuario, string &strRetMsg, string &strExceptionTratado);
int GetInnerXML(string & strXML, const string & strTAGName);
char szAux[4000 + 1];
long iErrorNumber=0;

#define ECD_DDD_NAO_CADASTRADO_COMO_AUTORIZADO					  "24E9012"
#define ECD_REGIONAL_NAO_CADASTRADA_COMO_AUTORIZADA				  "24E9013"
#define EMSG_DDD_NAO_CADASTRADO_COMO_AUTORIZADO					  "O DDD não foi cadastrado na tabela de autorização para favoritos."
#define EMSG_REGIONAL_NAO_CADASTRADA_COMO_AUTORIZADA			  "A REGIONAL não foi cadastrada na tabela de autorização para favoritos."


void implLSTQTDFAVOR::Execute(DOMNode* dnode, XMLGen* xml_g)
{

	ULOG_START("implLSTQTDFAVOR::Execute()");
	
	ULOG_START("Acesso ao servico LSTQTDFAVOR. Entrada.");
    // Acertando o nivel de logs
			
	CTuxHelperClever helper;
	char* pcCdAreaRegistro;
	char* pcNrLinha;
	char* pcUsuario;
	char pcProxyLinha[256 + 1];
	memset(pcProxyLinha, 0x00, sizeof(pcProxyLinha));
	CLinha oLinha;
    char szXml[4000 + 1];
	memset(szXml, 0x00, sizeof(szXml));
	memset(szAux, 0x00, sizeof(szAux));
	string strqtMaxInclusao;

	pcCdAreaRegistro = helper.walkTree(dnode, "cdAreaRegistro", 0);
	pcNrLinha = helper.walkTree(dnode, "nrLinha", 0);
	pcUsuario = helper.walkTree(dnode, "usuario", 0);

	try {
	
		if (pcCdAreaRegistro == NULL)
		{
			ULOG("TAG_<cdAreaRegistro>_INEXISTENTE");
			throw new TuxBasicSvcException("00E0000", "TAG_<cdAreaRegistro>_INEXISTENTE");
		}
		if (*pcCdAreaRegistro == '\0')
		{
			ULOG("TAG_<cdAreaRegistro>__VALOR_VAZIO");
			throw new TuxBasicSvcException("00E0000", "TAG_<cdAreaRegistro>__VALOR_VAZIO");
		}
		if (CUtil::IsNumeric(pcCdAreaRegistro) == 0)
		{
			ULOG("TAG_<cdAreaRegistro>__VALOR_INVALIDO");
			throw new TuxBasicSvcException("00E0000", "TAG_<cdAreaRegistro>__VALOR_INVALIDO");
		}		
		if (pcNrLinha == NULL)
		{
			ULOG("TAG_<nrLinha>_INEXISTENTE");
			throw new TuxBasicSvcException("00E0000", "TAG_<nrLinha>_INEXISTENTE");
		}
		if (*pcNrLinha == '\0')
		{
			ULOG("TAG_<nrLinha>__VALOR_VAZIO");
			throw new TuxBasicSvcException("00E0000", "TAG_<nrLinha>__VALOR_VAZIO");		
		}
			
		if (pcUsuario == NULL)
		{
			ULOG("TAG_<usuario>_INEXISTENTE");
			throw new TuxBasicSvcException("00E0000", "TAG_<usuario>_INEXISTENTE");
		}
		if (*pcUsuario == '\0')
		{
			ULOG("TAG_<usuario>__VALOR_VAZIO");
			throw new TuxBasicSvcException("00E0000", "TAG_<usuario>__VALOR_VAZIO");
		}
		
			/*Old Implementation*/
			/*oLinha.setIdUF(atoi(pcCdAreaRegistro));*/
			//oLinha.setCdAreaRegistro(atoi(pcCdAreaRegistro));

	/*	JAVA -> FO

		<msg>
			<msgHdr>
				<user>1</user>
				<service>TUXNGINBE</service>
			</msgHdr>
			<msgBody>
				<ProxyOperacao>getFavorito</ProxyOperacao>
				<ProxyLinha>4191009650</ProxyLinha>
				<usuario>Portal</usuario>
				<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
			</msgBody>
		</msg>
	*/
			string strExceptionTratado;
			string strExcerptXml;
			ULOG("xml de envio para o servico getFavoritos='%s'\r\n", szXml);
			
			int intRC = 0;
			
			try{
				int intRC = callGetFavoritos(pcCdAreaRegistro,pcNrLinha,pcUsuario,strExcerptXml,strExceptionTratado);
				
				if (intRC < 0)
				{
					ULOG("valor do strExceptionTratado  [%s]", (char *)strExceptionTratado.data());
					if (strExceptionTratado.compare("24E9012") == 0 )
					{
						setStatusCode("24W9012", "O DDD não foi cadastrado na tabela de autorização para favoritos.");
					}
					else if (strExceptionTratado.compare("24E9013") == 0 )
					{
						setStatusCode("24W9013", "A REGIONAL não foi cadastrada na tabela de autorização para favoritos.");
					}
					else if (strExceptionTratado.compare("24E9014") == 0 )
					{
						setStatusCode("24W9014", "Serviço nao subscrito.");
					}
					else if (strExceptionTratado.compare("24E9007") == 0 )
					{
						setStatusCode("24W9007", "Saldo insuficiente");
					}
					else if (strExceptionTratado.compare("24E9008") == 0 )
					{
						setStatusCode("24W9008", "Celular não cadastrado");
					}
					else if (strExceptionTratado.compare("24E3002") == 0 )
					{
						setStatusCode("24W3002", "Erro desconhecido.");
					}
					else
					{
						setStatusCode("11W0002", "Consulta não realizada.");
					}
				}
				else
				{
					strqtMaxInclusao = strExcerptXml;
					
					if (GetInnerXML(strqtMaxInclusao, string("qtMaxInclusao")) == -1 )
					{
						ULOG("Ocorreu um erro ao tentar obter o conteudo da TAG <qtMaxInclusao> do XML=%s",(char *)strExcerptXml.c_str());
						strqtMaxInclusao = "qtMaxInclusao NULO";
						throw new TuxBasicSvcException("00E0000", "qtMaxInclusao NULO");
					}
					else
					{
						ULOG("Conteudo [%s] da TAG <qtMaxInclusao> do XML=%s obtido com sucesso",strqtMaxInclusao.c_str(), strExcerptXml);
					}
					xml_g->createTag("LSTQTDFAVORVO");
					xml_g->addProp("xmlns", "servicos.vol.vivo.com.br/vo");
					//xml_g->addItem("qtFavoritos", oLinha.getQtFavoritos());
					xml_g->addItem("qtFavoritos", (char *)strqtMaxInclusao.c_str());
					xml_g->closeTag();
					
					setStatusCode("11I0000", "Consulta realizado com sucesso.");
				}
			}
			catch (...) 
			{
				setStatusCode("11W0002", "Consulta não realizada.");
			}
	}
	catch (TuxBasicOraException &e) 
	{
		ULOG_END("LSTQTDFAVOR --------> entrou aqui. catch (TuxBasicOraException &e) ");
	
		if (e.eCode == NO_DATA_FOUND)
			setStatusCode("11W0001", "Quantidade de Favoritos não cadastrada para UF.");
		else 
			setStatusCode("11W0002", "Consulta não realizada.");
	}
	
	
	ULOG_END("implLSTQTDFAVOR::Execute()");
}

int callGetFavoritos(char *pcCdAreaRegistro, char *pcNrLinha, char *pcUsuario, string &strRetMsg, string &strExceptionTratado) 
{
	char pcNrLinhaCompleta[20];
	memset(pcNrLinhaCompleta, 0, sizeof(pcNrLinhaCompleta));
	sprintf(pcNrLinhaCompleta, "%s%s", pcCdAreaRegistro, pcNrLinha);
	pcNrLinhaCompleta[10] = NULL;
	
	XMLGen *pclXmlGenGetFavoritos=NULL;
	TuxRemoteService *remoteServiceGetFavoritos=NULL;
	TuxMessage *inputMessageGetFavoritos=NULL;
	
	
	pclXmlGenGetFavoritos = new XMLGen;
	remoteServiceGetFavoritos = new TuxRemoteService();
	inputMessageGetFavoritos = new TuxMessage();
	int iRetSistemaLegado=0;
	char *pStatusText=NULL;
	char *pStatusCode=NULL;

	try
	{
		string strNrLinhaCompleta = pcNrLinhaCompleta;
		string strUsuario = pcUsuario;
		
		pclXmlGenGetFavoritos->addItem("ProxyOperacao", "getFavoritos");
		pclXmlGenGetFavoritos->addItem("ProxyLinha", (char *)strNrLinhaCompleta.data());
		pclXmlGenGetFavoritos->addItem("usuario", (char *)strUsuario.data());
		pclXmlGenGetFavoritos->addItem("xmlns", "cliente.fo.vivo.com.br/vo");
		pclXmlGenGetFavoritos->addItem("sgSistemaOrigem", "NSP");

		inputMessageGetFavoritos->setUser("2");
		inputMessageGetFavoritos->setService("TUXNGINBE");
		inputMessageGetFavoritos->setMessageBody(pclXmlGenGetFavoritos);
		
		remoteServiceGetFavoritos->setServiceName("TUXNGINBE");
		remoteServiceGetFavoritos->setInputMessage(inputMessageGetFavoritos);
		
		ULOG("Inicio do acesso remoto ao serviço getFavoritos.");
		int len=0;
		string strXml = pclXmlGenGetFavoritos->retrieveXML( &len );
		ULOG("xml de envio para o servico getFavoritos='%s'\r\n", strXml.data());
		
		iRetSistemaLegado = remoteServiceGetFavoritos->remoteCall(); 
		
		ULOG("iRetSistemaLegado(%d)", iRetSistemaLegado);
		
		if(iRetSistemaLegado != TUXFWRET_OK)
		{
			ULOG("Erro chamada remota getFavoritos. retorno != TUXFWRET_OK");
			return -1;
		}

		
		pStatusCode = remoteServiceGetFavoritos->getOutputMessage()->getStatusCode();
		strExceptionTratado = pStatusCode;
		
		ULOG("LSTQTDFAVOR -------->  pStatusCode[%s]", (char *)strExceptionTratado.data());
	
		if(pStatusCode == NULL)
		{
			pStatusText = remoteServiceGetFavoritos->getOutputMessage()->getStatusText();
			ULOG("pStatusText[%s]", pStatusText?pStatusText:"...NULL...", pStatusText);
			return -1;
		}

		if(strlen(pStatusCode) ==0) 
		{
			pStatusText = remoteServiceGetFavoritos->getOutputMessage()->getStatusText();
			ULOG("pStatusText[%s]", pStatusText?pStatusText:"...NULL...", pStatusText);
			return -1;
		}

		if(pStatusCode[2] != 'I') 
		{
			pStatusText = remoteServiceGetFavoritos->getOutputMessage()->getStatusText();
			ULOG("pStatusText[%s]", pStatusText?pStatusText:"...NULL...", pStatusText);
			return -1;
		}

		char* pc_textMessage = remoteServiceGetFavoritos->getOutputMessage()->getMessageBody();
		if (pc_textMessage == NULL)
		{
			ULOG("remoteService.getOutputMessage()->getMessageBody() retornou NULL");
			return -1;
		}
		else
		{
			ULOG("remoteService.getOutputMessage()->getMessageBody() retornou o XML[%s]", pc_textMessage);
			strRetMsg = pc_textMessage;
			free(pc_textMessage);
		}
		return 0;
	}
	catch(...)
	{
		ULOG("Retorno de erro na chamada remoto ao serviço getFavoritos.");
		ULOG("*********%s\r\n.", (char *)strExceptionTratado.data());
		return -1;
	}
	ULOG("Fim do acesso remoto ao serviço getFavoritos.");
	
	ULOG("pStatusCode[%s](%p)", pStatusCode?pStatusCode:"...NULL...", pStatusCode);
	ULOG("pStatusText[%s](%p)", pStatusText?pStatusText:"...NULL...", pStatusText);
    return 0;
}

int callGetFavoritos2(char *pXmlSaida, char *pRetMsg) 
{
    char *sendbuf, *rcvbuf;
    long sendlen, rcvlen;

    sendlen = strlen(pXmlSaida);

    // sprintf(szAux, "Tamanho da msg = (%d)\n", sendlen); oLog.logInformation(szAux);

    if((sendbuf = (char *) tpalloc("STRING", NULL, sendlen+1)) == NULL) {
        ULOG("Error allocating send buffer\r\n");
        tpterm();

        return -1;
    }

    if((rcvbuf = (char *) tpalloc("STRING", NULL, sendlen+1)) == NULL) {
		ULOG("Error allocating receive buffer\r\n");
        tpfree(sendbuf);
        tpterm();

        return -1;
    }

    strcpy(sendbuf, pXmlSaida);

    if(tpcall("TUXNGINBE", (char *)sendbuf, sendlen, (char **)&rcvbuf, &rcvlen, (long)0) == -1) {
        ULOG("Tperrno = %d\r\n", tperrno);
        ULOG("rcvbuf[%s]\r\n", rcvbuf);
        

        iErrorNumber=tperrno;
        strcpy(pRetMsg, rcvbuf);

        tpfree(sendbuf);
        tpfree(rcvbuf);

        return 1;
    }

    iErrorNumber=0;
    strcpy(pRetMsg, rcvbuf);

    tpfree(sendbuf);
    tpfree(rcvbuf);

    return 0;
}

int GetInnerXML(string & strXML, const string & strTAGName)
{
	//ULOG("DENTRO DA FUNCAO  GetInnerXML() ");

	//ULOG("Valor do XML=%s",strXML.c_str());
	int cutAt;

	if(strXML.length() <= 0)
	{
		return -1;
	}

	string strTemp(" ");
	strTemp.append(strXML);

	int iPosInicial = 0;
	int iPosFinal = 0;

	string strDelim;


	// OBTEM POSIÇÃO INICIAL
	strDelim = "<";
	strDelim.append(strTAGName.c_str());
	//strDelim.append(">");
	int icutAt2=0;
	if( (cutAt = strTemp.find(strDelim)) != strTemp.npos )
	{
		if(cutAt > 0)
		{
			if ( (icutAt2 = strTemp.find_first_of(">",cutAt+1)) != strTemp.npos)
			{
				iPosInicial = icutAt2+1;
			}
		}
	}
	//ULOG("Valor da cutAt=%d",cutAt);
	//ULOG("Valor da icutAt2=%d",icutAt2);
	//ULOG("Valor da PosicaoInicial=%d",iPosInicial);

	// OBTEM POSIÇÃO FINAL
	strDelim = "</";
	strDelim.append(strTAGName.c_str());
	strDelim.append(">");
	if( (cutAt = strTemp.find(strDelim)) != strTemp.npos )
	{
		if(cutAt > 0)
		{
			iPosFinal = cutAt;
		}
	}
	
	//ULOG("Valor da cutAt=%d",cutAt);
	//ULOG("Valor da PosicaoFinial=%d",iPosFinal);
	
	if( (iPosInicial != 0 ) && (iPosFinal != 0 ))
	{
		strXML = strTemp.substr(iPosInicial, iPosFinal-iPosInicial);
		return 0;
	}
	else
	{
		return -1;
	}
	
	//ULOG("SAIU DA FUNCAO  GetInnerXML() ");	
}
