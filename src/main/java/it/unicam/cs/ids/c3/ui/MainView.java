package it.unicam.cs.ids.c3.ui;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Home")
public class MainView extends VerticalLayout {

    public MainView() {
        this.setAlignItems(Alignment.CENTER);
        Image img = new Image("images/C3-logo.png","Logo");
        H1 h1 = new H1("Centro Commerciale in Centro");
        add(img,h1);
    }
}
