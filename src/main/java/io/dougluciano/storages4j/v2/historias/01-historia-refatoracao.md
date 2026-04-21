# Refatoração arquitetural — Separação entre Service e Provider

## 🎯 Objetivo
Refatorar a arquitetura da biblioteca para separar claramente as responsabilidades entre **orquestração (Service)** e **integração com provider (Adapter)**, alinhando o projeto a um modelo de arquitetura hexagonal.

## 🧠 Problema atual
- A classe `MinioStorageClient` concentra múltiplas responsabilidades:
    - Comunicação com o provider (MinIO)
    - Orquestração de fluxo (upload + metadata)
    - Regras de negócio (geração de metadata)
- Isso dificulta:
    - Manutenção
    - Testes
    - Evolução para novos providers (ex: S3)

## 🧩 Proposta de solução

### 🔹 Separação de responsabilidades

#### 1. Provider (Adapter)
Responsável apenas por comunicação com o storage.

Exemplo:
- `putObject(...)`
- `getObject(...)`
- `removeObject(...)`
- `statObject(...)`

Sem:
- regras de negócio
- geração de metadata
- orquestração de fluxo

---

#### 2. Service (Core)
Responsável por orquestrar o fluxo da biblioteca.

Exemplo:
- `upload(...)`
    - chama provider
    - gera metadata
    - persiste metadata
- `download(...)`
- `delete(...)`

Contém:
- regras de negócio
- decisões baseadas em configuração (`enableMetadata`, `useUniquePath`)

---

#### 3. Configuração
Classe única responsável por definir o comportamento da biblioteca.

Exemplo de campos:
- `provider`
- `bucket`
- `accessKey`
- `secretKey`
- `enableMetadata`
- `useUniquePath`

Sem:
- leitura de `.properties`
- lógica de execução

---

## 🔄 Fluxo esperado (upload)

1. Service recebe requisição de upload
2. Service resolve o caminho do objeto (com ou sem UUID)
3. Service chama `putObject(...)` do provider
4. Service consulta `statObject(...)`
5. Service gera metadata (se habilitado)
6. Service persiste metadata via provider

---

## ⚠️ Regras importantes

- Provider não deve conter lógica de negócio
- Service não deve conhecer detalhes do SDK (MinIO, etc.)
- Configuração não deve executar lógica
- Metadata deve ser opcional (`enableMetadata`)
- Estrutura com UUID deve ser opcional (`useUniquePath`)

---

## 🎯 Benefícios esperados

- Separação clara de responsabilidades
- Facilidade para adicionar novos providers (S3, etc.)
- Melhor testabilidade
- Código mais limpo e previsível
- Redução de acoplamento

---

## 🚀 Próximos passos

- Criar `StorageService`
- Refatorar `MinioStorageClient` para atuar apenas como adapter
- Ajustar `StorageClient` para refletir nova arquitetura
- Adaptar fluxo de upload para nova estrutura