package co.za.gatling.poc.model

import javax.persistence.*

@Entity
@Table(name = "persons")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    lateinit var name: String

}