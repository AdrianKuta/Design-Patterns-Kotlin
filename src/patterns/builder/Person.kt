package patterns.builder

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