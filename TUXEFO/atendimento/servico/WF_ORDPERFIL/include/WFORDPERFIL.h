#ifndef SVCMAIN
#define SVCMAIN

#include <tuxfw.h>

#define STRLENNULL( y ) ( y == NULL ? 0 : strlen( y )  )

int Grupo_SelecionarTodos( DOMNode*dnode, XMLGen* );

#endif
