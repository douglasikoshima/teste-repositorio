/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela EtapaPedido
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ETAPAPEDIDO_H__
#define __ETAPAPEDIDO_H__
 
#include <tuxfw.h>
#include "GlobalPedido.h"

//==============================================================================
class CEtapaPedido : private TuxHelper
{
public:
        CEtapaPedido(XMLGen *xml_g) {this->xml_g=xml_g;}
        ~CEtapaPedido() {}

        void buscarLstEtapaPedidoPorPedDoc(struct DadosParametros *pDadosParametros);
        int buscarLstPedidosPorDocumento(struct DadosParametros *pDadosParametros);

private:
        CEtapaPedido() {xml_g=0;}
        XMLGen *xml_g;
};

#endif // __ETAPAPEDIDO_H__
