# Design Patterns in Kotlin
![status](https://img.shields.io/badge/status-WorkInProgress-green)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/AdrianKuta/Design-Patterns-Kotlin/blob/master/LICENSE)
## Creational Design Patterns

Creational design patterns are useful when you need to instantiate object in the best possible way for specific situation.

### Builder

This pattern is for initializing objects with a lot of optional parameters.
Below example was prepared to be useful both in Kotlin and Java.
- Such object must have private constructor.
- We create `Builder` class, inside needed class, with constructor with all required parameters.
- For each optional parameter, we create public method which set this optional value and then returns the same `Builder` object.
- `Builder` class must provide public method (generally `build()`) which instantiate and returns needed class.
> Additionally for Kotlin:
- For Kotlin users we can use public `var` properties in `Builder`, but hide `void` setter from Java with `@JvmSynthetic` annotation.
This allows Kotlin users to still get full usage of the property for reading and writing
- In Kotlin we prefer constructor with default parameter values and named arguments. We marked primary constructor as private, so we can't use it to initialization.
But we can create function with name the same as class type, which looks like constructor. In this example it is `fun Person(...`.
```kotlin
class Person private constructor(
    //Required parameters
    val nickname: String,
    val mail: String,
    //optional parameters
    val firstName: String?,
    val lastName: String?,
    val city: String?
) {

    override fun toString(): String {
        return "Person(nickname=$nickname, mail=$mail, firstName=$firstName, lastName=$lastName, city=$city)"
    }


    class Builder(val nickname: String, val mail: String) {

        var firstName: String? = null
            @JvmSynthetic set

        var lastName: String? = null
            @JvmSynthetic set

        var city: String? = null
            @JvmSynthetic set

        fun setFirstName(firstName: String?) = apply { this.firstName = firstName }

        fun setLastName(lastName: String?) = apply { this.lastName = lastName }

        fun setCity(city: String?) = apply { this.city = city }

        fun build() = Person(nickname, mail, firstName, lastName, city)

    }
}

@JvmSynthetic // Hide from Java callers who should use Builder.
fun Person(nickname: String, mail: String, initializer: Person.Builder.() -> Unit): Person {
    return Person.Builder(nickname, mail).apply(initializer).build()
}
```

#### Usage in Kotlin

```kotlin
val person: Person = Person("JohnD", "test@test.com") {
    firstName = "John"
    city = "LA"
}
print(person)
```

#### Usage in Java

```java
Person person = new Person.Builder("JohnD", "test@test.com")
        .setFirstName("John")
        .setCity("LA")
        .build();

System.out.print(person);
```

##### output:
```kotlin
Person(nickname=JohnD, mail=test@test.com, firstName=John, lastName=null, city=LA)
```