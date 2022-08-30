package com.example.Error_Notes.Controller;

import com.example.Error_Notes.ConfigurationJWTSERCURITY.JwtTokenUtil;
import com.example.Error_Notes.Services.JwtUserDetailsService;
import com.example.Error_Notes.models.JwtRequest;
import com.example.Error_Notes.models.JwtResponse;
import com.example.Error_Notes.models.User;
import com.example.Error_Notes.models.UserDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value ="/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value ="/add",method =RequestMethod.POST )
    public ResponseEntity<?> add(@RequestBody UserDto user) throws Exception{
        return ResponseEntity.ok(userDetailsService.save(user));

    }

    @PutMapping("/update/{iduser}")
    User update(@RequestBody User user, @PathVariable Long iduser){
        //retourner un String
        return userDetailsService.modifier(user,iduser);
    }
    @GetMapping("/list")
    List<User> lister(){
        return userDetailsService.lister();
    }

    @DeleteMapping("/delete/{iduser}")
    public String delete(@PathVariable Long iduser){
        this.userDetailsService.supprimer(iduser);
        return "utilisateur supprimée";
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
