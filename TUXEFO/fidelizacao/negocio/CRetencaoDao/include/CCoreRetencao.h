//////////////////////////////////////////////////////////////////////
// CCoreRetencao.h: interface for the CRelatorioGerenciamento class.
//////////////////////////////////////////////////////////////////////

#include <tuxfw.h>

class CCoreRetencao 
{
public:
	int Executar(DOMNode*,char *idUser);
	int RegistraContato(DOMNode*,char *idUser);
	int GeraMsgRetorno(XMLGen*);

private:
	//variaveis para retencao
	char m_idRetencao[21+1];				//é propagado por várias tabelas
	char m_idOfertaAceita[21+1];			//mantem a oferta aceita para propagar na CARACTERISTICAOFERTAREALIZADA.	
	char m_idTipoEncerramento[21+1];		//idtipoencerramento para mantermos os procedimentos atuaiz para retorno.	
	//variaveis para Registro de Contato
	char m_idChamadaTelefonica[21+1];		//valor fixo encaminhamento
	char m_idGrupoAbertura[21+1];			//valor do grupo	encaminhamento
	char m_inResponsavelAbertura[21+1];		//cliente ou usuario	encaminhamento
	char m_tpOperacao[21+1];				//fechamento imediato ou abertura
	char m_idProcedencia[21+1];				//fixo 1 telefone encaminhamento
	char m_idCanal[21+1];					//fixo 16 RETENCAO encaimhamento
	char m_idSegmentacao[21+1];				//segmentacao da linha encaminhamento
	char m_idTipoCarteira[21+1];			//carteira do cliente encaimnhamento
	char m_idTipoLinha[21+1];				//tipo da linha (Sempre será POS, mas podera ser GSM OU CDMA); encaminhamento
	char m_inTipoPessoa[21+1];				//indicador do tipo de pessoa; encaminhamento
	char m_idUfOperadora[21+1];				//operadora da linha encaminhamento
	char m_idContato[21+1];					//contato a ser registrado
	char m_idPessoa[21+1];					//idPessoaDepara da linha
	char m_AtendimentoTipoComunicacaoVO[2]; //valor fixo 1--sem função aparente.
	char m_idConta[21+1];					//conta daquele Cliente
	char m_idTipoComunicacao[2];			//9 SMS retorno de SMS
	char m_nrTelefone						//Telefone do cliente em formato (99)9999-9999

};



int CCoreRetencao::Executar(DOMNode *pdnode,char *idUser )
{
	
	
	return 1;
}

int CCoreRetencao::RegistraContato(DOMNode*,char *idUser)
{

	return 1;
}

int CCoreRetencao::GeraMsgRetorno(XMLGen*)
{

	return 1;
}


