#ifndef CTipoApresentacaoPerguntaH
#define CTipoApresentacaoPerguntaH

#include <tuxfw.h>
#include "../include/CTapItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CTipoApresentacaoPergunta : public CTipoApresentacaoPerguntaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoApresentacaoPergunta();
		~CTipoApresentacaoPergunta();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoApresentacaoPergunta );
		//Recupera todos os registros
		int ListAll( void );
};

#endif

