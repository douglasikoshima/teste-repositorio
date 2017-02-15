/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:49 $
 **/ 

#include <tuxfw.h>

#include "../../../commons/SmallString.h"
#include "../../../commons/Collection/include/Collection.h"

#include "stWFFluxoFase.h"

//
// Usada para a coleção de ações resultante da pesquisa do fluxo do workflow
//
struct ACOES_FLUXO
{
    int idFluxoFase;
    int idFase;
    int idAtividade;
    SmallString dsAtividade;
    SmallString nmFuncValidacao;
};

class cWFFluxoFase
{

    st_FluxoFase    m_stDados;
    st_vlFluxoFase  m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cWFFluxoFase() {entrada=0;saida=0;}
        cWFFluxoFase(DOMNode*dnode, XMLGen*xml_g);
        bool incluir();
        int alterar();
        int excluir();
        bool consultar();
        void getTipoRelacionamento(st_FluxoFase *dados,st_vlFluxoFase *status);
        bool ObtemWFAcoes();
        //bool ObtemWFAcoes(st_FluxoFase *dados, st_vlFluxoFase *status,XMLGen *saida,int idTipoProcesso);
        bool ObtemWFAcoes(st_FluxoFase *dados, st_vlFluxoFase *status,Collection *colecaoAcoes,int idTipoProcesso);
        bool ObtemWFAcoesAbertura(st_FluxoFase *dados, st_vlFluxoFase *status,Collection *saidaCollection);
        bool ObtemWFAcoesAberturaCore(st_FluxoFase *dados, st_vlFluxoFase *status,XMLGen *saida);
        bool ObtemWFFluxo();
        bool ObtemWFCanc();
        bool ObtemWFCanc(st_FluxoFase *dados, st_vlFluxoFase *status,XMLGen *saida);
        bool ObtemWFFluxoFt();
        bool ObtemWFFluxo(st_FluxoFase *dados,st_vlFluxoFase *status,XMLGen *saida);
        bool ObtemWFFluxoFt(st_FluxoFase *dados,st_vlFluxoFase *status,XMLGen *saida);
        bool ObtemWFFluxoAtividade(st_FluxoFase *dados,st_vlFluxoFase *status,XMLGen *saida);
        bool ObtemWFFluxoAtividadePOut(st_FluxoFase *dados,st_vlFluxoFase *status,XMLGen *saida);

    private:
        void carregaDados();

};
