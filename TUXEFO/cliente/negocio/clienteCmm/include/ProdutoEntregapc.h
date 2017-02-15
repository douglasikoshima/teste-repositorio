/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela ProdutoEntrega
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __PRODUTOENTREGAPC_H__
#define __PRODUTOENTREGAPC_H__
 
#include "PedidoNegocio.h"

//==============================================================================
class CProdutoEntregapc
{
public:
        CProdutoEntregapc() {}
        ~CProdutoEntregapc() {}

        int proCbuscarLstProdEntregaPorDocOVenda(struct DadosParametros *pDadosParametros
                                                ,VEC_TPRODUTOENTREGA &vecTProdutoEntrega);
        int proCbuscarProdEntregaPorId(struct DadosParametros *pDadosParametros
                                      ,VEC_TPRODUTOENTREGA &vecTProdutoEntrega);
};

#endif // __PRODUTOENTREGAPC_H__
