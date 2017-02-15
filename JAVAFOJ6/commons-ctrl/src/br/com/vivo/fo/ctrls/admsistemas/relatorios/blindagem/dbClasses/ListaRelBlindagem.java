package br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem.dbClasses;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class ListaRelBlindagem {

    private String dtBlindagem = ConstantesCRM.SVAZIO;
    private int qtCliFid       = 0;
    private int qtCliInb       = 0;


    public String getDtBlindagem(){
        return dtBlindagem;
    }

    public void setDtBlindagem(String dtBlindagem){
        this.dtBlindagem = dtBlindagem;
    }

    public int getQtCliFid(){
        return qtCliFid;
    }

    public void setQtCliFid(int qtCliFid){
        this.qtCliFid = qtCliFid;
    }

    public int getQtCliInb(){
        return qtCliInb;
    }

    public void setQtCliInb(int qtCliInb){
        this.qtCliInb = qtCliInb;
    }

}
