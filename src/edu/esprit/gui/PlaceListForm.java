/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Codename One/guibuilderForm.java to edit this template
 */
package edu.esprit.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import edu.esprit.entities.PlaceToVisit;
import edu.esprit.services.ServicePlace;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author Nasr
 */
public class PlaceListForm extends SideMenuBaseForm {

    List<PlaceToVisit> prods = ServicePlace.getInstance().affichagePlaces();
    int sum = 0;

    Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    public PlaceListForm(Resources res)  {
        super(BoxLayout.y());
            sum = prods.size();
        
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
int szi =   prods.size() ;
        Container remainingTasks = BoxLayout.encloseY(
                new Label("Traveling", "CenterTitle"),
                new Label(String.valueOf(szi)+" posts ", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");


        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                GridLayout.encloseIn(2, remainingTasks)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener((e) -> new AddPlaceForm(res).show());
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, remainingTasks.getPreferredH() - fab.getPreferredH() );
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        add(new Label("Place To Visit", "TodayTitle"));
        TextField searchField = new TextField("", "entrer Titre!!");
        searchField.setUIID("TextFieldBlack");
        add(BorderLayout.west(new Label("Recherche", "PaddedLabel"))
                .add(BorderLayout.CENTER, searchField));
        searchField.addActionListener(e -> {
            String searchQuery = searchField.getText();
            List<PlaceToVisit> filteredData = new ArrayList<PlaceToVisit>();
            for (PlaceToVisit p : prods) {
                if (p.getPlace_name().contains(searchQuery.toLowerCase())) {
                    filteredData.add(p);
                }
            }
            all.removeAll();
            for (PlaceToVisit rec : filteredData) {
                System.out.println("the filtred data is " + rec);
                addButtonBottom(rec, CENTER, focusScrolling , res);
            }
        });
        for (PlaceToVisit p : prods) {
            addButtonBottom(p, CENTER, focusScrolling, res);
        }
        add(all);
        setupSideMenu(res);
    }

    private void addButtonBottom(PlaceToVisit p, int color, boolean first , Resources res) {
        MultiButton finishLandingPage = new MultiButton(p.getPlace_name());
        finishLandingPage.setTextLine2(p.getPlace_Type() + " || " + p.getTickets_Price() );
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.addActionListener((e) ->{
            new SoloPlaceForm(res , p ).show();
        });
        all.add(FlowLayout.encloseIn(finishLandingPage));
    }

  
    @Override
    protected void showOtherForm(Resources res) {
            new PlaceListForm(res).show();

}


}