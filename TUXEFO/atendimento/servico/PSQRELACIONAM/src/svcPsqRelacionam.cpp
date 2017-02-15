/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2010/08/02 15:05:55 $
 **/

#include <tuxfw.h>
#include "../../Atendimento/include/cWFAtendimento.h"

DECLARE_TUXEDO_SERVICE(PSQRELACIONAM)

void implPSQRELACIONAM::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implPSQRELACIONAM::Execute()");
	
	cWFAtendimento atd(dnode, xml_g);

    char* p;
    char operacao[25];
    char PsqNrProtocolo[41];
    
    memset( PsqNrProtocolo, 0x0, sizeof(PsqNrProtocolo) );
    
	xml_g->createTag("AtendimentoRelacionamentosVO");
		xml_g->addProp("xmlns",    "workflow.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:ns1","workflow.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:ns2","admsistemas.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:ns3","usuario.fo.vivo.com.br/vo");

        strcpy( PsqNrProtocolo,atd.ObterPsqNrProtocolo() );
        if ( PsqNrProtocolo[0] != 0x0 )
        {
            ULOG( "Obteve protocolo [%s]",PsqNrProtocolo );
        }
        if ( atd.ObterMigracao() != true )   // Nao acessa dados de migracao
        {
            ULOG( ">>> ACESSANDO DADOS NAO MIGRACAO" );
            if ( PsqNrProtocolo[0] == 0x0 )
            {
                atd.consultarRelacionamento();
            }
            else
            {
                atd.consultarProtocolos();
            }
        }
        else   // Acessando dados de migracao
        {
            ULOG( ">>> ACESSANDO DADOS DE MIGRACAO" );
            if ( PsqNrProtocolo[0] == 0x0 )
            {
                atd.consultarRelacionamentoMig();
            }
            else
            {
                atd.consultarProtocolosMig();
            }
        }

	xml_g->closeTag();

	setStatusCode("04I0000","Consulta Concluída.");

	ULOG_END("implPSQRELACIONAM::Execute()");

}

