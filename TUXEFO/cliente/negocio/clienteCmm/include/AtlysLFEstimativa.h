// AtlysLFEstimativa.h: interface for the 
// CAtlysLFEstimativa class.
//////////////////////////////////////////////////////////////////////
 
#ifndef AtlysLFEstimativaHH
#define AtlysLFEstimativaHH
 
#define XML_INT_MSG_BODY					"msgBody"
#define XML_INT_ID_CONTA					"idConta"
#define XML_INT_NR_CONTA                    "nrConta"
#define XML_INT_DS_CICLO_FATURA             "dsCicloFatura"

class CAtlysLFEstimativa:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CAtlysLFEstimativa(int, char*, char*);
 	virtual ~CAtlysLFEstimativa();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getXMLLFEstimativa();
 
private:
  	char* cLFEstimativa;
};
 
#endif