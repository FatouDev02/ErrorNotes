package com.example.Error_Notes.Services;

import com.example.Error_Notes.Repository.CommentaireRepository;
import com.example.Error_Notes.Repository.ProblemeRepository;
import com.example.Error_Notes.Repository.SolutionRepository;
import com.example.Error_Notes.Repository.UserRepository;
import com.example.Error_Notes.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private ProblemeRepository problemeRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user1=userRepository.findByUsername(username);

        if (user1 == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user1.getUsername(), user1.getPassword(),
                new ArrayList<>());
    }
    /////////////////////////////Utilisateurs
    public User save(UserDto userDto){
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        newUser.setNom(userDto.getNom());
        newUser.setPrenom(userDto.getPrenom());
        newUser.setEmail(userDto.getEmail());
        newUser.setRole(userDto.getRole());
        newUser.setAdresse(userDto.getAdresse());
        return userRepository.save(newUser);
    }

    public User modifier(User user, Long iduser) {
        User user1 = this.userRepository.findById(iduser).orElseThrow();
        user1.setNom(user.getNom());
        user1.setPrenom(user.getPrenom());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setAdresse(user.getAdresse());
        user1.setUsername(user.getUsername());
        //user1.setRole(user.getRole());
        return  userRepository.save(user1);
    }

    public List<User> lister() {
        return userRepository.findAll();
    }

    public String supprimer(Long iduser) {
        userRepository.deleteById(iduser);
        return "Suppression effectuée avec succés !";
    }
    //////////////////////////////FinUser
    //////////////////////////////////////////Probleme
    public Object recherche(String mot_cle) {
        if (mot_cle != null) {
            List<Probleme> retrouve = problemeRepository.findAll(mot_cle);
//            System.out.println(retrouve);
            if (retrouve.size() != 0) {
                return retrouve;
            }else{
                return "Désolé ce mot est introuvable !!";
            }
        }
        return problemeRepository.findAll();
    }
    public String creerprobleme(Probleme probleme, Long idprobleme) {
        Optional<Probleme> problemeOptional=problemeRepository.findByIdprobleme(probleme.getIdprobleme());
        if(problemeOptional.isPresent()){
            return null;
        }
        // Probleme probleme1=this.problemeRepository.save(probleme);
        //probleme1.setEtat(Etat.INITIAL);
        this.problemeRepository.save(probleme);
        return "Problème créer";
    }
    public Probleme modifierprobleme(Probleme probleme, Long idprobleme) {
        Probleme probleme1= problemeRepository.findById(idprobleme).orElseThrow();
        // probleme1.setEtat(probleme.getEtat());
        probleme1.setTitre(probleme.getTitre());
        probleme1.setEtat(probleme.getEtat());
        probleme1.setDescription(probleme.getDescription());
        probleme1.setTechnologie(probleme.getTechnologie());
        return problemeRepository.save(probleme1);
    }

    public String supprimerprobleme(Long idprobleme) {
        problemeRepository.deleteById(idprobleme);
        return "Suppression effectuée avec succés";
    }

    public Probleme trouverProblemeParId(Long id) {

        return problemeRepository.findById(id).get();
    }


    public Probleme trouverProblemeParTitre(String titre) {
        return problemeRepository.findByTitre(titre);
    }



    /////////////////////////////////////////////Fin Probleme


    //////////////////////////////////////////Solution

    public Solution creerSolution(Solution solution) {

        return solutionRepository.save(solution);
    }

    public Solution modifierSolution(Solution solution, Long id_solution) {
        return solutionRepository.findById(id_solution)
                .map(s ->{
                    s.setDescription(solution.getDescription());
                    s.setTemps(solution.getTemps());
                    s.setRessource(solution.getRessource());
                    return solutionRepository.save(s);
                } ).orElseThrow(() -> new RuntimeException("Cette solution n'existe pas !"));
    }
    public String supprimerSolution(Long id_solution) {
        solutionRepository.deleteById(id_solution);
        return "Suppression effectuée avec succés !";
    }
/////////////////////////////////////////////Fin Solution
public Commentaire CreerComm(Commentaire commentaire) {

        return commentaireRepository.save(commentaire);
}


    public List<Commentaire> listerComm() {

        return commentaireRepository.findAll();
    }

    public String SupprimerComm(Long id_Commentaire) {
        commentaireRepository.deleteById(id_Commentaire);
        return"Commentaire supprimé";

    }
    public List<Commentaire> AfficherCommentaireParSolution(Solution solution) {
        return commentaireRepository.findBySolution(solution);

    }

}
