/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package edu.esprit.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import edu.esprit.entities.User;
import edu.esprit.services.UserService;

/**
 * The SignUp form
 *
 * @author Shai Almog
 */
public class SignUpForm extends Form {

    public SignUpForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Sign", "WelcomeWhite"),
                new Label("Up", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton guide = RadioButton.createToggle("Guide", barGroup);
        RadioButton touriste = RadioButton.createToggle("Touriste", barGroup);

        Container Title = BoxLayout.encloseY(
                welcome,
                LayeredLayout.encloseIn(
                        GridLayout.encloseIn(2, guide, touriste)));

        add(BorderLayout.NORTH, Title);
        Container layerTouriste = createTouristForm(theme);
        Container layerGuide = createGuideForm(theme);
        layerTouriste.setVisible(false);
        layerGuide.setVisible(false);
        Container formulaire = LayeredLayout.encloseIn(layerTouriste, layerGuide);
        add(BorderLayout.CENTER, formulaire);
        touriste.setSelected(false);
        touriste.addActionListener((e) -> {
            layerTouriste.setVisible(true);
            layerGuide.setVisible(false);
            revalidate();
        });
        guide.setSelected(false);
        guide.addActionListener((e) -> {
            layerGuide.setVisible(true);
            layerTouriste.setVisible(false);
            revalidate();
        });

    }

    public Container createTouristForm(Resources theme) {
        TextField fnameField = new TextField("", "First name", 20, TextField.ANY);
        TextField lnameField = new TextField("", "Last name", 20, TextField.ANY);
        TextField phoneField = new TextField("", "Phone number", 20, TextField.NUMERIC);
        TextField userField = new TextField("", "Username", 20, TextField.ANY);
        TextField mailField = new TextField("", "Mail", 20, TextField.EMAILADDR);
        TextField pwdField = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField natField = new TextField("", "Nationality", 20, TextField.ANY);
        TextField langField = new TextField("", "Language", 20, TextField.ANY);

        fnameField.getAllStyles().setMargin(LEFT, 0);
        lnameField.getAllStyles().setMargin(LEFT, 0);
        phoneField.getAllStyles().setMargin(LEFT, 0);
        userField.getAllStyles().setMargin(LEFT, 0);
        mailField.getAllStyles().setMargin(LEFT, 0);
        pwdField.getAllStyles().setMargin(LEFT, 0);
        natField.getAllStyles().setMargin(LEFT, 0);
        langField.getAllStyles().setMargin(LEFT, 0);

        Button createNewAccount = new Button("Already have an account, SignIn");
        createNewAccount.setUIID("SignIn");
        createNewAccount.getAllStyles().setAlignment(CENTER);
        createNewAccount.addActionListener(e -> {
            Toolbar.setGlobalToolbar(false);
            new LoginForm(theme).show();
            Toolbar.setGlobalToolbar(true);
        });
        
        Button signupTourButton = new Button("SIGNUP");
        signupTourButton.setUIID("LoginButton");
        signupTourButton.addActionListener((e) -> {
            User u=new User();
            u.setFname(fnameField.getText());
            u.setLname(lnameField.getText());
            u.setPhone(Integer.parseInt(phoneField.getText()));
            u.setUname(userField.getText());
            u.setMail(mailField.getText());
            u.setPwd(pwdField.getText());
            u.setLang(langField.getText());
            u.setNationality(natField.getText());
            UserService.getInstance().signupGuide(u);
            new LoginForm(theme).show();
        });
        Container byTour = BoxLayout.encloseY(
                BorderLayout.center(fnameField),
                BorderLayout.center(lnameField),
                BorderLayout.center(phoneField),
                BorderLayout.center(userField),
                BorderLayout.center(mailField),
                BorderLayout.center(pwdField),
                BorderLayout.center(natField),
                BorderLayout.center(langField),
                signupTourButton,
                createNewAccount
        );
        byTour.setScrollableY(true);
        byTour.setScrollVisible(false);
        return byTour;
    }

    public Container createGuideForm(Resources theme) {
        TextField fnameField = new TextField("", "First name", 20, TextField.ANY);
        TextField lnameField = new TextField("", "Last name", 20, TextField.ANY);
        TextField phoneField = new TextField("", "Phone number", 20, TextField.NUMERIC);
        TextField userField = new TextField("", "Username", 20, TextField.ANY);
        TextField mailField = new TextField("", "Mail", 20, TextField.EMAILADDR);
        TextField pwdField = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField lang1Field = new TextField("", "First language", 20, TextField.ANY);
        TextField lang2Field = new TextField("", "Second language", 20, TextField.ANY);
        TextField lang3Field = new TextField("", "Third language", 20, TextField.ANY);
        TextField cityField = new TextField("", "City", 20, TextField.ANY);

        fnameField.getAllStyles().setMargin(LEFT, 0);
        lnameField.getAllStyles().setMargin(LEFT, 0);
        phoneField.getAllStyles().setMargin(LEFT, 0);
        userField.getAllStyles().setMargin(LEFT, 0);
        mailField.getAllStyles().setMargin(LEFT, 0);
        pwdField.getAllStyles().setMargin(LEFT, 0);
        lang1Field.getAllStyles().setMargin(LEFT, 0);
        lang2Field.getAllStyles().setMargin(LEFT, 0);
        lang3Field.getAllStyles().setMargin(LEFT, 0);
        cityField.getAllStyles().setMargin(LEFT, 0);

        Button createNewAccount = new Button("Already have an account, SignIn");
        createNewAccount.setUIID("SignIn");
        createNewAccount.getAllStyles().setAlignment(CENTER);   
        createNewAccount.addActionListener(e -> {
            Toolbar.setGlobalToolbar(false);
            new LoginForm(theme).show();
            Toolbar.setGlobalToolbar(true);
        });

        Button signupGuideButton = new Button("SIGNUP");
        signupGuideButton.setUIID("LoginButton");
        signupGuideButton.addActionListener((e) -> {
            User u=new User();
            u.setFname(fnameField.getText());
            u.setLname(lnameField.getText());
            u.setPhone(Integer.parseInt(phoneField.getText()));
            u.setUname(userField.getText());
            u.setMail(mailField.getText());
            u.setPwd(pwdField.getText());
            u.setLang1(lang1Field.getText());
            u.setLang2(lang2Field.getText());
            u.setLang3(lang3Field.getText());
            u.setCity(cityField.getText());
            UserService.getInstance().signupGuide(u);
            new LoginForm(theme).show();
        });

        signupGuideButton.addActionListener((e) -> {
            User u=new User();
            u.setFname(fnameField.getText());
            u.setLname(lnameField.getText());
            u.setPhone(Integer.parseInt(phoneField.getText()));
            u.setUname(userField.getText());
            u.setMail(mailField.getText());
            u.setPwd(pwdField.getText());
            u.setLang1(lang1Field.getText());
            u.setLang2(lang2Field.getText());
            u.setLang3(lang3Field.getText());
            u.setCity(cityField.getText());
            UserService.getInstance().signupGuide(u);
            new LoginForm(theme).show();
        });

        Container byGuide = BoxLayout.encloseY(
                BorderLayout.center(fnameField),
                BorderLayout.center(lnameField),
                BorderLayout.center(phoneField),
                BorderLayout.center(userField),
                BorderLayout.center(mailField),
                BorderLayout.center(pwdField),
                BorderLayout.center(lang1Field),
                BorderLayout.center(lang2Field),
                BorderLayout.center(lang3Field),
                BorderLayout.center(cityField),
                signupGuideButton,
                createNewAccount
        );
        byGuide.setScrollableY(true);
        byGuide.setScrollVisible(false);
        return byGuide;
    }
}
