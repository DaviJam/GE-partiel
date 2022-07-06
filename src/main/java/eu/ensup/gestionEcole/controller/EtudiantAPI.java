package eu.ensup.gestionEcole.controller;
import eu.ensup.gestionEcole.domain.Etudiant;
import eu.ensup.gestionEcole.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type Etudiant api.
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class EtudiantAPI {

    @Autowired
    private EtudiantService etudiantService;

    /**
     * Gets students.
     *
     * @return the students
     */
    @GetMapping("/getall")
    public List<Etudiant> getallStudents() {
        return etudiantService.getallStudent();
    }

    /**
     * Add student etudiant.
     *
     * @param etudiant the etudiant
     * @return the etudiant
     */
    @PostMapping("/add")
    public Etudiant addStudent(@RequestBody Etudiant etudiant) {
        return etudiantService.createStudent(etudiant);
    }

    /**
     * Gets student.
     *
     * @param uuid the uuid
     * @return the student
     */
    @GetMapping("/get/{uuid}")
    public Etudiant getStudent(@PathVariable String uuid) {
        return etudiantService.getStudent(uuid);
    }

    /**
     * Delete student string.
     *
     * @param uuid the uuid
     * @return the string
     */
    @DeleteMapping(value = "/delete/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteStudent(@PathVariable String uuid) {
        etudiantService.deleteStudent(uuid);

        if(etudiantService.getStudent(uuid) == null){
            return  "{result : L'étudiant a bien été supprimé}" ;
        }
        else{
            return "Erreur de suppression de l'étudiant";
        }
    }


    /**
     * Update student etudiant.
     *
     * @param etudiant the etudiant
     * @return the etudiant
     */
    @PutMapping("/update")
    public Etudiant updateStudent(@RequestBody Etudiant etudiant) {
        return etudiantService.updateStudent(etudiant);
    }
}
