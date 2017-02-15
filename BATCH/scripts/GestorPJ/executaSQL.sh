rm extracao_gc_$(date +"%d%m%Y").txt
sqlplus /@tva @executaScriptSQL >> /dev/null
mv resultadoConsultaSQL.txt extracao_gc_$(date +"%d%m%Y").txt