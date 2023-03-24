# Pizzaria - Estudo de Padrões de Projeto em Java

Este é um projeto de estudo de padrões de projeto em Java, que implementa os padrões Singleton, Factory, Adapter, Decorator, Observer e Chain of Responsibility em uma aplicação de pizzaria que tem clientes e pedidos.

## Como executar o projeto

O projeto foi desenvolvido utilizando a versão 11 do Java Development Kit (JDK 11). Certifique-se de ter essa versão ou superior instalada em seu sistema antes de prosseguir.

Para executar o projeto, siga os seguintes passos:

1. Clone o repositório em seu computador: 

```
git clone https://github.com/seu-usuario/pizzaria-java.git
```

2. Navegue até o diretório do projeto:
```
cd pizzaria-java
```

3. Compile o projeto:
```
javac src/main/java/*.java -d build/
```

4. Execute o projeto:
```
java -cp build/ App
```

## Padrões de Projeto implementados

Os seguintes padrões de projeto foram implementados neste projeto:

### Singleton

O padrão Singleton foi utilizado na classe `SaveCsv`, garantindo que apenas uma instância de SaveCsv seja criada durante a execução do programa.

### Factory

O padrão Factory foi utilizado na classe `PizzaFactory`, que é responsável por criar sabores de pizza.

### Adapter

O padrão Adapter foi utilizado na classe `SaveCsvAdapter`, que adapta a interface `ISaveOrder` para a classe `SaveCsv`, permitindo que seja possível fazer o download do pedido em formato .csv

### Decorator

O padrão Decorator foi utilizado na classe `PizzaDecorator`, que adiciona uma borda de catupiry ou cheddar à pizza.

### Observer

O padrão Observer foi utilizado na interface `IOrderObserver` e nas classe `Cliente`, permitindo que o cliente receba a atualização do status do seu pedido.

### Chain of Responsibility

O padrão Chain of Responsibility foi utilizado na interface `IOrderHanalder`, que é responsável por validar os pedidos feitos pelos clientes.


## Contribuindo

Sinta-se à vontade para contribuir com o projeto enviando pull requests ou reportando problemas através das issues. 

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para obter mais informações.
