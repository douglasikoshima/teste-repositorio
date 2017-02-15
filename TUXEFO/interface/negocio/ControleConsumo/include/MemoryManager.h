#ifndef MEMORYMANAGER_H
#define MEMORYMANAGER_H
#include <tuxfw.h>
#include "../../commons/include/vectorlist.h"

class DOMNodeParser{
public:
	DOMNodeParser();
	DOMNode* getDOMNode();
	~DOMNodeParser();
	void setMemBuf(MemBufInputSource*);
	void setParser(XercesDOMParser*);
	void parse();
private:
	XercesDOMParser*   m_pParser;
	MemBufInputSource* m_pMemBuf;
};

class XMLStringBuffer{
public:
	XMLStringBuffer(char*buffer){this->m_buffer = buffer;tuxfw_getlogger()->debug("XMLStringBuffer::XMLStringBuffer()");}
	~XMLStringBuffer(){XMLString::release(&m_buffer);tuxfw_getlogger()->debug("XMLStringBuffer::~XMLStringBuffer()");}
public:
	char*m_buffer;
};

class StringBuffer{
public:
	StringBuffer(char*buffer){this->m_buffer = buffer;}
	~StringBuffer(){free(m_buffer);}
public:
	char*m_buffer;
};

typedef CVectorList<DOMNodeParser> ListDOMNode;
typedef CVectorList<XMLStringBuffer> ListXMLStringBuffer;
typedef CVectorList<StringBuffer> ListStringBuffer;

class CMemoryManager : public TuxHelper
{
public:
	CMemoryManager();
	~CMemoryManager();
	DOMNode* createDOMNode(char *buffer);
	char* getTag( DOMNode* pdnNode, char* pcNomeTag );
	char* getTag( DOMNode* pdnNode, char* pcNomeTag, int nNivel );
	void addChar(char*buffer);
private:
	ListDOMNode m_listDOMNode;
	ListXMLStringBuffer m_listXmlStringBuffer;
	ListStringBuffer m_listStringBuffer;
};

#endif