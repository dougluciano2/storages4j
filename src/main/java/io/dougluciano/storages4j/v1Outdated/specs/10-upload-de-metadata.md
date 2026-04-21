# Upload do metadata

## 🎯 Objetivo
Permitir o armazenamento do metadata enriquecido como um arquivo JSON no provider de storage, associando-o diretamente ao arquivo principal.

## 📌 Comportamento esperado
- A biblioteca deve receber o metadata já construído
- Deve serializar o metadata em formato JSON
- Deve armazenar o JSON no mesmo diretório do arquivo principal
- O processo deve ser transparente para o consumidor

## 🔄 Fluxo funcional
1. Receber o objeto de metadata enriquecido
2. Gerar o JSON a partir do objeto
3. Determinar a chave de armazenamento do metadata
4. Enviar o arquivo JSON para o storage
5. Confirmar a conclusão da operação

## ⚠️ Regras importantes
- O metadata deve ser salvo no mesmo diretório do arquivo principal
- O nome do metadata deve seguir um padrão definido pela biblioteca
- O processo não deve reutilizar o fluxo de upload principal (evitar recursão)
- O upload deve ser feito por um método interno dedicado

## 🧱 Considerações funcionais
- O metadata é tratado como um artefato independente
- Deve ser possível recuperar o metadata futuramente via download
- O processo deve garantir consistência com o arquivo original

## ✅ Resultado esperado
Ao final dessa função, o metadata deve estar armazenado no storage como um arquivo JSON, associado ao objeto principal.