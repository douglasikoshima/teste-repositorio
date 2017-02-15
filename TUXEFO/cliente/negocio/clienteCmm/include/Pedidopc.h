/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela Pedido
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __PEDIDOPC_H__
#define __PEDIDOPC_H__
 
#define TAM_BLOCO_PEDIDO    9

#include "Pedido.h"
#include "PedidoNegocio.h"

//==============================================================================
class CPedidopc
{
public:
        CPedidopc() {}
        ~CPedidopc() {}

        int procBuscarDetPedidoPorDocumento(struct DadosParametros *pDadosParametros,VEC_TPEDIDO &vecTPedido);
        int procCompletarDetPedidoOrdemPorDocumento(struct DadosParametros *pDadosParametros,VEC_TPEDIDO &vecTPedido);
private:
};

#endif // __PEDIDOPC_H__
