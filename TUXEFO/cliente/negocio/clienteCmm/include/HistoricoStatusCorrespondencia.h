// HistoricoStatusCorrespondencia.h: interface for the 
// CHistoricoStatusCorrespondencia class.
//////////////////////////////////////////////////////////////////////
 
#ifndef HistoricoStatusCorrespondencia
#define HistoricoStatusCorrespondencia
 
#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"

class CHistoricoStatusCorrespondencia  
{
public:
 	// Construtor/Destrutor
 	CHistoricoStatusCorrespondencia();
 	virtual ~CHistoricoStatusCorrespondencia();
 
 	// Métodos de acesso a banco de dados
 	static CHistoricoStatusCorrespondencia* listaPorCorrespondencia(int, int*);	// Lista todos os histõricos de status por correspondência
 
    char* getStatusAtual(int);

 	// Métodos de acesso aos atributos
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