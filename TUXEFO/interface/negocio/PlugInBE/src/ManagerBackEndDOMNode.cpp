#include "ManagerBackEndDOMNode.h"

#include <tuxfw.h>


BackEndDOMNode::BackEndDOMNode()
{
	tuxfw_getlogger()->debug("BackEndDOMNode::BackEndDOMNode()");

	this->m_pParser = NULL;
	this->m_pMemBuf = NULL;
}

BackEndDOMNode::~BackEndDOMNode()
{
	tuxfw_getlogger()->debug("BackEndDOMNode::~BackEndDOMNode()");
}


// Set para o Parser e MemBuff
void BackEndDOMNode::setDOMNode(XercesDOMParser* pParser, MemBufInputSource* pMemBuf)
{
	tuxfw_getlogger()->debug("BackEndDOMNode::setDOMNode()");
	
	this->m_pParser = pParser;
	this->m_pMemBuf = pMemBuf;

	// Atribui o BUffer ao Parser
	this->m_pParser->parse(*this->m_pMemBuf);
}

// Get para o DOMNode
DOMNode* BackEndDOMNode::getDOMNode()
{
	tuxfw_getlogger()->debug("BackEndDOMNode::getDOMNode()");

	return this->m_pParser->getDocument();
}

// realese Memory do Parser e do Membuff
void BackEndDOMNode::releaseDOMNode()
{
	tuxfw_getlogger()->debug("BackEndDOMNode::releaseDOMNode()");

	if (this->m_pMemBuf)
		delete this->m_pMemBuf;
	
	if (this->m_pParser)
		delete this->m_pParser;
}

// Sobrecarga de operadores
BackEndDOMNode& BackEndDOMNode::operator= ( const BackEndDOMNode &oP)
{
	tuxfw_getlogger()->debug("BackEndDOMNode::operator=(BackEndDOMNode&)");

	this->m_pParser = oP.m_pParser;
	this->m_pMemBuf = oP.m_pMemBuf;

	return *this;
}


// Manager BackEndDOMNode
ManagerBackEndDOMNode::ManagerBackEndDOMNode()
{
	tuxfw_getlogger()->debug("ManagerBackEndDOMNode::ManagerBackEndDOMNode()");

	// Garante que a lista esta vazia
	m_lstDOMNodes.clear();

	tuxfw_getlogger()->debug("ManagerBackEndDOMNode::m_lstDOMNodes.clear()");
}

ManagerBackEndDOMNode::~ManagerBackEndDOMNode()
{
	tuxfw_getlogger()->debug("ManagerBackEndDOMNode::~ManagerBackEndDOMNode()");
	
	BackEndDOMNode oDOMNode;

	while(0 < m_lstDOMNodes.size())
	{	
		// Obtem uma o Primeiro DOMNode da Lista
		oDOMNode = m_lstDOMNodes.front();

		// Libera a Memoria Alocada
		oDOMNode.releaseDOMNode();

		//Remove da Lista.
		m_lstDOMNodes.pop_front();
		tuxfw_getlogger()->debug("ManagerBackEndDOMNode::m_lstDOMNodes.pop_front() [Size: %d]",m_lstDOMNodes.size());
	}
}

// Factory for DOMNodes
DOMNode* ManagerBackEndDOMNode::createDOMNode(char *textXml)
{
	tuxfw_getlogger()->debug("ManagerBackEndDOMNode::createDOMNode()");

	if(textXml == NULL)
		return NULL;

	BackEndDOMNode oDOMNode;

	const char pMemBufId[] = "inputInfo";
		
	oDOMNode.setDOMNode(
		new XercesDOMParser(),
		new MemBufInputSource((const XMLByte*)textXml, strlen(textXml), pMemBufId)
	);

    // Guarda o DOMNode Criado na Lista de Alocados.	
	m_lstDOMNodes.push_back(oDOMNode);

	tuxfw_getlogger()->debug("ManagerBackEndDOMNode::m_lstDOMNodes.push_back(oDOMNode) [Size: %d]",m_lstDOMNodes.size());

	return oDOMNode.getDOMNode();
}

