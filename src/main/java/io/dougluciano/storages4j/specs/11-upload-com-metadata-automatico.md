# Upload com metadata automático

## 🎯 Objetivo
Orquestrar o processo completo de upload de arquivo com geração e persistência automática de metadata, sem necessidade de intervenção do consumidor.

## 📌 Comportamento esperado
- Ao realizar o upload de um arquivo, o metadata deve ser gerado automaticamente
- O metadata deve ser persistido após o upload do arquivo principal
- O processo deve ser transparente para o consumidor
- O fluxo deve evitar recursão infinita

## 🔄 Fluxo funcional
1. Receber o arquivo e a chave de destino
2. Realizar o upload do arquivo principal
3. Consultar os metadados técnicos do objeto
4. Gerar o metadata enriquecido
5. Persistir o metadata no storage

## ⚠️ Regras importantes
- O upload do metadata não deve chamar o método de upload principal
- Deve existir separação clara entre envio de arquivo e envio de metadata
- O fluxo deve garantir consistência entre arquivo e metadata
- Falhas devem ser tratadas de forma controlada

## 🧱 Considerações funcionais
- Essa função representa a composição de múltiplas responsabilidades
- O consumidor interage apenas com o upload padrão
- A geração de metadata é um comportamento interno da biblioteca

## ✅ Resultado esperado
Ao final dessa função, o arquivo e seu metadata devem estar armazenados no storage, mantendo consistência, rastreabilidade e enriquecimento de dados sem esforço adicional do consumidor.