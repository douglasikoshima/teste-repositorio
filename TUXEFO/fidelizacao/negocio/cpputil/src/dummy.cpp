//
// $Id: dummy.cpp,v 1.1 2009/07/31 15:33:39 a5110702 Exp $
//

#include <stdio.h>
#include "cpputil.hpp"


// Soh e usado para compilar no msvc
int codErroBase = 0;

int main()
{
  int codErro = 123;

  ulog(" Oi mundo !!!");
  ulog_int(codErro);
  ulog(" Adeus mundo cruel !");
 
  return 0;
}

