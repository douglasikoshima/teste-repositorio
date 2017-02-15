/**
 * @modulo  Cliente
 * @usecase Ura
 * @author  Bruno Pereira Soares
 * @author  Daniel Parra Tucunduva
 * @author  Luiz Carlos Fonseca
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:47 $
 **/

/*
 * Serviço de consulta de pontos, ativado via URA
 */

#ifndef SALDOPONTOSHPP
#define SALDOPONTOSHPP

#include <time.h>
#include "../../../negocio/commons/include/Commom.hpp"
#include <tuxfw.h>

 /* TAGs para o XML de resposta a chamada ao serviço de pontos
 */
#define TAG_SUB_SALDO				"SALDO"

/* TAGs do xml de entrada
 */
#define TAG_DDD						"cdDDD"
#define TAG_FONE					"numTelefone"

 /* TAGs do xml de saída
 */
#define TAG_SALDO					"numSaldoContaCliente"
#define TAG_DATA					"dtDataSaldo"
#define TAG_CODIGORETORNO           			"cdCodigoRetorno"

#endif
