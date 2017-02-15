/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:03 $
 **/

#include "../../../commons/msgPadrao.h"

#include "../include/cWFIBWFPSUsuario.h"
#include"../../../commons/Collection/include/Collection.h"

extern bool proCIbPesquisaUsuario(st_VariaveisUsuario* _dados, st_Result *result);
// extern bool proCIbPesquisaGrupoAberturaUsuario(DOMNode*entrada,XMLGen* saida);
extern bool proCIbConsultaWFGrupos(XMLGen* saida);
extern bool proCIbConsultaWFGruposFilaProcesso( DOMNode * entrada , XMLGen * saida );
// extern bool proCIbPesquisaUsuarioPorGrupo(DOMNode*entrada,XMLGen* saida);
extern bool proCIbPesquisaGrupoUsuarioAbertura(DOMNode*entrada,XMLGen* saida);
extern bool proCIbConsultaGruposUsuario(DOMNode*entrada, XMLGen* saida);
extern bool proCIbPesquisaGrupoFaseVariables(st_VariaveisUsuario* _dados, Collection** _grupos);
extern bool proCIbPesquisaGrupoFaseVariablesUsuario(DOMNode*entrada,XMLGen* saida);
extern bool proCIbPesquisaGrupoFase(st_VariaveisUsuario* _dados, Collection** _grupos);
extern bool proCIbPesquisaGrupoFaseUsuario(DOMNode*entrada,XMLGen* saida);
extern bool proCIbPesquisaUsuarioGrupoCanal(DOMNode*entrada,XMLGen* saida);
extern bool proCIbPesquisaUsuarioGrupoProcedencia(DOMNode*entrada,XMLGen* saida);

cIBWFPSUsuario::cIBWFPSUsuario(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;
}

int cIBWFPSUsuario::Executar()
{
	ULOG_START("cIBWFPSUsuario::Executar()");
	char *p;
	st_Result result;
	st_VariaveisUsuario dados;
	TuxHelper tx;
	int retorno = cIBWFPSUsuario::ERRO;

   try
   {
	    saida->createTag("UsuarioVIVO");
	    saida->addProp("xmlns","usuario.fo.vivo.com.br/vo");

        if ( p = tx.walkTree( entrada, "idPessoaUsuario", 0 ),p )
        {
	         dados.idPessoaUsuario = atoi(p); 
            XMLString::release(&p);

            ULOG("idPessoaUsuario = %d",dados.idPessoaUsuario);

            if ( proCIbPesquisaUsuario(&dados,&result) )
            {
	            saida->addItem("idPessoaUsuario",result.idPessoaUsuario);
	            saida->addItem("nmNome", result.nmNome);
	            saida->addItem("nmLoginUsuario",result.nmLoginUsuario);
	            saida->addItem("inDisponivelWF",result.inDisponivelWF);

                ULOG("Linha resultante da query:");
                ULOG("idPessoaUsuario = '%s'",result.idPessoaUsuario);
                ULOG("nmNome = '%s'",result.nmNome);
                ULOG("nmLoginUsuario = '%s'",result.nmLoginUsuario);
                ULOG("inDisponivelWF = '%s'",result.inDisponivelWF);

                retorno = cIBWFPSUsuario::SUCESSO;
            }
            else
            {
                ULOG("idPessoaUsuario '%d' nao encontrado" ,dados.idPessoaUsuario);
            }
        }
        else
        {
            ULOG("'idPessoaUsuario' nao especificado no XML de entrada");
        }

	    saida->closeTag();
    }
    catch (...)
    {
        ULOGE("Excecao nao identificada");
    }
    ULOG_END("cIBWFPSUsuario::Executar()");
    return retorno;
}



int cIBWFPSUsuario::ObtemUsuario( int idUsuario,XMLGen * xml_g )
{
    ULOG_START("cIBWFPSUsuario::ObtemUsuario()");
    st_Result result;
    st_VariaveisUsuario dados;
    int retorno = cIBWFPSUsuario::ERRO;

    try
    {
	    dados.idPessoaUsuario = idUsuario; 

        ULOG("idPessoaUsuario = %d",dados.idPessoaUsuario);

        if ( proCIbPesquisaUsuario(&dados,&result) )
        {
	        xml_g->addItem("nmLoginUsuario",result.nmLoginUsuario);
	        xml_g->addItem("idStatusUsuario",result.idStatusUsuario);
	        xml_g->addItem("inDisponivelWF",result.inDisponivelWF);

            ULOG("Linha resultante da query:");
            ULOG("nmLoginUsuario = '%s'",result.nmLoginUsuario);
            ULOG("inDisponivelWF = '%s'",result.inDisponivelWF);

            retorno = cIBWFPSUsuario::SUCESSO;
        }
        else
        {
            ULOG("idPessoaUsuario '%d' nao encontrado",dados.idPessoaUsuario);
        }

    }
    catch (...)
    {
        ULOGE("Excecao nao identificada");
    }
    ULOG_END("cIBWFPSUsuario::ObtemUsuario()");
    return retorno;
}
