set echo off
set feed off
set pages 0
set lines 10000
set trimspool on
set termout off

spool ../data/cargo.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
			cg.idcargo
	||'|'||	cg.nmcargo
	from	acesso.cargo cg;

spool off

spool ../data/ddd.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
			ar.idarearegistro
	||'|'||	ar.cdarearegistro
	from	apoio.arearegistro ar;

spool off

spool ../data/departamento.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
  dep.iddepartamento
	||'|'||	dep.nmdepartamento
  from	organograma.departamento dep;

spool off

spool ../data/fornecedor.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
			fn.idfornecedorconsultoratd
	||'|'||	fn.dsfornecedorconsultoratd
	from	apoio.fornecedorconsultoratd fn;

spool off

spool ../data/hierarquia.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
	nv.idnivel
	||'|'||	nv.dsnivel
	from	organograma.nivel nv;

spool off

spool ../data/organizacao.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
  tporg.idtipoorganizacao
	||'|'||	tporg.dstipoorganizacao
  from	organograma.tipoorganizacao tporg;

spool off

spool ../data/pais.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
	pa.idpais
	||'|'||	pa.nmpais
	from	apoio.pais pa;

spool off

spool ../data/perfil.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
	pcatd.idperfilconsultoratd
	||'|'||	pcatd.dsperfilconsultoratd
	from	apoio.perfilconsultoratd pcatd;

spool off

spool ../data/site.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
	scatd.idsiteconsultoratd
	||'|'||	scatd.dssiteconsultoratd
	from	apoio.siteconsultoratd scatd;

spool off

spool ../data/uf.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
	uf.iduf
	||'|'||	uf.nmuf
	from	apoio.uf uf;

spool off

spool ../data/usuario.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
	susr.idstatususuario
	||'|'||	susr.dsstatususuario
	from	apoio.statususuario susr;

spool off

spool ../data/regionais.csv

	select /*+ PARALLEL(tmp 8) PARALLEL(pec 8) */
	uf.iduf
	||'|'||	uf.sguf||' - '||uf.nmuf
	from	apoio.uf uf
	where inpreenchelista = 1;

spool off

spool ../data/template.csv

	select sysdate from dual;

spool off

spool ../data/usuarioIDM.csv

	select sysdate from dual;

spool off

exit
