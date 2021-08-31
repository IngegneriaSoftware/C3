package it.unicam.cs.ids.c3.ui.cliente_ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import it.unicam.cs.ids.c3.ui.MainView;

import java.util.HashMap;
import java.util.Map;

public class ClienteLayout extends AppLayout {
    private Tabs tabs = new Tabs();
    private Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();

    public ClienteLayout() {
        Image img = new Image("images/C3-logos_transparent.png","Logo");
        img.addClickListener(event -> {img.getUI().ifPresent(ui -> ui.navigate(MainView.class));});
        img.setHeight("50px");
        addMenuTab("Mostra punti vendita", MostraPuntiVenditaView.class);
        addToNavbar(img,tabs);
    }

    private void addMenuTab(String label, Class<? extends Component> target) {
        Tab tab = new Tab(new RouterLink(label, target));
        navigationTargetToTab.put(target, tab);
        tabs.add(tab);
    }
}
