/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela Pedido
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __PRODUTOENTREGA_H__
#define __PRODUTOENTREGA_H__
 
#include <tuxfw.h>
#include "GlobalPedido.h"

//==============================================================================
class CProdutoEntrega : private TuxHelper
{
public:
        CProdutoEntrega(XMLGen *xml_g) {this->xml_g=xml_g;}
        ~CProdutoEntrega() {}

private:
        CProdutoEntrega() {xml_g=0;}
        XMLGen *xml_g;
};

#endif // __PRODUTOENTREGA_H__
