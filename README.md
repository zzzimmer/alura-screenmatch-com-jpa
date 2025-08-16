
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


## Aula 01 - Transformar API em WEB
- Foi necessário transformar a API, de uma CommandLineApp para WEB através da 
manipulação do POM, adicionando a dependencia Starter-Web, a qual fornece
um conjunto de recursos para prog web como o TomCat. 
- Adicionar os Controllers à aplicação

## Aula 02 - Fornecendo dados ao Front
- Package by Layer, ou pacotes por camadas.
O Package by Layer é uma abordagem que diz que você deve dividir seu código com base em suas responsabilidades 
funcionais. Isso pode incluir coisas como 'model', 'view', 'controller', e 'repository'. 
Cada camada tem uma responsabilidade específica. Por exemplo, a camada 'view' manipula a interface do usuário, enquanto
a camada 'controller' lidará com a lógica de negócio.

- Os conceitos centrair foram DTO's e CORS. Sendo este, responsável por configurar o contato entre back-end e requisições
- front-end, e aquele, responsável por enviar representações de objetos de forma mais otimizada para o front-end, 
- convertendo objetos em classes record, apropriadas para JSON. Ademais, também vimos live preview e configurações.

## Aula 03 - Mapeando rotas

- Introdução ao pacote Service - Desacoplar repository do controlador
- O controlador 1) recebe a requisição 2) delega para alguém 3) recebe a resposta. Ademais, lida com as coisas que 
acontecem no navegador, senão, está com responsabilidades demais
- A classe service, através da notação @Service, ganha comportamentos referentes
a implementação de regras de negócio. Ademais, também é entendida como classe de acesso a banco, por isso, se injeta
um repository nessa classe.
- Deixar o código mais limpo e organizado. Vimos que a única responsabilidade de um controlador é tratar da comunicação 
e das rotas da API. Assim, ele não deve conter regras de negócio. E para fazer essa divisão, criamos uma classe de 
serviços, a SerieService.

- Utilizar boas práticas de extração de métodos Aplicamos princípios da orientação a objetos, extraindo métodos que eram
comuns no código, facilitando a manutenção.
  Criar uma url fixa para o Controller. Usamos o @RequestMapping para que todas as urls mapeadas pelo controlador de séries 
tenham como prefixo o “/series”.

- Retornar os dados de uma única série. Para buscar uma série, precisamos que seu id seja passado como parâmetro. 
Conhecemos o @PathVariable, que nos auxilia nesse objetivo.

## Aula 04 - 

Trabalhar de forma colaborativa. Vimos que é importante sempre testar exaustivamente o código, principalmente com 
registros diferentes. Somente assim temos a confirmação de que nossas buscas estão corretas.

Passar parâmetros na url. Usamos novamente a anotação @PathVariable e vimos que ela pode ser utilizada tanto com 
números quanto com strings. Para que ela funcione, basta que passemos o nome do parâmetro entre chaves na url do 
@GetMapping, exatamente como ele está declarado na função.

Comparar streams e buscas no banco de dados. Aprendemos que podemos utilizar tanto streams quanto consultas do banco de
dados, não precisamos nos restringir ao uso exclusivo de um deles. Basta que analisemos a complexidade das buscas,
filtros e operações que faremos.

Desenvolver uma aplicação de forma incremental. Ao trabalhar na integração do front com o back-end, identificamos, 
ao longo do tempo, os requisitos necessários para tudo funcionar em conjunto. O trabalho incremental é muito comum no 
ambiente de desenvolvimento.

## Aula 05 -


