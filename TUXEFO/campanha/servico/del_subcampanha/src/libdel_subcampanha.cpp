/**
 * 
 * @modulo  libdelsubcampanha
 * @usecase delsubcampanha
 * @author  Roberto
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELSUBCAMPANHA);  

extern int del_subcampanha(int, int); 
extern char retstr[256];
extern char retsta[256];

void implDELSUBCAMPANHA::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	//
	// declaracao de variaveis
	char * ptUsr;
	char *stidSubCampanhaHistorico;
	int  idSubCampanhaHistorico;
	int  idUsuario;

	ULOG_START("DELSUBCAMPANHA");

	ptUsr = getUser();

	idUsuario = atoi( ptUsr );

	stidSubCampanhaHistorico=NULL;

	stidSubCampanhaHistorico=walkTree(dnode,"idSubCampanhaHistorico",0);

	if ( stidSubCampanhaHistorico == NULL ) 
	{
		ULOG("idSubCampanhaHistorico não encontrado");
		setStatusCode(NOKCMP, "TAG idSubCampanhaHistorico não encontrado");
		return; 
	}

	idSubCampanhaHistorico = atoi( stidSubCampanhaHistorico ); 

	if ( del_subcampanha(idSubCampanhaHistorico, idUsuario) == 1 )
	{
		//
		// Gera XML de saida contendo o id da lista recém-incluida
		xml_g->createTag("tns:retornoVO");
		xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
		xml_g->addItem( "valor",idSubCampanhaHistorico);
		xml_g->closeTag();
		setStatusCode(OKCMP, retstr);
	}	
	else
		setStatusCode("05W0001",retstr);

//	ulog(retstr); 

	ULOG_END("DELSUBCAMPANHA");

	XMLString::release(&stidSubCampanhaHistorico);

}
