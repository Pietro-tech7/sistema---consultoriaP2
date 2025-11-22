
Consultoria — Sistema de Gestão

Projeto simples em Java para gerenciamento de clientes, consultores, contratos, projetos,
etapas e pagamentos, utilizando arquivos JSON como sistema de persistência.

Tecnologias Utilizadas

Java 21

Maven

Gson (JSON)

IntelliJ IDEA

Estrutura do Projeto

src/
└── main/
└── java/com/consultoria/
├── app/          → Classe Main
├── model/        → Entidades (Cliente, Projeto, Consultor…)
├── service/      → Regras de negócio
└── repository/   → JsonRepository (persistência)
data/
└── .json                  → Arquivos de armazenamento
pom.xml

Como Compilar o Projeto

No IntelliJ:

1. Abra o painel do Maven
   View → Tool Windows → Maven


2. Acesse:

consultoria
└── Lifecycle


3. Clique em:

clean

install

Como Executar

1. Abra Main.java em
   src/main/java/com/consultoria/app/Main.java


2. Clique em Run


Funcionalidades

Cadastro e listagem de clientes

Clientes VIP e sistema de fidelidade

Cadastro de consultores

Criação de contratos

Criação de projetos e etapas

Registro de pagamentos

Relatório VIP com dados completos


Persistência (JSON)

Os dados são armazenados na pasta:

/data

Com arquivos como:

clientes.json  
consultores.json  
projetos.json  
contratos.json  
etapas.json     
pagamentos.json


//Fiz pelo celular, por isso das setas
