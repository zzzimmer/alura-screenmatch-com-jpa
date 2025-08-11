
![Programação-Formação Java](https://github.com/iasminaraujoc/3355-java-screenmatch-com-jpa/assets/84939115/3c51e000-962d-4dc9-97fc-1d384e2511a2)

# Java: persistência de dados e consultas com Spring Data JPA

Projeto desenvolvido no segundo curso da formação Avançando com Java da Alura


## 🔨 Objetivos do projeto

- Evoluir no projeto Screenmatch, iniciado no primeiro curso da formação, criando um menu com várias opções;
- Modelar as abstrações da aplicação através de classes, enums, atributos e métodos;
- Consumir a API do ChatGPT;
- Utilizar o Spring Data JPA para persistir dados no banco;
- Conhecer vários tipos de banco de dados e utilizar o PostgreSQL;
- Trabalhar com vários tipos de consultas ao banco de dados;
- Aprofundar na interface JPARepository


Aula 04 
- Derived Querys JPQL: Recurso que utiliza as consultas padrões da JPA Repository e incrementa
com "filtros" ou aprimoramentos na query. Exemplo buscarSeriePorTitulo() em Principal.
  - A estrutura básica de uma derived query na JPA consiste em:

verbo introdutório + palavra-chave “By” + critérios de busca

Como verbos introdutórios, temos find, read,
query, count e get. Já os critérios são variados.
Palavras relativas à igualdade:

Is, para ver igualdades
Equals, para ver igualdades (essa palavra-chave e a anterior têm os mesmos princípios, e são mais utilizadas para a legibilidade do método).
IsNot, para checar desigualdades
IsNull, para verificar se um parâmetro é nulo
Palavras relativas à similaridade:

Containing, para palavras que contenham um trecho
StartingWith, para palavras que comecem com um trecho
EndingWith, para palavras que terminem com um trecho
Essas palavras podem ser concatenadas com outras condições, como o ContainingIgnoreCase, para não termos problemas de Case Sensitive.
Palavras relacionadas à comparação:

LessThan, para buscar registros menores que um valor
LessThanEqual, para buscar registros menores ou iguais a um valor
GreaterThan, para identificar registros maiores que um valor
GreaterThanEqual, para identificar registros maiores ou iguais a um valor
Between, para saber quais registros estão entre dois valores

- Buscas com multiplos critérios: 
  - Exemplo : findTop10ByOrderByAvaliacaoDesc() 
    - Distinct, para remover dados duplicados
      First, para pegar o primeiro registro
      Top, para limitar o número de dados
    - Criar queries derivadas com a JPA. Conhecemos o recurso padrão da JPA para fazer buscas utilizando palavras-chave em métodos na classe Repository.

Comparar streams e buscas no banco de dados. Percebemos as mudanças em utilizar streams e as derived queries na nossa aplicação.

Conhecer diversas palavras-chave para criar seus métodos. Aprofundamos nas palavras-chave e em como utilizá-las, reforçando a prática.

Discutir os vários tipos de retorno ao realizar as buscas. Conversamos sobre as diferenças entre retornar uma série, uma lista de séries ou um Optional de séries.

Ler dados dinamicamente e armazenar em um Enum. Vimos como fazer a correspondência entre o que está sendo digitado e um campo no enum.

Aula 05 - Introdução a JPQL
- A JPQL é uma linguagem de consulta orientada a objetos que foi definida como parte da especificação JPA (Java Persistence API). 
Ela é usada para fazer consultas em bancos de dados relacionais de maneira similar ao SQL, mas com uma diferença
fundamental: em vez de trabalhar com tabelas e colunas, como no SQL, a JPQL trabalha com classes e atributos que 
fazem parte do seu modelo de domínio.
- *Flexibilidade:* JPQL oferece uma grande flexibilidade na criação de consultas complexas. Você pode usar operadores 
lógicos, funções agregadas, junções e outras funcionalidades para criar consultas que atendam às suas necessidades.
- *Independência do Banco de Dados:* JPQL permite que você escreva consultas que são independentes do banco de 
dados subjacente. Isso significa que você pode trocar de banco de dados sem precisar modificar suas consultas JPQL.
- As estrutoras apontaram para uma possível perca de performance, bem como, da necessidade de refletir qual a ferramenta de banco
mais adequada para cada contexto de aplicação. 
- A principal diferença entre JPQL e SQL Nativo é, portanto, o nível de abstração. A JPQL abstrai os detalhes do banco d
e dados, permitindo que você trabalhe no nível do modelo de domínio. Isso pode tornar o código mais legível e fácil de manter.

Porém, há a questão da portabilidade. Como a JPQL é uma abstração de alto nível, ela é compatível com qualquer banco de
dados que suporte a especificação JPA. Se você precisar mudar seu aplicativo de um banco de dados para outro, a maioria 
das suas consultas JPQL continuarão funcionando sem alterações.

Diferenciar os tipos de consulta da JPA. Vimos que podemos trabalhar com derived queries, com queries nativas usando o nativequery e a JPQL, que é a linguagem de buscas da JPA.
Criar métodos totalmente personalizados e mais legíveis. Vimos que utilizar a JPQL pode auxiliar na escrita de métodos mais legíveis. Para isso, basta escrever o nome do método e anotá-lo com @Query.
Aprofundar em linguagem SQL. Conhecemos várias expressões utilizadas em SQL, como LIKE, ORDER e LIMIT.
Recuperar informações secundárias. Conseguimos buscar informações relacionadas a episódios a partir da série, utilizando o recurso das junções (JOIN).
Comparar recursos SQL e Java. Percebemos que, assim como o Java tem uma API de datas, o SQL também tem sua forma de lidar com datas. No nosso caso, utilizamos a função YEAR do SQL.