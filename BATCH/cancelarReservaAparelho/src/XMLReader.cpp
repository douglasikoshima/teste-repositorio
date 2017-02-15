#include "../include/XMLReader.hpp"

XMLReader::XMLReader() {
	log.setNivel(2);
	log.logDebug("XMLReader:: iniciando o XMLReader");
}
XMLReader::~XMLReader() {
	log.logDebug("XMLReader:: finalizando o XMLReader");
}

const char* XMLReader::getNodeValue(TiXmlNode *node, const char *value) {
	// log.logDebug("XMLReader::getNodeValue");	
	TiXmlNode *child = node->FirstChild(value);
	if (child != NULL) {
		TiXmlElement *element = child->ToElement();
		if (child->Type() == TiXmlNode::ELEMENT) {	
			log.log("tag = %s, valor = %s", (char*)element->Value(), (char*)element->GetText());			
			return element->GetText();
		}
	}
	return NULL;
}