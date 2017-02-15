#ifndef TXPB_REGISTRO_CONTATO_H
#define TXPD_REGISTRO_CONTATO_H 1

#include <tuxfw.h>
#include "../../commons/include/vectorlist.h"

#define TXPB_REGCON_SERVICE                             "DREGCONTATOA"


class RegUtil
{
public:
	RegUtil(){};
	~RegUtil(){};
	static char* rtrim(char *pStr);
	static char* ltrim(char *pStr);
	static char* trim(char *pStr);
};

class DOMNodeParserRe{
public:
	DOMNodeParserRe();
	DOMNode* getDOMNode();
	~DOMNodeParserRe();
	void setMemBuf(MemBufInputSource*);
	void setParser(XercesDOMParser*);
	void parse();
private:
	XercesDOMParser*   m_pParser;
	MemBufInputSource* m_pMemBuf;
};
typedef CVectorList<DOMNodeParserRe> ListDOMNodeParser;


class CRegistroContato
{
public:

	CRegistroContato(void);
	
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
	void consultarContato(char*chave,char*pvalor);

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

	char* getStatusCode(void);
	char* getStatusText(void);

	DOMNode* getResponse(void);

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

	// Para valores internos
	char mvc_idProcedencia[256];
	char mvc_nomeCliente[256];
	char mvc_idSegmentacao[256];
	char mvc_idLinhaTelefonica[256];
	char mvc_idConta[256];
	char mvc_idTipoCarteira[256];
	char mvc_idPessoaDePara[256];

	
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

	// Armazena mesnagens de informação, aviso e erro.
	char mvc_statusCode[16];
	char mvc_statusText[1024];

	void setStatusCode(char*);
	void setStatusText(char*);

	// Armazena o DOMNode retornado
	DOMNode* mpo_response;
	ListDOMNodeParser m_listParser;
};


#endif