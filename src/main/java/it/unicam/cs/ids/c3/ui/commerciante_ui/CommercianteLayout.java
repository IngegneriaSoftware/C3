package it.unicam.cs.ids.c3.ui.commerciante_ui;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import it.unicam.cs.ids.c3.ui.MainView;

public class CommercianteLayout extends AppLayout {
	private Tabs tabs = new Tabs();
	private Map<Class<? extends Component>, Tab> navigationTargetToTab = new HashMap<>();

	public CommercianteLayout() {
		Anchor logout = new Anchor("logout", "Log out");
		Image img = new Image("images/C3-logos_transparent.png","Logo");
		img.addClickListener(event -> {img.getUI().ifPresent(ui -> ui.navigate(MainView.class));});
		img.setHeight("50px");
		addMenuTab("Prodotti", DescrizioneProdottoView.class);
		addMenuTab("Negozi", NegozioView.class);
		addMenuTab("Crea Negozio", CreaNegozioView.class);
		addMenuTab("Crea Ordine", CreaOrdineView.class);
		addMenuTab("Aggiorna Ordine", AggiornaOrdineView.class);
		addToNavbar(img,tabs,logout);
	}

	private void addMenuTab(String label, Class<? extends Component> target) {
		Tab tab = new Tab(new RouterLink(label, target));
		navigationTargetToTab.put(target, tab);
		tabs.add(tab);
	}

}
