// MotivoDevolucao.h: interface for the 
 // CMotivoDevolucao class.
 //////////////////////////////////////////////////////////////////////
 
#ifndef MotivoDevolucao
#define MotivoDevolucao

#define MSG_ERR_MEMORIA		"Erro de aloca��o de mem�ria"
#define NRO_ERR_MEMORIA		"24E0359"
 
class CMotivoDevolucao  
{
public:
 	// Construtor/Destrutor
 	CMotivoDevolucao();
 	CMotivoDevolucao(int);
 	virtual ~CMotivoDevolucao();
 
 	// M�todos de acesso a banco de dados
 	int inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	int altera(void);					// altera os dados do objeto
 	static CMotivoDevolucao* lista(int*);			// lista todos os objetos na tabela
	int checaExclusaoPossivel();		// Checa se � poss�vel excluir
 
 	// M�todos de acesso aos atributos
 	// Getters
 	int getIdMotivoDevolucao();
 	char* getSgMotivoDevolucao();
 	char* getDsMotivoDevolucao();
 	int getInDisponibilidade();
 	// Setters
 	void setIdMotivoDevolucao(int value);
 	void setSgMotivoDevolucao(char* value);
 	void setDsMotivoDevolucao(char* value);
 	void setInDisponibilidade(int value);
	// Usu�rio
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdMotivoDevolucao;
 	char cSgMotivoDevolucao[256];
 	char cDsMotivoDevolucao[256];
	int iInDisponibilidade;
 
 	// Vari�veis de indica��o do oracle
 	short icSgMotivoDevolucao;
 	short icDsMotivoDevolucao;

	// Usuario altera��o
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
 #endif
