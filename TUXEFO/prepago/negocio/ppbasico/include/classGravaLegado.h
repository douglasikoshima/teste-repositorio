#ifndef classGravaLegadoH
#define classGravaLegadoH

#include <tuxfw.h>
#include <classGravaLegadoItr.h>
#define STRLENNULL( y ) ( y == NULL ? 0 : strlen( y )  )

class CGravaLegado : public CGravaLegadoItr
{
	public:
		CGravaLegado();
		~CGravaLegado();
		//Metodo recupera pessoas para serem enviadas ao legado
		void PesquisaPorData( char* pszDataIni
                             ,char* pszDataFim
                             ,char* pszUser );
		//Metodo recupera pessoas para serem enviadas ao legado
		void PesquisaPorDataNOK( char* pszDataIni
                                ,char* pszDataFim
                                ,char* pszUser );
		int  PesquisaPorIdPessoa( char* pszIdPessoa
                                 ,char* pszUser );
	private:
		int  iStatus;
		//Metodo envia um item para o legado
		int  EnviaParaLegado( char* pszUser );
		void GravaStatus( char* cStatus
						 ,char* cIdPessoa );

};

#endif