package it.unicam.cs.ids.c3.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.Categoria;
import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.service.NegozioService;

@Route(value = "puntivendita", layout = ClienteLayout.class)
@PageTitle("Mostra punti vendita")
public class MostraPuntiVenditaView extends VerticalLayout {

    private NegozioService negozioService;
    private Select<Categoria> categoriaSelect = new Select<>();
    private TextField filterText;
    private Grid<Negozio> negozioGrid = new Grid<>(Negozio.class);

    public MostraPuntiVenditaView(NegozioService negozioService) {
        this.negozioService = negozioService;
        add(setComponents(),configureGrid());
    }

    private HorizontalLayout setComponents() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setAlignItems(Alignment.BASELINE);
        categoriaSelect.setLabel("Categoria");
        categoriaSelect.setItems(Categoria.values());
        categoriaSelect.addValueChangeListener(e -> updateList(e.getValue()));
        filterText = new TextField();
        filterText.setSuffixComponent(new Icon(VaadinIcon.SEARCH));
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList(e.getValue()));
        layout.add(categoriaSelect, filterText);
        return layout;
    }

    private Grid<Negozio> configureGrid() {
       // negozioGrid.addClassName("negozio-grid");
       // negozioGrid.setSizeFull();
        negozioGrid.setColumns("nomeNegozio", "indirizzo","categoria");
        return negozioGrid;
    }

    private void updateList(String stringFilter) {
        negozioGrid.setItems(negozioService.findAll(stringFilter));
    }

    private void updateList(Categoria categoria) {
       negozioGrid.setItems(negozioService.findAll(categoria));
    }
}
