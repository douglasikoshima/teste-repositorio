/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:33 $
 **/

#include "../include/cWFAtdPqChaAtDus.h"

#include "../../ChamadaAtendimento/include/cWFChamadaAtendimento.h"

cWFAtdPqChaAtDus::cWFAtdPqChaAtDus(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

bool cWFAtdPqChaAtDus::Executar()
{
    char *p = tx.walkTree( entrada, "idChamadaTelefonica", 0 );

    if ( !p )
    {
        SetarErro(NULL,"Valor do 'idChamadaTelefonica' é obrigatório e não foi fornecido");
        ULOGE(ObterMsgErro());
        return false;
    }

    XMLString::release(&p);

    saida->createTag("AtendimentoVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
    saida->createTag("AtendimentosCorrentes");

    bool retorno = ConsultarContato();

    saida->closeTag();
    saida->closeTag();

    return retorno;
}

bool cWFAtdPqChaAtDus::ConsultarContato()
{
    ULOG_START("cWFAtdPqChaAtDus::ConsultarContato()");
    
    cWFChamadaAtendimento cwfChamadaAtendimento(entrada,saida);

    

	bool retorno = cwfChamadaAtendimento.consultarContato();

    if ( !retorno )
    {
        SetarErro(NULL,"cWFChamadaAtendimento::consultarContato falhou!");
        ULOGE(ObterMsgErro());
    }
    ULOG_END("cWFAtdPqChaAtDus::ConsultarContato()");
    return retorno;
}
