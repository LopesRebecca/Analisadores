# Analisadores e Conversor

## Visão geral
Pequeno projeto realizado em java, codificação de Analisadores : Lexicos e Sintatico e um Conversor de qualquer fórmulas proposicional em uma equivalente na forma normal conjuntiva.

## Problema
Indicar se a formula escrita pelo usuario é proposicional ou não e converte a formula proposicional em em uma formula equivalente na forma normal conjuntiva

## Proprosta de solução
Para esse probelma foi implementado dois analisadores, um analisador lexico e outro sintatico verificando se cada elemento da formula está dentro do conjunto de 
biblioteca abordado pela questão e verificar se sua escrita está correta, alem disso, um conversor responsavel por converter  uma formula formula proposicional em em uma formula equivalente na forma noormla conjuntiva

# Informações Técnicas
## Biblioteca utilizada
Notação matematica usada na construção das formulas
<img src = "https://github.com/LopesRebecca/Analisadores/blob/main/biblioteca.jpeg" width="900">

*Biblioteca das formulas*

## Informações tecnicas 
* Sistema Operacional - Linux, Windows, MacOs
* Linguagem Utilizadas
    * Back end - Git e Java, para esse algoritmo foi utilizado o java 11.11.0
    * Front end - Totalmente feito em linha de comando, suas formulas são escritas em arquivo txt contido dentro da pasta `src`
* Arquivos de dados - a linguagem de marcação dos dados deve ser txt, nome do arquivo: `input.txt`
* IDE's : IntelliJ e Spring Tools Suite 4

## Processo de execução
Para executar o algoritmo deve ser aberto o arquivo `App.java` e compilar com a IDE
* caso queira compilar em linha de comando basta digitar no termnal : 
  1. `javac App.java`
  2. `java App`
  
# Testes

### Primeira imagem do teste
<img src = "https://github.com/LopesRebecca/Analisadores/blob/main/testes/Captura%20de%20tela_2022-01-07_22-53-13.png" width="800">

*Menu de escolha principal*

### Sugestões de formulas para testes dos analisadores

```
(x # y) & -(-x)
(x & y > z) > (x & q)
(-x) > ( q # r)
-(a & b) # r
(n > r) & ( q # (-r))
```
### Resultado exemplo testes
#### formula de exemplo utilizada: (x > z > z)
<img src = "https://github.com/LopesRebecca/Analisadores/blob/main/testes/Captura%20de%20tela_2022-01-07_22-53-43.png" width="300">

*Resultado do teste de analise*

Observa-se que a string é separada em Tokens e valores,ou seja cada tipo de elemento tem seu Token e o seu valor é como está escrito, sendo da seguinte maneira:
* Token 0 = Letra
* Token 1 = Operações
* Token 2 = Negação
* Token 3 = Parenteses
* Token 4 = Espaço 

```
public static final int TK_LETRA = 0;
public static final int TK_OPERATION = 1;
public static final int TK_NEGATION = 2;
public static final int TK_PARENTHESIS = 3;
public static final int TK_SPACE = 4;
 ```
 *exemplos de Tokens utilizados*

### Sugestões de formulas para testes da conversão 
```
( x > y)
(-x > y) > y
(--x)
x # y & z
( x > y > z)
```

### Resultado exemplo conversão
#### formula de exemplo utilizada: (x > z > z)
<img src = "https://github.com/LopesRebecca/Analisadores/blob/main/testes/Captura%20de%20tela_2022-01-07_22-54-58.png" width="300">

*Resultado do teste de conversão*

Observa-se que:
 1. Primeira mente é realizado a analise de formula
 2. A retirada dos parenteses na formula
 3. Retorna verdadeira caso a formula encaixe em uma das expressões regulares 
 4. Realizado a conversão

```
Pattern implica = Pattern.compile("[a-z][>][a-z]");
Pattern disjuncao = Pattern.compile("[-][a-z][#][a-z]");
Pattern conjucao = Pattern.compile("[-][a-z][&][a-z]");
Pattern negacao = Pattern.compile("[-][-][a-z]");
Pattern morgan = Pattern.compile("[a-z][#][a-z][&][a-z]");
```
 *exemplos de expressões regulares utilizadas*
 
# Aplicação em desenvolvimento:
Possiveis erros de conversão aonde o Pattern as vezes não reconhece a equivalencia entre a formula e a expressão regular

# Desenvolvedores

## Equipe
[Davi Fernandes](https://github.com/Davizex)

[Rebecca Lelis](https://github.com/LopesRebecca)

[Mahouvi Emmanuel](https://github.com/mahouvi4)


## Licença

licenciado sob [MIT](https://github.com/erikyryan/trabalho-de-poo/blob/main/LICENSE)



