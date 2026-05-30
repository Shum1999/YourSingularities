#!/bin/bash

# Создаём директории
mkdir -p ~/.gradle/caches/minecraft/net/minecraft/minecraft/1.7.10/
mkdir -p ~/.gradle/caches/minecraft/net/minecraft/minecraft_server/1.7.10/

# Скачиваем правильные файлы
echo "Downloading Minecraft client JAR..."
wget -O ~/.gradle/caches/minecraft/net/minecraft/minecraft/1.7.10/minecraft-1.7.10.jar \
  "https://maven.minecraftforge.net/net/minecraft/minecraft/1.7.10/minecraft-1.7.10.jar"

echo "Downloading Minecraft server JAR..."
wget -O ~/.gradle/caches/minecraft/net/minecraft/minecraft_server/1.7.10/minecraft_server-1.7.10.jar \
  "https://maven.minecraftforge.net/net/minecraft/minecraft_server/1.7.10/minecraft_server-1.7.10.jar"

echo "Creating version JSON..."
cat > ~/.gradle/caches/minecraft/net/minecraft/minecraft/1.7.10/1.7.10.json << 'EOF'
{
  "id": "1.7.10",
  "type": "release",
  "minecraftArguments": "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userProperties ${user_properties} --userType ${user_type}",
  "libraries": []
}
EOF

echo "Done! Files prepared."