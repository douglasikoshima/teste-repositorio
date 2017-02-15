#ifndef CTipoLinhaH
#define CTipoLinhaH

#include <tuxfw.h>
#include "../include/CLinItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CTipoLinha : public CTipoLinhaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoLinha();
		~CTipoLinha();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoLinha );
		//Recupera todos os registros
		int ListAll( void );
		//Lista relacionados
		int RelacaoCttLin( char* cidContato );
		//Lista existentes
		int ExistemCttLin( char* cidContato );
		//Linhas relacionadas ao formulario
		int Relacionadas( char* cidContato );
		//Linhas nao relacionadas ao formulario
		int NaoRelacionadas( char* cidContato );
		//Linhas relacionadas ao formulario filtrado por fase
		int Relacionadas( char* cidContato, char* cidFaseProcesso );
		//Linhas nao relacionadas ao formulario filtrado por fase
		int NaoRelacionadas( char* cidContato, char* cidFaseProcesso );
		
};

#endif

