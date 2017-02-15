/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:42 $
 **/

#include "../include/cWfAtdFechaProt.h"
#include "../../../commons/cWfAtdProtocoloException.h"

void cWfAtdFechaProt::Executar()
{
    try
    {
        CarregarDados();

        strcpy(dados.idUsuarioAlteracao,getUser());

        cwfAtdFechaProtpc.FecharProtocolo(&dados,&status);
    }
    catch (...)
    {
        throw;
    }
}

void cWfAtdFechaProt::CarregarDados()
{
    if ( 0 == entrada )
    {
        throw new TuxException(ERR_XML_ENTRADA,CErroAtendimento(ERR_XML_ENTRADA).outMsg());
    }

    char *p;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    if ( p=walkTree(entrada,"nrProtocolo",0),p )
    {
        if ( *p )
        {
             SAFE_STRNCPY(dados.idAtendimentoProtocolo,p);
             status.idAtendimentoProtocolo = 1;
        }

        XMLString::release(&p);

    }

    if ( dados.idAtendimentoProtocolo[0]==0 )
    {
        throw new TuxException(ERR_XML_IDATENDIMENTOPROTOCOLO,CErroAtendimento(ERR_XML_IDATENDIMENTOPROTOCOLO).outMsg());
    }
    if ( p=walkTree(entrada,"idSistemaOrigem",0),p )
    {
        if ( *p )
        {
             SAFE_STRNCPY(dados.idSistemaOrigem,p);
             status.idSistemaOrigem = 1;
        }

        XMLString::release(&p);
    }

    if ( dados.idSistemaOrigem[0]==0 )
    {
        throw new TuxException(ERR_XML_IDSISTEMAORIGEM,CErroAtendimento(ERR_XML_IDSISTEMAORIGEM).outMsg());
    }

}
