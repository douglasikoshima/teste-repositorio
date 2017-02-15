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

class AssociaUsuarioCampanha : public cDiscadorUtil
{

	public:
		AssociaUsuarioCampanha();
		AssociaUsuarioCampanha(DOMNode*dnode, XMLGen*xml_g);		
		
		int   getNPU();
		int   getIdCampanha();
		int   getIdUsuario();
		char* getLoginUsuarioCTI();

		void setNPU(int npu);
		void setIdCampanha(int idCampanha);
		void setIdUsuario(int idUsuario);
		void setLoginUsuarioCTI(char* loginUsuarioCTI);

		void enviar();

};
