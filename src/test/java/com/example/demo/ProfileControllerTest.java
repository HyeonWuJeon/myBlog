package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.TextParsingException;
import org.springframework.context.annotation.Profile;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.*;


class ProfileControllerTest {

        @Test
//        @DisplayName("real_profile 조회된다")
        public void real_profile_find(){
            //given
            String expectedProfile ="real";
            MockEnvironment env = new MockEnvironment();
            env.addActiveProfile(expectedProfile);
            env.addActiveProfile("ouath");
            env.addActiveProfile("real-db");

            ProfileController controller = new ProfileController(env);

            //when
            String profile = controller.proflie();

            assertEquals(profile, expectedProfile, ()->"properties file");
        }

        @Test
//        @DisplayName("profile이 없으면 oauth가 조회된다.")
        public void real_profile_oauth() {
            //given
            String extpectedProfile = "oauth";
            MockEnvironment env = new MockEnvironment();

            env.addActiveProfile(extpectedProfile);
            env.addActiveProfile("real-db");

            ProfileController controller = new ProfileController(env);

            //when
            String profile = controller.proflie();
            System.out.println("profile: " +profile);
            System.out.println("expectedProfile"+ extpectedProfile);
            //then
            assertEquals(profile, extpectedProfile);
        }

        @Test
//        @DisplayName("prfile이 없으면 default가 조회된다.")
        public void active_profile(){
            //given
            String expectedProfile ="default";
            MockEnvironment env = new MockEnvironment();
            ProfileController controller = new ProfileController(env);

            //when
            String profile = controller.proflie();

            //then
            assertEquals(profile, expectedProfile);

        }
}