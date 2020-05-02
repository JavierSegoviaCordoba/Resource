# Release to MavenCentral from local machine

## Steps:

### Prerequisites

  1. Generate key: `gpg --full-generate-key`
  2. Check key id: `gpg --list-signatures`
  3. Upload to server: `gpg --keyserver hkps://keys.openpgp.org --send-keys [keyId]`
  4. Show the private key: `gpg --armor --export-secret-keys 0x[keyId]`

### Change version and select repository

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
      
### Configure CI/CD (GitHub)

#### GitHub secrets

  - KeyId as `gpgKeyName`
  - Passphrase as `gpgPassphrase`
  - Private key as `gpgKey`
    1. Get the key using the command from point 4 in prerequisites.
    2. Replace all newlines with `\n`
  - Sonatype - Nexus user as `ossUser`
  - Sonatype - Nexus token as `ossToken`

#### GitHub workflow jobs

```
jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Maven publish
        env:
          ossUser: ${{ secrets.ossUser }}
          ossPass: ${{ secrets.ossPass }}
          gpgKeyName: ${{ secrets.gpgKeyName }}
          gpgPassphrase: ${{ secrets.gpgPassphrase }}
        run: ./gradlew publishAllPublicationsToMavenRepository -Psigning.gnupg.keyName=${{ secrets.gpgKeyName }} -Psigning.gnupg.passphrase=${{ secrets.gpgPassphrase }}
```