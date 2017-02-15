/*****************************************************************************
 *
 * Modulo:    SMSSTATUS
 * Arquivo:   SMSSTATUS.cpp
 * Proposito: Receber uma mensagem de uma fila TIBCO para alterar o status de um SMS
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
 * @CVS     $Author: akurak $ - $Date: 2007/07/12 14:36:57 $
 ****************************************************************************/
/**************************************************************************
 * Notas: Este servico receber uma mensagem contendo um identificador unico
 *        de uma mensagem SMS, gerado pelo servico 'SMSENVIA', e um status.
 *        Ele localiza o SMS nas bases FO atraves do identificador unico e
 *        atualiza o status da mensagem.
 *        
 *        Servicos relacionados 'SMSENVIA'
 *        Instancias do MBO relacionadas: 'SMSSend'
 *
 **************************************************************************/

//Definicao Global
#define SMSSTATUSCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include <CSms.h>

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(SMSSTATUS);

/**************************************************************************
 *  Funcao de Negocios:  SMSSTATUS
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
void implSMSSTATUS::Execute(DOMNode * dnEntrada, XMLGen * xmlSaida )
{
	CSms oSms;
	DOMNode* dnSmsStatus;
	char* pzcIdPessoaUsuaurio = getUser();
	char codigoInterno[21+1];
	char status[21+1];
	char dataHoraEntrega[21+1];
	char* pzcAux;

	setStatusCode("00I0000","Sucesso");
	for( int x = 0;; x++ )
	{
		memset( codigoInterno, 0, sizeof( codigoInterno ) );
		memset( status, 0, sizeof( status ) );
		memset( dataHoraEntrega, 0, sizeof( dataHoraEntrega ) );
	
		if( ( dnSmsStatus = walkDOM( dnEntrada, "smsRetorno", x ) ) == NULL )
		{
			if( x == 0 )
				setStatusCode("00E0003","Não há status para atualizar, não foi achada nenhuma TAG <smsRetorno>");
			break;
		}
		pzcAux = walkTree( dnSmsStatus, "codigoInterno", 0 );
		if( STRLENNULL( pzcAux ) > 0 )
		{
			strcpy( codigoInterno, pzcAux );
			//Desaloca o ponteiro
			XMLString::release( &pzcAux );

			pzcAux = walkTree( dnSmsStatus, "status", 0 );
			if( STRLENNULL( pzcAux ) > 0 )
			{
				strcpy( status, pzcAux );
				//Desaloca o ponteiro
				XMLString::release( &pzcAux );

				pzcAux = walkTree( dnSmsStatus, "dataHoraEntrega", 0 );
				if( STRLENNULL( pzcAux ) > 0 )
				{
					strcpy( dataHoraEntrega, pzcAux );
					//Desaloca o ponteiro
					XMLString::release( &pzcAux );

					oSms.AtiualizaStatus( codigoInterno, status, dataHoraEntrega, pzcIdPessoaUsuaurio );
				}
				else
				{
					setStatusCode("00E0004","Data e hora de entrega é obrigatória: <dataHoraEntrega>");
					break;
				}//else if( STRLENNULL( pzcAux ) > 0 )
			}//if( STRLENNULL( pzcAux ) > 0 )
			else
			{
				setStatusCode("00E0004","Status do envio é obrigatório: <status>");
				break;
			}//else if( STRLENNULL( pzcAux ) > 0 )
		}//if( STRLENNULL( pzcAux ) > 0 )
		else
		{
			setStatusCode("00E0005","Código Interno é obrigatório: <codigoInterno>");
			break;
		}//else if( STRLENNULL( pzcAux ) > 0 )
	}//for( int x=0;; x++ )
}
