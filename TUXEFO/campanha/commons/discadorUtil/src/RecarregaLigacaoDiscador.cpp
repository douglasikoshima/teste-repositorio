/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para geração de ligações no discador.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/RecarregaLigacaoDiscador.h"

RecarregaLigacaoDiscador::RecarregaLigacaoDiscador() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("RecLigDiscador");
	cDiscadorUtil::setEntity("006");
}

RecarregaLigacaoDiscador::RecarregaLigacaoDiscador(DOMNode*dnode, XMLGen*xml_g) : cDiscadorUtil(dnode, xml_g) {
	cDiscadorUtil::setChamada("RecLigDiscador");
	cDiscadorUtil::setEntity("006");
}	

int   RecarregaLigacaoDiscador::getNPU()
{
	return cDiscadorUtil::getNPU();
}

int   RecarregaLigacaoDiscador::getIdLigacao()
{
	return cDiscadorUtil::getIdLigacao();
}

void RecarregaLigacaoDiscador::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void RecarregaLigacaoDiscador::setIdLigacao(int idLigacao)
{
	cDiscadorUtil::setIdLigacao(idLigacao);
}

void RecarregaLigacaoDiscador::enviar() {

	if (cDiscadorUtil::m_vlDados.npu == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do NPU para recarregar uma ligação.");		
	
	if (cDiscadorUtil::m_vlDados.idLigacao == -1)
		throw new TuxException( "99E00002", "É necessário informar o idLigacao para recarregar uma ligação.");		

	cDiscadorUtil::enviar();
	
}
