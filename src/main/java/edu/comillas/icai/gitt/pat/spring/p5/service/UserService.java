package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private Hashing hashing;

    public Token login(String email, String password) {
        AppUser appUser = appUserRepository.findByEmail(email); //busco por email
        if (appUser == null ||  !hashing.compare(appUser.getPassword(), password)) return null;//!appUser.getPassword().equals(password)) return null; //si no lo encuentra o la contraseña no coincide retorna null

        Token token = tokenRepository.findByAppUser(appUser); //devuelve el token asociado si era correcto el login

        if(token == null){
            token = new Token();
            token.setAppUser(appUser);
            tokenRepository.save(token); // si no tiene token activo y las credenciales son correctas se debe de crear uno porque si
            // en logout se elimina, cuando vuelv a a iniciar sesión se debe de crear uno nuevo

        }

        return token;
    }

    public AppUser authentication(String tokenId) {

        Optional<Token> tokenOptional = tokenRepository.findById(tokenId); //Es un optional porque puede ser null

        if (!tokenOptional.isPresent()) { //si no existe retorna un null
            return null;
        }

        // Si el token está presente, obtenemos el valor y devolvemos el AppUser asociado
        Token token = tokenOptional.get();
        return token.getAppUser();
    }

    public ProfileResponse profile(AppUser appUser) {
        AppUser appUsuario = appUserRepository.findByEmail(appUser.getEmail());
        if (appUsuario == null) return null;
        return new ProfileResponse(appUsuario.getName(),appUsuario.getEmail(),appUsuario.getRole());
    }

    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {

        AppUser appUsuario = appUserRepository.findByEmail(appUser.getEmail());
        if (appUsuario == null) return null;

        appUsuario.setName(profile.name());
        appUsuario.setRole(profile.role());
        appUsuario.setPassword(hashing.hash(profile.password()));
        //appUsuario.setPassword(profile.password());
        appUserRepository.save(appUsuario); //Si existe en la base de datos actualiza el usuario con esa ID que es la clave primaria
        return new ProfileResponse(appUsuario.getName(),appUsuario.getEmail(),appUsuario.getRole());

    }
    public ProfileResponse profile(RegisterRequest register) {

        AppUser existingUser = appUserRepository.findByEmail(register.email());
        if (existingUser != null)  throw new IllegalArgumentException("El usuario con este email ya está registrado.");; //el usuario estaría registrado

        AppUser appUser = new AppUser();
        appUser.setName(register.name());
        appUser.setEmail(register.email());
        appUser.setRole(register.role());
        //appUser.setPassword(register.password());
        appUser.setPassword(hashing.hash(register.password()));

        appUserRepository.save(appUser);//lo guarda en base de datos
        return new ProfileResponse(appUser.getName(),appUser.getEmail(),appUser.getRole());
    }

    public void logout(String tokenId) {

        Optional<Token> tokenOptional = tokenRepository.findById(tokenId);
        if (tokenOptional.isPresent()) {
            tokenRepository.delete(tokenOptional.get());//si el token existe lo eliminamos para cerrar sesion
        }else {
            throw new IllegalArgumentException("Token no encontrado");
        }
    }

    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }

}
