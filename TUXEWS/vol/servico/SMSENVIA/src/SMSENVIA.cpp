/*****************************************************************************
 *
 * Modulo:    SMSENVIA
 * Arquivo:   SMSENVIA.cpp
 * Proposito: Receber uma mensagem para persistencia dentro do FO de uma mensagem de SMS
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 24/10/2005  C_EDMartins           Criacao
 *
 ****************************************************************************/
/*****************************************************************************
 * @modulo  Interface
 * @usecase SMS
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: akurak $ - $Date: 2007/07/12 14:36:14 $
 ****************************************************************************/
/**************************************************************************
 * Notas: Estes servico trabalha em conjunto com uma instancia de MBO com
 *        o nome de 'SMSSend'. O 'SMSSend' recebe um XML IN, que eh repassado
 *        para este servico que vai realizaa a persistencia e gerar um identificador
 *        unico para cada SMS. Na sequencia ele devolve a mensagem para o MBO
 *        que a coloca em uma FILA TIBCO.
 *
 *        Este SMS era receber posteriormente, de uma fila TIBCO, uma mensagem
 *        com o status da mensagem, o servico que realizar esta atualizacao tem
 *        o nome de 'SMSSTATUS'
 *
 *        Servicos relacionados 'SMSSTATUS'
 *        Instancias do MBO relacionadas: 'SMSSend'
 *
 **************************************************************************/

//Definicao Global
#define SMSENVIACPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include <CSms.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SMSENVIA);

/**************************************************************************
 *  Funcao de Negocios:  SMSENVIA
 *
 *  Descricao: chama funcao de negocios
 *
 *  Parametros de Entrada:
 *  DOMNode		*dnEntrada   Nodos da Arvore DOM (DONNode)
 *
 *  Parametros de Saida:
 *  XMLGen		*xmlSaida    XMLGen para construcao de XML de saida
 *
 *************************************************************************/
void implSMSENVIA::Execute(DOMNode * dnEntrada, XMLGen * xmlSaida )
{
	CSms oSms;
	DOMNode* dnSmsNovo = walkDOM( dnEntrada, "sms", 0 );
	char* pzcIdPessoaUsuaurio = getUser();
	char codigoInterno[21+1];
	char destino[21+1];
	char mensagem[255+1];
	char* pzcAux;

	//Verifica se existe a TAG <sms>, ela indica que o XML é no formato novo
	//ou no formato antigo
	if( dnSmsNovo == NULL )
	{
		//XML antigo
		memset( destino, 0, sizeof( destino ) );
		memset( mensagem, 0, sizeof( mensagem ) );
		memset( codigoInterno, 0, sizeof( codigoInterno ) );
		pzcAux = walkTree( dnEntrada, "recipient", 0 );
		if( STRLENNULL( pzcAux ) > 0 )
		{
			strcpy( destino, pzcAux );
			//Desaloca o ponteiro
			XMLString::release( &pzcAux );
			pzcAux = walkTree( dnEntrada, "message", 0 );
			if( STRLENNULL( pzcAux ) > 0 )
			{
				strcpy( mensagem, pzcAux );
				XMLString::release( &pzcAux );

				oSms.RecuperaOrigem();

				oSms.Insert( mensagem, oSms.getOrigem(), destino, pzcIdPessoaUsuaurio );

				//Monta XML OUT
				xmlSaida->createTag( "sms" );
					xmlSaida->addItem( "codigoInterno", oSms.getIdSms() );
					xmlSaida->addItem( "origem", oSms.getOrigem() );
					xmlSaida->addItem( "destino", destino );
					xmlSaida->addItem( "mensagem", mensagem );
				xmlSaida->closeTag();//sms

				setStatusCode("00I0000","Sucesso");
			}
			else
			{
				setStatusCode("00E0001","Sem mensagem de entrada <message>");
			}
		}
		else
		{
			setStatusCode("00E0002","Telefone de destino é obrigatório <recipient>");
		}
	}
	else
	{
		//XML Novo
		setStatusCode("00I0000","Sucesso");
		oSms.RecuperaOrigem();
		for( int x=0;; x++ )
		{
			memset( destino, 0, sizeof( destino ) );
			memset( mensagem, 0, sizeof( mensagem ) );
			memset( codigoInterno, 0, sizeof( codigoInterno ) );
			if( ( dnSmsNovo = walkDOM( dnEntrada, "sms", x ) ) == NULL )
			{
				if( x == 0 )
					setStatusCode("00E0003","Não há mensagens para enviar, não foi achada nenhuma TAG <sms>");
				break;
			}
			pzcAux = walkTree( dnSmsNovo, "recipient", 0 );
			if( STRLENNULL( pzcAux ) > 0 )
			{
				strcpy( destino, pzcAux );
				//Desaloca o ponteiro
				XMLString::release( &pzcAux );
				pzcAux = walkTree( dnSmsNovo, "mensagem", 0 );
				if( STRLENNULL( pzcAux ) > 0 )
				{
					strcpy( mensagem, pzcAux );
					XMLString::release( &pzcAux );

					oSms.Insert( mensagem, oSms.getOrigem(), destino, pzcIdPessoaUsuaurio );

					//Monta XML OUT
					xmlSaida->createTag( "sms" );
						xmlSaida->addItem( "codigoInterno", oSms.getIdSms() );
						xmlSaida->addItem( "origem", oSms.getOrigem() );
						xmlSaida->addItem( "destino", destino );
						xmlSaida->addItem( "mensagem", mensagem );
					xmlSaida->closeTag();//sms

				}//if( STRLENNULL( pzcAux ) > 0 )
				else
				{
					setStatusCode("00E0004","Mensagem de entrada é obrigatória: <mensagem>");
					break;
				}//else if( STRLENNULL( pzcAux ) > 0 )
			}//if( STRLENNULL( pzcAux ) > 0 )
			else
			{
				setStatusCode("00E0005","Telefone de destino é obrigatório: <origem>");
				break;
			}//else if( STRLENNULL( pzcAux ) > 0 )
		}//for( int x=0;; x++ )
	}
}
