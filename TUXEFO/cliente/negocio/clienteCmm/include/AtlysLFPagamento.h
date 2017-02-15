// AtlysLFPagamento.h: interface for the 
// CAtlysLFPagamento class.
//////////////////////////////////////////////////////////////////////
 
#ifndef AtlysLFPagamentoHH
#define AtlysLFPagamentoHH
 
#define XML_INT_MSG_BODY					"msgBody"
#define XML_INT_ID_CONTA					"idConta"
#define XML_INT_NR_CONTA                    "nrConta"
#define XML_INT_DS_CICLO_FATURA             "dsCicloFatura"

class CAtlysLFPagamento:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CAtlysLFPagamento(int, char*, char*);
 	virtual ~CAtlysLFPagamento();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getXMLLFPagamento();
 
private:
  	char* cLFPagamento;
};
 
#endif