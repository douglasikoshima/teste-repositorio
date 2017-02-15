

#include "../include/cAtzContato.h"


// Prototipos
extern void proCAtualizaFolhas( unsigned long idGrupoPrm, unsigned long idUsuarioPrm  );
extern void proCCarregaPrm(void);


cAtzContato::cAtzContato( void )
{
    Executa();
}



void cAtzContato::Executa( void )
{
    proCCarregaPrm();
}

