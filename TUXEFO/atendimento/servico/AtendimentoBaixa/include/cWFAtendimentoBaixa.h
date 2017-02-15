/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:31 $
 **/

#include <tuxfw.h>
#include "stWFAtendimentoBaixa.h"

class cWFAtendimentoBaixa
{

	st_AtendimentoBaixa	   m_stDados;
	st_vlAtendimentoBaixa	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
        cWFAtendimentoBaixa() {entrada=0;saida=0;}
		cWFAtendimentoBaixa(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();
		int obtemBLista();
		int obtemBContato();
		int obtemCLista();
		int obtemCFolha();
		int obtemCRaiz();
		int obtemAtdHrq();
		int obtemAtdCtHrq();
        int obtemAtdCtHrq(int idContato,XMLGen* saida);
        int obtemAtdHrq(int idBaixa,XMLGen* saida);

	private:
		void carregaDados();

};
