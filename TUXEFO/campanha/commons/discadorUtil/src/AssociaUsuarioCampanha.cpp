/**
 * 
 * @modulo  Commons
 * @usecase Prototipo para geração de ligações no discador.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/AssociaUsuarioCampanha.h"

AssociaUsuarioCampanha::AssociaUsuarioCampanha() : cDiscadorUtil() {
	cDiscadorUtil::setChamada("AssUsuarioCamp");
	cDiscadorUtil::setEntity("001");
}

AssociaUsuarioCampanha::AssociaUsuarioCampanha(DOMNode*dnode, XMLGen*xml_g)  : cDiscadorUtil(dnode, xml_g) {
	cDiscadorUtil::setChamada("AssUsuarioCamp");
	cDiscadorUtil::setEntity("001");
}

int   AssociaUsuarioCampanha::getNPU()
{
	return cDiscadorUtil::getNPU();
}

int   AssociaUsuarioCampanha::getIdCampanha()
{
	return cDiscadorUtil::getIdCampanha();
}

int   AssociaUsuarioCampanha::getIdUsuario()
{
	return cDiscadorUtil::getIdUsuario();
}

char* AssociaUsuarioCampanha::getLoginUsuarioCTI()
{
	return cDiscadorUtil::getLoginUsuarioCTI();
}


void AssociaUsuarioCampanha::setNPU(int npu)
{
	cDiscadorUtil::setNPU(npu);
}

void AssociaUsuarioCampanha::setIdCampanha(int idCampanha)
{
	cDiscadorUtil::setIdCampanha(idCampanha);
}

void AssociaUsuarioCampanha::setIdUsuario(int idUsuario)
{
	cDiscadorUtil::setIdUsuario(idUsuario);
}

void AssociaUsuarioCampanha::setLoginUsuarioCTI(char* loginUsuarioCTI)
{
	cDiscadorUtil::setLoginUsuarioCTI(loginUsuarioCTI);
}

void AssociaUsuarioCampanha::enviar() {

	if (cDiscadorUtil::m_vlDados.npu == -1)
		throw new TuxException( "99E00001", "É necessário informar o código do NPU para associar um usuário a uma campanha.");		
	
	if (cDiscadorUtil::m_vlDados.idCampanha == -1)
		throw new TuxException( "99E00002", "É necessário informar o idCampanha para associar um usuário a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.idUsuario == -1)
		throw new TuxException( "99E00003", "É necessário informar o idUsuario para associar um usuário a uma campanha.");		

	if (cDiscadorUtil::m_vlDados.loginUsuarioCTI == -1)
		throw new TuxException( "99E00004", "É necessário informar o loginUsuarioCTI para associar um usuário a uma campanha.");		

	cDiscadorUtil::enviar();
	
}
