// CorrespondenciaDevolvida.h: interface for the 
// CCorrespondenciaDevolvida class.
//////////////////////////////////////////////////////////////////////

#ifndef CorrespondenciaDevolvida
#define CorrespondenciaDevolvida

EXEC SQL INCLUDE "HistoricoStatus.h";

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"

class CCorrespondenciaDevolvida  
{
public:
	// Construtor/Destrutor
	CCorrespondenciaDevolvida();
	CCorrespondenciaDevolvida(int);
	virtual ~CCorrespondenciaDevolvida();

	// Métodos de acesso a banco de dados
	void inclui(void);					// inclui nova correspondencia
	void exclui(CHistoricoStatus*);		// remove a correspondencia
	void altera(void);					// altera os dados da correspondencia
	void atualizaStatus(CHistoricoStatus*, int); // Atualiza o status da correspondencia
	char** getNomeEDoctoCliente();		// Retorna nome e docto da pessoa

    char *getCodigoConta(int); //Retorna o numero da conta de um IdPessoa
    char *getNumeroLinha(int); //Retorna o numero da linha de um IdPessoa

	// Métodos de acesso aos atributos
	// Getters
	int getIdCorrespondenciaDevolvida();
	int getIdPessoaUsuarioInclusao();
	int getIdMotivoDevolucao();
	int getIdPessoa();
	int getIdPais();
	int getIdUf();
	int getIdTipoCorrespondencia();
	int getIdPessoaUsuarioAlteracao();
	char* getDtDevolucao();
	char* getDtEnvioSMS();
	char* getDtEnvioTelemensagem();
	char* getNmLogradouro();
	char* getNrEndereco();
	char* getDsComplemento();
	char* getNmBairro();
	char* getNmCidade();
	char* getNrCEP();
	char* getCdConta();
	char* getNrLinha();
	char* getTipoDocumento();
	// Setters
	void setIdCorrespondenciaDevolvida(int value);
	void setIdPessoaUsuarioInclusao(int value);
	void setIdMotivoDevolucao(int value);
	void setIdPessoa(int value);
	void setIdPais(int value);
	void setIdUf(int value);
	void setIdTipoCorrespondencia(int value);
	void setIdPessoaUsuarioAlteracao(int value);
	void setDtDevolucao(char* value);
	void setDtEnvioSMS(char* value);
	void setDtEnvioTelemensagem(char* value);
	void setNmLogradouro(char* value);
	void setNrEndereco(char* value);
	void setDsComplemento(char* value);
	void setNmBairro(char *value);
	void setNmCidade(char* value);
	void setNrCEP(char* value);
	void setCdConta(char* value);
	void setNrLinha(char* value);
	void setTipoDocumento(char* value);
	// Usuário
	void setUsuarioAlteracao(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdCorrespondenciaDevolvida;
	int iIdPessoaUsuarioInclusao;
	int iIdMotivoDevolucao;
	int iIdPessoa;
	int iIdPais;
	int iIdUf;
	int iIdTipoCorrespondencia;
	int iIdPessoaUsuarioAlteracao;
	char cDtDevolucao[11];
	char cDtEnvioSMS[11];
	char cDtEnvioTelemenssagem[11];
	char cNmLogradouro[256];
	char cNrEndereco[256];
	char cDsComplemento[256];
	char cNmBairro[256];
	char cNmCidade[256];
	char cNrCEP[256];
    char cCdConta[256];
    char cNrLinha[256];
    char cTipoDocumento[256];

	// Variáveis de indicação do Oracle
	short iiIdPessoaUsuarioAlteracao;
	short icDtDevolucao;
	short icDtEnvioSMS;
	short icDtEnvioTelemenssagem;
	short icNmLogradouro;
	short iiNrEndereco;
	short icDsComplemento;
	short icNmBairro;
	short icNmCidade;
	short icNrCEP;
    short icCdConta;
    short icNrLinha;
    short icTipoDocumento;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
	EXEC SQL END DECLARE SECTION;
};

#endif

