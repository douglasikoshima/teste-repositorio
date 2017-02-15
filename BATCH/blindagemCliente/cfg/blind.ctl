Load data
append INTO TABLE load.clienteblindagem
( cdarearegistro  POSITION(1:2),
  nrlinha POSITION(3:10),
  dtvalidade POSITION(11:20) "to_date(:dtvalidade,'dd/mm/yyyy')"
 )