/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela OrdemVenda
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ORDEMVENDAPC_H__
#define __ORDEMVENDAPC_H__
 
#include "PedidoNegocio.h"

//==============================================================================
class COrdemVendapc
{
public:
        COrdemVendapc() {}
        ~COrdemVendapc() {}

        int proCbuscarOrdVdaComparacao(struct DadosParametros *pDadosParametros
                                      ,VEC_TORDEMVENDA &vecTOrdemVenda
                                      ,bool preencherLstProdEntrega);

        int proCbuscarOrdVendaPorPedDoc(struct DadosParametros *pDadosParametros
                                       ,VEC_TORDEMVENDA &vecTOrdemVenda
                                       ,bool preencherLstProdEntrega=true);

        int procBuscarDetOVendaPorDocumento(struct DadosParametros *pDadosParametros,
                                           VEC_TPEDIDO &vecTPedido);
private:
};

#endif // __ORDEMVENDAPC_H__
