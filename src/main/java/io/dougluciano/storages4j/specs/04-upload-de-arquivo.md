# Upload de arquivo

## 🎯 Objetivo
Permitir o envio de arquivos para o provider de storage configurado, de forma padronizada, simples e desacoplada da implementação concreta utilizada internamente pela biblioteca.

## 📌 Comportamento esperado
- A biblioteca deve receber um arquivo e uma chave de destino
- O arquivo deve ser enviado ao bucket configurado
- O processo deve utilizar o provider ativo na configuração
- O consumidor deve interagir apenas com a abstração da biblioteca
- O upload deve preservar as informações necessárias para identificação e manipulação posterior do objeto

## 🔄 Fluxo funcional
1. Receber o arquivo de origem
2. Receber a chave do objeto que será usada no storage
3. Identificar o provider configurado
4. Enviar o arquivo para o bucket correspondente
5. Confirmar a conclusão da operação

## ⚠️ Regras importantes
- O bucket de destino deve existir antes do upload
- A chave do objeto deve identificar corretamente o arquivo no storage
- O upload deve falhar de forma controlada em caso de erro de conexão, autenticação ou envio
- A função de upload deve manter comportamento consistente independentemente do provider
- O processo de upload não deve expor detalhes do SDK utilizado internamente

## 🧱 Considerações funcionais
- Esta função representa a operação principal de entrada de arquivos na biblioteca
- O upload serve como base para funcionalidades complementares, como geração de metadata e futuras extensões
- A biblioteca deve manter a experiência de uso uniforme, mesmo que a implementação interna varie por provider

## ✅ Resultado esperado
Ao final dessa função, o arquivo deve estar persistido no storage configurado, acessível pela chave informada e disponível para operações futuras como download, verificação de existência, consulta de metadata e remoção.