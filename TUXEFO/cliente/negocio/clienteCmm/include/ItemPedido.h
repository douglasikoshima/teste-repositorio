/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela ItemPedido
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ITEMPEDIDO_H__
#define __ITEMPEDIDO_H__
 
#include <tuxfw.h>
#include "GlobalPedido.h"

//==============================================================================
class CItemPedido : private TuxHelper
{
public:
        CItemPedido(XMLGen *xml_g) {this->xml_g=xml_g;}
        ~CItemPedido() {}

        void buscarLstItemPedidoPorPedDoc(struct DadosParametros *pDadosParametros);
        void buscarLstItemPedidoPorPedDocComp(struct DadosParametros *pDadosParametros);

private:
        CItemPedido() {xml_g=0;}
        XMLGen *xml_g;
};

#endif // __ITEMPEDIDO_H__
