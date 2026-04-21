# LogMessages

## 🎯 Objetivo
Centralizar todas as mensagens de log da biblioteca, evitando strings espalhadas pelo código e garantindo padronização, legibilidade e facilidade de manutenção.

---

## 📦 Pacote
io.dougluciano.storages4j.v2.logMessages

---

## 📛 Estrutura
Enum responsável por armazenar mensagens de log parametrizadas, utilizando placeholders `{}` compatíveis com SLF4J.

---

## 🧩 Responsabilidades

- Definir mensagens de log padronizadas
- Evitar duplicação de strings no código
- Facilitar manutenção e futuras mudanças
- Preparar o projeto para possível internacionalização

---

## 🛠️ Uso

Exemplo:

log.info(LogMessages.UPLOAD_START.getMessage(), objectKey, bucket);

---

## ⚠️ Regras importantes

- Não conter lógica de negócio
- Apenas armazenar mensagens
- Utilizar placeholders `{}` para parâmetros
- Manter nomenclatura clara e consistente

---

## 🎯 Resultado esperado

- Logs padronizados em toda a aplicação
- Código mais limpo e organizado
- Facilidade de manutenção e evolução