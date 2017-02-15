// BuscaPessoaCorrespondencia.h: interface for the 
// CBuscaPessoaCorrespondencia class.
//////////////////////////////////////////////////////////////////////

#ifndef BuscaPessoaCorrespondencia
#define BuscaPessoaCorrespondencia

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"
 
class CBuscaPessoaCorrespondencia  
{
public:
	// Construtor/Destrutor
	CBuscaPessoaCorrespondencia();
	virtual ~CBuscaPessoaCorrespondencia();

	// Métodos de acesso a banco de dados
	static CBuscaPessoaCorrespondencia* buscaPorLinha(char*, int*);
	static CBuscaPessoaCorrespondencia* buscaPorConta(char*, int*);
	static CBuscaPessoaCorrespondencia* buscaPorCliente(int, int*);

	// Métodos de acesso aos atributos
	// Getters
	int getIdPessoa();
	int* getIdLinha();
	int* getIdConta();
	char* getNmCliente();
	char* getNrDocumento();
	char* getDsTipoEndereco();
	char* getDsEndereco();
	char* getNrEndereco();
	char* getDsComplemento();
	char* getDsBairro();
	char* getDsCidade();
	char* getNrCEP();
	int getIdUF();
	char* getSgUF();
	char* getNmUF();
	int getIdPais();
	char* getSgPais();
	char* getNmPais();
	char* getTipoDocumento();
	// Setters
	void setIdPessoa(int);
	void setIdLinha(int*);
	void setIdConta(int*);
	void setNmCliente(char*);
	void setNrDocumento(char*);
	void setDsTipoEndereco(char*);
	void setDsEndereco(char*);
	void setNrEndereco(char*);
	void setDsComplemento(char*);
	void setDsBairro(char*);
	void setDsCidade(char*);
	void setNrCEP(char*);
	void setIdUF(int);
	void setSgUF(char*);
	void setNmUF(char*);
	void setIdPais(int);
	void setSgPais(char*);
	void setNmPais(char*);
	void setTipoDocumento(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdPessoa;
	int iIdLinha;
	int iIdConta;
	char cNmCliente[256];
	char cNrDocumento[256];
	char cDsTipoEndereco[256];
	char cDsEndereco[256];
	char cNrEndereco[256];
	char cDsComplemento[256];
	char cDsBairro[256];
	char cDsCidade[256];
	char cNrCEP[256];
	int iIdUF;
	char cSgUF[256];
	char cNmUF[256];
	int iIdPais;
	char cSgPais[256];
	char cNmPais[256];
    char cTipoDocumento[256];

	// Variáveis indicativas do oracle
	short iiIdLinha;
	short iiIdConta;
	short icNmCliente;
	short icNrDocumento;
	short icNrEndereco;
	short icDsEndereco;
	short icDsComplemento;
	short icNrCEP;
	short icDsBairro;
	short icDsCidade;
    short icTipoDocumento;
	EXEC SQL END DECLARE SECTION;
};

#endif