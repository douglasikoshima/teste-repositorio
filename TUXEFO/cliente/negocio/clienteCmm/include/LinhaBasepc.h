///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase LinhaBase
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef LINHABASEPC
#define LINHABASEPC

#include "Global.h"

class CLinhaBasepc
{
public:
    bool proCBuscaLinhaBase(TLinhaBase* ptLinhaBase);
    void proCInsereLinhaBase(TLinhaBase* ptLinhaBase);
    void proCAtualizaLinhaBase(TLinhaBase* ptLinhaBase);
	bool proCBuscaAreaServico(TLinhaBase* ptLinhaBase, char *DDD);
};

#endif
