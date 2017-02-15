#include "MemoryManager.h"

///////////////////////////////////////////////////////////////////
// DOMNodeParser
DOMNodeParser::DOMNodeParser()
{
	tuxfw_getlogger()->debug("DOMNodeParser::DOMNodeParser()");
	this->m_pMemBuf = NULL;
	this->m_pParser = NULL;
}
DOMNodeParser::~DOMNodeParser()
{
	tuxfw_getlogger()->debug("DOMNodeParser::~DOMNodeParser()");
	if(this->m_pMemBuf != NULL)
		delete this->m_pMemBuf;

	if(this->m_pParser != NULL)
		delete this->m_pParser;	
}
DOMNode* DOMNodeParser::getDOMNode(){
	tuxfw_getlogger()->debug("DOMNodeParser::getDOMNode()");
	return this->m_pParser->getDocument();
}

void DOMNodeParser::setParser(XercesDOMParser*parser){
	tuxfw_getlogger()->debug("DOMNodeParser::setParser()");
	this->m_pParser = parser;
}

void DOMNodeParser::setMemBuf(MemBufInputSource* membuf){
	tuxfw_getlogger()->debug("DOMNodeParser::setMemBuf()");
	this->m_pMemBuf = membuf;
}

void DOMNodeParser::parse(){
	tuxfw_getlogger()->debug("DOMNodeParser::parse()");
	this->m_pParser->parse(*this->m_pMemBuf);
}

///////////////////////////////////////////////////////////////////
// CMemoryManager
CMemoryManager::CMemoryManager()
{

}
	
CMemoryManager::~CMemoryManager()
{
	tuxfw_getlogger()->debug("CMemoryManager::~CMemoryManager()");
}

DOMNode* CMemoryManager::createDOMNode(char *buffer)
{
	tuxfw_getlogger()->debug("CMemoryManager::createDOMNode()");
	const char pMemBufId[] = "inputInfo";
	DOMNodeParser*dom = new DOMNodeParser();
	dom->setParser(new XercesDOMParser());
	dom->setMemBuf(new MemBufInputSource((const XMLByte*)buffer, strlen(buffer), pMemBufId));
	dom->parse();
	this->m_listDOMNode.add(dom);
	return dom->getDOMNode();
}

char* CMemoryManager::getTag( DOMNode* pdnNode, char* pcNomeTag )
{
	tuxfw_getlogger()->debug("CMemoryManager::getTag()");
	return getTag( pdnNode, pcNomeTag, 0 );
}
char* CMemoryManager::getTag( DOMNode* pdnNode, char* pcNomeTag, int nNivel )
{
	char*tag = TuxHelper::walkTree( pdnNode, pcNomeTag, nNivel );
	XMLStringBuffer *buff = new XMLStringBuffer(tag);
	this->m_listXmlStringBuffer.add(buff);
	return tag;
}

void CMemoryManager::addChar(char*buffer)
{
	this->m_listStringBuffer.add(new StringBuffer(buffer));
}
