#include "ServiceAtlys.h"
#include <Util.h>

DOMNode* ServiceAtlys::inputGetBillDates(char*acctNbr) 
{
	XMLGen gen;
	DOMNode*domNode = NULL;
	gen.createTag(XML_TAG_INPUT_GET_BILL_DATES);
	gen.addProp(XML_ATTR_ACCT_NBR,acctNbr);
	domNode = this->getRemoteAPI(SERVICE_ATLYS,&gen,XML_TAG_INPUT_GET_BILL_DATES);
	// Faz tratamento de erro
	char*fault = m_mem.getTag(domNode,XML_TAG_FAULT,0);
	if(fault != NULL)
		return NULL;
	return domNode;
}


DOMNode* ServiceAtlys::inputGetBillImage(CFileXenos *file,stGetBillImage *stImage,bool bareMore) 
{	
	tuxfw_getlogger()->debug("ServiceAtlys::inputGetBillImage()");
	XMLGen gen;
	DOMNode*dom = NULL;
	gen.createTag(XML_TAG_INPUT_GET_BILL_IMAGE);
	// valores padrão, sempre passados
	gen.addProp(XML_ATTR_ACCT_NBR,stImage->acctNbr);
	gen.addProp(XML_ATTR_CYCLE_CD,stImage->cycleCd);
	gen.addProp(XML_ATTR_CYCLE_END_DT,stImage->cycleEndDt);		
	gen.addProp(XML_ATTR_DOCUMENT_TYPE,stImage->documentType);
	gen.addProp(XML_ATTR_FILE_FORMAT,stImage->fileFormat);
	gen.addProp(XML_ATTR_ACCESSNBR,stImage->accessNbr);

	// Caso os dados de afpFile forem diferentes de nulo, colocar no XML de envio
	if(bareMore)
	{
		gen.addProp(XML_ATTR_AFP_FILE,stImage->afpFile);
		gen.addProp(XML_ATTR_START_KEY,stImage->startKey);
	}

	// Chama a API remota
	tuxfw_getlogger()->debug("FAZ A CHAMADA REMOTA");
	dom = this->getRemoteAPI(SERVICE_ATLYS,&gen,XML_TAG_INPUT_GET_BILL_IMAGE);

	// Faz tratamento de erro
	char*fault = m_mem.getTag(dom,XML_TAG_FAULT,0);
	if(fault != NULL)
		return NULL;

	// verifica se existe mais dados, se existir, chamar novamente a API
	char* areMore = tuxhp.walkAttr(dom,XML_TAG_OUTPUT_GET_BILL_IMAGE,XML_ATTR_ARE_MORE,0);
	if(areMore != NULL && strcmp(areMore,"true") == 0){
		tuxfw_getlogger()->debug("Tem mais arquivos dados");
		CMemoryManager *mem = new CMemoryManager();
		char*buffer = mem->getTag(dom,XML_TAG_BILL_IMAGE,0);
		//file->write(buffer);
		Util::decodeTrimBase64(buffer,file->getFile());
		delete mem;
		stImage->afpFile = tuxhp.walkAttr(dom,XML_TAG_OUTPUT_GET_BILL_IMAGE,XML_ATTR_AFP_FILE,0);
		stImage->startKey = tuxhp.walkAttr(dom,XML_TAG_OUTPUT_GET_BILL_IMAGE,XML_ATTR_START_KEY,0);
		stImage->areMore = true;
		this->inputGetBillImage(file,stImage,true);
	}else{	
		tuxfw_getlogger()->debug("Finalizar");
		// Se já chamou antes a API, colocar no arquivo o restante
		if(stImage->areMore)
		{
			CMemoryManager *mem = new CMemoryManager();
			char*buffer = mem->getTag(dom,XML_TAG_BILL_IMAGE,0);
			//file->write(buffer);
			//file->write("\r\n");
			Util::decodeTrimBase64(buffer,file->getFile());
			delete mem;
		}
		stImage->areMore = false;
		stImage->afpFile = "";
		stImage->startKey = "";
	}
	return dom;
}

DOMNode* ServiceAtlys::inputSearchSubscriptionByAccountNumber(char*acctNbr)
{
	XMLGen gen;
	DOMNode*domNode = NULL;
	gen.createTag(XML_TAG_INPUT_SEARCH_SUBS_BY_ACCOUNT);
	gen.addProp(XML_ATTR_ACCT_NBR,acctNbr);
	domNode = this->getRemoteAPI(SERVICE_ATLYS,&gen,XML_TAG_INPUT_SEARCH_SUBS_BY_ACCOUNT);
	// Faz tratamento de erro
	char*fault = m_mem.getTag(domNode,XML_TAG_FAULT,0);
	if(fault != NULL)
		return NULL;
	return domNode;
}

