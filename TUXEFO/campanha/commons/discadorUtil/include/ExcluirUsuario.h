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

class ExcluirUsuario : private cDiscadorUtil
{

	public:
		ExcluirUsuario();
		ExcluirUsuario(DOMNode*dnode, XMLGen*xml_g);		
		
		int   getNPU();
		char* getLoginUsuarioCTI();

		void setNPU(int npu);
		void setLoginUsuarioCTI(char* loginUsuarioCTI);

		void enviar();

};
