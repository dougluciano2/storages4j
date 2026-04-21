# Storages4JConfiguration Class

## 🎯 Objetivo
Representar a configuração central da biblioteca **storages4j**, contendo todas as informações necessárias para conexão com o provider e definição de comportamento da lib.

A classe é imutável e construída através do padrão **Builder**, garantindo consistência e segurança na criação dos objetos.

---

## 📦 Pacote
io.dougluciano.storages4j.v2.configuration

---

## 🧱 Estrutura

### Atributos

- `provider` → Provider de storage (enum `StorageProvider`)
- `bucket` → Nome do bucket
- `accessKey` → Chave de acesso
- `secretKey` → Chave secreta
- `enableMetadata` → Habilita geração automática de metadata
- `useUniquePath` → Define uso de caminho único (ex: UUID)

---

## 🛠️ Construção (Builder)

A instância deve ser criada exclusivamente via Builder:

````java
StorageConfiguration config = StorageConfiguration.builder()
.provider(StorageProvider.MINIO)
.bucket("uploads")
.accessKey("user")
.secretKey("password")
.enableMetadata(true)
.useUniquePath(true)
.build();
````
---

## 🔍 Acesso aos dados

A classe expõe seus dados através de métodos de leitura:

- `getProvider()`
- `getBucket()`
- `getAccessKey()`
- `getSecretKey()`
- `getEnableMetadata()`
- `getUseUniquePath()`

---

## ⚠️ Validações

Realizadas no método `build()`:

- `provider` não pode ser nulo
- `bucket` não pode ser nulo ou vazio
- `accessKey` não pode ser nulo ou vazio
- `secretKey` não pode ser nulo ou vazio

### Regras adicionais (opcionais)

- `boolean` não necessita validação de nulidade
- validações entre flags devem ser aplicadas apenas se houver dependência lógica

---

## 🧠 Boas práticas adotadas

- Classe imutável (`final`)
- Uso de Builder Pattern
- Sem leitura de `.properties`
- Sem lógica de negócio
- Sem dependência de frameworks

---

## 🎯 Responsabilidade

- Apenas representar configuração
- Não executar lógica
- Não acessar recursos externos
- Não conhecer detalhes de provider

---

## 🚀 Papel na arquitetura

A classe `StorageConfiguration` atua como ponto central de configuração dentro da arquitetura, sendo utilizada pelo **Service** e pelos **Providers**, mantendo desacoplamento entre camadas e flexibilidade de uso.