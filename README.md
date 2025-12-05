Consultoria — Sistema de Gestão

Projeto em Java para gerenciamento de clientes, consultores, contratos, projetos, etapas e pagamentos, com persistência em arquivos JSON.

Tecnologias Utilizadas
Java 21
Maven
Gson (para JSON)
IntelliJ IDEA

Estrutura do Projeto

src/
└── main/
└── java/com/consultoria/
├── app/          → Main
├── model/        → Entidades (Cliente, Consultor, Projeto…)
├── service/      → Regras de negócio
└── repository/   → JsonRepository
data/
└── .json                 → Arquivos de persistência
pom.xml

Como Compilar
No IntelliJ:

View → Tool Windows → Maven
Em Lifecycle, execute:
clean
install

Como Executar

Abra Main.java em:

src/main/java/com/consultoria/app/Main.java
Clique em Run
Funcionalidades Implementadas
 Cadastro completo de clientes (Regular/VIP), consultores (financeiro, gestão, TI) e contratos
 Histórico de contratos anteriores por cliente
 Alocação automática de consultores conforme especialização
 Projetos divididos em etapas (análise, implementação, revisão) com prazos e status
 Atualização de status de etapa com controle de progresso
 Descontos automáticos para VIPs em pagamentos e faturamento
 Relatórios personalizados para VIPs com insights e recomendações
 Programa de fidelidade com acúmulo de pontos
 Histórico detalhado de projetos com sugestões de novos serviços
 Faturas detalhadas por etapa concluída

Persistência
Dados salvos automaticamente na pasta:
/data

Arquivos gerados:

clientes.json
consultores.json
contratos.json
projetos.json
etapas.json
pagamentos.json