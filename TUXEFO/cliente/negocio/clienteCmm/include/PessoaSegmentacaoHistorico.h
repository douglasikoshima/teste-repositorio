// PessoaSegmentacaoHistorico.h: interface for the 
// CPessoaSegmentacaoHistorico class.
//////////////////////////////////////////////////////////////////////
 
#ifndef PessoaSegmentacaoHistorico
#define PessoaSegmentacaoHistorico
 
#define MSG_ERR_MEMORIA		"Erro de aloca��o de mem�ria"
#define NRO_ERR_MEMORIA		"24E0359"

class CPessoaSegmentacaoHistorico  
{
public:
	// Construtor/Destrutor
	CPessoaSegmentacaoHistorico();
	virtual ~CPessoaSegmentacaoHistorico();
 
 	// M�todos de acesso a banco de dados
	static CPessoaSegmentacaoHistorico* listaPorIdPessoa(long, int*); // Lista hist�rico para uma pessoa
 
 	// M�todos de acesso aos atributos
 	// Getters
 	long getIdPessoaSegmentacao();
 	long getIdPessoaDePara();
 	int getIdSegmentacao();
 	int* getVlRentabilidade();
 	char* getDtSegmentacao();
 	char* getDtRentabilidade();
	char* getDsSegmentacao();
 	// Setters
 	void setIdPessoaSegmentacao(long value);
 	void setIdPessoaDePara(long value);
 	void setIdSegmentacao(int value);
 	void setVlRentabilidade(int* value);
 	void setDtSegmentacao(char* value);
 	void setDtRentabilidade(char* value);
	void setDsSegmentacao(char* value);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	long iIdPessoaSegmentacao;
 	long iIdPessoaDePara;
 	int iIdSegmentacao;
 	int iVlRentabilidade;
 	char cDtSegmentacao[11];
 	char cDtRentabilidade[11];
	char cDsSegmentacao[256];
 
 	// Vari�veis de indica��o do oracle
 	short iiVlRentabilidade;
 	short icDtSegmentacao;
 	short icDtRentabilidade;
	short icDsSegmentacao;
 	EXEC SQL END DECLARE SECTION;
 };
 
#endif