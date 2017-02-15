/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela OrdemNotaFiscal
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ORDEMNOTAFISCAL_H__
#define __ORDEMNOTAFISCAL_H__
 
#include <tuxfw.h>
#include "GlobalPedido.h"

//==============================================================================
class COrdemNotaFiscal : private TuxHelper
{
public:
        COrdemNotaFiscal(XMLGen *xml_g) {this->xml_g=xml_g;}
        ~COrdemNotaFiscal() {}

private:
        COrdemNotaFiscal() {xml_g=0;}
        XMLGen *xml_g;
};

#endif // __ORDEMNOTAFISCAL_H__
