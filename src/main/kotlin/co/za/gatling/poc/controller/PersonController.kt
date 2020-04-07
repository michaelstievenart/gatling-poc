package co.za.gatling.poc.controller

import co.za.gatling.poc.model.Person
import co.za.gatling.poc.repository.PersonRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PersonController(val repo: PersonRepository) {

    private var count: Long = 0

    @GetMapping("/all")
    fun allPersons(): Collection<Person> {
        return repo.findAll()
    }

    @PostMapping("/add")
    fun addPerson() {
        val person = Person()
        person.apply {
            id = count
            name = "Test Person $count"
        }
        repo.save(person)
        count ++
    }
}