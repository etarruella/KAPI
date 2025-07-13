# Propuesta detallada de clases por paquete

**com.etarruella.config**

* `ConfigManager` — carga y valida config.yml
* `ConfigWatcher` — (opcional) detecta cambios en configuración y recarga dinámicamente

**com.etarruella.event**

* `PlayerHealthChangeEvent` — evento de cambio de vida
* `PlayerPositionChangeEvent` — evento de cambio de posición
* `PlayerAttributesUpdateEvent` — evento genérico para cambios en atributos
* Clases auxiliares que representen datos de jugador (ej: `PlayerState`)

**com.etarruella.listener**

* `PlayerEventListener` — escucha eventos Bukkit/Paper y convierte en eventos propios para el sistema
* `PlayerJoinListener` — gestión de inicialización al entrar un jugador
* `PlayerQuitListener` — limpieza al salir un jugador

**com.etarruella.network**

* `NetworkServer` — servidor WebSocket/TCP que acepta conexiones del bridge o frontend
* `NetworkClientHandler` — gestión individual de cada cliente conectado
* `MessageSerializer` / `MessageDeserializer` — convierte eventos en mensajes para enviar y viceversa
* `SubscriptionManager` — controla qué atributos de qué jugadores se envían a qué clientes
