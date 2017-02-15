#ifndef GESTORCONTACONTATOHPORT
#define GESTORCONTACONTATOHPORT

#include "tuxfw.h"
#include "Global.h"
#include "GestorContaContatopc.h"

class CGestorContaContato
{

public:
	TGestorContaContato tGestorContaContato;
	CGestorContaContatopc clGestorContaContatopc;

    CGestorContaContato(void);

    bool buscaGestorContaContato(void);
    void insereGestorContaContato(void);
    void atualizaGestorContaContato(void);
    void apagaGestorContaContato(void);

    void clearStruct(void);


    void setIdNrLinha(char *pszIdNrLinha);
    void setNrRamal(char *pszNrRamal);
    void setIdNrCPF(char *pszIdNrCPF);
    void setCdAreaRegistro(char *pszCdAreaRegistro);
    void setIdTipoComunicacao(char *pszIdTipoComunicacao);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    
    char *getIdNrLinha(void);
    char *getNrRamal(void);
    char *getIdNrCPF(void);
    char *getCdAreaRegistro(void);
    char *getIdTipoComunicacao(void);
};

#endif
