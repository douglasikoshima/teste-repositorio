/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:28 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>

using namespace std;

#include "../../AndamentoProcessoFODPR/include/cWFAndamentoProcessoFODPR.h"
#include "../include/cWFAtendimentoGerarXMLDPR.h"


void cWFAtdGerarXMLDPR::persistirDadosDPRContatoTecnico(XMLDPR *xml)
{
    ULOG_START("cWFAtdGerarXMLDPR::persistirDadosDPRContatoTecnico()");

    try
    {
        if ( xml->obterTamAreaXML() )
        { // só gera se existir algum VO na memória
            XMLGen xmlDpr;

            string xmlStr = 
                "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
               "<msg>"
                   "<msgHdr>"
                       "<user>"+xml->obterIDUser()+"</user>"
                       "<service>"+(string)xml->nomeServico+"</service>"
                    "</msgHdr>"
                    "<msgBody>"
                       "<xml-fragment>" + xml->obterXMLDpr() +
                       "</xml-fragment>"
                   "</msgBody>"
               "</msg> ";

            // Campo para o XML foi alterado para CLOB
            // if ( xmlStr.size() > 4000 )
            // {
            //     throw DPRXMLException(ERR_DPR_TMAX_XML_OVERFLOW,__FILE__,__LINE__);
            // }

            st_AndamentoProcFODPR dados;
            st_vlAndamentoProcFODPR status;

            memset(&dados,0,sizeof(dados));
            memset(&status,0,sizeof(status));

            dados.idUsuarioAlteracao = xml->idUser;
            dados.xml = xmlStr.c_str();

            status.idUsuarioAlteracao = status.xml = 1;

            cWFAndamentoProcFODPR cwfandamentoprocfodpr;

            cwfandamentoprocfodpr.incluir(&dados,&status,xml->idAtendimento);
        }
        else
        {
            ULOGW("Não existem dados para o DPR.");
        }
    }
    catch(DPRXMLException dprxmlex)
    { // traduz exceção local
        string cod = dprxmlex.getCodErr();
        string msg = dprxmlex.getMsgErr();

        throw new TuxException((char*)cod.c_str(), (char*)msg.c_str());
    }
    catch(...)
    {
        throw;
    }

    ULOG_END("cWFAtdGerarXMLDPR::persistirDadosDPRContatoTecnico()");
}
