/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:33 $
 **/


//------------------------------------------------------------------------------        

#include "../../../commons/queryMacro.h"
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/WalkTreeAux.h"

#include "../../AtendimentoPesquisaSatisfa/include/cWFAtendPesquisaSatisfa.h"
#include "../../AtendimentoPesquisaAtual/include/cWFAtendimentoPesquisaAtual.h"
#include "../../AtendimentoPesquisaResp/include/cWFAtendimentoPesquisaResp.h"
#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------        
// cWF_Encaminham 
//------------------------------------------------------------------------------        

class cWFAtRelPqSatGrava : public cWF_Acao
{
    public:
         cWFAtRelPqSatGrava(DOMNode* dnode, XMLGen* xml_g, char *user);
        ~cWFAtRelPqSatGrava() {};

        WalkTreeAux wta;
        //TuxHelper tx;
    
        int idPesquisaAtendimento;
        long idPesquisaAtendimentoAtual;

        void Executar();

    protected: 
        long ObterPesquisaAtendimentoAtual();
        int getEstadoFuturo();

        void IncluiPesquisaAtendimentoAtual();
        void AlteraPesquisaAtendimentoAtual();
        void IncluiPesquisaResposta();
        void IncluiPesquisa();
        void IncluiTesteResposta();

};

