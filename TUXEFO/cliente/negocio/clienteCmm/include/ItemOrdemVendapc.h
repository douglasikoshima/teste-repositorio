/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela ItemOrdemVenda
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ITEMORDEMVENDAPC_H__
#define __ITEMORDEMVENDAPC_H__
 
#include "PedidoNegocio.h"

//==============================================================================
class CItemOrdemVendapc
{
public:
        CItemOrdemVendapc() {}
        ~CItemOrdemVendapc() {}

        int proCbuscarLstItemOrVdaPorPedDoc(struct DadosParametros *pDadosParametros
                                           ,VEC_TITEMORDEMVENDA &VecTItemOrdemVenda);

        int proCbuscarLstItemOrVdaSapPorPedDoc(struct DadosParametros *pDadosParametros
                                              ,VEC_TITEMORDEMVENDASAP &vecTItemOrdemVendaSap);
private:
};

#endif // __ITEMORDEMVENDAPC_H__
