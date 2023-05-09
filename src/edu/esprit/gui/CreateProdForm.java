
package edu.esprit.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import edu.esprit.entities.Produit;
import edu.esprit.services.ServiceProduit;

/**
 * The SignUp form
 *
 * @author Shai Almog
 */
public class CreateProdForm extends SideMenuBaseForm {

    public CreateProdForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        Container welcome = FlowLayout.encloseCenter(
                new Label("Ajout Produit", "WelcomeBlue")
        );
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        getTitleArea().setUIID("Container");
        Container Title = BoxLayout.encloseY(welcome);

        add(BorderLayout.NORTH, Title);

        TextField nameField = new TextField("", "Nom produit", 20, TextField.ANY);
        TextField descField = new TextField("", "Description", 20, TextField.ANY);
        TextField typeField = new TextField("", "Type", 20, TextField.ANY);
        TextField priceField = new TextField("", "Prix", 20, TextField.NUMERIC);
        TextField qteField = new TextField("", "Quantite", 20, TextField.NUMERIC);
        TextField statusField = new TextField("", "Status", 20, TextField.ANY);
        nameField.getAllStyles().setMargin(LEFT, 0);
        descField.getAllStyles().setMargin(LEFT, 0);
        typeField.getAllStyles().setMargin(LEFT, 0);
        priceField.getAllStyles().setMargin(LEFT, 0);
        qteField.getAllStyles().setMargin(LEFT, 0);
        statusField.getAllStyles().setMargin(LEFT, 0);

        Button addButton = new Button("Ajouter");
        addButton.setUIID("LoginButton");
        addButton.addActionListener((e) -> {
            Produit p = new Produit();
            p.setNameProd(nameField.getText());
            p.setProdDescription(descField.getText());
            p.setTypeProd(typeField.getText());
            p.setPriceProd(Float.parseFloat(priceField.getText()));
            p.setQuantite(Integer.parseInt(qteField.getText()));
            p.setStatus(statusField.getText());
            System.err.println(p);
            ServiceProduit.getInstance().ajoutProduit(p);
        });
        Container formul = BoxLayout.encloseY(
                BorderLayout.center(nameField),
                BorderLayout.center(descField),
                BorderLayout.center(typeField),
                BorderLayout.center(priceField),
                BorderLayout.center(qteField),
                BorderLayout.center(statusField),
                addButton
        );
        formul.setScrollableY(true);
        formul.setScrollVisible(false);
        add(BorderLayout.CENTER, formul);
        setupSideMenu(theme);
    }

    @Override
    protected void showOtherForm(Resources res) {
        new CreateProdForm(res).show();
    }
}
