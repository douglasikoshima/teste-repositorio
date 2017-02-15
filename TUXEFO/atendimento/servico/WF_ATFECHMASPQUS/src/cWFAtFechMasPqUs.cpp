/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.4 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:34 $
 **/

#include "../include/cWFAtFechMasPqUs.h"

#include "../../Usuario/include/cWFUsuario.h"
#include "../../Atendimento/include/cWFAtendimento.h"

cWFAtFechMasPqUs::cWFAtFechMasPqUs(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtFechMasPqUs::Executar()
{
    bool retorno = false;
    char *idGrupo = tx.walkTree( entrada, "idGrupo", 0 );

    if ( !idGrupo )
    {
        SetarErro(NULL,"Valor do 'idGrupo' é obrigatório e não foi fornecido");
        ULOGE(ObterMsgErro());
        return false;
    }

    XMLString::release(&idGrupo);

    char *idAtendimento = tx.walkTree( entrada, "idAtendimento", 0 );

    saida->createTag("AtendimentoInformacaoVO ");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    //Removido atendendo chamado de produção 15415271 descrito abaixo:
    //Defeito 2 - Supervisor do grupo não consegue encaminhar o processo MC1 para outro analista.
    //15415271 - Supervisores não conseguem encaminhar os processos do Modelo Meu Cliente 1 manualmente para outro representante
    //TRATATIVAS:
    //- O supervisor do grupo poderá encaminhar todos os processos para outros usuários do mesmo grupo de abertura do processo.
    //
    // if ( idAtendimento && *idAtendimento )
    // {
    //     cWFAtendimento cwfAtendimento;
    //     DetalheAtendimento detalheAtendimento;
    // 
    //     int iIdAtendimento = atol(idAtendimento);
    //     XMLString::release(&idAtendimento);
    // 
    //     if ( cwfAtendimento.ObtemDetalheAtend(iIdAtendimento,&detalheAtendimento) == false )
    //     {
    //         SetarErro(NULL,"Protocolo fornecido não foi encontrado.");
    //         ULOGE(ObterMsgErro());
    //         return false;
    //     }
    // 
    //     if ( strcmp(detalheAtendimento.sgFluxoAtendimento,"MC1")==0 )
    //     {
    //         retorno = PesquisarUsuarioMC1();
    //         saida->closeTag();
    //         return retorno;
    //     }
    // }

    char *inMeuCliente = tx.walkTree( entrada, "inMeuCliente", 0 );

    if ( inMeuCliente )
    {
        ULOG("inMeuCliente=%s",inMeuCliente);
        XMLString::release(&inMeuCliente);

        DetalheAtendimento detalheAtendimento;

        cWFAtendimento cwfatendimento;
        long i_idAtendimento = atol(idAtendimento);
        cwfatendimento.ObtemDetalheAtend(i_idAtendimento,&detalheAtendimento);

        if ( strcmp(detalheAtendimento.sgFluxoAtendimento,"MC2")==0 )
        {
            retorno = PesquisarUsuarioPorGrupo();
        }
        else
        {
            retorno = PesquisarUsuarioPorGrupoMC();
        }
    }
    else
    {
        retorno = PesquisarUsuarioPorGrupo();
    }

    saida->closeTag();

    return retorno;
}

// bool cWFAtFechMasPqUs::PesquisarUsuarioMC1()
// {
//     cWFUsuario cwfUsuario;
// 
// 	bool retorno = cwfUsuario.pesqMC1UserAtual(entrada,saida);
// 
//     if ( !retorno )
//     {
//         SetarErro(NULL,"cWFUsuario::PesquisarUsuarioMC1 falhou!");
//         ULOGE(ObterMsgErro());
//     }
// 
//     return retorno;
// }

bool cWFAtFechMasPqUs::PesquisarUsuarioPorGrupo()
{
    cWFUsuario cwfUsuario;

	bool retorno = cwfUsuario.pesqLgUserPorGrupo(entrada,saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFUsuario::pesqLgUserPorGrupo falhou!");
        ULOGE(ObterMsgErro());
    }

    return retorno;
}

bool cWFAtFechMasPqUs::PesquisarUsuarioPorGrupoMC()
{
    cWFUsuario cwfUsuario;

	bool retorno = cwfUsuario.pesqLgUserPorGrupoMC(entrada,saida,idUsuario);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFUsuario::pesqLgUserPorGrupoMC falhou!");
        ULOGE(ObterMsgErro());
    }

    return retorno;
}
