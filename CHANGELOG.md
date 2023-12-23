# Changelog
## v20.2.3
### v20.2.3-beta
- Fixed crash when opening the mod menu entry on NeoForge

### 20.2.2-beta
- Fixed ModOptionsScreen registration on Forge and NeoForge

### 20.2.1-beta
- Fixed links in modloader meta files.
- Fixed ModOptionsScreen rendering. Likely broke due to 1.20.2.

### 20.2.0-beta
- Changed version format to follow NeoForge's `<minecraft_minor>.<minecraft_patch>.<number>(-beta)`.
  - Read more about it [here](https://neoforged.net/news/20.2release/).
- No longer directly provide Quilt mod loader support. The Fabric version will likely continue to work on Quilt.
- Add support for the NeoForge mod loader.
- Heavily rearranged packages, including a change to the `ash_api` package.
- Made classes and methods use appropriate jetbrains annotations.

## 3.0.2+1.20.1
- Fix version in jar file

## 3.0.1+1.20.1
- Add `*ModInitializer` APIs to give Ash control over initialization flow.

## 3.0.0+1.20.1
- Update to MC 1.20.1

## 2.1.0+1.20
- Change mod id to `ash_api`
- Add minecraft version to the end of version number

## 2.0.0
### 2.0.0-rc.2
- Fix ModOptionsScreenRegistry on Quilt

### 2.0.0-rc.1
- Port to 1.20

## 1.0.1
- Fix crash when Mod Menu is not installed but a `ModOptionsScreen` is registered.

## 1.0.0
- Add `ForgeModLoader` API on Forge
- Add `ModOptions` and `ModOptionsManager` API
- Add `ModOptionsScreen` and `ModOptionsScreenRegistry` API
- Add `ResourceLoader` API to allow registering resource reload listeners.
  - Note: It has not been tested for server-side resource reload listeners, but it should work.
- Add `ModLoader` API
- Add `UseBlockEvent` API
- Add `ClientTickEvents` API
- Add `KeyMappingRegistry` API
- Add `ModEventBus` API on Forge
