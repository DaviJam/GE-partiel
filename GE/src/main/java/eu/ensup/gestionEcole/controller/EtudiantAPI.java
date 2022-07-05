package eu.ensup.gestionEcole.controller;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class EtudiantAPI {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/getall")
    public List<Etudiant> getallStudents() {
        return etudiantService.getallStudent();
    }

    @PostMapping("/add")
    public Etudiant addStudent(@RequestBody Etudiant etudiant) {
        return etudiantService.createStudent(etudiant);
    }

    @GetMapping("/get/{uuid}")
    public Etudiant getStudent(@PathVariable String uuid) {
        return etudiantService.getStudent(uuid);
    }

    @GetMapping("/delete/{uuid}")
    public String deleteStudent(@PathVariable String uuid) {
        etudiantService.deleteStudent(uuid);

        if(etudiantService.getStudent(uuid) == null){
            return "L'étudiant a bien été supprimé";
        }
        else{
            return "Erreur de suppression de l'étudiant";
        }
    }


    @PostMapping("/update")
    public Etudiant updateStudent(@RequestBody Etudiant etudiant) {
        return etudiantService.updateStudent(etudiant);
    }
}
