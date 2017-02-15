/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para acessos a parâmetros de tracking
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __PARAMPEDIDOPC_H__
#define __PARAMPEDIDOPC_H__
 
#include "PedidoNegocio.h"

//==============================================================================
class CParamPedidopc
{
public:
        CParamPedidopc() {}
        ~CParamPedidopc() {}

        int proCbuscarLstNmSOrigemOperLogistico(struct DadosParametros *pDadosParametros);
        int proCbuscarLstURLOperLogistico(struct DadosParametros *pDadosParametros);

private:
};

#endif // __PARAMPEDIDOPC_H__
