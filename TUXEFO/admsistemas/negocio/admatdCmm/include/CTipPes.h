#ifndef CTipoPessoaH
#define CTipoPessoaH

#include<tuxfw.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

#include "../include/CTipPesItr.h"


class CTipoPessoa : public CTipoPessoaItr
{
	public:
		CTipoPessoa();
		~CTipoPessoa();
		int ListId( char* cidTipoPessoa );
		int ListAll( void );
		int ListSigla( char* csgTipoPessoa);
		void GetXml( char* cNomeTag, XMLGen*xml );
		void GetXmlNatureza( char* cNomeTag, XMLGen*xml );

};

#endif