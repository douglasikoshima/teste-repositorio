package br.com.indrasistemas.vivoservices.atendimento.backoffice.dao;

import java.util.List;

import org.hibernate.Query;

import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.to.ComprovanteCancelamentoTO;

public class ComprovanteCancelamentoDAO extends HibernateBaseDAO {

	//private static final Log logger = LogFactory
	//		.getLog(ComprovanteCancelamentoFacadeSession.class);

	public List buscarComprovanteCancelamento(ComprovanteCancelamentoTO filtro,
			Integer first, Integer total) throws DAOException {

		// PATH DE TESTE EM GBLDEVB:
		// 'VIVO/Canais Eletrônicos/Meus Dados/Endereços/Inclusão de Endereços'

		String hql =
	        "select distinct "
	         + "conta.cdconta, "
	         + "conta.cddigitoconta, "
	         + "atendimento.pessoalinhahistorico.cdarearegistro, "
	         + "atendimento.pessoalinhahistorico.nrlinha, "
	         + "atendimento.dtabertura, "
	         + "atendimento.idatendimento, "
	         + "pessoadocumento.documento.nrdocumento, "
	         + "pessoa.nmpessoa "
	     + "from "
	         + "Atendimento atendimento, "
	         + "Atendimentoconta conta, "
	         + "Pessoa pessoa, "
	         + "Pessoadocumento pessoadocumento "
	     + "where "
	         + "atendimento = conta.atendimento "
	     + "and atendimento.contatofolha.contato.nmpath = 'VIVO/SERVIÇOS/SERVIÇOS BÁSICOS/CANCELAMENTO DE LINHA/CANCELAMENTO VIVO PÓS' "
	     + "and atendimento.linhatelefonica.linhabase.estadolinha.dsestadolinha = 'DESATIVO' "
	     + "and atendimento.pessoalinhahistorico.pessoadepara.pessoaByIdpessoa.idpessoa = pessoa.idpessoa "
	     + "and pessoadocumento.pessoa.idpessoa = atendimento.pessoalinhahistorico.pessoadepara.pessoaByIdpessoa.idpessoa "
	     + "and pessoadocumento.documento.tipodocumento.sgclassificacao = 'CNPJ' "
	     + "and pessoadocumento.documento.nrdocumento = :nrDocumento "
	     + "order by "
	         + "conta.cdconta, "
	         + "conta.cddigitoconta, "
	         + "atendimento.pessoalinhahistorico.cdarearegistro, "
	         + "atendimento.pessoalinhahistorico.nrlinha, "
	         + "atendimento.dtabertura, "
	         + "atendimento.idatendimento ";
		
		Query q = this.getQuery(hql, first, total);

		if (filtro.getNrDocumento() != null
				&& !"".equals(filtro.getNrDocumento().trim())) {
			q.setString("nrDocumento", filtro.getNrDocumento());
		}

		List lista = q.list();

		return ComprovanteCancelamentoTOBuilder
				.buildComprovanteCancelamentoTO(lista);
	}

}
