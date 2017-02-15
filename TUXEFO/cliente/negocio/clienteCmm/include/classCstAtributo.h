#ifndef CLASSCSTSUBASSUNTO
#define CLASSCSTSUBASSUNTO

#include "Global.h"

EXEC SQL INCLUDE "classCstValorPossivel.h";
EXEC SQL INCLUDE "ValorLivre.h";

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdAtributo[LEN_NUMBER + LEN_EOS];
VARCHAR sIdSubAssunto[LEN_NUMBER + LEN_EOS];
VARCHAR sDsAtributo[LEN_DSATRIBUTO + LEN_EOS];
VARCHAR sSqApresentacao[LEN_NUMBER + LEN_EOS];
VARCHAR sIdTAPergunta[LEN_NUMBER + LEN_EOS];
VARCHAR sInDisponibilidade[LEN_NUMBER + LEN_EOS];

/* Nulls */
short iIdAtributoNull;
short iIdSubAssuntoNull;
short iDsAtributoNull;
short iSqApresentacaoNull;
short iidTAPerguntaNull;
short iInDisponibilidadeNull;
}TATRIBUTO;

EXEC SQL END DECLARE SECTION;

class CCstAtributo{

public:
    CCstAtributo();
    ~CCstAtributo();

    void setIdAtributo(char*);
    void setIdSubAssunto(char*);
    void setDsAtributo(char*);
    void setSqApresentacao(char*);
    void setIdTAPergunta(char*);
    void setInDisponibilidade(char*);
	void setNrValoresLivres(int);
	void setValoresLivres(CValorLivre*);
	void setNrValoresPossiveis(int);
	void setValoresPossiveis(CCstValorPossivel*);
	void setNrValoresSelecionados(int);
	void setValoresSelecionados(CCstValorPossivel*);
    
    char *getIdAtributo(void);
    char *getIdSubAssunto(void);
    char *getDsAtributo(void);
    char *getSqApresentacao(void);
    char *getIdTAPergunta(void);
    char *getInDisponibilidade(void);
	int getNrValoresLivres(void);
	CValorLivre* getValoresLivres(void);
	int getNrValoresPossiveis(void);
	CCstValorPossivel* getValoresPossiveis(void);
	int getNrValoresSelecionados(void);
	CCstValorPossivel* getValoresSelecionados(void);
	
    int Alterar();
    int Excluir();
    int Incluir();
    static CCstAtributo *RecuperarTodos(int *);
    static CCstAtributo *RecuperarPorIdSubAss(int *, char *);
	static CCstAtributo *RecuperarPorIdSubAssEPessoa(int*, int, int);
    static CCstAtributo *RecuperarPorIdSubAssETipoApresentacao(int*, char*, char*);
    static CCstAtributo* RecuperarPorIdSubAssIdPessoaIdCanal(int* iNroObjetos, int iIdSub, int iIdPessoa, int iIdCanal);
    static CCstAtributo* RecuperarPorIdSubAssIdPessoaIdGrupo(int* iNroObjetos, int iIdSub, int iIdPessoa, int iIdGrupo);
        void limpaCanais();
        void associaCanal(int);

	// Usuário
	void setUsuarioAlteracao(char*);


private:
    EXEC SQL BEGIN DECLARE SECTION;
        TATRIBUTO tAtributo;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
    EXEC SQL END DECLARE SECTION;
	CValorLivre* poValoresLivres;
	int iNrValoresLivres;
	CCstValorPossivel* poValoresPossiveis;
	int iNrValoresPossiveis;
	CCstValorPossivel* poValoresSelecionados;
	int iNrValoresSelecionados;
};


#define NRO_ERR_TAG_CS      "24E0002"
#define MSG_ERR_TAG_CS      "O valor do Codigo Sub Assunto nao encontrado no XML de entrada"
#define NRO_ERR_TAG_CT      "24E0003"
#define MSG_ERR_TAG_CT      "O valor do Codigo Tipo Apresentacao nao encontrado no XML de entrada"
#define NRO_ERR_TAG_CD      "24E0004"
#define MSG_ERR_TAG_CD      "O valor do Codigo Disponibilidade nao encontrado no XML de entrada"
#define NRO_ERR_TAG_CO      "24E0005"
#define MSG_ERR_TAG_CO      "O valor de Conteudo nao encontrado no XML de entrada"
#define NRO_ERR_TAG_SE      "24E0006"
#define MSG_ERR_TAG_SE      "O valor de Sequencia ao encontrado no XML de entrada"

#define NRO_ERR_NO_TAG      "24E9999"
#define MSG_ERR_NO_TAG_CS   "Nao foi possivel encontrar a TAG codigo de SubAssuntoVO"
#define MSG_ERR_NO_TAG_CT   "Nao foi possivel encontrar a TAG codigo de TipoApresentacaoVO"
#define MSG_ERR_NO_TAG_CD   "Nao foi possivel encontrar a TAG codigo de DisponibilidadeVO"
#define MSG_ERR_NO_TAG_CO   "Nao foi possivel encontrar a TAG Conteudo"
#define MSG_ERR_NO_TAG_SE   "Nao foi possivel encontrar a TAG Sequencia"
#endif
