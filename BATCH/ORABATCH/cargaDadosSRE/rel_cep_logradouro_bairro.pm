#!/apps/perl-5.005_03/bin/perl
#ident @(#) P2K: src/cacs/tools/loadDNE/rel_cep_logradouro_bairro.pm 70.2 09/12/14 14:47:30
# (c) 2007,2008,2009, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   rel_cep_logradouro_bairro.pm  -  04/01/2004 by aldebaran perseke
#
#   Brazil's address rel_cep_logradouro_bairro class
################################################################################

package rel_cep_logradouro_bairro;

use     strict;

use     logradouro;
use     CEP;
use     bairro;

################################################################################
#   constants
################################################################################
use constant SUCCESS => 0;
use constant ERROR => 1;

################################################################################
#   construct a new rel_cep_logradouro_bairro object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_LOGRADOURO } = undef;
    $self->{ COD_CEP } = undef;
    $self->{ NUM_INIC } = undef;
    $self->{ COD_BAIRRO } = undef;
    $self->{ NUM_FINAL } = undef;
    $self->{ IND_LADO_NUMERACAO } = undef;
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

sub codigoLogradouro {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_LOGRADOURO } = shift;
    }

    $self->{ COD_LOGRADOURO };
}

################################################################################
#   set/get COD_CEP attribute
################################################################################

sub codigoCEP {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_CEP } = shift;
    }

    $self->{ COD_CEP };
}

################################################################################
#   set/get NUM_INIC attribute
################################################################################

sub numeroInicial {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_INIC } = substr( shift, 0, 7 );
    }

    $self->{ NUM_INIC };
}

################################################################################
#   set/get COD_BAIRRO attribute
################################################################################

sub codigoBairro {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_BAIRRO } = shift;
    }

    $self->{ COD_BAIRRO };
}

################################################################################
#   set/get NUM_FINAL attribute
################################################################################

sub numeroFinal {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_FINAL } = substr( shift, 0, 7 );
    }

    $self->{ NUM_FINAL };
}

################################################################################
#   set/get IND_LADO_NUMERACAO attribute
################################################################################

sub indLadoNumeracao {
    my  $self = shift;

    if( @_ ) {
        $self->{ IND_LADO_NUMERACAO } = shift;
    }

    $self->{ IND_LADO_NUMERACAO };
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

    sprintf qq/codigo logradouro: %d\ncodigo CEP: %d\nnum inicial: %d\ncodigo bairro: %d\nnum final: %d\nind lado numeracao: %d\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_LOGRADOURO } )?$self->{ COD_LOGRADOURO }:0
            , ( defined $self->{ COD_CEP } )?$self->{ COD_CEP }:0
            , ( defined $self->{ NUM_INIC } )?$self->{ NUM_INIC }:0
            , ( defined $self->{ COD_BAIRRO } )?$self->{ COD_BAIRRO }:0
            , ( defined $self->{ NUM_FINAL } )?$self->{ NUM_FINAL }:0
            , ( defined $self->{ IND_LADO_NUMERACAO } )?$self->{ IND_LADO_NUMERACAO }:0
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

#   try to find the record in the database

    my  $rel_cep_logradouro_bairro;

    $rel_cep_logradouro_bairro = queryByCodigo( $connection
            , $self->{ COD_LOGRADOURO }
            , $self->{ COD_CEP }
            , $self->{ NUM_INIC }
            , $self->{ COD_BAIRRO } );

    if( ! defined $rel_cep_logradouro_bairro
            || 0 == $rel_cep_logradouro_bairro ) {

        my  $SQLQuery = <<SQL_QUERY;
insert into
    REL_CEP_LOGRAD_BAIRRO
( COD_LOGRADOURO
    , COD_CEP
    , NUM_INIC
    , COD_BAIRRO
    , NUM_FINAL
    , IND_LADO_NUMERACAO
    , DAT_ATUALIZACAO
    , USUARIO 
    , VER_NBR
    , DAT_CARGA )
values
( ?
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
        $recordSet->execute( $self->{ COD_LOGRADOURO }
                , $self->{ COD_CEP }
                , $self->{ NUM_INIC }
                , $self->{ COD_BAIRRO }
                , $self->{ NUM_FINAL }
                , $self->{ IND_LADO_NUMERACAO }
                , $self->{ USUARIO } )
            or return ERROR;
    } else {

        my  $SQLQuery = <<SQL_QUERY;
update
    REL_CEP_LOGRAD_BAIRRO
set
    NUM_FINAL = ?
    , IND_LADO_NUMERACAO = ?
    , DAT_ATUALIZACAO = $self->{ DAT_ATUALIZACAO }
    , USUARIO = ?
    , VER_NBR = ?
    , DAT_CARGA = SYSDATE
where
    COD_LOGRADOURO = ?
    and COD_CEP = ?
    and NUM_INIC = ?
    and COD_BAIRRO = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ NUM_FINAL }
                , $self->{ IND_LADO_NUMERACAO }
                , $self->{ USUARIO }
                , ($self->{ VER_NBR })+1
                , $self->{ COD_LOGRADOURO }
                , $self->{ COD_CEP }
                , $self->{ NUM_INIC }
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

    if( defined $self->{ COD_LOGRADOURO }
     && defined $self->{ COD_CEP }
     && defined $self->{ NUM_INIC }
     && defined $self->{ COD_BAIRRO } ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from REL_CEP_LOGRAD_BAIRRO
 where COD_LOGRADOURO = ?
   and COD_CEP = ?
   and NUM_INIC = ?
   and COD_BAIRRO = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_LOGRADOURO },
                             $self->{ COD_CEP },
                             $self->{ NUM_INIC },
                             $self->{ COD_BAIRRO } ) or return ERROR;

        if ($recordSet->rows == 0) {
            $returnCode = ERROR;
        }
    } else {
        $returnCode = ERROR;
    }

    $returnCode;
}

################################################################################
#   delete all rel_cep_lograd_bairro base on cod_logradouro
################################################################################
sub deleteByLogradouro {

    my $connection = shift;
    my $cod_logradouro = shift;
    my $returnCode = SUCCESS;

    if( defined $cod_logradouro ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from REL_CEP_LOGRAD_BAIRRO
 where COD_LOGRADOURO = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $cod_logradouro ) or return ERROR;

        if ($recordSet->rows == 0) {
            $returnCode = ERROR;
        }
    } else {
        $returnCode = ERROR;
    }

    $returnCode;
}

################################################################################
#   delete all rel_cep_lograd_bairro base on cod_logradouro and cod_cep
################################################################################
sub deleteByLogradouroCEP {

    my $connection     = shift;
    my $cod_logradouro = shift;
    my $cod_cep        = shift;
    my $returnCode = SUCCESS;

    if( defined $cod_logradouro  ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from
    REL_CEP_LOGRAD_BAIRRO
where
        COD_LOGRADOURO = ?
    and COD_CEP =?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $cod_logradouro,
                             $cod_cep        ) or return ERROR;

        if ($recordSet->rows == 0) {
            $returnCode = ERROR;
        }
    } else {
        $returnCode = ERROR;
    }

    $returnCode;
}

################################################################################
#   delete all rel_cep_lograd_bairro base on cod_logradouro, cod_cep and cod_bairro
################################################################################
sub deleteByLogradouroCEPBairro {

    my $connection     = shift;
    my $cod_logradouro = shift;
    my $cod_cep        = shift;
    my $cod_bairro     = shift;
    my $returnCode = SUCCESS;

    if( defined $cod_logradouro &&
        defined $cod_cep &&
        defined $cod_bairro ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from
    REL_CEP_LOGRAD_BAIRRO
where
        COD_LOGRADOURO = ?
    and COD_CEP =?
    and COD_BAIRRO =?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $cod_logradouro,
                             $cod_cep,
                             $cod_bairro ) or return ERROR;

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
    my  $COD_CEP = shift;
    my  $NUM_INIC = shift;
    my  $COD_BAIRRO = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOGRADOURO
    , COD_CEP
    , NUM_INIC
    , COD_BAIRRO
    , NUM_FINAL
    , IND_LADO_NUMERACAO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    REL_CEP_LOGRAD_BAIRRO
where
    COD_LOGRADOURO = ?
    and COD_CEP = ?
    and NUM_INIC = ?
    and COD_BAIRRO = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_LOGRADOURO
            , $COD_CEP
            , $NUM_INIC
            , $COD_BAIRRO )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_LOGRADOURO } = $resultRow[ 0 ];
        $self->{ COD_CEP } = $resultRow[ 1 ];
        $self->{ NUM_INIC } = $resultRow[ 2 ];
        $self->{ COD_BAIRRO } = $resultRow[ 3 ];
        $self->{ NUM_FINAL } = $resultRow[ 4 ];
        $self->{ IND_LADO_NUMERACAO } = $resultRow[ 5 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 6 ];
        $self->{ USUARIO } = $resultRow[ 7 ];
        $self->{ VER_NBR } = $resultRow[ 8 ];
        $self->{ DAT_CARGA } = $resultRow[ 9 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

################################################################################
#   get all objects from database by cod_logradouro
################################################################################
sub queryByLogradouro {
    my  $connection = shift;
    my  $cod_logradouro = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOGRADOURO
    , COD_CEP
    , NUM_INIC
    , COD_BAIRRO
    , NUM_FINAL
    , IND_LADO_NUMERACAO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    REL_CEP_LOGRAD_BAIRRO
where
    COD_LOGRADOURO = ?
SQL_QUERY

    my  $recordSet;
    my  @resultRow;
    my  @container = ();

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $cod_logradouro )
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {

        my  $new = {};

        $new->{ COD_LOGRADOURO } = $resultRow[ 0 ];
        $new->{ COD_CEP } = $resultRow[ 1 ];
        $new->{ NUM_INIC } = $resultRow[ 2 ];
        $new->{ COD_BAIRRO } = $resultRow[ 3 ];
        $new->{ NUM_FINAL } = $resultRow[ 4 ];
        $new->{ IND_LADO_NUMERACAO } = $resultRow[ 5 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 6 ];
        $new->{ USUARIO } = $resultRow[ 7 ];
        $new->{ VER_NBR } = $resultRow[ 8 ];
        $new->{ DAT_CARGA } = $resultRow[ 9 ];

        bless( $new );

        push(@container, $new);
    }

    $recordSet->finish();

    @container;
}

################################################################################
#   get all objects from database by cod_cep
################################################################################
sub queryByCEP {
    my  $connection = shift;
    my  $cod_cep = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOGRADOURO
    , COD_CEP
    , NUM_INIC
    , COD_BAIRRO
    , NUM_FINAL
    , IND_LADO_NUMERACAO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    REL_CEP_LOGRAD_BAIRRO
where
    COD_CEP = ?
SQL_QUERY

    my  $recordSet;
    my  @resultRow;
    my  @container = ();

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $cod_cep )
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {

        my  $new = {};

        $new->{ COD_LOGRADOURO } = $resultRow[ 0 ];
        $new->{ COD_CEP } = $resultRow[ 1 ];
        $new->{ NUM_INIC } = $resultRow[ 2 ];
        $new->{ COD_BAIRRO } = $resultRow[ 3 ];
        $new->{ NUM_FINAL } = $resultRow[ 4 ];
        $new->{ IND_LADO_NUMERACAO } = $resultRow[ 5 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 6 ];
        $new->{ USUARIO } = $resultRow[ 7 ];
        $new->{ VER_NBR } = $resultRow[ 8 ];
        $new->{ DAT_CARGA } = $resultRow[ 9 ];

        bless( $new );

        push(@container, $new);
    }

    $recordSet->finish();

    @container;
}

################################################################################
#   get all objects from database by cod_logradouro and cod_cep
################################################################################
sub queryByLogradouroCEP {
    my $connection     = shift;
    my $cod_logradouro = shift;
    my $cod_cep        = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOGRADOURO
    , COD_CEP
    , NUM_INIC
    , COD_BAIRRO
    , NUM_FINAL
    , IND_LADO_NUMERACAO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    REL_CEP_LOGRAD_BAIRRO
where
        COD_LOGRADOURO = ?
    and COD_CEP = ?
SQL_QUERY

    my  $recordSet;
    my  @resultRow;
    my  @container = ();

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $cod_logradouro,
                         $cod_cep )
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {

        my  $new = {};

        $new->{ COD_LOGRADOURO } = $resultRow[ 0 ];
        $new->{ COD_CEP } = $resultRow[ 1 ];
        $new->{ NUM_INIC } = $resultRow[ 2 ];
        $new->{ COD_BAIRRO } = $resultRow[ 3 ];
        $new->{ NUM_FINAL } = $resultRow[ 4 ];
        $new->{ IND_LADO_NUMERACAO } = $resultRow[ 5 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 6 ];
        $new->{ USUARIO } = $resultRow[ 7 ];
        $new->{ VER_NBR } = $resultRow[ 8 ];
        $new->{ DAT_CARGA } = $resultRow[ 9 ];

        bless( $new );

        push(@container, $new);
    }

    $recordSet->finish();

    @container;
}

################################################################################
#   get all objects from database by cod_logradouro, cod_cep and cod_bairro
################################################################################
sub queryByLogradouroCEPBairro {
    my $connection     = shift;
    my $cod_logradouro = shift;
    my $cod_cep        = shift;
    my $cod_bairro     = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_LOGRADOURO
    , COD_CEP
    , NUM_INIC
    , COD_BAIRRO
    , NUM_FINAL
    , IND_LADO_NUMERACAO
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    REL_CEP_LOGRAD_BAIRRO
where
        COD_LOGRADOURO = ?
    and COD_CEP = ?
    and COD_BAIRRO = ?
SQL_QUERY

    my  $recordSet;
    my  @resultRow;
    my  @container = ();

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $cod_logradouro,
                         $cod_cep,
                         $cod_bairro )
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {

        my  $new = {};

        $new->{ COD_LOGRADOURO } = $resultRow[ 0 ];
        $new->{ COD_CEP } = $resultRow[ 1 ];
        $new->{ NUM_INIC } = $resultRow[ 2 ];
        $new->{ COD_BAIRRO } = $resultRow[ 3 ];
        $new->{ NUM_FINAL } = $resultRow[ 4 ];
        $new->{ IND_LADO_NUMERACAO } = $resultRow[ 5 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 6 ];
        $new->{ USUARIO } = $resultRow[ 7 ];
        $new->{ VER_NBR } = $resultRow[ 8 ];
        $new->{ DAT_CARGA } = $resultRow[ 9 ];

        bless( $new );

        push(@container, $new);
    }

    $recordSet->finish();

    @container;
}

#   return 1 to module requiring or using it

1;


