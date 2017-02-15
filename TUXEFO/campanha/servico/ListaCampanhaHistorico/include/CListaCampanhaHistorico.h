#ifndef CListaCampanhaHistoricoH
#define CListaCampanhaHistoricoH

// #include <XMLImpl.h>
#include <tuxfw.h>

#include <CListaCampanhaHistoricoItr.h>

#define ULOG tuxfw_getlogger()->debug

class CListaCampanhaHistorico : public CListaCampanhaHistoricoItr
{
    private:
		int InserirRaiz();
		int InserirNode();
    
	public:
		CListaCampanhaHistorico();
		~CListaCampanhaHistorico();

		int ListId( char* cid, char* dtini, char* dtter );
		int ListAll( void );

		void GetXml( char* cNomeTag, XMLGen*xml );
};

#endif
