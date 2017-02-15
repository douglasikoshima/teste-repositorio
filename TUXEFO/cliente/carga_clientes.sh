echo Copiando arquivos LIBs para server...
cp -f staging/lib/libClienteCommons.so        server/lib/libClienteCommons.so 
cp -f staging/lib/libCadCompCliAtt.so         server/lib/libCadCompCliAtt.so  
cp -f staging/lib/libCadCompCliIni.so         server/lib/libCadCompCliIni.so  
cp -f staging/lib/libCadCompCliIns.so         server/lib/libCadCompCliIns.so  
cp -f staging/lib/libLupaCarteiraPF.so        server/lib/libLupaCarteiraPF.so 
cp -f staging/lib/libLupaCarteiraPJ.so        server/lib/libLupaCarteiraPJ.so 
cp -f staging/lib/libAbaComunicacao.so        server/lib/libAbaComunicacao.so 
cp -f staging/lib/libAbaCtaPontuacao.so       server/lib/libAbaCtaPontuacao.so
cp -f staging/lib/libAbaDocumento.so          server/lib/libAbaDocumento.so   
cp -f staging/lib/libAbaEndereco.so           server/lib/libAbaEndereco.so    
cp -f staging/lib/libBuscaDadosCEP.so         server/lib/libBuscaDadosCEP.so  
cp -f staging/lib/libLCInicial.so             server/lib/libLCInicial.so      
cp -f staging/lib/libManuComunicaIni.so       server/lib/libManuComunicaIni.so
cp -f staging/lib/libManuEnderecoIni.so       server/lib/libManuEnderecoIni.so
cp -f staging/lib/libAbaContrato.so           server/lib/libAbaContrato.so    
cp -f staging/lib/libAbaGeral.so              server/lib/libAbaGeral.so       
cp -f staging/lib/libAbaPlanoServico.so       server/lib/libAbaPlanoServico.so
cp -f staging/lib/libAbaServico.so            server/lib/libAbaServico.so     
cp -f staging/lib/libDivNumeroAlt.so          server/lib/libDivNumeroAlt.so   
cp -f staging/lib/libFiltroLupaLinha.so       server/lib/libFiltroLupaLinha.so
cp -f staging/lib/libListaDetalheIni.so       server/lib/libListaDetalheIni.so
cp -f staging/lib/libLupaLinhaIni.so          server/lib/libLupaLinhaIni.so   
cp -f staging/lib/libPesquisaEnd.so           server/lib/libPesquisaEnd.so    
cp -f staging/lib/libPesquisaEndFil.so        server/lib/libPesquisaEndFil.so 
cp -f staging/lib/libPesquisaEndIni.so        server/lib/libPesquisaEndIni.so 
cp -f staging/lib/libPesquisaLinha.so         server/lib/libPesquisaLinha.so  
cp -f staging/lib/libPesquisaPessoa.so        server/lib/libPesquisaPessoa.so 
cp -f staging/lib/libAbaServFiltro.so         server/lib/libAbaServFiltro.so  
cp -f staging/lib/libAbaServicos.so           server/lib/libAbaServicos.so    
cp -f staging/lib/libCarregaParam.so          server/lib/libCarregaParam.so   
cp -f staging/lib/libCarregaTI.so             server/lib/libCarregaTI.so      
cp -f staging/lib/libDadClienteTI.so          server/lib/libDadClienteTI.so   
cp -f staging/lib/libDadFacturaTI.so          server/lib/libDadFacturaTI.so   
cp -f staging/lib/libDadLinhaTI.so            server/lib/libDadLinhaTI.so     
cp -f staging/lib/libDadUsuarioTI.so          server/lib/libDadUsuarioTI.so   
cp -f staging/lib/libHISTORICOSERV.so         server/lib/libHISTORICOSERV.so  
cp -f staging/lib/libReloadLinha.so           server/lib/libReloadLinha.so    
cp -f staging/lib/libAAssunto.so              server/lib/libAAssunto.so       
cp -f staging/lib/libEAssunto.so              server/lib/libEAssunto.so       
cp -f staging/lib/libIAssunto.so              server/lib/libIAssunto.so       
cp -f staging/lib/libLAssunto.so              server/lib/libLAssunto.so       
cp -f staging/lib/libAAtributo.so             server/lib/libAAtributo.so      
cp -f staging/lib/libEAtributo.so             server/lib/libEAtributo.so      
cp -f staging/lib/libIAtributo.so             server/lib/libIAtributo.so      
cp -f staging/lib/libLAtributo.so             server/lib/libLAtributo.so      
cp -f staging/lib/libListAtributoId.so        server/lib/libListAtributoId.so 
cp -f staging/lib/libLContInicial.so          server/lib/libLContInicial.so   
cp -f staging/lib/libLConteudo.so             server/lib/libLConteudo.so      
cp -f staging/lib/libACanalPreferido.so       server/lib/libACanalPreferido.so
cp -f staging/lib/libARecusaMContato.so       server/lib/libARecusaMContato.so
cp -f staging/lib/libLCanal.so                server/lib/libLCanal.so         
cp -f staging/lib/libLCanalPreferido.so       server/lib/libLCanalPreferido.so
cp -f staging/lib/libLMeioContato.so          server/lib/libLMeioContato.so   
cp -f staging/lib/libLRecusaMContato.so       server/lib/libLRecusaMContato.so
cp -f staging/lib/libASubAssunto.so           server/lib/libASubAssunto.so    
cp -f staging/lib/libESubAssunto.so           server/lib/libESubAssunto.so    
cp -f staging/lib/libISubAssunto.so           server/lib/libISubAssunto.so    
cp -f staging/lib/libLSubAssunto.so           server/lib/libLSubAssunto.so    
cp -f staging/lib/libAValorPossivel.so        server/lib/libAValorPossivel.so 
cp -f staging/lib/libEValorPossivel.so        server/lib/libEValorPossivel.so 
cp -f staging/lib/libIValorPossivel.so        server/lib/libIValorPossivel.so 
cp -f staging/lib/libLValorPossivel.so        server/lib/libLValorPossivel.so 
cp -f staging/lib/libListValorPossID.so       server/lib/libListValorPossID.so
cp -f staging/lib/libGetUrlRouter.so          server/lib/libGetUrlRouter.so   

echo Copiando servidores para server...
cp -f staging/bin/UrlRouter                   server/bin/UrlRouter       
cp -f staging/bin/admValorPossivel            server/bin/admValorPossivel
cp -f staging/bin/admSubAssunto               server/bin/admSubAssunto   
cp -f staging/bin/admConteudo                 server/bin/admConteudo     
cp -f staging/bin/admPermissao                server/bin/admPermissao    
cp -f staging/bin/CadCompCli                  server/bin/CadCompCli      
cp -f staging/bin/LupaCarteira                server/bin/LupaCarteira    
cp -f staging/bin/LupaCliente                 server/bin/LupaCliente     
cp -f staging/bin/LupaLinha                   server/bin/LupaLinha       
cp -f staging/bin/PesquisaEndereco            server/bin/PesquisaEndereco
cp -f staging/bin/PesquisaTI                  server/bin/PesquisaTI      
cp -f staging/bin/TelaInicial                 server/bin/TelaInicial     
cp -f staging/bin/admAssunto                  server/bin/admAssunto      
cp -f staging/bin/admAtributo                 server/bin/admAtributo     


echo Derrubando servidores...

tmshutdown -k KILL -s UrlRouter       
tmshutdown -k KILL -s admValorPossivel
tmshutdown -k KILL -s admSubAssunto   
tmshutdown -k KILL -s admConteudo     
tmshutdown -k KILL -s admPermissao    
tmshutdown -k KILL -s CadCompCli      
tmshutdown -k KILL -s LupaCarteira    
tmshutdown -k KILL -s LupaCliente     
tmshutdown -k KILL -s LupaLinha       
tmshutdown -k KILL -s PesquisaEndereco
tmshutdown -k KILL -s PesquisaTI      
tmshutdown -k KILL -s TelaInicial     
tmshutdown -k KILL -s admAssunto      
tmshutdown -k KILL -s admAtributo     

echo Levantando servidores...

tmboot -s UrlRouter       
tmboot -s admValorPossivel
tmboot -s admSubAssunto   
tmboot -s admConteudo     
tmboot -s admPermissao    
tmboot -s CadCompCli      
tmboot -s LupaCarteira    
tmboot -s LupaCliente     
tmboot -s LupaLinha       
tmboot -s PesquisaEndereco
tmboot -s PesquisaTI      
tmboot -s TelaInicial     
tmboot -s admAssunto      
tmboot -s admAtributo     