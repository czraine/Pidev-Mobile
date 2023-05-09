/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.GroupLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.GroupConstraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import edu.esprit.api.Weather;
import edu.esprit.entities.PlaceToVisit;
import edu.esprit.entities.Reviews;
import edu.esprit.services.ServicePlace;
import edu.esprit.services.ServiceReviews;

/**
 *
 * @author Nasr
 */
public class SoloPlaceForm extends Form {

    private PlaceToVisit place;

    private Label placeNameLabel;
    private Label cityNameLabel;
    private Label placeTypeLabel;
    private Label placeDescriptionLabel;
    private Image placeImages1;
    private Image placeImages2;
    private Image placeImages3;


    private Label ticketPriceLabel;
    private Button returnButton;
    private TextField rateme ;
    private TextArea reviewCommentArea;
    private Button submitReviewButton;

        public SoloPlaceForm(Resources res, PlaceToVisit place)  {
        super(new BorderLayout());

        this.place = place;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Command backCommand = new Command("Back") {
            public void actionPerformed(ActionEvent evt) {
                    new PlaceListForm(res).show();
              
            }
        };
        tb.setBackCommand(backCommand);
Command deleteCommand = new Command("Delete"){
            public void actionPerformed(ActionEvent evt) {
                ServicePlace.getInstance().deletePlace(place.getId());
                                    new PlaceListForm(res).show();

            }
        };
Command editcomand = new Command("Edit"){
            public void actionPerformed(ActionEvent evt) {
           AddPlaceForm apf = new AddPlaceForm(res);
           apf.infalteUI(place);
           apf.show();
           
            }
        };
Command ShowComment = new Command("Show Comments"){
            public void actionPerformed(ActionEvent evt) {
                    new ReviewListForm( place , res).show();
              
            }
        };
tb.addCommandToOverflowMenu(editcomand);
tb.addCommandToOverflowMenu(deleteCommand);
tb.addCommandToOverflowMenu(ShowComment);
        // Initialize UI components
        placeNameLabel = new Label(place.getPlace_name());
        cityNameLabel = new Label(place.getCityname());
        placeTypeLabel = new Label(place.getPlace_Type());
        placeDescriptionLabel = new Label(place.getPlace_Description());
        ticketPriceLabel = new Label("Ticket Price: " + place.getTickets_Price());
        rateme = new TextField();   
        reviewCommentArea = new TextArea();
        submitReviewButton = new Button("Submit Review");
//        reviewsListView = new ListView<Reviews>((ObservableList<Reviews>) ServiceReviews.getInstance().affichageReviewss());

        // Set up the images


        placeImages1 = res.getImage("culture-2.jpg");

        placeImages2 = res.getImage(place.getPlace_img2());

        placeImages3 = res.getImage("culture-2.jpg");

        // Set up the layout
        Container topContainer = new Container(new FlowLayout(Component.CENTER, Component.CENTER));
  topContainer.add(placeNameLabel);
        topContainer.getStyle().setMargin(0, 0, 20, 0);
        add(BorderLayout.NORTH, topContainer);

        Container imagesContainer = new Container(new GridLayout(1, 3));
        imagesContainer.add(placeImages1);
        imagesContainer.add(placeImages2);
        imagesContainer.add(placeImages3);
        imagesContainer.getStyle().setMargin(0, 10, 20, 10);
        // add(BorderLayout.CENTER, imagesContainer);

        Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        detailsContainer.getStyle().setMargin(20, 20, 20, 20);
        detailsContainer.add(placeImages1);
        detailsContainer.add(placeImages2);
        detailsContainer.add(placeImages3);
        detailsContainer.add(cityNameLabel);
        detailsContainer.add(placeTypeLabel);
        detailsContainer.add(placeDescriptionLabel);
        detailsContainer.add(ticketPriceLabel);
        detailsContainer.getStyle().setMarginBottom(50);
        add(BorderLayout.CENTER, detailsContainer);

        Container bottomContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        rateme.setHint("Add your rate ");
        reviewCommentArea.setHint("Write your review here...");
        Reviews review = new Reviews();
        submitReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 review.setRating(Double.parseDouble( rateme.getText()));
                 review.setReview_txt(reviewCommentArea.getText());
                 review.setPlace_id(place.getId());
                 review.setId_User(124);
                 review.setPlace_Name(place.getPlace_name());
                 
                ServiceReviews.getInstance().ajoutReviews(review);
                rateme.clear();
                reviewCommentArea.setText("");
            }
        });
        bottomContainer.add(rateme);
        bottomContainer.add(reviewCommentArea);
        bottomContainer.add(submitReviewButton);
        bottomContainer.getStyle().setMargin(0, 20, 0, 20);
        add(BorderLayout.SOUTH, bottomContainer);
        Validator val = new Validator();
        val.setShowErrorMessageForFocusedComponent(true);
        val.addConstraint(reviewCommentArea, 
                new GroupConstraint(
                        new LengthConstraint(10), 
                        new RegexConstraint("^([a-zA-Z ]*)$", "Please only use latin characters for name"))).
                addSubmitButtons(submitReviewButton);
                val.setShowErrorMessageForFocusedComponent(true);
        val.addConstraint(rateme, 
                new GroupConstraint(
                        new LengthConstraint(1), 
                        new RegexConstraint("^([0-9 ]*)$", "Please only use numbers betwen 0 and 9"))).
                addSubmitButtons(submitReviewButton);


        // Set up the reviews list view
        //  reviewsListView.setRenderer(new ReviewListCellRenderer());
        // add(BorderLayout.WEST, reviewsListView);
    }
}
