package br.com.indrasistemas.vivoservices.historico.titulolinha.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.DAOExceptionBuilder;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.historico.titulolinha.to.ListaTitularidadeLinhaTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.to.ParametrosTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.to.TitularidadeLinhaTO;

public class TitularidadeLinhaDAO extends HibernateBaseDAO {

    private static final Log logger = LogFactory.getLog(TitularidadeLinhaDAO.class);

    public TitularidadeLinhaDAO() {

    }

    public TitularidadeLinhaTO pesquisarTitularidadeLinha(ParametrosTO parametros) throws DAOException {

        TitularidadeLinhaTO result = new TitularidadeLinhaTO();

        Connection conn = getJDBCConnection();
        PreparedStatement ps = null;

        try {

            StringBuffer query = new StringBuffer("");

            boolean temDoc  = parametros.getNrCPFCNPJ() != null || parametros.getNrRG() != null;
            boolean temNome = parametros.getPrimeiroNome() != null && parametros.getUltimoNome() !=null;

            // indRetorno = 0
            // todos os campos considerados na busca
            // devolve somente dados do cliente
            if (!parametros.isBuscaCompleta()) {

            	query.append("SELECT idpessoa cdcliente, ");
            	query.append("       idtipopessoa, ");
            	query.append("       cdarearegistro cdArea, ");
            	query.append("       nrlinha nrTelefone,");
            	query.append("       rg, ");
            	query.append("       dsOrgaoEmissorRG,");
            	query.append("       nrCPFCNPJ,");
            	query.append("       primeironome nmnome, ");
            	query.append("       nmnomemeio, ");
            	query.append("       nmsobrenome, ");
            	query.append("       razaosocial, ");
            	query.append("       to_char(dtnascimento,'dd/mm/yyyy hh24:mi:ss') dtnascimento, ");
            	query.append("       to_char(dthabilitacao,'dd/mm/yyyy hh24:mi:ss') dtAtivacao, ");
            	query.append("       to_char(dtexpiracao,'dd/mm/yyyy hh24:mi:ss') dtDesativacao, ");
            	query.append("       sgclassificacao linhaAtiva, ");
            	query.append("       idclassificacaotipolinha dsModalidade,");
            	query.append("       sgtecnologia dsTecnologia,");
            	query.append("       nmtipologradouro, ");
            	query.append("       nmtitulologradouro, ");
            	query.append("       nmlogradouro, ");
            	query.append("       nrendereco, ");
            	query.append("       complemento dsenderecocomplemento, ");
            	query.append("       bairro nmbairro, ");
            	query.append("       cep nrcep, ");
            	query.append("       municipio nmmunicipio, ");
            	query.append("       uf sguf, ");
            	query.append("       indpreferencial ");
            	query.append(" FROM ( ");
            	query.append("   SELECT /*+ INDEX (PESSOA PESSOAIE2)*/ ");
            	query.append("          pessoa.idpessoa, ");
            	query.append("          pessoa.idtipopessoa, ");
            	query.append("          cdarearegistro, ");
            	query.append("          nrlinha, ");
            	query.append("          dadoslinha.dthabilitacao, ");
            	query.append("          dadoslinha.dtexpiracao, ");
            	query.append("          dadoslinha.sgclassificacao, ");
            	query.append("          dadoslinha.idclassificacaotipolinha,");
            	query.append("          dadoslinha.sgtecnologia, ");
            	query.append("          (SELECT DISTINCT nrdocumento ");
            	query.append("             FROM customer.documento d, customer.pessoadocumento pd ");
            	query.append("            WHERE idtipodocumento IN (SELECT idtipodocumento FROM apoio.tipodocumento WHERE sgclassificacao = 'RG') ");
            	query.append("              AND d.iddocumento = pd.iddocumento ");
            	query.append("              AND pd.idpessoa = pessoa.idpessoa) rg, ");
            	query.append("          (SELECT DISTINCT d.SGORGAOEXPEDIDOR ");
            	query.append("             FROM customer.documento d, customer.pessoadocumento pd ");
            	query.append("            WHERE idtipodocumento IN (SELECT idtipodocumento FROM apoio.tipodocumento WHERE sgclassificacao = 'RG') ");
            	query.append("              AND d.iddocumento = pd.iddocumento ");
            	query.append("              AND pd.idpessoa = pessoa.idpessoa ");
            	query.append("              AND ROWNUM <= 1) dsOrgaoEmissorRG, ");
            	query.append("          (CASE pessoa.idtipopessoa ");
            	query.append("              WHEN 1 ");
            	query.append("                 THEN NVL ");
            	query.append("                        ((SELECT nrdocumento ");
            	query.append("                            FROM customer.documento dx, customer.pessoadocumento pdx ");
            	query.append("                           WHERE idtipodocumento IN (SELECT idtipodocumento FROM apoio.tipodocumento WHERE sgclassificacao = 'CPF') ");
            	query.append("                             AND dx.iddocumento = pdx.iddocumento ");
            	query.append("                             AND pdx.idpessoa = pessoa.idpessoa), '') ");
            	query.append("              WHEN 2 ");
            	query.append("                 THEN NVL ");
            	query.append("                        ((SELECT nrdocumento ");
            	query.append("                            FROM customer.documento dy, customer.pessoadocumento pdy ");
            	query.append("                           WHERE idtipodocumento IN (SELECT idtipodocumento FROM apoio.tipodocumento WHERE sgclassificacao = 'CNPJ') ");
            	query.append("                             AND dy.iddocumento = pdy.iddocumento ");
            	query.append("                             AND pdy.idpessoa = pessoa.idpessoa), '') ");
            	query.append("           END ");
            	query.append("          ) nrCPFCNPJ, ");
            	query.append("          pf.dtnascimento, ");
            	query.append("          UPPER(trim(pessoa.nmnome)) primeironome, ");
            	query.append("          UPPER(trim(pessoa.nmnomemeio)) nmnomemeio, ");
            	query.append("          UPPER(trim(pessoa.nmsobrenome)) nmsobrenome, ");
            	query.append("          UPPER(trim(pessoa.nmpessoa)) nmpessoa, ");
            	query.append("          (SELECT nmpessoafilial FROM customer.pessoajuridica WHERE idpessoa = pessoa.idpessoa) razaosocial, ");
            	query.append("          endereco.nmtipologradouro, ");
            	query.append("          endereco.nmtitulologradouro, ");
            	query.append("          endereco.nmlogradouro, ");
            	query.append("          endereco.nrendereco, ");
            	query.append("          endereco.dsenderecocomplemento complemento, ");
            	query.append("          endereco.nmbairro bairro,  ");
            	query.append("          endereco.nmmunicipio municipio, ");
            	query.append("          endereco.nrcep cep, ");
            	query.append("          NVL (endereco.sguf, 'NC') uf, ");
            	query.append("          (CASE ");
            	query.append("              WHEN ((SELECT COUNT (1) FROM customer.contaendereco WHERE idpessoaendereco = endereco.idpessoaendereco) > 0 OR ");
            	query.append("                    (SELECT COUNT (1) FROM customer.pessoaendereco WHERE idpessoa = pessoa.idpessoa) = 0 )");
            	query.append("              THEN 1 ");
            	query.append("              ELSE 0 ");
            	query.append("           END ) indpreferencial ");

                if(temNome){
                    String nome = parametros.getPrimeiroNome() + " " + parametros.getUltimoNome();
                	query.append("    FROM (SELECT /*+ INDEX (PESSOA PESSOAIE2)*/ P.* FROM customer.pessoa P WHERE UPPER(P.NMPESSOA) = '").append(nome.toUpperCase()).append("' ) pessoa,  ");
                    
                }else{
                	query.append("    FROM customer.pessoa pessoa, ");
                }

            	query.append("          customer.pessoafisica pf, ");
            	query.append("          customer.pessoadepara pessoadepara, ");
            	query.append("          (SELECT /*+ INDEX (ENDERECO PESSOAENDERECOIE1)*/ ");
            	query.append("                  pe.*, u.sguf ");
            	query.append("             FROM customer.pessoaendereco pe, apoio.uf u ");
            	query.append("            WHERE dtexpiracao IS NULL AND pe.iduf = u.iduf) endereco, ");
            	query.append("          /*DADOSLINHA*/ ");
            	query.append("          (SELECT pessoalinha.idpessoadepara, ");
            	query.append("                  linhatelefonica.idlinhatelefonica,  ");
            	query.append("                  area.cdarearegistro, ");
            	query.append("                  linhabase.nrlinha, ");
            	query.append("                  tp.IDCLASSIFICACAOTIPOLINHA, ");
            	query.append("                  TP.SGTIPOLINHA,");
            	query.append("                  TP.SGTECNOLOGIA,");
            	query.append("                  es.SGCLASSIFICACAO, ");
            	query.append("                  linhatelefonica.DTHABILITACAO, ");
				query.append("                   linhatelefonica.DTEXPIRACAO,  ");
				query.append("                   so.nmsistemaorigem ");
            	query.append("             FROM customer.pessoalinha pessoalinha, ");
            	query.append("                  linha.linhatelefonica linhatelefonica, ");
            	query.append("                  linha.linhabase linhabase, ");
            	query.append("                  apoio.arearegistro area, ");
            	query.append("                  apoio.tipolinha tp, ");
				query.append("                   apoio.estadolinha es, ");
				query.append("                   apoio.sistemaorigem so ");
            	query.append("            WHERE pessoalinha.idlinhatelefonica = linhatelefonica.idlinhatelefonica ");
            	query.append("              and tp.IDTIPOLINHA = linhatelefonica.IDTIPOLINHA ");
            	query.append("              and es.IDESTADOLINHA = linhabase.IDESTADOLINHA ");
            	query.append("              AND linhatelefonica.idlinhabase = linhabase.idlinhabase ");
            	query.append("              AND linhabase.idarearegistro = area.idarearegistro ");
				query.append("               AND pessoalinha.idtiporelacionamento = 2 ");
				query.append("				 AND linhatelefonica.idsistemaorigem = so.idsistemaorigem ");
				query.append("				 AND so.sgsistemaorigem in ('ATY','NGN','NGI','NSP') ");
				query.append("				 AND so.cdorigem = 'MOVEL'	");
				query.append("				 AND linhatelefonica.cdfixomovel = 'M') dadoslinha ");
            	query.append("    WHERE pessoadepara.idpessoa = pessoa.idpessoa ");
            	query.append("      AND dadoslinha.idpessoadepara = pessoadepara.idpessoadepara ");
            	
            	if(temDoc){
                	query.append("  AND pessoa.idpessoa in (");
                	query.append("      SELECT PD.IDPESSOA");
                	query.append("        FROM CUSTOMER.PESSOADOCUMENTO PD, ");
                	query.append("             CUSTOMER.DOCUMENTO D, ");
                	query.append("                 APOIO.TIPODOCUMENTO TD, ");
                	query.append("                 (select pdpog.idpessoa ");
                	query.append("                    from customer.pessoadocumento pdpog, customer.documento dpog ");
                	query.append("                   where pdpog.iddocumento = dpog.iddocumento ");
                	
                    if (parametros.getNrCPFCNPJ() != null && parametros.getNrRG() != null) {
	                    query.append("                     and dpog.nrdocumento IN ('").append(parametros.getNrCPFCNPJ()).append("', '").append(parametros.getNrRG()).append("') ");
                    
                    } else if (parametros.getNrCPFCNPJ() != null) {
                        query.append("                     and dpog.nrdocumento = '").append(parametros.getNrCPFCNPJ()).append("' ");
                    
                    } else if (parametros.getNrRG() != null) {
                        query.append("                     and dpog.nrdocumento = '").append(parametros.getNrRG()).append("' ");
                    }
                	
                	query.append("                 ) docpog ");
                	query.append("        WHERE PD.IDDOCUMENTO = D.IDDOCUMENTO ");
                	query.append("          AND D.IDTIPODOCUMENTO = TD.IDTIPODOCUMENTO ");
                	query.append("          AND TD.DSTIPODOCUMENTO IN ('CPF', 'CNPJ', 'RG') ");
                	query.append("          and pd.IDPESSOA = docpog.idpessoa) ");
            	}
            	
            	query.append("      and pessoa.idpessoa = pf.idpessoa(+) ");
            	query.append("      AND pessoa.idpessoa = endereco.idpessoa(+))a ");
            	query.append(" where 1 = 1");

                if (parametros.isEnderecoPreferencial()) {
                    query.append(" and indpreferencial = 1 ");
                }

                if (parametros.getCdDDD() != 0) {
                    query.append("  AND a.cdarearegistro = ").append(parametros.getCdDDD());
                    query.append("  AND a.nrlinha = ").append(parametros.getNrTelefone());
                }

            } else

            // indRetorno = 1
            // parâmetros de busca: somente número do telefone e CPF/CNPJ
            // retorna dados do cliente e histórico de titularidade
            {
            	query.append("SELECT idpessoa cdcliente, ");
            	query.append("       idtipopessoa, ");
            	query.append("       cdarearegistro cdArea, ");
            	query.append("       nrlinha nrTelefone,");
            	query.append("       rg, ");
            	query.append("       dsOrgaoEmissorRG,");
            	query.append("       nrCPFCNPJ,");
            	query.append("       primeironome nmnome, ");
            	query.append("       nmnomemeio, ");
            	query.append("       nmsobrenome, ");
            	query.append("       razaosocial, ");
            	query.append("       to_char(dtnascimento,'dd/mm/yyyy hh24:mi:ss') dtnascimento, ");
            	query.append("       to_char(dthabilitacao,'dd/mm/yyyy hh24:mi:ss') dtAtivacao, ");
            	query.append("       to_char(dtexpiracao,'dd/mm/yyyy hh24:mi:ss') dtDesativacao, ");
            	query.append("       sgclassificacao linhaAtiva, ");
            	query.append("       idclassificacaotipolinha dsModalidade,");
            	query.append("       sgtecnologia dsTecnologia,");
            	query.append("       nmtipologradouro, ");
            	query.append("       nmtitulologradouro, ");
            	query.append("       nmlogradouro, ");
            	query.append("       nrendereco, ");
            	query.append("       complemento dsenderecocomplemento, ");
            	query.append("       bairro nmbairro, ");
            	query.append("       cep nrcep, ");
            	query.append("       municipio nmmunicipio, ");
            	query.append("       uf sguf, ");
            	query.append("       indpreferencial ");
            	query.append(" FROM   ( ");
            	query.append("   SELECT /*+ INDEX (PESSOA PESSOAIE2)*/ ");
            	query.append("          pessoa.idpessoa, ");
            	query.append("          pessoa.idtipopessoa, ");
            	query.append("          cdarearegistro, ");
            	query.append("          nrlinha, ");
            	query.append("          dadoslinha.dthabilitacao, ");
            	query.append("          dadoslinha.dtexpiracao, ");
            	query.append("          dadoslinha.SGCLASSIFICACAO, ");
            	query.append("          dadoslinha.IDCLASSIFICACAOTIPOLINHA, ");
            	query.append("          dadoslinha.sgtecnologia, ");
            	query.append("          (SELECT DISTINCT nrdocumento ");
            	query.append("             FROM customer.documento d, customer.pessoadocumento pd ");
            	query.append("            WHERE idtipodocumento IN (SELECT idtipodocumento FROM apoio.tipodocumento WHERE sgclassificacao = 'RG') ");
            	query.append("              AND d.iddocumento = pd.iddocumento ");
            	query.append("              AND pd.idpessoa = pessoa.idpessoa) rg, ");
            	query.append("          (SELECT DISTINCT d.SGORGAOEXPEDIDOR ");
            	query.append("                      FROM customer.documento d, customer.pessoadocumento pd ");
            	query.append("                     WHERE idtipodocumento IN (SELECT idtipodocumento FROM apoio.tipodocumento WHERE sgclassificacao = 'RG') ");
            	query.append("                       AND d.iddocumento = pd.iddocumento ");
            	query.append("                       AND pd.idpessoa = pessoa.idpessoa ");
            	query.append("                       and rownum<=1) dsOrgaoEmissorRG, ");
            	query.append("           (CASE pessoa.idtipopessoa ");
            	query.append("               WHEN 1 ");
            	query.append("                  THEN NVL ");
            	query.append("                         ((SELECT nrdocumento ");
            	query.append("                             FROM customer.documento dx, customer.pessoadocumento pdx ");
            	query.append("                            WHERE idtipodocumento IN (SELECT idtipodocumento FROM apoio.tipodocumento WHERE sgclassificacao = 'CPF') ");
            	query.append("                              AND dx.iddocumento = pdx.iddocumento ");
            	query.append("                              AND pdx.idpessoa = pessoa.idpessoa), '') ");
            	query.append("               WHEN 2 ");
            	query.append("                  THEN NVL ");
            	query.append("                         ((SELECT nrdocumento ");
            	query.append("                             FROM customer.documento dy, customer.pessoadocumento pdy ");
            	query.append("                            WHERE idtipodocumento IN (SELECT idtipodocumento FROM apoio.tipodocumento WHERE sgclassificacao = 'CNPJ') ");
            	query.append("                              AND dy.iddocumento = pdy.iddocumento ");
            	query.append("                              AND pdy.idpessoa = pessoa.idpessoa), '') ");
            	query.append("            END ");
            	query.append("           ) nrCPFCNPJ, ");
            	query.append("          pf.DTNASCIMENTO, ");
            	query.append("          UPPER(trim(pessoa.nmnome)) primeironome, ");
            	query.append("          UPPER(trim(pessoa.nmnomemeio)) nmnomemeio, ");
            	query.append("          UPPER(trim(pessoa.nmsobrenome)) nmsobrenome, ");
            	query.append("          UPPER(trim(pessoa.nmpessoa)) nmpessoa, ");
            	query.append("          (SELECT nmpessoafilial FROM customer.pessoajuridica  WHERE idpessoa = pessoa.idpessoa) razaosocial, ");
            	query.append("          endereco.nmtipologradouro, ");
            	query.append("          endereco.nmtitulologradouro, ");
            	query.append("          endereco.nmlogradouro, ");
            	query.append("          endereco.nrendereco, ");
            	query.append("          endereco.dsenderecocomplemento complemento, ");
            	query.append("          endereco.nmbairro bairro,  ");
            	query.append("          endereco.nmmunicipio municipio, ");
            	query.append("          endereco.nrcep cep, ");
            	query.append("          NVL (endereco.sguf, 'NC') uf, ");
            	query.append("          (CASE ");
            	query.append("              WHEN ((SELECT COUNT (1) FROM customer.contaendereco WHERE idpessoaendereco = endereco.idpessoaendereco) > 0 OR ");
            	query.append("                    (SELECT COUNT (1) FROM customer.pessoaendereco WHERE idpessoa = pessoa.idpessoa) = 0 ) ");
            	query.append("              THEN 1 ");
            	query.append("              ELSE 0 ");
            	query.append("           END ) indpreferencial ");

                if(temNome){
                    String nome = parametros.getPrimeiroNome().trim() + " " + parametros.getUltimoNome().trim();
                	query.append("     FROM (SELECT /*+ INDEX (PESSOA PESSOAIE2)*/ P.* FROM customer.pessoa P WHERE UPPER(P.NMPESSOA) = '").append(nome.toUpperCase()).append("' ) pessoa,  ");

                }else{
                	query.append("     FROM customer.pessoa pessoa, ");
                }
                
            	query.append("          customer.pessoafisica pf, ");
            	query.append("          customer.pessoadepara pessoadepara, ");
            	query.append("          (SELECT /*+ INDEX (ENDERECO PESSOAENDERECOIE1)*/ ");
            	query.append("                  pe.*, u.sguf ");
            	query.append("             FROM customer.pessoaendereco pe, apoio.uf u ");
            	query.append("            WHERE dtexpiracao IS NULL AND pe.iduf = u.iduf) endereco, ");
            	query.append("          /*DADOSLINHA*/ ");
            	query.append("          (SELECT pessoalinha.idpessoadepara, ");
            	query.append("                  linhatelefonica.idlinhatelefonica,  ");
            	query.append("                  pessoalinha.cdarearegistro, ");
            	query.append("                  pessoalinha.nrlinha, ");
            	query.append("                  tp.IDCLASSIFICACAOTIPOLINHA, ");
            	query.append("                  TP.SGTIPOLINHA,");
            	query.append("                  TP.SGTECNOLOGIA,");
            	query.append("                  linhabase.SGCLASSIFICACAO, ");
            	query.append("                  linhatelefonica.DTHABILITACAO, ");
				query.append("                  linhatelefonica.DTEXPIRACAO,  ");
				query.append("					so.nmsistemaorigem ");
            	query.append("             FROM customer.pessoalinhahistorico pessoalinha, ");
            	query.append("                  linha.linhatelefonica linhatelefonica, ");
            	query.append("                  apoio.tipolinha tp, ");
            	query.append("                  (select linhabase.IDLINHABASE, es.SGCLASSIFICACAO ");
            	query.append("                     from linha.linhabase linhabase, apoio.arearegistro area, apoio.estadolinha es ");
            	query.append("                    where es.IDESTADOLINHA=linhabase.IDESTADOLINHA ");
				query.append("                      and linhabase.idarearegistro = area.idarearegistro) linhabase, ");
				query.append("					apoio.sistemaorigem so ");
            	query.append("            WHERE pessoalinha.idlinhatelefonica =linhatelefonica.idlinhatelefonica ");
            	query.append("              and tp.IDTIPOLINHA=linhatelefonica.IDTIPOLINHA ");
            	query.append("              AND linhatelefonica.idlinhabase = linhabase.idlinhabase(+) ");
				query.append("              and pessoalinha.idtiporelacionamento = 2 ");
				query.append(" 				AND linhatelefonica.idsistemaorigem = so.idsistemaorigem ");
				query.append("				 AND so.sgsistemaorigem in ('ATY','NGN','NGI','NSP') ");
				query.append("				 AND so.cdorigem = 'MOVEL'	");
				query.append("				 AND linhatelefonica.cdfixomovel = 'M') dadoslinha ");
            	query.append("    WHERE pessoadepara.idpessoa = pessoa.idpessoa ");
            	query.append("      AND dadoslinha.idpessoadepara = pessoadepara.idpessoadepara ");
            	
            	if(temDoc){
                	query.append("  AND pessoa.idpessoa in (");
                	query.append("      SELECT PD.IDPESSOA");
                	query.append("        FROM CUSTOMER.PESSOADOCUMENTO PD, ");
                	query.append("             CUSTOMER.DOCUMENTO D, ");
                	query.append("                 APOIO.TIPODOCUMENTO TD, ");
                	query.append("                 (select pdpog.idpessoa ");
                	query.append("                    from customer.pessoadocumento pdpog, customer.documento dpog ");
                	query.append("                   where pdpog.iddocumento = dpog.iddocumento ");
                	
                    if (parametros.getNrCPFCNPJ() != null && parametros.getNrRG() != null) {
	                    query.append("                     and dpog.nrdocumento IN ('").append(parametros.getNrCPFCNPJ()).append("', '").append(parametros.getNrRG()).append("') ");
                    
                    } else if (parametros.getNrCPFCNPJ() != null) {
                        query.append("                     and dpog.nrdocumento = '").append(parametros.getNrCPFCNPJ()).append("' ");
                    
                    } else if (parametros.getNrRG() != null) {
                        query.append("                     and dpog.nrdocumento = '").append(parametros.getNrRG()).append("' ");
                    }
                	
                	query.append("                 ) docpog ");
                	query.append("        WHERE PD.IDDOCUMENTO = D.IDDOCUMENTO ");
                	query.append("          AND D.IDTIPODOCUMENTO = TD.IDTIPODOCUMENTO ");
                	query.append("          AND TD.DSTIPODOCUMENTO IN ('CPF', 'CNPJ', 'RG') ");
                	query.append("          and pd.IDPESSOA = docpog.idpessoa) ");
            	}
            	
            	query.append("      and pessoa.idpessoa = pf.idpessoa(+) ");
            	query.append("      AND pessoa.idpessoa = endereco.idpessoa(+) ) a ");

            	query.append("WHERE 1 = 1 ");

                if (parametros.isEnderecoPreferencial()) {
                    query.append("  AND indpreferencial = 1 ");
                }

                if (parametros.getCdDDD() != 0) {
                    query.append("  AND a.cdarearegistro = ").append(parametros.getCdDDD());
                    query.append("  AND a.nrlinha = ").append(parametros.getNrTelefone());
                }
            }

            ps = conn.prepareStatement(query.toString());

            if (!parametros.isBuscaCompleta()) {
                result.setCdArea(Long.toString(parametros.getCdDDD()));
                result.setNrTelefone(Long.toString(parametros.getNrTelefone()));
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                java.util.Date data = null;

                ArrayList<ListaTitularidadeLinhaTO> arrlista = new ArrayList<ListaTitularidadeLinhaTO>();
                do {
                    ListaTitularidadeLinhaTO lista = new ListaTitularidadeLinhaTO();

                    lista.setNrTelefone(rs.getLong("nrTelefone"));
                    lista.setCdArea(rs.getLong("cdArea"));

                    if ("A".equalsIgnoreCase(rs.getString("linhaAtiva"))) {
                        lista.setLinhaAtiva(true);
                    } else {
                        lista.setLinhaAtiva(false);
                    }

                    lista.setNrCPFCNPJ(rs.getString("nrCPFCNPJ"));

                    String sgTipoLinha = rs.getString("dsModalidade");
                    if ("PRE".equalsIgnoreCase(sgTipoLinha)) {
                        lista.setDsModalidade("Pré-pago");
                    } else if ("POS".equalsIgnoreCase(sgTipoLinha)) {
                        lista.setDsModalidade("Pós-pago");
                    } else if ("CTR".equalsIgnoreCase(sgTipoLinha)) {
                        lista.setDsModalidade("Controle");
                    }
                    
                    lista.setDsTecnologia(rs.getString("dsTecnologia"));

                    lista.setNrRG(rs.getString("RG"));
                    lista.setDsOrgaoEmissorRG(rs.getString("dsOrgaoEmissorRG"));

                    data = null;
                    if (rs.getString("dtnascimento") != null) {
                        data = formatter.parse(rs.getString("dtnascimento"));
                    }
                    lista.setDtNascimento(data);

                    String tipoPessoa = rs.getInt("idtipopessoa") == 1 ? "PF" : "PJ";
                    if ("PF".equals(tipoPessoa)) {
                        lista.setNome(rs.getString("nmnome"));
                        lista.setNomeMeio(rs.getString("nmnomemeio"));
                        lista.setSobrenome(rs.getString("nmsobrenome"));

                    } else {
                        lista.setRazaoSocial(rs.getString("razaosocial"));
                    }

                    StringBuffer endereco = new StringBuffer();
                    String tipo = getField(rs.getString("nmtipologradouro")).toUpperCase();
                    String titulo = getField(rs.getString("nmtitulologradouro")).toUpperCase();
                    String logradouro = getField(rs.getString("nmlogradouro")).toUpperCase();
                    String numero = getField(rs.getString("nrendereco")).toUpperCase();
                    String complemento = getField(rs.getString("dsenderecocomplemento")).toUpperCase();
                    String bairro = getField(rs.getString("nmbairro")).toUpperCase();
                    
                    if(!"".equals(logradouro)){
                        if(logradouro.startsWith(tipo)){
                        	if("".equals(titulo)){
                        		endereco.append(logradouro);
                        	}else{
                        		endereco.append(titulo).append(" ").append(logradouro);
                        	}
                        
                        }else{
                        	if("".equals(titulo)){
                        		endereco.append(tipo).append(" ").append(logradouro);
                        	}else{
                        		endereco.append(tipo).append(" ").append(titulo).append(" ").append(logradouro);
                        	}
                        }
                        
                        if(!"".equals(numero)){
                        	endereco.append(", ").append(numero);
                        }
                        
                        if(!"".equals(complemento)){
                        	endereco.append(", ").append(complemento);
                        }
                        
                        if(!"".equals(bairro)){
                        	endereco.append(", ").append(bairro);
                        }
                    }

                    lista.setDsEndereco(endereco.toString());

                    lista.setNmMunicipio(rs.getString("nmmunicipio"));
                    lista.setNrCEP(rs.getString("nrcep"));
                    lista.setSgUF(rs.getString("sguf"));

                    data = null;
                    if (rs.getString("dtAtivacao") != null) {
                        data = formatter.parse(rs.getString("dtAtivacao"));
                    }
                    lista.setDtAtivacao(data);

                    data = null;
                    if (rs.getString("dtDesativacao") != null) {
                        data = formatter.parse(rs.getString("dtDesativacao"));
                    }
                    lista.setDtDesativacao(data);

                    arrlista.add(lista);

                } while(rs.next());

                result.setLista((ListaTitularidadeLinhaTO[])arrlista.toArray(new ListaTitularidadeLinhaTO[0]));

            } else {
                result.setLista(null);
            }

            if (parametros.getCdDDD() != 0) {
                result.setNrTelefone(Long.toString(parametros.getNrTelefone()));
                result.setCdArea(Long.toString(parametros.getCdDDD()));
            }

        }catch(Exception e){
            logger.debug("Não foi possível realizar a pesquisa. " + e.getMessage(), e);
            throw DAOExceptionBuilder.build(e);

        } finally {
            closePreparedStatement(ps);
            closeConnection(conn);
        }
        return result;
    }

    private String getField(String value){
        return value!=null?value:"";
    }

}
