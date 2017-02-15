                     
                     

#include<stdlib.h>

#include "../../commons/Propriedade/include/Propriedade.h"
#include "../../commons/queryMacro.h"
#include "../../commons/Log/include/Log.h"


class cAtzContato
{
    public:
        cAtzContato( void );
        void Executa( void );

    private:
        void CarregaParam( void );
        void AtualizaFolhas( void );

};
