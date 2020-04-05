[![Download](https://img.shields.io/bintray/v/javiersegoviacordoba/Resources/Resource?label=Version)](https://bintray.com/javiersegoviacordoba/Resources/Resource/_latestVersion)
[![Master](https://img.shields.io/github/workflow/status/JavierSegoviaCordoba/Resource/Master/master?label=Master&logo=GitHub)](https://github.com/JavierSegoviaCordoba/Resource/actions?query=workflow%3AMaster)
[![Coverage Master](https://img.shields.io/codecov/c/github/JavierSegoviaCordoba/resource/master?label=Master&logo=codecov&logoColor=white)](https://codecov.io/gh/JavierSegoviaCordoba/Resource/branch/master)
[![Develop](https://img.shields.io/github/workflow/status/JavierSegoviaCordoba/Resource/Develop/develop?label=Develop&logo=GitHub)](https://github.com/JavierSegoviaCordoba/Resource/actions?query=workflow%3ADevelop)
[![Coverage Develop](https://img.shields.io/codecov/c/github/JavierSegoviaCordoba/resource/develop?label=Develop&logo=codecov&logoColor=white)](https://codecov.io/gh/JavierSegoviaCordoba/Resource/branch/develop)


# Resource

`Resource` is a `sealed class` which let you wrap any thing easily and has these options:

- 🔄 Loading: To use at that moment that a loading indicator should appear.
- 👍 Success: When the happy path occurs.
- ❌ Error: If there is a problem you will get this.
- 📦 Cache: To show a resource from a cache, for example when a network request fails

This library works very good if you use it together 
[`NetworkResponse`](https://github.com/JavierSegoviaCordoba/NetworkResponse) which is very similar
to `Resource` but thought to use with `Retrofit`.

## Download
```groovy
Groovy
implementation "com.javiersc.resources:resource:$version"
```

```kotlin
Kotlin DSL
implementation("com.javiersc.resources:resource:$version")
```

## Fold your Resource

Fold a `Resource` invokes multiple callbacks to manage its state for any event. A normal flow can be:

1. Emit `Loading` to show the progress indicator.
    - Emit `Cache` to populate data before you get the success data.
2. Emit `Success` to populate your data or emit `Error` if something were wrong to show an error.
    - Emit `Cache` if you want to populate some data after an error.

```kotlin
val dog: Dog = Dog("Auri")
val resource: Resource<Dog, Error> = Resource.Success(dog)

resource.fold {
    loading { println("Loading: Yes") }
    noLoading { println("Loading: no") }  // Invoked

    success { dog: Dog -> println("Success: $dog") } // Invoked
    successEmpty { println("Success: empty") }
    noSuccess { println("Success: no") }

    error { error: Error -> println("Error: $error") }
    errorEmpty { println("Error: empty") }
    noError { println("Error: no") }  // Invoked

    cache { dog: Dog -> println("Cache: $dog") }
    cacheEmpty { println("Cache: empty") }
    noCache { println("Cache: no") }  // Invoked
}
```

You don't have to add all those functions, for example usually you only have to use:
- `loading` to show a progress indicator.
- `noLoading` to hide the progress indicator.
- `success` to load the data.
- `error` to show and error.

## Mappers and common extension functions

Map a `Resource` to another `Resource` is possible with the following 
[extension function](/resource/src/main/kotlin/com/javiersc/resource/extensions/Resource.kt):

- `Resource` to `Resource`

```kotlin
val anotherResource: Resource<AnotherUser, AnotherError> = resource.map(
    mapResource = { user: User -> user.toAnotherUser() },
    mapError = { error: Error -> error.toAnotherError() }
)
// toAnotherUser() and toAnotherError() mappers should be created by youself
```

- Some value to `Resource`

```kotlin
val name: String = "Auri"
val nameResource = name.toResourceSuccess()

val message: String = "Some error message"
val messageResource = message.toResourceError()

val nameCache: String = "Roni"
val nameCacheResource = nameCache.toResourceCache()
```

- A lot of checkers for each state, for example:
```kotlin
val resource: Resource<String> = Resource.Success("Auri")
resource.ifSuccess { data: String ->
    println(data) // "Auri"
}
```


You can see all the common extension functions
[here](/resource/src/main/kotlin/com/javiersc/resource/extensions/Flow.kt) 
and [here](/resource/src/main/kotlin/com/javiersc/resource/extensions/Any.kt)

## Flow 

There are four `Flow` extension functions:
- `Flow<R>.map(...)` included in Kotlin, let you to easily map the object inside of your `Flow` to 
any `Resource`:

```kotlin
val usersFlow: Flow<List<User>>

val usersResourceFlow: Flow<Resource<List<User>, Error>> =
    usersFlow.map { users: List<User> -> Resource.Success(users) }
```
- `Flow<R>.toResourceSuccess()`
```kotlin
val usersSuccessFlow: Flow<Resource<List<User>, Error>> = usersFlow.toResourceSuccess()
``` 

- `Flow<R>.toResourceError()`
```kotlin
val usersErrorFlow: Flow<Resource<List<User>, Error>> = usersFlow.toResourceError()
``` 

- `Flow<R>.toResourceCache()`
```kotlin
val usersCacheFlow: Flow<Resource<List<User>, Error>> = usersFlow.toResourceCache()
``` 
