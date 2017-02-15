/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela Pedido
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __PEDIDO_H__
#define __PEDIDO_H__
 
#include <tuxfw.h>
#include "GlobalPedido.h"

//==============================================================================
class CPedido : private TuxHelper
{
public:
        CPedido(XMLGen *xml_g) {this->xml_g=xml_g;}
        ~CPedido() {}

        void buscarDetPedidoPorDocumento(struct DadosParametros *pDadosParametros);
        void buscarDetPedidoOrdemPorDocumento(struct DadosParametros *pDadosParametros);

private:
        CPedido() {xml_g=0;}
        XMLGen *xml_g;
};

#endif // __PEDIDO_H__
