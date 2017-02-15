#ifndef PERMISSAOLINHAPUPH
#define PERMISSAOLINHAPUPH

#include "tuxfw.h"
#include "PermissaoLinhaPUP.h"
#include "PermissaoLinhaPUPpc.h"


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
