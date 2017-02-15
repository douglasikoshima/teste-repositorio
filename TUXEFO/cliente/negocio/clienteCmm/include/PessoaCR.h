// PessoaCR.h: interface for the 
// CPessoaCR class.
/////////////////////////////////////////////////////////////////////
 
#ifndef PessoaCR
#define PessoaCR

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"

class CPessoaCR  
{
public:
 	// Construtor/Destrutor
 	CPessoaCR();
 	virtual ~CPessoaCR();
 
 	// Métodos de acesso a banco de dados
	static CPessoaCR* listaConsultores(int* iNroObjetos);	// Lista todos os consultores de relacionamento
 	static CPessoaCR* listaParaCR(int, int, int, char*, int*); // Lista todos os clientes associados a um consultor
 	static CPessoaCR* listaClientesEConsultores(int, int, int, char*, int, char*, int*); // Lista todos os cliente/consultor e so´ clientes
 	static CPessoaCR* listaClientesEConsultoresFiltroDoc(int, int, int, char*, char*, char*, int*); // Lista todos os cliente/consultor e so´ clientes considerando o filtro de tipodocumento INVISUALIZA
	void removeVinculo();									// Remove o vírelacionamento definido pelo ID de relacionamento

 	// Métodos de acesso aos atributos
 	// Getters
	int getIdPessoaRelacionamento();
 	int getIdPessoaCR();
 	char* getNmPessoaCR();
	char* getNrDocumento();
	char* getDsSegmentacao();
	int getIdSegmentacao();
	char* getDsCarteirizacao();
	int getIdCarteirizacao();
    int getIdCliente();
    char* getNmCliente();
    char* getTipoDocumento();

    char* getDsTipoDocumento();

 	// Setters
	void setIdPessoaRelacionamento(int value);
 	void setIdPessoaCR(int value);
 	void setNmPessoaCR(char* value);
	void setNrDocumento(char* value);
	void setDsSegmentacao(char* value);
	void setIdSegmentacao(int value);
	void setDsCarteirizacao(char* value);
	void setIdCarteirizacao(int value);
    void setIdCliente(int value);
    void setNmCliente(char* value);
    void setDsTipoDocumento(char* value);

    void getDsTipoDocumento(char*);

	// Usuário
	void setUsuarioAlteracao(char*);

	// Métodos de banco de dados
	void criaVinculo();

private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
	int iIdPessoaRelacionamento;
 	int iIdPessoaCR;
 	char cNmPessoaCR[257];
	char cNrDocumento[257];
	char cDsSegmentacao[257];
	int iIdSegmentacao;
	char cDsCarteirizacao[257];
	int iIdCarteirizacao;
    int iIdCliente;
    char cIdCliente[257];
 	char cNmPessoaCliente[257];
    char cDsTipoDocumento[257];
    int iIdConsultor;

	// Usuario alteração
	char sIdUsuarioAlteracao[257];
 	EXEC SQL END DECLARE SECTION;
 };
 
#endif
