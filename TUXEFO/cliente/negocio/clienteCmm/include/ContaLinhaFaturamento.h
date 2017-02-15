// ContaLinhaFaturamento.h: interface for the 
// CContaLinhaFaturamento class.
//////////////////////////////////////////////////////////////////////

#ifndef ContaLinhaFaturamento
#define ContaLinhaFaturamento

EXEC SQL INCLUDE "LinhaFaturamento.h";

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"
 
class CContaLinhaFaturamento  
{
public:
	// Construtor/Destrutor
	CContaLinhaFaturamento();
	virtual ~CContaLinhaFaturamento();

	// Métodos de acesso a banco de dados
	static CContaLinhaFaturamento* lista(long*, char*, char*, long*);

	// Métodos de acesso aos atributos
	// Getters
	long getIdConta();
	char* getIdContaSistemaOrigem();
	char* getNrConta();
	char* getDsCicloFatura();
	char* getDtVencimento();
	CLinhaFaturamento* getLinhas();
	long getNrLinhas();
	// Setters
	void setIdConta(long);
	void setIdContaSistemaOrigem(char*);
	void setNrConta(char*);
	void setDsCicloFatura(char*);
	void setDtVencimento(char*);
	void setLinhas(CLinhaFaturamento*);
	void setNrLinhas(long value);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	long iIdConta;
	char cNrConta[257];
	char cDsCicloFatura[257];
	char cDtVencimento[11];
	char cIdContaSistemaOrigem[257];
	EXEC SQL END DECLARE SECTION;
	CLinhaFaturamento* poLinhas;
	long iNrLinhas;
};

#endif