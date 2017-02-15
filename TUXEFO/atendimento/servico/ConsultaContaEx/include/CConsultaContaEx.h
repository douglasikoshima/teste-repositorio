
#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>


class CConsultaContaEx
{

    TuxHelper tx;

    public:
        int iOperacao;
        CConsultaContaEx( DOMNode * dnode );
        int GetOperacao( void );
        int ListaContas( XMLGen* xml_g );
        int ListaLinhas( XMLGen* xml_g );
        int PesquisaConta( XMLGen* xml_g );
        int PesquisaLinhasAssoc( XMLGen* xml_g );

    private:
        long idPessoaPrm;
        char cdContaPrm[101];
        char nrLinhaPrm[15];
        long idAtendimentoPrm;

};

