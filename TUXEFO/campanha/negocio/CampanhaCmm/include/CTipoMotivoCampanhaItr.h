#ifndef CTipoMotivoCampanhaItrH
#define CTipoMotivoCampanhaItrH

#include <stdlib.h>
#include "TString.h"
#include <string.h>

typedef enum tpTAG  { NUMERICO=0, PERCENTUAL, GRAFICO, GRAFICO2 }; 

struct STTipoMotivoCampanhaRegistro
{
	TString	cidTipoMotivoCampanha;
	TString	csgTipoMotivoCampanha;
	TString	cdsTipoMotivoCampanha;
	TString	cidPessoaUsuarioInclusao;
	TString	cidPessoaUsuarioAlteracao;
	TString	cdtInclusao;
	TString	cdtAlteracao;
	TString	cinAtivo;
	int		tipo; 
};

class CTipoMotivoCampanhaItr
{
	public:
		CTipoMotivoCampanhaItr	();
	   ~CTipoMotivoCampanhaItr	();
		int Primeiro			();
		int Proximo				();
		int Anterior			();
		int Ultimo				();
		int Quantidade			();

		STTipoMotivoCampanhaRegistro* Registro();
		STTipoMotivoCampanhaRegistro* Registro(int);

	private:
		STTipoMotivoCampanhaRegistro* stcrTipoMotivoCampanha;
		int _iQuantidade;
		int _iPosicao;

	protected:
		STTipoMotivoCampanhaRegistro* AddNew();
		void Add( TString , TString , TString , int Tipo = NUMERICO);
		void Add( TString , TString , TString , TString , TString , TString , TString , TString , int Tipo = NUMERICO);
		void ZeraTipoMotivoCampanha();
};

#endif