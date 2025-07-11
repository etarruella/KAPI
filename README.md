# KAPI 🎮✨

A **K**-powered **API** for Minecraft servers — real-time player data streaming with event-driven architecture! 🚀🔥

## 🚀 What is KAPI?

KAPI is a lightweight and efficient Minecraft plugin + bridge system that allows real-time tracking of player attributes like health, position, potion effects, and more — all sent via asynchronous event subscriptions. Perfect for dynamic overlays in OBS Studio 🎥 for tournaments or streaming! 🎉

> [!WARNING]
> This plugin is currently in early development and does not yet have a stable release. Use with caution.

## ⚙️ Features

* Real-time player stats updates 💨
* Subscribe to player attributes (health ❤️, food 🍗, saturation 🌟, position 📍, effects ✨)
* Low-latency event-driven architecture ⚡
* Easy integration with external tools (like OBS Studio) 🎛️
* Built on PaperMC for performance and stability 🛡️


## 🛠️ Getting Started

1. Build the plugin with Gradle:

   ```bash
   ./gradlew shadowJar
   ```

2. Run your Paper server with the plugin:

   ```bash
   ./gradlew runServer
   ```

3. Connect your bridge or frontend to subscribe to player events!


## 📦 Requirements

* Java 21 ☕
* PaperMC 1.21.5 🧱
* Gradle with Shadow and Run-Paper plugins 🔧


## 🤝 Contributing

Feel free to open issues or PRs! Let’s make Minecraft integrations smoother together! 💪🐝


## 📄 License

The Unlicense — see `LICENSE` file for details 📜

## 👥 Credits

- 💡 **Main idea**: 
   - Erik Tarruella
   - Víctor De Lomas
   - Pol Prat
- 🔧 **Backend development**: 
   - Erik Tarruella
   - Víctor De Lomas

