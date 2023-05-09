/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Codename One/guibuilderForm.java to edit this template
 */
package edu.esprit.gui;

/**
 * GUI builder created Form
 *
 * @author Nasr
 */
public class TESTFORM extends com.codename1.ui.Form {

    public TESTFORM() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public TESTFORM(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.components.ScaleImageButton gui_ScaleImageButton = new com.codename1.components.ScaleImageButton();
    protected com.codename1.ui.Container gui_Border_Layout = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.components.SpanLabel gui_SpanLabel = new com.codename1.components.SpanLabel();
    protected com.codename1.components.ScaleImageButton gui_ScaleImageButton_1 = new com.codename1.components.ScaleImageButton();
    protected com.codename1.ui.Button gui_Button = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setScrollableY(true);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("TESTFORM");
        setName("TESTFORM");
        gui_ScaleImageButton.setPreferredSizeStr("267.9894mm 91.79894mm");
                gui_ScaleImageButton.setInlineStylesTheme(resourceObjectInstance);
        gui_ScaleImageButton.setName("ScaleImageButton");
        gui_ScaleImageButton.setBackgroundType((byte)33);
        gui_ScaleImageButton.setIcon(resourceObjectInstance.getImage("culture-2.jpg"));
        gui_Border_Layout.setPreferredSizeStr("261.37567mm 42.06349mm");
                gui_Border_Layout.setInlineStylesTheme(resourceObjectInstance);
        gui_Border_Layout.setName("Border_Layout");
        addComponent(gui_ScaleImageButton);
        addComponent(gui_Border_Layout);
        gui_SpanLabel.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
                gui_SpanLabel.setInlineStylesTheme(resourceObjectInstance);
        gui_SpanLabel.setName("SpanLabel");
                gui_ScaleImageButton_1.setInlineStylesTheme(resourceObjectInstance);
        gui_ScaleImageButton_1.setName("ScaleImageButton_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_ScaleImageButton_1,"\ue3f4".charAt(0));
        gui_Button.setText("Edit");
                gui_Button.setInlineStylesTheme(resourceObjectInstance);
        gui_Button.setName("Button");
        gui_Border_Layout.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_SpanLabel);
        gui_Border_Layout.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_ScaleImageButton_1);
        gui_Border_Layout.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Button);
        ((com.codename1.ui.layouts.LayeredLayout)gui_ScaleImageButton.getParent().getLayout()).setInsets(gui_ScaleImageButton, "0.0mm 0.0mm 56.89655% 2.645504mm").setReferenceComponents(gui_ScaleImageButton, "-1 -1 -1 -1").setReferencePositions(gui_ScaleImageButton, "0.0 0.0 0.0 0.0");
        ((com.codename1.ui.layouts.LayeredLayout)gui_Border_Layout.getParent().getLayout()).setInsets(gui_Border_Layout, "43.2266% 2.0447922% auto 4.761902mm").setReferenceComponents(gui_Border_Layout, "-1 -1 -1 -1").setReferencePositions(gui_Border_Layout, "0.0 0.0 0.0 0.0");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
