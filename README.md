<h1 align="center">Teste Tecnico - Jera</h1>

<p align="center">Aplicação Web para gerenciamento de lista de filmes🎬</p>

<p align="center">Navegue pelo sumário abaixo</p>
<p align="center">
 <a href="#inicio">Inicio</a> •
 <a href="#pré-requisitos">Pré Requisitos (Instalação)</a> •
 <a href="#como-executar-o-projeto">Como Executar o projeto</a> •
 <a href="#tecnologias">Tecnologias</a> •
 <a href="#autor">Autor</a>
</p>

---
### Inicio
#### 🚀 Regra de negócio

- [x] Para abrir uma conta é necessário apenas um email, nome, senha e data de nascimento.
- [x] Cada conta pode ter até 4 perfis. Ao chegar no limite máximo o registro de novos perfis é barrado.
- [x] Ao logar na conta é mostrado todos os perfis nela cadastrados. e com possibilidade de registrar mais um perfil.
- [x] Cada perfil pode pesquisar filmes e adiciona-los às listas.
- [x] Cada perfil tem duas listas que podem ser visualizadas, uma lista de filmes que ja foram assistidos e uma lista de filmes para serem assistidos.
- [x] Ao adicionar 1 filme na lista de assistidos, se ele ja for cadastrado na lista de planejados, ele é removido da lista de planejados pois já foi visto. 
- [x] Uma conta não tem acesso às informações de outras contas.

### Pré-requisitos
#### 🔧 Instalação e configuração

⚠️ As ferramentas informadas aqui foram as que eu utilizei, fique a vontade para escolher a que for mais familiar a você.

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [IntelliJ IDEA Community Edition](https://www.jetbrains.com/pt-br/idea/download/#section=windows) para executar o projeto de maneira local; 
- [openjdk 21.0.2 2024-01-16 LTS](https://www.oracle.com/java/technologies/downloads/#java21);
- [Sqlite](https://www.sqlite.org/download.html);

Você também precisará fazer a seguinte configuração (siga o caminho abaixo)
- Clique com o botão direito em "Meu Computador";
- Clique em "Propriedades";
- Clique em "Configurações Avançadas do Sistema";
- Clique em "Variáveis de Ambiente";
- Copie o caminho para onde você fez o download do [openjdk 21.0.2 2024-01-16 LTS](https://www.oracle.com/java/technologies/downloads/#java21);
- Em "Variáveis de Ambiente>Variáveis do Sistema" clique em "novo" e insira o nome da variável como sendo "JAVA_HOME" e insira o caminho do [openjdk 21.0.2 2024-01-16 LTS](https://www.oracle.com/java/technologies/downloads/#java21) que você copiou e clique em "OK";
- Ainda em "Variáveis de Ambiente>Variáveis do Sistema" clique em "novo" e insira o nome da variável como sendo "CLASSPATH" e no valor coloque um ponto "." e depois clique em "OK";
- Em "Variáveis do Sistema" localize o "Path" e dê um duplo clique sobre ele, e adicione "%JAVA_HOME%\bin" e mova-o para cima e depois clique em "OK" e novamente em "OK";
- 
- [x] Para validar a instalação do JDK abra o cmd e digite "java --version" e aparecerá a versão do java instalado.


### Como Executar o projeto
#### ⚙️ Executando e Instalando as Dependências

```bash
# Clone este repositório
$ [git clone https://github.com/SalgadoMaff/TesteTecnico]
```
- Abra o [IntelliJ IDEA Community Edition](https://www.jetbrains.com/pt-br/idea/download/#section=windows) que você instalou e depois abra o projeto;
- Localize na parte direita superior lateral uma aba chamada "Maven" e a abra;
- Clique em "Reload All Maven Projects" para sincronizar e baixar as dependências do Maven;
- Após isso, basta clicar na seta verde ou apertar "shift+F10" e a aplicação já estará executando.
- Entre no seu navegador de preferencia e digite na barra de endereço: http://localhost:8080/

##### Demonstração em vídeo:
 [![image](https://github.com/SalgadoMaff/TesteTecnico/assets/53794516/1de56cb4-2a2a-4fc2-82c3-676a2fd9a767)
](https://youtu.be/eDVL3yMCryI)

### Tecnologias
#### 🛠️ Ferramentas

As seguintes ferramentas foram usadas na construção do projeto:

- [IntelliJ IDEA Ultimate Edition](https://www.jetbrains.com/pt-br/idea/download/#section=windows)
- [openjdk 21.0.2 2024-01-16 LTS](https://www.oracle.com/java/technologies/downloads/#java21);
- [Sqlite](https://www.sqlite.org/download.html);

---

### Autor

Made by Felipe Salgado Maffini 
