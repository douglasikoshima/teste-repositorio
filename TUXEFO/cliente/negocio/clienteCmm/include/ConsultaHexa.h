// CConsultaHexa.h: interface for the 
// CConsultaHexa class.
//////////////////////////////////////////////////////////////////////
 
#ifndef ConsultaHexa
#define ConsultaHexa
 
#define NRO_ERR_LINHA_NAO_ENCONTRADA		"24E0489"
#define MSG_ERR_LINHA_NAO_ENCONTRADA		"Linha não encontrada"

#define NRO_ERR_COMM_ATLYS					"24E0491"
#define MSG_ERR_COMM_ATLYS					"Erro recuperando Hexa da HLR"

class CConsultaHexa:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CConsultaHexa(int, char *);
 	virtual ~CConsultaHexa();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getHexa();
 
private:
  	char cHexa[256];
};
 
#endif