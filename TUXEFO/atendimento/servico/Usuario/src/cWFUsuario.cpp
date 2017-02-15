/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/


#include "../include/cWFUsuario.h"
#include "../../../commons/Collection/include/Collection.h"

extern bool proCAltStatusUsuario(DOMNode*entrada);
extern bool proCPesquisaUsuario(st_VariaveisUsuario* _dados, st_Result *result);
// extern bool proCPesquisaGrupoAberturaUsuario(DOMNode*entrada,XMLGen* saida);
extern bool proCConsultaWFGrupos(XMLGen* saida);
extern bool proCConsultaWFGruposCri(XMLGen* saida);
extern bool proCConsultaWFGruposRC(XMLGen* saida, int idUser);
extern bool proCConsultaWFGruposRelatorios(XMLGen* saida);
extern bool proCConsultaWFGruposFilaProcesso(int _idPessoaUsuario,Collection* _grupos);
extern bool proCPesquisaUsuarioPorGrupo(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaGrupoUsuarioAbertura(DOMNode*entrada,XMLGen* saida);
extern bool proCConsultaGruposUsuario(DOMNode*entrada, XMLGen* saida);
extern bool proCConsultaGruposUsuario( int _idPessoaUsuario, XMLGen* saida );
//extern bool proCPesquisaGrupoFaseVariables(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCPesquisaGrupoFaseVariablesUsuario(DOMNode*entrada,XMLGen* saida);
//extern bool proCPesquisaGrupoFaseVariables(st_VariaveisUsuario* _dados, XMLGen* saida, int *contadorLinhas);
extern bool proCPesquisaGrupoFaseUsuario(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaUsuarioGrupoCanal(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaUsuarioGrupoProcedencia(DOMNode*entrada,XMLGen* saida);
extern bool proCAltStatusUsuario(int idPessoaUsuario,int idStatusUsuario);
extern bool proCPesquisaGrupoProxNivel(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCPesquisaGrupoProxNivel(st_VariaveisUsuario* _dados, XMLGen* saida,int *qtdeLinhas=0);
extern bool proCAltInDisponivelWF(int idPessoaUsuario,int inDisponivelWF);
extern bool proCAltInDisponivelWF(DOMNode*entrada);
extern bool proCPesquisaUsuarioRelatorio(int sIdGrupo,XMLGen* saida,int start,int stop);
extern bool proCConsultaWFGruposRelatoriosCRI(XMLGen* saida);
extern bool proCPesquisaUsuarioRelatorioTodo(int sIdGrupo,XMLGen* saida);
extern bool proCPesquisaGrupoProcedencia(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaGrupoCanal(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaMC1GrpUser(DOMNode*entrada,XMLGen* saida);

extern bool proCPesquisaLgUserPorGrupoNaoBlocado(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaLgUserPorGrupoBlocado(DOMNode*entrada,XMLGen* saida,int _nrRegistroInicial,int _nrRegistroFinal);
extern bool proCPesquisaLgUserPorGrupo(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaLgUserPorGrupoMC(DOMNode*entrada,XMLGen* saida,const char *idPessoaUsuario);
extern bool proCPesquisaLgUserPorGrupoNaoBlocadoMC(DOMNode*entrada,XMLGen* saida,const char *nrPesoHierarquia);
extern bool proCPesquisaLgUserPorGrupoBlocadoMC(DOMNode*entrada,XMLGen* saida,const char *nrPesoHierarquia,int _nrRegistroInicial,int _nrRegistroFinal);
extern int proCPesquisaGrupoRetornoGrupoRetorno(st_VariaveisUsuario* _dados,char *_nmGrupo,int *inAssociado);
extern int proCGetStatusDispUsuario(int _idPessoaUsuario,int *_idStatusUsuario,int *_inDisponivelWF);
extern int proCGetStatusDispUsuario(DOMNode *entrada,int *_idStatusUsuario,int *_inDisponivelWF);
extern int proCObterTipoRetornoContato(struct DadosTipoRelacionamento *dadosTipoRelacionamento);

extern int  proCGetStatusUsuario(DOMNode*entrada);
extern int  proCGetStatusUsuario(int idPessoaUsuario,DadosStatusUsuario &dadosStatusUsuario);
extern int  proCGetStatusUsuario(int idPessoaUsuario);
extern int  proCGetInDisponivelWF(DOMNode*entrada);

extern int proCGetUsuarioSupervisorSimNao(int _idPessoaUsuario,int _idGrupo,bool &isSupervisor);

extern void proCGetListaStatusUsuario(XMLGen* saida);

extern bool proCConsultaWFGrupos1(XMLGen* saida, int idUser);
extern bool proCConsultaWFGruposBko(XMLGen* saida, int idUser);
extern bool proCConsultaWFGruposCri1(XMLGen* saida, int idUser);

extern bool proCPesquisaGrupoFaseVariablesCRI(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCPesquisaGrupoFaseCRI(st_VariaveisUsuario* _dados, Collection* _grupos);

cWFUsuario::cWFUsuario(st_VariaveisUsuario* dados, st_vlVariaveisUsuario* status, XMLGen*xml_g)
{
    memcpy(&m_stDados, dados, sizeof(m_stDados));
    memcpy(&m_vlDados, status, sizeof(m_vlDados));

    entrada = 0;
    saida   = xml_g;
}

cWFUsuario::cWFUsuario(DOMNode* dnode, XMLGen* xml_g)
{
    entrada = dnode;
    saida   = xml_g;

    carregaDados();
}

int cWFUsuario::getUsuarioSupervisorSimNao(int _idPessoaUsuario,int _idGrupo,bool &isSupervisor)
{
    return proCGetUsuarioSupervisorSimNao(_idPessoaUsuario,_idGrupo,isSupervisor);
}

bool cWFUsuario::altInDisponivelWF(DOMNode* entrada)
{
    return proCAltInDisponivelWF(entrada);
}

bool cWFUsuario::altInDisponivelWF(int idPessoaUsuario,bool inDisponivelWF)
{
    return proCAltInDisponivelWF(idPessoaUsuario,inDisponivelWF);
}

bool cWFUsuario::altInDisponivelWF()
{
    return proCAltInDisponivelWF(entrada);
}

bool cWFUsuario::altStatusUsuario(DOMNode* entrada)
{
    return proCAltStatusUsuario(entrada);
}

bool cWFUsuario::altStatusUsuario(int idPessoaUsuario,int idStatusUsuario)
{
    return proCAltStatusUsuario(idPessoaUsuario,idStatusUsuario);
}

bool cWFUsuario::altStatusUsuario()
{
    return proCAltStatusUsuario(entrada);
}

bool cWFUsuario::pesqUsuGrpProxNivel(st_VariaveisUsuario* dados, XMLGen* saida,int *contadorLinhas)
{
    return proCPesquisaGrupoProxNivel(dados,saida,contadorLinhas);
}

// bool cWFUsuario::pesqUsuGrpProxFase(st_VariaveisUsuario* dados, XMLGen* saida,int *contadorLinhas)
// {
//     return proCPesquisaGrupoFaseVariables(dados,saida,contadorLinhas);
// }

int cWFUsuario::obterGrupoRetornoGrupoRetorno(st_VariaveisUsuario* _dados)
{
    return proCPesquisaGrupoRetornoGrupoRetorno(_dados,0,0);
}

int cWFUsuario::obterGrupoRetornoGrupoRetorno(st_VariaveisUsuario* _dados,char *nmGrupo)
{
    return proCPesquisaGrupoRetornoGrupoRetorno(_dados,nmGrupo,0);
}


int cWFUsuario::pesqStatusUsuario()
{
    if (m_vlDados.idPessoaUsuario != 1)
    {
        return 0;
    }

    return proCGetStatusUsuario(m_stDados.idPessoaUsuario);
}

int cWFUsuario::pesqStatusUsuario(int idPessoaUsuario,DadosStatusUsuario &dadosStatusUsuario)
{
    return proCGetStatusUsuario(idPessoaUsuario,dadosStatusUsuario);
}

int cWFUsuario::pesqStatusDispUsuario(int idPessoaUsuario,int *idStatusUsuario,int *inDisponivelWF)
{
    return proCGetStatusDispUsuario(idPessoaUsuario,idStatusUsuario,inDisponivelWF);
}

int cWFUsuario::pesqStatusDispUsuario(int *idStatusUsuario,int *inDisponivelWF)
{
    return proCGetStatusDispUsuario(entrada,idStatusUsuario,inDisponivelWF);
}

bool cWFUsuario::consultarWFGruposFilaProcesso(DOMNode* entrada,XMLGen* saida)
{
    Collection grupos;
    TuxHelper tx;
    char *p = tx.walkTree(entrada,"idPessoaUsuario",0);
    DadosGrupo* dg;

    int idPessoaUsuario = p ? atoi(p) : 0;

    if ( p ) XMLString::release(&p);

    proCConsultaWFGruposFilaProcesso(idPessoaUsuario, &grupos);

    for (int i = 0; i < grupos.GetCount(); i++)
    {
        dg = (DadosGrupo*) grupos.GetItem(i);
        
        saida->createTag("WFGrupoVO");
            saida->addItem("idGrupo",dg->idGrupo);
            saida->addItem("dsGrupo",dg->dsGrupo);
        saida->closeTag();
    }    

    return true;
}

bool cWFUsuario::consultarWFGruposFilaProcesso(int idPessoaUsuario,XMLGen* saida)
{
    Collection grupos;
    DadosGrupo* dg;

    proCConsultaWFGruposFilaProcesso(idPessoaUsuario, &grupos);

    for (int i = 0; i < grupos.GetCount(); i++)
    {
        dg = (DadosGrupo*) grupos.GetItem(i);
        
        saida->createTag("WFGrupoVO");
            saida->addItem("idGrupo",dg->idGrupo);
            saida->addItem("dsGrupo",dg->dsGrupo);
        saida->closeTag();
    }    

    return true;
}

bool cWFUsuario::consultarWFGruposFilaProcesso()
{
    return cWFUsuario::consultarWFGruposFilaProcesso(entrada,saida);
}

bool cWFUsuario::pesqUsuarioPorGrupo()
{
    return proCPesquisaUsuarioPorGrupo(entrada,saida);
}

bool cWFUsuario::pesqUsuarioPorGrupo(DOMNode* entrada,XMLGen* saida)
{
    return proCPesquisaUsuarioPorGrupo(entrada,saida);
}

bool cWFUsuario::pesqLgUserPorGrupo()
{
    return proCPesquisaLgUserPorGrupo(entrada,saida);
}

bool cWFUsuario::pesqLgUserPorGrupo(DOMNode* entrada,XMLGen* saida)
{
    return proCPesquisaLgUserPorGrupo(entrada,saida);
}

bool cWFUsuario::pesqLgUserPorGrupoMC(DOMNode* entrada,XMLGen* saida,const char *idPessoaUsuario)
{
    return proCPesquisaLgUserPorGrupoMC(entrada,saida,idPessoaUsuario);
}

bool cWFUsuario::pesqMC1UserAtual(DOMNode* entrada,XMLGen* saida)
{
    return proCPesquisaMC1GrpUser(entrada,saida);
}

bool cWFUsuario::pesqUsuarioRelatorio(int idGrupo, XMLGen* saida,int start,int stop)
{
    return proCPesquisaUsuarioRelatorio(idGrupo,saida,start,stop);
}

bool cWFUsuario::pesqUsuarioRelatorio(int idGrupo, XMLGen* saida)
{
    return proCPesquisaUsuarioRelatorioTodo(idGrupo,saida);
}

bool cWFUsuario::pesqUsuarioGrpCanal(DOMNode* entrada,XMLGen* saida)
{
    return proCPesquisaUsuarioGrupoCanal(entrada,saida);
}

bool cWFUsuario::pesqUsuarioGrpCanal()
{
    return proCPesquisaUsuarioGrupoCanal(entrada,saida);
}

bool cWFUsuario::pesqGrupoCanal(DOMNode* entrada,XMLGen* saida)
{
    return proCPesquisaGrupoCanal(entrada,saida);
}

bool cWFUsuario::pesqGrupoCanal()
{
    return proCPesquisaGrupoCanal(entrada,saida);
}

bool cWFUsuario::pesqUsuarioGrpProcedencia(DOMNode* entrada,XMLGen* saida)
{
    return proCPesquisaUsuarioGrupoProcedencia(entrada,saida);
}

bool cWFUsuario::pesqUsuarioGrpProcedencia()
{
    return proCPesquisaUsuarioGrupoProcedencia(entrada,saida);
}

bool cWFUsuario::pesqGrupoProcedencia(DOMNode* entrada,XMLGen* saida)
{
    return proCPesquisaGrupoProcedencia(entrada,saida);
}

bool cWFUsuario::pesqGrupoProcedencia()
{
    return proCPesquisaGrupoProcedencia(entrada,saida);
}

bool cWFUsuario::pesqConsultaWFGrupos(XMLGen *saida)
{
    return proCConsultaWFGrupos(saida);
}

bool cWFUsuario::pesqConsultaWFGruposCri(XMLGen *saida)
{
    return proCConsultaWFGruposCri(saida);
}

bool cWFUsuario::pesqConsultaWFGrupos1(XMLGen *saida, int idUser)
{
    return proCConsultaWFGrupos1(saida, idUser);
}

bool cWFUsuario::pesqConsultaWFGruposBko(XMLGen *saida, int idUser)
{
    return proCConsultaWFGruposBko(saida, idUser);
}

bool cWFUsuario::pesqConsultaWFGruposCri1(XMLGen *saida, int idUser)
{
    return proCConsultaWFGruposCri1(saida, idUser);
}

bool cWFUsuario::pesqConsultaWFGruposRC(XMLGen *saida, int idUser)
{
    return proCConsultaWFGruposRC(saida, idUser);
}

bool cWFUsuario::pesqConsultaWFGruposRelatorios(XMLGen *saida)
{
    return proCConsultaWFGruposRelatorios(saida);
}

bool cWFUsuario::pesqConsultaWFGruposRelatoriosCRI(XMLGen *saida)
{
    return proCConsultaWFGruposRelatoriosCRI(saida);
}


bool cWFUsuario::pesqConsultaGruposUsuario()
{
    return proCConsultaGruposUsuario(entrada,saida);
}

bool cWFUsuario::pesqConsultaGruposUsuario(DOMNode*entrada,XMLGen *saida)
{
    return proCConsultaGruposUsuario(entrada,saida);
}

bool cWFUsuario::pesqConsultaGruposUsuario(int idPessoaUsuario,XMLGen *saida)
{
    return proCConsultaGruposUsuario( idPessoaUsuario, saida );
}

void cWFUsuario::obterListaStatusUsuario(XMLGen *saida)
{
    proCGetListaStatusUsuario(saida);
}

void cWFUsuario::carregaDados()
{
    ULOG_START("cWFUsuario::carregaDados()");
    char *p;

    // Inicializa as estruturas de dados para armazenar os valores
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( p=tx.walkTree( entrada, "idAtendimento", 0 ),p )
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idTipoCarteira", 0 ),p )
    {
        m_stDados.idTipoCarteira = atoi(p); 
        m_vlDados.idTipoCarteira = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idSegmentacao", 0 ),p )
    {
        m_stDados.idSegmentacao = atoi(p); 
        m_vlDados.idSegmentacao = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idProcedencia", 0 ),p )
    {
        m_stDados.idProcedencia = atoi(p); 
        m_vlDados.idProcedencia = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idContato", 0 ),p )
    {
        m_stDados.idContato = atoi(p); 
        m_vlDados.idContato = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idGrupoAbertura", 0 ),p )
    {
        m_stDados.idGrupoAbertura = atoi(p); 
        m_vlDados.idGrupoAbertura = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idTipoPessoa", 0 ),p )
    {
        m_stDados.idTipoPessoa = atoi(p); 
        m_vlDados.idTipoPessoa = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idTipoLinha", 0 ),p )
    {
        m_stDados.idTipoLinha = atoi(p); 
        m_vlDados.idTipoLinha = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idTipoRelacionamento", 0 ),p )
    {
        m_stDados.idTipoRelacionamento=atoi(p); 
        m_vlDados.idTipoRelacionamento=1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idCanal", 0 ),p )
    {
        m_stDados.idCanal = atoi(p); 
        m_vlDados.idCanal = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idUfOperadora", 0 ),p )
    {
        m_stDados.idUFOperadora = atoi(p); 
        m_vlDados.idUFOperadora = 1;
        XMLString::release(&p);
    }
    
    ULOG_END("cWFUsuario::carregaDados()");

}

int cWFUsuario::ObterInDisponivelWF(DOMNode* entrada)
{
    return entrada ? proCGetInDisponivelWF(entrada) : 0;
}

int cWFUsuario::ObterInDisponivelWF()
{
    return entrada ? proCGetInDisponivelWF(entrada) : 0;
}

int cWFUsuario::ObterStatusUsuario(DOMNode* entrada)
{
    return entrada ? proCGetStatusUsuario(entrada) : 0;
}

int cWFUsuario::ObterStatusUsuario()
{
    return entrada ? proCGetStatusUsuario(entrada) : 0;
}
