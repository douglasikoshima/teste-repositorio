/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:20 $
 **/

#include "../include/cWfAtdGetDadoPro.h"
#include "../../../commons/definesAtendimento.h"
#include "../../../commons/cWfAtdProtocoloException.h"

void cWfAtdGetDadoPro::Executar()
{
    try
    {
        char *p;
        char idAtendimentoProtocolo[39] = {0};
        char idSistemaOrigem[39] = {0};
        char cdAreaRegistro[3] = {0};
        char nrTelefone[15] = {0};

        if ( p=walkTree(entrada,"nrProtocolo",0),p )
        {
            if ( *p )
            {
                 SAFE_STRNCPY(idAtendimentoProtocolo,p);
            }
            XMLString::release(&p);
        }

        if ( p=walkTree(entrada,"idSistemaOrigem",0),p )
        {
            if ( *p )
            {
                 SAFE_STRNCPY(idSistemaOrigem,p);
            }
            XMLString::release(&p);
        }

        if ( p=walkTree(entrada,"cdAreaRegistro",0),p )
        {
            if ( *p )
            {
                 SAFE_STRNCPY(cdAreaRegistro,p);
            }
            XMLString::release(&p);
        }

        if ( p=walkTree(entrada,"nrTelefone",0),p )
        {
            if ( *p )
            {
                 SAFE_STRNCPY(nrTelefone,p);
            }
            XMLString::release(&p);
        }

        dadosVOSaida = cwfatdgetdadopropc.getDadosAtendimentoProtocolo();

        // se forneceu um numero de protocolo retorna os dados do mesmo.
        if ( idAtendimentoProtocolo[0] )
        {
            if ( cwfatdgetdadopropc.proCObterDadosProtocoloSMS(idAtendimentoProtocolo) == false )
            {
                throw new TuxException(ERR_NEGINFO_PROTOCOLO_NOTFOUND,
                                            CErroAtendimento(ERR_NEGINFO_PROTOCOLO_NOTFOUND).outMsg());
            }
        }
        else
        {
            if ( idSistemaOrigem[0]==0 )
            {
                throw new TuxException(ERR_XML_IDSISTEMAORIGEM,
                                            CErroAtendimento(ERR_XML_IDSISTEMAORIGEM).outMsg());
            }

            if ( cdAreaRegistro[0]==0 )
            {
                throw new TuxException(ERR_XML_CDAREAREGISTRO,
                                            CErroAtendimento(ERR_XML_CDAREAREGISTRO).outMsg());
            }

            if ( nrTelefone[0]==0 )
            {
                throw new TuxException(ERR_XML_NRLINHA,
                                            CErroAtendimento(ERR_XML_NRLINHA).outMsg());
            }
            //
            // se não forneceu um número de protocolo e forneceu um telefone e
            // sistema origem, busca um protocolo em atendimento.
            if ( cdAreaRegistro[0]==0 || nrTelefone[0]==0 || idSistemaOrigem[0]==0 )
            {
                throw new TuxException(ERR_NEGINFO_TAGSOBRIG_NOTFOUND,
                                            CErroAtendimento(ERR_NEGINFO_TAGSOBRIG_NOTFOUND).outMsg());
            }

            if ( cwfatdgetdadopropc.
                    proCObterDadosProtocoloEmAtendimento(cdAreaRegistro,
                                                         nrTelefone,
                                                         idSistemaOrigem) == false )
            {
                throw new TuxException(ERR_NEGINFO_PROTATEND_NOTFOUND,
                                            CErroAtendimento(ERR_NEGINFO_PROTATEND_NOTFOUND).outMsg());
            }
        }

    }
    catch (...)
    {
        throw;
    }
}
