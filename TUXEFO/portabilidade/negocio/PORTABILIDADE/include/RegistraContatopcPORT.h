#ifndef REGISTRACONTATOPCHPORT
#define REGISTRACONTATOPCHPORT

#include "PPGlobalPORT.h"

class CRegistraContatopc
{
    public:
        void proCIncluiRegistraContato(TRegistraContato *ptRegistraContato);
        void proCAtualizaRegistraContato(TRegistraContato tRegistraContato);
        bool proCExisteRegistraContato(TRegistraContato tRegistraContato);
};

#endif
