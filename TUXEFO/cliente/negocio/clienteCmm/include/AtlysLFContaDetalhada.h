// AtlysLFContaDetalhada.h: interface for the 
// CAtlysLFContaDetalhada class.
//////////////////////////////////////////////////////////////////////
 
#ifndef AtlysLFContaDetalhadaHH
#define AtlysLFContaDetalhadaHH
 
#define XML_INT_MSG_BODY					"msgBody"
#define XML_INT_ID_CONTA					"idConta"
#define XML_INT_NR_CONTA                    "nrConta"
#define XML_INT_DS_CICLO_FATURA             "dsCicloFatura"

class CAtlysLFContaDetalhada:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CAtlysLFContaDetalhada(int, char*, char*);
 	virtual ~CAtlysLFContaDetalhada();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getXMLLFContaDetalhada();
 
private:
  	char* cLFContaDetalhada;
};
 
#endif