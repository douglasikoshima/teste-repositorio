package br.com.vivo.fo.ctrls.tracking.relatoriosTracking.dbClasses;

import java.math.BigDecimal;

/**
 * Tabela TRACKING.AUXRELATORIO1
 */
public class TrackingRelatorioConsolidado {

	public int status;
	public String dsStatus;
	public BigDecimal volume1;
	public BigDecimal volume2;
	public BigDecimal volume3;
	public BigDecimal volume4;
	public BigDecimal volume5;
	public BigDecimal volume6;
	public BigDecimal volume7;
	public BigDecimal volume8;
	public int agingstatus;
	public int agingpedido;
	public String sguf;
	public String dssegmento;
	public BigDecimal idcanalvenda;

	public int getAgingpedido() {
		return agingpedido;
	}

	public void setAgingpedido(int agingpedido) {
		this.agingpedido = agingpedido;
	}

	public int getAgingstatus() {
		return agingstatus;
	}

	public void setAgingstatus(int agingstatus) {
		this.agingstatus = agingstatus;
	}

	public String getDssegmento() {
		if (dssegmento == null) {
			dssegmento = "";
		}
		return dssegmento;
	}

	public void setDssegmento(String dssegmento) {
		this.dssegmento = dssegmento;
	}

	public String getDsStatus() {
		if (dsStatus == null) {
			dsStatus = "";
		}
		return dsStatus;
	}

	public void setDsStatus(String dsStatus) {
		this.dsStatus = dsStatus;
	}

	public BigDecimal getIdcanalvenda() {
		if (idcanalvenda == null) {
			idcanalvenda = new BigDecimal("0");
		}
		return idcanalvenda;
	}

	public void setIdcanalvenda(BigDecimal idcanalvenda) {
		this.idcanalvenda = idcanalvenda;
	}

	public String getSguf() {
		if (sguf == null) {
			sguf = "";
		}
		return sguf;
	}

	public void setSguf(String sguf) {
		this.sguf = sguf;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BigDecimal getVolume1() {
		if (volume1 == null) {
			volume1 = new BigDecimal("0");
		}
		return volume1;
	}

	public void setVolume1(BigDecimal volume1) {
		this.volume1 = volume1;
	}

	public BigDecimal getVolume2() {
		if (volume2 == null) {
			volume2 = new BigDecimal("0");
		}
		return volume2;
	}

	public void setVolume2(BigDecimal volume2) {
		this.volume2 = volume2;
	}

	public BigDecimal getVolume3() {
		if (volume3 == null) {
			volume3 = new BigDecimal("0");
		}
		return volume3;
	}

	public void setVolume3(BigDecimal volume3) {
		this.volume3 = volume3;
	}

	public BigDecimal getVolume4() {
		if (volume4 == null) {
			volume4 = new BigDecimal("0");
		}
		return volume4;
	}

	public void setVolume4(BigDecimal volume4) {
		this.volume4 = volume4;
	}

	public BigDecimal getVolume5() {
		if (volume5 == null) {
			volume5 = new BigDecimal("0");
		}
		return volume5;
	}

	public void setVolume5(BigDecimal volume5) {
		this.volume5 = volume5;
	}

	public BigDecimal getVolume6() {
		if (volume6 == null) {
			volume6 = new BigDecimal("0");
		}
		return volume6;
	}

	public void setVolume6(BigDecimal volume6) {
		this.volume6 = volume6;
	}

	public BigDecimal getVolume7() {
		if (volume7 == null) {
			volume7 = new BigDecimal("0");
		}
		return volume7;
	}

	public void setVolume7(BigDecimal volume7) {
		this.volume7 = volume7;
	}

	public BigDecimal getVolume8() {
		if (volume8 == null) {
			volume8 = new BigDecimal("0");
		}
		return volume8;
	}

	public void setVolume8(BigDecimal volume8) {
		this.volume8 = volume8;
	}
}
