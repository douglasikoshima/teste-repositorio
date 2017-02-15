// HLRHexa.h: interface for the 
// CHLRHexa class.
//////////////////////////////////////////////////////////////////////
 
#ifndef HLRHexa
#define HLRHexa

#define NRO_ERR_LINHA_NAO_ENCONTRADA		"24E0489"
#define MSG_ERR_LINHA_NAO_ENCONTRADA		"Linha não encontrada"
#define NRO_ERR_USUARIO_NAO_ENCONTRADO		"24E0490"
#define MSG_ERR_USUARIO_NAO_ENCONTRADO		"Usuário/Senha não encontrado"
#define NRO_ERR_COMM_HLR					"24E0491"
#define MSG_ERR_COMM_HLR					"Erro recuperando Hexa da HLR"
#define NRO_ERR_CHAVE_NAO_ENCONTRADA		"24E0492"
#define MSG_ERR_CHAVE_NAO_ENCONTRADA		"Chave de criptografia não encontrada"

class CHLRHexa:public TuxHelper  
{
public:
 	// Construtor/Destrutor
 	CHLRHexa(int);
 	virtual ~CHLRHexa();
 
 	// Métodos de acesso aos atributos
 	// Getters
 	char* getHexa();
 	char* getMin();
 
private:
  	char cHexa[9];
    char szMin[8 + 1];
};
 
#endif
