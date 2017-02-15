package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.StatusArquivoImportacaoTO;

public class ImportacaoServicoFixaConsultaForm extends ValidatorActionForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idArquivo;
	private int idTipoImportacao;
	private Long paginaSolicitada;
	private Integer idStatusArquivoImportacao;
	private String nmArquivo;
	private String userImportacao;
	private String periodoInicio;
	private String periodoFim;
	private List<ImportacaoServicoFixaTO> resultadoImportacaoList;
	private List<StatusArquivoImportacaoTO> statusArquivoImportacaoTOList;
    private String nmArquivoConsulta;

	public int getIdArquivo() {
		return idArquivo;
	}
	public void setIdArquivo(int idArquivo) {
		this.idArquivo = idArquivo;
	}
	public int getIdTipoImportacao() {
		return idTipoImportacao;
	}
	public void setIdTipoImportacao(int idTipoImportacao) {
		this.idTipoImportacao = idTipoImportacao;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public String getNmArquivo() {
		return nmArquivo;
	}
	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}
	public String getPeriodoFim() {
		return periodoFim;
	}
	public void setPeriodoFim(String periodoFim) {
		this.periodoFim = periodoFim;
	}
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	public Integer getIdStatusArquivoImportacao() {
		return idStatusArquivoImportacao;
	}
	public void setIdStatusArquivoImportacao(Integer idStatusArquivoImportacao) {
		this.idStatusArquivoImportacao = idStatusArquivoImportacao;
	}
	public String getPeriodoInicio() {
		return periodoInicio;
	}
	public String getUserImportacao() {
		return userImportacao;
	}
	public void setUserImportacao(String userImportacao) {
		this.userImportacao = userImportacao;
	}
	public List<ImportacaoServicoFixaTO> getResultadoImportacaoList() {
		return resultadoImportacaoList;
	}
	public void setResultadoImportacaoList(
			List<ImportacaoServicoFixaTO> resultadoImportacaoList) {
		this.resultadoImportacaoList = resultadoImportacaoList;
	}		
	public List<StatusArquivoImportacaoTO> getStatusArquivoImportacaoTOList() {
		return statusArquivoImportacaoTOList;
	}
	public void setStatusArquivoImportacaoTOList(
			List<StatusArquivoImportacaoTO> statusArquivoImportacaoTOList) {
		this.statusArquivoImportacaoTOList = statusArquivoImportacaoTOList;
	}
    public String getNmArquivoConsulta() {
        return nmArquivoConsulta;
    }
    public void setNmArquivoConsulta(String nmArquivoConsulta) {
        this.nmArquivoConsulta = nmArquivoConsulta;
    }

}
