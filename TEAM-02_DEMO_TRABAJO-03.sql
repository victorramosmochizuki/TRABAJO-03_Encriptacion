
--Otorgamos privilegios
grant all privileges to DEMOT3;

--Otorgamos permisos desde el usuario sys
grant execute on sys.dbms_crypto to DEMOT3;


create table code(
usuario number,
clave varchar2(20));


insert into code values (1, 'abc123');
insert into code values (2, 'abc654');
insert into code values (3, 'abc783');
insert into code values (4, 'abc153');
insert into code values (5, 'abc683');


create or replace package cryptit is 
function encrypt_data(p_data in varchar2)return raw deterministic;
function decrypt_data(p_data in raw)return varchar2 deterministic;
end cryptit;
/


create or replace package body cryptit is
v_key raw(128) := utl_raw.cast_to_raw('testkey1');
----------------------------------------------------------------------------
function encrypt_data(p_data in varchar2) return raw deterministic
is
l_data raw(208) := utl_raw.cast_to_raw(p_data);
l_encrypted raw(2048);
begin
null;
l_encrypted := dbms_crypto.encrypt
(src=> l_data,
    typ => dbms_crypto.des_cbc_pkcs5,
key => v_key);
return l_encrypted;
end encrypt_data;
-------------------------------------------------------------------------
function decrypt_data(p_data in raw) return varchar2 deterministic
is
l_decrypted raw(2048);
begin
l_decrypted := dbms_crypto.decrypt
(src=> p_data,
    typ => dbms_crypto.des_cbc_pkcs5,
key => v_key);
return utl_raw.cast_to_varchar2(l_decrypted);
end decrypt_data;
end cryptit;
/

update code set clave=cryptit.encrypt_data(clave);
select * from code;

update code set clave=cryptit.decrypt_data(clave);
select * from code;


