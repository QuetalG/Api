package midas.technologies.modelo

import org.springframework.stereotype.Service

@Service
class TodoServicio(private val interfaz: Interfaz) {

    public fun agregar(todo: Todo):Todo{
        return interfaz.save(todo);
    }
    public fun imprimir(): List<Todo>{
        return interfaz.findAll();
    }
    public fun obtener(idPorBuscar: Int): Todo {
        return interfaz.findById(idPorBuscar).orElse(null);
    }
    fun actualizarElemento(idPorActualizar:Int, actualizar:Todo): Todo {
        if (interfaz.existsById(idPorActualizar)) {
            actualizar.id = idPorActualizar;
            return interfaz.save(actualizar)
        }
        throw NoSuchElementException("El ID que busca no existe");
    }

    fun eliminar(id: Int):Boolean {
        if (interfaz.existsById(id)) {
            interfaz.deleteById(id)
            return true
        }
        return false
    }
}