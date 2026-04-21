# Remoção de arquivo

## 🎯 Objetivo
Permitir a exclusão de arquivos armazenados no provider de storage, garantindo que o objeto não esteja mais disponível para operações futuras.

## 📌 Comportamento esperado
- A biblioteca deve receber a chave do objeto a ser removido
- Deve localizar o arquivo no bucket configurado
- Deve remover o objeto do storage
- A operação deve ser transparente quanto ao provider utilizado

## 🔄 Fluxo funcional
1. Receber a chave do objeto
2. Identificar o provider configurado
3. Localizar o objeto no bucket
4. Executar a remoção do arquivo
5. Confirmar a conclusão da operação

## ⚠️ Regras importantes
- A remoção deve ser idempotente (não falhar se o arquivo não existir, se assim definido)
- Falhas de conexão ou permissão devem ser tratadas adequadamente
- A operação não deve expor detalhes do SDK utilizado internamente
- A chave deve identificar corretamente o objeto a ser removido

## 🧱 Considerações funcionais
- A remoção deve ser consistente com a chave utilizada no upload
- Pode ser necessário, em cenários futuros, remover também artefatos associados (ex: metadata)
- A abstração deve permitir troca de provider sem impacto no consumo

## ✅ Resultado esperado
Ao final dessa função, o arquivo não deve mais existir no storage, não podendo ser recuperado por operações de download ou consulta de existência.