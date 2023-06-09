# Changelog
## 2.0.0-rc.1
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
