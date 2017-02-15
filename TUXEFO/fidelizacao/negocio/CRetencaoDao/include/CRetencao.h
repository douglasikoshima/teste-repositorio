//////////////////////////////////////////////////////////////////////
// CRetencao.h: interface for the CRelatorioGerenciamento class.
//////////////////////////////////////////////////////////////////////

#ifndef CRetencaoHH
#define CRetencaoHH


#include "utilretencao.h"

struct Param_URA
{
    char nmUsuario[256];
    char nrTelefone[15];
    char idPessoadePara[40];
    char idUFOperadora[40];
    char sgTipoPessoa[3];
    char idLinhaTelefonica[40];
    char idTipoLinha[40];
    char idSegmentacao[40];
    char idRespostaIntencao[40];
    char idRespostaDestino[40];
    char idGrupo[5];
    char idTipoEncerramento[5];
    char idOfertaAceita[40];
    char sgOfertaAceita[16];
    char nmBonus[256];
    char idMatrizBonus[40];
    char dtInicioVigencia[16];
    char dtFinalVigencia[40];
    char sCodErro[16];
    char sMsgErro[256];
};

class CRetencao 
{
    public:
        Param_URA   prmURA;
        int  Inserir(DOMNode *,char *,char*);
        void ObtemIdAtendimento( char *nrProtocoloPrm, char *idAtendimentoPrm );
        int  ObtemParametrosToURA( DOMNode * pdnode, char *idUser );
        int  get_errors( char * cdServicoPrm, char *pnrlinha, char *pcdRet, char *pmsgRet );
    private:

};

#endif