#ifndef PESSOAPORTABILIDADEHISTHPORT
#define PESSOAPORTABILIDADEHISTHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "PessoaPorHistpcPORT.h"

class CPessoaPortabilidadeHist
{

public:
	TPessoaPortabilidadeHist tPessoaPortabilidadeHist;
	CPessoaPortabilidadeHistpc clPessoaPortabilidadeHistpc;

    CPessoaPortabilidadeHist(void);

    void inserePessoaPortabilidadeHist(void);


    void setIdPessoaPortabilidadeHist(char *pszIdPessoaPortabilidadeHist);
    void setIdTipoLinha(char *pszIdTipoLinha);
    void setCdAreaRegistro(char *pszCdAreaRegistro);
    void setNrLinha(char *pszNrLinha);
    void setIdTipoPessoa(char *pszIdTipoPessoa);
    void setNmPessoa(char *pszNmPessoa);
    void setIdTipoDocumento(char *pszIdTipoDocumento);
    void setNrDocumento(char *pszNrDocumento);
    void setIdTipoEndereco(char *pszIdTipoEndereco);
    void setNmTipoLogradouro(char *pszNmTipoLogradouro);
    void setNmLogradouro(char *pszNmLogradouro);
    void setNrEndereco(char *pszNrEndereco);
    void setNmMunicipio(char *pszNmMunicipio);
    void setNmBairro(char *pszNmBairro);
    void setNrCep(char *pszNrCep);
    void setDsAcaoPortabilidade(char *pszDsAcaoPortabilidade);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setSgTipoPortabilidade(char *pszSgTipoPortabilidade);
    
    char *getIdPessoaPortabilidadeHist(void);
    char *getIdTipoLinha(void);
    char *getCdAreaRegistro(void);
    char *getNrLinha(void);
    char *getIdTipoPessoa(void);
    char *getNmPessoa(void);
    char *getIdTipoDocumento(void);
    char *getNrDocumento(void);
    char *getIdTipoEndereco(void);
    char *getNmTipoLogradouro(void);
    char *getNmLogradouro(void);
    char *getNrEndereco(void);
    char *getNmMunicipio(void);
    char *getNmBairro(void);
    char *getNrCep(void);
    char *getDsAcaoPortabilidade(void);
    char *getIdUsuarioAlteracao(void);
    char *getSgTipoPortabilidade(void);

    void clearStruct(void);
};

#endif
