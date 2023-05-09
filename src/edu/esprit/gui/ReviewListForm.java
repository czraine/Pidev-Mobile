/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import edu.esprit.entities.PlaceToVisit;
import edu.esprit.entities.Reviews;
import edu.esprit.services.ServicePlace;
import edu.esprit.services.ServiceReviews;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nasr
 */
public class ReviewListForm extends Form  {
    List<Reviews> prods = ServiceReviews.getInstance().affichageReviewss();

        public ReviewListForm(PlaceToVisit place , Resources res ) {
        setTitle("Reviews");
        setLayout(new BorderLayout());
        Container reviewList = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        reviewList.setScrollableY(true);
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Command backCommand = new Command("Back") {
            public void actionPerformed(ActionEvent evt) {
                    new SoloPlaceForm(res , place).show();
              
            }
        };
        tb.setBackCommand(backCommand);

            List<Reviews> filteredData = new ArrayList<Reviews>();

        for (Reviews review : prods) {
            MultiButton reviewButton = new MultiButton(review.getPlace_Name());
            reviewButton.setTextLine2(review.getReview_txt());
            reviewButton.setTextLine3("Rating: " + review.getRating());
            reviewButton.addActionListener(evt -> showReviewPage(review , res));
            reviewList.add(reviewButton);
        }

        // add the review list container to the form
     
        add(BorderLayout.CENTER, reviewList);
        show();
    }
      private void showReviewPage(Reviews review , Resources res) {
        // create a new ReviewPage for the selected review
        ReviewPage reviewPage = new ReviewPage(review , res);
        // show the ReviewPage
        reviewPage.show();
    }
}
