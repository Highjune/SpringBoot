--
--
--
--
ALTER SESSION SET "_ORACLE_SCRIPT"=true;

CREATE USER membership IDENTIFIED BY membership
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP;

ALTER USER membership
DEFAULT TABLESPACE USERS
QUOTA UNLIMITED ON USERS;

GRANT resource, connect TO membership;

conn membership/membership;

CREATE TABLE Member(
	userid        VARCHAR2(20),
	passwd      VARCHAR2(20)  CONSTRAINT member_passwd_nn  NOT NULL,
	name        VARCHAR2(20)  CONSTRAINT  member_name_nn   NOT NULL,
	age           NUMBER(2)   CONSTRAINT member_age_nn     NOT NULL,
	gender      CHAR(1)       CONSTRAINT member_gender_nn  NOT NULL,
	city       VARCHAR2(30)   CONSTRAINT member_city_nn    NOT NULL,
	CONSTRAINT member_userid_pk  PRIMARY KEY(userid),
	CONSTRAINT  member_age_ck CHECK(age BETWEEN 20 AND 65),
	CONSTRAINT  member_gender_ck  CHECK(gender IN ('1', '0')),
	CONSTRAINT member_city_ck CHECK(city IN('서울', '부산', '대전', '광주', '대구'))
)


CREATE OR REPLACE PROCEDURE member_insert_sp
(
  v_userid      IN     Member.userid%TYPE,
  v_passwd      IN     Member.passwd%TYPE,
  v_name        IN     Member.name%TYPE,
  v_age         IN     Member.age%TYPE,
  v_gender      IN     Member.gender%TYPE,
  v_city        IN     Member.city%TYPE
)
IS
BEGIN
  INSERT INTO Member
  VALUES(v_userid, v_passwd, v_name, v_age, v_gender, v_city);
END;

CREATE OR REPLACE PROCEDURE member_select_all_sp
(
  member_record     OUT     SYS_REFCURSOR
)
AS
BEGIN
  OPEN member_record FOR
    SELECT userid, name, age, gender, city
    FROM Member
    ORDER BY userid ASC;
END;

CREATE OR REPLACE PROCEDURE member_select_sp
(
  v_userid          IN      Member.userid%TYPE,
  member_record     OUT     SYS_REFCURSOR
)
AS
BEGIN
  OPEN member_record FOR
    SELECT userid, name, age, gender, city
    FROM Member
    WHERE userid = v_userid;
END;


CREATE OR REPLACE PROCEDURE member_delete_sp
(
  v_userid    IN      Member.userid%TYPE
)
IS
BEGIN
  DELETE FROM Member
  WHERE userid = v_userid;
END;

CREATE OR REPLACE PROCEDURE member_update_sp
(
  v_age       IN     Member.age%TYPE,
  v_gender       IN     Member.gender%TYPE,
  v_city       IN     Member.city%TYPE,
  v_userid       IN     Member.userid%TYPE
)
IS
BEGIN
  UPDATE Member SET age = v_age, gender = v_gender, city = v_city
  WHERE userid = v_userid;
END;