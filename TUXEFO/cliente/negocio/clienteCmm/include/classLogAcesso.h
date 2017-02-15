#ifndef CLASSLOGACESSO
#define CLASSLOGACESSO

#include "Global.h"
//#include <oraca.h> 
#include <tuxfw.h>

class CLogAcesso:public TuxBasicSvc
{
public:
    CLogAcesso(char *);
    ~CLogAcesso(){};

    int RegistrarAcao(char *);

private:
    char sUsuario[255];
};

#endif
