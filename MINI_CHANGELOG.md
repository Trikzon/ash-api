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

A full changelog is hosted on [GitHub](https://github.com/Trikzon/ash-api/blob/1.20.2/CHANGELOG.md).
