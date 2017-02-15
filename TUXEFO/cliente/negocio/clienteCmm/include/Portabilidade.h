#ifndef PORTABILIDADEHPORT
#define PORTABILIDADEHPORT

#include "tuxfw.h"
#include "Global.h"
#include "Portabilidadepc.h"

class CPortabilidade
{

public:
	TPortabilidade tPortabilidade;
	CPortabilidadepc clPortabilidadepc;

    CPortabilidade(void);

    bool buscaPortabilidade(void);

    void setNrLinha(char *pszNrLinha);
    void setCdAreaRegistro(char *pszCdAreaRegistro);
    void setIdLinhaTelefonica(char* pszIdLinhaTelefonica);

    char *getIdPessoa(void);
    char *getIdPessoaDePara(void);
    char *getSgTipoPessoa(void);
    char *getNmPessoa(void);
    char *getCdAreaRegistro(void);
    char *getNrLinha(void);
    char *getDsTipoLinha(void);
    char *getIdTipoLinha(void);
    char *getIdPessoaEndereco(void);
    char *getDsEndereco(void);
    char *getNrEndereco(void);
    char *getDsEnderecoComplemento(void);
    char *getNmBairro(void);
    char *getNmMunicipio(void);
    char *getNrCEP(void);
    char *getIdUF(void);
    char *getSgUF(void);
    char *getNmUF(void);
    char *getIdPais(void);
    char *getSgPais(void);
    char *getDsPais(void);
    char *getDsNacionalidade(void);
    char *getIdDocumento(void);
    char *getDsTipoDocumento(void);
    char *getNrDocumento(void);
    char* getIdLinhaTelefonica();
};

#endif
