package org.example.domain.tarea;

import lombok.Data;
import org.example.domain.usuario.Usuario;

import java.io.Serializable;

@Data
public class Tarea implements Serializable {
    private Long id;
    private String titulo;
    private String prioridad;
    private Long usuario_id;
    // Una Tarea pertenece a un usuario //
    // Un usuario puede tener varias Tareas //
    private Usuario usuario;
    private String categoria;
    private String descripcion;

}
