TESTADO NO  https://www.db-fiddle.com/

------- Painel esquerdo -------
CREATE TABLE TB_USUARIOS (
                cpf VARCHAR(15) NOT NULL,
                nome_funcionario VARCHAR(35),
                orgao VARCHAR(15) NOT NULL,
                cargo VARCHAR(15) NOT NULL,
                CONSTRAINT TB_USUARIOS_pk PRIMARY KEY (cpf)
);

CREATE TABLE TB_CARGOS (
                cargo VARCHAR(15) NOT NULL,
                descricao_cargo VARCHAR(100),
                CONSTRAINT TB_CARGOS_pk PRIMARY KEY (cargo)
);

CREATE TABLE TB_ORGAOS (
                orgao VARCHAR(15) NOT NULL,
                descricao_orgao VARCHAR(30),
                CONSTRAINT TB_ORGAOS_pk PRIMARY KEY (orgao)
);

CREATE TABLE TB_SISTEMAS (
                codigo_sistema VARCHAR(15) NOT NULL,
                descricao_sistema VARCHAR(60) NOT NULL,
                CONSTRAINT TB_SISTEMAS_pk PRIMARY KEY (codigo_sistema)
               
);

CREATE TABLE TB_ACESSOS (
                codigo_sistema VARCHAR(15) NOT NULL,
                cpf VARCHAR(15) NOT NULL,
                CONSTRAINT tb_funcionario_sistema_pk PRIMARY KEY (codigo_sistema, cpf),
                FOREIGN KEY (cpf)  REFERENCES tb_usuarios (cpf),
                FOREIGN KEY (codigo_sistema) REFERENCES tb_sistemas (codigo_sistema) 
               
);


INSERT INTO TB_CARGOS VALUES('ANALISTA','ANALISTA DE SISTEMAS');
INSERT INTO TB_CARGOS VALUES('AUDITOR','AUDITOR FISCAL');
INSERT INTO TB_CARGOS VALUES('GERENTE','GERENTE DE PROJETOS');

INSERT INTO TB_ORGAOS VALUES('SEFAZ','SECRETARIA DA FAZENDA');
INSERT INTO TB_ORGAOS VALUES('SESP','SECRETARIA DA SAUDE PERNAMBUCO');

INSERT INTO TB_USUARIOS VALUES('06622819424','THIAGO ARAUJO','SEFAZ','ANALISTA');
INSERT INTO TB_USUARIOS VALUES('06622819425','FERNADO ARAUJO','SES','GERENTE');

INSERT INTO TB_SISTEMAS VALUES('SEFAZ_IFC','SISTEMA1');
INSERT INTO TB_SISTEMAS VALUES('SEFAZ_EFC','SISTEMA2');

INSERT INTO TB_ACESSOS VALUES('SEFAZ_IFC','06622819424');
INSERT INTO TB_ACESSOS VALUES('SEFAZ_EFC','06622819424');
INSERT INTO TB_ACESSOS VALUES('SEFAZ_IFC','06622819425');
INSERT INTO TB_ACESSOS VALUES('SEFAZ_EFC','06622819425');


CREATE VIEW VIEW_USUARIOS_SISTEMAS AS
	SELECT CPF, string_agg(DISTINCT codigo_sistema, ',') as SISTEMAS FROM TB_ACESSOS GROUP BY CPF;
    
    
-------- PAINEL DIREITO --------------  
SELECT * FROM VIEW_USUARIOS_SISTEMAS;

select (substr(u.cpf, 1, 3) || '.' || substr(u.cpf, 4, 3) || '.' || substr(u.cpf, 7, 3) || '-' || substr(u.cpf, 10, 2))  CPF_FORMATADO, upper(u.nome_funcionario), COALESCE(c.descricao_cargo, '-'), COALESCE(o.descricao_orgao, '-'), COALESCE(v.sistemas, '-') from TB_USUARIOS u
LEFT JOIN VIEW_USUARIOS_SISTEMAS v ON v.cpf = u.cpf
LEFT JOIN tb_cargos c on u.cargo = c.cargo
LEFT JOIN tb_orgaos o on u.orgao = o.orgao





