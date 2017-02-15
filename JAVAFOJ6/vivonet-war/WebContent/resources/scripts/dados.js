var oXml=null;

function carregarDados(strXml){
        oXml = new ActiveXObject("microsoft.xmldom");
        oXml.async=false;
        if(!oXml.loadXML(strXml)){
            alert("Error on load of document");
            alert(oXml.parseError.reason);
            alert(oXml.parseError.line);
        }
                
        if(oXml.selectSingleNode("/salida/@tipo").text!='naoCliente'){
            if(document.getElementById('cliente_nmNome')){

                cliente_nmNome.innerHTML = oXml.selectSingleNode("/salida/cliente/nome").text;            
                cliente_inTipoPessoa.innerHTML = oXml.selectSingleNode("/salida/cliente/inTipoPessoa").text;            
                cliente_nrTelefone.innerHTML = oXml.selectSingleNode("/salida/cliente/nrTelefone").text;
                cliente_dsChurn.innerHTML = oXml.selectSingleNode("/salida/cliente/dsChurn").text;
                cliente_carterizacaoVO_descricao.innerHTML = oXml.selectSingleNode("/salida/cliente/carterizacaoVO/descricao").text;            
                cliente_segmentacaoVO_descricao.innerHTML = oXml.selectSingleNode("/salida/cliente/segmentacaoVO/descricao").text;
                dsTpDocto.innerHTML = oXml.selectSingleNode("/salida/cliente/documento/dsTipoDocumento").text;            
                nrDocto.innerHTML = oXml.selectSingleNode("/salida/cliente/documento/nrDocumento").text;
                                
                if (document.getElementById('cliente_endereco_dsEndereco')) {
                    cliente_endereco_dsEndereco.innerHTML = oXml.selectSingleNode("/salida/cliente/endereco/dsEndereco").text;
                    cliente_endereco_nrEndereco.innerHTML = ', ' + oXml.selectSingleNode("/salida/cliente/endereco/nrEndereco").text;
                    cliente_endereco_dsBairro.innerHTML = oXml.selectSingleNode("/salida/cliente/endereco/dsBairro").text;
                    cliente_endereco_dsBairro.innerHTML = oXml.selectSingleNode("/salida/cliente/endereco/dsBairro").text;
                    cliente_endereco_dsCidade.innerHTML = oXml.selectSingleNode("/salida/cliente/endereco/dsCidade").text;
                    cliente_endereco_UFVO_nmUF.innerHTML = oXml.selectSingleNode("/salida/cliente/endereco/UFVO/nmUF").text;
                    cliente_endereco_nrCEP.innerHTML = oXml.selectSingleNode("/salida/cliente/endereco/nrCEP").text;
                }
            }

        } else {
            if(document.getElementById('cliente_nmNome')){
                cliente_nmNome.innerHTML = oXml.selectSingleNode("/salida/nome").text;        
            }
        }
        
        var idTipoLinha         = oXml.selectSingleNode("/salida/parametros/idTipoLinha").text;
        var tipoRelacionamiento = oXml.selectSingleNode("/salida/parametros/tipoRelacionamiento").text;
        var idPessoaCliente     = oXml.selectSingleNode("/salida/parametros/idPessoaCliente").text;
        var idProspect          = oXml.selectSingleNode("/salida/parametros/idProspect").text;
        var inCorporativo       = oXml.selectSingleNode("/salida/parametros/inCorporativo").text;
        var nrConta             = oXml.selectSingleNode("/salida/parametros/nrConta").text;
        var inLegado            = oXml.selectSingleNode("/salida/parametros/inLegado").text;
        
        top.frameApp.frmDados.idTipoLinha = tipoLinha;
        
        //alert(tipoLinha);
    if(tipoLinha==1 || tipoLinha==5){
        //alert('tipoLinha==1 nr t idP:'+nrConta+"-"+tipoRelacionamiento+"-"+idProspect);
            //carrega abaRetençao somente se tipoLinha==1            
            top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt06, false);
            if(document.getElementById('cliente_nmNome')){
                try{
					frmFatura.setNroLinha(nrConta,tipoRelacionamiento,idProspect);
                if(inLegado==1){
                        document.getElementById("dvFaturamento").style.display="block";
                    }else{
                            //desbilita lupa pre-pago
                            document.getElementById("dvPrePago").style.display="none";
                    }
                }catch(e){
                    //alert('Privilégios insuficientes para a exibição correta da página!');
                }
            }
    }else if(tipoLinha==2 || tipoLinha==6){
            //carrega abaRetençao somente se tipoLinha==1            
            top.frameApp.abaOcultar(top.frameApp.btAba, top.frameApp.bt06, true);
            if(document.getElementById('cliente_nmNome')){
                if( inLegado==1 ){ 
                    document.getElementById("dvPrePago").style.display="block";
                }else{
                        //desbilita lupa faturamento e pre-pago
                        document.getElementById("dvFaturamento").style.display="none";
                        document.getElementById("dvPrePago").style.display="none";
                }
            }
        }
        bindLupaCliente(tipoRelacionamiento, idPessoaCliente);
        bindLupaCarterizacaoSegmentacao(tipoRelacionamiento, idProspect, inCorporativo);
        parent.frameURA.bindCombos(1, tipoRelacionamiento);
}
    
    function limparDados(){
            if(document.getElementById('cliente_nmNome')){
                cliente_nmNome.innerHTML = "";
                cliente_inTipoPessoa.innerHTML = "";
                cliente_nrTelefone.innerHTML = "";
                cliente_dsChurn.innerHTML = "";
                
                cliente_carterizacaoVO_descricao.innerHTML = "";
                cliente_segmentacaoVO_descricao.innerHTML = "";
                
                dsTpDocto.innerHTML = "";
                nrDocto.innerHTML = "";
                        
                cliente_endereco_dsEndereco.innerHTML = "";
                cliente_endereco_nrEndereco.innerHTML = "";
                cliente_endereco_dsBairro.innerHTML = "";
                cliente_endereco_dsBairro.innerHTML = "";
                cliente_endereco_dsCidade.innerHTML = "";
                cliente_endereco_UFVO_nmUF.innerHTML = "";
                cliente_endereco_nrCEP.innerHTML = "";
            }
            if(document.getElementById('cliente_nmNome')){
                try{
                    frmFatura.setNroLinha("",6,"  ");  
                    frmFatura.limparDados();        
                    frmPrePago.limparDados();
                }catch(e){
                    //alert('Privilégios insuficientes para a exibição correta da página!');
                }        
                document.getElementById("dvFaturamento").style.display="none";
            }
            bindLupaCliente(7, 26);
            bindLupaCarterizacaoSegmentacao(6, "  ", null);
            
            if(document.getElementById('cliente_nmNome')){
                ifrLinha.limparDados();
            }
    }
        
    function bindLupaCarterizacaoSegmentacao(tipoRelacionamiento, idProspect, inCorporativo){
    //alert('bindLupaCarterizacaoSegmentacao\n'+tipoRelacionamiento+'-'+idProspect+'-'+inCorporativo);
        if(tipoRelacionamiento==6 || tipoRelacionamiento==7 || idProspect!=""){
            if(document.getElementById('imgLupaCarterizacao')){
                imgLupaCarterizacao.onclick = null;
                imgLupaCarterizacao.style.cursor='';
            }
            if(document.getElementById('imgLupaSegmentacao')){
                imgLupaSegmentacao.onclick = null;
                imgLupaSegmentacao.style.cursor='';
            }
        }else{
            top.frameApp.inCorporativo = inCorporativo;
            if(document.getElementById('imgLupaCarterizacao')){
                imgLupaCarterizacao.onclick = top.frameApp.abreLupaCarteirizacao;
                imgLupaCarterizacao.style.cursor='hand';
            }
            if(document.getElementById('imgLupaSegmentacao')){
                imgLupaSegmentacao.onclick = top.frameApp.abreLupaSegmentacao;
                imgLupaSegmentacao.style.cursor='hand';
            }
        }
    }
    
    function bindLupaCliente(tipoRelacionamiento, idPessoaCliente){
    //alert('bindLupaCliente\n'+tipoRelacionamiento+'-'+idPessoaCliente);
        if(document.getElementById('imgLupaCliente')){
            imgLupaCliente.onclick= null;
            imgLupaCliente.style.cursor='';
            if(tipoRelacionamiento==6){
                imgLupaCliente.style.cursor='hand';    
                imgLupaCliente.onclick= top.frameApp.abreLupaUsuario;
            }else{
                if(idPessoaCliente!=26){
                    imgLupaCliente.style.cursor='hand';
                    imgLupaCliente.onclick= top.frameApp.abreLupaCliente;
                }
            }
        }    
    }
    
    function getNroConta(){
        return oXml.selectSingleNode("/salida/parametros/nrConta").text;
    }
