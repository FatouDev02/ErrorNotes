package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Services.SolutionService;
import com.example.Error_Notes.models.Solution;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solution")
@Data
public class SolutionController {
    @Autowired
    SolutionService solutionService;

    @PostMapping("/add")
    Solution add(@RequestBody Solution solution){
        return solutionService.creer(solution);
    }

    @PutMapping("/update/{id_solution}")
    Solution update(@RequestBody Solution solution, @PathVariable Long id_solution){
        return solutionService.modifier(solution, id_solution);
    }

    @DeleteMapping("/delete/{id_solution}")
    String delete(@PathVariable Long id_solution){
        return solutionService.supprimer(id_solution);
    }
}
