//
// $Id: dummy.cpp,v 1.1 2009/07/31 15:34:30 a5110702 Exp $
//

#include <stdio.h>
#include "../../negocio/fidutil/include/retencao.hpp"


int codErroBase = COD_BASE_RETENCAO;

int main()
{
  int teste_int = 123;
  char codErroStr[10];
  int codErro = 123;

  sprintf(codErroStr, NOKFID,codErroBase + codErro);
  
  ULOG(" erro : [%s] \n",codErroStr);
  ULOG(" ok   : [%s] \n",OKFID);
  ULOG(" adeus mundo cruel !");
 
  return 0;
}

