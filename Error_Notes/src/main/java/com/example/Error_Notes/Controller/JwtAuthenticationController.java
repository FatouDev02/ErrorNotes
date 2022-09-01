package com.example.Error_Notes.Controller;

import com.example.Error_Notes.ConfigurationJWTSERCURITY.JwtTokenUtil;
import com.example.Error_Notes.Services.JwtUserDetailsService;
import com.example.Error_Notes.Services.SolutionService;
import com.example.Error_Notes.models.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;
@Autowired
    private SolutionService solutionService;
    @RequestMapping(value ="/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
////////////////user
    @RequestMapping(value ="/add",method =RequestMethod.POST )
    public ResponseEntity<?> add(@RequestBody UserDto user) throws Exception{
        return ResponseEntity.ok(userDetailsService.save(user));

    }
@PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{iduser}")
    User update(@RequestBody User user, @PathVariable Long iduser){
        //retourner un String
        return userDetailsService.modifier(user,iduser);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    List<User> lister(){
        return userDetailsService.lister();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{iduser}")
    public String delete(@PathVariable Long iduser){
        this.userDetailsService.supprimer(iduser);
        return "utilisateur supprimée";
    }

    /////////////////////////Fin User

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    ////////////////////////////////////////////Probleme
    @GetMapping("/search/{mot_cle}")
    Object search(@PathVariable String mot_cle){
        return userDetailsService.recherche(mot_cle);
    }
    @ApiOperation(value = "Ajouter un Probleme ")
    @PostMapping("/addprob")
    public String addp(@RequestBody Probleme probleme, Long id_probleme){
        if(this.userDetailsService.creerprobleme(probleme,id_probleme)==null){

            return "cet probleme existe deja";
        }
        return "probleme ajouté";
    }

    @ApiOperation(value = "Modifier un probleme ")
    @PutMapping("/updateprob/{idprobleme}")
    public String updatep(@RequestBody Probleme probleme, @PathVariable Long idprobleme){
        this.userDetailsService.modifierprobleme(probleme,idprobleme);
        return "modification reussi";
    }

    @ApiOperation(value = "Supprimer un probleme ")
    @DeleteMapping("/deleteprob/{idprobleme}")
    public  String deletep(@PathVariable Long idprobleme){
        this.userDetailsService.supprimerprobleme(idprobleme);
        return "Suppression reussi";
    }
    @GetMapping("/displayprobleme/{titre}")
    public Probleme displayp(@PathVariable String titre){
        return userDetailsService.trouverProblemeParTitre(titre);
    }
    //////////////////////////FinProbleme
    /////////////////////////////Solution
    @ApiOperation(value = "Donner une solution")
    @PostMapping("/addsolution")
    Solution addS(@RequestBody Solution solution){
        return  userDetailsService.creerSolution(solution);
    }

    @ApiOperation(value = "Modifier Une solution")
    @PutMapping("/updatesolution/{id_solution}")
    Solution updateS(@RequestBody Solution solution, @PathVariable Long id_solution){
        return userDetailsService.modifierSolution(solution,id_solution);
    }

    @ApiOperation(value = " Supprimer une Solution")
    @DeleteMapping("/deletesolution/{id_solution}")
    public String deleteS(@PathVariable Long id_solution){

        return  userDetailsService.supprimerSolution(id_solution);
    }
    /////////////////FInSolution
    ///////////////////////////////////////Commentaire
    @PostMapping("/addCom")
    @ApiOperation(value = "Ajouter un commentaire")
    Commentaire addCom(@RequestBody Commentaire commentaire){

        return userDetailsService.CreerComm(commentaire);
    }

    @DeleteMapping("/deletecomm/{id_Commentaire}")
    @ApiOperation(value = "Supprimer un commentaire ")
    public String deleteCom(@PathVariable Long id_Commentaire){

        return userDetailsService.SupprimerComm(id_Commentaire);
    }
    @GetMapping("/Affichercommparsolution/{id_solution}")
    public List<Commentaire> displayComm(@PathVariable Long id_solution){
        Solution solution=solutionService.TouverSolutionparId(id_solution);
        return  userDetailsService.AfficherCommentaireParSolution(solution);

    }
    /////////////////////////////FinCommmentaire

}
