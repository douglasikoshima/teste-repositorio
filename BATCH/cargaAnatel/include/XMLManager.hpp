#ifndef XMLMANAGER_H
#define XMLMANAGER_H

#include "../../commons/Log/include/Log.h"
#include "../../commons/tinyxml/include/tinyxml.h"

class XMLManager {
	private:
		TiXmlDocument doc;
		Log log;
	public:
		XMLManager();
		~XMLManager();
		int parse(char*file);	
		int parseString(char *xml);
		TiXmlElement* getRootElement();
}; 

#endif