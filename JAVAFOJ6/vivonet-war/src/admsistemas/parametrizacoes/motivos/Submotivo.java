package admsistemas.parametrizacoes.motivos;

public class Submotivo {

	private String nome;
	private String inativo;
	private Long codigo;
	private Long pai;

	public Long getPai() {
		return pai;
	}

	public void setPai(Long pai) {
		this.pai = pai;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}
}