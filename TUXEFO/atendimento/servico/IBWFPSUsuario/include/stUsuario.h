/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:36 $
 **/

#ifndef STVARIAVEISUSUARIO
	#define STVARIAVEISUSUARIO

struct st_VariaveisUsuario
{
	int idFase;
	int idTipoCarteira;
	int idSegmentacao;
	int idPessoaUsuario;
	int idProcedencia;
	int idContato;
	int idGrupoAbertura;
	int idTipoPessoa;
	int idTipoLinha;
	int idTipoRelacionamento;
	int idCanal;

} ;

struct st_Result
{
    char idPessoaUsuario[256];
    char nmNome[256];
    char nmLoginUsuario[256];
    char idStatusUsuario[256];
    char inDisponivelWF[256];
} ;


#endif

