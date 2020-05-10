| [![Master Download](https://img.shields.io/maven-central/v/com.javiersc.resources/resource?label=Master)](https://repo1.maven.org/maven2/com/javiersc/resources/resource/)                                                                          | [![Coverage Master](https://img.shields.io/codecov/c/github/JavierSegoviaCordoba/resource/master?label=Coverage&logo=codecov&logoColor=white)](https://codecov.io/gh/JavierSegoviaCordoba/Resource/branch/master)    | [![Master Build](https://img.shields.io/github/workflow/status/JavierSegoviaCordoba/Resource/Master/master?label=Build&logo=GitHub)](https://github.com/JavierSegoviaCordoba/Resource/actions?query=workflow%3AMaster/master)      | [![Quality Master](https://img.shields.io/codacy/grade/cedb7663279a4526befcbe16be6bfd66/master?label=Code%20quality&logo=codacy&logoColor=white)](https://app.codacy.com/manual/JavierSegoviaCordoba/Resource/dashboard?bid=17391050)   |
| :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [![Develop Download](https://img.shields.io/nexus/s/com.javiersc.resources/resource?server=https%3A%2F%2Foss.sonatype.org%2F&label=Develop&color=orange)](https://oss.sonatype.org/content/repositories/snapshots/com/javiersc/resources/resource/) | [![Coverage Develop](https://img.shields.io/codecov/c/github/JavierSegoviaCordoba/resource/develop?label=Coverage&logo=codecov&logoColor=white)](https://codecov.io/gh/JavierSegoviaCordoba/Resource/branch/develop) | [![Develop Build](https://img.shields.io/github/workflow/status/JavierSegoviaCordoba/Resource/Develop/develop?label=Build&logo=GitHub)](https://github.com/JavierSegoviaCordoba/Resource/actions?query=workflow%3ADevelop/develop) | [![Quality Develop](https://img.shields.io/codacy/grade/cedb7663279a4526befcbe16be6bfd66/develop?label=Code%20quality&logo=codacy&logoColor=white)](https://app.codacy.com/manual/JavierSegoviaCordoba/Resource/dashboard?bid=17391049) |

# Resource

`Resource` is a `sealed class` that allows you to wrap any object based on a state. It has these options:

-  🔄 Loading: To use at that moment that a loading indicator should appear.
-  👍 Success: When the happy path occurs.
-  ❌ Error: If there is a problem you will get this.

This library works very well when used in conjunction with
[`NetworkResponse`](https://github.com/JavierSegoviaCordoba/NetworkResponse) which is very similar
to `Resource` but is intended for use with `Retrofit`.

## Download

This library is Kotlin Multiplatform but at this moment `jvm` is the only artifact generated. It is 
available at Maven Central.

```run-kotlin
implementation("com.javiersc.resources:resource:$version")
```
{theme="darcula" lines="false" data-autocomplete="true" data-highlight-only="nocursor"}


## Fold your Resource

Fold a `Resource` invokes multiple callbacks to manage its state for any event. A normal flow can be:

1.  Emit `Loading` to show the progress indicator.
2.  Emit `Success` to populate your data or emit `Error` if something were wrong to show an error.

```run-kotlin
val dog: Dog = Dog("Auri")
val resource: Resource<Dog, Error> = Resource.Success(dog)

resource.folder {
    loading { println("Loading: Yes") }
    noLoading { println("Loading: no") }  // Invoked

    success { dog: Dog -> println("Success: $dog") } // Invoked
    noSuccess { println("Success: no") }

    error { error: Error -> println("Error: $error") }
    noError { println("Error: no") }  // Invoked
}
``` 
{theme="darcula" lines="true" data-autocomplete="true" data-highlight-only="nocursor"}

Exists a `fold` function similar to folder buth without the builder pattern

```run-kotlin
val dog: Dog = Dog("Auri")
val resource: Resource<Dog, Error> = Resource.Success(dog)

resource.fold(
    loading = { println("Loading: Yes") },
    noLoading = { println("Loading: no") },  // Invoked
    success = { dog: Dog -> println("Success: $dog") }, // Invoked
    noSuccess = { println("Success: no") },
    error = { error: Error -> println("Error: $error") },
    noError = { println("Error: no") },  // Invoked
)
```
{theme="darcula" lines="true" data-autocomplete="true" data-highlight-only="nocursor"}

You don't have to add all those functions, for example, you usually only have to use:
-  `loading` to show a progress indicator.
-  `noLoading` to hide the progress indicator.
-  `success` to load the data.
-  `error` to show and error.

## Mappers and common extension functions

Map a `Resource` to another `Resource` is possible with the following 
[extension function](/resource/src/main/kotlin/com/javiersc/resource/extensions/Resource.kt):

-  `Resource` to `Resource`

```run-kotlin
val anotherResource: Resource<AnotherUser, AnotherError> = resource.map(
    data = { user: User -> user.toAnotherUser() },
    error = { error: Error -> error.toAnotherError() }
)
// toAnotherUser() and toAnotherError() mappers should be created by yourself, if they are
// extension functions and the resource uses inference for the type:
val anotherResource = resource.map(User::toAnotherUser, Error::toAnotherError)
```
{theme="darcula" lines="true" data-autocomplete="true" data-highlight-only="nocursor"}

-  Some value to `Resource`
  
```run-kotlin
val name: String = "Auri"
val nameResource = name.toResourceSuccess()

val message: String = "Some error message"
val messageResource = message.toResourceError()
```
{theme="darcula" lines="true" data-autocomplete="true" data-highlight-only="nocursor"}

-  A lot of checkers for each state, for example:
  
```run-kotlin
val resource: Resource<String> = Resource.Success("Auri")
resource.ifSuccess { data: String ->
    println(data) // "Auri"
}
```
{theme="darcula" lines="true" data-autocomplete="true" data-highlight-only="nocursor"}

You can see all the common extension functions
[here](/resource/src/main/kotlin/com/javiersc/resource/extensions/Flow.kt) 
and [here](/resource/src/main/kotlin/com/javiersc/resource/extensions/Any.kt)

## Flow 

There are four `Flow` extension functions:

-  `Flow<R>.map(...)` included in Kotlin, let you to easily map the object inside of your `Flow` to 
any `Resource`:

```run-kotlin
val usersFlow: Flow<List<User>>

val usersResourceFlow: Flow<Resource<List<User>, Error>> =
    usersFlow.map { users: List<User> -> Resource.Success(users) }
```
{theme="darcula" lines="true" data-autocomplete="true" data-highlight-only="nocursor"}

-  `Flow<R>.toResourceSuccess()`

```run-kotlin
val usersSuccessFlow: Flow<Resource<List<User>, Error>> = usersFlow.toResourceSuccess()
```
{theme="darcula" lines="true" data-autocomplete="true" data-highlight-only="nocursor"}

-  `Flow<R>.toResourceError()`

```run-kotlin
val usersErrorFlow: Flow<Resource<List<User>, Error>> = usersFlow.toResourceError()
```
{theme="darcula" lines="true" data-autocomplete="true" data-highlight-only="nocursor"}
