/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:41 $
 **/

#include "../include/cWFAtdPsqGrpUtSq.h"

#include "../../Usuario/include/cWFUsuario.h"

cWFAtdPsqGrpUtSq::cWFAtdPsqGrpUtSq(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdPsqGrpUtSq::Executar()
{
    char *p = tx.walkTree( entrada, "idPessoaUsuario", 0 );

    if ( !p )
    {
        SetarErro(NULL,"Valor do 'idPessoaUsuario' é obrigatório e não foi fornecido");
        ULOGE(ObterMsgErro());
        return false;
    }

    XMLString::release(&p);

    saida->createTag("AtendimentoInformacaoVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    bool retorno = ConsultarGruposUsuario();

    saida->closeTag();

    return retorno;
}

bool cWFAtdPsqGrpUtSq::ConsultarGruposUsuario()
{
    cWFUsuario cwfUsuario;

    

	bool retorno = cwfUsuario.pesqConsultaGruposUsuario(entrada,saida);

    if ( !retorno )
    {
        SetarErro(NULL,"cWFUsuario::pesqConsultaGruposUsuario falhou!");
        ULOGE(ObterMsgErro());
    }

    return retorno;
}
