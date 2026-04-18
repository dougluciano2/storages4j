# Inicialização por configuração

## 🎯 Objetivo
Permitir que a biblioteca seja inicializada a partir de um arquivo de propriedades, centralizando a definição do provider e dos dados necessários para conexão com o storage. Essa função deve reduzir o acoplamento com a implementação concreta e simplificar o uso da biblioteca por quem a consome.

## 📌 Comportamento esperado
- A biblioteca deve ler as propriedades de configuração do storage
- Deve montar um objeto de configuração padronizado
- Deve identificar qual provider foi definido
- Deve permitir que, a partir dessa configuração, o client correto seja criado
- A configuração deve conter, no mínimo, os dados essenciais para autenticação e acesso ao bucket

## 🧾 Propriedades esperadas
- `storages4j.provider`
- `storages4j.endpoint`
- `storages4j.accessKey`
- `storages4j.secretKey`
- `storages4j.bucket`

## 🔄 Fluxo funcional
1. A aplicação disponibiliza um arquivo `.properties`
2. A biblioteca lê os valores esperados
3. Os dados são convertidos para uma configuração interna
4. O provider configurado é resolvido
5. A configuração fica pronta para ser utilizada na criação do `StorageClient`

## ⚠️ Regras importantes
- O provider deve ser compatível com os providers suportados pela biblioteca
- A ausência de propriedades obrigatórias deve impedir a inicialização correta
- A configuração deve ser tratada como ponto único de verdade para a criação do client
- A leitura da configuração não deve conter lógica de upload, download ou regras de negócio de storage

## ✅ Resultado esperado
Ao final dessa função, a biblioteca deve possuir uma configuração válida e suficiente para instanciar o client do provider selecionado, mantendo o consumo simples, previsível e desacoplado da implementação concreta.