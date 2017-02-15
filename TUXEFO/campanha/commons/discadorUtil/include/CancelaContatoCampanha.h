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

class CancelaContatoCampanha : private cDiscadorUtil
{

	public:
		CancelaContatoCampanha();
		CancelaContatoCampanha(DOMNode*dnode, XMLGen*xml_g);		
		
		int   getNPU();
		int   getIdLigacao();
		int   getIdCampanha();

		void setNPU(int npu);
		void setIdLigacao(int idLigacao);
		void setIdCampanha(int idCampanha);

		void enviar();

};
