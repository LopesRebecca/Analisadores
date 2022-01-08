# Analisadores e Conversor

## Visão geral
Pequeno projeto realizado em java, codificação de Analisadores : Lexicos e Sintatico e um Conversor de qualquer fórmulas proposicional em uma equivalente na forma normal conjuntiva.

## Problema
Indicar se a formula escrita pelo usuario é proposicional ou não e converte a formula proposicional em em uma formula equivalente na forma noormla conjuntiva

## Proprosta de solução
Para esse probelma foi implementado dois analisadores, um analisador lexico e outro sintatico verificando se cada elemento da formula está dentro do conjunto de 
biblioteca abordado pela questão e verificar se sua escrita está correta, alem disso, um conversor responsavel por converter  uma formula formula proposicional em em uma formula equivalente na forma noormla conjuntiva

# Informações Técnicas
## Biblioteca utilizada
Notação matematica usada na construção das formulas
<img src = "https://github.com/LopesRebecca/Analisadores/blob/main/biblioteca.jpeg" width="800"><br><sub>Biblioteca das formulas</sub>

## Pré-requisitos
* Sistema Operacional - Linux, Windows, MacOs
* Linguagem Utilizadas
    * Back end - Git e Java, para esse algoritmo foi utilizado o java 11.11.0
    * Front end - Totalmente feito em linha de comando, suas formulas são escritas em arquivo txt contido dentro da pasta `src`
* Arquivos de dados - a linguagem de marcação dos dados deve ser txt, nome do arquivo: `input.txt`
* IDE's : IntelliJ e Spring Tools Suite 4

## Processo de execução
Para executar o algoritmo deve ser aberto o arquivo `App.java` e compilar com a IDE
* caso queira compilar em linha de comando basta digitar no termnal : 
  1. `java c App.java`
  2. `java App`
  
#Testes
###Sugestões de formulas para testes dos analisadores

```
(x # y) & -(-x)
(x & y > z) > (x & q)
(-x) > ( q # r)
-(a & b) # r
(n > r) & ( q # (-r))
```

###Sugestões de formulas para testes da conversão 
```
( x > y)
(--x)
x # y & z
( x > y > z)

```

# Desenvolvedores

## Membros
[Davi Fernandes](https://github.com/Davizex)

[Rebecca Lelis](https://github.com/LopesRebecca)

## Licença

licenciado sob [MIT](https://github.com/erikyryan/trabalho-de-poo/blob/main/LICENSE)
