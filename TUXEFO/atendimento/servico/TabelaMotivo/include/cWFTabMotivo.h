/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:34 $
 **/

#ifndef CWFTABMOTIVO
	#define CWFTABMOTIVO

#include <tuxfw.h>
// #include "stTabMotivo.h"

class cWFTabMotivo
{
	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
        cWFTabMotivo() { entrada=0; saida=0; };

	public:
        bool pesqTabelaMotivo(DOMNode* entrada,XMLGen* saida);
        bool pesqMotivoAtividade(DOMNode* entrada,XMLGen* saida);
        bool pesqMotivoAtividade(int _idFase,DOMNode* entrada,XMLGen* saida);
	private:
		void carregaDados();
};

#endif
