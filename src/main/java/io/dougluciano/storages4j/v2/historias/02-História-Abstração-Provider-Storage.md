# 02 — História: Abstração do Provider de Storage

## 🎯 Objetivo
Permitir que a biblioteca se comunique com diferentes provedores de storage de forma desacoplada, através de uma interface comum, sem expor detalhes de implementação ou SDKs específicos.

---

## 🧠 Problema
Atualmente, a comunicação com o storage tende a ficar acoplada a uma implementação específica (ex: MinIO), o que dificulta:
- manutenção
- testes
- troca de provider
- evolução da biblioteca

---

## 🧩 Solução proposta

Criar uma abstração (contrato) que represente operações básicas de storage, permitindo que diferentes providers sejam plugados sem impactar a lógica de negócio.

Essa abstração será responsável apenas por operações técnicas, como envio, recuperação e remoção de objetos.

---

## 📌 Comportamento esperado

- A biblioteca deve interagir com o storage através de uma interface
- A implementação concreta do provider deve ser transparente para o restante do sistema
- Deve ser possível trocar o provider sem alterar a lógica de negócio
- As operações devem ser padronizadas independentemente do provider

---

## 🔄 Fluxo funcional

1. A camada de serviço solicita uma operação (ex: upload)
2. A interface de provider é utilizada para executar a operação
3. A implementação concreta realiza a comunicação com o storage
4. O resultado é retornado de forma padronizada

---

## ⚠️ Regras importantes

- A interface não deve conter lógica de negócio
- A interface não deve conhecer metadata da biblioteca
- A interface não deve conhecer regras de UUID ou estrutura de diretórios
- A interface não deve expor classes do SDK do provider
- A interface deve ser genérica e reutilizável

---

## 🧱 Considerações

- Essa abstração é essencial para suportar múltiplos providers no futuro
- Mantém o core da aplicação isolado de detalhes de infraestrutura
- Facilita testes com mocks ou implementações alternativas

---

## 🎯 Resultado esperado

- Comunicação com storage desacoplada da implementação concreta
- Base preparada para múltiplos providers
- Arquitetura mais limpa, testável e extensível