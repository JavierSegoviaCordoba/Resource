# Resource

## Download
```
Groovy
implementation "com.javiersc.resources:resource:0.9.4"

Kotlin DSL
implementation("com.javiersc.resources:resource:0.9.4")
```

## [Resource](/resource/src/main/kotlin/com/javiersc/resource/Resource.kt) and [NetworkResponse](/resource/src/main/kotlin/com/javiersc/resource/network/NetworkResponse.kt) sealed classes

Resource `sealed class` has this options

- Loading To use at that moment that a loading indicator should appear. The resource can be null to 
show only the indicator, or not null to show some cache resource too.
- Cache -> To show a resource from a cache, great to use if the network resource has failed
- Success -> When the happy path occurs. This has to have a valid not null resource.
    - NoData -> When the resource has no data
- Error -> If there is a problem you will get this. Error can be null.
     
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
    mapError = { errorDTO: ErrorDTO? -> errorDTO.toError() }
)
// UserDTO and ErrorDTO are your network objects, User and Error your domain objects
// UserDTO.toUser() and ErrorDTO?.toError() mappers should be created by youself
// There are more maps, not only mapResponse and mapError for more customization.
// For example, you can map all errors with mapError, but if you need a custom map for NotFound
// you can use mapNotFound. This let you not only map an ErrorDTO object, you can use a custom
// provide so you can send custom messages if your backend is not sending values which can be used
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