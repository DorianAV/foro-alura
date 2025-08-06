package org.dorian.foroalura.controller;


import jakarta.validation.Valid;
import org.dorian.foroalura.domain.curso.CursoRepository;
import org.dorian.foroalura.domain.topico.*;
import org.dorian.foroalura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest().body("Tópico duplicado: ya existe uno con el mismo título y mensaje");
        }


        if (!usuarioRepository.existsById(datos.autorId())) {
            return ResponseEntity.badRequest().body("Autor no encontrado");
        }

        if(!cursoRepository.existsById(datos.cursoId())){
            return ResponseEntity.badRequest().body("Curso no encontrado");
        }

        var autor = usuarioRepository.getReferenceById(datos.autorId());
        var curso = cursoRepository.getReferenceById(datos.cursoId());
        var topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);

        var uri= uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"} ) Pageable paginacion){
        var page = topicoRepository.findByStatusTrue(paginacion)
                .map(DatosDetalleTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> getTopico(@PathVariable("id") Long id) {
        Topico topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionTopico datos) {
        if (!topicoRepository.existsById(datos.id())) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoRepository.getReferenceById(datos.id());

        topico.actualizar(datos);


        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable("id") Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.noContent().build();
    }

}
