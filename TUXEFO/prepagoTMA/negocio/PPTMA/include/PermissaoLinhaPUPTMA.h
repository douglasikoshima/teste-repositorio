#ifndef PERMISSAOLINHAPUPHTMA
#define PERMISSAOLINHAPUPHTMA

#include "tuxfw.h"
#include "PermissaoLinhaPUPTMA.h"
#include "PermissaoLinhaPUPpcTMA.h"


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
    void setAlteraCadastroLinhaPUP( char* idLinhaTelefonicaPrm,char* idUsuarioPrm );
    void setSgPermissaoPUP(char *pszSgPermissaoPUP);
    void setInAtivo(char *pszInAtivo);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setDtExpiracao(char *pszDtExpiracao);

    char *getIdLinhaTelefonica(void);
    char *getSgPermissaoPUP(void);
};

#endif
