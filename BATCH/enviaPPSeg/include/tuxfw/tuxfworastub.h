/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2014/08/14 15:47:13 $
 * @ID      $Id: tuxfworastub.h,v 1.1.6.2 2014/08/14 15:47:13 a5114878 Exp $
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

