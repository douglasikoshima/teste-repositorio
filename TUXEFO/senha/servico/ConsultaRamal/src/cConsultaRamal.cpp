/* $Id: cConsultaRamal.cpp,v 1.1 2009/07/31 15:33:32 a5110702 Exp $ */

#include <tuxfw.h>
#include "../include/cConsultaRamal.h"

extern bool proCConsultaRamal(int idCallCenter, XMLGen*xml_g);


cConsultaRamal::cConsultaRamal()
{

}

void cConsultaRamal::consultarRamal(int idCallCenter, XMLGen*xml_g)
{

		proCConsultaRamal(idCallCenter, xml_g);

}
