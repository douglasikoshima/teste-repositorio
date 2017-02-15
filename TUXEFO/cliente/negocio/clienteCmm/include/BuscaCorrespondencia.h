// BuscaCorrespondencia.h: interface for the 
// CBuscaCorrespondencia class.
//////////////////////////////////////////////////////////////////////

#ifndef BuscaCorrespondencia
#define BuscaCorrespondencia

#define MSG_ERR_MEMORIA		"Erro de aloca��o de mem�ria"
#define NRO_ERR_MEMORIA		"24E0359"
 
class CBuscaCorrespondencia  
{
public:
	// Construtor/Destrutor
	CBuscaCorrespondencia();
	virtual ~CBuscaCorrespondencia();

	// M�todos de acesso a banco de dados
	static CBuscaCorrespondencia* busca(char*, char*, char*, char*, char*, int, int, int, int*);

	// M�todos de acesso aos atributos
	// Getters
	int getIdCorrespondencia();
	char* getNrLinha();
	char* getNrConta();
	char* getDsTipoCorrespondencia();
	char* getDsMotivoDevolucao();
	char* getDtDevolucao();
	char* getDtRegistro();
	char* getNomePessoa();
	// Setters
	void setIdCorrespondencia(int);
	void setNrLinha(char*);
	void setNrConta(char*);
	void setDsTipoCorrespondencia(char*);
	void setDsMotivoDevolucao(char*);
	void setDtDevolucao(char*);
	void setDtRegistro(char*);
	void setNomePessoa(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdCorrespondencia;
	char cNrLinha[256];
	char cNrConta[256];
	char cDsTipoCorrespondencia[256];
	char cDsMotivoDevolucao[256];
	char cDtDevolucao[256];
	char cDtRegistro[256];
    char cNomePessoa[256];

	// Vari�veis indicativas do oracle
	short icNrLinha;
	short icNrConta;
	short icDsTipoCorrespondencia;
	short icDsMotivoDevolucao;
	short icDtDevolucao;
	short icDtRegistro;
    short icNomePessoa;
	EXEC SQL END DECLARE SECTION;
};

#endif