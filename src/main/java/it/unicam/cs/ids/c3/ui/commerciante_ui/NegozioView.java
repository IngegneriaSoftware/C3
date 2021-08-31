package it.unicam.cs.ids.c3.ui.commerciante_ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import it.unicam.cs.ids.c3.backend.entity.DescrizioneProdotto;
import it.unicam.cs.ids.c3.backend.entity.Negozio;
import it.unicam.cs.ids.c3.backend.entity.Prodotto;
import it.unicam.cs.ids.c3.backend.service.CommercianteService;
import it.unicam.cs.ids.c3.backend.service.DescrizioneProdottoService;
import it.unicam.cs.ids.c3.backend.service.NegozioService;
import it.unicam.cs.ids.c3.ui.commerciante_ui.CommercianteLayout;

import java.util.List;

@Route(value="negozio", layout = CommercianteLayout.class)
@PageTitle("Negozio")
public class NegozioView extends VerticalLayout {
    NegozioService negozioService;
    CommercianteService commercianteService;
    DescrizioneProdottoService descrizioneProdottoService;
    private Grid<Prodotto> grid = new Grid<>(Prodotto.class);
    private Select<Negozio> negozioSelect = new Select<>();
    private Select<DescrizioneProdotto> prodottiSelect = new Select<>();
    private Button searchButton = new Button(new Icon(VaadinIcon.SEARCH));
    private Button addProductButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE));
    private IntegerField qty = new IntegerField();

    public NegozioView(NegozioService negozioService, CommercianteService commercianteService, DescrizioneProdottoService descrizioneProdottoService) {
        this.negozioService = negozioService;
        this.commercianteService = commercianteService;
        this.descrizioneProdottoService = descrizioneProdottoService;
        addClassName("list-view");
        setSizeFull();
        HorizontalLayout horizontalLayout = new HorizontalLayout(negozioSelect, searchButton,prodottiSelect,addProductButton,qty);
        horizontalLayout.setAlignItems(Alignment.BASELINE);
        searchButton.addClickListener(e -> showNegozio(negozioSelect.getValue()));
        addProductButton.addClickListener(e->addToGrid());
        setComponents();
        configureGrid();
        add(horizontalLayout,grid);
       // updateList();
    }

    private void showNegozio(Negozio negozio) {
       // negozioService.getById(value.getId());
        if(negozio.getVetrina()!=null){
        updateList(negozio.getVetrina());
        }else{Notification notification = new Notification("Vetrina vuota", 3000);
            notification.open();}
    }

    private void updateList(List<Prodotto> list) {grid.setItems(list); }

    private void addToGrid() {
       Prodotto prodotto = new Prodotto(prodottiSelect.getValue(),qty.getValue());
       Negozio negozio = negozioSelect.getValue();
       negozio.getVetrina().add(prodotto);
        negozioService.save(negozio);
        showNegozio(negozio);
    }

    private void setComponents(){
        negozioSelect.setLabel("Negozio");
        negozioSelect.setItemLabelGenerator(Negozio::getNomeNegozio);
        negozioSelect.setItems(negozioService.findAll());
        prodottiSelect.setLabel("Prodotti");
        prodottiSelect.setItemLabelGenerator(DescrizioneProdotto::getNomeProdotto);
        prodottiSelect.setItems(descrizioneProdottoService.findAll());
        qty.setHasControls(true);
        qty.setMin(1);
        qty.setValue(1);
    }



    private void configureGrid() {
        grid.addClassName("vetrina-grid");
        grid.setSizeFull();
        grid.setColumns("descrizione.codiceProdotto", "descrizione.nomeProdotto", "descrizione.descrizioneProdotto","quantita");
    }
}
