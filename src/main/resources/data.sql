-- Carga inicial de dados para desenvolvimento/testes com o perfil H2 (spring.profiles.active=h2).
-- Executado automaticamente após o Hibernate criar o schema (ddl-auto: create-drop),
-- graças a spring.jpa.defer-datasource-initialization=true em application-h2.yaml.
-- Não roda contra o MySQL (spring.sql.init.mode=embedded é o padrão do Spring Boot).

-- ================= INSUMOS =================
INSERT INTO insumo (id, nome, ativo, preco, tipo) VALUES
  (1, 'Farinha de Trigo', true, 6.50, 'KG'),
  (2, 'Açúcar Refinado', true, 4.20, 'KG'),
  (3, 'Cacau em Pó', true, 28.00, 'KG'),
  (4, 'Corante Vermelho', true, 45.00, 'L'),
  (5, 'Manteiga', true, 32.00, 'KG'),
  (6, 'Leite', true, 5.50, 'L'),
  (7, 'Fermento em Pó', true, 18.00, 'KG'),
  (8, 'Cream Cheese', true, 38.00, 'KG'),
  (9, 'Açúcar de Confeiteiro', true, 9.00, 'KG'),
  (10, 'Confeitos Coloridos', true, 60.00, 'KG');

-- ================= PRODUTOS =================
-- O valor de "preco" abaixo é só um placeholder: a API sempre recalcula custo/preco
-- em tempo real (Produto.calcularCusto/calcularPreco), nunca lê essa coluna de volta.
INSERT INTO produto (id, nome, ativo, preco) VALUES
  (1, 'Massa Red Velvet', true, 0.00),
  (2, 'Cobertura Cream Cheese', true, 0.00),
  (3, 'Bolo Red Velvet', true, 0.00);

-- ================= FICHA TÉCNICA: Massa Red Velvet (produto 1) =================
-- Quantidades propositalmente em unidade diferente da nativa do insumo (G para
-- insumos cadastrados em KG, ML para os cadastrados em L) para exercitar a conversão
-- de unidade em ConversorUnidade. "preco" aqui é sempre o preço do insumo por unidade
-- nativa (snapshot no momento do vínculo), igual ao que Produto.addInsumo grava.
-- Custo resultante: 3,25 + 1,26 + 0,84 + 0,90 + 4,80 + 1,10 + 0,18 = R$ 12,33
INSERT INTO insumo_produto (produto_id, insumo_id, quantidade, preco, tipo) VALUES
  (1, 1, 500, 6.50, 'G'),   -- 500g de farinha (cadastrada em KG) = R$ 3,25
  (1, 2, 300, 4.20, 'G'),   -- 300g de açúcar (cadastrado em KG) = R$ 1,26
  (1, 3, 30, 28.00, 'G'),   -- 30g de cacau (cadastrado em KG) = R$ 0,84
  (1, 4, 20, 45.00, 'ML'),  -- 20ml de corante (cadastrado em L) = R$ 0,90
  (1, 5, 150, 32.00, 'G'),  -- 150g de manteiga (cadastrada em KG) = R$ 4,80
  (1, 6, 200, 5.50, 'ML'),  -- 200ml de leite (cadastrado em L) = R$ 1,10
  (1, 7, 10, 18.00, 'G');   -- 10g de fermento (cadastrado em KG) = R$ 0,18

-- ================= FICHA TÉCNICA: Cobertura Cream Cheese (produto 2) =================
-- Custo resultante: 15,20 + 1,35 + 3,20 = R$ 19,75
INSERT INTO insumo_produto (produto_id, insumo_id, quantidade, preco, tipo) VALUES
  (2, 8, 400, 38.00, 'G'),  -- 400g de cream cheese (cadastrado em KG) = R$ 15,20
  (2, 9, 150, 9.00, 'G'),   -- 150g de açúcar de confeiteiro (cadastrado em KG) = R$ 1,35
  (2, 5, 100, 32.00, 'G');  -- 100g de manteiga (cadastrada em KG) = R$ 3,20

-- ================= FICHA TÉCNICA: Bolo Red Velvet (produto 3) =================
-- Combina dois produtos-componentes (Massa e Cobertura) + um insumo direto (decoração).
-- "preco" aqui é o custo puro (sem margem) do produto-filho no momento do vínculo,
-- igual ao que ProdutoServiceImpl.montarComponentes grava (produtoFilho.calcularCusto()).
INSERT INTO produto_componente (produto_pai_id, produto_filho_id, quantidade, preco, tipo) VALUES
  (3, 1, 1, 12.33, 'KG'),  -- 1x Massa Red Velvet
  (3, 2, 1, 19.75, 'KG');  -- 1x Cobertura Cream Cheese

INSERT INTO insumo_produto (produto_id, insumo_id, quantidade, preco, tipo) VALUES
  (3, 10, 20, 60.00, 'G'); -- 20g de confeitos coloridos para decoração = R$ 1,20

-- Custo total do Bolo Red Velvet: 12,33 + 19,75 + 1,20 = R$ 33,28
-- Com a margem de 40% configurada abaixo, preço final = R$ 46,59

-- ================= CONFIGURAÇÃO (margem de lucro global) =================
INSERT INTO configuracao (id, margem_percentual) VALUES
  (1, 40.00);
