-- Generado por Oracle SQL Developer Data Modeler 4.2.0.921
--   en:        2020-04-05 13:15:05 CST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g
ALTER TABLE asesor
 DROP PRIMARY KEY CASCADE;
DROP TABLE asesor CASCADE CONSTRAINTS;

ALTER TABLE cliente
 DROP PRIMARY KEY CASCADE;
DROP TABLE cliente CASCADE CONSTRAINTS;


ALTER TABLE cuenta
 DROP PRIMARY KEY CASCADE;
DROP TABLE cuenta CASCADE CONSTRAINTS;

ALTER TABLE empleado
 DROP PRIMARY KEY CASCADE;
DROP TABLE empleado CASCADE CONSTRAINTS;

ALTER TABLE pago
 DROP PRIMARY KEY CASCADE;
DROP TABLE pago CASCADE CONSTRAINTS;

ALTER TABLE prestamo
 DROP PRIMARY KEY CASCADE;
DROP TABLE prestamo CASCADE CONSTRAINTS;

ALTER TABLE sucursal
 DROP PRIMARY KEY CASCADE;
DROP TABLE sucursal CASCADE CONSTRAINTS;



CREATE TABLE asesor (
    asesor_numcue       VARCHAR2(25 BYTE) NOT NULL,
    asesor_codemp   VARCHAR2(5 BYTE) NOT NULL
);

ALTER TABLE asesor ADD CONSTRAINT asesor_pk PRIMARY KEY ( asesor_numcue,asesor_codemp );

CREATE TABLE cliente (
    cliente_codcli      VARCHAR2(35 BYTE) NOT NULL,
    cliente_nombre      VARCHAR2(200 BYTE),
    cliente_direccion   VARCHAR2(100 BYTE),
	cliente_correo      VARCHAR2(100 BYTE),
	cliente_contrasena	VARCHAR2(100 BYTE)
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( cliente_codcli );

CREATE TABLE cuenta (
    cuenta_numcue            VARCHAR2(25 BYTE) NOT NULL,
    cuenta_saldo             NUMBER,
    cuenta_tipo              VARCHAR2(1 BYTE),
    cuenta_codcli  VARCHAR2(35 BYTE) NOT NULL
);

ALTER TABLE cuenta ADD CONSTRAINT cuenta_pk PRIMARY KEY ( cuenta_numcue );


CREATE TABLE empleado (
    empleado_codemp       VARCHAR2(5 BYTE) NOT NULL,
    empleado_nombre       VARCHAR2(200),
    empleado_antiguedad   NUMBER,
    empleado_fecini       DATE,
    empleado_puesto       VARCHAR2(50 BYTE)
);

ALTER TABLE empleado ADD CONSTRAINT empleado_pk PRIMARY KEY ( empleado_codemp );

CREATE TABLE PAGO 
    ( 
     pago_numpag              NUMBER  NOT NULL , 
     pago_fecha               DATE , 
     pago_importe             VARCHAR2 (100 BYTE) , 
     pago_codpre VARCHAR2 (5 BYTE)  NOT NULL , 
     pago_numero NUMBER (10)  NOT NULL 
    ) 
;

ALTER TABLE pago ADD CONSTRAINT pago_pk PRIMARY KEY ( pago_numpag );

CREATE TABLE PRESTAMO 
    ( 
     prestamo_codpre           VARCHAR2 (5 BYTE)  NOT NULL , 
     prestamo_numero           NUMBER (10)  NOT NULL , 
     prestamo_importe          NUMBER , 
     prestamo_codcli    		VARCHAR2 (4 BYTE)  NOT NULL , 
     prestamo_codsuc 			VARCHAR2 (5 BYTE)  NOT NULL 
    ) 
;

ALTER TABLE prestamo ADD CONSTRAINT prestamo_pk PRIMARY KEY ( prestamo_codpre,prestamo_numero );

CREATE TABLE sucursal (
    sucursal_codsuc   VARCHAR2(5 BYTE) NOT NULL,
    sucursal_nombre   VARCHAR2(200 BYTE),
    sucursal_ciudad   VARCHAR2(100 BYTE)
);

ALTER TABLE sucursal ADD CONSTRAINT sucursal_pk PRIMARY KEY ( sucursal_codsuc );

ALTER TABLE asesor ADD CONSTRAINT asesor_cuenta_fk FOREIGN KEY ( asesor_numcue )
    REFERENCES cuenta ( cuenta_numcue );

ALTER TABLE asesor ADD CONSTRAINT asesor_empleado_fk FOREIGN KEY ( asesor_codemp )
    REFERENCES empleado ( empleado_codemp );

ALTER TABLE cuenta ADD CONSTRAINT cuenta_cliente_fk FOREIGN KEY (cuenta_codcli )
    REFERENCES cliente ( cliente_codcli );

ALTER TABLE pago ADD CONSTRAINT pago_prestamo_fk FOREIGN KEY ( pago_codpre,pago_numero )
    REFERENCES prestamo ( prestamo_codpre,prestamo_numero );

ALTER TABLE prestamo ADD CONSTRAINT prestamo_cliente_fk FOREIGN KEY ( prestamo_codcli )
    REFERENCES cliente ( cliente_codcli );

ALTER TABLE prestamo ADD CONSTRAINT prestamo_sucursual_fk FOREIGN KEY ( prestamo_codsuc )
    REFERENCES sucursal ( sucursal_codsuc );

