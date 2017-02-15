/* $Id: cConsultaRamal.h,v 1.1 2009/07/31 15:34:34 a5110702 Exp $ */

#ifndef CONSULTARAMAL
	#define CONSULTARAMAL

#include <tuxfw.h>

class cConsultaRamal
{

	public:
		cConsultaRamal();
		void consultarRamal(int idCallCenter, XMLGen* xml_g);

};
 
#endif

