/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para gera��o de liga��es no discador.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/ExcluirUsuario.h"

ExcluirUsuario::ExcluirUsuario() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("ExcUsuarioDisc");
	cDiscadorUtil::setEntity("008");
}

ExcluirUsuario::ExcluirUsuario(DOMNode*dnode, XMLGen*xml_g)  : cDiscadorUtil(dnode, xml_g) {
	cDiscadorUtil::setChamada("ExcUsuarioDisc");
	cDiscadorUtil::setEntity("008");
}	

int   ExcluirUsuario::getNPU()
{
	return cDiscadorUtil::getNPU();
}

char* ExcluirUsuario::getLoginUsuarioCTI()
{
	return cDiscadorUtil::getLoginUsuarioCTI();
}

void ExcluirUsuario::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void ExcluirUsuario::setLoginUsuarioCTI(char* loginUsuarioCTI)
{
	cDiscadorUtil::setLoginUsuarioCTI(loginUsuarioCTI);
}

void ExcluirUsuario::enviar() {

	if (cDiscadorUtil::m_vlDados.npu == -1)
		throw new TuxException( "99E00001", "� necess�rio informar o c�digo do NPU para excluir um usu�rio no discador.");		
	
	if (cDiscadorUtil::m_vlDados.loginUsuarioCTI == -1)
		throw new TuxException( "99E00003", "� necess�rio informar o loginUsuarioCTI para excluir um usu�rio no discador.");		

	cDiscadorUtil::enviar();
	
}
