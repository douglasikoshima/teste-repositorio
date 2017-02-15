#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <DOMTreeReporter/DOMTreeReporter.hpp>
#include <Defines/Defines.h>
#include <Linha/Linha.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTEXTRATOPO);

static void MoveTags(XercesDOMParser* parser, char *cNewTag, char *cTag)
{
	XMLCh tempStr[STR_SIZE + 1];
	XMLString::transcode("CONTA", tempStr, STR_SIZE);

	DOMDocument* doc		= parser->getDocument();		
	DOMNodeList* contaList  = doc->getElementsByTagName(tempStr);

	DOMNode    * contaNode;
	DOMNode    * extsNode;

	if(contaList->getLength() <= 0)
		return;
	
	///
	XMLString::transcode(cTag, tempStr, STR_SIZE);
	DOMNodeList* extList  = doc->getElementsByTagName(tempStr);	

	if(extList->getLength() > 0)
	{
		///
		XMLString::transcode(cNewTag, tempStr, STR_SIZE);
		
		DOMElement*  extElement = doc->createElement(tempStr);
		
		contaNode  = contaList->item(0);

		contaNode->appendChild(extElement);
		
		DOMNodeList* extsList = doc->getElementsByTagName(tempStr);

		extsNode = extsList->item(0);

		DOMNode *nodeRef;

		// movendo elementos cTag para dentro de cNewTag
		for(int i = extList->getLength() - 1; i >= 0; i--) 
		{
			DOMNode *node = extList->item(i);
			
			if(i == (extList->getLength() - 1))
				extsNode->appendChild(node);
			else
				extsNode->insertBefore(node, nodeRef);

			nodeRef = node;
		}
	}
}

static int RemoveTag(XercesDOMParser* parser, char* c_Area, char* c_Linha)
{
	XMLCh tempStr[STR_SIZE + 1];
	XMLString::transcode("CONTA", tempStr, STR_SIZE);

	DOMDocument* doc		= parser->getDocument();		
	DOMElement * ele		= doc->getDocumentElement();
	DOMNodeList* contaList  = doc->getElementsByTagName(tempStr);
	DOMNode    * contaNode;
	bool		 bRemove;

	int          iRet = 0;

	if(contaList->getLength() <= 0)
		return iRet;

	iRet = -1;

	for(int i = 0; i < contaList->getLength(); i++)
	{
		bRemove = true;

		contaNode = contaList->item(i);

		DOMElement * eleConta = static_cast<DOMElement *> (contaNode);

		// área da linha
		XMLString::transcode("AREA", tempStr, STR_SIZE);

		DOMNodeList* cdLinhaList = eleConta->getElementsByTagName(tempStr);
		DOMNode    * cdLinhaNode;

		// número da linha
		XMLString::transcode("NUM_LINHA", tempStr, STR_SIZE);

		DOMNodeList* linhaList = eleConta->getElementsByTagName(tempStr);
		DOMNode    * linhaNode;

		if(cdLinhaList->getLength() == linhaList->getLength())
		{
			for(int j = 0; j < linhaList->getLength(); j++)
			{
				cdLinhaNode = cdLinhaList->item(j);
				linhaNode = linhaList->item(j);

				char *pcArea  = XMLString::transcode(cdLinhaNode->getTextContent());
				char *pcLinha = XMLString::transcode(linhaNode->getTextContent());

				if(!strcmp(pcArea, c_Area) &&
				   !strcmp(pcLinha, c_Linha))
				{
					bRemove = false;
					iRet = 1;
				}

				XMLString::release(&pcArea);
				XMLString::release(&pcLinha);
			}
		}

		if(bRemove)
		{
			ele->removeChild(contaNode);
			i--;
		}
	}

	return iRet;
}

void implLSTEXTRATOPO::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CLinha oLinha;

	char*  pcTagXmlIn = NULL;
	int    iCdAreaRegistro = -1;
 	int    iNrLinha = -1;
	int    iNrLancamentos = -1;

	char*  pcXMLOut = NULL;

	char*  c_Area[32];
	char*  c_Linha[32];

	int    iLinhaEncontrada;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");
	strcpy((char *) c_Area, pcTagXmlIn);

	///
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");
	strcpy((char *) c_Linha, pcTagXmlIn);

	///
	pcTagXmlIn = helper.walkTree(dnode,"nrLancamentos", 0);
	ASSERT_INT(iNrLancamentos, pcTagXmlIn, "nrLancamentos");

	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);

	oLinha.getPonto()->setNrLancamentos(iNrLancamentos);

	xml_g->createTag("LSTEXTRATOPOVO");
	xml_g->addProp("xmlns", "servicos.vol.vivo.com.br/vo");

	try
	{
		oLinha.consultarExtratoPontos();
	}
	catch(TuxBasicSvcException &e)
	{
		xml_g->closeTag();
		
		//seta mensagem de retorno - header
		setStatusCode(e.getCode(), e.getMessage());

		return;
	}
	catch(...)
	{
		xml_g->closeTag();

		//seta mensagem de retorno - header
		setStatusCode("11W0002", "SALDO PONTOS INDISPONIVEL");

		return;
	}

	//seta mensagem de retorno - header
	setStatusCode("11W0001", "ERRO AO CRIAR EXTRATO");

	tuxfw_getlogger()->information("LSTEXTRATOPO::INICIALIZANDO AMBIENTE XML");

	// inicializando ambiente XML
	try
    {
         XMLPlatformUtils::Initialize();
    }
    catch (...)
    {
		 tuxfw_getlogger()->information("LSTEXTRATOPO::ERRO AO INICIALIZAR XMLPLATFORM\r\n"); 	
         return;
    }

	// criando parser
	XercesDOMParser* parser = new XercesDOMParser();

	parser->setValidationScheme(XercesDOMParser::Val_Never);
	parser->setCreateEntityReferenceNodes(false);

	DOMTreeReporter *errReporter = new DOMTreeReporter();
	parser->setErrorHandler(errReporter);
	
	MemBufInputSource* memBufIS = new MemBufInputSource
		(
			(const XMLByte*) oLinha.getPonto()->getXMLExtrato(), 
			strlen(oLinha.getPonto()->getXMLExtrato()), 
			"extratoPO"
		);
	
	int errorCount = -1;
	
	try
	{
		tuxfw_getlogger()->information("LSTEXTRATOPO::PARSE");

		parser->parse(*memBufIS);
		errorCount = parser->getErrorCount();			
	}
	catch (const XMLException& e)
	{
		tuxfw_getlogger()->information("LSTEXTRATOPO::XML_EXCEPT_PARSE:\r\n%s\r\n", StrX(e.getMessage())); 	
	}

	if(!errorCount)
	{
		try
		{ 
			// montando XML Final
			XMLCh tempStr[100];

			tuxfw_getlogger()->information("LSTEXTRATOPO::MONTANDO XML FINAL");

			XMLString::transcode("CONTA", tempStr, 99);
			
			// removendo contas indesejáveis do arquivo
			iLinhaEncontrada = RemoveTag(parser, (char *) c_Area, (char *) c_Linha);

			// movendo tags
			MoveTags(parser, "EXTRATOS", "EXTRATO");
			MoveTags(parser, "EXPIRACOES", "EXPIRACAO");
			
			DOMDocument* doc		= parser->getDocument();							
			DOMNodeList* contaList  = doc->getElementsByTagName(tempStr);
			DOMNode    * contaNode;							

			if(contaList->getLength() >= 1 && iLinhaEncontrada > 0)
			{
				tuxfw_getlogger()->information("LSTEXTRATOPO::AGREGANDO XML");

				contaNode  = contaList->item(0);				
				
				XMLString::transcode("LS", tempStr, 99);

				DOMImplementation *impl          = DOMImplementationRegistry::getDOMImplementation(tempStr);
				DOMWriter         *theSerializer = ((DOMImplementationLS*)impl)->createDOMWriter();

				// pegando o XML de saída
				XMLCh *xml = theSerializer->writeToString(*contaNode);
				
				//printf("\n\n\n\n XML::FINAL:\n%s\n\n\n\n", XMLString::transcode(xml));

				char *pcXml = XMLString::transcode(xml);
				
				xml_g->aggregateXML(pcXml);

				// liberando memoria
				XMLString::release(&pcXml);
				delete theSerializer;
				
				// registrando contato
				REG_CONTATO(iRes, REG_VALIDA_TAG);

				//seta mensagem de retorno - header	
				setStatusCode("11I0000", "EXTRATO CRIADO COM SUCESSO");

			}
			else if(iLinhaEncontrada < 0)
				//seta mensagem de retorno - header	
				setStatusCode("11W0003", "LINHA NAO ENCONTRADA");


		}
		catch (const XMLException& e)
		{
			tuxfw_getlogger()->information("LSTEXTRATOPO::XML_EXCEPT_DOC:\r\n%s\r\n", StrX(e.getMessage()));
		}
		catch(...)
		{
			tuxfw_getlogger()->information("LSTEXTRATOPO::XML_EXCEPT_DOC:\r\nERRO GENERICO\r\n");
		}
	}
	else
	{
		tuxfw_getlogger()->information("LSTEXTRATOPO::ERRO NO PARSE");
	}

	xml_g->closeTag();

	delete parser;
	delete errReporter;
	delete memBufIS;

	XMLPlatformUtils::Terminate();

}
