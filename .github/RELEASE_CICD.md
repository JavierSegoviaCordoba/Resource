# Release to MavenCentral from GitHub Actions

## Steps:

### Prerequisites

  1. Generate key: `gpg --full-generate-key`
  2. Check key id: `gpg --list-signatures`
  3. Upload to server: `gpg --keyserver hkps://keys.openpgp.org --send-keys [keyId]`
  4. Show the private key: `gpg --armor --export-secret-keys [keyId]`

### Versioning

You can change the version and indicate if is release or snapshot in the next file:

- resource/[gradle.properties](/resource/gradle.properties)

#### Releases

Before create the pull request to sync develop with master, change the project version and set 
`isResourceRelease` to true. Then create the pull request.

#### Snapshots

Before create the pull request to develop, change the project version and set `isResourceRelease` 
to false. 

Automatically, the version generated includes a timestamp and the suffix `-SNAPSHOT`.
      
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
