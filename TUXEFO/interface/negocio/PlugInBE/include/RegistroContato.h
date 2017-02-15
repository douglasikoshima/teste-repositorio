#ifndef TXPB_REGISTRO_CONTATO_H
#define TXPD_REGISTRO_CONTATO_H 1


#include <tuxfw.h>


#define TXPB_REGCON_SERVICE                             "DREGCONTATO"
#define ID_CANAL_URA									9

class CRegistroContato
{
public:

	CRegistroContato(void);
	CRegistroContato(DOMNode*);
	
	// Metodos SET
	void setInRegistrarContato(char*);
	void setCdAreaRegistro(char*);
	void setNrLinha(char*);
	void setIdTipoRelacionamento(char*);
	void setCdContato(char*);
	void setIdCanal(char*);
	void setIdTerminal(char*);
	void setIdUser(char*);
	void setIdGrupoAbertura(char*);

	// Metodos GET
	char* getInRegistrarContato(void);
	char* getCdAreaRegistro(void);
	char* getNrLinha(void);
	char* getIdTipoRelacionamento(void);
	char* getCdContato(void);
	char* getIdCanal(void);
	char* getIdTerminal(void);
	char* getIdUser(void);
	char* getIdGrupoAbertura(void);


	int registrarContato(void);
	int registrarContatoProtocolo(void);

	char* getStatusCode(void);
	char* getStatusText(void);

	DOMNode* getResponse(void);

	// recupera o número do protocolo
	void getNumeroProtocoloAntigo();

	// recupera o IDCONTATO a partir de uma chave de funcionalidade
	void getTipoProtocolo(char*cdFuncionalidade);

	char*		getNrProtocolo();
	char*		getInProtocolo();
	char*		getInRelacionamento();
	char*		getExibeProtocolo();

	void setNrProtocolo(char*value);
	void setInProtocolo(char*value);
	void setInRelacionamento(char*value);
	void setExibeProtocolo(char*value);

	void setIdTipoAberturaProtocolo(char*value);
	void setIdSistemaOrigem(char*value);

	char* getIdTipoAberturaProtocolo();
	char* getIdSistemaOrigem();
	char* getNumeroProtocolo();

private:

	// Para valores vindos de fora
	char mvc_inRegistrarContato[256];
	char mvc_cdAreaRegistro[256];
	char mvc_nrLinha[256];
	char mvc_idTipoRelacionamento[256];
	char mvc_cdContato[256];
	char mvc_idCanal[256];
	char mvc_idTerminal[256];
	char mvc_idUser[256];
	char mvc_idGrupoAbertura[256];
	char mvc_idTipoAberturaProtocolo[22];
	char mvc_idSistemaOrigem[22];
	char mvc_numeroProtocolo[256];

	// Para valores internos
	char mvc_idProcedencia[256];
	char mvc_nomeCliente[256];
	char mvc_idSegmentacao[256];
	char mvc_idLinhaTelefonica[256];
	char mvc_idConta[256];
	char mvc_idTipoCarteira[256];
	char mvc_idPessoaDePara[256];

	// Valores para protocolo
	char  mvc_nrProtocolo[256];
	char  mvc_inProtocolo[2];
	char  mvc_inRelacionamento[2];
	char  mvc_exibeProtocolo[2];

	
	// Metodos SET
	void setIdProcedencia(char*);
	void setIdProcedencia(int);
	void setNomeCliente(char*);
	void setIdSegmentacao(char*);
	void setIdSegmentacao(int);
	void setIdLinhaTelefonica(char*);
	void setIdLinhaTelefonica(int);
	void setIdConta(char*);
	void setIdConta(int);
	void setIdTipoCarteira(char*);
	void setIdTipoCarteira(int);
	void setIdPessoaDePara(char*);
	void setIdPessoaDePara(int);

	// Metodos GET
	char* getIdProcedencia(void);
	char* getNomeCliente(void);
	char* getIdSegmentacao(void);
	char* getIdLinhaTelefonica(void);
	char* getIdConta(void);
	char* getIdTipoCarteira(void);
	char* getIdPessoaDePara(void);

	// Metodos de CONSULTA
	int   consultarProcedenciaLinha(void);
	int   consultarNomeCliente(void);
	int   consultarDadosLinhaSessao(void);
	int   consultarDadosSessao(void);
	int	  consultarIdPessoa(void);

	void remoteCall(char* nomeServico, char* sMsgIn, int lFlags);

	/* 20050110 - Michel - pedido para ser removido a busca na tabela URA
	char* consultarIdContatoUra(char *); */

	// Armazena mesnagens de informação, aviso e erro.
	char mvc_statusCode[16];
	char mvc_statusText[1024];

	void setStatusCode(char*);
	void setStatusText(char*);

	// Armazena o DOMNode retornado
	DOMNode* mpo_response;
};


#endif