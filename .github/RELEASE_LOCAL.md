# Release to MavenCentral from local machine

## Steps:

### Prerequisites

   1. Generate key: `gpg --full-generate-key`
   2. Check key id: `gpg --list-signatures`
   3. Upload to server: `gpg --keyserver hkps://keys.openpgp.org --send-keys [keyId]`
   4. Add Nexus user environment variable: `ossUser`
   5. Add Nexus token environment variable: `ossToken`
   
### Upload artifacts to Nexus Repository Manager

Before create PR from develop to master you have to:

  - Change the project version in:
  
```
/resource/build.gradle.kts
```

  - Choose between Release or Snapshot repo in:

```
/buildSrc/src/main/kotlin/plugin/MavenPublish.gradle.kts
```

  - Launch the next command:

```
gradle publishAllPublicationsToMavenRepository -Psigning.gnupg.keyName=[keyId] -Psigning.gnupg.passphrase=[passphrase]
```

