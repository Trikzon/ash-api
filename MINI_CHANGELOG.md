- Update to MC 1.21
- Remove Forge support
- Remove NeoForgeModLoader. Mods no longer need to register themselves with
  Ash API on NeoForge
- Remove empty NeoForge entrypoint. NeoForge does not require one anymore
- Remove Ash ModInitializers completely
    1. Mods no longer have to register themselves with Ash API on NeoForge
    2. NeoForge now allows for client-only entry points
- Rename ClientTickEvents to ClientTickEvent
    - Rename start and end callbacks to pre and post to match NeoForge
- Rename `ModOptionsScreenRegistry#registerModOptionsScreen` to just `ModOptionsScreenRegistry#register`
- Rename `KeyMappingRegistry#registerKeyMapping` to just `KeyMappingRegistry#register`
- Rewrite UseBlockEvent to use root level functional interface

A full changelog is hosted on [GitHub](https://github.com/Trikzon/ash-api/blob/1.21/CHANGELOG.md).
