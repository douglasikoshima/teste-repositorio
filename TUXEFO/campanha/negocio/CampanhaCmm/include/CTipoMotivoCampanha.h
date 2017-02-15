#ifndef CTipoMotivoCampanhaH
#define CTipoMotivoCampanhaH

#include <tuxfw.h>

#include "CTipoMotivoCampanhaItr.h"
#include "SRelatorioGerenciamento.h"
#include "SRelatorioCampanha.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CTipoMotivoCampanha : public CTipoMotivoCampanhaItr
{
	public:
		CTipoMotivoCampanha();
		~CTipoMotivoCampanha();

    private:
		int		InserirRaiz();
		int		InserirNode();

	public:
		int		ListId ( char* );
		int		ListAll( void );		
		int		Insert ( char* , char* , char* , char* , char* , char* , char*  );
		int		Update ( char* , char* , char* , char* , char* , char* , char* , char* );
		void	GetXml ( char* , XMLGen* );

		//Lista de ids dos motivos do relatório
		int		ListRelGerMot  ( char * );
		void	GetXmlRelGerMot( XMLGen*, struct SRelatorioCampanha*  );
		void	GetXmlRelGerDP ( char* , XMLGen*, struct SRelatorioCampanha*  );
		char*	FiltroRelatorioOperador( char* , char * );
		char*	FiltroRelatorioGerenciamento( char*  );
		long	CountByText    ( char* , char *, struct SRelatorioCampanha* );
		long    getContatosEfetivos( struct SRelatorioCampanha* sRelatorioCampanha );

	private:
		// Calcula a quantidade de um determinado motivo 
		float	CountByIdAtendimentoCampanha(	char* , struct SRelatorioCampanha*  );
		float	MatematicaSQRT( float );
};
#endif
