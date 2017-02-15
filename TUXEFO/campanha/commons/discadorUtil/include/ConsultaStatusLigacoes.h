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

class ConsultaStatusLigacoes : private cDiscadorUtil
{

	public:
		ConsultaStatusLigacoes();
		ConsultaStatusLigacoes(DOMNode*dnode, XMLGen*xml_g);		
		
		int   getNPU();
		char* getDataConsultaInicio();
		char* getDataConsultaFim();         

		int getRegistrosCampanhaLista();
		int addCampanhaLista(char* contato);
		char* getCampanhaLista(int idContato);
		void limpaCampanhaLista();

		void setNPU(int npu);
		void setDataConsultaInicio(char* dataHoraInicio);
		void setDataConsultaFim(char* dataHoraFim);

		void enviar();

};
