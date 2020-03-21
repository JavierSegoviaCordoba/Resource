# Resource

## Download
```groovy
Groovy
implementation "com.javiersc.resources:resource:0.9.7"
```

```kotlin
Kotlin DSL
implementation("com.javiersc.resources:resource:0.9.7")
```

## [Resource](/resource/src/main/kotlin/com/javiersc/resource/Resource.kt) and [NetworkResponse](/resource/src/main/kotlin/com/javiersc/resource/network/NetworkResponse.kt) sealed classes

Resource `sealed class` has these options

- Loading To use at that moment that a loading indicator should appear.
- Cache -> To show a resource from a cache, great to use if the network resource has failed or to 
show a temporal cache pre-request
- Success -> When the happy path occurs.
- Error -> If there is a problem you will get this. Error can be null.

You can fold a resource easily with this function:

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
- loading to show a progress indicator.
- noLoading to hide the progress indicator.
- success to load the data.
- error to show and error.

`NetworkResponse` has a lot of options (all standard status code and some generics). I recommend
you check the class directly and take a look to both demos (app and backend) to see its usage.
     
## NetworkResponseCallAdapterFactory

Easily wrap the Retrofit calls: 
```kotlin
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, ErrorDTO>
```
If the server doesn't return an error body, or it is irrelevant:
```kotlin
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, Unit>
```
Add the NetworkResponseCallAdapterFactory to the Retrofit builder:
```kotlin
private val retrofit = Retrofit.Builder().apply {
    //...
    addCallAdapterFactory(NetworkResponseCallAdapterFactory())
    //...
}.build()
```
It is possible to use Deferred too:
```kotlin
@GET("users")
fun getUsers(): Deferred<NetworkResponse<List<UserDTO>, ErrorDTO>>
```

## Mappers

Map any NetworkResponse to Resource easily with this 
[extension function](/resource/src/main/kotlin/com/javiersc/resource/network/extensions/NetworkResponse.kt):
```kotlin
val resource: Resource<UserDTO, Error> = networkResponse.toResource(
    mapResponse = { userDto: UserDTO -> userDTO.toUser() },
    mapError = { errorDTO: ErrorDTO? -> errorDTO.toError() }
)
// UserDTO and ErrorDTO are your network objects, User and Error your domain objects
// UserDTO.toUser() and ErrorDTO?.toError() mappers should be created by youself
// There are more maps, not only mapResponse and mapError for more customization.
// For example, you can map all errors with mapError, but if you need a custom map for NotFound
// you can use mapNotFound. This let you not only map an ErrorDTO object, you can use a custom
// provide so you can send custom messages if your backend is not sending values which can be used
```

Map a Resource to another Resource is possible with the following 
[extension function](/resource/src/main/kotlin/com/javiersc/resource/extensions/Resource.kt):
```kotlin
val anotherResource: Resource<AnotherUser, AnotherError> = resource.map(
    mapResource = { user: User -> user.toAnotherUser() },
    mapError = { error: Error -> error.toAnotherError() }
)
// toAnotherUser() and toAnotherError() mappers should be created by youself
```

You can see some examples of mappers 
[here](/demo/app/src/main/kotlin/com/javiersc/app/data/datasource/network/mappers) 
and [here](/demo/app/src/main/kotlin/com/javiersc/app/data/datasource/local/mappers)

## Flow 

There are four `Flow` extension functions:
- `Flow<R>.map(...)` included in Kotlin, let you to easily wrap the object inside of your `Flow` to a 
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

## Demo: Kotlin app (no Android) and Kotlin backend (Spring)

Check the [demo folder](/demo) to get a Kotlin local app and a Kotlin Spring Boot. Run both to and 
feel free to play with them.

The `users` endpoint is http://localhost:8080/users/

##Credits
Based on [NetworkResponseAdapter](https://github.com/haroldadmin/NetworkResponseAdapter)
by [Kshitij Chauhan](https://github.com/haroldadmin)