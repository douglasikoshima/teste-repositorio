#include "../include/XMLManager.hpp"

XMLManager::XMLManager() {
	log.setNivel(2);
	log.logDebug("inicializando o XMLManager");
}

XMLManager::~XMLManager() {
	log.logDebug("finalizando o XMLManager");
}

int XMLManager::parse(char *file) {
	log.logDebug("iniciando parse XML do arquivo");
	log.logDebug(file);	
	
	// carregando o arquivo XML para fazer parse
	if (this->doc.LoadFile(file)) {
		log.logDebug("parser realizado com sucesso");		
		return 1;
	} else {
		log.logError("erro de parser");
		return 0;
	}
}

int XMLManager::parseString(char *xml) {
	log.logDebug("iniciando parse XML do arquivo");
	log.logDebug(xml);	
	
	// carregando o arquivo XML para fazer parse
	this->doc.Parse((const char*)xml);
	
	return 1;
}

TiXmlElement* XMLManager::getRootElement() {
	log.logDebug("retornar o elemento principal do XML");
	
	// devolve a raiz do XML
	return this->doc.RootElement(); 
}