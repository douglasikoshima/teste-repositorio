#ifndef GESTORCONTAHPORT
#define GESTORCONTAHPORT

#include "tuxfw.h"
#include "Global.h"
#include "GestorContapc.h"

class CGestorConta
{

public:
	TGestorConta tGestorConta;
	CGestorContapc clGestorContapc;

    CGestorConta(void);

    bool buscaGestorConta(void);
    void insereGestorConta(void);
    void atualizaGestorConta(void);
    void apagaGestorConta(void);

    void setIdNrCPF(char *pszIdNrCPF);
    void setDsTipoGestor(char *pszDsTipoGestor);
    void setNmNome(char *pszNmNome);
    void setNmNomeMeio(char *pszNmNomeMeio);
    void setNmSobreNome(char *pszNmSobreNome);
    void setNmCargo(char *pszNmCargo);
    void setNmLogradouro(char *pszNmLogradouro);
    void setNrEndereco(char *pszNrEndereco);
    void setNmEnderecoComplemento(char *pszNmEnderecoComplemento);
    void setNmBairro(char *pszNmBairro);
    void setNmCidade(char *pszNmCidade);
    void setNrCEP(char *pszNrCEP);
    void setIdUF(char *pszIdUF);
    void setDsEmail(char *pszDsEmail);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getIdNrCPF(void);
    char *getDsTipoGestor(void);
    char *getNmNome(void);
    char *getNmNomeMeio(void);
    char *getNmSobreNome(void);
    char *getNmCargo(void);
    char *getDtUltimaAlteracao(void);
    char *getDsEmail(void);

};

#endif
