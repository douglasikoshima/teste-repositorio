#ifndef CLinhaBaseH
#define CLinhaBaseH

#include <tuxfw.h>
#include <CLinhaBaseItr.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CLinhaBase : public CLinhaBaseItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CLinhaBase();
		~CLinhaBase();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidLinhaBase );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidAreaRegistro,
				char* cnrLinha,
				char* cnrMim,
				char* cnrDigitoLinha,
				char* cidEstadoLinha,
				char* csqSincronismoEstado,
				char* ctsSincronismoEstado,
				char* cdtEstadoLinha,
				char* cdsMotivoEstado,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidLinhaBase,
				char* cidAreaRegistro,
				char* cnrLinha,
				char* cnrMim,
				char* cnrDigitoLinha,
				char* cidEstadoLinha,
				char* csqSincronismoEstado,
				char* ctsSincronismoEstado,
				char* cdtEstadoLinha,
				char* cdsMotivoEstado,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidLinhaBase );
		//Valida Linha PrePago
		int ValidaLinhaPrePago( char* ccdAreaRegistro,
		                        char* cnrLinha );
};

#endif

