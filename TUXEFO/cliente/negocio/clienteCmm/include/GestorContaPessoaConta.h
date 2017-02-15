#ifndef GESTORCONTAPESSOACONTAHPORT
#define GESTORCONTAPESSOACONTAHPORT

#include "tuxfw.h"
#include "Global.h"
#include "GestorContaPessoaContapc.h"

class CGestorContaPessoaConta
{

public:
	TGestorContaPessoaConta tGestorContaPessoaConta;
	CGestorContaPessoaContapc clGestorContaPessoaContapc;

    CGestorContaPessoaConta(void);

    bool buscaGestorContaPessoaConta(void);
    void insereGestorContaPessoaConta(void);
    void atualizaGestorContaPessoaConta(void);
    void apagaGestorContaPessoaConta(void);


    void clearStruct(void);

    void setIdNrCPF(char *pszIdNrCPF);
    void setIdPessoaConta(char *pszIdPessoaConta);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    
    char *getIdNrCPF(void);
    char *getIdPessoaConta(void);
};

#endif
