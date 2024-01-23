package midas.technologies.modelo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(private val servicio: TodoServicio) {

    @PostMapping
    fun crear(@RequestBody todo: Todo): ResponseEntity<Todo> {
        val temp = servicio.agregar(todo);
        return ResponseEntity(temp, HttpStatus.CREATED);
    }

    @GetMapping
    fun imprime(): ResponseEntity<List<Todo>> {
        val temp = servicio.imprimir();
        return ResponseEntity(temp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    fun obtener(@PathVariable id: Int): ResponseEntity<Todo?> {
        val todo = servicio.obtener(id);
        return if (todo != null) {
            ResponseEntity(todo, HttpStatus.OK)
        } else {
            ResponseEntity(throw NoSuchElementException("El ID que busca no existe"));
        }
    }

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Int, @RequestBody cambio: Todo): ResponseEntity<Todo?> {
        val temp = servicio.actualizarElemento(id, cambio);
        return if (temp != null) {
            ResponseEntity(temp, HttpStatus.OK);
        } else {
            ResponseEntity(throw NoSuchElementException("El ID que busca no existe"));
        }
    }

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Int): ResponseEntity<Unit> {
        val eliminar = servicio.eliminar(id);
        return if (eliminar) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}