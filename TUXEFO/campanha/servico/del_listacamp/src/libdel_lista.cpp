/**
 * 
 * @modulo  libins_listacampanha
 * @usecase insereListaCampanha
 * @author  Roberto
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:48 $
 **/

#include "../../negocio/cmputil/include/campanha.hpp"
#include "del_lista.hpp"

DECLARE_TUXEDO_SERVICE(DELLISTACAMP);  

extern int del_lista(int, int); 
extern char retstr[256];
extern char retsta[256];

void implDELLISTACAMP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	//
	// declaracao de variaveis
	char * ptUsr;
	char *nmIdLista;
	int  idLista;
	int  idUsuario;

	ULOG_START("DELLISTACAMP");

	ptUsr = getUser();

	idUsuario = atoi( ptUsr );

	nmIdLista=NULL;

	nmIdLista=walkTree(dnode,"idLista",0);

	if ( nmIdLista == NULL ) 
	{
		ULOG("idLista não encontrado");
		setStatusCode(NOKCMP, "idLista não encontrado");
		return; 
	}

	idLista = atoi( nmIdLista ); 

	if ( !del_lista(idLista, idUsuario) )
	{
		//
		// Gera XML de saida contendo o id da lista recém-incluida
		xml_g->createTag("tns:retornoVO");
		xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
		xml_g->addItem( "valor",idLista);
		xml_g->closeTag();
		setStatusCode(OKCMP, retstr);
	}	
	else
	setStatusCode(NOKCMP, retstr);

//	ulog(retstr);

	ULOG_END("DELLISTACAMP");

	XMLString::release(&nmIdLista);

}
