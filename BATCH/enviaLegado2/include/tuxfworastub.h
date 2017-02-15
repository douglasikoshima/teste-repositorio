/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/09/29 14:29:20 $
 * @ID      $Id: tuxfworastub.h,v 1.1.2.1 2009/09/29 14:29:20 a5110702 Exp $
 **/

#define TUXFW_DBOK 1
#define TUXFW_DBFAIL -1

/*!
 * Proposito: Realiza a conexao com o banco de dados para stub local W32
 * Parametros: nenhum
 * Retorno:
 * @returns flag indicador de sucesso da conexao
 */
extern int DBConn( char* connectionString );
/*!
 * Proposito: Realiza a deconexao com o banco de dados para stub local W32
 * Parametros: nenhum
 * Retorno:
 * @returns flag indicador de sucesso da conexao
 */
extern int DBDisconn();


/* 2004-10-15 - MRRMG - Incorporação de ativação SQLConnect exatamento como MBO */
/* error handling */


extern int tuxfw_oraError;				/* if set to 1 there is an Oracle error */
extern int tuxfw_oraErrorCode;			/* has the oracle error code */
extern char tuxfw_oraErrorText[2048];	/* oracle error text */
void tuxfw_dyn_error(void);


/* ATENÇÂO !!! Estes métodos NÂO DEVEM ser usados pelos desenvolvedores EM HIPOTESE NENHUMA */
extern int tuxfw_oracleConnect(char *oraUserName, char *oraPassword, int *oracleProCError, char **procText);
extern int tuxfw_oracleDisconnect(int *oracleProCError, char **procText);
extern int tuxfw_oracleRollback();
extern int tuxfw_oracleCommit();

