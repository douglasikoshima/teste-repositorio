/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:55 $
 **/

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#include <tuxfw.h>

#include "stVariaveisGrupo.h"
#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"

class cMonObtGruPC
{
    public:
        void obtemGruposPC(  st_VariaveisGrupo *, Collection * );
        void obterTodosGruposPC( st_VariaveisGrupo *_dadosEntradaPC, Collection *_ResultadoPC );
        void obtemGruposPCRC( st_VariaveisGrupo *, Collection * );
        void obtemMonitoramentoPC( st_VariaveisGrupo *_dadosEntradaPC, st_vlVariaveisGrupo *_statusEntradaPC, XMLGen *saida );
        void obtemRegionaisPC ( st_VariaveisRegional *, Collection * ); 
        void obterTiposCarteiraPC( st_VariaveisTipoCarteira *_dadosEntradaPC, Collection *_ResultadoPC );
        void obterSegmentacaoPC( st_VariaveisSegmentacao *_dadosEntradaPC, Collection *_ResultadoPC );
        void obterAlertaPC( st_VariaveisAlerta *_dadosEntradaPC, Collection *_ResultadoPC );
        void obterTipoPessoaPC( st_VariaveisTipoPessoa *_dadosEntradaPC, Collection *_ResultadoPC );
        void obterClassificacaoTipoLinhaPC( st_VariaveisClassificacaoTipoLinha *_dadosEntradaPC, Collection *_ResultadoPC );
        
    private:
        void sql_error_WFobtemGrupos( sqlca * sqlca );
};

