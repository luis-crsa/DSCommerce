# 📦Ecommerce
Aplicação backend de um e-commerce, onde é possível gerenciar usuários, produtos e categorias. O projeto foi desenvolvido durante o curso Java Spring Professional da [DevSuperior](https://devsuperior.com.br/).

## 🛠️ Tecnologias utilizadas  
- [Java](https://www.java.com/)  
- [Spring Boot](https://spring.io/projects/spring-boot)  
- [JPA / Hibernate](https://hibernate.org/)  
- [Spring Security](https://spring.io/projects/spring-security) / [OAuth2](https://oauth.net/2/) / [JWT](https://jwt.io/)  
- [Maven](https://maven.apache.org/)  

## 🗺️Modelo conceitual
![image](https://github.com/user-attachments/assets/189b892e-8bff-440d-97e5-40e2087ccf28)

## 📍 Endpoints da API

### 🪪Auth
- **POST** `/oauth2/token` - Realiza a autenticação do usuário e retorna um token de acesso JWT para ser usado nas requisições subsequentes.
  #### Authorization
  - Basic Auth
  - Username: myclientid
  - Password: myclientsecret 
  #### Body formato `x-www-form-urlencoded`
  - grant_type=password  
  - username={email_do_usuario}  
  - password={senha_do_usuario}  

### 🏷️Category
- **GET** `/categories` - Retorna as categorias existentes

### 🛎️Order
- **GET** `/orders/{id}` - Retorna um as informações de um pedido pelo ID  
- **POST** `/orders` - Salva um novo pedido
  #### Exemplo de corpo de requisição
  ```json
  {
    "items": [
      {
        "productId": 1,
        "quantity": 2
      },
      {
        "productId": 5,
        "quantity": 1
      }
    ]
  }
  ```

### 📦Product
- **GET** `/products` - Retorna informações parciais de todos os produtos  
- **GET** `/products/{id}` - Retorna todas as informações de um produto pelo ID  
- **POST** `/products` - Salva um novo produto  
- **PUT** `/products/{id}` - Atualiza as informações de um produto pelo ID
  #### Exemplo de corpo de requisição
  ```json
  {
      "name": "Meu novo produto",
      "description": "Lorem ipsum, dolor sit amet consectetur adipisicing elit. Qui ad, adipisci illum ipsam velit et odit eaque reprehenderit ex maxime delectus dolore labore, quisquam quae tempora natus esse aliquam veniam doloremque quam minima culpa alias maiores commodi.",
      "imgUrl": "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg",
      "price": 100.0,
      "categories": [
          {
              "id": 2
          },
          {
              "id": 3
          }
      ]
  }
  ```
- **DELETE** `/products/{id}` - Deleta um produto pelo ID  

### 👤User  
- **GET** `/users/me` - Retorna as informações do usuário logado.
  #### Authorization
  - Bearer {token}

## 💻Execução do projeto
Pré-requisitos: Java 21
```bash
# Clone o repositório
git clone https://github.com/luis-crsa/Ecommerce.git

# Acesse a pasta do projeto
cd ecommerce

# Execute o projeto
./mvnw spring-boot:run
```

## 📝Licença  
Este projeto está sob a licença MIT. Para mais informações, acesse o arquivo LICENSE.

# 👨‍💻Autor
Luís Cláudio Rodrigues Sarmento
