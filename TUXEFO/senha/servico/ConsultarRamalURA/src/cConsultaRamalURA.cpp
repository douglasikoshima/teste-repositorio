/* $Id: cConsultaRamalURA.cpp,v 1.1 2009/07/31 15:34:07 a5110702 Exp $ */

#include <tuxfw.h>
#include "../include/cConsultaRamalURA.h"

extern bool proCConsultaRamalURA(int idRamalSenha, XMLGen*xml_g);
extern bool proCConsultaNavegacaoURA(  DOMNode*dnode, XMLGen*xml_g );

cConsultaRamalURA::cConsultaRamalURA()
{

}



void cConsultaRamalURA::consultarRamalURA(int idRamalSenha, XMLGen*xml_g)
{

		proCConsultaRamalURA(idRamalSenha, xml_g);

}



void cConsultaRamalURA::consultaNavegacaoURA( DOMNode*dnode, XMLGen*xml_g )
{
   proCConsultaNavegacaoURA( dnode, xml_g );
}
