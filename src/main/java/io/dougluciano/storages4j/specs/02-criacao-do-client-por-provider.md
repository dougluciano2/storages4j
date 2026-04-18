# Criação do client por provider

## 🎯 Objetivo
Permitir a criação de um `StorageClient` a partir do provider configurado, garantindo que a implementação correta seja instanciada de forma transparente para o consumidor da biblioteca.

## 📌 Comportamento esperado
- A biblioteca deve identificar o provider definido na configuração
- Deve instanciar automaticamente o client correspondente
- O consumidor não deve precisar conhecer a implementação concreta
- O client retornado deve estar pronto para uso

## 🔄 Fluxo funcional
1. Receber um objeto de configuração válido
2. Identificar o provider configurado
3. Selecionar a implementação correspondente
4. Instanciar o client com os dados necessários
5. Retornar uma instância de `StorageClient`

## ⚠️ Regras importantes
- O provider deve ser suportado pela biblioteca
- Caso o provider não seja reconhecido, uma exceção deve ser lançada
- A criação do client não deve conter lógica de negócio (apenas resolução e instanciação)
- A responsabilidade de decidir qual implementação usar deve ficar centralizada (Factory)

## 🧱 Padrão adotado
- Uso de **Factory Pattern** para criação do client
- Desacoplamento entre interface (`StorageClient`) e implementações concretas
- Facilidade para adicionar novos providers no futuro

## ✅ Resultado esperado
Ao final dessa função, deve existir uma instância válida de `StorageClient`, pronta para executar operações de storage, sem que o consumidor precise conhecer detalhes da implementação.