INSERT INTO TB_LABORATORY (name)  VALUES ('LABORATORIO XPTO LTDA');
INSERT INTO TB_LABORATORY (name) VALUES ('LABORATORIO XYZ LTDA');

INSERT INTO TB_PROPERTY_INFO (name) VALUES ('Nome exemplo fazenda');
INSERT INTO TB_PROPERTY_INFO (name) VALUES ('Nome exemplo fazenda 2');

INSERT INTO TB_COMPANY  (name, cnpj, initial_date, final_date, observation, laboratory_id, property_info_id)
    VALUES ('Teste Companhia LTDA', '51.671.343/0001-19', TIMESTAMP WITH TIME ZONE '2023-04-13T00:09:00Z',
                TIMESTAMP WITH TIME ZONE '2023-04-13T01:09:00Z','Teste de dados',2,1 );

        INSERT INTO TB_COMPANY  (name, cnpj, initial_date, final_date, observation, laboratory_id, property_info_id)
VALUES ('Teste Companhia LTDA', '05.846.881/0001-80', TIMESTAMP WITH TIME ZONE '2023-04-13T00:15:00Z',
    TIMESTAMP WITH TIME ZONE '2023-04-13T02:19:00Z','Teste de dados',1,2 );