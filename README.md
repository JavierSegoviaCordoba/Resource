# Resource

## Download
```
Groovy
implementation "com.javiersc.resource:resource:0.9.2"

Kotlin DSL
implementation("com.javiersc.resource:resource:0.9.2")
```

## [Resource](/resource/src/main/kotlin/com/javiersc/resource/Resource.kt) and [NetworkResponse](/resource/src/main/kotlin/com/javiersc/resource/network/NetworkResponse.kt) sealed classes

Wrap any NetworkResponse in this way:

- Network
    - Info (1XX)
    - Success (2XX resource)
    - Redirection (3XX)
    - Client error (4XX error resource)
    - Server error (5XX error resource)
    - Non generic error for custom codes (error resource)
- Other network issues with
    - InternetNotAvailable (IOException, common use: the device has no connection to the Internet) 
    - RemoteError (ConnectException, common use: server is completely dead, it even doesn't return
     5XX codes)
     
For Resource, you have all Network classes from above mode:

- Loading
    - To use at that moment that a loading indicator should appear.
      The resource can be null to show only the indicator, or not null to show the cache too.
- Cache
    - The resource is getting from a cache, great to use if the network resource has failed
     
## NetworkResponseCallAdapterFactory

Easily wrap the Retrofit calls: 
```
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, ErrorDTO>
```
If the server doesn't return an error body, or it is irrelevant:
```
@GET("users")
suspend fun getUsers(): NetworkResponse<List<UserDTO>, Unit>
```
Add the NetworkResponseCallAdapterFactory to the Retrofit builder:
```
private val retrofit = Retrofit.Builder().apply {
    ...
    addCallAdapterFactory(NetworkResponseCallAdapterFactory())
    ...
}.build()
```
It is possible to use Deferred too:
```
@GET("users")
fun getUsers(): Deferred<NetworkResponse<List<UserDTO>, ErrorDTO>>
```

## Mappers

Map any NetworkResponse to Resource easily with this [extension function](/resource/src/main/kotlin/com/javiersc/resource/network/extensions/NetworkResponse.kt):
```
val resource: Resource<UserDTO, Error> = networkResponse.toResource(
    mapResponse = { userDto: UserDTO -> userDTO.toUser() },
    mapError = { errorDTO: ErrorDTO -> errorDTO.toError() }
)
// UserDTO and ErrorDTO are your network objects, User and Error your domain objects
// toUser() and toError() mappers should be created by youself
```

Map a Resource to another Resource is possible with the following [extension function](/resource/src/main/kotlin/com/javiersc/resource/extensions/Resource.kt):
```
val anotherResource: Resource<AnotherUser, AnotherError> = resource.map(
    mapResource = { user: User -> user.toAnotherUser() },
    mapError = { error: Error -> error.toAnotherError() }
)
// toAnotherUser() and toAnotherError() mappers should be created by youself
```

You can see some examples of mappers [here](/demo/app/src/main/kotlin/com/javiersc/app/data/datasource/network/mappers) 
and [here](/demo/app/src/main/kotlin/com/javiersc/app/data/datasource/local/mappers)

## Flow 

There are two Flow extension functions:
- Flow<R>.toResource to easily wrap the object inside of your Flow to a Resource:
```
val usersResourceFlow: Flow<Resource<List<User>, Error>> =
    usersFlow.toResource { users: List<User> -> Resource.Success.OK(users) }
```
- Android Room database library has support for Flow, so for sending a cache Resource easily after
a failed network response, you can use the next extension function:
```
val usersResourceFlow: Flow<Resource<List<User>, Error>> = usersFlow.toResourceCache()
// usersFlow is your Room Flow.
``` 

## Demo: Kotlin app (no Android) and Kotlin backend (Spring)

Check the [demo folder](/demo) to get a Kotlin local app and a Kotlin Spring Boot. Run both to and 
feel free to play with them.

The `users` endpoint is http://localhost:8080/users/

##Credits
Based on [NetworkResponseAdapter](https://github.com/haroldadmin/NetworkResponseAdapter)
by [Kshitij Chauhan](https://github.com/haroldadmin)