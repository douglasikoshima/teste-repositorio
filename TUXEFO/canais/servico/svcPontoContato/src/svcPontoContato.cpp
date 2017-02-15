
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"
#include "../../negocio/UtilCanais/include/UtilCanais/UtilCanais.hpp"



extern void proCConsultaStatus( XMLGen * Saida );

DECLARE_TUXEDO_SERVICE(STATUSPTOCNT);

void implSTATUSPTOCNT::Execute(DOMNode* dnode, XMLGen* xml_g) 
{

   CTuxHelperClever helper;
   
   
   char *user = getUser();
   
   proCConsultaStatus( xml_g );

   setStatusCode("13I0000","Sucesso");
   
}
