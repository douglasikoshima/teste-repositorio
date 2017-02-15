#ifndef CGruposH
#define CGruposH

#include <stdlib.h>
#include <string.h>

#include <tuxfw.h>

const tamBlocoGrupo = 200;

struct GrupoWrk
{
    long  idSeqMem;
    long  idGrupoMem;
    long  idContatoGrupoMem;
    long  flagListaXml;  // 0 - Remover -> Nao encontrou na Lista XML
};                       // 1 - Encontrou na Lista XML -> Nao sera removido


class CGrupos: public TuxHelper
{
public:
    CGrupos();
    ~CGrupos();
    long Primeiro( void );
    long Proximo( void );
    long Anterior( void );
    long Ultimo( void );
    long Quantidade( void );
    GrupoWrk* Registro( void );
    GrupoWrk* Registro(long nPosicao);
    void Add(long idGrupoParam,long idContatoGrupoParam,long idSequenciaParam);
    void LoadIdGrupos(long idContatoParam,long idTpSeq);
    bool ExisteContatoTipoRetorno(long idContatoParam,long idTipoRetorno);
    bool GrupoAssociadoTipoFechamento(long idContatoParam,long idGrupoParam);

private:
	GrupoWrk * pGrupoWrk;
	long _iQuantidade;
	long _iPosicao;

protected:
	void ZeraGruposEncontrados( void );

};

#endif
