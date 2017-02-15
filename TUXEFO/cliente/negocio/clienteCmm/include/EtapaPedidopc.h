/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela EtapaPedido
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ETAPAPEDIDOPC_H__
#define __ETAPAPEDIDOPC_H__
 
#include "PedidoNegocio.h"

//==============================================================================
class CEtapaPedidopc
{
public:
        CEtapaPedidopc() {}
        ~CEtapaPedidopc() {}

        int proCbuscarLstEtapaPedidoPorPedDoc(struct DadosParametros *pDadosParametros
                                             ,VEC_TETAPAPEDIDO &vecTEtapaPedido);

        int proCbuscarLstPedidosPorDocumento(struct DadosParametros *pDadosParametros
                                            ,VEC_TETAPAPEDIDOPORDOC &TEtapaPedidoPorDoc);
private:
};

#endif // __ETAPAPEDIDOPC_H__
