# Sistema Acadêmico

Sistema Acadêmico desenvolvido em Java para a disciplina de Projeto de Programas. O projeto tem como objetivo simular um sistema simples de gerenciamento acadêmico, permitindo o cadastro de alunos, cadastro de disciplinas, lançamento de notas, cálculo de média final e verificação da situação do aluno.

O sistema utiliza a arquitetura **MVCR**, separando as responsabilidades em quatro camadas principais: **Model**, **View**, **Controller** e **Repository**.

---

## Índice

* [Sobre o Projeto](#sobre-o-projeto)
* [Funcionalidades](#funcionalidades)
* [Arquitetura MVCR](#arquitetura-mvcr)
* [Estrutura de Pastas](#estrutura-de-pastas)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)
* [Modelagem das Classes](#modelagem-das-classes)
* [Como Executar o Projeto](#como-executar-o-projeto)
* [Persistência dos Dados](#persistência-dos-dados)
* [Divisão da Equipe](#divisão-da-equipe)
* [Regras de Negócio](#regras-de-negócio)
* [Possíveis Melhorias](#possíveis-melhorias)
* [Autores](#autores)

---

## Sobre o Projeto

O **Sistema Acadêmico** é uma aplicação desktop feita em Java com interface gráfica em Swing.

O objetivo do sistema é permitir que uma instituição acadêmica consiga gerenciar informações básicas de alunos, disciplinas e notas. A partir das notas lançadas, o sistema calcula automaticamente a média final do aluno e informa se ele está aprovado ou reprovado.

Este projeto foi desenvolvido com foco na aplicação dos principais conceitos estudados na disciplina, como:

* Programação orientada a objetos;
* Encapsulamento;
* Classes e objetos;
* Estruturas de decisão;
* Estruturas de repetição;
* Listas dinâmicas com `ArrayList`;
* Interface gráfica com Swing;
* Leitura e gravação de arquivos;
* Organização em camadas usando MVCR.

---

## Funcionalidades

O sistema possui as seguintes funcionalidades principais:

### Alunos

* Cadastrar aluno;
* Listar alunos cadastrados;
* Buscar aluno por matrícula;
* Editar dados de aluno;
* Remover aluno.

### Disciplinas

* Cadastrar disciplina;
* Listar disciplinas cadastradas;
* Buscar disciplina por código;
* Editar dados de disciplina;
* Remover disciplina.

### Notas

* Vincular aluno a uma disciplina;
* Lançar notas para um aluno;
* Armazenar várias notas usando `ArrayList<Double>`;
* Calcular média final;
* Verificar situação do aluno;
* Consultar boletim do aluno.

### Relatórios

* Listar alunos aprovados;
* Listar alunos reprovados;
* Exibir média final por aluno e disciplina;
* Consultar notas por matrícula.

---

## Arquitetura MVCR

O projeto utiliza a arquitetura **MVCR**, composta pelas seguintes camadas:

```text
Model
View
Controller
Repository
```

Cada camada possui uma responsabilidade específica, evitando que o código fique misturado.

---

### Model

A camada **Model** representa os dados do sistema.

Ela contém as classes que modelam as entidades principais, como:

* `Aluno`;
* `Disciplina`;
* `Nota`.

Essa camada não deve conter código de interface gráfica, leitura de arquivos ou regras complexas de controle.

Exemplo de responsabilidade do Model:

```java
Aluno aluno = new Aluno("2024001", "João Silva", "Engenharia de Computação", 2);
```

---

### View

A camada **View** é responsável pela interface gráfica do sistema.

Ela contém as telas feitas com Swing, como:

* Tela principal;
* Tela de cadastro de alunos;
* Tela de cadastro de disciplinas;
* Tela de lançamento de notas;
* Tela de relatórios.

A View deve apenas capturar dados do usuário e exibir informações na tela. Ela não deve salvar arquivos diretamente nem aplicar regras de negócio sozinha.

---

### Controller

A camada **Controller** é responsável por fazer a comunicação entre a View, o Model e o Repository.

Ela recebe os dados da interface, executa validações e chama os métodos corretos do Repository.

Exemplo de responsabilidade do Controller:

```java
public void cadastrarAluno(String matricula, String nome, String curso, int periodo) {
    Aluno aluno = new Aluno(matricula, nome, curso, periodo);
    alunoRepository.salvar(aluno);
}
```

---

### Repository

A camada **Repository** é responsável pela persistência dos dados.

Ela faz a leitura e a gravação dos dados em arquivos, como:

* `alunos.csv`;
* `disciplinas.csv`;
* `notas.csv`.

Essa camada centraliza o acesso aos dados, evitando que a View ou o Controller manipulem arquivos diretamente.

---

## Estrutura de Pastas

A estrutura sugerida para o projeto é:

```text
SistemaAcademico/
│
├── src/
│   │
│   ├── model/
│   │   ├── Aluno.java
│   │   ├── Disciplina.java
│   │   └── Nota.java
│   │
│   ├── repository/
│   │   ├── AlunoRepository.java
│   │   ├── DisciplinaRepository.java
│   │   └── NotaRepository.java
│   │
│   ├── controller/
│   │   ├── AlunoController.java
│   │   ├── DisciplinaController.java
│   │   └── NotaController.java
│   │
│   ├── view/
│   │   ├── TelaPrincipal.java
│   │   ├── TelaAluno.java
│   │   ├── TelaDisciplina.java
│   │   ├── TelaNota.java
│   │   └── TelaRelatorio.java
│   │
│   └── Main.java
│
├── data/
│   ├── alunos.csv
│   ├── disciplinas.csv
│   └── notas.csv
│
└── README.md
```

---

## Tecnologias Utilizadas

* Java;
* Java Swing;
* Programação Orientada a Objetos;
* Arquivos CSV;
* Arquitetura MVCR;
* IDE Java, como NetBeans, IntelliJ IDEA, Eclipse ou VS Code.

---

## Modelagem das Classes

### Classe Aluno

Representa um aluno cadastrado no sistema.

Atributos principais:

```java
private String matricula;
private String nome;
private String curso;
private int periodo;
```

Responsabilidades:

* Guardar os dados do aluno;
* Permitir acesso aos dados por meio de getters e setters;
* Representar o aluno como objeto dentro do sistema.

---

### Classe Disciplina

Representa uma disciplina cadastrada no sistema.

Atributos principais:

```java
private String codigo;
private String nome;
private int cargaHoraria;
```

Responsabilidades:

* Guardar os dados da disciplina;
* Permitir busca pelo código da disciplina;
* Ser utilizada no vínculo entre aluno e notas.

---

### Classe Nota

Representa as notas de um aluno em determinada disciplina.

Atributos principais:

```java
private String matriculaAluno;
private String codigoDisciplina;
private ArrayList<Double> notas;
```

A classe `Nota` utiliza um `ArrayList<Double>` para armazenar as notas, permitindo que o sistema trabalhe com uma quantidade flexível de avaliações.

Exemplo:

```java
ArrayList<Double> notas = new ArrayList<>();
notas.add(8.0);
notas.add(7.5);
notas.add(9.0);
```

Responsabilidades:

* Armazenar as notas de um aluno em uma disciplina;
* Calcular a média das notas;
* Retornar a situação do aluno.

Exemplo de cálculo de média:

```java
public double calcularMedia() {
    if (notas.isEmpty()) {
        return 0.0;
    }

    double soma = 0.0;

    for (double nota : notas) {
        soma += nota;
    }

    return soma / notas.size();
}
```

---

## Como Executar o Projeto

### Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

* JDK instalado na máquina;
* Uma IDE Java, como NetBeans, IntelliJ IDEA, Eclipse ou VS Code.

---

### Executando pela IDE

1. Abra o projeto na IDE escolhida;
2. Verifique se a pasta `src` está configurada corretamente;
3. Execute o arquivo `Main.java`;
4. A tela principal do sistema será aberta.

---

### Executando pelo Terminal

Acesse a pasta do projeto e compile os arquivos:

```bash
javac src/**/*.java
```

Depois execute a classe principal:

```bash
java src/Main
```

Dependendo da organização dos pacotes, pode ser necessário ajustar o comando de execução.

---

## Persistência dos Dados

O sistema utiliza arquivos CSV para armazenar os dados cadastrados.

Os arquivos ficam na pasta:

```text
data/
```

Arquivos utilizados:

```text
alunos.csv
disciplinas.csv
notas.csv
```

### Exemplo de `alunos.csv`

```csv
2024001;João Silva;Engenharia de Computação;2
2024002;Maria Souza;Sistemas de Informação;1
```

### Exemplo de `disciplinas.csv`

```csv
LP2;Linguagem de Programação 2;80
AED1;Algoritmos e Estruturas de Dados 1;80
```

### Exemplo de `notas.csv`

```csv
2024001;LP2;8.0;7.5;9.0
2024002;AED1;6.0;5.5;7.0
```

---

## Divisão da Equipe

O projeto foi dividido entre quatro integrantes, seguindo a arquitetura MVCR.

### Pessoa 1 — Model

Responsável pela criação das classes que representam os dados do sistema.

Arquivos:

```text
model/
├── Aluno.java
├── Disciplina.java
└── Nota.java
```

Atividades:

* Criar os atributos das classes;
* Criar construtores;
* Criar getters e setters;
* Criar métodos auxiliares;
* Implementar o uso de `ArrayList<Double>` na classe `Nota`.

---

### Pessoa 2 — Repository

Responsável pela persistência dos dados em arquivos.

Arquivos:

```text
repository/
├── AlunoRepository.java
├── DisciplinaRepository.java
└── NotaRepository.java
```

Atividades:

* Salvar alunos em arquivo;
* Ler alunos do arquivo;
* Salvar disciplinas em arquivo;
* Ler disciplinas do arquivo;
* Salvar notas em arquivo;
* Ler notas do arquivo;
* Atualizar e remover registros.

---

### Pessoa 3 — Controller

Responsável pelas regras de controle e comunicação entre as camadas.

Arquivos:

```text
controller/
├── AlunoController.java
├── DisciplinaController.java
└── NotaController.java
```

Atividades:

* Controlar cadastro de alunos;
* Controlar cadastro de disciplinas;
* Controlar lançamento de notas;
* Validar dados recebidos da View;
* Calcular média;
* Verificar situação do aluno;
* Chamar os métodos do Repository.

---

### Pessoa 4 — View

Responsável pela interface gráfica do sistema.

Arquivos:

```text
view/
├── TelaPrincipal.java
├── TelaAluno.java
├── TelaDisciplina.java
├── TelaNota.java
└── TelaRelatorio.java
```

Atividades:

* Criar telas usando Swing;
* Criar botões, campos de texto e tabelas;
* Exibir mensagens para o usuário;
* Chamar os métodos dos Controllers;
* Exibir relatórios e consultas.

---

## Regras de Negócio

### Cadastro de Aluno

* A matrícula não pode ser vazia;
* O nome não pode ser vazio;
* A matrícula deve ser única;
* O período deve ser maior que zero.

---

### Cadastro de Disciplina

* O código da disciplina não pode ser vazio;
* O nome da disciplina não pode ser vazio;
* O código deve ser único;
* A carga horária deve ser maior que zero.

---

### Lançamento de Notas

* O aluno precisa estar cadastrado;
* A disciplina precisa estar cadastrada;
* As notas devem estar entre 0 e 10;
* Um aluno pode ter várias notas em uma disciplina;
* As notas devem ser armazenadas em um `ArrayList<Double>`.

---

### Cálculo da Média

A média é calculada pela soma das notas dividida pela quantidade de notas lançadas:

```java
media = somaDasNotas / quantidadeDeNotas;
```

Exemplo:

```text
Notas: 8.0, 7.5, 9.0

Média = (8.0 + 7.5 + 9.0) / 3
Média = 8.16
```

---

### Situação do Aluno

A situação do aluno é definida com base na média final.

```text
Média maior ou igual a 6.0: Aprovado
Média menor que 6.0: Reprovado
```

Exemplo em Java:

```java
if (media >= 6.0) {
    return "Aprovado";
} else {
    return "Reprovado";
}
```

---

## Fluxo de Funcionamento

O fluxo básico do sistema segue a arquitetura MVCR:

```text
Usuário interage com a View
        ↓
View envia dados para o Controller
        ↓
Controller valida e processa os dados
        ↓
Controller usa os Models
        ↓
Repository salva ou busca os dados
        ↓
View exibe o resultado para o usuário
```

Exemplo prático:

```text
1. Usuário digita os dados de um aluno na TelaAluno.
2. TelaAluno envia os dados para AlunoController.
3. AlunoController cria um objeto Aluno.
4. AlunoController chama AlunoRepository.
5. AlunoRepository salva o aluno no arquivo alunos.csv.
6. TelaAluno exibe uma mensagem de sucesso.
```

---

## Exemplo de Uso

### Cadastro de Aluno

Dados informados:

```text
Matrícula: 2024001
Nome: João Silva
Curso: Engenharia de Computação
Período: 2
```

Resultado:

```text
Aluno cadastrado com sucesso.
```

---

### Cadastro de Disciplina

Dados informados:

```text
Código: LP2
Nome: Linguagem de Programação 2
Carga Horária: 80
```

Resultado:

```text
Disciplina cadastrada com sucesso.
```

---

### Lançamento de Notas

Dados informados:

```text
Aluno: 2024001
Disciplina: LP2
Notas: 8.0, 7.5, 9.0
```

Resultado:

```text
Média final: 8.16
Situação: Aprovado
```

---

## Possíveis Melhorias

Algumas melhorias futuras para o sistema:

* Implementar tela de login;
* Criar perfil de administrador;
* Criar perfil de professor;
* Exportar relatórios em PDF;
* Usar banco de dados no lugar de arquivos CSV;
* Adicionar filtro por curso;
* Adicionar filtro por disciplina;
* Criar histórico acadêmico completo;
* Adicionar validação visual nos campos da interface;
* Melhorar o design da interface gráfica.

---

## Autores

Projeto desenvolvido por:

```text
Integrante 1 - Model
Integrante 2 - Repository
Integrante 3 - Controller
Integrante 4 - View
```

Disciplina:

```text
Projeto de Programas
```

Tema escolhido:

```text
Sistema Acadêmico
```

---
