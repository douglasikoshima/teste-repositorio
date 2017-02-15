/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:38 $
 **/ 

#include <tuxfw.h>

#include "../../../commons/Collection/include/Collection.h"
#include "stWFCondicaoAparicao.h"

class cWFCondicaoAparicao
{

	st_CondicaoAparicao	m_stDados;
	st_vlCondicaoAparicao	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
        cWFCondicaoAparicao() {entrada=0;saida=0;}
		cWFCondicaoAparicao(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();
		bool ObtemWFCndAcoes();
        bool ObtemWFCndAcoes(st_CondicaoAparicao* dados, st_vlCondicaoAparicao* status);
		bool ObtemWFCnd();
		
	private:
		void carregaDados();

};
