#ifndef _IcWFAtdTratamentoCri_
#define _IcWFAtdTratamentoCri_
/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/ 
 
#include <tuxfw.h>
#include "stWFAtdTratamentoCri.h"

class cWFAtdTratamentoCri
{

	st_AtdTratamentoCri	m_stDados;
	st_vlAtdTratamentoCri	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtdTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* indicator, XMLGen*xml_g);
		cWFAtdTratamentoCri(DOMNode*dnode, XMLGen*xml_g);
        	~cWFAtdTratamentoCri() {}
		long  incluirAtendimentoCri();
		long  incluirTratamentoCri();
		long  alterarAtendimentoGrupoAtualCri();
		long  alterarAtendimentoGrupoCri();
		long  alterarGrupoCri();
		long  alterarAtendimentoCri();
		long  alterarTratamentoCri();
		long  alterarUsuarioDevolucaoCri();
		long  alterarGrupoDevolucaoCri();
		bool getGrupoCri();
		bool getTratamentoCri();
		int getGrupo();
		int getPessoaLinhaHistorico();

	private:
		void carregaDados();

};
#endif
