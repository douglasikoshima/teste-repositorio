/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela ItemPedido
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ITEMPEDIDOPC_H__
#define __ITEMPEDIDOPC_H__
 
#include "PedidoNegocio.h"

//==============================================================================
class CItemPedidopc
{
public:
        CItemPedidopc() {}
        ~CItemPedidopc() {}

        int proCbuscarLstItemPedidoPorPedDoc(struct DadosParametros *pDadosParametros, VEC_TITEMPEDIDO &vecTItemPedido);
        int proCbuscarLstItemOrdemVendaPorPedDoc(struct DadosParametros *pDadosParametros, VEC_TITEMPEDIDO &vecTItemPedido);

private:
};

#endif // __ITEMPEDIDOPC_H__
