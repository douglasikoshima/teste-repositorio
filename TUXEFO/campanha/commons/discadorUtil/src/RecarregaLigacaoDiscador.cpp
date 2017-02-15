/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para gera��o de liga��es no discador.
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
		throw new TuxException( "99E00001", "� necess�rio informar o c�digo do NPU para recarregar uma liga��o.");		
	
	if (cDiscadorUtil::m_vlDados.idLigacao == -1)
		throw new TuxException( "99E00002", "� necess�rio informar o idLigacao para recarregar uma liga��o.");		

	cDiscadorUtil::enviar();
	
}
