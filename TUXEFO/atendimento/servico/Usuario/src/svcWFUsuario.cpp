/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/


#include "../../../commons/routerLib/include/RouterClient.h"

#include <tuxfw.h>
#include "../../../commons/Collection/include/Collection.h"
#include "../include/stUsuario.h"
#include "../../../commons/msgPadrao.h"
#include"../include/cApoio.h"

DECLARE_TUXEDO_SERVICE(WFUsuario);

extern bool proCConsultaWFGrupos(XMLGen* saida);
extern bool proCPesquisaUsuario(st_VariaveisUsuario* _dados, st_Result *result);
extern bool proCAltStatusUsuario(DOMNode*entrada);
extern bool proCPesquisaUsuarioPorGrupo(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaGrupoUsuarioAbertura(DOMNode*entrada,XMLGen* saida);
extern bool proCConsultaGruposUsuario(DOMNode*entrada, XMLGen* saida);
extern bool proCPesquisaGrupoFaseVariables(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCPesquisaGrupoFase(st_VariaveisUsuario* _dados,int &idGrupoDestino);
extern bool proCPesquisaGrupoProxNivel(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCPesquisaGrupoProxNivel(st_VariaveisUsuario* _dados, XMLGen* saida);
extern bool proCPesquisaUsuarioGrupoCanal(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaUsuarioGrupoProcedencia(DOMNode*entrada,XMLGen* saida);
extern bool proCGetStatusUsuario(DOMNode*entrada);

extern bool proCPesquisaGrupoFaseVariablesUsuario(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaGrupoFaseUsuario(DOMNode*entrada,XMLGen* saida);
extern bool proCConsultaWFGruposFilaProcesso( int _idPessoaUsuario, Collection* _grupos );

void implWFUsuario::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implWFUsuario::Execute()");
    proCPesquisaUsuarioGrupoProcedencia(dnode, xml_g);
    setStatusCode("00I0000","Operação Concluida.");
    ULOG_END("implWFUsuario::Execute()");
}

// Pesquisa Grupos de Usuário.
BEGIN_DECLARE_ROUTER_PARM(PsqGruCon)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqGruCon,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqGruCon,1)
    proCConsultaWFGrupos(ac->var.xml);
END_DECLARE_ROUTER_INTERF

// Pesquisa de Grupo Fila Processo
BEGIN_DECLARE_ROUTER_PARM(PsqGrpFilaProc)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqGrpFilaProc,AC_XMLGEN)
BEGIN_DECLARE_ROUTER_INTERF(PsqGrpFilaProc,1)

   

    TuxHelper tx;
    char *p = tx.walkTree( pPsqGrpFilaProc[0].accum->var.dom->RetrieveDOM(), "idPessoaUsuario", 0 );
    Collection grupos;
    DadosGrupo* dg;
    int idPessoaUsuario = p ? atoi(p) : 0;

    if (p) XMLString::release(&p);

    proCConsultaWFGruposFilaProcesso(idPessoaUsuario, &grupos);

    for (int i = 0; i < grupos.GetCount(); i++)
    {
        dg = (DadosGrupo*) grupos.GetItem(i);
        
        ac->var.xml->createTag("WFGrupoVO");
            ac->var.xml->addItem("idGrupo",dg->idGrupo);
            ac->var.xml->addItem("dsGrupo",dg->dsGrupo);
        ac->var.xml->closeTag();
    }
    
END_DECLARE_ROUTER_INTERF
//----------------------------------------------------------------------------------------



// Altera o estado do usuário.
BEGIN_DECLARE_ROUTER_PARM(AltStaUsuario)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_NO_RETURN(AltStaUsuario)

BEGIN_DECLARE_ROUTER_INTERF(AltStaUsuario,1)
    proCAltStatusUsuario(pAltStaUsuario[0].accum->var.dom->RetrieveDOM());
END_DECLARE_ROUTER_INTERF

// Pesquisa os usuários por grupo.
BEGIN_DECLARE_ROUTER_PARM(PsqUsuGrupo)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqUsuGrupo,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqUsuGrupo,1)
    proCPesquisaUsuarioPorGrupo(pPsqUsuGrupo[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

// Pesquisa os grupos de abertura de atendimento do usuário.
BEGIN_DECLARE_ROUTER_PARM(PsUsGrAbertura)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsUsGrAbertura,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsUsGrAbertura,1)
    proCPesquisaGrupoUsuarioAbertura(pPsUsGrAbertura[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

// Pesquisa os grupos do usuário e seus tipo de sequencia.
BEGIN_DECLARE_ROUTER_PARM(PsqGrupoUsuario)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM
// Pesquisa os grupos de uma fase em funcao dos parametros do proceso.
BEGIN_DECLARE_ROUTER_PARM(PsUsGrFsVar)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

// Pesquisa nome e login de usuario a partir de um idPessoaUsuario
BEGIN_DECLARE_ROUTER_PARM(PsqUsuario)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqGrupoUsuario,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqGrupoUsuario,1)
    proCConsultaGruposUsuario(pPsqGrupoUsuario[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

//--------------------------------------------------------------------------------------------------
DECLARE_RETURN_TYPE(PsUsGrFsVar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsUsGrFsVar,1)

    Collection resultado;
    char *p;
    st_VariaveisUsuario dados;
    DOMNode *domNode = pPsUsGrFsVar[0].accum->var.dom->RetrieveDOM();
    TuxHelper tx;

    if ( p=tx.walkTree( domNode, "idFase", 0 ),p )
    {
        dados.idFase = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idTipoCarteira", 0 ),p )
    {
        dados.idTipoCarteira  = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idSegmentacao", 0 ),p )
    {
        dados.idSegmentacao   = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idProcedencia", 0 ),p )
    {
        dados.idProcedencia   = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idContato", 0 ),p )
    {
        dados.idContato       = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idGrupoAbertura", 0 ),p )
    {
        dados.idGrupoAbertura = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idTipoPessoa", 0 ),p )
    {
        dados.idTipoPessoa    = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idTipoLinha", 0 ),p )
    {
        dados.idTipoLinha     = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idTipoRelacionamento", 0 ),p )
    {
        dados.idTipoRelacionamento=atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idCanal", 0 ),p )
    {
        dados.idCanal         = atoi(p); 
        XMLString::release(&p);
    }

    proCPesquisaGrupoFaseVariables(&dados, &resultado);

    for (int i = 0; i < resultado.GetCount(); i++)
    {
        char *idGrupo = (char*) resultado.GetItem(i);

        if ( idGrupo )
        {
            ac->var.xml->createTag("GrupoFase");
                ac->var.xml->addItem("idGrupo", idGrupo);
            ac->var.xml->closeTag();
        }
    }

END_DECLARE_ROUTER_INTERF

//--------------------------------------------------------------------------------------------------
BEGIN_DECLARE_ROUTER_PARM(PsUsGrPrNv)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsUsGrPrNv,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsUsGrPrNv,1)

    TuxHelper tx;

    st_VariaveisUsuario dados;
    Collection resultado;
    char *p;
    DOMNode *domNode = pPsUsGrPrNv[0].accum->var.dom->RetrieveDOM();

    if ( p=tx.walkTree( domNode, "idAtendimento", 0 ),p )
    {
        dados.idAtendimento = atol(p);
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idTipoCarteira", 0 ),p )
    {
        dados.idTipoCarteira  = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idSegmentacao", 0 ),p )
    {
        dados.idSegmentacao   = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idProcedencia", 0 ),p )
    {
        dados.idProcedencia   = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idContato", 0 ),p )
    {
        dados.idContato       = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idGrupoAbertura", 0 ),p )
    {
        dados.idGrupoAbertura = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idTipoPessoa", 0 ),p )
    {
        dados.idTipoPessoa    = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idTipoLinha", 0 ),p )
    {
        dados.idTipoLinha     = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idTipoRelacionamento", 0 ),p )
    {
        dados.idTipoRelacionamento=atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( domNode, "idCanal", 0 ),p )
    {
        dados.idCanal         = atoi(p); 
        XMLString::release(&p);
    }

    proCPesquisaGrupoProxNivel(&dados, &resultado);

    for (int i = 0; i < resultado.GetCount(); i++)
    {
        char *idGrupo = (char*) resultado.GetItem(i);

        if ( idGrupo )
        {
            ac->var.xml->createTag("GrupoFase");
                ac->var.xml->addItem("idGrupo", idGrupo);
            ac->var.xml->closeTag();
        }
    }

END_DECLARE_ROUTER_INTERF

//--------------------------------------------------------------------------------------------------
DECLARE_RETURN_TYPE(PsqUsuario,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqUsuario,1)

    char *p;
    st_Result result;
    st_VariaveisUsuario dados;
    TuxHelper tx;

    try
    {
        ac->var.xml->createTag("UsuarioVIVO");
        ac->var.xml->addProp("xmlns","usuario.fo.vivo.com.br/vo");

        if ( p = tx.walkTree( pPsqUsuario[0].accum->var.dom->RetrieveDOM(), "idPessoaUsuario", 0 ),p )
        {
            dados.idPessoaUsuario = atoi(p); 
            XMLString::release(&p);

            ULOG("idPessoaUsuario = %d" ,dados.idPessoaUsuario);

            if ( proCPesquisaUsuario(&dados,&result) )
            {
                ac->var.xml->addItem("idPessoaUsuario",result.idPessoaUsuario);
                ac->var.xml->addItem("nmNome", result.nmNome);
                ac->var.xml->addItem("nmLoginUsuario",result.nmLoginUsuario);
                ac->var.xml->addItem("inDisponivelWF",result.inDisponivelWF);
                ac->var.xml->addItem("idStatusUsuario",result.idStatusUsuario);
            }
            else
            {
                ULOG("idPessoaUsuario '%d' nao encontrado",dados.idPessoaUsuario);
            }
        }
        else
        {
            ULOG("'idPessoaUsuario' nao especificado no XML de entrada");
        }

        ac->var.xml->closeTag();
    }
    catch (...)
    {
        ULOGE("Excecao nao identificada");
    }

END_DECLARE_ROUTER_INTERF

//--------------------------------------------------------------------------------------------------
// Pesquisa os grupos de uma fase do proceso.
BEGIN_DECLARE_ROUTER_PARM(PsUsGrFase)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsUsGrFase,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsUsGrFase,1)

    TuxHelper tx;

    st_VariaveisUsuario dados;
    int idGrupo;
    char *p;

    memset(&dados,0,sizeof(dados));

    if ( p=tx.walkTree( pPsUsGrFase[0].accum->var.dom->RetrieveDOM(), "idFase", 0 ),p )
    {
        dados.idFase = atoi(p); 
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( pPsUsGrFase[0].accum->var.dom->RetrieveDOM(), "idContato", 0 ),p )
    {
        dados.idContato = atoi(p); 
        XMLString::release(&p);
    }

    if ( proCPesquisaGrupoFase(&dados,idGrupo) )
    {
        ac->var.xml->createTag("GrupoFase");
            ac->var.xml->addItem("idGrupo", idGrupo);
        ac->var.xml->closeTag();
    }

END_DECLARE_ROUTER_INTERF

//--------------------------------------------------------------------------------------------------
// Pesquisa canal
BEGIN_DECLARE_ROUTER_PARM(PsUsGrCanal)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsUsGrCanal,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsUsGrCanal,1)
    proCPesquisaUsuarioGrupoCanal(pPsUsGrCanal[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

//--------------------------------------------------------------------------------------------------
// Pesquisa os grupos de uma fase do processo.
BEGIN_DECLARE_ROUTER_PARM(PsUsGrProce)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsUsGrProce,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsUsGrProce,1)
    proCPesquisaUsuarioGrupoProcedencia(pPsUsGrProce[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

//--------------------------------------------------------------------------------------------------
// Pesquisa grupo de abertura do usuario pela segmentacao
BEGIN_DECLARE_ROUTER_PARM(PsUsGrFsVarUsu)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsUsGrFsVarUsu,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsUsGrFsVarUsu,1)
    proCPesquisaGrupoFaseVariablesUsuario(pPsUsGrFsVarUsu[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

//--------------------------------------------------------------------------------------------------
// Pesquisa grupo de abertura do usuario.
BEGIN_DECLARE_ROUTER_PARM(PsUsGrFaseUsu)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsUsGrFaseUsu,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsUsGrFaseUsu,1)
    proCPesquisaGrupoFaseUsuario(pPsUsGrFaseUsu[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

