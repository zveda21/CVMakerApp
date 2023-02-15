package org.example;

import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.example.connection.ConnectionFactory;
import org.example.controllers.*;
import org.example.manager.JwtTokenManagerImpl;
import org.example.manager.TokenManager;
import org.example.repository.*;
import org.example.service.*;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(7076);
        try {


            Connection connection = new ConnectionFactory("public", 5433).getConnection();

            //signup user
            TokenManager manager = new JwtTokenManagerImpl();
            UserRepositoryImpl userRepository = new UserRepositoryImpl(connection);
            UserService userService = new UserServiceImpl(userRepository, manager);
            UserController userController = new UserController(userService, manager);
            app.post("/signup", userController::signup);

            // login user
            app.post("/login", userController::login);
            // update user's personal information
            app.put("/user", userController::addPersonalInformation);
            // get user information
            app.get("/user", userController::getUserPersonalInformation);

            //Education
            EducationRepositoryImpl educationRepository = new EducationRepositoryImpl(connection);
            EducationService educationService = new EducationServiceImpl(educationRepository);
            EducationController educationController = new EducationController(educationService, manager);
            app.post("/education", educationController::createEducation);
            app.get("/education", educationController::getAllEducationOfUser);

            //Language
            LanguageRepositoryImpl languageRepository = new LanguageRepositoryImpl(connection);
            LanguageServiceImpl languageService = new LanguageServiceImpl(languageRepository);
            LanguageController languageController = new LanguageController(languageService, manager);
            app.post("/language", languageController::createLanguage);
            app.get("/language", languageController::getAllLanguagesOfUser);

            //Work Experience
            WorkExperienceRepositoryImpl workExperienceRepository = new WorkExperienceRepositoryImpl(connection);
            WorkExperienceServiceImpl workExperienceService = new WorkExperienceServiceImpl(workExperienceRepository);
            WorkExperienceController workExperienceController = new WorkExperienceController(workExperienceService, manager);
            app.post("/experience", workExperienceController::createWorkExperience);
            app.get("/experience", workExperienceController::getAllWorkExperienceOfUser);


            SkillRepositoryImpl skillRepository = new SkillRepositoryImpl(connection);
            SkillService skillService = new SkillServiceImpl(skillRepository);
            SkillController skillController = new SkillController(skillService, manager);

            app.post("/skill", skillController::createSkill);
            app.get("/skill", skillController::getAllUserSkills);

        } catch (SQLException e) {
            System.err.println("DB connection failed");
            System.exit(1);
        }
    }
}

