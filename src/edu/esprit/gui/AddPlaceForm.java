/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.GroupConstraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import edu.esprit.entities.PlaceToVisit;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServicePlace;
import edu.esprit.services.ServiceProduit;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;

/**
 *
 * @author Nasr
 */
public class AddPlaceForm extends SideMenuBaseForm {

    private Boolean isInEditMode = Boolean.FALSE;
        TextField nameField = new TextField("", "Place Name", 20, TextField.ANY);
        TextField cityfield = new TextField("", "City Name", 20, TextField.ANY);
        TextField typeField = new TextField("", "Place type", 20, TextField.ANY);
        TextField descfield = new TextField("", "Place Description", 20, TextField.ANY);
        TextField adrfield = new TextField("", "Place Address", 20, TextField.ANY);
        TextField tickets_Price = new TextField("", "tickets Price", 20, TextField.NUMERIC);
    private int id_place;
       
    public AddPlaceForm(Resources res) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Container welcome = FlowLayout.encloseCenter(
                new Label("Ajout Place", "Welcome")
        );
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Command backCommand = new Command("Back") {
            public void actionPerformed(ActionEvent evt) {
                new PlaceListForm(res).show();
            }
        };
        tb.setBackCommand(backCommand);
        getTitleArea().setUIID("Container");
        Container Title = BoxLayout.encloseY(welcome);

        add(BorderLayout.NORTH, Title);

 nameField.getAllStyles().setFgColor(0xFF0000);
        cityfield.getAllStyles().setFgColor(0xFF0000);
        typeField.getAllStyles().setFgColor(0xFF0000);
        descfield.getAllStyles().setFgColor(0xFF0000);
        adrfield.getAllStyles().setFgColor(0xFF0000);
        tickets_Price.getAllStyles().setFgColor(0xFF0000);
 nameField.getAllStyles().setMargin(LEFT, 0);
        cityfield.getAllStyles().setMargin(LEFT, 0);
        typeField.getAllStyles().setMargin(LEFT, 0);
        descfield.getAllStyles().setMargin(LEFT, 0);
        adrfield.getAllStyles().setMargin(LEFT, 0);
        tickets_Price.getAllStyles().setMargin(LEFT, 0);

        Button addButton = new Button("Ajouter");
        addButton.setUIID("LoginButton");
    
        Validator val = new Validator();
        val.setShowErrorMessageForFocusedComponent(true);
        val.addConstraint(nameField,
                new GroupConstraint(
                        new LengthConstraint(4),
                        new RegexConstraint("^([a-zA-Z ]*)$", "Please only use latin characters for name"))).
                addSubmitButtons(addButton);
        val.setShowErrorMessageForFocusedComponent(true);
        val.addConstraint(cityfield,
                new GroupConstraint(
                        new LengthConstraint(5),
                        new RegexConstraint("^([a-zA-Z ]*)$", "Please only use latin characters for name"))).
                addSubmitButtons(addButton);
        val.addConstraint(typeField,
                new GroupConstraint(
                        new LengthConstraint(5),
                        new RegexConstraint("^([a-zA-Z ]*)$", "Please only use latin characters for name"))).
                addSubmitButtons(addButton);
        val.addConstraint(descfield,
                new GroupConstraint(
                        new LengthConstraint(15),
                        new RegexConstraint("^([a-zA-Z ]*)$", "Please only use latin characters for name"))).
                addSubmitButtons(addButton);
                val.addConstraint(adrfield,
                new GroupConstraint(
                        new LengthConstraint(15),
                        new RegexConstraint("^([a-zA-Z ]*)$", "Please only use latin characters for name"))).
                addSubmitButtons(addButton);
                                val.addConstraint(tickets_Price,
                new GroupConstraint(
                        new LengthConstraint(1),
                        new RegexConstraint("^([0-9 ]*)$", "Please only use numbers "))).
                addSubmitButtons(addButton);
                                       if (isInEditMode) {
            handleEditOperation(res);
            return;
        } 
        addButton.addActionListener((e) -> {
            PlaceToVisit p = new PlaceToVisit();
            p.setPlace_name(nameField.getText());
            p.setCityname(cityfield.getText());
            p.setPlace_Type(typeField.getText());
            p.setPlace_Description(descfield.getText());
            p.setPlace_Address(adrfield.getText());
            p.setTickets_Price(Double.parseDouble(tickets_Price.getText()));

            System.err.println(p);
            ServicePlace.getInstance().ajoutPlace(p);
             new PlaceListForm(res).show()   ;           

        });
        Container formul = BoxLayout.encloseY(
                BorderLayout.center(nameField),
                BorderLayout.center(cityfield),
                BorderLayout.center(typeField),
                BorderLayout.center(descfield),
                BorderLayout.center(adrfield),
                BorderLayout.center(tickets_Price),
                addButton
        );
        formul.setScrollableY(true);
        formul.setScrollVisible(false);
        add(BorderLayout.CENTER, formul);
        setupSideMenu(res);


    }

    @Override
    protected void showOtherForm(Resources res) {
        new CreateProdForm(res).show();
    }
    public void infalteUI(PlaceToVisit place) {
        nameField.setText(place.getPlace_name());
        cityfield.setText(place.getCityname());
        typeField.setText(place.getPlace_Type());
        descfield.setText(place.getPlace_Description());
        adrfield.setText(place.getPlace_Address());
        tickets_Price.setText(String.valueOf(place.getTickets_Price()));
        id_place = place.getPlace_Id() ;

       isInEditMode = Boolean.TRUE;
    }
        private void handleEditOperation(Resources res) {
                        PlaceToVisit p = new PlaceToVisit();
            p.setPlace_name(nameField.getText());
            p.setCityname(cityfield.getText());
            p.setPlace_Type(typeField.getText());
            p.setPlace_Description(descfield.getText());
            p.setPlace_Address(adrfield.getText());
            p.setTickets_Price(Double.parseDouble(tickets_Price.getText()));

            System.err.println(p);
            ServicePlace.getInstance().modifierPlace(p);
             new PlaceListForm(res).show()   ;    

    }
}
