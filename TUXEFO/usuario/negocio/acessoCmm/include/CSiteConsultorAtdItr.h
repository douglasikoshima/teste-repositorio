/**
 * 
 * @modulo  AdmSistemas
 * @usecase listas de sites de atendentes (OS MeuCliente)
 * @author  Cassio Garcia
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:41:29 $
 **/

#ifndef CSiteConsultorAtdItrH
#define CSiteConsultorAtdItrH

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#include <stdlib.h>
#include <string.h>

struct STSiteConsultorAtdRegistro
{
    char cidSiteConsultorAtd[10+1];
    char cdsSiteConsultorAtd[255+1];
};

class CSiteConsultorAtdItr
{
    public:
        CSiteConsultorAtdItr();
        ~CSiteConsultorAtdItr();
        int Primeiro( void );
        int Proximo( void );
        int Anterior( void );
        int Ultimo( void );
        int Quantidade( void );
        STSiteConsultorAtdRegistro* Registro( void );
        STSiteConsultorAtdRegistro* Registro(int nPosicao);

    private:
        STSiteConsultorAtdRegistro* stcrSiteConsultorAtd;
        int _iQuantidade;
        int _iPosicao;
        int _tamBloco;

    protected:
        void Add(
                    const char* cidSiteConsultorAtd,
                    const char* cdsSiteConsultorAtd
                );

        void ZeraSiteConsultorAtd( void );

};

#endif
