// AtlysLFAjustes.h: interface for the 
// CAtlysLFAjustes class.
//////////////////////////////////////////////////////////////////////
 
#ifndef AtlysLFAjustesHH
#define AtlysLFAjustesHH
 
#define XML_INT_MSG_BODY					"msgBody"
#define XML_INT_ID_CONTA					"idConta"
#define XML_INT_NR_CONTA                    "nrConta"
#define XML_INT_DS_CICLO_FATURA             "dsCicloFatura"

class CAtlysLFAjustes:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CAtlysLFAjustes(int, char*, char*);
 	virtual ~CAtlysLFAjustes();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getXMLLFAjustes();
 
private:
  	char* cLFAjustes;
};
 
#endif