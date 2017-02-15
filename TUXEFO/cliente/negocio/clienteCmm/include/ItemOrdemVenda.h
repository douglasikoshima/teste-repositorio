/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela ItemOrdemVenda
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ITEMORDEMVENDA_H__
#define __ITEMORDEMVENDA_H__
 
#include <tuxfw.h>
#include "GlobalPedido.h"

//==============================================================================
class CItemOrdemVenda : private TuxHelper
{
public:
        CItemOrdemVenda(XMLGen *xml_g) {this->xml_g=xml_g;}
        ~CItemOrdemVenda() {}

        void buscarLstItemOrVdaPorPedDoc(struct DadosParametros *pDadosParametros);

private:
        CItemOrdemVenda() {xml_g=0;}
        XMLGen *xml_g;
};

#endif // __ITEMORDEMVENDA_H__
