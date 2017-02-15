#ifndef CGruposLstLstH
#define CGruposLstLstH

#include <stdlib.h>
#include <string.h>

#include <tuxfw.h>

const tamBloco = 200;

struct GrupoWrkLst
{
    long  idSeqLst;
    long  idGrupoMemLst;
    long  flagSalvar;
};


class CGruposLst: public TuxHelper
{
public:
    CGruposLst();
    ~CGruposLst();
    void Init( void );
    long Primeiro( void );
    long Proximo( void );
    long Anterior( void );
    long Ultimo( void );
    long Quantidade( void );
    GrupoWrkLst* Registro( void );
    GrupoWrkLst* Registro(long nPosicao);
    void AddGrpLst( long idGrupoParam );
    void ListaGrupos( DOMNode*node );

private:
	GrupoWrkLst * pGrupoWrkLst;
	long _iQuantidadeLst;
	long _iPosicaoLst;
    long _iQuantidadeBloco;

protected:
	void ZeraGruposListaXML( void );

};

#endif
