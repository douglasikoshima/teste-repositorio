// AtlysAbaServicos.h: interface for the 
// CAtlysAbaServicos class.
//////////////////////////////////////////////////////////////////////
 
#ifndef AtlysAbaServicosHH
#define AtlysAbaServicosHH
 
#define XML_INT_MSG_BODY					"msgBody"
#define XML_INT_ID_CONTA					"idConta"
#define XML_INT_ID_LINHA                    "idLinha"

class CAtlysAbaServicos:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CAtlysAbaServicos(int, int*);
 	virtual ~CAtlysAbaServicos();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getXMLAbaServicos();
 
private:
  	char* cAbaServicos;
};
 
#endif