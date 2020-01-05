package patterns.builder

fun main() {

    val person: Person = Person("JohnD", "test@test.com") {
        firstName = "John"
        city = "LA"
    }
    print(person)
}