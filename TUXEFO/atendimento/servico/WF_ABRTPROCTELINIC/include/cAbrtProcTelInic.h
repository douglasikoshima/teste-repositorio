#include <tuxfw.h>
#include "../../../commons/SmallString.h"


class cAbrtProcTelInic
{
   
    public:
        char *gerarIDDom() { static int id=0; static char stID[32]; sprintf(stID,"ID_%d",++id); return stID; }

};