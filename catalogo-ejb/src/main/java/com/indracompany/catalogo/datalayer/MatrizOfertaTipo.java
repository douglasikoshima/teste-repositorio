package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="MATRIZOFERTATIPO", schema = "CATALOGOPRS_OW")
public class MatrizOfertaTipo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public MatrizOfertaTipo() {}
	
	@Id
	@Column(name = "IDMATRIZOFERTATIPO")
	private Integer idMatrizOfertaTipo;

	@Column(name = "NMMATRIZOFERTATIPO")
	private String nmMatrizOfertaTipo;

	//bi-directional many-to-one association to Matrizoferta
	@OneToMany(mappedBy="matrizOfertaTipo")
	private List<MatrizOferta> matrizOfertaList;

	//bi-directional many-to-one association to Matrizofertatipocontrato
	@OneToMany(mappedBy="matrizOfertaTipo")
	private List<MatrizOfertaTipoContrato> matrizOfertaTipoContratoList;

	public Integer getIdMatrizOfertaTipo() {
		return idMatrizOfertaTipo;
	}

	public void setIdMatrizOfertaTipo(Integer idMatrizOfertaTipo) {
		this.idMatrizOfertaTipo = idMatrizOfertaTipo;
	}

	public List<MatrizOferta> getMatrizOfertaList() {
		return matrizOfertaList;
	}

	public void setMatrizOfertaList(List<MatrizOferta> matrizOfertaList) {
		this.matrizOfertaList = matrizOfertaList;
	}

	public List<MatrizOfertaTipoContrato> getMatrizOfertaTipoContratoList() {
		return matrizOfertaTipoContratoList;
	}

	public void setMatrizOfertaTipoContratoList(
			List<MatrizOfertaTipoContrato> matrizOfertaTipoContratoList) {
		this.matrizOfertaTipoContratoList = matrizOfertaTipoContratoList;
	}

	public String getNmMatrizOfertaTipo() {
		return nmMatrizOfertaTipo;
	}

	public void setNmMatrizOfertaTipo(String nmMatrizOfertaTipo) {
		this.nmMatrizOfertaTipo = nmMatrizOfertaTipo;
	}
}