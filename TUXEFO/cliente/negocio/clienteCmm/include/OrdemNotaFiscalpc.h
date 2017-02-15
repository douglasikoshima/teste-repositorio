/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas DAO básicas para a tabela OrdemNotaFiscal
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ORDEMNOTAFISCALPC_H__
#define __ORDEMNOTAFISCALPC_H__
 
#include "PedidoNegocio.h"

//==============================================================================
class COrdemNotaFiscalpc
{
public:
        COrdemNotaFiscalpc() {}
        ~COrdemNotaFiscalpc() {}

        int procBuscarLstOrdemNotaFiscal(struct DadosParametros *pDadosParametros
                                        ,VEC_TORDEMNOTAFISCAL &vecTOrdemNotaFiscal);

        int procBuscarValorTotalParcelas(struct DadosParametros *pDadosParametros
                                        ,VEC_TORDEMNOTAFISCAL &vecTOrdemNotaFiscal);
private:
};

#endif // __ORDEMNOTAFISCALPC_H__
