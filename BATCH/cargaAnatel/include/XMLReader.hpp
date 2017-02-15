#ifndef XMLREADER_H
#define XMLREADER_H

#include "../../commons/Log/include/Log.h"
#include "../../commons/tinyxml/include/tinyxml.h"

class XMLReader {
	private:		
		Log log;
	public:
		XMLReader();
		~XMLReader();
		const char* getNodeValue(TiXmlNode *node, const char *value);		
};

#endif