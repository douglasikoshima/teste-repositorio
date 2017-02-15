/* $Id: cConsultaRamalURA.h,v 1.1 2009/07/31 15:35:01 a5110702 Exp $ */

#ifndef CONSULTARAMALURA
	#define CONSULTARAMALURA

#include <tuxfw.h>

class cConsultaRamalURA
{

	public:
		cConsultaRamalURA();
		void consultarRamalURA(int idRamalSenha, XMLGen* xml_g);
		void consultaNavegacaoURA( DOMNode*dnode, XMLGen*xml_g );

};
 
#endif

