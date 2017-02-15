// HistoricoStatusCorrespondencia.h: interface for the 
// CHistoricoStatusCorrespondencia class.
//////////////////////////////////////////////////////////////////////
 
#ifndef HistoricoStatusCorrespondencia
#define HistoricoStatusCorrespondencia
 
#define MSG_ERR_MEMORIA		"Erro de aloca��o de mem�ria"
#define NRO_ERR_MEMORIA		"24E0359"

class CHistoricoStatusCorrespondencia  
{
public:
 	// Construtor/Destrutor
 	CHistoricoStatusCorrespondencia();
 	virtual ~CHistoricoStatusCorrespondencia();
 
 	// M�todos de acesso a banco de dados
 	static CHistoricoStatusCorrespondencia* listaPorCorrespondencia(int, int*);	// Lista todos os hist�ricos de status por correspond�ncia
 
    char* getStatusAtual(int);

 	// M�todos de acesso aos atributos
 	// Getters
 	int getIdStatus();
 	char* getDsStatus();
 	char* getDtStatus();
 	// Setters
 	void setIdStatus(int value);
 	void setDsStatus(char* value);
 	void setDtStatus(char* value);
 
private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
	int iIdStatus;
 	char cDsStatus[256];
 	char cDtStatus[256];
 	EXEC SQL END DECLARE SECTION;
};
 
#endif