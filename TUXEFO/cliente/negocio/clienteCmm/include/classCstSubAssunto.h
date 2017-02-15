#ifndef CLASSCSTSUBASSUNTO
#define CLASSCSTSUBASSUNTO

#include "Global.h"

EXEC SQL BEGIN DECLARE SECTION;

typedef struct{
VARCHAR sIdSubAssunto[LEN_NUMBER + LEN_EOS];
VARCHAR sIdAssunto[LEN_NUMBER + LEN_EOS];
VARCHAR sNmSubAssunto[LEN_NMSUBASSUNTO + LEN_EOS];
VARCHAR sSqApresentacao[LEN_NUMBER + LEN_EOS];
VARCHAR sInDisponibilidade[LEN_NUMBER + LEN_EOS];
//VARCHAR sNmUrlSubAssunto[LEN_NMURLSUBASSUNTO + LEN_EOS];

/* Nulls */
short   iIdSubAssuntoNull;
short   iIdAssuntoNull;
short   iNmSubAssuntoNull;
short   iSqApresentacaoNull;
short   iInDisponibilidadeNull;
//short   iNmUrlSubAssuntoNull;
}TSUBASSUNTO;

EXEC SQL END DECLARE SECTION;

class CCstSubAssunto{

public:
    CCstSubAssunto();
    ~CCstSubAssunto() {};

    void setIdSubAssunto(char*);
    void setIdAssunto(char*);
    void setNmSubAssunto(char*);
    void setSqApresentacao(char*);
    void setInDisponibilidade(char*);
    //void setNmUrlSubAssunto(char*);

    char *getIdSubAssunto();
    char *getIdAssunto();
    char *getNmSubAssunto();
    char *getSqApresentacao();
    char *getInDisponibilidade();
    //char *getNmUrlSubAssunto();

    int Alterar();
    int Excluir();
    int Incluir();
    static CCstSubAssunto *RecuperarTodos(int*, char*);
    static CCstSubAssunto *RecuperarIdAssuntoETipoPergunta(int*, char*, char*);

	// Usuário
	void setUsuarioAlteracao(char*);


private:
    EXEC SQL BEGIN DECLARE SECTION;
        TSUBASSUNTO tSubAss;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
    EXEC SQL END DECLARE SECTION;
};

#define NRO_ERR_TAG_CA      "24E0002"
#define MSG_ERR_TAG_CA      "O valor do Codigo de Assunto nao encontrado no XML de entrada"
#define NRO_ERR_TAG_DI      "24E0003"
#define MSG_ERR_TAG_DI      "O valor de Disponibilidae nao encontrado no XML de entrada"
#define NRO_ERR_TAG_CD      "24E0004"
#define MSG_ERR_TAG_CD      "O valor do Codigo Disponibilidade nao encontrado no XML de entrada"
#define NRO_ERR_TAG_DE      "24E0005"
#define MSG_ERR_TAG_DE      "O valor de Descricao nao encontrado no XML de entrada"
#define NRO_ERR_TAG_SE      "24E0006"
#define MSG_ERR_TAG_SE      "O valor de SequenciaApresetacao ao encontrado no XML de entrada"

#define NRO_ERR_NO_TAG      "24E9999"
#define MSG_ERR_NO_TAG_CA   "Nao foi possivel encontrar a TAG codigo de SubAssuntoVO"
#define MSG_ERR_NO_TAG_DI   "Nao foi possivel encontrar a TAG disponibilidade de SubAssuntoVO"
#define MSG_ERR_NO_TAG_DE   "Nao foi possivel encontrar a TAG Descricao de SubAssuntoVO"
#define MSG_ERR_NO_TAG_SE   "Nao foi possivel encontrar a TAG SequenciaApresentacao de SubAssuntoVO"

#endif
