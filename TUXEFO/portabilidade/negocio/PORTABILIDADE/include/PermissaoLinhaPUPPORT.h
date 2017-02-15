#ifndef PERMISSAOLINHAPUPHPORT
#define PERMISSAOLINHAPUPHPORT

#include "tuxfw.h"
#include "PermissaoLinhaPUPPORT.h"
#include "PermissaoLinhaPUPpcPORT.h"


class CPermissaoLinhaPUP
{
public:
	TPermissaoLinhaPUP      tPermissaoLinhaPUP;
	CPermissaoLinhaPUPpc    clPermissaoLinhaPUPpc;

	CPermissaoLinhaPUP(void);

    bool buscaPermissaoLinhaPUP(void);
    bool buscaPermissaoLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP);
    void inserePermissaoLinhaPUP(void);
    void AlteraCadastroLinhaPUP( void );
    void apagaPermissaoLinhaPUP(void);

    void clearStruct(void);
    void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
    void setSgPermissaoPUP(char *pszSgPermissaoPUP);
    void setInAtivo(char *pszInAtivo);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setDtExpiracao(char *pszDtExpiracao);

    void setInProcon(char *pszInProcon);
    void setIdSistemaOrigem(char *pszIdSistemaOrigem);
    void setIdCanal(char *pszIdCanal);

    char *getIdLinhaTelefonica(void);
    char *getSgPermissaoPUP(void);
};

#endif
