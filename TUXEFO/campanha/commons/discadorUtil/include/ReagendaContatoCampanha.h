/**
 * 
 * @modulo  Commons
 * @usecase Recarga de ligações no discador via EAI.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:32 $
 **/ 

#include <tuxfw.h>
#include "cDiscadorUtil.h"

class ReagendaContatoCampanha : private cDiscadorUtil
{

	public:
		ReagendaContatoCampanha();
		ReagendaContatoCampanha(DOMNode*dnode, XMLGen*xml_g);		
		
		int   getNPU();
		int   getIdLigacao();
		int   getIdCampanha();
		char* getDataHoraLigacao();

		int getRegistrosContatoLista();
		int addContatoLista(char* contato);
		char* getContatoLista(int idContato);
		void limpaContatoLista();

		void setNPU(int npu);
		void setIdLigacao(int idLigacao);
		void setIdCampanha(int idCampanha);
		void setDataHoraLigacao(char* dataHoraLigacao);
		
		void enviar();

};
