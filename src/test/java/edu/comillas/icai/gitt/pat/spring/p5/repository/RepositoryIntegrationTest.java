package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */
    @Test void saveTest() {
        // Given: creamos un usuario y lo guardamos
        AppUser user = new AppUser();
        user.setName("UserTest");
        user.setEmail("test@gmail.com");
        user.setRole(Role.USER);
        user.setPassword("Test123456");

        appUserRepository.save(user);


        Token token = new Token();
        token.setAppUser(user);

        tokenRepository.save(token);

        // When: buscamos el usuario por email y el token por usuario
        AppUser retrievedUser = appUserRepository.findByEmail("test@gmail.com");
        Token retrievedToken = tokenRepository.findByAppUser(user); //Retrieved de CRUD

        // Then: comprobamos que los datos se guardaron y se pueden recuperar correctamente
        Assertions.assertNotNull(retrievedUser); //Verifica que no es null
        Assertions.assertEquals("UserTest", retrievedUser.getName()); //Verifica que coincide
        Assertions.assertEquals("test@gmail.com", retrievedUser.getEmail()); //

        Assertions.assertNotNull(retrievedToken);
        Assertions.assertEquals(user.getId(), retrievedToken.getAppUser().getId());
    }


    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */
    @Test void deleteCascadeTest() {
        // Given creamos un usuario y lo guardamos con su token
        AppUser user = new AppUser();
        user.setName("UserTest");
        user.setEmail("test@gmail.com");
        user.setRole(Role.USER);
        user.setPassword("Test123456");

        appUserRepository.save(user);

        Token token = new Token();
        token.setAppUser(user);

        tokenRepository.save(token);
        // When borramos usuario
        appUserRepository.delete(user);
        // Then verificamos que el usuario y token se han eliminado
        Assertions.assertEquals(0, appUserRepository.count());
        Assertions.assertEquals(0, tokenRepository.count());
    }
}