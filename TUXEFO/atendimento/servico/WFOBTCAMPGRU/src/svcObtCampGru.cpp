/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:54 $
 **/


#include "../include/cObtCampGruPC.h"

DECLARE_TUXEDO_SERVICE(WFOBTCAMPGRU);

void implWFOBTCAMPGRU::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFOBTCAMPGRU::Execute()");
    register int indx;
    Collection resultado;
    cObtCampGruPC rc;
    st_VariaveisCampanhaGrupo dados;
    st_VariaveisCampanhaGrupo * pdadosResult;
    char *p;

    if ( p=walkTree( dnode, "idGrupo", 0 ),p )
    {
        strcpy( dados.idGrupo, p);
        XMLString::release(&p);
    }

    rc.SelectByGroupPC( &dados, &resultado );

    xml_g->createTag("RetornoWFCTIResultadoVO");
    xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");

    for ( indx=0;indx < resultado.GetCount();indx++ )
    {
        pdadosResult  = (st_VariaveisCampanhaGrupo *)resultado.GetItem(indx);

        xml_g->createTag("RetornoWFCTIVO");
        xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
            xml_g->addItem("idRetornoWFCTI", pdadosResult->idretornowfcti );
            xml_g->addItem("sgRetornoWFCTI", pdadosResult->sgretornowfcti );
            xml_g->addItem("dsRetornoWFCTI", pdadosResult->dsretornowfcti );
            xml_g->addItem("sgStatus", pdadosResult->sgstatus );
            xml_g->addItem("inPadrao", pdadosResult->inpadrao );
        xml_g->closeTag();
    }

    xml_g->closeTag();

    setStatusCode("04I0000","Obtencao de Campanhas por Grupo Concluida.");
    
    ULOG_END("implWFOBTCAMPGRU::Execute()");
}
