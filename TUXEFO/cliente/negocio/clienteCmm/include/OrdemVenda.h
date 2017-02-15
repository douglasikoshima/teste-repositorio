/**
 * @modulo  Clientes
 * @usecase Aba Tracking
 * @remark  Rotinas básicas para a tabela OrdemVenda
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/

#ifndef __ORDEMVENDA_H__
#define __ORDEMVENDA_H__
 
#include <tuxfw.h>
#include "GlobalPedido.h"

//==============================================================================
class COrdemVenda : private TuxHelper
{
public:
        COrdemVenda(XMLGen *xml_g) {this->xml_g=xml_g;}
        ~COrdemVenda() {}

        void buscarOrdVendaPorPedDoc(struct DadosParametros *pDadosParametros);
        void buscarOrdVdaComparacao(struct DadosParametros *pDadosParametros);

private:
        COrdemVenda() {xml_g=0;}
        XMLGen *xml_g;
};

#endif // __ORDEMVENDA_H__
