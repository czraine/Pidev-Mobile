/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import edu.esprit.entities.Reviews;
import edu.esprit.services.ServiceReviews;

/**
 *
 * @author Nasr
 */
 class ReviewPage extends Form {

        public ReviewPage(Reviews review , Resources res) {
            super(new BorderLayout());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Command backCommand = new Command("Back") {
            public void actionPerformed(ActionEvent evt) {
 new PlaceListForm(res).show()   ;           
            }
        };
        tb.setBackCommand(backCommand);
        Command deleteCommand = new Command("Delete"){
            public void actionPerformed(ActionEvent evt) {
                ServiceReviews.getInstance().deleteReviews(review.getReview_id());
                                    new PlaceListForm(res).show();

            }
        };
        tb.addCommandToOverflowMenu(deleteCommand);

            setTitle(review.getPlace_Name() + "'s Review");

            // create a container for the review details
            Container reviewDetails = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            reviewDetails.add(createLabel("Name:", review.getPlace_Name()));
            reviewDetails.add(createLabel("Text:", review.getReview_txt()));
            reviewDetails.add(createLabel("Rating:", String.valueOf(review.getRating())));

            // add the review details container to the form
            add(BorderLayout.CENTER, reviewDetails);
        }

        // helper method to create a label for a review detail
        private Component createLabel(String labelText, String detailText) {
            MultiButton label = new MultiButton(labelText);
            label.setTextLine2(detailText);
            label.setTextLine3("");
            label.setEmblem(null);
            label.setUIID("Label");
            label.setEnabled(false);
            return label;
        }
    }
