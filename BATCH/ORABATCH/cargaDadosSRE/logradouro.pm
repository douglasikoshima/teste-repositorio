#!/apps/perl-5.005_03/bin/perl
#ident @(#) P2K: src/cacs/tools/loadDNE/logradouro.pm 70.2 09/12/14 14:45:56
# (c) 2007,2008,2009, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   logradouro.pm  -  03/29/2004 by aldebaran perseke
#
#   Brazil's address logradouro class
################################################################################

package logradouro;

use     strict;

use     tipo_logradouro;
use     titulo_logradouro;
use     localidade;

################################################################################
#   contants
################################################################################
use constant SUCCESS => 0;
use constant ERROR => 1;

################################################################################
#   construct a new logradouro object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_LOGRADOURO } = undef;
    $self->{ COD_TIPO_LOGRAD } = undef;
    $self->{ COD_TITULO_LOGRAD } = undef;
    $self->{ COD_LOCALIDADE } = undef;
    $self->{ NOM_LOGRADOURO } = undef;
    $self->{ NOM_ABREV_LOGRADOURO } = undef;
    $self->{ IND_NUMERACAO_ENDERECO } = undef;
    $self->{ FLG_COMPLEMENTO } = undef;
    $self->{ DAT_ATUALIZACAO } = undef;
    $self->{ USUARIO } = undef;
    $self->{ VER_NBR } = undef;
    $self->{ DAT_CARGA } = undef;

    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_LOGRADOURO attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_LOGRADOURO } = shift;
    }

    $self->{ COD_LOGRADOURO };
}

################################################################################
#   set/get COD_TIPO_LOGRAD attribute
################################################################################

sub codigoTipo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_TIPO_LOGRAD } = shift;
    }

    $self->{ COD_TIPO_LOGRAD };
}

################################################################################
#   set/get COD_TITULO_LOGRAD attribute
################################################################################

sub codigoTitulo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_TITULO_LOGRAD } = shift;
    }

    $self->{ COD_TITULO_LOGRAD };
}

################################################################################
#   set/get COD_LOCALIDADE attribute
################################################################################

sub codigoLocalidade {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_LOCALIDADE } = shift;
    }

    $self->{ COD_LOCALIDADE };
}

################################################################################
#   set/get NOM_LOGRADOURO attribute
################################################################################

sub nome {
    my  $self = shift;

    if( @_ ) {
        $self->{ NOM_LOGRADOURO } = substr( shift, 0, 60 );
    }

    $self->{ NOM_LOGRADOURO };
}

################################################################################
#   set/get NOM_ABREV_LOGRADOURO attribute
################################################################################

sub nomeAbreviado {
    my  $self = shift;

    if( @_ ) {
        $self->{ NOM_ABREV_LOGRADOURO } = substr( shift, 0, 36 );
    }

    $self->{ NOM_ABREV_LOGRADOURO };
}

################################################################################
#   set/get IND_NUMERACAO_ENDERECO attribute
################################################################################

sub indNumeracao {
    my  $self = shift;

    if( @_ ) {
        $self->{ IND_NUMERACAO_ENDERECO } = shift;
    }

    $self->{ IND_NUMERACAO_ENDERECO };
}

################################################################################
#   set/get FLG_COMPLEMENTO attribute
################################################################################

sub flagComplemento {
    my  $self = shift;

    if( @_ ) {
        $self->{ FLG_COMPLEMENTO } = shift;
    }

    $self->{ FLG_COMPLEMENTO };
}

################################################################################
#   set/get DAT_ATUALIZACAO attribute
################################################################################

sub dataAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_ATUALIZACAO } = shift;
    }

    $self->{ DAT_ATUALIZACAO };
}

################################################################################
#   set/get DAT_CARGA attribute
################################################################################

sub dataCarga {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_CARGA } = shift;
    }

    $self->{ DAT_CARGA };
}

################################################################################
#   set/get USUARIO attribute
################################################################################

sub usuarioAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ USUARIO } = shift;
    }

    $self->{ USUARIO };
}

################################################################################
#   set/get VER_NBR attribute
################################################################################

sub verNbr {
    my  $self = shift;

    if( @_ ) {
        $self->{ VER_NBR } = shift;
    }

    $self->{ VER_NBR };
}

################################################################################
#   transform an object in a printable string
################################################################################

sub to_string {
    my  $self = shift;

    sprintf qq/codigo: %d\ncodigo tipo: %d\ncodigo titulo: %d\ncodigo localidade: %d\nnome: '%s'\nnome abreviado: '%s'\nind numeracao: %d\nflag complemento: %d\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_LOGRADOURO } )?$self->{ COD_LOGRADOURO }:0
            , ( defined $self->{ COD_TIPO_LOGRAD } )?$self->{ COD_TIPO_LOGRAD }:0
            , ( defined $self->{ COD_TITULO_LOGRAD } )?$self->{ COD_TITULO_LOGRAD }:0
            , ( defined $self->{ COD_LOCALIDADE } )?$self->{ COD_LOCALIDADE }:0
            , ( defined $self->{ NOM_LOGRADOURO } )?$self->{ NOM_LOGRADOURO }:''
            , ( defined $self->{ NOM_ABREV_LOGRADOURO } )?$self->{ NOM_ABREV_LOGRADOURO }:''
            , ( defined $self->{ IND_NUMERACAO_ENDERECO } )?$self->{ IND_NUMERACAO_ENDERECO }:0
            , ( defined $self->{ FLG_COMPLEMENTO } )?$self->{ FLG_COMPLEMENTO }:0
            , ( defined $self->{ DAT_ATUALIZACAO } )?$self->{ DAT_ATUALIZACAO }:0
            , ( defined $self->{ USUARIO } )?$self->{ USUARIO }:0
            , ( defined $self->{ VER_NBR } )?$self->{ VER_NBR }:0
            , ( defined $self->{ DAT_CARGA } )?$self->{ DAT_CARGA }:0;
}

################################################################################
#   persist the object in database
################################################################################

sub persist {
    my  $self = shift;
    my  $connection = shift;

#   nullable fields

    my  $COD_TITULO_LOGRAD;

    if( defined $self->{ COD_TITULO_LOGRAD } ) {
        $COD_TITULO_LOGRAD = $self->{ COD_TITULO_LOGRAD };
    } else {
        $COD_TITULO_LOGRAD = undef;
    }

#   if there's no codigo, insert it

    if( ! defined $self->{ COD_LOGRADOURO }
            || 0 == $self->{ COD_LOGRADOURO } ) {

        my  $SQLQuery = <<SQL_QUERY;
insert into
    LOGRADOURO
( COD_LOGRADOURO
    , COD_TIPO_LOGRAD
    , COD_TITULO_LOGRAD
    , COD_LOCALIDADE
    , NOM_LOGRADOURO
    , NOM_ABREV_LOGRADOURO
    , IND_NUMERACAO_ENDERECO
    , FLG_COMPLEMENTO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA )
values
( SEQ_LOGRADOU_01.nextval
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , $self->{ DAT_ATUALIZACAO }
    , ?
    , '1'
    , SYSDATE )
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_TIPO_LOGRAD }
                , $COD_TITULO_LOGRAD
                , $self->{ COD_LOCALIDADE }
                , $self->{ NOM_LOGRADOURO }
                , $self->{ NOM_ABREV_LOGRADOURO }
                , $self->{ IND_NUMERACAO_ENDERECO }
                , $self->{ FLG_COMPLEMENTO }
                , $self->{ USUARIO } )
                or return ERROR;

        $SQLQuery = <<SQL_QUERY;
select
    max( COD_LOGRADOURO )
from
    LOGRADOURO
SQL_QUERY
        my  @resultRow;


        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute()
                or return ERROR;

        if( @resultRow = $recordSet->fetchrow_array() ) {
            $self->{ COD_LOGRADOURO } = $resultRow[ 0 ];
        }
    } else {

        my  $SQLQuery = <<SQL_QUERY;
update
    LOGRADOURO
set
    COD_TIPO_LOGRAD = ?
    , COD_TITULO_LOGRAD = ?
    , COD_LOCALIDADE = ?
    , NOM_LOGRADOURO = ?
    , NOM_ABREV_LOGRADOURO = ?
    , IND_NUMERACAO_ENDERECO = ?
    , FLG_COMPLEMENTO = ?
    , DAT_ATUALIZACAO = $self->{ DAT_ATUALIZACAO }
    , USUARIO = ?
    , VER_NBR = ?
    , DAT_CARGA = SYSDATE
where
    COD_LOGRADOURO = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_TIPO_LOGRAD }
                , $COD_TITULO_LOGRAD
                , $self->{ COD_LOCALIDADE }
                , $self->{ NOM_LOGRADOURO }
                , $self->{ NOM_ABREV_LOGRADOURO }
                , $self->{ IND_NUMERACAO_ENDERECO }
                , $self->{ FLG_COMPLEMENTO }
                , $self->{ USUARIO }
                , ($self->{ VER_NBR })+1
                , $self->{ COD_LOGRADOURO } )
              or return ERROR;
        if ($recordSet->rows == 0) {
            return ERROR;
        }
    }

    SUCCESS;
}

################################################################################
#   delete an object from database
################################################################################
sub delete {

    my  $self = shift;
    my  $connection = shift;
    my $returnCode = SUCCESS;

    if( defined $self->{ COD_LOGRADOURO } ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from LOGRADOURO
where COD_LOGRADOURO = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_LOGRADOURO } )
                or return ERROR;

        if ($recordSet->rows == 0) {
            $returnCode = ERROR;
        }
    } else {
        $returnCode = ERROR;
    }

    $returnCode;
}

################################################################################
#   get an object from database by codigo
################################################################################

sub queryByCodigo {
    my  $connection = shift;
    my  $COD_LOGRADOURO = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOGRADOURO
    , COD_TIPO_LOGRAD
    , COD_TITULO_LOGRAD
    , COD_LOCALIDADE
    , NOM_LOGRADOURO
    , IND_NUMERACAO_ENDERECO
    , FLG_COMPLEMENTO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    LOGRADOURO
where
    COD_LOGRADOURO = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_LOGRADOURO )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_LOGRADOURO } = $resultRow[ 0 ];
        $new->{ COD_TIPO_LOGRAD } = $resultRow[ 1 ];
        $new->{ COD_TITULO_LOGRAD } = $resultRow[ 2 ];
        $new->{ COD_LOCALIDADE } = $resultRow[ 3 ];
        $new->{ NOM_LOGRADOURO } = $resultRow[ 4 ];
        $new->{ IND_NUMERACAO_ENDERECO } = $resultRow[ 5 ];
        $new->{ FLG_COMPLEMENTO } = $resultRow[ 6 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 7 ];
        $new->{ USUARIO } = $resultRow[ 8 ];
        $new->{ VER_NBR } = $resultRow[ 9 ];
        $new->{ DAT_CARGA } = $resultRow[ 10 ];

        $recordSet->finish();
        bless( $new );

        $new;
    }
}

################################################################################
#   get an object from database by localidade + (tipo + titulo) nome
################################################################################

sub queryByLocalidadeNome {
    my  $connection = shift;
    my  $localidade = shift;
    my  $tipo_logradouro = shift;
    my  $titulo_logradouro = shift;
    my  $NOM_LOGRADOURO = shift;

#   the select query

    my  $SQLQuery = <<SQL_QUERY;
select
    L.COD_LOGRADOURO
    , L.COD_TIPO_LOGRAD
    , L.COD_TITULO_LOGRAD
    , L.COD_LOCALIDADE
    , L.NOM_LOGRADOURO
    , L.IND_NUMERACAO_ENDERECO
    , L.FLG_COMPLEMENTO
    , L.DAT_ATUALIZACAO
    , L.USUARIO
    , L.VER_NBR
    , L.DAT_CARGA
SQL_QUERY

    my  $SQLFrom = <<SQL_FROM;
from
    LOGRADOURO L
SQL_FROM

    my $SQLWhere = <<SQL_WHERE;
where
    L.COD_LOCALIDADE = ?
    and L.NOM_LOGRADOURO = ?
SQL_WHERE

    if( ! defined $tipo_logradouro ) {

        $SQLWhere .= <<SQL_WHERE;
    and L.COD_TIPO_LOGRAD is null
SQL_WHERE

    } else {

        $SQLFrom .= <<SQL_FROM;
    , TIPO_LOGRADOURO TIPO
SQL_FROM

        $SQLWhere .= <<SQL_WHERE;
    and L.COD_TIPO_LOGRAD = TIPO.COD_TIPO_LOGRAD
    and TIPO.DSC_TIPO_LOGRAD = \'$tipo_logradouro->{ DSC_TIPO_LOGRAD }\'
SQL_WHERE

    }

    if( ! defined $titulo_logradouro ) {

        $SQLWhere .= <<SQL_WHERE;
    and L.COD_TITULO_LOGRAD is null
SQL_WHERE

    } else {

        $SQLFrom .= <<SQL_FROM;
    , TITULO_LOGRADOURO TITULO
SQL_FROM

        $SQLWhere .= <<SQL_WHERE;
    and L.COD_TITULO_LOGRAD = TITULO.COD_TITULO_LOGRAD
    and TITULO.DSC_TITULO_LOGRAD = \'$titulo_logradouro->{ DSC_TITULO_LOGRAD }\'
SQL_WHERE

    }

    $SQLQuery .= $SQLFrom.$SQLWhere;

    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $localidade->codigo()
            , $NOM_LOGRADOURO )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_LOGRADOURO } = $resultRow[ 0 ];
        $new->{ COD_TIPO_LOGRAD } = $resultRow[ 1 ];
        $new->{ COD_TITULO_LOGRAD } = $resultRow[ 2 ];
        $new->{ COD_LOCALIDADE } = $resultRow[ 3 ];
        $new->{ NOM_LOGRADOURO } = $resultRow[ 4 ];
        $new->{ IND_NUMERACAO_ENDERECO } = $resultRow[ 5 ];
        $new->{ FLG_COMPLEMENTO } = $resultRow[ 6 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 7 ];
        $new->{ USUARIO } = $resultRow[ 8 ];
        $new->{ VER_NBR } = $resultRow[ 9 ];
        $new->{ DAT_CARGA } = $resultRow[ 10 ];

        $recordSet->finish();
        bless( $new );

        $new;
    }
}

################################################################################
#   get an object from LOGRADOURO table by localidade + tipo + nome logradouro
################################################################################

sub queryByLocalidadeNomeSTitulo {
    my  $connection      = shift;
    my  $localidade      = shift;
    my  $tipo_logradouro = shift;
    my  $NOM_LOGRADOURO  = shift;

#   the select query

    my  $SQLQuery = <<SQL_QUERY;
select
      L.COD_LOGRADOURO
    , L.COD_TIPO_LOGRAD
    , L.COD_TITULO_LOGRAD
    , L.COD_LOCALIDADE
    , L.NOM_LOGRADOURO
    , L.IND_NUMERACAO_ENDERECO
    , L.FLG_COMPLEMENTO
    , L.DAT_ATUALIZACAO
    , L.USUARIO
    , L.VER_NBR
    , L.DAT_CARGA
SQL_QUERY

    my  $SQLFrom = <<SQL_FROM;
from
    LOGRADOURO L
SQL_FROM

    my $SQLWhere = <<SQL_WHERE;
where
        L.COD_LOCALIDADE = ?
    and L.NOM_LOGRADOURO = ?
SQL_WHERE

    if( ! defined $tipo_logradouro ) {

        $SQLWhere .= <<SQL_WHERE;
    and L.COD_TIPO_LOGRAD is null
SQL_WHERE

    } else {

        $SQLFrom .= <<SQL_FROM;
    , TIPO_LOGRADOURO TIPO
SQL_FROM

        $SQLWhere .= <<SQL_WHERE;
    and L.COD_TIPO_LOGRAD = TIPO.COD_TIPO_LOGRAD
    and TIPO.DSC_TIPO_LOGRAD = \'$tipo_logradouro->{ DSC_TIPO_LOGRAD }\'
SQL_WHERE

    }

    $SQLQuery .= $SQLFrom.$SQLWhere;

    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $localidade->codigo(), $NOM_LOGRADOURO )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_LOGRADOURO }          = $resultRow[ 0 ];
        $new->{ COD_TIPO_LOGRAD }         = $resultRow[ 1 ];
        $new->{ COD_TITULO_LOGRAD }       = $resultRow[ 2 ];
        $new->{ COD_LOCALIDADE }          = $resultRow[ 3 ];
        $new->{ NOM_LOGRADOURO }          = $resultRow[ 4 ];
        $new->{ IND_NUMERACAO_ENDERECO }  = $resultRow[ 5 ];
        $new->{ FLG_COMPLEMENTO }         = $resultRow[ 6 ];
        $new->{ DAT_ATUALIZACAO }         = $resultRow[ 7 ];
        $new->{ USUARIO } = $resultRow[ 8 ];
        $new->{ VER_NBR } = $resultRow[ 9 ];
        $new->{ DAT_CARGA }         = $resultRow[ 10 ];

        $recordSet->finish();
        bless( $new );

        $new;
    }
}

################################################################################
#   Check whether CENTRO_DISTRIBUICAO has relationship
################################################################################
sub hasRelationship {

    my $self       = shift;
    my $connection = shift;
    my $returnCode = 0;

    if( defined $self->{ COD_LOGRADOURO } ) {

        my  $SQLQuery = <<SQL_QUERY;
select
    nvl(count(*), 0)
from
    logradouro l
where
       l.cod_logradouro = ?
   and (    exists (select 1 from centro_distribuicao c
                    where c.cod_lograd_cdd = l.cod_logradouro)
         or exists (select 1 from rel_cep_lograd_bairro rclb
                    where rclb.cod_logradouro = l.cod_logradouro) )
SQL_QUERY

        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_LOGRADOURO } )
                or die "error running the query\n$SQLQuery\non database server for object\n"
                        . $self->to_string()
                        . "\n";
        $returnCode = $recordSet->fetchrow_array();
    }

    $returnCode;
}

#   return 1 to module requiring or using it

1;

