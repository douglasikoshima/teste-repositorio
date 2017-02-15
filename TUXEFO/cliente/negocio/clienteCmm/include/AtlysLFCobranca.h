// AtlysLFCobranca.h: interface for the 
// CAtlysLFCobranca class.
//////////////////////////////////////////////////////////////////////
 
#ifndef AtlysLFCobrancaHH
#define AtlysLFCobrancaHH
 
#define XML_INT_MSG_BODY					"msgBody"
#define XML_INT_ID_CONTA					"idConta"
#define XML_INT_NR_CONTA                    "nrConta"
#define XML_INT_DS_CICLO_FATURA             "dsCicloFatura"

class CAtlysLFCobranca:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CAtlysLFCobranca(int, char*, char*);
 	virtual ~CAtlysLFCobranca();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getXMLLFCobranca();
 
private:
  	char* cLFCobranca;
};
 
#endif