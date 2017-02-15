// AtlysLFLinhaIntraGrupo.h: interface for the 
// CAtlysLFLinhaIntraGrupo class.
//////////////////////////////////////////////////////////////////////
 
#ifndef AtlysLFLinhaIntraGrupoHH
#define AtlysLFLinhaIntraGrupoHH
 
#define XML_INT_MSG_BODY					"msgBody"
#define XML_INT_ID_CONTA					"idConta"
#define XML_INT_NR_CONTA                    "nrConta"
#define XML_INT_DS_CICLO_FATURA             "dsCicloFatura"

class CAtlysLFLinhaIntraGrupo:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CAtlysLFLinhaIntraGrupo(int, char*, char*);
 	virtual ~CAtlysLFLinhaIntraGrupo();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getXMLLFLinhaIntraGrupo();
 
private:
  	char* cLFLinhaIntraGrupo;
};
 
#endif