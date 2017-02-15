/**
 * 
 * @modulo  AdmSistemas
 * @usecase listas de perfis de atendentes (OS MeuCliente)
 * @author  Cassio Garcia
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:41:29 $
 **/

#ifndef CFornecedorConsultorAtdItrH
#define CFornecedorConsultorAtdItrH

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#include <stdlib.h>
#include <string.h>

struct STFornecedorConsultorAtdRegistro
{
    char cidFornecedorConsultorAtd[10+1];
    char cdsFornecedorConsultorAtd[255+1];
};

class CFornecedorConsultorAtdItr
{
    public:
        CFornecedorConsultorAtdItr();
        ~CFornecedorConsultorAtdItr();
        int Primeiro( void );
        int Proximo( void );
        int Anterior( void );
        int Ultimo( void );
        int Quantidade( void );
        STFornecedorConsultorAtdRegistro* Registro( void );
        STFornecedorConsultorAtdRegistro* Registro(int nPosicao);

    private:
        STFornecedorConsultorAtdRegistro* stcrFornecedorConsultorAtd;
        int _iQuantidade;
        int _iPosicao;
        int _tamBloco;

    protected:
        void Add(
                    const char* cidFornecedorConsultorAtd,
                    const char* cdsFornecedorConsultorAtd
                );

        void ZeraFornecedorConsultorAtd( void );

};

#endif
