package it.unicam.cs.ids.c3.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import it.unicam.cs.ids.c3.ui.cliente_ui.MostraPuntiVenditaView;
import it.unicam.cs.ids.c3.ui.commerciante_ui.NegozioView;


public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }


    private void createHeader() {
        Image img = new Image("images/C3-logos_transparent.png","Logo");
        img.addClassName("logo");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), img);

        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.setHeight("70px");
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink commercianteLink = new RouterLink("Commerciante", NegozioView.class);
        RouterLink clienteLink = new RouterLink("Cliente", MostraPuntiVenditaView.class);
        RouterLink corriereLink = new RouterLink("Corriere", NegozioView.class);
        commercianteLink.setHighlightCondition(HighlightConditions.sameLocation());
        clienteLink.setHighlightCondition(HighlightConditions.sameLocation());
        corriereLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(commercianteLink,clienteLink,corriereLink));
    }
}
