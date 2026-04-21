# MinioStorageProviderClient

## 🎯 Objetivo
Implementar a integração com o provider MinIO, executando operações de baixo nível de armazenamento de objetos.

Essa classe atua como adapter da infraestrutura dentro da arquitetura hexagonal.

---

## 📦 Pacote
io.dougluciano.storages4j.v2.provider

---

## 📛 Classe
MinioStorageProviderClient

---

## 🧩 Responsabilidades

- Enviar objetos para o storage (putObject)
- Recuperar objetos (getObject)
- Remover objetos (removeObject)
- Verificar existência de objetos (exists)
- Gerenciar bucket (verificação e criação)

---

## 🔄 Operações suportadas

- Upload de objetos
- Download de objetos
- Exclusão de objetos
- Verificação de existência
- Verificação de bucket
- Criação de bucket

---

## ⚠️ Regras importantes

- Não conter lógica de negócio
- Não gerar metadata
- Não manipular UUID ou paths
- Não depender de regras da aplicação
- Apenas executar operações técnicas no provider

---

## 🧠 Diretriz arquitetural

- Atua como camada de infraestrutura (adapter)
- Implementa o contrato `StorageProviderClient`
- É utilizada pela camada de serviço
- Deve ser facilmente substituível por outros providers

---

## 🛠️ Logging

- Utiliza SLF4J para logs
- Mensagens centralizadas no enum `LogMessages`
- Logs padronizados para:
    - início de operação
    - sucesso
    - erro

---

## 🎯 Resultado esperado

- Integração funcional com MinIO
- Operações de storage executadas corretamente
- Código desacoplado e alinhado à arquitetura hexagonal