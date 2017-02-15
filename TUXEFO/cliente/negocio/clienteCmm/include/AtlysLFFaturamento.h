// AtlysLFFaturamento.h: interface for the 
// CAtlysLFFaturamento class.
//////////////////////////////////////////////////////////////////////
 
#ifndef AtlysLFFaturamentoHH
#define AtlysLFFaturamentoHH
 
#define XML_INT_MSG_BODY					"msgBody"
#define XML_INT_ID_CONTA					"idConta"
#define XML_INT_NR_CONTA                    "nrConta"
#define XML_INT_DS_CICLO_FATURA             "dsCicloFatura"

class CAtlysLFFaturamento:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CAtlysLFFaturamento(int, char*, char*);
 	virtual ~CAtlysLFFaturamento();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getXMLLFFaturamento();
 
private:
  	char* cLFFaturamento;
};
 
#endif