#ifndef REGISTRACONTATOPCHTMA
#define REGISTRACONTATOPCHTMA

#include "PPGlobalTMA.h"

class CRegistraContatopc
{
    public:
        void proCIncluiRegistraContato(TRegistraContato *ptRegistraContato);
        void proCAtualizaRegistraContato(TRegistraContato tRegistraContato);
        bool proCExisteRegistraContato(TRegistraContato tRegistraContato);
};

#endif
