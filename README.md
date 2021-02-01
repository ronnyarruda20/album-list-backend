# album-list-backend

 Este projeto tem como base fornercer uma api para gerenciamento de álbuns.

### Pré-requisitos


Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Docker](https://www.docker.com/products/docker-desktop),  [JDK11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html),  [Maven](https://maven.apache.org/download.cgi).

### Rodando o BackEnd 

## Clone este repositório
``` 
git clone https://github.com/ronnyarruda20/album-list-backend
```
## Entre na pasta do projeto
```
cd album-list-backend
```
## Instale as depedencias 
```
mvn clean install
```
## Rode o projeto
```
docker-compose up -d --build
```
### Pronto agora sua api estará disponível [http://localhost:8080](http://localhost:8080)

