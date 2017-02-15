// BuscaCorrespondenciaTI.h: interface for the 
// CBuscaCorrespondenciaTI class.
//////////////////////////////////////////////////////////////////////

#ifndef BuscaCorrespondenciaTI
#define BuscaCorrespondenciaTI

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"
 
class CBuscaCorrespondenciaTI  
{
public:
	// Construtor/Destrutor
	CBuscaCorrespondenciaTI();
	virtual ~CBuscaCorrespondenciaTI();

	// Métodos de acesso a banco de dados
	static CBuscaCorrespondenciaTI* buscaCliUsu(char*, char*, int, int, int*);
	static CBuscaCorrespondenciaTI* buscaCli(char*, char*, int, int, int*);
	static CBuscaCorrespondenciaTI* buscaCli(char*, char*, int, int*);
	static CBuscaCorrespondenciaTI* buscaUsu(char*, char*, int, int, int*);

	// Métodos de acesso aos atributos
	// Getters
	int getIdCorrespondencia();
	char* getNrLinha();
	char* getNrConta();
	char* getDsTipoCorrespondencia();
	char* getDsMotivoDevolucao();
	char* getDtDevolucao();
	char* getDtRegistro();
	char* getNmPessoa();
	int getIdTipoRelacionamento();
	char* getDsTipoRelacionamento();
	// Setters
	void setIdCorrespondencia(int);
	void setNrLinha(char*);
	void setNrConta(char*);
	void setDsTipoCorrespondencia(char*);
	void setDsMotivoDevolucao(char*);
	void setDtDevolucao(char*);
	void setDtRegistro(char*);
	void setNmPessoa(char*);
	void setIdTipoRelacionamento(int);
	void setDsTipoRelacionamento(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdCorrespondencia;
	char cNrLinha[256];
	char cNrConta[256];
	char cDsTipoCorrespondencia[256];
	char cDsMotivoDevolucao[256];
	char cDtDevolucao[256];
	char cDtRegistro[256];
	char cNmPessoa[256];
	int iIdTipoRelacionamento;
	char cDsTipoRelacionamento[256];

	// Variáveis indicativas do oracle
	short icNrLinha;
	short icNrConta;
	short icDsTipoCorrespondencia;
	short icDsMotivoDevolucao;
	short icDtDevolucao;
	short icDtRegistro;
	EXEC SQL END DECLARE SECTION;
};

#endif