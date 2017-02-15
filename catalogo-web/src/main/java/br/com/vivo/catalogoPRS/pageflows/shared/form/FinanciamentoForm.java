package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.CondicaoPagamentoTO;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

public class FinanciamentoForm extends ValidatorActionForm  implements java.io.Serializable {
	
	private static final long serialVersionUID = 6044382487283681769L;
	
	private String codigoPlanoFinanciamento;
	private String nome;
	private String qtdeParcela;
	private String taxaJuros;
	private String origem;
	private String disponibilidade;
	private String usuario;
	
	private Integer idEditado;
	private String nomeNew;
	private String qtdeParcelaNew;
	private String taxaJurosNew;
	private String statusAtual;

	public String getCodigoPlanoFinanciamento() {
		return codigoPlanoFinanciamento;
	}
	public void setCodigoPlanoFinanciamento(String codigoPlanoFinanciamento) {
		this.codigoPlanoFinanciamento = codigoPlanoFinanciamento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQtdeParcela() {
		return qtdeParcela;
	}
	public void setQtdeParcela(String qtdeParcela) {
		this.qtdeParcela = qtdeParcela;
	}
	public String getTaxaJuros() {
        return this.taxaJuros == null ? "" : this.taxaJuros.replace(".", ",");
	}
    
	public void setTaxaJuros(String taxaJuros) {
        this.taxaJuros = taxaJuros.replace(",", ".");
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	public String getUsuario() {
		return disponibilidade;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getNomeNew() {
		return nomeNew;
	}
	public void setNomeNew(String nomeNew) {
		this.nomeNew = nomeNew;
	}
	public String getQtdeParcelaNew() {
		return qtdeParcelaNew;
	}
	public void setQtdeParcelaNew(String qtdeParcelaNew) {
		this.qtdeParcelaNew = qtdeParcelaNew;
	}
	public String getTaxaJurosNew() {
		return this.taxaJurosNew == null ? "" : this.taxaJurosNew.replace(".", ",");
	}
	public void setTaxaJurosNew(String taxaJurosNew) {
		this.taxaJurosNew = taxaJurosNew.replace(",", ".");
	}
	public Integer getIdEditado() {
		return idEditado;
	}
	public void setIdEditado(Integer idEditado) {
		this.idEditado = idEditado;
	}
	public String getStatusAtual() {
		return statusAtual;
	}
	public void setStatusAtual(String statusAtual) {
		this.statusAtual = statusAtual;
	}
	
	public CondicaoPagamentoTO buildTO() throws CatalogoPRSException {
        try {
			CondicaoPagamentoTO tObject = new CondicaoPagamentoTO();
			tObject.setIdCondicaoPagamento(this.idEditado);
			tObject.setNmCondicaoPagamento(this.nome);
			if (!StringUtils.isBlank(this.origem)) {
				tObject.setInCriacaoCatalogo(this.origem.equalsIgnoreCase("catalogo"));
			}
            if(!StringUtils.isBlank(this.qtdeParcela)) {
                tObject.setQtParcelas(Integer.valueOf(this.qtdeParcela));
            }
			if (!StringUtils.isBlank(this.disponibilidade)) {
				tObject.setInDisponivel(this.disponibilidade.equalsIgnoreCase("sim"));
			}
			tObject.setUsuarioForm(this.usuario);

            if (!StringUtils.isBlank(this.taxaJuros)) {
                tObject.setTxJuroParcela(new BigDecimal(this.taxaJuros));
            }
            tObject.setCdCondicaoPagamento(this.codigoPlanoFinanciamento);
			return tObject;
        } catch (Throwable e) {
            throw new CatalogoPRSException("Erro ao criar TO", e);
        }
	}
	
	public CondicaoPagamentoTO buildTOInsertChange() throws CatalogoPRSException{
		CondicaoPagamentoTO tObject = new CondicaoPagamentoTO();
		tObject.setIdCondicaoPagamento(this.idEditado);
		tObject.setNmCondicaoPagamento(this.nomeNew);
        try {
            tObject.setQtParcelas(Integer.valueOf(this.qtdeParcelaNew));
        } catch (Throwable t) {
            throw new CatalogoPRSException("Valor informado para parcela inv&aacute;lido");
        }
        try{
            tObject.setTxJuroParcela(new BigDecimal(this.taxaJurosNew));
        } catch (Throwable t) {
            throw new CatalogoPRSException("Valor informado para taxa de juros inv&aacute;lido");
        }
		tObject.setUsuarioForm(this.usuario);
		return tObject;
	}
}
