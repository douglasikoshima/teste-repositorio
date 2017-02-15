//Definicao Global
#define SKLINSALTCPP

#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CGrupoSkill.h"
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

DECLARE_TUXEDO_SERVICE(SKLLSTREGRAS);

void implSKLLSTREGRAS::Execute(DOMNode*XMLIn, XMLGen*XMLOut)
{
    ULOG_START("implSKLLSTREGRAS::Execute()");
    
    CGrupoSkill oGrupoSkill;
    CSafePointer oSafePointer;

	char* parm = oSafePointer.getTag( XMLIn, "idSkill", 0 );
	
	if( !parm )
	{
	  throw new TuxBasicSvcException( "60E0001", "idSkill está nulo" );
	}
    
	XMLOut->createTag("RegrasEncaminhamentoVO");
	XMLOut->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
	XMLOut->createTag( "RegrasEncaminhamentoDisponivelVO" );
	
	if( !oGrupoSkill.ConsultaTipoCarteiraSkill(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0002","=> CONSULTA: CARTEIRIZACAO");
	}
	
	if( !oGrupoSkill.ConsultaProcedenciaSkill(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0003","=> CONSULTA: PROCEDENCIA");
	}
	
	if( !oGrupoSkill.ConsultaSegmentacaoSkill(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0004","=> CONSULTA: SEGMENTACAO");
	}
	
	if( !oGrupoSkill.ConsultaTipoLinhaSkill(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0005","=> CONSULTA: TIPOCLIENTE");
	}
	
	if( !oGrupoSkill.ConsultaTipoPessoaSkill(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0006","=> CONSULTA: TIPOPESSOA");
	}
	
	if( !oGrupoSkill.ConsultaTipoRelacionamentoSkill(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0007","=> CONSULTA: TIPORELACIONAMENTO");
	}
	
	if( !oGrupoSkill.ConsultaGrupoSkill(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0008","=> CONSULTA: GRUPOABERTURAGRUPO");
	}
	
	if( !oGrupoSkill.ConsultaCanalSkill(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0009","=> CONSULTA: CANALGRUPO");
	}
	
	if( !oGrupoSkill.ConsultaRegionalSkill(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0010","=> CONSULTA: UFOPERADORAGRUPO");
	}
	
	XMLOut->closeTag();
	
	XMLOut->createTag( "RegrasEncaminhamentoSelecionadoVO" );
	
	
	if( !oGrupoSkill.ConsultaTipoCarteiraSkillSelecionado(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0010","=> CONSULTA: TIPOCARTEIRA SEL");
	}
	
	if( !oGrupoSkill.ConsultaProcedenciaSkillSelecionado(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0010","=> CONSULTA: PROCEDENCIA SEL");
	}
	
	if( !oGrupoSkill.ConsultaSegmentacaoSkillSelecionado(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0011","=> CONSULTA: SEGMENTACAO SEL");
	}
	
	if( !oGrupoSkill.ConsultaTipoLinhaSkillSelecionado(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0012","=> CONSULTA: TIPOCLIENTE SEL");
	}
	
	if( !oGrupoSkill.ConsultaTipoPessoaSkillSelecionado(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0013","=> CONSULTA: TIPOPESSOA SEL");
	}
	
	if( !oGrupoSkill.ConsultaTipoRelacionamentoSkillSelecionado(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0014","=> CONSULTA: TIPORELACIONAMENTO SEL");
	}
	
	if( !oGrupoSkill.ConsultaGrupoSkillSelecionado( parm,XMLOut ))
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0015","=> CONSULTA: GRUPOABERTURAGRUPO SEL");
	}
	
	if( !oGrupoSkill.ConsultaCanalSkillSelecionado(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0016","=> CONSULTA: CANALGRUPOSEL");
	}
	
	if( !oGrupoSkill.ConsultaRegionalSkillSelecionado(parm,XMLOut) )
	{
	  XMLString::release(&parm);
	  throw new TuxBasicSvcException("60E0010","=> CONSULTA: UFOPERADORAGRUPO SEL");
	}

	setStatusCode("60I0000", "Procedimento encerrado com sucesso");
	
	ULOG_END("implSKLLSTREGRAS::Execute()");
}
