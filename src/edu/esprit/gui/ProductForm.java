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

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
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
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user profile in the app, the first form we open after the
 * walkthru
 *
 * @author Shai Almog
 */
public class ProductForm extends SideMenuBaseForm {

    List<Produit> prods = ServiceProduit.getInstance().affichageProduits();
    int sum = 0;

    Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    public ProductForm(Resources res) {
        super(BoxLayout.y());
        for (Produit p : prods) {
            sum += p.getQuantite();
        }
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

        Container remainingTasks = BoxLayout.encloseY(
                new Label(String.valueOf(prods.size()), "CenterTitle"),
                new Label("products", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label(String.valueOf(sum), "CenterTitle"),
                new Label("available items", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.addActionListener((e) -> new CreateProdForm(res).show());
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        add(new Label("List of Products", "TodayTitle"));
        TextField searchField = new TextField("", "entrer Titre!!");
        searchField.setUIID("TextFieldBlack");
        add(BorderLayout.west(new Label("Recherche", "PaddedLabel"))
                .add(BorderLayout.CENTER, searchField));
        searchField.addActionListener(e -> {
            String searchQuery = searchField.getText();
            List<Produit> filteredData = new ArrayList<Produit>();
            for (Produit p : prods) {
                if (p.getNameProd().contains(searchQuery.toLowerCase())) {
                    filteredData.add(p);
                }
            }
            all.removeAll();
            for (Produit rec : filteredData) {
                addButtonBottom(rec, BOTTOM, focusScrolling);
            }
        });
        for (Produit p : prods) {
            addButtonBottom(p, CENTER, focusScrolling);
        }
        add(all);
        setupSideMenu(res);
    }

    private void addButtonBottom(Produit p, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(p.getNameProd());
        finishLandingPage.setTextLine2(p.getTypeProd() + " || " + p.getPriceProd() + "DT");
        finishLandingPage.setEmblem(createNumImage(p.getQuantite()));
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.addActionListener((e) -> Dialog.show(null, p.getProdDescription(), "OK", null));
        all.add(FlowLayout.encloseIn(finishLandingPage));
    }

    private Image createNumImage(int i) {
        int width = 100;
        int height = 100;
        int backgroundColor = 0x4dc2ff;
        Image image = Image.createImage(width, height);
        Graphics graphics = image.getGraphics();
        graphics.setColor(backgroundColor);
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 2;
        graphics.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, 0, 360);
        Font font = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        int numberColor = 0xFFFFFF; // White color for the number
        graphics.setFont(font);
        graphics.setColor(numberColor);
        String numberText = String.valueOf(i);
        int textWidth = font.stringWidth(numberText);
        int textHeight = font.getHeight();
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2;
        graphics.drawString(numberText, x, y);
        return EncodedImage.createFromImage(image, false);
    }

    @Override
    protected void showOtherForm(Resources res) {
        new ProductForm(res).show();
    }
}
