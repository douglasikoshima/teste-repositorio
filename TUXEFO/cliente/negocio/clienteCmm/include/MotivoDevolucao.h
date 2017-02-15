// MotivoDevolucao.h: interface for the 
 // CMotivoDevolucao class.
 //////////////////////////////////////////////////////////////////////
 
#ifndef MotivoDevolucao
#define MotivoDevolucao

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"
 
class CMotivoDevolucao  
{
public:
 	// Construtor/Destrutor
 	CMotivoDevolucao();
 	CMotivoDevolucao(int);
 	virtual ~CMotivoDevolucao();
 
 	// Métodos de acesso a banco de dados
 	int inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	int altera(void);					// altera os dados do objeto
 	static CMotivoDevolucao* lista(int*);			// lista todos os objetos na tabela
	int checaExclusaoPossivel();		// Checa se é possível excluir
 
 	// Métodos de acesso aos atributos
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
	// Usuário
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdMotivoDevolucao;
 	char cSgMotivoDevolucao[256];
 	char cDsMotivoDevolucao[256];
	int iInDisponibilidade;
 
 	// Variáveis de indicação do oracle
 	short icSgMotivoDevolucao;
 	short icDsMotivoDevolucao;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
 #endif
