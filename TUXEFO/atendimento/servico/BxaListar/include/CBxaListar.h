#ifndef CBaixaH
#define CBaixaH

#include <tuxfw.h>
#include "../include/CBxaListarItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CBaixa : public CBaixaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CBaixa();
		~CBaixa();

        //  Lista Baixas Filhos
        int ListChildren( char * cidBaixaParam, char * cidContatoParam );
        //  Lista Baixa Pai
        int ListOnlyParent( char * cidContatoParam );

        int ListChildrenSemContat( void );

        int ListChildrenIdBaixa( char *idBaixaWrk );
        int ListBxaComContatos( char * cidContatoParam );
        int ListBxa( char * idBaixaParam, char * cidContatoParam );
};

#endif