# wishlist-api
Desafio WishList

### Sobre

> Rest API wishlist ( lista de desejos )

- Java 17
- Spring Boot ( v.2.7.1 )
- Spring Security
- Gradle 
- Swagguer - ( v.3 )

- Mongo DB


- Mockito
- JUnit ( v.5 )       - testes unitários
- Jacoco ( v.0.8.10 ) - para relatorios de cobertura dos testes

### Versões

A api é disponibilizada em duas versões como um exemplo de versionamento da api.
Esse é um exemplo de como podemos garantir retrocompatibilidade durante atualizações e implementação
de nova features.

- V1 - todas as funcionalidades, **NÃO** possui autenticação
- V2 - todas as funcionalidades, possui autenticação

# Features

**Objetivo**: Api para gerenciar lista de sesejos de clientes, a principio essa api não faz a 
gestão de dados de usuarios ou produtos.

### Adicionar um produto na Wishlist do cliente

> POST: /api/{version}/registry

Para adicionar itens a lista de desejo de um usuario não é necessario um pré cadastro.

Uma vez que os dados são enviados todos os registro são devidamente atualizados.

*PAYLOAD*

```json
    {
      "userEmail": "string - email do user",
      "userName": "string - nome do usuario/customer",
      "userId": "string - identificador unico do usuario (max. 50 digitos)",
      "wishList": [ 
        {
          "productId": "string - identificador unico do produto (max. 50 digitos)",
          "description": "string - descricao do produto",
          "price": 0, // Decimal - valor do produto quando foi adicionado a lista
        }
      ]
    }
```

> GET: /api/{version}/{userId}



Remover um produto da Wishlist do cliente;
Consultar todos os produtos da Wishlist do cliente;
Consultar se um determinado produto está na Wishlist do
cliente;