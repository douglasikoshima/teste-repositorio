/**
	Interface tuxedo para associa��o de campanha no m�dulo de campanhas.
 
	@modulo  Campanha
	@usecase Fun��o de associa��o de campanhas no discador.
	@author  Renato Teixeira
	@version $Revision: 1.1 $
	@CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:42 $
*/

#include "../include/cAssocCampanha.h"

DECLARE_TUXEDO_SERVICE(ASSCONSCAM);

void implASSCONSCAM::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	int idSubCampanhaHistorico = 0;
	
	if (walkTree( dnode, "idSubCampanhaHistorico", 0 ) != NULL) {
		idSubCampanhaHistorico = atoi(walkTree( dnode, "idSubCampanhaHistorico", 0 ));
	}
	else
	{
		throw TuxException("05E00001", "� necess�rio informar o c�digo da campanha para associar ao discador.");
	}
		
	cAssocCampanha* as = new cAssocCampanha();

	// Executa as fun��es necess�rias para associar uma campanha.
	as->associaCampanha(idSubCampanhaHistorico);
	as->associaUsuariosCampanha(idSubCampanhaHistorico);
	
	setStatusCode("05I0000","Processo conclu�do.");

}
