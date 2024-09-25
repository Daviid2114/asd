package pe.edu.upc.practica.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.practica.dtos.AutorDTO;
import pe.edu.upc.practica.entities.Autor;
import pe.edu.upc.practica.servicesInterfaces.IAutorService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private IAutorService as;

    @PostMapping
    public void registrarAutor(@RequestBody AutorDTO dto) {
        ModelMapper m = new ModelMapper();
        Autor a = m.map(dto, Autor.class);
        as.insert(a);
    }

    @GetMapping
    public List<AutorDTO> listarAutores() {
        return as.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,AutorDTO.class);
        }).collect(Collectors.toList());
    }

    @PutMapping
    public void modificar(@RequestBody AutorDTO dto) {
        ModelMapper m = new ModelMapper();
        Autor a = m.map(dto, Autor.class);
        as.update(a);
    }

    @DeleteMapping("/{id}")
    public void eliminarAutor(@PathVariable("id") Integer id) {
        as.delete(id);
    }

}
