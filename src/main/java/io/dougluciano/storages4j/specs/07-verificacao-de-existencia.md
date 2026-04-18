# Verificação de existência de arquivo

## 🎯 Objetivo
Permitir a validação da existência de um objeto no provider de storage a partir de sua chave, possibilitando decisões seguras antes de operações como download, remoção ou geração de fluxos condicionais.

## 📌 Comportamento esperado
- A biblioteca deve receber a chave do objeto
- Deve consultar o provider configurado
- Deve informar se o objeto existe ou não
- O retorno deve ser simples e padronizado para o consumidor

## 🔄 Fluxo funcional
1. Receber a chave do objeto
2. Identificar o provider configurado
3. Consultar a existência do objeto no bucket
4. Retornar o resultado da verificação

## ⚠️ Regras importantes
- A verificação deve considerar o bucket configurado
- O método deve retornar um resultado claro para existência e inexistência
- Falhas de conexão, autenticação ou acesso devem ser tratadas adequadamente
- A operação não deve expor detalhes do SDK utilizado internamente

## 🧱 Considerações funcionais
- Essa função pode ser utilizada como apoio para fluxos defensivos
- O comportamento deve ser uniforme independentemente do provider
- A chave utilizada deve ser compatível com a mesma convenção adotada no upload

## ✅ Resultado esperado
Ao final dessa função, o consumidor deve saber de forma objetiva se o objeto está ou não presente no storage configurado.