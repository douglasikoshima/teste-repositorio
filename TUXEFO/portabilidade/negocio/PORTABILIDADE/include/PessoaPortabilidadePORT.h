#ifndef PESSOAPORTABILIDADEHPORT
#define PESSOAPORTABILIDADEHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "PessoaPortabilidadepcPORT.h"

class CPessoaPortabilidade
{

public:
	TPessoaPortabilidade tPessoaPortabilidade;
	CPessoaPortabilidadepc clPessoaPortabilidadepc;

    CPessoaPortabilidade(void);


    bool buscaPessoaPortabilidade(void);
    bool buscaPessoaPortabilidade(TPessoaPortabilidade *ptPessoaPortabilidade);
    bool inserePessoaPortabilidade(void);
    void atualizaPessoaPortabilidade(void);

    void setInSincronizado(char *pszInSincronizado);
    void setIdTipoPessoa(char *pszIdTipoPessoa);
    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setIdTipoLinha(char *pszIdTipoLinha);
    void setCdAreaRegistro(char *pszCdAreaRegistro);
    void setNrLinha(char *pszNrLinha);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setIdAcaoPortabilidade(char *pszIdAcaoPortabilidade);

    char *getInSincronizado(void);
    char *getIdTipoPessoa(void);
    char *getIdPessoaDePara(void);
    char *getIdTipoLinha(void);
    char *getCdAreaRegistro(void);
    char *getNrLinha(void);
    char *getIdUsuarioAlteracao(void);

    void clearStruct(void);
};

#endif
