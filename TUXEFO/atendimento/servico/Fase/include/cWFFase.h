/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:02 $
 **/ 

#include <tuxfw.h>
#include "stWFFase.h"
#include "../../../commons/Collection/include/Collection.h"

class cWFFase
{
	public:
        cWFFase() {entrada=0;saida=0;}
		cWFFase(DOMNode*dnode, XMLGen*xml_g);
        ~cWFFase() {}

	public:
		bool incluir();
		bool consultar();
        bool consultar(st_Fase* dados, st_vlFase* status, XMLGen* saida);
        bool obterFases(Collection &collectionFases);
		int alterar();
		int excluir();

	private:
		void carregaDados();

	private:
	    st_Fase	  m_stDados;
	    st_vlFase m_vlDados;

	    DOMNode* entrada;
	    XMLGen*  saida;

	    TuxHelper tx;
};
