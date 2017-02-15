/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para geração de ligações no discador.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/IncluirUsuario.h"

IncluirUsuario::IncluirUsuario() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("IncUsuarioDisc");
	cDiscadorUtil::setEntity("007");
}

IncluirUsuario::IncluirUsuario(DOMNode*dnode, XMLGen*xml_g) : cDiscadorUtil(dnode, xml_g) {
	cDiscadorUtil::setChamada("IncUsuarioDisc");
	cDiscadorUtil::setEntity("007");
}	

int IncluirUsuario::getNPU()
{
	return cDiscadorUtil::getNPU();
}

char* IncluirUsuario::getLoginUsuarioCTI()
{
	return cDiscadorUtil::getLoginUsuarioCTI();
}

char* IncluirUsuario::getLoginPabx()
{
	return cDiscadorUtil::getLoginPabx();
}

void IncluirUsuario::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void IncluirUsuario::setLoginUsuarioCTI(char* loginUsuarioCTI)
{
	cDiscadorUtil::setLoginUsuarioCTI(loginUsuarioCTI);
}

void IncluirUsuario::setLoginPabx(char* loginPabx)
{
	cDiscadorUtil::setLoginPabx(loginPabx);
}

void IncluirUsuario::enviar() {

	if (cDiscadorUtil::m_vlDados.npu == -1)
	{
		tuxfw_getlogger()->debug("O parametro obrigatorio NPU nao foi encontrado na inclusao do usuario no discador...\n\n");
		throw TuxException( "99E00001", "É necessário informar o código do NPU para incluir um usuário no discador.");		
	}
	
	if (cDiscadorUtil::m_vlDados.loginUsuarioCTI == -1)
	{
		tuxfw_getlogger()->debug("O parametro obrigatorio loginUsuarioCTI nao foi encontrado na inclusao do usuario no discador...\n\n");
		throw TuxException( "99E00003", "É necessário informar o loginUsuarioCTI para incluir um usuário no discador.");		
	}

	tuxfw_getlogger()->debug("Todos parametros obrigatorios enviados, vai chamar o metodo de envio dos dados...\n\n");
	cDiscadorUtil::enviar();
	tuxfw_getlogger()->debug("Executou o envido dos dados...\n\n");
	
}
