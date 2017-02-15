/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela PedidoCarga
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __PEDIDOCARGA_H__
#define __PEDIDOCARGA_H__
 
#include "GlobalPedido.h"

//==============================================================================
typedef vector<TPedidoCarga> VEC_PEDIDOCARGA;

//==============================================================================
class PedidoCarga : private TuxHelper
{
public:

private:
        TPedidoCarga tPedidoCarga;
        VEC_PEDIDOCARGA vecPedidoCarga;
};

#endif // __PEDIDOCARGA_H__
