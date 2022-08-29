package com.example.Error_Notes.Controller;

import com.example.Error_Notes.Services.SolutionService;
import com.example.Error_Notes.models.Solution;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solution")
@Data

@Api(value = "hello", description = "Methodes sur Solution")

public class SolutionController {
    @Autowired
    SolutionService solutionService;
    @ApiOperation(value = "Donner une solution")
    @PostMapping("/add")
    Solution add(@RequestBody Solution solution){
        return solutionService.creer(solution);
    }

    @ApiOperation(value = "Modifier Une solution")
    @PutMapping("/update/{id_solution}")
    Solution update(@RequestBody Solution solution, @PathVariable Long id_solution){
        return solutionService.modifier(solution, id_solution);
    }
    @ApiOperation(value = " Supprimer une Solution")
    @DeleteMapping("/delete/{id_solution}")
    public String delete(@PathVariable Long id_solution){
        return solutionService.supprimer(id_solution);
    }
}
