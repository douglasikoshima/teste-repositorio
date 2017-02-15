#!/apps/perl-5.005_03/bin/perl -w
#ident @(#) P2K: src/cacs/tools/loadDNE/bairro.pm 70.2 09/12/14 14:47:07
# (c) 2007,2008,2009, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   bairro.pm  -  03/23/2004 by aldebaran perseke
#
#   Brazil's address bairro class
################################################################################

package bairro;

use     strict;

################################################################################
#   constants
################################################################################
use constant SUCCESS => 0;
use constant ERROR => 1;

################################################################################
#   construct a new bairro object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_BAIRRO } = undef;
    $self->{ NOM_BAIRRO } = undef;
    $self->{ NOM_ABREV_BAIRRO } = undef;
    $self->{ DAT_ATUALIZACAO } = undef;
    $self->{ USUARIO } = undef;
    $self->{ VER_NBR } = undef;
    $self->{ DAT_CARGA } = undef;

    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_BAIRRO attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_BAIRRO } = shift;
    }

    $self->{ COD_BAIRRO };
}

################################################################################
#   set/get NOM_BAIRRO attribute
################################################################################

sub nome {
    my  $self = shift;

    if( @_ ) {
        $self->{ NOM_BAIRRO } = substr( shift, 0, 50 );
    }

    $self->{ NOM_BAIRRO };
}

################################################################################
#   set/get NOM_ABREV_BAIRRO attribute
################################################################################

sub nomeAbreviado {
    my  $self = shift;

    if( @_ ) {
        $self->{ NOM_ABREV_BAIRRO } = substr( shift, 0, 15 );
    }

    $self->{ NOM_ABREV_BAIRRO };
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

    sprintf qq/codigo: %d\nnome: '%s'\nabreviacao: '%s'\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_BAIRRO } )?$self->{ COD_BAIRRO }:0
            , ( defined $self->{ NOM_BAIRRO } )?$self->{ NOM_BAIRRO }:''
            , ( defined $self->{ NOM_ABREV_BAIRRO } )?$self->{ NOM_ABREV_BAIRRO }:''
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

#   if there's no codigo, insert it

    if( ! defined $self->{ COD_BAIRRO }
            || 0 == $self->{ COD_BAIRRO } ) {

        my  $SQLQuery = <<SQL_QUERY;
insert into
    BAIRRO
( COD_BAIRRO
    , NOM_BAIRRO
    , NOM_ABREV_BAIRRO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA )
values
( SEQ_BAIRRO_01.nextval
    , ?
    , ?
    , $self->{ DAT_ATUALIZACAO }
    , ?
    , '1' 
    , SYSDATE )
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ NOM_BAIRRO }
                , $self->{ NOM_ABREV_BAIRRO }
                , $self->{ USUARIO } )
				or return ERROR;

        $SQLQuery = <<SQL_QUERY;
select
    max( COD_BAIRRO )
from
    BAIRRO
SQL_QUERY
        my  @resultRow;

        $recordSet = $connection->prepare( $SQLQuery );
		$recordSet->execute()
				or return ERROR;

        if( @resultRow = $recordSet->fetchrow_array() ) {
            $self->{ COD_BAIRRO } = $resultRow[ 0 ];
        }
    } else {

        my  $SQLQuery = <<SQL_QUERY;
update
    BAIRRO
set
    NOM_BAIRRO = ?
    , NOM_ABREV_BAIRRO = ?
    , DAT_ATUALIZACAO = $self->{ DAT_ATUALIZACAO }
    , USUARIO = ?
    , VER_NBR = ?
    , DAT_CARGA = SYSDATE
where
    COD_BAIRRO = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ NOM_BAIRRO }
                , $self->{ NOM_ABREV_BAIRRO }
                , $self->{ USUARIO }
                , ($self->{ VER_NBR })+1
                , $self->{ COD_BAIRRO } )
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

    my $self = shift;
    my $connection = shift;
    my $returnCode = SUCCESS;

    if( defined $self->{ COD_BAIRRO } ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from BAIRRO
where COD_BAIRRO = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_BAIRRO } )
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
#   Check whether CEP has relationship
################################################################################
sub hasRelationship {

    my $self = shift;
    my $connection = shift;
    my $returnCode = 0;

    if( defined $self->{ COD_BAIRRO } ) {

        my  $SQLQuery = <<SQL_QUERY;
select nvl(count(*), 0) 
  from bairro b
 where b.cod_bairro = ?
   and (exists (select 1 from rel_cep_lograd_bairro rclb
                where rclb.cod_bairro = b.cod_bairro)
   or exists (select 1 from centro_distribuicao cdd
                where cdd.cod_bairro_cdd = b.cod_bairro))
SQL_QUERY

        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_BAIRRO } )
                or die "error running the query\n$SQLQuery\non database server for object\n"
                        . $self->to_string()
                        . "\n";
        $returnCode = $recordSet->fetchrow_array();
    }
    $returnCode;
}

################################################################################
#   get an object from database by codigo
################################################################################

sub queryByCodigo {
    my  $connection = shift;
    my  $COD_BAIRRO = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_BAIRRO
    , NOM_BAIRRO
    , NOM_ABREV_BAIRRO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    BAIRRO
where
    COD_BAIRRO = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_BAIRRO )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_BAIRRO } = $resultRow[ 0 ];
        $self->{ NOM_BAIRRO } = $resultRow[ 1 ];
        $self->{ NOM_ABREV_BAIRRO } = $resultRow[ 2 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 3 ];
        $self->{ USUARIO } = $resultRow[ 4 ];
        $self->{ VER_NBR } = $resultRow[ 5 ];
        $self->{ DAT_CARGA } = $resultRow[ 6 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

################################################################################
#   get an object from database by nome
################################################################################

sub queryByNome {
    my  $connection = shift;
    my  $NOM_BAIRRO = shift;

#   the select query

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_BAIRRO
    , NOM_BAIRRO
    , NOM_ABREV_BAIRRO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    BAIRRO
where
    NOM_BAIRRO = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $NOM_BAIRRO )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_BAIRRO } = $resultRow[ 0 ];
        $self->{ NOM_BAIRRO } = $resultRow[ 1 ];
        $self->{ NOM_ABREV_BAIRRO } = $resultRow[ 2 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 3 ];
        $self->{ USUARIO } = $resultRow[ 4 ];
        $self->{ VER_NBR } = $resultRow[ 5 ];
        $self->{ DAT_CARGA } = $resultRow[ 6 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

#   return 1 to module requiring or using it

1;

