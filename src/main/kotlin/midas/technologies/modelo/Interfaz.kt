package midas.technologies.modelo

import org.springframework.data.jpa.repository.JpaRepository

interface Interfaz: JpaRepository<Todo, Int> {
}