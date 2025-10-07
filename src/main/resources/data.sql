-- Inserção de ingredientes básicos
INSERT INTO ingredientes (nome, proteinas, carboidratos, gorduras, calorias) VALUES
('Frango', 26.0, 0.0, 3.0, 165.0),
('Arroz branco', 2.7, 28.0, 0.3, 130.0),
('Batata', 2.0, 17.0, 0.1, 77.0),
('Cenoura', 0.9, 10.0, 0.2, 41.0),
('Cebola', 1.1, 9.0, 0.1, 40.0),
('Alho', 6.4, 33.0, 0.5, 149.0),
('Azeite de oliva', 0.0, 0.0, 100.0, 884.0),
('Tomate', 0.9, 3.9, 0.2, 18.0),
('Ovo', 13.0, 1.1, 11.0, 155.0),
('Leite', 3.2, 4.8, 3.2, 61.0),
('Feijão preto', 21.3, 62.4, 1.2, 341.0),
('Banana', 1.1, 22.8, 0.3, 89.0),
('Aveia', 13.0, 67.0, 6.5, 389.0),
('Pão integral', 13.0, 43.0, 3.0, 265.0),
('Queijo mussarela', 28.0, 3.1, 17.0, 280.0);

-- Receita 1: Omelete simples
INSERT INTO receitas (titulo, proteinas, carboidratos, gorduras, calorias, porcoes) VALUES
('Omelete simples', 14.0, 2.0, 12.0, 170.0, 1);

INSERT INTO receita_ingredientes (receita_id, ingredientes) VALUES
(1, '2 ovos'),
(1, '1 colher de chá de azeite'),
(1, 'Sal a gosto'),
(1, '1/4 de cebola picada');

INSERT INTO receita_instrucoes (receita_id, instrucoes) VALUES
(1, 'Bata os ovos em uma tigela'),
(1, 'Adicione sal a gosto'),
(1, 'Aqueça o azeite em uma frigideira'),
(1, 'Refogue a cebola picada'),
(1, 'Despeje os ovos batidos'),
(1, 'Cozinhe até ficar firme');

-- Receita 2: Arroz com frango
INSERT INTO receitas (titulo, proteinas, carboidratos, gorduras, calorias, porcoes) VALUES
('Arroz com frango', 22.0, 30.0, 5.0, 350.0, 2);

INSERT INTO receita_ingredientes (receita_id, ingredientes) VALUES
(2, '200g de peito de frango'),
(2, '1 xícara de arroz'),
(2, '2 xícaras de água'),
(2, '1 cebola média'),
(2, '2 dentes de alho'),
(2, '2 colheres de sopa de azeite'),
(2, 'Sal e pimenta a gosto');

INSERT INTO receita_instrucoes (receita_id, instrucoes) VALUES
(2, 'Corte o frango em cubos e tempere com sal e pimenta'),
(2, 'Aqueça o azeite e doure o alho e a cebola'),
(2, 'Adicione o frango e cozinhe até dourar'),
(2, 'Acrescente o arroz e refogue por 2 minutos'),
(2, 'Adicione a água e cozinhe em fogo baixo até secar');

-- Receita 3: Salada de feijão
INSERT INTO receitas (titulo, proteinas, carboidratos, gorduras, calorias, porcoes) VALUES
('Salada de feijão', 15.0, 40.0, 3.0, 250.0, 4);

INSERT INTO receita_ingredientes (receita_id, ingredientes) VALUES
(3, '2 xícaras de feijão preto cozido'),
(3, '1 tomate picado'),
(3, '1/2 cebola picada'),
(3, '1 cenoura ralada'),
(3, '2 colheres de sopa de azeite'),
(3, 'Suco de 1 limão'),
(3, 'Sal e pimenta a gosto');

INSERT INTO receita_instrucoes (receita_id, instrucoes) VALUES
(3, 'Misture todos os ingredientes em uma tigela grande'),
(3, 'Tempere com azeite, limão, sal e pimenta'),
(3, 'Deixe descansar na geladeira por 30 minutos antes de servir');

-- Receita 4: Mingau de aveia com banana
INSERT INTO receitas (titulo, proteinas, carboidratos, gorduras, calorias, porcoes) VALUES
('Mingau de aveia com banana', 8.0, 45.0, 4.0, 220.0, 1);

INSERT INTO receita_ingredientes (receita_id, ingredientes) VALUES
(4, '1/2 xícara de aveia em flocos'),
(4, '1 xícara de leite'),
(4, '1 banana madura'),
(4, '1 colher de chá de mel'),
(4, 'Canela a gosto');

INSERT INTO receita_instrucoes (receita_id, instrucoes) VALUES
(4, 'Misture a aveia e o leite em uma panela'),
(4, 'Leve ao fogo médio, mexendo constantemente'),
(4, 'Quando começar a engrossar, adicione a banana amassada'),
(4, 'Cozinhe por mais 2-3 minutos até atingir a consistência desejada'),
(4, 'Sirva com mel e canela por cima');

-- Receita 5: Sanduíche de queijo com tomate
INSERT INTO receitas (titulo, proteinas, carboidratos, gorduras, calorias, porcoes) VALUES
('Sanduíche de queijo com tomate', 18.0, 30.0, 15.0, 320.0, 1);

INSERT INTO receita_ingredientes (receita_id, ingredientes) VALUES
(5, '2 fatias de pão integral'),
(5, '2 fatias de queijo mussarela'),
(5, '1 tomate fatiado'),
(5, 'Folhas de alface'),
(5, '1 colher de chá de azeite');

INSERT INTO receita_instrucoes (receita_id, instrucoes) VALUES
(5, 'Toste levemente as fatias de pão'),
(5, 'Monte o sanduíche com queijo, tomate e alface'),
(5, 'Regue com um fio de azeite antes de fechar o sanduíche');