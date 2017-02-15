package br.com.vivo.fo.ctrls.cliente.associarGestor.db;

import java.sql.SQLException;

import javax.ejb.Local;

@Local
public interface GestorDB {

    /**
     * @jc:sql array-max-length="all" statement::
     * SELECT   PESSOAGESTOR.IDPESSOASISTEMAORIGEM              idPessoaSistemaOrigem,
     *          PESSOAGESTOR.NMPESSOAGESTOR                     nmPessoaGestor,
     *          PESSOAGESTOR.NMGESTOR                           nmGestor,
     *          PESSOAGESTOR.NMMEIOGESTOR                       nmMeioGestor,
     *          PESSOAGESTOR.NMSOBRENOMEGESTOR                  nmSobreNomeGestor,
     *          PESSOAGESTOR.NRTELEFONECELULARVIVO              nrTelefoneCelVivo,
     *          PESSOAGESTOR.NRTELEFONECELULAROUTRO             nrTelefoneCelOutro,
     *          PESSOAGESTOR.NRTELEFONEFIXO                     nrTelefoneFixo,
     *          PESSOAGESTOR.EMAIL                              email,
     *          PESSOAGESTOR.NRDOCUMENTO                        nrDocumento,
     *          PESSOAGESTOR.IDTIPODOCUMENTO                    idTipoDocumento,
     *          PESSOAGESTOR.IDUSUARIOALTERACAO                 idUuarioAlteracao,
     *          PESSOAGESTOR.DTULTIMAALTERACAO                  dtUltimaAlteracao,
     *          PESSOAGESTOR.DTNASCIMENTO                       dtNascimento,
     *          TO_CHAR(CONTA.CDCONTA)                          cdConta,
     *          PESSOAGESTORCONTA.IDTIPORELACIONAMENTO          idTipoRelacionamento,
     *          TIPORELACIONAMENTO.SGTIPORELACIONAMENTO         sgTipoRelacionamento,
     *          NVL(USUARIO.NMLOGINUSUARIO,'Não informado')     nmLoginUsuario
     * FROM     CUSTOMER.PESSOAGESTOR PESSOAGESTOR,
     *          CUSTOMER.PESSOAGESTORCONTA PESSOAGESTORCONTA,
     *          CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO,
     *          CUSTOMER.CONTA CONTA,
     *          ACESSO.USUARIO USUARIO
     * WHERE    PESSOAGESTORCONTA.IDTIPORELACIONAMENTO = TIPORELACIONAMENTO.IDTIPORELACIONAMENTO
     *          AND PESSOAGESTOR.NRDOCUMENTO = PESSOAGESTORCONTA.NRDOCUMENTO
     *          AND PESSOAGESTOR.IDUSUARIOALTERACAO = USUARIO.IDPESSOAUSUARIO(+)
     *          AND PESSOAGESTORCONTA.IDCONTA = CONTA.IDCONTA
     *          AND CONTA.CDCONTA = {cdConta}
     * UNION
     * SELECT   PESSOAGESTOR.IDPESSOASISTEMAORIGEM              idPessoaSistemaOrigem,
     *          PESSOAGESTOR.NMPESSOAGESTOR                     nmPessoaGestor,
     *          PESSOAGESTOR.NMGESTOR                           nmGestor,
     *          PESSOAGESTOR.NMMEIOGESTOR                       nmMeioGestor,
     *          PESSOAGESTOR.NMSOBRENOMEGESTOR                  nmSobreNomeGestor,
     *          PESSOAGESTOR.NRTELEFONECELULARVIVO              nrTelefoneCelVivo,
     *          PESSOAGESTOR.NRTELEFONECELULAROUTRO             nrTelefoneCelOutro,
     *          PESSOAGESTOR.NRTELEFONEFIXO                     nrTelefoneFixo,
     *          PESSOAGESTOR.EMAIL                              email,
     *          PESSOAGESTOR.NRDOCUMENTO                        nrDocumento,
     *          PESSOAGESTOR.IDTIPODOCUMENTO                    idTipoDocumento,
     *          PESSOAGESTOR.IDUSUARIOALTERACAO                 idUuarioAlteracao,
     *          PESSOAGESTOR.DTULTIMAALTERACAO                  dtUltimaAlteracao,
     *          PESSOAGESTOR.DTNASCIMENTO                       dtNascimento,
     *          {cdConta}										cdConta,
     *		    (SELECT IDTIPORELACIONAMENTO FROM CUSTOMER.TIPORELACIONAMENTO WHERE SGTIPORELACIONAMENTO = 'GM') idTipoRelacionamento,
     * 			'GM'                                            sgTipoRelacionamento,
     *          NVL(USUARIO.NMLOGINUSUARIO,'Não informado')     nmLoginUsuario
     *   FROM   CUSTOMER.PESSOAGESTOR PESSOAGESTOR,
     *          CUSTOMER.PESSOAGESTORMASTER PESSOAGESTORMASTER,
     *          ACESSO.USUARIO USUARIO
     *   WHERE  PESSOAGESTORMASTER.NRDOCUMENTOGESTOR = PESSOAGESTOR.NRDOCUMENTO
     *          AND PESSOAGESTOR.IDUSUARIOALTERACAO = USUARIO.IDPESSOAUSUARIO(+)
     *          AND PESSOAGESTORMASTER.NRDOCUMENTOEMPRESA = {cdCnpjEmpresa}
     *
     * ::
     */
    Gestor[] getGestoresConta(String cdCnpjEmpresa, String cdConta) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     *
     * SELECT    PESSOACONSULTOR.NRDOCUMENTO                    nrDocumento,
     *           PESSOACONSULTOR.IDPESSOA                       idPessoa,
     *           PESSOACONSULTOR.IDTIPORELACIONAMENTO           idTipoRelacionamento,
     *           TIPORELACIONAMENTO.SGTIPORELACIONAMENTO        sgTipoRelacionamento,
     *           USUARIO.NMLOGINUSUARIO                         nmLoginUsuario,
     *           USUARIO.NMNOME                                 nmNome,
     *           USUARIO.IDPERFILCONSULTORATD                   idPerfilConsultorAtd
     * FROM      CUSTOMER.PESSOACONSULTOR PESSOACONSULTOR,
     *           CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO,
     *           ACESSO.USUARIO USUARIO
     * WHERE     PESSOACONSULTOR.IDTIPORELACIONAMENTO = TIPORELACIONAMENTO.IDTIPORELACIONAMENTO
     *           AND PESSOACONSULTOR.IDPESSOA = USUARIO.IDPESSOAUSUARIO
     *           AND PESSOACONSULTOR.NRDOCUMENTO = {cdCnpjEmpresa}
     * ::
     */
    PessoaConsultor[] getConsultorRelacionamento(String cdCnpjEmpresa) throws SQLException;

    /**
     * @jc:sql array-max-length="all" statement::
     *
     * SELECT  CLIENTEESPECIAL.CDCODIGOCLIENTEESPECIAL      cdCodigoClienteEspecial,
     *         CLIENTEESPECIAL.NMNOMECOMPLETO               nmNomeCompleto,
     *         CLIENTEESPECIAL.NRLINHACONTATO1              nrLinhaContato1,
     *         CLIENTEESPECIAL.NRLINHACONTATO2              nrLinhaContato2,
     *         CLIENTEESPECIAL.NRCPF                        nrCpf,
     *         CLIENTEESPECIAL.EMAIL                        email,
     *         CLIENTEESPECIAL.DTANIVERSARIO                dtAniversario,
     *         CLIENTEESPECIAL.IDTIPORELACIONAMENTO         tpCargo,
     *         CLIENTEESPECIAL.CNPJ                         cnpj,
     *         TIPORELACIONAMENTO.NMTIPORELACIONAMENTO      nmTipoRelacionamento,
     *         CLIENTEESPECIAL.IDUSUARIOALTERACAO           idUuarioAlteracao,
     *         CLIENTEESPECIAL.DTULTIMAALTERACAO            dtUltimaAlteracao,
     *         NVL(USUARIO.NMLOGINUSUARIO,'Não informado')     nmLoginUsuario
     * FROM    CUSTOMER.CLIENTEESPECIAL CLIENTEESPECIAL,
     *         CUSTOMER.TIPORELACIONAMENTO TIPORELACIONAMENTO,
     *         ACESSO.USUARIO USUARIO
     * WHERE   CLIENTEESPECIAL.IDTIPORELACIONAMENTO = TIPORELACIONAMENTO.IDTIPORELACIONAMENTO
     *         AND CLIENTEESPECIAL.IDUSUARIOALTERACAO = USUARIO.IDPESSOAUSUARIO(+)
     *         AND CLIENTEESPECIAL.CNPJ = {cdCnpjEmpresa}
     * ::
     */
    ClienteEspecial[] getClienteEspecial(String cdCnpjEmpresa) throws SQLException;
}
