//
// $Id: dummy.cpp,v 1.1 2009/07/31 15:33:56 a5110702 Exp $
//

#include <stdio.h>
#include "campanha.hpp"

int codErroBase = 1;

int main()
{
  int teste_int = 123;
  char codErroStr[10];
  int codErro = 123;

  sprintf(codErroStr, ERRCMP,codErroBase + codErro);
  
  ULOG(" erro : [%s] \n",codErroStr);
  ULOG(" ok   : [%s] \n",OKCMP);
  ULOG(" adeus mundo cruel !");
 
  return 0;
}

