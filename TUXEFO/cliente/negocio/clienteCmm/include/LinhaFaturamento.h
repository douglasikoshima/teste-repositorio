// LinhaFaturamento.h: interface for the 
// CLinhaFaturamento class.
//////////////////////////////////////////////////////////////////////

#ifndef LinhaFaturamento
#define LinhaFaturamento

#define MSG_ERR_MEMORIA		"Erro de aloca��o de mem�ria"
#define NRO_ERR_MEMORIA		"24E0359"

class CLinhaFaturamento  
{
public:
	// Construtor/Destrutor
	CLinhaFaturamento();
	virtual ~CLinhaFaturamento();

	// M�todos de acesso a banco de dados
	
	// M�todos de acesso aos atributos
	// Getters
	int getIdLinhaFaturamento();
	int getNrCodArea();
	long getNrLinha();
	char* getDsEstadoLinha();
	// Setters
	void setIdLinhaFaturamento(int value);
	void setNrCodArea(int value);
	void setNrLinha(long value);
	void setDsEstadoLinha(char* value);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdLinhaFaturamento;
	int iNrCodArea;
	long lNrLinha;
	char cDsEstadoLinha[256];
	EXEC SQL END DECLARE SECTION;
};

#endif
