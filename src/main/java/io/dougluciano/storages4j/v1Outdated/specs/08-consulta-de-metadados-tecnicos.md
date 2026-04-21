# Consulta de metadados técnicos

## 🎯 Objetivo
Permitir a recuperação de metadados técnicos de um objeto armazenado no provider, como tamanho, tipo de conteúdo e identificadores internos, sem a necessidade de baixar o arquivo.

## 📌 Comportamento esperado
- A biblioteca deve receber a chave do objeto
- Deve consultar o provider configurado
- Deve retornar os metadados técnicos do objeto
- O retorno deve ser padronizado independente do provider

## 🔄 Fluxo funcional
1. Receber a chave do objeto
2. Identificar o provider configurado
3. Consultar os metadados do objeto no bucket
4. Mapear o retorno do provider para um modelo padronizado
5. Retornar os metadados ao consumidor

## ⚠️ Regras importantes
- O objeto deve existir no storage
- Caso não exista, uma exceção deve ser lançada
- A operação não deve realizar download do arquivo
- O método deve abstrair diferenças entre providers
- Falhas de conexão ou autenticação devem ser tratadas adequadamente

## 🧱 Considerações funcionais
- Os metadados retornados devem incluir informações como:
    - tamanho (size)
    - tipo de conteúdo (contentType)
    - identificador do objeto (etag ou equivalente)
- Essa função serve de base para geração de metadata enriquecido
- Deve ser eficiente, evitando operações desnecessárias

## ✅ Resultado esperado
Ao final dessa função, o consumidor deve receber um conjunto de metadados técnicos confiáveis sobre o objeto armazenado, sem necessidade de transferir seu conteúdo.